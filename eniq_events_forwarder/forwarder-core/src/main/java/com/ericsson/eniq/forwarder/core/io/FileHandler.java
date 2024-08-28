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
package com.ericsson.eniq.forwarder.core.io;

import com.ericsson.eniq.events.modelservice.api.codec.Codec;

public abstract class FileHandler<T> {

	protected String storageLocation;
	protected String eventType;

	public abstract void init();

	public abstract void write(Codec codec, T event);
	
	public abstract void writeFileEnd(Codec codec, T event);

	public void rollOver() {
		if (save()) {
			init();
		}
	}

	public abstract boolean save();
}
