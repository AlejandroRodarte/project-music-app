package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.AlbumDao;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public Integer countAlbumsByBandId(Long bandId) {
        return albumDao.countAlbumsByBandId(bandId);
    }

}
