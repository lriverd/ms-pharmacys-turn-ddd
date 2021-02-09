package cl.duamit.pharmacy.service;

import cl.duamit.pharmacy.model.Person;
import cl.duamit.shared.entitie.Rut;
import cl.duamit.pharmacy.model.security.TokenMock;
import cl.duamit.pharmacy.type.OmdmDecisionType;
import cl.duamit.pharmacy.ws.soap.OmdmServiceWS;
import cl.duamit.pharmacy.repository.requirements.RequirimentsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OmdmServiceTest {

    @Mock
    private OmdmServiceWS omdmServiceWS;
    @Mock
    private RequirimentsRepository requirimentsRepository;

    @InjectMocks
    private OmdmService service;


    @Before
    public void setUp() throws Exception {
        Mockito.when(omdmServiceWS.callWS(Mockito.any(Rut.class)))
                .thenReturn(OmdmDecisionType.ACEPTED);
    }

    @Test
    public void execute() throws Exception{
        Person p = new Person();
        p.setCustomerId(new Rut(1, '9'));
        service.asyncExecute(p, new TokenMock());
        Assert.assertEquals(OmdmDecisionType.ACEPTED, service.execute(p, new TokenMock()));
    }

    @Test
    public void executeException() throws Exception{
        Mockito.when(omdmServiceWS.callWS(Mockito.any(Rut.class)))
                .thenThrow(new NullPointerException(""));
        Person p = new Person();
        p.setCustomerId(new Rut(1, '9'));
        Assert.assertEquals(OmdmDecisionType.REJECTED, service.execute(p, new TokenMock()));
    }
}