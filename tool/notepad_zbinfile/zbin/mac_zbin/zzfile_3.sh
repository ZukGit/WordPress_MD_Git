#!/bin/bash
CURPATH=$(pwd)

if [ $# -eq 0];
then 
  nautilus -s $CURPATH/1
  exit
fi

nautilus -s $1/1
