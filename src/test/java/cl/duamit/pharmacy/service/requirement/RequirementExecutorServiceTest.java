package cl.duamit.pharmacy.service.requirement;

import cl.duamit.pharmacy.model.Person;
import cl.duamit.pharmacy.model.mail.MailNotificationFilter;
import cl.duamit.pharmacy.model.requirement.CompleteRequirement;
import cl.duamit.pharmacy.model.requirement.Requirement;
import cl.duamit.pharmacy.model.requirement.RequirementMock;
import cl.duamit.security.model.Token;
import cl.duamit.pharmacy.model.security.TokenMock;
import cl.duamit.pharmacy.service.MailNotificationService;
import cl.duamit.pharmacy.service.OmdmService;
import cl.duamit.pharmacy.type.EnterpriseSegmentType;
import cl.duamit.pharmacy.type.OmdmDecisionType;
import cl.duamit.pharmacy.ws.domain.wsd.corporatetables.CorporateTableMock;
import cl.duamit.pharmacy.ws.domain.wsd.saverequest.SaveRequestRsMock;
import cl.duamit.pharmacy.ws.rest.wsd.SaveRequestService;
import cl.duamit.pharmacy.repository.requirements.RequirimentsRepository;
import cl.duamit.pharmacy.ws.domain.wsd.saverequest.SaveRequestRq;
import io.jsonwebtoken.lang.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RequirementExecutorServiceTest {

    @Mock
    private WdsCorporateTablesService wdsCorporateTablesService;
    @Mock
    private WdsAditionalDataService wdsAditionalDataService;
    @Mock
    private SaveRequestService saveRequestService;
    @Mock
    private WdsGeolocationService wdsGeolocationService;
    @Mock
    private MailNotificationService mailNotificationService;
    @Mock
    private RequirimentsRepository requirimentsRepository;
    @Mock
    private OmdmService omdmService;

    @InjectMocks
    private RequirementExecutorService service;

    @Before
    public void setUp() throws Exception {

        Mockito.when(wdsCorporateTablesService.execute(Mockito.any(EnterpriseSegmentType.class), Mockito.any(Token.class)))
                .thenReturn(new CorporateTableMock());
        Mockito.when(wdsAditionalDataService.execute(Mockito.any(CompleteRequirement.class)))
                .thenReturn(Boolean.TRUE);
        Mockito.when(saveRequestService.post(Mockito.any(SaveRequestRq.class)))
                .thenReturn(new SaveRequestRsMock());
        Mockito.when(wdsGeolocationService.execute(Mockito.any(Requirement.class), Mockito.any(Token.class)))
                .thenReturn(Boolean.TRUE);
        Mockito.when(mailNotificationService.execute(Mockito.any(MailNotificationFilter.class)))
                .thenReturn(Boolean.TRUE);
        Mockito.when(requirimentsRepository.getTransactionDetail(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn("");
        Mockito.when(omdmService.execute(Mockito.any(Person.class), Mockito.any(Token.class)))
                .thenReturn(OmdmDecisionType.ACEPTED);
        service.setOmdmStep(true);
    }

    @Test
    public void testExecute() throws Exception {
        Token t = new TokenMock();
        t.setCustomer(false);
        Assert.notNull(service.execute(new RequirementMock(), t));
    }

    @Test
    public void testExecuteNotificationException() throws Exception {
        Mockito.when(wdsAditionalDataService.execute(Mockito.any(CompleteRequirement.class)))
                .thenThrow(new NullPointerException("Null Exception"));
        Mockito.when(mailNotificationService.execute(Mockito.any(MailNotificationFilter.class)))
                .thenThrow(new NullPointerException("Null Exception"));
        Assert.notNull(service.execute(new RequirementMock(), new TokenMock()));
    }

    @Test
    public void testOmdmException() throws Exception {
        Mockito.when(omdmService.execute(Mockito.any(Person.class), Mockito.any(Token.class)))
                .thenThrow(new NullPointerException());
        Assert.isNull(service.execute(new RequirementMock(), new TokenMock()).getRequestNumber());
    }
}