package com.university.examination.service.imp;

import com.university.examination.dto.image.sdo.ImageSdo;
import com.university.examination.entity.media.Image;
import com.university.examination.exception.CustomException;
import com.university.examination.repository.ImageRepo;
import com.university.examination.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.university.examination.util.constant.Error.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {
    private final ImageRepo imageRepository;

    @Value("${media.img_path}")
    private String imgFolder;
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    private static final List<String> ACCEPTED_CONTENT_TYPES = Arrays.asList(
            "image/jpeg", "image/png", "image/gif"
    );

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public String getPathImage(Long id) {

        Image media = imageRepository.findById(id).orElse(null);
        String path = "";
        if (media != null) {
            path = imgFolder + File.separator + media.getFilename();
        }

        return path;
    }

    public Long uploadFile(MultipartFile req) {

        if (req.isEmpty()) {
            throw new CustomException(ERROR_EMPTY_FILE);
        }

        String contentType = req.getContentType();
        if (!ACCEPTED_CONTENT_TYPES.contains(contentType)) {
            throw new CustomException(ERROR_TYPE_FILE );
        }

        if (req.getSize() > MAX_FILE_SIZE) {
            throw new CustomException(ERROR_LIMIT_SIZE_FILE);
        }

        try {
            File dir = new File(imgFolder);
            if (!dir.exists()) {
                boolean created = dir.mkdirs();
                if (!created) {
                    throw new CustomException(ERROR_DIRECTORY_FILE);
                }
            }

            Random rand = new Random();
            int ranNum = rand.nextInt();
            String fileName = req.getName() + ranNum + getFileExtension(req.getOriginalFilename());
            Path filePath = Paths.get(dir.getAbsolutePath(), fileName);

            try (OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                outputStream.write(req.getBytes());
            }

            Image image = new Image();
            image.setFilename(fileName);
            image.setType(contentType);
            image = imageRepository.save(image);
            return image.getId();
        } catch (IOException e) {
            throw new CustomException(ERROR_SAVE_FILE);
        }
    }

    public List<Long> uploadFiles(MultipartFile[] reqs){
        List<Long> imagesId = new ArrayList<>();
        Arrays.stream(reqs).forEach(file -> imagesId.add(uploadFile(file)));
        return imagesId;
    }

    public void delete(Long id) {

        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new CustomException(ERROR_FIND_IMAGE));
        try {
            Path filePath = Paths.get(imgFolder, image.getFilename());
            File file = filePath.toFile();
            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    throw new CustomException(ERROR_DELETE_IMAGE);
                }
            } else {
                throw new CustomException(ERROR_NOT_FOUND_IMAGE);
            }
        } catch (Exception e) {
            throw new CustomException(ERROR_DELETE_IMAGE);
        }
        imageRepository.deleteById(id);

    }

    public ImageSdo getResource(Long id) throws IOException {
        Path path = Paths.get(getPathImage(id));
        Image media = getImage(id);
        Resource resource = new UrlResource(path.toUri());

        if (resource.exists()) {
            return  ImageSdo.of(resource, media.getType());
        } else {
            throw new CustomException(ERROR_OPEN_IMAGE);
        }
    }

    public String getFileExtension(String fileName) {
        return "." + FilenameUtils.getExtension(fileName);
    }
}

