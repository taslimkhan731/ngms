package template;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import db.ngm;

public class masterpanel{
	
	
	
public String pagination(String p_title,String p_searchtab,String sql,ArrayList<?> p_tbltitle, ArrayList<?> p_dbresults, Integer p_index,Integer p_limit,ngm GEG, String p_Servlet,String p_task,Integer pnumRows,int radio_index) throws Exception{	
		
		int l_limit = p_limit + p_index;
		Iterator<?> itr2=p_dbresults.iterator();
		Iterator<?> itr1=p_tbltitle.iterator();

	String tab="<div id=\"element-box\"><div class='m'><div id='pagination_div'> ";
	tab += p_searchtab;
	int colspantemp=1;
	tab+="<fieldset><legend>"+p_title+"</legend><table class=\"adminlist\"> <thead> <tr> <th class='center'>Select</th>";
		while(itr1.hasNext()){
			tab +="<th class='left'><a href='#'>"+itr1.next()+"</a></th>";		
			colspantemp++;
		}
		tab+="</tr></thead><tbody>";

		l_limit++;
		int j=0;
		int colspan=1;
			while(itr2.hasNext()){
				ArrayList<String> temporwData= (ArrayList<String>)itr2.next();
				
				String cls=(j%2==0)?"row0":"row1";
				tab +="<tr class="+cls+">";
				for(int k=0;k < temporwData.size();k++)
				{ 	
					if(k==0)
					{
						tab+="<td class='center'><input type='radio' id=\"genum\" name=\"genum\" value=\""+temporwData.get(radio_index)+"\">";
					}

					tab +="<td>"+temporwData.get(k)+"</td>";
					colspan++;
				}
				j++;
		  	} 
			if(colspan==1) colspan=colspantemp;
			tab+="</tr></tbody><tfoot><tr><td colspan=\""+colspan+"\" align=\"center\"><div class=\"container\" align=\"center\"><div class=\"pagination\" align=\"center\">";	
			
			int numRows=pnumRows;
	//------------------------------------------------------------------------------------------------------//	
	int remain=numRows/p_limit;
	
		remain++;
		int rem=numRows/p_limit;		
		int endIndex=rem*p_limit;		

		String option	="", sel="";
		ArrayList<Integer> paginationCount=new ArrayList<Integer>();
		paginationCount=GEG.ins_utility.getPaginationCount();
		for(int i=0;i<paginationCount.size();i++)
		{
			if(paginationCount.get(i)==p_limit) 	{ sel="selected=\"selected\"";} else sel="";
			option+="<option value=\""+paginationCount.get(i)+"\""+sel+">"+paginationCount.get(i)+"</option>";
		}
		String pag="<div class=\"limit\"><span>Display # </span>";
		pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" ";
		pag+=" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">";
		pag+=option+"</select> </div>";
		
		String refer="";
		String last="";
		String first="";
		first+="<div class=\"button2-right\"><div class=\"start\">"+
		"<a href=\"#\" onclick=\"pagination(1,"+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Start  </a></div></div>";
		if(endIndex!=numRows)
			
		last+="<div class=\"button2-left\"><div class=\"end\">"+
		"<a href='#' onclick=\"pagination("+(endIndex+1)+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> End  </a></div></div>";
		pag+=first;
		int t_index=1;
		
		refer+="<div class=\"button2-left\"><div class=\"page\">";
		for(int ref=1;ref<=remain;ref++){
			if(p_index==t_index)
				refer+="<span>"+ref+"</span>";
			else
				refer+="<a href='#' onclick=\"pagination("+t_index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">"+ref+"  </a>";
			 
			t_index=t_index+p_limit;
		}
		refer+="</div></div>";
		
		int index=p_index-p_limit;		
		if(p_index > 1) {
			pag+="<div class=\"button2-right\"><div class=\"prev\">"+
					"<a href='#' onclick=\"pagination("+index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Previous  </a></div></div>";
		}
		pag+=refer;

		int index2=p_index+p_limit;			
		if((index2) <= numRows){
			pag+="<div class=\"button2-left\"><div class=\"next\">"+
			"<a href=\"#\" onclick=\"pagination("+index2+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Next </a></div></div>";
		}
		if(p_index<=1){
			p_index=1;
		}
		pag+=last;
		if(index2 >= numRows) index2=numRows;
		if(index2==numRows){
			pag+="<div class=\"limit\">"+p_index +" to " + (index2) +" of " +numRows+"</div>";}
		else{
		pag+="<div class=\"limit\">"+p_index +" to " + (index2-1) +" of " +numRows+"</div>";}
		pag+="</div></div></td></tr></tfoot></table></fieldset></div></div></div>";
		return tab+=pag;	
	}

	
	
	
	public String getTable(String p_title,String p_table,ArrayList<?> p_tbltitle, ArrayList<?> p_dbfields, Integer p_index,Integer p_limit,ngm GEG, String p_Servlet ,String p_task ) throws Exception{	
			
			if(p_tbltitle.size()!=p_dbfields.size()){ throw new Exception("Column size mismatch");	}
			
			String str="";
			int i=1;
			int l_limit = p_limit + p_index;
			Iterator<?> itr2=p_dbfields.iterator();
			Iterator<?> itr1=p_tbltitle.iterator();
			while(itr2.hasNext()){
				str +=itr2.next();
				if(i!=p_dbfields.size())
					str+=",";
				i++;			
			}	
			String tab="<div id='element-box'> <div class='m'><fieldset><legend>"+p_title+"</legend><table class=\"adminlist\"> <thead> <tr> <th class='center'>&nbsp;</th>";
			while(itr1.hasNext()){
				tab +="<th class='left'><a href='#'>"+itr1.next()+"</a></th>";			
			}
			tab+="</tr></thead><tbody>";
			PreparedStatement ps1=GEG.tr_conn.prepareStatement("select count(*) as c from "+p_table );
			ResultSet rs1=ps1.executeQuery();
			rs1.next();
			int numRows=rs1.getInt("c");
			PreparedStatement ps2=GEG.tr_conn.prepareStatement("SELECT * FROM (SELECT ROWNUM as ID, "+str+" FROM "+p_table  +") WHERE id >= "+ p_index +" and id < "+l_limit );
			l_limit++;
			ResultSet rs=ps2.executeQuery();
			int j=0;
			int colspan=1;
				while(rs.next()){
					String cls=(j%2==0)?"row0":"row1";
					tab +="<tr class="+cls+">";
					for(int k=1;k <= p_dbfields.size();k++)
					{ 
						if(k==1)
							tab+="<td class='center'><input type='radio' id=\"chk[]\" name=\"chk[]\" value=\""+rs.getString(k+1)+"\"></td>";

						tab +="<td>"+rs.getString(k+1)+"</td>";
						colspan++;
					}
					j++;
			  	} 
				tab+="</tr></tbody><tfoot><tr><td colspan=\""+colspan+"\" align=\"center\"><div class=\"container\" align=\"center\"><div class=\"pagination\" align=\"center\">";	
		//------------------------------------------------------------------------------------------------------//	
			int remain=numRows/p_limit;
			remain++;
			int rem=numRows/p_limit;
			int endIndex=rem*p_limit;

			String option	="", sel="";
			ArrayList<Integer> paginationCount=new ArrayList<Integer>();
			paginationCount=GEG.ins_utility.getPaginationCount();
			for(int i1=0;i1<paginationCount.size();i1++)
			{
				if(paginationCount.get(i1)==p_limit) 	{ sel="selected=\"selected\"";} else sel="";
				option+="<option value=\""+paginationCount.get(i1)+"\""+sel+">"+paginationCount.get(i1)+"</option>";
			}
			String pag="<div class=\"limit\"><span>Display # </span>";
			pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" ";
			pag+=" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">";
			pag+=option+"</select> </div>";
			
			/*String pag="<div class=\"limit\"><span>Display # </span>";
			pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" "+
						" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','pagination','element-box')\"> "+
						"<option value=\"1\">1</option>"+
						"<option value=\"2\">2</option>"+
						"<option value=\"3\">3</option>"+
						"<option value=\"4\">4</option>"+
						"<option value=\"5\">5</option>"+
						"<option value=\"6\">6</option>"+
						"</select>";
			pag+="</div>";*/
			
			String refer="";
			String last="";
			String first="";
			first+="<div class=\"button2-right\"><div class=\"start\">"+
			"<a href=\"#\" onclick=\"pagination(1,"+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','element-box')\"> Start  </a></div></div>";
			if(endIndex!=numRows)
			last+="<div class=\"button2-left\"><div class=\"end\">"+
			"<a href='#' onclick=\"pagination("+(endIndex+1)+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','element-box')\"> End  </a></div></div>";
			pag+=first;
			int t_index=1;
			
			refer+="<div class=\"button2-left\"><div class=\"page\">";
			for(int ref=1;ref<=remain;ref++){
				if(p_index==t_index)
					refer+="<span>"+ref+"</span>";
				else
					refer+="<a href='#' onclick=\"pagination("+t_index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','element-box')\">"+ref+"  </a>";
				 
				t_index=t_index+p_limit;
			}
			refer+="</div></div>";
			
			int index=p_index-p_limit;
			if(p_index > 1) {
				pag+="<div class=\"button2-right\"><div class=\"prev\">"+
						"<a href='#' onclick=\"pagination("+index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','element-box')\"> Previous  </a></div></div>";
			}
			pag+=refer;

			int index2=p_index+p_limit;	
			
			if((index2) <= numRows){
				pag+="<div class=\"button2-left\"><div class=\"next\">"+
				"<a href=\"#\" onclick=\"pagination("+index2+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','element-box')\"> Next </a></div></div>";
			}
			if(p_index<=1){
				p_index=1;
			}
			pag+=last;
			if(index2 >= numRows) index2=numRows;
			if(index2==numRows){
				pag+="<div class=\"limit\">"+p_index +" to " + (index2) +" of " +numRows+"</div>";}
			else{
			pag+="<div class=\"limit\">"+p_index +" to " + (index2-1) +" of " +numRows+"</div>";}
			pag+="</div></div></td></tr></tfoot></table></fieldset></div></div>";
			return tab+=pag;		
		}
	
