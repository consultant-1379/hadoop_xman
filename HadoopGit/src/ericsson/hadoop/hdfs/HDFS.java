package ericsson.hadoop.hdfs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import ericsson.hadoop.hdfs.conf.Confs;

public class HDFS {

    /**
     * Rename a file
     * 
     * @param fromthis
     *            old file name
     * @param tothis
     *            new filename
     * */
    public void renameFile(String fromthis, String tothis) throws IOException {
	Configuration conf = new Configuration();

	FileSystem fs = FileSystem.get(URI.create(fromthis), conf);
	Path fromPath = new Path(fromthis);
	Path toPath = new Path(tothis);

	if (!(fs.exists(fromPath))) {
	    System.out.println("No such destination " + fromPath);
	    return;
	}

	if (fs.exists(toPath)) {
	    System.out.println("Already exists! " + toPath);
	    return;
	}

	try {
	    boolean isRenamed = fs.rename(fromPath, toPath);
	    if (isRenamed) {
		System.out.println("Renamed from " + fromthis + "to " + tothis);
	    }
	} catch (Exception e) {
	    System.out.println("Exception :" + e);
	    System.exit(1);
	} finally {
	    fs.close();
	}

	System.out.println("File renamed.");

    }

    /**
     * Upload or add a file to HDFS from client local directory
     * 
     * @param source
     *            local file path
     * @param dest
     *            file path on HDFS. i.e.
     *            hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/file1.txt
     * */
    public void addFile(String source, String dest) throws IOException {

	// Conf object will read the HDFS configuration parameters
	Configuration conf = new Configuration();

	FileSystem fs = FileSystem.get(URI.create(dest), conf);

	InputStream in = new BufferedInputStream(new FileInputStream(source));

	OutputStream out = fs.create(new Path(dest), new Progressable() {

	    @Override
	    public void progress() {
		System.out.print(".");
	    }
	});

	IOUtils.copyBytes(in, out, Confs.FILE_WRITE_BUFFER_SIZE, true);

	fs.close();
	System.out.println("Completed");
    }

    /**
     * Delete a file from HDFS
     * 
     * @param file
     *            file path on HDFS. i.e.
     *            hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/file1.txt
     * */
    public void deleteFile(String file) throws IOException {
	Configuration conf = new Configuration();
	FileSystem fs = FileSystem.get(URI.create(file), conf);

	Path path = new Path(file);
	if (!fs.exists(path)) {
	    System.out.println("File " + file + " does not exists");
	    return;
	}

	fs.delete(new Path(file), true);

	fs.close();

	System.out.println("File deleted.");
    }

    /**
     * Get modification time of a file in HDFS
     * 
     * @throws IOException
     * @param source
     *            file path on HDFS. i.e.
     *            hdfs://atclvm598.athtem.eei.ericsson.se/user/admin/file1.txt
     * */
    public void getModificationTime(String source) throws IOException {

	Configuration conf = new Configuration();

	FileSystem fileSystem = FileSystem.get(URI.create(source), conf);
	Path srcPath = new Path(source);

	// Check if the file already exists
	if (!(fileSystem.exists(srcPath))) {
	    System.out.println("No such destination " + srcPath);
	    return;
	}
	// Get the filename out of the file path
	String filename = source.substring(source.lastIndexOf('/') + 1,
		source.length());

	FileStatus fileStatus = fileSystem.getFileStatus(srcPath);
	long modificationTime = fileStatus.getModificationTime();

	fileSystem.close();
	System.out.println("File:" + filename + "; Modification time: "
		+ new SimpleDateFormat().format(modificationTime)
		+ " File status: " + fileStatus.getPermission());

    }

    /**
     * @throws IOException
     * 
     * @Description Get file permission
     * @param source
     *            File path on HDFS
     * 
     * @return String
     **/
    public String getPermissionStatus(String source) throws IOException {

	Configuration conf = new Configuration();

	FileSystem fileSystem = FileSystem.get(URI.create(source), conf);
	Path srcPath = new Path(source);

	// Check if the file already exists
	if (!(fileSystem.exists(srcPath))) {
	    System.out.println("No such destination " + srcPath);
	    return null;
	}

	FileStatus fileStatus = fileSystem.getFileStatus(srcPath);

	fileSystem.close();
	return fileStatus.getPermission().toString();

    }

