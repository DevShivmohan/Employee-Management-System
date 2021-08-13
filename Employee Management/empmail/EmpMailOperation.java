package empmail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmpMailOperation extends MailService {
	public EmpMailOperation() throws Exception {
	}

	public void sendMail(String message, String recipient) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String subject = "ALPHA SOFTWARE TECHNOLOGY INDIA PRIVATE LIMITED";
					Properties properties = new Properties();
					properties.put("mail.smtp.auth", "true");
					properties.put("mail.smtp.starttls.enable", "true");
					properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
					properties.put("mail.smtp.starttls.required", "true");
					properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
					properties.put("mail.smtp.host", "smtp.gmail.com");
					properties.put("mail.smtp.port", "587");

					Session session = Session.getInstance(properties, new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(MailService.NUM1, MailService.NUM2);
						}
					});
					Message message2 = new MimeMessage(session);
					message2.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
					message2.setSubject(subject);
					message2.setFrom(new InternetAddress(MailService.NUM1));
					message2.setContent(message, "text/html");
					Transport.send(message2);
					System.out.println("Message sent");
				} catch (Exception e) {
					System.out.println("Error messaging " + e);
//                    sendMail(message, recipient);
				}
			}
		}).start();
	}

	public void sendAcknowledgementMail(File path, String recipient, String id) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String subject = "ALPHA SOFTWARE TECHNOLOGY INDIA PRIVATE LIMITED";
					Properties properties = new Properties();
					properties.put("mail.smtp.auth", "true");
					properties.put("mail.smtp.starttls.enable", "true");
					properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
					properties.put("mail.smtp.starttls.required", "true");
					properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
					properties.put("mail.smtp.host", "smtp.gmail.com");
					properties.put("mail.smtp.port", "587");
					Session session = Session.getInstance(properties, new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(MailService.NUM1, MailService.NUM2);
						}
					});
					Message message2 = new MimeMessage(session);
					message2.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
					message2.setSubject(subject);
					message2.setFrom(new InternetAddress(MailService.NUM1));
					Multipart multipart = new MimeMultipart();
					BodyPart bodyPart = new MimeBodyPart();
					bodyPart = new MimeBodyPart();
					DataSource dataSource = new FileDataSource(path.getAbsolutePath());
					bodyPart.setDataHandler(new DataHandler(dataSource));
					bodyPart.setFileName(path.getName());
					multipart.addBodyPart(bodyPart);
					message2.setContent(multipart);
					Transport.send(message2);
					System.out.println("message file sent");
				} catch (Exception e) {
					System.out.println("Error messaging file " + e);
					sendAcknowledgementMail(path, recipient, id);
				}
			}
		}).start();
	}

	public boolean sendNotice(File file, String[] recipients, String userAdmin) {
		try {
			String subject = "ALPHA SOFTWARE TECHNOLOGY INDIA PRIVATE LIMITED";
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			properties.put("mail.smtp.starttls.required", "true");
			properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(MailService.NUM1, MailService.NUM2);
				}
			});
			Message message2 = new MimeMessage(session);

			// set recipients
			for (String recipient : recipients) {
				message2.addRecipient(RecipientType.TO, new InternetAddress(recipient));
			}

			message2.setSubject(subject);
			message2.setFrom(new InternetAddress(MailService.NUM1));
			Multipart multipart = new MimeMultipart();
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent("<h3><b><u>From User " + userAdmin + "</u></b></h3>", "text/html");
			multipart.addBodyPart(bodyPart);
			bodyPart = new MimeBodyPart();
			DataSource dataSource = new FileDataSource(file.getAbsolutePath());
			bodyPart.setDataHandler(new DataHandler(dataSource));
			bodyPart.setFileName(file.getName());
			multipart.addBodyPart(bodyPart);
			message2.setContent(multipart);
			Transport.send(message2);
			file.delete(); // deleting notice file
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}