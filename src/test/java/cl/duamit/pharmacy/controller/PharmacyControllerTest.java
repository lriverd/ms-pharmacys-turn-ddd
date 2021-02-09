package cl.duamit.pharmacy.controller;

import cl.duamit.pharmacy.api.controller.PharmacyController;
import cl.duamit.pharmacy.model.requirement.Requirement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class is a container for unit tests for individual methods of the RequirementController class
 * @version 1.0.0 - 19 Oct 2020
 * @since 1.0.0 - 19 Oct 2020
 * @author Arturo Silva
 */
public class PharmacyControllerTest {

    PharmacyController api;

    @Before
    public void setUp() {
        api = new PharmacyController();
        api.setWorkflowExecuter(new WorkflowExecuterMock());
    }

    @Test
    public void testGetValidUser() throws Exception {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        ResponseEntity<Object> response = api.getValidUser("", "", "", true, mockedRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSaveRequirement() throws Exception {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        Requirement requirement = new Requirement();
        ResponseEntity<Object> response = api.saveRequirement("", requirement, mockedRequest, null);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
