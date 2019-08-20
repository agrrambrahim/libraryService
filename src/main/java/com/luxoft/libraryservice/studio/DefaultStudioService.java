package com.luxoft.libraryservice.studio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.libraryservice.library.ResponseElementFactory;
import com.luxoft.libraryservice.rest.exceptions.NotAvailableResultsException;
import com.luxoft.libraryservice.media.model.Media;
import com.luxoft.libraryservice.media.repository.MediaRepository;
import com.luxoft.libraryservice.studio.itunes.AlbumsService;
import com.luxoft.libraryservice.web.Response;
import com.luxoft.libraryservice.web.ResponseElementDTO;
@Service
public class DefaultStudioService implements StudioService {
    // @Value("${com.libraryservice.result.response.limit:5}")
    private int maxResult = 5;

    @Autowired
    private MediaRepository albumsRepository;
    @Autowired
    private AlbumsService albumsService;


    @Override
    public List<ResponseElementDTO> searchAlbumsAndWriteResultInto(String input, Response response) {
        //int maxResult = Integer.parseInt(maxResultString);
        List<Media> storedCompositions = getAlbumsForDatabaseBy(input);
        if ( storedCompositions.size() < maxResult ){
            try {
                List<Media> endpointCompositions =  albumsService.getAlbumsBy(input, maxResult);
                saveResultInDataBase(endpointCompositions);

                return endpointCompositions.stream().map(composition -> ResponseElementFactory.from(composition)).collect(Collectors.toList());
            } catch (NotAvailableResultsException exception){
                return storedCompositions.stream().map(storedComposition -> ResponseElementFactory.from(storedComposition)).collect(Collectors.toList());
            }

        }
        return storedCompositions.subList(0, maxResult).stream().map(book -> ResponseElementFactory.from(book)).collect(Collectors.toList());

    }

    private List<Media> getAlbumsForDatabaseBy(String input) {
     return   albumsRepository.findAlbumsbyTerm("%"+input.toUpperCase()+"%");
    }

    private void saveResultInDataBase(List<Media> endpointAlbums) {
        albumsRepository.saveAll(endpointAlbums);
        }
}
