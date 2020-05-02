package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.FavoritSalon;
import com.kmerconsulting.clariss.service.FavoritSalonService;
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
@RequestMapping("/api/favoritSalon")
public class FavoritSalonController {

    @Autowired
    FavoritSalonService favoritSalonService;

    @PostMapping("/create")
    public ResponseEntity<FavoritSalon> create(@Valid @RequestBody FavoritSalon favoritSalon) throws Exception {
        FavoritSalon createdFavoritSalon = favoritSalonService.save(favoritSalon);

        if (createdFavoritSalon == null) {
            throw new Exception("Error while saving favoritSalon");
        }

        return ResponseEntity.ok(createdFavoritSalon);
    }

    @GetMapping()
    public ResponseEntity<List<FavoritSalon>> findAll() {
        List<FavoritSalon> favoritSalons = favoritSalonService.findAll();

        if (favoritSalons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(favoritSalons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoritSalon> findById(@PathVariable(value = "id") Long id) {
        FavoritSalon favoritSalon = favoritSalonService.findById(id);
        if (favoritSalon == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(favoritSalon);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<FavoritSalon>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        List<FavoritSalon> favoritSalons = favoritSalonService.findBySalonId(salonId);
        if (favoritSalons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(favoritSalons);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoritSalon>> findByUserId(@PathVariable(value = "userId") Long userId) {
        List<FavoritSalon> favoritSalons = favoritSalonService.findByUserId(userId);
        if (favoritSalons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(favoritSalons);
    }

    /**
     * This method updates a favoritSalon based on his id and his details
     *
     * @param favoritSalonDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<FavoritSalon> update(@PathVariable(value = "id") Long id, @Valid @RequestBody FavoritSalon favoritSalonDetail) {
        FavoritSalon favoritSalon = favoritSalonService.findById(id);
        if (favoritSalon == null) {
            return ResponseEntity.notFound().build();
        }

        favoritSalon.setSalonId(favoritSalonDetail.getSalonId());
        favoritSalon.setUserId(favoritSalonDetail.getUserId());

        FavoritSalon updatedFavoritSalon = favoritSalonService.update(favoritSalon);

        return ResponseEntity.ok(updatedFavoritSalon);
    }

    /**
     * This method deletes a favoritSalon based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<FavoritSalon> delete(@PathVariable(value = "id") Long id) {
        FavoritSalon favoritSalon = favoritSalonService.findById(id);
        if (favoritSalon == null) {
            return ResponseEntity.notFound().build();
        }
        favoritSalonService.delete(id);

        return ResponseEntity.ok(favoritSalon);
    }
}
