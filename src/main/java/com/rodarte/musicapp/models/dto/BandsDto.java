package com.rodarte.musicapp.models.dto;

import java.util.List;

public class BandsDto {

    private List<BandDto> content;
    private Boolean hasNext;
    private Boolean hasPrevious;

    public BandsDto(List<BandDto> content, Boolean hasNext, Boolean hasPrevious) {
        this.content = content;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public List<BandDto> getContent() {
        return content;
    }

    public void setContent(List<BandDto> content) {
        this.content = content;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

}
