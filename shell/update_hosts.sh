#!/bin/bash
# 该脚本会看打开一条通向google的大门
# 运行改脚本后， 请在浏览器中输入www.google.com  注意不要写google.com.hk
# 这个脚本需要sudo权限，会修改/etc/hosts文件，如果需要，请备份/etc/hosts文件
# pegasus.lw@gmail.com


if [ "" == "`which git`" ]; then
    sudo apt-get install git
fi

GIT_REPO_DIR=~/hosts_git

if [ ! -d ${GIT_REPO_DIR} ]; then
    git clone https://coding.net/u/levi/p/imouto-host/git ${GIT_REPO_DIR}/git
else
    pushd ${GIT_REPO_DIR}/git
    git pull
    popd
fi

if [ "$?" != "0" ]; then
    echo "更新hosts仓库失败"
    exit
fi

pushd ${GIT_REPO_DIR}/git

SUC_DIR=()   #定义可以成功连上google的目录数组 

for DIR in `ls`
do
    if [ ! -d ${DIR} ]; then  ## 不是目录, 忽略本次循环
        continue
    fi

    pushd ${DIR}
    if [ "$?" -ne "0" ]; then
        #popd
        continue          # 进入目录失败
    else
        if [ ! -w /etc/hosts ];then
            sudo chmod a+w /etc/hosts
        fi
        echo "127.0.0.1 localhost" > /etc/hosts
        echo "127.0.0.1 ${HOSTNAME}" >> /etc/hosts
        cat hosts >> /etc/hosts 
        echo 现在用的是的hosts是:${DIR}
        #ping www.google.com -c 2
        if [ "$?" == "0" ]; then
            #telnet www.google.com 443 | grep "Escape"

            nc -w 1 www.google.com 443
            if [ "$?" == "0" ]; then
                echo "与google已通！ ^_^"
                #break  # 测试通了就返回，否则继续寻找通的host
                SUC_DIR=("${SUC_DIR[@]}" ${DIR})
            fi
         fi
        popd  # 回到git目录下
    fi
done

for d in ${SUC_DIR[@]}
do
    echo -n 1." "
    echo ${d}

done

if [ 11 == ${#SUC_DIR[@]}  ]; then
    pushd ${SUC_DIR[0]} 

    echo "127.0.0.1 localhost" > /etc/hosts
    echo "127.0.0.1 ${HOSTNAME}" >> /etc/hosts
    cat hosts >> /etc/hosts 
    popd
else
    echo "请选择你的输入:"
    read line
    line=$[line-1]

    pushd  ${SUC_DIR[${line}]} 

    echo "127.0.0.1 localhost" > /etc/hosts
    echo "127.0.0.1 ${HOSTNAME}" >> /etc/hosts
    cat hosts >> /etc/hosts 

    popd

fi


pushd  > /dev/null  ## 弹出 ~/hosts_git/git目录
