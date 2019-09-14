package com.rodarte.musicapp.models.dto;

import java.util.List;

public class AlbumsDto {

    private List<AlbumDto> content;
    private Boolean hasPrevious;
    private Boolean hasNext;

    public AlbumsDto(List<AlbumDto> content, Boolean hasPrevious, Boolean hasNext) {
        this.content = content;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
    }

    public List<AlbumDto> getContent() {
        return content;
    }

    public void setContent(List<AlbumDto> content) {
        this.content = content;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

}
