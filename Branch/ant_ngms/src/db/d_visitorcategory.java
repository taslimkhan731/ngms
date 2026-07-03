package db; 

import java.sql.*;

import common.db_validation;

public class d_visitorcategory extends db_validation{
	
	public String vc_id="",desg_category="",priority="",category="",sub_cat="";
	
	ngm NGM;
	
	public d_visitorcategory(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into visitor_category values(?,?,?,?,?)");
		ps.setString(1,vc_id);
		ps.setString(2, desg_category);
		ps.setInt(3,Integer.parseInt(priority));
		ps.setString(4, category);
		ps.setString(5, sub_cat );
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update visitor_category set desg_category=?,priority=?,category=?,sub_cat=? where vc_id=?");
		ps.setString(1, desg_category);
		ps.setInt(2,Integer.parseInt(priority));
		ps.setString(3, category);
		ps.setString(4, sub_cat );
		ps.setString(5,vc_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	public boolean chkCodeDB() throws Exception {
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from visitor_category where vc_id=?");
		ps.setString(1,vc_id);
		System.out.println("+++++++++++++++++++++++++++++++++++ ps "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	}
	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from visitor_category where vc_id=?");
		ps.setString(1,vc_id);
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from visitor_category where vc_id=?");
		ps.setString(1,vc_id);
		System.out.println("+++++++++++++++++++++++++++++++++++ ps "+ps);
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.vc_id=rs.getString("vc_id");
			this.desg_category=rs.getString("desg_category");
			this.priority=rs.getString("priority");
			this.category=rs.getString("category");
			this.sub_cat=rs.getString("sub_cat");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
public boolean checkbilldetails()throws Exception
{
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from visitor_category where vc_id=?");
	ps.setInt(1,Integer.parseInt(vc_id));
	ResultSet rs=ps.executeQuery();
	boolean l_status=false;
	while(rs.next()){
			
		l_status=true;
	}
	rs.close();
	ps.close();
	return l_status;

}

//--------------------------------------------validation-----------------------------------------------------------------

public void VAL_insertDB() throws Exception 
{
	 val_vc_id(vc_id);
	 val_desg_category(desg_category);
	 val_priority(priority);
	 val_category(category);
	 val_sub_cat(sub_cat);
		 
}



public boolean val_sub_cat(String sub_cat){
	if(sub_cat!=null){
		if(!cLength(sub_cat.toString(), 0, 5)){
				return false;
			}
			if (!cAlphaNumeric(sub_cat)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_vc_id(String vc_id){
	if(vc_id!=null){
		if(!cLength(vc_id.toString(), 0, 10)){
				return false;
			}
			if (!cAlphaNumeric(vc_id)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_desg_category(String desg_category){
	if(desg_category!=null){
		if(!cLength(desg_category.toString(), 0, 100)){
				return false;
			}
			if (!cAlphaNumeric(desg_category)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_priority(String priority){
	if(priority!=null){
		if(!cLength(priority.toString(), 0, 2)){
				return false;
			}
			if (!cNumeric(priority)){
					return false;
			}
			return true;
	}else
		return false;
	
}
public boolean val_category(String category){
	if(category!=null){
		if(!cLength(category.toString(), 0, 1)){
				return false;
			}
			if (!cAlphaNumeric(category)){
					return false;
			}
			return true;
	}else
		return false;
	
}
}