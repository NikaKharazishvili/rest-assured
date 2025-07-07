package reqres.clients;

import io.restassured.specification.RequestSpecification;
import reqres.config.ApiConfig;

/**
 * BaseClient exists only to avoid repeating the request field and constructor in every client class.
 * All client classes extend this to access the preconfigured RequestSpecification.
 */
public class BaseClient {
    protected RequestSpecification request;

    public BaseClient()
    {
        this.request = ApiConfig.getRequestSpec();
    }
}