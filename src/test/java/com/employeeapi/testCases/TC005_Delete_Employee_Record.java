package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC005_Delete_Employee_Record extends TestBase {

    @BeforeClass
    void updateEmployee() throws InterruptedException {
        logger.info("TC003 started");
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        response = httpRequest.request(Method.GET,"/employees");
        //Thread.sleep(5000);
        logger.info(response.getBody().asString());

        JsonPath jsonPathEvaluator = response.jsonPath();

        String empID = jsonPathEvaluator.get("[0].id");
        response = httpRequest.request(Method.DELETE,"/delete/"+ 1);
        //Thread.sleep(5000);
    }

    @Test
    void checkResponseBody() {
        String responseBody = response.getBody().asString();
        logger.info("Response is "+ responseBody);
        Assert.assertEquals(responseBody.contains("successfully! deleted Records"),true);

    }
}
