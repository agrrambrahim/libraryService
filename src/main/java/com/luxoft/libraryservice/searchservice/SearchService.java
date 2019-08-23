package com.luxoft.libraryservice.searchservice;

import com.luxoft.libraryservice.web.dto.Response;

public interface SearchService {
    Response searchForMediaBy(String terms);
}
