interface Test{
	public abstract  void printName();
}


class OuterClass{
	private String name = "OuterClass";

	public class InnerClass1{
		public void printName(){
			System.out.println("InnerClass1 cann access OuterClass name" + name);
		}

	}

	private class InnerClass2 implements Test{
		public void printName(){
			System.out.println("InnerClass2 cann access OuterClass name" + name);
		}		
	}

    public Test getTest(){
    	return new InnerClass2();
    }

}


public class TestInnerClass{
	public static void main(String[] args){
		OuterClass outer = new OuterClass();

		OuterClass.InnerClass1 ic1 = outer.new InnerClass1();

		ic1. printName();

		Test t = outer.getTest();
		t.printName();
	}
}