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

import java.util.Map;

import com.ericsson.oss.datalayer.event.Event;
import com.ericsson.oss.datalayer.event.Header;

public class MockEvent implements Event {

	@Override
	public long getTypeId() {
		return 0;
	}

	@Override
	public Map<String, Object> getHeaders() {
		return null;
	}

	@Override
	public Object getPayload() {
		return null;
	}

	@Override
	public String getNamespace() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getVersion() {
		return null;
	}

	@Override
	public long eventTypeId() {
		return 0;
	}

	@Override
	public boolean hasHeader() {
		return false;
	}

	@Override
	public Header getHeader() {
		return null;
	}

	@Override
	public boolean hasResourceId() {
		return false;
	}

	@Override
	public long getResourceId() {
		return 0;
	}

	@Override
	public boolean hasRopId() {
		return false;
	}

	@Override
	public long getRopId() {
		return 0;
	}
}
