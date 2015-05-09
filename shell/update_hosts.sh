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
    pushd ${GIT_REPO_DIR}/git > /dev/null
    git pull
    popd > /dev/null
fi

if [ "$?" != "0" ]; then
    echo "更新hosts仓库失败"
    exit
fi

pushd ${GIT_REPO_DIR}/git > /dev/null

SUC_DIR=()   #定义可以成功连上google的目录数组 
ALL_DIRS=()

#for DIR in `ls`
ls > my_dirs

while read -r DIR
do
    ALL_DIRS=("${ALL_DIRS[@]}" "${DIR}")
done < my_dirs


#echo ===================================
#echo ${ALL_DIRS[@]}
#echo ===================================


#:<<BLOCK
#while read -r DIR # 解决无法编译带空格的目录 的问题
for index in ${!ALL_DIRS[@]}
do
    DIR=${ALL_DIRS[$index]}

    if [ ! -d "${DIR}" ]; then  ## 不是目录, 忽略本次循环
        continue
    fi

    pushd "${DIR}" > /dev/null

    if [ "$?" -ne "0" ]; then
        echo "进入${DIR}失败"
        continue          # 进入目录失败
    else
        if [ ! -w /etc/hosts ];then
            sudo chmod a+w /etc/hosts
        fi
        echo "127.0.0.1 localhost" > /etc/hosts
        echo "127.0.0.1 ${HOSTNAME}" >> /etc/hosts
        cat hosts >> /etc/hosts 
        if [ "$?" == "0" ];then # 目录下没有hosts文件
            echo 正在测试的hosts是:"${DIR}"
#            ping www.google.com -c 1
            nc -w 1 www.google.com 443
            if [ "$?" == "0" ]; then
                echo "与google已通！ ^_^"
                SUC_DIR=("${SUC_DIR[@]}" "${DIR}")
            fi
        fi ## endif cat hosts文件 失败
        popd > /dev/null

    fi
done 

i=1
echo =======================
for index in ${!SUC_DIR[@]}
do
    echo -n ${i}." "
    echo ${SUC_DIR[$index]}
    i=$[i+1]
done
echo =======================

if [ 0 == ${#SUC_DIR[@]}  ]; then #如果只有一个目录，那么就直接就用了，不需要用户去选择
    echo "没有找到合适的hosts :("
    exit
elif [ 1 == ${#SUC_DIR[@]}  ]; then #如果只有一个目录，那么就直接就用了，不需要用户去选择
    pushd "${SUC_DIR[0]}"

    echo "127.0.0.1 localhost" > /etc/hosts
    echo "127.0.0.1 ${HOSTNAME}" >> /etc/hosts
    cat hosts >> /etc/hosts 
    popd > /dev/null
else
    echo "请选择你的输入:"
    read line
    line=$[line-1]

    pushd  "${SUC_DIR[${line}]}"

    echo "127.0.0.1 localhost" > /etc/hosts
    echo "127.0.0.1 ${HOSTNAME}" >> /etc/hosts
    cat hosts >> /etc/hosts 

    popd > /dev/null

fi

#BLOCK
pushd  > /dev/null  ## 弹出 ~/hosts_git/git目录
