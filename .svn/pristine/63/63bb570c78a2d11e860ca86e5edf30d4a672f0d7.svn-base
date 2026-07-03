package template;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import db.ngm;


@SuppressWarnings("rawtypes")
public class ngmenu {

public String getchild(ngmenudata m,ngm p_ngm,ngmenudata.Node parentnode,String temp_processid)
{
	String retstr="";
	int parentid = parentnode.id;
	
	Iterator it;
	it=m.menu.iterator();
	ngmenudata.Node node;
	boolean is_has_child=false;
	while(it.hasNext()){
	  node=(ngmenudata.Node)it.next();
	  
	  if(node.parent_id==parentid){
		  is_has_child = true;
	  }
	}
	if(is_has_child)
	{
		retstr=retstr.concat("<li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\"  href=\"#\"><i class=\"" +parentnode.image+"\"></i><span> "+parentnode.data +"</span> <i class=\"fa fa-caret-down\"></i></a>");
		retstr=retstr.concat("<ul class=\"dropdown-menu\">");
		
		
		it=m.menu.iterator();
		while(it.hasNext()){
		  node=(ngmenudata.Node)it.next();
		  
		  if(node.parent_id==parentid){
			  if(!node.url.toString().equals("#")){
					retstr=retstr.concat("<li class=\"dropdown\"><a class=\"\"   onclick=\"menu_url_call('"+node.url+"','"+temp_processid+"')\"><i class=\"" +node.image+"\"> </i><span> "+node.data+"</span></a></li>");
				  }
			  else
		       {
				  
				 // retstr=retstr.concat("<li class=\"dropdown\"><a class=\"\"  data-target=\".small_menu\" data-toggle=\"modal\" onclick=\"menu_pup_call('"+node.id+"','"+temp_processid+"')\"><i class=\"fa fa-arrow-circle-o-right fa-fw\"></i><span>"+node.data+"</span></a></li>");
				  retstr=retstr.concat("<li class=\"dropdown\"><a class=\"\"   onclick=\""+node.function_call+"\"><i class=\"" +node.image+"\"></i><span> "+node.data+"</span></a></li>");				  
				  
		       }
		  } //end of parent check
		} //end of while 
		
		
		retstr=retstr.concat("</ul>");
		
	} // end of if child
	else
	{
		if(!parentnode.url.toString().equals("#")){
	
			retstr=retstr.concat("<li class=\"dropdown anz\"><a class=\"\"   onclick=\"menu_url_call('"+parentnode.url+"','"+temp_processid+"')\"><i class=\"" +parentnode.image+"\"></i> <span>"+parentnode.data+"</span></a></li>");
		  }
	  else
       {
		  
		  //retstr=retstr.concat("<li class=\"dropdown\"><a class=\"\"  onclick=\"menu_pup_call('"+parentnode.id+"','"+temp_processid+"')\"><i class=\"fa fa-arrow-circle-o-right fa-fw\"></i><span>"+parentnode.data+"</span></a></li>");
		  retstr=retstr.concat("<li class=\"dropdown\"><a class=\"\"   onclick=\""+parentnode.function_call+"\"><i class=\"" +parentnode.image+"\"></i> <span>"+parentnode.data+"</span></a></li>");		  
	     }
}
		
		

	

	return retstr;
}
	
public String get_menu( ngmenudata m,ngm p_ngm) {
		
	String temp_process_id = p_ngm.ins_sess.process_id;
	Iterator it;	
	  String menu_items="";
//			  "<ul class=\"nav navbar-top-links navbar-right menu\">";
	  
	  ngmenudata.Node node;
	  
	      it=m.menu.iterator();
		  while(it.hasNext()){
		  node=(ngmenudata.Node)it.next();
		  
		  if(node.parent_id==0){
			  
			  menu_items=menu_items.concat(this.getchild(m,p_ngm,node,temp_process_id));
			  
			  
		  }
		  }
		
//   menu_items= menu_items+ "</ul>";
      
	return menu_items;	
	} //end of function

}

