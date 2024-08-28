package com.ericsson.oss.itpf.datalayer.modelservicelib.codec;

import com.ericsson.oss.itpf.datalayer.modelservicelib.Event;

public abstract class AbstractSybaseIqCodec extends AbstractCodec<Event, byte[]> {

    private boolean isBigEndian; // whatever is common to all sybase IQ codecs

    public final boolean isBigEndian() {
        return isBigEndian;
    }

    public final void setBigEndian(final boolean isBigEndian) {
        this.isBigEndian = isBigEndian;
    }
}
