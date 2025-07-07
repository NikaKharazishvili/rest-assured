package reqres.clients;

import io.restassured.response.Response;

/**
 * Client for retrieving delayed user responses from the API.
 */
public class DelayedClient extends BaseClient {
    public Response delayed(int delayTime) {
        // https://reqres.in/api/users?delay=2
        return request
                .queryParam("delay", delayTime)
                .get("users");
    }
}