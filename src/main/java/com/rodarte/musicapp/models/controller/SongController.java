package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.entity.Song;
import com.rodarte.musicapp.models.entity.SongDetail;
import com.rodarte.musicapp.models.entity.views.SongDetailView;
import com.rodarte.musicapp.models.entity.views.SongView;
import com.rodarte.musicapp.models.service.SongDetailService;
import com.rodarte.musicapp.models.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private SongDetailService songDetailService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SongView> getSongs(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestParam String sort,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String trackNumber,
        @RequestParam(required = false) List<String> durationRange,
        @RequestParam(required = false) String albumId,
        @RequestParam(required = false) String albumName,
        @RequestParam(required = false) String bandId,
        @RequestParam(required = false) String bandName
    ) {
        return songService.getSongs(page, size, sort, name, trackNumber, durationRange, albumId, albumName, bandId, bandName);
    }

    @GetMapping("/{id}")
    public SongView getSong(@PathVariable Long id) {
        return songService.getSong(id);
    }

    @GetMapping("/{id}/detail")
    public SongDetailView getSongDetail(@PathVariable Long id) {
        return songDetailService.getSongDetail(id);
    }

    @PostMapping
    public SongView saveSong(@RequestBody Song song) {
        Song savedSong = songService.saveSong(song);
        return songService.getSong(savedSong.getId());
    }

    @PostMapping("/{id}/detail")
    public SongDetailView saveSongDetail(@RequestBody SongDetail songDetail, @PathVariable Long id) {
        SongDetail savedSongDetail = songDetailService.saveSongDetail(songDetail, id);
        return songDetailService.getSongDetail(savedSongDetail.getSong().getId());
    }

    @PutMapping("/{id}")
    public SongView saveSong(@RequestBody Song song, @PathVariable Long id) {
        Song savedSong = songService.updateSong(song, id);
        return songService.getSong(savedSong.getId());
    }

    @PutMapping("/{id}/detail")
    public SongDetailView updateSongDetail(@RequestBody SongDetail songDetail, @PathVariable Long id) {
        SongDetail savedSongDetail = songDetailService.updateSongDetail(songDetail, id);
        return songDetailService.getSongDetail(savedSongDetail.getSong().getId());
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id) {
        songService.deleteSongById(id);
    }

    @DeleteMapping("/{id}/detail")
    public void deleteSongDetail(@PathVariable Long id) {
        songDetailService.deleteSongDetail(id);
    }

}
