package model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;












import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import common.c_ngm_fileitem;
import common.c_ngm_fileupload;
import common.common;
import common.utility;
import db.*;


public class m_document extends ngmodel{

	d_document ins_document;
	utility 		ins_util;
	d_document_items ins_document_items; 
	d_pubrequest ins_publicrequest;
	ArrayList <d_publicguestdetails> ins_guest = new ArrayList<d_publicguestdetails>();
	d_publicguestdetails ins_publicguestdetails;
	d_doc_history ins_doc_his; 
	public String p_req_id = "";
	public m_document(ngm p_ngm){
		NGM		= p_ngm;
		ins_document = new d_document(NGM);
		ins_util		= NGM.ins_utility;
		ins_publicrequest	  = new d_pubrequest(NGM);
		ins_document_items = new d_document_items(NGM);
		ins_publicguestdetails  =  new d_publicguestdetails(NGM);
		ins_doc_his = new d_doc_history(NGM);
		}
	
	public void setPostCreation(List  pitemlist) throws Exception{

		common ins_common = new common(NGM);
		ins_document.title = ins_common.getparametervalue(pitemlist, "txt_title");
		ins_document.from_date = ins_common.getparametervalue(pitemlist, "txt_valid_from");
		ins_document.to_date = ins_common.getparametervalue(pitemlist, "txt_valid_to");
		ins_document.created_by = NGM.ins_sess.user_id;
		ins_document.doc_type = ins_common.getparametervalue(pitemlist, "sel_doc");
	}
	
	public void setPostCreationDoc(List  pitemlist) throws Exception{

		common ins_common = new common(NGM);
		ins_document.title = "Supporting_Doc";
		ins_document.from_date = "30-09-2019";
		ins_document.to_date = "30-09-2019";
		ins_document.created_by = NGM.ins_sess.user_id;
		ins_document.doc_type = ins_common.getparametervalue(pitemlist, "sel_doc");
	}
	
	
	
	public void setPostUpdation(List  pitemlist) throws Exception{

		common ins_common = new common(NGM);
		ins_document.doc_id = ins_common.getparametervalue(pitemlist, "txt_doc_id");
		ins_document.title = ins_common.getparametervalue(pitemlist, "txt_title");
		ins_document.from_date = ins_common.getparametervalue(pitemlist, "txt_valid_from");
		ins_document.to_date = ins_common.getparametervalue(pitemlist, "txt_valid_to");
		ins_document.created_by = NGM.ins_sess.user_id;
		ins_document.doc_type = ins_common.getparametervalue(pitemlist, "sel_doc");
		}
	
	public boolean titlenotexists(HttpServletRequest p_request) throws Exception{	
		ins_document.title				= p_request.getParameter("txt_title");
		if(NGM.ins_utility.checkConstrain_reg(NGM.tr_conn, "tr_document", "title", ins_document.title)){ 
		return false;
		 }
		else{
		return true;
		}
		 
	}

	
	
