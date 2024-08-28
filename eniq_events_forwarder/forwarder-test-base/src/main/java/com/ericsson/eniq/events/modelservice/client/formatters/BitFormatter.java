package com.ericsson.eniq.events.modelservice.client.formatters;

/**
 * Utility class for formatting single bit binary data
 */
public abstract class BitFormatter extends BinFormatter {

	/**
	 * Format byte as single bit binary data.
	 * 
	 * @param value
	 *            value to format. Any value other than 0 is treated as 1.
	 * @return single byte binary representation of the value (plus null byte).
	 */
	public static byte[] doFormat(final byte value) {
		
		return new byte[] { value==0 ? (byte)0 : (byte)1, (byte) 0 };
	}
}
