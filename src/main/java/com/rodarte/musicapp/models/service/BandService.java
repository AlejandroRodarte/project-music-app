package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.entity.Band;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

public interface BandService {
    Page<Band> getAllBands(PageRequest request, Map<String, String> searchParams);
}
