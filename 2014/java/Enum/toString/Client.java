
enum Season{
	SPRING, SUMMER,AUTUME,WINTER;
}

public class Client{
	public static void main(String[] args){
		System.out.println("most comfortable season is " + Season.SPRING);

		// 这里编译不过， 看来java的枚举是没有值的,也不能把数字强制转换
		//Season s = (Season)2;
		//System.out.println("I like " + s);
		
		for(Season s:Season.values()){
			System.out.println(s.ordinal());
		}

	}
}
