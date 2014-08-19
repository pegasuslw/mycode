import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Client{
    public static void main(String[] args){
        int num = 10;
        ArrayList<Integer> list = new ArrayList<Integer>(num);

        for (int i=0;i<num;i++) {
            list.add(i);
        }
        printList(list);

        for (int i=0;i<3;i++ ) {
            Collections.shuffle(list);
            printList(list);
        }

        // List<Integer> ls = Collections.nCopies(10,0);
        // System.out.println("size:" + ls.size());
        // for (int i=0;i<ls.size();i++) {
        //     ls.add(i);
        // }
        // printList(ls);        
    }

    public static void printList(List<Integer> list){
        for (Integer i : list) {
        System.out.print(i + " ");
        }
        System.out.println();        
    }
}