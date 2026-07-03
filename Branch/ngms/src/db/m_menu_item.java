package db;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import com.itextpdf.text.log.SysoLogger;
import common.db_validation;
import common.utility;
import template.ngmenu;
import template.ngmenudata;
public class m_menu_item extends db_validation{
	public int menu_id;
	public String menu_name;
	public String page_url;
	public int parent_id;
	public String image_url;
	public String fn_call;
	public int priority;
	ngm NGM;

	public m_menu_item(ngm p_ngm){
		this.NGM=p_ngm;
	}
	
	public boolean insertDB()throws Exception {
		VAL_insertDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into m_menu_item values(?,?,?,?,?,?,?)");
		ps.setInt(1,menu_id);
		ps.setString(2, menu_name);
		ps.setString(3, page_url);
		ps.setInt(4, parent_id);
		ps.setString(5, image_url);
		ps.setString(6, fn_call);
		ps.setInt(7, priority);
		System.out.println("MENUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU       "+ps);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean updateDB()throws Exception {
		VAL_updateDB();
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update m_menu_item set menu_name=?,page_url=?,parent_id=?,image_url=?,fn_call=?,priority=? where menu_id=?");
		ps.setString(1, menu_name);
		ps.setString(2, page_url);
		ps.setInt(3, parent_id);
		ps.setString(4, image_url);
		ps.setString(5, fn_call);
		ps.setInt(6, priority);
		ps.setInt(7,menu_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB()throws Exception {
		boolean l_status=false;
		//validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from m_menu_item where menu_id=?");
		ps.setInt(1,menu_id);
		System.out.println("MENUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUDELEETE       "+ps);
		l_status=ps.execute();
		ps.close();
		return l_status;
	}
	
	public boolean selectDB()throws Exception {
		//validate_key("selectDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from m_menu_item where menu_id=?");
		NGM.ins_utility.setNullInteger(ps,1, menu_id);
		ResultSet rs=ps.executeQuery();
		boolean status=false;
		while(rs.next()){
			menu_name=rs.getString("menu_name");
			page_url=rs.getString("page_url");
			parent_id=rs.getInt("parent_id");
			image_url=rs.getString("image_url");
			fn_call=rs.getString("fn_call");
			menu_id=rs.getInt("menu_id");
		status=true;
		}
		rs.close();
		ps.close();
		return status;
	}
	
		
	public String getMenu()throws Exception{
		utility util=new utility();
		ngmenudata md=new ngmenudata();
		NGM.ins_val.cNumeric(NGM.ins_sess.role_code.toString());


		String	str_rolelist =util.getrolelist(NGM.ins_sess.role_code);
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select distinct a.* from M_MENU_ITEM a,M_ROLE_MENU b where a.menu_id=b.menu_id and role_code in ("+str_rolelist+") order by a.priority");

		//PreparedStatement ps=NGM.tr_conn.prepareStatement("select a.* from M_MENU_ITEM a,M_ROLE_MENU b where a.menu_id=b.menu_id and role_code in('07') order by a.priority");

System.out.println("Menu_item  "+ps);
		//Array array = NGM.tr_conn.createArrayOf("VARCHAR",NGM.ins_sess.role_code.toArray());
		//ps.setArray(1, array );
		

		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			md.addMenu_Item(rs.getInt("menu_id"), rs.getString("menu_name"), rs.getInt("parent_id"), rs.getString("image_url"), rs.getString("page_url"), rs.getString("fn_call"));
		}
		rs.close();
		ps.close();
		return new ngmenu().get_menu(md,NGM);
		
		
	}

	


//------------------------------------------------------------------------------------------------Validation--------------------------------------------------------------------------------------

public void VAL_insertDB() throws Exception 
{
		if(!(val_menuname(menu_name) && val_page_url(page_url) && val_image_url(image_url) && val_fn_call(fn_call))){
			throw NGM.ins_utility.setException("menu_item", "Invalid menu item", "VAL_insertDB", NGM);
		}
}
public void VAL_updateDB() throws Exception 
{
	System.out.println("menu_name "+menu_name);
	System.out.println("page_url "+page_url);
	System.out.println("image_url "+image_url);
	System.out.println("fn_call "+fn_call);
		if(!(val_menuname(menu_name) && val_page_url(page_url) && val_image_url(image_url) && val_fn_call(fn_call))){
			throw NGM.ins_utility.setException("menu_item", "Invalid menu item", "VAL_insertDB", NGM);
		}
}


public boolean val_menuname(String menu_name){
	if(menu_name!=null){
		if(!cLength(menu_name.toString(), 0, 100)){
				return false;
			}
		if (!cAlphaSpecial(menu_name," ")){
					return false;
			}
			return true;
	}else
		return false;
	
}

public boolean val_page_url(String page_url){
	if(page_url!=null){
		if(!cLength(page_url.toString(), 0, 150)){
				return false;
			}
			if (!cAlphaSpecial(page_url,"_? = ")){
					return false;
			}
			return true;
	}else
		return true;
	
}

public boolean val_image_url(String image_url){
	if(image_url!=null){
		if(!cLength(image_url.toString(), 0, 255)){
				return false;
			}
			if (!cAlphaNumericSpecial(image_url,"_? =-")){
					return false;
			}
			return true;
	}else
		return true;
	
}
public boolean val_fn_call(String fn_call){
	if(fn_call!=null){
		if(!cLength(fn_call.toString(), 0, 255)){
				return false;
			}
			if (!cAlphaNumericSpecial(image_url,"_? =")){
					return false;
			}
			return true;
	}else
		return true;
	
}
}



//	public void validate(String p_method)throws Exception{
//		if(!(NGM.ins_val.cNumeric(menu_id.toString()))){
//			throw NGM.ins_utility.setException("m_menu_item", "invalid pslip_id", p_method, NGM);
//		}
//		else if(!(NGM.ins_val.cAlphaNumeric(menu_name))){
//			throw NGM.ins_utility.setException("m_menu_item", "invalid generate_date", p_method, NGM);
//		}
//		else if(!(NGM.ins_val.cAlphaNumeric(page_url))){
//			throw NGM.ins_utility.setException("m_menu_item", "invalid generate_date", p_method, NGM);
//		}
//		else if(!(NGM.ins_val.cNumeric(parent_id.toString()))){
//			throw NGM.ins_utility.setException("m_menu_item", "invalid generate_date", p_method, NGM);
//		}
//		else if(!(NGM.ins_val.cAlphaNumeric(image_url))){
//			throw NGM.ins_utility.setException("m_menu_item", "invalid generate_date", p_method, NGM);
//		}
//		else if(!(NGM.ins_val.cAlphaNumeric(fn_call))){
//			throw NGM.ins_utility.setException("m_menu_item", "invalid generate_date", p_method, NGM);
//		}
//	}
//	
//	public void validate_key(String p_method)throws Exception{
//		if(!(NGM.ins_val.cNumeric(menu_id.toString())))
//			throw NGM.ins_utility.setException("m_menu_item", "Invalid pslip_id", p_method, NGM);
//	}
//}
