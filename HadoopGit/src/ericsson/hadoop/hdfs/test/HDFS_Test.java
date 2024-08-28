package ericsson.hadoop.hdfs.test;

import java.io.IOException;

import org.apache.hadoop.fs.Path;

import ericsson.hadoop.hdfs.HDFS;

public class HDFS_Test {

    public static void main(String[] args) throws IOException {
	// new HDFS()
	// .addFile(
	// "C:\\Users\\esidfan\\Desktop\\2013-05-01_17-08-20__50.csv.processed",
	// "hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/2013-05-01_17-08-20__50.csv.processed");
	// new
	// HDFS().renameFile("hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/2013-05-01_17-08-20__50.csv",
	// "hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/2013-05-01_17-08-20__50.csv.processed");

	// new
	// HDFS().deleteFile("hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/TestUpload/2013-05-01_21-08-50__109.csv.processed");

	// new
	// HDFS().getModificationTime("hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/2013-05-01_17-08-20__50.csv.processed");

	// new
	// HDFS().mkdir("hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/new");

	// new HDFS()
	// .deleteFile("hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/new");

	System.out
		.println(new HDFS()
			.ifExists(new Path(
				"hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/HadoopInPractice.pdf")));

    }
}
