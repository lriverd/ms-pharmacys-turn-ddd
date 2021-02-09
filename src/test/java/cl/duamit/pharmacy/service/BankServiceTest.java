package cl.duamit.pharmacy.service;

import cl.duamit.bank.entitie.Bank;
import cl.duamit.bank.usecase.BankService;
import cl.duamit.shared.entitie.Rut;
import cl.duamit.pharmacy.ws.soap.BankServiceWS;
import com.bns.xsd.ib.msgs.bussupt.bldgequipandfac.siteops.banksinfoinq.v1.RetrieveBanksInfoInqRqPayload;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 06-08-2020
 * @since 1.0.0 - 06-08-2020
 */
@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {

    @Mock
    private BankServiceWS bankServiceWS;

    @InjectMocks
    private BankService service;

    @PostConstruct
    private void onInit() throws Exception {
        List<Bank> banks = new ArrayList<>();
        Bank b1 = new Bank();
        Bank b2 = new Bank();
        b1.setRut(new Rut(1, '9'));
        b2.setRut(new Rut(1, '9'));
        b1.setName("Banco 1");
        b2.setName("Banco 2");
        b1.setCode("001");
        b2.setCode("014");

        banks.add(b1);
        banks.add(b2);

        Mockito.when(bankServiceWS.callWS(Mockito.any(RetrieveBanksInfoInqRqPayload.class)))
                .thenThrow(new UnsupportedOperationException())
                .thenReturn(banks)
                .thenReturn(banks);

    }

    @Test
    public void testBanks() {
        Assert.assertTrue(service.execute().isEmpty());
        Assert.assertNull(service.getByCode("001"));
    }

}
