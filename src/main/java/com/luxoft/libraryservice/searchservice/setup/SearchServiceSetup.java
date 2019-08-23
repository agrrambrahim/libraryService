package com.luxoft.libraryservice.searchservice.setup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luxoft.libraryservice.searchservice.dao.MediaDao;
import com.luxoft.libraryservice.searchservice.DefaultSearchService;
import com.luxoft.libraryservice.searchservice.SearchService;
@Configuration
public class SearchServiceSetup {
    @Bean
    public SearchService searchService() {
        return new DefaultSearchService();
    }
    @Bean
    public MediaDao mediaDao(){
        return new MediaDao();
    }
}
