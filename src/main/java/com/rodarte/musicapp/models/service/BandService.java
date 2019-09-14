package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dto.BandDto;
import com.rodarte.musicapp.models.dto.BandsDto;
import com.rodarte.musicapp.models.entity.Band;

import java.util.List;

public interface BandService {

    BandsDto getBands(
        Integer page,
        Integer size,
        String sort,
        String name,
        String country,
        List<String> yearRange
    );

    BandDto getBand(Long id);

    BandDto saveBand(Band band);

    void deleteBandById(Long id);

    Integer albumCountByBandId(Long bandId);

}
