package com.ericsson.oss.itpf.datalayer.modelservicelib.codec;

import com.ericsson.oss.itpf.datalayer.modelservicelib.Event;

public abstract class AbstractTextCodec extends AbstractCodec<Event, String> {

    private String separator = ",";
    private String nullValue = "";

    public final String getSeparator() {
        return separator;
    }

    public final void setSeparator(final String separator) {
        this.separator = separator;
    }

    public final String getNullValue() {
        return nullValue;
    }

    public final void setNullValue(final String nullValue) {
        this.nullValue = nullValue;
    }

    protected final boolean isNull(final String value) {
        return getNullValue().equals(value);
    }
}
