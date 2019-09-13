package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.entity.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandDao bandDao;

    @Override
    public Page<Band> getAllBands(PageRequest request, Map<String, String> searchParams) {
        return bandDao.findBySearchParams(
                searchParams.get("name"),
                searchParams.get("country"),
                Integer.parseInt(searchParams.get("startYear")),
                Integer.parseInt(searchParams.get("endYear")),
                request
        );
    }

}
