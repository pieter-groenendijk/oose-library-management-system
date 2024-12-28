package com.github.pieter_groenendijk.utils.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class TimeUtils {
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.from(
            Instant.ofEpochMilli(
                date.getTime()
            )
        );
    }
}
