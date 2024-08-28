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
package com.ericsson.eniq.forwarder.event.handler;

import com.ericsson.eniq.forwarder.core.io.FileManagerSet;

public class EventInputHandler<T> extends AbstractEventHandler implements EventHandler<T> {

    private FileManagerSet<T> fileManagers = new FileManagerSet<T>();

    /**
     * Pass event to file manager
     * */
    public void onEvent(T event) {
        //Pass event to file manager
        fileManagers.getFileManager(event).onEvent(event);
    }
}
