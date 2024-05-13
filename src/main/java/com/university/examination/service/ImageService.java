package com.university.examination.service;

import com.university.examination.dto.image.sdo.ImageSdo;
import com.university.examination.entity.media.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ImageService {
    Image getImage(Long id);
    String getPathImage(Long id);
    Long uploadFile(MultipartFile req);
    List<Long> uploadFiles(MultipartFile[] reqs);
    void delete(Long id);
    ImageSdo getResource(Long id) throws IOException;
}
