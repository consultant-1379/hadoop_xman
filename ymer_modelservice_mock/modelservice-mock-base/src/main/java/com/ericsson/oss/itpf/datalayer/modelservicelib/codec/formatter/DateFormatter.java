package com.ericsson.oss.itpf.datalayer.modelservicelib.codec.formatter;

import java.util.Calendar;

/**
 * Utility class for formatting dates as binary data
 */
public abstract class DateFormatter extends BinFormatter {

	/**
	 * Format date as 4 byte binary data (plus 1 byte for null). The field will
	 * be formatted according to the native endian order.
	 * 
	 * @param value
	 *            Date as string in format "YYYY-MM-DD". An empty string is
	 *            treated as null.
	 * @return 4 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final String value) {
		
		return doFormat(value, IS_LITTLE_ENDIAN);
	}

	/**
	 * Format date as 4 byte binary data (plus 1 byte for null)
	 * 
	 * @param value
	 *            Date as string in format "YYYY-MM-DD". An empty string is
	 *            treated as null.
	 * @param isLittleEndian
	 *            true if date is to be stored in little endian format. i.e.
	 *            with the least significant byte first
	 * @return 4 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final String value,
			final boolean isLittleEndian) {

		final boolean isNull = (value == null || value.length() == 0);
		byte[] ret = createEmptyByteField(5, isNull);

		if (!isNull) {
			ret = UnsignedintFormatter.doFormat(getBinaryValue(value),
					isLittleEndian, false);
		}

		return ret;
	}

	/**
	 * Convert string date to integer value
	 * 
	 * @param value
	 *            Date to be converted
	 * @return date as a number
	 */
	protected static long getBinaryValue(final String value) {

		final Calendar cal = Calendar.getInstance();

		String[] parts;

		// remove time info if present
		final int pos = value.indexOf(' ');
		if (pos > -1) {
			parts = value.substring(0, pos).split("-");
		} else {
			parts = value.split("-");
		}

		int year = Integer.parseInt(parts[0]);
		final int month = Integer.parseInt(parts[1]);
		final int day = Integer.parseInt(parts[2]);

		cal.set(year, month - 1, day);
		final int jDay = cal.get(Calendar.DAY_OF_YEAR);

		// Do the trick
		year--;
		return (year * 365) + (year / 4) - (year / 100) + (year / 400) + 366
				+ jDay - 1;
	}

}
