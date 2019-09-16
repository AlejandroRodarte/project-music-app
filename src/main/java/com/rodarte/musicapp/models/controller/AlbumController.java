package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.dto.AlbumDto;
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
    public AlbumDto updateAlbum(@RequestBody Album album, @PathVariable Long id) {

//        AlbumDto albumDto = albumService.getAlbum(id);
//
//        updateAlbum(albumDto, album);
//
//        Album mappedAlbum = modelMapper.map(albumDto, Album.class);
//
//        if (album.getBand() != null) {
//            mappedAlbum.getBand().setId(album.getBand().getId());
//            return albumService.saveAlbum(mappedAlbum, true);
//        }

        return null;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbumById(id);
    }

    private void updateAlbum(AlbumDto albumDto, Album album) {

        if (album.getName() != null) {
            albumDto.setName(album.getName());
        }

        if (album.getImagePath() != null) {
            albumDto.setImagePath(album.getImagePath());
        }

        if (album.getReleaseYear() != null) {
            albumDto.setReleaseYear(album.getReleaseYear());
        }

    }


}
