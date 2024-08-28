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
package com.ericsson.eniq.events.forwarder.datagen.object;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class is a queue Java object accessor
 * */
public class ObjectCollection {

    private BlockingQueue<SimpleObject> queue;

    public ObjectCollection() {
	queue = new LinkedBlockingQueue<SimpleObject>();
    }

    public SimpleObject consume() throws InterruptedException {
	return queue.take();
    }

    public void produce(SimpleObject obj) throws InterruptedException {
	queue.put(obj);
    }

    public boolean isEmpty() {
	if (queue.isEmpty())
	    return true;

	return false;
    }

    public int getSize() {
	return queue.size();
    }
}
