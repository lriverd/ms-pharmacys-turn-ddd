package cl.duamit.pharmacy.controller;

import cl.duamit.pharmacy.api.controller.LocationController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class is a container for unit tests for individual methods of the LocationController class
 * @version 1.0.0 - 19 Oct 2020
 * @since 1.0.0 - 19 Oct 2020
 * @author Arturo Silva
 */
public class LocationControllerTest {

    LocationController api;

    @Before
    public void setUp() {
        api = new LocationController();
        api.setWorkflowExecuter(new WorkflowExecuterMock());
    }

    @Test
    public void testGetRegion() throws Exception {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        ResponseEntity<Object> response = api.getRegionList(mockedRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCommune() throws Exception {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        String regionCode = "014";
        ResponseEntity<Object> response = api.getCommuneList(regionCode, mockedRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
