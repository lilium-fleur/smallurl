package com.asd.controller;

import com.asd.dto.GenerateUrlDto;
import com.asd.dto.UrlDto;
import com.asd.exception.BadRequestException;
import com.asd.factory.UrlDtoFactory;
import com.asd.model.UrlEntity;
import com.asd.repository.UrlRepository;
import com.asd.service.ShortUrlGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Transactional
@RestController
@RequiredArgsConstructor
public class GenerateController {

    private final ShortUrlGenerator shortUrlGenerator;

    private final UrlRepository urlRepository;

    private final UrlDtoFactory urlDtoFactory;

    private final String POST_GENERATE = "/generate";


    @PostMapping(POST_GENERATE)
    public UrlDto generate(@RequestBody GenerateUrlDto generateUrlDto) {

        if(generateUrlDto.original().isEmpty()){
            throw new BadRequestException(("generateUrlDto link cant be empty"));
        }

        Optional<UrlEntity> optionalUrlEntity = urlRepository
                .findByOriginal(generateUrlDto.original());

        if(optionalUrlEntity.isEmpty()){
            UrlEntity firstUrlEntity = shortUrlGenerator.generate(
                    UrlEntity
                            .builder()
                            .original(generateUrlDto.original())
                            .build()
            );
            UrlEntity urlEntity = urlRepository.saveAndFlush(firstUrlEntity);
            return urlDtoFactory.createUrlDto(urlEntity);

        }else{
            UrlEntity urlEntity = optionalUrlEntity.get();
            return urlDtoFactory.createUrlDto(urlEntity);
        }

    }

}
