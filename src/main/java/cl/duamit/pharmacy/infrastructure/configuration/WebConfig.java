package cl.duamit.pharmacy.infrastructure.configuration;

@Configuration
public class WebConfig {
	@Value("${app.corsPattern}")
	private String corsPattern;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(corsPattern).allowCredentials(true);

			}
		};
	}
}
