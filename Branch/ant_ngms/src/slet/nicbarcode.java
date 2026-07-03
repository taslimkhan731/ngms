package slet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onbarcode.barcode.QRCode; 
import common.nic_barcode;

import java.io.*; 

@WebServlet("/nicbarcode")
public class nicbarcode extends HttpServlet {
private static final long serialVersionUID = 1L;


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {     
			String data= request.getParameter("data");
			 
			QRCode barcode = new QRCode();   
			barcode.setData(data);     
			ServletOutputStream servletoutputstream = response.getOutputStream(); 
			response.setContentType("image/jpeg");   
			response.setHeader("Pragma", "no-cache");      
			response.setHeader("Cache-Control", "no-cache");  
			response.setDateHeader("Expires", 0);  
			// Generate QRCode barcode & output to ServletOutputStream     
			barcode.drawBarcode(servletoutputstream);        
			} catch (Exception e) {
				throw new ServletException(e); 
				}

		nic_barcode ins_nic_barcode = new nic_barcode();
		String req_id="4445454";
		ins_nic_barcode.save_barcode_image("d:\\barcode\\apdata"+req_id, req_id);
		
			
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
}

}
