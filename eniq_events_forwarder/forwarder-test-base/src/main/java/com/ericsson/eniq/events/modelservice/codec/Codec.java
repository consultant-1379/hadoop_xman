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
package com.ericsson.eniq.events.modelservice.codec;

import org.apache.hadoop.io.Writable;

/**
 * interface for the codec(encode&decode)
 */
public interface Codec {

    String encodeAsString(Object o);

    Object decodeFromString(String string);

    byte[] encode(Object o);

    Object decode(byte[] data);

    Writable encodeAsWritable(Object o);

    Object decodeFromWritable(Writable writable);

    void setSeparator(String separator);

    String getSeparator();

}
