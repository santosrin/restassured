package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_Get_All_Employees extends TestBase {

    @BeforeClass
    void getAllEmployees() throws InterruptedException {

        logger.info("****Started test case 1****");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employees");

        Thread.sleep(3);
    }

    @Test
    void checkResponseBody() {

        logger.info("Checking response body");

        String responseBody = response.getBody().asString();
        logger.info("Response body: "+ responseBody);
        Assert.assertTrue(responseBody!=null);
    }

    @Test
    void checkStatusCode() {

        logger.info("Checking checkStatusCode ");

        int statusCode = response.getStatusCode();
        logger.info("checkStatusCode : "+ statusCode);
        Assert.assertEquals(statusCode,200);
    }

    @Test
    void checkResponseTime() {

        logger.info("Checking checkResponseTime ");

        long responseTime = response.getTime();
        logger.info("checkResponseTime : "+ responseTime);

        if(responseTime>2000)
            logger.warn("response time greater than 2000");
        Assert.assertTrue(responseTime<2000);
    }

    @Test
    void checkStatusLine() {

        logger.info("Checking checkStatusLine ");

        String statusLine = response.getStatusLine();
        logger.info("checkStatusLine : "+ statusLine);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType() {

        logger.info("Checking checkContentType ");

        String contentType = response.header("Content-Type");
        logger.info("checkContentType : "+ contentType);
        Assert.assertEquals(contentType,"application/json;charset=utf-8");
    }

    @Test
    void checkServerType() {

        logger.info("Checking checkServerType ");

        String serverType = response.header("Server");
        logger.info("checkServerType : "+ serverType);
        Assert.assertEquals(serverType,"nginx/1.16.0");
    }

    @Test
    void checkContentEncoding() {

        logger.info("Checking checkContentEncoding ");

        String contentEncoding = response.header("Content-Encoding");
        logger.info("checkContentEncoding : "+ contentEncoding);
        Assert.assertEquals(contentEncoding,"gzip");
    }

    @Test
    void checkContentLenght() {

        logger.info("Checking checkContentLenght ");

        String contentLength = response.header("Content-Length");
        logger.info("contentLength : "+ contentLength);

        if(Integer.parseInt(contentLength)<100)
            logger.warn("contentLength is less than 100");

        Assert.assertTrue(Integer.parseInt(contentLength)>100);
    }

    @Test
    void checkCokies() {

        logger.info("Checking checkCokies ");

        String checkCookies = response.getCookie("PHPSESSID");

    }

    @AfterClass
    void tearDown() {
        logger.info("All testcases done");
    }
}
