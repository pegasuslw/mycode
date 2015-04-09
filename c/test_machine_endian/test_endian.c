/*************************************************************************
	> File Name: test_endian.c
	> Author: 
	> Mail: 
	> Created Time: 2015年04月09日 星期四 15时22分17秒
 ************************************************************************/

#include<stdio.h>


int main(int argc, int* argv[])
{
    int value = 0x04030201;
    int i=0;
    char *p = (char*)&value;
    
    for (; i<4; i++){
        printf("0x%02x\n", *p++);
    }

    if(*p == 0x04){
        printf("Your machine use Little Endian\n");
    }else{
        printf("Your machine use Big Endian\n");
    }
    return 0;
}
