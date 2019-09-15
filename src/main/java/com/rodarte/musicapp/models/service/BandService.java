package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.entity.views.BandView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BandService {

    Page<BandView> getBands(
        Integer page,
        Integer size,
        String sort,
        String name,
        String country,
        List<String> yearRange,
        List<String> albumRange,
        List<String> songRange
    );

    BandView getBand(Long id);

    Band saveBand(Band band);

    void deleteBandById(Long id);

    Integer albumCountByBandId(Long bandId);

}
