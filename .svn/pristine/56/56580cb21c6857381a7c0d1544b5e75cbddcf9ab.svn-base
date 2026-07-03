package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_userdeatilscreation extends ngmodel{
	public String refno,message1, message2, t_capcha,t_password,t_repassword,t_smscheck,t_emailcheck;
	public String ins_form,user_id,username,orgpswd,cpswd,reason,status,email_id,mob,dob;
	public String role,dept_code,office_code,sectionid;
	utility 		ins_util;
	m_user_details ins_userdetails;
	m_uservsrole ins_uservsrole;
	m_uservsgh ins_uservsgh;
	public m_userdeatilscreation(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_userdetails  = new m_user_details(NGM);
		ins_uservsrole	=new m_uservsrole(NGM);
		ins_uservsgh	=new m_uservsgh(NGM);
	}

	public boolean saveCreation(HttpServletRequest p_request) throws Exception{

		ins_userdetails.userid=ins_util.setNullParameter(p_request, "a");
		ins_userdetails.username=ins_util.setNullParameter(p_request, "b");
		ins_userdetails.password = ins_util.setNullParameter(p_request, "c");
		ins_userdetails.email_id=ins_util.setNullParameter(p_request, "d");
		ins_userdetails.mob=ins_util.setNullParameter(p_request, "e");
		ins_userdetails.status=ins_util.setNullParameter(p_request, "f");
		String select[] = p_request.getParameterValues("rolecode"); 
		String select2[] = p_request.getParameterValues("gh");
		if(select == null){
			return false;
		}
		if(select.length == 0){
			return false;
		}
		if(select2 == null){
			return false;
		}
		if(select2.length == 0){
			return false;
		}
		if (select != null && select.length != 0) {
			System.out.println("You have selected: ");
			for (int i = 0; i < select.length; i++) {
				System.out.println(select[i]); 

				ins_uservsrole.userid=ins_util.setNullParameter(p_request, "a");
				ins_uservsrole.role_code=(select[i]);
				if(!ins_uservsrole.insertDB())return false;    	

				System.out.println("Split1 Element "+i+" :"+select[i]);
			}
		}
		if (select2 != null && select2.length != 0) {
			for (int i = 0; i < select2.length; i++) {
				System.out.println(select2[i]); 

				ins_uservsgh.userid=ins_util.setNullParameter(p_request, "a");
				ins_uservsgh.gh_id=(select2[i]);
				if(!ins_uservsgh.insertDB())return false;    	

				System.out.println("Split1 Element "+i+" :"+select2[i]);
			}
		}
		System.out.println("HIDVALUE      "+ins_uservsrole.role_code);

		if(!ins_userdetails.insertDB())return false;

		return true;
	}
	public boolean isvalidNewPassword()throws Exception{
		if(orgpswd.equals(cpswd)){
			return true;
		}
		else{
			return false;
		}
	}
	public void setPostCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("      setPostCreation            ");

		this.user_id					= ins_util.setNullParameter(p_request, "a");
		System.out.println("this.user_id     "+this.user_id);
		this.username              	    =ins_util.setNullParameter(p_request, "b");
		this.orgpswd					= ins_util.setNullParameter(p_request,"c");
		this.cpswd						= ins_util.setNullParameter(p_request,"c");
		this.role						=ins_util.setNullParameter(p_request, "f");

	}

	public void setPostPassword(HttpServletRequest p_request) throws Exception{
		this.user_id					= ins_util.setNullParameter(p_request, "a");
		this.username              	    = ins_util.setNullParameter(p_request, "u");
		this.orgpswd					= ins_util.setNullParameter(p_request,"c");
		this.cpswd						= ins_util.setNullParameter(p_request,"c1");

	}
	public boolean usernamenotexists(HttpServletRequest p_request) throws Exception{	
		//this.user_id				= this.user_id
		System.out.println("user_id         "+this.user_id);
		if(NGM.ins_utility.checkConstrain_reg(NGM.tr_conn, "m_user_details", "userid", this.user_id)){ 
			return false;
		}
		else{
			return true;
		}

	}
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_uservsrole.userid=ins_util.setNullParameter(p_request, "user_id");
		ins_uservsgh.userid=ins_util.setNullParameter(p_request, "user_id");
		System.out.println("1     "+ins_uservsrole.userid);
		System.out.println("2     "+ins_uservsgh.userid);
		String select[] = p_request.getParameterValues("rolecode"); 
		String select2[] = p_request.getParameterValues("gh"); 
		if(select == null){
			return false;
		}
		if(select.length == 0){
			return false;
		}
		if(select2 == null){
			return false;
		}
		if(select2.length == 0){
			return false;
		}
		if(!ins_uservsrole.deleteDB())return false;
		if(!ins_uservsgh.deleteDB())return false;
		ins_userdetails.userid=ins_util.setNullParameter(p_request, "a");
		ins_userdetails.username=ins_util.setNullParameter(p_request, "b");
		ins_userdetails.password=ins_util.setNullParameter(p_request, "password");
		ins_userdetails.email_id=ins_util.setNullParameter(p_request, "e");
		ins_userdetails.mob=ins_util.setNullParameter(p_request, "f");
		ins_userdetails.status=ins_util.setNullParameter(p_request, "d");
		if (select != null && select.length != 0) {
			System.out.println("You have selected: ");
			for (int i = 0; i < select.length; i++) {
				System.out.println(select[i]); 
				ins_uservsrole.userid=ins_util.setNullParameter(p_request, "a");
				ins_uservsrole.role_code=(select[i]);
				System.out.println("DELTEEEEEEEEEEEEEEEEEEEEEEEEEE       ");
				if(!ins_uservsrole.insertDB())return false;    	

				System.out.println("Split1 Element "+i+" :"+select[i]);
			}
		}
		if (select2 != null && select2.length != 0) {
			System.out.println("You have selected: ");
			for (int i = 0; i < select2.length; i++) {
				System.out.println(select2[i]); 

				ins_uservsgh.userid=ins_util.setNullParameter(p_request, "a");
				ins_uservsgh.gh_id=(select2[i]);
				System.out.println("DELTEEEEEEEEEEEEEEeeeeeeeeeeeeee       "+ins_uservsgh.userid);
				if(!ins_uservsgh.insertDB())return false;    	

				System.out.println("Split1 Element "+i+" :"+select2[i]);
			}
		}
		if(!ins_userdetails.updateDB())return false;
		return true;
	}
	//reset password
	public boolean updateReset(HttpServletRequest p_request) throws Exception{
		ins_userdetails.userid=ins_util.setNullParameter(p_request, "a");
		ins_userdetails.password=ins_util.setNullParameter(p_request, "c");
		ins_userdetails.username=ins_util.setNullParameter(p_request, "u");
		ins_userdetails.status=ins_util.setNullParameter(p_request, "s");
		ins_userdetails.email_id=ins_util.setNullParameter(p_request, "e");
		ins_userdetails.mob=ins_util.setNullParameter(p_request, "m");
		if(!ins_userdetails.updateDB_R())return false;
		return true;
	}
	//end

	public String search(HttpServletRequest request)throws Exception{

		String user_id = (String) request.getParameter("user_id");	 
		String name = (String)request.getParameter("name");	 
		String criteria ="" ;
		String join = "";

		try{
			if (!name.equals("")) {
				criteria += join +" upper(username) like upper('%"+name+"%')";
			}
		}catch (NullPointerException x)	{}

		try{
			if (!user_id.equals("")){
				if (!criteria.equals("")) {
					criteria += join +" and";
				}
				criteria += join +" userid like '"+user_id+"'";
			}
		}catch (NullPointerException x)	{}


		System.out.println("cccccccccccccccccccccc"+criteria);
		return criteria;

	}


	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_userdetails.userid=ins_util.setNullParameter(p_request, "user_id");
		ins_uservsgh.userid=ins_util.setNullParameter(p_request, "user_id");
		ins_uservsrole.userid=ins_util.setNullParameter(p_request, "user_id");
		System.out.println("rollllllllllllllllllllllll     "+ins_userdetails.userid);
		if(!ins_userdetails.deleteDB())return false;
		if(!ins_uservsrole.deleteDB())return false;
		if(!ins_uservsgh.deleteDB())return false;
		return true;
	}
}	




