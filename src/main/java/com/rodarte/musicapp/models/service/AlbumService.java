package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dto.AlbumsDto;

import java.util.List;

public interface AlbumService {
    Integer countAlbumsByBandId(Long bandId);

    AlbumsDto getAlbums(
        Integer page,
        Integer size,
        String sort,
        String name,
        List<String> yearRange,
        String bandId
    );

}
