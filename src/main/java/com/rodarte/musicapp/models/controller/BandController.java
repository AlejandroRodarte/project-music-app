package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.entity.views.BandView;
import com.rodarte.musicapp.models.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<BandView> getBands(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestParam String sort,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String country,
        @RequestParam(required = false) List<String> yearRange,
        @RequestParam(required = false) List<String> albumRange,
        @RequestParam(required = false) List<String> songRange,
        @RequestParam(required = false) String genre,
        @RequestParam(required = false) String artistFirstName,
        @RequestParam(required = false) String artistLastName,
        @RequestParam(required = false) String albumName,
        @RequestParam(required = false) String songName
    ) {
        return bandService.getBands(page, size, sort, name, country, yearRange, albumRange, songRange, genre, artistFirstName, artistLastName, albumName, songName);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BandView getBand(@PathVariable Long id) {
        return bandService.getBand(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BandView saveBand(@RequestBody Band band) {
        Band savedBand = bandService.saveBand(band);
        return bandService.getBand(savedBand.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BandView updateBand(@RequestBody Band updatedBand, @PathVariable Long id) {
        Band savedBand = bandService.updateBand(updatedBand, id);
        return bandService.getBand(savedBand.getId());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBand(@PathVariable Long id) {
        bandService.deleteBandById(id);
    }

}
