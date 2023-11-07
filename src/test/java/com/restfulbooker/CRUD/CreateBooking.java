package com.restfulbooker.CRUD;

import com.Restfulbooker.endpoints.APIConstant;
import com.Restfulbooker.modules.PayloadModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.restfulbooker.actions.AssertActions;
import com.restfulbooker.test.base.BaseTest;
import org.testng.annotations.Test;

public class CreateBooking extends BaseTest {

    String Bookinid;

    @Owner("Sachin chauhan")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC#1:Create the booking")
    @Test(groups = {"Stage", "P0"})

    public void createBooking() throws JsonProcessingException {
        AssertActions actions = new AssertActions();
        payloadModule = new PayloadModule();
        requestSpecification.baseUri(APIConstant.Base_Url).basePath(APIConstant.CREATE_BOOKING).contentType(ContentType.JSON);
        response = requestSpecification.when().body(payloadModule.createPayload()).post();
        validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(200);
        Bookinid = jsonPath.getString("bookingid");
        System.out.println("BookingID is----->>" + Bookinid);

    }

    @Owner("Sachin chauhan")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC#2:Verify the booking  is created without giving payload")
    @Test(groups = {"Stage", "P0"})
    public void createBooking_Negative() throws JsonProcessingException {
        AssertActions actions = new AssertActions();
        payloadModule = new PayloadModule();
        requestSpecification.baseUri(APIConstant.Base_Url).basePath(APIConstant.CREATE_BOOKING).contentType(ContentType.JSON);
        response = requestSpecification.when().body("").post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);



    }
}
