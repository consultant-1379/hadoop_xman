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

import com.ericsson.eniq.forwarder.event.handler.EventHandler;
import com.ericsson.eniq.forwarder.event.handler.EventInputHandler;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;

/**
 * This is a In-memory storage (hazelcast) accessor
 * */
public class HazelCastAccessor<T> {

    private Config cfg;
    private HazelcastInstance instance;
    private ITopic<T> topic;

    public HazelCastAccessor(String name) {
        cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
        topic = instance.getTopic(name);

    }

    /**
     * Put element in queue
     * */
    public void publish(T obj) {
        topic.publish(obj);
    }

    public void startListeningEvents() {
        topic.addMessageListener(new MessageListener<T>() {

            public void onMessage(final Message<T> arg0) {
                //System.out.println(arg0.getMessageObject().toString());		

                EventHandler<T> evtHandler = new EventInputHandler<T>();

                evtHandler.onEvent(arg0.getMessageObject());

            }

        });
    }
}
