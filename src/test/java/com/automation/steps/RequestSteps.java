package com.automation.steps;

import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class RequestSteps {


    @When("user wants to call {string} endpoint")
    public void user_wants_to_call_endpoint(String endPoint) {
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @When("set {string} to {string}")
    public void set_to(String key, String value) {
       RestAssuredUtils.setHeader(key,value);
    }


    @And("send body from file {string}")
    public void sendBodyFromFile(String fileName) {
        RestAssuredUtils.sendBody(fileName);
    }
    @And("user perform post call")
    public void userPerformPostCall() {
        RestAssuredUtils.post();
    }


}
