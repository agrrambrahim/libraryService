package com.luxoft.libraryservice.upstream.client.itunes.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.luxoft.libraryservice.rest.error.handler.ResponseErrorHandler;
import com.luxoft.libraryservice.rest.factory.RestRequestFactory;
import com.luxoft.libraryservice.rest.factory.RestTemplateFactory;
import com.luxoft.libraryservice.rest.setup.RestClientSetup;
import com.luxoft.libraryservice.upstream.client.itunes.endpoint.ItunesApiClient;
import com.luxoft.libraryservice.upstream.client.itunes.endpoint.ItunesApiResponseErrorHandler;
import com.luxoft.libraryservice.upstream.client.itunes.endpoint.ItunesApiRestClient;
import com.luxoft.libraryservice.upstream.client.itunes.endpoint.ItunesEndpointsFactory;

@Configuration
@Import(RestClientSetup.class)
public class ItunesSetup {
    @Autowired
    RestClientSetup restClientSetup;

    @Bean
    ItunesConfiguration iTunesConfiguration() {
        return new ItunesConfiguration();
    }

    @Bean
    ItunesEndpointsFactory iTunesEndpointsFactory() {
        return new ItunesEndpointsFactory(iTunesConfiguration().getBaseUrl());
    }

    @Bean
    ResponseErrorHandler iTunesResponseErrorHandler() {
        return new ItunesApiResponseErrorHandler();
    }

    @Bean
    public RestTemplate itunesRestTemplate() {
        return this.restClientSetup.restTemplate(new RestTemplateFactory(
            iTunesConfiguration().getUpstreamRequestRetryCount(),
            iTunesConfiguration().getConnectionRequestTimeout(),
            iTunesResponseErrorHandler()
        ).create());
    }

    @Bean
    RestRequestFactory iTunesRestRequestFactory() {
        return new RestRequestFactory(itunesRestTemplate());
    }

    @Bean
    ItunesApiClient itunesRetriver() {
        return new ItunesApiRestClient(iTunesEndpointsFactory(), iTunesRestRequestFactory());
    }
}
