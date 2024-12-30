package com.asd.controller;

import com.asd.exception.BadRequestException;
import com.asd.exception.NotFoundException;
import com.asd.model.UrlEntity;
import com.asd.repository.UrlRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Transactional
@Controller
@RequiredArgsConstructor
public class ShortUrlController {

    private final String GET_LINK = "/l/{link}";

    private final UrlRepository urlRepository;

    @GetMapping(GET_LINK)
    public String redirect(@PathVariable(name = "link") String link) {

        if(link.trim().isEmpty()){
            throw new BadRequestException("Link cannot be empty");
        }

        UrlEntity urlEntity = urlRepository
                .findByLink(link)
                .orElseThrow(() -> new NotFoundException("Link not found"));

        urlEntity.incrementCounter();

        urlRepository.saveAndFlush(urlEntity);

        return "redirect:" + urlEntity.getOriginal();

    }
}
