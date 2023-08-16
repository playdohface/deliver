package com.karlerikenkelmann.deliver.service;

import com.karlerikenkelmann.deliver.entity.Delivery;
import com.karlerikenkelmann.deliver.entity.TransitLocation;
import com.karlerikenkelmann.deliver.entity.TransitLog;
import com.karlerikenkelmann.deliver.model.DeliveryStatus;
import com.karlerikenkelmann.deliver.repository.DeliveryRepository;
import com.karlerikenkelmann.deliver.repository.TransitLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryService {
    private DeliveryRepository deliveryRepository;
    private TransitLogRepository transitLogRepository;
    private AddressService addressService;

    public List<Delivery> findAllActive() {
        return deliveryRepository.findAllActive();
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> findById(Integer id) {
        return this.deliveryRepository.findById(id);
    }

    public Optional<Delivery> checkIn(Integer deliveryId, TransitLocation location, String message) {
        var found = deliveryRepository.findById(deliveryId);
        if (found.isEmpty()){
            return found;
        }
        var delivery = found.get();
        delivery.setTransitLocation(location);
        transitLogRepository.save(new TransitLog(delivery, message));
        return Optional.of(deliveryRepository.save(delivery));

    }

    public Delivery update(Delivery delivery) {
        return this.deliveryRepository.save(resolveAddresses(delivery));
    }
    public void deliver(Integer id) {
        this.deliveryRepository.findById(id)
        .ifPresent(delivery -> {
            delivery.setStatus(DeliveryStatus.DELIVERED);
            deliveryRepository.save(delivery);
        });
    }

    public Delivery create(Delivery delivery) {
        delivery.setId(null);
        return this.deliveryRepository.save(resolveAddresses(delivery));
    }

    private Delivery resolveAddresses(Delivery delivery) {
        if(delivery.getSender().getId() == null) {
            delivery.setSender(this.addressService.createOrFind(delivery.getSender()));
        }
        if(delivery.getReceiver().getId() == null) {
            delivery.setSender(this.addressService.createOrFind(delivery.getReceiver()));
        }
        return delivery;
    }

}
