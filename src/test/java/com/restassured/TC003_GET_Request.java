package com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_GET_Request {

    @Test
    public void googleMapTest() {

        //Specify base uri
        RestAssured.baseURI = "https://maps.googleapis.com";

        //Request object created
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is : " + responseBody);

        //capture header details from headers
        String contentType = response.header("Content-Type");
        System.out.println("Content Type :" + contentType);
        Assert.assertEquals(contentType,"application/xml; charset=UTF-8");

        String contentEncoding = response.header("Content-Encoding");
        System.out.println("Content Type :" + contentEncoding);
        Assert.assertEquals(contentEncoding,"gzip");
    }
}
