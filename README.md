# Log forwarder

Log forwarder is a simple application aimed at passing the logs which are coming through HTTP endpoint to chosen channels.
In this case there are:
- mongo db
- kafka

Logs can be delivered through two endpoints:
- single entry - <code>POST /api/logs </code>
- array of entries - <code>POST /api/logs/batch </code>

OpenAPI doc defining communication is generated based on controller <code>com.example.logs.forwarding.LogController</code>.
It is exposed on path <code>/v3/api-docs</code> when application is running. Test <code>com.example.logs.forwarding.OpenApiGeneratorIT</code> is storing
generated specification in target directory. I added it manually to <code>docs/openapi.yml</code>.

### Running

Application is dependent on mongodb and kafka instances. To run it locally you should set them up
e.g. with docker-compose. <code>application-local.yml</code> provides default connection config. 

### Testing

- There are sample unit tests to check isolated business logic like <code>com.example.logs.forwarding.destination.KafkaLogChannelTest</code>
- There is controller layer test to check api contracts <code>com.example.logs.forwarding.LogControllerTest</code>
- There is full integration test to check end2end flow relying with test containers (kafka + mongo) <code>com.example.logs.forwarding.LogForwarderApplicationIT</code>
- There are also load test scenarios (<code>com.example.logs.forwarding.load</code>) based on gatling framework which show application behavior under increased loads.

### Load testing

For load testing [Gatling framework] (https://gatling.io/docs/gatling/tutorials/quickstart/) was used.
There are two scenarios: one per endpoint (single log entry, multiple log entries). Both of them are running in the same way:
15 sec with 10 users, 15 sec with 100 users, 15 sec with 1000 users. 
Reports from tests were added to docs folder:
- docs/multiuserscenarioonelog
- docs/multiuserscenariobatchlogs

You can cleary see there that 1000 users is generating load that cannot be handled by the server (large number of connection refused/timeouts errors).
Reason for that is the number of threads on the server side (200 by default for tomcat).

### Out of scope

Things which were not in the scope of this project:
- security
- test coverage setting
- deployment