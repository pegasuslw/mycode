/*************************************************************************
	> File Name: DinnerMenu.java
	> Author: liuwei03@tcl.com
	> Mail: 
	> Created Time: 2015年10月12日 星期一 11时01分49秒
 ************************************************************************/

import java.util.Iterator;

public class DinnerMenu implements Menu{

    private MenuItem[] mMenusItems = new MenuItem[10];

    public DinnerMenu(){}

    public void add(MenuItem item){
        for(int i=0;i<mMenusItems.length;i++)
            if(null == mMenusItems[i]){
                mMenusItems[i] = item;
                break;
            }
        return;
    }

    public void remove(MenuItem item){
        for(int i=0;i<mMenusItems.length;i++){
            if(item == mMenusItems[i]){
                mMenusItems[i] = null;
                break;
            }
        }
        return;
    }

    public Iterator createIterator(){
        return new DinnerMenuIterator(mMenusItems);
    }
}

