package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.Salon;
import com.kmerconsulting.clariss.service.SalonService;
import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salon")
public class SalonController {
    @Autowired
    SalonService salonService;

    @PostMapping()
    public ResponseEntity<Salon> create(@Valid @RequestBody Salon salon) throws Exception {
        salon.setCreatedAt(LocalDateTime.now());
        salon.setViews(0);
        Salon createdSalon = salonService.save(salon);
        if(createdSalon == null){
            throw new Exception("Error while saving salon");
        }
        return ResponseEntity.ok(createdSalon);
    }

    /**
     * This method updates a salon based on his id and his details
     *
     * @param salon
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Salon> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Salon salon) throws Exception {
        Salon salonFound = salonService.findById(id);

        if (salonFound == null) {
            throw new Exception("Error while saving salon");
        }
        salonFound.setAddressId(salon.getAddressId());
        salonFound.setDescription(salon.getDescription());
        salonFound.setEmail(salon.getEmail());
        salonFound.setName(salon.getName());
        salonFound.setPhone(salon.getPhone());
        salonFound.setRating(salon.getRating());
        salonFound.setStatus(GlobalStatus.active);
        salonFound.setViews(salon.getViews());
        salonFound.setCreatedAt(salon.getCreatedAt());

        Salon updatedSalon = salonService.update(salonFound);

        return ResponseEntity.ok(updatedSalon);
    }

    /**
     * get all salons
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<Salon>> findAll() {
        List<Salon> salones = salonService.findAll();
        if (salones == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(salones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salon> findById(@PathVariable(value = "id") Long id) {
        Salon salon = salonService.findById(id);
        if (salon == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(salon);
    }

    /**
     * This method deletes a salon based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Salon> delete(@PathVariable(value = "id") Long id) throws Exception {
        Salon salon = salonService.findById(id);
        if (salon == null) {
            throw new Exception("Error while saving salon");
        }

        salonService.delete(id);

        return ResponseEntity.ok(salon);
    }

}
