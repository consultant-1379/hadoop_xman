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
package com.ericsson.eniq.events.modelservice.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.ericsson.eniq.events.modelservice.ModelService;

public class Main {

    public void startup() {

        try {
            final Registry registry = LocateRegistry.createRegistry(PropertyManager.getInstance().getRegistryPort());
            registry.rebind("ModelService", new ModelService());
            System.out.println("Model Service is ready!");
        } catch (final RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {

        new Main().startup();
    }
}
