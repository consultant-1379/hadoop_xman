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
package com.ericsson.oss.itpf.datalayer.ddl.table;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ericsson.oss.itpf.datalayer.ddl.columns.*;
import com.ericsson.oss.itpf.datalayer.ddl.table.HiveClusterBy;


public class HiveClusterByTest {

    private static final HiveColumn TEST_CLUSTER1 = new HiveColumn("Cluster1");
    private static final HiveColumn TEST_CLUSTER2 = new HiveColumn("Cluster2");
    private static final HiveColumn TEST_CLUSTER3 = new HiveColumn("Cluster3");
    private static final HiveColumnList<HiveColumn> singleClusterColList = new HiveColumnList<HiveColumn>();
    private static final HiveColumnList<HiveColumn> multipleClusterColList = new HiveColumnList<HiveColumn>();

    private static final HiveSortColumn TEST_SORT1 = new HiveSortColumn("Sort1");
    private static final HiveSortColumn TEST_SORT2 = new HiveSortColumn("Sort2", HiveSortOrder.DESC);
    private static final HiveSortColumn TEST_SORT3 = new HiveSortColumn("Sort3", HiveSortOrder.ASC);
    private static final HiveColumnList<HiveSortColumn> singleSortColList = new HiveColumnList<HiveSortColumn>();
    private static final HiveColumnList<HiveSortColumn> multipleSortColList = new HiveColumnList<HiveSortColumn>();

    @BeforeClass
    public static void setUpClass() {
        singleClusterColList.add(TEST_CLUSTER1);

        multipleClusterColList.add(TEST_CLUSTER1);
        multipleClusterColList.add(TEST_CLUSTER2);
        multipleClusterColList.add(TEST_CLUSTER3);

        singleSortColList.add(TEST_SORT1);

        multipleSortColList.add(TEST_SORT1);
        multipleSortColList.add(TEST_SORT2);
        multipleSortColList.add(TEST_SORT3);
    }

    @Test
    public void testConstructorDefaultBuckets() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        assertEquals(1, clusterBy.getNumberOfBuckets());
        assertEquals(0, clusterBy.getClusterCols().size());
        assertEquals(0, clusterBy.getSortCols().size());
    }

    @Test
    public void testConstructorWithBucket() {
        final HiveClusterBy clusterBy = new HiveClusterBy(1);
        assertEquals(1, clusterBy.getNumberOfBuckets());
        assertEquals(0, clusterBy.getClusterCols().size());
        assertEquals(0, clusterBy.getSortCols().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithZeroBuckets() {
        new HiveClusterBy(0);
    }

    @Test
    public void testSetGetNumberOfBuckets() {
        final HiveClusterBy clusterBy = new HiveClusterBy(1);
        assertEquals("Number of buckets should be 1 to start off", 1, clusterBy.getNumberOfBuckets());
        clusterBy.setNumberOfBuckets(7);
        assertEquals(7, clusterBy.getNumberOfBuckets());
    }

    @Test
    public void testSetGetClusterCols() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        clusterBy.setClusterCols(multipleClusterColList);
        HiveColumnList<HiveColumn> checkCols = clusterBy.getClusterCols();
        assertEquals(multipleClusterColList, checkCols);

        clusterBy.setClusterCols(singleClusterColList);
        checkCols = clusterBy.getClusterCols();
        assertEquals(singleClusterColList, checkCols);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetClusterColsNull() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        clusterBy.setClusterCols(null);
    }

    @Test
    public void testSetGetSortCols() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        clusterBy.setSortCols(multipleSortColList);
        HiveColumnList<HiveSortColumn> checkCols = clusterBy.getSortCols();
        assertEquals(multipleSortColList, checkCols);

        clusterBy.setSortCols(singleSortColList);
        checkCols = clusterBy.getSortCols();
        assertEquals(singleSortColList, checkCols);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSortColsNull() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        clusterBy.setSortCols(null);
    }

    @Test
    public void testGetDDLNoClustering() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        final String expected = "";
        assertEquals(expected, clusterBy.getDDL());

    }

    @Test
    public void testGetDDLNoSort() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        clusterBy.setClusterCols(multipleClusterColList);
        String expected = " CLUSTERED BY (Cluster1, Cluster2, Cluster3) INTO 1 BUCKETS";
        assertEquals(expected, clusterBy.getDDL());

        clusterBy.setNumberOfBuckets(5);
        expected = " CLUSTERED BY (Cluster1, Cluster2, Cluster3) INTO 5 BUCKETS";
        assertEquals(expected, clusterBy.getDDL());

        clusterBy.setClusterCols(singleClusterColList);
        expected = " CLUSTERED BY (Cluster1) INTO 5 BUCKETS";
        assertEquals(expected, clusterBy.getDDL());
    }

    @Test
    public void testGetDDLWithSort() {
        final HiveClusterBy clusterBy = new HiveClusterBy();
        clusterBy.setClusterCols(multipleClusterColList);
        clusterBy.setSortCols(multipleSortColList);
        String expected = " CLUSTERED BY (Cluster1, Cluster2, Cluster3) SORTED BY (Sort1 ASC, Sort2 DESC, Sort3 ASC) INTO 1 BUCKETS";
        assertEquals(expected, clusterBy.getDDL());

        clusterBy.setSortCols(singleSortColList);
        expected = " CLUSTERED BY (Cluster1, Cluster2, Cluster3) SORTED BY (Sort1 ASC) INTO 1 BUCKETS";
        assertEquals(expected, clusterBy.getDDL());
    }
}
