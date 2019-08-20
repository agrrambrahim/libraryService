package com.luxoft.libraryservice.studio;

import java.util.List;

import com.luxoft.libraryservice.web.Response;
import com.luxoft.libraryservice.web.ResponseElementDTO;

public interface StudioService {
    List<ResponseElementDTO> searchAlbumsAndWriteResultInto(String input, Response response);
}
