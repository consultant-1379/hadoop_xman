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
import com.ericsson.oss.modelservice.codec.Codec;

public class MockCodec implements Codec<Event, String> {

	public String encode(Event input) {
		return "TestString";
	}

	public Event decode(String input) {
		return null;
	}
}
