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

        // 🔹 Load digital key
        DigitalKey key = digitalKeyRepository
                .findById(log.getDigitalKey().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Digital key not found")
                );

        // 🔹 Load guest
        Guest guest = guestRepository
                .findById(log.getGuest().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Guest not found")
                );

        // 🔐 ACCESS VALIDATION (CRITICAL FIX)
        boolean isOwner =
                key.getBooking().getGuest().getId().equals(guest.getId());

        boolean isRoommate =
                key.getBooking().getRoommates()
                        .stream()
                        .anyMatch(r -> r.getId().equals(guest.getId()));

        boolean isShared =
                keyShareRequestRepository
                        .existsByDigitalKeyIdAndSharedWithId(
                                key.getId(),
                                guest.getId()
                        );

        if (!isOwner && !isRoommate && !isShared) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Guest not authorized to use this key"
            );
        }

        // 🔹 Attach managed entities
        log.setDigitalKey(key);
        log.setGuest(guest);

        // 🔹 Server-side time only
        log.setAccessTime(Instant.now());

        // 🔹 Grant / deny
        if (key.getActive() && Instant.now().isBefore(key.getExpiresAt())) {
            log.setResult("SUCCESS");
        } else {
            log.setResult("DENIED");
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

    @Override
    public List<AccessLog> getAllLogs() {
        return accessLogRepository.findAll();
    }
}
