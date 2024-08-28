package com.ericsson.oss.itpf.datalayer.modelservicelib.codec.formatter;

import java.math.BigInteger;

/**
 * Utility class for formatting date-times as binary data
 */
public abstract class DatetimeFormatter extends BinFormatter {

	/**
	 * Format datetime as 8 byte binary data (plus 1 byte for null).
	 * 
	 * @param value
	 *            Date as string in format "YYYY-MM-DD HH:mm:ss.ms". An empty
	 *            string is treated as null.
	 * @return 8 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final String value) {
		
		return doFormat(value, IS_LITTLE_ENDIAN);
	}
	
	/**
	 * Format datetime as 8 byte binary data (plus 1 byte for null).
	 * 
	 * @param value
	 *            Date as string in format "YYYY-MM-DD HH:mm:ss.ms". An empty
	 *            string is treated as null.
	 * @param isLittleEndian
	 *            true if datetime is to be stored in little endian format. i.e.
	 *            with the least significant byte first
	 * @return 8 byte binary representation of the value (plus null byte)
	 */
	public static byte[] doFormat(final String value,
			final boolean isLittleEndian) {

		final boolean isNull = (value == null || value.length() == 0);
		byte[] ret = createEmptyByteField(9, isNull);
		
		if (!isNull) {
			final String[] parts = value.split(" ");

			if (parts.length != 2) {
				throw new IllegalArgumentException("Unknown datetime format: "
						+ value);
			}

			final BigInteger binaryDateValue = BigInteger.valueOf(DateFormatter
					.getBinaryValue(parts[0]));
			final BigInteger binaryTimeValue = TimeFormatter
					.getBinaryValue(parts[1]);

			final BigInteger hos_date_scale = BigInteger.valueOf(86400000000L);

			final BigInteger binaryDatetime = binaryDateValue.multiply(
					hos_date_scale).add(binaryTimeValue);

			ret = UnsignedbigintFormatter.doFormat(binaryDatetime,
					isLittleEndian, false);
		}

		return ret;
	}

}
