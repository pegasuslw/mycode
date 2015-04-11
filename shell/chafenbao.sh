#!/bin/bash

# usage : ./command.sh  or  ./command.sh -f
# This command will copy old.zip(new.zip) to current dir and name it A.zip(B.zip), and then make increasmental package , the final generate file is update.zip 
# if -f parameter is given, A.zip and B.zip will be removed and copy again.
#  


CUR_DIR=`pwd`

OLD_ZIP_PATH="/home/lw/mt55_3600_old/Code_GridUI/Project/vm_linux/android/ics-4.x/out/target/product/mt5880/obj/PACKAGING/target_files_intermediates"
NEW_ZIP_PATH="/home/lw/mt55_3600/Code_GridUI/Project/vm_linux/android/ics-4.x/out/target/product/mt5880/obj/PACKAGING/target_files_intermediates"

echo ----------------Setp 1 , get A.zip and B.zip ----------------------
if [ ! -e ${CUR_DIR}/A.zip ]; then
    echo "A.zip doesn't exist, copy A.zip form ${OLD_ZIP_PATH}/*.zip"
    cp ${OLD_ZIP_PATH}/*.zip ${CUR_DIR}/A.zip
elif [ "$1" == "-f" ]; then
    echo "remving A.zip and copy from ${OLD_ZIP_PATH}"
    cp ${OLD_ZIP_PATH}/*.zip ./A.zip
else 
    echo "A.zip exist..."
fi

if [ ! -e ${CUR_DIR}/B.zip ]; then
    echo "B.zip doesn't exist, copy B.zip form ${NEW_ZIP_PATH}/*.zip"
    cp ${NEW_ZIP_PATH}/*.zip ${CUR_DIR}/B.zip
elif [ "$1" == "-f" ]; then
    echo "remving B.zip and copy from ${NEW_ZIP_PATH}"
    cp ${NEW_ZIP_PATH}/*.zip ./B.zip
else 
    echo "B.zip exist..."
fi
echo Step1 Done. ------------------------------
echo 
echo 
echo


echo Step2--------------Copy ota_from_target_files-------------
pushd ${NEW_ZIP_PATH}/../../../../../../../build/tools/releasetools/
cp ota_from_target_files  ota_from_target_files.backup
cp ${CUR_DIR}/ota_from_target_files ./
popd


echo Step3--------------Start generate increasmental package...-------------

rm ~/chafen2/update.zip  2>/dev/null
#pushd /home/lw/mt55_3600/Code_GridUI/Project/vm_linux/android/ics-4.x/
pushd ${NEW_ZIP_PATH}/../../../../../../../
./build/tools/releasetools/ota_from_target_files -v -i ${CUR_DIR}/A.zip ${CUR_DIR}/B.zip ${CUR_DIR}/update.zip
popd


echo ---------------Generate increasmental package Done---------------------
ls --color=auto -alht

pushd ${NEW_ZIP_PATH}/../../../../../../../build/tools/releasetools/
cp ota_from_target_files.backup  ota_from_target_files
popd
