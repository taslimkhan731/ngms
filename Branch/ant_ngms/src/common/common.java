package common;
import java.awt.MenuItem;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;






import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.itextpdf.text.log.SysoLogger;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;




//import java.util.GregorianCalendar
import db.*;

public class common {
	ngm NGM = null;
	m_menu_item    ins_menu=null;
	public common(ngm p_ngm) {
		NGM = p_ngm;
		ins_menu=new m_menu_item(p_ngm);
		
	}
	
	public String getRESTYPE(String ptype)
	{
		String rettype ="";
			if(ptype.equals("R"))
			{
				rettype ="Room";
			}
			if(ptype.equals("BL"))
			{
				rettype ="Bed For Ladies";
			}
			if(ptype.equals("BG"))
			{
				rettype ="Bed For Gents";
			}
			if(ptype.equals("DG"))
			{
				rettype ="TH For Gents";
			}
			if(ptype.equals("DL"))
			{
				rettype ="TH For Ladies";
			}
			if(ptype.equals("B"))
			{
				rettype ="Bed";
			}
			if(ptype.equals("D"))
			{
				rettype ="TH";
			}
		return rettype;
	}
	
//	public String getQuotaDescription(String ptype)
//	{
//		String rettype ="";
//			if(ptype.equals("SPO"))
//			{
//				rettype ="SPO Quota";
//			}
//			else if(ptype.equals("GEN"))
//			{
//				rettype ="General Quota";
//			}
//			else if(ptype.equals("RES"))
//			{
//				rettype ="Reservation Quota";
//			}
//			else if(ptype.equals("PUBLIC"))
//			{
//				rettype ="Public Quota";
//			}
//			else if(ptype.equals("WAIT"))
//			{
//				rettype ="Waiting Quota";
//			}
//			else{
//				rettype = "";
//			}
//		return rettype;
//	}
	
	public String getQuotaDescription(String ptype) throws SQLException
	{
		String rettype ="";
		String sql="select quota_desc from quota where quota_type=?";
		PreparedStatement ps=NGM.conn.prepareStatement(sql);
		ps.setString(1,ptype);
		System.out.println("pssss "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			rettype = rs.getString("quota_desc");
		}
		return rettype;
	}
	
	public String getStatusDescription(String status)
	{
		String rettype ="";
			if(status.equals("REQ"))
			{
				rettype ="REQUESTED";
			}
			else if(status.equals("MOD"))
			{
				rettype ="MODERATED";
			}
			else if(status.equals("PRO"))
			{
				rettype ="PROCESSED";
			}
			else if(status.equals("CAN"))
			{
				rettype ="CANCELLED";
			}
			else if(status.equals("ALO"))
			{
				rettype ="ALLOTED";
			}
			else if(status.equals("CIN"))
			{
				rettype ="CHECKED IN";
			}
			else if(status.equals("COUT"))
			{
				rettype ="CHECKED OUT";
			}
			else if(status.equals("PCIN"))
			{
				rettype ="PARTIALLY CHECKED IN";
			}
			else if(status.equals("PCOUT"))
			{
				rettype ="PARTIALLY CHECKED OUT";
			}
			else if(status.equals("BILL"))
			{
				rettype ="BILL PREPARED";
			}
			else if(status.equals("RECT"))
			{
				rettype ="RECEIPT PREPARED";
			}
			else if(status.equals("WMOD"))
			{
				rettype ="WAITING LIST";
			}
			else if(status.equals("CNF"))
			{
				rettype ="CONFIRMED";
			}
			else{
				rettype = "";
			}
		return rettype;
	}
	
	public boolean sendConfirmationSMS(String processid,String office) throws Exception
	{
		String req_id = "";
		d_gadrequest ins_gad = new d_gadrequest(NGM);
		String sql="select distinct(req_id) from reservation_main_table where process_id=?";
		//select distinct(req_id) from reservation_main_table where process_id='969' union select distinct(req_id) from reservation_waitinglist  where par_process_id='969' 
		PreparedStatement ps=NGM.conn.prepareStatement(sql);
		ps.setString(1,processid);
		System.out.println("pssss           "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			req_id = rs.getString("req_id");
			ins_gad.req_id = req_id;
			if(!ins_gad.selectDB_gad()) return false;
			System.out.println("ins_gad.address1 "+ins_gad.mobile_no);

			System.out.println("ins_gad.address1 "+ins_gad.address1);
			String message = this.SMSbody(processid,req_id,office);
			
			
			this.sendconfirmSMS(ins_gad.mobile_no,message);
		}
		//close
		rs.close();
		ps.close();
		
		return true;
	}
	
	public boolean sendConfirmationSMSForReject(String processid,String remarks) throws Exception
	{
		String req_id = "";
		d_gadrequest ins_gad = new d_gadrequest(NGM);
		String sql="select distinct(req_id) from reservation_main_table where process_id=?";
		//select distinct(req_id) from reservation_main_table where process_id='969' union select distinct(req_id) from reservation_waitinglist  where par_process_id='969' 
		PreparedStatement ps=NGM.conn.prepareStatement(sql);
		ps.setString(1,processid);
		System.out.println("pssss           "+ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			req_id = rs.getString("req_id");
			ins_gad.req_id = req_id;
			if(!ins_gad.selectDB_gad()) return false;
			System.out.println("ins_gad.address1 "+ins_gad.mobile_no);

			System.out.println("ins_gad.address1 "+ins_gad.address1);
			String message = "Your Application with request number " + req_id + " is rejected for reason " + remarks;
			this.sendconfirmSMS(ins_gad.mobile_no,message);
		}
		//close
		rs.close();
		ps.close();
		
		return true;
	}
	
