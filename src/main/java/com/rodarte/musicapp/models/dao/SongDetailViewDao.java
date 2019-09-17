package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.views.SongDetailView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongDetailViewDao extends JpaRepository<SongDetailView, Long> {
    SongDetailView findBySongId(Long songId);
}
