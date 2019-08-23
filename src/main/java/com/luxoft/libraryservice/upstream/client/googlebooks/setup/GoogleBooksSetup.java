package com.luxoft.libraryservice.upstream.client.googlebooks.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.luxoft.libraryservice.upstream.client.googlebooks.endpoint.GoogleBooksApiClient;
import com.luxoft.libraryservice.upstream.client.googlebooks.endpoint.GoogleBooksApiRestClient;
import com.luxoft.libraryservice.upstream.client.googlebooks.endpoint.GoogleBooksEndpointsFactory;
import com.luxoft.libraryservice.upstream.client.googlebooks.endpoint.GoogleBooksResponseErrorHandler;
import com.luxoft.libraryservice.rest.error.handler.ResponseErrorHandler;
import com.luxoft.libraryservice.rest.setup.RestClientSetup;
import com.luxoft.libraryservice.rest.factory.RestRequestFactory;
import com.luxoft.libraryservice.rest.factory.RestTemplateFactory;

@Configuration
@Import(RestClientSetup.class)
public class GoogleBooksSetup {
    @Autowired
    RestClientSetup restClientSetup;

    @Bean
    GoogleBooksConfiguration googleBooksConfigurationService() {
        return new GoogleBooksConfiguration();
    }

    @Bean
    GoogleBooksEndpointsFactory googleBooksEndpointsFactory() {
        return new GoogleBooksEndpointsFactory(
            googleBooksConfigurationService().getApiBaseUrl(),
            googleBooksConfigurationService().getApiKey(),
            googleBooksConfigurationService().getSearchLocalisation()
        );
    }

    @Bean
    ResponseErrorHandler googleBooksResponseErrorHandler() {
        return new GoogleBooksResponseErrorHandler();
    }

    @Bean
    public RestTemplate googleBooksRestTemplate() {

        return this.restClientSetup.restTemplate(new RestTemplateFactory(
            googleBooksConfigurationService().getRetryUpStreamRequestCount(),
            googleBooksConfigurationService().getConnectionRequestTimeout(),
            googleBooksResponseErrorHandler()
        ).create());
    }

    @Bean
    RestRequestFactory restRequestFactory() {
        return new RestRequestFactory(googleBooksRestTemplate());
    }

    @Bean
    GoogleBooksApiClient googleBooksRetriver() {
        return new GoogleBooksApiRestClient(googleBooksEndpointsFactory(), restRequestFactory());
    }


    /*@Bean
    BooksService googleBooksService(){
     return new GoogleBooksService(googleBooksRetriver());
}
*/
}
