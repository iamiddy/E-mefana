package com.idrene.emefana.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author iddymagohe
 *
 */
public class DateConvertUtil {
	public static LocalDate asLocalDate(java.util.Date date) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date)
            return ((java.sql.Date) date).toLocalDate();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(java.util.Date date) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Timestamp)
            return ((java.sql.Timestamp) date).toLocalDateTime();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static java.util.Date asUtilDate(Object date) {
        if (date == null)
            return null;

        if (date instanceof java.util.Date)
            return (java.util.Date) date;
        if (date instanceof java.sql.Date || date instanceof java.sql.Timestamp)
            return new java.util.Date(((java.util.Date) date).getTime());
        if (date instanceof LocalDate)
            return java.util.Date.from(((LocalDate) date).atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (date instanceof LocalDateTime)
            return java.util.Date.from(((LocalDateTime) date).atZone(ZoneId.systemDefault()).toInstant());
        if (date instanceof ZonedDateTime)
            return java.util.Date.from(((ZonedDateTime) date).toInstant());
        if (date instanceof Instant)
            return java.util.Date.from((Instant) date);

        throw new UnsupportedOperationException("Don't know to convert " + date.getClass().getName() + " into java.util.Date");
    }
    
	public static java.util.Date asUtilDate(LocalDate date) {
		if (date == null)
			return null;

		return java.util.Date.from(((LocalDate) date).atStartOfDay(
				ZoneId.systemDefault()).toInstant());

	}
}
