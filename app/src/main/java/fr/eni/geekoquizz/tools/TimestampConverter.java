package fr.eni.geekoquizz.tools;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class TimestampConverter {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static long fromDate(Date date) {
        return date.getTime();
    }
}
