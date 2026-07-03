package com.stl.ngms.utils;

import com.ccavenue.security.AesCryptUtil;
import com.stl.ngms.common.model.PGSettings;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class CheckPaymentStatus {

    private static String wsURL="https://login.ccavenue.com/apis/servlet/DoWebTrans";
    //Production API URL:-https://login.ccavenue.com/apis/servlet/DoWebTrans
    //Staging API URL:-https://apitest.ccavenue.com/apis/servlet/DoWebTrans
//    public static void main(String args[]) {
//    	System.out.println(verifyStatus("04"));
//    }
    public static String verifyStatus(String blockId) {
        String data = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><Order_Lookup_Query><from_date>01-01-2025</from_date></Order_Lookup_Query>";
        PGSettings pg = new PGSettings(blockId);
        String pAccessCode=pg.getAccessCode();
        String aesKey=pg.getWorkingKey();
        String pCommand="orderLookup";
        String pRequestType="xml";
        String pResponseType="json";
        String pVersion="1.2";
        return new StatusVerification().callCCavenueApi(data, pAccessCode, pCommand, aesKey, pRequestType, pResponseType, pVersion);
    }

    /**
     * Method to call ccavenue api. Output is printed on console within this method.
     * */
    public String callCCavenueApi(String pXmlData,String pAccessCode,String pCommand,String aesKey,String pRequestType,String pResponseType, String pVersion){
        String vResponse="",encXMLData="",encResXML="",decXML="";
        StringBuffer wsDataBuff=new StringBuffer();
        try {
            if(aesKey!=null && !aesKey.equals("") && pXmlData!=null && !pXmlData.equals("")){
                AesCryptUtil aesUtil=new AesCryptUtil(aesKey);
                encXMLData  = aesUtil.encrypt(pXmlData);
                System.out.println(encXMLData);
            }
            wsDataBuff.append("enc_request="+encXMLData+"&access_code="+pAccessCode+"&command="+pCommand+"&response_type="+pResponseType+"&request_type="+pRequestType+"&version="+pVersion);
            vResponse = processUrlConnectionReq(wsDataBuff.toString(), wsURL);
            if(vResponse!=null && !vResponse.equals("")){
                Map hm=tokenizeToHashMap(vResponse, "&", "=");
                encResXML = hm.containsKey("enc_response")?hm.get("enc_response").toString():"";
                String vStatus = hm.containsKey("status")?hm.get("status").toString():"";
                String vError_code = hm.containsKey("enc_error_code")?hm.get("enc_error_code").toString():"";
                if(vStatus.equals("1")){//If Api call failed
                    System.out.println("enc_response : "+ encResXML);
                    System.out.println("error_code : "+ vError_code);
                    return null;
                }
                if(!encResXML.equals("")) {
                    AesCryptUtil aesUtil=new AesCryptUtil(aesKey);
                    decXML = aesUtil.decrypt(encResXML);
                    System.out.println("enc_response : "+decXML);
                    return decXML;
                }
            }
        }
        catch (Exception e) {
            System.out.println("enc_response : "+ e.getMessage());
        }
        return null;
    }

    public static HashMap tokenizeToHashMap(String msg, String delimPairValue, String delimKeyPair){
        HashMap keyPair = new HashMap();
        ArrayList respList = new ArrayList();
        String part = "";
        StringTokenizer strTkn = new StringTokenizer(msg, delimPairValue,true);
        while (strTkn.hasMoreTokens())
        {
            part = (String)strTkn.nextElement();
            if(part.equals(delimPairValue)) {
                part=null;
            }
            else {
                String str[]=part.split(delimKeyPair, 2);
                keyPair.put(str[0], str.length>1?(str[1].equals("")?null:str[1]):null);
            }
            if(part == null) continue;
            if(strTkn.hasMoreTokens()) strTkn.nextElement();
        }
        return keyPair.size() > 0 ? keyPair : null;
    }

    public static String processUrlConnectionReq(String pBankData,String pBankUrl) throws Exception{
        URL	vUrl = null;
        URLConnection vHttpUrlConnection = null;
        DataOutputStream vPrintout = null;
        DataInputStream	vInput = null;
        StringBuffer vStringBuffer=null;
        vUrl = new URL(pBankUrl);

        if(vUrl.openConnection() instanceof HttpsURLConnection)
        {
            vHttpUrlConnection = (HttpsURLConnection)vUrl.openConnection();
        }
        else if(vUrl.openConnection() instanceof com.sun.net.ssl.HttpsURLConnection)
        {
            vHttpUrlConnection = (com.sun.net.ssl.HttpsURLConnection)vUrl.openConnection();
        } else
        {
            vHttpUrlConnection = (URLConnection)vUrl.openConnection();
        }
        vHttpUrlConnection.setDoInput(true);
        vHttpUrlConnection.setDoOutput(true);
        vHttpUrlConnection.setUseCaches(false);
        vHttpUrlConnection.connect();
        vPrintout = new DataOutputStream (vHttpUrlConnection.getOutputStream());
        vPrintout.writeBytes(pBankData);
        vPrintout.flush();
        vPrintout.close();
        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(vHttpUrlConnection.getInputStream()));
            vStringBuffer = new StringBuffer();
            String vRespData;
            while((vRespData = bufferedreader.readLine()) != null)
                if(vRespData.length() != 0)
                    vStringBuffer.append(vRespData.trim());
            bufferedreader.close();
            bufferedreader = null;
        }finally {
            if (vInput != null)
                vInput.close();
            if (vHttpUrlConnection != null)
                vHttpUrlConnection = null;
        }
        return vStringBuffer.toString();
    }

}
