import java.lang.Math;
import java.util.List;
import java.util.Arrays;
import java.util.TreeSet;


public class Client{
	public static void main(String[] args){

		int num = 10;
		Integer[] a = new Integer[num];

		for (int i=0; i<num ; i++ ) {
			a[i] = (int)(Math.random()*num) + 1;
		}

		for (int i :  a) {
			System.out.print(i + " ");
		}
		System.out.println();


		List<Integer> la = Arrays.asList(a);
		TreeSet<Integer> ts = new TreeSet<Integer>(la);

		System.out.println("Second Max:" + ts.lower(ts.last()));

	}
}