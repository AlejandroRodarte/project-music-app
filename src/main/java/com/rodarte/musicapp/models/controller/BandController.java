package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @GetMapping
    public List<Band> getAllBands() {
        return bandService.getAllBands();
    }

}
