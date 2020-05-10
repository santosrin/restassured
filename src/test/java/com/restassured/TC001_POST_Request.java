package com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_POST_Request {

    @Test
    public void registrationSuccessfulAPI() {

        //Specify base uri
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        //Request object created
        RequestSpecification httpRequest = RestAssured.given();

        //Request payload sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("FirstName","Vishal121");
        requestParams.put("LastName","Vishal3212");
        requestParams.put("UserName","Vishal2212");
        requestParams.put("Password","Vishal112");
        requestParams.put("Email","Vishal4122@gmail.com");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString());

        //Response object
        Response response = httpRequest.request(Method.POST,"/register");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is : " + responseBody);

        //Status code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: "+ statusCode);
        Assert.assertEquals(statusCode,200);

        //Success code
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode,"OPERATION_SUCCESS");



    }
}
