package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.entity.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandDao bandDao;

    @Override
    public Page<Band> getAllBands(PageRequest request) {
        return bandDao.findAll(request);
    }

}
