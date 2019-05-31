
# JavaParser简介
```

主页:    https://javaparser.org/

Git页: https://github.com/javaparser

javaparser仓库主页:    https://github.com/javaparser/javaparser

API说明文档:  https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/3.14.1


代码示例git仓库:  https://github.com/javaparser/javaparser-visited/tree/master/src/main/java/org/javaparser

JavaParser库的目标: 我们的目标是构建一组简单且轻量级的工具来生成、分析和处理Java代码。

JavaParser.jar文件下载:
https://www.mvnjar.com/com.github.javaparser/javaparser-core/3.2.8/detail.html

```

## JavaParser库作用


### parse Java code.(语法解析Java代码)
```
使用JavaParser，您可以从Java代码中获得一个抽象语法树(AST)。AST是以一种易于处理的方式表示Java代码的结构:


```

#### 示例
```
CompilationUnit compilationUnit = StaticJavaParser.parse("class A { }");   // Java 代码的字符串
Optional<ClassOrInterfaceDeclaration> classA = compilationUnit.getClassByName("A");


```
### analyze Java code.(分析Java代码)
```
假设您希望查找公共字段而不是静态字段。使用JavaParser非常简单:




```

#### 把文件转为ComplilationUtil对象
```

ComplilationUtil mComplilationUtil = JavaParser.parser(new File("XXXXXX.java"))  // 【添加 Java文件对象路径】

```

#### ComplilationUtil可使用的API

##### mComplilationUtil.toString()
```

mComplilationUtil.toString() ;  // 把指定Java文件的内容完全输出(并设置好了格式)

```

##### ComplilationUtilMetaModel  mComplilationUtil.getMetaModel()
```
 ComplilationUtilMetaModel  compMetaModel =  mComplilationUtil.getMetaModel();    //  获取元数据  这里面有价值的东西


```

##### Optional<ModuleDeclaration>  mComplilationUtil.getModel()
```
Optional<ModuleDeclaration>  mModule_opt =  mComplilationUtil.getModel();    // Optional 作用好像是标识 这个包含的<ModuleDeclaration> 可能不存在 通过 mModule_opt.isPrsent() 来判断

ModuleDeclaration mModule = null;
if(mModule_opt.isPresent()){
ModuleDeclaration mModule = mModule_opt.get();           // 获取 真正的 ModuleDeclaration
}



```

##### List<NodeList<?>> mComplilationUtil.getNodeLists()
```
List<NodeList<?>>  nodelistList = mComplilationUtil.getNodeLists();    //   集合的集合?  需要深究下有什么


for (int i = 0; i < nodeList2List.size(); i++) {
                NodeList<?> listItem = nodeList2List.get(i);
                for (int j = 0; j < listItem.size(); j++) {
                    String NodeType = listItem.get(j).getMetaModel().getTypeName();
                   String nodeStr =  listItem.get(j).toString();
                    System.out.println("i="+i+"   j="+j+ "     NodeType="+ NodeType+"   nodeString="+nodeStr);
                }
            }
```
对于一个Java文件,在最初的解析时 表达如下:

```

package com.test.javaparser
import java.io.*;
import java.util.*;

class Test{
int a = 0;

public static void main(String[] args){
System.out.println("Hello-World!");
} 

}


```
<img src="//../zimage/tool/javaparser/getNodeList.jpg">



##### List<Node> mComplilationUtil.getChildNodes()
```
List<Node> childNodeList =  mComplilationUtil.getChildNodes() ;    //   获取到当前ABT树的子节点(有点像根目录获取子文件)



```

##### Optional<Node> mComplilationUtil.getParentNode()
```

Optional<Node>  parantNode_opt =     mCompilationUnit.getParentNode();  //  获取父类的结点 如果获取不到 说明自己就是 RootNode
            Node fatherNode = null;
            if(parantNode_opt.isPresent()){
                fatherNode = parantNode_opt.get();
                System.out.println("fatherNode.toString() "+    fatherNode.toString());
            }else{
                System.out.println("没有父类的Node 说明自己就是 RootNode");
            }

```


