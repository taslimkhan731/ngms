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
//    	System.out.println(verifyStatus("OND157","02"));
//    	AesCryptUtil aesUtil=new AesCryptUtil("55BDC4153DF19B4285AA9393E08BDBD5");
//        System.out.println(aesUtil.decrypt("a105a1320c2b89bafdb809dea8f441b67fd8edcf0a8e6afd2e66585b2f4fc1831ecd4438eefa5097efcc4cf3666d832f50fe09ecf6fb6b874dfac67af44e37ad698855a337978011b8db37b8629ee6b5f52389ba2f5eb9298cb80805ea116095aff592895fc0b9ead72c083ccc7e04b81349e760d1ebc74b2d545435b5bd2f3eb422df6110e885151af708962147b5ab645c4dc47dfe076b1caacfa4ede7622d0227714b0390e78ff9137d254cd6a9b6ea160ca3915d78bb2efc2e2738f2d8a807ec370490bddc95baadad06d51b5fa0ea616c7d427e11a34044d5af95773e5b095957add839fe89ebfe1a7c4fede6498154d0b75f450b6b2229a05662c70b0aa8dbbeb1a88098e63c77504e2ac586ae412c37ac7a280cdc8185c47bb53a72267b13768323821b926c3b1624f23df39ce7158e8789907248cfc74fc8e955f74daef3269b7b48f2b62f11a132c63039fae7f02ac5b8c87187f2e19866fd8bbafc9ff36c5e0679685ed73969f5365ad2d334026ac6b7d8ef849485b6e26ade9edbea4c45f52edc197a73dffe79553b66001e820ee5898ddecc72ddd84d945ebddaf732c9b7eca0471e7698db65799b0f8598da4efd995aed705833356de4d6c44729b13a87cdfe196203d8d15ca5b6a18e11df01d5c7d6cb1f8e4f2ebf4748b8cacc598b89171b7c45b6ceaa6c97c456d6ef59f649b537d1f8292d00c94420ca3f2278da9b6777f55faf2a97d4d6fd894aae3b278f260c4663361d717f0c751317121b6f0c05753becedef3f0af1e2811ada1f73ff7f5a71754c272dfc44db5c621913d8b2ba8963b031baec350c2d6faf8fcaf82eebe2938a7ebed4226efbc61e08bcdce2974f4b0af3446b3f89563e45874e90f267e41cf849686f0a766432d2b664816cb9af5b399259933f22b07d27491d149e7f1787c203f8017cea30097caf7a89b4d62fc3e69ff2c6de551c0c20bbf098fb860d63a5237eca8a402e3ff5688faec1165f8787acf69fcbc1e69bb2ba1168d50bb681694484b785eaee3d46c360c4393c0003b06f11fd6887db808f9a5372273628d8710a177ed459f4f98f121f185a9ede3c28d19d1e0da6e0dbacae940f34b0465c65b9c286a42bf87fac0481b3b599331a001ee71a5558e8c228ca10b79d070bde72328c670504bf59b1bd62b667b70af08b186a0b0e2f59ad93ba7a7ef6d6341d1f170a2ecb10bd13dd1dcbebc3cdb5159935d8855dd3cdbbbbcb14d1f528f42cedb78a570dcd9d58d8a54e0ca0b4dfebd6b6b3ecb6033d9d900b7808973f3dd2b30578f403a2532d2253de10059e9474054af6d48a482b04c4430cf85fd34e2a8ed9d6d4afa4e2b145204f4d617db593486d564cc16d93682c959931d4c62a26cd177341fe6a2fda83adb2769bccdb40697d07e3a3d15859e6a8f543fb449e691e6c9e978439993efba238fcef29899c33dc21ce4697db5305a4c18544deea7aca2020c291518f1edfe8f35d0643d5f3dec1684e612ea93384d0889ebd122423d398d15201b5efd078319106cdd68f5db4b0db640138c4708d6ed15a93a54008d2d74e15ee66470993d6db2e2210fdb13707ebfdb2443e263f3cd8f37e27fa829101fae2b75f4633ae"));
//    	System.out.println(verifyStatus("OND158","02"));
//    	AesCryptUtil aesUtil=new AesCryptUtil("55BDC4153DF19B4285AA9393E08BDBD5");
//        System.out.println(aesUtil.decrypt("a105a1320c2b89bafdb809dea8f441b63ad6388b58fb59018f775f89e8127941e2f9d20de50f20ad21a895f2590fd8c5ca91e6fd9eb062c339f68b9c00a17853ce54c881d964f114beea31ab60aff853c45506f233c8651ddb69fd416731f38b1f3898200d9b78f9fc1ea49de5bb88ff034510ca96667afe5668b8d848983ae628858f3ecced5af16601d12678a4361720f124c1a1117a918bca9e6adc6a9cd02835811d09afd9160de7ae901a41f66658f20bb4e2dcbc1801dc6f38b5b86fb61230289df903b26aeed678a9540ea6034d580a85fe84592dc95a71561345b27b928789bad32066ea4a79d1c20b5aecca73b8072f7322834b5ee6a6c014b9ded2b4278698b0675270535f83775d454889367df0d25ba4143090e8bea95a56b514d6a45089b8c16a63d4b746bd4dadc9e6f0e066e00c31f5550b1b3174ad06bbc1efe22822d1d3ca54cbc980f02154016e79e0e01d62d0ac435f7fcad6a164f3d6ddc5fdc8f33f9ea0cb363546223e620a96d93a75027cde84ace0cc113ff34587cbb1babbca27a484752f0ffcf3d6a313eef061e52753cf2c3d57b7d4ff51ce11aa0a77588dfce6f0d27387c861a6337a69873a5a85023b5d739e277d606f1243003b02135a8489dc88d4b5604bc48e7d46ea8bbce142f9c3b995c843b02735d363066191ad918e872244da294192ce606622e48eda019361e0f282a85d5181f605301bee7d7ec178530bf50a09863e59405c48eeba9a57b91c72ee5dd434a4938983f5237f0e0c5f2752dd75ac6abc8de5b8db6e9b53cdd4b4d59b3d5013a601e6de79fab482b831a64e339e6a18f7aaa3e3846ae636346f43b049653b1bdedfa49670820793402aed137384b309487bf7ecb2c829d7e66350b6f974574e0bf3f58ef22cfdbb6f8be18baa38a207260e5fcad4c1e2c54c62aa52083eb256c5a4af49549e976500a3bb0cae084f7638f7c0676236a8e131e6c654dd0bca29d312a44c4034dd91df418acf72d35756270e2f77703461d5a7df1e592a31315bea89c09a4392a8089e2c75da424e70d3d2634f169ad5b84617ab8d7f7c2fc2ab37e63cfa7e32738c4a6ba9cc38bcd66c8a562fcdd06e23a48a514779487f31f1c744df5e5d2c30246dc16143aafbe23b25ff5142777702cf31ac51b3e634a4a6ae8c5defa0a0a3cbad781dad20a35f7ae2600b9b7f7d87b18f2d88a548d9d29cd10a4ebb70b18fd3b4945afdcfdc32f07ae7da7964c74589aa07e82eafe52b308078de34bca8393dbae95867cf767bb04b7b334fb487802de3a3b73645e684c8e3aecd283231452ffb69149420beaa4d08ee7051ee3c8aee3b85b6514b2599da2369f9299934f734dff8f77ac39ebda8bce703276cd0238e8d1b2923138bb1c9a1116ea9a254b105f10e46e70e62890880fb018288815b29e77b652b4708cea61dfdca2690630a1ad21462dd30c7675ec2f10aa29edbb80ac342ea77843fbb509782101cdd379ab5e7eca71974c6b24fe35a9b177a314c6d50f7bc36e6cbe65177e1ac4fe282c9bc1565a2baa973a77bc4a616cb22ad993fe98a9fd47c805be6c6faa66e6d96fc86bcaf6d4de47cc21f2a78e8fdda7ffd28247d5163205fa5b069ba"));
//    	System.out.println(verifyStatus("OND159","02"));
//    	AesCryptUtil aesUtil=new AesCryptUtil("55BDC4153DF19B4285AA9393E08BDBD5");
//        System.out.println(aesUtil.decrypt("a105a1320c2b89bafdb809dea8f441b6d42a1382988c14a9a45cd74351e34e8e36a6ce15a1021c7c1211a8413d455d0effa39f6b4e7cd2c878555d2f6c114e425421fbe913d29a1345b4af86dfb2c9f5e9f88d0f0ed138a608fa7cec5fae4f55e20030c227f4d8063daa849c21d73c5793723f8a6917c1e831a274307a00a10fef7f3c5c033e80726c459ea232fe93e422de86fb2bbabc02f38bab0e66ae26c2ff331a5d0f8005de8ad604f086d886484258224247fc59618036899f908ac5b0c0786ccd7d8ea2b7d67b63bd7e99f67d2b812b59d4ac7e4ccba871ca13574442b943eb8f77bd6cce5524f5646ae2bad40b3a7d2f4510424cae554f085c7c7f4da13a89f3fcdffc6591fa30244800eccaf19583b70b1b385bec7811b50ac63b9f5146bdf1e2f88c0f18de7895e834a4a9cb5c14a53d0fce8970ca0d4584ed81b88ba8e9ba7c512bb801d6ea6f373649011b30fb3577f2772a1d2b9a460d9b0dc0a8d0c13a8e09b69ca030cef0a5bc7430d99d518dfa5f08cadbcd972507d1ec836ad923b0f6b994b58b179fac4dcc8f8802c2bf30add6ee2d89edc7fd8e1bdb4192f12a6b901ee3494fde8b0c2e642c3c8c7eaae392d4fd1a1ccf843556c54b63c884b711314dbcea3ff471ed04ab53222856e9b5e3020e293140e7452cd383920bfce1d527656dcca8ed5e74dda0dc5bfca5d088f9d5e319f9e52dab7f0bb91bfff362837ec2600d4d541579ae8c31f9020938c9afec3943c14799b2bb6fe15f55b78e6b384d4e7bad1de1435b34dbe40349bb0b82800e8ad79d610591268364c7204d32e5e4e381fd5dfd389698142fd11676f4bbda739f6daf9517c0d1e05562477c65eb2b9407c7942bd4e236d409a84a9d316dc2c896ef7dd5e6368ad3feab5f76535560116b57cf781e97ff9afabb7f62d562d6540d5f35d2ebaaeb33386e744e2648fa216be7639a7bcdea023d9cc5575f8c757a526cbd60ad52153bc93b7e3cca49d3d39825e6ff0df0f57a590a03eec728db1c88e33e9ef07ca1a80ca89b48d3999ead9f67b94de63db44aa7ffb05c2e2c56b50774fa2e9bdb0eb318130a53772a639c6b9dfc2e550d54686c7d4e83621b60f9e1835879ab3c9e30ae483bb063be281dcb88fe4916c2160863aa57e98d86141be3a17f74bea8c5ae8343ac71c1ada3b0dcac85951ba0ff6bc72d7d2464cce1c354bb03f93636d118ceed46b5d447055950acb686029a79dd9205743996a7cff46a4b070de0fbe21b6f9abb33125e4310c7bfca4c40e71d3aa0453d8e6634a094723f4da2331258507a30532f07ab5e0c0b490a3aa5ce386b4be838d859b9b9c4070cadee02bbc6fa74a0cdaa5e578eeda138c78503f2306e3657903907389a4599ad2c1178ce21fca2d8a5022a66d40515f2ea17993c7adeb3a3686666162ebc9933b4350567f9d8bd8e557c2c5a5e206cca4ed0c39daf5c1ec819e71b369a8afaa84d4021a0998e2c28b4c4459f7b3924bd20e269239f0b947b1aafbdfaaaeef40c332489d77d2914507cca53f4c6b4b7d2d2491fec440dc3cefad732858e0b7487dd30891de2f3ce6e6c08e3fb0354b2ca78bde872365ec3960b9574ee87f9c9cf18a2f2f0b62b5f"));
//    	System.out.println(verifyStatus("OND163","02"));
//    	AesCryptUtil aesUtil=new AesCryptUtil("55BDC4153DF19B4285AA9393E08BDBD5");
//        System.out.println(aesUtil.decrypt("a105a1320c2b89bafdb809dea8f441b65c03f9d6c59aaa7470f52b2e966bdbff5a1c4330bdc545bafbf84705bd800d400d868ad2b66470a9300b39e3bc2fcd41d51d64bcf74bb6b0916828e74a4d03e1833ef865029086c0f210f4a95ae39b20561b44219b81e833f95591ed9a51f3236052e71365596d19262cab7db11e07635b30b3bf3a246a7e1957926b2a63097300f059becfdee6e179cf9e48fc1c0d80f186d6d25bd35f3ad196ac55f1126cac337e4a5bde396292960f5be01f1b2852580185a22a1aa5f0cc249f8d9ed90790dd6ee4538e8c995428e87c0091c6aa54bc2655f97a1d018a98deadd3500d28d4423b6afb818d6d06289842141ec6f04201c55220e39c12eb62d3d2e1621b657a48bf3029869ad1d80627b7cc682591a0203b7fa530fe9b8106aa43b3eb2c767d1005f57d570ca70028eeb7d2a2fbacb0cd8932ea0bcc45ed0deee9fcba56f6edfc6fb8e6f092f105e12aaf504d467b2a959a811e156b81db078810afb8084d30723249bf3671053b6ad9af3976306a44f30004514a3c8f212922a370d745dad5c279f5e7059adf864f4ccaca7d4eddbf953d107f682f75e0f38f695f4a739e3856c3b5cca26e052b6b61545662a8881d3704f90eb94f6a5173cd5742b95df58a11918dd88c004ad79b950c959205c7fa11cf796dc247116919dd0bfbcdde769b33d92c523f03effde20cc2a5f120d4b01fb24ef6420b9205538084a7e302d9638b93781ba8d5718226074906dc511d6c2c8199585ff29df3ce73f09e2e0ef20410bfbd6b540a41fad3bf02bd11b8e64df8c5f3d37e58973d69f1dbe0dea3976f9a3bc11a9fed693d651ce27502432ccbc6f2b4d72b13d70ac9f6c7efdc127f8fe7e73aa3ed3c6975db99d6f9b2e321647d05caef6e07747bbb4343b6473bf0dbdb84da54af9781423588e3b76b2e86dc03faf3a87f94f960243b1bd641c42c7d86e0606cd54eeca8774b5bc18eef654e1dd730be89d29035daa966484f02b59ff94a5b708e27c29dd7a069d6982e92f5855f6d1ddc4cabe4066eedda4824e4031be4396c09890a508462e468ebd1ae60d201d7f0db00b8faf806f488b77c821c6acd292403d044c20c8d62c39bb871f51b461dc1f31bd70e660632aa240e339eeaf226f47814206e8617d49e3b36de01b8315175a976756f37a91515f15b72d7ee2265e508bc688691f04890b590555a356b85e16a189582951fd2340894d922a9cc61e710f02047753e4dbfe124fb5aeb07c80675c313bd890625d7418d03a88ad1c522c1cff6227563a7ea7b10053ba73a9db2a9cc439b3fc1748d3503f7f302188975540415163473128d49a226a1ab32b59b02c91a2d743b74188ff7e452f17065d73e26d53db6112a8bda3056e777ab83f143d825fc6a361d4ec859b880b4b6702b44f96bd44bec26d09c3f1ad731559788f2620761951bdb76e87949fbe93f6a91494aef4da195e56402215541763de1931d4a902eceae88a49353aa86ace517cd3b75be6e1d29adaa2de1e5a4e521f6ba5a5e4bffc4385fa9606a3339c0f9ece760060f501e4bd225e04e477d0e0160239c3c7f6d64978a7d6bf6a4fac1b91c5bdd6ab90f25aadb4376441194"));
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
