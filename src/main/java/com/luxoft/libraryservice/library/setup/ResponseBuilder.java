package com.luxoft.libraryservice.library.setup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.api.client.util.Maps;
import com.google.common.collect.Lists;
import com.luxoft.libraryservice.library.ResponseElementFactory;
import com.luxoft.libraryservice.media.SearchResult;
import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.web.Response;
import com.luxoft.libraryservice.web.ResponseElementDTO;

public class ResponseBuilder {
    List<ResponseElementDTO> results = Lists.newArrayList();
    Map<String,String> upstreamsResponseTime  = Maps.newHashMap();

    public ResponseBuilder addResults(List<Media> results) {
        this.results.addAll(results.stream().map(composition -> ResponseElementFactory.from(composition)).collect(Collectors.toList()));
        return this;
    }

    public ResponseBuilder addUpstreamsResponseTime(String upstreamName,float responseTime) {
        String upstreamsResponseTimeInString = String.valueOf(responseTime) +" "+"sec. ";
        upstreamsResponseTime.put(upstreamName,upstreamsResponseTimeInString);
        return this;
    }
    public ResponseBuilder addSearchResult(SearchResult searchResult){
        return addResults(searchResult.getSearchResults())
            .addUpstreamsResponseTime(searchResult.getUsedUpStream(),searchResult.getResponseTime());
    }

    public Response build() {
        return new Response(this.results,this.upstreamsResponseTime);
    }
}
