package template;
import java.util.StringTokenizer;


import db.ngm;
import javax.servlet.http.HttpServletRequest;

import db.ngm;

public class http {
	
	public void getHtmlheader(HttpServletRequest request,String p_css,String p_js,ngm p_global)throws Exception {
		p_global.ins_writer.write( "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		p_global.ins_writer.write( "<META HTTP-EQUIV=\"EXPIRES\" CONTENT=\"Mon, 22 Jul 2002 11:12:01 GMT\">");
		p_global.ins_writer.write( "<meta http-equiv= pragma content= no-cache / meta http-equiv= expires content= -1 />");	
		p_global.ins_writer.write( "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");	
		p_global.ins_writer.write("<html>");
		p_global.ins_writer.write("<head>");
		p_global.ins_writer.write("<!--[if lt IE 7]><link href='css/ie7.css' rel='stylesheet'><![endif]-->");
		p_global.ins_writer.write("<meta charset=\"utf-8\">");
		p_global.ins_writer.write("<!--[if lt IE 9]><script src='js/common/html5shiv.js'  type='text/javascript'></script><script src='js/common/respond.js'  type='text/javascript'></script><![endif]-->");
		p_global.ins_writer.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		p_global.ins_writer.write("<title>Bhawan Management System - Government of Odisha</title>");
		String G_css_str = "main.css,style.css,font-awesome/css/font-awesome.min.css,nicpopup.css";
		/*String G_css_str = "min.css";*/
		String G_jsarr = "ajax.js,sha2.js,validation.js,jquery.js,nicjsfunctions.js,bootstrap.min.js,ngm_functions.js,ngm_menu.js,ngm_common.js,ngm_val_common.js,nicpopup.js,html5shiv.js,respond.js,datepicker.js";
		getCSS(G_css_str,p_global);
		getCSS(p_css,p_global);
		getJsCom(G_jsarr,p_global);
		getJsAppCommon(p_js,p_global);
		p_global.ins_writer.write("</head>");
	}
	
	public void getHttpHeader(HttpServletRequest request,ngm p_global)throws Exception {
		p_global.ins_writer.write( "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		p_global.ins_writer.write( "<META HTTP-EQUIV=\"EXPIRES\" CONTENT=\"Mon, 22 Jul 2002 11:12:01 GMT\">");
		p_global.ins_writer.write( "<meta http-equiv= pragma content= no-cache / meta http-equiv= expires content= -1 />");
		p_global.ins_writer.write( "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");	
	}
	
	public void getJsCom(String p_filename,ngm p_global)throws Exception {
		StringTokenizer l_st=new StringTokenizer(p_filename, ",");
		String include="";
		while (l_st.hasMoreTokens()) {
			include=include.concat("<script src=\"js/common/"+l_st.nextToken()+"\" type=\"text/javascript\"></script>");		
		}
		p_global.ins_writer.write(include);
	}
	public void getJsMenu(String p_filename,ngm p_global)throws Exception {
		StringTokenizer l_st=new StringTokenizer(p_filename, ",");
		String include="";
		while (l_st.hasMoreTokens()) {
			include=include.concat("<script src=\"js/ge_menu/"+l_st.nextToken()+"\" type=\"text/javascript\"></script>");		
		}
		p_global.ins_writer.write(include);
	}

	public void getJsApp(String p_filename,ngm p_global)throws Exception {
		StringTokenizer l_st=new StringTokenizer(p_filename, ",");
		
		String include="<script src=\"js/ngm_application/"+p_global.domain_js_shortcode_path+"dm_configure.js\" type=\"text/javascript\"></script>";
		while (l_st.hasMoreTokens()) {
			include=include.concat("<script src=\"js/ngm_application/"+p_global.domain_js_shortcode_path+l_st.nextToken()+"\" type=\"text/javascript\"></script>");	
			//include=include.concat("<script src=\"js/ngm_application/"+l_st.nextToken()+"\" type=\"text/javascript\"></script>");	
		}
		p_global.ins_writer.write(include);
	}
	
	public void getJsAppCommon(String p_filename,ngm p_global)throws Exception {
		StringTokenizer l_st=new StringTokenizer(p_filename, ",");
		String include="";
		while (l_st.hasMoreTokens()) {	
			include=include.concat("<script src=\"js/application/"+l_st.nextToken()+"\" type=\"text/javascript\"></script>");	
		}
		p_global.ins_writer.write(include);
	}

	public void getCSS(String p_filename,ngm p_global)throws Exception {
		StringTokenizer l_st=new StringTokenizer(p_filename, ",");
		String include="";
		while (l_st.hasMoreTokens()) {
			include=include.concat("<LINK REL=StyleSheet HREF=\"css/"+l_st.nextToken()+"\" TYPE=\"text/css\">");	
		}
		p_global.ins_writer.write( include);
	}
	public void getMCSS(String p_filename,ngm p_global)throws Exception {
		StringTokenizer l_st=new StringTokenizer(p_filename, ",");
		String include="";
		while (l_st.hasMoreTokens()) {
			include=include.concat("<LINK REL=StyleSheet HREF=\"css/menu/"+l_st.nextToken()+"\" TYPE=\"text/css\" MEDIA=screen>");	
		}
		p_global.ins_writer.write( include);
	}
}
