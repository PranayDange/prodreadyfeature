package com.example.learnprodreadyfeature.prodreadyfeature.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Configuration
public class RestClientConfig {
    @Value("${employeeService.base.url}")
    private  String BASE_URL;

    @Bean
    @Qualifier("employeeRestClient")
        //if there are multiple microservices then //this Qualifier will provide unique identifier
        //so that we can access this perticular bean
    RestClient getEmployeeServiceRestClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(req, res)->{
                    throw new RuntimeException("Employee Server Down");
                })
                .build();
    }

}
