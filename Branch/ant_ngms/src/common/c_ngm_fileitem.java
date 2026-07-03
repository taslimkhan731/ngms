package common;

import java.io.File;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class c_ngm_fileitem
{
public String a_filename;
public String s_filename;
public String filesize;
public String filetype;
public String filepath;
public String  validfiletypelist;
public boolean is_error=false;


public void setfiletype()
{
	if(this.isvalidfiletype())
	{
		int i = a_filename.lastIndexOf('.');
		if (i >= 0) {
			this.filetype = a_filename.substring(i+1);
		}
	}
	else
		this.is_error = true;
	//return fromfilename.
	//return this.filetype;
}

public boolean isvalidfiletype()
{
	String temp_filetype="";
	boolean ret=false;
	int i = a_filename.lastIndexOf('.');
	if (i >= 0) {
		temp_filetype = a_filename.substring(i+1);
	}
    
    String[] parts = validfiletypelist.split("-");
    for(i=0;i<(parts.length);i++){
        String part1 = parts[i]; 
        if(part1.equals(temp_filetype)){
        	ret= true;
        	break;
        }
    }
	return ret;
}
}
