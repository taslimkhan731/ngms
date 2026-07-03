package template;
import java.util.Calendar;
import java.io.PrintWriter;
import java.math.*;
import java.util.Date;
import java.util.StringTokenizer;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.log.SysoLogger;

import common.common;
import db.*;
import model.*;
public class doc {
	
/*	public void docMenu(ngm p_ngm)throws Exception{      
		
		String b,c;
		String a=
				"" +
				"" +
				"";

	   p_ngm.ins_writer.write(a);
	}*/
	
	public void Header(ngm p_ngm)throws Exception {
		
		m_menu_item ins_menuitem	=	new m_menu_item(p_ngm);	
		String temp_username = p_ngm.ins_sess.username;
		String[] temp_user = null;
		if(temp_username.length()>10){
			temp_user = temp_username.split(" ");
		}else{
			temp_user = temp_username.split("#");
		}
		
		if(temp_user[0].length()>10){
			temp_user = temp_user[0].substring(0, 10).split("#");
		}
		//String location	=	p_ngm.ins_utility.getLocation(p_ngm);
		String header="<header class='header'>" +
				" <a class='logo' href='#' onclick=\"menu_url_call('index?task=abc','"+p_ngm.ins_sess.process_id+ "')\"></a>" +
				" <nav role='navigation' class='navbar navbar-static-top'>" +
				"<div><center><b><h3 style='color:white'>e-Bhawan - ODISHA</h3></b></center></div>"+
				"<div class='navbar-right'>" +
				//"<div class='info'><div class='info-menu'><div class='user-info'><h4>"+p_ngm.ins_sess.username+"</h4><p>Last Login: <small>"+p_ngm.ins_sess.last_login_date+"</small> <br>Department: <small>Guest House</small></p></div>" +
				"<div class='info'><div class='info-menu'><div class='user-info'><div class='pull-left'><h4>"+temp_user[0]+"</h4> <p>Last Login: <small>"+p_ngm.ins_sess.last_login_date+"</small></p> <p><a href='#'onclick=\"menu_url_call('s_change_password_admin?task=abc','"+p_ngm.ins_sess.process_id+"')\"><i class='fa fa-lock'></i><span> Change Password</span></a>";
//				if(p_ngm.ins_sess.role_code.get(0).equals("01")||p_ngm.ins_sess.role_code.get(0).equals("02")||p_ngm.ins_sess.role_code.get(0).equals("03")||p_ngm.ins_sess.role_code.get(0).equals("16")||p_ngm.ins_sess.role_code.get(0).equals("18"))
				//header=header+"<a href='#' onclick=\"menu_url_call('s_view?task=abc','"+p_ngm.ins_sess.process_id+"')\"><span><i class='fa fa-eye'></i> View</span></a>";
				
				header=header+"</p></div>"+
				
				"<div class='user-img float-right'><img src='img/user.jpg'><a href='#' onclick=\"menu_url_call('logout?task=abc','"+p_ngm.ins_sess.process_id+"')\"><i class='fa fa-sign-out'></i><span> Logout</span></a></div>"+
				 
				"</div></div> </div>" +
				"</nav>" +
				"<div class='menu navbar'>" +
				"<ul class='nav navbar-nav'>" +
				""+ins_menuitem.getMenu()+ "" +
				"</ul>" +
			//	"<div class='pull-right'>" +
			//	"<ul class='nav navbar-nav'><li><a class='dropdown-toggle' href='#'><i class='fa fa-user'></i><span> "+p_ngm.ins_sess.username+" </span></a></li>" +
			//	"<li><a href='#'onclick=\"help_document_gh('"+p_ngm.ins_sess.process_id+"')\">Help Document</a></li>" +
			//	"<li></li>" +
			//			"" +
					//	"<li></li>" +
					//	"<li></li>" +

				//"<li></li><li><a href='#' onclick=\"menu_url_call('logout?task=abc','"+p_ngm.ins_sess.process_id+"')\"><span>Logout</span></a></li>" +
			//	"</ul>" +
				//"</div>"
				 "</div>" +
				"</header>";
		p_ngm.ins_writer.write(header);
	}
	
	
	public void public_Header(ngm p_ngm)throws Exception {
		m_menu_item ins_menuitem	=	new m_menu_item(p_ngm);	
		//String location	=	p_ngm.ins_utility.getLocation(p_ngm);
		String header="<header class='header'>" +
				" <a class='logo' href='#' onclick=\"menu_url_call('s_publicdataentry?task=abc','"+p_ngm.ins_sess.process_id+ "')\"></a>" +
				" <nav role='navigation' class='navbar navbar-static-top'>" +
				"<div><center><b><h3 style='color:white'>e-Bhawan - ODISHA</h3></b></center></div>"+
				"<div class='navbar-right'>" +
				"<div class='info'><div class='info-menu'><div class='user-info'><h4>"+p_ngm.ins_sess.username+"</h4><p>Last Login: <small>"+p_ngm.ins_sess.last_login_date+"</small></p></div>" +
				"<div class='user-img'><img src='img/user.jpg'></div>" +
				"</div></div> </div>" +
				"</nav>" +
				"<div class='menu navbar'>" +
				"<ul class='nav navbar-nav'>" +
				""+ins_menuitem.getMenu()+ "" +
				"</ul>" +
				"<div class='pull-right'>" +
				"<ul class='nav navbar-nav'><li><a class='dropdown-toggle' href='#'><i class='fa fa-user'></i><span> "+p_ngm.ins_sess.username+" </span></a></li>" +
				"<li></li>" +
				"<li><a href='#'onclick=\"menu_url_call('s_cpwd?task=abc','"+p_ngm.ins_sess.process_id+"')\"><i class='fa fa-lock'></i><span> Change Password</span></a></li>" +
				"<li><a href='#' onclick=\"menu_url_call('logout_public?task=abc','"+p_ngm.ins_sess.process_id+"')\"><i class='fa fa-sign-out'></i><span> Logout</span></a></li>" +
				"</ul>" +
				"</div></div>" +
				"</header>";
		p_ngm.ins_writer.write(header);
	}
	
	
	
	public void Body(ngm p_ngm)throws Exception {
		String body=" <body class='skin-nic'>" +
				"<div class='maindiv'>" ;
		p_ngm.ins_writer.write(body);	
	}
	
	
	
	public void closeBody(ngm p_ngm)throws Exception {
		p_ngm.ins_writer.write("</div></body>");
	}
	
	
			
	public void getHiddenField(ngm p_ngm)
	{
		p_ngm.ins_writer.write("<input type=\"hidden\" value=\""+p_ngm.ins_sess.process_id+"\" name=\"process_id\" id=\"process_id\">");
	}
	
	public void openForm(ngm p_ngm,String p_formname,String p_method){
		p_ngm.ins_writer.write("<form name=\""+p_formname+"\" method=\""+p_method+"\" id=\""+p_formname+"\">");
	}
	public void openFormM(ngm p_ngm,String p_formname,String p_method){
		p_ngm.ins_writer.write("<form name=\""+p_formname+"\" method=\""+p_method+"\" id=\""+p_formname+"\" enctype=\"multipart/form-data\">");
	}
	
	public void closeForm(ngm p_ngm){
		p_ngm.ins_writer.write("</form>");
	}
	
	public void docClosePageTitle(ngm p_ngm){
		p_ngm.ins_writer.write("</div>");
	}
	
	public void docCloseToolbar(ngm p_ngm){
		p_ngm.ins_writer.write("</div></div>");
	}
	
	public void docHeaderInner(ngm p_ngm)throws Exception {
		String location	=	p_ngm.ins_utility.getLocation(p_ngm);
		String tem="<div id=\"border-top\" class=\"h_bluen\" align=\"center\">" +
					"<span class=\"spanln\"></span>" +
					"<span class=\"spanrn\"></span>" +
					"<div class=\"log_details\">" +
					"<img src=\"images/menu/icon-16-user.png\" />" +
					"<span>Welcome </span>" + p_ngm.ins_sess.username+ "<br />";
		//tem+=this.get_dept_ofc(p_ngm);
		tem+=	"<span>Last Access: </span>" +p_ngm.ins_sess.last_login_date+
			    	"<span>  From IP: </span>"+p_ngm.ins_sess.client_ip +"</div>" +
					"<span class=\"line_sep\"></span>" +
					"<div id=\"title-inner\" class=\"header_center\">"+
					//"<img src=\"images/common/header.png\" width=\"50\" height=\"40\" />"+
					//"<div class=\"location\">"+location+"</div>"+
					"</div>" +
					"</div>";
		p_ngm.ins_writer.write(tem);
	}
	
	

	public void opencontainerdiv(ngm p_ngm)throws Exception{		
		String s1="<div id=\"content-box\">"+
					"<div id=\"container\">";										
					p_ngm.ins_writer.write(s1);
	}
	public void openwrapperdiv(ngm p_ngm)throws Exception{		
		String s1="<div id=\"wrapper\">";
					p_ngm.ins_writer.write(s1);
	}
	public void closewrapperdiv(ngm p_ngm)throws Exception{		
		String s1="</div>";
					p_ngm.ins_writer.write(s1);
					p_ngm.ins_writer.write("</div>");
	}
	
	public void closecontainerdiv(ngm p_ngm)throws Exception{
		String s1=	"</div>"+
					"<div class=\"clr\"></div>"+
					"</div>";
		p_ngm.ins_writer.write(s1);
	}
	
	
	public void projectform(ngm p_ngm) throws Exception
	{
		String s1="<div id='projectform'> <form id='projectformid'><input type=hidden id='project_process_id' value='"+p_ngm.ins_sess.process_id+"'></form></div>";
		p_ngm.ins_writer.write(s1);
	//	return s1;
		
	} //end of method
	
	public void docFooter(ngm p_ngm)throws Exception {
		p_ngm.ins_writer.write("</div>");
		this.projectform(p_ngm);
		this.getfooterpage(p_ngm);
		p_ngm.ins_writer.write("<div class=\"footer hidden-print\"><center>Website Content Managed by <b>Home Department</b> &copy 2020</center></div>");
	}

	public void openHtml(ngm p_ngm)throws Exception {
		p_ngm.ins_writer.write("<html>");
	}

	public void openHtmlHead(ngm p_ngm)throws Exception {
		p_ngm.ins_writer.write("<head>");
	}

	public void getTitle(ngm p_ngm)throws Exception{
		p_ngm.ins_writer.write("<title>NGMS</title>");
	}
	
	public void getFavicon(ngm p_ngm)throws Exception{
		p_ngm.ins_writer.write("<link rel=\"icon\" type=\"images/common/ico\" href=\"images/common/url_logo.ico\"></link>");
		p_ngm.ins_writer.write("<link rel=\"shortcut icon\" href=\"images/common/url_logo.ico\"></link>");
	}

