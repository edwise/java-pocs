package com.edwise.pocs.orikapoc.config;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateTimeToLocalDateConverter
        extends CustomConverter<LocalDateTime, LocalDate> {
    public LocalDate convert(LocalDateTime source,
                             Type<? extends LocalDate> destinationType) {
        return source.toLocalDate();
    }
}
