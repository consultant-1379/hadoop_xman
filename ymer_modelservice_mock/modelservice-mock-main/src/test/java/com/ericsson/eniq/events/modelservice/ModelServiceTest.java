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

            String clazz = modelSrv.getCodecClass("bin", "CALLFAILURE_EVENT");

            assertEquals("com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.codec.bin.CALLFAILURE_EVENT_Codec", clazz);

            clazz = modelSrv.getCodecClass("text", "CALLFAILURE_EVENT");
            assertEquals("com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.codec.text.CALLFAILURE_EVENT_Codec", clazz);

            clazz = modelSrv.getCodecClass("writable", "CALLFAILURE_EVENT");
            assertEquals("com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.codec.writable.CALLFAILURE_EVENT_Codec", clazz);

        } catch (final RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
