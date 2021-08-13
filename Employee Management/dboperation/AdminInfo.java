package dboperation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import empmail.EmpMailOperation;
import security.Encryption;
import security.SRandom;

public class AdminInfo extends DBConnection {
	private MongoClient mongoClient;

	private String name, username, role, datetime, password, oldUserName;

	public void setName(String name) {
		this.name = name;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setOldUserName(String username) {
		this.oldUserName = username;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setDateTime(String time) {
		this.datetime = time;
	}

	public AdminInfo() {
		mongoClient = getLoginConnection();
	}

	private String osLevelInformation() {
		String info = "";
		try {
			info = info + "IP Address <b>" + Inet4Address.getLocalHost().getHostAddress() + "</b>";
		} catch (Exception e) {
		}

		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		Map<String, String> properties = runtimeMXBean.getSystemProperties();
		Set<String> keys = properties.keySet();
		for (String key : keys) {
			info = info + "<br>[" + key + "] = [" + properties.get(key) + "]";
		}
		return info;
	}

	public int checkUser(String userName, String password, String OTP) {
		try {
			Encryption encryption = new Encryption();
			DBCursor cursor = mongoClient.getDB("empadmin").getCollection("empusers").find();
			while (cursor.hasNext()) {
				cursor.next();
				byte[] uname = (byte[]) cursor.curr().get("username");
				byte[] pass = (byte[]) cursor.curr().get("password");
				if (encryption.getAdminDecryptedData(uname).equalsIgnoreCase(userName)
						&& encryption.getAdminDecryptedData(pass).equals(password)) {
					// initiating varible for hashmap
					name = encryption.getAdminDecryptedData((byte[]) cursor.curr().get("name"));
					role = encryption.getAdminDecryptedData((byte[]) cursor.curr().get("role"));
					datetime = encryption.getAdminDecryptedData((byte[]) cursor.curr().get("datetime"));
					username = userName;

					String systemInfo = osLevelInformation();
					String message = "Dear user,<br> Your One Time Password is <b>" + OTP
							+ "</b> for logging in Employee Management System application.<br><b><h3>Device Information Programmer Level<br></h3></b>"
							+ systemInfo;
					new EmpMailOperation().sendMail(message, userName.toLowerCase()); // sending OTP
					return 0;
				}
			}
			return 1;
		} catch (Exception e) {
			return 2;
		}
	}

	// checking admin user
	public int checkUser(String userName) {
		try {
			Encryption encryption = new Encryption();
			DBCursor cursor = mongoClient.getDB("empadmin").getCollection("empusers").find();
			while (cursor.hasNext()) {
				cursor.next();
				byte[] uname = (byte[]) cursor.curr().get("username");
				byte[] pass = (byte[]) cursor.curr().get("password");
				byte[] name = (byte[]) cursor.curr().get("name");
				if (encryption.getAdminDecryptedData(uname).equalsIgnoreCase(userName)) {
					String systemInfo = osLevelInformation();
					String message = "Dear <b>" + encryption.getAdminDecryptedData(name) + "</b><br> Your Username <<b>"
							+ userName.toLowerCase() + "</b>> and Password <<b>"
							+ encryption.getAdminDecryptedData(pass)
							+ "</b>> account recovered successfully.<br>Thanks for Recovery<br><b><h3>Device Information Programmer Level<br></h3></b>"
							+ systemInfo;
					new EmpMailOperation().sendMail(message, userName.toLowerCase());
					return 0;
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	public int checkUserAlready(String userName) {
		try {
			Encryption encryption = new Encryption();
			DBCursor cursor = mongoClient.getDB("empadmin").getCollection("empusers").find();
			while (cursor.hasNext()) {
				cursor.next();
				byte[] uname = (byte[]) cursor.curr().get("username");
				if (encryption.getAdminDecryptedData(uname).equalsIgnoreCase(userName)) {
					return 0;
				}
			}
			return 1;
		} catch (Exception e) {
			return 2;
		}
	}

	public boolean updateSession(String username) {
		try {
			Encryption encryption = new Encryption();
			SRandom sRandom = new SRandom();

			DBCursor cursor = mongoClient.getDB("empadmin").getCollection("empusers").find();
			while (cursor.hasNext()) {
				cursor.next();
				if (username
						.equalsIgnoreCase(encryption.getAdminDecryptedData((byte[]) cursor.curr().get("username")))) {
					String random = sRandom.getRandom();
					updateRandom(random);
					mongoClient.getDB("empadmin").getCollection("empusers").update(
							new BasicDBObject("_id", cursor.curr().get("_id")),
							new BasicDBObject("$set", new BasicDBObject("status", random)));
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	private void updateRandom(String random) {
		try {
			Encryption encryption = new Encryption();
			String path = getSessionFileStructure() + "//Random.shiv";
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			fileOutputStream
					.write(encryption.getEncryptedData(random.getBytes(), new SRandom().getInitAdminKey().getBytes()));
			fileOutputStream.flush();
			fileOutputStream.close();

		} catch (Exception e) {
		}
	}

	private String getSessionFileStructure() {
		File file = new File(System.getProperty("user.home") + "//EmployeeManagementSystem");
		file.mkdir();
		file = new File(file.getAbsolutePath() + "//EmployeeRequire");
		file.mkdir();
		file = new File(file.getAbsolutePath() + "//Session");
		file.mkdir();
		file = new File(file.getAbsolutePath() + "//LoginSession");
		file.mkdir();
		file = new File(file.getAbsolutePath() + "//UserAccess");
		file.mkdir();
		return file.getAbsolutePath();
	}

	public void setAdminInfoInHashTable() {
		try {
			File file = new File(getSessionFileStructure() + "//AdminInfo.binary");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", name);
			map.put("username", username);
			map.put("role", role);
			map.put("datetime", datetime);
			objectOutputStream.writeObject(map);
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			setAdminInfoInHashTable();
		}
	}

	public boolean updateAdminData() {
		try {
			Encryption encryption = new Encryption();
			DBCursor cursor = mongoClient.getDB("empadmin").getCollection("empusers").find();
			while (cursor.hasNext()) {
				cursor.next();
				if (oldUserName
						.equalsIgnoreCase(encryption.getAdminDecryptedData((byte[]) cursor.curr().get("username")))) {
					BasicDBObject object = new BasicDBObject();
					object.put("name", encryption.getAdminEncryptedData(name));
					object.put("username", encryption.getAdminEncryptedData(username));
					object.put("password", encryption.getAdminEncryptedData(password));

					mongoClient.getDB("empadmin").getCollection("empusers").update(
							new BasicDBObject("_id", cursor.curr().get("_id")), new BasicDBObject("$set", object));
					setAdminInfoInHashTable();
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