	public void closeHtmlHead(ngm p_ngm)throws Exception {
		p_ngm.ins_writer.write("</head>");
	}

	public void closeHtml(ngm p_ngm)throws Exception {
		p_ngm.ins_writer.write("</html>");
	}

	public void openHtmlBody(ngm p_ngm)throws Exception {
		p_ngm.ins_writer.write("<body class='skin-nic'>");
//		p_ngm.ins_writer.write("<div class='pagedivcss' id='pagediv'>");
		
		//getWait(p_ngm);
	}
	
	public void openHtmlBody_admin(ngm p_ngm)throws Exception {
		p_ngm.ins_writer.write("<body class=\"admin\">");
		//getWait(p_ngm);
	}

	public void getWait(ngm p_ngm) {
		p_ngm.ins_writer.write("<div id=\"popup\" align=\"center\"  class=\"menupopup\">"+
							   "<table class=\"popup\">"+
							   "<tr>"+
							   "<td align=\"center\">"+
							  // "Processing....."+
							   "<img width=\"128\" height=\"128\" src=\"images/common/preproc.gif\"  />"+
							   "</td>"+
							   "</tr>"+
							   "</table>"+
							   "</div>");
		
	}
	public void openHtmlBodyOfLogin(ngm p_ngm)throws Exception {
		
		Calendar calend	= Calendar.getInstance();
		SimpleDateFormat sdf =  new java.text.SimpleDateFormat("dd-MM-yyyy");
		String date=sdf.format(calend.getTime());
		
		StringTokenizer str=new StringTokenizer(date,"-");
		p_ngm.ins_writer.write("<body  class=\"login\" onload=\"verify("+str.nextToken()+","+str.nextToken()+","+str.nextToken()+")\">");
		//getWait(p_ngm);
}
	
public void openHtmlBodyPublic(ngm p_ngm)throws Exception {
		
		Calendar calend	= Calendar.getInstance();
		SimpleDateFormat sdf =  new java.text.SimpleDateFormat("dd-MM-yyyy");
		String date=sdf.format(calend.getTime());
		
		StringTokenizer str=new StringTokenizer(date,"-");
		p_ngm.ins_writer.write("<body  class=\"pub_login\" onload=\"verify("+str.nextToken()+","+str.nextToken()+","+str.nextToken()+")\">");
		//getWait(p_ngm);
}


public void docMenu_old(ngm p_ngm)throws Exception{
		m_menu_item ins_menuitem	=	new m_menu_item(p_ngm);	
		Date date 					= 	new Date();
		SimpleDateFormat curr_datetime =  new SimpleDateFormat ("E dd-MM-YYYY  hh:mm:ss a ");
		p_ngm.ins_writer.write( "<div id=\"header-box\">"+	
				/*"<div id=\"module-status\">" +				
				"<span class=\"ctime\">" + curr_datetime.format(date)+"</span>"+
					"<span class=\"viewsite\"><a onclick=\"redirect_link('s_change_password','"+p_ngm.ins_sess.process_id+"','abc')\">Change Password</a></span>" +
					"<span class=\"logout\"><a onclick=logout('"+p_ngm.ins_sess.process_id+"')>Log out</a></span>"+
				"</div>"+*/
				"<div id=\"module-menu\">"+ins_menuitem.getMenu()+ "</div>"+		
				"</div>");
	}
	


	public void display_icons_copy(String formid,String status,ngm p_ngm){
		p_ngm.ins_writer.write("<div class=\"breadcrumb\">"+
           "<ol class=\"list-inline pull-left\">"+
               "<li><a href=\"#\"> <i class=\"fa fa-file \"></i> Form Mangement</a></li>"+
          " </ol>"+
              " <ul class=\"pull-right\"> "+
              " <li><a href=\"#\"><i class=\"fa fa-plus-circle\"></i> <span class=\"text_links\">New </span></a></li>"+
              " <li><a href=\"#\"><i class=\"fa fa-folder-open\"></i> <span class=\"text_links\">History </span></a></li>"+
              "  <li><a href=\"#\"><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit </span></a></li>"+
              " <li><a href=\"#\"><i class=\"fa fa-refresh\"></i> <span class=\"text_links\">Refresh </span></a></li>"+
              " <li><a href=\"#\"><i class=\"fa fa-book\"></i> <span class=\"text_links\">Details </span></a></li>"+
              " <li><a href=\"#\" data-target=\".search_salary\" data-toggle=\"modal\"><i class=\"fa fa-search\"></i> <span class=\"text_links\" id=\"Search\">Search </span></a></li>"+
              " <li><a href=\"#\"><i class=\"fa fa-times-circle\"></i> <span class=\"text_links\">Close </span></a></li>"+
              " </ul>"+
              " </div>"); 
	}
	
	public void display_home_icons(ngm p_ngm){
		String str="";
		
		str="<div class='content-header'>" +
				"<div class='pull-left'><h1> Home </h1></div>";
		str+= " </div>";
		p_ngm.ins_writer.write(str);
	}
	
	
	public void display_report_icons(String serv,String task,ngm p_ngm,String report,String pdf_name){
		System.out.println("REPORT DOC     ********************");
		String str="";
		str="<div class=\"breadcrumb\">"+
				"<ol class=\"list-inline pull-left\">";
		str+="<li><a href=\"#\"> <i class=\"fa fa-file \"></i> Report : </a></li>";
		str+=  "<li><a href=\"#\">"+report+" </a></li>";
		str+=" </ol>"+
              " <ul class=\"pull-right\"> "+
              " <li><a href=\"#\"onclick=\"getpdfreport('"+p_ngm.ins_sess.process_id+"','"+pdf_name+"')\"><i class=\"fa fa-file\"></i> <span class=\"text_links\">Export to PDF</span></a></li>"+
              " <li><a href=\"#\"onclick=\"reloadpagepost('index','abc','"+p_ngm.ins_sess.process_id+"')\"><i class=\"fa fa-times-circle\"></i> <span class=\"text_links\">Go to Home</span></a></li>"+             
              " <li><a href=\"#\" onclick=\"reloadpagepost('"+serv+"','"+task+"','"+p_ngm.ins_sess.process_id+"')\"><i class=\"fa fa-refresh\" ></i> <span class=\"text_links\" id=\"text_links\">Refresh</span></a></li>"+
                "</ul>"+
              " </div>";
		p_ngm.ins_writer.write(str);
	}		
	
	public void display_public_icons(ngm p_ngm,String page_task,String title,String icon){
		String str="";
		if (icon=="")
		{
			System.out.println("NO ICON name is specified");
			str="<div class='content-header'>" +
					"<div class='pull-left'><h1> <i class='fa fa-book'></i> " + title +"</h1></div>"+
					"<div class='pull-right'>" ;
		}
		else
		{
			System.out.println("Contains one icon"+icon);
			str="<div class='content-header'>" +
					"<div class='pull-left'><h1> <i class='fa "+icon+"'></i> " + title +"</h1></div>"+
					
						
					"<div class='pull-right'>" ;
			
		}
		
		if(page_task.equals("s_profile"))
		{
			//str+=  " <a onclick=\"show_form_pubdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Public Dataentry</span></a>"; 
			//str+=  " <a onclick=\"show_form_editdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Edit Dataentry</span></a>"; 
			str+=  " "; 
		
		}
		
		if(page_task.equals("s_publicdataentry"))
		{
			//str+=  " <a onclick=\"show_form_pubdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Public Dataentry</span></a>"; 
			//str+=  " <a onclick=\"show_form_editdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Edit Dataentry</span></a>"; 
			str+=  " <a onclick=\"show_form_new()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">New Application</span></a>"; 
			str+=  " <a onclick=\"get_list_gh2()\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Edit</span></a>"; 
//			str+=  " <a onclick=\"show_form_cancel()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel</span></a>"; 
//			str+=  " <a onclick=\"show_form_import()\" class='btn btn-success'><i class=\"fa fa-download\"></i> <span class=\"text_links\">Import</span></a>"; 
//			str+=  " <a onclick=\"show_form_publicrequest()\" class='btn btn-success'><i class=\"fa fa-male\"></i> <span class=\"text_links\">Public request</span></a>"; 
//		  
			str+=  " <a onclick=\"printapplication()\" class='btn btn-success'><i class=\"fa fa-print\"></i> <span class=\"text_links\">Print</span></a>";
			str+=  " <a onclick=\"printstatus()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Status</span></a>";
		}
		str+= "</div> </div>";
		//////////////////////////////////////
		
		
		//////////////////////////////////
		p_ngm.ins_writer.write(str);
		
	}
	
