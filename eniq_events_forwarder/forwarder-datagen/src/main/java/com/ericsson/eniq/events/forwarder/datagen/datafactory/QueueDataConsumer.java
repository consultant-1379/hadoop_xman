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
package com.ericsson.eniq.events.forwarder.datagen.datafactory;

import com.ericsson.eniq.events.forwarder.datagen.object.ObjectCollection;
import com.ericsson.eniq.events.forwarder.datagen.constant.Configuration;

public class QueueDataConsumer implements Runnable {
    ObjectCollection objCollection;

    public QueueDataConsumer(ObjectCollection objCollection) {
	this.objCollection = objCollection;
    }

    public void run() {
	System.out.println("QueueDataConsumer started.");
	while (true) {
	    try {
		System.out.println("Consumed "+objCollection.consume().getContent());
		System.out.println("pool size: "+objCollection.getSize());
	    } catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }

	    try {
		Thread.sleep(Configuration.Timer.DATA_CONSUMER_TIMER);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

}
