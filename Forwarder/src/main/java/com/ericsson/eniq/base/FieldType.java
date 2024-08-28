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
package com.ericsson.eniq.base;

/**
 * Enum to represent type of field within the Forwarder. e.g. int, varchar, date
 */
public enum FieldType {
	TINYINT, SMALLINT, INT, VARCHAR, BINARY, TIMESTAMP, FLOAT, BIT, DATE, UNSIGNEDINT, UNSIGNEDBIGINT, UNDEFINED;

	public static FieldType classToFieldType(Class<?> type) {
		
		FieldType ft = FieldType.UNDEFINED;

		String className = type.getSimpleName();

		if (className.equals("Byte") || className.equals("byte")) {
			ft = TINYINT;
		} else if (className.equals("Short") || className.equals("short")) {
			ft = SMALLINT;
		} else if (className.equals("Integer") || className.equals("int")
				|| className.equals("Long") || className.equals("long")) {
			ft = INT;
		} else if (className.equals("String")) {
			ft = VARCHAR;
		} else if (className.equals("Byte[]") || className.equals("byte[]")) {
			ft = BINARY;
		} else if (className.equals("Timestamp")) {
			ft = TIMESTAMP;
		} else if (className.equals("Float") || className.equals("float")
				|| className.equals("Double") || className.equals("double")) {
			ft = FLOAT;
		} else if (className.equals("Boolean") || className.equals("boolean")) {
			ft = BIT;
		} else if (className.equals("Date")) {
			ft = DATE;
		} 

		return ft;
	}
}
