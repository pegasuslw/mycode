/*************************************************************************
	> File Name: client.c
	> Author: 
	> Mail: 
	> Created Time: 2015年04月15日 星期三 13时39分55秒
 ************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>

#include <sys/types.h>
//#include <sys/socket.h>
#include <sys/select.h>
#include <netinet/in.h>

#define SERVER_IP "127.0.0.1"

#define SERVER_PORT 2357

#define max(a,b) ((a > b) ? a : b)

int  connect_server(){
    int sock_fd = -1;
    struct sockaddr_in server_addr;

    sock_fd = socket(AF_INET,SOCK_STREAM,0);
    if(sock_fd<=0){
        perror("create socket error\n");
        exit(-1);
    }
	printf("sock_fd:%d\n",sock_fd);
    bzero(&server_addr, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(SERVER_PORT);
    inet_pton(AF_INET,SERVER_IP,&server_addr.sin_addr);
	    
    int i;
    if( i = connect(sock_fd,(struct sockaddr*)&server_addr, sizeof(server_addr))  < 0) {
        printf("i=%d\n",i);
        perror("connect server error");
        exit(-1);
    }
    return sock_fd;
}


void handle_connection(int sock_fd){

    int nready;
    int max_fd = -1;
    char buf[1024+1];
    int nBytes = 0;
    fd_set set;


    FD_ZERO(&set);

    while(1){
        FD_SET(sock_fd, &set);
        FD_SET(STDIN_FILENO, &set);
        max_fd = max(sock_fd,STDIN_FILENO);
        max_fd = sock_fd;
        nready = select(max_fd+1,&set,NULL,NULL,NULL);
        if(nready < 0){
            perror("select error:");
        }
        printf("select return\n");

        if ( FD_ISSET(sock_fd,&set) ){
            nBytes = read(sock_fd, buf, 1024);
            if(nBytes == 0){
                perror("server closed.");
                exit(-1);
            }
            fprintf(stdout,"from server:%s",buf);
        }else if (FD_ISSET(STDIN_FILENO,&set)){
            bzero(buf,1025);
            nBytes = read(STDIN_FILENO,buf,1024);
            if(nBytes>0){
                write(sock_fd,buf,strlen(buf));
            }
        }else{
            printf("unknown select return");
        }


    }
    return;    
}

int main(int argc, char* argv[]){

    int sock_fd = connect_server();
    if(sock_fd <=0 ){
        exit(-1);
    }

    int max = max(sock_fd, STDIN_FILENO);
    printf("max:%d, sock_fd=%d,STDIN_FILENO=%d\n",max,sock_fd,STDIN_FILENO);

    handle_connection(sock_fd);
    return 0;
}
