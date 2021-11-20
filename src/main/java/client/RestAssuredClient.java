package client;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import com.github.dzieciou.testing.curl.Options;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredClient {
    String baseUrl ;
    RequestSpecification reqSpec;
    Options options = Options.builder()
            .printMultiliner()
            .useShortForm()
            .build();
    RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig(options);


    public RestAssuredClient(String baseUrl){
        this.baseUrl = baseUrl ;
        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseUrl)
                .setConfig(config)
                .addFilter(new ResponseLoggingFilter())
                .build() ;
    }

    public Response get(String path){
        return
                given()
                        .spec(reqSpec)
                        .when()
                        .get(path);
    }

    public Response post(String path , Map object){
        return
                given()
                        .spec(reqSpec)
                        .body(object)
                        .when()
                        .post(path) ;
    }

    public Response getWithParam(String path , String paramName , String value){
        return
                given()
                        .spec(reqSpec)
                        .when()
                        .param(paramName , value)
                        .get(path) ;
    }
    public Response getWithParams(String path , Map map){
        return
                given()
                        .spec(reqSpec)
                        .when()
                        .params(map)
                        .get(path) ;
    }

    public Response delete(String path , String tokenCode){
        return
                given()
                        .spec(reqSpec)
                        .when()
                        .delete(path) ;
    }

    public ValidatableResponse put(){
        return null ;
    }
}
