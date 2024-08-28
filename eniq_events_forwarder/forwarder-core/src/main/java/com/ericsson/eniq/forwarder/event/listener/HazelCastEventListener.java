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
package com.ericsson.eniq.forwarder.event.listener;

import com.ericsson.eniq.forwarder.core.io.HazelCastManager;

public class HazelCastEventListener<T> implements EventListener {

    private HazelCastManager<T> hazelcastManager;

    /**
     * Initialize HazelCastEventListener
     * 
     * */
    public HazelCastEventListener(String hazelfield) {
        hazelcastManager = new HazelCastManager<T>(hazelfield);
    }

    /**
     * Listen on incoming events
     * */
    public void onListen() {
        hazelcastManager.startListeningEvents();
    }

}
