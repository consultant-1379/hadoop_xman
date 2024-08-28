package ericsson.hadoop.hive.test;

import java.sql.SQLException;

public class HiveTest {

    public static void main(final String[] args) throws SQLException {

        HiveJdbcClient.tableExists("jb_test1");

    }
}
