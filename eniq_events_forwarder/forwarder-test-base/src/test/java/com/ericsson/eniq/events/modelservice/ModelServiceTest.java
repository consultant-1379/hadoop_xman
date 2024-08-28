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

import junit.framework.TestCase;

public class ModelServiceTest extends TestCase {

    public void testGetCodec() {

        ModelService modelSrv;
        try {
            modelSrv = new ModelService();

            final Class clazz = modelSrv.getCodec("CALLFAILURE_EVENT");

            assertEquals("com.ericsson.eniq.events.modelservice.codec.impl.CALLFAILURE_EVENT_Codec", clazz.getName());

        } catch (final RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
