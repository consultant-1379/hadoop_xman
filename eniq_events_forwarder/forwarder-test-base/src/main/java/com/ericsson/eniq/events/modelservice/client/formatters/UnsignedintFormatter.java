package com.ericsson.eniq.events.modelservice.client.formatters;

/**
 * Utility class for formatting long as 4 byte binary data
 */
public abstract class UnsignedintFormatter extends BinFormatter {

	/**
	 * Format long as 4 byte binary data (plus 1 byte for null). Values less
	 * than zero will be treated as null. The field will be formatted according
	 * to the native endian order.
	 * 
	 * @param value
	 *            value to be formatted
	 * @return 4 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final long value) {
		
		final boolean isNull = (value < 0);
		return doFormat(value, IS_LITTLE_ENDIAN, isNull);
	}

	/**
	 * Format long as 4 byte binary data (plus 1 byte for null)
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
	public static byte[] doFormat(final long value,
			final boolean isLittleEndian, final boolean isNull) {

		final byte[] ret = createEmptyByteField(5, isNull);

		if (!isNull) {
			if (isLittleEndian) {
				ret[0] = (byte) (value & 0xFF);
				ret[1] = (byte) ((value >> 8) & 0xFF);
				ret[2] = (byte) ((value >> 16) & 0xFF);
				ret[3] = (byte) ((value >> 24) & 0xFF);
			} else {
				ret[0] = (byte) ((value >> 24) & 0xFF);
				ret[1] = (byte) ((value >> 16) & 0xFF);
				ret[2] = (byte) ((value >> 8) & 0xFF);
				ret[3] = (byte) (value & 0xFF);
			}

			ret[4] = (byte) 0; // null byte
		}

		return ret;
	}

}
