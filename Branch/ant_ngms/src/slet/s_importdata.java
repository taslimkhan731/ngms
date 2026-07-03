package slet;
import java.io.ByteArrayOutputStream;



import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
 
import java.util.ArrayList;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
import model.*;
import common.db_validation;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import common.*;
import template.gepicmenudata;
import db.m_user_details;
import db.m_uservsrole;
import db.ngm;


@WebServlet("/s_importdata")
public class s_importdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_importdata(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_importdata";
		m_importdata ins_imp = new m_importdata(NGM);
		NGM.ins_writer=response.getWriter();
		 
		   
		try {
				task=request.getParameter("task");
				System.out.println(task);
				NGM.ins_utility.validate_task(task,NGM);
				String time= request.getParameter("time");
				if(task.equals("importdata"))
				{						 								 
					ins_imp.setuserdata(request);
					if(ins_imp.validatePostData()) 
					{//					 
						if(ins_imp.isUservalid(request))
						{						 
							if(ins_imp.importdata(request)){
								 
									if(ins_imp.json.size()==0){
										NGM.ins_writer.write("Message: No data for import...");
									}else{
										ins_imp.changeread(time);
										NGM.ins_writer.write(ins_imp.json.toString());
									}
								
						 				 
							}
								else{
									NGM.ins_writer.write("Message: Please contact administrator...");
								}
								 
							 
						}
							else{
								
								NGM.ins_writer.write("Message: Login failed...");
							}
					 
						}else{	 
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