	public boolean saveCreation(HttpServletRequest p_request, List FILEMULTIPARTITEMS, common ins_common) throws Exception{
		System.out.println("FILE UPLOAD saveCreation");
		
		ins_document.doc_id = NGM.ins_utility.getID(NGM.tr_conn, "doc_id").toString();
		String temp_curdate = NGM.ins_utility.getcurdatestring();
		c_ngm_fileupload ins_bfupload = new c_ngm_fileupload();
		ins_bfupload.read_file_request(FILEMULTIPARTITEMS, NGM.ins_sess.user_id, NGM.ins_ngmprop,temp_curdate);
		ArrayList<c_ngm_fileitem> fileitem_list	= ins_bfupload.fileitem_list;
		
//		setPostCreationDoc(FILEMULTIPARTITEMS);
		setPostpublicrequest(FILEMULTIPARTITEMS,ins_common);
		setPostpublicguestdetails(FILEMULTIPARTITEMS,ins_common);
		
		NGM.ins_utility.updateID(NGM.tr_conn, "doc_id",Integer.parseInt(ins_document.doc_id));
		boolean retval=true;
		
//		if(!ins_document.insertDB()) 
//			 retval= false;
//		else retval=true;
//		
//		Iterator itr = fileitem_list.iterator();
//		while (itr.hasNext()) {
//			c_ngm_fileitem cfi = (c_ngm_fileitem)itr.next();
//			System.out.println("TESTTTTTT " + cfi.filepath);
//			ins_document_items.doc_id= ins_document.doc_id;
//			ins_document_items.file_type= cfi.filetype;
//			ins_document_items.file_path=cfi.filepath;
//			ins_document_items.a_filename=cfi.a_filename;
//			ins_document_items.filesize=cfi.filesize;
//			ins_document_items.s_filename= cfi.s_filename;
//			ins_document_items.attachment_id = NGM.ins_utility.getID(NGM.tr_conn, "attachment_id").toString();
//			NGM.ins_utility.updateID(NGM.tr_conn, "attachment_id",Integer.parseInt(ins_document_items.attachment_id));
//			if(!ins_document_items.insertDB())
//				retval = false;
//			else retval=true;
//		}

		return retval;
	}
	
public boolean updateCreation(HttpServletRequest p_request, List FILEMULTIPARTITEMS) throws Exception{
		
        String temp_curdate = NGM.ins_utility.getcurdatestring();
		c_ngm_fileupload ins_bfupload = new c_ngm_fileupload();
		ins_bfupload.read_file_request(FILEMULTIPARTITEMS, NGM.ins_sess.user_id, NGM.ins_ngmprop,temp_curdate);
		ArrayList<c_ngm_fileitem> fileitem_list	= ins_bfupload.fileitem_list;
		
		setPostUpdation(FILEMULTIPARTITEMS);
		boolean retval=true;

		if(!ins_document.updateDB()) 
			 retval= false;
		else retval=true;
		
		Iterator itr = fileitem_list.iterator();
		while (itr.hasNext()) {

			c_ngm_fileitem cfi = (c_ngm_fileitem)itr.next();
			ins_document_items.file_type= cfi.filetype;
			ins_document_items.file_path=cfi.filepath;
			ins_document_items.a_filename=cfi.a_filename;
			ins_document_items.filesize=cfi.filesize;
			ins_document_items.s_filename= cfi.s_filename;
			ins_document_items.doc_id=ins_document.doc_id;
			ins_document_items.attachment_id = NGM.ins_utility.getID(NGM.tr_conn, "attachment_id").toString();
			NGM.ins_utility.updateID(NGM.tr_conn, "attachment_id",Integer.parseInt(ins_document_items.attachment_id));
			if(!ins_document_items.insertDB())
				retval = false;
			else retval=true;
		}

		return retval;
	
	}
	
public void setPostDeleteItem (HttpServletRequest p_request,String attachment_id) throws Exception{
	ins_document_items.attachment_id					= attachment_id;
	ins_document_items.doc_id					= NGM.ins_utility.setNullParameter(p_request, "doc_id");
}
public boolean deletePostItem(HttpServletRequest p_request) throws Exception{

	if(!ins_document_items.deleteDBitem()){
		return false;
	}
	return true;
}

public void setPostDelete (HttpServletRequest p_request,String doc_id) throws Exception{
	ins_document.doc_id					= doc_id;
	System.out.println(" ins_document.doc_id kkk "+ins_document.doc_id);
	ins_document_items.doc_id			= ins_document.doc_id;
	ins_doc_his.doc_id 					= ins_document.doc_id;
	ins_doc_his.title 					= NGM.ins_utility.setNullParameter(p_request, "txt_title");
	System.out.println(" ins_doc_his.title kkk "+ins_doc_his.title);

	ins_doc_his.userid 					= NGM.ins_sess.user_id;
	ins_doc_his.reason 					= NGM.ins_utility.setNullParameter(p_request,"txt_reason");
}

public boolean deletePostuser(HttpServletRequest p_request) throws Exception{
	
	if(!ins_doc_his.insertDB())return false;
	else{
		if(ins_document_items.selectDBdoc()){
			if(!ins_document_items.deleteDBdocid()){
				return false;
			}
		}
			if(!ins_document.deleteDB())return false;
	}
	return true;
}

public boolean setPostpublicrequest(List  pitemlist, common ins_common) throws Exception{ 
	String req_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "req_id").toString(); 
    NGM.ins_utility.updateseqID(NGM.tr_conn, "req_id", Integer.parseInt(req_id1));
    ins_publicrequest.req_id ="P"+req_id1;//P_
    
