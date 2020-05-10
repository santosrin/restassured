package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC004_Put_Employee_Record extends TestBase {

//    public static RequestSpecification httpRequest;
//    public static Response response;

    String empName = RestUtils.empName();
    String empSal = RestUtils.empSal();
    String empAge = RestUtils.empAge();


    @BeforeClass
    void updateEmployee() throws InterruptedException {
        logger.info("TC003 started");
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name",empName);
        requestParams.put("salary",empSal);
        requestParams.put("age",empAge);

        httpRequest.header("Content-Type","application/json");

        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.PUT,"/update/"+2);
        Thread.sleep(5000);

    }

    @Test
    void checkResponseBody() {
        String responseBody = response.getBody().asString();
        logger.info("Response is "+ responseBody);
        Assert.assertEquals(responseBody.contains(empName),true);
        Assert.assertEquals(responseBody.contains(empSal),true);
        Assert.assertEquals(responseBody.contains(empAge),true);
    }
}
