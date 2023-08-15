package com.karlerikenkelmann.deliver.service;

import com.karlerikenkelmann.deliver.entity.Address;
import com.karlerikenkelmann.deliver.repository.AddressRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
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

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Integer id) {
        return addressRepository.findById(id);
    }

    public Optional<Address> findByExample(Address address) {
        address.setId(null);
        Example<Address> example = Example.of(address);
        return addressRepository.findOne(example);
    }
    public Address update(Address address) {
        if (address.getId() == null || address.getId() == 0) {
            throw new RuntimeException("Can not update Address without Id");
        }
        return addressRepository.save(address);
    }
    public Optional<Address> deleteById(Integer id) {
        var foundAddress = addressRepository.findById(id);
        foundAddress.ifPresent(a -> addressRepository.deleteById(id));
        return foundAddress;

    }
}
