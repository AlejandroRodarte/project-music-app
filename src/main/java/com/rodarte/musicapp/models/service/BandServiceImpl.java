package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.AlbumDao;
import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.dao.BandViewDao;
import com.rodarte.musicapp.models.dto.BandDto;
import com.rodarte.musicapp.models.entity.Album;
import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.entity.views.BandView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandDao bandDao;

    @Autowired
    private BandViewDao bandViewDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<BandView> getBands(
        Integer page,
        Integer size,
        String sort,
        String name,
        String country,
        List<String> yearRange,
        List<String> albumRange,
        List<String> songRange
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

        Page<BandView> bands = bandViewDao.findAllBySearchParams(
            name,
            country,
            yearRange == null ? null : Integer.parseInt(yearRange.get(0)),
            yearRange == null ? null : Integer.parseInt(yearRange.get(1)),
            albumRange == null ? null : Integer.parseInt(albumRange.get(0)),
            albumRange == null ? null : Integer.parseInt(albumRange.get(1)),
            songRange == null ? null : Integer.parseInt(songRange.get(0)),
            songRange == null ? null : Integer.parseInt(songRange.get(1)),
            PageRequest.of(page, size, Sort.by(direction, sortParam))
        );

        return bands;

    }

    @Override
    @Transactional(readOnly = true)
    public BandView getBand(Long id) {
        return bandViewDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public BandDto saveBand(Band band) {

        List<Album> albums = new ArrayList<>();

        for (Long id : band.getAlbums().stream().map(Album::getId).collect(Collectors.toList())) {
            Album album = albumDao.findById(id).get();
            albums.add(album);
        }

        band.setAlbums(albums);

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
