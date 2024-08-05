package com.automation.utils;

import com.automation.pojo.CreateTokenPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    static RequestSpecification requestSpecification = RestAssured.given();
    static String endPoint;
    static Response response;

    public static void setEndPoint(String endPoint) {
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setHeader(String key, String value) {
        requestSpecification = requestSpecification.header(key, value);
    }


    public static void sendBody(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        CreateTokenPojo createTokenPojo;
        try {
            createTokenPojo = mapper.readValue(readData(fileName), CreateTokenPojo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        requestSpecification = requestSpecification.body(createTokenPojo);
    }

    public static String readData(String fileName) {
        String path = ConfigReader.getValue("folder.path") + fileName;
        String content;
        try {
            content = new Scanner(new FileInputStream(path)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    public static Response post() {
        response = requestSpecification.post(endPoint);
        response.then().log().all();
        return response;
    }

    public static int getStatusCode() {
        return response.getStatusCode();
    }

    public static void setTokenId(String tokenId) {
        ConfigReader.setValue(tokenId, response.jsonPath().getString("token"));
        System.out.println(ConfigReader.getValue(tokenId));
    }
}
