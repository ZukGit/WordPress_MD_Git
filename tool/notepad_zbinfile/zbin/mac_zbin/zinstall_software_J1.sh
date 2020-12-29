#!/bin/bash
CURPATH=$(pwd)
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#cd $DIR
classes=$DIR

echo ~~~~~~~~~~~~~~~~~~~~~~~Try Zip Check~~~~~~~~~~~~~~~~~~~~~~~
$HOME/Desktop/zbin/H5.sh   $CURPATH  J1_all
echo  $HOME/Desktop/zbin/J1.sh  $CURPATH  J1_all
echo  $CURPATH/J1_InstallSoft.sh
echo;
echo;


echo ~~~~~~~~~ A begin_zsoftware_install ~~~~~~~~~ 

$HOME/Desktop/zbin/J1.sh $CURPATH $1 $2 $3 $4 $5 $6 $7 $8 $9
echo;
echo;


echo ~~~~~~~~~~~~~~~~~~~~~~~B begin_zsoftware_install~~~~~~~~~~~~~~~~~~~~~~~
echo  $HOME/Desktop/zbin/J1_InstallSoft.sh
$HOME/Desktop/zbin/J1_InstallSoft.sh
echo;
echo;


echo ~~~~~~~~~ C Add_PATH_Operation ~~~~~~~~~ 
$HOME/Desktop/zbin/J1_Path.sh $CURPATH $1 $2 $3 $4 $5 $6 $7 $8 $9
