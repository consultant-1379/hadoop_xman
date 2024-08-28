/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.modelservice.client;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ericsson.eniq.events.modelservice.client.formatters.*;

public class IQHelper {

    private static final int ONE_MINUTE = 60 * 1000;

    private static final String TIME_ZONE = "UTC";

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    private static final String DATE_ONLY_FORMAT = "yyyy-MM-dd";

    // This date formatter is required to format dates into UTC
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);

    private static final DateFormat dateOnlyFormatter = new SimpleDateFormat(DATE_ONLY_FORMAT);

    private static final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE));

    // Set the time zone for the formatter as UTC
    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        dateOnlyFormatter.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
    }

    // Methods for writing pojo fields into output stream

    public static byte[] writeBit(final boolean value) throws IOException {
        final byte[] res = BitFormatter.doFormat(value ? (byte) 1 : (byte) 0);
        return res;
    }

    public static byte[] writeBit() throws IOException {
        return writeBit(DefaultValues.DEFAULT_BOOLEAN_VALUE);
    }

    public static byte[] writeUnsignedbigint(final long value, final boolean isLittleEndian) throws IOException {
        final boolean isNull = value < 0;
        final byte[] res = BigintFormatter.doFormat(value, isLittleEndian, isNull);
        return res;
    }

    public static byte[] writeUnsignedbigint(final boolean isLittleEndian) throws IOException {
        return writeUnsignedbigint(DefaultValues.DEFAULT_LONG_VALUE, isLittleEndian);
    }

    public static byte[] writeInt(final int value, final boolean isLittleEndian) throws IOException {
        final boolean isNull = value == Integer.MIN_VALUE;
        final byte[] res = IntFormatter.doFormat(value, isLittleEndian, isNull);
        return res;
    }

    public static byte[] writeInt(final boolean isLittleEndian) throws IOException {
        return writeInt(DefaultValues.DEFAULT_INT_VALUE, isLittleEndian);
    }

    public static byte[] writeUnsignedint(final int value, final boolean isLittleEndian) throws IOException {
        final boolean isNull = value < 0;
        final byte[] res = UnsignedintFormatter.doFormat(value, isLittleEndian, isNull);
        return res;
    }

    public static byte[] writeUnsignedint(final boolean isLittleEndian) throws IOException {
        return writeUnsignedint(DefaultValues.DEFAULT_INT_VALUE, isLittleEndian);
    }

    public static byte[] writeTinyint(final byte value) throws IOException {
        final boolean isNull = value < 0;
        final byte[] res = TinyintFormatter.doFormat(value, isNull);
        return res;
    }

    public static byte[] writeTinyint() throws IOException {
        return writeTinyint(DefaultValues.DEFAULT_BYTE_VALUE);
    }

    public static byte[] writeSmallint(final short value, final boolean isLittleEndian) throws IOException {
        final boolean isNull = value < 0;
        final byte[] res = SmallintFormatter.doFormat(value, isLittleEndian, isNull);
        return res;
    }

    public static byte[] writeSmallint(final boolean isLittleEndian) throws IOException {
        return writeSmallint(DefaultValues.DEFAULT_SHORT_VALUE, isLittleEndian);
    }

    public static byte[] writeVarchar(final String value, final int size) throws IOException {
        final byte[] res = VarcharFormatter.doFormat(value, size);
        return res;
    }

    public static byte[] writeVarchar(final int size) throws IOException {
        return writeVarchar(DefaultValues.DEFAULT_STRING_VALUE, size);
    }

    public static byte[] writeTimestamp(final long timestamp, final boolean isLittleEndian) throws IOException {
        return writeTimestamp(timestamp, false, isLittleEndian);
    }

    public static byte[] writeTimestamp(final long timestamp, final boolean truncateMillis, final boolean isLittleEndian) throws IOException {
        try {
            String value = null;
            if (timestamp > 0) {
                if (truncateMillis) {
                    final long truncatedTimestamp = (timestamp / ONE_MINUTE) * ONE_MINUTE;
                    synchronized (IQHelper.class) {
                        value = dateFormat.format(truncatedTimestamp);
                    }
                } else {
                    synchronized (IQHelper.class) {
                        value = dateFormat.format(timestamp);
                    }
                }
            }
            final byte[] res = DatetimeFormatter.doFormat(value, isLittleEndian);
            return res;
        } catch (final Exception e) {
            return DatetimeFormatter.doFormat(null, isLittleEndian);
        }
    }

    public static byte[] writeDate(final String value, final boolean isLittleEndian) throws IOException {
        final byte[] res = DateFormatter.doFormat(value, isLittleEndian);
        return res;
    }

    public static byte[] writeFloat(final float value, final boolean isLittleEndian) throws IOException {
        final boolean isNull = value == DefaultValues.DEFAULT_FLOAT_VALUE;
        final byte[] res = FloatFormatter.doFormat(value, isLittleEndian, isNull);
        return res;
    }

    public static byte[] writeFloat(final boolean isLittleEndian) throws IOException {
        return writeFloat(DefaultValues.DEFAULT_FLOAT_VALUE, isLittleEndian);
    }

    public static byte[] writeBinary(final byte[] value, final int size) throws IOException {
        final byte[] data = BinaryFormatter.doFormat(value, size);
        return data;
    }

    public static byte[] writeBinary(final int size) throws IOException {
        return writeBinary(DefaultValues.DEFAULT_BYTE_ARRAY_VALUE, size);
    }

    // Functions to manipulate input data
    public static String getDate(final long timestamp) {
        final Date date = new Date(timestamp);
        return dateOnlyFormatter.format(date);
    }

    public static short getYear(final long timestamp) {
        final Date date = new Date(timestamp);
        calendar.setTime(date);
        return (short) calendar.get(Calendar.YEAR);
    }

    public static byte getMonth(final long timestamp) {
        final Date date = new Date(timestamp);
        calendar.setTime(date);
        return (byte) (calendar.get(Calendar.MONTH) + 1);
    }

    public static byte getDay(final long timestamp) {
        final Date date = new Date(timestamp);
        calendar.setTime(date);
        return (byte) calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static byte getHour(final long timestamp) {
        final Date date = new Date(timestamp);
        calendar.setTime(date);
        return (byte) calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static byte getMinute(final long timestamp) {
        final Date date = new Date(timestamp);
        calendar.setTime(date);
        return (byte) calendar.get(Calendar.MINUTE);
    }

    public static boolean getBooleanFromByte(final byte byteValue) {
        return (byteValue % 2) == 1;
    }

    public static String getStringFromInt(final int intValue) {
        return new Integer(intValue).toString();
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(final byte[] bytes) {
        final char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
