package db;

import java.sql.PreparedStatement;

public class exception_log{

  public String e_id;
  public String e_class;
  public String e_message;
  public String e_method;
  public String e_userid;
  ngm NGM;
  
  public exception_log(ngm p_ngm){
		NGM=p_ngm;
	}
  
	public boolean insertDB() {
		boolean status=false;
		try {
			System.out.println("Deepakkkkkkkkkkkk...........");
			Integer p_curval=NGM.ins_utility.getID(NGM.tr_conn, "e_id");
			NGM.ins_utility.updateID(NGM.tr_conn, "e_id", p_curval);
			PreparedStatement ps=NGM.conn.prepareStatement("insert into exception_log values(?,?,?,?,?,now())");
			ps.setString(1, p_curval.toString());
			ps.setString(2, e_class);
			ps.setString(3, e_message);
			ps.setString(4, e_method);
			ps.setString(5, e_userid);
			status=ps.execute();
			ps.close();
		} catch (Exception e) {				
			
		}
		return status;
	}
}