	public void display_page_icons(ngm p_ngm,String page_task,String title,String icon) throws Exception{
		String str="";
		String t_gh_id = "01";
		PreparedStatement gh=p_ngm.conn.prepareStatement("select gh_id,gh_name from gh_details");
		PreparedStatement block=p_ngm.conn.prepareStatement("select block_id,block_name from room_block where gh_id=?");
		block.setString(1, t_gh_id);
		if (icon=="")
		{
			System.out.println("value is null");
			str="<div class='content-header'>" +
					"<div class='pull-left'><h1> <i class='fa fa-book'></i> " + title +"</h1></div>"+
					"<div class='pull-right'>" ;
		}
		else
		{
			/*str="<div clas	s='content-header row'>" +
					"<div class='pull-left col-sm-2'><h1> <i class='fa "+icon+"'></i> " + title +"</h1></div>"+
					"<div class='header-center col-sm-5'>" +
						"<div class='small-box-header bg-teal col-sm-5'> " +
						"<label class='col-sm-5 no-padding label-select'>Select City:</label>" +
						"<div class='col-sm-7 no-padding'>" +p_ngm.ins_utility.getguesthouselistkeralahouse(p_ngm.tr_conn,p_ngm.ins_sess.user_id)+ //getguesthouselist      ///getguesthouselistkeralahouse
						//"<select id='sel_id' class='form-control' name='sel_id' onchange=''><option value='0'>--select--</option><option value='1'>1 - ODISHA House</option><option value='2'>2 - Odisha</option></select>" +
						"</div></div><div class='col-sm-1'></div>" +
						"<div class='small-box-header bg-teal col-sm-6'> " +
						"<label class='col-sm-5 no-padding label-select'>Select Guest House:</label>" +
						"<div class='col-sm-7 no-padding'>" +p_ngm.ins_utility.getguesthouselistkeralahouse1(p_ngm.tr_conn,p_ngm.ins_sess.user_id)+ //getguesthouselist      ///getguesthouselistkeralahouse
						//"<select id='sel_id' class='form-control' name='sel_id' onchange=''><option value='0'>--select--</option><option value='1'>1 - ODISHA House</option><option value='2'>2 - Odisha</option></select>" +
						"</div></div>"+
					"</div>"+
					"<div class='pull-right'>" ;*/
			
			str="<div class='content-header row'>" +
					"<div class='pull-left col-sm-2'><h1> <i class='fa "+icon+"'></i> " + title +"</h1></div>"+
					"<div class='header-center col-sm-5'>" +
						"<div class='small-box-header bg-teal col-sm-5'> " +
						"<label class='col-sm-5 no-padding label-select'>Select City:</label>" +
						"<div class='col-sm-7 no-padding'>" +p_ngm.ins_utility.getDBcombo_special_common("sel_id","form-control med", "sel_id","", gh,t_gh_id,p_ngm)+ //getguesthouselist      ///getguesthouselistkeralahouse
						//"<select id='sel_id' class='form-control' name='sel_id' onchange=''><option value='0'>--select--</option><option value='1'>1 - ODISHA House</option><option value='2'>2 - Odisha</option></select>" +
						"</div></div><div class='col-sm-1'></div>" +
						"<div class='small-box-header bg-teal col-sm-6'> " +
						"<label class='col-sm-5 no-padding label-select'>Select Guest House:</label>" +
						"<div class='col-sm-7 no-padding'>" +p_ngm.ins_utility.getDBcombo_special_common("sel_block","form-control med", "sel_block","", block,"",p_ngm)+ //getguesthouselist      ///getguesthouselistkeralahouse
						//"<select id='sel_id' class='form-control' name='sel_id' onchange=''><option value='0'>--select--</option><option value='1'>1 - ODISHA House</option><option value='2'>2 - Odisha</option></select>" +
						"</div></div>"+
					"</div>"+
					"<div class='pull-right'>";
			
		}
		
				
//           "<ol class=\"list- pull-left\">";
//		
//		str+="<li><a href=\"#\" onClick=\"cancel_creation('"+p_ngm.ins_sess.process_id+"')\"> <i class=\"fa fa-user \"></i>"+title+"</a></li>";
//		str+=" </ol>"+
//              " <ul class=\"pull-right\"> ";

		if(page_task.equals("s_sms_allotment"))
		{
			str+=  " <a onclick=\"sms_list('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">SMS</span></a>";
			//str+=  " <a onclick=\"cancelfoodbill('0')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Cancel Item</span></a>";
			
		}
		
		if(page_task.equals("s_dailyguest"))
        {
            /*str+=  " <a onclick=\"addfooddetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Food Details</span></a>";
            
            str+=  " <a onclick=\"editfooddetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Food Details</span></a>";*/
            str+=  " <a onclick=\"dailyguestlist(1)\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">List</span></a>";
            //str+=  " <a onclick=\"dailyguestlist('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">List</span></a>";             
        
        }
		
		if(page_task.equals("s_foodbill"))
		{
			str+=  " <a onclick=\"foodbill('0')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Item</span></a>";
			//str+=  " <a onclick=\"cancelfoodbill('0')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Cancel Item</span></a>";
			
		} 
		if(page_task.equals("s_vehiclemaster"))
		{
			str+=  " <a onclick=\"addvehiclemaster()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Vehiclemaster</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editvehiclemaster()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Vehiclemaster</span></a>";
			str+=  " <a onclick=\"deletevehiclemaster()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Vehiclemaster</span></a>";
				         
		
		} 
		
		if(page_task.equals("s_vehicleallotment"))
		{
			str+=  " <a onclick=\"addvehicleallotment('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Vehicleallotment</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editvehicleallotment('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Vehicleallotment</span></a>";
			//str+=  " <a onclick=\"deletevehicleallotment('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Vehicleallotment</span></a>";
			str+=  " <a onclick=\"vehiclereport('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Vehicle Allotment Report</span></a>";
			         
		
		} 
		if(page_task.equals("s_moderator"))
		{
			String clientid = p_ngm.ins_ngmprop.getpropvalue("clientid");
			str+=  " <a onclick=\"get_list_gh('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Application List</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"show_form_editmoderation('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Moderation</span></a>";
			str+=  " <a onclick=\"importlogin('"+clientid+"')\" class='btn btn-success hide'><i class=\"fa fa-download\"></i> <span class=\"text_links\">Import</span></a>"; 
//			str+=  " <a onclick=\"show_form_publicrequest('1')\" class='btn btn-success'><i class=\"fa fa-male\"></i> <span class=\"text_links\">Public request</span></a>"; 
			str+=  " <a onclick=\"showEditGuestDetails('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Edit Guest Details</span></a>"; 
			 str+=  " <a onclick=\"showCancelReservation('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Cancel Reservation</span></a>";
		}  
		if(page_task.equals("s_taskauthorization"))
		{
			str+=  " <a onclick=\"add_authorization()\" class='btn btn-primary'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Authorization</span></a>";
			str+=  " <a onclick=\"edit_authorization('1')\" class='btn btn-primary'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit</span></a>";
			str+=  " <a onclick=\"delete_authorization('1')\" class='btn btn-primary'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete</span></a>";			         
		
		
		}
		
		if(page_task.equals("s_statusallotment"))
		{
			str+=  " <a onclick=\"statusallotment()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Status Allotment</span></a>";
			
		} 
		if(page_task.equals("s_allot_occupancy"))
		{
			str+=  " <a onclick=\"allotOccupancyList()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Allot and Occupancy List</span></a>";
			
		} 
		if(page_task.equals("s_arrival"))
		{
			str+=  " <a onclick=\"arrivalList()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Arrival List</span></a>";
			
		} 
		if(page_task.equals("s_billdetails"))
		{
			str+=  " <a onclick=\"billdetails()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Bill Details</span></a>";
			
		} 
		if(page_task.equals("s_roomstatusallotment"))
		{
			str+=  " <a onclick=\"roomstatusallotment()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Room Status Allotment</span></a>";
			
		}
		if(page_task.equals("s_guestdetails"))
		{
			str+=  " <a onclick=\"roomstatusallotment()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Room Status Allotment</span></a>";
			
		}
		
		if(page_task.equals("s_roomstatuscount"))
		{
			str+=  " <a onclick=\"roomstatuscount()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Room Status Count</span></a>";
			
		}
		if(page_task.equals("s_misc_billing"))
		{
			str+=  " <a onclick=\"miscbilling()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Miscellaneous Billing</span></a>";
			str+=  " <a onclick=\"miscbillingList()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">List</span></a>";
		}
		if(page_task.equals("s_allotment_full_list"))
		{
			str+=  " <a onclick=\"allotmentfulllist()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Allotment Full List</span></a>";
			
		}
		if(page_task.equals("s_blockstatus"))
		{
			str+=  " <a onclick=\"blocklist()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">House Status List</span></a>";
			
		}
		if(page_task.equals("s_dailyroomstatus"))
		{
			str+=  " <a onclick=\"dailyroomstatus()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Daily Room Status</span></a>";
			
		}
		
		if(page_task.equals("s_monthlyreport"))
		{
			str+=  " <a onclick=\"monthlyreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Monthly Report</span></a>";
			
		} 
		if(page_task.equals("s_yearlyreport"))
		{
			str+=  " <a onclick=\"yearlyreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Yearly Report</span></a>";
			
		} 
		if(page_task.equals("s_accountsreport"))
		{
			str+=  " <a onclick=\"accountsreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Accounts Report</span></a>";
			
		}
		if(page_task.equals("s_vehiclerequiedreport"))
		{
			str+=  " <a onclick=\"vehiclereportreport('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Vehicle Report</span></a>";
			
		} 
//		{
//			str+=  " <a onclick=\"vehiclereport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Vehicle Report</span></a>";
//			
//		} 
//		if(page_task.equals("s_role"))
//		{
//			str+=  " <a onclick=\"addrole('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Role</span></a>";
//			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
//			str+=  " <a onclick=\"editrole()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Role</span></a>";
//			str+=  " <a onclick=\"deleterole()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Role</span></a>";
//				         
//		
//		} 
		
		if(page_task.equals("s_rent"))
		{
			str+=  " <a onclick=\"addrent()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Rent</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editrent('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Rent</span></a>";
			str+=  " <a onclick=\"deleterent('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Rent</span></a>";
				         
		
		} 
		if(page_task.equals("s_basicpay"))
		{
			str+=  " <a onclick=\"addbasicpay()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add DA</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editbasicpay('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit DA</span></a>";
			str+=  " <a onclick=\"deletebasicpay('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete DA</span></a>";
				         
		
		} 
		
		
		if(page_task.equals("s_role"))
		{
			str+=  " <a onclick=\"addrole()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Role</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editrole('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Role</span></a>";
			str+=  " <a onclick=\"deleterole('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Role</span></a>";
				         
		
		} 

if(page_task.equals("s_guesthouse"))
		{
			str+=  " <a onclick=\"addguesthouse()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Guest House</span></a>";
			str+=  " <a onclick=\"editguesthouse('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Guest House</span></a>";
			str+=  " <a onclick=\"deleteguesthouse('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Guest House</span></a>";
				         
		
		}  
		 

if(page_task.equals("s_menudetails"))
		{
			str+=  " <a onclick=\"addmenudetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Menu Details</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editmenudetails('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Menu Details</span></a>";
			str+=  " <a onclick=\"deletemenudetails('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Menu Details</span></a>";
				         
		
		}  




		if(page_task.equals("s_repairblock"))
		{
			str+=  " <a onclick=\"addrepairblock()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Repair House</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editrepairblock()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Repair House</span></a>";
			str+=  " <a onclick=\"deleterepairblock()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Repair House</span></a>";
				         
		
		} 
		if(page_task.equals("s_mastercharges"))
		{
			str+=  " <a onclick=\"addmastercharges()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Master Charges</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editmastercharges()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Master Charges</span></a>";
			str+=  " <a onclick=\"deletemastercharges()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Master Charges</span></a>";
				         
		
		} 
		if(page_task.equals("s_roomdetails"))
		{
			str+=  " <a onclick=\"addroomdetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Room Details</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editroomdetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Room Details</span></a>";
			str+=  " <a onclick=\"deleteroomdetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Room Details</span></a>";
		} 
		if(page_task.equals("s_notice"))
		{
			str+=  " <a onclick=\"addroomdetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Notice</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editroomdetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Notice</span></a>";
			str+=  " <a onclick=\"deleteroomdetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Notice</span></a>";
		} 
		
		if(page_task.equals("s_foodmaster"))
		{
			str+=  " <a onclick=\"addfooddetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Food Details</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editfooddetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Food Details</span></a>";
			str+=  " <a onclick=\"deletefooddetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Food Details</span></a>";
			         
		
		} 
		
		if(page_task.equals("s_quotablock"))
		{
			str+=  " <a onclick=\"addquotablock()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Quota House</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editquotablock()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Quota House</span></a>";
			str+=  " <a onclick=\"deletequotablock()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Quota House</span></a>";
				         
		
		} 
		if(page_task.equals("s_roomblock"))
		{
			str+=  " <a onclick=\"addroomblock()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Room House</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editroomblock()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Room House</span></a>";
			str+=  " <a onclick=\"deleteroomblock()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Room House</span></a>";
				         
		
		} 
		if(page_task.equals("s_quota"))
		{
			str+=  " <a onclick=\"addquota()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Quota</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editquota()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Quota</span></a>";
			str+=  " <a onclick=\"deletequota()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Quota</span></a>";
				         
		
		} 
		if(page_task.equals("s_visitorcategory"))
		{
			str+=  " <a onclick=\"addvisitorcategory()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Visitercategory</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editvisitorcategory()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Visitercategory</span></a>";
			str+=  " <a onclick=\"deletevisitorcategory()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Visitercategory</span></a>";
				         
		
		} 
		if(page_task.equals("s_userdetails"))
		{
			str+=  " <a onclick=\"adduserdetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add User Details</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"edituserdetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit User Details</span></a>";
			str+=  " <a onclick=\"deleteuserdetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete User Details</span></a>";
				         
		
		}  
		
		if(page_task.equals("s_ghdetails"))
		{
			str+=  " <a onclick=\"addghdetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add ghdetails</span></a>";
			str+=  " <a onclick=\"editghdetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit ghdetails</span></a>";
			str+=  " <a onclick=\"deleteghdetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete ghdetails</span></a>";
				         
		
		}  
		
		
		 
//		if(page_task.equals("s_dataentry"))
//		{
//			str+=  " <a onclick=\"show_form_dataentry()\"><i class=\"fa fa-plus-square\"></i> <span class=\"text_links\">Dataentry</span></a>"; 
//		     
//		}
		if(page_task.equals("requestupload"))
		{ String clientid = p_ngm.ins_ngmprop.getpropvalue("clientid");
			str+=  " <a onclick=\"uploadrequest('"+clientid+"')\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Upload data</span></a>";
			str+=  " <a onclick=\"ghimportlogin('"+clientid+"')\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Download data</span></a>";
/*			str+= " <a onclick=\"cancelrequest()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Cancel</span></a>";
*/		}
		if(page_task.equals("ghupload"))
		{ String clientid = p_ngm.ins_ngmprop.getpropvalue("clientid");
			str+=  " <a onclick=\"gh_upload('"+clientid+"')\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Upload data</span></a>";
			str+=  " <a onclick=\"gh_download('"+clientid+"')\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Download data</span></a>";

/*			str+= " <a onclick=\"cancelrequest()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Cancel</span></a>";
*/		}
		
		
		if(page_task.equals("uploadtable"))
		{
			str+=  " <a onclick=\"listupload()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">List of Uploads</span></a>";
			str+= " <a onclick=\"cancel()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Cancel All</span></a>";
		}
 
		if(page_task.equals("accept"))
		{
			str+=  " <a onclick=\"getlist()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Go</span></a>";
			 
		}
		
		if (page_task.equals("s_report"))
		{
			//str+=  " <a target=\"_blank\" onclick=\"view_uploaded"('http://localhost/ngmsproject','/doc_handler','"+p_ngm.ins_sess.process_id+"','generatetestpdf','')\"   class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Print PDF</span></a>";
			
		}
		if(page_task.equals("s_dataentry"))
		{ 
			//String clientid = p_ngm.ins_ngmprop.getpropvalue("clientid");
			//str+=  " <a onclick=\"show_form_pubdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Public Dataentry</span></a>"; 
			//str+=  " <a onclick=\"show_form_editdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Edit Dataentry</span></a>"; 
			str+=  " <a onclick=\"show_form_new()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">New</span></a>"; 
			str+=  " <a onclick=\"get_list_gh2('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Update</span></a>"; 
			str+=  " <a onclick=\"show_form_cancel('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel</span></a>"; 
		//	str+=  " <a onclick=\"search_req()\" class='btn btn-success'><i class=\"fa fa-search\"></i> <span class=\"text_links\">Search</span></a>"; 
			str+=  " <a onclick=\"view_conformation('1')\" class='btn btn-success'><i class=\"fa fa-check-square\"></i> <span class=\"text_links\">Confirmed List</span></a>";
			str+=  " <a onclick=\"showEditGuestDetails('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Edit Guest Details</span></a>"; 
			str+=  " <a onclick=\"showcancelReservation('1')\" class='btn btn-success'><i class=\"fa fa-download\"></i> <span class=\"text_links\">Cancel Reservation</span></a>"; 
//			str+=  " <a onclick=\"show_form_publicrequest()\" class='btn btn-success'><i class=\"fa fa-male\"></i> <span class=\"text_links\">Public request</span></a>"; 
		}
			
		if(page_task.equals("s_publicdataentry"))
		{
			//str+=  " <a onclick=\"show_form_pubdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Public Dataentry</span></a>"; 
			//str+=  " <a onclick=\"show_form_editdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Edit Dataentry</span></a>"; 
			str+=  " <a onclick=\"show_form_new()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">New Application</span></a>"; 
			str+=  " <a onclick=\"get_list_gh2()\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Edit Application</span></a>"; 
//			str+=  " <a onclick=\"show_form_cancel()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel</span></a>"; 
//			str+=  " <a onclick=\"show_form_import()\" class='btn btn-success'><i class=\"fa fa-download\"></i> <span class=\"text_links\">Import</span></a>"; 
//			str+=  " <a onclick=\"show_form_publicrequest()\" class='btn btn-success'><i class=\"fa fa-male\"></i> <span class=\"text_links\">Public request</span></a>"; 
//		  
			str+=  " <a onclick=\"printapplication()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Print Application</span></a>";
			str+=  " <a onclick=\"printstatus()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Status</span></a>";
			
		}
		if(page_task.equals("s_document"))
		{

			str+=  " <a onclick=\"show_form_add_document()\" class='btn btn-danger'><i class=\"fa fa-plus-plus\"></i> <span class=\"text_links\">Add</span></a>";
			str+=  " <a onclick=\"show_form_edit_document()\" class='btn btn-danger'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit</span></a>";
			//str+=  " <a onclick=\"show_form_search_document()\"><i class=\"fa fa-plus-square\"></i> <span class=\"text_links\">Search</span></a>";
			str+=  " <a onclick=\"show_form_delete_document()\" class='btn btn-danger'><i class=\"fa fa-remove-sign\"></i> <span class=\"text_links\">Delete</span></a>";

		     
		}

		

		if(page_task.equals("s_processing"))
		{

			str+=  " <a onclick=\"show_modlist()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Mod List.</span></a>";
			str+=  " <a onclick=\"show_draftlist('1')\" class='btn btn-success'><i class=\"fa fa-file-text-o\"></i> <span class=\"text_links\">Draft List</span></a>";
			//str+=  " <a onclick=\"show_confirmedlist()\" class='btn btn-danger'><i class=\"fa fa-remove-sign\"></i> <span class=\"text_links\">Confirmed List</span></a>";
			str+=  " <a onclick=\"show_conformation('1')\" class='btn btn-success'><i class=\"fa fa-check-square\"></i> <span class=\"text_links\">Confirmed List</span></a>";
			str+=  " <a onclick=\"showEditGuestDetails('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Edit Guest Details</span></a>"; 

		     
		}
		
		if(page_task.equals("s_confirmation_order"))
		{

			str+=  " <a onclick=\"show_conformationGO('1')\" class='btn btn-success'><i class=\"fa fa-check-square\"></i> <span class=\"text_links\">Confirmed List</span></a>";

		     
		}
		
		if(page_task.equals("s_checkin"))
		{
			str+=  " <a onclick=\"checkin('1')\" class='btn btn-success'><i class=\"fa fa-sign-in\"></i> <span class=\"text_links\">Check in</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"cancel('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel Check in</span></a>";
			str+=  " <a onclick=\"checkout('1')\" class='btn btn-success'><i class=\"fa fa-shopping-cart\"></i> <span class=\"text_links\">Check out</span></a>";
			str+=  " <a onclick=\"checkoutcancel('1')\" class='btn btn-success'><i class=\"fa fa-shopping-cart\"></i> <span class=\"text_links\">Cancel Check out</span></a>";
			str+=  " <a onclick=\"checkinSlip('1')\" class='btn btn-success'><i class=\"fa fa-shopping-cart\"></i> <span class=\"text_links\">Check-In Slip</span></a>";
			//str+=  " <a onclick=\"additionalBed('1')\" class='btn btn-success'><i class=\"fa fa-shopping-cart\"></i> <span class=\"text_links\">Additional Bed</span></a>";
		}  
		
		if(page_task.equals("s_extension_checkin"))
		{
			str+=  " <a onclick=\"extension_checkin('1')\" class='btn btn-success'><i class=\"fa fa-sign-in\"></i> <span class=\"text_links\">Extension Check in</span></a>";
			//str+=  " <a onclick=\"extension_cancel()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel Extension Checkin</span></a>";
				         
		
		} 
		if(page_task.equals("s_shifting"))
		{
			str+=  " <a onclick=\"addshift()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Shift</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"changeshift()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Change Shift</span></a>";
			str+=  " <a onclick=\"closeshift()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Close Shift</span></a>";
			str+=  " <a onclick=\"shift_report_TC('1')\" class='btn btn-success'><i class=\"fa fa-pie-chart\"></i> <span class=\"text_links\">Report</span></a>";
				         
		
		}  
		if(page_task.equals("s_current_booking"))
		{
			 
			str+=  " <a onclick=\"current_new_req()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">New Request</span></a>";
			str+=  " <a onclick=\"current_edit_req('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Request</span></a>";

			str+=  " <a onclick=\"current_new_mod('1')\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"current_edit_mod('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Moderation</span></a>";
			str+=  " <a onclick=\"process_current()\" class='btn btn-success'><i class=\"fa fa-tasks\"></i> <span class=\"text_links\">Process All</span></a>";
			/*str+= " <a onclick=\"changeallot()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Change Allotment</span></a>";
			str+= " <a onclick=\"cancelallotment()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel Allotment</span></a>";
	         */
		
		}  
		
		if(page_task.equals("allotment"))
		{
			str+=  " <a onclick=\"allotmentlist('1')\" class='btn btn-success'><i class=\"fa fa-tasks\"></i> <span class=\"text_links\">Allotment</span></a>";
			str+= " <a onclick=\"changeallot('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Change</span></a>";
			str+= " <a onclick=\"cancelallotment('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel</span></a>";
			str+= " <a onclick=\"allotmentreport()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Report</span></a>";
//			str+=  " <a onclick=\"allotmentlistOnduty('1')\" class='btn btn-success'><i class=\"fa fa-tasks\"></i> <span class=\"text_links\">On Duty</span></a>";
			str+=  " <a onclick=\"allotmentlistAdditionalBed('1')\" class='btn btn-success'><i class=\"fa fa-tasks\"></i> <span class=\"text_links\">Additional Bed</span></a>";
		}
		
		if(page_task.equals("s_occupancy"))
		{
			str+=  " <a onclick=\"occupancy()\" class='btn btn-success'><i class=\"fa fa-building-o\"></i> <span class=\"text_links\"> Occupancy</span></a>";
			//str+=  " <a onclick=\"occupancyold()\" class='btn btn-success'><i class=\"fa fa-building-o\"></i> <span class=\"text_links\"> Old</span></a>";
		}
		if(page_task.equals("s_view"))
		{
			str+=  " <a onclick=\"view()\" class='btn btn-success'><i class=\"fa fa-eye\"></i> <span class=\"text_links\">VIEW</span></a>";
			 
		}
		if(page_task.equals("s_view_confirmed"))
		{
			str+=  " <a onclick=\"viewConfirmed()\" class='btn btn-success'><i class=\"fa fa-eye\"></i> <span class=\"text_links\">VIEW</span></a>";
			 
		}
		if(page_task.equals("s_dcb"))
		{
			str+=  " <a onclick=\"new_dcb()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">New DCB</span></a>";
			str+=  " <a onclick=\"list_dcb('1')\" class='btn btn-success'><i class=\"fa fa-eye\"></i> <span class=\"text_links\">List All DCB</span></a>";
			 
		}
		if(page_task.equals("s_billing"))
		{

			str+=  " <a onclick=\"add_bill_lists()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Bill</span></a>";
			str+=  " <a onclick=\"cancel_bill_list('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel Bill</span></a>";
			str+=  " <a onclick=\"bill_list('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Bill List</span></a>";
		}
		if(page_task.equals("s_receipt"))
		{

			str+=  " <a onclick=\"addreceipt()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Receipt</span></a>";
			str+=  " <a onclick=\"listreceipt('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">List Receipt</span></a>";
			
		}
		
		if(page_task.equals("s_checkoutreport"))
		{
			str+=  " <a onclick=\"checkoutreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Check out Report</span></a>";
			
		} 
		
		if(page_task.equals("s_conferencehallrequest"))
		{
			str+=  " <a onclick=\"addconferencehallrequest()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Request</span></a>";
			str+=  " <a onclick=\"cancelcloseconferencehallrequest('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Cancel/Close</span></a>";
			str+=  " <a onclick=\"paymentconferencehallrequest('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Payment</span></a>";
			str+=  " <a onclick=\"receiptconferencehallrequest('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Receipt</span></a>";
			//str+=  " <a onclick=\"receiptconferencehalllist('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Receipt List</span></a>";
		} 
		
		if(page_task.equals("s_dues"))
		{
			str+=  " <a onclick=\"duesList('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">List</span></a>";
			str+=  " <a onclick=\"duesReport('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Partial Dues Report</span></a>";
			str+=  " <a onclick=\"duesBillReport('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Dues Report</span></a>";

			//str+=  " <a onclick=\"cancelfoodbill('0')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Cancel Item</span></a>";
			
		} 
		
		str+= "</div> </div>";
		//////////////////////////////////////
		
		
		//////////////////////////////////
		p_ngm.ins_writer.write(str);
	}

	
	public void display_page_icons1(HttpServletRequest request,ngm p_ngm,String page_task,String title,String icon) throws Exception{
		
		String str="";
		p_ngm.ins_sess.gh_id = (String)request.getAttribute("gh_id");
		System.out.println("GHHHH IDD  >> "+p_ngm.ins_sess.gh_id);
		System.out.println("t_gh_id  >> "+p_ngm.ins_sess.gh_id + " TESTTTTTTTTTTT " + p_ngm.ins_sess.user_id);
		PreparedStatement gh=p_ngm.conn.prepareStatement("select a.gh_id,a.gh_name from gh_details a join uservsgh b on a.gh_id = b.gh_id where b.userid = ?");
		gh.setString(1, p_ngm.ins_sess.user_id);
		PreparedStatement block=p_ngm.conn.prepareStatement("select block_id,block_name from room_block where gh_id=?");
		block.setString(1, p_ngm.ins_sess.gh_id);
		if (icon=="")
		{
			System.out.println("value is null");
			str="<div class='content-header'>" +
					"<div class='pull-left'><h1> <i class='fa fa-book'></i> " + title +"</h1></div>"+
					"<div class='pull-right'>" ;
		}
		else
		{
			
			str="<div class='content-header row' id='header_div' style='margin-top:-10px;'>" +
					"<form name='form-general' method='post' id='form-general1'>"+
					"<div class='pull-left col-sm-'><h1> <i class='fa "+icon+"'></i> " + title +"</h1></div>"+
					"<div class='header-center col-sm-5'>" +
						"<div class='small-box-header bg-teal col-sm-5'> " +
						"<label class='col-sm-5 no-padding label-select'>Select City:</label>" +
						"<div class='col-sm-7 no-padding'>" +p_ngm.ins_utility.getDBcombo_special_common("sel_id","form-control med", "sel_id","getBlocks()", gh,p_ngm.ins_sess.gh_id,p_ngm)+ 
						"</div></div><div class='col-sm-1'></div>" +
						"<div class='small-box-header bg-teal col-sm-6' style=''> " +
						"<label class='col-sm-6 no-padding label-select'>Select Guest House:</label>" +
						"<div id ='block_div' class='col-sm-6 no-padding'>" +p_ngm.ins_utility.getDBcombo_special_common("sel_block","form-control med", "sel_block","", block,"",p_ngm)+ 
						"</div></div>"+
					"</div>"+
						"</form>"+
					"<div class='pull-right'>";
		}
		
				
//           "<ol class=\"list- pull-left\">";
//		
//		str+="<li><a href=\"#\" onClick=\"cancel_creation('"+p_ngm.ins_sess.process_id+"')\"> <i class=\"fa fa-user \"></i>"+title+"</a></li>";
//		str+=" </ol>"+
//              " <ul class=\"pull-right\"> ";
		if(page_task.equals("s_finalreport"))
		{
			str+=  " <a onclick=\"report_list('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Report List</span></a>";
		}
		if(page_task.equals("s_sms_allotment"))
		{
			str+=  " <a onclick=\"sms_list('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">SMS</span></a>";
			//str+=  " <a onclick=\"cancelfoodbill('0')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Cancel Item</span></a>";
			
		}
		
		if(page_task.equals("s_dailyguest"))
        {
            /*str+=  " <a onclick=\"addfooddetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Food Details</span></a>";
            
            str+=  " <a onclick=\"editfooddetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Food Details</span></a>";*/
            str+=  " <a onclick=\"dailyguestlist(1)\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">List</span></a>";
            //str+=  " <a onclick=\"dailyguestlist('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">List</span></a>";             
        
        }
		
		if(page_task.equals("s_foodbill"))
		{
			str+=  " <a onclick=\"foodbill('0')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Item</span></a>";
			//str+=  " <a onclick=\"cancelfoodbill('0')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Cancel Item</span></a>";
			
		} 
		if(page_task.equals("s_vehiclemaster"))
		{
			str+=  " <a onclick=\"addvehiclemaster()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Vehiclemaster</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editvehiclemaster()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Vehiclemaster</span></a>";
			str+=  " <a onclick=\"deletevehiclemaster()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Vehiclemaster</span></a>";
				         
		
		} 
		
		if(page_task.equals("s_vehicleallotment"))
		{
			str+=  " <a onclick=\"addvehicleallotment('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Vehicleallotment</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editvehicleallotment('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Vehicleallotment</span></a>";
			//str+=  " <a onclick=\"deletevehicleallotment('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Vehicleallotment</span></a>";
			str+=  " <a onclick=\"vehiclereport('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Vehicle Allotment Report</span></a>";
			         
		
		} 
		if(page_task.equals("s_moderator"))
		{
			String clientid = p_ngm.ins_ngmprop.getpropvalue("clientid");
			str+=  " <a onclick=\"get_list_gh('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Application List</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"show_form_editmoderation('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Moderation</span></a>";
		    str+=  " <a onclick=\"importlogin('"+clientid+"')\" class='btn btn-success hide'><i class=\"fa fa-download\"></i> <span class=\"text_links\">Import</span></a>"; 
//			str+=  " <a onclick=\"show_form_publicrequest('1')\" class='btn btn-success'><i class=\"fa fa-male\"></i> <span class=\"text_links\">Public request</span></a>"; 
			str+=  " <a onclick=\"showEditGuestDetails('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Edit Guest Details</span></a>"; 
			 str+=  " <a onclick=\"showCancelReservation('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Cancel Reservation</span></a>";
			
		}  
		if(page_task.equals("s_taskauthorization"))
		{
			str+=  " <a onclick=\"add_authorization()\" class='btn btn-primary'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Authorization</span></a>";
			str+=  " <a onclick=\"edit_authorization('1')\" class='btn btn-primary'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit</span></a>";
			str+=  " <a onclick=\"delete_authorization('1')\" class='btn btn-primary'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete</span></a>";			         
		
		
		}
		
		if(page_task.equals("s_statusallotment"))
		{
			str+=  " <a onclick=\"statusallotment()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Status Allotment</span></a>";
			
		} 
		if(page_task.equals("s_allot_occupancy"))
		{
			str+=  " <a onclick=\"allotOccupancyList()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Allot and Occupancy List</span></a>";
			
		} 
		if(page_task.equals("s_arrival"))
		{
			str+=  " <a onclick=\"arrivalList()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Arrival List</span></a>";
			
		} 
		if(page_task.equals("s_billdetails"))
		{
			str+=  " <a onclick=\"billdetails()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Bill Details</span></a>";
			
		} 
		if(page_task.equals("s_roomstatusallotment"))
		{
			str+=  " <a onclick=\"roomstatusallotment()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Room Status Allotment</span></a>";
			
		}
		if(page_task.equals("s_guestdetails"))
		{
			str+=  " <a onclick=\"roomreport('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Room Report</span></a>";
			str+=  " <a onclick=\"roomconveancyreport(1)\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">RoomConveancy Report</span></a>";
			str+=  " <a onclick=\"conveancyreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Conveancy Report</span></a>";
			str+=  " <a onclick=\"cancelledreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Cancelled Report</span></a>";
			
		}
		if(page_task.equals("s_roomstatuscount"))
		{
			str+=  " <a onclick=\"roomstatuscount()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Room Status Count</span></a>";
			
		}
		if(page_task.equals("s_misc_billing"))
		{
			str+=  " <a onclick=\"miscbilling()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Miscellaneous Billing</span></a>";
			str+=  " <a onclick=\"miscbillingList()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">List</span></a>";
		}
		if(page_task.equals("s_allotment_full_list"))
		{
			str+=  " <a onclick=\"allotmentfulllist()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Allotment Full List</span></a>";
			
		}
		if(page_task.equals("s_blockstatus"))
		{
			str+=  " <a onclick=\"blocklist()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">House Status List</span></a>";
			
		}
		if(page_task.equals("s_dailyroomstatus"))
		{
			str+=  " <a onclick=\"dailyroomstatus()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Daily Room Status</span></a>";
			
		}
		if(page_task.equals("s_monthlyreport"))
		{
			str+=  " <a onclick=\"monthlyreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Monthly Report</span></a>";
			
		} 
		if(page_task.equals("s_yearlyreport"))
		{
			str+=  " <a onclick=\"yearlyreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Yearly Report</span></a>";
			
		} 
		if(page_task.equals("s_accountsreport"))
		{
			str+=  " <a onclick=\"accountsreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Accounts Report</span></a>";
			
		}
		if(page_task.equals("s_vehiclerequiedreport"))
		{
			str+=  " <a onclick=\"vehiclereportreport('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Vehicle Report</span></a>";
			
		} 
//		{
//			str+=  " <a onclick=\"vehiclereport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Vehicle Report</span></a>";
//			
//		} 
//		if(page_task.equals("s_role"))
//		{
//			str+=  " <a onclick=\"addrole('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Role</span></a>";
//			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
//			str+=  " <a onclick=\"editrole()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Role</span></a>";
//			str+=  " <a onclick=\"deleterole()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Role</span></a>";
//				         
//		
//		} 
		
		if(page_task.equals("s_rent"))
		{
			str+=  " <a onclick=\"addrent()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Rent</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editrent('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Rent</span></a>";
			str+=  " <a onclick=\"deleterent('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Rent</span></a>";
				         
		
		} 
		if(page_task.equals("s_basicpay"))
		{
			str+=  " <a onclick=\"addbasicpay()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add DA</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editbasicpay('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit DA</span></a>";
			str+=  " <a onclick=\"deletebasicpay('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete DA</span></a>";
				         
		
		} 
		
		
		if(page_task.equals("s_role"))
		{
			str+=  " <a onclick=\"addrole()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Role</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editrole('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Role</span></a>";
			str+=  " <a onclick=\"deleterole('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Role</span></a>";
				         
		
		} 

if(page_task.equals("s_guesthouse"))
		{
			str+=  " <a onclick=\"addguesthouse()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Guest House</span></a>";
			str+=  " <a onclick=\"editguesthouse('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Guest House</span></a>";
			str+=  " <a onclick=\"deleteguesthouse('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Guest House</span></a>";
				         
		
		}  
		 

if(page_task.equals("s_menudetails"))
		{
			str+=  " <a onclick=\"addmenudetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Menu Details</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editmenudetails('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Menu Details</span></a>";
			str+=  " <a onclick=\"deletemenudetails('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Menu Details</span></a>";
				         
		
		}  




		if(page_task.equals("s_repairblock"))
		{
			str+=  " <a onclick=\"addrepairblock()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Repair House</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editrepairblock()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Repair House</span></a>";
			str+=  " <a onclick=\"deleterepairblock()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Repair House</span></a>";
				         
		
		} 
		if(page_task.equals("s_mastercharges"))
		{
			str+=  " <a onclick=\"addmastercharges()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Master Charges</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editmastercharges()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Master Charges</span></a>";
			str+=  " <a onclick=\"deletemastercharges()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Master Charges</span></a>";
				         
		
		} 
		if(page_task.equals("s_roomdetails"))
		{
			str+=  " <a onclick=\"addroomdetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Room Details</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editroomdetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Room Details</span></a>";
			str+=  " <a onclick=\"deleteroomdetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Room Details</span></a>";
				         
		
		} 
		
		if(page_task.equals("s_notice"))
		{
			str+=  " <a onclick=\"addroomdetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Notice</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editroomdetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Notice</span></a>";
			str+=  " <a onclick=\"deleteroomdetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Notice</span></a>";
		} 
		
		if(page_task.equals("s_foodmaster"))
		{
			str+=  " <a onclick=\"addfooddetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Food Details</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editfooddetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Food Details</span></a>";
			str+=  " <a onclick=\"deletefooddetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Food Details</span></a>";
			         
		
		} 
		
		if(page_task.equals("s_quotablock"))
		{
			str+=  " <a onclick=\"addquotablock()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Quota House</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editquotablock()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Quota House</span></a>";
			str+=  " <a onclick=\"deletequotablock()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Quota House</span></a>";
				         
		
		} 
		if(page_task.equals("s_roomblock"))
		{
			str+=  " <a onclick=\"addroomblock()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Room House</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editroomblock()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Room House</span></a>";
			str+=  " <a onclick=\"deleteroomblock()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Room House</span></a>";
				         
		
		} 
		if(page_task.equals("s_quota"))
		{
			str+=  " <a onclick=\"addquota()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Quota</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editquota()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Quota</span></a>";
			str+=  " <a onclick=\"deletequota()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Quota</span></a>";
				         
		
		} 
		if(page_task.equals("s_visitorcategory"))
		{
			str+=  " <a onclick=\"addvisitorcategory()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Visitercategory</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"editvisitorcategory()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Visitercategory</span></a>";
			str+=  " <a onclick=\"deletevisitorcategory()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete Visitercategory</span></a>";
				         
		
		} 
		if(page_task.equals("s_userdetails"))
		{
			str+=  " <a onclick=\"adduserdetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add User Details</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"edituserdetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit User Details</span></a>";
			str+=  " <a onclick=\"deleteuserdetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete User Details</span></a>";
				         
		
		}  
		
		if(page_task.equals("s_ghdetails"))
		{
			str+=  " <a onclick=\"addghdetails()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add ghdetails</span></a>";
			str+=  " <a onclick=\"editghdetails()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit ghdetails</span></a>";
			str+=  " <a onclick=\"deleteghdetails()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Delete ghdetails</span></a>";
				         
		
		}  
		
		
		 
//		if(page_task.equals("s_dataentry"))
//		{
//			str+=  " <a onclick=\"show_form_dataentry()\"><i class=\"fa fa-plus-square\"></i> <span class=\"text_links\">Dataentry</span></a>"; 
//		     
//		}
		if(page_task.equals("requestupload"))
		{ String clientid = p_ngm.ins_ngmprop.getpropvalue("clientid");
			str+=  " <a onclick=\"uploadrequest('"+clientid+"')\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Upload data</span></a>";
			str+=  " <a onclick=\"ghimportlogin('"+clientid+"')\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Download data</span></a>";
/*			str+= " <a onclick=\"cancelrequest()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Cancel</span></a>";
*/		}
		if(page_task.equals("ghupload"))
		{ String clientid = p_ngm.ins_ngmprop.getpropvalue("clientid");
			str+=  " <a onclick=\"gh_upload('"+clientid+"')\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Upload data</span></a>";
			str+=  " <a onclick=\"gh_download('"+clientid+"')\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Download data</span></a>";

/*			str+= " <a onclick=\"cancelrequest()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Cancel</span></a>";
*/		}
		
		
		if(page_task.equals("uploadtable"))
		{
			str+=  " <a onclick=\"listupload()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">List of Uploads</span></a>";
			str+= " <a onclick=\"cancel()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Cancel All</span></a>";
		}
 
		if(page_task.equals("accept"))
		{
			str+=  " <a onclick=\"getlist()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Go</span></a>";
			 
		}
		
		if (page_task.equals("s_report"))
		{
			//str+=  " <a target=\"_blank\" onclick=\"view_uploaded"('http://localhost/ngmsproject','/doc_handler','"+p_ngm.ins_sess.process_id+"','generatetestpdf','')\"   class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Print PDF</span></a>";
			
		}
		if(page_task.equals("s_dataentry"))
		{ 
			//String clientid = p_ngm.ins_ngmprop.getpropvalue("clientid");
			//str+=  " <a onclick=\"show_form_pubdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Public Dataentry</span></a>"; 
			//str+=  " <a onclick=\"show_form_editdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Edit Dataentry</span></a>"; 
			str+=  " <a onclick=\"show_form_new()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">New</span></a>"; 
			str+=  " <a onclick=\"get_list_gh2('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Update</span></a>"; 
			str+=  " <a onclick=\"show_form_cancel('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel</span></a>"; 
		//	str+=  " <a onclick=\"search_req()\" class='btn btn-success'><i class=\"fa fa-search\"></i> <span class=\"text_links\">Search</span></a>"; 
			str+=  " <a onclick=\"view_conformation('1')\" class='btn btn-success'><i class=\"fa fa-check-square\"></i> <span class=\"text_links\">Confirmed List</span></a>";
			str+=  " <a onclick=\"showEditGuestDetails('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Edit Guest Details</span></a>"; 
			str+=  " <a onclick=\"showcancelReservation('1')\" class='btn btn-success'><i class=\"fa fa-download\"></i> <span class=\"text_links\">Cancel Reservation</span></a>"; 
//			str+=  " <a onclick=\"show_form_publicrequest()\" class='btn btn-success'><i class=\"fa fa-male\"></i> <span class=\"text_links\">Public request</span></a>"; 
		}
			
		if(page_task.equals("s_publicdataentry"))
		{
			//str+=  " <a onclick=\"show_form_pubdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Public Dataentry</span></a>"; 
			//str+=  " <a onclick=\"show_form_editdataentry()\" class='btn btn-success'><i class=\"fa fa-exchange\"></i> <span class=\"text_links\">Edit Dataentry</span></a>"; 
			str+=  " <a onclick=\"show_form_new()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">New Application</span></a>"; 
			str+=  " <a onclick=\"get_list_gh2()\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Edit</span></a>"; 
//			str+=  " <a onclick=\"show_form_cancel()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel</span></a>"; 
//			str+=  " <a onclick=\"show_form_import()\" class='btn btn-success'><i class=\"fa fa-download\"></i> <span class=\"text_links\">Import</span></a>"; 
//			str+=  " <a onclick=\"show_form_publicrequest()\" class='btn btn-success'><i class=\"fa fa-male\"></i> <span class=\"text_links\">Public request</span></a>"; 
//		  
			str+=  " <a onclick=\"printapplication()\" class='btn btn-success'><i class=\"fa fa-print\"></i> <span class=\"text_links\">Print</span></a>";
			str+=  " <a onclick=\"printstatus()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Status</span></a>";
		}
		
		if(page_task.equals("s_document"))
		{

			str+=  " <a onclick=\"show_form_add_document()\" class='btn btn-danger'><i class=\"fa fa-plus-plus\"></i> <span class=\"text_links\">Add</span></a>";
			str+=  " <a onclick=\"show_form_edit_document()\" class='btn btn-danger'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit</span></a>";
			//str+=  " <a onclick=\"show_form_search_document()\"><i class=\"fa fa-plus-square\"></i> <span class=\"text_links\">Search</span></a>";
			str+=  " <a onclick=\"show_form_delete_document()\" class='btn btn-danger'><i class=\"fa fa-remove-sign\"></i> <span class=\"text_links\">Delete</span></a>";

		     
		}

		

		if(page_task.equals("s_processing"))
		{

			str+=  " <a onclick=\"show_modlist()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Mod List.</span></a>";
			str+=  " <a onclick=\"show_draftlist('1')\" class='btn btn-success'><i class=\"fa fa-file-text-o\"></i> <span class=\"text_links\">Draft List</span></a>";
			//str+=  " <a onclick=\"show_confirmedlist()\" class='btn btn-danger'><i class=\"fa fa-remove-sign\"></i> <span class=\"text_links\">Confirmed List</span></a>";
			str+=  " <a onclick=\"show_conformation('1')\" class='btn btn-success'><i class=\"fa fa-check-square\"></i> <span class=\"text_links\">Confirmed List</span></a>";
			str+=  " <a onclick=\"showEditGuestDetails('1')\" class='btn btn-success'><i class=\"fa fa fa-pencil-square-o\"></i> <span class=\"text_links\">Edit Guest Details</span></a>"; 

		     
		}
		
		if(page_task.equals("s_confirmation_order"))
		{

			str+=  " <a onclick=\"show_conformationGO('1')\" class='btn btn-success'><i class=\"fa fa-check-square\"></i> <span class=\"text_links\">Confirmed List</span></a>";

		     
		}
		
		if(page_task.equals("s_checkin"))
		{
			str+=  " <a onclick=\"checkin('1')\" class='btn btn-success'><i class=\"fa fa-sign-in\"></i> <span class=\"text_links\">Check in</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"cancel('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel Check in</span></a>";
			str+=  " <a onclick=\"checkout('1')\" class='btn btn-success'><i class=\"fa fa-shopping-cart\"></i> <span class=\"text_links\">Check out</span></a>";
			str+=  " <a onclick=\"checkoutcancel('1')\" class='btn btn-success'><i class=\"fa fa-shopping-cart\"></i> <span class=\"text_links\">Cancel Check out</span></a>";
			str+=  " <a onclick=\"checkinSlip('1')\" class='btn btn-success'><i class=\"fa fa-shopping-cart\"></i> <span class=\"text_links\">Check-In Slip</span></a>";
			//str+=  " <a onclick=\"additionalBed('1')\" class='btn btn-success'><i class=\"fa fa-shopping-cart\"></i> <span class=\"text_links\">Additional Bed</span></a>";
		}  
		
		if(page_task.equals("s_extension_checkin"))
		{
			str+=  " <a onclick=\"extension_checkin('1')\" class='btn btn-success'><i class=\"fa fa-sign-in\"></i> <span class=\"text_links\">Extension Check in</span></a>";
			//str+=  " <a onclick=\"extension_cancel()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel Extension Checkin</span></a>";
				         
		
		} 
		if(page_task.equals("s_shifting"))
		{
			str+=  " <a onclick=\"addshift()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Shift</span></a>";
			//str+=  " <a onclick=\"show_form_moderation()\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"changeshift()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Change Shift</span></a>";
			str+=  " <a onclick=\"closeshift()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Close Shift</span></a>";
			str+=  " <a onclick=\"shift_report_TC('1')\" class='btn btn-success'><i class=\"fa fa-pie-chart\"></i> <span class=\"text_links\">Report</span></a>";
				         
		
		}  
		if(page_task.equals("s_current_booking"))
		{
			 
			str+=  " <a onclick=\"current_new_req()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">New Request</span></a>";
			str+=  " <a onclick=\"current_edit_req('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Request</span></a>";

			str+=  " <a onclick=\"current_new_mod('1')\" class='btn btn-success'><i class=\"fa fa-check-circle\"></i> <span class=\"text_links\">Moderate</span></a>";
			str+=  " <a onclick=\"current_edit_mod('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Edit Moderation</span></a>";
			str+=  " <a onclick=\"process_current()\" class='btn btn-success'><i class=\"fa fa-tasks\"></i> <span class=\"text_links\">Process All</span></a>";
			/*str+= " <a onclick=\"changeallot()\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Change Allotment</span></a>";
			str+= " <a onclick=\"cancelallotment()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel Allotment</span></a>";
	         */
		
		}  
		
		if(page_task.equals("allotment"))
		{
			str+=  " <a onclick=\"allotmentlist('1')\" class='btn btn-success'><i class=\"fa fa-tasks\"></i> <span class=\"text_links\">Allotment</span></a>";
			str+= " <a onclick=\"changeallot('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Change</span></a>";
			str+= " <a onclick=\"cancelallotment('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel</span></a>";
			str+= " <a onclick=\"allotmentreport()\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Report</span></a>";
//			str+=  " <a onclick=\"allotmentlistOnduty('1')\" class='btn btn-success'><i class=\"fa fa-tasks\"></i> <span class=\"text_links\">On Duty</span></a>";
			str+=  " <a onclick=\"allotmentlistAdditionalBed('1')\" class='btn btn-success'><i class=\"fa fa-tasks\"></i> <span class=\"text_links\">Additional Bed</span></a>";
			str+=  " <a onclick=\"cancelreservationAllotment('1')\" class='btn btn-success'><i class=\"fa fa-tasks\"></i> <span class=\"text_links\">Cancel Reservation</span></a>";
			
		}
		
		if(page_task.equals("s_occupancy"))
		{
			str+=  " <a onclick=\"viewroomtoday()\" class='btn btn-success'><i class=\"fa fa-building-o\"></i> <span class=\"text_links\"> Occupancy</span></a>";
			//str+=  " <a onclick=\"occupancyold()\" class='btn btn-success'><i class=\"fa fa-building-o\"></i> <span class=\"text_links\"> Old</span></a>";
		}
		if(page_task.equals("s_view"))
		{
			str+=  " <a onclick=\"view()\" class='btn btn-success'><i class=\"fa fa-eye\"></i> <span class=\"text_links\">VIEW</span></a>";
			 
		}
		if(page_task.equals("s_view_confirmed"))
		{
			str+=  " <a onclick=\"viewConfirmed()\" class='btn btn-success'><i class=\"fa fa-eye\"></i> <span class=\"text_links\">VIEW</span></a>";
			 
		}
		if(page_task.equals("s_dcb"))
		{
			str+=  " <a onclick=\"new_dcb()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">New DCB</span></a>";
			str+=  " <a onclick=\"list_dcb('1')\" class='btn btn-success'><i class=\"fa fa-eye\"></i> <span class=\"text_links\">List All DCB</span></a>";
			 
		}
		if(page_task.equals("s_billing"))
		{

			str+=  " <a onclick=\"add_bill_lists()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Bill</span></a>";
			str+=  " <a onclick=\"cancel_bill_list('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Cancel Bill</span></a>";
			str+=  " <a onclick=\"bill_list('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Bill List</span></a>";
		}
		if(page_task.equals("s_receipt"))
		{

			str+=  " <a onclick=\"addreceipt()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Add Receipt</span></a>";
			str+=  " <a onclick=\"listreceipt('1')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">List Receipt</span></a>";
			
		}
		
		if(page_task.equals("s_checkoutreport"))
		{
			str+=  " <a onclick=\"checkoutreport()\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Check out Report</span></a>";
			
		} 
		
		if(page_task.equals("s_conferencehallrequest"))
		{
			str+=  " <a onclick=\"addconferencehallrequest()\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Request</span></a>";
			str+=  " <a onclick=\"cancelcloseconferencehallrequest('1')\" class='btn btn-success'><i class=\"fa fa-edit\"></i> <span class=\"text_links\">Cancel/Close</span></a>";
			str+=  " <a onclick=\"paymentconferencehallrequest('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Payment</span></a>";
			str+=  " <a onclick=\"receiptconferencehallrequest('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Receipt</span></a>";
			//str+=  " <a onclick=\"receiptconferencehalllist('1')\" class='btn btn-success'><i class=\"fa fa-times\"></i> <span class=\"text_links\">Receipt List</span></a>";
		} 
		
		if(page_task.equals("s_dues"))
		{
			str+=  " <a onclick=\"duesList('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">List</span></a>";
			str+=  " <a onclick=\"duesReport('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Partial Dues Report</span></a>";
			str+=  " <a onclick=\"duesBillReport('1')\" class='btn btn-success'><i class=\"fa fa-plus\"></i> <span class=\"text_links\">Dues Report</span></a>";

			//str+=  " <a onclick=\"cancelfoodbill('0')\" class='btn btn-success'><i class=\"fa fa-list\"></i> <span class=\"text_links\">Cancel Item</span></a>";
			
		} 
		
		
		str+= "</div> </div>";
		//////////////////////////////////////
		
		
		//////////////////////////////////
		p_ngm.ins_writer.write(str);
	}
	
	public void viewall(ngm p_ngm)throws Exception{     
		String a= 
				"<div aria-hidden=\"false\" aria-labelledby=\"viewall_page\" role=\"dialog\" tabindex=\"-1\" class=\"modal fade viewall_page in\" >"+
						"<div class=\"modal-dialog modal-lg\">"+
						  	"<div class=\"modal-content\">"+
				 
				  			"<div id=\"viewall_window\" class=\"\">"+
				  
				  			"</div>"+
				 
				  			"</div>"+
				  		  "</div>"+
				  "</div>"
				  ;
		   p_ngm.ins_writer.write(a);
		}

	public String pagination_docc(String p_id,int total_row,int n_row,String function_name)throws Exception{
		
		float total=total_row;
		float row=n_row;
		Integer pag=(int) Math.ceil(total/row);
		String pag_div="<ul class=\"pagination\"><li><span>Pages</span></li>";
	    for(int page_no=1;page_no<=pag;page_no++){
	    	pag_div+="<li><a href=\"#\" onclick=\""+function_name+"('"+p_id+"','"+page_no+"') \">"+page_no+"</a></li>";
			} 	
	    pag_div+="</ul>";
	    return pag_div;
	    		
	}


	
	public String pagination_form(String p_id,String gh_id,int total_row,int n_row,String function_name,int ppage_no)throws Exception{
		System.out.println("*******"+p_id+"----"+gh_id+"----"+total_row+"----"+n_row+"----"+function_name+"----"+ppage_no+"----");
		float total=total_row;
		float row=n_row;
		Integer pag=(int) Math.ceil(total/row);
		String pag_div="<div class=\"pull-left\"> <ul class='pagination'>";
		pag_div+="<div class='cur-page' style='margin-right:20px'> <div class='nopages'><span> Current Page : </span>";
		 		for(int page_no=ppage_no;page_no<=pag && page_no<=ppage_no;page_no++){
			    	if(ppage_no==page_no)
			    	{
			    	
			    		pag_div+="<span class='active'><a>"+page_no+"</a></span>";
			    	}
			    } 
		 		
		 	pag_div+="</div><div class='div-goto'> <label>Goto Page </label><div><input type='text' id='pageno' name='pageno' size='1'>";
		 	//	pag_div+="</label><div class='input-group'><input type='text' ><span class='input-group-btn'><button class='btn btn-default' type='button'>Go!</button></span></div>";
		 		pag_div+="<button class='btn btn-xs btn-danger btn-flat' type='button' onclick='go("+function_name+","+pag+")'>GO</button></div>";
		 		
		pag_div+="</div>";
		pag_div+="</div>";
		pag_div+="<span class='pull-left' style='margin-right:10px'>No of Pages " +pag+"</span>";
		
		 	int start=(ppage_no - 1);
			if(start>=1){
				pag_div+="<li><a  onclick=\""+function_name+"('"+start+"','"+gh_id+"') \">Previous</a></li>";
			}
			
			int temp_propvalue = 10;
			int i = 1;
			
			if(ppage_no>5){
				i = ppage_no-4;
				temp_propvalue = 5+ppage_no;
				if(temp_propvalue>pag){
					i = pag-9;
					temp_propvalue = pag;
					if(i<1){
						i = 1;
						temp_propvalue = pag;
					}
				}
			}
			
			for(int page_no=i;page_no<=pag && page_no<=temp_propvalue;page_no++){
		    	if(ppage_no!=page_no){
		    	   	pag_div+="<li><a onclick=\""+function_name+"('"+page_no+"','"+gh_id+"') \">"+page_no+"</a><li>";
		    	}
		    	else{
		    		pag_div+="<li class='active'><a>"+page_no+"</a></li>";
		    	}
			} 
			
			int prev = (ppage_no + 1);
			if(prev<=pag){
				pag_div+="<li><a  onclick=\""+function_name+"('"+prev+"','"+gh_id+"') \">Next</a></li>";
			}
		
			
	    pag_div+="</ul></div>";
	    return pag_div;
	    		
	}
	
	
	
	public String pagination_form12(String p_id,String gh_id,int total_row,int n_row,String function_name,int ppage_no)throws Exception{
		float total=total_row;
		float row=n_row;
		Integer pag=(int) Math.ceil(total/row);
		String pag_div="<div class=\"pull-left\"> <ul class='pagination'>";
		pag_div+="<div class='cur-page' style='margin-right:20px'> <div class='nopages'><span> Current Page : </span>";
		 		for(int page_no=ppage_no;page_no<=pag && page_no<=ppage_no;page_no++){
			    	if(ppage_no==page_no)
			    	{
			    	
			    		pag_div+="<span class='active'><a>"+page_no+"</a></span>";
			    	}
					
			    } 
		 		
		 	pag_div+="</div><div class='div-goto'> <label>Goto Page </label><div><input type='text' id='pageno' name='pageno' size='1'>";
		 	//	pag_div+="</label><div class='input-group'><input type='text' ><span class='input-group-btn'><button class='btn btn-default' type='button'>Go!</button></span></div>";
		 		pag_div+="<button class='btn btn-xs btn-danger btn-flat' type='button' onclick='go("+function_name+","+pag+")'>GO</button></div>";
		 		
		pag_div+="</div>";
		pag_div+="</div>";
		pag_div+="<span class='pull-left' style='margin-right:10px'>No of Pages " +pag+"</span>";
		
		 	int start=(ppage_no - 1);
			if(start>=1){
				pag_div+="<li><a  onclick=\""+function_name+"('"+start+"','"+gh_id+"') \">Previous</a></li>";
			}
			
			int temp_propvalue = 10;
			for(int page_no=1;page_no<=pag && page_no<=temp_propvalue;page_no++){
		    	if(ppage_no!=page_no){
		    	   	pag_div+="<li><a onclick=\""+function_name+"('"+page_no+"','"+gh_id+"') \">"+page_no+"</a><li>";
		    	}
		    	else{
		    		pag_div+="<li class='active'><a>"+page_no+"</a></li>";
		    	}
			} 
			
			int prev = (ppage_no + 1);
			if(prev<=pag){
				pag_div+="<li><a  onclick=\""+function_name+"('"+prev+"','"+gh_id+"') \">Next</a></li>";
			}
		
			
	    pag_div+="</ul></div>";
	    return pag_div;
	    		
	}
	
	
public String pagination_formss(String p_id,String gh_id,int total_row,int n_row,String function_name,int ppage_no)throws Exception{
	System.out.println("pagination form  doc   p_id     "+p_id);
	System.out.println("pagination form  doc    total_row   "+total_row);
	System.out.println("pagination form  doc    n_row      "+n_row);
	System.out.println("pagination form  doc     function_name"+function_name);
	System.out.println("pagination form  doc   ppage_no     "+ppage_no);
		float total=total_row;
		float row=n_row;
		Integer pag=(int) Math.ceil(total/row);
		System.out.println("pagination form  doc  pag    "+pag);
		String pag_div="<div class=\"pull-left\"> <ul class='pagination'>";
		 //pag_div+="<li><a>No of Pages " +pag+"</a></li>";
		 pag_div+="<span class='pull-left' style='margin-right:20px'>No of Pages " +pag+"</span>";
		 
		 int temp_propvalue = 5;
		for(int page_no=1;page_no<=pag && page_no<=temp_propvalue;page_no++){
	    	if(ppage_no!=page_no)
	    	{
	    	   	pag_div+="<li><a href=\"#\" onclick=\""+function_name+"('"+page_no+"','"+gh_id+"') \">"+page_no+"</a><li>";
	    	}
	    	else
	    	{
	    	
	    		pag_div+="<li class='active'><a>"+page_no+"</a></li>";
	    	}
			
	    } 
		
		int prev = (ppage_no + 1);
		int start=(ppage_no - 1);
		if(start>=1){
			//pag_div+="<li><a href=\"#\" onclick=\""+function_name+"('"+start+"','"+gh_id+"') \">"+ppage_no+"</a><li>";
		pag_div+="<li><a href=\"#\" onclick=\""+function_name+"('"+start+"','"+gh_id+"') \">Previous</a></li>";
		}
		if(prev<=pag){
			//pag_div+="<li><a href=\"#\" onclick=\""+function_name+"('"+prev+"','"+gh_id+"') \">"+ppage_no+"</a><li>";
		pag_div+="<li><a href=\"#\" onclick=\""+function_name+"('"+prev+"','"+gh_id+"') \">Next</a></li>";
		}
	    pag_div+="</ul></div>";
	    return pag_div;
	    		
	}

public String pagination_formmaster(String p_id,int total_row,int n_row,String function_name,int ppage_no)throws Exception{
	System.out.println("pagination form  doc");
		float total=total_row;
		float row=n_row;
		Integer pag=(int) Math.ceil(total/row);
		System.out.println("pagination form  doc  pag    "+pag);
		String pag_div="<div class=\"pull-left\"> <ul class='pagination'>";
		
		for(int page_no=1;page_no<=pag;page_no++){
	    	if(ppage_no!=page_no)
	    	{
	    		
	    	pag_div+="<li><a href=\"#\" onclick=\""+function_name+"('"+page_no+"') \">"+page_no+"</a><li>";
	    	}
	    	else
	    	{
	    	
	    		pag_div+="<li class='active'><a>"+page_no+"</a></li>";
	    	}
			
	    } 	
	    pag_div+="</ul></div>";
	    return pag_div;
	    		
	}
public String pagination_search(String p_id,int total_row,int n_row,String function_name)throws Exception{
	
	float total=total_row; 
	float row=n_row;
	Integer pag=(int) Math.ceil(total/row);
	String pag_div="";
    for(int page_no=1;page_no<=pag;page_no++){
    	pag_div+="<div class=\"pull-left\"><a href=\"#\" onclick=\""+function_name+"('"+p_id+"','"+page_no+"') \">|"+page_no+"|</a></div>";
		} 												
    return pag_div;
    		
}
public String pagination_searchform(String p_id,String criteria,int total_row,int n_row,String function_name,String form_id)throws Exception{
	
	float total=total_row;
	float row=n_row;
	String crteria = criteria.toString();
	Integer pag=(int) Math.ceil(total/row);
	String pag_div="";
	System.out.println(p_id);
	System.out.println(total_row);
	System.out.println(n_row);
	System.out.println(form_id);
	System.out.println(criteria);
    for(int page_no=1;page_no<=pag;page_no++){
    	System.out.println("gopika"+page_no);
    	pag_div+="<div class=\"pull-left\"><a href=\"#\" onclick=\""+function_name+"('"+p_id+"','"+criteria+"','"+page_no+"','"+form_id+"') \">|"+page_no+"|</a></div>";
		} 												
    return pag_div;
    		
}

	public void frm_mgr_div(ngm p_ngm)throws Exception{		
		String s1="<div id=\"frm_mgr_div\"> </div>";
		
					p_ngm.ins_writer.write(s1);
	}
	
	public void getfooterpage(ngm p_ngm) throws Exception
	{
		
		
		String s1="<div id='poparrow_img' class='poparrow_imgcss'><img src='' style='width:10px;height:10px;border:0;'> </div>";
		s1=s1+"<div class='nicpopbox' id='nicpopbox'> <div id='nicpopcontent'> </div></div>";
	
	
		/*s1=s1+"<script type='text/javascript' charset='utf-8'>";
		s1=s1+"var myEl = document.getElementById('pagediv');";
		s1=s1+"	myEl.addEventListener('click', function() {	action_on_click();}, false);</script>";*/
		
		p_ngm.ins_writer.write(s1);
	//	return s1;
		
	} //end of method



public void getpopup(ngm p_ngm) throws Exception
{
	
	String str="<div class='modal fade new' id='nic-popup' data-backdrop='static' data-keyboard='false'>" +
			"<table  align='center' class='nicpopuptable'><tr><td>" +
			"<div class='modal-dialog'>" +
			"<div class='modal-content'><div class='modal-body'>" +
			"<div class='' id='popcontentdiv'></div>" +
			"</div></div></div>" +
			"</td></tr></table>" +
			"</div><!-- /.modal -->" ;
			
	p_ngm.ins_writer.write(str);
	

}


/*public void getpopup(ngm p_ngm) throws Exception
{
	String str="<div class='modal fade new' id='nic-popup' data-backdrop='static' data-keyboard='false'>" +
			"<table  align='center' class='nicpopuptable'><tr><td>" +
			"<div class='modal-dialog'>" +
			"<div class='modal-content'><div class='modal-body'>" +
			"<div class='row'><div class='' id='popcontentdiv'></div>" +
			"</div></div></div></div>" +
			"</td></tr></table>" +
			"</div><!-- /.modal -->" ;
			
	p_ngm.ins_writer.write(str);

}
*/


} //end of class
