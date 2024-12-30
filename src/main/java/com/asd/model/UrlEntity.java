package com.asd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="url")
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String original;

    @Column(unique = true, nullable = false)
    private String link;

    @Builder.Default
    @Column(name="clicks_count", nullable = false)
    private AtomicLong clicksCount = new AtomicLong(0);


    @Builder.Default
    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public void incrementCounter(){
        clicksCount.incrementAndGet();
    }

}
