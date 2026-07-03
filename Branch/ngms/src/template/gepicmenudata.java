package template;

import java.util.ArrayList;
import java.util.List;



public class gepicmenudata {

	

			public List<Pic_menu>pic_menu=new ArrayList<Pic_menu>();
			
		public void addpic_menu_Item(int id, String title, String icon, String url, String fcall) {
			if(url==null||url.equals("")){
				url="#";
				
			}
			pic_menu.add(new Pic_menu(id, title, icon, url, fcall));

		}
		public class Pic_menu {
			public int id;
			public String title;
			public String icon;
			public String url;
			public String fcall;
			public Pic_menu(int id, String title, String icon, String url, String fcall) {
				super();
				this.id = id;
				this.title = title;
				this.icon = icon;
				this.url = url;
				this.fcall = fcall;
			}
			
		}
		}