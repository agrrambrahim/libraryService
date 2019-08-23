package com.luxoft.libraryservice.web.dto;

import java.util.Comparator;

public class ResponseElementsAlphabeticalOrder implements ResponseElementsOrdering {
    @Override
    public Response sort(Response response) {
        response.results.sort(new Comparator<ResponseElementDTO>() {
            @Override
            public int compare(ResponseElementDTO o1, ResponseElementDTO o2) {
                return (o1.getTitle().compareToIgnoreCase(o2.getTitle()));
            }
        });
        return response;
    }
}
