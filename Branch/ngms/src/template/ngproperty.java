package template;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

public class ngproperty {
	public ArrayList<String> prop_id= new ArrayList<String>(); 
	public ArrayList<String> prop_value= new ArrayList<String>(); 
	public ArrayList<String> prop_desc= new ArrayList<String>(); 


public void setpropertey(Connection con){
	try{
		PreparedStatement ps=con.prepareStatement("select * from property");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			prop_id.add(rs.getString("prop_id"));    
			prop_value.add(rs.getString("prop_value"));    
			prop_desc.add(rs.getString("prop_desc"));    
		}
		 //close
	    rs.close();
		ps.close();
	}
	catch (Exception se){
		System.out.println("SQL Error");
	}
	
}
	
public String getpropvalue(String p_prop_id){
	String retstr=""; 
	for (int i = 0; i < prop_id.size(); i++){
	   if(prop_id.get(i).trim().equals(p_prop_id.trim())) {
		   	retstr = prop_value.get(i);    
	   }
	}
return retstr;
	}
}
