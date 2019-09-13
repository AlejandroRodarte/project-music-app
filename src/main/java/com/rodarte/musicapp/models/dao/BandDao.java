package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.Band;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BandDao extends JpaRepository<Band, Long> {

    @Query(value = "SELECT b FROM Band b WHERE b.name LIKE :name% AND " +
            "b.originCountry LIKE :country% AND b.originYear BETWEEN :startYear AND :endYear")
    Page<Band> findBySearchParams(
            @Param("name") String name,
            @Param("country") String country,
            @Param("startYear") Integer startYear,
            @Param("endYear") Integer endYear,
            Pageable pageable
    );

}
