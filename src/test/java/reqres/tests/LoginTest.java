package reqres.tests;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import reqres.clients.LoginClient;

/**
 * Tests the LoginClient.
 */
public class LoginTest {
    private final LoginClient loginClient = new LoginClient();

    @Test(description = "Successful login should return status 200 and a token")
    public void testLogin() {
        Map<String, String> userData = Map.of("email", "eve.holt@reqres.in", "password", "cityslicka");
        Response response = loginClient.login(userData);
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(response.jsonPath().getString("token"), "Token should be present");
    }

    @Test(description = "Login without password should return status 400 and an error message")
    public void testBadLogin() {
        Map<String, String> userData = Map.of("email", "eve.holt@reqres.in");
        Response response = loginClient.login(userData);
        Assert.assertEquals(response.statusCode(), 400, "Expected status code 400");
        Assert.assertEquals(response.jsonPath().getString("error"), "Missing password", "Expected error message");
    }
}