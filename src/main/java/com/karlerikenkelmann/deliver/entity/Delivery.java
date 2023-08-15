package com.karlerikenkelmann.deliver.entity;

import com.karlerikenkelmann.deliver.model.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Delivery {
    @Id
    @TableGenerator(name = "tracking_code_gen", initialValue = 1000000)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tracking_code_gen")
    private Integer Id;

    @ManyToOne
    @NotNull
    private Address sender;

    @ManyToOne
    @NotNull
    private Address receiver;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime lastLocationChange;

    @ManyToOne
    private TransitLocation transitLocation;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DeliveryStatus status;

}