	public String getTablewithSql(String p_title,String p_searchtab,String sql,ArrayList<?> p_tbltitle, ArrayList<?> p_dbfields, Integer p_index,Integer p_limit,ngm GEG, String p_Servlet,String p_task ) throws Exception{	
		
		if(p_tbltitle.size()!=p_dbfields.size()){ throw new Exception("Column size mismatch");	}
		
		String str="";
		int i=1;
		int l_limit = p_limit + p_index;
		Iterator<?> itr2=p_dbfields.iterator();
		Iterator<?> itr1=p_tbltitle.iterator();
		while(itr2.hasNext()){
			str +=itr2.next();
			if(i!=p_dbfields.size())
				str+=",";
			i++;			
		}	
		
	String tab="<div id='pagination_div'> <div class='m'>";
	tab += p_searchtab;
	tab+="<fieldset><legend>"+p_title+"</legend><table class=\"adminlist\"> <thead> <tr> <th class='center'><input type=\"checkbox\" id=\"chkall\" name=\"chkall\"></th>";
		while(itr1.hasNext()){
			tab +="<th class='left'><a href='#'>"+itr1.next()+"</a></th>";			
		}
		tab+="</tr></thead><tbody>";
	
		Statement st1=GEG.tr_conn.createStatement();
		st1.execute(sql);
		ResultSet rs=st1.getResultSet();
		
		l_limit++;
		int j=0;
		int colspan=1;
			while(rs.next()){
				String cls=(j%2==0)?"row0":"row1";
				tab +="<tr class="+cls+">";
				
				for(int k=1;k <= p_dbfields.size();k++)
				{ 				
					if(k==1)
						tab+="<td class='center'><input type='checkbox' id=\"chk\" name=\"chk"+j+"\" value=\""+rs.getString(k)+"\"></td>";

					tab +="<td>"+rs.getString(k)+"</td>";
					colspan++;
				}
				j++;
		  	} 
			tab+="</tr></tbody><tfoot><tr><td colspan=\""+colspan+"\" align=\"center\"><div class=\"container\" align=\"center\"><div class=\"pagination\" align=\"center\">";	
			
			int numRows=j;
	//------------------------------------------------------------------------------------------------------//	
	int remain=numRows/p_limit;
	
		remain++;
		int rem=numRows/p_limit;		
		int endIndex=rem*p_limit;		
		
		String option	="", sel="";
		ArrayList<Integer> paginationCount=new ArrayList<Integer>();
		paginationCount=GEG.ins_utility.getPaginationCount();
		for(int i1=0;i1<paginationCount.size();i1++)
		{
			if(paginationCount.get(i1)==p_limit) 	{ sel="selected=\"selected\"";} else sel="";
			option+="<option value=\""+paginationCount.get(i1)+"\""+sel+">"+paginationCount.get(i1)+"</option>";
		}
		String pag="<div class=\"limit\"><span>Display # </span>";
		pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" ";
		pag+=" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">";
		pag+=option+"</select> </div>";
		
		String refer="";
		String last="";
		String first="";
		first+="<div class=\"button2-right\"><div class=\"start\">"+
		"<a href=\"#\" onclick=\"pagination(1,"+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Start  </a></div></div>";
		if(endIndex!=numRows)
			
		last+="<div class=\"button2-left\"><div class=\"end\">"+
		"<a href='#' onclick=\"pagination("+(endIndex+1)+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> End  </a></div></div>";
		pag+=first;
		int t_index=1;
		
		refer+="<div class=\"button2-left\"><div class=\"page\">";
		for(int ref=1;ref<=remain;ref++){
			if(p_index==t_index)
				refer+="<span>"+ref+"</span>";
			else
				refer+="<a href='#' onclick=\"pagination("+t_index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">"+ref+"  </a>";
			 
			t_index=t_index+p_limit;
		}
		refer+="</div></div>";
		
		int index=p_index-p_limit;		
		if(p_index > 1) {
			pag+="<div class=\"button2-right\"><div class=\"prev\">"+
					"<a href='#' onclick=\"pagination("+index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Previous  </a></div></div>";
		}
		pag+=refer;

		int index2=p_index+p_limit;			
		if((index2) <= numRows){
			pag+="<div class=\"button2-left\"><div class=\"next\">"+
			"<a href=\"#\" onclick=\"pagination("+index2+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Next </a></div></div>";
		}
		if(p_index<=1){
			p_index=1;
		}
		pag+=last;
		if(index2 >= numRows) index2=numRows;
		if(index2==numRows){
			pag+="<div class=\"limit\">"+p_index +" to " + (index2) +" of " +numRows+"</div>";}
		else{
		pag+="<div class=\"limit\">"+p_index +" to " + (index2-1) +" of " +numRows+"</div>";}
		pag+="</div></div></td></tr></tfoot></table></fieldset></div></div>";
		return tab+=pag;	
	}
	
