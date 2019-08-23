package com.luxoft.libraryservice.searchservice.dto.factory;

import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.web.dto.ResponseElementDTO;

public class ResponseElementFactory {

    public static ResponseElementDTO from(Media composition) {
        ResponseElementDTO.ResponseElementBuilder responseElementBuilder = new ResponseElementDTO.ResponseElementBuilder();
        composition.writeInto(responseElementBuilder);
        return responseElementBuilder.build();
    }
    public static String errorMessageFrom(String upstreamName, Throwable error){
        StringBuilder errorMessage = new StringBuilder("Failed to retrive "+upstreamName +"s from the upstream due to the following Error type : " + error.getClass().getName());
        if (!error.getMessage().isEmpty()){
            errorMessage.append(". Message :"+ error.getMessage());
        }
        return errorMessage.toString();
    }
}
