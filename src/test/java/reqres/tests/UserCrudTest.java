package reqres.tests;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import reqres.clients.UserCrudClient;

/**
 * Tests the UserCrudClient.
 */
public class UserCrudTest {
    private final UserCrudClient userClient = new UserCrudClient();

    @Test(description = "Verify that creating a user returns status 201 and includes name, job, id, and createdAt fields")
    public void testCreateUser() {
        Map<String, String> userData = Map.of("name", "morpheus", "job", "leader");

        Response response = userClient.createUser(userData);
        Assert.assertEquals(response.statusCode(), 201, "Expected status code 201");
        Assert.assertEquals(response.jsonPath().getString("name"), "morpheus");
        Assert.assertEquals(response.jsonPath().getString("job"), "leader");
        Assert.assertNotNull(response.jsonPath().getString("id"), "User ID should be present");
        Assert.assertNotNull(response.jsonPath().getString("createdAt"), "Creation timestamp should be present");
    }

    @Test(description = "Verify that full update using PUT returns status 200 and updated name/job fields")
    public void testUpdateUser() {
        Map<String, String> userData = Map.of("name", "morpheus", "job", "zion resident");

        Response response = userClient.updateUserById(userData, 2);
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Assert.assertEquals(response.jsonPath().getString("name"), "morpheus");
        Assert.assertEquals(response.jsonPath().getString("job"), "zion resident");
    }

    @Test(description = "Verify that partial update using PATCH returns status 200 and updated name/job fields")
    public void testPartialUpdateUser() {
        Map<String, String> userData = Map.of("name", "morpheus", "job", "zion resident");

        Response response = userClient.updateUserById2(userData, 2); // fixed
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Assert.assertEquals(response.jsonPath().getString("name"), "morpheus");
        Assert.assertEquals(response.jsonPath().getString("job"), "zion resident");
    }

    @Test(description = "Verify that deleting a user returns status 204 with no content")
    public void deleteUserById() {
        Response response = userClient.deleteUserById(2);
        Assert.assertEquals(response.statusCode(), 204, "Expected status code 204");
        Assert.assertTrue(response.getBody().asString().isBlank(), "Expected empty response body");
    }
}