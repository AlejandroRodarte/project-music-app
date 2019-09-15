package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.views.AlbumView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlbumViewDao extends JpaRepository<AlbumView, Long> {

    @Query(value = "SELECT a FROM AlbumView a WHERE (:name is null OR a.name LIKE :name%) AND " +
            "(:startYear is null OR a.releaseYear >= :startYear) AND (:endYear is null OR a.releaseYear <= :endYear) " +
            "AND (:bandId is null OR a.bandId = :bandId) AND (:bandName is null OR a.bandName LIKE :bandName%) AND " +
            "(:songMin is null OR a.songCount >= :songMin) AND (:songMax is null OR a.songCount <= :songMax)")
    Page<AlbumView> findAllBySearchParams(
        @Param("name") String name,
        @Param("startYear") Integer startYear,
        @Param("endYear") Integer endYear,
        @Param("bandId") Long bandId,
        @Param("bandName") String bandName,
        @Param("songMin") Integer songMin,
        @Param("songMax") Integer songMax,
        Pageable pageable
    );

}
