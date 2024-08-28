package com.ericsson.eniq.events.modelservice.client.formatters;

/**
 * Utility class for formatting byte arrays as binary data
 */
public abstract class BinaryFormatter extends BinFormatter {

	/**
	 * Format byte array as binary field of given length. If the value is null
	 * or not of the expected length, the returned value will represent null
	 * with data bytes set to 0 and final null byte set to 1
	 * 
	 * @param value
	 *            value to be formatted
	 * @param fieldSize
	 *            size of binary field to create
	 * @return binary field of the given size (plus null byte)
	 */
	public static byte[] doFormat(final byte[] value, final int fieldSize) {

		final boolean isNull = (value == null || value.length != fieldSize);
		final byte[] result = createEmptyByteField(fieldSize+1, isNull);

		if (!isNull) {
			System.arraycopy(value, 0, result, 0, fieldSize);
			result[result.length - 1] = 0;
		}

		return result;
	}

	/**
	 * Format string as binary field of given length. If the value is null or
	 * not of the expected length, the returned value will represent null with
	 * data bytes set to 0 and final null byte set to 1
	 * 
	 * @param value
	 *            value to be formatted
	 * @param fieldSize
	 *            size of binary field to create
	 * @return binary field of the given size (plus null byte)
	 */
	public static byte[] doFormat(final String value, final int fieldSize) {

		byte[] ret;
		
		if (value == null) {
			ret = doFormat((byte[])null, fieldSize);
		} else {
			ret = doFormat(value.getBytes(), fieldSize);
		}

		return ret;
	}
}
