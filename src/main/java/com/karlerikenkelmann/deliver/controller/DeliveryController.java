package com.karlerikenkelmann.deliver.controller;

import com.karlerikenkelmann.deliver.entity.Delivery;
import com.karlerikenkelmann.deliver.model.DeliveryRequest;
import com.karlerikenkelmann.deliver.model.DeliveryStatus;
import com.karlerikenkelmann.deliver.repository.TransitLocationRepository;
import com.karlerikenkelmann.deliver.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/deliver")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class DeliveryController {

    private DeliveryService deliveryService;
    private TransitLocationRepository transitLocationRepository;

    @GetMapping
    ResponseEntity<List<Delivery>> getAll() {
        return ResponseEntity.ok(deliveryService.findAll());
    }

    @PatchMapping("/admin/{id}")
    ResponseEntity<Delivery> handle(@PathVariable Integer id,
                                    @RequestParam DeliveryStatus status,
                                    @RequestParam Integer locationId,
                                    @RequestParam String message) {
        var delivery = this.deliveryService.findById(id);
        var transitLocation = this.transitLocationRepository.findById(locationId);
        if (delivery.isEmpty() || transitLocation.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.deliveryService.handle(delivery.get(), status, transitLocation.get(), message));
    }

    @GetMapping("/{id}")
    ResponseEntity<Delivery> findById(@PathVariable Integer id, @RequestParam String lastName) {
        var found = deliveryService.findById(id);
        if (found.isPresent() &&
                found.get().getReceiver().getLastName().equalsIgnoreCase(lastName.trim())) {
            return ResponseEntity.ok(found.get());
        } else if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/admin/{id}")
    ResponseEntity<Delivery> findById(@PathVariable Integer id) {
        return ResponseEntity.of(deliveryService.findById(id));
    }

    @PostMapping
    ResponseEntity<Delivery> create(@Valid @RequestBody DeliveryRequest deliveryRequest) {
        var res = this.deliveryService.createFromReq(deliveryRequest);
        if(res.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.of(res);
    }

    @PutMapping
    ResponseEntity<Delivery> update(@Valid @RequestBody Delivery delivery) {
        if(delivery.getId() == null || delivery.getId() == 0) {
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(deliveryService.update(delivery));
    }
}