	public String getTablewithSql(String p_title,String sql,ArrayList<?> p_tbltitle, ArrayList<?> p_dbfields, Integer p_index,Integer p_limit,ngm GEG, String p_Servlet,String p_task ,String ckfield_name, String p_ckfieldval ) throws Exception{	
	
		if(p_tbltitle.size()!=p_dbfields.size()){ throw new Exception("Column size mismatch");	}

		String str="";
		int i=1;
		int l_limit = p_limit + p_index;
		Iterator<?> itr2=p_dbfields.iterator();
		Iterator<?> itr1=p_tbltitle.iterator();
		while(itr2.hasNext()){
			str +=itr2.next();
			if(i!=p_dbfields.size())
				str+=",";
			i++;			
		}	
		
	String tab="<div id='pagination_div'> <div class='m'><fieldset><legend>"+p_title+"</legend><table class=\"adminlist\"> <thead> <tr> <th class='center'>&nbsp;</th>";
		
	
	
	while(itr1.hasNext()){
			tab +="<th class='left'><a href='#'>"+itr1.next()+"</a></th>";			
		}
		tab+="</tr></thead><tbody>";
	
		//PreparedStatement ps1=GEG.tr_conn.prepareStatement("select count(*) as c from "+p_table );
		Statement st1=GEG.tr_conn.createStatement();
		st1.execute(sql);
		ResultSet rs=st1.getResultSet();
		
		l_limit++;
		int j=0,tmpcount=0;
		int colspan=1;
			while(rs.next()&&tmpcount<p_limit){
				String cls=(j%2==0)?"row0":"row1";
				tab +="<tr class="+cls+">";
				for(int k=1;k <= p_dbfields.size();k++)
				{ 
				String 	id			=	rs.getString("event_id");
				String 	eventcode	=	rs.getString("event_code");
				
								
				if(k==1)
				{					
				if(!eventcode.equals("E01")){
					if(rs.getString("db_status").equals(p_ckfieldval))	
					{
						tab+="<td class='center'><input type='checkbox' id=\"chkb[]\" name=\"chkb[]\" value=\""+id+"\"><input type=\"hidden\" id=\"txt_eventcode[]\" name=\"txt_eventcode[]\" value=\""+eventcode+"\"</td>";
					}
					else
					{
						tab+="<td class='center'><input type='checkbox' id=\"chkb[]\" name=\"chkb[]\" value=\""+id+"\" disabled='disabled'></td>";	
					}
				}
				else
				{
					tab+="<td class='center'><input type='checkbox' id=\"chkb[]\" name=\"chkb[]\" value=\""+id+"\" disabled='disabled'></td>";	
				}
			  }
				
				tab +="<td>"+rs.getString(k+1)+"</td>";
				colspan++;
				}
				j++;tmpcount++;
		  	} 
			tab+="</tr></tbody><tfoot><tr><td colspan=\""+colspan+"\" align=\"center\"><div class=\"container\" align=\"center\"><div class=\"pagination\" align=\"center\">";	
			
			int numRows=j;
	//------------------------------------------------------------------------------------------------------//	
	int remain=numRows/p_limit;
	
		remain++;
		int rem=numRows/p_limit;		
		int endIndex=rem*p_limit;		

		String option	="", sel="";
		ArrayList<Integer> paginationCount=new ArrayList<Integer>();
		paginationCount=GEG.ins_utility.getPaginationCount();
		for(int i1=0;i1<paginationCount.size();i1++)
		{
			if(paginationCount.get(i1)==p_limit) 	{ sel="selected=\"selected\"";} else sel="";
			option+="<option value=\""+paginationCount.get(i1)+"\""+sel+">"+paginationCount.get(i1)+"</option>";
		}
		String pag="<div class=\"limit\"><span>Display # </span>";
		pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" ";
		pag+=" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">";
		pag+=option+"</select> </div>";
		
		String refer="";
		String last="";
		String first="";
		first+="<div class=\"button2-right\"><div class=\"start\">"+
		"<a href=\"#\" onclick=\"pagination(1,"+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Start  </a></div></div>";
		if(endIndex!=numRows)
			
		last+="<div class=\"button2-left\"><div class=\"end\">"+
		"<a href='#' onclick=\"pagination("+(endIndex+1)+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> End  </a></div></div>";
		pag+=first;
		int t_index=1;
		
		refer+="<div class=\"button2-left\"><div class=\"page\">";
		for(int ref=1;ref<=remain;ref++){
			if(p_index==t_index)
				refer+="<span>"+ref+"</span>";
			else
				refer+="<a href='#' onclick=\"pagination("+t_index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">"+ref+"  </a>";
			 
			t_index=t_index+p_limit;
		}
		refer+="</div></div>";
		
		int index=p_index-p_limit;		
		if(p_index > 1) {
			pag+="<div class=\"button2-right\"><div class=\"prev\">"+
					"<a href='#' onclick=\"pagination("+index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Previous  </a></div></div>";
		}
		pag+=refer;

		int index2=p_index+p_limit;			
		if((index2) <= numRows){
			pag+="<div class=\"button2-left\"><div class=\"next\">"+
			"<a href=\"#\" onclick=\"pagination("+index2+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Next </a></div></div>";
		}
		if(p_index<=1){
			p_index=1;
		}
		pag+=last;
		if(index2 >= numRows) index2=numRows;
		if(index2==numRows){
			pag+="<div class=\"limit\">"+p_index +" to " + (index2) +" of " +numRows+"</div>";}
		else{
		pag+="<div class=\"limit\">"+p_index +" to " + (index2-1) +" of " +numRows+"</div>";}
		pag+="</div></div></td></tr></tfoot></table></fieldset></div></div>";
		return tab+=pag;	
	}
	
