package slet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.m_importdata;
import db.d_pubrequest;
import db.ngm;


@WebServlet("/autocomplete")
public class autocomplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public JSONArray array1 = new JSONArray();
	//JSONArray jarray;
	JSONParser parser=new JSONParser();
	public JSONObject json= new JSONObject();  
	 
	ngm	NGM	= new ngm();
    

   
    public boolean importdata(String psdata)throws Exception{ 
    	boolean retval = false;
    	
    		JSONArray jobj = new JSONArray();
    		array1 = new JSONArray();
    
    		PreparedStatement ps=NGM.tr_conn.prepareStatement("select distinct name,age,gender,designation,department,address from guest_details where upper(name) like upper(?) limit 5");
    		ps.setString(1,"%"+ psdata+"%");
    		System.out.println("Query"+ps);
    		ResultSet rs=ps.executeQuery();

    		ResultSetMetaData rsmet = rs.getMetaData();
    		int col_count = rsmet.getColumnCount();
    		String fieldname="";
    		int rownum =0;
    		int count=0;
			

    		 while(rs.next()){ 
    			
    			 JSONObject jsonObj1 = new JSONObject();
    				for (int i = 1; i < col_count +1; i++ ) 
    				{ 	 
    				      fieldname  = rsmet.getColumnName(i);
    				      String fieldvalue = rs.getString(fieldname)!=null?rs.getString(fieldname):"";
    				      jsonObj1.put(fieldname, fieldvalue);
    					 
    				}
    					
    				
    				array1.add(jsonObj1);
    				count = count +1;
    				
    				
    				System.out.println("Value of Array 1 is: "+array1);
    				
    		retval = true;
    		}
    		return retval;
    		}
    	 
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="autocomplete";
		NGM.ins_writer=response.getWriter();
		 
		   
		try {
				task=request.getParameter("task");
				System.out.println(task);
				NGM.ins_utility.validate_task(task,NGM);
				String pobj= request.getParameter("pobj");
				System.out.println("PPOB"+pobj);
				if(task.equals("abc"))
				{		
					jsp_task=0;
					
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					
					String s_data =request.getParameter("sdata");
								 // s_data validate
							if(this.importdata(s_data)){						 
										System.out.println("Value of Array1 Inside POST: "+this.array1);
										NGM.ins_writer.write(this.array1.toJSONString());
									}
								else{
									NGM.ins_writer.write("Message: Please contact administrator...");
								}

					}
				 
			 
				 
									 
				 NGM.tr_conn.commit();
				if(NGM.tr_conn!=null) NGM.tr_conn.close();
				if(NGM.conn!=null) NGM.conn.close();
			}
			catch(Exception exp){
				try {	
					NGM.tr_conn.rollback();
					if(NGM.tr_conn!=null) NGM.tr_conn.close();
					if(NGM.conn!=null) NGM.conn.close();
				}
				catch(Exception e){		}
				exp.printStackTrace();
				NGM.ins_writer.write("Message: Please Contact Administrator");
			}
	}
		
		
		
	

}