    /**
     * @throws IOException
     * 
     * @Description Get file replication
     * @param source
     *            File path on HDFS
     * 
     * @return String
     **/
    public String getFileReplication(String source) throws IOException {

	Configuration conf = new Configuration();

	FileSystem fileSystem = FileSystem.get(URI.create(source), conf);
	Path srcPath = new Path(source);

	// Check if the file already exists
	if (!(fileSystem.exists(srcPath))) {
	    System.out.println("No such destination " + srcPath);
	    return null;
	}

	FileStatus fileStatus = fileSystem.getFileStatus(srcPath);

	fileSystem.close();
	return String.valueOf(fileStatus.getReplication());

    }

    /**
     * @throws IOException
     * 
     * @Description Get file block size in B
     * @param source
     *            File path on HDFS
     * 
     * @return String
     **/
    public String getFileBlockSize(String source) throws IOException {

	Configuration conf = new Configuration();

	FileSystem fileSystem = FileSystem.get(URI.create(source), conf);
	Path srcPath = new Path(source);

	// Check if the file already exists
	if (!(fileSystem.exists(srcPath))) {
	    System.out.println("No such destination " + srcPath);
	    return null;
	}

	FileStatus fileStatus = fileSystem.getFileStatus(srcPath);

	fileSystem.close();
	return String.valueOf(fileStatus.getBlockSize());

    }

    /**
     * @throws IOException
     * 
     * @Description Get file user group
     * @param source
     *            File path on HDFS
     * 
     * @return String
     **/
    public String getFileUserGroup(String source) throws IOException {

	Configuration conf = new Configuration();

	FileSystem fileSystem = FileSystem.get(URI.create(source), conf);
	Path srcPath = new Path(source);

	// Check if the file already exists
	if (!(fileSystem.exists(srcPath))) {
	    System.out.println("No such destination " + srcPath);
	    return null;
	}

	FileStatus fileStatus = fileSystem.getFileStatus(srcPath);

	fileSystem.close();
	return fileStatus.getGroup();

    }

    /**
     * @throws IOException
     * 
     * @Description Get file owner
     * @param source
     *            File path on HDFS
     * 
     * @return String
     **/
    public String getFileOwner(String source) throws IOException {

	Configuration conf = new Configuration();

	FileSystem fileSystem = FileSystem.get(URI.create(source), conf);
	Path srcPath = new Path(source);

	// Check if the file already exists
	if (!(fileSystem.exists(srcPath))) {
	    System.out.println("No such destination " + srcPath);
	    return null;
	}

	FileStatus fileStatus = fileSystem.getFileStatus(srcPath);

	fileSystem.close();
	return fileStatus.getOwner();

    }

    /**
     * Create a new directory in HDFS
     * 
     * @param dir
     *            directory name on HDFS
     * */
    public void mkdir(String dir) throws IOException {
	Configuration conf = new Configuration();

	FileSystem fileSystem = FileSystem.get(URI.create(dir), conf);

	Path path = new Path(dir);
	if (fileSystem.exists(path)) {
	    System.out.println("Dir " + dir + " already exists!");
	    return;
	}

	fileSystem.mkdirs(path);

	fileSystem.close();

	System.out.println("Directory created.");
    }

    /**
     * Read a file from HDFS
     * 
     * @param HDFSfile
     *            file path on HDFS
     * @param _localDirectory
     *            file path on local directory. If _localDirectory is null, then
     *            the path will be set as default directory
     * */
    public void readFile(String HDFSfile, String _localDirectory)
	    throws IOException {
	Configuration conf = new Configuration();

	FileSystem fileSystem = FileSystem.get(URI.create(HDFSfile), conf);

	Path path = new Path(HDFSfile);
	if (!fileSystem.exists(path)) {
	    System.out.println("File " + HDFSfile + " does not exists");
	    return;
	}

	FSDataInputStream in = fileSystem.open(path);

	// create file at local directory. Modify to customize local directory
	String filename = _localDirectory;
	if (_localDirectory == null) {
	    filename = HDFSfile.substring(HDFSfile.lastIndexOf('/') + 1,
		    HDFSfile.length());
	}

	OutputStream out = new BufferedOutputStream(new FileOutputStream(
		new File(filename)));

	byte[] b = new byte[Confs.FILE_READ_BUFFER_SIZE];
	int numBytes = 0;
	while ((numBytes = in.read(b)) > 0) {
	    out.write(b, 0, numBytes);
	}

	in.close();
	out.close();
	fileSystem.close();

	System.out.println("File read completed.");
    }

    /**
     * @Description check if file exists
     * @return boolean true, if exists; false if not exists.
     * */
    public boolean ifExists(Path source) throws IOException {

	Configuration conf = new Configuration();

	FileSystem fileSystem = FileSystem.get(URI.create(source.toString()),
		conf);
	boolean isExists = fileSystem.exists(source);

	fileSystem.close();
	return isExists;
    }

}
