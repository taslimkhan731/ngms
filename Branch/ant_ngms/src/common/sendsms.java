package common;

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
	
		/**
		 * 
		 */
		public sendsms() {
		}

		/**
		 * 
		 * @param Mobile Number
		 * @param Message
		 * @param senderId
		 * @return
		 */
		public String SmsMessage(String mobNum, String msg, String senderID) {
			String s3 = "";
			String s4 = this.smsuserid;
			String s5 = this.smspassword;
			String senderId = "GADKER";
//			String senderId = "ODHBHA";
			//String s4 = "Distind";
//			String s5 = "qwerty123";
			try {
				msg = URLEncoder.encode(msg, "UTF-8");
				String s6 = (new StringBuilder())
						.append(this.smsurl)
//						.append("http://api.esms.kerala.gov.in/fastclient/SMSclient.php?username=")
						.append("?username=").append(s4)
						.append("&password=").append(s5)
						.append("&message=").append(msg).append("&numbers=91")
						.append(mobNum).append("&senderid=").append(senderId).toString();
				/*String s6 = (new StringBuilder())
						.append(this.smsurl)
//						.append("http://api.esms.kerala.gov.in/fastclient/SMSclient.php?username=")
						.append("?username=").append(s4)
						.append("&password=").append(s5)
						.append("&message=").append(msg).append("&numbers=")
						.append(mobNum).append("&senderid=").append(senderId).toString();*/
				
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


