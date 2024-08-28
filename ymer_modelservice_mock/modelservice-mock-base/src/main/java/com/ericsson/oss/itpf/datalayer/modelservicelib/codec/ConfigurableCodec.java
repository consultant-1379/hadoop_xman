package com.ericsson.oss.itpf.datalayer.modelservicelib.codec;


public interface ConfigurableCodec<A, B> extends Codec<A, B> {

	boolean isSet(int fieldIndex);
	
	/**
	 * Should be called only during instantiation of codec.
	 * Do not call during actual processing of events.
	 */
	void set(int index, boolean value);
}
