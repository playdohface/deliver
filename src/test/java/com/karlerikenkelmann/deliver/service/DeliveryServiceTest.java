package com.karlerikenkelmann.deliver.service;

import com.karlerikenkelmann.deliver.entity.Address;
import com.karlerikenkelmann.deliver.entity.Delivery;
import com.karlerikenkelmann.deliver.entity.TransitLocation;
import com.karlerikenkelmann.deliver.model.Country;
import com.karlerikenkelmann.deliver.model.DeliveryStatus;
import com.karlerikenkelmann.deliver.repository.AddressRepository;
import com.karlerikenkelmann.deliver.repository.DeliveryRepository;
import com.karlerikenkelmann.deliver.repository.TransitLocationRepository;
import com.karlerikenkelmann.deliver.repository.TransitLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DeliveryServiceTest {

    @Autowired private DeliveryRepository deliveryRepository;
    @Autowired private AddressRepository addressRepository;
    @Autowired private TransitLocationRepository transitLocationRepository;
    @Autowired private TransitLogRepository transitLogRepository;

    private DeliveryService deliveryService;
    private AddressService addressService;

    private TransitLogService transitLogService;
    private Address testAddr1;
    private Address testAddr2;

    private TransitLocation testLoc;
    @BeforeEach
    void init() {
        this.addressService = new AddressService(addressRepository);
        this.transitLogService = new TransitLogService(transitLogRepository);
        this.deliveryService = new DeliveryService(this.deliveryRepository, transitLocationRepository,  addressService, transitLogService);
        this.testAddr1 = new Address();
        this.testAddr1.setFirstName("Test");
        this.testAddr1.setLastName("Test");
        this.testAddr1.setStreet("Foo");
        this.testAddr1.setCity("New York");
        this.testAddr1.setPostalCode("1111");
        this.testAddr1.setCountry(Country.ARGENTINA);

        this.testAddr2 = new Address();
        this.testAddr2.setFirstName("Test2");
        this.testAddr2.setLastName("Test2");
        this.testAddr2.setStreet("Foo2");
        this.testAddr2.setCity("New York");
        this.testAddr2.setPostalCode("1111");
        this.testAddr2.setCountry(Country.ARGENTINA);

        this.testLoc = new TransitLocation();
        this.testLoc.setDescription("Test");
    }
    @Test
    void testSetupWorks() {
        assertNotNull(this.deliveryRepository);
        assertNotNull(this.deliveryService);
        assertNotNull(this.addressRepository);
        assertNotNull(this.transitLocationRepository);
        assertNotNull(this.addressService);
    }

    @Test
    void canCreateWithUnsavedAddresses() {
        var newDelivery = new Delivery();
        var transitLoc = this.transitLocationRepository.save(this.testLoc);
        newDelivery.setStatus(DeliveryStatus.DELIVERED);
        newDelivery.setSender(this.testAddr1);
        newDelivery.setReceiver(this.testAddr2);
        newDelivery.setCreatedAt(LocalDateTime.now());
        newDelivery.setTransitLocation(transitLoc);
        this.deliveryService.create(newDelivery);
        assertEquals(1,deliveryRepository.findAll().size());
        assertEquals(2, addressRepository.findAll().size());

    }
    @Test
    void findAllActiveFindsOnlyInTransit() {
        var addr1 = addressRepository.save(this.testAddr1);
        var addr2 = addressRepository.save(this.testAddr2);
        var transitLoc = transitLocationRepository.save(this.testLoc);

        var activeDelivery = new Delivery();
        activeDelivery.setStatus(DeliveryStatus.DELIVERED);
        activeDelivery.setSender(addr1);
        activeDelivery.setReceiver(addr2);
        activeDelivery.setCreatedAt(LocalDateTime.now());
        activeDelivery.setTransitLocation(transitLoc);

        var saved = deliveryRepository.save(activeDelivery);

        assertEquals(1,deliveryRepository.findAll().size());
        assertEquals(0, this.deliveryService.findAllActive().size());

        saved.setStatus(DeliveryStatus.TRANSIT);
        deliveryRepository.save(saved);

        assertEquals(1,deliveryRepository.findAll().size());
        assertEquals(1, this.deliveryService.findAllActive().size());

    }

}