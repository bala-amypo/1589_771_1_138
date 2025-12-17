package com.example.demo.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.AccessLog;
import com.example.demo.service.AccessLogService;
import java.util.List;

@RestController
@RequestMapping("/api/access-logs")
@Tag(name = "Access Log Controller", description = "Endpoints for tracking room access history")
public class AccessLogController {

    @Autowired
    private AccessLogService logService;
    @PostMapping
    public AccessLog createLog(@RequestBody AccessLog log) {
        return logService.saveLog(log);
    }
    @GetMapping("/key/{keyId}")
    public List<AccessLog> getLogsByKey(@PathVariable Long keyId) {
        return logService.getLogsByExternalKeyId(keyId);
    }
    @GetMapping("/guest/{guestId}")
    public List<AccessLog> getLogsByGuest(@PathVariable Long guestId) {
        return logService.getLogsByGuestId(guestId);
    }
}