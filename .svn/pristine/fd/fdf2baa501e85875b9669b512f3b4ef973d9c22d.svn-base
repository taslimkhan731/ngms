package com.stl.ngms.orrisaone.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stl.ngms.orrisaone.util.TpiAesUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TpiReqResJsonUtils {

	public static<T> String generateEncData(T reqDTO,String accessKey,String checkSumKey) throws JsonProcessingException {
		ObjectMapper mapper= new ObjectMapper();
		JsonNode node = mapper.convertValue(reqDTO, JsonNode.class);
		String hashCal = calculateCheckSum(node,checkSumKey);
		((ObjectNode) node).put("CHECKSUM", hashCal);
		//reqDTO.setChecksum(hashCal);
		return TpiAesUtil.getInstance(accessKey).encrypt(mapper.writeValueAsString(node));
	}

	public static JsonNode decryptAndReturnJsonNode(String encData,String accessKey) throws IOException {
		TpiAesUtil tpiAesUtil = TpiAesUtil.getInstance(accessKey);
		String decValue = tpiAesUtil.decrypt(encData);
		System.out.println(decValue);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readTree(decValue);
	}

	public static String calculateCheckSum(JsonNode node,String checkSumKey){
		Iterator<Entry<String, JsonNode>> fields = node.fields();
		StringBuilder hashString = new StringBuilder();
		hashString.append("495416").append("|");
		while (fields.hasNext()) {
			Entry<String, JsonNode> entry = fields.next();

			//log.debug("key --> " + entry.getKey() + " value-->" + entry.getValue());
			if(!entry.getKey().equals("CHECKSUM")) {
				hashString.append("\""+entry.getValue().asText()+"\"");
				hashString.append("|");
			}
		}
		hashString.append(checkSumKey);
		String hashCal = hashCal("SHA-512",hashString.toString());
		return hashCal;
	}

	private static final DateTimeFormatter DATE_FORMAT_yyyyMMddHHmmssSSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	
	  public static String getReqTimeStamp() {
	        LocalDateTime date = LocalDateTime.now();
			return DATE_FORMAT_yyyyMMddHHmmssSSS.format(date);
		}

	public static boolean validateCheckSum(JsonNode jsonNode,String generatedChecksum){
		String tpiCheckSum =  jsonNode.get("CHECKSUM").asText();
		System.out.println("Checksum From json request : "+tpiCheckSum);
		return tpiCheckSum.equalsIgnoreCase(generatedChecksum);
	}

	public static JsonNode convertJsonObjectToNode(JSONObject jsonObject) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonObject.toString(), JsonNode.class);
	}

	public static String convertJsonNodeToEncData(JsonNode jsonNode,String accessKey,String checkSumKey) throws JsonProcessingException {
		TpiAesUtil tpiAesUtil = TpiAesUtil.getInstance(accessKey);
		ObjectMapper objectMapper = new ObjectMapper();
	//	String json = objectMapper.writeValueAsString(jsonNode);
		// tpiAesUtil.decrypt(json);
		
	//	return tpiAesUtil.encrypt(json);
		String hashCal = calculateCheckSum(jsonNode,checkSumKey);
		((ObjectNode) jsonNode).put("CHECKSUM", hashCal);
		//reqDTO.setChecksum(hashCal);
		ObjectMapper mapper = new ObjectMapper();
		return TpiAesUtil.getInstance(accessKey).encrypt(mapper.writeValueAsString(jsonNode));
	}

	public static String hashCal(String type, String str) {
		System.out.println("#####################$$$$$$$$ TPI TEST APLICATION HASH STRING--> "+str);
		byte[] hashseq = str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte[] messageDigest = algorithm.digest();

			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1)
					hexString.append("0");
				hexString.append(hex);
			}

		} catch (NoSuchAlgorithmException nsae) {
		}
		System.out.println("Hash Calculated : "+hexString.toString());
		return hexString.toString();
	}

	public static String cancelRequest(String departmentCode,String serviceId,String subServiceId,String requestId,String timeStamp,String userName,String checksum_key,String access_key) throws IOException, JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("DEPARTEMENTID",departmentCode);
		jsonObject.put("SERVICEID",serviceId);
		jsonObject.put("SUBSERVICEID",subServiceId);
		jsonObject.put("REQUESTID",requestId);
		jsonObject.put("REQTIMESTAMP",timeStamp);
		jsonObject.put("OOUSERCODE",userName);
		jsonObject.put("CHECKSUM","");
		JsonNode node = TpiReqResJsonUtils.convertJsonObjectToNode(jsonObject);
		String checksum=  calculateCheckSum(node,checksum_key);
		jsonObject.put("CHECKSUM",checksum);
		node = TpiReqResJsonUtils.convertJsonObjectToNode(jsonObject);
		String encData = TpiReqResJsonUtils.convertJsonNodeToEncData(node,access_key,checksum_key);
		System.out.println("cancel request encData: "+encData);
		return encData;
	}


}
