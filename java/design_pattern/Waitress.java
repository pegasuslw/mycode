/*************************************************************************
	> File Name: Waitress.java
	> Author: 
	> Mail: 
	> Created Time: 2015年10月12日 星期一 13时25分10秒
 ************************************************************************/

import java.util.ArrayList;
import java.util.Iterator;

public class Waitress{

    Menu  mCake;
    Menu  mDinner;

    ArrayList<Menu> menus;

    public Waitress(Menu cake, Menu dinner){
        /*
        menus = new ArrayList<Menu>();
        menus.add(cake);
        menus.add(dinner);
        */
        mCake = cake;
        mDinner = dinner;
        
    }

    public void printAll(){
        System.out.println("Cake:----------------------------------");
        print(mCake);

        System.out.println();
        System.out.println();

        System.out.println("Dinner:----------------------------------");
        print(mDinner);
    }

    public void print(Menu menu){

        Iterator ite = menu.createIterator();
        while(ite.hasNext()){
            MenuItem item = (MenuItem)ite.next();
            item.print();
        }
    }

}

