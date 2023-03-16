package com.example.logs.forwarding;

import static com.example.logs.forwarding.utils.Utils.classpathFileToString;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.testcontainers.shaded.org.awaitility.Awaitility.waitAtMost;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import com.example.logs.forwarding.destination.DBLogEntry;
import com.example.logs.forwarding.utils.TestKafkaConsumer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
@ActiveProfiles("itest")
class LogForwarderApplicationIT {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TestKafkaConsumer kafkaConsumer;

    @Autowired
    WebApplicationContext applicationContext;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.webAppContextSetup(applicationContext);
        RestAssuredMockMvc.basePath = LogController.PATH;
        mongoTemplate.dropCollection(DBLogEntry.class);
        kafkaConsumer.resetData();
    }

    @Test
    void shouldForwardLogEntryIntoAvailableChannels() throws InterruptedException {
        final var requestBody = classpathFileToString("requests/singleLogRequest.json");
        kafkaConsumer.setLatch(new CountDownLatch(1));
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        waitAtMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> assertThat(mongoTemplate.findAll(DBLogEntry.class))
                        .hasSize(1)
                        .first()
                        .returns("twill_booking_service", DBLogEntry::getApplicationID)
                        .returns("random123", DBLogEntry::getTraceId)
                        .returns("WARN", DBLogEntry::getSeverity)
                        .returns(1678952952l, DBLogEntry::getTimestamp)
                        .returns("Sth irritating happen.", DBLogEntry::getMessage)
                        .returns("", DBLogEntry::getComponentName)
                        .returns("request_123", DBLogEntry::getRequestId));

        boolean messageConsumed = kafkaConsumer.getLatch().await(1, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertThat(kafkaConsumer.getRecords()).hasSize(1)
                .first()
                .returns("twill_booking_service", ConsumerRecord::key)
                .satisfies(consumerRecord -> {
                    JSONAssert.assertEquals(
                            requestBody,
                            consumerRecord.value().toString(),
                            false
                    );
                });
    }

    @Test
    void shouldForwardLogMultipleLogEntriesIntoAvailableChannels() throws InterruptedException, JSONException {
        final var requestBody = classpathFileToString("requests/multipleLogsRequest.json");
        kafkaConsumer.setLatch(new CountDownLatch(2));
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post("/batch")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        waitAtMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> assertThat(mongoTemplate.findAll(DBLogEntry.class))
                        .hasSize(2)
                        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                        .containsExactlyInAnyOrder(
                                DBLogEntry.builder()
                                        .applicationID("twill_booking_service")
                                        .traceId("random123")
                                        .severity("WARN")
                                        .timestamp(1678952952l)
                                        .message("Sth irritating happen.")
                                        .componentName("")
                                        .requestId("request_123")
                                        .build(),
                                DBLogEntry.builder()
                                        .applicationID("twill_pricing_service")
                                        .traceId("random124")
                                        .severity("WARN")
                                        .timestamp(1678952953l)
                                        .message("Sth irritating happen.")
                                        .componentName("")
                                        .requestId("request_124")
                                        .build()
                        )
                );

        boolean messageConsumed = kafkaConsumer.getLatch().await(1, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertThat(kafkaConsumer.getRecords()).hasSize(2);
    }

}
