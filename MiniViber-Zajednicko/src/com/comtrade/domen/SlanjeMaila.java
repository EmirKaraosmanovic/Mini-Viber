package com.comtrade.domen;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class SlanjeMaila {

	private String adminEmail = "miniviberemir@gmail.com"; 
	private String adminPass = "miniViber15";
	private String host = "smtp.gmail.com";
	private String port = "465";

	public boolean posaljiMail(String primalac, String subject, String poruka) {

		Properties props = new Properties();
		props.put("mail.smtp.user", adminEmail);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(poruka);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(adminEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(primalac));
			msg.saveChanges();

			Transport transport = session.getTransport("smtp");
			transport.connect(host, adminEmail, adminPass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