    p_req_id =  ins_publicrequest.req_id ;
    
    ins_publicrequest.gh_id    						= ins_common.getparametervalue(pitemlist, "gh_id");
    ins_publicrequest.block_id    						= ins_common.getparametervalue(pitemlist, "sel_block");
	ins_publicrequest.from_date    					= ins_common.getparametervalue(pitemlist, "from");
	ins_publicrequest.to_date    					= ins_common.getparametervalue(pitemlist, "to");
	ins_publicrequest.is_veh_req    				= ins_common.getparametervalue(pitemlist, "checkedval");
	ins_publicrequest.is_veh_req1    				= ins_common.getparametervalue(pitemlist, "checkedval1");
	ins_publicrequest.address1    					    = ins_common.getparametervalue(pitemlist, "addressoffice");
	System.out.println("ins_publicrequest.address1  "+ins_publicrequest.address1  );
	ins_publicrequest.ext    				= ins_common.getparametervalue(pitemlist, "extval");
	if(ins_publicrequest.ext.equals("1")){
		ins_publicrequest.existing_req_id    				= ins_common.getparametervalue(pitemlist, "exist_req_id");
	}else{
		ins_publicrequest.existing_req_id 					= null;
	}
	ins_publicrequest.purpose						= ins_common.getparametervalue(pitemlist, "purpose");
	ins_publicrequest.family= ins_common.getparametervalue(pitemlist, "familyval");
	ins_publicrequest.nor_req    					= ins_common.getparametervalue(pitemlist, "rooms");
	ins_publicrequest.nob_g_req    					= ins_common.getparametervalue(pitemlist, "nbg");
	ins_publicrequest.nob_l_req    					= ins_common.getparametervalue(pitemlist, "nbl");
	
	ins_publicrequest.nod_g_req = ins_common.getparametervalue(pitemlist, "ndg");
	System.out.println("  NIC dddd           "+ins_publicrequest.nod_g_req);
	ins_publicrequest.nod_l_req = ins_common.getparametervalue(pitemlist, "ndl");
	
	ins_publicrequest.mobile_no					=	ins_common.getparametervalue(pitemlist, "mobile");
	ins_publicrequest.email_id					=	ins_common.getparametervalue(pitemlist, "email");
	if(!(ins_publicrequest.insertDB_publicreq() && ins_publicrequest.insertDB_publicreq_Gad()))return false;
	return true;
}

public boolean setPostpublicguestdetails(List  pitemlist, common ins_common) throws Exception{ 
	int count= Integer.parseInt(ins_common.getparametervalue(pitemlist, "rowCnt"));
	System.out.println("count    "+count);
	for(int i=0;i<count;i++)
	{
		int guest_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "guest_id"); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "guest_id", guest_id1);
		ins_publicguestdetails.guest_id = "p_"+String.valueOf(guest_id1);
		String gender="gender";
		System.out.println("iiiiii       "+i);
			String name="name";
			String age="age";
			String desg="desg";
			String dept="dept";
			String address="address";
	
		//ins_dataentry.guest_id    					= ins_util.setNullParameter(p_request, "name");
		
		//System.out.println("djfhdjshf gender    "+ins_dataentry.gender);
			ins_publicguestdetails.req_id = ins_publicrequest.req_id;
			ins_publicguestdetails.name    				= ins_common.getparametervalue(pitemlist, name+i);
			ins_publicguestdetails.age    					= ins_common.getparametervalue(pitemlist, age+i);
			ins_publicguestdetails.gender    				= ins_common.getparametervalue(pitemlist, gender+i);
			ins_publicguestdetails.designation    				= ins_common.getparametervalue(pitemlist, desg+i);
			ins_publicguestdetails.department   					= ins_common.getparametervalue(pitemlist, dept+i);
			ins_publicguestdetails.address2    					= ins_common.getparametervalue(pitemlist, address+i);
			
			if(ins_publicguestdetails.address2.equals("Same As Above")){
				ins_publicguestdetails.address2 = ins_common.getparametervalue(pitemlist, "addressoffice");
			}
			ins_guest.add(ins_publicguestdetails);
			if(!(ins_publicguestdetails.insertDB_guest() && ins_publicguestdetails.insertDB_guest_Gad()))return false;
	}
	return true;
}

}



