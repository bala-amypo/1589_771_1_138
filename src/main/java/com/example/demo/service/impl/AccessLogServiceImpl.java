package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AccessLog;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.AccessLogService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;
    private final KeyShareRequestRepository keyShareRequestRepository;

    public AccessLogServiceImpl(
            AccessLogRepository accessLogRepository,
            DigitalKeyRepository digitalKeyRepository,
            GuestRepository guestRepository,
            KeyShareRequestRepository keyShareRequestRepository
    ) {
        this.accessLogRepository = accessLogRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
        this.keyShareRequestRepository = keyShareRequestRepository;
    }

    @Override
    public AccessLog createLog(AccessLog log) {

        // ✅ Allow 5-second clock drift (IMPORTANT FIX)
        if (log.getAccessTime() != null &&
            log.getAccessTime().isAfter(Instant.now().plusSeconds(5))) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Access time cannot be in the future"
            );
        }

        // ✅ Load Digital Key safely
        DigitalKey key = digitalKeyRepository.findById(
                log.getDigitalKey().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Digital key not found")
        );

        // ✅ Load Guest safely
        Guest guest = guestRepository.findById(
                log.getGuest().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Guest not found")
        );

        // ✅ Attach managed entities
        log.setDigitalKey(key);
        log.setGuest(guest);

        // ✅ Server-controlled access time (BEST PRACTICE)
        log.setAccessTime(Instant.now());

        // ✅ Access decision
        if (key.getActive() && Instant.now().isBefore(key.getExpiresAt())) {
            log.setResult("SUCCESS");
        } else {
            log.setResult("DENIED");
        }

        return accessLogRepository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return accessLogRepository.findByGuestId(guestId);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return accessLogRepository.findByDigitalKeyId(keyId);
    }

    @Override
    public List<AccessLog> getAllLogs() {
        return accessLogRepository.findAll();
    }
}
