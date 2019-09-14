package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.entity.Band;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BandService {

    Page<Band> getBands(
            Integer page,
            Integer size,
            String sort,
            String name,
            String country,
            List<String> yearRange
    );

    Optional<Band> getBand(Long id);

    Band saveBand(Band band);

    void deleteBandById(Long id);

    Integer albumCountByBandId(Long bandId);

}
