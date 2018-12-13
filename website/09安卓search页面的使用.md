# 安卓在线搜索首页_OpenGrop

<img src="//../zimage/website/09_01.jpg" />


```

Full Search: 进行全文搜索，会匹配所有的单词、字符串、标识符以及数字等，
             例如在frameworks 下通过 Full Search 搜索”mediacodec“



Definition：  搜索符号定义相关的代码，例如搜索 ondraw 函数的定义




Symbol：搜索符号，例如可以搜索类中的成员变量等 



File Path：搜索源码文件名中包含给定字符串的文件 


```

# 搜索官方文档教程

```
http://androidxref.com/6.0.0_r5/help.jsp



----------------
a plus "+" or a minus "-" sign, indicating that the clause is required or prohibited respectively;
一个 + 加号前缀 表示所要搜索的字符是必须显示的     一个 - 减号  表示当前的搜索结果是禁止显示的


----------------
a term followed by a colon ":", indicating the field to be searched. This enables one to construct queries which search multiple fields.
后跟冒号“:”的术语，表示要搜索的字段。这使我们能够构造搜索多个字段的查询 


----------------
 group of words surrounded by double quotes " ", e.g. "hello dolly"
由双引号包围的一组单词，如"hello dolly"  那么搜索会把它当做 一个词搜索  即 [hello dolly]


----------------
a nested query, enclosed in parentheses "(" ")" (also called query/field grouping) . Note that this may be used with a +/- prefix to require any of a set of terms.
嵌套查询，括号中括“(”)”(也称为查询/字段分组)。注意，这可以与+/-前缀一起使用，以要求任何一组术语。

----------------
boolean operators which allow terms to be combined through logic operators. Supported are AND(&&), "+", OR(||), NOT(!) and "-" (Note: they must be ALL CAPS).
布尔运算符，它允许通过逻辑运算符组合术语。支持are和(&&)、“+”或(||)，不支持(!)和“-”(注意:它们必须都是大写)。

----------------
to perform a single character wildcard search use the "?" symbol, e.g. te?t
要执行单个字符通配符搜索，请使用“?”符号，例如te?t Hello?World  --> hello_world   


----------------
to perform a multiple character wildcard search use the "*" symbol, e.g. test* or te*t
若要执行多字符通配符搜索，请使用“*”符号，例如test*或te*t

----------------
to do a fuzzy search(find words similar in spelling, based on the Levenshtein Distance, or Edit Distance algorithm) use the tilde, "~", e.g. rcs~
模糊搜索(根据Levenshtein Distance或Edit Distance算法查找拼写相似的单词)使用波浪号“~”，如rcs~    


----------------
to do a proximity search use the tilde, "~", symbol at the end of a Phrase. For example to search for a "opengrok" and "help" within 10 words of each other enter: "opengrok help"~10
要进行近距离搜索，请使用短语末尾的波浪号“~”符号。例如，要搜索“opengrok”和“help”，在10个单词以内输入:“opengrok help”~10
“hello world”~11


---------------
Opengrok supports escaping special characters that are part of the query syntax. Current special characters are:
Opengrok支持转义属于查询语法的特殊字符。目前的特殊字符有:
+ - && || ! ( ) { } [ ] ^ " ~ * ? : \ 
To escape these character use the \ before the character. For example to search for (1+1):2 use the query: \(1\+1\)\:2
要转义这些字符，请在字符前使用\。例如，要搜索(1+1):2，使用查询:\(1\+1\)\:2





```

# 使用示例
```

使用*号来代替任意字符的示例【  +wifistate*  】
同时搜索两个关键字, 显示那些只在一个文件中都包含这两个关键字的文件【 AccessPoint List 】


```

```
例如【  +wifistate   】： 以wifistate为关键字查找 它是一个独立的字符串   结果: new WifiState();  wifiState = wifiManager.getWifiState();  mWifiState.set(wifiState);
例如【  +wifistate*  】： 以wifistate为关键字查找 * 表示后面的字符任意   结果:  wifiState,   WifiStateListener();   WifiStateTracker
例如【  hello?world   】：  单个单词匹配
例如【  WifiManager Enable   】：  搜索同一个文件中都出现 WifiManager 和 Enable 的文件
例如【 "WifiManager Enable" 】 ： 搜索会把双引号当做一个单词进行查找  【"Hello World"】
【    "hello world" + "word" 】 搜索两个关键词  在 同一个文件 
```