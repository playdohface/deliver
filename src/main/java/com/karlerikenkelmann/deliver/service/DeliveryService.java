package com.karlerikenkelmann.deliver.service;

import com.karlerikenkelmann.deliver.entity.Delivery;
import com.karlerikenkelmann.deliver.entity.TransitLocation;
import com.karlerikenkelmann.deliver.model.DeliveryRequest;
import com.karlerikenkelmann.deliver.model.DeliveryStatus;
import com.karlerikenkelmann.deliver.repository.DeliveryRepository;
import com.karlerikenkelmann.deliver.repository.TransitLocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryService {
    private DeliveryRepository deliveryRepository;
    private TransitLocationRepository transitLocationRepository;
    private AddressService addressService;
    private TransitLogService transitLogService;

    public List<Delivery> findAllActive() {
        return deliveryRepository.findAllActive();
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> findById(Integer id) {
        return this.deliveryRepository.findById(id);
    }

    public Delivery update(Delivery delivery) {
        return this.deliveryRepository.save(resolveAddresses(delivery));
    }

    public Delivery handle(Delivery delivery,
                                     DeliveryStatus newStatus,
                                     TransitLocation newLocation,
                                     String logMessage) {
        delivery.setTransitLocation(newLocation);
        delivery.setStatus(newStatus);
        String message = logMessage;
        if (logMessage.length() < 1 || logMessage.equals("auto")) {
            message = newStatus + " Location: " + newLocation.getDescription();
        }
        this.transitLogService.log(delivery,message);
        return this.deliveryRepository.save(delivery);

    }

    public Optional<Delivery> createFromReq(DeliveryRequest deliveryRequest) {
        var dropOff = this.transitLocationRepository.findById(deliveryRequest.getDropOffId());
        if (dropOff.isEmpty()) {
            return Optional.empty();
        }
        var drop = dropOff.get();
        var delivery = new Delivery(
                deliveryRequest.getSender(),
                deliveryRequest.getReceiver(),
                DeliveryStatus.PENDING,
                drop);
        var saved = deliveryRepository.save(resolveAddresses(delivery));
        transitLogService.deliveryCreation(saved);
        return Optional.of(saved);
    }

    public Delivery create(Delivery delivery) {
        delivery.setId(null);
        return this.deliveryRepository.save(resolveAddresses(delivery));
    }

    private Delivery resolveAddresses(Delivery delivery) {
        if (delivery.getSender().getId() == null) {
            delivery.setSender(this.addressService.createOrFind(delivery.getSender()));
        }
        if (delivery.getReceiver().getId() == null) {
            delivery.setReceiver(this.addressService.createOrFind(delivery.getReceiver()));
        }
        return delivery;
    }

}
