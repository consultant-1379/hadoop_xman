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

package com.ericsson.oss.datalayer.event;

import java.util.Map;

public class AbstractEvent implements Event{

	Header header;
	long typeId = NULL_RESOURCE_ID;
	long ropId = NULL_ROP_ID;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}

	public boolean hasHeader() {
		return header != null;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public long getTypeId() {
		return typeId;
	}

	public long getRopId() {
		return ropId;
	}

	@Override
	public long eventTypeId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasResourceId() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getResourceId() {
		return typeId;
	}

	@Override
	public boolean hasRopId() {
		return getRopId() != NULL_ROP_ID;
	}

}
