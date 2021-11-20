package service;

import client.RestAssuredClient;
import io.restassured.response.Response;

import java.util.Map;

public class FakerApiService extends RestAssuredClient {
    public FakerApiService(String baseUrl){
        super(baseUrl);
    }


    public Response get(String path){
        return super.get(path);

    }
    public Response getByParam(String path,String paramName , String value){
        return getWithParam(path, paramName , value);

    }
    public Response getByParams(String path,Map jsonBody){
        return getWithParams(path, jsonBody);

    }



}
