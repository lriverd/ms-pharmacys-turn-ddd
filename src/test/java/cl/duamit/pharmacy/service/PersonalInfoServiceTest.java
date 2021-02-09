package cl.duamit.pharmacy.service;

import cl.duamit.pharmacy.model.Person;
import cl.duamit.shared.entitie.Rut;
import cl.duamit.pharmacy.ws.domain.equifax.serialvalidation.UserInfoRs;
import cl.duamit.pharmacy.ws.rest.equifax.UserInfoService;
import cl.duamit.pharmacy.repository.requirements.RequirimentsRepository;
import cl.duamit.security.JwtProperties;
import cl.duamit.security.JwtTokenService;
import cl.duamit.pharmacy.ws.domain.admin.enterprise.Data;
import cl.duamit.pharmacy.ws.domain.admin.enterprise.EnterpriseRs;
import cl.duamit.pharmacy.ws.domain.admin.enterprise.Segment;
import cl.duamit.pharmacy.ws.domain.equifax.serialvalidation.IdCard;
import io.jsonwebtoken.lang.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 06-08-2020
 * @since 1.0.0 - 06-08-2020
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonalInfoServiceTest {

    @Mock
    private UserInfoService userInfoService;
    @Mock
    private RequirimentsRepository requirimentsRepository;
    @Mock
    private AdminEnterpriseService adminEnterpriseService;

    @InjectMocks
    private PersonalInfoService service;

    @Before
    public void setUp() throws Exception {
        UserInfoRs r1 = new UserInfoRs();
        UserInfoRs r2 = new UserInfoRs();
        IdCard card1 = new IdCard();
        IdCard card2 = new IdCard();
        card1.setCardStatus("V");
        card2.setCardStatus("I");
        r1.setIdCard(card1);
        r2.setIdCard(card2);
        Mockito.when(userInfoService.post(Mockito.any()))
                .thenReturn(r1)
                .thenReturn(r1)
                .thenReturn(r2)
                .thenThrow(new UnsupportedOperationException());

        EnterpriseRs enterpriseRs = new EnterpriseRs();
        EnterpriseRs enterpriseRs2 = new EnterpriseRs();
        enterpriseRs.setData(new Data());
        enterpriseRs2.setData(new Data());
        enterpriseRs.getData().setSegment(new Segment());
        enterpriseRs2.getData().setSegment(new Segment());
        enterpriseRs.getData().getSegment().setKey("12");
        enterpriseRs2.getData().getSegment().setKey("13");

        Mockito.when(adminEnterpriseService.execute(Mockito.any(Person.class)))
                .thenReturn(enterpriseRs)
                .thenReturn(enterpriseRs2)
                .thenReturn(enterpriseRs)
                .thenReturn(enterpriseRs);

        Mockito.when(requirimentsRepository.createTransaction(Mockito.any(Person.class)))
                .thenReturn(1);

        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("asdfasdfasdf");
        jwtProperties.setTimeExp(1000000);
        JwtTokenService jwtToken = new JwtTokenService();
        jwtToken.setJwtProperties(jwtProperties);
        service.setJwtToken(jwtToken);
        service.init();
    }

    @Test
    public void execute() {
        Person p = new Person();
        p.setUserId(new Rut(1, '9'));
        p.setCustomerId(new Rut(1, '9'));
        p.setDocumentNumber("A132456789");
        Assert.isTrue(!service.execute(p).getToken().isEmpty());
        Assert.isNull(service.execute(p).getToken());
        Assert.isNull(service.execute(p).getToken());
        Assert.isNull(service.execute(p).getToken());
    }
}