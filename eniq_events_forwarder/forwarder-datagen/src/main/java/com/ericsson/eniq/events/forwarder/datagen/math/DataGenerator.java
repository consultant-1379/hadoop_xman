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
package com.ericsson.eniq.events.forwarder.datagen.math;

import java.util.Random;

public class DataGenerator {

    public static int getRandomInteger() {
        return (new Random().nextInt()) ;
    }

    public static long getRandomLong() {
        return (new Random().nextLong());
    }

    public static short getRandomShort() {
        return (short)(new Random().nextInt());
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {

            System.out.println(DataGenerator.getRandomShort());

            Thread.sleep(500);
        }
    }
}
