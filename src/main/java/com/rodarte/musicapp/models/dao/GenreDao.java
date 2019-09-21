package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreDao extends JpaRepository<Genre, Long> {
    List<Genre> findByBands(Band band);
}
