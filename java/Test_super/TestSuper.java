class base{

    public void print(){
    //private void print(){
        System.out.println("Base: Hello,world!");
    }
}

public class TestSuper extends base{

    public void print(){
        System.out.println("TestSuper: Hello,world");
    }

    public void test(){
        super.print();
    }

    public static void main(String[] args){
        TestSuper test = new TestSuper();
        test.print();
        test.test();
    }
}