package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.entity.SongDetail;
import com.rodarte.musicapp.models.entity.views.SongDetailView;

public interface SongDetailService {

    SongDetailView getSongDetail(Long songId);

    SongDetail saveSongDetail(SongDetail songDetail, Long songId);

    SongDetail updateSongDetail(SongDetail songDetail, Long songId);

    void deleteSongDetail(Long songId);

}
