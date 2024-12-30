package com.asd.controller;


import com.asd.dto.UrlDto;
import com.asd.exception.BadRequestException;
import com.asd.factory.UrlDtoFactory;
import com.asd.model.UrlEntity;
import com.asd.repository.UrlRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Transactional
@RestController
@RequiredArgsConstructor
public class StatsController {

    private final String GET_STATS_LINK = "/stats/l/{link}";
    private final String GET_STATS = "/stats";

    private final UrlRepository urlRepository;
    private final UrlDtoFactory urlDtoFactory;


    @GetMapping(GET_STATS_LINK)
    public UrlDto getStatsByLink(@PathVariable(name = "link") String link){
        if(link.trim().isEmpty()){
            throw new BadRequestException(("link cant be empty"));
        }

        UrlEntity urlEntity = urlRepository
                .findByLink(link)
                .orElseThrow(() -> new BadRequestException(("link not found")));

        return urlDtoFactory.createUrlDto(urlEntity);


    }

    @GetMapping(GET_STATS)
    public Page<UrlDto> getRating(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10") Integer count){



        final String sortBy = "clicksCount";
        final String order = "DESC";

        Pageable pageable = PageRequest.of(page, count, Sort.Direction.valueOf(order), sortBy);

        return urlRepository.findAll(pageable)
                .map(urlDtoFactory::createUrlDto);

    }
}
