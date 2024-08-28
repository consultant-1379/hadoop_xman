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
package com.ericsson.eniq.events.modelservice.integration;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.modelservice.server.Main;
import com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.event.CALLFAILURE_EVENT;
import com.ericsson.oss.itpf.datalayer.modelservicelib.ModelServiceInterface;
import com.ericsson.oss.itpf.datalayer.modelservicelib.codec.Codec;
import com.ericsson.oss.itpf.datalayer.modelservicelib.codec.helper.IQHelper;

public class ModelServiceIntegrationTest {

    @Before
    public void startUp() {

        final Main main = new Main();

        main.startup();
    }

    @Test
    public void testCodec() {

        ModelServiceInterface modelSrv;
        try {
            final Registry registry = LocateRegistry.getRegistry(6602);

            modelSrv = (ModelServiceInterface) registry.lookup("ModelService");

            final String strClazz = modelSrv.getCodecClass("bin", "CALLFAILURE_EVENT");

            final Class clazz = Class.forName(strClazz);
            final Codec<CALLFAILURE_EVENT, byte[]> codec = (Codec<CALLFAILURE_EVENT, byte[]>) clazz.newInstance();

            final CALLFAILURE_EVENT event = new CALLFAILURE_EVENT();
            final byte[] b = codec.encode(event);

            System.out.println(IQHelper.bytesToHex(b));

        } catch (final RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (final NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
