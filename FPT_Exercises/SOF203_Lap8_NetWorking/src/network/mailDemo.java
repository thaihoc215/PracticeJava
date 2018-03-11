package network;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailDemo {

	public static void main(String[] args) {

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// turn off the secure app email
		// https://myaccount.google.com/lesssecureapps
		// turn of 2 verification google https://myaccount.google.com/

		Properties config = new Properties();
		config.put("mail.smtp.host", "smtp.gmail.com");
		config.put("mail.smtp.port", "465");
		config.put("mail.smtp.debug", "true");
		config.put("mail.smtp.auth", "true");

		//
		config.put("mail.smtp.socketFactory.port", "465");
		config.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		config.put("mail.smtp.socketFactory.fallback", "false");

		config.put("mail.smtp.starttls.enable", "true");
		config.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		config.put("mail.store.protocol", "pop3");
		config.put("mail.transport.protocol", "smtp");

		Authenticator authen = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				String email = "bboyfool215@gmail.com";
				// String password = "hiphopordie215";
				String password = "bboyfool215315";
				return new PasswordAuthentication(email, password);
			}
		};

		String fromName = "Hà Nguyễn Thái Học";
		String fromEmail = "bboyfool215@gmail.com";
		String toEmails = "thaihoc2105@gmail.com,bboyfool215@gmail.com";// send multiple email
		String subject = "java test send multiple email";
		String body = "email sent";

		Session session = Session.getInstance(config, authen);
		session.setDebug(true);
		MimeMessage mail = new MimeMessage(session);

		InternetAddress sender;
		try {
			sender = new InternetAddress(fromEmail, fromName, "utf-8");
			mail.setFrom(sender);
			mail.setReplyTo(new InternetAddress[] { sender });
			mail.setRecipients(Message.RecipientType.TO, toEmails);// set danh sach nguoi nhan
			mail.setSubject(subject, "utf-8");
			mail.setContent(body, "text/html; charset=utf-8");
			mail.setSentDate(new Date());
			mail.saveChanges();

			Transport.send(mail);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
