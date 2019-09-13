package com.rodarte.musicapp.models.dao;

import com.rodarte.musicapp.models.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandDao extends JpaRepository<Band, Long> {

}
