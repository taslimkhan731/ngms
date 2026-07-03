package model;
import java.net.InetAddress;


import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.itextpdf.text.log.SysoLogger;
import com.sun.jersey.json.impl.writer.JsonEncoder;

import common.common;
import common.utility;
import common.validation;
import db.*;

public class m_view extends ngmodel{
	utility 		ins_util;
	public m_view(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
				
		ins_val =new validation();
		 	
		
		}
		
	public String search(HttpServletRequest request)throws Exception{
		  
		common con = new common(NGM);
	 	String req_id = (String) request.getParameter("req_id");	 
		String name = (String)request.getParameter("name");	 
		String designation=(String)request.getParameter("designation");
		String date=(String)request.getParameter("date");
		if(!date.equals("")){
			date = con.display_date_calander(date);
		}
		String criteria ="" ;
				String join = "";
			 		 
				try{
					if (!name.equals("")) {
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" upper(c.name) like upper('%"+name+"%')";
					}
				}catch (NullPointerException x)	{}
				
				try{
					if (!req_id.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" c.req_id like '"+req_id+"'";
					}
				}catch (NullPointerException x)	{}
				
	//--------ATHIRA EDITED------
				
				
				
//				try{
//					if (!rno.equals("")){
//						if (!criteria.equals("")) {
//							criteria += join +" and";
//						}
//						criteria += join +" c.room_no like '"+rno+"'";
//					}
//				}catch (NullPointerException x)	{}
				try{
					if (!designation.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						criteria += join +" upper(c.designation) like upper('%"+designation+"%')";
					}
				}catch (NullPointerException x)	{}
				
				
// 	--------ATHIRA EDITED---END-------			
				try{
					if (!date.equals("")){
						if (!criteria.equals("")) {
							criteria += join +" and";
						}
						//criteria += join +" c.from_date = '"+date+"'"; // 
						criteria += join +" date('"+date+"') between date(d.from_date) and date(d.to_date)";
					}
				}catch (NullPointerException x)	{}
				
				
				 System.out.println("cccccccccccccccccccccc"+criteria);
				 return criteria;
				
}
}