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
package com.ericsson.oss.itpf.datalayer.ddl.columns;

import com.ericsson.oss.itpf.datalayer.ddl.HiveComment;

public class HiveTypedColumn extends HiveColumn {

    private HiveDataType dataType;
    HiveComment comment;

    /**
     * @param name
     *            Cannot be null or empty. If an invalid name is supplied, IllegalArgumentException will be thrown.
     * @param dataType
     */
    public HiveTypedColumn(final String name, final HiveDataType dataType) {
        this(name, dataType, null);
    }

    /**
     * @param name
     *            Cannot be null or empty. If an invalid name is supplied, IllegalArgumentException will be thrown.
     * @param dataType
     * @param comment
     */
    public HiveTypedColumn(final String name, final HiveDataType dataType, final String comment) {
        super(name);
        setDataType(dataType);
        setComment(comment);
    }

    /**
     * @return the dataType
     */
    public HiveDataType getDataType() {
        return dataType;
    }

    /**
     * @param dataType
     *            the dataType to set
     */
    public void setDataType(final HiveDataType dataType) {
        if (dataType == null) {
            throw new IllegalArgumentException("DataType cannot be null");
        }
        this.dataType = dataType;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment.getComment();
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(final String comment) {
        this.comment = new HiveComment(comment);
    }

    /**
     * Builds DDL for the column as it would appear in a create table statement.
     * 
     * @return DDL for column
     */
    @Override
    public String getDDL() {
        return getName() + " " + getDataType().toString() + comment.getDDL();
    }
}
