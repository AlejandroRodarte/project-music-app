package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Band getBand(@PathVariable Long id) {

        Optional<Band> band = bandService.getBand(id);

        if (band.isEmpty()) {
            throw new RuntimeException("Band not found.");
        }

        return band.get();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Band saveBand(@RequestBody Band band) {
        return bandService.saveBand(band);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Band updateBand(@RequestBody Band updatedBand, @PathVariable Long id) {

        Optional<Band> bandToUpdate = bandService.getBand(id);

        if (bandToUpdate.isEmpty()) {
            throw new RuntimeException("Band not found.");
        }

        updateBand(bandToUpdate.get(), updatedBand);

        return bandService.saveBand(bandToUpdate.get());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBand(@PathVariable Long id) {
        bandService.deleteBandById(id);
    }

    private void updateBand(Band bandToUpdate, Band updatedBand) {

        if (updatedBand.getName() != null) {
            bandToUpdate.setName(updatedBand.getName());
        }

        if (updatedBand.getImagePath() != null) {
            bandToUpdate.setImagePath(updatedBand.getImagePath());
        }

        if (updatedBand.getOriginCountry() != null) {
            bandToUpdate.setOriginCountry(updatedBand.getOriginCountry());
        }

        if (updatedBand.getOriginYear() != null) {
            bandToUpdate.setOriginYear(updatedBand.getOriginYear());
        }

    }

}
