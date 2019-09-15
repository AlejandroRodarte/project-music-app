package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumDao extends JpaRepository<Album, Long> {

    @Query(value = "SELECT COUNT(*) FROM albums WHERE band_id = :bandId", nativeQuery = true)
    Integer countAlbumsByBandId(@Param("bandId") Long bandId);

    @Query(value = "SELECT a FROM Album a WHERE (:name is null OR a.name LIKE :name%) AND " +
            "(:startYear is null OR a.releaseYear >= :startYear) AND (:endYear is null OR a.releaseYear <= :endYear) " +
            "AND (:bandId is null OR a.band.id = :bandId)")
    Page<Album> findBySearchParams(
        @Param("name") String name,
        @Param("startYear") Integer startYear,
        @Param("endYear") Integer endYear,
        @Param("bandId") Long bandId,
        Pageable pageable
    );

    List<Album> findByBandId(Long bandId);

}
