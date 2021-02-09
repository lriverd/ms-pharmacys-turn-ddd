package cl.duamit.pharmacy.model.requirement;

import cl.duamit.pharmacy.type.ContactByType;

public class LegalRepresentativeMock extends LegalRepresentative {
    public LegalRepresentativeMock() {
        setName("Representative");
        setPhone("+56948512369");
        setEmail("mail@enterprise.com");
        setContactBy(ContactByType.EMAIL);
    }
}