##### Node mComplilationUtil.getParentNodeForChildren()
```

Node   nodeSelf = mComplilationUtil.getParentNodeForChildren()；  // 把自身 ComplilationUtil  转为 对应的 Node 
String nodeType = nodeSelf.getMetaModel().getType().getName().toString();
//  nodeType  是  com.github.javaparser.ast.ComplilationUtil

```




##### NodeList<TypeDeclaration<?>>  mComplilationUtil.getTypes()
```
NodeList<TypeDeclaration<?>>  mComplilationUtil.getTypes()    //   获取当前ABT树的表达类型集合

```

##### TypeDeclaration<?>  mComplilationUtil.getType(int i)
```
mComplilationUtil.getType(0);    // 获取当前第一个子节点的声明表达类型


```

#####  List<Comment> mComplilationUtil.getComments()
```
 List<Comment>  commentList =  mComplilationUtil.getComments();     //  获取当前Java解析中 注释  //   /**/ 的集合


```

#####  List<Comment> mComplilationUtil.getAllContainedComments()
```
 List<Comment>  commentList =  mComplilationUtil.getAllContainedComments();     //  获取当前类中包含的注释(包括类头注释)   不包括文件开头的说明注释


```



#####  List<ImportDeclaration> mComplilationUtil.getImports()
```

List<ImportDeclaration> mImportDeclarationList = mComplilationUtil.getImports();    //  获取当前Java解析中 Import语句的集合
```


##### ImportDeclaration mComplilationUtil.getImport(int i )
```

ImportDeclaration mFirstDeclarationList = mComplilationUtil.getImport(0);    //  获取当前Java解析中 第一个Import语句
```


##### Optional<PackageDeclaration>  mComplilationUtil.getPackageDeclaration()
```

Optional<PackageDeclaration>  package_opt =  mComplilationUtil.getPackageDeclaration()    //  获取当前Java中的包名

PackageDeclaration pckageDec = null;

if(package_opt.isPresent()){
pckageDec= package_opt.get();           // 获取 真正的 ModuleDeclaration
System.out.print("包名:"+ pckageDec.toString() );   //  得到包名
}


```


##### Optional<Range> mComplilationUtil.getRange()
```
int beginLine =   mCompilationUnit.getRange().get().begin.line;
int endLine =   mCompilationUnit.getRange().get().end.line;              // 获取文件行数
System.out.println("起始行beginLine = "+ beginLine +"   最后一行endLine="+endLine);
String rangeString =  mCompilationUnit.getRange().get().toString();
System.out.println("rangeString = "+ rangeString);
String beginString =   mCompilationUnit.getRange().get().begin.toString();
String endString =   mCompilationUnit.getRange().get().end.toString();
System.out.println("beginString = "+ beginString+"          endString = "+endString);

//起始行beginLine = 1   最后一行endLine=1173
//rangeString = (line 1,col 1)-(line 1173,col 2)
//beginString = (line 1,col 1)          endString = (line 1173,col 2)
```



