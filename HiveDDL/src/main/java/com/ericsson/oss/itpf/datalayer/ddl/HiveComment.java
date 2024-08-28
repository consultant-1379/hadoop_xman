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
package com.ericsson.oss.itpf.datalayer.ddl;

public class HiveComment {

    String comment;

    /**
     * @param comment
     */
    public HiveComment(final String comment) {
        super();
        setComment(comment);
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * Returns comment for use within DDL enclosed in single quotes and prefixed with " COMMENT". Any single quotes in the comment will be escaped.
     * 
     * Returns empty string if comment is null or empty.
     * 
     * @return comment for DDL
     */
    public String getDDL() {
        if (comment == null || comment.isEmpty()) {
            return "";
        } else {
            return " COMMENT '" + escapeSingleQuotes(comment) + "'";
        }
    }

    private String escapeSingleQuotes(final String comment) {
        return comment.replace("'", "\\'");
    }

}
