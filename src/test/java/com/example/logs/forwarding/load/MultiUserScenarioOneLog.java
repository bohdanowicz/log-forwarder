package com.example.logs.forwarding.load;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import java.time.Duration;

import com.example.logs.forwarding.utils.Utils;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class MultiUserScenarioOneLog extends Simulation {

    HttpProtocolBuilder httpProtocol = HttpDsl.http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json")
            .userAgentHeader("Gatling/Performance Test");

    ScenarioBuilder scn = CoreDsl.scenario("Load Test Forwarding Logs")
            .exec(http("forward-log-request")
                    .post("/api/logs")
                    .header("Content-Type", "application/json")
                    .body(StringBody(Utils.classpathFileToString("/requests/singleLogRequest.json")))
                    .check(status().is(204))
            );

    public MultiUserScenarioOneLog() {
        this.setUp(scn.injectOpen(
                        constantUsersPerSec(10).during(Duration.ofSeconds(15)),
                        constantUsersPerSec(100).during(Duration.ofSeconds(15)),
                        constantUsersPerSec(1000).during(Duration.ofSeconds(15))
                ))
                .protocols(httpProtocol);
    }
}
