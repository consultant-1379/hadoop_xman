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

import com.ericsson.eniq.events.forwarder.datagen.object.HazelCastAccessor;
import com.ericsson.eniq.events.forwarder.datagen.constant.Configuration;

public class HazelCastDataConsumer implements Runnable {

    HazelCastAccessor<String> accessor;

    // EventHandler evtHandler=new EventInputHandler();

    public HazelCastDataConsumer() {
	accessor = new HazelCastAccessor<String>(
		Configuration.HazelCast.HAZEL_MEMORY_BLOCK_FIELD_NAME);
    }

    public void run() {
	accessor.startListeningEvents();
    }

}
