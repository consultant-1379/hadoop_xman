package ericsson.hadoop.hive.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DebugHelper {

    /**
     * @throws SQLException
     * 
     * @Description Print resultset on screen
     * */
    public static void PrintResultSet(ResultSet res) throws SQLException {
	ResultSetMetaData mdata = res.getMetaData();
	int columnCount = mdata.getColumnCount();

	// print data schema
	for (int i = 1; i <= columnCount; i++) {
	    System.out.print(mdata.getColumnName(i) + " ");
	}
	System.out.print("\n");

	// print data
	while (res.next()) {
	    for (int i = 1; i <= columnCount; i++) {
		System.out.print(String.valueOf(res.getObject(i)) + " ");
	    }

	    System.out.print("\n");
	}
    }
}
