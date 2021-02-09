package cl.duamit.pharmacy.service.requirement;

import cl.duamit.pharmacy.model.location.Commune;
import cl.duamit.pharmacy.model.location.Region;
import cl.duamit.pharmacy.model.requirement.RequirementMock;
import cl.duamit.pharmacy.model.security.TokenMock;
import cl.duamit.pharmacy.service.location.RegionCommuneService;
import cl.duamit.pharmacy.ws.rest.wsd.GeoLocationService;
import cl.duamit.pharmacy.repository.requirements.RequirimentsRepository;
import cl.duamit.pharmacy.ws.domain.wsd.geolocation.GeolocationRq;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class WdsGeolocationServiceTest {

    @Mock
    private RegionCommuneService regionCommuneService;
    @Mock
    private GeoLocationService geoLocationService;
    @Mock
    private RequirimentsRepository requirimentsRepository;

    @InjectMocks
    private WdsGeolocationService service;

    @Before
    public void setUp() throws Exception {
        Region region = new Region();
        region.setCode("13");
        region.setLongName("Region Metropolitana");

        Commune c = new Commune();
        c.setCode("13101");
        c.setLongName("Santiago");
        region.setCommunes(Collections.singletonList(c));
        Mockito.when(geoLocationService.post(Mockito.any(GeolocationRq.class)))
                .thenReturn(new ArrayList());
        Mockito.when(regionCommuneService.execute(Mockito.anyString()))
                .thenReturn(region);
    }

    @Test
    public void execute() throws Exception {
        Assert.assertTrue(service.execute(new RequirementMock(), new TokenMock()));
    }

    @Test(expected = HttpClientErrorException.class)
    public void executeException() throws Exception {
        Mockito.when(geoLocationService.post(Mockito.any(GeolocationRq.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        service.execute(new RequirementMock(), new TokenMock());
    }
}