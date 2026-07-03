package slet;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBConnect;

import db.ngm;

/**
 * Servlet implementation class gadcount
 */
@WebServlet("/gadcount")
public class gadcount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gadcount() {
        super();
     
        // TODO Auto-generated constructor stub
    }
	
    public 	int reqCount = 0,accCount=0,proCount=0;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String t_password = request.getParameter("password");
		String t_date = request.getParameter("date");
		System.out.println("d "+t_password);
		System.out.println("p "+t_date);
		ngm	NGM	= new ngm();
		NGM.ins_writer=response.getWriter();
		NGM.ins_val.cPassword(t_password); //db_datecheck
		NGM.ins_val.db_datecheck(t_date);
		System.out.println("hjfhgfhgf");
		
		
		
		 DBConnect ins_db = new DBConnect();
	       java.sql.Connection conn = ins_db.getConnection();
		
		if(t_password.equals("abc")){
	
		PreparedStatement ps,ps1,ps2;
		try {
			ps = conn.prepareStatement("select count(distinct(req_id)) as reqCount from pub_request where date(lastupdated)=to_date(?,'dd-mm-yyyy')");
			ps.setString(1, t_date);
			System.out.println("ggg"+ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				reqCount = rs.getInt("reqCount");
			}
			 //close
		    rs.close();
			ps.close();
			
			ps1 = conn.prepareStatement("select count(distinct(req_id)) as accCount from gad_request where mode_of_req='GAD' and date(lastupdated)=to_date(?,'dd-mm-yyyy')");
			ps1.setString(1, t_date);
			System.out.println("ggg"+ps1);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				accCount = rs1.getInt("accCount");
			}
			 //close
		    rs1.close();
			ps1.close();
			int req_total = accCount+reqCount;
			
			ps2 = conn.prepareStatement("select count(distinct(req_id)) as proCount from ((select req_id,last_modified,gh_id,'R' from reservation_main_table where status!='CAN' union select req_id,last_modified,gh_id,'W' from reservation_waitinglist where status!='CAN') union (select req_id,lastmodified,gh_id,'C' from cancel_reservation))c where date(last_modified)=to_date(?,'dd-mm-yyyy')");
					//"select count(distinct(req_id)) as proCount from (select req_id,last_modified from reservation_main_table union select req_id,last_modified from reservation_waitinglist)a where date(last_modified)=to_date(?,'dd-mm-yyyy')");
			ps2.setString(1, t_date);
			System.out.println("ggg"+ps2);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()){
				proCount = rs2.getInt("proCount");
			}
			 //close
		    rs2.close();
			ps2.close();
						response.setContentType("application/xml");
						NGM.ins_writer.write("<eTaal_Odisha>");
						NGM.ins_writer.write("<Response ServiceCode=\"D098206007849\" Count=\""+req_total+"\" LocationCode=\"32\">");
						NGM.ins_writer.write("</Response>");
						NGM.ins_writer.write("<Response ServiceCode=\"B098206006470\" Count=\""+proCount+"\" LocationCode=\"32\">");
						NGM.ins_writer.write("</Response>");
						NGM.ins_writer.write("</eTaal_Odisha>");
						
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		}else{
			NGM.ins_writer.write("Unauthorized Access");
		}
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
