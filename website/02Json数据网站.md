# JSON数据网站统计


JSON在线查看格式  视图查看
https://www.bejson.com/jsoneditoronline/  
https://www.bejson.com/jsonviewernew/

Json转Java类网站
http://www.atool.org/json2javabean.php

Json技术论坛
https://www.sojson.com/tag_api.html


虚拟Json数据的API接口
https://jsonplaceholder.typicode.com/ 

## 100条简讯
https://jsonplaceholder.typicode.com/posts

返回数据:
```
[
  {
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
  },
  ......
   {
    "userId": 10,
    "id": 100,
    "title": "at nam consequatur ea labore ea harum",
    "body": "cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut"
  }
]
  
  
类分析:
class Info {
int  userId ;
int  id ;
String title;
String body;
}

new ArrayList<Info>( 100 ).toJson();
```


## 500条评论
https://jsonplaceholder.typicode.com/comments
返回数据:
```
[
  {
    "postId": 1,
    "id": 1,
    "name": "id labore ex et quam laborum",
    "email": "Eliseo@gardner.biz",
    "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
  },
  ......
    {
    "postId": 100,
    "id": 500,
    "name": "ex eaque eum natus",
    "email": "Emma@joanny.ca",
    "body": "perspiciatis quis doloremque\nveniam nisi eos velit sed\nid totam inventore voluptatem laborum et eveniet\naut aut aut maxime quia temporibus ut omnis"
  }
]



类分析:
class Comment {
int  postId ;
int  id ;
String name;
String email;
String body;
}

new ArrayList<Comment>( 500 ).toJson();



```


## 100 条简短信息
https://jsonplaceholder.typicode.com/albums

返回数据:
```

[
  {
    "userId": 1,
    "id": 1,
    "title": "quidem molestiae enim"
  },
......
  {
    "userId": 10,
    "id": 100,
    "title": "enim repellat iste"
  }
]



类分析:
class Album {
int  userId ;
int  id ;
String title;
}

new ArrayList<Album>( 100 ).toJson();


```


## 5000张 条图片信息
https://jsonplaceholder.typicode.com/albums

返回数据:
```
[
  {
    "albumId": 1,
    "id": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "http://placehold.it/600/92c952",
    "thumbnailUrl": "http://placehold.it/150/92c952"
  },
  ......
  {
    "albumId": 100,
    "id": 5000,
    "title": "error quasi sunt cupiditate voluptate ea odit beatae",
    "url": "http://placehold.it/600/6dd9cb",
    "thumbnailUrl": "http://placehold.it/150/6dd9cb"
  }
]



类分析:
class Photo {
int  albumId ;
int  id ;
String title;
String url;
String thumbnailUrl;
}

new ArrayList<Photo>( 5000 ).toJson();



```



## 10个  用户组合信息

https://jsonplaceholder.typicode.com/users


```





[
  {
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {
      "street": "Kulas Light",
      "suite": "Apt. 556",
      "city": "Gwenborough",
      "zipcode": "92998-3874",
      "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
      }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company": {
      "name": "Romaguera-Crona",
      "catchPhrase": "Multi-layered client-server neural-net",
      "bs": "harness real-time e-markets"
    }
  },
  
.......
,
  {
    "id": 10,
    "name": "Clementina DuBuque",
    "username": "Moriah.Stanton",
    "email": "Rey.Padberg@karina.biz",
    "address": {
      "street": "Kattie Turnpike",
      "suite": "Suite 198",
      "city": "Lebsackbury",
      "zipcode": "31428-2261",
      "geo": {
        "lat": "-38.2386",
        "lng": "57.2232"
      }
    },
    "phone": "024-648-3804",
    "website": "ambrose.net",
    "company": {
      "name": "Hoeger LLC",
      "catchPhrase": "Centralized empowering task-force",
      "bs": "target end-to-end models"
    }
  }
]
  
  
  
  
Java类
public class user {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;
    private Company company;
}
---------------------------
public class Address {
    private String street;
    private Date suite;
    private String city;
    private String zipcode;
    private Geo geo;
}

public class Geo {
    private String lat;
    private String lng;
}
---------------------------
public class Company {
    private String name;
    private String catchphrase;
    private String bs;
}

```



