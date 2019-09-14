package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.AlbumDao;
import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.dto.BandDto;
import com.rodarte.musicapp.models.dto.BandsDto;
import com.rodarte.musicapp.models.entity.Band;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandDao bandDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public BandsDto getBands(
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

        Page<Band> bands = bandDao.findBySearchParams(
            name,
            country,
            yearRange == null ? null : Integer.parseInt(yearRange.get(0)),
            yearRange == null ? null : Integer.parseInt(yearRange.get(1)),
            PageRequest.of(page, size, Sort.by(direction, sortParam))
        );

        Boolean hasNext = bands.hasNext();
        Boolean hasPrevious = bands.hasPrevious();

        List<BandDto> bandDtos = bands.stream()
                .map(band -> modelMapper.map(band, BandDto.class))
                .collect(Collectors.toList());

        return new BandsDto(bandDtos, hasNext, hasPrevious);

    }

    @Override
    @Transactional(readOnly = true)
    public BandDto getBand(Long id) {
        return modelMapper.map(bandDao.findById(id).get(), BandDto.class);
    }

    @Override
    @Transactional
    public BandDto saveBand(Band band) {
        return modelMapper.map(bandDao.save(band), BandDto.class);
    }

    @Override
    @Transactional
    public void deleteBandById(Long id) {
        bandDao.deleteById(id);
    }

    @Override
    @Transactional
    public Integer albumCountByBandId(Long bandId) {
        return albumDao.countAlbumsByBandId(bandId);
    }

}
