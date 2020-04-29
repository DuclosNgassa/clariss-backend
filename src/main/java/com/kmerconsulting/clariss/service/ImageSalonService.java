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
    @Autowired
    ImageSalonRepository imageSalonRepository;

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

    public void delete(Long id) {
        imageSalonRepository.deleteById(id);
    }

    public String saveImage(MultipartFile imageFile) throws Exception {
        String folder = "src/main/resources/static/imagesSalon/";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path, bytes);
        return imageFile.getOriginalFilename();
    }
}
