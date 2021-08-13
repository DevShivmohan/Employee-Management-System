package dboperation;

import java.io.File;
import java.io.FileInputStream;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import security.Encryption;
import security.SRandom;

public abstract class DBConnection extends Encryption {
	private final String protocol = "mongodb+srv";
	private final String host = "1010101_1010101_0101010";
	private final String pass = "1010101Shiv5muhi";
	private final String db = "test";
	private final String serverAdd = "cluster0.itzhy.mongodb.net";
	private final String desc = "?retryWrites=true&w=majority";
	private final String uri = protocol + "://" + host + ":" + pass + "@" + serverAdd + "/" + db + desc;
	private MongoClient mongoClientClass;

	public DBConnection() {
		try {
			MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
			mongoClientClass = mongoClient;
			System.out.println("Connection success...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			mongoClientClass.close();
		} catch (Exception e) {
		}
	}

	public MongoClient getConnection() {
		if (isSetSession())
			return mongoClientClass;
		else {
			closeConnection();
			destroySession();
			return mongoClientClass;
		}
	}

	public MongoClient getLoginConnection() {
		return mongoClientClass;
	}

	public boolean isSetSession() {
		try {
			String userName = getUserName();
			String random = getRandom();
			if (userName != null && random != null) {
				DBCursor cursor = mongoClientClass.getDB("empadmin").getCollection("empusers").find();
				while (cursor.hasNext()) {
					cursor.next();
					if (userName.equalsIgnoreCase(getAdminDecryptedData((byte[]) cursor.curr().get("username")))
							&& random.equals((String) cursor.curr().get("status"))) {
						return true;
					}
				}
				return false;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUserName() {
		try {
			byte[] key = new SRandom().getInitAdminKey().getBytes();
			FileInputStream fileInputStream = new FileInputStream(getSessionFileStructure() + "//LoginSession.shiv");
			FileInputStream fileInputStream2 = new FileInputStream(getSessionFileStructure() + "//LoginGrant.sta");
			byte[] data = getDecryptedData(fileInputStream.readAllBytes(),
					getDecryptedData(fileInputStream2.readAllBytes(), key));
			fileInputStream2.close();
			fileInputStream.close();
			String userName = new String(data);
			if (userName == null)
				return null;
			else
				return userName;
		} catch (Exception e) {
			return null;
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

	public String getRandom() {
		try {
			byte[] key = new SRandom().getInitAdminKey().getBytes();
			FileInputStream fileInputStream = new FileInputStream(getSessionFileStructure() + "//Random.shiv");
			byte[] data = fileInputStream.readAllBytes();
			fileInputStream.close();
			return getAdminDecryptedData(data);
		} catch (Exception e) {
			return null;
		}
	}

	private void destroySession() {
		try {
			File file = new File(getSessionFileStructure() + "//Random.shiv");
			file.delete();
			file = new File(getSessionFileStructure() + "//LoginSession.shiv");
			file.delete();
			file = new File(getSessionFileStructure() + "//LoginGrant.sta");
			file.delete();
			file = new File(getSessionFileStructure() + "//AdminInfo.binary");
			file.delete();
		} catch (Exception e) {
		}
	}
}