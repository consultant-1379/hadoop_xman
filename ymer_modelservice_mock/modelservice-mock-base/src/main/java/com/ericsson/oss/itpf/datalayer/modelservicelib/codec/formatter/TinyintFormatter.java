package com.ericsson.oss.itpf.datalayer.modelservicelib.codec.formatter;

/**
 * Utility class for formatting tiny int (byte) as 1 byte binary data
 */
public abstract class TinyintFormatter {

	/**
	 * Format tiny int (byte) as 1 byte binary data (plus 1 byte for null). Values less
	 * than zero will be treated as null. 
	 * 
	 * @param value
	 *            value to be formatted
	 * @return 1 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final byte value) {

		final boolean isNull = (value < 0);
		return doFormat(value, isNull);
	}

	/**
	 * Format tiny int (byte) as 1 byte binary data (plus 1 byte for null)
	 * 
	 * @param value
	 *            value to be formatted
	 * @param isNull
	 *            If true, the returned value will represent null with data
	 *            bytes set to 0 and final null byte set to 1
	 * @return 1 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final byte value, final boolean isNull) {

		final byte[] ret = new byte[2];

		if (isNull) {
			ret[0] = 0;
			ret[1] = 1;
		} else {
			ret[0] = value;
			ret[1] = 0;
		}
		return ret;
	}

}
