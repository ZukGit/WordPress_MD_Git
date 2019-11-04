#!/bin/bash
nautilus -q 
# ps -ef | grep nautilus | grep -v grep | awk '{print $2}' | xargs kill -9

ps -ef | grep firefox | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep gedit | grep -v grep | awk '{print $2}' | xargs kill -9


CURPID="$( ps | grep bash | awk '{print $1}' )"
#echo "PID="$CURPID
#echo "ps -ef | grep bash | grep pts | grep -v "$CURPID
 ps -ef | grep bash | grep pts | grep -v $CURPID | grep -v grep | awk '{print $2}' | xargs kill -9
nautilus & disown & exit 
exit