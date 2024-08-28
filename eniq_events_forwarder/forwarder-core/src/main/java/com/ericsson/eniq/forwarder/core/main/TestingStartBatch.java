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
package com.ericsson.eniq.forwarder.core.main;

import com.ericsson.eniq.events.modelservice.server.Main;



public class TestingStartBatch {

    public static void main(String[] args) throws InterruptedException {

        //run ModelService
        System.out.println("Start ModelService");
        Main.main(null);
        Thread.sleep(200);

        //run EventListener
        System.out.println("Start EventListener");
        App.main(null);
        Thread.sleep(1000);

        //run data generator
        //System.out.println("Start data generator");
        //App_HazelcastApproach.main(null);
    }
}
