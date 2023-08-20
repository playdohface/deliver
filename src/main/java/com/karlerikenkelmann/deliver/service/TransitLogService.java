package com.karlerikenkelmann.deliver.service;

import com.karlerikenkelmann.deliver.entity.Delivery;
import com.karlerikenkelmann.deliver.entity.TransitLog;
import com.karlerikenkelmann.deliver.repository.TransitLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransitLogService {
    private TransitLogRepository transitLogRepository;

    void deliveryCreation(Delivery delivery) {
        var tl = new TransitLog(delivery, "Request received");
        transitLogRepository.save(tl);
    }

    void log(Delivery delivery, String message) {
        transitLogRepository.save(new TransitLog(delivery, message));
    }
}
