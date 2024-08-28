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
package com.ericsson.eniq.forwarder.core.io;

import com.ericsson.eniq.forwarder.event.handler.EventInputHandler;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;

public class HazelCastManager<T> {

    private Config cfg;
    private HazelcastInstance instance;
    private ITopic<T> topic;

    private EventInputHandler<T> eventInputHandler;
    public HazelCastManager(String name) {
        cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
        topic = instance.getTopic(name);
        eventInputHandler=new EventInputHandler<T>();
    }

    public HazelCastManager(String name, Config cfg) {
        instance = Hazelcast.newHazelcastInstance(cfg);
        topic = instance.getTopic(name);
        eventInputHandler=new EventInputHandler<T>();
    }

    /**
     * Start listening on events and send to relevant event handler
     * */
    public void startListeningEvents() {
        topic.addMessageListener(new MessageListener<T>() {

            public void onMessage(final Message<T> arg0) {
                eventInputHandler.onEvent(arg0.getMessageObject());
            }

        });
    }
}
