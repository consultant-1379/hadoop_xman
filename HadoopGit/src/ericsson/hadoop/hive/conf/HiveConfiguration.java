package ericsson.hadoop.hive.conf;

public class HiveConfiguration {
    //    public static final String DRIVER_NAME = "org.apache.hadoop.hive.jdbc.HiveDriver";
    //    public static final String CONNECTION_STRING = "jdbc:hive://atclvm598.athtem.eei.ericsson.se:10000/default";

    public static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver"; //org.apache.hadoop.hive.jdbc.HiveDriver
    public static final String CONNECTION_STRING = "jdbc:hive2://atclvm603.athtem.eei.ericsson.se:10000/";

    public class PrepareStatement {

        // test sql commands
        public static final String COUNT_FROM_event_e_ran_session_inter_sys_util_raw_text = "select count(*) from event_e_ran_session_inter_sys_util_raw_text";
        public static final String SELECT_LIMIT_10 = "select * from event_e_ran_session_inter_sys_util_raw_text LIMIT 10";
        public static final String SHOW_TABLES = "SHOW TABLES";
        public static final String LOAD_FILE = "";
    }
}
