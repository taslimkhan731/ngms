package com.stl.ngms.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

public class MailUtil {
	
	public static int noOfQuickServiceThreads = 20;

	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

	private JavaMailSender mailSender;
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	/*
	 * send simple text as mail text to single recipient
	 * @author Jayant
	 */
	@Async
	public void sendSimpleMail(final String to, final String subject, final String msg) {
		// creating message
		SimpleMailMessage message = new SimpleMailMessage();
		//message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		// sending message
		mailSender.send(message);
	}
	/*
	 * send mail with markup texts to multiple recipient with multiple cc
	 * @author Jayant
	 */
	@Async
	public void sendMimeMail(final String[] to, final String[] cc,final String subject, final String msg) {
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setCc(cc);
				message.setSubject(subject);
				message.setText(msg, true);
				//message.addAttachment("CoolStuff.doc", new File("CoolStuff.doc"));
				System.out.println("sendMimeMail prepare success.");
			}
		});
	}
	/*
	 * send simple text as mail text to multiple users 
	 * @author Jayant
	 */
	@Async
	public void sendMultiSimpleMail(final String[] to, final String subject, final String msg) {
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setSubject(subject);
				message.setText(msg, true);
				//message.addAttachment("CoolStuff.doc", new File("CoolStuff.doc"));
			}
		});
	}
	/*
	 * send mail with markup texts to single recipient
	 * @author Jayant
	 */
	@Async
	public void sendMimeMail(final String to, final String subject, final String msg) {
		System.out.println("inside mail send");
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				/* MimeMessageHelper message = new MimeMessageHelper(mimeMessage);*/
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				System.out.println(to);
				System.out.println(subject);
				System.out.println(msg);
				message.setTo(to);
				message.setSubject(subject);
				message.setText(msg, true);
				//message.addAttachment("CoolStuff.doc", new File("CoolStuff.doc"));
			}
		});
	}

	/*
	 * send mail with markup texts to single recipient
	 * with attachment
	 * @author Jayant
	 */
	@Async
	public void sendMimeMailWithAttachment(final String to, final String subject, final String msg,final String nameAttachment, final DataSource attachment) {
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setSubject(subject);
				message.setText(msg, true);
				message.addAttachment(nameAttachment, attachment);
			}
		});
	}
	/*
	 * send mail with markup texts to single recipient
	 * with attachment
	 * @author Jayant using thraed
	 */
	public void sendASynchronousMailWithAttachment(final String to, final String subject, final String msg,final String nameAttachment, final DataSource attachment) throws MailException,RuntimeException{
		quickService.submit(new Runnable() {
			@Override
			public void run() {
				mailSender.send(new MimeMessagePreparator() {
					public void prepare(MimeMessage mimeMessage) throws MessagingException {
						MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						message.setTo(to);
						message.setSubject(subject);
						message.setText(msg, true);
						message.addAttachment(nameAttachment, attachment);
					}
				});
			}
		});
	}

	public void sendASynchronousMail(final String to, final String subject, final String msg) throws MailException,RuntimeException{
		quickService.submit(new Runnable() {
			@Override
			public void run() {
				mailSender.send(new MimeMessagePreparator() {
					public void prepare(MimeMessage mimeMessage) throws MessagingException {
						MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
						message.setTo(to);
						message.setSubject(subject);
						message.setText(msg, true);
					}
				});
			}
		});
	}
}