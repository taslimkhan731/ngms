package allotment;

import db.d_allotment;
import db.d_checkin_allot;
import db.d_gadrequest;
import db.d_reservation_maintable;
import db.d_reservation_waitinglist;
import db.ngm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import common.utility;
import db.*;


public class c_column_chart
{
	public String roomno; //int
	public String date;
	public String status;
	public int max_no_beds=0; //dont forget in 
	public boolean is_heading =false;
	public ArrayList<d_allotment> list_alloted  = new ArrayList<d_allotment>();
	
}

		
