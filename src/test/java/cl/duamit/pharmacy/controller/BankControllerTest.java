package cl.duamit.pharmacy.controller;

import cl.duamit.pharmacy.api.controller.BankController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * BankController Test
 *
 * @version 1.0.0 - 19 Oct 2020
 * @since 1.0.0 - 19 Oct 2020
 * @author Arturo Silva
 */
public class BankControllerTest {

    BankController api;

    @Before
    public void setUp() {
        api = new BankController();
        api.setWorkflowExecuter(new WorkflowExecuterMock());
    }

    @Test
    public void test() throws Exception {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        ResponseEntity<Object> response = api.getRegionList(mockedRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
