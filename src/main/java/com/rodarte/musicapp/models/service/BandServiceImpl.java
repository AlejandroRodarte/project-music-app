package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.entity.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandDao bandDao;

    @Override
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

}