	public String gethtmlTable(String p_title,String p_searchtab,String sql,ArrayList<?> p_tbltitle, ArrayList<?> p_dbresults, Integer p_index,Integer p_limit,ngm GEG, String p_Servlet,String p_task,Integer pnumRows ) throws Exception{	
		
		int l_limit = p_limit + p_index;
		Iterator<?> itr2=p_dbresults.iterator();
		Iterator<?> itr1=p_tbltitle.iterator();

	String tab="<div id=\"element-box\"><div class='m'><div id='pagination_div'> ";
	tab += p_searchtab;
	int colspantemp=1;
	tab+="<fieldset><legend>"+p_title+"</legend><table class=\"adminlist\"> <thead> <tr> <th class='center'>Select</th>";
		while(itr1.hasNext()){
			tab +="<th class='left'><a href='#'>"+itr1.next()+"</a></th>";		
			colspantemp++;
		}
		tab+="</tr></thead><tbody>";
	
		
		
		l_limit++;
		int j=0,i=0;
		int colspan=1;
		int tslno=p_index;
			while(itr2.hasNext()&&i<p_limit){
				ArrayList<String> temporwData= (ArrayList<String>)itr2.next();
				
				String cls=(j%2==0)?"row0":"row1";
				tab +="<tr class="+cls+">";
				for(int k=0;k < temporwData.size();k++)
				{ 	
					if(k==0)
					{
						tab+="<td class='left'><input type='radio' id=\"genum\" name=\"genum\" value=\""+temporwData.get(k)+"\">" +
								"<input type=\"hidden\" id=\""+temporwData.get(k)+"\" value=\""+temporwData.get(k+1)+"\"/></td>";
						tslno++;
					}

					tab +="<td>"+temporwData.get(k)+"</td>";
					colspan++;
				}
				j++;i++;
		  	} 
			if(colspan==1) colspan=colspantemp;
			tab+="</tr></tbody><tfoot><tr><td colspan=\""+colspan+"\" align=\"center\"><div class=\"container\" align=\"center\"><div class=\"pagination\" align=\"center\">";	
			
			int numRows=pnumRows;
	//------------------------------------------------------------------------------------------------------//	
	int remain=numRows/p_limit;
	
		remain++;
		int rem=numRows/p_limit;		
		int endIndex=rem*p_limit;		
		
		String option	="", sel="";
		ArrayList<Integer> paginationCount=new ArrayList<Integer>();
		paginationCount=GEG.ins_utility.getPaginationCount();
		for(int i1=0;i1<paginationCount.size();i1++)
		{
			if(paginationCount.get(i1)==p_limit) 	{ sel="selected=\"selected\"";} else sel="";
			option+="<option value=\""+paginationCount.get(i1)+"\""+sel+">"+paginationCount.get(i1)+"</option>";
		}
		String pag="<div class=\"limit\"><span>Display # </span>";
		pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" ";
		pag+=" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">";
		pag+=option+"</select> </div>";
		
		/*String pag="<div class=\"limit\"><span>Display # </span>";
		pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" "+
					" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> "+
					"<option value=\"10\">10</option>"+
					"<option value=\"15\">15</option>"+
					"<option value=\"20\">20</option>"+
					"<option value=\"25\">25</option>"+
					"</select>";
		pag+="</div>";*/
		
		String refer="";
		String last="";
		String first="";
		first+="<div class=\"button2-right\"><div class=\"start\">"+
		"<a href=\"#\" onclick=\"pagination(1,"+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Start  </a></div></div>";
		if(endIndex!=numRows)
			
		last+="<div class=\"button2-left\"><div class=\"end\">"+
		"<a href='#' onclick=\"pagination("+(endIndex+1)+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> End  </a></div></div>";
		pag+=first;
		int t_index=1;
		
		refer+="<div class=\"button2-left\"><div class=\"page\">";
		
	//	for(int ref=1;ref<=remain;ref++){
		if(remain>50) remain =50;
		for(int ref=1;ref<=remain;ref++){
			if(p_index==t_index)
				refer+="<span>"+ref+"</span>";
			else
				refer+="<a href='#' onclick=\"pagination("+t_index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">"+ref+"  </a>";
			 
			t_index=t_index+p_limit;
		}
		refer+="</div></div>";
		
		int index=p_index-p_limit;		
		if(p_index > 1) {
			pag+="<div class=\"button2-right\"><div class=\"prev\">"+
					"<a href='#' onclick=\"pagination("+index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Previous  </a></div></div>";
		}
		pag+=refer;

		int index2=p_index+p_limit;			
		if((index2) <= numRows){
			pag+="<div class=\"button2-left\"><div class=\"next\">"+
			"<a href=\"#\" onclick=\"pagination("+index2+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Next </a></div></div>";
		}
		if(p_index<=1){
			p_index=1;
		}
		pag+=last;
		if(index2 >= numRows) index2=numRows;
		if(index2==numRows){
			pag+="<div class=\"limit\">"+p_index +" to " + (index2) +" of " +numRows+"</div>";}
		else{
		pag+="<div class=\"limit\">"+p_index +" to " + (index2-1) +" of " +numRows+"</div>";}
		pag+="</div></div></td></tr></tfoot></table></fieldset></div></div></div>";
		return tab+=pag;	
	}
	
