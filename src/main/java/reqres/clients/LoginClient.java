package reqres.clients;

import java.util.Map;
import io.restassured.response.Response;

/**
 * LoginClient handles user authentication via the /login endpoint.
 */
public class LoginClient extends BaseClient {
    public Response login(Map<String, String> userData) {
        // https://reqres.in/api/login
        return request
                .body(userData)
                .post("login");
    }
}