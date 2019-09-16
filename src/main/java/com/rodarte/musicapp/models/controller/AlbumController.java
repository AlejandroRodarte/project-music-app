package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.entity.Album;
import com.rodarte.musicapp.models.entity.views.AlbumView;
import com.rodarte.musicapp.models.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<AlbumView> getAlbums(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestParam String sort,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) List<String> yearRange,
        @RequestParam(required = false) String bandId,
        @RequestParam(required = false) String bandName,
        @RequestParam(required = false) List<String> songRange
    ) {
        return albumService.getAlbums(page, size, sort, name, yearRange, bandId, bandName, songRange);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumView getAlbum(@PathVariable Long id) {
        return albumService.getAlbum(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumView saveAlbum(@RequestBody Album album) {
        Album savedAlbum = albumService.saveAlbum(album);
        return albumService.getAlbum(savedAlbum.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumView updateAlbum(@RequestBody Album album, @PathVariable Long id) {
        Album savedAlbum = albumService.updateAlbum(album, id);
        return albumService.getAlbum(savedAlbum.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbumById(id);
    }

}
