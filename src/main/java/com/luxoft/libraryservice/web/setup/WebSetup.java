package com.luxoft.libraryservice.web.setup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luxoft.libraryservice.web.dto.ResponseElementsAlphabeticalOrder;
import com.luxoft.libraryservice.web.dto.ResponseElementsOrdering;

@Configuration
public class WebSetup {

    @Bean
    public ResponseElementsOrdering responseElementsOrdering() {
        return new ResponseElementsAlphabeticalOrder();
    }

}