##### Optional<TokenRange> mComplilationUtil.getTokenRange()
```

// 获取第一个AST结点的内容 对称符号{} /* */ 内容
           String mTokenRangeBeginStr =  mCompilationUnit.getTokenRange().get().getBegin().getText();
           int kind =  mCompilationUnit.getTokenRange().get().getBegin().getKind();
            JavaToken beginJavaToken= mCompilationUnit.getTokenRange().get().getBegin();

                    while(beginJavaToken != null){
                        String mStr =  beginJavaToken.getText();
                        int mkind =  beginJavaToken.getKind();
                        System.out.println("mStr = "+mStr +"      mkind = "+ mkind );
                        if(beginJavaToken.getNextToken().isPresent()){
                            beginJavaToken = beginJavaToken.getNextToken().get();  // 获取下一个 单词
                        }else{
                            beginJavaToken = null;
                        }
                    }




关键词与Kind一一对应关系:


mStr =        mkind = 1 【  空格 】
mStr = 
              mkind = 4   【 换行符 】
mStr = // Instance state keys       mkind = 32   【 // 单行注释 】
mStr = /**
     * @return new WifiEnabler or null (as overridden by WifiSettingsForSetupWizard)
     */       mkind = 35               【 多行注释 】
mStr = /* End of "used in Wifi Setup context" */      mkind = 36   【 多行注释 】
mStr = boolean      mkind = 40
mStr = break      mkind = 41
mStr = case      mkind = 43
mStr = class      mkind = 46
mStr = else      mkind = 52
mStr = extends      mkind = 54
mStr = false      mkind = 55
mStr = final      mkind = 56
mStr = for      mkind = 59
mStr = if      mkind = 61
mStr = implements      mkind = 62
mStr = import      mkind = 63
mStr = int      mkind = 65
mStr = new      mkind = 69
mStr = null      mkind = 70
mStr = package      mkind = 71
mStr = private      mkind = 72
mStr = protected      mkind = 73
mStr = public      mkind = 74
mStr = return      mkind = 75
mStr = static      mkind = 77
mStr = super      mkind = 79
mStr = switch      mkind = 80
mStr = this      mkind = 82
mStr = true      mkind = 86
mStr = try      mkind = 87
mStr = void      mkind = 88
mStr = DISALLOW_CONFIG_WIFI      mkind = 114    【定义的非关键字字符串】 mStr = android      mkind = 114  mStr = Override      mkind = 114
mStr = (      mkind = 117
mStr = )      mkind = 118
mStr = {      mkind = 119
mStr = }      mkind = 120
mStr = ;      mkind = 123
mStr = .      mkind = 125
mStr = @      mkind = 126
mStr = =      mkind = 127
mStr = !      mkind = 129
mStr = :      mkind = 132
mStr = ==      mkind = 133
mStr = !=      mkind = 136
mStr = &&      mkind = 138




```



##### Optional<ComplilationUtil.Storage>  mComplilationUtil.getStorage()
```

Optional<ComplilationUtil.Storage>  mStorage_opt =  mComplilationUtil.getStorage();     //  获取当前解析文件的存储信息

ComplilationUtil.Storage  mStorage = null;

if(mStorage_opt.isPresent()){
mStorage= mStorage_opt.get();      
System.out.print("解析文件的路径:"+ mStorage.getFileName() );   //  得到文件名    /xxx/xxx/xx/test.java   得到 test.java
System.out.print("解析文件的绝对路径:"+ mStorage.getPath().toString() );   //  得到文件绝对路径     /xxx/xxx/xx/test.java 
System.out.print("解析文件的绝对路径:"+ mStorage.getDirectory().toString() );    // 得到文件夹的路径 
}


```






### transform Java code.(转换java代码)
```
也许你想确保所有抽象类都有一个以abstract开头的名称:


```
#### 示例
```

compilationUnit.findAll(ClassOrInterfaceDeclaration.class).stream()
        .filter(c -> !c.isInterface()
                && c.isAbstract()
                && !c.getNameAsString().startsWith("Abstract"))
        .forEach(c -> {
            String oldName = c.getNameAsString();
            String newName = "Abstract" + oldName;
            System.out.println("Renaming class " + oldName + " into " + newName);
            c.setName(newName);
        });
```
### generate Java code.(产生java代码)


#### 示例
```
CompilationUnit compilationUnit = new CompilationUnit();
ClassOrInterfaceDeclaration myClass = compilationUnit
        .addClass("MyClass")
        .setPublic(true);
myClass.addField(int.class, "A_CONSTANT", PUBLIC, STATIC);
myClass.addField(String.class, "name", PRIVATE);
String code = myClass.toString();

```

# lexical preservation