package com.ericsson.eniq.base;

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

public class SampleClassSmall {
	public int id;
//	public byte[] binaryVal = null;
	public String firstName;
	public String lastName;
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param income
	 * @param outgoings
	 */
	public SampleClassSmall(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
}
