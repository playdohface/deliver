package com.karlerikenkelmann.deliver.controller;

import com.karlerikenkelmann.deliver.entity.Delivery;
import com.karlerikenkelmann.deliver.model.DeliveryRequest;
import com.karlerikenkelmann.deliver.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/deliver")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class DeliveryController {

    private DeliveryService deliveryService;

    @GetMapping
    ResponseEntity<List<Delivery>> getAll() {
        return ResponseEntity.ok(deliveryService.findAll());
    }

    @PostMapping
    ResponseEntity<Delivery> create(@Valid @RequestBody DeliveryRequest deliveryRequest) {
        var res = this.deliveryService.createFromReq(deliveryRequest);
        if(res.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.of(res);
    }
}
