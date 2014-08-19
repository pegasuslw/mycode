import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Client{
	public static void main(String[] args){
		List<People> list = new ArrayList<People>();
		list.add(new People(0, "David"));
		list.add(new People(1, "Kenny"));

		for(People p:list){
			System.out.println(p);
		}
		System.out.println("------------------------");

		TreeSet<People> set = new TreeSet<People>(list);
		for(People p:set){
			System.out.println(p);
			p.setName("name");
		}
		System.out.println("------------------------");

		for(People p:list){
			System.out.println(p);
		}
		System.out.println("------------------------");

		for(People p:set){
			System.out.println(p);
		}
	}

	static public class People implements Comparable{
		private int id;
		private String name;
		public People(int _id,String _name){
			id   = _id;
			name = _name;
		}

		public String toString(){
			return new ToStringBuilder(this).
				append("id",id).
				append("name",name).
				toString();
		}

		@Override
		public int compareTo(Object p){
			People people = (People)p;
			return id - people.getId();
		}

		public int getId(){
			return id;
		}
		public void setId(int _id){
			id = _id;
		}
		public String getName(){
			return name;
		}
		public void setName(String _name){
			name = _name;
		}
	}
}
