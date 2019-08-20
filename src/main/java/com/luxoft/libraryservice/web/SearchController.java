package com.luxoft.libraryservice.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.libraryservice.library.LibraryService;
import com.luxoft.libraryservice.studio.StudioService;

@RestController
@RequestMapping(path = "/libraryservice")
public class SearchController {

    @Resource
    @Qualifier("libraryService")
    private LibraryService libraryService;
    @Autowired
    private StudioService studioService;
    @Autowired
    private ResponseElementsOrdering responseElementsOrdering;

    @GetMapping(path = "/search")
    public Response getEntryByName(@RequestParam String input) {

        Response response = libraryService.searchForMediaBy(input);
        return responseElementsOrdering.sort(response);
    }
}
