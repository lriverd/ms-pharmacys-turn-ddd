package cl.duamit.pharmacy.service.requirement;

import cl.duamit.bank.entitie.Bank;
import cl.duamit.shared.entitie.Rut;
import cl.duamit.bank.usecase.BankService;
import cl.duamit.pharmacy.ws.rest.wsd.AditionalDataService;
import cl.duamit.pharmacy.repository.requirements.RequirimentsRepository;
import cl.duamit.pharmacy.ws.domain.wsd.aditionaldata.AditionalDataRq;
import cl.duamit.pharmacy.ws.domain.wsd.templatedata.TemplateDataRs;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WdsAditionalDataServiceTest extends TestCase {

    @Mock
    private WdsTemplateDataService wdsTemplateDataService;
    @Mock
    private AditionalDataService aditionalDataService;
    @Mock
    private BankService bankService;
    @Mock
    private RequirimentsRepository requirimentsRepository;

    @InjectMocks
    private WdsAditionalDataService service;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        TemplateDataRs templateDataRs = new TemplateDataRs();
        templateDataRs.setTmpDataOrder("1");

        Mockito.when(wdsTemplateDataService.findTemplateData(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(templateDataRs);

        Mockito.when(aditionalDataService.post(Mockito.any(AditionalDataRq.class)))
                .thenReturn(Boolean.TRUE);
        //.thenReturn(Boolean.FALSE);
        //.thenThrow(new NullPointerException("Exception"));
        Bank bank = new Bank();
        bank.setCode("001");
        bank.setName("Banco del pais");
        bank.setRut(new Rut(1, '9'));
        Mockito.when(bankService.getByCode(Mockito.anyString()))
                .thenReturn(bank);
    }

    @Test
    public void test1() {
        Assert.assertTrue(service.execute(new CompleteRequirementMock()));
    }

    @Test
    public void test2() throws Exception {
        Mockito.when(aditionalDataService.post(Mockito.any(AditionalDataRq.class)))
                .thenReturn(Boolean.TRUE)
                .thenReturn(Boolean.FALSE)
                .thenThrow(new NullPointerException("Exception"));

        Assert.assertFalse(service.execute(new CompleteRequirementMock()));
    }
}