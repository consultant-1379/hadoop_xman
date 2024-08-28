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
package ericsson.hadoop.hive.table;

import ericsson.hadoop.hive.table.columns.*;

public class HiveTableClusterBy {

    private static final int DEFAULT_NUMBER_OF_BUCKETS = 1;

    private HiveColumnList<HiveColumn> clusterCols = new HiveColumnList<HiveColumn>();
    private HiveColumnList<HiveSortColumn> sortCols = new HiveColumnList<HiveSortColumn>();
    private int numberOfBuckets = DEFAULT_NUMBER_OF_BUCKETS;

    public HiveTableClusterBy() {
    }

    public HiveTableClusterBy(final int numberOfBuckets) {
        setNumberOfBuckets(numberOfBuckets);
    }

    /**
     * @return the clusterCols
     */
    public HiveColumnList<HiveColumn> getClusterCols() {
        return clusterCols;
    }

    /**
     * @param clusterCols
     *            the clusterCols to set
     */
    public void setClusterCols(final HiveColumnList<HiveColumn> clusterCols) {
        if (clusterCols == null) {
            throw new IllegalArgumentException("Trying to set cluster columns to null.");
        } else {
            this.clusterCols = clusterCols;
        }
    }

    /**
     * @return the sortCols
     */
    public HiveColumnList<HiveSortColumn> getSortCols() {
        return sortCols;
    }

    /**
     * @param sortCols
     *            the sortCols to set
     */
    public void setSortCols(final HiveColumnList<HiveSortColumn> sortCols) {
        if (sortCols == null) {
            throw new IllegalArgumentException("Trying to set sort columns to null.");
        } else {
            this.sortCols = sortCols;
        }
    }

    /**
     * @return the numberOfBuckets
     */
    public int getNumberOfBuckets() {
        return numberOfBuckets;
    }

    /**
     * @param numberOfBuckets
     *            the numberOfBuckets to set
     */
    public void setNumberOfBuckets(final int numberOfBuckets) {
        if (numberOfBuckets == 0) {
            throw new IllegalArgumentException("Attempt to set 0 buckets. Should be at least 1 bucket");
        } else {
            this.numberOfBuckets = numberOfBuckets;
        }
    }

    /**
     * Builds the DDL for the cluster by part of a create table statement
     * 
     * @return the cluster by DDL if any cluster by columns are defined, otherwise an empty string
     */
    public String getDDL() {
        if (clusterCols == null || clusterCols.size() == 0) {
            return "";
        } else {
            return " CLUSTERED BY" + clusterCols.getSimpleDDL() + getSortColsDDL() + " INTO " + numberOfBuckets + " BUCKETS";
        }
    }

    /**
     * Builds the sorted by part of the cluster by clause
     * 
     * @return
     */
    private String getSortColsDDL() {
        if (sortCols == null || sortCols.size() == 0) {
            return "";
        } else {
            return " SORTED BY" + sortCols.getDDL();
        }
    }

}
