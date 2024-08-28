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
package com.ericsson.oss.output.adaptor.hazelcast;

public class HazelCastEventListener<T> {

    private HazelCastConnector hazelcastConnector;

    /**
     * Initialize HazelCastEventListener
     * 
     * */
    public HazelCastEventListener(String topic) {
    	hazelcastConnector = new HazelCastConnector(topic);
    }

    /**
     * Listen on incoming events
     * */
    public void onListen() {
        hazelcastConnector.startListeningEvents();
    }

}
