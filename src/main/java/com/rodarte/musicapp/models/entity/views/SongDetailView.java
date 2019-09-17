package com.rodarte.musicapp.models.entity.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "song_details_view")
@Immutable
public class SongDetailView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lyrics")
    @Lob
    private String lyrics;

    @Column(name = "youtube_url")
    private String youtubeUrl;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "song_id")
    private Long songId;

    @Column(name = "name")
    private String name;

    @Column(name = "track_number")
    private Integer trackNumber;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "album_id")
    private Long albumId;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "album_image_path")
    private String albumImagePath;

    @Column(name = "band_id")
    private Long bandId;

    @Column(name = "band_name")
    private String bandName;

    @Column(name = "band_image_path")
    private String bandImagePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
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

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumImagePath() {
        return albumImagePath;
    }

    public void setAlbumImagePath(String albumImagePath) {
        this.albumImagePath = albumImagePath;
    }

    public Long getBandId() {
        return bandId;
    }

    public void setBandId(Long bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBandImagePath() {
        return bandImagePath;
    }

    public void setBandImagePath(String bandImagePath) {
        this.bandImagePath = bandImagePath;
    }

}
