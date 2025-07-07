package reqres.clients;

import io.restassured.response.Response;

/**
 * UserClient handles all API calls related to users.
 * It supports listing users by page and getting a user by ID.
 */
public class UserClient extends BaseClient {
    public Response getUsersByPage(int page) {
        // https://reqres.in/api/users?page=2
        return request
                .queryParam("page", page)
                .get("users");
    }

    public Response getUserById(int id) {
        // https://reqres.in/api/users/{id}
        return request
                .get("users/" + id);
    }
}