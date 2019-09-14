package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlbumDao extends JpaRepository<Album, Long> {

    @Query(value = "SELECT COUNT(*) FROM albums WHERE band_id = :bandId", nativeQuery = true)
    Integer countAlbumsByBandId(@Param("bandId") Long bandId);

}
