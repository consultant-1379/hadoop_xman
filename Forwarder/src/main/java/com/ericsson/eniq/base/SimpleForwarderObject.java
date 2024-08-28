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

import java.lang.reflect.Field;
import java.util.*;

public class SimpleForwarderObject implements ForwarderObject {

	String objectType;
	private Object real;

	private List<ForwarderField> fields = new ArrayList<ForwarderField>();

	/**
	 * @param objectType
	 * @param real
	 */
	public SimpleForwarderObject(String objectType, Object real) {
		super();
		this.objectType = objectType;
		setRealObject(real);
	}

	public void setRealObject(Object real) {
		this.real = real;
		parseRealObject();
	}

	public Object getRealObject() {
		return real;
	}

	public String getObjectType() {
		return objectType;
	}

	public List<ForwarderField> toList() {
		return fields;
	}

	/**
	 * Examine instances wrapped object and creates field list from instance
	 * variables.
	 */
	private void parseRealObject() {
		
		fields.clear();
		
		Field[] realObjectsFields = real.getClass().getFields();

		for (Field f : realObjectsFields) {
			
			int size = 0;
			String name = f.getName();

			Object value = null;
			try {
				value = f.get(real);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			FieldType type = FieldType.classToFieldType(f.getType());

			if (type == FieldType.VARCHAR) {
				size = ((String) value).length();
			} else if (type == FieldType.BINARY) {
				size = ((byte[]) value).length;
			}

			fields.add(new ForwarderField(name, value, type, size));
		}

	}

}
