# WordPress_MD_Git

## 当前组织目录

**一 一对应原则：  分类目录与图片目录一一对应: 新增一个目录则在对应目录下增加 分类文件件**
### 分类目录:x

```


├─architect
├─program
│  ├─c
│  ├─cplusplus
│  ├─gradle
│  ├─java
│  ├─javascript
│  ├─json
│  ├─python
│  ├─shell
│  └─xml
├─selffree
├─selfstudy
├─system
│  ├─android
│  ├─ios
│  ├─linux
│  ├─macos
│  ├─wechat
│  └─windows
├─tool
│  ├─adb
│  ├─androidstudio
│  ├─git
│  ├─sourceinsight
│  ├─understand
│  └─wireshark
├─vendor
│  ├─mtk
│  └─qcom
├─website
├─wireless
  ├─bluetooth
  ├─nfc
  └─wifi



```


### 图片目录:


```

─zimage
    ├─architect
    ├─program
    │  ├─c
    │  ├─cplusplus
    │  ├─gradle
    │  ├─java
    │  ├─javascript
    │  ├─json
    │  ├─python
    │  ├─shell
    │  └─xml
    ├─selffree
    ├─selfstudy
    ├─system
    │  ├─android
    │  ├─ios
    │  ├─linux
    │  ├─macos
    │  ├─wechat
    │  └─windows
    ├─tool
    │  ├─adb
    │  ├─androidstudio
    │  ├─git
    │  ├─sourceinsight
    │  ├─understand
    │  └─wireshark
    ├─vendor
    │  ├─mtk
    │  └─qcom
    ├─website
    └─wireless
        ├─bluetooth
        ├─nfc
        └─wifi



```


## 图片的引用方式


###   MD文件中本地图片引用


```

<img src="//../zimage/architect/xxx.png">

<img src="//../zimage/system/android/xxx.png">
```


###   FTP图片文件夹位置

```   
/wwwroot/wp-content/zimage   
其中 /wwwroot/wp-content 为FTP网站内容目录

```

###   上传FTP后的引用


```
http://host810096584.s248.pppf.com.cn/wp-content/   为网站内容目录

<img src="http://host810096584.s248.pppf.com.cn/wp-content/zimage/architect/xxx.png">


<img src="http://host810096584.s248.pppf.com.cn/wp-content/zimage/architect/xxx.png">

```
###  Git仓库

```

zimage/ 下存放所有图片  依据 category分类
zimage/categoryA/categoryA1/xxxx.png


```

### wordpress中 MD-Editor的修改


```

在 MD-Editor 中 把所有的   //../   替换为web路径 http://host810096584.s248.pppf.com.cn/wp-content/    
即可实现网页图片的显示




```



## 文章与图片的命名方式


```
文章命名方式:    (001)XXXXX   ----》 (999)XXXXX
对应文章内图片命名方式:   001_xxxx.png     999_xxxx.png

```


## MD文件命令方式
```
在主分类文件夹下    从三级分类起开始添加01   99 标签
/一级分类/二级分类_01三级分类_01四级分类
/一级分类/二级分类_99三级分类_99四级分类

/tool/androidstudio.md
/tool/androidstudio_01help_xxxx.md



```