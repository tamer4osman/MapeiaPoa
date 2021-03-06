package integration.org.mapeiapoa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapeiapoa.MapeiapoaApplication;
import org.mapeiapoa.domain.Incident;
import org.mapeiapoa.domain.Types;
import org.mapeiapoa.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static common.JsonUtil.toJson;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mapeiapoa.domain.Types.FIRE;
import static org.mapeiapoa.domain.Types.OVERFLOW;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@TestPropertySource("/test.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MapeiapoaApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MapeiaPoaIntegration {

    @Autowired
    private IncidentRepository repository;
    @Value("${local.server.port}")
    private int serverPort;
    private Incident incidentOne;
    private Incident incidentTwo;

    @Before
    public void setUp() {
        repository.deleteAll();
        incidentOne = new Incident("01", "Incident One", "1", "1", FIRE);
        incidentTwo = new Incident("02", "Incident Two", "2", "2", OVERFLOW);
        RestAssured.port = serverPort;
    }

    @Test
    public void shouldSaveAndFetchIncidents() throws JsonProcessingException {
        given()
            .body(toJson(incidentOne))
            .contentType(JSON)
        .when()
            .post("/incident")
        .then()
            .statusCode(CREATED.value())
            .body("id", is("01"))
            .body("description", is("Incident One"))
            .body("type", is("FIRE"));

        given()
            .body(toJson(incidentTwo))
            .contentType(JSON)
        .when()
            .post("/incident")
        .then()
            .statusCode(CREATED.value())
            .body("id", is("02"))
            .body("description", is("Incident Two"))
            .body("type", is("OVERFLOW"));

        when()
            .get("/incident")
        .then()
            .statusCode(OK.value())
            .body("id", contains("01", "02"))
            .body("description", contains("Incident One", "Incident Two"))
            .body("type", contains("FIRE", "OVERFLOW"));

    }
}
