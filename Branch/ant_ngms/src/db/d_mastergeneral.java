package db; 

import java.sql.*;

public class d_mastergeneral{
	
	public String pid,rollno,name_of_guest,designation,p_of_visit,arrival_time_date,amount_of_advance,rooms_allotted,total_no_of_beds_occupied,departure_time_date,charges_realised,bill_no,remarks;
	
	ngm NGM;
	
	public d_mastergeneral(ngm p_ngm){		
		NGM=p_ngm;
	}

	public boolean insertDB() throws Exception {
		boolean l_status=false;	
		validate("insertDB");
		validate_key("insertDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("insert into tr_master_general values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1,Integer.parseInt(pid));
		ps.setInt(2,Integer.parseInt(rollno));
		ps.setString(3, name_of_guest);
		ps.setString(4, designation);
		ps.setString(5, p_of_visit );
		ps.setString(6, arrival_time_date);
		ps.setString(7, amount_of_advance);
		ps.setString(8, rooms_allotted );
		ps.setString(9, total_no_of_beds_occupied);
		ps.setString(10, departure_time_date );
		ps.setString(11, charges_realised );
		ps.setInt(12,Integer.parseInt(bill_no));
		ps.setString(13, remarks);
		
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;		
	} 
	
	public boolean updateDB() throws Exception
	{
		boolean l_status=false;
		validate_key("updateDB");
		validate("updateDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("update tr_master_general set rollno=?,name_of_guest=?,designation=?,p_of_visit=?,arrival_time_date=?, amount_of_advance=?,rooms_allotted=? total_no_of_beds_occupied=?,departure_time_date=?,bill_no=?,remarks=? where pid=?");
		ps.setInt(1,Integer.parseInt(rollno));
		ps.setString(2, name_of_guest);
		ps.setString(3, designation);
		ps.setString(4, p_of_visit );
		ps.setString(5, arrival_time_date);
		ps.setString(6, amount_of_advance);
		ps.setString(7, rooms_allotted );
		ps.setString(8, total_no_of_beds_occupied);
		ps.setString(9, departure_time_date );
		ps.setString(10, charges_realised );
		ps.setInt(11,Integer.parseInt(bill_no));
		ps.setString(12, remarks);
		ps.setInt(13,Integer.parseInt(pid));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;
	}

	
	public boolean deleteDB() throws Exception
	{
		boolean l_status=false;
		validate_key("deleteDB");
		PreparedStatement ps=NGM.tr_conn.prepareStatement("Delete from tr_master_general where pid=?");
		ps.setInt(1,Integer.parseInt(pid));
		l_status=NGM.ins_utility.executeDB(ps);
		ps.close();
		return l_status;			
	}

	public boolean selectDB() throws Exception 
	{
		
		PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_master_general where pid=?");
		ps.setInt(1,Integer.parseInt(pid));
		ResultSet rs=ps.executeQuery();
		boolean l_status=false;
		while(rs.next()){
			this.pid=rs.getString("pid");
			this.rollno=rs.getString("rollno");
			this.name_of_guest=rs.getString("name_of_guest");
			this.designation=rs.getString("designation");
			this.p_of_visit=rs.getString("p_of_visit");
			this.arrival_time_date=rs.getString("arrival_time_date");
			this.amount_of_advance=rs.getString("amount_of_advance");
			this.rooms_allotted=rs.getString("rooms_allotted");
			this.total_no_of_beds_occupied=rs.getString("total_no_of_beds_occupied");
			this.departure_time_date=rs.getString("departure_time_date");
			this.charges_realised=rs.getString("charges_realised");
			this.bill_no=rs.getString("bill_no");
			this.remarks=rs.getString("remarks");
			l_status=true;
		}
		rs.close();
		ps.close();
		return l_status;
	} 	
public boolean checkbilldetails()throws Exception
{
	PreparedStatement ps=NGM.tr_conn.prepareStatement("select * from tr_master_general where pid=?");
	ps.setInt(1,Integer.parseInt(pid));
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


public void validate(String p_method) throws Exception 
{
	if(pid!=null) {
		if(!NGM.ins_val.cLength(pid.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(pid.toString()))
			throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
	 }
	 else
			throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);
	
	 if(rollno!=null) {
		if(!NGM.ins_val.cLength(rollno.toString(), 1, 10))
			throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(rollno.toString()))
			throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
	 }
	 else
		 throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);
	
	 if(name_of_guest!=null) {
			if(!NGM.ins_val.cLength(name_of_guest.toString(), 1, 25))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(name_of_guest.toString()))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);
		 
	 if(designation!=null) {
			if(!NGM.ins_val.cLength(designation.toString(), 1, 25))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(designation.toString()))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);

	 if(p_of_visit!=null) {
			if(!NGM.ins_val.cLength(p_of_visit.toString(), 1, 50))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(p_of_visit.toString()))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);

	 if(arrival_time_date!=null) {
			if(!NGM.ins_val.cLength(arrival_time_date.toString(), 1, 25))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cDate(arrival_time_date.toString(),""))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);

	 if(amount_of_advance!=null) {
			if(!NGM.ins_val.cLength(amount_of_advance.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(amount_of_advance.toString()))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);

	 if(rooms_allotted!=null) {
			if(!NGM.ins_val.cLength(rooms_allotted.toString(), 1, 10))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(rooms_allotted.toString()))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);

	 if(total_no_of_beds_occupied!=null) {
			if(!NGM.ins_val.cLength(total_no_of_beds_occupied.toString(), 1, 25))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(total_no_of_beds_occupied.toString()))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);

	 if(departure_time_date!=null) {
			if(!NGM.ins_val.cLength(departure_time_date.toString(), 1, 25))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cDate(departure_time_date.toString(),""))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);
	
	 if(charges_realised!=null) {
			if(!NGM.ins_val.cLength(charges_realised.toString(), 1, 25))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(charges_realised.toString()))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);

	 if(bill_no!=null) {
			if(!NGM.ins_val.cLength(bill_no.toString(), 1, 25))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cNumeric(bill_no.toString()))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);

	 if(remarks!=null) {
			if(!NGM.ins_val.cLength(remarks.toString(), 1, 100))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);
			else if (!NGM.ins_val.cAlphaNumeric(remarks.toString()))
				throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral", p_method, NGM);	
		 }
		 else
				throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);

	 
}	 
public void validate_key(String p_method) throws Exception 
{	
	if(pid!=null){
		if(p_method=="insertDB"){
			if(!NGM.ins_utility.checkConstrain(NGM.tr_conn, "tr_master_general", "pid", pid.toString(),NGM))
				throw NGM.ins_utility.setException("m_mastergeneral", " Already existing", p_method, NGM);
		}
		if(!NGM.ins_val.cLength(pid.toString(), 1, 1000))
			throw NGM.ins_utility.setException("m_mastergeneral", "Invalid mastergeneral length", p_method, NGM);
		else if (!NGM.ins_val.cNumeric(pid.toString()))
			throw NGM.ins_utility.setException("m_mastergeneral", "Invalid  mastergeneral", p_method, NGM);
	}
	else
		throw NGM.ins_utility.setException("m_mastergeneral", "Null mastergeneral", p_method, NGM);
		  
}

}