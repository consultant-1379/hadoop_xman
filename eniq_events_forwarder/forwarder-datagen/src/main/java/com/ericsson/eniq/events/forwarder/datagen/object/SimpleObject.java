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
package com.ericsson.eniq.events.forwarder.datagen.object;

import java.io.Serializable;

/**
 * this is a temp test object for all events
 * */
public class SimpleObject implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 8977522405538468524L;
    private String id;
    private String content;
    private String otherProperty;

    /**
     * 
     */
    public SimpleObject() {

    }

    /**
     * @param id
     * @param content
     * @param otherProperty
     */
    public SimpleObject(String id, String content, String otherProperty) {
	this.id = id;
	this.content = content;
	this.otherProperty = otherProperty;
    }

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
	this.id = id;
    }

    /**
     * @return the content
     */
    public String getContent() {
	return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
	this.content = content;
    }

    /**
     * @return the otherProperty
     */
    public String getOtherProperty() {
	return otherProperty;
    }

    /**
     * @param otherProperty
     *            the otherProperty to set
     */
    public void setOtherProperty(String otherProperty) {
	this.otherProperty = otherProperty;
    }

}
