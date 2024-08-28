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
package com.ericsson.eniq.events.forwarder.datagen.main;

import com.ericsson.eniq.events.forwarder.datagen.object.ObjectCollection;
import com.ericsson.eniq.events.forwarder.datagen.datafactory.QueueDataConsumer;
import com.ericsson.eniq.events.forwarder.datagen.datafactory.QueueDataGenerator;

public class App_QueueApproach {

    // data poll for all events
    public static final ObjectCollection objCollection = new ObjectCollection();

    public void ProduceData() {
	//add data generator
	new Thread(new QueueDataGenerator(objCollection)).start();
	
	//add data consumer
	new Thread(new QueueDataConsumer(objCollection)).start();
    }

    public static void main(String[] args) {
	
	//main entry point
	new App_QueueApproach().ProduceData();
    }
}
