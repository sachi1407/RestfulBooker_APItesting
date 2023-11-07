package com.restfulbooker.test.base;

import com.Restfulbooker.endpoints.APIConstant;
import com.Restfulbooker.modules.PayloadModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.restfulbooker.actions.AssertActions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

    public static RequestSpecification requestSpecification = RestAssured.given();
    public static RestAssured restAssured;
    public static Response response;
    public static ValidatableResponse validatableResponse;

    public static PayloadModule payloadModule;

    public static AssertActions assertActions;

    public static JsonPath jsonPath;

    @BeforeMethod

    public void setConfig() throws JsonProcessingException {
        payloadModule = new PayloadModule();
        assertActions = new AssertActions();
        requestSpecification.baseUri(APIConstant.Base_Url).contentType(ContentType.JSON);

    }



    public String createToken() throws JsonProcessingException {
        requestSpecification.baseUri(APIConstant.Base_Url).basePath("/auth").contentType(ContentType.JSON);
        response = requestSpecification.when().body(payloadModule.createToken()).post();
        validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
         return jsonPath.getString("token");

    }
}
