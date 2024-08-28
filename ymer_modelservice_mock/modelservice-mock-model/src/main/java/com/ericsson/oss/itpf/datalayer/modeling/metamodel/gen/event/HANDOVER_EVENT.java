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
package com.ericsson.oss.itpf.datalayer.modeling.metamodel.gen.event;

import com.ericsson.oss.itpf.datalayer.modelservicelib.Event;

/**
 * Mock handover event POJO
 */
public class HANDOVER_EVENT extends Event {

    protected short EVENT_ID = -1;
    protected short START_RNC = -1;
    protected short END_RNC = -1;
    protected int START_C_ID = -1;
    protected int END_C_ID = -1;
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
     * @return the sTART_RNC
     */
    public short getSTART_RNC() {
        return START_RNC;
    }

    /**
     * @param sTART_RNC
     *            the sTART_RNC to set
     */
    public void setSTART_RNC(final short sTART_RNC) {
        START_RNC = sTART_RNC;
    }

    /**
     * @return the eND_RNC
     */
    public short getEND_RNC() {
        return END_RNC;
    }

    /**
     * @param eND_RNC
     *            the eND_RNC to set
     */
    public void setEND_RNC(final short eND_RNC) {
        END_RNC = eND_RNC;
    }

    /**
     * @return the sTART_C_ID
     */
    public int getSTART_C_ID() {
        return START_C_ID;
    }

    /**
     * @param sTART_C_ID
     *            the sTART_C_ID to set
     */
    public void setSTART_C_ID(final int sTART_C_ID) {
        START_C_ID = sTART_C_ID;
    }

    /**
     * @return the eND_C_ID
     */
    public int getEND_C_ID() {
        return END_C_ID;
    }

    /**
     * @param eND_C_ID
     *            the eND_C_ID to set
     */
    public void setEND_C_ID(final int eND_C_ID) {
        END_C_ID = eND_C_ID;
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
