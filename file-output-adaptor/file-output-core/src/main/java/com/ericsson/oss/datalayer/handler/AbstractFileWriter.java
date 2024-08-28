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
package com.ericsson.oss.datalayer.handler;

import com.ericsson.oss.datalayer.event.Event;
import com.ericsson.oss.datalayer.util.PropertyManager;
import com.ericsson.oss.modelservice.codec.Codec;

/**
 * Base implementation of FileWriter interface
 * 
 * @param <T>
 */
public abstract class AbstractFileWriter<T> implements FileWriter<T> {

	private static final String DEFAULT_BYTE_COUNT_LIMIT = "1048576";
	private static final String DEFAULT_EVENT_COUNT_LIMIT = "5000";
	private final static long BYTE_COUNT_LIMIT = Long.parseLong(PropertyManager
			.getProperty("fileWriter.byteCountLimit", DEFAULT_BYTE_COUNT_LIMIT));
	private final static long EVENT_COUNT_LIMIT = Long
			.parseLong(PropertyManager.getProperty("fileWriter.eventCountLimit",
					DEFAULT_EVENT_COUNT_LIMIT));
	private long eventCount = 0;
	private long byteCount = 0;
	private Codec<Event, T> codec;

	/**
	 * 
	 * @return Codec used by file writer to encode events
	 */
	public final Codec<Event, T> getCodec() {
		return codec;
	}

	/**
	 * Set the Codec used by file writer to encode events
	 * 
	 * @param codec
	 */
	public final void setCodec(final Codec<Event, T> codec) {
		this.codec = codec;
	}

	/**
	 * Receive incoming events and process.
	 * Rollover file if needed.
	 * 
	 * @param event
	 */
	@Override
	public void onEvent(final Event event) {
		onEventInternal(event);
		if (shouldRollover()) {
			rollover();
		}
	}

	protected boolean shouldRollover() {
		return overByteCountLimit() || overEventCountLimit();
	}

	private boolean overEventCountLimit() {
		return eventCount > EVENT_COUNT_LIMIT;
	}

	private boolean overByteCountLimit() {
		return byteCount > BYTE_COUNT_LIMIT;
	}

	protected long getByteCount() {
		return byteCount;
	}

	protected void setByteCount(final long byteCount) {
		this.byteCount = byteCount;
	}

	protected long getEventCount() {
		return eventCount;
	}

	protected void setEventCount(final long eventCount) {
		this.eventCount = eventCount;
	}

	protected void updateStats(final long newEvents, final long newBytes) {
		eventCount += newEvents;
		byteCount += newBytes;
	}

	/**
	 * Process the event using the current codec.
	 */
	protected abstract void onEventInternal(Event event);

	/**
	 * Close the current writer and open a new one
	 */
	protected abstract void rollover();
}
