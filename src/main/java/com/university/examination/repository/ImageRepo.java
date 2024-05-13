package com.university.examination.repository;

import com.university.examination.entity.media.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
