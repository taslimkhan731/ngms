package template;
import java.util.ArrayList;
import java.util.List;

public class ngmenudata {
	List<Node> menu=new ArrayList<Node>();
	public void addMenu_Item(int id,String data,int parent_id,String image,String url,String function_call){
		if(url==null||url.equals("")||url.startsWith("#")){url="#";}
		menu.add(new Node(id, data, parent_id,image,url,function_call));
	}
	
	public class Node {
		public int id;
		public String data;
		public int parent_id;
		public String image;
		public String url;
		public String function_call;
		public Node(int id, String data, int parent_id, String image, String url,
		
		String function_call){
			super();
			this.id = id;
			this.data = data;
			this.parent_id = parent_id;
			this.image = image;
			this.url = url;
			this.function_call = function_call;
		}		
	}
}

