/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.itpf.datalayer.query;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class StandaloneBulkDataStoreQuery {
	private static final String HIVE_JDBC_DRIVER = "org.apache.hive.jdbc.HiveDriver";

	public static void main(String[] args) throws Exception {
		new StandaloneBulkDataStoreQuery().executeDefaultQuery();
	}

	private String host;
	private String port;
	private String sql;
	private String user;
	private String pass;
	private ResultSetPrinter printer;

	private StandaloneBulkDataStoreQuery() {
		loadDriver();
		loadProps();
	}

	void executeDefaultQuery() {
		executeQuery(sql);
	}

	void executeQuery(String query) {
		System.out.println("Executing: " + query);
		Connection conn = getConnection();
		Statement stmt;
		try {
			long start = System.currentTimeMillis();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			printer.print(rs);
			long duration = (System.currentTimeMillis() - start) / 1000; 
			System.out.println("Query executed in: " + duration + " secs");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:hive2://" + host + ":"
					+ port + "/default", user, pass);
		} catch (SQLException e) {
			System.err.println("Could not get connection to database.");
			e.printStackTrace();
			System.exit(1);
		}
		
		// cannot be reached
		return null;
	}

	private void loadDriver() {
		try {
			Class.forName(HIVE_JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load driver: " + HIVE_JDBC_DRIVER
					+ " exiting...");
			System.exit(1);
		}
	}

	private void loadProps() {
		InputStream in = getClass().getClassLoader().getResourceAsStream(
				"./com/ericsson/oss/itpf/datalayer/query/bds-config.properties");
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			System.out
					.println("Error loading proerties file! Will use default values.");
		}

		host = props.getProperty("db.host", "atclvm603.athtem.eei.ericsson.se");
		port = props.getProperty("db.port", "10000");
		user = props.getProperty("db.user", "root");
		pass = props.getProperty("db.pass", "shroot");
		sql = props.getProperty("sql", "select * from emicned_test");
		String printerProp = props.getProperty("printer");
		try {
			Class<? extends ResultSetPrinter> printerClass = (Class<? extends ResultSetPrinter>) Class.forName(printerProp);
			printer = printerClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			printer = new DefaultResultSetPrinter();
		}
	}
}
