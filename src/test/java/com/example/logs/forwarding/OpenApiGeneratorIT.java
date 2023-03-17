package com.example.logs.forwarding;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

@SpringBootTest
@ActiveProfiles("itest")
class OpenApiGeneratorIT {

    @Autowired
    WebApplicationContext applicationContext;

    @Test
    void generateOpenApiSpec() throws Exception {
        var bytes = given()
                .webAppContextSetup(applicationContext)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .get("/v3/api-docs")
                .then()
                .extract().body().asByteArray();
        final byte[] yml = new YAMLMapper().writeValueAsBytes(new ObjectMapper().readTree(bytes));

        Files.write(Paths.get("target" + File.separator + "openapi.yml"), yml);
    }
}