	public boolean sendconfirmSMS(String num,String message)throws Exception {		//ok
		System.out.println("SMSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSs........................");
		System.out.println(" num "+num);
		System.out.println(" message "+message);
		if(num!=null){
			String templateId="1107160707732601528";
			NGM.ins_utility.SENDSMS(num,message, "NGMSgopika",templateId);//this.ins_user.userid
		}
		
		return true;
	} 
	
	
	public String SMSbody(String pro,String req,String office)throws Exception {		//ok
		System.out.println("SMS body........");
		int res_r = this.conformroom(req);
		int res_bg = this.conformgents(req);
		int res_bl = this.conformladies(req);
		int res_dg = this.conformgentsDor(req);
		int res_dl = this.conformladiesDor(req);
		int wait_r = this.conformroomw(req);
		int wait_bg = this.conformgentsw(req);
		int wait_bl = this.conformladiesw(req);
		int wait_dg = this.conformgentswDor(req);
		int wait_dl = this.conformladieswDor(req);
		String officeorderno = office;
		
		String msgdetails="";
		
		if(res_r>0){
			msgdetails = ""+res_r+" rooms";
		}
		
		if(res_bg>0){
			if(res_r>0){
				msgdetails = msgdetails + ",";
			}
			msgdetails = msgdetails + ""+res_bg+" gents beds";
		}
		
		if(res_bl>0){
			
			if((res_r>0) || (res_bg>0)){
				msgdetails = msgdetails + ",";
			}
			msgdetails = msgdetails + ""+res_bl+" ladies beds";
		}
		if(res_dg>0){
			
			if((res_r>0) || (res_bg>0) || (res_bl>0)){
				msgdetails = msgdetails + ",";
			}
			msgdetails = msgdetails + ""+res_dg+" TH gents beds";
		}
		if(res_dl>0){
			
			if((res_r>0) || (res_bg>0) || (res_bl>0)||(res_dg>0)){
				msgdetails = msgdetails + ",";
			}
			msgdetails = msgdetails + ""+res_dl+" TH ladies beds";
		}
		
		if((res_r>0) || (res_bg>0) || (res_bl>0)||(res_dl>0)||(res_dg>0)){
			msgdetails = msgdetails + " confirmed. ";
		}
		
		if(wait_r>0){
			msgdetails = msgdetails + ""+wait_r+" rooms";
		}
		
		if(wait_bg>0){
			if(wait_r>0){
				msgdetails = msgdetails + ",";
			}
			msgdetails = msgdetails + ""+wait_bg+" gents beds";
		}
		
		if(wait_bl>0){
			
			if((wait_r>0) || (wait_bg>0)){
				msgdetails = msgdetails + ",";
			}
			msgdetails = msgdetails + ""+wait_bl+" ladies beds";
		}
		if(wait_dg>0){
			
			if((wait_r>0) || (wait_bg>0) || (wait_bl>0)){
				msgdetails = msgdetails + ",";
			}
			msgdetails = msgdetails + ""+wait_dg+" TH gents beds";
		}
		if(wait_dl>0){
			
			if((wait_r>0) || (wait_bg>0) || (wait_bl>0)||(wait_dg>0)){
				msgdetails = msgdetails + ",";
			}
			msgdetails = msgdetails + ""+wait_dl+" TH ladies beds";
		}
		if((wait_r>0) || (wait_bg>0) || (wait_bl>0)||(wait_dg>0)||(wait_dl>0)){
			msgdetails = msgdetails + " waiting list.";
		} 
		
//		String msg = "Your Office Order is "+officeorderno+" for the Request ID "+req+" : "+msgdetails+"";
		String msg = "Your Request ID "+req+" : "+msgdetails+"";

			return msg; 
		} 
	
	public String getparametervalue(List items, String fieldname)
	{
		Iterator itr = items.iterator();
		   int icount=1;
		   String retvalue = null;
		   while (itr.hasNext()) {
	            FileItem fi = (FileItem)itr.next();
	            if ( fi.isFormField () )
	            {
	            	String tempfieldname = fi.getFieldName();
	                String fieldvalue = fi.getString();
             	 	if (tempfieldname.equals(fieldname))
	                {
                     retvalue = fieldvalue;	 
                     break;
	                 }
	            }	
		   } //end while
	return retvalue;
	} 
	
public String getlastdate(int noofday)throws Exception
	{

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar c = Calendar.getInstance();
	c.setTime(new Date()); // Now use today date.
	c.add(Calendar.DATE, noofday); // Adding 5 days
	String output = sdf.format(c.getTime());
	return output;
	   }

public String display_date(String dates) throws Exception
{String newstring="";
	 
	if(dates!=null){
		if(!dates.isEmpty()) {
			String oldstring = dates;
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);
	
			 newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);
		}
	}
		return newstring;
	
}
public String display_date_new(String dates) throws Exception
{
	String oldstring = dates;
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);

		//String newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);
		String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);

		return newstring;
	
}

public String display_date_newfull(String dates) throws Exception
{
	String oldstring = dates;
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);

		//String newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);
		String newstring = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);

		return newstring;
	
}
public String display_date_calander(String dates) throws Exception
{
	String oldstring = dates;
		Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(oldstring);
		String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);

		return newstring;
	
}
public String display_date_calander1(String dates) throws Exception
{
	String oldstring = dates;
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldstring);
		String newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);

		return newstring;
	
}
public String display_date_vahicle(String dates) throws Exception
{
	String oldstring = dates;
		Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(oldstring);
		String newstring = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);

		return newstring;
	
}
public String display_time_date(String dates) throws Exception
{
	System.out.println(dates);
	String newstring="";
	 
	if(dates!=null){
		String oldstring = dates;
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);

		 newstring = new SimpleDateFormat("yyyy/mm/dd").format(date);
	}
		return newstring;
	
	
}
public String display_time_zone(String dates) throws Exception
{
	 
	String oldstring = dates;
	DateFormat formatter;
    Date date;
    formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    date = (Date) formatter.parse(oldstring);	 
    String newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);
	 
	return newstring;	
	
}

