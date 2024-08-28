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

public class HiveSortColumn extends HiveColumn {

    HiveSortOrder sortOrder = HiveSortOrder.ASC;

    /**
     * @param name
     *            name of column
     */
    public HiveSortColumn(final String name) {
        super(name);
    }

    /**
     * @param name
     * @param sortOrder
     */
    public HiveSortColumn(final String name, final HiveSortOrder sortOrder) {
        super(name);
        this.sortOrder = sortOrder;
    }

    /**
     * @return the sortOrder
     */
    public HiveSortOrder getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder
     *            the sortOrder to set
     */
    public void setSortOrder(final HiveSortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String getDDL() {
        return getName() + " " + getSortOrder().toString();
    }

}
