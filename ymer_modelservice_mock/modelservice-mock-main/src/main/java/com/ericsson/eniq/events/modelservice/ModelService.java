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

import com.ericsson.oss.itpf.datalayer.modelservicelib.ModelServiceInterface;

public class ModelService extends UnicastRemoteObject implements ModelServiceInterface {

    /**
     * @throws RemoteException
     */
    public ModelService() throws RemoteException {
        super();

    }

    @Override
    public String getCodecClass(final String codecType, final String eventType) throws RemoteException {

        return "com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.codec." + codecType + "." + eventType + "_Codec";

    }

}
