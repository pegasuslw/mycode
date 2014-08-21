class Base{

	public static Test test1 = new Test();

    static {
       System.out.println("This is Base Static block");   
    }

    public Base(){
        System.out.println("Base Constrcutor");
    }
}

class Test
{
	public Test(){
		System.out.println("---Test---");
	}
}

public class Sub extends Base{

    public static int i = 1;

    static {
       System.out.println("This is Sub Static block");   
    }


    {
        System.out.println("This is Sub block");
    }

    public Sub(){
        System.out.println("Sub Constructor");
    }

    public static void main(String[] args){
        Sub sub;
    }
}
