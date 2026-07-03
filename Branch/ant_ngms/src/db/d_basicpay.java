package db; 

import java.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.db_validation;
import common.utility;


public class d_basicpay extends db_validation
{
	public String da_id,grade_title,effect_date_from,effect_date_to,basic_pay_from,basic_pay_to,da_cal_mode,da_value;
	ngm NGM;
	utility 		ins_util;
	
	public d_basicpay(ngm p_ngm){		
		NGM=p_ngm;
		ins_util = new utility();
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into basicpay_da values(?,?,to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),?,?,?,?)");
		ps.setString(1, da_id);
		ps.setString(2, grade_title);
		ps.setString(3, effect_date_from);
		ps.setString(4, effect_date_to);
		ps.setString(5, basic_pay_from);
		ps.setString(6, basic_pay_to);
		ps.setString(7, "D");
		ps.setString(8, da_value);
		System.out.println("basicpay pssssssssssssssss    "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;	
	} 
	
	public boolean chkCodeDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Select * from basicpay_da where vc_id=?");
		ps.setString(1, da_id);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	}
	
	public boolean updateDB() throws Exception {	
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update basicpay_da set grade_title=?,effect_date_from=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),effect_date_to=to_timestamp(?,'dd-MM-yyyy HH24:MI:ss'),basis_pay_from=?,basis_pay_to=? ,da_cal_mode=?,da_value=? where da_id=?");
		ps.setString(1, grade_title);
		ps.setString(2, effect_date_from);
		ps.setString(3, effect_date_to);
		ps.setString(4, basic_pay_from);
		ps.setString(5, basic_pay_to);
		ps.setString(6, da_cal_mode);
		ps.setString(7, da_value);
		ps.setString(8, da_id);
		System.out.println("psssssssssss   "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception{	
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from basicpay_da where da_id=?");
		ps.setString(1, da_id);
		System.out.println("pppppppppppsssssssssss   "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	
	public boolean selectDB() throws Exception {		
		boolean l_status=false;	
	    PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from basicpay_da where da_id=??");
		ps.setString(1, da_id);
		System.out.println("psssssss     "+ps);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			
			da_id =rs.getString("da_id");   
			grade_title=rs.getString("grade_title");
			effect_date_from =rs.getString("effect_date_from");
			effect_date_to  =rs.getString("effect_date_to");
			basic_pay_from =rs.getString("basis_pay_from");
			basic_pay_to  =rs.getString("basis_pay_to");
			da_cal_mode =rs.getString("da_cal_mode");
			da_value =rs.getString("da_value");
			
		
					
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	

}