package model;

import javax.servlet.http.HttpServletRequest;

import common.utility;
import db.d_notice;
import db.ngm;

public class m_notice extends ngmodel{

	utility ins_util;
	d_notice dnotice;
	
	public m_notice(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		dnotice  = new d_notice(NGM);
	}
	
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		dnotice.subject=ins_util.setNullParameter(p_request, "subject");
		dnotice.desc=ins_util.setNullParameter(p_request, "desc");
		dnotice.from=ins_util.setNullParameter(p_request, "from");
		dnotice.tdate=ins_util.setNullParameter(p_request, "tdate");
	    if(!dnotice.insertDB())return false;
		return true;
	}
	
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		dnotice.id1 = ins_util.setNullParameter(p_request, "idvalue");
		dnotice.subject=ins_util.setNullParameter(p_request, "subject");
		dnotice.desc=ins_util.setNullParameter(p_request, "desc");
		dnotice.from=ins_util.setNullParameter(p_request, "from");
		dnotice.tdate=ins_util.setNullParameter(p_request, "tdate");
	    if(!dnotice.updateDB())return false;
		return true;
	}
	
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		 dnotice.id1 = ins_util.setNullParameter(p_request, "id");
		 if(!dnotice.deleteDB())return false;
		  	return true;
	}
}
