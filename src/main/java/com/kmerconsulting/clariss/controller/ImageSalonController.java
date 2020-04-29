package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.ImageSalon;
import com.kmerconsulting.clariss.service.ImageSalonService;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/imageSalon")
public class ImageSalonController {

    //Logger logger = Logger.
    @Autowired
    ImageSalonService imageSalonService;

    @PostMapping()
    public ResponseEntity<ImageSalon> create(@Valid @RequestBody ImageSalon imageSalon) throws Exception {
        ImageSalon createdImageSalon = imageSalonService.save(imageSalon);

        if (createdImageSalon == null) {
            throw new Exception("Error while saving imageSalon");
        }

        return ResponseEntity.ok(createdImageSalon);
    }

    @GetMapping()
    public ResponseEntity<List<ImageSalon>> findAll() {
        List<ImageSalon> imageSalons = imageSalonService.findAll();

        if (imageSalons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(imageSalons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageSalon> findById(@PathVariable(value = "id") Long id) {
        ImageSalon imageSalon = imageSalonService.findById(id);
        if (imageSalon == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(imageSalon);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<ImageSalon>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        List<ImageSalon> imageSalons = imageSalonService.findBySalonId(salonId);
        if (imageSalons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(imageSalons);
    }

    /**
     * This method updates a imageSalon based on his id and his details
     *
     * @param imageSalonDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ImageSalon> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ImageSalon imageSalonDetail) {
        ImageSalon imageSalon = imageSalonService.findById(id);
        if (imageSalon == null) {
            return ResponseEntity.notFound().build();
        }

        imageSalon.setSalonId(imageSalonDetail.getSalonId());
        imageSalon.setImageUrl(imageSalonDetail.getImageUrl());

        ImageSalon updatedImageSalon = imageSalonService.update(imageSalon);

        return ResponseEntity.ok(updatedImageSalon);
    }

    /**
     * This method deletes a imageSalon based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ImageSalon> delete(@PathVariable(value = "id") Long id) {
        ImageSalon imageSalon = imageSalonService.findById(id);
        if (imageSalon == null) {
            return ResponseEntity.notFound().build();
        }
        imageSalonService.delete(id);

        return ResponseEntity.ok(imageSalon);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
        String imageUrl = "";
        try {
            imageUrl = imageSalonService.saveImage(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO log error
            imageUrl = "error";
        }
        return ResponseEntity.ok(imageUrl);
    }
}
