package com.luxoft.libraryservice.studio.itunes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.studio.itunes.endpoint.AlbumsRetriver;
import com.luxoft.libraryservice.studio.itunes.json.Album;

@Service
public class ItunesAlbumsService implements AlbumsService {

    @Autowired
    private AlbumsRetriver albumsRetriver;

    @Override
    public List<Media> searchBy(String terms, int resultLimits) throws NotAvailableResultsException {
        List<Album> jsonResponse = albumsRetriver.retrieve(terms, resultLimits);
        return ItunesAlbumsMapper.from(jsonResponse);
    }
}
