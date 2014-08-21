import java.util.List;
import java.util.ArrayList;

public class Client{
    public static void main(String[] args){
        List<Integer> listA = new ArrayList<Integer>();
        listA.add(1);
        listA.add(2);
        listA.add(2);
        listA.add(3);
        listA.add(1);

        List<Integer> listB = new ArrayList<Integer>();
        listB.add(1);
        listB.add(4);
        //listB.add(1);


        // listA.addAll(listB);
        // for(Integer i : listA){
        //  System.out.println( i + " ");
        // }
        // System.out.println("-----------------------------------------");


        // listA.removeAll(listB);
        // for(Integer i: listA){
        //     System.out.println(i + " ");
        // }
        // System.out.println("-----------------------------------------");

        listA.retainAll(listB);
        for (Integer i : listA) {
           System.out.println(i + " ");
        }
        System.out.println("-----------------------------------------");

    }
}