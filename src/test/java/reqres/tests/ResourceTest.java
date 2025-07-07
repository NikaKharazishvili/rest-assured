package reqres.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import reqres.clients.ResourceClient;

/**
 * Tests the ResourceClient.
 */
public class ResourceTest {
    private final ResourceClient resourceClient = new ResourceClient();

    @Test(description = "Verify that getting list of resources returns status code 200 and non-empty data")
    public void testGetResourcesList() {
        Response response = resourceClient.getResourcesList();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, "Resources list should not be empty");
    }

    @Test(description = "Verify that getting single resource with ID=2 returns status 200 and correct ID")
    public void testGetResourceById() {
        int resourceId = 2;
        Response response = resourceClient.getResourceById(resourceId);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("data.id"), resourceId, "Resource ID should be 2");
    }

    @Test(description = "Verify that getting resource with non-existent ID returns 404")
    public void testGetResourceNotFound() {
        int nonExistentId = 23;
        Response response = resourceClient.getResourceById(nonExistentId);
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.getBody().asString().trim(), "{}", "Expected empty JSON object");
    }
}