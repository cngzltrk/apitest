import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import service.FakerApiService;
import service.ServiceConstants;

public class BaseTest {

    static FakerApiService fakerApiService;

    @BeforeAll
    public static void setUp(){
        String URL= ServiceConstants.URL;
        fakerApiService = new FakerApiService(URL);
    }
}
