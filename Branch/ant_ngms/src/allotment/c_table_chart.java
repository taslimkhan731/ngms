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

public class c_table_chart
{
	public String block_title="",block_id="";
	public String gh_id=""; /////gh
	public ArrayList<String> date_list  = new ArrayList<String>();
	public ArrayList<String> room_list  = new ArrayList<String>();
	public ArrayList<c_row_chart> row_chart_list   = new ArrayList<c_row_chart>();

	
}