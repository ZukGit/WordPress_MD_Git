# 微信基础学习









#  基础代码

参考文档:   https://developers.weixin.qq.com/miniprogram/dev/framework/view/wxml/

```
if/else, for等控制能力，在小程序里边，这些控制能力都用 wx: 开头的属性来表达
```



## if else

```



```



## for

```


```





##  while

```
sss
```





# 重点语句



## 小程序与普通网页开发的区别

```
网页开发渲染线程和脚本线程是互斥的( 只能有一个线程正在运行  另一个线程等待)，这也是为什么长时间的脚本运行可能会导致页面失去响应

小程序中，渲染线程和脚本线程 是分开的，分别运行在不同的线程中

小程序的逻辑层和渲染层是分开的，逻辑层运行在 JSCore 中，并没有一个完整浏览器对象，因而缺少相关的DOM API和BOM API



```

### 小程序的在各种原生系统下运行环境

| **运行环境**     | **逻辑层**     | **渲染层**       |
| :--------------- | :------------- | :--------------- |
| iOS              | JavaScriptCore | WKWebView        |
| 安卓             | V8             | chromium定制内核 |
| 小程序开发者工具 | NWJS           | Chrome WebView   |



## 小程序工程说明

```
.json 后缀的 JSON 配置文件
.wxml 后缀的 WXML 模板文件
.wxss 后缀的 WXSS 样式文件
.js 后缀的 JS 脚本逻辑文件


```

![wechat_project](../zimage/system/wechat/wechat_project.jpg)

### JSON 配置

```
JSON 是一种数据格式，并不是编程语言，在小程序中，JSON扮演的静态配置的角色
app.json 
project.config.json
pages/logs/logs.json

```



#### `app.json` 

App.json 配置参考文档:    https://developers.weixin.qq.com/miniprogram/dev/reference/configuration/app.html  整个小程序只有一个 App 实例，是全部页面共享的

`pages`字段 —— 用于描述当前小程序所有页面路径，这是为了让微信客户端知道当前你的小程序页面定义在哪个目录 微信客户端在打开小程序之前，会把整个小程序的代码包下载到本地。紧接着通过 `app.json` 的 `pages` 字段就可以知道你当前小程序的所有页面路径。

而写在 `pages` 字段的第一个页面就是这个小程序的首页（打开小程序看到的第一个页面）



`window`字段 —— 定义小程序所有页面的顶部背景颜色，文字颜色定义等

```
 app.json 是当前小程序的全局配置，包括了小程序的所有页面路径、界面表现、网络超时时间、底部 tab 等
 
 app.json 
 {
  "pages": [  ##  用于描述当前小程序所有页面路径，这是为了让微信客户端知道当前你的小程序页面定义在哪个目录
    "pages/index/index",
    "pages/logs/logs"
  ],
  "window": {  ## 定义小程序所有页面的顶部背景颜色，文字颜色定义等
    "backgroundTextStyle": "light",
    "navigationBarBackgroundColor": "#fff",
    "navigationBarTitleText": "WeChat",
    "navigationBarTextStyle": "black"
  },
  "sitemapLocation": "sitemap.json"
}
```



##### 小程序只有一个 App （app.js）

参考文档： https://developers.weixin.qq.com/miniprogram/dev/framework/app-service/app.html

```javascript
整个小程序只有一个 App 实例，是全部页面共享的
开发者可以通过 getApp 方法获取到全局唯一的 App 示例

App({
  onLaunch: function () {
    // 小程序启动之后 触发
  }
})
```

```js
// app.js
App({
  onLaunch (options) {
    // Do something initial when launch.
  },
  onShow (options) {
    // Do something when show.
  },
  onHide () {
    // Do something when hide.
  },
  onError (msg) {
    console.log(msg)
  },
  globalData: 'I am global data'
})
```

```js
整个小程序只有一个 App 实例，是全部页面共享的
开发者可以通过 getApp 方法获取到全局唯一的 App 示例
// xxx.js
const appInstance = getApp()
console.log(appInstance.globalData) // I am global data
```





###  project.config.json

