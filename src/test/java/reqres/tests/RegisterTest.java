package reqres.tests;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import reqres.clients.RegisterClient;

/**
 * Tests the RegisterClient.
 */
public class RegisterTest {
    private final RegisterClient registerClient = new RegisterClient();

    @Test(description = "Successful registration should return 200 and include user ID and token")
    public void testRegisterAccountSuccessfully() {
        Map<String, String> userData = Map.of("email", "eve.holt@reqres.in", "password", "pistol");
        Response response = registerClient.registerAccount(userData);
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(response.jsonPath().getString("id"), "ID should be present");
        Assert.assertNotNull(response.jsonPath().getString("token"), "Token should be present");
    }

    @Test(description = "Registration without password should return 400 and error message")
    public void testRegisterAccountWithoutPassword() {
        Map<String, String> userData = Map.of("email", "eve.holt@reqres.in");
        Response response = registerClient.registerAccount(userData);
        Assert.assertEquals(response.statusCode(), 400, "Expected status code 400");
        Assert.assertEquals(response.jsonPath().getString("error"), "Missing password", "Expected error message");
    }
}