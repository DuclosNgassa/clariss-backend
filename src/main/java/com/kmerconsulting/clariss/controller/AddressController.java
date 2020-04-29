package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.Address;
import com.kmerconsulting.clariss.service.AddressService;
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
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping()
    public ResponseEntity<Address> create(@Valid @RequestBody Address address) throws Exception {
        Address createdAddress = addressService.save(address);
        if(createdAddress == null){
            throw new Exception("Error while saving address");
        }
        return ResponseEntity.ok(createdAddress);
    }

    /**
     * This method updates a address based on his id and his details
     *
     * @param address
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Address address) throws Exception {
        Address addressFound = addressService.findById(id);

        if (addressFound == null) {
            throw new Exception("Error while saving address");
        }
        addressFound.setCity(address.getCity());
        addressFound.setCountry(address.getCountry());
        addressFound.setHousnumber(address.getHousnumber());
        addressFound.setQuarter(address.getQuarter());
        addressFound.setStreet(address.getStreet());

        Address updatedAddress = addressService.update(addressFound);


        return ResponseEntity.ok(updatedAddress);
    }

    /**
     * get all addresses
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<Address>> findAll() {
        List<Address> addresses = addressService.findAll();
        if (addresses == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable(value = "id") Long id) {
        Address address = addressService.findById(id);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(address);
    }

    /**
     * This method deletes a address based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable(value = "id") Long id) throws Exception {
        Address address = addressService.findById(id);
        if (address == null) {
            throw new Exception("Error while saving address");
        }

        addressService.delete(id);

        return ResponseEntity.ok(address);
    }

}
