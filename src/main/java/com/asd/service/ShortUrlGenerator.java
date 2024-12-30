package com.asd.service;


import com.asd.model.UrlEntity;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShortUrlGenerator {


    public UrlEntity generate(UrlEntity urlEntity){
        String uuid = UUID.randomUUID().toString();
        String result = uuid.replace("-", "").substring(0, 8);
        urlEntity.setLink(result);
        return urlEntity;

    }


}
