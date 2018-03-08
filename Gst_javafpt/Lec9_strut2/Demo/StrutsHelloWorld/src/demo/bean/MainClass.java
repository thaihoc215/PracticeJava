package demo.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainClass {

	public static void main(String args[]){
		
		List<Lion> list = new ArrayList<Lion>();
		Set set = new HashSet();
		Lion lion = new Lion();
		lion.setLionName("Bobby");
		list.add(lion);
		lion = new Lion();
		lion.setLionName("Daddy");
		list.add(lion);
		lion = new Lion();
		lion.setLionName("Caddy");
		list.add(lion);
		
		for(int i = 0; i < list.size(); i++){
			System.out.println(((Lion)list.get(i)).lionName);
		}
		//Collections.sort(list);
		
		for(int i = 0; i < list.size(); i++){
			System.out.println(((Lion)list.get(i)).lionName);
		}
		
		
		
		List<String> unsortList = new ArrayList<String>();
		 
		unsortList.add("CCC");
		unsortList.add("111");
		unsortList.add("AAA");
		unsortList.add("BBB");
		unsortList.add("ccc");
		unsortList.add("bbb");
		unsortList.add("aaa");
		unsortList.add("333");
		unsortList.add("222");
 
		//before sort
		System.out.println("ArrayList is unsort");
		for(String temp: unsortList){
			System.out.println(temp);
		}
 
		//sort the list
		Collections.sort(unsortList);
 
		//after sorted
		System.out.println("ArrayList is sorted");
		for(String temp: unsortList){
			System.out.println(temp);
		}
		
		
		
		
		set.add(lion);
		set.add(lion);
		
		for(int i =0; i < set.size(); i++){
			
			System.out.println(((Lion)set.iterator().next()).getLionName());
			
		}
	}
}
