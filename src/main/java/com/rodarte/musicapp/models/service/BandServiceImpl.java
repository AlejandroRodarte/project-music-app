package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.AlbumDao;
import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.entity.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandDao bandDao;

    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Band> getBands(
            Integer page,
            Integer size,
            String sort,
            String name,
            String country,
            List<String> yearRange
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

        return bandDao.findBySearchParams(
                name,
                country,
                yearRange == null ? null : Integer.parseInt(yearRange.get(0)),
                yearRange == null ? null : Integer.parseInt(yearRange.get(1)),
                PageRequest.of(page, size, Sort.by(direction, sortParam))
        );

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Band> getBand(Long id) {
        System.out.println(albumDao.countAlbumsByBandId(id));
        return bandDao.findById(id);
    }

    @Override
    @Transactional
    public Band saveBand(Band band) {
        return bandDao.save(band);
    }

    @Override
    @Transactional
    public void deleteBandById(Long id) {
        bandDao.deleteById(id);
    }

}
