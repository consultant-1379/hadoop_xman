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
package com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.codec.bin;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.event.CALLFAILURE_EVENT;
import com.ericsson.oss.itpf.datalayer.modelservicelib.Event;
import com.ericsson.oss.itpf.datalayer.modelservicelib.codec.AbstractSybaseIqCodec;
import com.ericsson.oss.itpf.datalayer.modelservicelib.codec.helper.IQHelper;

/**
 * codec for handover event
 */
public class CALLFAILURE_EVENT_Codec extends AbstractSybaseIqCodec {

    private static int SIZE = 27;

    @Override
    public CALLFAILURE_EVENT decode(final byte[] input) {

        return null;
    }

    @Override
    public byte[] encode(final Event o) {
        final CALLFAILURE_EVENT event = (CALLFAILURE_EVENT) o;
        final byte[] data = new byte[SIZE];
        final ByteBuffer buffer = ByteBuffer.wrap(data);

        try {
            buffer.put(IQHelper.writeSmallint(event.getEVENT_ID(), false));
            buffer.put(IQHelper.writeSmallint(event.getCAUSE_VALUE(), false));
            buffer.put(IQHelper.writeSmallint(event.getEXTENDED_CAUSE_VALUE(), false));
            buffer.put(IQHelper.writeUnsignedbigint(event.getIMSI(), false));
            buffer.put(IQHelper.writeTimestamp(event.getDATETIME_ID(), false));

            return buffer.array();

        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
