package com.rodarte.musicapp.models.controller;

import com.rodarte.musicapp.models.entity.Band;
import com.rodarte.musicapp.models.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @GetMapping
    public Page<Band> getAllBands(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sort,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) List<Integer> yearRange
    ) {

        String[] sortArr = sort.split(":");

        String sortParam = sortArr[0];
        String sortDirection = sortArr[1];

        Sort.Direction direction;

        Map<String, String> searchParams = new LinkedHashMap<>();

        searchParams.put("name", name);
        searchParams.put("country", country);

        if (yearRange == null) {
            searchParams.put("startYear", null);
            searchParams.put("endYear", null);
        } else {
            searchParams.put("startYear", yearRange.get(0).toString());
            searchParams.put("endYear", yearRange.get(1).toString());
        }

        if (sortDirection.equals("asc")) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }

        return bandService.getAllBands(PageRequest.of(page, size, Sort.by(direction, sortParam)), searchParams);

    }

}
