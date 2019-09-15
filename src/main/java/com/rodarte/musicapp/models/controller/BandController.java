package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.dto.BandDto;
import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.entity.views.BandView;
import com.rodarte.musicapp.models.service.BandService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

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
        @RequestParam(required = false) List<String> songRange
    ) {
        return bandService.getBands(page, size, sort, name, country, yearRange, albumRange, songRange);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BandView getBand(@PathVariable Long id) {
        return bandService.getBand(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BandDto saveBand(@RequestBody Band band) {
        return bandService.saveBand(band);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BandDto updateBand(@RequestBody Band updatedBand, @PathVariable Long id) {

//        BandDto bandToUpdate = bandService.getBand(id);
//
//        updateBand(bandToUpdate, updatedBand);

        return null;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBand(@PathVariable Long id) {
        bandService.deleteBandById(id);
    }

    private void updateBand(BandDto bandToUpdate, Band updatedBand) {

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
