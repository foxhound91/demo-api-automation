package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import other.HttpHelper;
import other.UrlshortenerResponse;
import util.Utils;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MyStepdefs extends HttpHelper {

    private final String apiKey = "AIzaSyDtd0f0Y3oWSoI-nfXFn0BLrC3h3G3_Jx4";
    private final String requestUri = "https://www.googleapis.com/urlshortener/v1/url";

    @Given("^a valid urlshortener get request$")
    public void aValidUrlshortenerGetRequest() throws Throwable {
        getRequest = createGetRequest(requestUri, apiKey, "http://goo.gl/fbsS");
    }

    @Given("^an invalid urlshortener get request$")
    public void anInvalidUrlshortenerGetRequest() throws Throwable {
        getRequest = createGetRequest(requestUri, apiKey, "http://goo.gl/fccS");
    }

    @Given("^a urlshortener get request without key$")
    public void aUrlshortenerGetRequestWithoutKey() throws Throwable {
        getRequest = createGetRequest(requestUri, null, "http://goo.gl/fbsS");
    }

    @Given("^a urlshortener get request without shortUrl$")
    public void aUrlshortenerGetRequestWithoutShortUrl() throws Throwable {
        getRequest = createGetRequest(requestUri, apiKey, null);
    }

    @Given("^a urlshortener get request with empty key$")
    public void aUrlshortenerGetRequestWithEmptyKey() throws Throwable {
        getRequest = createGetRequest(requestUri, "", "http://goo.gl/fbsS");
    }

    @When("^the request is executed$")
    public void theRequestIsExecuted() throws Throwable {
        if (null != getRequest) {
            context = HttpClientContext.create();
            httpResponse = HttpClientBuilder.create().build().execute(getRequest, context);
        } else {
            httpResponse = HttpClientBuilder.create().build().execute(postRequest);
        }
    }

    @Then("^the default response content type is Json$")
    public void theDefaultResponseContentTypeIsJson() throws Throwable {
        String jsonMimeType = "application/json";
        String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }

    @Then("^the retrieved resource content is valid$")
    public void theRetrievedResourceContentIsValid(Map<String, String> map) throws Throwable {
        UrlshortenerResponse resource = Utils.retrieveResourceFromResponse(
                httpResponse, UrlshortenerResponse.class);
        assertEquals(map.get("value"), resource.getClass().getField(map.get("property")).get(resource));
    }

    @Then("^the response status is \"([^\"]*)\"$")
    public void theResponseStatusIs(int expectedHttpStatus) throws Throwable {
        assertEquals(expectedHttpStatus, httpResponse.getStatusLine().getStatusCode());
    }

    @Given("^a urlshortener post request without header$")
    public void aUrlshortenerPostRequestWithoutHeader() throws Throwable {
        postRequest = createPostRequest(requestUri, apiKey, null, "{\"longUrl\": \"http://www.google.com/\"}");
    }

    @Given("^a urlshortener post request with wrong header$")
    public void aUrlshortenerPostRequestWithWrongHeader() throws Throwable {
        postRequest = createPostRequest(requestUri, apiKey, "application/xml", "{\"longUrl\": \"http://www.google.com/\"}");
    }

    @Given("^a valid urlshortener post request$")
    public void aValidUrlshortenerPostRequest() throws Throwable {
        postRequest = createPostRequest(requestUri, apiKey, "application/json", "{\"longUrl\": \"http://www.google.com/\"}");
    }

    @Given("^a urlshortener post request without key$")
    public void aUrlshortenerPostRequestWithoutKey() throws Throwable {
        postRequest = createPostRequest(requestUri, null, "application/json", "{\"longUrl\": \"http://www.google.com/\"}");
    }

    @Given("^a urlshortener post request without body$")
    public void aUrlshortenerPostRequestWithoutBody() throws Throwable {
        postRequest = createPostRequest(requestUri, apiKey, "application/json", null);
    }

    @Then("^it redirect to the correct location$")
    public void itRedirectToTheCorrectLocation() throws Throwable {
        HttpHost target = context.getTargetHost();
        List<URI> redirectLocations = context.getRedirectLocations();
        URI location = URIUtils.resolve(getRequest.getURI(), target, redirectLocations);
        assertEquals("http://www.google.com/", location.toASCIIString());
    }

    @Given("^a valid short url request$")
    public void aValidShortUrlRequest() throws Throwable {
        getRequest = new HttpGet("http://goo.gl/fbsS");
    }

    @When("^the request is executed without redirect$")
    public void theRequestIsExecutedWithoutRedirect() throws Throwable {
        httpResponse = HttpClientBuilder.create().disableRedirectHandling().build().execute(getRequest, context);
    }

}