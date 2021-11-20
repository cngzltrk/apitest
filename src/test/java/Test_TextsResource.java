import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test_TextsResource extends BaseTest{

    @Test
    @DisplayName("Checking fields")
    public void check_fields_isNotEmpty(){
        Response response = fakerApiService.getByParam("texts","_quantity","1");
        assertEquals(response.getStatusCode() , 200);
        assertTrue(response.jsonPath().get("data.title")!=null);
        assertTrue(response.jsonPath().get("data.author")!=null);
        assertTrue(response.jsonPath().get("data.genre")!=null);
        assertTrue(response.jsonPath().get("data.content")!=null);

    }
    @Test
    @DisplayName("Checking all fields")
    public void check_all_fields_isNotEmpty(){
        Response response = fakerApiService.get("texts");
        assertEquals(response.getStatusCode() , 200);
        List<Map> array=response.jsonPath().getList("data");

        for (Map var : array) {
            assertTrue(var.get("title")!=null);
            assertTrue(var.get("author")!=null);
            assertTrue(var.get("genre")!=null);
            assertTrue(var.get("content")!=null);
        }

    }



    @ParameterizedTest
    @MethodSource("provideCharacterCount")
    @DisplayName("Checking characters count is correct")
    public void check_all_characters_count_isCorrect(int value){
        Response response = fakerApiService.getByParam("texts","_characters",Integer.toString(value));
        assertEquals(response.getStatusCode() , 200);
        List<Map> array=response.jsonPath().getList("data");
        for (Map var : array    ) {
            assertTrue(var.get("content").toString().length()<= value,"Content charecter count more than "+ value );
            System.out.println(var.get("content"));
            System.out.println(var.get("content").toString().length());

        }
    }
    private static Stream<Arguments> provideCharacterCount(){
        return Stream.of(
                Arguments.of(0),
                Arguments.of(200),
                Arguments.of(500)
        );
    }
}
