package integration.org.mapeiapoa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapeiapoa.MapeiapoaApplication;
import org.mapeiapoa.domain.Incident;
import org.mapeiapoa.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
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
        incidentOne = new Incident("01", "Incident One", "1", "1");
        incidentTwo = new Incident("02", "Incident Two", "2", "2");
        RestAssured.port = serverPort;
    }

    @Test
    public void shouldSaveAndFetchIncidents() throws JsonProcessingException {
        given()
            .body(toJson(incidentOne))
            .contentType(JSON)
        .when()
            .post("/")
        .then()
            .statusCode(CREATED.value())
            .body("id", is("01"))
            .body("descricao", is("Incident One"));

        given()
            .body(toJson(incidentTwo))
            .contentType(JSON)
        .when()
            .post("/")
        .then()
            .statusCode(CREATED.value())
            .body("id", is("02"))
            .body("descricao", is("Incident Two"));

        when()
            .get("/")
        .then()
            .statusCode(OK.value())
            .body("id", contains("01", "02"))
            .body("descricao", contains("Incident One", "Incident Two"));

    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(object);
        return jsonInString;
    }
}
