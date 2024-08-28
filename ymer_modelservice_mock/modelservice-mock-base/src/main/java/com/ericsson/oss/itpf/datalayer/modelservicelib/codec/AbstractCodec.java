package com.ericsson.oss.itpf.datalayer.modelservicelib.codec;

public abstract class AbstractCodec<A, B> implements ConfigurableCodec<A, B> {

    private boolean[] flags;

    public AbstractCodec() {
        this(null);
    }

    public AbstractCodec(final boolean[] flags) {
        this.flags = flags;
    }

    public final void setFlags(final boolean[] flags) {
        this.flags = flags;
    }

    public final boolean isSet(final int fieldIndex) {
        return flags != null && flags[fieldIndex];
    }

    public final void set(final int index, final boolean value) {
        flags[index] = value;
    }
}
