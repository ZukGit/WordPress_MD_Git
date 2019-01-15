# A

# B

## ~/.bashrc
```
export PATH=$PATH:~/Desktop/bin   # 在桌面创建  bin 文件夹    mkdir ~/Desktop/bin 
unset _JAVA_OPTIONS   #这句一定放在Java环境变量配置的命令的最前面
## https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
export JAVA_HOME=~/dev/java/jdk1.8.0_191	#JDK的安装路径
export PATH=$PATH:$JAVA_HOME/bin
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
alias findm="grep -rnws --include='*.[mb][kp]' 'LOCAL_MODULE\|LOCAL_PACKAGE_NAME\|name:'"
alias cls='clear'
alias cdd='cd ~/Desktop'



```


# C
# D
# E
# F
# G
# H
# I
## install.sh
```
sudo apt-get install curl
sudo apt-get install python



```
# J
# K
# L
# M
# N
# O
# P

## /etc/profile
```
# /etc/profile: system-wide .profile file for the Bourne shell (sh(1))
# and Bourne compatible shells (bash(1), ksh(1), ash(1), ...).

if [ "`id -u`" -eq 0 ]; then
  PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
else
  PATH="/usr/local/bin:/usr/bin:/bin:/usr/local/games:/usr/games:/sbin:/usr/sbin"
fi
export PATH

if [ "$PS1" ]; then
  if [ "$BASH" ] && [ "$BASH" != "/bin/sh" ]; then
    # The file bash.bashrc already sets the default PS1.
    # PS1='\h:\w\$ '
    if [ -f /etc/bash.bashrc ]; then
      . /etc/bash.bashrc
    fi
  else
    if [ "`id -u`" -eq 0 ]; then
      PS1='# '
    else
      PS1='$ '
    fi
  fi
fi

if [ -d /etc/profile.d ]; then
  for i in /etc/profile.d/*.sh; do
    if [ -r $i ]; then
      . $i
    fi
  done
  unset i
fi
tty | egrep -q tty[1-6] && export LC_ALL=C


```

# Q
# R
# S
# T
# U
# V
# W
# X
# Y
# Z