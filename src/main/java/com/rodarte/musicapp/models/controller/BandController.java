package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @GetMapping
    public Page<Band> getAllBands(@RequestParam Integer page, @RequestParam Integer size) {
        return bandService.getAllBands(PageRequest.of(page, size));
    }

}
