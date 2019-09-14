package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.dto.BandDto;
import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.service.BandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BandDto> getBands(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestParam String sort,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String country,
        @RequestParam(required = false) List<String> yearRange
    ) {

        List<BandDto> bandDtos = bandService.getBands(page, size, sort, name, country, yearRange);

        for (BandDto band : bandDtos) {
            Integer albumCount = bandService.albumCountByBandId(band.getId());
            band.setAlbumCount(albumCount);
        }

        return bandDtos;

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BandDto getBand(@PathVariable Long id) {

        BandDto band = bandService.getBand(id);

        Integer albumCount = bandService.albumCountByBandId(id);
        band.setAlbumCount(albumCount);

        return band;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BandDto saveBand(@RequestBody Band band) {
        return bandService.saveBand(band);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BandDto updateBand(@RequestBody Band updatedBand, @PathVariable Long id) {

        BandDto bandToUpdate = bandService.getBand(id);

        updateBand(bandToUpdate, updatedBand);

        return bandService.saveBand(modelMapper.map(bandToUpdate, Band.class));

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
