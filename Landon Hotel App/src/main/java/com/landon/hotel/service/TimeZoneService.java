package com.landon.hotel.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TimeZoneService {
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    public Map<String, String> getPresentationTimes() {
        // Set the presentation time in Eastern Time
        LocalDateTime presentationTime = LocalDateTime.now()
            .withHour(14)  // 2 PM
            .withMinute(0)
            .withSecond(0)
            .withNano(0);
        
        // Convert to different time zones
        ZonedDateTime etTime = presentationTime.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime mtTime = etTime.withZoneSameInstant(ZoneId.of("America/Denver"));
        ZonedDateTime utcTime = etTime.withZoneSameInstant(ZoneId.of("UTC"));
        
        // Format times
        Map<String, String> times = new HashMap<>();
        times.put("ET", etTime.format(TIME_FORMATTER) + " ET");
        times.put("MT", mtTime.format(TIME_FORMATTER) + " MT");
        times.put("UTC", utcTime.format(TIME_FORMATTER) + " UTC");
        
        return times;
    }
} 