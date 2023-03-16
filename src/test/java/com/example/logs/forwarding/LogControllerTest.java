package com.example.logs.forwarding;


import static com.example.logs.forwarding.utils.Utils.classpathFileToString;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(LogController.class)
class LogControllerTest {

    @MockBean
    LogForwardingService logForwardingService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.basePath = LogController.PATH;
    }

    @Test
    void shouldPassLogRequestToLogForwarderService() {

        final var requestBody = classpathFileToString("requests/singleLogRequest.json");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        verify(logForwardingService).forwardLogEntry(new LogEntry(
                "twill_booking_service",
                "random123",
                "WARN",
                1678952952l,
                "Sth irritating happen.",
                "",
                "request_123"
        ));
    }

    @Test
    void shouldReturn400InCaseOfIncompleteLogEntry() {

        final var requestBody = classpathFileToString("requests/singleLogBadRequest.json");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

        verifyNoInteractions(logForwardingService);
    }

    @Test
    void shouldPassBulkOfLogsToLogForwarderService() {

        final var requestBody = classpathFileToString("requests/multipleLogsRequest.json");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post("/batch")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());


        ArgumentCaptor<LogEntry> captor = ArgumentCaptor.forClass(LogEntry.class);
        verify(logForwardingService, times(2)).forwardLogEntry(captor.capture());

        assertThat(captor.getAllValues())
                .containsExactly(
                        new LogEntry(
                                "twill_booking_service",
                                "random123",
                                "WARN",
                                1678952952l,
                                "Sth irritating happen.",
                                "",
                                "request_123"
                        ),
                        new LogEntry(
                                "twill_pricing_service",
                                "random124",
                                "WARN",
                                1678952953l,
                                "Sth irritating happen.",
                                "",
                                "request_124"
                        )
                );
    }
}