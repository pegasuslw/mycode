/*************************************************************************
	> File Name: MenuItem.java
	> Author: liuwei03@tcl.com
	> Mail: 
	> Created Time: 2015年10月12日 星期一 10时59分29秒
 ************************************************************************/

public class MenuItem{
    private String mName;
    private String mDescription;
    private float mPrice;

    public MenuItem(String name, String description, float price){
        mName = name;
        mDescription = description;
        mPrice = price;
    }

    void print(){
        System.out.println("name:"+ mName + "\t\tDescription:"+mDescription+"\tprice:"+mPrice);
    }

}

