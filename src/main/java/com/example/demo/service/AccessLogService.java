package com.example.demo.service;
import java.util.*;
public interface AccesLogService {
    AccessLog createLog(AccessLog log);
    List<AccessLog> getLogsForKey(Long keyId);
    List<AccessLog> getLogsForGuest(Long guestId);
}
