package common;

import java.net.InetAddress;





import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.sendsms;


import db.ngm;

import java.sql.Timestamp;

import db.*;

public class utility extends db_validation {

	private static final int CAPTCHA_LENGTH = 5;
	private static final long serialVersionUID = 1512371749422L;
	public String  err          = null;
	public String  event_id;
	public Integer fixed_basicpay;
	public String  dni;
	public Integer pay_min;
	public Integer pay_max;
	public Integer no_increGvn;
	public String  is_MC_Domain = "N";
	public String smsurl ="";
	public String smsuserid ="";
	public String smspassword ="";

	public utility(String smsurl,String smsuserid, String smspassword)

	{
		this.smsurl = smsurl;
		this.smsuserid = smsuserid;
		this.smspassword = smspassword;

	}
	public utility(){

	}

	public String getCapcha() {
		Random r = new Random();
		int f = System.currentTimeMillis() > serialVersionUID ? 1 : 0;
		String token = Long.toString(Math.abs(r.nextLong()), 36);
		String ch = token.substring(0, CAPTCHA_LENGTH);
		return ch;
	}

	/*public String getCapcha() {
		String processId = random(6);
		return processId;
	}*/

	public String getProcessId() {
		String processId = random(32);
		return processId;
	}


	public boolean 	validate_gh_id(String gh_id) throws Exception
	{
		if(gh_id!=null){
			if(!cLength(gh_id.toString(), 1, 10)){
				return false;
			}
			if (!cNumeric(gh_id)){
				return false;
			}
			return true;
		}else

			return false;
	}


	//		int a=10;
	//		if(a==10) 
	//		{
	//			//select * from uservsghid where userid =? and gh_id =?
	//			return true;
	//		}
	//		else
	//			throw new Exception();
	//	}

