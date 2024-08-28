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
package com.ericsson.oss.datalayer.handler;

import com.ericsson.oss.datalayer.handler.FileWriter;
import com.ericsson.oss.datalayer.event.Event;

public class StandardFileOutputHandler<T> extends AbstractFileOutputHandler<T>{

	private FileWriter<T> fileWriter;
	
	@Override
	protected void onEventInternal(Event event) {
		// TODO Auto-generated method stub		
	}

}
