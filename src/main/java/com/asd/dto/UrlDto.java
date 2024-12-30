package com.asd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UrlDto {

    private Long id;

    private String original;

    private String link;

    private LocalDateTime createdAt;

    private AtomicLong clicksCount;
}
