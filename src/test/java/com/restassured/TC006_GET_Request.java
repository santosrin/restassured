package com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC006_GET_Request {

    @Test
    public void getResponse() {

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,"/Bangalore");
        String responseBody = response.getBody().asString();
        System.out.println("Respons is: "+ responseBody);

        JsonPath jsonPath = response.jsonPath();
        String city = jsonPath.get("City").toString();
        System.out.println(jsonPath.get("City").toString());
        System.out.println(jsonPath.get("Temperature").toString());
        System.out.println(jsonPath.get("Humidity").toString());
        System.out.println(jsonPath.get("WeatherDescription").toString());
        System.out.println(jsonPath.get("WindSpeed").toString());
        System.out.println(jsonPath.get("WindDirectionDegree").toString());

        Assert.assertEquals(jsonPath.get("City").toString(),"Bengaluru");



    }
}
