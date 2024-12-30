package com.asd.repository;

import com.asd.model.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    Optional<UrlEntity> findByOriginal(String original);
    Optional<UrlEntity> findByLink(String link);


}
