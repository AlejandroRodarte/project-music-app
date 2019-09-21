package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.views.BandView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BandViewDao extends JpaRepository<BandView, Long> {

    @Query(value = "SELECT b FROM BandView b WHERE (:name is null OR b.name LIKE :name%) AND " +
            "(:country is null OR b.originCountry LIKE :country%) AND (:startYear is null OR b.originYear >= :startYear) " +
            "AND (:endYear is null OR b.originYear <= :endYear) AND (:albumMin is null OR b.albumCount >= :albumMin) " +
            "AND (:albumMax is null OR b.albumCount <= :albumMax) AND (:songMin is null OR b.songCount >= :songMin) " +
            "AND (:songMax is null OR b.songCount <= :songMax) AND (:genre is null OR b.genre = :genre) " +
            "AND (:artistFirstName is null OR b.artistFirstName LIKE :artistFirstName%) " +
            "AND (:artistLastName is null OR b.artistLastName LIKE :artistLastName%) " +
            "AND (:albumName is null OR b.albumName LIKE :albumName%) AND (:songName is null OR b.songName LIKE :songName%) " +
            "GROUP BY b.id")
    Page<BandView> findAllBySearchParams(
        @Param("name") String name,
        @Param("country") String country,
        @Param("startYear") Integer startYear,
        @Param("endYear") Integer endYear,
        @Param("albumMin") Integer albumMin,
        @Param("albumMax") Integer albumMax,
        @Param("songMin") Integer songMin,
        @Param("songMax") Integer songMax,
        @Param("genre") String genre,
        @Param("artistFirstName") String artistFirstName,
        @Param("artistLastName") String artistLastName,
        @Param("albumName") String albumName,
        @Param("songName") String songName,
        Pageable pageable
    );

    @Query(value = "SELECT b from BandView b WHERE b.id = :bandId GROUP BY b.id")
    BandView findByIdGroupByBandId(
        @Param("bandId") Long bandId
    );

}
