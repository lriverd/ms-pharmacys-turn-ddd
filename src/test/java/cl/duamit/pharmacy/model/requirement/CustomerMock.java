package cl.duamit.pharmacy.model.requirement;

import cl.duamit.pharmacy.type.EnterpriseSegmentType;

public class CustomerMock extends Customer {
    public CustomerMock() {
        setAddress("Alameda");
        setBankId("001");
        setCommuneId("13101");
        setName("Enterprises Inc");
        setRegionId("13");
        setSegment(EnterpriseSegmentType.PYME);
    }
}