public String display_time_zone_only() throws Exception
{
    String newstring = new SimpleDateFormat("HH:mm:ss").format(new Date());
	 
	return newstring;	
	
}

public String display_time_zonenew(String dates) throws Exception
{
	 
	String oldstring = dates;
	 //05-01-2015 13:10
	DateFormat formatter;
    Date date;
    formatter = new SimpleDateFormat("dd-MM-yyyy");
    date = (Date) formatter.parse(oldstring);	 
    String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);
	 
	return newstring;	
	
}
public String display_date_yr(String dates) throws Exception
{
	String oldstring = dates;
	DateFormat formatter;
    Date date;
    formatter = new SimpleDateFormat("yyyy-MM-dd");
    date = (Date) formatter.parse(oldstring);	 
    String newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);
	return newstring;	
}

public String display_date() throws Exception
{
    String newstring = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	return newstring;	
}

public String parse_string(Date from) throws Exception
{
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
    String strDate = dateFormat.format(from);  
    return strDate;
}

public String short_conform_blocks(String req_id,String gh_id) throws Exception
{
	String block_name_short="";
	String room="select DISTINCT(block_name_short) from(((select gh_id,block_id from reservation_main_table where status!='CAN' and req_id = ? and gh_id=?))) cc join (select * from room_block) dd on cc.gh_id=dd.gh_id and cc.block_id=dd.block_id";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	ps.setString(2,gh_id);
	System.out.println("BLOCK SHORT  i    "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		if(!block_name_short.equals("")){
			block_name_short = block_name_short +",";
		}
		block_name_short = block_name_short + rs.getString("block_name_short");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("block_name_short       "+block_name_short);
	return block_name_short;
}
public String short_wait_blocks(String req_id,String gh_id) throws Exception
{
	String block_name_short="";
	String room="select DISTINCT(block_name_short) from(((select gh_id,block_id from reservation_waitinglist where status!='CAN' and status!='PRO' and req_id = ? and gh_id=?))) cc join (select * from room_block) dd on cc.gh_id=dd.gh_id and cc.block_id=dd.block_id";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	ps.setString(2,gh_id);
	System.out.println("BLOCK SHORT  i    "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		if(!block_name_short.equals("")){
			block_name_short = block_name_short +",";
		}
		block_name_short = block_name_short + rs.getString("block_name_short");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("block_name_short       "+block_name_short);
	return block_name_short;
}
//GAD request
public String GO(String pro_id) throws Exception
{
	String officeorderno="";
	String room="select officeorderno from process_table where process_id=?";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,pro_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		officeorderno = rs.getString("officeorderno");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("officeorderno       "+officeorderno);
	return officeorderno;
}

public int conformroom(String req_id) throws Exception
{
	int countroom=0;
	String room="select count(*) from reservation_main_table where type='R' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countroom = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countroom);
	return countroom;
}

public String conformroomno(String req_id) throws Exception
{
	String room_no = "";
	String room="SELECT  room_no,bed_no FROM public.tr_allotment where type='R' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		if(room_no == ""){
			room_no = rs.getString("room_no")+"-"+rs.getString("bed_no");
		}else{
			room_no = room_no + "," + rs.getString("room_no")+"-"+rs.getString("bed_no");
		}
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+room_no);
	return room_no;
}
public String conformroomnog(String req_id) throws Exception
{
	String room_no = "";
	String room="SELECT  room_no,bed_no FROM public.tr_allotment where type='BG' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		if(room_no == ""){
			room_no = rs.getString("room_no")+"-"+rs.getString("bed_no");
		}else{
			room_no = room_no + "," + rs.getString("room_no")+"-"+rs.getString("bed_no");
		}
	//	if(room_no == null){
	//		room_no = rs.getString("room_no");
//	}
/*	else{
			room_no = room_no + ":" + rs.getString("room_no");
		}*/
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+room_no);
	return room_no;
}

public String conformroomnol(String req_id) throws Exception
{
	String room_no = "";
	String room="SELECT  room_no,bed_no FROM public.tr_allotment where type='BL' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		if(room_no == ""){
			room_no = rs.getString("room_no")+"-"+rs.getString("bed_no");
		}else{
			room_no = room_no + "," + rs.getString("room_no")+"-"+rs.getString("bed_no");
		}
//		}
		/*else{
			room_no = room_no + ":" + rs.getString("room_no");
		}*/
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+room_no);
	return room_no;
}
public int conformladies(String req_id) throws Exception
{
	int countladies=0;
	String room="select count(*) from reservation_main_table where type='BL' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countladies = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countladies);
	return countladies;
}
public int conformgents(String req_id) throws Exception
{
	int countgents=0;
	String room="select count(*) from reservation_main_table where type='BG' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countgents = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countgents);
	return countgents;
}
public int conformgentsDor(String req_id) throws Exception
{
	int countdorgents=0;
	String room="select count(*) from reservation_main_table where type='DG' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countdorgents = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countdorgents);
	return countdorgents;
}
public int conformladiesDor(String req_id) throws Exception
{
	int countdorlady=0;
	String room="select count(*) from reservation_main_table where type='DL' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countdorlady = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countdorlady);
	return countdorlady;
}
public  long calculateDays(String startDate, String endDate)
{
    Date sDate = new Date(startDate);
    Date eDate = new Date(endDate);
    Calendar cal3 = Calendar.getInstance();
    cal3.setTime(sDate);
    Calendar cal4 = Calendar.getInstance();
    cal4.setTime(eDate);
    
    Calendar date = (Calendar) cal3.clone();
    long daysBetween = 0;
    while (date.before(cal4)) {
        date.add(Calendar.DAY_OF_MONTH, 1);
        daysBetween++;
    }
    return daysBetween;
}
 
 
public String display_timestamp(String dates) throws Exception
{
	 System.out.println(dates);
	
	String newstring="";
	if(dates!=null){
		String oldstring = dates;
	java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);

		 newstring = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
	}
		return newstring;
 
	
}

