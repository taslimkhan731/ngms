package com.stl.ngms.utils;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	final static Logger logger = Logger.getLogger(Utils.class);
			
	//FCM Auth KEY
	public final static String AUTH_KEY_FCM = "AAAA3FZCYrA:APA91bFc0rhnWDaQCwfI7SjvcoYwWiQmq1KibWtd3aPAnuG2U1Q7DyHW-c9I3qfKCIamMdgQrHWr056GmMP4BLKDdHLPA1GDt8VkqO3EWskBN5D3Wi6E0xjgC4SZ0dzK9Pzq5wSLxPppUNEzI7FaaVHKsHt3vo6fpw";
	public final static  String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";//URL to push data 
	public static JSONArray jsonArrayWrapper = new JSONArray();
	public final static String GROUP_API_URL_FCM = "https://android.googleapis.com/gcm/notification";
	
	
	/*
	 * Method to send SYNC Notification for a single user
	 */
	public static JSONObject pushFCMNotification(String userDeviceIdKey,String sid) throws Exception {
		logger.info("inside method to push notification");
		String authKey = AUTH_KEY_FCM;	
		String FMCurl = API_URL_FCM;    
		final String KEY_NOTIFICATION = "notification";
		final String KEY_DATA = "data";
		
		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization","key="+authKey);
		conn.setRequestProperty("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("to",userDeviceIdKey.trim());
		
		/*JSONObject notification_pay_load = new JSONObject();
		notification_pay_load.put("title", "ANQ-You Notification");   // Notification title
		notification_pay_load.put("body", data); // Notification-body
		notification_pay_load.put("click_action", "ACTIVITY_HOME");
		json.put(KEY_NOTIFICATION, notification_pay_load);*/
		
		JSONObject data_pay_load = new JSONObject();
		data_pay_load.put("sid", sid);
		json.put(KEY_DATA, data_pay_load);
		
		
		logger.info(json.toString());
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		InputStream is = conn.getInputStream();
		String strToGetLength = is.toString();
		long len = strToGetLength.length();
		JSONObject contentAsString = readIt(is, len);
		/*logger.info("### Return Value" + contentAsString);*/
		
		return 	contentAsString;	
		
	}
	
	/*
	 * Method to send MSG Notification for a single user
	 */
	
	public static JSONObject pushFCMNotificationForMsg(String userDeviceIdKey,String data) throws Exception {
		logger.info("inside method to push notification");
		String authKey = AUTH_KEY_FCM;	
		String FMCurl = API_URL_FCM;    
		final String KEY_NOTIFICATION = "notification";
		final String KEY_DATA = "data";
		logger.info("call****************************************************************************");
		
		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization","key="+authKey);
		conn.setRequestProperty("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("to",userDeviceIdKey.trim());
		
		JSONObject notification_pay_load = new JSONObject();
		notification_pay_load.put("title", "AnQ-You");   // Notification title
		notification_pay_load.put("body", data); // Notification-body
		notification_pay_load.put("click_action", "ACTIVITY_HOME");
		json.put(KEY_NOTIFICATION, notification_pay_load);
		
		/*JSONObject data_pay_load = new JSONObject();
		data_pay_load.put("sid", sid);
		json.put(KEY_DATA, data_pay_load);*/
		
		
		logger.info(json.toString());
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		InputStream is = conn.getInputStream();
		String strToGetLength = is.toString();
		long len = strToGetLength.length();
		JSONObject contentAsString = readIt(is, len);
		/*logger.info("### Return Value" + contentAsString);*/
		
		return 	contentAsString;	
		
	}
	
	/********************
	 * Read Inputstream and convert it to json object
	 ******************************/
	public static JSONObject readIt(InputStream stream, long len) throws IOException, UnsupportedEncodingException, JSONException {
	    Reader reader = null;
	    JSONObject jsonObject = null;
	    StringBuilder resServer = new StringBuilder();
	    BufferedReader buffReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
	    String line;
	    while ((line = buffReader.readLine()) != null) {
	        resServer.append(line);
	        jsonObject = new JSONObject(resServer.toString());
	    }
	    return jsonObject;
	}

	/********************************
	 *convert each array of size 20
	 ********************************/
	
	public static  void getRegistrationIdArray(List al,int listIndex){
		JSONArray jObjArray = new JSONArray();
		int i,j;
		for(i = listIndex,  j = 0; i < al.size() && j < 20 ; i++,j++ ){
			jObjArray.put(al.get(i));
		}
		
		jsonArrayWrapper.put(jObjArray);
		if(i >= al.size()){
			return;
		}
		getRegistrationIdArray(al,i);
		
	}
	
	
	/**************************************
	 * Method to create notification_key that will be used
	 * to send notification For a group of user
	 * The groupof user always<=20
	 **********************************/
	
	public static JSONObject generateGroupIdForMsgNotification(JSONArray userDeviceIdKey,String notiFicationKeyName) throws Exception{
		String serverUrl = GROUP_API_URL_FCM;
		String authKey = AUTH_KEY_FCM;
		URL url;
	    InputStream is = null;
	    JSONObject  contentAsString = new JSONObject();
	    try {//Check wheather The URL is CORRECT or not
        	logger.info(serverUrl);
            url = new URL(serverUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url: " + serverUrl);
        }
        HttpURLConnection conn = null;
        try {
        	 conn = (HttpURLConnection) url.openConnection();
        	 conn.setDoInput(true);
 	         conn.setDoOutput(true);
 	         conn.setUseCaches(false);
 	         conn.setRequestMethod("POST");
	         conn.setRequestProperty("Content-Type", "application/json");
	         conn.setRequestProperty("Authorization","key="+authKey);
	         conn.setRequestProperty("project_id", "946339996336");
	         JSONObject json = new JSONObject();
			 json.put("operation", "create");
			 json.put("notification_key_name", notiFicationKeyName);
			 json.put("registration_ids",userDeviceIdKey);
			 
			 logger.info("notiFicationKeyName"+notiFicationKeyName.toString());
			 logger.info("userDeviceIdKey"+userDeviceIdKey.toString());
			 logger.info(json.toString());
			 OutputStream out = conn.getOutputStream();
		     out.write(json.toString().getBytes("UTF-8"));
		     int status = conn.getResponseCode();
		     int len = 500;
		     if (status != 200) {
		        	InputStream is1 = conn.getErrorStream();
		            String strToGetLength = is1.toString();
		            len = strToGetLength.length();
		            contentAsString = readIt(is1, len);
		            logger.info(contentAsString);
		            throw new IOException("Post failed with error code " + status);
		        }
		     out.flush();
		     out.close();
		     is = conn.getInputStream();
		     String strToGetLength = is.toString();
		     len = strToGetLength.length();
		     contentAsString = readIt(is, len);
		     logger.info(contentAsString);
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	 if (conn != null) {
 	            conn.disconnect();
 	        }
        }
        
		return  contentAsString;
		
	}
//Generating Captcha code
	public static String generateCaptchaTextMethod1() {
		Random randomNo = new Random();
		int randomIntNo = randomNo.nextInt();
		Integer ra=randomNo.nextInt(999999);
		logger.info("jgjjhkjjjjjjjjjjjjjj"+ra);
		//String hashRandomIntNo = Integer.toHexString(randomIntNo).substring(0,6);
		String hashRandomIntNo=ra.toString();
		return hashRandomIntNo;
	}
	
	public static String generateFAid(String ogrName, String finYear, String maxId) {
		if(StringUtils.isEmpty(maxId)){
			return ogrName+"/"+finYear+"/"+"0001";
		}else{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumIntegerDigits(4);//4 character based appid
			nf.setGroupingUsed(false);
			String pidIntVal = nf.format(Double.parseDouble(Integer.toString(Integer.parseInt(maxId)+1)));
			pidIntVal = ogrName+"/"+finYear+"/" + pidIntVal;//prepend with RT-
			return pidIntVal;
		}
	}
	
	
	/**
	 * Used for ROLE Based fetch DATA
	 * @created_on: 01-09-2018
	 * @creted_by: Vamsi Krish
	 * @param groupIdLocationId
	 * @param groupName
	 * @return location ID 
	 */
	public static long getLocationId(Map<String, Object> groupIdLocationId, String groupName){
		
		long locId = 0;
		String grpName = (String) groupIdLocationId.get("group_name");// USED for Cross Check
		
		if(groupIdLocationId.get("location_id") == null){
			return 0;
		}
		if(groupName.equalsIgnoreCase("stc") && (grpName.contains("stc"))){
			locId = ((BigDecimal) groupIdLocationId.get("districtId")).longValue();
		}else if(groupName.equalsIgnoreCase("tc") && (grpName.contains("tc"))){
			locId = ((BigDecimal) groupIdLocationId.get("blockId")).longValue();
		}/*else if(groupName.equalsIgnoreCase("CLUSTER") && grpName.contains("CRCC")){
			locId = ((BigDecimal) groupIdLocationId.get("clusterId")).longValue();
		}*/else{
			locId = 0;
		}
		
//		Integer locationId = (Integer) groupIdLocationId.get("location_id");
//		String grpName = (String) groupIdLocationId.get("group_name");// USED for Cross Check
//			
//		long locId = 0;
//		String locIdString = String.valueOf(locationId);
//		
//		if(groupName.equalsIgnoreCase("BLOCK") && grpName.contains("DEO")){
//			return 0;
//		}
//		
//		if(groupName.equalsIgnoreCase("DISTRICT") && locIdString.length() >= 4){
//			locId = Long.parseLong(locIdString.substring(0, 4));
//		}else if(groupName.equalsIgnoreCase("BLOCK") && locIdString.length() >= 6){
//			locId = Long.parseLong(locIdString.substring(0, 6));
//		}else if(groupName.equalsIgnoreCase("CLUSTER") && locIdString.length() >= 10){
//			locId = Long.parseLong(locIdString.substring(0, 10));
//		}else if(groupName.equalsIgnoreCase("SCHOOL") && locIdString.length() >= 11){
//			locId = Long.parseLong(locIdString.substring(0, 11));
//		}
//		
//		if(locId == 0){
//			if(grpName.contains("DEO")){
//				locId = Long.parseLong(locIdString.substring(0, 4));
//			}else if(grpName.contains("BEO")){
//				locId = Long.parseLong(locIdString.substring(0, 6));
//			}
//		}
		
		return locId;
	}
	
	/**
	 * Used for ROLE Based fetch DATA
	 * @created_on: 01-09-2018
	 * @creted_by: Vamsi Krish
	 * @param groupIdLocationId
	 * @param groupName
	 * @return location ID 
	 */
	public static String getLocationString(Map<String, Object> groupIdLocationId, String groupName){
		
		String locName = null;
		String grpName = (String) groupIdLocationId.get("group_name");// USED for Cross Check
		
		if(groupIdLocationId.get("location_id") == null){
			return null;
		}
		if(groupName.equalsIgnoreCase("DISTRICT") && (grpName.contains("DEO") || grpName.contains("BEO") || grpName.contains("CRCC"))){
			locName = (String) groupIdLocationId.get("districtName");
		}else if(groupName.equalsIgnoreCase("BLOCK") && (grpName.contains("BEO") || grpName.contains("CRCC"))){
			locName = (String) groupIdLocationId.get("blockName");
		}else if(groupName.equalsIgnoreCase("CLUSTER") && grpName.contains("CRCC")){
			locName = (String) groupIdLocationId.get("clusterName");
		}else{
			locName = null;
		}
		return locName;
	}
	
	/****************************************************************************
	 * To get project_id sequence in project re-allocation
	 * @date 12-Sept-2018
	 * @author Raghu
	 * @param work_id 
	 ***************************************************************************/
	public static String getProjectIdSequence(String maxId, String seqfinYear, long work_id) {
		if(StringUtils.isEmpty(maxId)){
			return work_id+seqfinYear+"00001";
		}else{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumIntegerDigits(5);//5 character based projectId
			nf.setGroupingUsed(false);
			String pidIntVal = nf.format(Double.parseDouble(Integer.toString(Integer.parseInt(maxId)+1)));
			pidIntVal = work_id+seqfinYear+pidIntVal; // complete project_id
			return pidIntVal;
		}
	}
	
	/****************************************************************************
	 * To get project np_id sequence in project allocation
	 * @date 05-Oct-2018
	 * @author Raghu
	 * @param work_id,maxId,seqfinYear
	 ***************************************************************************/
	public static String getProjectNpIdSequence(String maxNpId, String seqfinYear, long work_id) {
		if(StringUtils.isEmpty(maxNpId)){
			return work_id+seqfinYear+"1";
		}else{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumIntegerDigits(1);//1 character based projectId
			nf.setGroupingUsed(false);
			String pidIntVal = nf.format(Double.parseDouble(Integer.toString(Integer.parseInt(maxNpId)+1)));
			pidIntVal = work_id+seqfinYear+pidIntVal; // complete np_id for project allocation
			return pidIntVal;
		}
	}
	/****************************************************************************
	 * To get super  project super_np_id sequence in master project
	 * @date 28-Feb-2019
	 * @author Raghu
	 * @param schemeId,superMaxNpId,seqfinYear
	 ***************************************************************************/
	public static String getSuperProjectNpIdSequence(String superMaxNpId,String seqfinYear, long schemeId) {
		if(StringUtils.isEmpty(superMaxNpId)){
			return schemeId+seqfinYear+"00001";
		}else{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumIntegerDigits(5);//5 character based projectId
			nf.setGroupingUsed(false);
			String superNpIdVal = nf.format(Double.parseDouble(Integer.toString(Integer.parseInt(superMaxNpId))));
			superNpIdVal = schemeId+seqfinYear+superNpIdVal; // complete project_id
			return superNpIdVal;
		}
	}
	
	public static String saveFileInSysPath(MultipartFile file, String directory) throws IOException {
		String filepathlist = "";
		if(!file.isEmpty()) {
			Path path = null;
			File dir = new File(directory);
			if (!dir.exists())
				dir.mkdirs();
			byte[] bytes = file.getBytes();
			path = Paths.get(directory + File.separator + file.getOriginalFilename());
			Files.write(path, bytes);
			filepathlist = path.toString();
			logger.info("Saved File Path : "+path.toString());
			System.out.println("Saved File Path : "+path.toString());
		}
		return filepathlist;
	}
	public static String saveFileInSysPathGH(MultipartFile file, String directory) throws IOException {
		String filepathlist = "";
		if(!file.isEmpty()) {
			Path path = null;
			File dir = new File(directory);
			if (!dir.exists())
				dir.mkdirs();
			byte[] bytes = file.getBytes();
			path = Paths.get(directory + File.separator + file.getOriginalFilename());
			Files.write(path, bytes);
			filepathlist = file.getOriginalFilename().toString();
			logger.info("Saved File Path : "+filepathlist.toString());
			System.out.println("Saved File Path : "+filepathlist.toString());
		}
		return filepathlist;
	}
	public static String getClientIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("X-Forwarded-For");  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_X_FORWARDED");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_FORWARDED");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("HTTP_VIA");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getHeader("REMOTE_ADDR");  
	    }  
	    if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}

	public static ArrayList<String> getCityIdListFromSession(HttpSession session) {
		ArrayList<String> cityIdList = new ArrayList<>();
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(session.getAttribute("mappedCityLocsByUser").toString());
			for(int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				cityIdList.add(jsonObject.getString("gh_id"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return cityIdList;
	}

	public static ArrayList<Map<String, Object>> getCityIdListFromJsonArrayString(String mappedCityLocsByUser) {
		ArrayList<Map<String, Object>> cityIdList = new ArrayList<>();
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(mappedCityLocsByUser);
			for(int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Map<String, Object> city = new HashMap<>();
				city.put("cityId", jsonObject.getString("gh_id"));
				city.put("cityName", jsonObject.getString("gh_name"));
				cityIdList.add(city);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return cityIdList;
	}

	// Get the list of dates between the two dates
	public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = startDate.until(endDate, ChronoUnit.DAYS);
		return IntStream.iterate(0, i -> i + 1)
				.limit(numOfDaysBetween)
				.mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}
	
	//convert numbers to words
	private static final String[] lowNames = {
			   "zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
			   "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

			private static final String[] tensNames = {
			   "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

			private static final String[] bigNames = {
			   "Thousand", "Million", "Billion"};
	public static String convertNumberToWords (int n) {
		   if (n < 0) {
		      return "minus " + convertNumberToWords(-n); }
		   if (n <= 999) {
		      return convert999(n); }
		   String s = null;
		   int t = 0;
		   while (n > 0) {
		      if (n % 1000 != 0) {
		         String s2 = convert999(n % 1000);
		         if (t > 0) {
		            s2 = s2 + " " + bigNames[t-1]; }
		         if (s == null) {
		            s = s2; }
		          else {
		            s = s2 + ", " + s; }}
		      n /= 1000;
		      t++; }
		   return s; }

		// Range 0 to 999.
		private static String convert999 (int n) {
		   String s1 = lowNames[n / 100] + " hundred";
		   String s2 = convert99(n % 100);
		   if (n <= 99) {
		      return s2; }
		    else if (n % 100 == 0) {
		      return s1; }
		    else {
		      return s1 + " " + s2; }}

		// Range 0 to 99.
		private static String convert99 (int n) {
		   if (n < 20) {
		      return lowNames[n]; }
		   String s = tensNames[n / 10 - 2];
		   if (n % 10 == 0) {
		      return s; }
		   return s + "-" + lowNames[n % 10]; }
		
		// Validate DOB for Future Dates
		public static boolean isValidDob(String dob) {
	        try {
	        	LocalDate dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd-MM-yyyy"));        	
	            if (dateOfBirth.isAfter(LocalDate.now())) {
	                return false;
	            }
	            LocalDate hundredYearsAgo = LocalDate.now().minus(100, ChronoUnit.YEARS);
	            if (dateOfBirth.isBefore(hundredYearsAgo)) {
	                return false;
	            }
	            // If both conditions pass, the DOB is valid
	            return true;
	        } catch (DateTimeParseException e) {
	            return false;
	        }
	    }

}