# 提供火车近30天全国车次信息的网站
https://kyfw.12306.cn/otn/resources/js/query/train_list.js?scriptVersion=1.0





# 查看QQ空间 音乐的  JSON 接口


https://www.sojson.com/api/qqmusic/8446666    【默认Json】
https://www.sojson.com/api/qqmusic/8446666/json   【Json】
https://www.sojson.com/api/qqmusic/8446666/xml    【XML信息】



```
{
  "data": {
    "curtime": "1527490542",
    "issmarter": "0",
    "xmusicnum": "1",
    "picurl": [
      
    ],
    "playlist": {
      "song": {
        "xqusic_mid": "0030Yhjy3D20mr ",
        "xqusic_id": "2115190274",
        "xctype": "1",
        "xexpire_time": "0",
        "xdesc": " ",
        "xsinger_name": "手岛葵 ",
        "xsong_name": "The&#32;Rose ",
        "xsong_url": "http://xz.iebook.cn:801/2009book/diy1001/iebook_tth/FC57BC00-B3FF-45D4-8A65-9BBD1AD5B0AD.mp3 ",
        "xsong_size": "0",
        "xsong_playtime": "0",
        "xsong_diskname": "0",
        "xsong_dissmid": "0047airw212ppN ",
        "xsong_dissid": "0",
        "xis_word": "-999",
        "xcopy_right": "-999",
        "xsinger_id": "-999"
      }
    },
    "systemtime": "1527490542"
  },
  "type": "json",
  "status": 200
}




https://www.sojson.com/api/qqmusic/8446666/xml  类型返回的数据


{"data":"
<?xml version=\"1.0\" encoding=\"gb2312\"?>\n
<qqmusic>\n
    <curtime>1527490850</curtime>\n
    <issmarter>0</issmarter>\n
    <xmusicnum>1</xmusicnum>
    <picurl></picurl>
    <playlist>
        <song>
            <xqusic_mid>
                <![CDATA[0030Yhjy3D20mr ]]>
            </xqusic_mid>\n
            <xqusic_id>2115190274</xqusic_id>\n
            <xctype>1</xctype>\n
            <xexpire_time>0</xexpire_time>\n
            <xdesc>
                <![CDATA[ ]]>
            </xdesc>\n
            <xsinger_name>
                <![CDATA[手岛葵 ]]>
            </xsinger_name>\n
            <xsong_name>
                <![CDATA[The&#32;Rose ]]>
            </xsong_name>\n
            <xsong_url>
                <![CDATA[http://xz.iebook.cn:801/2009book/diy1001/iebook_tth/FC57BC00-B3FF-45D4-8A65-9BBD1AD5B0AD.mp3 ]]>
            </xsong_url>\n
            <xsong_size>0</xsong_size>\n
            <xsong_playtime>0</xsong_playtime>\n
            <xsong_diskname>0</xsong_diskname>\n
            <xsong_dissmid>
                <![CDATA[0047airw212ppN ]]>
            </xsong_dissmid>\n
            <xsong_dissid>0</xsong_dissid>\n
            <xis_word>-999</xis_word>
            <xcopy_right>-999</xcopy_right>
            <xsinger_id>-999</xsinger_id>
        </song>
    </playlist>
    <systemtime>1527490850</systemtime>\n
</qqmusic>\n","type":"xml","status":200}
```




# 查询天气的API  JSON接口
https://www.sojson.com/open/api/weather/json.shtml?city=北京
https://www.sojson.com/open/api/weather/json.shtml?city=上海
https://www.sojson.com/open/api/weather/json.shtml?city=上饶



