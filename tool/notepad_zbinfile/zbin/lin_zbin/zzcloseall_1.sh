#!/bin/bash
nautilus -q
killall nautilus


ps -ef | grep firefox | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep gedit | grep -v grep | awk '{print $2}' | xargs kill -9
CURPID="$(ps | grep bash | awk '{print $1}' )"

ps -ef | grep bash | grep pts |grep -v $CURPID | grep -v grep | awk '{print $2}' | xargs kill -9
nautilus -s ~/Desktop & disown
exit
