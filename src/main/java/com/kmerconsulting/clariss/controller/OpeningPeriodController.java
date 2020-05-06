package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.OpeningPeriod;
import com.kmerconsulting.clariss.service.OpeningPeriodService;
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
@RequestMapping("/api/openingPeriod")
public class OpeningPeriodController {

    @Autowired
    OpeningPeriodService openingPeriodService;

    @PostMapping()
    public ResponseEntity<OpeningPeriod> save(@Valid @RequestBody OpeningPeriod openingPeriod) throws Exception {
        OpeningPeriod createdOpeningPeriod = openingPeriodService.save(openingPeriod);

        if (createdOpeningPeriod == null) {
            throw new Exception("Error while saving openingPeriod");
        }

        return ResponseEntity.ok(createdOpeningPeriod);
    }

    @GetMapping()
    public ResponseEntity<List<OpeningPeriod>> findAll() {
        List<OpeningPeriod> openingPeriods = openingPeriodService.findAll();

        if (openingPeriods == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(openingPeriods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpeningPeriod> findById(@PathVariable(value = "id") Long id) {
        OpeningPeriod openingPeriod = openingPeriodService.findById(id);
        if (openingPeriod == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(openingPeriod);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<OpeningPeriod>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        List<OpeningPeriod> openingPeriods = openingPeriodService.findBySalonId(salonId);
        if (openingPeriods == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(openingPeriods);
    }

    /**
     * This method updates a openingPeriod based on his id and his details
     *
     * @param openingPeriodDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<OpeningPeriod> update(@PathVariable(value = "id") Long id, @Valid @RequestBody OpeningPeriod openingPeriodDetail) {
        OpeningPeriod openingPeriod = openingPeriodService.findById(id);
        if (openingPeriod == null) {
            return ResponseEntity.notFound().build();
        }

        openingPeriod.setSalonId(openingPeriodDetail.getSalonId());
        openingPeriod.setStart(openingPeriodDetail.getStart());
        openingPeriod.setEnd(openingPeriodDetail.getEnd());
        openingPeriod.setWeekday(openingPeriodDetail.getWeekday());

        OpeningPeriod updatedOpeningPeriod = openingPeriodService.update(openingPeriod);

        return ResponseEntity.ok(updatedOpeningPeriod);
    }

    /**
     * This method deletes a openingPeriod based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<OpeningPeriod> delete(@PathVariable(value = "id") Long id) {
        OpeningPeriod openingPeriod = openingPeriodService.findById(id);
        if (openingPeriod == null) {
            return ResponseEntity.notFound().build();
        }
        openingPeriodService.delete(id);

        return ResponseEntity.ok(openingPeriod);
    }
}
