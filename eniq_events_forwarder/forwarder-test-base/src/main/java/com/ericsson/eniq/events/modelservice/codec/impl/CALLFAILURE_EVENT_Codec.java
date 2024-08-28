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
package com.ericsson.eniq.events.modelservice.codec.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;

import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.hive.serde2.columnar.BytesRefWritable;
import org.apache.hadoop.io.Writable;

import com.ericsson.eniq.events.forwarder.model.CALLFAILURE_EVENT;
import com.ericsson.eniq.events.modelservice.client.IQHelper;
import com.ericsson.eniq.events.modelservice.codec.Codec;

/**
 * codec for handover event
 */
public class CALLFAILURE_EVENT_Codec implements Codec {

    private static int SIZE = 27;
    private String separator = ",";

    public String encodeAsString(final Object o) {

        final CALLFAILURE_EVENT event = (CALLFAILURE_EVENT) o;

        final StringWriter writer = new StringWriter();

        writer.write("" + event.getEVENT_ID() + getSeparator());
        writer.write("" + event.getCAUSE_VALUE() + getSeparator());
        writer.write("" + event.getEXTENDED_CAUSE_VALUE() + getSeparator());
        writer.write("" + event.getIMSI() + getSeparator());
        writer.write("" + event.getDATETIME_ID());

        return writer.toString();

    }

    public Object decodeFromString(final String string) {
        // TODO Auto-generated method stub
        return null;
    }

    public byte[] encode(final Object o) {

        if (o instanceof CALLFAILURE_EVENT) {

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

        }

        return null;

    }

    public Object decode(final byte[] data) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setSeparator(final String separator) {
        this.separator = separator;

    }

    public Writable encodeAsWritable(final Object o) {

        final CALLFAILURE_EVENT event = (CALLFAILURE_EVENT) o;
        final BytesRefArrayWritable writable = new BytesRefArrayWritable(5);
        int index = 0;

        writable.set(index, new BytesRefWritable(String.valueOf(event.getEVENT_ID()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getCAUSE_VALUE()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getEXTENDED_CAUSE_VALUE()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getIMSI()).getBytes()));
        writable.set(++index, new BytesRefWritable(String.valueOf(event.getDATETIME_ID()).getBytes()));

        return writable;

    }

    public Object decodeFromWritable(final Writable writable) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getSeparator() {
        return separator;
    }

}
