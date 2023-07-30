package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class WhetherConstants {
    public static final String token = "6c4a25f7e55b2179ec97cf243d5080f8";
    public static final String whetherURI = "http://api.weatherstack.com/current";

    public static final RequestSpecification specification = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setContentType(ContentType.URLENC)
            .setBaseUri(whetherURI)
            //.addFilters(List.of(new ResponseLoggingFilter(), new RequestLoggingFilter()))
            .build()
            .param("access_key", token);
}
