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
package com.ericsson.eniq.events.forwarder.datagen.datafactory;

import com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.event.*;

import com.ericsson.eniq.events.forwarder.datagen.constant.Configuration;
import com.ericsson.eniq.events.forwarder.datagen.math.DataGenerator;
import com.ericsson.eniq.events.forwarder.datagen.object.HazelCastAccessor;


public class HazelCastDataGenerator implements Runnable {

    HazelCastAccessor<HANDOVER_EVENT> accessor;
    HazelCastAccessor<CALLFAILURE_EVENT> accessor2;

    public HazelCastDataGenerator() {
        accessor = new HazelCastAccessor<HANDOVER_EVENT>(Configuration.HazelCast.HAZEL_MEMORY_BLOCK_FIELD_NAME);
        accessor2 = new HazelCastAccessor<CALLFAILURE_EVENT>(Configuration.HazelCast.HAZEL_MEMORY_BLOCK_FIELD_NAME);
    }

    public void run() {
        int count = 0;
        for (int i=0;i<10000;i++) {
            if (count % 2 == 0) {
                // test
                HANDOVER_EVENT testObj = new HANDOVER_EVENT();

                //	    protected short EVENT_ID = -1;
                //	    protected short START_RNC = -1;
                //	    protected short END_RNC = -1;
                //	    protected int START_C_ID = -1;
                //	    protected int END_C_ID = -1;
                //	    protected long IMSI = -1;
                //	    protected long DATETIME_ID = -1;
                //	  

                //set up attributeds
                testObj.setEVENT_ID((short)i);
                testObj.setSTART_RNC(DataGenerator.getRandomShort());
                testObj.setEND_RNC(DataGenerator.getRandomShort());
                testObj.setSTART_C_ID(DataGenerator.getRandomInteger());
                testObj.setEND_C_ID(DataGenerator.getRandomInteger());
                testObj.setIMSI(DataGenerator.getRandomLong());
                testObj.setDATETIME_ID(DataGenerator.getRandomLong());

                accessor.publish(testObj);
            } else {
                CALLFAILURE_EVENT testObj2 = new CALLFAILURE_EVENT();
                accessor2.publish(testObj2);
            }

            try {
                Thread.sleep(Configuration.Timer.DATA_GENERATOR_TIMER);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            count++;
        }
    }

}
