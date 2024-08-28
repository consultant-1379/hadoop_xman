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

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.ericsson.eniq.events.modelservice.codec.Codec;

public interface ModelServiceInterface extends Remote {

    public Class<? extends Codec> getCodec(String event) throws RemoteException;

}
