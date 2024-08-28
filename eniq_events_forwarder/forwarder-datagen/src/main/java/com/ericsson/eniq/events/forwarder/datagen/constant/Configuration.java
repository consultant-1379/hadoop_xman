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
package com.ericsson.eniq.events.forwarder.datagen.constant;

public class Configuration {

    /**
     * The timer is able to specify the thread waiting time between two loop life cycle
     * */
    public class Timer {
        public static final int DATA_GENERATOR_TIMER = 2;
        public static final int DATA_CONSUMER_TIMER = 150;
    }

    /**
     * The HazelCast specifies the memory field name, you can free to edit it as prefer value.
     * */
    public class HazelCast {
        public static final String HAZEL_MEMORY_BLOCK_FIELD_NAME = "myTopic";
    }
}
