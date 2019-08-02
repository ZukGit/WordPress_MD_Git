#!/bin/bash 
CDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
ZBIN="$HOME/Desktop/zbin"
cd $HOME/Desktop/zbin
echo  CDIR=$CDIR
echo  ZBIN=$ZBIN
./D4_ImgSrcRevert_Mac.sh $ZBIN  $CDIR
echo 
read -n 1
echo 