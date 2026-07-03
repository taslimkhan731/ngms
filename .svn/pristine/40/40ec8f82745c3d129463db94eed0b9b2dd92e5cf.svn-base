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


@WebServlet("/s_receive_impdata")
public class s_receive_impdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		
    public s_receive_impdata(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int jsp_task=0;
		String task=null;
		ngm	NGM	= new ngm();
		NGM.cur_sevletname="s_receive_impdata";
		m_receive_impdata ins_imp = new m_receive_impdata(NGM);
		NGM.ins_writer=response.getWriter();
		 
		   
		try {
				task=request.getParameter("task");
				System.out.println(task);
				NGM.ins_utility.validate_task(task,NGM);
				if(task.equals("updatedata"))
				{	
					System.out.println("ssssssssssssssssssddddddddddddaaaaaaattttaaaagf");
					NGM.ins_sess=NGM.ins_template.ajaxTemplate(request,NGM);
					if(ins_imp.setpostdata(request)){
						
						NGM.ins_writer.write("Message: Imported successfully");
				 				}	
					else{
						
						NGM.ins_writer.write("Message: updation failed");
					}
									
					
				}
							else{
								
								NGM.ins_writer.write("Message: contact administrator");
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
