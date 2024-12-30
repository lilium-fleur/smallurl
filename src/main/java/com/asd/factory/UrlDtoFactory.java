package com.asd.factory;

import com.asd.dto.UrlDto;
import com.asd.model.UrlEntity;
import org.springframework.stereotype.Component;

@Component
public class UrlDtoFactory {

    public UrlDto createUrlDto(UrlEntity entity) {
        return UrlDto.builder()
                .id(entity.getId())
                .original(entity.getOriginal())
                .link(entity.getLink())
                .createdAt(entity.getCreatedAt())
                .clicksCount(entity.getClicksCount())
                .build();
    }

}
