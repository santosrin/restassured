package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_Get_Single_Employee_Record  extends TestBase {


    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("Started test case 2");
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/employee/2";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"");
        response = httpRequest.request(Method.GET,"");

        Thread.sleep(3000);
    }

    @Test
    void checkResponseBody() {
        String responseBody = response.getBody().asString();
        logger.info(responseBody);
        Assert.assertEquals(responseBody.contains(empID),true);
    }

    @Test
    void checkStatusCode() {
        int checkStatusCode = response.getStatusCode();
        Assert.assertEquals(checkStatusCode,200);
    }


    @Test
    void checkResponseTime() {
        long checkResponseTime = response.getTime();
        Assert.assertTrue(checkResponseTime<2000);
    }

    @AfterClass
    void tearDown() {
        logger.info("Test case done ");
    }
}
