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


public class c_selected_details
{
	
public String etableid="",etdid="",esource_status="", eroom_type="",  eroomno="",  ebedno="", edate="", eres_type="", egh_id="", eblock_id="";
public int emax_no_bed=0;


public boolean setParameters(String ptableid,String ptdid,String source_type,String selected_type,String proomno,String  pdate,String pbedno,int pnumberofbeds,String p_res_type,String pgh_id,String pblock_id) throws SQLException
{
	etableid = ptableid;
	etdid = ptdid;
	esource_status = source_type;
    eroom_type=selected_type;
	eroomno=proomno;
	ebedno=pbedno;
    edate=pdate;
    emax_no_bed=pnumberofbeds;
    eres_type=p_res_type;
    egh_id=pgh_id;
    eblock_id=pblock_id;

	return true;
}
}
