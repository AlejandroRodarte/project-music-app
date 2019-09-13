package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @GetMapping
    public Page<Band> getBands(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sort,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) List<String> yearRange
    ) {

        return bandService.getBands(page, size, sort, name, country, yearRange);

    }

}
