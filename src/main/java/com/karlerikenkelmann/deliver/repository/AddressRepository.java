package com.karlerikenkelmann.deliver.repository;

import com.karlerikenkelmann.deliver.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
