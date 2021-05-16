package com.campool.configuration.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ISO_DATE.format(object);
    }
}
