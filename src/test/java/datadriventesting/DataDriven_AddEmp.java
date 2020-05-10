package datadriventesting;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import java.io.IOException;

public class DataDriven_AddEmp {

    @Test(dataProvider = "empDataProvider")
    public void addNewEmp(String name, String salary, String age) {

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        RequestSpecification httpRequest = RestAssured.given();

        //Prepare request body for POST call with JSONObject class
        JSONObject requestParams = new JSONObject();
        requestParams.put("name",name);
        requestParams.put("salary",salary);
        requestParams.put("age",age);

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString());

        Response response = httpRequest.request(Method.POST,"/create");

        String responseBody = response.getBody().asString();
        System.out.println("Response is :" + responseBody);

        Assert.assertEquals(responseBody.contains(name),true);
        Assert.assertEquals(responseBody.contains(salary),true);
        Assert.assertEquals(responseBody.contains(age),true);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }


    @DataProvider(name="empDataProvider")
    Object[][] getEmpData() throws IOException {

        //Read data from excel
        String filePath = System.getProperty("user.dir")+"/src/test/java/datadriventesting/EmpData.xlsx";
        int rowNo = XLSUtils.getRowCount(filePath,"Sheet1");
        int colNo = XLSUtils.getCellCount(filePath,"Sheet1",1);

        System.out.println(rowNo);
        System.out.println(colNo);

        String empdata[][] = new String[rowNo][colNo];
        System.out.println(empdata.length);

        for(int i=1; i<=rowNo; i++) {
            for(int j=0; j<colNo; j++) {
                empdata[i-1][j] = XLSUtils.getCellData(filePath,"Sheet1",i,j);
            }
        }


        //String empdata[][] = {{"abc123","34884","56"}, {"abc1234","348834","76"}, {"abcds123","348824","96"}};
        return empdata;
    }
}
