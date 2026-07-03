package com.stl.ngms.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 
 * @author R. Dash
 * @since 18-09-2017
 *
 */
@Component
@PropertySource("classpath:default_config.properties")
public class SendSMS {
	final static Logger logger = Logger.getLogger(SendSMS.class);
	@Autowired Environment env;

	public SendSMS() {
		super();
		logger.info("SmsService initialized..");
	}

	public String encryptMessage(String messg) {
		if(messg != null){
			if(!messg.isEmpty()){
				String tmp[] = messg.split(" ");
				messg = "";
				for(String item : tmp){
					messg = messg.trim() + "%20" + item.trim();
				}
				tmp = messg.split(":");
				if(tmp.length != 0){
					messg = "";
					for(String item : tmp){
						messg = messg.trim() + "%20" + item.trim();
					}
				}
			}
		}
		return messg;
	}

	@Async
	public void sendSMS(final String toMobNo, final String msgStr,final String templateID) {
		final String USER_AGENT = "Mozilla/5.0";
		// http://bulksms.mysmsmantra.com:8080/WebSMS/SMSAPI.jsp?username=mantdemo&password=1951034277&sendername=SMDEMO&mobileno=9658602048&message=HiRanvir

		// http://msdgweb.mgov.gov.in/esms/sendsmsrequest?username=orms&password=orms@2014&smsservicetype=singlemsg&mobileno=7205388748&senderid=NALORS&content=Test
		// http://msdgweb.mgov.gov.in/esms/sendsmsrequest?username=orms&password=orms@2014&smsservicetype=singlemsg&mobileno=9658602048&senderid=NALCO&content=Message%20Testing
		String mess = "";
		try {
			mess = encryptMessage(msgStr);
//			String msg = URLEncoder.encode(mess, "UTF-8");
			
			if (StringUtils.isEmpty(mess) || StringUtils.isEmpty(toMobNo)) {
				logger.info("Invalid msg or mobile no--" + toMobNo + "--" + mess);
				return;
			}
			
//			String strUrl = String.format("%s?username=%s&pin=%s&message=%s&mnumber=%s&signature=%s",
//					env.getProperty("admin.sms.url"), env.getProperty("admin.sms.username"),
//					env.getProperty("admin.sms.pass"), msg ,toMobNo, env.getProperty("admin.sms.sender"));

			//			String strUrl = String.format("%s?username=%s&password=%s&sender=%s&to=%s&message=%s",
			//					env.getProperty("admin.sms.url"), env.getProperty("admin.sms.username"),
			//					env.getProperty("admin.sms.pass"), env.getProperty("admin.sms.sender"), toMobNo, msg);
			System.out.println("*****Inside Send SMS*****");
			String strUrl = (new StringBuilder())
			.append(env.getProperty("admin.sms.url").trim())
//			.append("http://api.esms.kerala.gov.in/fastclient/SMSclient.php?username=")
			.append("?username=").append(env.getProperty("admin.sms.username").trim())
			.append("&pin=").append(env.getProperty("admin.sms.pass").trim())
			.append("&message=").append(mess).append("&mnumber=91")
			.append(toMobNo.trim()).append("&signature=").append(env.getProperty("admin.sms.sender").trim())
			.append("&dlt_entity_id=").append(env.getProperty("admin.sms.EntityID").trim())
			.append("&dlt_template_id=").append(templateID.trim()).toString();
			
			logger.info((new StringBuilder()).append("URI:--")
					.append(strUrl.toString()).toString());
			System.out.println("*****msg*****"+mess);
			URL url;
			try {
				SSLContext ssl_ctx = SSLContext.getInstance("TLS");
				TrustManager[] trust_mgr = get_trust_mgr();
				ssl_ctx.init(null, // key manager
						trust_mgr, // trust manager
						new SecureRandom()); // random number generator
				HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx
						.getSocketFactory());

				url = new URL(strUrl);
				HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
				int response = con.getResponseCode();
				System.out.println("response = "+response);
				print_content(con);

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void print_content(HttpsURLConnection con) {
		if (con != null) {
			try {
				logger.info("****** Content of the URL ********");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));

				String input;

				while ((input = br.readLine()) != null) {
					logger.info(input);
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private TrustManager[] get_trust_mgr() {
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String t) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String t) {
			}
		} };
		return certs;
	}
}