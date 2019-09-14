package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.dto.AlbumsDto;
import com.rodarte.musicapp.models.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AlbumsDto getAlbums(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestParam String sort,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) List<String> yearRange,
        @RequestParam(required = false) String bandId
    ) {
        return albumService.getAlbums(page, size, sort, name, yearRange, bandId);
    }

}
