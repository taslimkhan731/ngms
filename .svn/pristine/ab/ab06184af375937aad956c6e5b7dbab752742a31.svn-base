/*package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class sendsms {


	public String smsurl ="";
	public String smsuserid ="";
	public String smspassword ="";

	public sendsms(String smsurl,String smsuserid, String smspassword)

	{
	this.smsurl = smsurl;
	this.smsuserid = smsuserid;
	this.smspassword = smspassword;

	}

 *//**
 * 
 *//*
		public sendsms() {
		}

  *//**
  * 
  * @param Mobile Number
  * @param Message
  * @param senderId
  * @return
  *//*
		public String SmsMessage(String mobNum, String msg, String senderID) {
			String s3 = "";
			String s4 = this.smsuserid;
			String s5 = this.smspassword;
//			String senderId = "GADKER";
			String senderId = "ODHBHA";
			//String s4 = "Distind";
			//String s5 = "qwerty123";
			try {
				msg = URLEncoder.encode(msg, "UTF-8");
				String s6 = (new StringBuilder())
						.append(this.smsurl)
//						.append("http://api.esms.kerala.gov.in/fastclient/SMSclient.php?username=")
						.append("?username=").append(s4)
						.append("&pin=").append(s5)
						.append("&message=").append(msg).append("&mnumber=91")
						.append(mobNum).append("&signature=").append(senderId).toString();
				String s6 = (new StringBuilder())
						.append(this.smsurl)
//						.append("http://api.esms.kerala.gov.in/fastclient/SMSclient.php?username=")
						.append("?username=").append(s4)
						.append("&password=").append(s5)
						.append("&message=").append(msg).append("&numbers=")
						.append(mobNum).append("&senderid=").append(senderId).toString();

				System.out.println((new StringBuilder()).append("URI:--")
						.append(s6).toString());
				HttpURLConnection httpurlconnection = (HttpURLConnection) new URL(s6).openConnection();
				s3 = httpurlconnection.getResponseMessage();
				int i = httpurlconnection.getResponseCode();
				System.out.println((new StringBuilder()).append("Response:--")
						.append(s3).toString());
				System.out.println((new StringBuilder())
						.append("Response Length:--").append(s3.length())
						.toString());
				System.out.println((new StringBuilder()).append("CODE:").append(i)
						.toString());
				if (i == 200)
					httpurlconnection.disconnect();
			} catch (Exception exception) {
				System.out.println((new StringBuilder()).append("Error:--")
						.append(exception.toString()).toString());
			}
			return s3;
		}
	}


   */


package test;
/* Author Syed Taha owais */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class sendsms {
	public String smsurl ="";
	public String smsuserid ="";
	public String smspassword ="";
	public sendsms() {
	}
	public sendsms(String smsurl,String smsuserid, String smspassword)

	{
	this.smsurl = smsurl;
	this.smsuserid = smsuserid;
	this.smspassword = smspassword;

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

	public void SmsMessage(String mobNum, String msg, String senderID,String templateId) {
		String senderId = "BHAODH";
		String entityId="1101466490000045271";
//		String senderId = "GADKER";
		String s4 = this.smsuserid;
		String s5 = this.smspassword;
		String https_url = (new StringBuilder())
				.append(this.smsurl.trim())
//				.append("http://api.esms.kerala.gov.in/fastclient/SMSclient.php?username=")
				.append("username=").append(s4.trim())
				.append("&pin=").append(s5.trim())
				.append("&message=").append(msg).append("&mnumber=91")
				.append(mobNum.trim()).append("&signature=").append(senderId.trim())
				.append("&dlt_entity_id=").append(entityId.trim())
				.append("&dlt_template_id=").append(templateId.trim()).toString();
				
//		String https_url = (new StringBuilder())
//				.append(this.smsurl)
////				.append("http://api.esms.kerala.gov.in/fastclient/SMSclient.php?username=")
//				.append("?username=").append(s4)
//				.append("&password=").append(s5)
//				.append("&message=").append(msg).append("&numbers=")
//				.append(mobNum).append("&senderid=").append(senderId).toString();
		System.out.println((new StringBuilder()).append("URI:--")
				.append(https_url.toString()).toString());
//		String https_url = "https://smsgw.sms.gov.in/failsafe/HttpLink??username=<USERNAME>&pin=<PASSWORD>&&message=Hello&mnumber=9968261034&signature=NICSMS";
		URL url;
		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, // key manager
					trust_mgr, // trust manager
					new SecureRandom()); // random number generator
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx
					.getSocketFactory());

			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
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
	}

	private void print_content(HttpsURLConnection con) {
		if (con != null) {

			try {

				System.out.println("****** Content of the URL ********");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));

				String input;

				while ((input = br.readLine()) != null) {
					System.out.println(input);
				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}