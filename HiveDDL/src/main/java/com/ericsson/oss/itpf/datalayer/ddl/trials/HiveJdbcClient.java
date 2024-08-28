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
package com.ericsson.oss.itpf.datalayer.ddl.trials;

import java.sql.*;

import org.apache.log4j.Logger;

public class HiveJdbcClient {
    /**
     * 
     */
    private static final String HIVE_URL = "jdbc:hive2://atclvm603.athtem.eei.ericsson.se:10000/";

    private static Logger logger = Logger.getLogger(HiveJdbcClient.class);
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet res;

    public HiveJdbcClient() throws ClassNotFoundException {
        loadHiveDriver();
    }

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     **/
    public static void main(final String[] args) throws ClassNotFoundException {
        final HiveJdbcClient myJob = new HiveJdbcClient();
        try {
            myJob.testConnection();
        } catch (final Exception e) {
            logger.error(e.getMessage(), e.getCause());
            e.printStackTrace();
        }

    }

    public void testConnection() throws SQLException {
        final String sql = "show tables";
        logger.info("Start HiveJob");
        execQuery(sql);
        logger.info("HiveJob executed!");
    }

    public void runStatement(final String sql) throws SQLException {
        try {
            execSql(sql);
        } catch (final SQLException e) {
            logger.error("SQL failed with " + e.getMessage());
            throw e;
        } finally {
            close();
        }
    }

    /**
     * @throws SQLException
     */
    private void connectToHiveAndCreateStatement() throws SQLException {
        con = DriverManager.getConnection(HIVE_URL, "hive", null);
        stmt = con.createStatement();
    }

    private void execSql(final String sql) throws SQLException {
        connectToHiveAndCreateStatement();
        logger.info("Hive> " + sql);
        stmt.execute(sql);
        logger.info("SQL executed successfully.");
    }

    /**
     * @param sql
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void execQuery(final String sql) throws SQLException {
        connectToHiveAndCreateStatement();
        logger.info("Hive> " + sql);
        res = stmt.executeQuery(sql);
    }

    /**
     * @throws SQLException
     */
    private void close() throws SQLException {
        stmt.close();
        con.close();
    }

    /**
     * @throws ClassNotFoundException
     * 
     */
    private void loadHiveDriver() throws ClassNotFoundException {
        Class.forName(driverName);
    }

    /**
     * @throws SQLException
     */
    private void closeResultsAndConnection() throws SQLException {
        res.close();
        close();
    }

    public boolean tableExists(final String tableName) throws SQLException {
        try {
            return queryReturnsResult("show tables like '" + tableName.toLowerCase() + "'");
        } catch (final SQLException e) {
            throw (e);
        } finally {
            closeResultsAndConnection();
        }
    }

    /**
     * @param sql
     * @return true if sql executes and returns something, otherwise false
     * @throws SQLException
     */
    private boolean queryReturnsResult(final String sql) throws SQLException {
        execQuery(sql);
        if (res.next()) {
            return true;
        } else {
            return false;
        }
    }

}