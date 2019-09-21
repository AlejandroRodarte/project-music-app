package com.rodarte.musicapp.models.service;

import com.rodarte.musicapp.models.dao.*;
import com.rodarte.musicapp.models.entity.Album;
import com.rodarte.musicapp.models.entity.Artist;
import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.entity.Genre;
import com.rodarte.musicapp.models.entity.views.BandView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private ArtistDao artistDao;

    @Autowired
    private GenreDao genreDao;

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
        List<String> songRange,
        String genre,
        String artistFirstName,
        String artistLastName
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

        return bandViewDao.findAllBySearchParams(
            name,
            country,
            yearRange == null ? null : Integer.parseInt(yearRange.get(0)),
            yearRange == null ? null : Integer.parseInt(yearRange.get(1)),
            albumRange == null ? null : Integer.parseInt(albumRange.get(0)),
            albumRange == null ? null : Integer.parseInt(albumRange.get(1)),
            songRange == null ? null : Integer.parseInt(songRange.get(0)),
            songRange == null ? null : Integer.parseInt(songRange.get(1)),
            genre,
            artistFirstName,
            artistLastName,
            PageRequest.of(page, size, Sort.by(direction, sortParam))
        );

    }

    @Override
    @Transactional(readOnly = true)
    public BandView getBand(Long id) {
        return bandViewDao.findByIdGroupByBandId(id);
    }

    @Override
    @Transactional
    public Band saveBand(Band band) {

        List<Long> albumIds = new ArrayList<>();
        List<Long> artistIds = new ArrayList<>();
        List<Long> genreIds = new ArrayList<>();

        List<Album> albums = new ArrayList<>();
        List<Artist> artists = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        if (band.getAlbums() != null) {
            albumIds = band.getAlbums().stream().map(Album::getId).collect(Collectors.toList());
        }

        for (Long albumId : albumIds) {

            Optional<Album> album = albumDao.findById(albumId);

            if (album.isEmpty()) {
                throw new RuntimeException("Album not found. Operation cancelled.");
            }

            if (album.get().getBand() != null) {
                throw new RuntimeException("Album " + album.get().getName() + " is already associated with a band. Operation cancelled.");
            }

            albums.add(album.get());
            album.get().setBand(band);

        }

        if (band.getArtists() != null) {
            artistIds = band.getArtists().stream().map(Artist::getId).collect(Collectors.toList());
        }

        for (Long artistId : artistIds) {

            Optional<Artist> artist = artistDao.findById(artistId);

            if (artist.isEmpty()) {
                throw new RuntimeException("Artist not found. Aborting.");
            }

            artists.add(artist.get());
            artist.get().getBands().add(band);

        }

        if (band.getGenres() != null) {
            genreIds = band.getGenres().stream().map(Genre::getId).collect(Collectors.toList());
        }

        for (Long genreId : genreIds) {

            Optional<Genre> genre = genreDao.findById(genreId);

            if (genre.isEmpty()) {
                throw new RuntimeException("Genre not found. Aborting.");
            }

            genres.add(genre.get());
            genre.get().getBands().add(band);

        }

        band.setAlbums(albums);
        band.setArtists(artists);
        band.setGenres(genres);

        return bandDao.save(band);

    }

    @Override
    @Transactional
    public Band updateBand(Band band, Long id) {

        Optional<Band> dbBand = bandDao.findById(id);

        if (dbBand.isEmpty()) {
            throw new RuntimeException("Band not found. Aborting.");
        }

        updateFields(dbBand.get(), band);

        List<Album> dbBandAlbums = albumDao.findByBandId(id);
        List<Artist> dbBandArtists = artistDao.findByBands(dbBand.get());
        List<Genre> dbBandGenres = genreDao.findByBands(dbBand.get());

        List<Long> albumsToDelete = dbBandAlbums.stream().map(Album::getId).collect(Collectors.toList());
        List<Long> albumsToAdd = band.getAlbums().stream().map(Album::getId).collect(Collectors.toList());

        List<Long> artistsToDelete = dbBandArtists.stream().map(Artist::getId).collect(Collectors.toList());
        List<Long> artistsToAdd = band.getArtists().stream().map(Artist::getId).collect(Collectors.toList());

        List<Long> genresToDelete = dbBandGenres.stream().map(Genre::getId).collect(Collectors.toList());
        List<Long> genresToAdd = band.getGenres().stream().map(Genre::getId).collect(Collectors.toList());

        for (int i = 0; i < albumsToDelete.size(); i++) {

            for (int j = 0; j < albumsToAdd.size(); j++) {

                if (albumsToDelete.get(i).equals(albumsToAdd.get(j))) {
                    albumsToAdd.remove(j);
                    albumsToDelete.remove(i);
                    i--;
                    break;
                }

            }

        }

        for (int i = 0; i < artistsToDelete.size(); i++) {

            for (int j = 0; j < artistsToAdd.size(); j++) {

                if (artistsToDelete.get(i).equals(artistsToAdd.get(j))) {
                    artistsToAdd.remove(j);
                    artistsToDelete.remove(i);
                    i--;
                    break;
                }

            }

        }

        for (int i = 0; i < genresToDelete.size(); i++) {

            for (int j = 0; j < genresToAdd.size(); j++) {

                if (genresToDelete.get(i).equals(genresToAdd.get(j))) {
                    genresToAdd.remove(j);
                    genresToDelete.remove(i);
                    i--;
                    break;
                }

            }

        }

        dbBandAlbums = dbBandAlbums.stream().filter(album -> {
            for (Long albumId : albumsToDelete) {
                if (album.getId().equals(albumId)) {
                    album.setBand(null);
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

        dbBandArtists = dbBandArtists.stream().filter(artist -> {
            for (Long artistId : artistsToDelete) {
                if (artist.getId().equals(artistId)) {
                    artist.getBands().remove(band);
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

        dbBandGenres = dbBandGenres.stream().filter(genre -> {
            for (Long genreId : genresToDelete) {
                if (genre.getId().equals(genreId)) {
                    genre.getBands().remove(band);
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

        for (Long albumId : albumsToAdd) {

            Optional<Album> album = albumDao.findById(albumId);

            if (album.isEmpty()) {
                throw new RuntimeException("Album not found. Operation cancelled.");
            }

            if (album.get().getBand() != null) {
                throw new RuntimeException("Album " + album.get().getName() + " is already associated with a band. Operation cancelled.");
            }

            dbBandAlbums.add(album.get());
            album.get().setBand(dbBand.get());

        }

        for (Long artistId : artistsToAdd) {

            Optional<Artist> artist = artistDao.findById(artistId);

            if (artist.isEmpty()) {
                throw new RuntimeException("Album not found. Operation cancelled.");
            }

            dbBandArtists.add(artist.get());
            artist.get().getBands().add(dbBand.get());

        }

        for (Long genreId : genresToAdd) {

            Optional<Genre> genre = genreDao.findById(genreId);

            if (genre.isEmpty()) {
                throw new RuntimeException("Album not found. Operation cancelled.");
            }


            dbBandGenres.add(genre.get());
            genre.get().getBands().add(dbBand.get());

        }

        dbBand.get().setAlbums(dbBandAlbums);
        dbBand.get().setArtists(dbBandArtists);
        dbBand.get().setGenres(dbBandGenres);

        return bandDao.save(dbBand.get());

    }

    @Override
    @Transactional
    public void deleteBandById(Long id) {
        bandDao.deleteById(id);
    }

    private void updateFields(Band bandToUpdate, Band updatedBand) {

        if (updatedBand.getName() != null) {
            bandToUpdate.setName(updatedBand.getName());
        }

        if (updatedBand.getImagePath() != null) {
            bandToUpdate.setImagePath(updatedBand.getImagePath());
        }

        if (updatedBand.getOriginCountry() != null) {
            bandToUpdate.setOriginCountry(updatedBand.getOriginCountry());
        }

        if (updatedBand.getOriginYear() != null) {
            bandToUpdate.setOriginYear(updatedBand.getOriginYear());
        }

    }

}
