package reqres.clients;

import java.util.Map;
import io.restassured.response.Response;

/**
 * RegisterClient handles user registration using the /register endpoint.
 */
public class RegisterClient extends BaseClient {
    public Response registerAccount(Map<String, String> userData) {
        // https://reqres.in/api/register
        return request
                .body(userData)
                .post("register");
    }
}