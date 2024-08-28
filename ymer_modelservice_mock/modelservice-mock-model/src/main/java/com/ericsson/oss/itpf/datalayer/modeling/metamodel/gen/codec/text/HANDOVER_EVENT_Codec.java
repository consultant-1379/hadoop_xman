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
package com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.codec.text;

import java.io.StringWriter;

import com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.event.HANDOVER_EVENT;
import com.ericsson.oss.itpf.datalayer.modelservicelib.Event;
import com.ericsson.oss.itpf.datalayer.modelservicelib.codec.AbstractTextCodec;

/**
 * codec for handover event
 */
public class HANDOVER_EVENT_Codec extends AbstractTextCodec {

    @Override
    public String encode(final Event o) {
        final HANDOVER_EVENT event = (HANDOVER_EVENT) o;
        final StringWriter writer = new StringWriter();

        writer.write("" + event.getEVENT_ID() + getSeparator());
        writer.write("" + event.getSTART_RNC() + getSeparator());
        writer.write("" + event.getEND_RNC() + getSeparator());
        writer.write("" + event.getSTART_C_ID() + getSeparator());
        writer.write("" + event.getEND_C_ID() + getSeparator());
        writer.write("" + event.getIMSI() + getSeparator());
        writer.write("" + event.getDATETIME_ID());

        return writer.toString();
    }

    @Override
    public Event decode(final String input) {
        // TODO Auto-generated method stub
        return null;
    }

}
