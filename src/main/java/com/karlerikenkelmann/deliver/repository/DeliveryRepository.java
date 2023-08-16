package com.karlerikenkelmann.deliver.repository;

import com.karlerikenkelmann.deliver.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    @Query (value = "SELECT d FROM Delivery d where d.status = 'TRANSIT'")
    List<Delivery> findAllActive();
}
