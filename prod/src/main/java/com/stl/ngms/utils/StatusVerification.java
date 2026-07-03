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


public class StatusVerification {

    private static String wsURL="https://login.ccavenue.com/apis/servlet/DoWebTrans";
    //Production API URL:-https://login.ccavenue.com/apis/servlet/DoWebTrans
    //Staging API URL:-https://apitest.ccavenue.com/apis/servlet/DoWebTrans
//    public static void main(String args[]) {
//    	System.out.println(verifyStatus("OND188","02"));
//    	AesCryptUtil aesUtil=new AesCryptUtil("55BDC4153DF19B4285AA9393E08BDBD5");
//        System.out.println(aesUtil.decrypt("a105a1320c2b89bafdb809dea8f441b6599245addabb8761cba0b1fe2789f11070b8d5e5e60deb10387831aea008aae020681b63f88fc34d440465840361e5c4463ed51b0e8a09dd9cb79d6ecec242b932eeb2f4180532109c186257fa7b745feab746ba12fbd3637098c66e64bf5de3b2899e7c2c9aacac1f4753e20b1e5caca45c679dde4b9422b937171397db3e1d6a6b505aa4c9c3a16e7019747a38b95683821a78ebd2a7121436e4828937c47791f5d3c972f8071424385e4d0fc75c27928e384403c73cc3977bea8412e25d2a7367bdc2c3651e7fc9b8b2eab98de0c1b8d5aec13de8a9d8d72281b6919f6a3161095486619b306af8f8ece8f2c5bbcd0a1394e061f78da241296d3cfbf9b2880c40ff1ac303e6b628c007db5fd97c8475e5ccbb39924c8700d345ed827ed931c0ac3d3f1fe9a2761947640276700c713069cb358134171f57d2c98a2f2923b7cbe49634da4c9387d1a69beaf99bbb4d864359e654e670ca4efb34ebc3cd63a3e6950f835777c6c6c665281ac7a86af50ec1bab89bfa9b1b9072321129e8e58cfe53a8c7b2da69c09535797b028916e5c5868256eeb6998720aef5cdf5bfd8e60b0e66230d6fe58a0ad84b4cbcaf1b39b6ba1651aba57aa0661270218c55ecd43e15b381005dbd348044b6d3e5458bdf9aa7540a5d2e71779a0daf7510a1d5a7f664e6f50420018f8888e382861e007cdc826f7bb4064ab64a253377e0e2e2a16584098a1e3c81433db46bf5af13025bcfb3912e3b64354733ccd1d96a8ba4b902bbc74d3c805065d0a927ff56b30cdcba190cfd8462aff2538a13aa72d30f1e1c201fbbe3cf596a5f7cc46a395dd73db32c523f1c9877f54aeabfceb0ec34aee83f88c097d74ed89ac212ad8a55b99d18b8cc8e1c7c88dd32c15921df40199dfb3c65095f16774e798e7b86aea98951ea56d5f0a966e8778252dc300f22498351577da0ad123ddf704db933535887050a70d6893e5c36837d1d283fd9330703e6c6f98d0b6fd7141e38dc24a622041fcb2cade66d654ff19e503f882cf7bbee58235e69bfe37b584ac3cd42f5092c5d2ad45454d3a2855c2915e3ee0ed5f9806716d69d1faf0a74484304d4ca06b0117465942b41bf018442724c1b79de05f73b7215fbcd63f5897fb242a533c07602e39e37807dcfddefdb73524773a4583291ceba3df78b91658939b2093a92e2986b2b1b17b801df919eb9b6963e17bd1c397fae28bf8167b885b4d51c4a909a94d376021869c797cd15d437a6ced110c3f96b5977fdecc43885bdfd445925baef26fb3f8e273205c7c666512417a8c73cc78340133322fb7a828034164337923e49cf275b4dc5a163fca24b7d9186270c1f0d9407c510fb5a1cc28bb011759164b89f13e0281056c509ccdf11ac6424697ec0d68ca785ae2321ba35c308e59d1dd38137ac9de7da4e6a9f569f1ac9f0c1ad6a6527ad3adffb4b2992975886d972a0fbe71b1bc479028de3a0a63c4cf5a341a65b772f25caacb1318e9c7764ad09bd385b04e787232363987f0aff20c7bcd3e0b16f013f60f215e10aa37170bc9fbfebd74827d6efdfb732dc952399c65b5d17498f6ee5fbdf010d1b653acca8b4"));
//    }
    public static String verifyStatus(String orderId, String blockId) {
        String data = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><Order_Status_Query order_no='" + orderId + "' reference_no=''/>";
        PGSettings pg = new PGSettings(blockId);
        String pAccessCode=pg.getAccessCode();
        String aesKey=pg.getWorkingKey();
        String pCommand="orderStatusTracker";
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
