package other;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public abstract class HttpHelper {

    protected HttpUriRequest getRequest;
    protected HttpEntityEnclosingRequestBase postRequest;
    protected HttpResponse httpResponse;
    protected HttpClientContext context;

    public static HttpPost createPostRequest(String requestUri, String key, String header, String body) throws UnsupportedEncodingException {
        if (key != null) requestUri = requestUri.concat("?key=" + key);

        HttpPost localPostRequest = new HttpPost(requestUri);

        if (header != null) localPostRequest.addHeader("Content-Type", header);

        if (body != null) localPostRequest.setEntity(new StringEntity(body));

        return localPostRequest;
    }

    public static HttpGet createGetRequest(String requestUri, String key, String shortUrl) {
        if (key != null) requestUri = requestUri.concat("?key=" + key);

        if (shortUrl != null && key != null) {
            requestUri = requestUri.concat("&shortUrl=" + shortUrl);
        } else if (shortUrl != null) {
            requestUri = requestUri.concat("?shortUrl=" + shortUrl);
        }

        return new HttpGet(requestUri);
    }

}
