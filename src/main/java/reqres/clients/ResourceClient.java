package reqres.clients;

import io.restassured.response.Response;

/**
 * ResourceClient handles all API calls related to resources (unknown endpoint).
 * It supports retrieving the list of resources and individual resources by ID.
 */
public class ResourceClient extends BaseClient {
    public Response getResourcesList() {
        // https://reqres.in/api/unknown
        return request
                .get("unknown");
    }

    public Response getResourceById(int id) {
        // https://reqres.in/api/unknown/{id}
        return request
                .get("unknown/" + id);
    }
}