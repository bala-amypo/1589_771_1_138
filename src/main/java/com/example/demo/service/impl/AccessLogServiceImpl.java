package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.model.AccessLog;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Autowired
    private DigitalKeyRepository digitalKeyRepository;

    @Override
    public AccessLog createLog(AccessLog log) {
        DigitalKey key = digitalKeyRepository.findById(log.getKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Digital Key not found"));

        boolean isValid = true;
                if (!key.isActive()) {
            isValid = false;
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(key.getBooking().getCheckInDate()) || 
            now.isAfter(key.getBooking().getCheckOutDate())) {
            isValid = false;
        }
        if (isValid) {
            log.setResult("SUCCESS");
        } else {
            log.setResult("DENIED");
        }

        log.setTimestamp(now); 
        return accessLogRepository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return accessLogRepository.findByKeyId(keyId);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return accessLogRepository.findByKey_Booking_GuestId(guestId);
    }
}

