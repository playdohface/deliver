package com.karlerikenkelmann.deliver.model;

import com.karlerikenkelmann.deliver.entity.Address;
import com.karlerikenkelmann.deliver.entity.TransitLocation;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeliveryRequest {

    @NotNull
    private Address sender;

    @NotNull
    private Address receiver;

    private Integer dropOffId;
}
