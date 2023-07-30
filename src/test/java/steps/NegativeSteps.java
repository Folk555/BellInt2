package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import utils.WhetherConstants;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static utils.WhetherConstants.token;
import static utils.WhetherConstants.whetherURI;

public class NegativeSteps {
    private ValidatableResponse response;
    @When("посылаем get запрос, с невалидным api {string}")
    public void посылаемGetЗапросСНевалиднымApi(String whetherURI) {
        RequestSpecification specification = new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.URLENC)
                .setBaseUri(whetherURI)
                .addFilters(List.of(new ResponseLoggingFilter(), new RequestLoggingFilter()))
                .build()
                .param("access_key", token);

        response = given().spec(specification)
                .when()
                .param("query", "Saint Petersburg")
                .get()
                .then();
    }

    @Then("получаем ответ, где http статус {int}, а поле error.code {int}")
    public void получаемОтветГдеHttpСтатусАПолеErrorCode(int httpCode, int apiErrorCode) {
        response
                .assertThat().statusCode(httpCode)
                .assertThat().body("error.code", is(apiErrorCode));
    }

    @When("посылаем get запрос, с несуществующим городом {string}")
    public void посылаемGetЗапросСНесуществующимГородом(String incorrectCity) {
        RequestSpecification specification = new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.URLENC)
                .setBaseUri(whetherURI)
                .addFilters(List.of(new ResponseLoggingFilter(), new RequestLoggingFilter()))
                .build()
                .param("access_key", token);

        response = given().spec(specification)
                .when()
                .param("query", incorrectCity)
                .get()
                .then();
    }

    @When("посылаем get запрос, с невалидным токеном {string}")
    public void посылаемGetЗапросСНевалиднымТокеном(String invalidToken) {
        RequestSpecification specification = new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.URLENC)
                .setBaseUri(whetherURI)
                .addFilters(List.of(new ResponseLoggingFilter(), new RequestLoggingFilter()))
                .build()
                .param("access_key", invalidToken);

        response = given().spec(specification)
                .when()
                .param("query", "Moscow")
                .get()
                .then();
    }
}
