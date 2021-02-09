package cl.duamit.pharmacy.service.requirement;

import cl.duamit.pharmacy.model.requirement.CompleteRequirement;
import cl.duamit.pharmacy.model.requirement.RequirementMock;
import cl.duamit.pharmacy.model.security.TokenMock;
import cl.duamit.pharmacy.ws.domain.wsd.saverequest.SaveRequestRqMock;
import cl.duamit.pharmacy.ws.domain.wsd.saverequest.SaveRequestRsMock;

public class CompleteRequirementMock extends CompleteRequirement {

    public CompleteRequirementMock() {
        setRequirement(new RequirementMock());
        setSaveRequestRq(new SaveRequestRqMock());
        setSaveRequestRs(new SaveRequestRsMock());
        setToken(new TokenMock());
    }
}
