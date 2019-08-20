package com.luxoft.libraryservice.library.googlebooks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.util.Value;
import com.luxoft.libraryservice.rest.exceptions.TechnicalException;
import com.luxoft.libraryservice.library.googlebooks.endpoint.BooksRetriver;
import com.luxoft.libraryservice.library.googlebooks.endpoint.GoogleBooksEndpointsFactory;
import com.luxoft.libraryservice.library.googlebooks.endpoint.GoogleBooksRetriver;
import com.luxoft.libraryservice.rest.ResponseErrorHandler;
import com.luxoft.libraryservice.rest.RestRequestFactory;
import com.luxoft.libraryservice.rest.RestTemplateFactory;

@Configuration
public class GoogleBooksConfig {

    @Value("${com.libraryservice.result.response.limit}")
    private String googleBooksBaseURL;
    @Value("${deploy.environment.name}")
    private String environmentName;

    @Value("#{new Integer('${com.libraryservice.rest.max.connections.total}')}")
    private int maxConnectionsTotal;

    @Value("#{new Integer('${com.libraryservice.rest.max.connections.per.route}')}")
    private int maxConnectionsPerRoute;

    @Value("#{new Integer('${com.libraryservice.rest.connection.time.to.live.in.seconds}')}")
    private int timeToLive;

    @Value("#{new Integer('${com.libraryservice.rest.connection.request.timeout}')}")
    private int connectionRequestTimeout;

    @Bean
    GoogleBooksEndpointsFactory googleBooksEndpointsFactory() {
        return new GoogleBooksEndpointsFactory();
    }

    @Bean
    ResponseErrorHandler responseErrorHandler() {
        return new ResponseErrorHandler();
    }

    @Bean
    public RestTemplate googleBooksRestTemplate() {
        try {
            return new RestTemplateFactory(
                environmentName,
                maxConnectionsTotal,
                maxConnectionsPerRoute,
                timeToLive,
                connectionRequestTimeout,
                responseErrorHandler()
            ).create();
        } catch (TechnicalException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    RestRequestFactory restRequestFactory() {
        return new RestRequestFactory(googleBooksRestTemplate());
    }

    @Bean
    BooksRetriver googleBooksRetriver() {
        return new GoogleBooksRetriver(googleBooksEndpointsFactory(), restRequestFactory());
    }
/*@Bean
    BooksService googleBooksService(){
     return new GoogleBooksService(googleBooksRetriver());
}
*/
}
