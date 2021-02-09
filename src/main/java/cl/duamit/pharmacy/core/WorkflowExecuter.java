package cl.duamit.pharmacy.core;

import cl.duamit.config.ApplicationContextProvider;
import cl.duamit.pharmacy.exception.PortabilityException;
import cl.duamit.security.model.Token;
import cl.duamit.security.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.TooManyListenersException;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 03-08-2020
 * @since 1.0.0 - 03-08-2020
 */
@Component
public class WorkflowExecuter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowExecuter.class);

    private RequestCount requestCount;
    private JwtTokenService jwtTokenService;

    public ResponseEntity<Object> execute(Workflow workflow) throws Exception {
        validateRequestCall(workflow);
        try {
            Executor executor = ApplicationContextProvider.getApplicationContext().getBean(workflow.getExecutor());
            return executeByJson(workflow, executor);
        } catch (PortabilityException pe) {
            throw pe;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    private ResponseEntity<Object> executeByJson(Workflow workflow, Executor executor) throws Exception {
        try {
            Object executed = null;
            Token token = null;
            if(Objects.nonNull(workflow.getToken())) {
                token = jwtTokenService.parse(workflow.getToken());
            }

            if (Objects.isNull(workflow.getRequest()) && Objects.isNull(workflow.getToken())) {
                executed =executor.execute();
            } else if (Objects.isNull(workflow.getToken())) {
                executed =executor.execute(workflow.getRequest());
            } else if (Objects.isNull(workflow.getRequest())){
                executed =executor.execute(token);
            }else{
                executed =executor.execute(workflow.getRequest(), token);
            }
            return ResponseEntity.ok(executed);
        } catch (Exception e) {
            throw e;
        }
    }

    private void validateRequestCall(Workflow workflow) throws Exception{
        countConnections(workflow);
        validateRequestError(workflow);
    }

    private void countConnections(Workflow workflow) throws TooManyListenersException{
        if(Objects.nonNull(workflow.getHttpServletRequest())){
            if(!requestCount.canAccess(workflow.getHttpServletRequest().getRemoteAddr())){
                throw new TooManyListenersException("User can't make more connections");
            }
        }
    }

    private void validateRequestError(Workflow workflow){
        if(Objects.nonNull(workflow.getErrors()) && workflow.getErrors().hasErrors()){
            throw new UnsupportedOperationException(workflow.getErrors().getAllErrors().get(0).toString());
        }
    }

    @Autowired
    public void setJwtTokenService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setRequestCount(RequestCount requestCount) {
        this.requestCount = requestCount;
    }

//    private ResponseEntity<Object> withException(PortabilityException e) {
//        Response response;
//
//        response = ErrorService.buildError(e);
//        return ResponseEntity.status(ObjectFactoryApi.getStatus(response)).contentType(MediaType.APPLICATION_JSON)
//                .cacheControl(DEFAULT_CACHE_CONTROL).body(response);
//    }
}
