package com.luxoft.libraryservice.searchservice.dto.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.api.client.util.Maps;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.luxoft.libraryservice.searchservice.dao.SearchResult;
import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.web.dto.Response;
import com.luxoft.libraryservice.web.dto.ResponseElementDTO;

public class ResponseBuilder {
    private final int maxResults;
    Set<Media> results = Sets.newHashSet();
    Map<String, String> upstreamsResponseTime = Maps.newHashMap();
    Map<String, Throwable> errors=  Maps.newHashMap();

    public ResponseBuilder(int maxResults) {
        this.maxResults = maxResults;
    }

    public ResponseBuilder addResults(List<Media> results) {
        this.results.addAll(results);
        return this;
    }

    public ResponseBuilder addUpstreamsResponseTime(String upstreamName, float responseTime) {
        String upstreamsResponseTimeInString = String.valueOf(responseTime) + " " + "sec. ";
        upstreamsResponseTime.put(upstreamName, upstreamsResponseTimeInString);
        return this;
    }

    public ResponseBuilder addSearchResult(SearchResult searchResult) {

        if(searchResult.getSearchResults().isEmpty()){
            if(Float.isNaN(searchResult.getResponseTime()))
            return this;
          return this.addUpstreamsResponseTime(searchResult.getUsedUpStream(),searchResult.getResponseTime());
        }
        return addResults(searchResult.getSearchResults().stream().limit(maxResults).collect(Collectors.toList()))
            .addUpstreamsResponseTime(searchResult.getUsedUpStream(), searchResult.getResponseTime());
    }

    public Response build() {
        List<ResponseElementDTO> finalResults = this.results.stream().map(composition -> ResponseElementFactory.from(composition)).collect(Collectors.toList());
        List<String> errorMessages = this.errors.entrySet().stream().map(entry -> ResponseElementFactory.errorMessageFrom(entry.getKey(),entry.getValue())).collect(Collectors.toList());
        return new Response(finalResults, this.upstreamsResponseTime, errorMessages);
    }

    public void addErrorMessage(Throwable error, String usedUpStream) {
        this.errors.put(usedUpStream,error);
    }
}
