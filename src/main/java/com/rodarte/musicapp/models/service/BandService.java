package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.entity.Band;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BandService {
    Page<Band> getAllBands(PageRequest request);
}
