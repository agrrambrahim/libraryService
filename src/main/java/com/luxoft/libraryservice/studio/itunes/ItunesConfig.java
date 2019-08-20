package com.luxoft.libraryservice.studio.itunes;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.util.Value;
import com.luxoft.libraryservice.rest.ResponseErrorHandler;
import com.luxoft.libraryservice.rest.RestRequestFactory;
import com.luxoft.libraryservice.rest.RestTemplateFactory;
import com.luxoft.libraryservice.rest.exceptions.TechnicalException;
import com.luxoft.libraryservice.studio.itunes.endpoint.AlbumsRetriver;
import com.luxoft.libraryservice.studio.itunes.endpoint.ItunesAlbumsRetriver;
import com.luxoft.libraryservice.studio.itunes.endpoint.ItunesEndpointsFactory;

@Configuration
public class ItunesConfig {

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

    @Autowired
    ResponseErrorHandler responseErrorHandler;

    @Bean
    ItunesEndpointsFactory itunesEndpointsFactory() {
        return new ItunesEndpointsFactory();
    }

    @Bean
    public RestTemplate itunesRestTemplate() {
        try {
            return new RestTemplateFactory(
                environmentName,
                maxConnectionsTotal,
                maxConnectionsPerRoute,
                timeToLive,
                connectionRequestTimeout,
                responseErrorHandler
            ).create();
        } catch (TechnicalException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    RestRequestFactory itunesRestRequestFactory() {
        RestTemplate restTemplate = itunesRestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(mappingJackson2HttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);

        return new RestRequestFactory(itunesRestTemplate());
    }

    @Bean
    AlbumsRetriver itunesRetriver() {
        return new ItunesAlbumsRetriver(itunesEndpointsFactory(), itunesRestRequestFactory());
    }

    /*@Bean
    BooksService googleBooksService(){
     return new GoogleBooksService(googleBooksRetriver());
}
*/
}
