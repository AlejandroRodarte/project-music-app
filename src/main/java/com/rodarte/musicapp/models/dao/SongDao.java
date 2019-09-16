package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongDao extends JpaRepository<Song, Long> {
    List<Song> findByAlbumId(Long albumId);
}
