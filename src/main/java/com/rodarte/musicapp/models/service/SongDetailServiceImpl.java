package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.SongDao;
import com.rodarte.musicapp.models.dao.SongDetailDao;
import com.rodarte.musicapp.models.dao.SongDetailViewDao;
import com.rodarte.musicapp.models.entity.Song;
import com.rodarte.musicapp.models.entity.SongDetail;
import com.rodarte.musicapp.models.entity.views.SongDetailView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SongDetailServiceImpl implements SongDetailService {

    @Autowired
    private SongDao songDao;

    @Autowired
    private SongDetailDao songDetailDao;

    @Autowired
    private SongDetailViewDao songDetailViewDao;

    @Override
    @Transactional(readOnly = true)
    public SongDetailView getSongDetail(Long songId) {
        return songDetailViewDao.findBySongId(songId);
    }

    @Override
    @Transactional
    public SongDetail saveSongDetail(SongDetail songDetail, Long songId) {

        Optional<Song> song = songDao.findById(songId);

        if (song.isEmpty()) {
            throw new RuntimeException("Song not found. Aborting.");
        }

        songDetail.setSong(song.get());
        song.get().setSongDetail(songDetail);

        return songDetailDao.save(songDetail);

    }

    @Override
    @Transactional
    public SongDetail updateSongDetail(SongDetail songDetail, Long songId) {

        SongDetail dbSongDetail = songDetailDao.findBySongId(songId);

        updateFields(dbSongDetail, songDetail);

        return songDetailDao.save(dbSongDetail);

    }

    @Override
    @Transactional
    public void deleteSongDetail(Long songId) {
        songDetailDao.deleteBySongId(songId);
    }

    private void updateFields(SongDetail dbSongDetail, SongDetail songDetail) {

        if (songDetail.getLyrics() != null) {
            dbSongDetail.setLyrics(songDetail.getLyrics());
        }

        if (songDetail.getYoutubeUrl() != null) {
            dbSongDetail.setYoutubeUrl(songDetail.getYoutubeUrl());
        }

    }

}
