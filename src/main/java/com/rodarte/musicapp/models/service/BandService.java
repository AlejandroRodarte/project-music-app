package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.entity.Band;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BandService {
    Page<Band> getAllBands(
            Integer page,
            Integer size,
            String sort,
            String name,
            String country,
            List<String> yearRange
    );
}
