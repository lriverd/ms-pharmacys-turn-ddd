package cl.duamit.pharmacy.service;

import cl.duamit.pharmacy.core.Executor;
import cl.duamit.security.model.Token;
import cl.duamit.pharmacy.exception.PortabilityException;

/**
 *
 * Abstract service
 *
 * @author Luis Riveros
 * @version 1.0.0 - 03-08-2020
 * @since 1.0.0 - 03-08-2020
 */
public class AbstractService<T, R> implements Executor<R> {

    @Override
    public <T> T execute(R r, Token token) throws PortabilityException, Exception {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public <T> T execute(R r) throws PortabilityException, Exception {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public <T> T execute() throws PortabilityException, Exception {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
