#!/bin/bash

JDK_TAR_FILES=(`ls jdk*.tar.gz`)

function read_select_file(){
TAR_NUMS=${#JDK_TAR_FILES[@]}

if [ ${TAR_NUMS} -gt 1 ]; then
    for ((;;))
    do
        i=0
        for var in ${JDK_TAR_FILES[@]};do
            i=$[i+1]
            echo ${i}. ${var}
        done
        echo -n "please select a tar file to install:"
        read n
        if [ ${n} -gt ${TAR_NUMS} ]; then
            echo 
        elif [ ${n} -lt 1 ]; then
            echo 
        else 
            JDK_TAR_FILE=${JDK_TAR_FILES[$[n-1]]}
            break
        fi
    done
else
    JDK_TAR_FILE=${JDK_TAR_FILES[0]}
fi
}


function install_jdk(){
    JDK_VER=`tar tf ${JDK_TAR_FILE} | head -n 1`
    JDK_VER=${JDK_VER%%/*}
    echo ${JDK_VER}

    if [ ! -d /usr/${JDK_VER} ]; then
    sudo tar zxvf ${JDK_TAR_FILE} -C /usr/ || echo install ${JDK_VER} failed!
    else 
    echo  jdk ${JDK_VER} has already been installed.
    fi
}

function set_jdk_env(){
    sudo chmod a+w /etc/profile
    JAVA_HOME=/usr/${JDK_VER}
    JRE_HOME=${JAVA_HOME}/jre
    CLASSPATH=.:${JAVA_HOME}/lib/dt.jar:${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib:${JRE_HOME}/lib:${CLASSPATH}
    PATH=${JAVA_HOME}/bin:${PATH}
    echo "export JAVA_HOME=${JAVA_HOME}" >> /etc/profile
    echo "export JRE_HOME=${JRE_HOME}" >> /etc/profile
    echo "export CLASSPATH=${CLASSPATH}" >> /etc/profile
    echo "export PATH=${JAVA_HOME}/bin:\${PATH}" >> /etc/profile
    sudo chmod o-w /etc/profile
    sudo chmod g-w /etc/profile
}

read_select_file 
install_jdk
set_jdk_env
