import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Quantity Test")
public class Test_Quantity extends BaseTest{

    @Test
    @DisplayName("Companies quantity check")
    public void quantity_companies_test(){
        Response response = fakerApiService.getByParam("companies","_quantity","3");

        assertEquals(response.getStatusCode() , 200);

        assertEquals(3 , response.jsonPath().getInt("total"));
    }

    @Test
    public void quantity_test_with_negative_number(){
        Response response = fakerApiService.getByParam("companies","_quantity","-1");

        assertEquals(response.getStatusCode() , 200);

        assertEquals( "0", response.jsonPath().getString("total"));
    }

    @ParameterizedTest
    @MethodSource("provideQuantity")
    @DisplayName("Check qunatity endpoint")
    public void quantity_test(String path , String param , String value){
        Response response = fakerApiService.getByParam(path,param,value);

        assertEquals(response.getStatusCode() , 200);

        assertEquals(value , response.jsonPath().getString("total"));
    }

    private static Stream<Arguments> provideQuantity(){
        return Stream.of(
                Arguments.of("companies" , "_quantity" , "5"),
                Arguments.of("books" , "_quantity" , "2"),
                Arguments.of("books" , "_quantity" , "4")
        );
    }
}
