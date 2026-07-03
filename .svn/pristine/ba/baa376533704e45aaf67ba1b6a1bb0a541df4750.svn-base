package common;

import java.io.File;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import template.ngproperty;

import db.ngm;

public class c_ngm_fileupload
{
private static final Exception Exception = null;
public ArrayList<c_ngm_fileitem> fileitem_list = new ArrayList<c_ngm_fileitem>();


public void read_file_request(List pfilelist,String userid, ngproperty p_prop,String pcurdate) throws Exception
		{
	
			   File file ;
			   String rootfilepath = p_prop.getpropvalue("UFP");
			
			   Iterator itr = pfilelist.iterator();
			   int icount=1;
			   while (itr.hasNext()) {
				   c_ngm_fileitem budfi = new c_ngm_fileitem();
		           FileItem fi = (FileItem)itr.next();
		           if ( !fi.isFormField () )
		            {
		            // Get the uploaded file parameters
		            String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            String tempfilename = null;
		            if(fileName != null){
		            	if(!fileName.isEmpty()){
		            		tempfilename = userid+pcurdate+icount+fileName;
		            	}
		            }
		            icount++;
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();


		            budfi.validfiletypelist = p_prop.getpropvalue("validfiletypelist");
		            budfi.a_filename = fileName;
		            budfi.s_filename = tempfilename;
		            //budfi.filesize = sizeInBytes;
		            budfi.filesize = Long.toString(sizeInBytes);
		            budfi.setfiletype();
		            System.out.println("......filetype...."+budfi.filetype);
		            String tempfilepath = null; // +"."+budfi.filetype;
		            if(tempfilename != null){
		            	if(!tempfilename.isEmpty()){
		            		tempfilepath = rootfilepath + tempfilename;
		            	}
		            }
		            budfi.filepath = tempfilepath;
		            
		            // Write the file 
		            if(!budfi.is_error)
		            {
		            	if(tempfilepath != null){
				            file = new File(tempfilepath);
				            fi.write( file ) ;
		            	}
		            }
		            fileitem_list.add(budfi);
		            } // end if
			   }
	
		}
		
}