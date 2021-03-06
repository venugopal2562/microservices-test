package eu.epitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sadzeih on 12/9/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters=false)
public class WebServer {
    public static void main(String[] args) {
        // Will configure using web-server.yml
        System.setProperty("spring.config.name", "web-server");
        SpringApplication.run(WebServer.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public WebAccountService accountService()
    {
        return new WebAccountService("http://accounts-service");
    }

    @Bean
    public WebAccountController accountController() {
        return new WebAccountController(accountService());
    }
}
