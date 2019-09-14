package com.rodarte.musicapp.models.dto;

import java.util.Date;

public class AlbumDto {

    private Long id;
    private String name;
    private String imagePath;
    private Integer releaseYear;
    private Date createdAt;
    private Date updatedAt;
    private BandToAlbumDto band;

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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
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

    public BandToAlbumDto getBand() {
        return band;
    }

    public void setBand(BandToAlbumDto band) {
        this.band = band;
    }

}
