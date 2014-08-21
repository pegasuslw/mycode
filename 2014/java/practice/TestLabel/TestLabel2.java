public class TestLabel2{
	public static void main(String[] args){
		outer2:
		for (int i=0; i<100; i++ ) {
			for (int j=0;j<100; j++) {
				System.out.println("i=" + i + ",j=" + j );
				if(50==i && 50==j){
					break outer2;
				}
			}
	
		}

		
		      System.out.println("Hello,world!");
	}
}