	public String gethtmlTableSearch(String p_title,String p_searchtab,String sql,ArrayList<?> p_tbltitle, ArrayList<?> p_dbresults, Integer p_index,Integer p_limit,ngm GEG, String p_Servlet,String p_task ) throws Exception{	
		
		int l_limit = p_limit + p_index;
		Iterator<?> itr2=p_dbresults.iterator();
		Iterator<?> itr1=p_tbltitle.iterator();

	String tab="<div id=\"element-box\"><div class='m'><div id='pagination_div'> ";
	tab += p_searchtab;
	int colspantemp=1;
	tab+="<fieldset><legend>"+p_title+"</legend><table class=\"adminlist\"> <thead> <tr> <th class='center'>Select</th>";
		while(itr1.hasNext()){
			tab +="<th class='left'><a href='#'>"+itr1.next()+"</a></th>";		
			colspantemp++;
		}
		tab+="</tr></thead><tbody>";
	
		
		
		l_limit++;
		int j=0;
		int colspan=1;
			while(itr2.hasNext()){
				ArrayList<String> temporwData= (ArrayList<String>)itr2.next();
				
				String cls=(j%2==0)?"row0":"row1";
				tab +="<tr class="+cls+">";
				for(int k=0;k < temporwData.size();k++)
				{ 	
					if(k==0)
					{
						tab+="<td class='center'><input type='checkbox' id=\"genum[]\" name=\"genum[]\" value=\""+temporwData.get(k)+"\">" +
								"<input type=\"hidden\" id=\""+temporwData.get(k)+"\" value=\""+temporwData.get(k+1)+"\"/></td>";
					}

					tab +="<td>"+temporwData.get(k)+"</td>";
					colspan++;
				}
				j++;
		  	} 
			if(colspan==1) colspan=colspantemp;
			tab+="</tr></tbody><tfoot><tr><td colspan=\""+colspan+"\" align=\"center\"><div class=\"container\" align=\"center\"><div class=\"pagination\" align=\"center\">";	
			
			int numRows=j;
	//------------------------------------------------------------------------------------------------------//	
	int remain=numRows/p_limit;
	
		remain++;
		int rem=numRows/p_limit;		
		int endIndex=rem*p_limit;	
		
		
		String option	="", sel="";
		ArrayList<Integer> paginationCount=new ArrayList<Integer>();
		paginationCount=GEG.ins_utility.getPaginationCount();
		for(int i=0;i<paginationCount.size();i++)
		{
			if(paginationCount.get(i)==p_limit) 	{ sel="selected=\"selected\"";} else sel="";
			option+="<option value=\""+paginationCount.get(i)+"\""+sel+">"+paginationCount.get(i)+"</option>";
		}
		String pag="<div class=\"limit\"><span>Display # </span>";
		pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" ";
		pag+=" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">";
		pag+=option+"</select> </div>";
		
		String refer="";
		String last="";
		String first="";
		first+="<div class=\"button2-right\"><div class=\"start\">"+
		"<a href=\"#\" onclick=\"pagination(1,"+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Start  </a></div></div>";
		if(endIndex!=numRows)
			
		last+="<div class=\"button2-left\"><div class=\"end\">"+
		"<a href='#' onclick=\"pagination("+(endIndex+1)+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> End  </a></div></div>";
		pag+=first;
		int t_index=1;
		
		refer+="<div class=\"button2-left\"><div class=\"page\">";
		for(int ref=1;ref<=remain;ref++){
			if(p_index==t_index)
				refer+="<span>"+ref+"</span>";
			else
				refer+="<a href='#' onclick=\"pagination("+t_index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">"+ref+"  </a>";
			 
			t_index=t_index+p_limit;
		}
		refer+="</div></div>";
		
		int index=p_index-p_limit;		
		if(p_index > 1) {
			pag+="<div class=\"button2-right\"><div class=\"prev\">"+
					"<a href='#' onclick=\"pagination("+index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Previous  </a></div></div>";
		}
		pag+=refer;

		int index2=p_index+p_limit;			
		if((index2) <= numRows){
			pag+="<div class=\"button2-left\"><div class=\"next\">"+
			"<a href=\"#\" onclick=\"pagination("+index2+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Next </a></div></div>";
		}
		if(p_index<=1){
			p_index=1;
		}
		pag+=last;
		if(index2 >= numRows) index2=numRows;
		if(index2==numRows){
			pag+="<div class=\"limit\">"+p_index +" to " + (index2) +" of " +numRows+"</div>";}
		else{
		pag+="<div class=\"limit\">"+p_index +" to " + (index2-1) +" of " +numRows+"</div>";}
		pag+="</div></div></td></tr></tfoot></table></fieldset></div></div></div>";
		return tab+=pag;	
	}
	
	
	
