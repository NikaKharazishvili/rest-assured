package reqres.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import reqres.clients.DelayedClient;

/**
 * Tests the DelayedClient.
 */
public class DelayedTest {
    private final DelayedClient delayedClient = new DelayedClient();

    @Test(description = "Verify that delayed user data returns status 200 and contains expected fields")
    public void testDelay() {
        Response response = delayedClient.delayed(2);
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");

        // Basic content checks
        Assert.assertEquals(response.jsonPath().getInt("per_page"), 6, "Expected per_page = 6");
        Assert.assertEquals(response.jsonPath().getInt("total_pages"), 2, "Expected total_pages = 2");
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, "Data list should not be empty");

        // Check first user data fields are present
        Assert.assertNotNull(response.jsonPath().getString("data[0].id"), "First user should have an ID");
        Assert.assertNotNull(response.jsonPath().getString("data[0].email"), "First user should have an email");
        Assert.assertNotNull(response.jsonPath().getString("data[0].first_name"),
                "First user should have a first name");
        Assert.assertNotNull(response.jsonPath().getString("data[0].last_name"), "First user should have a last name");
        Assert.assertNotNull(response.jsonPath().getString("data[0].avatar"), "First user should have an avatar");

        // Optional: Support text
        Assert.assertTrue(response.jsonPath().getString("support.text").contains("Content Caddy"),
                "Support text should mention 'Content Caddy'");
    }
}