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
package com.ericsson.oss.output.adaptor.main;

import com.ericsson.oss.output.adaptor.hazelcast.HazelCastEventListener;


public class App {
 

	public static void main(String[] args) {

        // start listening incoming events
		HazelCastEventListener handler = new HazelCastEventListener<Object>("myTopic");
        handler.onListen();
    }
}
