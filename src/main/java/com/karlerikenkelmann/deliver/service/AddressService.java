package com.karlerikenkelmann.deliver.service;

import com.karlerikenkelmann.deliver.entity.Address;
import com.karlerikenkelmann.deliver.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {
    private AddressRepository addressRepository;

    public Address createOrFind(Address address) {
        address.setId(null);
        var duplicate = this.findByExample(address);
        return duplicate.orElseGet(() -> addressRepository.save(address));
    }

    public Optional<Address> findByExample(Address address) {
        address.setId(null);
        Example<Address> example = Example.of(address);
        return addressRepository.findOne(example);
    }

}
