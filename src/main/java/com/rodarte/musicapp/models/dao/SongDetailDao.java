package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.SongDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongDetailDao extends JpaRepository<SongDetail, Long> {
    SongDetail findBySongId(Long songId);
    void deleteBySongId(Long songId);
}
