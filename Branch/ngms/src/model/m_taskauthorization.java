package model;

import javax.servlet.http.HttpServletRequest;

import common.common;
import common.utility;


import db.d_servlet_task;

import db.m_uservsrole;
import db.ngm;


public class m_taskauthorization extends ngmodel {
	public String slno,serv_name,task_id,role;
	 utility 		ins_util;
	 d_servlet_task ins_servlet_task;
	 m_uservsrole ins_uservsrole;
	 
	public m_taskauthorization(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		  = NGM.ins_utility;
		ins_servlet_task = new d_servlet_task(NGM);
		ins_uservsrole	=new m_uservsrole(NGM);
		}
		
public boolean saveCreation(HttpServletRequest p_request) throws Exception{
	
    ins_servlet_task.serv_name=ins_util.setNullParameter(p_request, "servletname");
	ins_servlet_task.task_id=ins_util.setNullParameter(p_request, "task_id");
	
		String select[] = p_request.getParameterValues("rolecode"); 
		if (select != null && select.length != 0) {
		for (int i = 0; i < select.length; i++) {
		        ins_servlet_task.slno = NGM.ins_utility.get_seqID(NGM.tr_conn,"slno"); 
		        NGM.ins_utility.updateseqID(NGM.tr_conn, "slno",ins_servlet_task.slno);
		         ins_servlet_task.role_code=(select[i]);
        	if(!ins_servlet_task.insertDB())return false;    	
         }
		}
		return true;
	}
	
	
	
	public void setPostCreation(HttpServletRequest p_request) throws Exception{
		System.out.println("      setPostCreation            ");

		this.serv_name					= ins_util.setNullParameter(p_request, "servletname");
		System.out.println("this.user_id     "+this.serv_name);
		this.task_id              	    =ins_util.setNullParameter(p_request, "task_id");
		System.out.println("this.user_id     "+this.task_id);
		this.role						=ins_util.setNullParameter(p_request, "rolecode");
		System.out.println("this.user_id     "+this.role);
	}
	
	
	public boolean chkname(HttpServletRequest p_request) throws Exception {
		ins_servlet_task.serv_name=ins_util.setNullParameter(p_request, "servletname");
		ins_servlet_task.task_id=ins_util.setNullParameter(p_request, "task_id");
		ins_servlet_task.role_code=ins_util.setNullParameter(p_request, "rolecode");
		if(!ins_servlet_task.chknameDB()) 
			return false;
		else  
			return true;	
	}
	
	/*public boolean chkname1(HttpServletRequest p_request) throws Exception {
		ins_servlet_task.serv_name=ins_util.setNullParameter(p_request, "serv_name");
		ins_servlet_task.task_id=ins_util.setNullParameter(p_request, "task_id");
		ins_servlet_task.role_code=ins_util.setNullParameter(p_request, "rolecode");
		if(!ins_servlet_task.chknameDB()) 
			return false;
		else  
			return true;	
	}*/
	
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		
		ins_servlet_task.serv_name=ins_util.setNullParameter(p_request, "serv_name");
		ins_servlet_task.task_id=ins_util.setNullParameter(p_request, "task_id");
		
		System.out.println("slno     "+slno);
		if(!ins_servlet_task.deleteDB())return false;
		
	  	return true;
	}
	
	
	public String searchrequestlist(HttpServletRequest request)throws Exception{
	common con = new common(NGM);  
	String servlet = (String)request.getParameter("servlet");	 
		System.out.println("servlet     "+servlet);
	String taskid=(String)request.getParameter("taskid");
	System.out.println("taskid          "+taskid);
	String Role_code=(String)request.getParameter("Role_code");
	System.out.println("Role_code        "+Role_code);
	
	 
			String criteria ="" ;
			String join = "";
		 	 
			try{
				if (!servlet.equals("")) {
					criteria += join +" upper(serv_name) like upper('%"+servlet+"%')";
				}
			}catch (NullPointerException x)	{}
			
			try{
				if (!taskid.equals("")){
					if (!criteria.equals("")) {
						criteria += join +" and";
					}
					criteria += join +" task_id like '"+taskid+"'";
					}
					
				
			}catch (NullPointerException x)	{}
			
			
			
			try{
				if (!Role_code.equals("")){
					if (!criteria.equals("")) {
						criteria += join +" and";
					}
					//criteria += join +" c.from_date = '"+date+"'"; // 
					//criteria += join +"'"+role_code+"' between date(created_date) and date(created_date)";
					criteria += join +" role_code like '"+Role_code+"'";
					//criteria += join +" created_date= '"+date+"'";
				}
			}catch (NullPointerException x)	{}
			
			
			 System.out.println("cccccccccccccccccccccc"+criteria);
			 return criteria;
	}
	public boolean update_authorizationdetails(HttpServletRequest p_request) throws Exception{
		
		String  old_serv_name = p_request.getParameter("old_serv_name");
		String  old_task_id = p_request.getParameter("old_task_id");
		
		ins_servlet_task.serv_name=ins_util.setNullParameter(p_request, "serv_name1");
		ins_servlet_task.task_id=ins_util.setNullParameter(p_request, "task_id1");
		String serv_name=ins_servlet_task.serv_name;
				String task_id=ins_servlet_task.task_id;
		System.out.println(old_serv_name);
		System.out.println(old_task_id);
		System.out.println(serv_name);
		System.out.println(task_id);
		//String select[] = p_request.getParameterValues("rolecode"); 
		 /* System.out.println(select[i]); 
		   	ins_servlet_task.role_code=(select[i]);*/
		  	if(!ins_servlet_task.deleteforupdateDB(old_serv_name,old_task_id))return false;
		   	System.out.println("deleted....");
		  	String select[] = p_request.getParameterValues("rolecode"); 
			if (select != null && select.length != 0) {
			for (int i = 0; i < select.length; i++) {
			        ins_servlet_task.slno = NGM.ins_utility.get_seqID(NGM.tr_conn,"slno"); 
			        NGM.ins_utility.updateseqID(NGM.tr_conn, "slno",ins_servlet_task.slno);
			         ins_servlet_task.role_code=(select[i]);
	        	if(!ins_servlet_task.insertDB())return false;    	
	         }
			}
				return true;
			
		   	
		   	
		   	
		   	
		   	
		   	
		   	//if(!ins_servlet_task.selectupdateDB(old_serv_name,old_task_id))return false;  
		  	//else{
		   /*ins_servlet_task.slno = NGM.ins_utility.get_seqID(NGM.tr_conn,"slno"); 
	        NGM.ins_utility.updateseqID(NGM.tr_conn, "slno",ins_servlet_task.slno);*/
		  /* 	System.out.println(""+ins_servlet_task.serv_name+""+ins_servlet_task.task_id);
        	
        	//if(!ins_servlet_task.updateDB(old_serv_name,old_task_id))return false;    	
        	System.out.println("Split1 Element "+i+" :"+select[i]);*/
        	//}
       
	}

	public boolean chkname1(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		ins_servlet_task.serv_name=ins_util.setNullParameter(request, "serv_name");
		ins_servlet_task.task_id=ins_util.setNullParameter(request, "task_id");
		ins_servlet_task.role_code=ins_util.setNullParameter(request, "rolecode");
		if(!ins_servlet_task.chknameDB()) 
			return false;
		else  
		return true;
	}
	

}