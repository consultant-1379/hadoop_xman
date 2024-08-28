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
 * The ForwarderField class encapsulates field data within the forwarder.
 * 
 */
public class ForwarderField {

	/**
	 * Name of the field
	 */

	private String name;
	/**
	 * Value of the field
	 */
	private Object value;

	/**
	 * Type of field e.g. int, varchar
	 */
	private FieldType type;

	/**
	 * Size of the field where relevant to the type or 0
	 */
	private int size;

	/**
	 * Constructor used to create ForwarderField objects where size is left
	 * undefined e.g. int or date types
	 * 
	 * @param name
	 * @param value
	 * @param type
	 */
	public ForwarderField(final String name, final Object value,
			final FieldType type) {
		this.name = name;
		this.value = value;
		this.type = type;
		this.size = 0;
	}

	/**
	 * Constructor used to create ForwarderField objects that have a defined
	 * size e.g. varchar or binary types
	 * 
	 * @param name
	 * @param value
	 * @param type
	 * @param size
	 */
	public ForwarderField(final String name, final Object value,
			final FieldType type, final int size) {
		this.name = name;
		this.value = value;
		this.type = type;
		this.size = size;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(final Object value) {
		this.value = value;
	}

	/**
	 * @return the type
	 */
	public FieldType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final FieldType type) {
		this.type = type;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(final int size) {
		this.size = size;
	}

	public String toString() {
		StringBuffer output = new StringBuffer(getName());
		output.append(":");
		output.append(getType());
		if (getSize()>0) {
			output.append("(");
			output.append(getSize());
			output.append(")");
		}
		output.append(":");
		output.append(getValue());
		
		return output.toString();
	}
	
}
