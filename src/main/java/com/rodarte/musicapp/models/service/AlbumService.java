package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.entity.Album;
import com.rodarte.musicapp.models.entity.views.AlbumView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlbumService {

    Page<AlbumView> getAlbums(
        Integer page,
        Integer size,
        String sort,
        String name,
        List<String> yearRange,
        String bandId,
        String bandName,
        List<String> songRange
    );

    AlbumView getAlbum(Long id);

    Album saveAlbum(Album album);

    void deleteAlbumById(Long id);

}
