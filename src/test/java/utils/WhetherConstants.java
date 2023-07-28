package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class WhetherConstants {
    public static final String token = "3b63c1405e3a96a7b9a313d5dee34cc8";
    public static final String whetherURI = "http://api.weatherstack.com/current";

    public static final RequestSpecification specification = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setContentType(ContentType.URLENC)
            .setBaseUri(whetherURI)
            //.addFilters(List.of(new ResponseLoggingFilter(), new RequestLoggingFilter()))
            .build()
            .param("access_key", token);
}
