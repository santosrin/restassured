package com.employeeapi.base;

import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;

public class TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;
    public String empID = "2";

    public Logger logger;

    @BeforeClass
    public void setup() {
        logger = Logger.getLogger("EmployeesRestAPI");
        PropertyConfigurator.configure("Log4j.properties");
        logger.setLevel(Level.DEBUG);
    }
}
