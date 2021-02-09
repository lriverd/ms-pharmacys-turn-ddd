package cl.duamit.pharmacy.model.security;

import cl.duamit.security.model.Token;
import cl.duamit.shared.entitie.Rut;
import cl.duamit.pharmacy.type.EnterpriseSegmentType;

public class TokenMock extends Token {

    public TokenMock() {
        setCustomerId(new Rut(1,'9'));
        setUserId(new Rut(2,'7'));
        setUserName("Test User");
        setCustomer(true);
        setCiSerialnumber("A12345687");
        setSegment(EnterpriseSegmentType.PYME);
    }
}