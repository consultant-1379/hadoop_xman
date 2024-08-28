package com.ericsson.eniq.events.modelservice.client.formatters;

import java.math.BigInteger;

/**
 * Utility class for formatting BigIntegers as 8 byte binary data
 */
public abstract class UnsignedbigintFormatter extends BinFormatter {

	private static final int FIELD_SIZE = 8;

	/**
	 * Format BigInteger as 8 byte binary data (plus 1 byte for null). Values
	 * less than zero will be treated as null. The field will be formatted
	 * according to the native endian order.
	 * 
	 * @param value
	 *            value to be formatted
	 * @return 8 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final BigInteger value) {
		
		final boolean isNull = (value.compareTo(BigInteger.ZERO) == -1);
		return doFormat(value, IS_LITTLE_ENDIAN, isNull);
	}

	/**
	 * Format BigInteger as 8 byte binary data (plus 1 byte for null)
	 * 
	 * @param value
	 *            value to be formatted
	 * @param isLittleEndian
	 *            true if number is to be stored in little endian format. i.e.
	 *            with the least significant byte first
	 * @param isNull
	 *            If true, the returned value will represent null with data
	 *            bytes set to 0 and final null byte set to 1
	 * @return 8 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final BigInteger value,
			final boolean isLittleEndian, final boolean isNull) {
		
		byte[] ret;

		if (isNull) {
			ret = createEmptyByteField(FIELD_SIZE + 1, IS_NULL);
		} else {
			ret = doFormat(value, isLittleEndian);
		}

		return ret;
	}

	/**
	 * Format BigInteger as 8 byte binary data (plus 1 byte for null)
	 * 
	 * @param value
	 *            value to be formatted
	 * @param isLittleEndian
	 *            true if number is to be stored in little endian format. i.e.
	 *            with the least significant byte first
	 * @return 8 byte binary representation of the value (plus null byte = 0)
	 */
	protected static byte[] doFormat(final BigInteger value,
			final boolean isLittleEndian) {

		final byte[] byteArr = value.toByteArray();
		final byte[] ret = createEmptyByteField(FIELD_SIZE + 1, IS_NOT_NULL);
		int pos = byteArr.length - 1;

		if (isLittleEndian) {
			for (int i = 0; i < FIELD_SIZE && pos >= 0; i++) {
				ret[i] = byteArr[pos--];
			}
		} else {
			for (int i = FIELD_SIZE - 1; i >= 0 && pos >= 0; i--) {
				ret[i] = byteArr[pos--];
			}
		}

		return ret;
	}
}
