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
package com.ericsson.eniq.forwarder.core.main;

import com.ericsson.eniq.forwarder.core.constant.Constant;
import com.ericsson.eniq.forwarder.core.properties.PropertyManager;
import com.ericsson.eniq.forwarder.event.listener.EventListener;
import com.ericsson.eniq.forwarder.event.listener.HazelCastEventListener;

public class App {

    /**
     * Forwarder entry point
     */
    public static void main(String[] args) {

    	System.out.println("APP>>>>>ID:" + PropertyManager.getProperty(Constant.FileManager.APP_ID));
    	System.out.println("APP>>>>>FILE_FORMAT_PROPERTY:" + PropertyManager.getProperty(Constant.FileManager.FILE_FORMAT_PROPERTY));
        // start listening incoming events
        EventListener handler = new HazelCastEventListener<Object>(Constant.HazelCast.DISTRIBUTED_TOPIC_NAME);
        handler.onListen();
    }
}
