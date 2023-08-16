package com.karlerikenkelmann.deliver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class TransitLog {
    public TransitLog(@NotNull Delivery delivery, String message) {
        this.delivery = delivery;
        this.message = message;
        this.time = LocalDateTime.now();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @NotNull
    private Delivery delivery;

    @NotNull
    private LocalDateTime time;

    private String message;
}
