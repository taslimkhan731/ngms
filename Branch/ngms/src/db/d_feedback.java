package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;

import common.db_validation;
import common.utility;

public class d_feedback extends db_validation{
	public String feedback_id,req_id,mobile_no,gh_id,block_id,status,feedback_sms,replied_sms;
	public int food,clean,keep_room,conduct,checkin_out;
	public boolean view;
	ngm NGM;
	utility 	  ins_util;
	public JSONArray array1 = new JSONArray();
	public d_feedback(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}
	
	public String selectDB_mobDetails(String req_id,String feedbackid) throws Exception {		
		String mobile_no="";
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select mobile_no from feedback_system where req_id = ? and feedback_id=?");
		ps.setString(1, req_id);
		ps.setString(2, feedbackid);
		System.out.println("SSSSSSSSSSS  "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			mobile_no = rs.getString("mobile_no");
		}
		rs.close();
		ps.close();
		return mobile_no;
	} 
	public boolean update_feedback_replied() throws Exception {		
		boolean l_status=false;	
		PreparedStatement pss=NGM.tr_conn.prepareStatement("update feedback_system set replied_sms=?,replied_on=now(),status=?,view=false where req_id=? and feedback_id=?");
	    pss.setString(1,replied_sms);
	    pss.setString(2, "Closed");
		pss.setString(3, req_id);
		pss.setString(4, feedback_id);
		System.out.println("+++++++++++++++++++++++++++++++ pss "+pss);
		l_status=NGM.ins_utility.executeDB(pss);
		pss.close();
		return l_status;		
	} 
}
