package com.stl.ngms.orrisaone.model;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuarksConstants {

    public static final String EXCEPTION_OCCURRED = "{} -- Exception Occurred -- {}";

    // TPI Req/Res Keys
    public static final String REQSTATUSCODE = "REQSTATUSCODE";
    public static final String MSSSAGE = "MSSSAGE";
    public static final String DEPARTEMENTID = "DEPARTEMENTID";
    public static final String SERVICEID = "SERVICEID";
    public static final String SUBSERVICEID = "SUBSERVICEID";
    public static final String REQUESTID = "REQUESTID";
    public static final String REQTIMESTAMP = "REQTIMESTAMP";
    public static final String OOUSERCODE = "OOUSERCODE";
    public static final String ORIGINEDFROMOO = "ORIGINEDFROMOO";
    public static final String CHECKSUM = "CHECKSUM";

    public static final String APPLICATIONID = "APPLICATIONID";
    public static final String APPLICATIONSTATUS = "APPLICATIONSTATUS";
    public static final String TRANSACTIONID = "TRANSACTIONID";
    public static final String AMOUNT = "AMOUNT";
    public static final String POSTPAYMENTURL = "POSTPAYMENTURL";
    public static final String OOUSERTOKEN = "OOUSERTOKEN";
    public static final String ADDITIONALPARA1 = "ADDITIONALPARA1";
    public static final String ADDITIONALPARA2 = "ADDITIONALPARA2";
//    private static final DateTimeFormatter DATE_FORMAT_yyyyMMddHHmmssSSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
//    public static String getReqTimeStamp() {
//        LocalDateTime date = LocalDateTime.now();
//        return DATE_FORMAT_yyyyMMddHHmmssSSS.format(date);
//    }
}