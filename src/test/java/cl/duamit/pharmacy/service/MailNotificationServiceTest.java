package cl.duamit.pharmacy.service;

import cl.duamit.shared.entitie.Rut;
import cl.duamit.pharmacy.model.mail.MailNotificationFilter;
import cl.duamit.pharmacy.ws.domain.notification.NotificationRq;
import cl.duamit.pharmacy.ws.domain.notification.NotificationRs;
import cl.duamit.pharmacy.ws.domain.notification.SendnotifCrossroadSBKResult;
import cl.duamit.pharmacy.ws.rest.notification.NotificationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class MailNotificationServiceTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private MailNotificationService service;

    @Before
    public void setUp() throws Exception {
        NotificationRs notificationRs = new NotificationRs();
        SendnotifCrossroadSBKResult sendnotifCrossroadSBKResult = new SendnotifCrossroadSBKResult();
        sendnotifCrossroadSBKResult.setStatus("Success");
        notificationRs.setResult(sendnotifCrossroadSBKResult);

        NotificationRs notificationRs2 = new NotificationRs();
        SendnotifCrossroadSBKResult sendnotifCrossroadSBKResult2 = new SendnotifCrossroadSBKResult();
        sendnotifCrossroadSBKResult2.setStatus("Error");
        notificationRs2.setResult(sendnotifCrossroadSBKResult2);

        Mockito.when(notificationService.post(Mockito.any(NotificationRq.class)))
                .thenReturn(notificationRs)
                .thenReturn(notificationRs2);
    }

    @Test
    public void testExecute() {
        Assert.assertTrue(service.execute(buildFilter()));
        Assert.assertFalse(service.execute(buildFilter()));
    }

    @Test
    public void testErrorService() throws Exception {
        Mockito.when(notificationService.post(Mockito.any(NotificationRq.class)))
                .thenThrow(new NullPointerException("Error"));
        Assert.assertFalse(service.execute(buildFilter()));
    }

    private MailNotificationFilter buildFilter() {
        MailNotificationFilter filter = new MailNotificationFilter();
        filter.setRequirementId("123");
        filter.setRequirementDate(LocalDate.now());
        filter.setPhone("+5691321654");
        filter.setEmail("lriv@mail.com");
        filter.setUserName("User");
        filter.setCustomerId(new Rut(1, '9'));
        return filter;
    }
}