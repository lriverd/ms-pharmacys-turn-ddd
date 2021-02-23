package cl.duamit.pharmacy.infrastructure.providers.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Getter
public abstract class RestService {

	private  Map<String, String> pathParams = new HashMap<>();
	private Map<String, String> queryParams = new HashMap<>();
	private HttpHeaders httpHeaders = new HttpHeaders();

	@Setter
	private String url;
	@Setter
	private String tmplUrl;
	private RestTemplate restTemplate;

	public void setConverter() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		getRestTemplate().setMessageConverters(messageConverters);
	}

	protected RestTemplate getRestTemplate() {
		if (this.restTemplate == null) {
			this.restTemplate = new RestTemplate();
		}
		this.configureRestTemplate();

		return this.restTemplate;
	}

	private void configureRestTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(10000);
		factory.setReadTimeout(10000);
		this.restTemplate.setRequestFactory(factory);
	}
}
