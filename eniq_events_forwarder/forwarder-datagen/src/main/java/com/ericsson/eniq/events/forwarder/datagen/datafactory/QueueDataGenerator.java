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
import com.ericsson.eniq.events.forwarder.datagen.object.SimpleObject;
import com.ericsson.eniq.events.forwarder.datagen.constant.Configuration;

public class QueueDataGenerator implements Runnable {

    ObjectCollection objectColl;

    public QueueDataGenerator(ObjectCollection objectColl) {
	this.objectColl = objectColl;
    }

    public void run() {

	System.out.println("QueueDataGenerator started.");
	while (true) {
	    try {
		objectColl.produce(new SimpleObject(String.valueOf(Math.random()),
			String.valueOf(Math.random()), String.valueOf(Math
				.random())));
	    } catch (InterruptedException e1) {
		
		e1.printStackTrace();
	    }

	    // System.out.println("Generator: produced");

	    try {
		Thread.sleep(Configuration.Timer.DATA_GENERATOR_TIMER);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

}