public String display_date_alone(String dates) throws Exception
{
	 System.out.println(dates);
	
	String newstring="";
	if(dates!=null){
		String oldstring = dates;
	java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);

		 newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);
	}
		return newstring;
 
	
}


public int conformroomw(String req_id) throws Exception
{
	int countroomw=0;
	String room="select count(*) from reservation_waitinglist where type='R' and req_id=? and status!='CAN'";//(status='WMOD' or status='WAIT')
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countroomw = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countroomw);
	return countroomw;
}
public int conformladiesw(String req_id) throws Exception
{
	int countladiesw=0;
	String room="select count(*) from reservation_waitinglist where type='BL' and req_id=?  and status!='CAN'";// and (status='WMOD' or status='WAIT')";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countladiesw = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countladiesw);
	return countladiesw;
}
public int conformgentsw(String req_id) throws Exception
{
	int countgentsw=0;
	String room="select count(*) from reservation_waitinglist where type='BG' and req_id=?  and status!='CAN'";//and (status='WMOD' or status='WAIT')";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countgentsw = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countgentsw);
	return countgentsw;
}
public int conformladieswDor(String req_id) throws Exception
{
	int countdorladiesw=0;
	String room="select count(*) from reservation_waitinglist where type='DL' and req_id=?  and status!='CAN'";//and (status='WMOD' or status='WAIT')";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countdorladiesw = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countdorladiesw);
	return countdorladiesw;
}
public int conformgentswDor(String req_id) throws Exception
{
	int countdorgentsw=0;
	String room="select count(*) from reservation_waitinglist where type='DG' and req_id=? and status!='CAN'";// and (status='WMOD' or status='WAIT')";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countdorgentsw = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countdorgentsw);
	return countdorgentsw;
}


//end
//PUBLIC REQUEST

public int conformroom1(String p_id) throws Exception
{
	int countroom=0;
	String room="select count(*) from reservation_main_table_client where type='R' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,p_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countroom = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countroom);
	return countroom;
}
public int conformladies1(String p_id) throws Exception
{
	int countladies=0;
	String room="select count(*) from reservation_main_table_client where type='BL' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,p_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countladies = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countladies);
	return countladies;
}
public int conformgents1(String p_id) throws Exception
{
	int countgents=0;
	String room="select count(*) from reservation_main_table_client where type='BG' and req_id=? and status!='CAN'";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,p_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countgents = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countgents);
	return countgents;
}




public int conformroomw1(String p_id) throws Exception
{
	int countroomw=0;
	String room="select count(*) from reservation_waitinglist_client where type='R' and req_id=?";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,p_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countroomw = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countroomw);
	return countroomw;
}
public int conformladiesw1(String p_id) throws Exception
{
	int countladiesw=0;
	String room="select count(*) from reservation_waitinglist_client where type='BL' and req_id=?";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,p_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countladiesw = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countladiesw);
	return countladiesw;
}
public int conformgentsw1(String p_id) throws Exception
{
	int countgentsw=0;
	String room="select count(*) from reservation_waitinglist_client where type='BG' and req_id=?";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,p_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countgentsw = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countgentsw);
	return countgentsw;
}







//end
//public ArrayList<String> waitinglist(String p_id) throws Exception
//{
//		ArrayList<String> aa = new ArrayList<String>();
//			String room="select * from reservation_waitinglist where process_id=?";
//			PreparedStatement ps=NGM.conn.prepareStatement(room);
//			ps.setString(1,p_id);
//			System.out.println("pssss           "+ps);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next())
//			{
//				aa.add(0, rs.getString("nu_of_rms"));
//				aa.add(1, rs.getString("nu_of_bedg"));
//				aa.add(2, rs.getString("nu_of_bedl"));
//				
//			}
//		System.out.println("list 0000       "+aa);
//		return aa;
//		
//}

