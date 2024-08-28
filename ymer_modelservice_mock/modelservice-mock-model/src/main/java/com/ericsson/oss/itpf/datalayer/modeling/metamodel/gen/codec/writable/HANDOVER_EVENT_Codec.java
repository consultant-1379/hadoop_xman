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
package com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.codec.writable;

import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.hive.serde2.columnar.BytesRefWritable;
import org.apache.hadoop.io.Writable;

import com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.event.HANDOVER_EVENT;
import com.ericsson.oss.itpf.datalayer.modelservicelib.Event;
import com.ericsson.oss.itpf.datalayer.modelservicelib.codec.AbstractWritableCodec;

/**
 * codec for handover event
 */
public class HANDOVER_EVENT_Codec extends AbstractWritableCodec {

    @Override
    public Writable encode(final Event o) {
        final HANDOVER_EVENT event = (HANDOVER_EVENT) o;
        final BytesRefArrayWritable writable = new BytesRefArrayWritable(7);
        int index = 0;

        writable.set(index, new BytesRefWritable(String.valueOf(event.getEVENT_ID()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getSTART_RNC()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getEND_RNC()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getSTART_C_ID()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getEND_C_ID()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getIMSI()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getDATETIME_ID()).getBytes()));

        return writable;
    }

    @Override
    public Event decode(final Writable input) {
        // TODO Auto-generated method stub
        return null;
    }

}
