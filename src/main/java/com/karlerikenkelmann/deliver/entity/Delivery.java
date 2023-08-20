package com.karlerikenkelmann.deliver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.karlerikenkelmann.deliver.model.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Delivery {

    public Delivery(@NotNull  Address sender,
                    @NotNull Address receiver,
                    @NotNull DeliveryStatus status,
                    @NotNull TransitLocation transitLocation ) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.transitLocation = transitLocation;
        this.createdAt = LocalDateTime.now();
    }

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

    @JsonIgnoreProperties(value="delivery")
    @OneToMany(mappedBy = "delivery")
    private List<TransitLog> logs;

    @ManyToOne
    private TransitLocation transitLocation;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DeliveryStatus status;

}
