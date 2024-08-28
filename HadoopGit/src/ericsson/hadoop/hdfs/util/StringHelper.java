package ericsson.hadoop.hdfs.util;

public class StringHelper {

    /**
     * Get the filename out of the file path
     * 
     * @return String filename
     * */
    public static String getFileNameFromPath(String _path) {
	return _path.substring(_path.lastIndexOf('/') + 1, _path.length());
    }
}
