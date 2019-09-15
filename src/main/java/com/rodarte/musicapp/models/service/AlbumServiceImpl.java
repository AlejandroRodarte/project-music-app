package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.AlbumDao;
import com.rodarte.musicapp.models.dao.AlbumViewDao;
import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.dto.AlbumDto;
import com.rodarte.musicapp.models.entity.Album;
import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.entity.views.AlbumView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private BandDao bandDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlbumViewDao albumViewDao;

    @Override
    @Transactional
    public Page<AlbumView> getAlbums(
        Integer page,
        Integer size,
        String sort,
        String name,
        List<String> yearRange,
        String bandId,
        String bandName,
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

        return albumViewDao.findAllBySearchParams(
            name,
            yearRange == null ? null : Integer.parseInt(yearRange.get(0)),
            yearRange == null ? null : Integer.parseInt(yearRange.get(1)),
            bandId == null ? null : Long.parseLong(bandId),
            bandName,
            songRange == null ? null : Integer.parseInt(songRange.get(0)),
            songRange == null ? null : Integer.parseInt(songRange.get(1)),
            PageRequest.of(page, size, Sort.by(direction, sortParam))
        );

    }

    @Override
    @Transactional
    public AlbumView getAlbum(Long id) {
        return albumViewDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public AlbumDto saveAlbum(Album album, boolean setBandId) {
        Band foundBand = bandDao.findById(album.getBand().getId()).get();
        album.setBand(foundBand);
        return modelMapper.map(albumDao.save(album), AlbumDto.class);
    }

    @Override
    @Transactional
    public void deleteAlbumById(Long id) {
        albumDao.deleteById(id);
    }

}
