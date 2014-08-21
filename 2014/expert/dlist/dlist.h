#ifndef __DOUBLE_LIST__
#define __DOUBLE_LIST__


typedef struct _Node{
    void* data;
    struct _Node* prev;
    struct _Node* next;
}Node;

 
typedef void (*dlist_print_callback)(void*);                                // print callback
typedef Node* (*dlist_get_max_node_callback)(Node* n1, Node* n2);           
typedef void (*deal_foreach)(void* context, Node* node);                    // for_each fun;

Node* dlist_create(unsigned int length,  unsigned int start_value);
void dlist_print(Node* head);
void dlist_print_node(Node* node);

unsigned int dlist_get_length(Node* head);
Node* dlist_get_value(Node* head, unsigned int index);
void dlist_insert(Node* head, unsigned int index, void* value);
void dlist_delete(Node* head, unsigned int index);
void dlist_set_print_callback(dlist_print_callback callback);

Node* dlist_get_max(Node* head, dlist_get_max_node_callback callback);

void dlist_for_each(Node* node, deal_foreach fun, void* foreach_context);
void dlist_append(Node* head, void * data);

#endif // end ifndef __DOUBLE_LIST__