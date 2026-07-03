package model;

import javax.servlet.*;   

import javax.servlet.http.*;   

import db.*;


import java.io.*;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
  
  
public class m_file_read extends ngmodel{   

public String attachment_id;
public String filepath = "";
public String contenttype = "";
public String filename = "";
public String file_type;

public d_document_items ins_document_items = null;

public m_file_read(ngm p_ngm){
	ngm 		NGM		= new ngm();
	ins_document_items =new d_document_items(NGM);
}

public  void readAndDisplayFile(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {   
            
    	response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
    	response.setHeader("Pragma", "public");
    	response.setContentType(contenttype);
    	response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
    	System.out.println(" filepath : "+filepath);
    	InputStream inputStream = new FileInputStream(filepath);  //read the file
    
    	try {
    		byte[] buffer = new byte[8192];
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();

    		int bytesRead;
    		while ((bytesRead = inputStream.read(buffer)) != -1)
    		{
    			baos.write(buffer, 0, bytesRead);
    		}
	    
        response.setContentLength(baos.size());
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
        
    	} finally {
    		if (inputStream != null) 
            inputStream.close();
    		response.getOutputStream().close();
    	}

    }
    
    public boolean setcontenttype()
    {
    	System.out.println(" file_type "+file_type);
    	if(file_type.equalsIgnoreCase("pdf")){
    		System.out.println("pdf");
			this.contenttype = "application/pdf";
		}else if((file_type.equalsIgnoreCase("jpg"))||(file_type.equalsIgnoreCase("jpeg"))||(file_type.equalsIgnoreCase("png"))){
    		System.out.println("j");

			this.contenttype = "image/jpeg";
		}else if((file_type.equalsIgnoreCase("docx"))){
    		System.out.println("docx");

			this.contenttype = "application/msword";
    		System.out.println("this.contenttype "+this.contenttype);

		}
		return true;
    }
    
    public boolean getdocdata() throws Exception
    {
    	ins_document_items.attachment_id = this.attachment_id; 
    	ins_document_items.selectDB();
    	return true;
    }
    public void getfilepath()
    {
    	
    	this.filepath= ins_document_items.file_path;
    	this.file_type= ins_document_items.file_type;
    	System.out.println(" this.filepath "+this.filepath);
    	System.out.println(" this.file_type "+this.file_type);

    }
    public void setPostData(HttpServletRequest p_request) throws Exception{
		this.attachment_id=p_request.getParameter("attachment_id");
		System.out.println("this.attachment_id "+this.attachment_id);
	}
} 