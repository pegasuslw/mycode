#include <stdio.h>
#include "dlist.h"

void my_dlist_print_int(void* data)
{
    int value = (int)data;
    printf("%d ", value);
    return;
}

void my_dlist_print_char(void* data)
{
    int value = (int)data;
    printf("%c ", value);
    return;
}

void my_dlist_print_str(void* data)
{
    char* value = (char*)data;
    printf("%s ", value);
    return;
}



Node* dlist_get_max_node_int(Node* n1, Node* n2){
    if( (NULL == n1) || (NULL == n2) ){
        return 0;
    }

    int num1 = (int)(n1->data);
    int num2 = (int)(n2->data);
    return num1>num2 ? n1: n2;
}

void sum_for_each(void* context, Node* node){
    int  sum = *((int*)context);
    sum += (int)node->data;

    *(int *)context = sum;
}

void upper_case_for_each(void* context, Node* node){
    int letter = (int *)node->data;
    if(letter >= 'a' && letter <= 'z'){
        letter = letter - ('a' - 'A');
        node->data = (void*)letter;
    }
}

int main()
{

    int length = 0;
    int len = 0;
    // printf("please input list node num:");
    // scanf("%d", &len);

    //int i = 'a';
    //int i = 0;
    //Node* list = dlist_create(len, i);
   // dlist_set_print_callback(my_dlist_print_int);
    // dlist_print(list);
    //dlist_set_print_callback(my_dlist_print_char);
    //dlist_print(list);

    // length = dlist_get_length(list);
    // printf("lenght:%d\n", length);

    // dlist_for_each(list, upper_case_for_each, NULL);

    // dlist_print(list);



    // int index = 0;
    // int value =0;
    // printf("please input node index you want to insert:");
    // scanf("%d", &index);
    // printf("please input node value you want to insert:");
    // scanf("%d", &value);
    // dlist_insert(list,index, (void*)value);
    // dlist_print(list);

    // printf("please input node index you want to delete:");
    // scanf("%d", &index);
    // dlist_delete(list, index);
    // dlist_print(list);

    // Node* max = dlist_get_max(list, dlist_get_max_node_int);
    // printf("max:");
    // dlist_print_node(max);

    // int sum = 0;
    // dlist_for_each(list, sum_for_each, (void *)&sum);
    // printf("sum is %d\n", sum);


    int i = 0;
    Node* list = dlist_create(len, i);
    dlist_append(list, "hello");
    dlist_append(list, "world");
    dlist_append(list, "!");

    dlist_set_print_callback(my_dlist_print_str);
    dlist_print(list);

    return 0;
}