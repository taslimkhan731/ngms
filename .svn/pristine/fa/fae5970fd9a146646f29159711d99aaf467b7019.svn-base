package allotment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class URLShortener {

	
	
private static final String tinyUrl = "http://tinyurl.com/api-create.php?url=";
	
	public static String shorter(String url) throws IOException {
		String tinyUrlLookup = tinyUrl + url;
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(tinyUrlLookup).openStream()));
		String tinyUrl = reader.readLine();
		return tinyUrl;
	}
	public static void main(String args[]) {
		try {
			String url=shorter("https://bhawan.homeodisha.gov.in/ngms_guest/mobilefeedback.html?request_id=P7916&mobile_no=9337417561");
			System.out.println(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
		
}
