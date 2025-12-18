package com.example.demo.service;

import com.example.demo.model.AccessLog;
import com.example.demo.model.DigitalKey;
import com.example.demo.repository.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Override
    public AccessLog createLog(AccessLog log) {
        DigitalKey key = log.getDigitalKey();
        Timestamp now = Timestamp.from(Instant.now());

        // Check key validity: must be active and not expired
        if (key != null && key.getActive() && key.getExpiresAt().after(now)) {
            log.setResult("SUCCESS");
            log.setReason("Valid key presented");
        } else {
            log.setResult("DENIED");
            log.setReason(key == null ? "Key not found" : "Key expired or inactive");
        }
        
        return accessLogRepository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return accessLogRepository.findByDigitalKeyId(keyId);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return accessLogRepository.findByGuestId(guestId);
    }
}