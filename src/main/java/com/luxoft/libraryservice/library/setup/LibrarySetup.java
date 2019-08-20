package com.luxoft.libraryservice.library.setup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.luxoft.libraryservice.library.DefaultLibraryService;
import com.luxoft.libraryservice.library.LibraryService;
import com.luxoft.libraryservice.studio.DefaultStudioService;
import com.luxoft.libraryservice.studio.StudioService;
import com.luxoft.libraryservice.web.ResponseElementsAlphabeticalOrder;
import com.luxoft.libraryservice.web.ResponseElementsOrdering;

@EnableJpaRepositories
@Configuration
public class LibrarySetup {

    @Bean
    public ResponseElementsOrdering responseElementsOrdering() {
        return new ResponseElementsAlphabeticalOrder();
    }

    @Bean
    public StudioService studioService() {
        return new DefaultStudioService();
    }

    @Bean
    public LibraryService libraryService() {
        return new DefaultLibraryService();
    }
}
