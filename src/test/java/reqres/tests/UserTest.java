package reqres.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.clients.UserClient;

/**
 * Tests the UserClient.
 */
public class UserTest {
    private final UserClient userClient = new UserClient();

    @Test(description = "Verify that page=2 returns status code 200 and contains a list of users")
    public void testListUsersPage() {
        int page = 2;
        Response response = userClient.getUsersByPage(page);
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, "User list should not be empty");
    }

    @Test(description = "Verify that getting user with ID = 2 returns correct user and status code 200")
    public void testGetUserById() {
        int userId = 2;
        Response response = userClient.getUserById(userId);
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Assert.assertEquals(response.jsonPath().getInt("data.id"), userId, "User ID should be 2");
    }

    @Test(description = "Verify that getting user with non-existent ID returns 404")
    public void testUserNotFound() {
        int badUserId = 23;
        Response response = userClient.getUserById(badUserId);
        Assert.assertEquals(response.statusCode(), 404, "Expected status code 404 for user not found");
    }
}