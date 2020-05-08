package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.ImageSalon;
import com.kmerconsulting.clariss.repository.ImageSalonRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageSalonService {
    private static String uploadDirectory = System.getProperty("user.dir") + "/upload/imagesSalon";
    @Autowired
    ImageSalonRepository imageSalonRepository;
    @Autowired
    FileService fileService;

    public ImageSalon save(ImageSalon imageSalon) {
        return imageSalonRepository.save(imageSalon);
    }

    public ImageSalon update(ImageSalon imageSalon) {
        return imageSalonRepository.save(imageSalon);
    }

    public List<ImageSalon> findAll() {
        return imageSalonRepository.findAll();
    }

    public ImageSalon findById(Long id) {
        return imageSalonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity ImageSalon with id: " + id + "not found"));
    }

    public List<ImageSalon> findBySalonId(Long salonId) {
        return imageSalonRepository.findBySalonId(salonId);
    }

    public List<ImageSalon> findByImageUrl(String imageUrl) {
        return imageSalonRepository.findByImageUrl(imageUrl);
    }

    public void delete(ImageSalon imageSalon) {
        imageSalonRepository.deleteById(imageSalon.getId());
        deleteFile(imageSalon.getImageUrl());

    }

    public String saveImage(MultipartFile imageFile) throws Exception {
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(uploadDirectory, imageFile.getOriginalFilename());
        Files.write(path, bytes);
        return imageFile.getOriginalFilename();
    }

    public void deleteFile(String fileName) {
        fileService.deleteFile(fileName);
    }

}
