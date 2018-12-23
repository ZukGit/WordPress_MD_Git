
## git创建本地仓库

```
-------------------使用 创建一个本地的代码-------------------
1.在官网 https://github.com/ 创建一个新的 Repository仓库

2.在SHELL命令下创建用于存放 git代码的目录
mkdir coding
cd coding

3.在SHELL命令下执行初始化命令
git init

4.执行git remote add 添加在第一步在github中的 Repository仓库
git remote add origin git@github.com:XXXXyourNameXXXX/XXXXyourRepoXXXX.git

5.git add 添加修改文件
git add xxx

6.git commit  提交修改
git commit -m ”说明这次的提交“

7.上传到主分支
git push origin master
```


## git提交代码操作
```
============提交代码操作=====================
git add XXX
git commit -m "XXXX"
git remote  add origin https:git@github.com:ZukGit/IT.git
git push -u origin master

```


## linux下安装git并注册本机秘钥操作

```
Linux(Ubuntu)下安装使用git
---------------------注册-------------------
1.Shell命令行 Home路径安装git
sudo apt-get install git

2.到git官网注册账号
https://github.com/

3.Shell命令生成ssh秘钥
ssh-keygen -t rsa -C "XXXEmailXXX@qq.com"    //对应注册git账号的email 或者能收到邮件的email

4.Home路径下复制生成的 id_rsa.pub 秘钥字符串
cat ~/.ssh/id_rsa.pub         //将打印出的字符串复制到剪切板

5.登录到git官网 进入到 Acount Setting  左边选择 SSH Keys , Add SSH Key. title随便选,粘贴入4中的复制字符串

6.测试ssh key是否成功，使用命令“ssh -T git@github.com”，
如果出现You’ve successfully authenticated, but GitHub does not provide shell access 。这就表示已成功连上github 
```


## 从git官网下载自己的git仓库并提交

```
---------------------使用 创建一个本地的代码-------------------
1.在官网 https://github.com/ 创建一个新的 Repository仓库

2.在SHELL命令下创建用于存放 git代码的目录
mkdir coding
cd coding

3.在SHELL命令下执行初始化命令
git init

4.执行git remote add 添加在第一步在github中的 Repository仓库
git remote add origin git@github.com:XXXXyourNameXXXX/XXXXyourRepoXXXX.git

5.git add 添加修改文件
git add xxx

6.git commit  提交修改
git commit -m ”说明这次的提交“

7.上传到主分支
git push origin master
```


## 从git克隆代码到本地
```
---------------------从git克隆代码到本地-------------------
1.在官网 https://github.com/ 找到一个要克隆到本地的 Repository仓库 的路径
git@github.com:ZukGit/20160905.git

2.在SHELL命令下切换到对应本地代码的文件夹
mkdir XXXX
cd XXXX

3.执行命令
git clone git@github.com:ZukGit/Source_Code.git

拉完进入本地仓库 
cd 20160905

4.git add 添加修改文件
git add xxx

5.git commit  提交修改
git commit -m ”说明这次的提交“

6.上传
git  push 

```


##  Windows下安装使用git
```
Windows下安装使用git
---------------------------------------
1.下载安装git for windows

2.创建 文件夹 git_dir 右键 点击 git bash 进入 bash shell 界面

3.创建 ssh共秘钥

cd ~/.ssh        ## 如果没有这个文件夹则创建这个文件夹  mkdir ~/.ssh

cd ~                ##保证当前路径在”~”下

ssh-keygen -t rsa -C "382581427@qq.com"      ## 生成 秘钥文件

cd ~/.ssh/          ## 进入.ssh 文件夹

cat   ./id_rsa.pub       ##  查看公钥文件  并复制到 github自己账号下的 SSH Key中

git config --global user.name "Zukgit-windos10-Mac"    ## 配置用户名 
git config --global user.email "382581427@qq.com"        ## 配置邮箱

ssh -T git@github.com     ## 检测当前是否正确配置 能与github进行连接

git clone git@github.com:ZukGit/Source_Code.git     ## 拉取对应的github仓库 repositories

```

## 设置git快捷环境变量

```
在windows的系统环境变量中 设置 变量  gitpath  值为 C:\Users\aaa\Desktop\git_code\Source_Code

在 bash/shell  中 能直接通过   cd  $gitpath 切换到git仓库的主目录

alias gitpath="C:\Users\aaa\Desktop\git_code\Source_Code"
cd gitpath
 

```




## 加速git访问速度
```
git访问速度 下载速度慢

http://blog.csdn.net/sunsteam/article/details/63253933


windows下配置git
https://jingyan.baidu.com/article/a65957f4e91ccf24e77f9b11.html
```