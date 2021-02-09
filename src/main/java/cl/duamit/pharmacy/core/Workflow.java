package cl.duamit.pharmacy.core;

import org.springframework.http.MediaType;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 03-08-2020
 * @since 1.0.0 - 03-08-2020
 */
public class Workflow {

    private Class<? extends Executor> executor;
    private String token;
    private MediaType mediaType;

    private Object request;

    private HttpServletRequest httpServletRequest;
    private Errors errors;

    @Deprecated
    public static Workflow init() {
        return new Workflow(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE), null);
    }

    public static Workflow init(HttpServletRequest request) {
        return new Workflow(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE), request);
    }

    @Deprecated
    public static Workflow init(String token) {
        return new Workflow(token, MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE), null);
    }

    public static Workflow init(String token, HttpServletRequest request) {
        return new Workflow(token, MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE), request);
    }

    public Workflow(MediaType mediaType, HttpServletRequest request) {
        this.mediaType = mediaType;
        this.httpServletRequest = request;
    }

    public Workflow(String token, MediaType mediaType, HttpServletRequest request) {
        this.token = token;
        this.mediaType = mediaType;
        this.httpServletRequest = request;
    }

    public Workflow executor(Class<? extends Executor> executor){
        this.executor = executor;
        return this;
    }

    public Workflow execute(Object request){
        this.request = request;
        return this;
    }

    public Workflow errors(Errors errors){
        this.errors = errors;
        return this;
    }

    public Class<? extends Executor> getExecutor() {
        return executor;
    }

    public void setExecutor(Class<? extends Executor> executor) {
        this.executor = executor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }
}
