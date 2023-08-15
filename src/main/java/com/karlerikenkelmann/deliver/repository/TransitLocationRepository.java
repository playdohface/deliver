package com.karlerikenkelmann.deliver.repository;

import com.karlerikenkelmann.deliver.entity.TransitLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitLocationRepository extends JpaRepository<TransitLocation, Integer> {

}
