package com.karlerikenkelmann.deliver.service;

import com.karlerikenkelmann.deliver.entity.Address;
import com.karlerikenkelmann.deliver.model.Country;
import com.karlerikenkelmann.deliver.repository.AddressRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class AddressServiceTest {

    Address testAddr1;
    Address testAddr2;
    @Autowired
    private AddressRepository addressRepository;
    private AddressService addressService;

    @BeforeEach
    void init() {
        this.addressService = new AddressService(this.addressRepository);
        this.testAddr1 = new Address();
        this.testAddr1.setFirstName("Test");
        this.testAddr1.setLastName("Test");
        this.testAddr1.setStreet("Foo");
        this.testAddr1.setCity("New York");
        this.testAddr1.setPostalCode("1111");
        this.testAddr1.setCountry(Country.ARGENTINA);

        this.testAddr2 = new Address();
        this.testAddr2.setFirstName("Test");
        this.testAddr2.setLastName("Test");
        this.testAddr2.setStreet("Foo");
        this.testAddr2.setCity("New York");
        this.testAddr2.setPostalCode("1111");
        this.testAddr2.setCountry(Country.ARGENTINA);
    }

    @Test
    void injectedReposAreNotNull() {
        assertNotNull(this.addressRepository);
        assertNotNull(this.addressService);
    }

    @Test
    void createOrFindDoesNotCreateDuplicates() {
        //testAddr2 is a shallow copy of testAddr1
        Address createdAddr1 = addressService.createOrFind(this.testAddr1);
        Address createdAddr2 = addressService.createOrFind(this.testAddr2);
        assertEquals(createdAddr1.getId(), createdAddr2.getId());
    }

    @Test
    void findByExampleWorks() {
        Address createdAddr1 = addressService.createOrFind(this.testAddr1);
        Optional<Address> foundAddr = addressService.findByExample(this.testAddr2);
        assertEquals(createdAddr1.getId(), foundAddr.get().getId());

    }
}