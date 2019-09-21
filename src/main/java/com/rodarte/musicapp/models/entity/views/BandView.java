package com.rodarte.musicapp.models.entity.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bands_view")
@Immutable
public class BandView implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "origin_country")
    private String originCountry;

    @Column(name = "origin_year")
    private Integer originYear;

    @Column(name = "album_count")
    private Integer albumCount;

    @Column(name = "song_count")
    private Integer songCount;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "genre")
    private String genre;

    @Column(name = "artist_first_name")
    private String artistFirstName;

    @Column(name = "artist_last_name")
    private String artistLastName;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "song_name")
    private String songName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public Integer getOriginYear() {
        return originYear;
    }

    public void setOriginYear(Integer originYear) {
        this.originYear = originYear;
    }

    public Integer getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(Integer albumCount) {
        this.albumCount = albumCount;
    }

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtistFirstName() {
        return artistFirstName;
    }

    public void setArtistFirstName(String artistFirstName) {
        this.artistFirstName = artistFirstName;
    }

    public String getArtistLastName() {
        return artistLastName;
    }

    public void setArtistLastName(String artistLastName) {
        this.artistLastName = artistLastName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "BandView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", originYear=" + originYear +
                ", albumCount=" + albumCount +
                ", songCount=" + songCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", genre='" + genre + '\'' +
                ", artistFirstName='" + artistFirstName + '\'' +
                ", artistLastName='" + artistLastName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", songName='" + songName + '\'' +
                '}';
    }

}
