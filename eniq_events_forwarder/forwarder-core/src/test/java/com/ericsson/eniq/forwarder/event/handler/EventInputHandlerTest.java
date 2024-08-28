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

package com.ericsson.eniq.forwarder.event.handler;


import org.junit.Test;
import static org.junit.Assert.*;

import com.ericsson.eniq.events.modelservice.model.HANDOVER_EVENT;
import com.ericsson.eniq.forwarder.event.handler.EventInputHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class EventInputHandlerTest {
    private static final String EVENT_DETAILS = "Test Event Details";
	private static final HANDOVER_EVENT TEST_EVENT = new HANDOVER_EVENT();

    private static EventInputHandler testEventInputHandler = new EventInputHandler();



    @Test
    public void testonEvent() {
        //ToDO Write new test case for EventInputHandler to check output
    }


    /**
     * Test to check that no exceptions are thrown by the EventInputHandler
     */
    @Test
    public void TestNoExceptionIsThrownByMethodUnderTest()
    {
        try{
            testEventInputHandler.onEvent(TEST_EVENT);
        }catch (Exception ex){
            assertFalse("Exception Caught in Run Time: "+ ex.getMessage(),true);
        }
    }
}
