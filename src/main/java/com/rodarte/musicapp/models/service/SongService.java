package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.entity.Song;
import com.rodarte.musicapp.models.entity.views.SongView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SongService {

    Page<SongView> getSongs(
        Integer page,
        Integer size,
        String sort,
        String name,
        String trackNumber,
        List<String> durationRange,
        String albumId,
        String albumName,
        String bandId,
        String bandName
    );

    SongView getSong(Long id);

    Song saveSong(Song song);

    Song updateSong(Song song, Long id);

    void deleteSongById(Long id);

}
