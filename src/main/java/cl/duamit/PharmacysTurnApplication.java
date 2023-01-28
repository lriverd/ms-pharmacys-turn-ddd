package cl.duamit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:version.properties", ignoreResourceNotFound = true)
@MapperScan(basePackages = "cl.duamit.*.infraestructure.providers.db.*")
public class PharmacysTurnApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PharmacysTurnApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
