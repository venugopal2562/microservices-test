package eu.epitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OauthJugApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthJugApplication.class, args);
	}
}
