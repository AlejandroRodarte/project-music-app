package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.views.SongView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SongViewDao extends JpaRepository<SongView, Long> {

    @Query(value = "SELECT s FROM SongView s WHERE (:name is null OR s.name LIKE :name%) AND " +
            "(:trackNumber is null OR s.trackNumber = :trackNumber) AND (:durationMin is null OR s.duration >= :durationMin) AND " +
            "(:durationMax is null OR s.duration <= :durationMax) AND (:albumId is null OR s.albumId = :albumId) " +
            "AND (:albumName is null OR s.albumName LIKE :albumName%) AND (:bandId is null OR s.bandId = :bandId) " +
            "AND (:bandName is null OR s.bandName LIKE :bandName%)")
    Page<SongView> findAllBySearchParams(
        @Param("name") String name,
        @Param("trackNumber") Integer trackNumber,
        @Param("durationMin") Integer durationMin,
        @Param("durationMax") Integer durationMax,
        @Param("albumId") Long albumId,
        @Param("albumName") String albumName,
        @Param("bandId") Long bandId,
        @Param("bandName") String bandName,
        Pageable pageable
    );

}
