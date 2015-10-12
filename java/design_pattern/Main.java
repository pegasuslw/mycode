/*************************************************************************
	> File Name: Main.java
	> Author: 
	> Mail: 
	> Created Time: 2015年10月12日 星期一 14时27分47秒
 ************************************************************************/

public class Main{

    public static void main(String[] args){

        CakeMenu cakeMenu = new CakeMenu();
        DinnerMenu dinnerMenu = new DinnerMenu();

        cakeMenu.add(new MenuItem("芝士蛋糕","從來沒吃過的芝士蛋糕",1));
        cakeMenu.add(new MenuItem("起司蛋糕","听起来很好吃的蛋糕",2));
        cakeMenu.add(new MenuItem("乳酪蛋糕","下次要买来尝一尝的蛋糕",3));

        dinnerMenu.add(new MenuItem("红烧排骨","哇,最喜欢吃的菜了",20));
        dinnerMenu.add(new MenuItem("油焖大虾","38元一只的好吃大虾",1280));
        dinnerMenu.add(new MenuItem("西红柿炒鸡蛋","唯一自己炒过的菜",8));

        Waitress waitress = new Waitress(cakeMenu, dinnerMenu);

        waitress.printAll();

    }
}

