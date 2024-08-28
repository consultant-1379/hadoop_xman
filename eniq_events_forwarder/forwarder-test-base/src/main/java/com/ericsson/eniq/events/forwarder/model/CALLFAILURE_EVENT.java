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
package com.ericsson.eniq.events.forwarder.model;

import java.io.Serializable;

/**
 * Mock call failure POJO
 */
public class CALLFAILURE_EVENT implements Serializable {

    protected short EVENT_ID = -1;
    protected byte CAUSE_VALUE = -1;
    protected byte EXTENDED_CAUSE_VALUE = -1;
    protected long IMSI = -1;
    protected long DATETIME_ID = -1;

    /**
     * @return the eVENT_ID
     */
    public short getEVENT_ID() {
        return EVENT_ID;
    }

    /**
     * @param eVENT_ID
     *            the eVENT_ID to set
     */
    public void setEVENT_ID(final short eVENT_ID) {
        EVENT_ID = eVENT_ID;
    }

    /**
     * @return the cAUSE_VALUE
     */
    public byte getCAUSE_VALUE() {
        return CAUSE_VALUE;
    }

    /**
     * @param cAUSE_VALUE
     *            the cAUSE_VALUE to set
     */
    public void setCAUSE_VALUE(final byte cAUSE_VALUE) {
        CAUSE_VALUE = cAUSE_VALUE;
    }

    /**
     * @return the eXTENDED_CAUSE_VALUE
     */
    public byte getEXTENDED_CAUSE_VALUE() {
        return EXTENDED_CAUSE_VALUE;
    }

    /**
     * @param eXTENDED_CAUSE_VALUE
     *            the eXTENDED_CAUSE_VALUE to set
     */
    public void setEXTENDED_CAUSE_VALUE(final byte eXTENDED_CAUSE_VALUE) {
        EXTENDED_CAUSE_VALUE = eXTENDED_CAUSE_VALUE;
    }

    /**
     * @return the iMSI
     */
    public long getIMSI() {
        return IMSI;
    }

    /**
     * @param iMSI
     *            the iMSI to set
     */
    public void setIMSI(final long iMSI) {
        IMSI = iMSI;
    }

    /**
     * @return the dATETIME_ID
     */
    public long getDATETIME_ID() {
        return DATETIME_ID;
    }

    /**
     * @param dATETIME_ID
     *            the dATETIME_ID to set
     */
    public void setDATETIME_ID(final long dATETIME_ID) {
        DATETIME_ID = dATETIME_ID;
    }
}
