package com.ericsson.eniq.base;
import java.util.Date;

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

public class SampleClass {
	public int id;
//	public byte[] binaryVal = null;
	public String firstName;
	public String lastName;
	public Date today = new Date();
	public short age = 10;
	public float income;
	public float outgoings;
	public Float balance;
	public byte[] flags = new byte[10];
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param income
	 * @param outgoings
	 */
	public SampleClass(int id, String firstName, String lastName, float income,
			float outgoings) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.income = income;
		this.outgoings = outgoings;
		this.balance = income-outgoings;
		
		for (int i=0; i<flags.length ; i++) {
			flags[i] = 0;
		}
	}
	
	
	
}