```
微信开发工具配置文档--方便换电脑时保存配置 project.config.json
通常大家在使用一个工具的时候，都会针对各自喜好做一些个性化配置，例如界面颜色、编译配置等等，当你换了另外一台电脑重新安装工具的时候，你还要重新配置。
考虑到这点，小程序开发者工具在每个项目的根目录都会生成一个 project.config.json
```



### page.json | pages/logs/logs.json

页面配置参考文档:   https://developers.weixin.qq.com/miniprogram/dev/reference/configuration/page.html

```
page.json  pages/logs/logs.json 其实用来表示 pages/logs 目录下的 logs.json 这类和小程序页面相关的配置。
如果你整个小程序的风格是蓝色调，那么你可以在 app.json 里边声明顶部颜色是蓝色即可。实际情况可能不是这样，可能你小程序里边的每个页面都有不一样的色调来区分不同功能模块，因此我们提供了 page.json，让开发者可以独立定义每个页面的一些属性，例如刚刚说的顶部颜色、是否允许下拉刷新等等
用于配置每一个页面的属性
```

```
Page 是一个页面构造器，这个构造器就生成了一个页面。
在生成页面的时候，小程序框架会把 data 数据和 xxx.wxml 一起渲染出最终的结构，于是就得到了你看到的小程序的样子。

```



####  Page.js （每个界面的逻辑）

```
Page 是一个页面构造器，这个构造器就生成了一个页面。
在生成页面的时候，小程序框架会把 data 数据和 index.wxml 一起渲染出最终的结构，于是就得到了你看到的小程序的样子。
在渲染完界面之后，页面实例就会收到一个 onLoad 的回调，你可以在这个回调处理你的逻辑

Page({
  data: { // 参与页面渲染的数据
    logs: []
  },
  onLoad: function () {
    // 页面渲染后 执行   在 app.js 的 onLaunch   onShow  方式之后执行
  }
})

```





 

![wechat_project](../zimage/system/wechat/wechat_project.jpg)











### WXSS(CSS)中新的尺寸单位 `rpx`

```

新增了尺寸单位。在写 CSS 样式时，开发者需要考虑到手机设备的屏幕会有不同的宽度和设备像素比，采用一些技巧来换算一些像素单位。

rpx:
WXSS 在底层支持新的尺寸单位 rpx ，开发者可以免去换算的烦恼，只要交给小程序底层来换算即可，由于换算采用的浮点数运算，所以运算结果会和预期结果有一点点偏差。


```





# # 组件



## map





# 案例代码



##  Image

###  使用 WeixinJSBridge 预览图片
https://developers.weixin.qq.com/miniprogram/dev/framework/quickstart/#%E5%B0%8F%E7%A8%8B%E5%BA%8F%E6%8A%80%E6%9C%AF%E5%8F%91%E5%B1%95%E5%8F%B2
```



WeixinJSBridge.invoke('imagePreview', {
    current: 'http://inews.gtimg.com/newsapp_bt/0/1693121381/641',
    urls: [ // 所有图片的URL列表，数组格式
        'https://img1.gtimg.com/10/1048/104857/10485731_980x1200_0.jpg',
        'https://img1.gtimg.com/10/1048/104857/10485726_980x1200_0.jpg',
        'https://img1.gtimg.com/10/1048/104857/10485729_980x1200_0.jpg'
    ]
}, function(res) {
    console.log(res.err_msg)
})

```



###  使用 JS-SDK 调用图片预览组件

```
wx.previewImage({
  current: 'https://img1.gtimg.com/10/1048/104857/10485726_980x1200_0.jpg',
  urls: [ // 所有图片的URL列表，数组格式
    'https://img1.gtimg.com/10/1048/104857/10485731_980x1200_0.jpg',
    'https://img1.gtimg.com/10/1048/104857/10485726_980x1200_0.jpg',
    'https://img1.gtimg.com/10/1048/104857/10485729_980x1200_0.jpg'
  ],
  success: function(res) {
    console.log(res)
  }
})
```

## button





### button按钮指定响应函数属性 bindtap

```
app.wxml 文件
<view>{{ msg }}</view>
<button bindtap="clickMe">点击我</button>   #  指定发生tap点击事件时执行 clickMe函数

app.js 文件
Page({
  clickMe: function() {                  # 在 app.js 中定义的  clickMe函数
    this.setData({ msg: "Hello World" })
  }
})


```

