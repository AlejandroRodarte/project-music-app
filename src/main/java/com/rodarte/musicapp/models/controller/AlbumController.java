package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.dto.AlbumDto;
import com.rodarte.musicapp.models.dto.AlbumsDto;
import com.rodarte.musicapp.models.entity.Album;
import com.rodarte.musicapp.models.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {


    @Autowired
    private AlbumService albumService;

    @Autowired
    private ModelMapper modelMapper;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumDto getAlbum(@PathVariable Long id) {
        return albumService.getAlbum(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumDto saveAlbum(@RequestBody Album album) {
        return albumService.saveAlbum(album, true);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumDto updateAlbum(@RequestBody Album album, @PathVariable Long id) {

        AlbumDto albumDto = albumService.getAlbum(id);

        updateAlbum(albumDto, album);

        Album mappedAlbum = modelMapper.map(albumDto, Album.class);

        if (album.getBand() != null) {
            mappedAlbum.getBand().setId(album.getBand().getId());
            return albumService.saveAlbum(mappedAlbum, true);
        }

        return albumService.saveAlbum(mappedAlbum, false);

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
