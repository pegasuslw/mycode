/*************************************************************************
	> File Name: DinnerMenuIterator.java
	> Author: 
	> Mail: 
	> Created Time: 2015年10月12日 星期一 13时43分27秒
 ************************************************************************/
import java.util.Iterator;

public class DinnerMenuIterator implements Iterator{

    private MenuItem[] mItems;
    private int mIndex;
    public DinnerMenuIterator(MenuItem[] items){
        mItems = items;
        mIndex = 0;
    }

    public MenuItem next(){
        MenuItem item;
        if(hasNext()){
            item = mItems[mIndex];
            mIndex += 1;
            return item;
        }
        return null;
    }

    public boolean hasNext(){
        if(mItems != null && mIndex >= 0 && mIndex < mItems.length){
            //System.out.printf("mIndex =" + mIndex);
            if(mItems[mIndex] != null){
                return true;
            }
        }

        return false;
    }

    public void remove(){
    }
}

