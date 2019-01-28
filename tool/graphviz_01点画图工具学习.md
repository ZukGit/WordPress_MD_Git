# graphviz 历史

```
Graphviz 或 Graph Visualization 是由  
【 AT&T 】 开发的一个开源的图形可视化工具, 提供了dot语言来编写绘图脚本，
可以很方便的绘制想要的设计图，通过本文你可以基本掌握Graphviz工具的时候，
能够灵活的绘制出自己想要的UML类图


用于绘制DOT语言脚本描述的图形。它也提供了供其它软件使用的库。
Graphviz是一个自由软件，其授权为Eclipse Public License。
其Mac版本曾经获得2004年的苹果设计奖。


是贝尔实验室几个计算机牛人设计的一个开源 的图表（计算机科学中数据结构中的图）
可视化项目，主要用C语言实现，主要实现了一些图布局算法。
通过这些算法，可以将图中的节点在画布上比较均匀的分 布，缩短节点之间的边长，并且尽量的减少边的交叉。


Graphviz 提供命令式的绘图方式，它提供一个dot语言 用来编写绘图脚本，然后对这个脚本进行解析，
分析出其中的定点，边以及子图，然后根据属性进行绘制。
Graphviz 的理念和一般的“所见即所得”的画图工具不一样，是“所想即所得”。
Graphviz提供了dot语言来编写绘图脚本，dot语言是非常简单





CMD下输入命令 确认是否生效
dot -version

配置地址: 
https://www.cnblogs.com/BlameKidd/p/9762046.html


https://blog.csdn.net/bxh7425014/article/details/51142464
graphviz 学习

```


# 下载安装 graphviz
```


下载地址: 
http://www.ddooo.com/softdown/62108.htm#dltab

安装路径: C:\Program Files (x86)\Graphviz2.38\

在用户变量的“path”里添加 
C:\Program Files (x86)\Graphviz2.38\bin\




在系统变量的“path”里添加 
C:\Program Files (x86)\Graphviz2.38\bin\dot.exe




```

## 测试安装结果
```
CMD下输入命令 确认是否生效
dot -version


C:\Users\aaa>dot -version
dot - graphviz version 2.38.0 (20140413.2041)
libdir = "C:\Program Files (x86)\Graphviz2.38\bin"
Activated plugin library: gvplugin_dot_layout.dll
Using layout: dot:dot_layout
Activated plugin library: gvplugin_core.dll
Using render: dot:core
Using device: dot:dot:core
The plugin configuration file:
        C:\Program Files (x86)\Graphviz2.38\bin\config6
                was successfully loaded.
    render      :  cairo dot fig gd gdiplus map pic pov ps svg tk vml vrml xdot
    layout      :  circo dot fdp neato nop nop1 nop2 osage patchwork sfdp twopi
    textlayout  :  textlayout
    device      :  bmp canon cmap cmapx cmapx_np dot emf emfplus eps fig gd gd2 gif gv imap imap_np ismap jpe jpeg jpg metafile pdf pic plain plain-ext png pov ps ps2 svg svgz tif tiff tk vml vmlz vrml wbmp xdot xdot1.2 xdot1.4
    loadimage   :  (lib) bmp eps gd gd2 gif jpe jpeg jpg png ps svg



```



## pycharm中使用 graphviz
```

1.CMD 下输入命令安装graphviz   pip install graphviz


2. 重启电脑 让 配置生效 
3. 打开 pycharm   安装 graphviz
file 》  setting 》 Project Interpreter 》 + 》 输入 graphviz 搜索 》install安装


4. 创建python 工程 导入包  from graphviz import Digraph;  开始编写 图形代码

```


### 1.py
 <img src="//../zimage/tool/graphviz/py1.jpg">
```
from graphviz import Digraph;

if __name__ == '__main__':
    B = Digraph('G',filename='test1.gv');
    B.edge('1','2');
    B.edge('1','3');
    B.edge('1','4');
    B.edge('3','4');
    B.view();
```


## gvedit
```
在CMD 下 输入   gvedit   打开绘图界面  gvedit


test.gv
digraph {
   size = "80, 80";
   A [label="Dot A"]
   B [label="Dot B"]
   C [label="Dot C"]
   A -> B
   A -> C
   B -> C [label=test]
}


F5 快速预览照片



```