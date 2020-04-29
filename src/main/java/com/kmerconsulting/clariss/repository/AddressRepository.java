package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
