package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.Artist;
import com.rodarte.musicapp.models.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistDao extends JpaRepository<Artist, Long> {
    List<Artist> findByBands(Band band);
}
