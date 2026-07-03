package common;

import java.util.Date;  
import java.util.Properties;  

import javax.mail.Authenticator;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Multipart;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;  


public class SendGmail {  
	private static final String HOST = "smtp.gmail.com";     
	private static final int PORT = 587;     
	public SendGmail() {  

	}  

	public void execute(String to, String from, String password, String subject, String body) {  

		Properties properties = new Properties();  

		// Debug or not true/false  
		properties.put("mail.debug", "false");  

		// the email account on gmail  
		properties.put("mail.smtp.user", from);  

		// gmail host details  
		properties.put("mail.smtp.host", SendGmail.HOST);  
		properties.put("mail.smtp.port", SendGmail.PORT);  

		// enable authentication of STARTTLS  
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug", "true");

		Authenticator authenticator = new MyAuthenticator(from, password);  

		Session session = Session.getInstance(properties, authenticator);  

		try {  

			Transport transport = session.getTransport("smtp");  
			transport.connect();  

			Message message = new MimeMessage(session);  
			// Set from  
			message.setFrom(new InternetAddress(from));  

			// Set to  
			InternetAddress[] address = { new InternetAddress(to) };  
			message.setRecipients(Message.RecipientType.TO, address);  

			// Set subject  
			message.setSubject(subject);  

			// Set time  
			message.setSentDate(new Date());  

			// Set content  
			// part1 is email body  
			// part2 is the attachement  
			MimeBodyPart bodyPart1 = new MimeBodyPart();  
			bodyPart1.setText(body);  

			//MimeBodyPart bodyPart2 = new MimeBodyPart();  
			//FileDataSource fileDataSource = new FileDataSource(fileAttachment);  
			//bodyPart2.setDataHandler(new DataHandler(fileDataSource));  
			//bodyPart2.setFileName(fileDataSource.getName());  

			Multipart multipart = new MimeMultipart();  
			multipart.addBodyPart(bodyPart1);  
			//multipart.addBodyPart(bodyPart2);  
			message.setContent(multipart);  

			// Set complete  
			message.saveChanges();  

			// Send the message  
			transport.sendMessage(message, address);  
			transport.close();  

		}  
		catch (MessagingException e) {  
			e.printStackTrace();  
		}  

	}  

	public boolean email(String mail_id,String Content,String Subject,String attach) {  
		long start = System.currentTimeMillis();  	   
		SendGmail sendEmail = new SendGmail();  	   
		String to = mail_id;  
		//String to = "jeswinmanimala@gmail.com";
		//		String from = "gad.political.kerala@gmail.com";  //"gad.political.kerala@gmail.com";  
		String from = "udreamstl@stlindia.com";
		String password = "Techlab@2019";
		//		String password = "Budget123#";  //Budget123#  //Budget123#
		String subject = Subject;  
		String body = Content;  
		//String fileAttachment = Attachment;  
		System.out.println("to mail id   :"+to);
		System.out.println("subject   :"+Subject);
		System.out.println("body   :"+body);  
		if(to.equals("")){		

		}
		else{
			sendEmail.execute(to, from, password, subject, body);
		}
		return true;  
	}  


	//..............................Sending Multiple email.................................................................................
	/**
	 * Send email to multiple address
	 * @param mail_id array of email addess
	 * @param Subject
	 * @param Content
	 * @return
	 */
	public boolean email(String[] mail_id,String Subject,String Content) {  
		long start = System.currentTimeMillis();  

		SendGmail sendEmail = new SendGmail();  

		String[] to = mail_id;  
		String from = "gad.political.kerala@gmail.com";  //"gad.political.kerala@gmail.com";  
		String password = "Budget123#";  //Budget123#
		String subject = Subject;  
		String body = Content;  
		//String fileAttachment = Attachment;  

		sendEmail.execute(to, from, password, subject, body);  
		return true;  
	} 

	public void execute(String[] to, String from, String password, String subject, String body) {  

		Properties properties = new Properties();  

		// Debug or not true/false  
		properties.put("mail.debug", "false");  

		// the email account on gmail  
		properties.put("mail.smtp.user", from);  

		// gmail host details  
		properties.put("mail.smtp.host", SendGmail.HOST);  
		properties.put("mail.smtp.port", SendGmail.PORT);  

		// enable authentication of STARTTLS  
		properties.put("mail.smtp.auth", "true");  
		properties.put("mail.smtp.debug", "true");  
		properties.put("mail.smtp.socketFactory.port", SendGmail.PORT);  
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
		properties.put("mail.smtp.socketFactory.fallback", "false");  
		properties.put("mail.smtp.starttls.enable", "true");  

		Authenticator authenticator = new MyAuthenticator(from, password);  

		Session session = Session.getInstance(properties, authenticator);  

		try {  

			Transport transport = session.getTransport("smtp");  
			transport.connect();  

			Message message = new MimeMessage(session);  
			// Set from  
			message.setFrom(new InternetAddress(from));  

			// Set to  
			InternetAddress[] address  = new InternetAddress[to.length];  

			for(int i=0;i<to.length;i++)
			{
				address[i] = new InternetAddress(to[i]);
				//InternetAddress[] address = { new InternetAddress(to) };  
			}


			message.setRecipients(Message.RecipientType.TO, address);  

			// Set subject  
			message.setSubject(subject);  

			// Set time  
			message.setSentDate(new Date());  

			// Set content  
			// part1 is email body  
			// part2 is the attachement  
			MimeBodyPart bodyPart1 = new MimeBodyPart();  
			bodyPart1.setText(body);  

			//MimeBodyPart bodyPart2 = new MimeBodyPart();  
			//FileDataSource fileDataSource = new FileDataSource(fileAttachment);  
			//bodyPart2.setDataHandler(new DataHandler(fileDataSource));  
			//bodyPart2.setFileName(fileDataSource.getName());  

			Multipart multipart = new MimeMultipart();  
			multipart.addBodyPart(bodyPart1);  
			//multipart.addBodyPart(bodyPart2);  
			message.setContent(multipart);  

			// Set complete  
			message.saveChanges();  

			// Send the message  
			transport.sendMessage(message, address);  
			transport.close();  

		}  
		catch (MessagingException e) {  
			e.printStackTrace();  
		}  

	} 


	//...............................................................................................................

}  

class MyAuthenticator extends Authenticator {  

	protected PasswordAuthentication passwordAuthentication;  

	public MyAuthenticator(String user, String password) {  
		this.passwordAuthentication = new PasswordAuthentication(user, password);  
	}  

	protected PasswordAuthentication getPasswordAuthentication() {  
		return passwordAuthentication;  
	}  
}  