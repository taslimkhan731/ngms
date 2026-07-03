package common;


import java.util.ArrayList;
import com.itextpdf.text.pdf.PdfPTable;

public class Table {
	PdfPTable table;
	float table_width,table_height;
    int rows;
    int columns;
    ArrayList<String>Table_heading;
    float heading_size;
    float content_size;
    ArrayList<String>[]Table_content;
   
public Table(){
	this.rows=0;
	this.columns=0;
	//this.table=new PdfPTable(columns);
	heading_size=16;
	//this.table_width=table_width;
	//this.table_height=table_height;
	//this.table.setTotalWidth(50f);
	this.Table_content=Table_content;
}

public void setHeading_size(float heading_size) {
	this.heading_size = heading_size;
}

public void setContent_size(float content_size) {
	this.content_size = content_size;
}

public void setTable_heading(ArrayList<String> head){
	this.Table_heading=head;	
}

public void add_tablecontent(ArrayList<String> content[]){
	Table_content=content;
}

}

