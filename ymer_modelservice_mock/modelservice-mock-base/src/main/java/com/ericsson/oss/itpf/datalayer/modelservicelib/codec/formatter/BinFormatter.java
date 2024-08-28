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
package com.ericsson.oss.itpf.datalayer.modelservicelib.codec.formatter;

import java.nio.ByteOrder;

/**
 * Base class for other binary formatters
 */
public abstract class BinFormatter {

	protected static final boolean IS_LITTLE_ENDIAN = (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN);

	protected static final boolean IS_NULL = true;
	protected static final boolean IS_NOT_NULL = false;

	private static final byte NULL_BIT = (byte) 1;
	private static final byte NOT_NULL_BIT = (byte) 0;

	private static final int SPACE = 32;
	private static final int ZERO_BYTE = 0;

	public static boolean isLittleEndian() {
		return IS_LITTLE_ENDIAN;
	}

	private static byte[] createEmptyField(final int length, final int fill, final boolean isNull) {

		final byte[] ret = new byte[length];

		for (int i = 0; i < length - 1; i++) {
			ret[i] = (byte) fill;
		}
		ret[length - 1] = isNull ? NULL_BIT : NOT_NULL_BIT;

		return ret;
	}

	/**
	 * Create empty byte array of given length, initialising bytes as a space.
	 * Final byte is used to indicate field is null (1) or non-null (0).
	 * 
	 * @param length
	 *            Length of field
	 * @param isNull
	 *            true if null field required
	 * @return byte array of given length and null byte set as required
	 */
	protected static byte[] createEmptyCharField(final int length, final boolean isNull) {
		return createEmptyField(length, SPACE, isNull);
	}

	/**
	 * Create empty byte array of given length, initialising bytes as zero.
	 * Final byte is used to indicate field is null (1) or non-null (0).
	 * 
	 * @param length
	 *            Length of field
	 * @param isNull
	 *            true if null field required
	 * @return byte array of given length and null byte set as required
	 */
	protected static byte[] createEmptyByteField(final int length, final boolean isNull) {
		return createEmptyField(length, ZERO_BYTE, isNull);
	}
}
