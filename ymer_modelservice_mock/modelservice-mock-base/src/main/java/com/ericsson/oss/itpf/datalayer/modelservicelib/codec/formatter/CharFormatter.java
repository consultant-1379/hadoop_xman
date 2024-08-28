package com.ericsson.oss.itpf.datalayer.modelservicelib.codec.formatter;

/**
 * Utility class for formatting character data as binary data
 */
public abstract class CharFormatter extends BinFormatter {

	/**
	 * Format string as binary field of given length.
	 * 
	 * If the string is null, the returned value will represent null with data
	 * bytes set to 32 (space) and null byte set to 1.
	 * 
	 * If the string is empty, the returned value will represent an empty string
	 * with data bytes set to 32 (space) and null byte set to 0.
	 * 
	 * If the string is longer than the given length, it will be truncated. If
	 * the string is shorter than the given length, the remaining data bytes are
	 * set to 32 (space).
	 * 
	 * @param value
	 *            value to be formatted
	 * @param fieldSize
	 *            size of binary field to create
	 * @return binary field of the given size (plus null byte)
	 */
	public static byte[] doFormat(final String value, final int fieldSize) {

		final boolean isNull = (value == null);
		final byte[] ret = createEmptyCharField(fieldSize + 1, isNull);

		if (!isNull) {
			final char[] arr = value.toCharArray();
			for (int i = 0; i < arr.length && i < ret.length - 1; i++) {
				ret[i] = (byte) arr[i];
			}
		}

		return ret;
	}
}
