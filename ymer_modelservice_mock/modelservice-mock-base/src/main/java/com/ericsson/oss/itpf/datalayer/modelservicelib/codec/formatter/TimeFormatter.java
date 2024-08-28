package com.ericsson.oss.itpf.datalayer.modelservicelib.codec.formatter;

import java.math.BigInteger;

/**
 * Utility class for formatting date-times as binary data
 */
public abstract class TimeFormatter extends BinFormatter {

	public static byte[] doFormat(final String value) {
		return doFormat(value, IS_LITTLE_ENDIAN);
	}	
	
	/**
	 * Format time as 8 byte binary data (plus 1 byte for null).
	 * 
	 * @param value
	 *            Time as string in format "HH:mm:ss.ms". An empty string is
	 *            treated as null.
	 * @param isLittleEndian
	 *            true if time is to be stored in little endian format. i.e.
	 *            with the least significant byte first
	 * @return 8 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final String value, final boolean isLittleEndian) {

		final boolean isNull = (value == null || value.length() == 0);
		byte[] ret; 

		if (isNull) {
			ret =  createEmptyByteField(9, isNull);
		} else {
			ret = UnsignedbigintFormatter.doFormat(getBinaryValue(value),
					isLittleEndian);
		}

		return ret;
	}

	/**
	 * Convert string time to Big Integer value
	 * 
	 * @param value
	 *            Date to be converted
	 * @return date as a number
	 */
	protected static BigInteger getBinaryValue(final String value) {

		final int pos = value.indexOf('.');
		String millis = null;
		String[] parts;
		
		if (pos > -1) {
			millis = value.substring(pos + 1);
			parts = value.substring(0, pos).split(":");
		} else {
			parts = value.split(":");
		}
		
		// format is HH:mm:ss:ms, parse values
		long hours;
		long minutes;
		long seconds;
		long millisecs;

		try {
			hours = Long.parseLong(parts[0]);
			minutes = Long.parseLong(parts[1]);
			seconds = Long.parseLong(parts[2]);
			millisecs = millis == null || millis.isEmpty() ? 0 : Long.parseLong(millis);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Unknown time format: " + value + ": ",e);
		}

		final long msecs = millisecs + seconds * 1000 + minutes * 60000 + hours * 3600000;

		final BigInteger bsecs = BigInteger.valueOf(msecs);
		final BigInteger mses = BigInteger.valueOf(1000);

		return bsecs.multiply(mses);
	}

}
