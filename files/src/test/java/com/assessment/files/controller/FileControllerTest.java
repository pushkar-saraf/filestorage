package com.assessment.files.controller;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class FileControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @BeforeEach
    void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void givenValidRequest_whenSave_thenOk() {
        given()
                .body("{\n" +
                        "\t\"age\": 24,\n" +
                        "\t\"dob\": \"1997-11-08\",\n" +
                        "\t\"salary\": 10077,\n" +
                        "\t\"name\": \"John\"\n" +
                        "}")
                .header(new Header("fileType", "CSV"))
                .contentType(JSON)
                .when()
                .post("/file/")
                .then()
                .log().ifValidationFails()
                .statusCode(OK.value()).body(is("{\"status\":\"Success\",\"message\":\"\"}"));
    }

    @Test
    public void givenInvalidAgeDoBRequest_whenSave_thenBadRequest() {
        given()
                .body("{\n" +
                        "\t\"age\": 24,\n" +
                        "\t\"dob\": \"1990-11-08\",\n" +
                        "\t\"salary\": 10077,\n" +
                        "\t\"name\": \"John\"\n" +
                        "}")
                .header(new Header("fileType", "CSV"))
                .contentType(JSON)
                .when()
                .post("/file/")
                .then()
                .log().ifValidationFails()
                .statusCode(BAD_REQUEST.value()).body(is("{\"status\":\"Failed\",\"message\":\"Error in object 'dataDTO': codes [ValidDataDTO.dataDTO,ValidDataDTO]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [dataDTO.,]; arguments []; default message []]; default message [Invalid age for provided DoB]\"}"));
    }

}