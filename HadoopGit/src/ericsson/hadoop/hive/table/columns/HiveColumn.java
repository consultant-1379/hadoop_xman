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
package ericsson.hadoop.hive.table.columns;

import ericsson.hadoop.hive.table.HiveName;

/**
 *
 */
public class HiveColumn {

    private HiveName name;

    /**
     * @param name
     *            name of column
     */
    public HiveColumn(final String name) {
        super();
        setName(name);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.getName();
    }

    /**
     * @param name
     *            the name to set
     * @throws HiveNameException
     */
    public void setName(final String name) {
        this.name = new HiveName(name);
    }

    /**
     * Create ddl for use in create statements etc
     * 
     * @return name of column
     */
    public String getDDL() {
        return getName();
    }

}