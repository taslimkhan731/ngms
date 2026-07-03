package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import common.utility;
import db.*;

public class m_basicpay extends ngmodel{
	
	  utility 		ins_util;
	  d_basicpay ins_basicpay;
	 
	public m_basicpay(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_basicpay  = new d_basicpay(NGM);
		
		}
		
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		String da_id = NGM.ins_utility.get_seqID(NGM.tr_conn, "da_id").toString(); 
	    NGM.ins_utility.updateseqID(NGM.tr_conn, "da_id", Integer.parseInt(da_id));
		ins_basicpay.da_id =da_id;
		
		ins_basicpay.grade_title=ins_util.setNullParameter(p_request, "grade");
		ins_basicpay.effect_date_from=ins_util.setNullParameter(p_request, "e_from");
		ins_basicpay.effect_date_to=ins_util.setNullParameter(p_request, "e_to");
		ins_basicpay.basic_pay_from=ins_util.setNullParameter(p_request, "bpay_from");
		ins_basicpay.basic_pay_to=ins_util.setNullParameter(p_request, "bpay_to");
		//ins_basicpay.da_cal_mode=ins_util.setNullParameter(p_request, "mode");
		ins_basicpay.da_value=ins_util.setNullParameter(p_request, "da_value");
		
		if(!ins_basicpay.insertDB())return false;
	  	return true;
	}
//	public boolean chkCode(HttpServletRequest p_request) throws Exception {
//		ins_basicpay.vc_id=ins_util.setNullParameter(p_request, "sel_vc");
//		ins_basicpay.type=ins_util.setNullParameter(p_request, "type");
//		ins_basicpay.family=ins_util.setNullParameter(p_request, "family");
//		if(!ins_basicpay.chkCodeDB()) 
//			return false;
//		else  
//			return true;	
//	}
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		ins_basicpay.da_id = (String) p_request.getAttribute("da_id");
		ins_basicpay.grade_title=ins_util.setNullParameter(p_request, "grade");
		ins_basicpay.effect_date_from=ins_util.setNullParameter(p_request, "e_from");
		ins_basicpay.effect_date_to=ins_util.setNullParameter(p_request, "e_to");
		ins_basicpay.basic_pay_from=ins_util.setNullParameter(p_request, "bpay_from");
		ins_basicpay.basic_pay_to=ins_util.setNullParameter(p_request, "bpay_to");
		ins_basicpay.da_cal_mode="D";
		ins_basicpay.da_value=ins_util.setNullParameter(p_request, "da_value");
		
		if(!ins_basicpay.updateDB())return false;
	  	return true;
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		ins_basicpay.da_id=(String) p_request.getAttribute("da_id");
				
		if(!ins_basicpay.deleteDB())return false;
	  	return true;
	}
}	
		



