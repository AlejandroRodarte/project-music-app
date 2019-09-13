package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.entity.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandDao bandDao;

    @Override
    public List<Band> getAllBands() {
        return bandDao.findAll();
    }

}
