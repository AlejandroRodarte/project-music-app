package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.AlbumDao;
import com.rodarte.musicapp.models.dao.SongDao;
import com.rodarte.musicapp.models.dao.SongViewDao;
import com.rodarte.musicapp.models.entity.Album;
import com.rodarte.musicapp.models.entity.Song;
import com.rodarte.musicapp.models.entity.views.SongView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private SongDao songDao;

    @Autowired
    private SongViewDao songViewDao;

    @Override
    @Transactional(readOnly = true)
    public Page<SongView> getSongs(
        Integer page,
        Integer size,
        String sort,
        String name,
        String trackNumber,
        List<String> durationRange,
        String albumId,
        String albumName,
        String bandId,
        String bandName
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

        return songViewDao.findAllBySearchParams(
            name,
            trackNumber == null ? null : Integer.parseInt(trackNumber),
            durationRange == null ? null : Integer.parseInt(durationRange.get(0)),
            durationRange == null ? null : Integer.parseInt(durationRange.get(1)),
            albumId == null ? null : Long.parseLong(albumId),
            albumName,
            bandId == null ? null : Long.parseLong(bandId),
            bandName,
            PageRequest.of(page, size, Sort.by(direction, sortParam))
        );

    }

    @Override
    @Transactional(readOnly = true)
    public SongView getSong(Long id) {
        return songViewDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Song saveSong(Song song) {

        if (song.getAlbum() != null) {

            Optional<Album> album = albumDao.findById(song.getAlbum().getId());

            if (album.isEmpty()) {
                throw new RuntimeException("Album not found. Aborting.");
            }

            song.setAlbum(album.get());
            album.get().getSongs().add(song);

        }

        return songDao.save(song);

    }

    @Override
    @Transactional
    public Song updateSong(Song song, Long id) {
        return null;
    }

    @Override
    @Transactional
    public Song deleteSongById(Long id) {
        return null;
    }

}
