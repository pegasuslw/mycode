class Base{
	private String name;
	Base(){
		System.out.println("---- Base default constructor  1");
	}

	Base(String name){
		this.name = name;
		System.out.println("------ Base constructor 2");
	}

}

public class TestSuper extends Base{
	TestSuper(){
		System.out.println("--------- TestSuper default constructor  1");
	}

	TestSuper(String name){
		//super(name);
		System.out.println("------ TestSuper constructor 2");	
	}

	public static void main(String[] args){
		TestSuper ts1 = new TestSuper();

		TestSuper ts2 = new TestSuper("Liu Wei");
	}

}