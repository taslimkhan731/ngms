package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.log.SysoLogger;

import common.common;
import common.utility;
import db.*;

public class m_shifting extends ngmodel{
	utility 		ins_util;
	common ins_con;
	d_shiftmaster ins_shft;
	d_shiftvsuserid ins_shftuser;
	 public String p_gh_id = "";

	public m_shifting(ngm p_ngm){
		NGM		= p_ngm;
		ins_con=new common(NGM);
		ins_shft=new d_shiftmaster(NGM);
		 ins_shftuser = new d_shiftvsuserid(NGM);
		ins_util		= NGM.ins_utility;
	}
	
	public void VJ_showlist(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_shifting", "Validation fails", "showlist", NGM);
		}
	}
	public void VJ_reportlist(String pgh_id,String ppage_no) throws Exception
	{
		if(!(NGM.ins_dbval.val_ghid(pgh_id)&&NGM.ins_dbval.val_pageno(ppage_no)))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_shifting", "Validation fails", "reportlist", NGM);
		}
	}
	public void VJ_showform(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_shifting", "Validation fails", "showlist", NGM);
		}
	}
	public void VJ_changeshift(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_shifting", "Validation fails", "changeshift", NGM);
		}
	}
	public void VJ_closeshift(String pgh_id) throws Exception
	{
		if(!NGM.ins_dbval.val_ghid(pgh_id))
		{
			NGM.ins_sess.ERRMSG = " Validation fails.";
			throw NGM.ins_utility.setException("s_shifting", "Validation fails", "closeshift", NGM);
		}
	}
	
	
	
	
	public boolean postshift(HttpServletRequest request)throws Exception{
		boolean retval  = false;
		String shift_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "shift_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "shift_id", Integer.parseInt(shift_id));
		String parent_id=ins_shft.getparent_id();
		if(parent_id.equals("")){
			parent_id= "0";
			
		}
		ins_shft.shift_id = shift_id;
		ins_shft.gh_id = ins_util.setNullParameter(request,"gh_id");
		ins_shft.sel_block = ins_util.setNullParameter(request,"sel_block");
		ins_shft.createdby =NGM.ins_sess.user_id;
		ins_shft.openbal_refund =ins_util.setNullParameter(request,"bal_rfd");
		ins_shft.openbal_regular = ins_util.setNullParameter(request,"bal_reg");;
		ins_shft.openbal_total = ins_util.setNullParameter(request,"bal_tot");
		ins_shft.par_shiftid =  parent_id;
		ins_shft.status = "A";
		ins_shft.from_time =ins_util.setNullParameter(request,"opn_time");
		ins_shft.to_time = ins_util.setNullParameter(request,"cls_time");
		if(ins_shft.insertDB()){
			retval = true;
		}
		
	
		ins_shftuser.user_id = NGM.ins_sess.user_id;
		ins_shftuser.shift_id = shift_id;
		if(ins_shftuser.insertDB()){
    		retval = true;
    	
    }
		String[] values=request.getParameterValues("chk");
		System.out.println("PP values "+values);
		if(values!=null){
			for(int i=0;i<values.length;i++)
		       {
		        	String id = values[i];
		        			     
				    if (id!= null){
				    	System.out.println("valuessss"+id);
				    	ins_shftuser.user_id = id;
				    	ins_shftuser.shift_id = shift_id;
				    	if(ins_shftuser.insertDB())
				    		retval = true;
				    	
				    }
				    
				   

				}
		}
        
		return retval;
		
		
	}
	
	public boolean select_shift(HttpServletRequest request)throws Exception{
		boolean retval = false;
		ins_shft.gh_id=ins_util.setNullParameter(request,"gh_id");
		ins_shft.sel_block=ins_util.setNullParameter(request,"sel_block");
		ins_shft.status="A";
		if(ins_shft.selectDB_active()){
			retval= true;
		}
		
		return retval;
		
	}
	public boolean select_shift_close(HttpServletRequest request)throws Exception{
		boolean retval = false;
		ins_shft.gh_id=ins_util.setNullParameter(request,"gh_id");
		ins_shft.sel_block=ins_util.setNullParameter(request,"sel_block");
		String block_id = request.getParameter("sel_block");
		System.out.println("%%%%%%%%%%%%%"+block_id);
		ins_shft.status="A";
		if(ins_shft.selectDB_active_user()){
			retval= true;
		}
		
		return retval;
		
	}
	
	
	public boolean postupdate(HttpServletRequest request)throws Exception{
		boolean retval  = false;
		 
		ins_shft.shift_id = ins_util.setNullParameter(request,"shift_id");
		ins_shft.gh_id = ins_util.setNullParameter(request,"gh_id");
		 
		p_gh_id = ins_shft.gh_id;
		
		ins_shft.openbal_refund =ins_util.setNullParameter(request,"bal_rfd");
		ins_shft.openbal_regular = ins_util.setNullParameter(request,"bal_reg");;
		ins_shft.openbal_total = ins_util.setNullParameter(request,"bal_tot");
		ins_shft.from_time =ins_util.setNullParameter(request,"opn_time");
		ins_shft.to_time = ins_util.setNullParameter(request,"cls_time");

	 
		if(ins_shft.updateDB()){
			retval = true;
		}
		ins_shftuser.shift_id = ins_util.setNullParameter(request,"shift_id");
		if(ins_shftuser.deleteDB()){
			
			ins_shftuser.user_id = NGM.ins_sess.user_id;
			ins_shftuser.shift_id = ins_shftuser.shift_id;
			if(ins_shftuser.insertDB()){
	    		retval = true;
			}
	    		
		String[] values=request.getParameterValues("chk");
		 System.out.println("values lenghth......................"+ values);
		 if(values!=null){
			 for(int i=0;i<values.length;i++)
		       {
		        	String id = values[i];
		        			     
				    if (id!= null){
				    	System.out.println("valuessss"+id);
				    	ins_shftuser.user_id = id;
				    	ins_shftuser.shift_id = ins_util.setNullParameter(request,"shift_id");
				    	
				    	
				    		
				    	if(ins_shftuser.insertDB())
				    		retval = true;
				    	 
				    }
				    
		       }  
		 }
		   

			}
		return retval;
	}
	public String getshift(String shift_id){
		 
		String criteria ="" ;
		String join = " and ";
	 		 
		try{
			if (!shift_id.equals("")) {
				criteria += join +"shift_id ='"+shift_id+"'";
			}
		}catch (NullPointerException x)	{}
		
		return criteria ;
	}
	public boolean close_shift(HttpServletRequest request)throws Exception{
		boolean retval = false;
		ins_shft.gh_id=ins_util.setNullParameter(request,"gh_id");
		ins_shft.sel_block=ins_util.setNullParameter(request,"sel_block");
		ins_shft.shift_id=ins_util.setNullParameter(request,"shift_id");
		ins_shft.status="C";
		
		if(ins_shft.updateDB_close()){
			
			retval= true;
		}
		
		return retval;
		
	}
	
	public String searchTC(HttpServletRequest request)throws Exception{
		  
	 	String shift_id = (String) request.getParameter("shift_id");	 
		String name = (String)request.getParameter("name");	 
		String date=(String)request.getParameter("date");
		if(!name.equals("")){
			name = "%"+name+"%";
		}
		if(!date.equals("")){
			date = ins_con.display_time_zonenew(date);
		}
		 
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!name.equals("")) {
						criteria += join +" upper(createdby) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!shift_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" shift_id = '"+shift_id+"'";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						//criteria += join +" c.from_date = '"+date+"'"; // 
						criteria += join +" date('"+date+"') between date(from_time) and date(to_time)";
								//" date(arrival_time_date) = date('"+date+"')";  //"'"+date+"' between date(from_date) and date(to_date)";
					}
				}catch (NullPointerException x)	{} 
				
				
				 System.out.println(" TC                "+criteria);
				 return criteria;
				
}
	
}