返回数据
```
{
  "date": "20180528",
  "message": "Success !",
  "status": 200,
  "city": "上饶",
  "count": 1803,
  "data": {
    "shidu": "93%",
    "pm25": 32.0,
    "pm10": 53.0,
    "quality": "良",
    "wendu": "22",
    "ganmao": "极少数敏感人群应减少户外活动",
    "yesterday": {
      "date": "27日星期日",
      "sunrise": "05:13",
      "high": "高温 28.0℃",
      "low": "低温 22.0℃",
      "sunset": "18:58",
      "aqi": 64.0,
      "fx": "西北风",
      "fl": "<3级",
      "type": "中雨",
      "notice": "记得随身携带雨伞哦"
    },
    "forecast": [
      {
        "date": "28日星期一",
        "sunrise": "05:13",
        "high": "高温 32.0℃",
        "low": "低温 23.0℃",
        "sunset": "18:59",
        "aqi": 54.0,
        "fx": "西风",
        "fl": "<3级",
        "type": "多云",
        "notice": "阴晴之间，谨防紫外线侵扰"
      },
      {
        "date": "29日星期二",
        "sunrise": "05:12",
        "high": "高温 28.0℃",
        "low": "低温 23.0℃",
        "sunset": "18:59",
        "aqi": 37.0,
        "fx": "东北风",
        "fl": "<3级",
        "type": "阵雨",
        "notice": "阵雨来袭，出门记得带伞"
      },
      {
        "date": "30日星期三",
        "sunrise": "05:12",
        "high": "高温 27.0℃",
        "low": "低温 21.0℃",
        "sunset": "19:00",
        "aqi": 37.0,
        "fx": "西风",
        "fl": "<3级",
        "type": "中雨",
        "notice": "记得随身携带雨伞哦"
      },
      {
        "date": "31日星期四",
        "sunrise": "05:12",
        "high": "高温 23.0℃",
        "low": "低温 20.0℃",
        "sunset": "19:00",
        "aqi": 37.0,
        "fx": "东北风",
        "fl": "<3级",
        "type": "小雨",
        "notice": "雨虽小，注意保暖别感冒"
      },
      {
        "date": "01日星期五",
        "sunrise": "05:11",
        "high": "高温 27.0℃",
        "low": "低温 19.0℃",
        "sunset": "19:01",
        "aqi": 56.0,
        "fx": "东北风",
        "fl": "<3级",
        "type": "多云",
        "notice": "阴晴之间，谨防紫外线侵扰"
      }
    ]
  }
}


JAVA类说明


public class WeatherContent {
    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private Data data;
}

------------------------------------------
public class Data {

    private Date shidu;
    private int pm25;
    private int pm10;
    private String quality;
    private String wendu;
    private String ganmao;
    private Yesterday yesterday;
    private List<Forecast> forecast;
}

public class Yesterday {

    private String date;
    private String sunrise;
    private String high;
    private String low;
    private String sunset;
    private int aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;
	}
	
	
public class Forecast {
    private String date;
    private String sunrise;
    private String high;
    private String low;
    private String sunset;
    private int aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;
}




```




# 智能机器人API JSON接口
```
支持功能：天气、翻译、藏头诗、笑话、歌词、计算、域名信息/备案/收录查询、IP查询、手机号码归属、人工智能聊天。

接口地址：http://api.qingyunke.com/api.php?key=free&appid=0&msg=关键词

　　　　　key　固定参数free

　　　　　appid 设置为0，表示智能识别，可忽略此参数

　　　　　msg　关键词，请参考下方参数示例，该参数可智能识别，该值请经过 urlencode 处理后再提交

返回结果：{"result":0,"content":"内容"}

　　　　　result　状态，0表示正常，其它数字表示错误

　　　　　content　信息内容
```

```
http://api.qingyunke.com/api.php?key=free&appid=0&msg=你好
{"result":0,"content":"不要每次都用你好做开场白行不，忒俗了"}
```

```
http://api.qingyunke.com/api.php?key=free&appid=0&msg=上饶天气
{
  "result": 0,
  "content": "★ 上饶天气{br}阴，30℃，东北风2级{br}★ 三天预测{br}[05月28日] 白天：阴，29℃，北风；夜晚：多云，23℃，东南风{br}[05月29日] 白天：阵雨，31℃，东南风；夜晚：小雨，23℃，南风{br}[05月30日] 白天：中雨，28℃，东北风；夜晚：大雨，21℃，东风"
}
```



```
http://api.qingyunke.com/api.php?key=free&appid=0&msg=拉夏贝尔
{
  "result": 0,
  "content": "★ 上饶天气{br}阴，30℃，东北风2级{br}★ 三天预测{br}[05月28日] 白天：阴，29℃，北风；夜晚：多云，23℃，东南风{br}[05月29日] 白天：阵雨，31℃，东南风；夜晚：小雨，23℃，南风{br}[05月30日] 白天：中雨，28℃，东北风；夜晚：大雨，21℃，东风"
}
```

