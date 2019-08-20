package com.luxoft.libraryservice.studio.itunes;

import java.util.List;

import com.luxoft.libraryservice.media.MediaService;
import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;

public interface AlbumsService  extends MediaService {

    List<Media> searchBy(String terms, int resultLimits) throws NotAvailableResultsException;
}
