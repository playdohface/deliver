package com.karlerikenkelmann.deliver.entity;

import com.karlerikenkelmann.deliver.model.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Locale;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String street;

    private String addressExtras;

    @NotNull
    private String postalCode;
    @NotNull
    private String city;
    @NotNull
    private Country country;
}
