package com.github.pieter_groenendijk.utils.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

// TODO: Remove when not needed anymore
public class TimeUtils {
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.from(
            Instant.ofEpochMilli(
                date.getTime()
            )
        );
    }
}
