#!/bin/bash
#1.  获取在 zbin/mac_zbin/mac_soft下的 fastgit文件夹 以及各个文件夹路径变量
#2. 开启 expect 开始交互 自动登录到 root 用户  su root     ##  sudo passwd root  改 root 用户的密码
#3. 复制文件夹中的 z.pac 到/Library/WebServer/Documents/ 目录 使得在 url路径:  http://localhost/z.pac   能访问到z.pac
#4. 在当前MacOS网络的代理   Wifi---Wifi详情---代理---自动配置代理(url)   中输入http://localhost/z.pac 
#5. 执行命令apachectl start   开启网络复制  这样 http://localhost/  http://localhost/z.pac 才能访问得到
#6. 执行 Desktop/zbin/mac_zbin/mac_soft/fastgithub_osx-arm64/fastgithub  可执行文件 开始执行对 git 网络的访问的代理

init_userprofile=$HOME
init_desktop=$HOME/Desktop
desktop=$HOME/Desktop
init_zbin=$init_desktop/zbin
zbin=$init_desktop/zbin
init_mac_zbin=$init_zbin/mac_zbin
init_mac_soft=$init_mac_zbin/macsoft
fastgithub_soft_dir=$init_mac_soft/fastgithub_osx-arm64
fastgithub_soft_path=$fastgithub_soft_dir/fastgithub
fastgithub_zpac_path=$fastgithub_soft_dir/z.pac  
## cp -fr  $fastgithub_zpac_path  /Library/WebServer/Documents/
## cp  -fr /Users/zukgit/Desktop/zbin/mac_zbin/macsoft/fastgithub_osx-arm64/z.pac  /Library/WebServer/Documents/z.pac

echo "init_userprofile="$init_userprofile
echo "desktop="$desktop
echo "init_desktop="$init_desktop
echo "zbin="$zbin
echo "init_zbin="$init_zbin
echo "init_mac_zbin="$init_mac_zbin
echo "init_mac_soft="$init_mac_soft
echo "fastgithub_soft_dir="$fastgithub_soft_dir
echo "fastgithub_soft_path="$fastgithub_soft_path
echo "fastgithub_zpac_path="$fastgithub_zpac_path
echo 

/usr/bin/expect <<EOF
set timeout -1
spawn su root
expect {
 "Password:" { send "zzj7520254\n" }
}
send "cp -fr $fastgithub_zpac_path  /Library/WebServer/Documents/\r"
send "apachectl start\r"
send "$fastgithub_soft_path\r"
expect eof
EOF