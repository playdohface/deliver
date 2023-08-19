package com.karlerikenkelmann.deliver.repository;

import com.karlerikenkelmann.deliver.entity.TransitLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransitLocationRepository extends JpaRepository<TransitLocation, Integer> {

    @Query("select t from TransitLocation t where t.isDropOff=true")
    List<TransitLocation> findDropOffLocations();

}
