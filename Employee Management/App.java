import java.awt.Image;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import empmail.MailService;
import security.Encryption;

public class App extends InfosysLogin {
	App() throws Exception {
		// fetch();
		// String id="20201000000110000000";
		// String filter=""+id.substring(0,12);
		// System.out.println(filter);
		// readMailMessages();
		// readSent();
		// sendMail("Hi Shivmohan kaise ho","nshivmohan08@gmail.com");
		// 12sendAcknowledgementMail(new
		// File("C://Users//DELL//Downloads//Acknowledgement.pdf"),"nshivmohan08@gmail.com","EMP202010000002");
		// getEmployeeRegistrationAcknowledgement();
		// createIndustryIcon();
		// uploadDeveloperData();
		// createDeveloperFile();//extends AboutDevelopers
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new App();
		// System.out.println("CWD ath---"+System.getProperty("user.dir"));
	}

	private void createDeveloperFile() {
		try {
			Encryption encryption = new Encryption();

			String muhiintro = "Hi my self Sunita Devi ,\nI am from Khaga Fatehpur - 212655\nWe are currently persoing diploma in Computer Science and Engg.\nOur team are developed a windows software that is Employee Management System in Minor Project Work.\nIn this project i have build some features.\nSo many intresting project and building light weighted GUI (Graphical User Interface) and best security provided by team Encryption in back-end level.\n\tContact Details\nEmail :- nshivmohan08@gmail.com.\n \t\tThank You";
			String alokmaniintro = "Hi my self Alok Mani ,\nI am from Ajhuwa Kaushambi - 212217\nWe are currently persoing diploma in Computer Science and Engg.\nOur team are developed a windows software that is Employee Management System in Minor Project Work.\nIn this project i have build some features.\nSo many intresting project and building light weighted GUI (Graphical User Interface) and best security provided by team Encryption in back-end level.\n\tContact Details\nEmail :- alokmani101@gmail.com.\n \t\tThank You";
			String ayushintro = "Hi my self Ayush Tripathi ,\nI am from Khaga Fatehpur - 212655\nWe are currently persoing diploma in Computer Science and Engg.\nOur team are developed a windows software that is Employee Management System in Minor Project Work.\nIn this project i have build some features.\nSo many intresting project and building light weighted GUI (Graphical User Interface) and best security provided by team Encryption in back-end level.\n\tContact Details\nEmail :- ayushtripathi2126@gmail.com.\n \t\tThank You";
			String mhaintro = "Hi my self Mahima Devi ,\nI am from Khaga Fatehpur - 212655\nWe are currently persoing diploma in Computer Science and Engg.\nOur team are developed a windows software that is Employee Management System in Minor Project Work.\nIn this project i have build some features.\nSo many intresting project and building light weighted GUI (Graphical User Interface) and best security provided by team Encryption in back-end level.\n\tContact Details\nEmail :- nshivmohan08@gmail.com.\n \t\tThank You";
			String prinkaintro = "Hi my self Priyanka Gupta ,\nI am from Ajhuwa Kaushambi - 212217\nWe are currently persoing diploma in Computer Science and Engg.\nOur team are developed a windows software that is Employee Management System in Minor Project Work.\nIn this project i have build some features.\nSo many intresting project and building light weighted GUI (Graphical User Interface) and best security provided by team Encryption in back-end level.\n\tContact Details\nEmail :- nshivmohan08@gmail.com.\n \t\tThank You";
			String shivintro = "Hi my self Shivmohan ,\nI am from Siddharth Nagar - 272206\nWe are currently persoing diploma in Computer Science and Engg.\nOur team are developed a windows software that is Employee Management System in Minor Project Work.\nIn this project i have build some features.\nSo many intresting project and building light weighted GUI (Graphical User Interface) and best security provided by team Encryption in back-end level.\n\tContact Details\nEmail :- nshivmohan08@gmail.com.\n \t\tThank You";
			String shivaniintro = "Hi my self Shivani Maurya ,\nI am from Khaga Fatehpur - 212655\nWe are currently persoing diploma in Computer Science and Engg.\nOur team are developed a windows software that is Employee Management System in Minor Project Work.\nIn this project i have build some features.\nSo many intresting project and building light weighted GUI (Graphical User Interface) and best security provided by team Encryption in back-end level.\n\tContact Details\nEmail :- nshivmohan08@gmail.com.\n \t\tThank You";
			String vipulintro = "Hi my self Vipul Kumar ,\nI am from Varansi - 221002\nWe are currently persoing diploma in Computer Science and Engg.\nOur team are developed a windows software that is Employee Management System in Minor Project Work.\nIn this project i have build some features.\nSo many intresting project and building light weighted GUI (Graphical User Interface) and best security provided by team Encryption in back-end level.\n\tContact Details\nEmail :- vipulkumarjagdishpur221002@gmail.com.\n \t\tThank You";

			// String[]
			// photos={"home.jpeg","employee.png","talent.png","financial.png","benefit.png","about.png","setting.png","accounting.png","attendance.png"};
			String[] photoNames = { "user.png", "alokmani.jpg", "ayush.jpg", "user.png", "user.png", "shiv.jpg",
					"user.png", "vipul.jpg" };
			String[] bioKeys = { "5muhiimg", "alokmaniimg", "ayushimg", "mhaimg", "prinkaimg", "shivimg", "shivaniimg",
					"vipulimg" };
			String[] introKeys = { "5muhiintro", "alokmaniintro", "ayushintro", "mhaintro", "prinkaintro", "shivintro",
					"shivaniintro", "vipulintro" };
			String[] introDatas = { muhiintro, alokmaniintro, ayushintro, mhaintro, prinkaintro, shivintro,
					shivaniintro, vipulintro };

			// File file=new
			// File("C://Users//DELL//EmployeeManagementSystem//EmployeeRequire//Session");
			FileOutputStream fileOutputStream = new FileOutputStream(
					"F://UploadDeveloperData//DeveloperBioInfo.binary");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			FileOutputStream fileOutputStream2 = new FileOutputStream(
					"F://UploadDeveloperData//DeveloperDataInfo.binary");
			ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream2);

			HashMap<String, ImageIcon> photomap = new HashMap<String, ImageIcon>();
			HashMap<String, String> intromap = new HashMap<String, String>();
			for (int i = 0; i < 8; i++) {
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("F://UploadDeveloperData//" + photoNames[i])
						.getImage().getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING));
				System.out.println(imageIcon);
				photomap.put(bioKeys[i], imageIcon);
				intromap.put(introKeys[i], encryption.getDeveloperEncryptedData(introDatas[i]));

			}
			System.out.println("Before executed");
			objectOutputStream.writeObject(photomap);
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
			System.out.println("After executed");

			objectOutputStream2.writeObject(intromap);
			objectOutputStream2.flush();
			objectOutputStream2.close();
			fileOutputStream2.close();
			System.out.println("Done Successfully");
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	public void createIndustryIcon() {
		try {
			com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("F://UploadDeveloperData//logo.jpg");
			HashMap<String, com.itextpdf.text.Image> map = new HashMap<>();
			map.put("logo", image);
			FileOutputStream fileOutputStream = new FileOutputStream("F://UploadDeveloperData//logo.binary");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(map);
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
			System.out.println("Done Created logo file");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readMailMessages() {
		try {
			Properties properties = new Properties();
			// properties.put("mail.pop3.auth","true");
			properties.put("mail.pop3.starttls.enable", "true");
			properties.put("mail.pop3.host", "pop.gmail.com");
			properties.put("mail.pop3.port", "995");

			Session session = Session.getInstance(properties);
			Store store = session.getStore("pop3s");
			store.connect("smtp.gmail.com", MailService.NUM1, MailService.NUM2);
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);

			Message[] messages = folder.getMessages();
			System.out.println("Messages length " + messages.length);
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				String recipient = message.getFrom()[0].toString();
				System.out.println("----------------------");
				System.out.println(message.getSubject());
				System.out.println(recipient);
				recipient = recipient.substring(recipient.lastIndexOf("<") + 1, recipient.lastIndexOf(">"));
				System.out.println(recipient);
				// messages[i].setFlag(Flags.Flag.DELETED,true);
				// System.out.println("Messages deleted");
			}
			// folder.close(true);
			// store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readSent() {
		try {
			Properties properties = new Properties();
			// properties.put("mail.pop3.auth","true");
			properties.put("mail.pop3.starttls.enable", "true");
			properties.put("mail.pop3.host", "pop.gmail.com");
			properties.put("mail.pop3.port", "995");

			Session session = Session.getInstance(properties);
			Store store = session.getStore("pop3s");
			store.connect("smtp.gmail.com", MailService.NUM1, MailService.NUM2);
			Folder[] folder = store.getDefaultFolder().list();
			System.out.println("Length----" + folder.length);
			for (Folder folder2 : folder) {
				System.out.println(folder2.getFullName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}