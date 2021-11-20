import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import model.CustomModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_CustomResource extends BaseTest{
    CustomModel customModel =new CustomModel();
    Response response;
    @BeforeAll
    public void create_custom_data_for_test(){

        Map<String , Object> jsonBody = new HashMap<String , Object>();
        jsonBody.put("_quantity" , "1");
        jsonBody.put("name" , "name");
        jsonBody.put("lmd" , "dateTime");
        jsonBody.put("phoneNumber" , "phone");
        jsonBody.put("description" , "longText");
        System.out.println(jsonBody);
        response = fakerApiService.getByParams("/custom",jsonBody);


        System.out.println(response.asString());
        customModel.setName(response.jsonPath().getString("data.name"));
        customModel.setPhoneNumber(response.jsonPath().getString("data.phoneNumber"));
        customModel.setDescription(response.jsonPath().getString("data.description"));
        customModel.setDate(response.jsonPath().getString("data.lmd.date"));
        System.out.println(response.asString());
        System.out.println(customModel.toString());

    }
    @Test
    public void isResponseStatusOk()
    {
        Assertions.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void structureValidation() {
        String json =response.asString();
        assertThat(json, matchesJsonSchemaInClasspath("custom.json"));
    }

    @Test
    public void isResponseFieldNotNull()
    {
        Assertions.assertTrue(customModel.getName()!=null);
        Assertions.assertTrue(customModel.getDescription()!=null);
        Assertions.assertTrue(customModel.getDate()!=null);
        Assertions.assertTrue(customModel.getPhoneNumber()!=null);

    }


}
