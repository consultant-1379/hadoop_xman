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

import java.util.List;

/**
 * ForwarderObject provides a simple interface for objects passed around within
 * the forwarder and acts as a container for the real object
 */
public interface ForwarderObject {

	/**
	 * Store the real object
	 * 
	 * @param real
	 *            The object to be wrapped by the ForwarderObject
	 */
	void setRealObject(Object real);

	/**
	 * Get the real object that is wrapped by the ForwarderObject
	 * 
	 * @return The wrapped object
	 */
	Object getRealObject();

	/**
	 * Returns a string representing the type of object wrapped by the
	 * ForwarderObject e.g. LTE_ERR
	 * 
	 * @return String representing type of wrapped object
	 */
	String getObjectType();

	/**
	 * Return instance variables of the wrapped object as a list of
	 * ForwarderField objects
	 * 
	 * @return list of ForwarderField objects
	 */
	List<ForwarderField> toList();
}
