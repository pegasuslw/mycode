/*************************************************************************
	> File Name: CakeMenu.java
	> Author: 
	> Mail: 
	> Created Time: 2015年10月12日 星期一 13时14分04秒
 ************************************************************************/

import java.util.ArrayList;
import java.util.Iterator;

public class CakeMenu implements Menu{

    private ArrayList<MenuItem> mMenuItems = new ArrayList<MenuItem>();

    public CakeMenu(){
    }

    public void add(MenuItem item){
        mMenuItems.add(item);
    }

    public void remove(MenuItem item){
        mMenuItems.remove(item);
    }

    public Iterator createIterator(){
        return mMenuItems.iterator();
    }

}


