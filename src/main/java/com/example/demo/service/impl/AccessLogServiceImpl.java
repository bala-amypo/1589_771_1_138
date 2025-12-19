
package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.AccessLogService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository repository;
    private final DigitalKeyRepository keyRepo;
    private final GuestRepository guestRepo;

    public AccessLogServiceImpl(AccessLogRepository repository, 
                                DigitalKeyRepository keyRepo, 
                                GuestRepository guestRepo) {
        this.repository = repository;
        this.keyRepo = keyRepo;
        this.guestRepo = guestRepo;
    }

    @Override
    public AccessLog createLog(AccessLog log) {
        DigitalKey key = keyRepo.findById(log.getDigitalKey().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
        Guest guest = guestRepo.findById(log.getGuest().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        log.setDigitalKey(key);
        log.setGuest(guest);
        return repository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return repository.findByDigitalKeyId(keyId);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return repository.findByGuestId(guestId);
    }
}