	//used for tables not having single primary key
		public String gethtmlTableMultiple(String p_title,String p_searchtab,String sql,ArrayList<?> p_tbltitle, ArrayList<?> p_dbresults, ArrayList<?> p_keys, Integer p_index,Integer p_limit,ngm GEG, String p_Servlet,String p_task,Integer pnumRows ) throws Exception{	
			
			int l_limit = p_limit + p_index;
			Iterator<?> itr2=p_dbresults.iterator();
			Iterator<?> itr1=p_tbltitle.iterator();
			Iterator<?> itr3=p_keys.iterator();
			
			ArrayList<String> keyData=new ArrayList<String>();
			while(itr3.hasNext()){
				keyData.add((String)itr3.next());
			}

		String tab="<div id=\"element-box\"><div class='m'><div id='pagination_div'> ";
		tab += p_searchtab;
		int colspantemp=1;
		tab+="<fieldset><legend>"+p_title+"</legend><table class=\"adminlist\"> <thead> <tr> <th class='center'>Select</th>";
			while(itr1.hasNext()){
				tab +="<th class='left'><a href='#'>"+itr1.next()+"</a></th>";		
				colspantemp++;
			}
			tab+="</tr></thead><tbody>";
		
			
			
			l_limit++;
			int j=0;
			int colspan=1;
			int tslno=p_index,i1=0;
				while(itr2.hasNext()&& i1<p_limit){ i1++;
					ArrayList<String> temporwData= (ArrayList<String>)itr2.next();
					
					String cls=(j%2==0)?"row0":"row1";
					tab +="<tr class="+cls+">";
					for(int k=0;k < temporwData.size();k++)
					{ 	
						if(k==0)
						{
							tab+="<td class='left'><input type='radio' id=\"genum\" name=\"genum\" value=\""+temporwData.get(k)+"\">"+
									"<input type=\"hidden\" id=\"key[]\" name=\"key[]\" value=\""+keyData.get(j)+"\"/></td>";
							tslno++;
						}

						tab +="<td>"+temporwData.get(k)+"</td>";
						colspan++;
					}
					j++;
			  	} 
				if(colspan==1) colspan=colspantemp;
				tab+="</tr></tbody><tfoot><tr><td colspan=\""+colspan+"\" align=\"center\"><div class=\"container\" align=\"center\"><div class=\"pagination\" align=\"center\">";	
				
				int numRows=pnumRows;
		//------------------------------------------------------------------------------------------------------//	
		int remain=numRows/p_limit;
		
			remain++;
			int rem=numRows/p_limit;		
			int endIndex=rem*p_limit;		

			String option	="", sel="";
			ArrayList<Integer> paginationCount=new ArrayList<Integer>();
			paginationCount=GEG.ins_utility.getPaginationCount();
			for(int i=0;i<paginationCount.size();i++)
			{
				if(paginationCount.get(i)==p_limit) 	{ sel="selected=\"selected\"";} else sel="";
				option+="<option value=\""+paginationCount.get(i)+"\""+sel+">"+paginationCount.get(i)+"</option>";
			}
			String pag="<div class=\"limit\"><span>Display # </span>";
			pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" ";
			pag+=" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">";
			pag+=option+"</select> </div>";
			
			String refer="";
			String last="";
			String first="";
			first+="<div class=\"button2-right\"><div class=\"start\">"+
			"<a href=\"#\" onclick=\"pagination(1,"+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Start  </a></div></div>";
			if(endIndex!=numRows)
				
			last+="<div class=\"button2-left\"><div class=\"end\">"+
			"<a href='#' onclick=\"pagination("+(endIndex+1)+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> End  </a></div></div>";
			pag+=first;
			int t_index=1;
			
			refer+="<div class=\"button2-left\"><div class=\"page\">";
			for(int ref=1;ref<=remain;ref++){
				if(p_index==t_index)
					refer+="<span>"+ref+"</span>";
				else
					refer+="<a href='#' onclick=\"pagination("+t_index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">"+ref+"  </a>";
				 
				t_index=t_index+p_limit;
			}
			refer+="</div></div>";
			
			int index=p_index-p_limit;		
			if(p_index > 1) {
				pag+="<div class=\"button2-right\"><div class=\"prev\">"+
						"<a href='#' onclick=\"pagination("+index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Previous  </a></div></div>";
			}
			pag+=refer;

			int index2=p_index+p_limit;			
			if((index2) <= numRows){
				pag+="<div class=\"button2-left\"><div class=\"next\">"+
				"<a href=\"#\" onclick=\"pagination("+index2+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Next </a></div></div>";
			}
			if(p_index<=1){
				p_index=1;
			}
			pag+=last;
			if(index2 >= numRows) index2=numRows;
			if(index2==numRows){
				pag+="<div class=\"limit\">"+p_index +" to " + (index2) +" of " +numRows+"</div>";}
			else{
			pag+="<div class=\"limit\">"+p_index +" to " + (index2-1) +" of " +numRows+"</div>";}
			pag+="</div></div></td></tr></tfoot></table></fieldset></div></div></div>";
			return tab+=pag;	
		}
		
		
//A_18-09-2012		
		
public String gethtmlTable_Check(String p_title,String p_searchtab,String sql,ArrayList<?> p_tbltitle, ArrayList<?> p_dbresults, Integer p_index,Integer p_limit,ngm GEG, String p_Servlet,String p_task,Integer pnumRows ) throws Exception{	
			
			int l_limit = p_limit + p_index;
			Iterator<?> itr2=p_dbresults.iterator();
			Iterator<?> itr1=p_tbltitle.iterator();

		String tab="<div id=\"element-box\"><div class='m'><div id='pagination_div'> ";
		tab += p_searchtab;
		int colspantemp=1;
		tab+="<fieldset><legend>"+p_title+"</legend><table class=\"adminlist\"> <thead> <tr> <th class='center'>Select</th>";
			while(itr1.hasNext()){
				tab +="<th class='left'><a href='#'>"+itr1.next()+"</a></th>";		
				colspantemp++;
			}
			tab+="</tr></thead><tbody>";
		
			
			
			l_limit++;
			int j=0;
			int colspan=1;
			int tslno=p_index;
				while(itr2.hasNext()){
					ArrayList<String> temporwData= (ArrayList<String>)itr2.next();
					
					String cls=(j%2==0)?"row0":"row1";
					tab +="<tr class="+cls+">";
					for(int k=0;k < temporwData.size();k++)
					{ 	
						if(k==0)
						{
							tab+="<td class='left'><input type='checkbox' id=\"genum\" name=\"genum\" value=\""+temporwData.get(k)+"\">" +
									"<input type=\"hidden\" id=\""+temporwData.get(k)+"\" value=\""+temporwData.get(k+1)+"\"/></td>";
							tslno++;
						}

						tab +="<td>"+temporwData.get(k)+"</td>";
						colspan++;
					}
					j++;
			  	} 
				if(colspan==1) colspan=colspantemp;
				tab+="</tr></tbody><tfoot><tr><td colspan=\""+colspan+"\" align=\"center\"><div class=\"container\" align=\"center\"><div class=\"pagination\" align=\"center\">";	
				
				int numRows=pnumRows;
		//------------------------------------------------------------------------------------------------------//	
		int remain=numRows/p_limit;
		
			remain++;
			int rem=numRows/p_limit;		
			int endIndex=rem*p_limit;		

			String option	="", sel="";
			ArrayList<Integer> paginationCount=new ArrayList<Integer>();
			paginationCount=GEG.ins_utility.getPaginationCount();
			for(int i=0;i<paginationCount.size();i++)
			{
				if(paginationCount.get(i)==p_limit) 	{ sel="selected=\"selected\"";} else sel="";
				option+="<option value=\""+paginationCount.get(i)+"\""+sel+">"+paginationCount.get(i)+"</option>";
			}
			String pag="<div class=\"limit\"><span>Display # </span>";
			pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" ";
			pag+=" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">";
			pag+=option+"</select> </div>";
			
			String refer="";
			String last="";
			String first="";
			first+="<div class=\"button2-right\"><div class=\"start\">"+
			"<a href=\"#\" onclick=\"pagination(1,"+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Start  </a></div></div>";
			if(endIndex!=numRows)
				
			last+="<div class=\"button2-left\"><div class=\"end\">"+
			"<a href='#' onclick=\"pagination("+(endIndex+1)+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> End  </a></div></div>";
			pag+=first;
			int t_index=1;
			
			refer+="<div class=\"button2-left\"><div class=\"page\">";
			for(int ref=1;ref<=remain;ref++){
				if(p_index==t_index)
					refer+="<span>"+ref+"</span>";
				else
					refer+="<a href='#' onclick=\"pagination("+t_index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">"+ref+"  </a>";
				 
				t_index=t_index+p_limit;
			}
			refer+="</div></div>";
			
			int index=p_index-p_limit;		
			if(p_index > 1) {
				pag+="<div class=\"button2-right\"><div class=\"prev\">"+
						"<a href='#' onclick=\"pagination("+index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Previous  </a></div></div>";
			}
			pag+=refer;

			int index2=p_index+p_limit;			
			if((index2) <= numRows){
				pag+="<div class=\"button2-left\"><div class=\"next\">"+
				"<a href=\"#\" onclick=\"pagination("+index2+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Next </a></div></div>";
			}
			if(p_index<=1){
				p_index=1;
			}
			pag+=last;
			if(index2 >= numRows) index2=numRows;
			if(index2==numRows){
				pag+="<div class=\"limit\">"+p_index +" to " + (index2) +" of " +numRows+"</div>";}
			else{
			pag+="<div class=\"limit\">"+p_index +" to " + (index2-1) +" of " +numRows+"</div>";}
			pag+="</div></div></td></tr></tfoot></table></fieldset></div></div></div>";
			return tab+=pag;	
		}
		

public String gethtmlTableSearch(String p_title,String p_searchtab,String sql,ArrayList<?> p_tbltitle, ArrayList<?> p_dbresults, Integer p_index,Integer p_limit,ngm GEG, String p_Servlet,String p_task ,Integer pnumRows) throws Exception{	
	
	int l_limit = p_limit + p_index;
	Iterator<?> itr2=p_dbresults.iterator();
	Iterator<?> itr1=p_tbltitle.iterator();

String tab="<div id=\"element-box\"><div class='m'><div id='pagination_div'> ";
tab += p_searchtab;
int colspantemp=1;
tab+="<fieldset><legend>"+p_title+"</legend><table class=\"adminlist\"> <thead> <tr> <th class='center'>Select</th>";
	while(itr1.hasNext()){
		tab +="<th class='left'><a href='#'>"+itr1.next()+"</a></th>";		
		colspantemp++;
	}
	tab+="</tr></thead><tbody>";

	
	
	l_limit++;
	int j=0,i=0;
	int colspan=1;
		while(itr2.hasNext() && i<p_limit){
			ArrayList<String> temporwData= (ArrayList<String>)itr2.next();
			
			String cls=(j%2==0)?"row0":"row1";
			tab +="<tr class="+cls+">";
			for(int k=0;k < temporwData.size();k++)
			{ 	
				if(k==0)
				{
					tab+="<td class='center'><input type='checkbox' id=\"genum[]\" name=\"genum[]\" value=\""+temporwData.get(k)+"\">" +
							"<input type=\"hidden\" id=\""+temporwData.get(k)+"\" value=\""+temporwData.get(k+1)+"\"/></td>";
				}

				tab +="<td>"+temporwData.get(k)+"</td>";
				colspan++;
			}
			j++;i++;
	  	} 
		if(colspan==1) colspan=colspantemp;
		tab+="</tr></tbody><tfoot><tr><td colspan=\""+colspan+"\" align=\"center\"><div class=\"container\" align=\"center\"><div class=\"pagination\" align=\"center\">";	
		
		int numRows=pnumRows;
//------------------------------------------------------------------------------------------------------//	
int remain=numRows/p_limit;

	remain++;
	int rem=numRows/p_limit;		
	int endIndex=rem*p_limit;	
	
	
	String option	="", sel="";
	ArrayList<Integer> paginationCount=new ArrayList<Integer>();
	paginationCount=GEG.ins_utility.getPaginationCount();
	for(int i1=0;i1<paginationCount.size();i1++)
	{
		if(paginationCount.get(i1)==p_limit) 	{ sel="selected=\"selected\"";} else sel="";
		option+="<option value=\""+paginationCount.get(i1)+"\""+sel+">"+paginationCount.get(i1)+"</option>";
	}
	String pag="<div class=\"limit\"><span>Display # </span>";
	pag+="<select id=\"limit\" class=\"inputbox\" name=\"limit\" size=\"1\" ";
	pag+=" onchange=\"pagination('"+p_index+"',this.value,'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">";
	pag+=option+"</select> </div>";
	
	String refer="";
	String last="";
	String first="";
	first+="<div class=\"button2-right\"><div class=\"start\">"+
	"<a href=\"#\" onclick=\"pagination(1,"+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Start  </a></div></div>";
	if(endIndex!=numRows)
		
	last+="<div class=\"button2-left\"><div class=\"end\">"+
	"<a href='#' onclick=\"pagination("+(endIndex+1)+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> End  </a></div></div>";
	pag+=first;
	int t_index=1;
	
	refer+="<div class=\"button2-left\"><div class=\"page\">";
	for(int ref=1;ref<=remain;ref++){
		if(p_index==t_index)
			refer+="<span>"+ref+"</span>";
		else
			refer+="<a href='#' onclick=\"pagination("+t_index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\">"+ref+"  </a>";
		 
		t_index=t_index+p_limit;
	}
	refer+="</div></div>";
	
	int index=p_index-p_limit;		
	if(p_index > 1) {
		pag+="<div class=\"button2-right\"><div class=\"prev\">"+
				"<a href='#' onclick=\"pagination("+index+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Previous  </a></div></div>";
	}
	pag+=refer;

	int index2=p_index+p_limit;			
	if((index2) <= numRows){
		pag+="<div class=\"button2-left\"><div class=\"next\">"+
		"<a href=\"#\" onclick=\"pagination("+index2+","+p_limit+",'"+GEG.ins_sess.process_id+"','"+p_Servlet+"','"+p_task+"','pagination_div')\"> Next </a></div></div>";
	}
	if(p_index<=1){
		p_index=1;
	}
	pag+=last;
	if(index2 >= numRows) index2=numRows;
	if(index2==numRows){
		pag+="<div class=\"limit\">"+p_index +" to " + (index2) +" of " +numRows+"</div>";}
	else{
	pag+="<div class=\"limit\">"+p_index +" to " + (index2-1) +" of " +numRows+"</div>";}
	pag+="</div></div></td></tr></tfoot></table></fieldset></div></div></div>";
	return tab+=pag;	
}
}