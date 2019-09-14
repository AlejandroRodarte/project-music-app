package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.AlbumDao;
import com.rodarte.musicapp.models.dao.BandDao;
import com.rodarte.musicapp.models.dto.AlbumDto;
import com.rodarte.musicapp.models.dto.AlbumsDto;
import com.rodarte.musicapp.models.dto.BandToAlbumDto;
import com.rodarte.musicapp.models.entity.Album;
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
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private BandDao bandDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Integer countAlbumsByBandId(Long bandId) {
        return albumDao.countAlbumsByBandId(bandId);
    }

    @Override
    @Transactional
    public AlbumsDto getAlbums(
        Integer page,
        Integer size,
        String sort,
        String name,
        List<String> yearRange,
        String bandId
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

        Page<Album> albums = albumDao.findBySearchParams(
            name,
            yearRange == null ? null : Integer.parseInt(yearRange.get(0)),
            yearRange == null ? null : Integer.parseInt(yearRange.get(1)),
            bandId == null ? null : Long.parseLong(bandId),
            PageRequest.of(page, size, Sort.by(direction, sortParam))
        );

        Boolean hasNext = albums.hasNext();
        Boolean hasPrevious = albums.hasPrevious();

        List<AlbumDto> albumDtos = albums.stream()
            .map(album -> {
                AlbumDto albumDto = modelMapper.map(album, AlbumDto.class);
                albumDto.setBand(modelMapper.map(album.getBand(), BandToAlbumDto.class));
                return albumDto;
            }).collect(Collectors.toList());

        return new AlbumsDto(albumDtos, hasNext, hasPrevious);

    }

    @Override
    @Transactional
    public AlbumDto getAlbum(Long id) {
        return modelMapper.map(albumDao.findById(id).get(), AlbumDto.class);
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
