
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

#### 示例
```
compilationUnit.findAll(FieldDeclaration.class).stream()
        .filter(f -> f.isPublic() && !f.isStatic())
        .forEach(f -> System.out.println("Check field at line " +
            f.getRange().map(r -> r.begin.line).orElse(-1)));
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