package com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TC001_GET_Request {

    @Test
    public void getWeatherDetails() {

        //Specify base uri
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city/";

        //Request object created
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.GET,"/Hyderabad");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is : " + responseBody);

        //Status code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: "+ statusCode);
        Assert.assertEquals(statusCode,200);

        //Status line
        String statusLine = response.getStatusLine();
        System.out.println("Status Line: "+ statusLine );
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

    }


}
