import java.util.HashMap;
import java.util.ArrayList;


public class Client{
	public static void main(String[] args){
		int size = 10000;

		// calculate ArrayList contains() spend time
		ArrayList<String> al = new ArrayList<String>(size);
		for(int i=0;i<size;i++){
			al.add("string"+i);
		}
		long begin = System.nanoTime();
		al.contains("string"+ (size-1));
		long end = System.nanoTime();
		System.out.println("ArrayList spend:" + (end-begin) + "ns");

		// calculate HashMap containsKey() spend time
		HashMap<String, String> map = new HashMap<String, String>(size);
		for(int i=0;i<size;i++){
			map.put("key"+i, "string"+i);
		}
		begin = System.nanoTime();
		map.containsKey("key"+(size-1));
		end = System.nanoTime();
		System.out.println("HashMap spend:" + (end-begin) + "ns");
	}
}
