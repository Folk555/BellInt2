package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utils.WhetherConstants.specification;

public class WhetherApiSteps {

    private ValidatableResponse currentResponse;
    private static boolean scenarioFail = false;

    @When("посылаем get запрос, в котором указываем {string}")
    public void посылаемGetЗапросВКоторомУказываемCity(String city) {
        //if (!city.equals("Saint Petersburg")) return; //временно чтобы не проходить все кейсы

        currentResponse = given().spec(specification)
                .when()
                .param("query", city)
                .get()
                .then()
                .statusCode(200)
                .assertThat().body("$", not(hasKey("error")))
                .assertThat().body("error", not(hasKey("code")));
    }

    @Then("получаем ответ, где температура города между {string} и {string} градусами")
    public void получаемОтветГдеТемператураГородаМеждуИГрадусами(String arg0, String arg1) {
        int lowerTempr = Integer.parseInt(arg0);
        int upperTempr = Integer.parseInt(arg1);
        int actualTempr = currentResponse.extract().jsonPath().getInt("current.temperature");

        if ((actualTempr < lowerTempr) || (actualTempr > upperTempr)) {
            String errorDescription = "Значение поля current.temperature {" + actualTempr + "} не находится " +
                    "в интервале" + lowerTempr + " - " + upperTempr;
            System.out.println(errorDescription);
            Allure.addAttachment("Ошибка", "text/plain", errorDescription);
            scenarioFail=true;
        }
    }

    @And("{string} соответствует ожидаемой стране города")
    public void соответствуетОжидаемойСтранеГорода(String expectedCountry) {
        String actualCountry = currentResponse.extract().jsonPath().getString("location.country");

        if (!expectedCountry.equals(actualCountry)) {
            String errorDescription = "Значение поля location.country {" + actualCountry + "} в теле ответа " +
                    "не совпало с ожидаемым {"+expectedCountry+"}";
            System.out.println(errorDescription);
            Allure.addAttachment("Ошибка", "text/plain", errorDescription);
            scenarioFail=true;
        }
    }

    @And("скорость ветра соответствует случайно генерируемому числу")
    public void скоростьВетраСоответствуетСлучайноГенерируемомуЧислу() {
        int actualWindSpeed = currentResponse.extract().jsonPath().getInt("current.wind_speed");
        int randomWindSpeed = (int) (Math.random() * 15 + 15);

        if (actualWindSpeed != randomWindSpeed) {
            String errorDescription = "Значение поля current.wind_speed {"+actualWindSpeed+"} в теле ответа " +
                    "не совпало с ожидаемым {"+randomWindSpeed+"}";
            System.out.println(errorDescription);
            Allure.addAttachment("Ошибка", "text/plain", errorDescription);
            scenarioFail=true;
        }
    }

    @After
    public static void failScenarioIfDivergence() {
        Assertions.assertFalse(scenarioFail, "Были расхождение с ожидаемыми значениями");
    }
}
