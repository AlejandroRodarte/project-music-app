package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongDao extends JpaRepository<Song, Long> {
}
