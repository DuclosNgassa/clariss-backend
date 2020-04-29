package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.Address;
import com.kmerconsulting.clariss.repository.AddressRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address update(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Address with id: " + id + "not found"));
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

}
