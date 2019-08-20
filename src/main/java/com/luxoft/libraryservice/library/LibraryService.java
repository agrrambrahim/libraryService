package com.luxoft.libraryservice.library;

import com.luxoft.libraryservice.web.Response;


public interface LibraryService {
    Response searchForMediaBy(String terms);
}
