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
package com.ericsson.eniq.events.modelservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import com.ericsson.eniq.events.modelservice.codec.Codec;

public class ModelService extends UnicastRemoteObject implements ModelServiceInterface {

    public Map<String, Class<? extends Codec>> models;
    private static String[] events = new String[] { "CALLFAILURE_EVENT", "HANDOVER_EVENT" };

    /**
     * @throws RemoteException
     */
    protected ModelService() throws RemoteException {
        super();
        models = new HashMap<String, Class<? extends Codec>>();

        init();
    }

    /**
     * initialise the model service
     */
    private void init() {

        for (final String event : events) {

            Class clazz = null;

            try {

                clazz = Class.forName("com.ericsson.eniq.events.modelservice.codec.impl." + event + "_Codec");

            } catch (final ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            models.put(event, clazz);

        }

    }

    public Class<? extends Codec> getCodec(final String event) throws RemoteException {

        return models.get(event);

    }

}
