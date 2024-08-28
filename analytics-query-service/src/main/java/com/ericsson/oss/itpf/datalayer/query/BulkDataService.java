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

import java.sql.*;

import javax.ws.rs.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class BulkDataService {
	final static Logger logger = LoggerFactory.getLogger(BulkDataService.class);

	@GET
	@Path("/query")
	@Produces({ "text/plain" })
	public String executeQuery(@QueryParam(value = "sql") String sql) {
		logger.info("Executing: " + sql);
		int cols = getColumns(sql);
		StringBuilder res = new StringBuilder();
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:hive2://atclvm603.athtem.eei.ericsson.se:" + 10000
							+ "/default", "root", "shroot");
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			int rows = 0;
			res.append("<table border=\"1\">");
			while (rs.next()) {
				res.append("<tr>");
				if (rows++ > 100) {
					logger.info("truncating results...");
					res.append("<td colspan=" + cols + "/>...</td>");
					break;
				}
				for (int i = 0; i < cols; i++) {
					res.append("<td>" + rs.getString(i + 1) + "</td>");
				}
				res.append("</tr>");
			}
			res.append("</table>");
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.info("Error", e);
			return e.getMessage();
		}

		logger.info("sql:" + sql);
		return res.toString();
	}

	private int getColumns(String sql) {
		sql = sql.toLowerCase();
		int pos = sql.indexOf("select");
		pos+= "select".length();
		sql = sql.substring(pos);
		pos = sql.indexOf(" from ");
		sql = sql.substring(0, pos);
		String[] tokens = sql.split(",");
		return tokens.length;
	}
}
