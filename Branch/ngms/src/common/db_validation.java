package common;
import db.ngm;
public class db_validation extends validation
{
	ngm NGM = null;
	

public boolean val_ghid(String gh_id){
	if(gh_id!=null){
			if(!cLength(gh_id.toString(), 1, 10)){
					return false;
			}
			if (!cNumeric(gh_id)){
				return false;
			}
			return true;
	}else
				
				return false;
}
public boolean val_role_code(String role_code){
	if(role_code!=null){
		if(!cLength(role_code.toString(), 0, 2)){
				return false;
			}
			if (!cNumeric(role_code)){
					return false;
			}
			return true;
	}else
		return false;
	
}	
public boolean val_roomno(String roomno){
	if(roomno!=null){
			if(!cLength(roomno.toString(), 1, 10)){
					return false;
			}
//			if (!cNumeric(roomno)){
//				return false;
//			}
			return true;
	}else
				
				return false;
}
public boolean val_flight(String flight){
	if(flight!=null){
			if(!cLength(flight.toString(), 1, 50)){
					return false;
			}
			/*if (!cAlphaNumericSpecial(flight," ")){
				return false;
			}*/
			return true;
	}else
			return false;
}
public boolean val_mobile(String mobno){
	if(mobno!=null){
			if(!cLength(mobno.toString(), 1, 10)){
					return false;
			}
			if (!cPhoneMobile(mobno)){
				return false;
			}
			return true;
	}else
				
				return true;
}
public boolean val_email(String email){
	if(email!=null){
			if(!cLength(email.toString(), 1, 50)){
					return false;
			}
			if (!cEmail(email)){
				return false;
			}
			return true;
	}else
				return true;
}
public boolean val_bedno(String bedno){
	if(bedno!=null){
			if(!cLength(bedno.toString(), 1, 10)){
					return false;
			}
			if (!cAlphaNumeric(bedno)){
				return false;
			}
			return true;
	}else
				
				return false;
}
//user_id
public boolean val_user_id(String userid){
	if(userid!=null){
			if(!cLength(userid.toString(), 1, 25)){
					return false;
			}
			if (!cAlphaNumeric(userid)){
				return false;
			}
			return true;
	}else
				
				return false;
}



public boolean val_pageno(String page_no_s){
	if(page_no_s!=null)
	{
			if(!cLength(page_no_s.toString(), 1, 10)){
					return false;
			}
			if (!cNumeric(page_no_s)){
				return false;
			}
			return true;
	}
	else
				
				return false;
}



public boolean val_blockid(String block_id){
	if(block_id!=null)
	{
			if(!cLength(block_id.toString(), 1, 10)){
					return false;
			}
			if (!cNumeric(block_id)){
				return false;
			}
			return true;
	}
	else
				
				return false;
}

public boolean val_vccategory(String vc_category){
	if(vc_category!=null){
		if(!cLength(vc_category.toString(), 0, 10)){
				return false;
			}
			if (!cNumeric(vc_category)){
					return false;
			}
			return true;
	}else
		return false;
	
}

public boolean val_date(String pdate){
	if(pdate!=null){
		if(!cDate(pdate.toString(),"ymd")){
				return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_date_dmy(String pdate){
	if(pdate!=null){
		if(!cDate(pdate.toString(),"dmy")){
				return false;
			}
			return true;
	}else
		return false;
	
}

public boolean val_fromdate(String from_date){
	if(from_date!=null){
		if(!cDateTime(from_date.toString(),"yyyy-MM-dd HH:mm:ss")){
				return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_todate(String to_date){
	if(to_date!=null){
		if(!cDateTime(to_date.toString(),"yyyy-MM-dd HH:mm:ss")){
				return false;
			}
			return true;
	}else
		return false;
	
}

public boolean val_one_zero(String one_zero){
	if(one_zero!=null){
		if(!cLength(one_zero.toString(), 1, 1)){
				return false;
			}
			if (!cNumeric(one_zero)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_doctype(String doc_type){
	if(doc_type!=null){
		if(!cLength(doc_type.toString(), 2, 2)){
				return false;
			}
			if (!cNumeric(doc_type)){
					return false;
			}
			return true;
	}else
		return false;
	
}

public boolean val_userid(String userid){
	if(userid!=null){
		if(!cLength(userid.toString(), 0, 25)){
				return false;
			}
			if (!cAlphaNumeric(userid)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_loginid(String loginid){
	if(loginid!=null){
		if(!cLength(loginid.toString(), 0, 100)){
				return false;
			}
			if (!cEmail(loginid)){
					return false;
			}
			return true;
	}else
		return false;
	
}

public boolean val_doc_id(String doc_id){
	if(doc_id!=null){
		if(!cLength(doc_id.toString(), 1, 15)){
				return false;
			}
			if (!cNumeric(doc_id)){
					return false;
			}
			return true;
	}else
		return false;
	
}

public boolean cfilepath(String p_value){
	String rg="[,-. :/_a-zA-Z0-9]*";
	if (p_value.matches(rg)){
	return true;
	}
	else
	return false;
	}

public boolean cNameComma(String p_value,String p_allowedSpl){
	String rg="[,a-zA-Z"+p_allowedSpl+"]*";
	if (p_value.matches(rg)){
	return true;
	}
	else
	return false;
	}

public boolean val_title(String title){
	if(title!=null){
		if(!cLength(title.toString(), 2, 50)){
				return false;
			}
			if (!cAlphaNumericSpecial(title," ")){
					return false;
			}
			return true;
	}else
		return false;
	
}


public boolean val_conf_cat_desc(String title){
	if(title!=null){
		if(!cLength(title.toString(), 2, 1000)){
				return false;
			}
			if (!cRemark(title)){
					return false;
			}
			return true;
	}else
		return false;
	
}



public boolean val_vehid(String veh_id){
if(veh_id!=null){
		if(!cLength(veh_id.toString(), 1, 10)){
				return false;
		}
		if (!cAlphaNumericSpecial(veh_id,"_")){
			return false;
		}
		return true;
}else
			
			return false;
}

public boolean val_type(String type){
if(type!=null){
		if(!cLength(type.toString(), 1, 10)){
				return false;
		}
		if (!cAlphaNumeric(type)){
			return false;
		}
		return true;
}else
			
			return false;
}
public boolean val_purpose(String purpose){
	if(purpose!=null){
		if(!cLength(purpose.toString(), 0, 25)){
				return false;
			}
			if (!cAlphabetbkt(purpose)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_modeoftravel(String mode_travel){
	if(mode_travel!=null){
		if(!cLength(mode_travel.toString(), 1, 1)){
				return false;
			}
			if (!cAlphabet(mode_travel)){
					return false;
			}
			return true;
	}else
		return false;
	
}
//val_tablename
public boolean val_tablename(String tablename){
if(tablename!=null){
		if(!cLength(tablename.toString(), 1, 10)){
				return false;
		}
		if (!cAlphabet(tablename)){
			return false;
		}
		return true;
}else
			
			return false;
}

//rec_id	
public boolean val_rec_id(String rec_id){
	if(rec_id!=null){
		if(!cLength(rec_id.toString(), 1, 25)){
				return false;
			}
			if (!cNumeric(rec_id)){
					return false;
			}
			return true;
	}else
		return false;
	
}

//bill_id

public boolean val_bill_id(String bill_id){
if(bill_id!=null){
	if(!cLength(bill_id.toString(), 1, 25)){
			return false;
		}
		if (!cNumeric(bill_id)){
				return false;
		}
		return true;
}else
	return false;

}

//amount
public boolean val_amount(String amount){
if(amount!=null){
	if(!cLength(amount.toString(), 1, 25)){
			return false;
		}
		if (!cDecimal(amount)){
				return false;
		}
		return true;
}else
	return false;

}

//status
public boolean val_status(String status){
if(status!=null){
	if(!cLength(status.toString(), 1, 5)){
			return false;
		}
		if (!cAlphabet(status)){
				return false;
		}
		return true;
}else
	return false;
}

public boolean val_officeorder(String officeorder){
if(officeorder!=null){
	if(!cLength(officeorder.toString(), 1, 50)){
			return false;
		}
		if (!cOrdno(officeorder)){
				return false;
		}
		return true;
}else
	return true;
}
public boolean val_quota_type(String quota_type){
if(quota_type!=null){
	if(!cLength(quota_type.toString(), 1, 10)){
			return false;
		}
		if (!cAlphaNumeric(quota_type)){
				return false;
		}
		return true;
}else
	return false;
}
//shift_id

public boolean val_shiftid(String shift_id){
if(shift_id!=null){
	if(!cLength(shift_id.toString(), 1, 25)){
			return false;
		}
		if (!cNumeric(shift_id)){
				return false;
		}
		return true;
}else
	return false;
}
//reqt_id

public boolean val_reqid(String req_id){
if(req_id!=null){
	if(!cLength(req_id.toString(), 1, 10)){
			return false;
		}
		/*if (!cAlphaNumericSpecial(req_id,"_")){
				return false;
		}*/
		return true;
}else
	return false;
}

public boolean val_modid(String modid){
if(modid!=null){
	if(!cLength(modid.toString(), 1, 10)){
			return false;
		}
		if (!cAlphaNumericSpecial(modid,"_")){
				return false;
		}
		return true;
}else
	return false;
}
//val_res_id
public boolean val_res_id(String res_id){
if(res_id!=null){
	if(!cLength(res_id.toString(), 1, 25)){
			return false;
		}
		if (!cAlphaNumericSpecial(res_id,"_")){
				return false;
		}
		return true;
}else
	return false;

}

//pro_id

public boolean val_pro_id(String pro_id){
if(pro_id!=null){
	if(!cLength(pro_id.toString(), 1, 25)){
			return false;
		}
		if (!cAlphaNumericSpecial(pro_id,"_")){
				return false;
		}
		return true;
}else
	return false;

}
//
public boolean val_pro_id_opt(String pro_id){
if(pro_id!=null){
	if(!cLength(pro_id.toString(), 1, 25)){
			return false;
		}
		if (!cAlphaNumericSpecial(pro_id,"_")){
				return false;
		}
		return true;
}else
	return true;

}
public boolean val_seq_id(String id){
if(id!=null){
	if(!cLength(id.toString(), 1, 25)){
			return false;
		}
		if (!cAlphaNumericSpecial(id,"_")){
				return false;
		}
		return true;
}else
	return false;

}
public boolean val_name(String name){
if(name!=null){
	if(!cLength(name.toString(), 0, 50)){
			return false;
		}
		if (!cNAME(name)){
				return false;
		}
		return true;
}else{
	return false;
}
}
public boolean val_department(String department){
if(department!=null){
	if(!cLength(department.toString(), 0, 100)){
			return false;
		}
		if (!cRemark(department)){
				return false;
		}
		return true;
}else{
	return true;
}
}
public boolean val_designation(String designation){
if(designation!=null){
	if(!cLength(designation.toString(), 0, 100)){
			return false;
		}
		if (!cRemark(designation)){
				return false;
		}
		return true;
}else{
	return true;
}
}
//guest_id

public boolean val_guestid(String guest_id){
if(guest_id!=null){
	if(!cLength(guest_id.toString(), 1, 10)){
			return false;
		}
		/*if (!cAlphaNumericSpecial(guest_id,"_")){
				return false;
		}*/
		return true;
}else
	return false;

}
//charges_id

public boolean val_chargesid(String charges_id){
if(charges_id!=null){
	if(!cLength(charges_id.toString(), 1, 10)){
			return false;
		}
		if (!cNumeric(charges_id)){
				return false;
		}
		return true;
}else
	return false;

}
//seq_id

public boolean val_seqid(String seq_id){
if(seq_id!=null){
	if(!cLength(seq_id.toString(), 1, 25)){
			return false;
		}
		if (!cNumeric(seq_id)){
				return false;
		}
		return true;
}else
	return false;

}

//IP
public boolean val_IP(String seq_id){
if(seq_id!=null){
	if(!cLength(seq_id.toString(), 1, 25)){
			return false;
		}
		if (!cAlphaNumeric(seq_id)){
				return false;
		}
		return true;
}else
	return false;

}
public boolean val_address(String address1){
	if(address1!=null){
		if(!cLength(address1.toString(), 0, 200)){
				return false;
			}
			if (!cRemark(address1)){
					return false;
			}
			return true;
	}else
		return false;
}
public boolean val_address_opt(String address1){
	if(address1!=null){
		if(!cLength(address1.toString(), 0, 200)){
				return false;
			}
			if (!cRemark(address1)){
					return false;
			}
			return true;
	}else
		return true;
}
public boolean val_age(String age){
	System.out.println("ageage "+age);
	if(age!=null){
		System.out.println("%%%%%%%%%%%%%%%%%%%%% "+age);
		if(!cLength(age, 1,5)){
				return false;
			}
			if (!cNumeric(age)){
					return false;
			}
			return true;
	}else
		return true;
}
public boolean val_gender(String gender){
	if(gender!=null){
		if(!cLength(gender.toString(), 1, 6)){
				return false;
			}
			if (!cAlphabet(gender)){
					return false;
			}
			return true;
	}else
		return false;
}

public boolean val_id(String id){
if(id!=null){
	if(!cLength(id.toString(), 1, 10)){
			return false;
		}
		if (!cNumeric(id)){
				return false;
		}
		return true;
}else
	return false;

}

public boolean val_item(String item){
if(item!=null){
    if(!cLength(item.toString(), 0, 150)){
            return false;
        }
        if (!cNAME(item)){
                return false;
        }
        return true;
}else{
    return false;
}
}

}

