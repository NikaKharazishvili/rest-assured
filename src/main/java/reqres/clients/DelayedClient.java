package reqres.clients;

import io.restassured.response.Response;

/**
 * Client for retrieving delayed user responses from the API.
 */
public class DelayedClient extends BaseClient {
    public Response delayed(int page) {
        // https://reqres.in/api/users?delay=3
        return request
                .queryParam("delay", page)
                .get("users");
    }
}