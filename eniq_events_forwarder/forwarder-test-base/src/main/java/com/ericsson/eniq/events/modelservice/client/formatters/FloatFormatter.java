package com.ericsson.eniq.events.modelservice.client.formatters;

/**
 * Utility class for formatting floating point numbers as 4 byte binary data
 */
public abstract class FloatFormatter extends BinFormatter {

	/**
	 * Format float as 4 byte binary data (plus 1 byte for null). If the value
	 * equals Float.MIN_VALUE it will be treated as null. The field will be
	 * formatted according to the native endian order.
	 * 
	 * @param value
	 *            value to be formatted
	 * @return 4 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final float value) {

		final boolean isNull = (value == Float.MIN_VALUE);
		return doFormat(value,IS_LITTLE_ENDIAN, isNull);
	}

	/**
	 * Format float as 4 byte binary data (plus 1 byte for null)
	 * 
	 * @param value
	 *            value to be formatted
	 * @param isLittleEndian
	 *            true if number is to be stored in little endian format. i.e.
	 *            with the least significant byte first
	 * @param isNull
	 *            If true, the returned value will represent null with data
	 *            bytes set to 0 and final null byte set to 1
	 * @return 4 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final float value,
			final boolean isLittleEndian, final boolean isNull) {

		final int rawInt = Float.floatToIntBits(value);
		return IntFormatter.doFormat(rawInt, isLittleEndian, isNull);
	}
}
