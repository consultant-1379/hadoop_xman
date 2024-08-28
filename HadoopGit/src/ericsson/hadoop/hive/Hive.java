package ericsson.hadoop.hive;

import java.sql.*;

import org.apache.log4j.Logger;

import ericsson.hadoop.hive.conf.HiveConfiguration;

public class Hive {
    public Connection con;
    public Statement stmt;
    public ResultSet res;
    private final Logger mLogger = Logger.getLogger(Hive.class);

    /**
     * Initialize database connection
     * 
     * @throws SQLException
     *             , CLassNotFoundException
     * */
    public void openDB() throws SQLException, ClassNotFoundException {
        Class.forName(HiveConfiguration.DRIVER_NAME);
        con = DriverManager.getConnection(HiveConfiguration.CONNECTION_STRING, "hive", "");
    }

    /**
     * Class constructor, call DB initializer
     * */
    public Hive() throws ClassNotFoundException, SQLException {
        openDB();
    }

    /**
     * Execute query operation.
     * 
     * @param: _sql: SQL String
     * @return: ResultSet
     * */
    public ResultSet executeQuery(final String _sql) throws SQLException {
        stmt = con.createStatement();
        return stmt.executeQuery(_sql);
    }

    /**
     * Import file from local(remote host file system) into hive table
     * 
     * @param _filePath
     *            local file path.
     * @param _tableName
     *            : name of the table in Hive.
     * @param _isOverwrite
     *            : true: Overwrite existing data in the table. false: Append data at the end of the table.
     * */
    public void loadFileFromLocal(final String _filePath, final String _tableName, final boolean _isOverwrite) throws SQLException {

        String sql;
        stmt = con.createStatement();
        if (_isOverwrite) {
            sql = "LOAD DATA LOCAL INPATH '" + _filePath + "' OVERWRITE INTO TABLE " + _tableName;
        } else {
            sql = "LOAD DATA LOCAL INPATH '" + _filePath + "' INTO TABLE " + _tableName;
        }
        System.out.println("Running: " + sql); // debug output
        res = stmt.executeQuery(sql);
    }

    /**
     * Import file from HDFS into hive table
     * 
     * @param _filePath
     *            file path from HDFS.
     * 
     * @param _tableName
     *            table name in the Hive.
     * 
     * @param _isOverwrite
     *            true: Overwrite existing data in the table. false: Append data at the end of the table.
     * 
     * */
    public void loadFileFromHDFS(final String _filePath, final String _tableName, final boolean _isOverwrite) throws SQLException {

        stmt = con.createStatement();
        String sql;
        if (_isOverwrite) {
            sql = "LOAD DATA INPATH '" + _filePath + "' OVERWRITE INTO TABLE " + _tableName;
        } else {
            sql = "LOAD DATA INPATH '" + _filePath + "' INTO TABLE " + _tableName;
        }
        System.out.println("Running: " + sql); // debug output
        res = stmt.executeQuery(sql);
    }

    /**
     * @throws SQLException
     * @Description execute non-query SQL
     * */
    public void nonQueryExecute(final String _sql) throws SQLException {

        System.out.println("Initialize " + _sql);
        stmt = con.createStatement();
        stmt.execute(_sql);
        System.out.println("Job completed.");
    }

    /**
     * Display tables from HDFS
     * 
     * @throws SQLException
     * */
    public void showTable() throws SQLException {
        res = null;
        stmt = con.createStatement();
        res = stmt.executeQuery(HiveConfiguration.PrepareStatement.SHOW_TABLES);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }

    /**
     * @throws SQLException
     * 
     * @Description Close database connection
     * */
    public void closeDB() throws SQLException {
        if (res != null) {
            res.close();
        }

        if (stmt != null) {
            stmt.close();
        }

        if (con != null) {
            con.close();
        }
    }
}
