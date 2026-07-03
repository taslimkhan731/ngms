package db;
import java.io.PrintWriter;
import java.sql.Connection;

import template.ngsession;
import java.sql.SQLException;

import common.DBConnect;
import common.conversion;
import common.utility;
import common.validation;
import common.db_validation;
import template.doc;
import template.http;
import template.masterpanel;
import template.ngtemplate;
import template.ngproperty;

public class ngm {
	
	public ngsession 		ins_sess;
	public m_uservsrole     ins_userrole;
	public PrintWriter 		ins_writer;
	public Connection 		tr_conn,conn;
	public utility 			ins_utility;
	public ngtemplate 		ins_template;
	public http				ins_http;
	public doc				ins_doc;
	public masterpanel 		ins_panel;
	public validation 		ins_val;
	public db_validation    ins_dbval;
	public conversion       ins_conv;
	public ngproperty      ins_ngmprop;
	public String domain_java_shortcode_path	=	"s_";
	public String domain_java_shortcode			=	"";
	public String domain_master_servlet_shortcode	=	"";
	public String domain_js_shortcode_path	=	"";
	public String domain_js_shortcode		=	"";
//	public String domain_db_shortcode		=	"D6.";
	public String domain_jsp_shortcode_path	=	"";//for ais ais/
	public String schema_java_name			=	"";
	public static String fileName=null;
	public String cur_sevletname="";
	public String cur_task="";
	public ngm()
	{		
		try{
			ins_utility		= new utility();
			ins_template	= new ngtemplate();
			ins_http		= new http();
			ins_doc			= new doc();
			ins_panel		= new masterpanel();
			ins_val			= new validation();
			ins_dbval		= new db_validation();
			ins_conv		= new conversion();
			this.tr_conn	= new DBConnect().getConnection();
			this.conn		= new DBConnect().getConnection();
			tr_conn.setAutoCommit(false);
			ins_ngmprop = new ngproperty();
			ins_ngmprop.setpropertey(this.conn);
			ins_utility		= new utility(ins_ngmprop.getpropvalue("smsurl"),ins_ngmprop.getpropvalue("smsuserid"),ins_ngmprop.getpropvalue("smspassword"));
			
		}
		catch (Exception se){
			System.out.println("SQL Error");
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.tr_conn.close();
		this.conn.close();
		System.runFinalization();
		System.gc();
	}
}