	public String getapplication_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from gad_request where status='REQ' and date(from_date)>=date(now())"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();
		System.out.println("COUNT @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@       "+retstr);			
		return retstr;
	}
	//select * from((select req_id as rid,* from tr_checkin where status='CIN') a join (select * from gad_request where status!='CAN')b on a.rid=b.req_id)c join (select * from guest_details)d on c.rid=d.req_id and c.guest_id=d.guest_id
	public String getguest_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from (select * from((select req_id as rid,* from tr_checkin where status='CIN') a join (select * from gad_request where status!='CAN')b on a.rid=b.req_id)c join (select * from guest_details)d on c.rid=d.req_id and c.guest_id=d.guest_id)aa"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();
		System.out.println("getguest_count >>>>>>>>>>      "+retstr);			
		return retstr;
	}
	
	public String getguest_count_by_gh(ngm NGM, String gh_id) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from (select * from((select req_id as rid,* from tr_checkin where status='CIN' and gh_id = ?) a join (select * from gad_request where status!='CAN' and gh_id = ?)b on a.rid=b.req_id)c join (select * from guest_details)d on c.rid=d.req_id and c.guest_id=d.guest_id)aa"); 
		ps.setString(1, gh_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();
		System.out.println("getguest_count >>>>>>>>>>      "+retstr);			
		return retstr;
	}
	
	public String getofficeorder_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from gad_request where status='CNF' and from_date>=now()"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	public String getmod_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from gad_request where status='MOD' and date(from_date)>=date(now())"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}

	public String getprocess_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from gad_request where status='PRO' and date(from_date)>=date(now())"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}

	public String getcancel_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from gad_request where status='CAN' and date(from_date)>=date(now())"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	
	public String getfeedback_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from feedback_system where status='Opened'"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	public String getnoOffeedback_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from feedback_system"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	public String getnoViewfeedback_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from feedback_system where status='Opened' and view=true"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	public String getnoOfClosedfeedback_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from feedback_system where status='Closed' and view=false"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	public String getPendingAllotment_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from (select * from ((select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where status!='CAN' union (select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist where (status='WMOD' or status='ALO'))) c join (select req_id,from_date,to_date,purpose,status from gad_request where status!='CAN' and date(from_date)>=date(now()))d on c.req_id=d.req_id) where c.status!='CAN' and (d.status='PRO' or d.status='PALO') order by c.req_id)aa"); 
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	
	public String getPendingAllotment_count_by_gh(ngm NGM, String gh_id) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from (select * from ((select req_id,process_id,type,status,res_id,'R' as tablename  from reservation_main_table where status!='CAN' and gh_id=? union (select req_id,process_id,type,status ,res_wid, 'W' as tablename  from reservation_waitinglist where (status='WMOD' or status='ALO'))) c join (select req_id,from_date,to_date,purpose,status from gad_request where status!='CAN' and gh_id=? and date(from_date)>=date(now()))d on c.req_id=d.req_id) where c.status!='CAN' and (d.status='PRO' or d.status='PALO') order by c.req_id)aa"); 
		ps.setString(1, gh_id);
		ps.setString(2, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	
	public String getPendingCheckin_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from (select c.gh_id,c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id from((select req_id as rid,from_date,to_date,gh_id,status from gad_request where (status='ALO' and status!='PALO') and ext='0')a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' union select process_id,req_id from reservation_waitinglist where status!='CAN')d on c.rid=d.req_id where date(c.to_date)>=date(now()))dd");
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	
	public String getPendingCheckin_count_by_gh(ngm NGM, String gh_id) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from (select c.gh_id,c.req_id, c.from_date,c.to_date,c.name,c.guest_id,c.designation,c.department,c.address,c.status,d.process_id from((select req_id as rid,from_date,to_date,gh_id,status from gad_request where (status='ALO' and status!='PALO') and ext='0' and gh_id=?)a join (select * from guest_details)b on a.rid=b.req_id) c left join (select process_id,req_id from reservation_main_table where status!='CAN' union select process_id,req_id from reservation_waitinglist where status!='CAN')d on c.rid=d.req_id where date(c.to_date)>=date(now()))dd");
		ps.setString(1, gh_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	
	public String getProcessedBills_count(ngm NGM) throws Exception
	{
		String retstr="";
		PreparedStatement ps=NGM.conn.prepareStatement("select count(*) from tr_bill_master  where status='BILL'");
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			retstr=rs.getString("count");
		}
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	public String getguesthouselist(Connection p_conn, String puserid) throws Exception
	{
		String temp_gh_id="0",temp_gh_name="---Not Assighned---";
		PreparedStatement ps=p_conn.prepareStatement("select * from gh_details where gh_id in (select gh_id from uservsgh where userid = ?)"); 
		ps.setString(1, puserid);
		System.out.println("ps getguesthouselist : "+ps);
		String retstr= "<select id='sel_id' class='' name='sel_id' onchange=''><option value='0'>--select--</option>";

		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			temp_gh_id = rs.getString("gh_id");
			temp_gh_name =temp_gh_id +" - "+ rs.getString("gh_name");
			retstr=retstr+"<option value='"+temp_gh_id+"'>"+temp_gh_name+"</option>";
		}
		//close
		rs.close();
		ps.close();
		//retstr= "<select id='sel_id' class='' name='sel_id' onchange=''><option value='"+temp_gh_id+"'>"+temp_gh_name+"</option>";
		retstr=retstr+"</select>";
		return retstr;
	}

	public String getguesthouselistkeralahouse(Connection p_conn, String puserid) throws Exception
	{
		String temp_gh_id="0",temp_gh_name="---Not Assighned---";
		PreparedStatement ps=p_conn.prepareStatement("select gh_id,gh_name from gh_details"); 
		//	 ps.setString(1, puserid);
		System.out.println("ps getguesthouselist : "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		String retstr="<select id='sel_id' class='' name='sel_id' onchange=''>";
		while(rs.next()){
			temp_gh_id = rs.getString("gh_id");
			temp_gh_name =temp_gh_id +" - "+ rs.getString("gh_name");
			//retstr=retstr+"<option value='"+temp_gh_id+"'>"+temp_gh_name+"</option>";
			retstr=retstr+"<option value='"+temp_gh_id+"'>"+temp_gh_name+"</option>";

			System.out.println("FFFFFFF"+retstr);
		}
		retstr=retstr+"</select>";
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	public String getguesthouselistkeralahouse1(Connection p_conn, String puserid) throws Exception
	{
		String temp_gh_id="0",temp_gh_name="---Not Assighned---";
		PreparedStatement ps=p_conn.prepareStatement("select block_id,block_name from room_block where gh_id=? "); 
		ps.setString(1, "01");
		System.out.println("ps getguesthouselist : "+ps);
		//String retstr= "<select id='sel_id' class='' name='sel_id' onchange=''><option value='0'>--select--</option>";

		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		String retstr="<select id='sel_id1' class='' name='sel_id' onchange=''>";
		while(rs.next()){
			temp_gh_id = rs.getString("block_id");
			temp_gh_name =temp_gh_id +" - "+ rs.getString("block_name");
			retstr=retstr+"<option value='"+temp_gh_id+"'>"+temp_gh_name+"</option>";
			System.out.println("FFFFFFF"+retstr);
		}
		retstr=retstr+"</select>";
		//close
		rs.close();
		ps.close();

		return retstr;
	}
	public String getguesthouselistOLD(Connection p_conn, String puserid) throws Exception
	{
		PreparedStatement ps=p_conn.prepareStatement("select * from gh_details where gh_id in (select gh_id from uservsgh where userid = ?)"); 
		ps.setString(1, puserid);
		String retstr= "<select id='sel_id' class='' name='sel_id' onchange=''><option value='0'>--select--</option>";

		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			String temp_gh_id = rs.getString("gh_id");
			String temp_gh_name =temp_gh_id +" - "+ rs.getString("gh_name");
			retstr=retstr+"<option value='"+temp_gh_id+"'>"+temp_gh_name+"</option>";
		}
		//close
		rs.close();
		ps.close();
		retstr=retstr+"</select>";
		return retstr;
	}
	public String getDBcombo_special_food(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			//String str=rs.getString(2)+"("+rs.getString(1)+")";
			String str=rs.getString(1)+" - "+rs.getString(2);
			addComboDatacode(str, rs.getString(1));
		}
		return getCombo_specl_food(p_id, p_class, p_name, p_fname,p_key);
	}
	public String getCombo_specl_food(String p_id,String p_class,String p_name,String p_fname,String p_key) {
		System.out.println("Second      "+p_fname);
		String option="";
		Iterator<String>it=comboset.iterator();
		while(it.hasNext()){
			String temp=it.next();
			if(temp.equals(p_key)) 
			{   option=option.concat("<option value=\""+temp+"\" selected value=\""+p_key+"\" >"+combomap.get(temp)+"</option>"); }
			else
				option=option.concat("<option value=\""+temp+"\" >"+combomap.get(temp)+"</option>"); 
		}
		String s="<select id=\""+p_id+"\" class=\""+p_class+"\" name=\""+p_name+"\"  onchange=\""+p_fname+"\">"+
				"<option value='0' selected='selected'>Select Any</option>" +
				option+
				"</select>";
		combomap.clear();
		comboset.clear();
		return s;
	}
	public boolean isvalid_authorization(Connection p_conn, String pservname, String ptask, String puserid) throws Exception
	{
		// PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from servlet_task where serv_name = ? and task_id=? and role_code in (Select role_code from uservsrole where userid = ?");

		PreparedStatement ps=p_conn.prepareStatement("select * from servlet_task where serv_name = ? and task_id=? and role_code in (Select role_code from uservsrole where userid = ?)"); 
		ps.setString(1, pservname);  
		ps.setString(2, ptask);  
		ps.setString(3, puserid);
		System.out.println("Inside isvalid_authorization           "+ps);    
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){

			l_status=true;
		}
		//close
		rs.close();
		ps.close();
		if(l_status)
			return true;
		else
			throw new Exception();

		//return true;


	}
	public boolean isvalid_authorization_Servlet(Connection p_conn, String pservname,String puserid) throws Exception
	{
		// PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from servlet_task where serv_name = ? and task_id=? and role_code in (Select role_code from uservsrole where userid = ?");

		System.out.println("Inside isvalid_authorization ------------------------------------------------"+puserid);
		PreparedStatement ps=p_conn.prepareStatement("select * from servlet_task where serv_name = ? and role_code in (Select role_code from uservsrole where userid = ?)"); 
		ps.setString(1, pservname);  
		ps.setString(2, puserid);
		System.out.println("Inside isvalid_authorization  GOPIKA**         "+ps);    
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			System.out.println("Inside isvalid_authorization  GOPIKA**    &&&&&&&&&&&&&&&&&&&&&&&&&&&& ");    
			l_status=true;
		}
		//close
		rs.close();
		ps.close();
		if(l_status)
			return true;
		else
			throw new Exception();

		//return true;


	}
	public boolean guesthouseexist(Connection p_conn,String pgh_id,String  puserid) throws Exception
	{
		PreparedStatement ps = p_conn.prepareStatement("select * from tr_shift_master where gh_id='"+pgh_id+"'"); 
		System.out.println("exist"+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){

			l_status=true;
		}
		//close
		rs.close();
		ps.close();
		if(l_status)
			return true;
		else
			/*throw new Exception();*/
			return false;
	}
	public boolean isvalid_guesthouse(Connection p_conn,String pgh_id,String  puserid) throws Exception
	{

		validate_gh_id(pgh_id); 
		boolean l_status=false;
		PreparedStatement ps = p_conn.prepareStatement("select * from uservsgh where userid =? and gh_id =?");
		ps.setString(1, puserid);
		ps.setString(2, pgh_id);
		System.out.println("ghid++++++++++++++++++++++++     "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){

			l_status=true;
		}
		//close
		rs.close();
		ps.close();
		if(l_status)
			return true;
		else
			throw new Exception();


	}
	
	public void SENDSMS(String mobileno,String messg,String senderId,String templateId)
	{
		// For NIC
		sendsms  ins_sms =new sendsms(this.smsurl,this.smsuserid,this.smspassword);
		if(messg != null){
			if(!messg.isEmpty()){
				String tmp[] = messg.split(" ");
				messg = "";
				for(String item : tmp){
					messg = messg.trim() + "%20" + item.trim();
				}
//				tmp = messg.split("&");
//				if(tmp.length != 0){
//					messg = "";
//					for(String item : tmp){
//						messg = messg.trim() + "%26" + item.trim();
//					}
//				}
			}
		}
//		sendsms ins_sms =new sendsms(this.smsurl,this.smsuserid,this.smspassword);
		if(messg != null){
			if(!messg.isEmpty()){
				ins_sms.SmsMessage(mobileno, messg.trim(), senderId,templateId);
			}
		}
	}

	public void validate_task(String p_task, ngm NGM) throws Exception {
		if (!NGM.ins_val.cAlphabet(p_task))
			throw new Exception();
	}

	public void validate_tab(String p_tab, ngm NGM) throws Exception {
		if (!NGM.ins_val.cAlphabet(p_tab))
			NGM.ins_writer.write("Message:  Illegal Tab Only Alphabets are Allowed.");
		throw new Exception();
	}

	public String getrolelists(String hidvalue)
	{

		System.out.println("hidvalueeeeeeeee      "+hidvalue);
		String str= "";

		//String[] s = hidvalue.split(",");
		// System.out.println("output     "+Arrays.toString(s));
		//ArrayList<String> s1=new ArrayList();
		//.add(hidvalue);

		//Iterator<String> itr = hidvalue.iterator();

		for (String retval: hidvalue.split(",")){
			//String retval[]=hidvalue.split(",");
			System.out.println("retval    "+retval);

			int i=0;
			for(i=1;i<=retval.length();i++){
				System.out.println("iiiiiiiiii   "+retval.length());
				//System.out.println(itr.next());
				//str=str+"'"+itr.next()+"'";
				if(i==0){
					str="'"+retval+"'";
					System.out.println("str1     "+str);
				}
				else
				{	
					str=str+",'"+retval+"'";
					System.out.println("str2     "+str);
				}
				//i++;

			}
		}
		return str;
	}



	public String random(int p_val) {
		String AB = "123456789ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz*#&$0";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(p_val);
		for (int i = 0; i < p_val; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public String getHash(String p_val) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(p_val.getBytes());
		byte byteData[] = md.digest();
		//convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		//convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}



	public String hash(String p_val) throws Exception {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(p_val.getBytes());
		byte byteData[] = md.digest();
		//convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		//convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}


	public String getSalt(String p_val1,String p_val2)throws Exception
	{
		return hash(p_val1+p_val2);
	}

	public boolean checkConstrainsalary(Connection p_conn,String p_table,String p_field,String p_val) throws Exception{
		Statement st=p_conn.createStatement();
		String ss="SELECT "+p_field +" FROM "+p_table +" WHERE "+ p_val;
		System.out.println(ss);
		ResultSet rs=st.executeQuery(ss);
		if(rs.next())
			return false;
		else
			return true;
	}

	public String getBrowser(String p_val) {
		String browserId = new String("");
		if (p_val != null) {
			if ((p_val.indexOf("MSIE") == -1) && (p_val.indexOf("msie") == -1)) {
				if ((p_val.indexOf("Firefox") != -1)) {
					browserId = "2";
				}
				else if ((p_val.indexOf("Chrome") != -1)) {
					browserId = "3";
				}
				else if ((p_val.indexOf("Opera") != -1)) {
					browserId = "4";
				}
			}
			else {
				browserId = "1";// IE		
			}
		}
		return browserId; 
	}

	public String getVersion(String p_val) {
		String version = new String("");
		if ((p_val.indexOf("MSIE") == -1) && (p_val.indexOf("msie") == -1)) {
			int verPos = p_val.indexOf("/");
			if (verPos != -1)
				version = p_val.substring(verPos + 1, verPos + 5);
		}
		else {
			String tempStr = p_val.substring(p_val.indexOf("MSIE"), p_val.length());
			version = tempStr.substring(4, tempStr.indexOf(";"));
		}
		return version;
	}

	public String getIp() throws Exception {
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		return ip;
	}

	public Integer get_diffDays(String p_date1, String p_date2, ngm NGM) throws Exception {
		Integer no_days = 0;
		if (p_date1 != null && p_date2 != null) {
			if (p_date1 != "" && p_date2 != "") {
				PreparedStatement ps_year = NGM.tr_conn.prepareStatement("select to_date(?, 'dd/mm/yyyy') - to_date(?, 'dd/mm/yyyy') as no_days from dual");
				ps_year.setString(1, p_date1);
				ps_year.setString(2, p_date2);
				ResultSet rs_year = ps_year.executeQuery();
				while (rs_year.next()) {
					no_days = rs_year.getInt("no_days");
				}
			}
		}
		return no_days;
	}

	public Integer getID(Connection p_conn, String p_id) throws Exception {
		PreparedStatement ps = p_conn.prepareStatement("select curvalue from ge_id where id=? for update");
		ps.setString(1, p_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Integer l_curvalue = rs.getInt("curvalue");
		ps.close();
		return l_curvalue + 1;
	}

	public boolean updateID(Connection p_conn, String p_id, Integer p_curvalue) throws Exception {
		boolean l_status = false;
		PreparedStatement ps = p_conn.prepareStatement("Update ge_id set curvalue=? where id=?");
		ps.setInt(1, p_curvalue);
		ps.setString(2, p_id);
		l_status = ps.execute();
		ps.close();
		return l_status;
	}

	public void includePg_Master(String page,HttpServletRequest request,HttpServletResponse response)throws Exception {
		RequestDispatcher rd=request.getRequestDispatcher("jsp/"+page);
		System.out.println("pageeee"+page);
		rd.include(request, response);
	}
	public void setNullInteger(PreparedStatement p_ps, Integer p_index, Integer p_data) throws Exception {
		if(p_data==null)
			p_ps.setNull(p_index, java.sql.Types.INTEGER);
		else
			p_ps.setInt(p_index,p_data);
		return;
	}
	public boolean executeDB(PreparedStatement p_ps) throws Exception{
		int c=p_ps.executeUpdate();
		return (c!=0)?true:false;
	}
	public Exception setException(String p_class,String p_message, String p_method, ngm p_ngm) {
		System.out.println("Exception class entry");
		exception_log ins_exception=new exception_log(p_ngm);
		ins_exception.e_class=p_class;
		ins_exception.e_message=p_message;
		ins_exception.e_method=p_method;
		ins_exception.e_userid=p_ngm.ins_sess.user_id;
		ins_exception.insertDB();
		return new Exception(p_message);	
	}
	public boolean checkConstrain(Connection p_conn,String p_table,String p_field,String p_val,ngm p_ngm) throws Exception{

		//p_val	=	p_val.toUpperCase();
		Statement st=p_conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT "+p_field +" FROM "+p_table +" WHERE "+ p_field+" = "+"'" +p_val +"'");
		if(rs.next())
			return false;
		else
			return true;
	}
	public ArrayList<Integer> getPaginationCount(){
		ArrayList<Integer> al=new ArrayList<Integer>();
		al.add(5);
		al.add(10);
		al.add(20);
		al.add(30);
		al.add(40);
		return al;
	}
	public String getLocation(ngm p_ngm) throws Exception{	

		Statement st=p_ngm.conn.createStatement();
		ResultSet rs1=st.executeQuery("SELECT location from m_configuration");
		rs1.next();
		String l_location	=	rs1.getString("location");
		st.close();
		return l_location;

	}




	//////////
	public String dispDate(Timestamp timestamp){
		if(timestamp!=null){
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy a");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy:ha");
			return sdf.format(timestamp);
		}
		else
			return null;
	}

	public String dispDate(Date p_date){
		if(p_date!=null){
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy a");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy:ha");
			return sdf.format(p_date);
		}
		else
			return null;
	}
	public String dispDat(Date p_date){
		if(p_date!=null){
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy a");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			return sdf.format(p_date);
		}
		else
			return null;
	}
	//----------------------	
	public String dispDate(Timestamp timestamp,String p_format){
		if(timestamp!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(p_format);
			return sdf.format(timestamp);
		}
		return null;
	}

	public String dispDate(Date p_date,String p_format){
		if(p_date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(p_format);
			return sdf.format(p_date);
		}
		return null;
	}

	public String dispDate(String p_date,String p_format){
		if(p_date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(p_format);
			return sdf.format(p_date);
		}
		return null;
	}
	
	public String dispDate_yy(String p_date,String p_format) throws ParseException{
		if(p_date!=null){
			java.util.Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(p_date);
			
			SimpleDateFormat sdf = new SimpleDateFormat(p_format);
			return sdf.format(p_date);
		}
		return null;
	}

	public boolean abs_form(ngm NGM,String table)throws Exception{
		boolean status = false;

		PreparedStatement ps = NGM.tr_conn	.prepareStatement("SELECT * from "+table+"");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			status = true; 

		}
		//close
		rs.close();
		ps.close();
		return status;
	}

	/////////

	public String getprice(String item_id,ngm NGM) throws Exception{ 
		String item_price = "";
		//PreparedStatement ps=NGM.conn.prepareStatement("select * from m_user_authentication where servlet=? and task=? and org_desig_code=? and eligible='Y'");
		PreparedStatement ps=NGM.conn.prepareStatement("select * from m_food_items where item_id=?");
		ps.setString(1, item_id);
		System.out.println("ggggggggggggggggggggggggggggggg     "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{   
			item_price=rs.getString("unitprice");
		}
		ps.close();
		rs.close();
		return item_price;

	}

	public boolean check_eligibility(String fullURL,String p_task,ngm NGM)throws Exception {

		boolean elgbl=true;
		//PreparedStatement ps=NGM.conn.prepareStatement("select * from m_user_authentication where servlet=? and task=? and org_desig_code=? and eligible='Y'");
		PreparedStatement ps=NGM.conn.prepareStatement("select * from m_user_authentication where servlet=? and task=? and role_code=?");
		ps.setString(1, fullURL);
		ps.setString(2, p_task);
		ps.setString(3, NGM.ins_sess.role_id);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{    
			if(rs.getString("eligible").equals("N")) elgbl=false;
		}
		//close
		rs.close();
		ps.close();
		return elgbl;
	}


	public String get_value_Oneparameter(Connection p_conn,String p_table,String p_field,String p_field1,String p_val) throws Exception{
		String ofc_name="";
		Statement st=p_conn.createStatement();
		String ss="SELECT "+p_field1 +" FROM "+p_table +" WHERE "+ p_field+" = "+"'" +p_val +"'";
		ResultSet rs=st.executeQuery(ss);
		if(rs.next()){
			ofc_name= rs.getString(p_field1);
		}
		return ofc_name;
	}
	public String setNullParameter(HttpServletRequest p_request,String p_parameter) throws Exception {
		System.out.println("NUUUUUUUUUUUUUUUUUUUUUU"+p_parameter);
		String p_val	= p_request.getParameter(p_parameter);
		System.out.println("NUUUUUUUUUUUUUUUUUUUUUU"+p_val);
		if(p_val.equals(""))
			return null;
		else 
			return p_val;
	}

	public boolean checkConstrain_reg(Connection p_conn,String p_table,String p_field,String p_val) throws Exception{

		Statement st=p_conn.createStatement();
		String q="SELECT "+p_field +" FROM "+p_table +" WHERE "+ p_field+" = "+"'" +p_val +"'";
		ResultSet rs=st.executeQuery(q);
		System.out.println("qqqqqqqqqqqqqqqqqqqqq "+q);
		if(rs.next())
			return true;
		else

			return false;
	}
	TreeMap<String, String>combomap	= new TreeMap<String,String>();
	TreeSet<String>comboset			= new TreeSet<String>();
	public String getDBcombo(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		if(p_ps!=null)
		{
			ResultSet rs=p_ps.executeQuery();
			while(rs.next()){			
				addComboData(rs.getString(2), rs.getString(1));
			}
			return getCombo(p_id, p_class, p_name, p_fname,p_key);
		}
		else
			return getCombo_disable(p_id, p_class, p_name);
	}
	//disabled combo valu
	public String getDBcombodisable(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		if(p_ps!=null)
		{
			ResultSet rs=p_ps.executeQuery();
			while(rs.next()){			
				addComboData(rs.getString(2), rs.getString(1));
			}
			return getCombo_disable(p_id, p_class, p_name);

		}
		else
			return getCombo(p_id, p_class, p_name, p_fname,p_key);
	}

	//end

	public String getDBcombo_special(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		System.out.println("sdfsd"+p_ps);
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			addComboData(rs.getString(2), rs.getString(1));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}
	public String getDBcombo_process_id(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			addComboData(rs.getString("process_id"), rs.getString("process_id"));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}
	public String getDBcombo_shiftid(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		int count = 0;
		while(rs.next()){

			String close_time="";
			String ctime = this.display_timestamp1(rs.getString("createdtime"));
			if(rs.getString("close_time")!=null){
				close_time = this.display_timestamp1(rs.getString("close_time"));
			}
			String str=rs.getString("shift_id")+" - "+rs.getString("username")+" - "+ctime+" - "+close_time;
			addComboData (str,rs.getString("shift_id"));
		}
		if(count==0){
			return getCombo_specl_shift(p_id, p_class, p_name, p_fname,p_key);
		}else{
			return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
		}
	}

	public String getDBcombo_shiftid_dis(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		int count = 0;
		while(rs.next()){
			count=1;
			String close_time="";
			String ctime = this.display_timestamp1(rs.getString("createdtime"));
			if(rs.getString("close_time")!=null){
				close_time = this.display_timestamp1(rs.getString("close_time"));
			}
			String str=rs.getString("shift_id")+" - "+rs.getString("username")+" - "+ctime+" - "+close_time;
			addComboData (str,rs.getString("shift_id"));

		}
		if(count==0){
			return getCombo_specl_shift(p_id, p_class, p_name, p_fname,p_key);
		}else{
			return getCombo(p_id, p_class, p_name, p_fname,p_key);
		}
	}


	//Jeswin
	public String getDBcombo_voted(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		String str_val="";
		while(rs.next()){	
			if(rs.getString(2).equals("V")){
				str_val = "Voted";
			}
			else{
				str_val = "Charged";
			}
			addComboData(str_val, rs.getString(1));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}

	//Jeswin
	public String getDBcombo_plan(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		String str_val="";
		while(rs.next()){		
			if(rs.getString(2).equals("NP")){
				str_val = "Non Plan";
			}
			else{
				str_val = "Plan";
			}
			addComboData(str_val, rs.getString(1));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}

	public void addComboData(String p_name,String p_value) {
		comboset.add(p_value);
		combomap.put(p_value, p_name);
	}

	///gopika
	public String getDBcombo_same(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			addComboData(rs.getString(1), rs.getString(1));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}
	public String getDBcombo_same1(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			addComboData(rs.getString(1), rs.getString(1));
		}
		return getCombo(p_id, p_class, p_name, p_fname,p_key);
	}
	
	public String getDBcombo_special_common(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key, ngm p_ngm) throws Exception{
		if(p_id.equalsIgnoreCase("sel_block")) {
			p_ps.clearParameters();
			p_ps.setString(1, p_ngm.ins_sess.gh_id);
		}
		ResultSet rs=p_ps.executeQuery();
		Integer count = 0;
		String val = "";
		while(rs.next()){			
			//String str=rs.getString(2)+"("+rs.getString(1)+")";
			String str=rs.getString(1)+" - "+rs.getString(2);
			addComboDatacode(str, rs.getString(1));
			val = rs.getString(1);
			count = count + 1;
		}
		if(count == 1) {
			p_key = val;
		}
		if(p_id.equalsIgnoreCase("sel_id")) {
			p_ngm.ins_sess.gh_id = p_key;
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}
	
	public String getDBcombo_special_code(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			//String str=rs.getString(2)+"("+rs.getString(1)+")";
			String str=rs.getString(1)+" - "+rs.getString(2);
			addComboDatacode(str, rs.getString(1));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}
	public String getDBcombo_quotatype(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			//String str=rs.getString(2)+"("+rs.getString(1)+")";
			String str=rs.getString(2);
			addComboDatacode(str, rs.getString(1));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}
	public String getDBcombo_special_code_designation(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			//String str=rs.getString(2)+"("+rs.getString(1)+")";
			String str=rs.getString(1)+" - "+rs.getString(2);
			addComboDatacode(str, rs.getString(1));
		}
		return getCombo_specl_shift(p_id, p_class, p_name, p_fname,p_key);

	}


	public String getDBcombo_special_three(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			//String str=rs.getString(2)+"("+rs.getString(1)+")";
			String str=rs.getString(2)+" - "+rs.getString(3);
			addComboDatacode(str, rs.getString(1));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}

	public String getDBcombo_special_three_twokey(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			String str=rs.getString(3)+" - "+rs.getString(4);
			String t_room_bed = rs.getString(4);
			if(!t_room_bed.equals("R")){
				str=str+" - "+rs.getString(5);
			}
			addComboDatacode(str, rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(4));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}



	public String getDBcombo_special_demand(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			//String str=rs.getString(2)+"("+rs.getString(1)+")";
			String str=rs.getString(1)+" - "+rs.getString(2);
			addComboDatacode(str, rs.getString(1));
		}
		return getCombo(p_id, p_class, p_name, p_fname,p_key);
	}
	public void addComboDatacode(String p_name,String p_value) {
		comboset.add(p_value);
		combomap.put(p_value, p_name);
	}
	///end gopika
	public String getCombo(String p_id,String p_class,String p_name,String p_fname,String p_key) {
		String option="";
		Iterator<String>it=comboset.iterator();
		while(it.hasNext()){
			String temp=it.next();
			if(temp.equals(p_key)) 
			{ option=option.concat("<option value=\""+temp+"\" selected value=\""+p_key+"\" >"+combomap.get(temp)+"</option>"); }
			else
				option=option.concat("<option value=\""+temp+"\" >"+combomap.get(temp)+"</option>"); 
		}

		String s="<select id=\""+p_id+"\" class=\""+p_class+"\" name=\""+p_name+"\"  onchange=\""+p_fname+"(this.value)"+"\">"+ 
				option+
				"</select>";
		combomap.clear();
		comboset.clear();
		return s;
	}
	public String getCombo_disable(String p_id,String p_class,String p_name) {
		String s="<select id=\""+p_id+"\" class=\""+p_class+"\" name=\""+p_name+"\" disabled=\"disabled\" \">"+
				"<option value=\"0\" >--select--</option>"+ 
				"</select>";
		return s;
	}

	public String getDBcombo_disable(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		if(p_ps!=null)
		{
			ResultSet rs=p_ps.executeQuery();
			while(rs.next()){			
				addComboData(rs.getString(2), rs.getString(1));
			}
			return getCombo_dis(p_id, p_class, p_name, p_fname,p_key);
		}
		else
			return getCombo_disable(p_id, p_class, p_name);
	}
	public String getDBcombo_disablesingle(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		if(p_ps!=null)
		{
			ResultSet rs=p_ps.executeQuery();
			while(rs.next()){			
				addComboData(rs.getString(1), rs.getString(1));
			}
			return getCombo_dis(p_id, p_class, p_name, p_fname,p_key);
		}
		else
			return getCombo_disable(p_id, p_class, p_name);
	}

	public String getCombo_specl(String p_id,String p_class,String p_name,String p_fname,String p_key) {
		String option="";
		Iterator<String>it=comboset.iterator();
		String tempValue = null;
		System.out.println("DEEEEEEEEEEEEEEEESSSSSSSSSSSSp_keyp_keyp_key"+p_key);
		
		while(it.hasNext()){
			String temp=it.next();
			System.out.println("DEEEEEEEEEEEEEEEESSSSSSSSSSSS"+temp);
			tempValue = combomap.get(temp);
			System.out.println("DEEEEEEEEEEEEEEEE"+tempValue);
			if(tempValue != null){
				int i =tempValue.indexOf("-");
				if(i != -1){
					tempValue = tempValue.split("-")[1];
				}
			}
			System.out.println(tempValue);
			if(temp.equals(p_key)) 
			{ option=option.concat("<option value=\""+temp+"\" selected value=\""+p_key+"\" >"+tempValue+"</option>"); }
			else
				option=option.concat("<option value=\""+temp+"\" >"+tempValue+"</option>"); 
		}
		System.out.println("OPTION::"+option);
		String s="<select id=\""+p_id+"\" class=\""+p_class+"\" name=\""+p_name+"\"  onchange=\""+p_fname+"\">"+
				"<option value=\"0\" >--select--</option>"+ 
				option+
				"</select>";
		System.out.println(s);
		combomap.clear();
		comboset.clear();
		return s;
	}

	public String getCombo_specl_shift(String p_id,String p_class,String p_name,String p_fname,String p_key) {
		String option="";
		Iterator<String>it=comboset.iterator();
		while(it.hasNext()){
			String temp=it.next();
			if(temp.equals(p_key)) 
			{ option=option.concat("<option value=\""+temp+"\" selected value=\""+p_key+"\" >"+combomap.get(temp)+"</option>"); }
			else
				option=option.concat("<option value=\""+temp+"\" >"+combomap.get(temp)+"</option>"); 
		}

		String s="<select id=\""+p_id+"\" class=\""+p_class+"\" name=\""+p_name+"\"  onchange=\""+p_fname+"\">"+
				"<option value=\"z\" >--select--</option>"+ 
				option+
				"</select>";
		combomap.clear();
		comboset.clear();
		return s;
	}

	public String getCombo_dis(String p_id,String p_class,String p_name,String p_fname,String p_key) {
		String option="";
		Iterator<String>it=comboset.iterator();
		while(it.hasNext()){
			String temp=it.next();
			if(temp.equals(p_key)) 
			{ option=option.concat("<option value=\""+temp+"\" selected value=\""+p_key+"\" >"+combomap.get(temp)+"</option>"); }
			else
				option=option.concat("<option value=\""+temp+"\" >"+combomap.get(temp)+"</option>"); 
		}

		String s="<select id=\""+p_id+"\" class=\""+p_class+"\" name=\""+p_name+"\" disabled=\"disabled\"   onchange=\""+p_fname+"(this.value)"+"\">"+
				"<option value=\"0\" >--select--</option>"+ 
				option+
				"</select>";
		combomap.clear();
		comboset.clear();
		return s;
	}

	public String getNewformDiv(){
		String s_div="";


		return s_div;

	}

	public String dispSDate(Date p_date){
		if(p_date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			return sdf.format(p_date);
		}
		else
			return null;
	}

	public String getID_Year(Connection p_conn,String p_id) throws Exception {
		Statement st=p_conn.createStatement();
		ResultSet rs1=st.executeQuery(" select extract(year from now()) as year");
		rs1.next();
		Integer l_curyear=rs1.getInt("year");
		st.close();

		PreparedStatement ps=p_conn.prepareStatement("select curvalue from ge_id_year where id=? and YEAR=? for update");
		ps.setString(1, p_id);
		ps.setInt(2,l_curyear);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			Integer l_curvalue=rs.getInt("curvalue");
			return l_curyear.toString()+(++l_curvalue).toString();
		}	
		else {			
			Integer l_val=1;
			PreparedStatement ps2=p_conn.prepareStatement("Insert into ge_id_year values(?,?,?)");
			ps2.setString(1, p_id);
			ps2.setInt(2,l_curyear);
			ps2.setInt(3,l_val);
			executeDB(ps2);
			return l_curyear.toString()+l_val.toString();
		}
	}
	public String getID_Year2(Connection p_conn,String p_id) throws Exception {
		Statement st=p_conn.createStatement();
		ResultSet rs1=st.executeQuery(" select extract(year from now()) as year");
		rs1.next();
		Integer l_curyear=rs1.getInt("year");
		st.close();

		PreparedStatement ps=p_conn.prepareStatement("select curvalue from ge_id_year where id=? and YEAR=? for update");
		ps.setString(1, p_id);
		ps.setInt(2,l_curyear);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			Integer l_curvalue=rs.getInt("curvalue");
			System.out.println(">>>>>>>>>>>>>>>"+l_curvalue);
			return (++l_curvalue).toString();
		}	
		else {			
			Integer l_val=1;
			PreparedStatement ps2=p_conn.prepareStatement("Insert into ge_id_year values(?,?,?)");
			ps2.setString(1, p_id);
			ps2.setInt(2,l_curyear);
			ps2.setInt(3,l_val);
			executeDB(ps2);
			return l_val.toString();
		}
	}
	public boolean updateID_Year(Connection p_conn,String p_id,String p_curvalue) throws Exception {
		boolean l_status		= false;
		int l_curyear			= Integer.parseInt(p_curvalue.substring(0,4));
		int l_curvalue			= Integer.parseInt(p_curvalue.substring(4));
		PreparedStatement ps	= p_conn.prepareStatement("Update ge_id_year set curvalue=? where id=? and year=?");
		ps.setInt(1, l_curvalue);
		ps.setString(2, p_id);
		ps.setInt(3, l_curyear);
		l_status=executeDB(ps);
		ps.close();
		return l_status;	
	}

	public boolean updateID_Year2(Connection p_conn,String p_id,String p_curvalue) throws Exception {
		boolean l_status		= false;
		int l_curyear			= Integer.parseInt(p_curvalue.substring(0,4));
		int l_curvalue			= Integer.parseInt(p_curvalue.substring(4));
		PreparedStatement ps	= p_conn.prepareStatement("Update ge_id_year set curvalue=? where id=? and year=?");
		ps.setInt(1, l_curvalue);
		ps.setString(2, p_id);
		ps.setInt(3, l_curyear);
		l_status=executeDB(ps);
		ps.close();
		return l_status;	
	}
	public String getDBcombohead(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		if(p_ps!=null)
		{
			ResultSet rs=p_ps.executeQuery();
			while(rs.next()){	

				String maj =rs.getString("maj");
				String smj =rs.getString("smj");
				String minr =rs.getString("minr");
				String sub =rs.getString("sub");
				String subsub =rs.getString("subsub");
				String head = maj+"-"+smj+"-"+minr+"-"+sub+"-"+subsub;

				addComboData(head, head);
			}
			return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
		}
		else
			return getCombo_disable(p_id, p_class, p_name);
	}
	//public String getDBcombodesig(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
	//	if(p_ps!=null)
	//	{
	//		ResultSet rs=p_ps.executeQuery();
	//		while(rs.next()){	
	//			
	//			String designation_name =rs.getString("designation_name");
	//			
	//			 
	//			
	//			addComboData(designation_name, designation_name);
	//		}
	//		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	//	}
	//	else
	//		return getCombo_disable(p_id, p_class, p_name);
	//}
	public String getDBcombodesig(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		if(p_ps!=null)
		{ 
			System.out.println(p_ps);
			ResultSet rs=p_ps.executeQuery();
			while(rs.next()){	

				String scale_name =rs.getString("designation_name");
				String scale_code =rs.getString("designation_code");
				String str= scale_code+"-"+scale_name;

				addComboData(str, scale_code);
			}
			//return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
			return getCombo_dis(p_id, p_class, p_name, p_fname,p_key);
		}
		else
			return getCombo_disable(p_id, p_class, p_name);
	}
	public String getDBcombodesignation(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		if(p_ps!=null)
		{ 
			System.out.println(p_ps);
			ResultSet rs=p_ps.executeQuery();
			while(rs.next()){	

				String scale_name =rs.getString("designation_name");
				String scale_code =rs.getString("designation_code");
				String str= scale_code+" - "+scale_name;

				addComboData(str, scale_code);
			}
			//return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
			return getCombo(p_id, p_class, p_name, p_fname,p_key);
		}
		else
			return getCombo_disable(p_id, p_class, p_name);
	}
	public String getDBcombosscale(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		if(p_ps!=null)
		{ 

			ResultSet rs=p_ps.executeQuery();
			while(rs.next()){	

				String scale_name =rs.getString("scale_name");
				String scale_code =rs.getString("scale_code");
				String str= scale_code+" - "+scale_name;

				addComboData(str, scale_code);
			}
			return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
			//return getCombo_dis(p_id, p_class, p_name, p_fname,p_key);
		}
		else
			return getCombo_disable(p_id, p_class, p_name);
	}

	public String getDBcombocategory(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		if(p_ps!=null)
		{ 

			ResultSet rs=p_ps.executeQuery();
			while(rs.next()){	

				String category_name =rs.getString("category_description");
				String category_code =rs.getString("category_code");

				String str = category_code+" - "+category_name;
				addComboData(str, category_code);
			}
			return getCombo(p_id, p_class, p_name, p_fname,p_key);
		}
		else
			return getCombo_disable(p_id, p_class, p_name);
	}

	public void includePg_documents(String page,HttpServletRequest request,HttpServletResponse response)throws Exception {	
		ngm NGM	=	(ngm) request.getAttribute("NGM");
		RequestDispatcher rd=request.getRequestDispatcher("documents/"+page);
		rd.include(request, response);
	}



	public Integer getsequenceid(ngm NGM,String table, String sequence_id) throws Exception {

		PreparedStatement ps1 = NGM.tr_conn.prepareStatement("SELECT max("+sequence_id+") as "+sequence_id+" FROM "+table+"");
		//System.out.println(ps1);
		ResultSet rs = ps1.executeQuery();
		rs.next();
		int  sequence_no = rs.getInt(sequence_id);
		ps1.close();
		return sequence_no +1 ;
	}

	public String getDBcombo_getParentDetails(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		int count = 0;
		String str="";
		while(rs.next()){
			count=1;
			String close_time="";
			String ctime = this.display_timestamp1(rs.getString("createdtime"));
			if(rs.getString("close_time")!=null){
				close_time = this.display_timestamp1(rs.getString("close_time"));
				str=rs.getString("shift_id")+" - "+rs.getString("username")+" - "+ctime+" - "+close_time;
			}

			//addComboData (str,rs.getString("shift_id"));

		}
		return str;

		//return getCombo(p_id, p_class, p_name, p_fname,p_key);


	}
	public Integer getmedicalid(ngm NGM,String table) throws Exception {

		PreparedStatement ps1 = NGM.tr_conn.prepareStatement("SELECT max(medical_id) as medical_id FROM "+table+"");
		ResultSet rs = ps1.executeQuery();
		rs.next();
		int  sequence_no = rs.getInt("medical_id");
		ps1.close();
		return sequence_no +1 ;
	}

	public Integer getseqid(ngm NGM,String table,String id) throws Exception {
		PreparedStatement ps1 = NGM.tr_conn.prepareStatement("SELECT max("+id+") as id FROM "+table+"");
		ResultSet rs = ps1.executeQuery();
		rs.next();
		int  sequence_no = rs.getInt("id");
		ps1.close();
		return sequence_no +1 ;
	}

	public Integer gethraid(ngm NGM,String table) throws Exception {

		PreparedStatement ps1 = NGM.tr_conn.prepareStatement("SELECT max(hra_id) as hra_id FROM "+table+"");
		ResultSet rs = ps1.executeQuery();
		rs.next();
		int  sequence_no = rs.getInt("hra_id");
		ps1.close();
		return sequence_no +1 ;
	}
	public Integer getsequenceidold(ngm NGM,String table) throws Exception {

		PreparedStatement ps1 = NGM.tr_conn.prepareStatement("SELECT max(seq_no) as sequence_id FROM "+table);
		ResultSet rs = ps1.executeQuery();
		rs.next();
		int  sequence_no = rs.getInt("sequence_id");
		ps1.close();
		return (sequence_no) +1 ;
	}

	public String getcurdatestring() {
		/*Calendar calend = Calendar.getInstance();
	SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
	StringBuilder retdate = new StringBuilder();
	 retdate.append(Calendar.DATE);
	 retdate.append(Calendar.MONTH);
	 retdate.append(Calendar.YEAR);
	 retdate.append(Calendar.HOUR);
	 retdate.append(Calendar.MINUTE);
	 retdate.append(Calendar.SECOND);
System.out.println("retdate.DATE()///"+Calendar.DATE);
System.out.println("retdate.MONTH()///"+Calendar.MONTH);
System.out.println("retdate.YEAR()///"+Calendar.YEAR);
System.out.println("retdate.HOUR()///"+Calendar.HOUR);
System.out.println("retdate.MINUTE()///"+Calendar.MINUTE);
System.out.println("retdate.SECOND()///"+Calendar.SECOND);
return retdate.toString();*/

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public String current_date() {
		Calendar calend = Calendar.getInstance();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy"); 
		//Date d1 = new Date();
		return sdf.format(calend.getTime());
	}

	public String getCombo_empty(String p_id,String p_class,String p_name,String p_fname) {

		String s="<select id=\""+p_id+"\" class=\""+p_class+"\" name=\""+p_name+"\"  onchange=\""+p_fname+"\">"+
				"<option value=\"0\" >--select--</option></select>";
		return s;
	}
	public String getDBcombo_special_new(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			addComboData(rs.getString(2), rs.getString(1));
		}
		return getCombo_dis(p_id, p_class, p_name, p_fname,p_key);
	}
	public String getvc(String vc){

		String vch;
		if (vc.equals("V")){
			vch = "voted";
		}else{

			vch="charged";
		}

		return vch;
	}

	public String getPlan(String pnp){
		String plan;
		if (pnp.equals("P")){
			plan= "plan";
		}else{

			plan ="No plan";
		}
		return plan;
	}
	public String getgaz(String gez){
		String gnz="";
		if (gez.equals("Y")){
			gnz= "Gazetted";
		}else{
			gnz ="Non Gazetted";
		}
		return gnz;	
	}

	public void setValues(HttpServletRequest p_request) throws Exception {
		System.out.println("inside ssetValues p_request::::"+p_request);

		/*String p_val	= p_request.getParameter(p_parameter);
	if(p_val.equals(""))
		return null;
	else 
		return p_val;*/
	}


	public String getsecurity_key_email() {
		String security_key=random_security_key(6);
		return security_key;	
	}	
	public String getsecurity_key_mobile() {
		String security_key=random_security_key(6);
		return security_key;	
	}
	public String random_security_key(int p_val) {
		String AB = "0123456789"; 
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( p_val ); 
		for( int i = 0; i < p_val; i++ )  
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) ); 
		return sb.toString(); 
	}
	public Integer get_seqID(Connection p_conn, String p_id) throws Exception {
		Integer l_curvalue =0;
		PreparedStatement ps1 = p_conn.prepareStatement("select curvalue from sequence_id where id=? for update");
		ps1.setString(1, p_id);
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()){
			l_curvalue = rs1.getInt("curvalue");
		}
		ps1.close();
		rs1.close();
		return l_curvalue + 1;
	}
	public String getCombo_category(String p_id,String p_class,String p_name,String p_fname,String p_key,TreeMap<String, String> spmap) {
		String option="";

		Iterator<String> it = spmap.keySet().iterator();

		while(it.hasNext()){
			String temp=it.next();
			String temp1 = "";
			temp1 = ""+temp;
			if(temp1.equals(p_key)) 
			{ option=option.concat("<option style='width:100px !important; overflow-x:auto;' value=\""+temp+"\" selected value=\""+p_key+"\" >"+spmap.get(temp)+"</option>"); }
			else
				option=option.concat("<option style='width:100px !important; overflow-x:auto;' value=\""+temp+"\" >"+spmap.get(temp)+"</option>"); 
		}

		String s="<select id=\""+p_id+"\" class=\""+p_class+"\" name=\""+p_name+"\"  onchange=\""+p_fname+"\">"+
				"<option value=\"z\" >--select--</option>"+ 
				option+
				"</select>";
		combomap.clear();
		comboset.clear();
		return s;
	}

	public String getCombo_specl_int(String p_id,String p_class,String p_name,String p_fname,String p_key,TreeMap<Integer, String> spmap) {
		String option="";

		Iterator<Integer> it = spmap.keySet().iterator();


		//    Object obj = itr.next();
		// System.out.println(map.get(obj));
		//

		//Iterator<String>it=comboset.iterator();

		while(it.hasNext()){
			Integer temp=it.next();
			String temp1 = "";
			temp1 = ""+temp;
			if(temp1.equals(p_key)) 
			{ option=option.concat("<option value=\""+temp+"\" selected value=\""+p_key+"\" >"+spmap.get(temp)+"</option>"); }
			else
				option=option.concat("<option value=\""+temp+"\" >"+spmap.get(temp)+"</option>"); 
		}

		String s="<select id=\""+p_id+"\" class=\""+p_class+"\" name=\""+p_name+"\"  onchange=\""+p_fname+"\">"+
				"<option value=\"z\" >--select--</option>"+ 
				option+
				"</select>";
		combomap.clear();
		comboset.clear();
		return s;
	}
	public String getDBcombo_category(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		System.out.println(p_ps);
		TreeMap<String, String> spmap = new TreeMap<String, String>();
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){	
			String str=rs.getString(4)+" - "+rs.getString(2);
			String key_value = rs.getString(1);
			System.out.println("########## "+key_value);
			spmap.put(key_value,str);
		}	
		System.out.println("DDDDDDDD L:"+p_key);
		return getCombo_category(p_id, p_class, p_name, p_fname,p_key,spmap);
	}

	public String getDBcombo_special_code_int(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		System.out.println(p_ps);
		TreeMap<Integer, String> spmap = new TreeMap<Integer, String>();
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){	
			String str=rs.getString(1)+" - "+rs.getString(2);
			String key_value = rs.getString(1);
			spmap.put(Integer.parseInt(key_value),str);
		}	
		System.out.println("DDDDDDDD L:"+p_key);
		return getCombo_specl_int(p_id, p_class, p_name, p_fname,p_key,spmap);
	}
	public boolean updateseqID(Connection p_conn, String p_id, Integer p_curvalue) throws Exception {
		boolean l_status = false;
		PreparedStatement ps1 = p_conn.prepareStatement("Update sequence_id set curvalue=? where id=?");
		ps1.setInt(1, p_curvalue);
		ps1.setString(2, p_id);
		l_status = ps1.execute();
		ps1.close();
		return l_status;
	}

	public Integer get_seqcurrent(Connection p_conn, String p_id) throws Exception {
		Integer l_curvalue =0;
		PreparedStatement ps1 = p_conn.prepareStatement("select curvalue from sequence_id where id=? for update");
		ps1.setString(1, p_id);
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()){
			l_curvalue = rs1.getInt("curvalue");
		}
		ps1.close();
		rs1.close();
		return l_curvalue;
	}
	//role arraylist settings

	public String getrolelist(ArrayList<String> arrl )
	{
		String str= "";
		Iterator<String> itr = arrl.iterator();
		int i=0;
		while(itr.hasNext()){
			//System.out.println(itr.next());
			//str=str+"'"+itr.next()+"'";
			if(i==0)
				str=str+"'"+itr.next()+"'";
			else
			{	
				str=str+",'"+itr.next()+"'";
			}
			i++;

		}
		return str;
	}
	public String display_date(String dates) throws Exception
	{
		String oldstring = dates;
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldstring);

		String newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);
		return newstring;

	}
	public String display_timestamp(String dates) throws Exception
	{
		String oldstring = dates;
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);

		String newstring = new SimpleDateFormat("dd-MM-yyyy").format(date);
		return newstring;

	}
	public String display_timestamp1(String dates) throws Exception
	{
		String oldstring = dates;
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);

		String newstring = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
		return newstring;

	}

	public int setNullvalue1(Object p_val) throws Exception {

		if(p_val==null)
			return 0;
		else 
			return (Integer) p_val;
	}
	public String setNullvalue(Object p_val) throws Exception {

		if(p_val==null)
			return null;
		else 
			return p_val.toString();
	}
	public String getDBcombo_block_id(String p_id,String p_class,String p_name,String p_fname, PreparedStatement p_ps,String p_key) throws Exception{
		ResultSet rs=p_ps.executeQuery();
		while(rs.next()){			
			addComboData(rs.getString("block_name"), rs.getString("block_id"));
		}
		return getCombo_specl(p_id, p_class, p_name, p_fname,p_key);
	}



}//end class



