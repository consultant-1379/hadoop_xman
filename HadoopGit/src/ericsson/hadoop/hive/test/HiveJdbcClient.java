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
package ericsson.hadoop.hive.test;

import java.sql.*;

import org.apache.log4j.Logger;

public class HiveJdbcClient {
    /**
     * 
     */
    private static final String HIVE_URL = "jdbc:hive2://atclvm603.athtem.eei.ericsson.se:10000/";
    //private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    /**
     * @param args
     * @throws SQLException
     **/
    private static Logger mLogger = Logger.getLogger(HiveJdbcClient.class);
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet res;

    public static void main(final String[] args) throws Exception {
        final HiveJdbcClient myJob = new HiveJdbcClient();
        myJob.testConnection();

        //final String tableName = "JxB_TEST1";
        //tableExists(tableName);
    }

    public void testConnection() throws Exception {
        final String sql = "show tables";

        System.out.println("Start HiveJob");

        execQuery(sql);

        System.out.println("HiveJob executed!");
    }

    public static void runStatement(final String sql) throws Exception {
        try {
            execSql(sql);
        } catch (final SQLException e) {
            throw e;
        }
        closeConnection();
    }

    /**
     * @param sql
     * @throws SQLException
     */
    private static void execQuery(final String sql) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (final ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        con = DriverManager.getConnection(HIVE_URL, "hive", null);
        stmt = con.createStatement();
        //String tableName = "testHiveDriverTable";
        // ResultSet res1 = stmt.executeQuery("create table " + tableName + " (key int, value string)");
        System.out.println("Hive> " + sql);
        res = stmt.executeQuery(sql);
    }

    private static void execSql(final String sql) throws Exception {
        try {
            Class.forName(driverName);
        } catch (final ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        con = DriverManager.getConnection(HIVE_URL, "hive", null);
        stmt = con.createStatement();
        //String tableName = "testHiveDriverTable";
        // ResultSet res1 = stmt.executeQuery("create table " + tableName + " (key int, value string)");
        System.out.println("Hive> " + sql);
        boolean sqlExecutedOK = false;
        try {
            sqlExecutedOK = stmt.execute(sql);

            System.out.println("SQL executed successfully.");
        } catch (final Exception e) {
            System.out.println("SQL failed with " + e.getMessage());
            throw e;
        } finally {
            stmt.close();
            con.close();
        }
    }

    /**
     * @param con
     * @param stmt
     * @throws SQLException
     */
    private static void closeConnection() throws SQLException {
        // close db connection
        res.close();
        stmt.close();
        con.close();
    }

    public static boolean tableExists(final String tableName) throws SQLException {
        boolean foundTable = false;
        final String tableNameLC = tableName.toLowerCase();

        final String sql = "show tables like '" + tableNameLC + "'";

        try {
            execQuery(sql);
            while (!foundTable && res.next()) {
                if (res.getString(1).toLowerCase().equals(tableNameLC)) {
                    foundTable = true;
                }
            }
        } catch (final SQLException e) {
            throw (e);
        } finally {
            closeConnection();
        }

        if (foundTable) {
            System.out.println("Table " + tableName + " exists.\n");
        } else {
            System.out.println("Table " + tableName + " does not exist.\n");
        }

        return foundTable;
    }

}