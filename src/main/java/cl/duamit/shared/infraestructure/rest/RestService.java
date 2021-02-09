package cl.duamit.shared.infraestructure.rest;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Abstract class from restservices
 *
 * @author Luis Riveros
 * @version 1.0.0 - 28-11-2020
 * @since 1.0.0 - 28-11-2020
 */
public abstract class RestService<Q, S> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RestService.class);

    private Map<String, String> pathParams = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private HttpHeaders httpHeaders;

    private String url;
    private String tmplUrl;
    private RestTemplate restTemplate;

    protected abstract Class<Q> type();

    public void pathParams(Map<String, String> pathParams) {
        this.pathParams = pathParams;
    }

    public void queryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public void httpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    /**
     * @return
     */
    public Q get() throws Exception{
        return exchange(null, HttpMethod.GET);
    }

    /**
     * @param rq
     * @return
     */
    public Q get(S rq) throws Exception{
        return exchange(rq, HttpMethod.GET);
    }

    /**
     * @param rq
     * @return
     */
    public Q post(S rq) throws Exception{
        return exchange(rq, HttpMethod.POST);
    }

    /**
     * Method to exchange rest services
     *
     * @param rq
     * @param httpMethod
     * @return Q Class defined on class
     */
    private Q exchange(S rq, HttpMethod httpMethod) throws Exception {
        this.buildUrl();

        if (Objects.isNull(this.httpHeaders) && Objects.nonNull(rq)) {
            this.httpHeaders = new HttpHeaders();
            this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        }

        HttpEntity<S> entity = new HttpEntity<>(rq, this.httpHeaders);

        try {
            ResponseEntity<Q> httpResponse = getRestTemplate().exchange(getUrl(), httpMethod, entity, type());
            return httpResponse.getBody();
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " : " + ((HttpClientErrorException) e).getResponseBodyAsString(), e);
            throw e;
        }
    }

    /**
     * Method that build the url based on tmplUrl and getting
     * query and path params
     */
    private void buildUrl() {
        UriComponentsBuilder ucb = UriComponentsBuilder.fromUriString(this.tmplUrl);

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            ucb.queryParam(entry.getKey(), entry.getValue());
        }

        this.url = ucb.buildAndExpand(pathParams).toString();
    }

    private RestTemplate getRestTemplate() {
        if (this.restTemplate == null) {
            this.restTemplate = new RestTemplate();
        }
        this.configureRestTemplate();

        return this.restTemplate;
    }

    private void configureRestTemplate() {
        if (this.tmplUrl.startsWith("https")) {
            configureSecureRestTemplate();
            return;
        }
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(10000);
        this.restTemplate.setRequestFactory(factory);
    }

    private void configureSecureRestTemplate() {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        try {
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();

            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            };
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, new TrustManager[]{tm}, null);
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(csf)
                    .build();
            requestFactory.setHttpClient(httpClient);
        } catch (Exception e) {
            LOGGER.error("Can't configure resttemplate as https connection, continue like simple http method", e);
        }

        requestFactory.setConnectTimeout(10000);
        requestFactory.setReadTimeout(10000);
        this.restTemplate.setRequestFactory(requestFactory);
    }


    private String getUrl() {
        return url;
    }

    public void setTmplUrl(String tmplUrl) {
        this.tmplUrl = tmplUrl;
    }
}
