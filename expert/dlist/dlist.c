#include <stdio.h>
#include "dlist.h"

dlist_print_callback print_callback;

Node* dlist_create(unsigned int length, unsigned int start_value){

	Node* p1 = NULL;
	Node* p2 = NULL;

	Node* head = NULL;
	int i = 0;

	head = (Node*)malloc( sizeof(Node) );
	if(NULL == head){
		printf("malloc mem fail in create_dlist");
		return NULL;
	}
	memset(head, 0x00, sizeof(Node));
	p1=head;

	for( i=start_value;i<length+start_value;i++){
		p2 = (Node*)malloc( sizeof(Node) );
		if(NULL == p2){
			printf("malloc mem fail in create_dlist");
			return NULL;
		}
		memset(p2, 0x00, sizeof(Node));
		p2->data = (void*)i;

		p1->next = p2;
		p2->prev = p1;

		p1 = p2;
		
	}

	if( p2 != NULL ){
		p2->next = head;
		head->prev = p2;
	}

	return head;
}


void dlist_append(Node* head, void * data){
	Node* tail = NULL;
	if( head!=NULL ){
		tail = head->prev;

		Node* p = malloc(sizeof(Node));
		memset(p,0x00,sizeof(Node));
		p->data = data;
		p->next = head;
		if(tail != NULL){    // list is not empty
			p->prev = tail;
			tail->next = p;
		}else{
			p->prev = head;  // list is empty
			head->next = p; 
		}
		head->prev = p;	
	}

	return;
}

void dlist_print(Node* head)
{
	if (NULL ==  print_callback)
	{
		printf("print_callback is NULL!");
		return;
	}

	if(NULL == head)
	{
		return;
	}

	printf("double list content:\n");
	printf("--------------------------------------------------\n");

	Node* p = head->next;
	while(p!=head){
		print_callback(p->data);
		p = p->next;
	};
	printf("\n--------------------------------------------------\n");

	return;
}

void dlist_print_node(Node* node){
	if (NULL == print_callback)
	{
		printf("print_callback is NULL!\n");
		return;
	}

	print_callback(node->data);
	printf("\n");
}

unsigned int dlist_get_length(Node* head){
	if(NULL == head){
		return 0;
	}

	unsigned int length = 0;

	Node* p = head->next;
	while(p!=head)
	{
		length++;
		p = p->next;
	};

	return length;
}

Node* dlist_get_value(Node* head, unsigned int index){
	if(NULL == head){
		return NULL;
	}

	int i = 0;
	Node* p = head;

	return p;
}

void dlist_insert(Node* head, unsigned int index, void* value){
	if(NULL == head ){
		return;
	}

	Node* p = head->next;
	int i=0;
	while(p!=head){
		if(i==index){
			Node* nd = (Node*)malloc(sizeof(Node));
			if( NULL==nd ){
				printf("dlist_insert() malloc memory fail\n");
				return;
			}
			nd->data = value;
			nd->prev=p->prev;
			nd->next=p;

			p->prev->next=nd;
			p->prev = nd;
			break;
		}
		p=p->next;
		i++;
	};
	return;
}

void dlist_delete(Node* head, unsigned int index){
	if(NULL == head){
		return;
	}

	int i = 0;
	Node* p = head->next;
	while(p!=head){
		if(i==index){
			p->prev->next = p->next;
			//if(p->next){
				p->next->prev = p->prev;	
			//}
			
			free(p);
		    break;
		}

		i++;
		p=p->next;
	};
	return;
}

void dlist_set_print_callback(dlist_print_callback callback){
	print_callback = callback;
}

Node* dlist_get_max(Node* head, dlist_get_max_node_callback callback){
	if(NULL == head){
		return;
	}

	Node* p = head->next;
	Node* max = p;
	while(p!=head){
		if(p->next){
			max = callback(max, p->next);
		}
		p = p->next;
	}
	return max;
}


void dlist_for_each(Node* head, deal_foreach fun, void* foreach_context){
	if(NULL == head){
		return;
	}
	Node* p = head->next;
	while(p!=head){
		fun(foreach_context, p);

		p=p->next;
	}

	return;
}