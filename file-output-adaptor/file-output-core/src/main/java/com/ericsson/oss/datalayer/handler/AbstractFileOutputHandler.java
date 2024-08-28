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

import com.ericsson.oss.datalayer.event.Event;
import com.ericsson.oss.itpf.common.event.handler.EventHandlerContext;

public abstract class AbstractFileOutputHandler<T> implements FileOutputHandler {

	@Override
	public boolean understandsURI(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onEvent(Object inputEvent) {
		Event event = (Event) inputEvent;
		onEventInternal(event);
	}

	@Override
	public void init(EventHandlerContext ctx) {
		// TODO initialise required objects
	}

	@Override
	public void destroy() {
		// TODO do some cleanup steps
	}

	protected abstract void onEventInternal(Event event);

}
