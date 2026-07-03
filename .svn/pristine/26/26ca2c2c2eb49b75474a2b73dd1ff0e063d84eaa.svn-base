package model;


import javax.servlet.http.HttpServletRequest;

import common.utility;
import db.d_food_rate;
import db.d_m_food_items;
import db.ngm;

public class m_foodmaster extends ngmodel{
	
	  utility 		ins_util;
	  d_food_rate ins_fooddetails;
	  d_m_food_items ins_mfooditem;
	 
	public m_foodmaster(ngm p_ngm){
		NGM		= p_ngm;
		ins_util		= NGM.ins_utility;
		ins_fooddetails  = new d_food_rate(NGM);
		ins_mfooditem = new d_m_food_items(NGM);
		
		}
		
	
	public boolean saveCreation(HttpServletRequest p_request) throws Exception{
		int p_curvalue; 
		System.out.println("rateee");
		for(int j=0;j<3;j++)
		{
			p_curvalue = NGM.ins_utility.get_seqID(NGM.tr_conn, "rate_id"); 
			NGM.ins_utility.updateseqID(NGM.tr_conn, "rate_id",(p_curvalue));
		    ins_fooddetails.rate_id=p_curvalue;
		    ins_fooddetails.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
			ins_fooddetails.cat_id=ins_util.setNullParameter(p_request, "cat_id");
			ins_fooddetails.item=ins_util.setNullParameter(p_request, "item");
			
		    if(j==0)
		    {	ins_fooddetails.purpose_visit="Official";
		    	ins_fooddetails.rate=Double.parseDouble(ins_util.setNullParameter(p_request, "rate1"));
		    }
		    else if(j==1)
		    {	ins_fooddetails.purpose_visit="Private";
		    	ins_fooddetails.rate=Double.parseDouble(ins_util.setNullParameter(p_request, "rate2"));
		    }
		    else
		    {	ins_fooddetails.purpose_visit="Outsider";
		    	ins_fooddetails.rate=Double.parseDouble(ins_util.setNullParameter(p_request, "rate3"));
		    }
		    ins_fooddetails.eff_date_from=ins_util.setNullParameter(p_request, "fromdate");
			ins_fooddetails.eff_date_to=ins_util.setNullParameter(p_request, "todate");
			ins_fooddetails.status=ins_util.setNullParameter(p_request, "status");
			System.out.println("rate"+ins_fooddetails.rate);
		    if(!ins_fooddetails.insertDB())
		    	return false;
		}  
		return true;
	}
	public boolean savefooditem(HttpServletRequest p_request) throws Exception{
		ins_mfooditem.item_id  = NGM.ins_utility.get_seqID(NGM.tr_conn, "food_id").toString(); 
		NGM.ins_utility.updateseqID(NGM.tr_conn, "food_id", Integer.parseInt(ins_mfooditem.item_id));
		
		ins_mfooditem.unitprice=ins_util.setNullParameter(p_request, "rate1");
		ins_mfooditem.item_title=ins_util.setNullParameter(p_request, "item");
		if(!ins_mfooditem.insertDB())
	    	return false;
		else 
	return true;
		
	}
	public boolean updateCreation(HttpServletRequest p_request) throws Exception{
		    
		
		 ins_fooddetails.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
		 ins_fooddetails.cat_id=ins_util.setNullParameter(p_request, "cat_id");
		 ins_fooddetails.item=ins_util.setNullParameter(p_request, "item");
		
		 ins_fooddetails.eff_date_from=ins_util.setNullParameter(p_request, "fromdate");
		 ins_fooddetails.eff_date_to=ins_util.setNullParameter(p_request, "todate");
		 ins_fooddetails.status=ins_util.setNullParameter(p_request, "status");
	
		 
			 ins_fooddetails.rate_id=Integer.parseInt(ins_util.setNullParameter(p_request, "rate_id1"));
		     ins_fooddetails.rate=Double.parseDouble(ins_util.setNullParameter(p_request, "rate1"));
		     ins_fooddetails.purpose_visit="Official";
		     if(!ins_fooddetails.updateDB())
			  return false;
		     ins_fooddetails.rate_id=Integer.parseInt(ins_util.setNullParameter(p_request, "rate_id2"));
		     ins_fooddetails.rate=Double.parseDouble(ins_util.setNullParameter(p_request, "rate2"));
		     ins_fooddetails.purpose_visit="Private";
		     if(!ins_fooddetails.updateDB())
				  return false;
		     ins_fooddetails.rate_id=Integer.parseInt(ins_util.setNullParameter(p_request, "rate_id3"));
		     ins_fooddetails.rate=Double.parseDouble(ins_util.setNullParameter(p_request, "rate3"));
		     ins_fooddetails.purpose_visit="Outsider";
		     if(!ins_fooddetails.updateDB())
				  return false;
			
		return true;
	}
public boolean updatefooditem(HttpServletRequest p_request) throws Exception{
	ins_mfooditem.item_id=ins_util.setNullParameter(p_request, "item_id");
		ins_mfooditem.unitprice=ins_util.setNullParameter(p_request, "rate1");
		ins_mfooditem.item_title=ins_util.setNullParameter(p_request, "item");
		
		if(!ins_mfooditem.updateDB())
	    	return false;
		else 
	return true;
		
	}
	public boolean deleteCreation(HttpServletRequest p_request) throws Exception{
		 System.out.println("tesssssssssst");
		ins_fooddetails.rate_id=Integer.parseInt(ins_util.setNullParameter(p_request, "rate_id1"));
		 System.out.println("1-->"+ins_fooddetails.rate_id);
		 if(!ins_fooddetails.deleteDB())return false;
		 ins_fooddetails.rate_id=Integer.parseInt(ins_util.setNullParameter(p_request, "rate_id2"));
		 System.out.println("2-->"+ins_fooddetails.rate_id);
		 if(!ins_fooddetails.deleteDB())return false;
		 ins_fooddetails.rate_id=Integer.parseInt(ins_util.setNullParameter(p_request, "rate_id3"));
		 System.out.println("3-->"+ins_fooddetails.rate_id);
		 if(!ins_fooddetails.deleteDB())return false;
		 
		 return true;
	}
	public boolean deletefooditem(HttpServletRequest p_request) throws Exception{
		ins_mfooditem.item_id=ins_util.setNullParameter(p_request, "item_id");
		if(!ins_mfooditem.deleteDB())
	    	return false;
		else 
	return true;
	}
	
	public boolean isDataExists(HttpServletRequest p_request) throws Exception{

		
//		 ins_fooddetails.cat_id=ins_util.setNullParameter(p_request, "cat_id");
		ins_mfooditem.item_title=ins_util.setNullParameter(p_request, "item");
		
//			if(!ins_fooddetails.getDBisExist())
		 if(!ins_mfooditem.getDBisExist())
				return false;
			else
				return true;
					
	}
	public boolean isDataExistsforedit(HttpServletRequest p_request) throws Exception{

//		 ins_fooddetails.gh_id=ins_util.setNullParameter(p_request, "sel_gh");
//		 ins_fooddetails.cat_id=ins_util.setNullParameter(p_request, "cat_id");
		ins_mfooditem.item_title=ins_util.setNullParameter(p_request, "item");
		
//		 ins_fooddetails.rate_id=Integer.parseInt(ins_util.setNullParameter(p_request, "rate_id"));
			
			if(!ins_fooddetails.getDBisExistforupdate())
				return false;
			else
				return true;
	}

}	
		



