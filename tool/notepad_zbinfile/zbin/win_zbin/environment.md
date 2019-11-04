

# CMDER自定环境设置

```



Cmder => Settings => StartUp[ Environment ] => 输入如下预置代码 把自定义目录  /zbin/win_zbin/加入环境变量PATH
doskey 用于在windows下设置别名

set PATH=%ConEmuBaseDir%\Scripts;%PATH%
set PATH=%USERPROFILE%\Desktop\zbin\win_zbin;%PATH%
alias gits=git status
alias cdd=cd /D %USERPROFILE%\Desktop
```

