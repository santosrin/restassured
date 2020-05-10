package com.restassured;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC007_GET_Request {

    @Test
    public void userAuth() {

        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

    //Basic authentications
        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName("ToolsQA");
        preemptiveBasicAuthScheme.setPassword("TestPassword");

        RestAssured.authentication=preemptiveBasicAuthScheme;


        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,"/");

        System.out.println(response.getBody().asString());

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);

        String sl = response.statusLine();
        System.out.println(sl);

        System.out.println(response.getTime());


    }
}
