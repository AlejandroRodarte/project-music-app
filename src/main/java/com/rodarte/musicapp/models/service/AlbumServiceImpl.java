package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.AlbumDao;
import com.rodarte.musicapp.models.dao.AlbumViewDao;
import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.dao.SongDao;
import com.rodarte.musicapp.models.entity.Album;
import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.entity.Song;
import com.rodarte.musicapp.models.entity.views.AlbumView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private BandDao bandDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private SongDao songDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlbumViewDao albumViewDao;

    @Override
    @Transactional(readOnly = true)
    public Page<AlbumView> getAlbums(
        Integer page,
        Integer size,
        String sort,
        String name,
        List<String> yearRange,
        String bandId,
        String bandName,
        List<String> songRange
    ) {

        String[] sortArr = sort.split(":");

        String sortParam = sortArr[0];
        String sortDirection = sortArr[1];

        Sort.Direction direction;

        if (sortDirection.equals("asc")) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }

        return albumViewDao.findAllBySearchParams(
            name,
            yearRange == null ? null : Integer.parseInt(yearRange.get(0)),
            yearRange == null ? null : Integer.parseInt(yearRange.get(1)),
            bandId == null ? null : Long.parseLong(bandId),
            bandName,
            songRange == null ? null : Integer.parseInt(songRange.get(0)),
            songRange == null ? null : Integer.parseInt(songRange.get(1)),
            PageRequest.of(page, size, Sort.by(direction, sortParam))
        );

    }

    @Override
    @Transactional
    public AlbumView getAlbum(Long id) {
        return albumViewDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Album saveAlbum(Album album) {

        Long bandId = null;
        List<Long> songIds = new ArrayList<>();

        if (album.getBand() != null) {
            bandId = album.getBand().getId();
        }

        if (album.getSongs() != null) {
            songIds = album.getSongs().stream().map(Song::getId).collect(Collectors.toList());
        }

        if (bandId != null) {

            Optional<Band> band = bandDao.findById(bandId);

            if (band.isEmpty()) {
                throw new RuntimeException("Band not found. Aborting.");
            }

            album.setBand(band.get());
            band.get().getAlbums().add(album);

        }

        List<Song> songs = new ArrayList<>();

        for (Long songId : songIds) {

            Optional<Song> song = songDao.findById(songId);

            if (song.isEmpty()) {
                throw new RuntimeException("Song not found. Aborting.");
            }

            if (song.get().getAlbum() != null) {
                throw new RuntimeException("Song " + song.get().getName() + " is already associated with an album.");
            }

            songs.add(song.get());
            song.get().setAlbum(album);

        }

        album.setSongs(songs);

        return albumDao.save(album);

    }

    @Override
    @Transactional
    public void deleteAlbumById(Long id) {
        albumDao.deleteById(id);
    }

}
