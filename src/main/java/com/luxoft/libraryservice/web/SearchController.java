package com.luxoft.libraryservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.libraryservice.searchservice.SearchService;
import com.luxoft.libraryservice.web.dto.Response;
import com.luxoft.libraryservice.web.dto.ResponseElementsOrdering;

@RestController
@RequestMapping(path = "/libraryservice")
public class SearchController {

    @Autowired
    @Qualifier(value = "searchService")
    private SearchService searchService;
    @Autowired
    private ResponseElementsOrdering responseElementsOrdering;
    @GetMapping(path = "/search")
    public Response getEntryByName(@RequestParam String input) {
        //to do :  validate input (check content security,...)
        Response response = searchService.searchForMediaBy(input);
        return responseElementsOrdering.sort(response);
    }
}
