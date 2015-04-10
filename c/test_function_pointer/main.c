/*************************************************************************
	> File Name: 
	> Author: 
	> Mail: 
	> Created Time: 2015年04月09日 星期四 15时22分17秒
 ************************************************************************/

#include<stdio.h>

typedef void (*Fun)(char *str, int value);

void test1(char* str, int value){
    printf("This is test1, str:%s, value:%d\n", str,value);
}

void test2(char* str, int value){
    printf("This is test2, value:%d, str:%s\n", value,str);
}

int main(int argc, int* argv[])
{

    Fun fun1 = test1;
    (*fun1)("123",4); // fun1("123",4); also work well 
    fun1 = test2;
    (*fun1)("567",8);
    return 0;
}
