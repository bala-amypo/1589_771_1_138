package com.example.demo.service;

import com.example.demo.model.AccessLog;
import java.util.List;

public interface AccessLogService {

    /**
     * Check key validity and set result SUCCESS/DENIED before saving.
     */
    AccessLog createLog(AccessLog log); //

    /**
     * Retrieve all logs associated with a specific Digital Key.
     */
    List<AccessLog> getLogsForKey(Long keyId); //

    /**
     * Retrieve all logs associated with a specific Guest.
     */
    List<AccessLog> getLogsForGuest(Long guestId); //
}