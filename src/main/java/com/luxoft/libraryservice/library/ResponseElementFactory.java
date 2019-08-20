package com.luxoft.libraryservice.library;

import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.web.ResponseElementDTO;

public class ResponseElementFactory {

    public static ResponseElementDTO from(Media composition) {
        ResponseElementDTO.ResponseElementBuilder responseElementBuilder = new ResponseElementDTO.ResponseElementBuilder();
        composition.writeInto(responseElementBuilder);
        return responseElementBuilder.build();
    }
}
