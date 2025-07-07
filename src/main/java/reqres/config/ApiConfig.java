package reqres.config;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

/**
 * ApiConfig provides the base configuration for all API requests.
 * It includes the base URI, headers, and content type.
 */
public class ApiConfig {
    private static final String BASE_URI = "https://reqres.in/api/";
    private static final String API_KEY_HEADER = "x-api-key";
    private static final String API_KEY = "reqres-free-v1";

    public static RequestSpecification getRequestSpec() {
        return given()
                .baseUri(BASE_URI)
                .header(API_KEY_HEADER, API_KEY)
                .contentType("application/json");
    }
}