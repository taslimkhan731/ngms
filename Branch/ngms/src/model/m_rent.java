package model;

import javax.servlet.http.HttpServletRequest;

import common.utility;

import db.d_rent;
import db.ngm;

public class m_rent extends ngmodel{
	
	  utility 		ins_util;
	  d_rent ins_rent;
	 
	public m_rent(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_rent  = new d_rent(NGM);
		
		}
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		String rent_id1 = NGM.ins_utility.get_seqID(NGM.tr_conn, "rent_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "rent_id", Integer.parseInt(rent_id1));
		ins_rent.rent_id =rent_id1;
	    
		ins_rent.from_date=ins_util.setNullParameter(p_request, "f_date");
		ins_rent.to_date=ins_util.setNullParameter(p_request, "t_date");
		ins_rent.vc_id=ins_util.setNullParameter(p_request, "sel_vc");
		ins_rent.type=ins_util.setNullParameter(p_request, "type");
		ins_rent.family=ins_util.setNullParameter(p_request, "family");
		System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWW     "+ins_rent.family);
		if(ins_rent.family.equals("1")){
			System.out.println("WWWWWWWWW11111111111");
			ins_rent.f_rent=ins_util.setNullParameter(p_request, "rent");
			ins_rent.rent="0";
		}
		else if(ins_rent.family.equals("0"))
		{
			System.out.println("WWWW22222222222222222");
			ins_rent.f_rent="0";
			ins_rent.rent=ins_util.setNullParameter(p_request, "rent");
		}
		ins_rent.mode=ins_util.setNullParameter(p_request, "mode_payment");
		if(ins_rent.mode.equals("D"))
		{
		ins_rent.da_per=ins_util.setNullParameter(p_request, "other");
		}
		else
		{
			ins_rent.da_per="0";
		}
		
		if(!ins_rent.insertDB())return false;
	  	return true;
	}
	public boolean chkCode(HttpServletRequest p_request) throws Exception {
		ins_rent.vc_id=ins_util.setNullParameter(p_request, "sel_vc");
		ins_rent.type=ins_util.setNullParameter(p_request, "type");
		ins_rent.family=ins_util.setNullParameter(p_request, "family");
		if(!ins_rent.chkCodeDB()) 
			return false;
		else  
			return true;	
	}
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_rent.rent_id=(String) p_request.getAttribute("rent_id");
		ins_rent.from_date=ins_util.setNullParameter(p_request, "f_date");
		ins_rent.to_date=ins_util.setNullParameter(p_request, "t_date");
		ins_rent.vc_id=ins_util.setNullParameter(p_request, "sel_vc");
		ins_rent.type=ins_util.setNullParameter(p_request, "type");
		ins_rent.family=ins_util.setNullParameter(p_request, "family");
		System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWW     "+ins_rent.family);
		if(ins_rent.family.equals("1")){
			System.out.println("WWWWWWWWW11111111111");
			ins_rent.f_rent=ins_util.setNullParameter(p_request, "rent");
			ins_rent.rent="0";
		}
		else if(ins_rent.family.equals("0"))
		{
			System.out.println("WWWW22222222222222222");
			ins_rent.f_rent="0";
			ins_rent.rent=ins_util.setNullParameter(p_request, "rent");
		}
		ins_rent.mode=ins_util.setNullParameter(p_request, "mode_payment");
		if(ins_rent.mode.equals("D"))
		{
		ins_rent.da_per=ins_util.setNullParameter(p_request, "other");
		}
		else
		{
			ins_rent.da_per="0";
		}
		
		if(!ins_rent.updateDB())return false;
	  	return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_rent.rent_id=(String) p_request.getAttribute("rent_id");
		
		System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWins_rent.rent_id     "+ins_rent.rent_id);
		if(!ins_rent.deleteDB())return false;
	  	return true;
	}
}	
		



