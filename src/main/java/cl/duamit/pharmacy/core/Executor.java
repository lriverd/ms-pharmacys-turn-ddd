package cl.duamit.pharmacy.core;

import cl.duamit.pharmacy.exception.PortabilityException;
import cl.duamit.security.model.Token;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 03-08-2020
 * @since 1.0.0 - 03-08-2020
 */
public interface Executor<R> {
    <T> T execute(R r, Token token) throws PortabilityException, Exception;
    <T> T execute(R r) throws PortabilityException, Exception;
    <T> T execute() throws PortabilityException, Exception;
}