```
http://api.qingyunke.com/api.php?key=free&appid=0&msg=你的名字
{"result":0,"content":"亲，叫我菲菲就可以了"}

```

```
http://api.qingyunke.com/api.php?key=free&appid=0&msg=笑话

{"result":0,"content":"★ 沟沟沟{br}在一个家里有一对夫妻，他们生了一对双包胎，哥哥的眼睛很好，可弟弟的眼睛却很差，有一天他们俩骑着摩托车在野外兜风可偏偏是弟弟在前驾驶，开着开着哥哥发现前面有一条沟，于是，连忙对弟弟说：“沟沟沟”可是弟弟却以为哥哥在唱歌跟着喝到：“噢了噢了噢了”话刚落音两人就摔倒了。{br}提示：按分类看笑话请发送“笑话分类”"}

```


```
http://api.qingyunke.com/api.php?key=free&appid=0&msg=歌词七里香

{"result":0,"content":"★ 七里香-周杰伦{br}作词：方文山　作曲：周杰伦{br}窗外的麻雀 在电线杆上多嘴{br}你说这一句 很有夏天的感觉{br}手中的铅笔 在纸上来来回回{br}我用几行字形容你是我的谁{br}秋刀鱼的滋味 猫跟你都想了解{br}初恋的香味就这样被我们寻回{br}那温暖的阳光 象刚摘的鲜艳草莓{br}你说你舍不得吃掉这一种感觉{br}雨下整夜 我的爱溢出就象雨水{br}院子落叶 跟我的思念厚厚一叠{br}几句是非 也无法将我的热情冷却{br}你出现在我诗的每一页{br}整夜 我的爱溢出就象雨水{br}窗台蝴蝶 象诗里纷飞的美丽章节{br}我接着写 把永远爱你写进诗的结尾{br}你是我唯一想要的了解{br}那饱满的稻穗 幸福了这个季节{br}而你的脸颊象田里熟透的蕃茄{br}你突然对我说 七里香的名字很美{br}我此刻却只想亲吻你倔强的嘴"}
```


```
http://api.qingyunke.com/api.php?key=free&appid=0&msg=翻译i love you
http://api.qingyunke.com/api.php?key=free&appid=0&msg=翻译fuck you
{"result":0,"content":"翻译：i love you{br}结果：我爱你"}

```

```  查看IP地址归属
http://api.qingyunke.com/api.php?key=free&appid=0&msg=归属43.226.44.170

{"result":0,"content":"查询：43.226.44.170{br}归属：中国辽宁大连"}
```


```  查看IP地址归属
http://api.qingyunke.com/api.php?key=free&appid=0&msg=归属18001854227

{"result":0,"content":"查询：43.226.44.170{br}归属：中国辽宁大连"}
```


```  翻译播报
https://ss0.baidu.com/6KAZsjip0QIZ8tyhnq/text2audio?tex=黄色&cuid=dict&lan=en&ctp=1&pdt=30&vol=100&spd=3
```


```  数字文字播报
https://ss0.baidu.com/6KAZsjip0QIZ8tyhnq/text2audio?tex=你好百度，你是谁，你在哪啊
&cuid=dict&lan=zh&ctp=1&pdt=30&vol=100&spd=3
```


```  数字文字播报
https://ss0.baidu.com/6KAZsjip0QIZ8tyhnq/text2audio?tex=你好百度，你是谁，你在哪啊
&cuid=dict&lan=zh&ctp=1&pdt=30&vol=100&spd=3
```

```  数字文字播报
https://ss0.baidu.com/6KAZsjip0QIZ8tyhnq/text2audio?tex=red and yellow
&cuid=dict&lan=en&ctp=1&pdt=30&vol=100&spd=3
``` 



# 在线HTTP 请求测试工具

https://www.sojson.com/httpRequest/



# 雪花特效 JS控制 HTML页面

https://www.sojson.com/blog/204.html
https://www.sojson.com/demo/christmas/index.html




# 测试网速网站
https://www.sojson.com/gongju/wangsu.html


# 网页截图功能网站
http://www.kjson.com/tools/screenshot