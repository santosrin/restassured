package com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005_GET_Request {

    @Test
    public void validateResponse() {

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,"/Bangalore");
        String responseBody = response.getBody().asString();
        System.out.println("Response is : "+ responseBody);

        Assert.assertEquals(responseBody.contains("Bengaluru"),true);
    }
}
