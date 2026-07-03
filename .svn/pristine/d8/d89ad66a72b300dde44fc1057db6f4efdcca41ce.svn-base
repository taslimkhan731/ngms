

function findPos(obj) {
var obj2 = obj;
var curtop = 0;
var curleft = 0;
if (document.getElementById || document.all) {
 do  {
  curleft += obj.offsetLeft-obj.scrollLeft;
  curtop += obj.offsetTop-obj.scrollTop;
  obj = obj.offsetParent;
  obj2 = obj2.parentNode;
  while (obj2!=obj) {
   curleft -= obj2.scrollLeft;
   curtop -= obj2.scrollTop;
   obj2 = obj2.parentNode;
  }
 } while (obj.offsetParent)
} else if (document.layers) {
 curtop += obj.y;
 curleft += obj.x;
}
return [curtop, curleft];
}   // end of findPos()


function bedselection_alert(str) {
	htmldata = str;
	$("#alertcontainer").css("display", "block");
	$("#messagefg div div").html(htmldata);
	$("#messagefg").css("display", "block");
	

} 


function show_Bedvalue( ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{
	

		// Set this variable with the desired height
	/*	var offsetPixels = 670; 

		$(window).scroll(function() {
			if ($(window).scrollTop() > offsetPixels) {
				$( "#testdiv22" ).css({
					"position": "fixed",
					"top": "15px"
				});
			} else {
				$( "#testdiv22" ).css({
					"position": "relative",
					"top": "0"
				});
			}
		});
	*/
	

	bedselection_alert("<div class='heading error' id='testdiv22'><h4>Please Select a Gender</h4><div class='inside-genders'> " +
					"<img src='img/male.jpg' class='showTooltip' title='Gents Bed' width='' onclick=\"getGL('BG','"+ptableid+"','"+ptdid+"','"+source_type+"','"+selected_type+"','"+proomno+"','"+pdate+"','"+pbedno+"','"+pnumberofbeds+"','"+p_res_type+"','"+pgh_id+"','"+pblock_id+"');stopAlert();\" >"
			+		
			"<img src='img/female.jpg' title='Ladies Bed' class='showTooltip' width='' onclick=getGL('BL','"+ptableid+"','"+ptdid+"','"+source_type+"','"+selected_type+"','"+proomno+"','"+pdate+"','"+pbedno+"','"+pnumberofbeds+"','"+p_res_type+"','"+pgh_id+"','"+pblock_id+"');stopAlert();> "+
					"</div><div class='genderselection-footer'><a onclick='stopAlert()'>Close this Window</a></div>");

/*
			bedselection_alert("<div class='heading error' id='testdiv22'><h4>Please Select a Gender</h4><div class='inside-genders'> " +
				"<img src='images/male.jpg' width='' onclick=\"getGL('BG','"+ptableid+"','"+ptdid+"','"+source_type+"','"+selected_type+"','"+proomno+"','"+pdate+"','"+pbedno+"','"+pnumberofbeds+"','"+p_res_type+"','"+pgh_id+"','"+pblock_id+"');stopAlert();\" >"
		+		
		"<img src='images/female.jpg' width='' onclick=getGL('BL','"+ptableid+"','"+ptdid+"','"+source_type+"','"+selected_type+"','"+proomno+"','"+pdate+"','"+pbedno+"','"+pnumberofbeds+"','"+p_res_type+"','"+pgh_id+"','"+pblock_id+"');stopAlert();> "+
				"</div><div class='genderselection-footer'><a onclick='stopAlert()'>Close this Window</a></div>");

*/
	
}
function getdateid(pdate)
	{
		var  retvalue ="";
		var arrdate= pdate.split("-");
	      for (var i=0;i<arrdate.length;++i){
	    	  retvalue=retvalue+arrdate[i];
	       
	      }

		return retvalue;
	}


	
function draw_emptyroom(ptableid, ptdid,source_type, selected_type,  proomno,pdate,pbedno,max_no_bed,prestype,pgh_id,pblock_id)
{
 
//alert("ptableid");
//alert(ptableid);
			var  dateid = getdateid(pdate);
			var  tableid=ptableid;
			var roomcellid="CELL_"+proomno+"_"+dateid;
			var draw="";
			
			//draw = "<td><table id='"+tableid+"'>"+	
			draw = ""+
					" <tr><td   id='"+roomcellid+"'  colspan=\"4\"> "+
					" <span class=\"notselected btn-block\" id=\"aa\"  onclick=\" do_select('"+tableid+"','"+roomcellid+"','R','R','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+prestype+"','"+pgh_id+"','"+pblock_id+"')\">R</span></td>"+ //"+selected_type+" "+proomno+" 
					"</tr>"+
					"<tr>";

			for(var k=1;k<=max_no_bed;k++)
			{
				var tempbedno="B"+k;
				var bedcellid="CELL_"+proomno+"_"+dateid+tempbedno;
					//<span onclick=selectbed(pre_id, 'R','B', roomno, pdate, max_no_room, bk);
				draw = 	draw + "<td  id='"+bedcellid+"' >"
						+"<span class=\"notselected\"  onclick=\"do_select('"+tableid+"','"+bedcellid+"','R','B','"+proomno+"','"+pdate+"','"+tempbedno+"',"+max_no_bed+",'"+prestype+"','"+pgh_id+"','"+pblock_id+"')\" > "+tempbedno+"</span></td>";//
						// 19-02-2016 +"<span class=\"notselected\"  onclick=\"do_select('"+tableid+"','"+bedcellid+"','R','B','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+prestype+"','"+pgh_id+"','"+pblock_id+"')\" > "+tempbedno+"</span></td>";//
			}			draw = draw + "</tr>";
					//"</table></td>";
			 
			//alert(ptableid);

			 document.getElementById(ptableid).innerHTML=draw;
			
		}

function draw_selectedroom(ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno,max_no_bed,prestype,pgh_id,pblock_id)
{
	var  dateid = getdateid(pdate);
	var  tableid=ptableid;
	var roomcellid="CELL_"+proomno+"_"+dateid;
	//draw = "<td><table id='"+tableid+"'>"+	
	draw = ""+	
	" <tr><td   id='"+roomcellid+"' > "+
	 "<span  class=\"selectedbox btn-block\" onclick=\" do_deselect('"+ptableid+"','"+ptdid+"','"+source_type+"','"+selected_type+"','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+prestype+"','"+pgh_id+"','"+pblock_id+"')\""+
	 "  >"+selected_type+"</span>	 "+		//"+proomno+" 
	"</td> "+
	"</tr> 		 ";			
	//"</table></td>";
	 document.getElementById(ptableid).innerHTML=draw;
	
} 
function vac_bed_draw(ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno,max_no_bed,prestype,pgh_id,pblock_id)//int bedno
{
	
	//var  dateid = getdateid(pdate);
	//var bedcellid="CELL_"+proomno+"_"+dateid+pbedno;
	var  draw= "<span class=\"notselected\" " ;
	draw = draw + " onclick=\"do_select('"+ptableid+"','"+ptdid+"','"+source_type+"','"+selected_type+"','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+prestype+"','"+pgh_id+"','"+pblock_id+"')\"> "+pbedno+"</span>";

	return draw;
}
function select_bed_draw(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno,max_no_bed,prestype,pgh_id,pblock_id)//int bedno
{
	
	var  dateid = getdateid(pdate);
	//var bedcellid="CELL_"+proomno+"_"+dateid+pbedno;
	
	
	var  draw= "<span class=\"selected_bed\" " ;
	draw = draw + " onclick=\"do_deselect('"+ptableid+"','"+ptdid+"','"+source_type+"','"+selected_type+"','"+proomno+"','"+pdate+"','"+pbedno+"',"+max_no_bed+",'"+prestype+"','"+pgh_id+"','"+pblock_id+"')\" > "+pbedno+"</span>";
	
	return draw;
}
function draw_selectedbed_BL_fromroom(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{
	
	var  dateid = getdateid(pdate);
	var  tableid=ptableid;
	//var roomcellid="CELL_"+proomno+"_"+dateid;
	var draw="";
	
	//draw = "<td><table id='"+tableid+"'>"+"<tr>";
	draw = "<tr>";

	for(var k=1;k<=pnumberofbeds;k++)
	{
		var tempbedno="B"+k;
		var bedcellid="CELL_"+proomno+"_"+dateid+tempbedno;
		if(pbedno==tempbedno)
			{
			draw = 	draw + "<td  id='"+bedcellid+"' >";
			draw = draw +select_bed_draw(ptableid, ptdid,source_type, selected_type, proomno, pdate,tempbedno,pnumberofbeds,p_res_type,pgh_id,pblock_id);
			draw = 	draw + "</td>";
			}
		else {
			draw = 	draw + "<td  id='"+bedcellid+"' >";
			draw = draw +vac_bed_draw(ptableid, ptdid,source_type, selected_type, proomno , pdate,tempbedno,pnumberofbeds,p_res_type,pgh_id,pblock_id);
			draw = 	draw + "</td>";
		}
	}		
	draw = draw + "</tr>";
		//	"</table></td>";
	

	 document.getElementById(ptableid).innerHTML=draw;
	
	
}
function draw_selectedbed_BG_fromroom(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{
	
	var  dateid = getdateid(pdate);
	var  tableid=ptableid;
	//var roomcellid="CELL_"+proomno+"_"+dateid;
	var draw="";
	
	//draw = "<td><table id='"+tableid+"'>"+		 
	draw =	"<tr>";

	for(var k=1;k<=pnumberofbeds;k++)
	{
		var tempbedno="B"+k;
		var bedcellid="CELL_"+proomno+"_"+dateid+tempbedno;
		if(pbedno==tempbedno)
			{
			draw = 	draw + "<td  id='"+bedcellid+"' >";
			draw = draw +select_bed_draw(ptableid, ptdid,source_type, selected_type, proomno, pdate,tempbedno,pnumberofbeds,p_res_type,pgh_id,pblock_id);
			draw = 	draw + "</td>";
			}
		else {
			draw = 	draw + "<td  id='"+bedcellid+"' >";
			draw = draw +vac_bed_draw(ptableid, ptdid,source_type, selected_type, proomno, pdate,tempbedno,pnumberofbeds,p_res_type,pgh_id,pblock_id);
			draw = 	draw + "</td>";
		}
	}		
	draw = draw + "</tr>";
		//	"</table></td>";
	

	 document.getElementById(ptableid).innerHTML=draw;
	
	
}


function draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{

			draw = select_bed_draw(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno,pnumberofbeds,p_res_type,pgh_id,pblock_id);

			document.getElementById(ptdid).innerHTML=draw;
	
	
}
function draw_selectedbed_BG_frombed(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{
			draw =select_bed_draw(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno,pnumberofbeds,p_res_type,pgh_id,pblock_id);
	 document.getElementById(ptdid).innerHTML=draw;// draw +
	
	
}
function draw_deselectedbed_BG_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{
			draw = vac_bed_draw(ptableid, ptdid,source_type, selected_type, proomno,pdate,pbedno,pnumberofbeds,p_res_type,pgh_id,pblock_id);
	 document.getElementById(ptdid).innerHTML=draw;//draw +
	
	
}

function draw_deselectbed_BL_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{


	draw = vac_bed_draw(ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno,pnumberofbeds,p_res_type,pgh_id,pblock_id);

		document.getElementById(ptdid).innerHTML=draw;	
}


















function getGL(bedvalue, ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{
	var dorm_type_prop = document.getElementById("dorm_type_prop").value;
	if(dorm_type_prop=="s"){
	 if(bedvalue=="BL")
	  {
		 nic_alert("You are selected Bed for a Lady","N");
	  setselecteddata(ptableid, ptdid,source_type, "BL", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  draw_selectedbed_BL_fromroom(ptableid, ptdid,source_type, "BL", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  }
 else if (bedvalue=="BG")
	  {
	  nic_alert("You are selected Bed for a Gent","N");
	  setselecteddata(ptableid, ptdid,source_type, "BG", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  draw_selectedbed_BG_fromroom(ptableid, ptdid,source_type, "BG", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  }
 else
	  nic_alert("Please enter BL/BG","V");
	
	}///sep
else if(dorm_type_prop=="b"){
	if(bedvalue=="BL"&&source_type!="R" && source_type=='BG')
	  {
		 //nic_alert("You are selected Bed for a Lady","N");
	
				
		 if(confirm("Are you sure to allot bed for LADY in GENTS room?") )
			 {
	  setselecteddata(ptableid, ptdid,source_type, "BL", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, "BL", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
			 }
	  }
else if (bedvalue=="BG"&&source_type!="R")
	  {
	  //nic_alert("You are selected Bed for a Gent","N");
	 if(confirm("Are you sure to allot bed for GENTS in LADIES room?") ){
	  setselecteddata(ptableid, ptdid,source_type, "BG", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, "BG", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  }}
else if(bedvalue=="BL")
	  {
		// nic_alert("You are selected Bed for a Lady","N");
	 if(confirm("Are you sure to allot bed for LADY in GENTS room?") ){
	  setselecteddata(ptableid, ptdid,source_type, "BL", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  draw_selectedbed_BL_fromroom(ptableid, ptdid,source_type, "BL", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  }}
else if (bedvalue=="BG")
	  {
	 // nic_alert("You are selected Bed for a Gent","N");
	 if(confirm("Are you sure to allot bed for GENTS in LADIES room?") ){
	  setselecteddata(ptableid, ptdid,source_type, "BG", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  draw_selectedbed_BG_fromroom(ptableid, ptdid,source_type, "BG", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	  }}
else
	  nic_alert("Please enter BL/BG","V");
}
	
}


function do_select(ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{
	
	var dorm_type_prop = document.getElementById("dorm_type_prop").value;
//	alert("dorm_type_prop "+dorm_type_prop);
	
	
	 
   if(source_type=="R" && selected_type=="R")  {
//	      alert("RR");
	   	  setselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
          draw_selectedroom(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
   }
   else if(source_type=="R" && selected_type=="B" && p_res_type=='R')  {
	     // alert("RBR");
	   var type="";
	   		//show_nicpopup("<div class='col-sm-12' style='border: 4px solid rgb(34, 34, 34); background: none repeat scroll 0% 0% rgb(255, 255, 255); padding: 20px;'><img src='images/male.jpg' onclick=\"alert('gents')\";this.type=\"BG\"'><img src='images/female.jpg'></div> ");
	   show_Bedvalue( ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	
   }
   
   else if(source_type=="R" && (selected_type=="BG" || selected_type=="BL") && p_res_type=='R')  {
	   //alert("RBR");
	   show_Bedvalue( ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
      
   }
   
   else if(source_type=="R" && (selected_type=="B" || selected_type=="BL") && p_res_type=='BL')  {
	     //  alert("RBBL");

	   	   setselecteddata(ptableid, ptdid,source_type, "BL", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
           draw_selectedbed_BL_fromroom(ptableid, ptdid,source_type, "BL", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
   }
   else if(source_type=="R" && (selected_type=="B" || selected_type=="BL") && p_res_type=='DL')  {
	      // alert("RBDL");

	       setselecteddata(ptableid, ptdid,source_type, "BL", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
           draw_selectedbed_BL_fromroom(ptableid, ptdid,source_type, "BL", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
   }
   else if(source_type=="R" && (selected_type=="B" || selected_type=="BG") && p_res_type=='DG')  {
	    //  alert("RBDG");

		  setselecteddata(ptableid, ptdid,source_type, "BG", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	      draw_selectedbed_BG_fromroom(ptableid, ptdid,source_type, "BG", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
   }
   else if(source_type=="R" && (selected_type=="B" || selected_type=="BG") && p_res_type=='BG')  {
	  // alert("RBBG");

	   	   setselecteddata(ptableid, ptdid,source_type, "BG", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
           draw_selectedbed_BG_fromroom(ptableid, ptdid,source_type, "BG", proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
   }
   else if(source_type=="BL" && selected_type=="BL" && p_res_type=='BL')  {
	  // alert("BLBLBL");

	       setselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
           draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
   }
   else if(source_type=="BG" && selected_type=="BG" && p_res_type=='BG')  {
	  // alert("BGBG!BG");
	       setselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
           draw_selectedbed_BG_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
   }
   		else if(source_type=="BL" && selected_type=="BL" && (p_res_type=='DL') )  {//(p_res_type=='BL' ||p_res_type!='DL')
   			   setselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	           draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	   }
	   else if(source_type=="BG" && selected_type=="BG" && (p_res_type=='DG'))  {//(p_res_type!='BG' ||p_res_type!='DG')
		       setselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	           draw_selectedbed_BG_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	   }
	   else if(dorm_type_prop=="s"){
		   
	    if(source_type=="BL" && selected_type=="BL" && p_res_type=='R')  {
		   //alert("BLBLR go");
          if(confirm("Only Ladies Bed Selection is Possible. Is it OK?")){
		       setselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	           draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
          }
	   }
	   else if(source_type=="BG" && selected_type=="BG" && p_res_type=='R')  {
		  // alert("BGBGR go");
		   if(confirm("Only Gents Bed Selection is Possible. Is it OK?")){
			   setselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	           draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
		   }
	   }
	   else{
		   nic_alert("Invalid Selection", "N");
	   }
	}// seperate
	else if(dorm_type_prop=="b"){
		  if(source_type=="BL" && selected_type=="BL" && p_res_type=='R')  {
			   //alert("BLBLR go");
	          /*if(confirm("Only Ladies Bed Selection is Possible. Is it OK?")){
			       setselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
		           draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	          }*/
			  show_Bedvalue( ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
		   }
		   else if(source_type=="BG" && selected_type=="BG" && p_res_type=='R')  {
			  // alert("BGBGR go");
			   /*if(confirm("Only Gents Bed Selection is Possible. Is it OK?")){
				   setselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
		           draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
			   }*/
			   show_Bedvalue( ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
		   }
		   else if(source_type=="BL" && selected_type=="BL" &&( p_res_type=='BG'||p_res_type=='DG'))  {
			  // nic_alert("Invalid Selection", "N");
			   setselecteddata(ptableid, ptdid,source_type, "BG", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
			   draw_selectedbed_BG_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
		   }
		   else if(source_type=="BG" && selected_type=="BG" &&( p_res_type=='BL'||p_res_type=='DL'))  {
				  // nic_alert("Invalid Selection", "N");
			   setselecteddata(ptableid, ptdid,source_type, "BL", proomno,pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
				   draw_selectedbed_BL_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
			   }
	}
}
                     
function do_deselect(ptableid, ptdid,source_type, selected_type,  proomno,pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
{
	// alert("do deselect");
	// alert(proomno);

   if(source_type=="R" && selected_type=="R") {
	   //alert("DE RR");
	   //removeselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	   removeselecteddata(pdate);
	   draw_emptyroom(ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
   }
   else if(source_type=="R" && (selected_type=="BL" || selected_type=="BG")) { 
	  // removeselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	   //alert("DE R BL||BL");
	   removeselecteddata(pdate);
	   draw_emptyroom(ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);  

   }
   else if(source_type=="BL" && selected_type=="BL" && p_res_type!='BG') { 
	  // alert("DE BL BL !BG");
	   removeselecteddata(pdate);
	   //removeselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	   draw_deselectbed_BL_frombed(ptableid, ptdid,source_type, selected_type,proomno, pdate, pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	 
   }
   else if(source_type=="BG" && selected_type=="BG" && p_res_type!='BL')  {
	 //  alert("DE BG BG !BL");
	 //  alert(pdate);
	  // removeselecteddata(ptableid, ptdid,source_type, selected_type, proomno,pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
	   removeselecteddata(pdate);
	   draw_deselectedbed_BG_frombed(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id);
 }

}



  function get_alloted_rowid(pdate)
   {
	i=0;
	while(true)
	{
	tempdate = GDI("D_date_"+i );
	if( tempdate != 'NULL' && tempdate ==pdate){  return i; break; };
	i=i+1;
	if(i==20) break;
	}
        
   }

    function GDI(pfield)
    {
	var tempvalue = 'NULL';
	var element = document.getElementById(pfield);
	if (element !== null)
 	{
	tempvalue=element.value;
	}
                 return tempvalue;
    }

      function SDI(pfield, pval)
{
	var element = document.getElementById(pfield);
	if (element !== null)
 	{
	element.value = pval;
	}
	else
		{
		
		}
/*	alert("Field Not available");*/

}

 function setselecteddata(ptableid, ptdid,source_type, selected_type, proomno, pdate,pbedno, pnumberofbeds,p_res_type,pgh_id,pblock_id)
    {
	   //var temp_allotrowid = get_alloted_rowid(pdate);

	    var t_tableid = GDI("tableidhidden"+pdate );
        var t_tdid = GDI("tdidhidden"+pdate ); // do for all parameters.
        var t_source_type = GDI("source_typehidden"+pdate );
        var t_selected_type = GDI("selected_typehidden"+pdate );
        var t_date = GDI("datehidden"+pdate );
        var t_bedno = GDI("bednohidden"+pdate );
        var t_numberofbedsno = GDI("numberofbedsnohidden"+pdate );
        var t_res_type = GDI("res_typehidden"+pdate );
        var t_gh_id = GDI("gh_idhidden"+pdate );
        var t_block_id = GDI("block_idhidden"+pdate );
        var t_roomno = GDI("roomnohidden"+pdate );
 //alert("alr");
// alert(t_block_id);
do_deselect(t_tableid, t_tdid,t_source_type, t_selected_type, t_roomno, t_date,t_bedno, t_numberofbedsno,t_res_type,t_gh_id,t_block_id);

        SDI("tableidhidden"+pdate ,ptableid ); 
        SDI("tdidhidden"+pdate,ptdid );
        SDI("source_typehidden"+pdate , source_type);
        SDI("selected_typehidden"+pdate ,selected_type );
        SDI("roomnohidden"+pdate,proomno);
        SDI("datehidden"+pdate,pdate);
        SDI("bednohidden"+pdate ,pbedno);
        SDI("numberofbedsnohidden"+pdate,pnumberofbeds );
        SDI("res_typehidden"+pdate,p_res_type );
        SDI("gh_idhidden"+pdate,pgh_id );
        SDI("block_idhidden"+pdate , pblock_id);

        SDI("selected_typeO"+pdate ,selected_type );///
        SDI("roomnoO"+pdate,proomno);
        //SDI("dateO"+pdate,pdate);///
        SDI("bednoO"+pdate ,pbedno);
/*        SDI("numberofbedsnohidden"+pdate,pnumberofbeds );
*/      
        
        
        SDI("tableidO"+pdate ,ptableid ); ///
        SDI("tdidO"+pdate,ptdid );///
        
        SDI("source_typeO"+pdate , source_type);///
        
        SDI("res_typeO"+pdate , t_res_type);///
        SDI("gh_idO"+pdate , pgh_id);///
        SDI("block_idO"+pdate , pblock_id);
        

    }
   
   function removeselecteddata(pdate)
   {
	   
	   SDI("selected_typeO"+pdate ,"" );///
       SDI("roomnoO"+pdate,"");
       //SDI("dateO"+pdate,"");///
       SDI("bednoO"+pdate ,"");
/*        SDI("numberofbedsnohidden"+pdate,pnumberofbeds );
*/      
       
       
       SDI("tableidhidden"+pdate ,"" ); ///
       SDI("tdidhidden"+pdate,"" );///
       SDI("source_typehidden"+pdate , "");/// "0"
       SDI("res_typeO"+pdate , "");///
       SDI("gh_idO"+pdate , "");///
       SDI("block_idO"+pdate , "");
   }