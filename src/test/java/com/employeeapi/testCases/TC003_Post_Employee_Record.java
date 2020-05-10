package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC003_Post_Employee_Record extends TestBase {

    String empName = RestUtils.empName();
    String empSalary = RestUtils.empSal();
    String empAge = RestUtils.empAge();

    @BeforeClass
    void createEmployee() throws InterruptedException {
        logger.info("TC003 started");
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name",empName);
        requestParams.put("salary",empSalary);
        requestParams.put("age",empAge);

        httpRequest.header("Content-Type","application/json");

        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST,"/create");
        Thread.sleep(5000);
    }

    @Test
    void checkResponseBody() {
        String responseBody = response.getBody().asString();
        logger.info("Response is "+ responseBody);
        Assert.assertEquals(responseBody.contains(empName),true);
        Assert.assertEquals(responseBody.contains(empSalary),true);
        Assert.assertEquals(responseBody.contains(empAge),true);
    }

    @Test
    void checkStatus() {
        int status = response.getStatusCode();
        Assert.assertEquals(status,200);
    }

    @AfterClass
    void tearDown() {
        logger.info("Test done");
    }
}
