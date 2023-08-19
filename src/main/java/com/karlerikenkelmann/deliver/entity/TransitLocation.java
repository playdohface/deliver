package com.karlerikenkelmann.deliver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class TransitLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull
    private String description;

    private boolean isDropOff;

    @ManyToOne
    private Address address;
}