public int modroom(String req_id,String block_id) throws Exception
{
	int countroom=0;
	String room="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='R' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	ps.setString(2,req_id);
	ps.setString(3,block_id);

	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countroom = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countroom);
	return countroom;
}
public int modladies(String req_id,String block_id) throws Exception
{
	int countladies=0;
	String room="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='BL' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
			//"select count(*) from mod_req_room where type='BL' and req_id=? and block_id=?";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	ps.setString(2,req_id);
	ps.setString(3,block_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countladies = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countladies);
	return countladies;
}
public int modgents(String req_id,String block_id) throws Exception
{
	int countgents=0;
	String room="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='BG' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
			//"select count(*) from mod_req_room where type='BG' and req_id=? and block_id=?";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	ps.setString(2,req_id);
	ps.setString(3,block_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countgents = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("count       "+countgents);
	return countgents;
}
public int moddorladies(String req_id,String block_id) throws Exception
{
	int countmodladies=0;
	String room="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='DL' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
			//"select count(*) from mod_req_room where type='DL' and req_id=? and block_id=?";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	ps.setString(2,req_id);
	ps.setString(3,block_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 countmodladies = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("countmodladies       "+countmodladies);
	return countmodladies;
}
public int moddorgents(String req_id,String block_id) throws Exception
{
	int countmodgents=0;
	String room="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='DG' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
			//"select count(*) from mod_req_room where type='DG' and req_id=? and block_id=?";
	PreparedStatement ps=NGM.conn.prepareStatement(room);
	ps.setString(1,req_id);
	ps.setString(2,req_id);
	ps.setString(3,block_id);
	System.out.println("pssss           "+ps);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		countmodgents = rs.getInt("count");
	}
	//close
	rs.close();
	ps.close();
	
	System.out.println("countmodgents       "+countmodgents);
	return countmodgents;
}
public static List<DateTime> getDateRange(DateTime start, DateTime end) {

    List<DateTime> ret = new ArrayList<DateTime>();
    DateTime tmp = start ;
    while(tmp.isBefore(end) || tmp.equals(end)) {
        ret.add(tmp);
        tmp = tmp.plusDays(1);
        
    }
   
    return ret;
}

public ArrayList<String> getDateRangeString(DateTime start, DateTime end) {
	System.out.println("Start: " + start);
	/*DateTime startstr = DateTime.parse(start);
	System.out.println("Start: " + startstr);

	DateTime endstr = DateTime.parse(end);
	System.out.println("End: " + end);*/
	
	
	
	ArrayList<String> ret = new ArrayList<String>();
    DateTime tmp = start ;
    while(tmp.isBefore(end) || tmp.equals(end)) {
    	try{
    	String dateonly =display_time_zone(tmp.toString());
    	ret.add(dateonly);
        //  ret.add(tmp.toString());
          tmp = tmp.plusDays(1);
    	}
    	catch(Exception e){
    		
    	}
    	 
        
    }
   System.out.println(ret);
    return ret;
}

//public int checking(String req_id)throws Exception
//{
//	System.out.println("rrrrrrrrrrrrreeeeeeeeqqqqqqqqqqq    "+req_id);
//	int countcheck=0;
//	String countcheckin="";
//	String countguest="";
//	int c1=1;
//	int c2=1;
//	
//	countguest="select count(*)from guest_details where req_id=?";
//	PreparedStatement ps1=NGM.conn.prepareStatement(countguest);
//	ps1.setString(1,req_id);
//	System.out.println("caaaaaaaa     "+ps1);
//	ResultSet rs1 = ps1.executeQuery();
//	while(rs1.next())
//	{
//		c2 = rs1.getInt("count");
//	}
//	System.out.println("C2     "+c2);
//	//for(int i=1;i<=c2;i++)
//	//{
//		countcheckin="select count(*)from tr_checkin where status='CIN' and req_id=?";
//		PreparedStatement ps=NGM.conn.prepareStatement(countcheckin);
//		ps.setString(1,req_id);
//		ResultSet rs = ps.executeQuery();
//		while(rs.next())
//		{
//			c1 = rs.getInt("count")+1;
//		}
//		System.out.println("C1     "+c1);
//	//}
//	if(c1!=c2)
//	{
//		countcheck=1;
//		System.out.println("0000000000000000");
//	}
//	else
//	{
//		countcheck=2;
//		System.out.println("111111111111111111");
//	}
//	return countcheck;
//}




public int checking1(String req_id)throws Exception
{
	System.out.println("rrrrrrrrrrrrreeeeeeeeqqqqqqqqqqq    "+req_id);
	String countguest="";
	int c2=1;
	
	countguest="select count(*)from guest_details where req_id=?";
	PreparedStatement ps1=NGM.conn.prepareStatement(countguest);
	ps1.setString(1,req_id);
	System.out.println("caaaaaaaa     "+ps1);
	ResultSet rs1 = ps1.executeQuery();
	while(rs1.next())
	{
		c2 = rs1.getInt("count");
	}
	//close
	rs1.close();
	ps1.close();
	
	System.out.println("C2     "+c2);
		return c2;
}


public int checking2(String req_id)throws Exception
{
	System.out.println("rrrrrrrrrrrrreeeeeeeeqqqqqqqqqqq    "+req_id);
	String countguest="";
	int c2=0;
	
	countguest="select count(*)from tr_checkin where status='CIN' and req_id=?";
	PreparedStatement ps1=NGM.conn.prepareStatement(countguest);
	ps1.setString(1,req_id);
	System.out.println("caaaaaaaa  c   "+ps1);
	ResultSet rs1 = ps1.executeQuery();
	if(rs1.next())
	{
		c2 = rs1.getInt("count");
	}
	//close
	rs1.close();
	ps1.close();
	
	System.out.println("C2  ftghfhfh  c "+c2);
		return c2;
}
public int checking4(String req_id)throws Exception
{
	System.out.println("rrrrrrrrrrrrreeeeeeeeqqqqqqqqqqq    "+req_id);
	String countguest="";
	int c2=0;
	
	countguest="select count(*)from tr_checkin where status='COUT' and req_id=?";
	PreparedStatement ps1=NGM.conn.prepareStatement(countguest);
	ps1.setString(1,req_id);
	System.out.println("caaaaaaaa  c   "+ps1);
	ResultSet rs1 = ps1.executeQuery();
	if(rs1.next())
	{
		c2 = rs1.getInt("count");
	}
	//close
	rs1.close();
	ps1.close();
	
	System.out.println("C2  ftghfhfh  c "+c2);
		return c2;
}

public int checking3(String req_id)throws Exception
{
	System.out.println("rrrrrrrrrrrrreeeeeeeeqqqqqqqqqqq    "+req_id);
	String countguest="";
	int c2=0;
	
	countguest="select count(*)from tr_checkin where status='CAN' and req_id=?";
	PreparedStatement ps1=NGM.conn.prepareStatement(countguest);
	ps1.setString(1,req_id);
	System.out.println("caaaaaaaa  c   "+ps1);
	ResultSet rs1 = ps1.executeQuery();
	if(rs1.next())
	{
		c2 = rs1.getInt("count");
	}
	//close
	rs1.close();
	ps1.close();
	
	System.out.println("C2  ftghfhfh  c "+c2);
		return c2;
}

public String getRequestID(String guest_id)throws Exception
{
	String req_id="";
	PreparedStatement ps=NGM.conn.prepareStatement("select req_id from guest_details where guest_id=?");
	ps.setString(1,guest_id);
	ResultSet rs = ps.executeQuery();
	if(rs.next())
	{
		req_id = rs.getString("req_id");
	}
	//close
	rs.close();
	ps.close();
	
	return req_id;
}

public List<DateTime> getdate(String from,String to){
	DateTime start = DateTime.parse(from);
    System.out.println("Start: " + start);

    DateTime end = DateTime.parse(to);
    System.out.println("End: " + end);

    List<DateTime> between = getDateRange(start.minusDays(1), end.plusDays(1));
    
    
//    for (DateTime d : between) {
//        System.out.println(" dates" + d);
//    }
    return between;
}
public List<String> getdateString(String from,String to){
	DateTime start = DateTime.parse(from);
    System.out.println("Start%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%: " + start);

    DateTime end = DateTime.parse(to);
    System.out.println("End#######################################################: " + end);

    ArrayList<String> between = getDateRangeString(start, end);
    
    
//    for (DateTime d : between) {
//        System.out.println(" dates" + d);
//    }
    return between;
}

 // Convert the rupees and words


private static final String[] lowNames = {
	   "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
	   "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

	private static final String[] tensNames = {
	   "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

	private static final String[] bigNames = {
	   "thousand", "million", "billion"};

	/**
	* Converts an integer number into words (american english).
	* @author Christian d'Heureuse, Inventec Informatik AG, Switzerland, www.source-code.biz
	**/
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


	public String display_name(String room_no,String block_id,String datee)throws Exception
	{
		String name="",name1="";
		int e=0;
		ArrayList <String> ss = new ArrayList<String>();
		PreparedStatement ps=NGM.conn.prepareStatement("select arrival_time_date,room,name,purpose from(((((select Distinct(room_no) as room from room_details where block_id=? and room_no=?)a left join (select room_no,allot_id from tr_allotment where block_id=? and status!='CAN' and date(?)  between from_date and to_date)b on a.room=b.room_no)c left join (select allot_id,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allot_id)e left join (select tr_id,guest_id,arrival_time_date from tr_checkin)f on e.tr_id=f.tr_id)g left join (select name,guest_id,req_id from guest_details)h on h.guest_id=g.guest_id)i left join (select purpose,req_id from gad_request)j on j.req_id=i.req_id order by room ");
		ps.setString(1,block_id);
		ps.setString(2,room_no);
		ps.setString(3,block_id);
		ps.setString(4,datee);
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{ 
			//String name="";
			//ss.add(rs.getString("name"));
			//for(e=0;e<ss.size();e++){
					if(name==""){
						//name= ss.get(e);
						name= rs.getString("name");
						System.out.println("name1     "+name+"limit"+e);
					}
					else{
						//name=name+","+ss.get(e);
						name=name+" , "+rs.getString("name");
					System.out.println("name2     "+name+"limit"+e);
					}
					e++;
			//}
			
		}
		//close
		rs.close();
		ps.close();
		
		return name;
	}
	public String display_date(String room_no,String block_id,String datee)throws Exception
	{
		String date="",date1="";
		int e=0;
		ArrayList <String> ss = new ArrayList<String>();
		PreparedStatement ps=NGM.conn.prepareStatement("select arrival_time_date,room,name,purpose from(((((select Distinct(room_no) as room from room_details where block_id=? and room_no=?)a left join (select room_no,allot_id from tr_allotment where block_id=? and status!='CAN' and date(?)  between from_date and to_date)b on a.room=b.room_no)c left join (select allot_id,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allot_id)e left join (select tr_id,guest_id,arrival_time_date from tr_checkin)f on e.tr_id=f.tr_id)g left join (select name,guest_id,req_id from guest_details)h on h.guest_id=g.guest_id)i left join (select purpose,req_id from gad_request)j on j.req_id=i.req_id order by room ");
		ps.setString(1,block_id);
		ps.setString(2,room_no);
		ps.setString(3,block_id);
		ps.setString(4,datee);
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			date1 = this.display_date(rs.getString("arrival_time_date"));
			//ss.add(date1);
			//for(e=0;e<ss.size();e++){
				if(date=="")
					//date= ss.get(e);
					date= date1;
				else
					//date=date+","+ss.get(e);
					date=date+" , "+date1;
				System.out.println("date     "+date);
				//}
				e++;
		}
		//close
		rs.close();
		ps.close();
		
		return date;
	}
	public String display_purpose(String room_no,String block_id,String datee)throws Exception
	{
		String p="",p1="";
		PreparedStatement ps=NGM.conn.prepareStatement("select arrival_time_date,room,name,purpose from(((((select Distinct(room_no) as room from room_details where block_id=? and room_no=?)a left join (select room_no,allot_id from tr_allotment where block_id=? and status!='CAN' and date(?)  between from_date and to_date)b on a.room=b.room_no)c left join (select allot_id,tr_id from tr_checkin_allot where status!='CAN')d on c.allot_id=d.allot_id)e left join (select tr_id,guest_id,arrival_time_date from tr_checkin)f on e.tr_id=f.tr_id)g left join (select name,guest_id,req_id from guest_details)h on h.guest_id=g.guest_id)i left join (select purpose,req_id from gad_request)j on j.req_id=i.req_id order by room ");
		ps.setString(1,block_id);
		ps.setString(2,room_no);
		ps.setString(3,block_id);
		ps.setString(4,datee);
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			p1 = rs.getString("purpose");
			
		}
		//close
		rs.close();
		ps.close();
		
		return p1;
	}
	
	public String display_Ghname(String gh_id)throws Exception
	{
		String p="",p1="";
		PreparedStatement ps=NGM.conn.prepareStatement("select * from gh_details where gh_id::Integer=?");
		ps.setInt(1,Integer.parseInt(gh_id));		
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			p1 = rs.getString("gh_name");
			
		}
		//close
		rs.close();
		ps.close();
		
		return p1;
	}
	
	public String display_blockname(String block_id)throws Exception
	{
		String p="",p1="";
		PreparedStatement ps=NGM.conn.prepareStatement("select * from room_block where block_id::Integer=?");
		ps.setInt(1,Integer.parseInt(block_id));		
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			p1 = rs.getString("block_name");
			
		}
		//close
		rs.close();
		ps.close();
		
		return p1;
	}


	public String dateheading_allotment(String date)throws Exception
	{
	String ret="";
	
	return ret;
	}
	
	public String getRollno(String p_bill_id,String p_gh_id)throws Exception
	{
		String ret="",final_res = "";
		int child=0,adults=0;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select * from tr_billvsguest where bill_id = ?) a join (select distinct on(guest_id) * from tr_checkin where gh_id=? and status!='CAN')b on a.guest_id = b.guest_id");
		ps.setString(1, p_bill_id);
		ps.setString(2, p_gh_id);
		System.out.println("ps Roll adults children . . . "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			if(!ret.equals("")){
				ret = ret + ",";
			}
			ret = ret + rs.getString("roll_no");
			/*if(!child.equals("")){
				child = child + ",";
			}*/
			if(rs.getString("no_of_children_below12")!=null){
				child = child + rs.getInt("no_of_children_below12");
			}
			if(rs.getString("adults")!=null){
				adults = adults + rs.getInt("adults");
			}
			
		}
		final_res = ret +":"+child+":"+adults;
		//close
		rs.close();
		ps.close();
		
		return final_res;
		}
	
	//name
	
	public String getNames(String p_bill_id,String p_gh_id)throws Exception
	{
	String ret="";
	int i = 0;
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (select * from tr_billvsguest where bill_id = ?) a join (select guest_id,name from guest_details)b on a.guest_id = b.guest_id");
	ps.setString(1, p_bill_id);
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		i++;
		if(!ret.equals("")){
			ret = ret + ",";
		}
		ret = ret + rs.getString("name");
	}
	//close
	rs.close();
	ps.close();
	
	return ret;
	}
	
	public String getGuestDetails(String p_req_id)throws Exception
	{
	String ret="";
	int i = 0;
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from guest_details where req_id=? order by guest_id");
	ps.setString(1, p_req_id);
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		i++;
		if(!ret.equals("")){
			ret = ret + " & ";
		}
		ret = ret + rs.getString("name");
		String t_desig=rs.getString("designation")!=null?rs.getString("designation"):"";
		if(!t_desig.equals("")){
			ret = ret + "," + t_desig;
		}
		String t_depa=rs.getString("department")!=null?rs.getString("department"):"";
		if(!t_depa.equals("")){
			ret = ret + "," + t_depa;
		}
		
	}
	//close
	rs.close();
	ps.close();
	
	return ret;
	}
	
	
	public String getPublicGuestDetails(String p_req_id)throws Exception
	{
	String ret="";
	int i = 0;
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from pub_guest_details where req_id=?");
	ps.setString(1, p_req_id);
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		i++;
		if(!ret.equals("")){
			ret = ret + " & ";
		}
		ret = ret + rs.getString("name");
		String t_desig=rs.getString("designation")!=null?rs.getString("designation"):"";
		if(!t_desig.equals("")){
			ret = ret + "," + t_desig;
		}
		String t_depa=rs.getString("department")!=null?rs.getString("department"):"";
		if(!t_depa.equals("")){
			ret = ret + "," + t_depa;
		}
	}
	//close
	rs.close();
	ps.close();
	
	return ret;
	}
	//category
	
	public String getCategory(String p_bill_id,String p_gh_id)throws Exception
	{
	String ret="";
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from ((select * from tr_billvsguest where bill_id = ?) a join (select req_id,vc_id from mod_req)b on a.req_id = b.req_id)c join(select * from visitor_category )d on c.vc_id=d.vc_id");
	ps.setString(1, p_bill_id);
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		if(!ret.equals("")){
			ret = ret + ",";
		}
		ret = ret + rs.getString("category"); //desg_category
	}
	//close
	rs.close();
	ps.close();
	
	return ret;
	}
	
	//room no
	
	public String getRoomno(String p_bill_id,String p_gh_id)throws Exception
	{
	String ret="",p_ret="",c_ret="";
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from (((select * from tr_billvsguest where bill_id = ?) a join (select tr_id,guest_id from tr_checkin where gh_id=?)b on a.guest_id = b.guest_id)c join(select * from tr_checkin_allot)d on c.tr_id=d.tr_id) e join (select room_no,allot_id from tr_allotment)f on e.allot_id=f.allot_id");
	ps.setString(1, p_bill_id);
	ps.setString(2, p_gh_id);
	System.out.println("GopIKA "+ps);
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		System.out.println("i 3");
		c_ret = rs.getString("room_no");
		if(!p_ret.equals(c_ret)){
			if(!ret.equals("")){
				ret = ret + ",";
			}
			System.out.println("GopIKA 1 ");
			ret = ret + c_ret;
			p_ret = c_ret;
		}
		
	}
	//close
	rs.close();
	ps.close();
	
	return ret;
	}

	//charges
	public Map<String, Integer> getCharges(String p_bill_id,String p_gh_id)throws Exception
	{
	Map<String,Integer> map_charges = new HashMap<String,Integer>();
	String t_charges_id="";
	int t_charge=0,total=0;
	PreparedStatement ps1=NGM.tr_conn.prepareStatement("select * from master_charges");
	ResultSet rs1=ps1.executeQuery();
	while(rs1.next()){
		t_charges_id=rs1.getString("charges_id");
	
	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select sum(amount::Integer) from tr_billvsguestvscharges where bill_id = ? and charges_id=?");
		ps.setString(1, p_bill_id);
		ps.setString(2, t_charges_id);
		//System.out.println("GopIKA "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			t_charge = rs.getInt("sum");
			map_charges.put(t_charges_id,t_charge);
			total=total+t_charge;
		}
		//close
		rs.close();
		ps.close();
		
	}
	map_charges.put("total",total);
	//close
	rs1.close();
	ps1.close();
	
	return map_charges;
	}
	
	//receipt
	public Map<String, String> getReceipt(String p_bill_id,String p_gh_id)throws Exception
	{
	Map<String,String> map_receipt = new HashMap<String,String>();

		PreparedStatement ps=NGM.tr_conn.prepareStatement("select rec_id,amount from tr_receipt where rec_id in (select rec_id from tr_receiptvsbill where bill_id = ?)");
		ps.setString(1, p_bill_id);
		System.out.println("getReceipt--------------------------------------------------------- "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			map_receipt.put("rec_id",rs.getString("rec_id"));
			map_receipt.put("rec_amount",rs.getString("amount"));

		}
		//close
		rs.close();
		ps.close();
		
	return map_receipt;
	}
	
	public String datesbetween(String str_date, String end_date) throws Exception
	{
		
	    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	    Date d1 = null;
	    Date d2 = null;
	    try {
	        d1 = format.parse(str_date);
	        d2 = format.parse(end_date);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    // Get msec from each, and subtract.
	    long diff = d2.getTime() - d1.getTime();
	    long diffSeconds = diff / 1000 % 60;
	    long diffMinutes = diff / (60 * 1000) % 60;
	    long diffHours = diff / (60 * 60 * 1000);
	    System.out.println("Time in seconds: " + diffSeconds + " seconds.");
	    System.out.println("Time in minutes: " + diffMinutes + " minutes.");
	    System.out.println("Time in hours: " + diffHours + " hours.");
	  
		ArrayList<Date> dates1 = new ArrayList<Date>();
		String ss="";
		
		DateFormat formatter ; 

		formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date  startDate = (Date)formatter.parse(str_date); 
		Date  endDate = (Date)formatter.parse(end_date);
		long interval = 24*1000 * 60 * 60; // 1 hour in millis
		long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
		long curTime = startDate.getTime();
		
		double min = diffMinutes;
		double hours = diffHours;
		double abc = 0;
		if(min>0){
			hours = hours + 1;
		}
		if(hours<24){
			abc = 1;
		}else{
			abc = hours/24;
		}
		long days = (long) Math.ceil(abc);
		
		System.out.println("DATES BETWEEN.............................days.......................... "+days);
		
		for(int i=0;i<days;i++){
			
			
		    Date lDate =DateUtil.addDays(startDate, i);
		    String ds = formatter.format(lDate);    
		   
		    ss=ss+"/"+ds;
		}
		
		System.out.println("DATES BETWEEN.............................ss.......................... "+ss);
		
		return ss;	
		
	}

	
	public String datesbetweenOLD(String str_date, String end_date) throws Exception
	{
		System.out.println("str_date "+str_date);
		System.out.println("end_date "+end_date);
		ArrayList<Date> dates1 = new ArrayList<Date>();
		String ss="";
		
		DateFormat formatter ; 

		formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date  startDate = (Date)formatter.parse(str_date); 
		Date  endDate = (Date)formatter.parse(end_date);
		long interval = 24*1000 * 60 * 60; // 1 hour in millis
		long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
		long curTime = startDate.getTime();
		System.out.println("--------------------------------------------------------curTime "+curTime);
		System.out.println("--------------------------------------------------------endTime*** "+endTime);
		System.out.println("--------------------------------------------------------interval "+interval);
		/*while (curTime < endTime) {
			System.out.println("c "+curTime);
			System.out.println("e "+endTime);
		    dates1.add(new Date(curTime));
		    curTime += interval;
		    System.out.println("-------------****-----------------curTime "+curTime);
		}
		for(int i=0;i<dates1.size();i++){
		    Date lDate =(Date)dates1.get(i);
		    String ds = formatter.format(lDate);    
		    //System.out.println(" Date is ..." + ds);
		    ss=ss+"/"+ds;
		}*/
		System.out.println("DATES BETWEEN....................................................... "+ss);
		return ss;	
		
	}



	public String display_date_rent(String dates) throws Exception
	{
		String oldstring = dates;
		System.out.println("dates................................ "+dates);
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);
			String newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);

			return newstring;
		
	}
	
	
	
}
