package reqres.clients;

import java.util.Map;
import io.restassured.response.Response;

/**
 * UserCrudClient handles creation, updating, and deletion of users via the /users endpoint.
 */
public class UserCrudClient extends BaseClient {
    public Response createUser(Map<String, String> userData) {
        // https://reqres.in/api/users
        return request
                .body(userData)
                .post("users");
    }

    public Response updateUserById(Map<String, String> userData, int id) {
        // https://reqres.in/api/users/{id}
        return request
                .body(userData)
                .put("users/" + id);
    }

    public Response updateUserById2(Map<String, String> userData, int id) {
        // https://reqres.in/api/users/{id}
        return request
                .body(userData)
                .patch("users/" + id);
    }

    public Response deleteUserById(int id) {
        // https://reqres.in/api/users/{id}
        return request
                .delete("users/" + id);
    }
}