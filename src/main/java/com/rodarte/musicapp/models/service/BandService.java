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
        List<String> songRange,
        String genre,
        String artistFirstName,
        String artistLastName,
        String albumName,
        String songName
    );

    BandView getBand(Long id);

    Band saveBand(Band band);

    Band updateBand(Band band, Long id);

    void deleteBandById(Long id);

}
