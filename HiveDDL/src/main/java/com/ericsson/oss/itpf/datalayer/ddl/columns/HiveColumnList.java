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

import java.util.ArrayList;
import java.util.List;

public class HiveColumnList<T extends HiveColumn> {

    private final List<T> columns = new ArrayList<T>();

    /**
     * @return list of columns
     */
    public List<T> getColumnList() {
        return columns;
    }

    /**
     * @return number of columns in list
     */
    public int size() {
        return columns.size();
    }

    /**
     * Adds a column to the list. If column is null, an IllegalArgumentException is thrown
     * 
     * @param testColumn
     */
    public void add(final T column) {
        if (column == null) {
            throw new IllegalArgumentException("Attempt to add null column to columns");
        } else {
            addNonNullColumn(column);
        }
    }

    /**
     * Adds column to list.
     * 
     * @param column
     */
    private void addNonNullColumn(final T column) {
        if (columnExists(column.getName())) {
            throw new IllegalArgumentException("Attempt to add column with the same name as existing column [" + column.getName() + "]");
        } else {
            columns.add(column);
        }
    }

    /**
     * Checks if column list contains column with named columnName
     * 
     * @param columnName
     *            name of column to check for
     * @return true if column list contains column with specified name, otherwise false
     */
    public boolean columnExists(final String columnName) {
        for (final T col : columns) {
            if (col.getName().equals(columnName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if column list contains any of the columns in checkList
     * 
     * @param checkList
     *            list of columns to check for
     * @return true if column list contains column with same name as any of the columns in the specified list, otherwise false
     */
    public boolean columnsExist(final HiveColumnList<? extends HiveColumn> checkList) {
        for (final HiveColumn col : checkList.getColumnList()) {
            if (columnExists(col.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Creates DDL for column list as used in create table statement.
     * 
     * @return DDL for column list including enclosing brackets, or empty string if column list is empty
     */
    public String getDDL() {
        String ddl = "";

        for (final T col : columns) {
            ddl += nextColumnDDL(ddl, col);
        }

        return wrappedDDL(ddl);
    }

    /**
     * Wraps DDL in brackets if ddl is not empty
     * 
     * @param ddl
     * @return ddl surrounded by opening and closing brackets or empty string if ddl is empty.
     */
    private String wrappedDDL(final String ddl) {
        if (ddl.isEmpty()) {
            return "";
        } else {
            return " (" + ddl + ")";
        }
    }

    /**
     * @param ddl
     * @param col
     * @return
     */
    private String nextColumnDDL(final String currentDDL, final HiveColumn nextCol) {
        if (!currentDDL.isEmpty()) {
            return ", " + nextCol.getDDL();
        } else {
            return nextCol.getDDL();
        }
    }

    /**
     * @return
     */
    public Object getSimpleDDL() {
        String ddl = "";

        for (final T col : columns) {
            ddl += nextColumnDDL(ddl, new HiveColumn(col.getName()));
        }

        return wrappedDDL(ddl);
    }
}
