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

import java.net.MalformedURLException;
import java.rmi.*;

import junit.framework.TestCase;

import com.ericsson.eniq.events.forwarder.model.CALLFAILURE_EVENT;
import com.ericsson.eniq.events.forwarder.model.HANDOVER_EVENT;
import com.ericsson.eniq.events.modelservice.ModelServiceInterface;
import com.ericsson.eniq.events.modelservice.client.IQHelper;
import com.ericsson.eniq.events.modelservice.codec.Codec;

public class ModelServiceIntegrationTest extends TestCase {

    public void testCodec() {

        ModelServiceInterface modelSrv;
        try {
            modelSrv = (ModelServiceInterface) Naming.lookup("rmi://localhost:6602/ModelService");

            Class<? extends Codec> clazz = modelSrv.getCodec("CALLFAILURE_EVENT");
            clazz = (Class<? extends Codec>) getClass().getClassLoader().loadClass(clazz.getName());

            final Codec codec = clazz.newInstance();

            final CALLFAILURE_EVENT event = new CALLFAILURE_EVENT();
            final String b = codec.encodeAsString(event);

            System.out.println(b);

            final byte[] bytes = codec.encode(event);

            System.out.println(IQHelper.bytesToHex(bytes));

        } catch (final MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (final RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (final NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (final ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void testCodec2() {

        ModelServiceInterface modelSrv;
        try {
            modelSrv = (ModelServiceInterface) Naming.lookup("rmi://localhost:6602/ModelService");

            Class<? extends Codec> clazz = modelSrv.getCodec("HANDOVER_EVENT");
            clazz = (Class<? extends Codec>) getClass().getClassLoader().loadClass(clazz.getName());

            final Codec codec = clazz.newInstance();

            final HANDOVER_EVENT event = new HANDOVER_EVENT();
            final String b = codec.encodeAsString(event);

            System.out.println(b);

            final byte[] bytes = codec.encode(event);

            System.out.println(IQHelper.bytesToHex(bytes));

        } catch (final MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (final RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (final NotBoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (final ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
