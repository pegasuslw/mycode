import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Client {
	public static void main(String[] args){
		// 求 80万学生的平均分数
		int num = 80 * 10000;
		
		int[] score = new int[num]; 
		for(int i=0;i<num;i++){
			score[i] = (new Random()).nextInt(150);
		}

		// 计算数组的80万学生平均成绩时间
		long begin = System.currentTimeMillis();
		System.out.print("数组访问:\t\t");
		System.out.print("average:" + average(score));
		System.out.println("spend time: " + (System.currentTimeMillis()-begin) + "ms");
		

		ArrayList<Integer> score_al = new ArrayList<Integer>();
		for(int i=0;i<num;i++){
			score_al.add((new Random()).nextInt(150));
		}
		
		// 计算ArrayList的80万学生平均成绩时间  游标iterator访问
		begin = System.currentTimeMillis();
		System.out.print("ArrayList游标访问:\t");
		System.out.print("average:" + average(score_al,1));
		System.out.println("spend time:" + (System.currentTimeMillis()-begin) + "ms");
		
		// 计算ArrayList的80万学生平均成绩时间 index下标访问
		begin = System.currentTimeMillis();
		System.out.print("ArrayList下标index访问:\t");
		System.out.print("average:" + average(score_al,2));
		System.out.println("spend time:" + (System.currentTimeMillis()-begin) + "ms");
		
		LinkedList<Integer> score_ll = new LinkedList<Integer>();
		for(int i=0;i<num;i++){
			score_ll.add((new Random()).nextInt(150));
		}
		// 计算LinkedList的80万学生平均成绩时间 游标iterator下标访问
		begin = System.currentTimeMillis();
		System.out.print("LinkedList游标访问:\t");
		System.out.print("average:" + average(score_ll,1));
		System.out.println("spend time:" + (System.currentTimeMillis()-begin) + "ms");
		
		// 计算LinkedList的80万学生平均成绩时间 下标index下标访问, 这个效率真tmd低啊
//		begin = System.currentTimeMillis();
//		System.out.print("LinkedList下标访问:\t");
//		System.out.print("average:" + average(score_ll,2));
//		System.out.println("spend time:" + (System.currentTimeMillis()-begin) + "ms");
//		System.out.println("123");
	}
	
	public static int average(int[] score){
		int sum = 0;
		for(int i:score){
			sum += i;
		}
		int average = sum/score.length;
		return average;
	}
	
	// type 参数可以去掉的, 可以根据instanceof RandomAccess类来判断！！！！！
	public static int average(List<Integer> score, int type){
		int sum = 0;
		
		if(type ==1 ){
			// 游标访问， 性能不如index访问， 因为ArrayList是RandomAccess类 
			for(int i:score){
				sum += i;
			}			
		}else{
			// index 访问 ArrayList
			for(int i=0;i<score.size(); i++){
				sum += score.get(i);
			}
		}

		int average = sum/score.size();
		return average;		
	}
}
