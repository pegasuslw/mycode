/*************************************************************************
	> File Name: server.c 
	> Author: 
	> Mail: 
	> Created Time: 2015年04月09日 星期四 15时22分17秒
 ************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

//#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/select.h>
#include <arpa/inet.h>


#define SERVER_IP        "127.0.0.1"
#define LISTEN_PORT      2357
#define MAX_CLIENT_NUM   16

int main(int argc, int* argv[])
{

    int listen_fd = socket_bind(SERVER_IP,LISTEN_PORT);
    if(listen_fd <=0){
        perror("bind error");
        return -1;
    }
	listen(listen_fd, 5);
    do_select(listen_fd);

    return 0;
}

int socket_bind(char* ip, int port){
    int ret = -1;
    int listen_fd = -1;

    struct sockaddr_in serv_addr;

    listen_fd = socket(AF_INET,SOCK_STREAM,0);
    if(-1 == listen_fd){
        perror("socket() error!");
        return -1;
    }

    bzero(&serv_addr,sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    inet_pton(AF_INET,ip,&serv_addr.sin_addr);
    serv_addr.sin_port = htons(port);

    if (bind(listen_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0){
        perror("bind() error!");
        return -1;
    }
    return listen_fd;
}


int do_select(int listen_fd){

    int max_fd  = 0;
    int nready  = -1;
    int con_fd = -1;
    int i = 0;
    int maxi = 0;
    int client_fds[MAX_CLIENT_NUM] ;

    fd_set rset, all_set;
    struct sockaddr_in client_addr;
    socklen_t client_addr_len;

    FD_ZERO(&all_set);
    FD_SET(listen_fd,&all_set);

    max_fd = listen_fd;
    printf("listen_fd:%d\n", listen_fd);

    for(i=0;i<MAX_CLIENT_NUM;i++){
        client_fds[i] = -1;
    }

    while(1){
        rset = all_set;
        nready = select(max_fd+1,&rset,NULL,NULL,NULL);
        if(nready == -1){
            perror("select error:");
            return -1;
        }


        if( FD_ISSET(listen_fd,&rset) ){
            client_addr_len = sizeof(client_addr);

            if ( (con_fd = accept(listen_fd,(struct sockaddr*)&client_addr,&client_addr_len)) == -1 ){
                if(errno == EINTR){
                    continue;
                }else{
                    perror("accept() error");
                    return -1;
                }
            }
            fprintf(stdout, "accept a new client:%s:%d\n", inet_ntoa(client_addr.sin_addr), client_addr.sin_port);

            for(i=0;i<MAX_CLIENT_NUM;i++){
                if(client_fds[i] == -1){
                    client_fds[i] = con_fd;
                    printf("i=%d,client_fds[%d]:%d\n",i,i,client_fds[i]);
                    break;
                }
            }

            if(i==MAX_CLIENT_NUM){
                perror("too many clients");
                continue;
            }

            // 把client连接的fd也 监听起来
            FD_SET(con_fd, &all_set);
            // 更新select的第一个参数，即所有fd中的最大值
            max_fd = (con_fd > max_fd ?  con_fd : max_fd);

            maxi = (i > maxi ? i : maxi);
            printf("----maxi: %d\n",maxi);

        }else{
            printf("handle_connection\n");
            handle_connection(client_fds,maxi,&rset,&all_set);        
        }
    }

    return 0;
}


void handle_connection(int* client_fds, int maxi,fd_set* prset, fd_set* pall_set ){
    
    char buf[1024] = {0};
    int i = 0;
    unsigned int read_bytes = 0;
    printf("maxi = %d\n", maxi);

    for(i=0;i<=maxi;i++){
        if(client_fds[i] < 0){
            continue;
        }
        if(FD_ISSET(client_fds[i],prset)){
           read_bytes =  read(client_fds[i], buf, 1024);
            if(read_bytes <= 0){
                close(client_fds[i]);
                FD_CLR(client_fds[i], pall_set);
                client_fds[i] = -1;
                printf("client closed \n");
                continue;
            }
            printf("from client msg:%s", buf);

            write(client_fds[i], buf, read_bytes);
        }   
    }
}
