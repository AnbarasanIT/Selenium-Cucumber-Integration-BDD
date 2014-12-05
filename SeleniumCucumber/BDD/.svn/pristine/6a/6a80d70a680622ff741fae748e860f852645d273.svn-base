package walgreens.ecom.batch.framework.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import walgreens.ecom.batch.framework.common.dao.TestDataDBManager;
import walgreens.ecom.batch.automation.library.common.CommonLibrary;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class RemoteShellUtils extends CommonLibrary {

    public static Map<String, Session> sessionsMap = null;

    public static String runCommand(String host, String userName, String password, String command, String searchStr, int waitTime, String passphrase, String saveFileName,
	    Map<String, String> sharedProperties) throws Exception {
	// 0 >> Didn't search
	// 1 >> Found
	// 2 >> Couldn't find
	String stringSearchStatus = "0";
	Session session = getSession(host, userName, password, passphrase);
	Channel channel = session.openChannel("exec");
	((ChannelExec) channel).setCommand(command);
	// X Forwarding
	// channel.setXForwarding(true);
	// channel.setInputStream(System.in);
	channel.setInputStream(null);
	((ChannelExec) channel).setErrStream(System.err);
	// InputStream in = channel.getInputStream();
	channel.connect(3 * 1000);
	InputStream in = channel.getInputStream();
	try {
	    Thread.sleep(waitTime * 1000);
	} catch (Exception ee) {
	    // ee.printStackTrace();
	}
	// SIVA 5-SEP-13// Changed the logic to StringBuilder insted of Bytes
	// Array
	// because of the memory issues of Bytes Array for Larger CosoleOutput.
	int intChar;
	StringBuilder output = new StringBuilder();
	while ((intChar = in.read()) != -1) {
	    output.append((char) intChar);
	}
	String consoleOutput = output.toString();
	System.out.println("consoleoutput is:" + consoleOutput);

	if (saveFileName != null && saveFileName.equalsIgnoreCase("chorderfile")) {
	    String chorderFileName = consoleOutput.split(" ")[consoleOutput.split(" ").length - 1].trim();
	    sharedProperties.put(saveFileName, chorderFileName);
	}
	if (searchStr != null) {
	    if (StringUtils.contains(consoleOutput, searchStr)) {
		stringSearchStatus = "1";
	    }
	}
	if (searchStr != null && !"1".equalsIgnoreCase(stringSearchStatus)) {
	    stringSearchStatus = "2";
	}

	if (channel.isClosed()) {
	    System.out.println("exit-status: " + channel.getExitStatus());
	}
	channel.disconnect();
	System.out.println(stringSearchStatus);
	return stringSearchStatus;
    }

    /**
     * sftp file from remote server to local. Note: (1) exception is handled
     * internally for caller convenience, check SFTP # to debug and determine
     * which step fails. (2) Be sure to provide sftp host file as first param.
     * The sftp host file is owned by system admin, ssh connection must be set
     * up before testing, get the file address from System Administrator.
     * 
     * @param sftpHostFile
     * @param host
     * @param user
     * @param pass
     * @param remoteFile
     * @param localFile
     * @return
     */
    public static boolean getFile(String host, String user, String pass, String localDir, String remoteDir, String remoteFileName, String passphrase) {
	try {
	    Session session = getSession(host, user, pass, passphrase);
	    System.out.print("\nSFTP 5: open channel");
	    Channel channel = session.openChannel("sftp");
	    System.out.print("\nSFTP 6: connect channel");
	    channel.connect();
	    System.out.print("\nSFTP 7: get sftp channel");
	    ChannelSftp sftpChannel = (ChannelSftp) channel;
	    String outputFileName = localDir + File.separator + remoteFileName;
	    System.out.print("\nSFTP 8: remote + local/output file name :" + remoteDir + File.separator + remoteFileName + "|" + outputFileName);
	    File f = new File(outputFileName);
	    System.out.print("\nSFTP 9: cd RemoteDir:" + remoteDir);
	    System.out.print("\n++current RemoteDir before cd:" + sftpChannel.pwd());
	    sftpChannel.cd(remoteDir);
	    System.out.print("\nSFTP 10: current RemoteDir:" + sftpChannel.pwd());
	    System.out.print("\nSFTP 11: get remote file start .......");
	    sftpChannel.get(remoteFileName, new FileOutputStream(f));
	    System.out.print("\nSFTP 12: get remote file done");
	    sftpChannel.exit();
	    System.out.print("\nSFTP 14: exit channel");
	    System.out.print("\nSFTP 16: get File successfully");
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    /**
     * putFile has the same signature as getFile, and same usage as getFile
     * 
     * @param sftpHostFile
     * @param host
     * @param user
     * @param pass
     * @param localFile
     * @param remoteFile
     * @return
     */
    public static boolean putFile(String host, String user, String pass, String localDir, String localFile, String remoteDir, String passphrase) {
	try {
	    // test given file exists or not at first
	    String localFileName = localDir + File.separator + localFile;
	    File lFile = new File(localFileName);
	    if (!lFile.exists()) {
		System.out.print("\nlocalFile not exists:" + localFileName);
		return false;
	    }

	    Session session = getSession(host, user, pass, passphrase);
	    System.out.print("\nSFTP 5: open channel");
	    Channel channel = session.openChannel("sftp");
	    System.out.print("\nSFTP 6: connect channel");
	    channel.connect();
	    System.out.print("\nSFTP 7: get sftp channel");
	    ChannelSftp sftpChannel = (ChannelSftp) channel;
	    System.out.print("\nSFTP 8: put the local to remote: " + localFileName + "|" + remoteDir + File.separator + localFile);
	    System.out.print("\n++current RemoteDir before cd:" + sftpChannel.pwd());
	    System.out.print("\nSFTP 9: cd RemoteDir:" + remoteDir);
	    sftpChannel.cd(remoteDir);
	    System.out.print("\nSFTP 10: current RemoteDir:" + sftpChannel.pwd());
	    sftpChannel.put(new FileInputStream(lFile), localFile);

	    System.out.print("\nSFTP 11: exit channel");
	    sftpChannel.exit();
	    System.out.print("\nSFTP 13: put File successfully");
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    public static Session getSession(String host, String userName, String password, String passPhrase) throws Exception {
	if (sessionsMap == null) {
	    sessionsMap = new HashMap<String, Session>();
	}

	if (sessionsMap.get(host) == null) {
	    JSch jsch = new JSch();
	    // RAM - 08/28/2013 - Added code to include the PRIVATE KEY as part
	    // of the login & added passPhrase for SECBT01 only for the time
	    // being.
	    if (StringUtils.containsIgnoreCase(host, "secbt01")) {
		String privKeyFile = System.getProperty("user.dir") + "\\Extensions\\id_rsa";
		jsch.addIdentity(privKeyFile, passPhrase);
	    }

	    Session session = jsch.getSession(userName, host, 22);
	    // RAM - 08/28/2013 - Commenting out password as now the
	    // authentication takes place via the public key in
	    // id_rsa/authenticated_keys2 files in secbt01
	    if (!StringUtils.containsIgnoreCase(host, "secbt01")) {
		session.setPassword(password);
	    }

	    session.setConfig("StrictHostKeyChecking", "no");
	    session.connect(30000); // making a connection with timeout.
	    sessionsMap.put(host, session);
	}

	return sessionsMap.get(host);

    }

    public static void disconnectSessions() {
	try {
	    System.out.println("####################### Disconnected Hosts: ######################### \n\n");
	    if (sessionsMap != null && sessionsMap.size() > 0) {
		Iterator<String> iter = sessionsMap.keySet().iterator();
		while (iter.hasNext()) {
		    String host = iter.next();
		    sessionsMap.get(host).disconnect();
		    sessionsMap.put(host, null);
		    System.out.println("Disconnected Host: " + host);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
