# 程序通过调用   show_date_time() 
```
show_date_time() 递归调用自己 并设置 1秒为重复调用间隔 来实现 每秒刷新时间的字符串

```

```
    <span id="span_dt_dt"></span><br>请在电脑端浏览本页😘


    <script language="javascript">  //  对  var date1 起始点的时间  进行 倒计时  
        function show_date_time() {  // 设置倒计时的起始函数
            window.setTimeout("show_date_time()", 1000);  // 设置每秒超时时间 1000ms  即1秒执行一次 show_date_time()
            var date1= '2017/06/07 08:52:00';  //开始时间
            var date2 = new Date();      // 现在的时间
            timeold = date2.getTime() - new Date(date1).getTime();;   // 时间差值间隔
            sectimeold = timeold / 1000
            secondsold = Math.floor(sectimeold);
            msPerDay = 24 * 60 * 60 * 1000
            e_daysold = timeold / msPerDay
            daysold = Math.floor(e_daysold);   //  相差天数 
            e_hrsold = (e_daysold - daysold) * 24;
            hrsold = Math.floor(e_hrsold); //  相差小时数
            e_minsold = (e_hrsold - hrsold) * 60;
            minsold = Math.floor((e_hrsold - hrsold) * 60);  //  相差分数
            seconds = Math.floor((e_minsold - minsold) * 60);  //  相差秒数
            span_dt_dt.innerHTML = daysold + " 天 " + hrsold + " 小时 " + minsold + " 分 " + seconds + " 秒 ";  // span_dt_dt 是一个Span标签 
        }
        show_date_time();
    </script>

```

# 创建数据Bean【S.Dot】   
```
  S.Dot{ Point({x【坐标】,y【坐标】,z【阴影 覆盖的权重】,a【alpha透明度】,h【高度】},
        e【移动的速度|与两点距离正比】 , 
		s【是否是之前字串的成员】 ,
		c 【点显示的颜色】, 
		t 【Point的 clone的对象(用于对比?)  S.Dot.prototype.clone()  】, 
		q【 当前需要移动到的 Point的集合 [PointA,PointB]】  }



```

# 在末尾调用 S.init() 来启动 Canvas的画面刷新 
```
倒数-3行：      S.init();  【JS的最后一行】

```

```

        var S = {  // 创建对象S    // 包含  init 方法
            init: function () { 
                S.Drawing.init('.canvas');  // 【1】 执行函数 S.Drawing.init  传入参数  '.canvas' 
				//  h5中新增了一个classList，原生js可以通过它来判断获取dom节点有无某个class 	
                //  add( String [, String] ) 方法。可以给元素添加类名，就像jquery中的 addClass() 
                document.body.classList.add('body--ready');
				console.log("zukgit2  simulate pre ");   
				// 【2】   画面显示的文字  以 |  依次显示      #countdown 3 应该是倒计时的数字   
                S.UI.simulate("ABC❤️DEF|#countdown 10|I love you！❤️|#time 3");   
				//  console.log("zukgit3  simulate back "); 
                S.Drawing.loop(function () {   //【3】   执行 S.Drawing.loop 函数 并传入 一个方法[ 该方法调用 S.Shape.render() 更新渲染界面的方法 ] 
                    S.Shape.render();  
                });
							
            }
        };


```


## S.Drawing.init('.canvas')

```
变量成员:
    var canvas,    // 画布
    context,      // 上下环境(包含系统变量)
    renderFn,    // 渲染的函数
	requestFrame,  // 每秒60次调用参数的函数
```
```
// S.Drawing.init 在调用时 会初始化它的函数内部定义的一些 私有变量   requestFrame 这个函数指针很重要

S.Drawing = (function () {   // Drawing 绘制方法
    var canvas,    // 画布
    context,      // 上下环境(包含系统变量)
    renderFn,    // 渲染的函数

// 最原始的你还可以使用window.setTimout()或者window.setInterval()通过不断更新元素的状态位置等来实现动画，
//前提是画面的更新频率要达到每秒60次才能让肉眼看到流畅的动画效果
// 现在又多了一种实现动画的方案，那就是还在草案当中的window.requestAnimationFrame()方法
// requestFrame 在 这里是一个函数指针  ， requestAnimationFrame(callback)  //callback为回调函数
// 如果 requestAnimationFrame  webkitRequestAnimationFrame ....msRequestAnimationFrame 都为空
// 那么就设置 requestFrame 为一个函数,该函数接收一个 callnack 并每 帧 1000/60 每帧的时间调用它

// 【 requestFrame 是一个函数指针 接收一个函数,并在 每帧大约 0.0166秒 调用  这个接收的函数 】
    requestFrame = window.requestAnimationFrame ||
        window.webkitRequestAnimationFrame ||
        window.mozRequestAnimationFrame ||
        window.oRequestAnimationFrame ||
        window.msRequestAnimationFrame ||
        function (callback) {      //  这里猜测是  S.Drawing 的构造器 接受一个 方法  然后每 0.01666 秒 调用一次
            window.setTimeout(callback, 1000 / 60);
        };
return {    // 函数返回的匿名对象?
    init: function (el) {   //    S.Drawing.init('.canvas')
	//  HTML 的DOM querySelector()方法可以不需要额外的jQuery等支持，也可以方便的获取DOM元素
	//  zukgit5  S.Drawing.init  el=.canvas
    //  console.log("zukgit5  S.Drawing.init  el="+ el); 
        canvas = document.querySelector(el); // el元素   这里 el ~=  '.canvas'
        context = canvas.getContext('2d');   //  通过 canvas 获取 context
        this.adjustCanvas();  //  【1 】 调整大小
        window.addEventListener('resize', function (e) {   //  【2】 加入 窗口大小变化执行事件
            S.Drawing.adjustCanvas();
        });
    },
......
}


```

###  this.adjustCanvas()
```

adjustCanvas: function () {  // 调整画布大小 始终比 窗口左右窄 100  上下小30
    canvas.width = window.innerWidth - 100;
    canvas.height = window.innerHeight - 30;
}



```


### window.addEventListener('resize' fn)
```
window.addEventListener('resize', function (e) {   //  【2】 加入 窗口大小变化调整大小的回调函数
    S.Drawing.adjustCanvas();
});


adjustCanvas: function () {  // 调整画布大小 始终比 窗口左右窄 100  上下小30
    canvas.width = window.innerWidth - 100;
    canvas.height = window.innerHeight - 30;
}

```


## S.UI.simulate("ABC❤️DEF|#countdown 10")
```
变量成员：

var interval,  // 需要间隔执行的函数
currentAction,  // 当前显示字符串
time,            // 时间
maxShapeSize = 30, // 最大显示的字符串长度
sequence = [],  // 对于 a|b|c|d 的分割数组 
cmd = '#';   // 以# 号开头的字符串是命令


```

```
// S.UI.simulate 是一个闭包 , 在该闭包中调用   S.UI.performAction方法
S.UI = (function () {

var interval,  // 时间间隔
currentAction,  // 当前显示字符串
time,            // 时间
maxShapeSize = 30, // 最大显示的字符串长度
sequence = [],  // 对于 a|b|c|d 的分割数组 
cmd = '#';   // 以 # 号开头的字符串是命令

return {
    simulate: function (action) {
        performAction(action); // 【1】  S.UI.simulate1 执行这里 这个 action 应该是 那个 中文字符串
    }
};

}());




```

###  performAction(action)
```
            function performAction(value) { //    S.UI.simulate 调用到此处
                var action,
                    value,   // value 就是输入的字符串  "ABC❤️DEF|#countdown 10"
                    current;
                //  sequence 是 以 | 为分隔的数组 
                sequence = typeof (value) === 'object' ? value : sequence.concat(value.split('|'));

                // 调用 timedAction 并传入一个 匿名的 func

**********************************************
            function getValue(value) { // 空格分隔的 第二个 对象
                return value && value.split(' ')[1];
            }
            function getAction(value) {  //  空格分隔的 第一个 对象
                value = value && value.split(' ')[0];
                return value && value[0] === cmd && value.substring(1);
            }
**********************************************
//-----------------------timedAction 函数调用开始-----------------------
                timedAction(function (index) {   // 【1】 timedAction的分析  index只有在倒计时的时候 使用到 
                    current = sequence.shift();   // shift() 表示从数组返回index=0的Item ,然后从数组中删除它
                    action = getAction(current);  // action等于 如果Item以#开头 那么 action就等于 #后的字符串 到空格前的字符, 如果不是命令#号开头 就等于false
                    value = getValue(current);   // value 等于当前字符串 空格分割后的第二个字符串 , 在 Commoand命令下 等于 命令的参数
					//  sequence= ABC❤️DEF,#countdown 10,I love you！❤️,#time 3
					//  current= ABC❤️DEF    action= false   value= undefined
					// current= #countdown 10     action= countdown    value= 10
					// current= I love you！❤️     action= false      value= love
					//  index= 1  index= 2   index= 3   index= 4  index=5 index= 1  index= 2
				// console.log("zukgit-performAction-timedAction  index= "+ index );
				//	console.log("zukgit-performAction   sequence= "+ sequence);
				//	console.log("zukgit-performAction   current= "+ current);
				//	console.log("zukgit-performAction   action= "+ action);
				//	console.log("zukgit-performAction   value= "+ value);
				

                    switch (action) {  // 如果当前的 字符串命令是 #countdown   #time  #rectangle  #circle 的命令开头 那么别的就是 显示的字符串 执行 default部分
                        case 'countdown':
                            value = parseInt(value) || 10;
                            value = value > 0 ? value : 10;
                            timedAction(function (index) {
                                if (index === 0) {
                                    if (sequence.length === 0) {
                                        S.Shape.switchShape(S.ShapeBuilder.letter(''));
                                    } else {
                                        performAction(sequence);
                                    }
                                } else {
                                    S.Shape.switchShape(S.ShapeBuilder.letter(index), true);
                                }
                            }, 1000, value, true);  // 一秒钟显示一个数字  value是数值 
                            break;
                        case 'rectangle':  // 标识什么？   应该是画布把
                            value = value && value.split('x');
                            value = (value && value.length === 2) ? value : [maxShapeSize, maxShapeSize / 2];
                            S.Shape.switchShape(S.ShapeBuilder.rectangle(Math.min(maxShapeSize, parseInt(value[0])), Math.min(maxShapeSize, parseInt(value[1]))));
                            break;
                        case 'circle':  // 圆 头像 
                            value = parseInt(value) || maxShapeSize;
                            value = Math.min(value, maxShapeSize);
                            S.Shape.switchShape(S.ShapeBuilder.circle(value));
                            break;
                        case 'time':  // 时间
                            var t = formatTime(new Date());
                            if (sequence.length > 0) {
                                S.Shape.switchShape(S.ShapeBuilder.letter(t));
                            } else {
                                timedAction(function () {
                                    t = formatTime(new Date());
                                    if (t !== time) {
                                        time = t;
                                        S.Shape.switchShape(S.ShapeBuilder.letter(time));
                                    }
                                }, 	1000);
                            }
                            break;
                        default:       //  如果当前字符串以 #号开头  但不是命令 那么显示字符串 'HeHe' , 把用户提供的当前字符串 current 传入给 switchShape
                             // S.ShapeBuilder.letter(x)  【2】                   
                             //  S.Shape.switchShape(x) 【3】
                            S.Shape.switchShape(S.ShapeBuilder.letter(current[0] === cmd ? 'HeHe' : current));
                    } 
                }, 3000, sequence.length);  // 3000表示每3秒钟timeout 重新执行一次   sequence.length 标识数组的长度  在这里标识 循环的次数 

//-----------------------timedAction 函数调用结束-----------------------

    }

```

### timedAction(fn,timeout,repeat)
```


            function timedAction(fn, delay, max, reverse) {  // 没理解含义 timedAction 
                clearInterval(interval);  // 清除interval的值 现在 = null 

// 如果 reverse 反转参数 为 true的话  currentAction = max  ， 否则其他情况下等于 1 
                currentAction = reverse ? max : 1;  
				// zukgit7  delay=2000   interval=undefined   currentAction=1   reverse=undefined  max=7
				// zukgit7  delay=1000   interval=2   currentAction=3   reverse=true  max=3
				// zukgit7  delay=2000   interval=11   currentAction=1   reverse=undefined  max=2
				// zukgit7  delay=1000   interval=15   currentAction=1   reverse=undefined  max=undefined 只执行了四次
				// console.log("zukgit7  delay="+delay+"   interval="+ interval+"   currentAction="+currentAction+"   reverse="+ reverse  + "  max="+ max);
                fn(currentAction);   //  这里只执行一次 显示 用户输入的第一个字符串 

//  如果当前的 max参数为空  或者    currentAction小于max    或者       reverse为true 并且 currentAction 大于0  那么重新设置interval的值
                if (!max || (!reverse && currentAction < max) || (reverse && currentAction > 0)) {  // 这里处理的是倒计时的逻辑 
                    interval = setInterval(function () {
                        currentAction = reverse ? currentAction - 1 : currentAction + 1;  //currentAction 会依次 增加 或者递减  依据 reverse的参数
// currentAction= 2(ABC❤️DEF)  【显示字符串从2开始,3,4,5,6,7(第6个字符串的index)】         9,8,7,6,5,4,3,2,1,0(倒计时)
				        console.log("zukgit8  currentAction= "+ currentAction );
                        fn(currentAction);  //  这里处理的是 字符串和 数字的逻辑  并 循环timeout 执行  // 所以这里始终是 第二次  currentAction=2 开始执行 
                        if ((!reverse && max && currentAction === max) || (reverse && currentAction === 0)) {  // 如果倒计时的 currentAction 为0  那么清除  interval
                            clearInterval(interval);
                        }
                    }, delay);   // interval 应该控制的是字母的显示的时间     每delay参数秒 执行     fn(currentAction);
                }
            }


```



### S.ShapeBuilder.letter(x)
```
S.ShapeBuilder = (function () {  // 图形构建  ?
    var gap = 5,  // 决定了 跳过的点的间隔   间隔越大 显示的点越小 ( 间接的决定了 点 的数量)
        shapeCanvas = document.createElement('canvas'),  // canvas的引用
        shapeContext = shapeCanvas.getContext('2d'),     // canvas的 上下文 context的引用 
        fontSize = 500,    // 显示的文字的大小
        fontFamily = 'Avenir, Helvetica Neue, Helvetica, Arial, sans-serif';  // 显示文字的字体




    letter: function (l) {    //    l 就是要显示的那个数字	或者  字符串   l = ABC❤️DEF
        var s = 0;
        setFontSize(fontSize);  // 设置字体大小
        s = Math.min(fontSize,
            (shapeCanvas.width / shapeContext.measureText(l).width) * 0.8 * fontSize,
            (shapeCanvas.height / fontSize) * (isNumber(l) ? 1 : 0.45) * fontSize); 
			// 三个值中 取 最小的那个值  赋值给 s  fontsize= 105.75  , 235 
			console.log("zukgit7   l= "+ l   +"    fontsize="+ s);
        setFontSize(s);  //  重新设置 经过计算得到的 FontSize 
        shapeContext.clearRect(0, 0, shapeCanvas.width, shapeCanvas.height);  // 清除 当前 canvas的 显示的内容
        shapeContext.fillText(l, shapeCanvas.width / 2, shapeCanvas.height / 2); // context.fillText(text,x,y,maxWidth); 在画布的中心点绘制 当前提供的字符串 
        return processCanvas(); 【1】  //  具体返回的 dots = []数组 的processCanvas()    , 主要提供当前画布显示的字符串所对象像素点的Dot的集合
    }

```

####  processCanvas()
```

//canvasContext.getImageData() 方法返回 ImageData 对象，该对象拷贝了画布指定矩形的像素数据。
//注意：ImageData 对象不是图像，它规定了画布上一个部分（矩形），并保存了该矩形内每个像素的信息。
//对于 ImageData 对象中的每个像素，都存在着四方面的信息，即 RGBA 值：
//R - 红色（0-255）
//G - 绿色（0-255）
//B - 蓝色（0-255）
//A - alpha 通道（0-255; 0 是透明的，255 是完全可见的）

// imgData.data.length =10000   10000/4 =  2500 个像素  50x50的图片的像素点个数是10000 
// 每个像素是四个值 R:data[0]  G:data[1]  B:data[2]  A:data[3]   最后一个像素是 data[9999]
//   width * height * 4 = data.length  , 50x50 每行处理的像素是50 个, 当处理第50个元素需要跳过 4*width=200个元素 才能访问到下一行的第一个元素


    var gap = 5,  // S.ShapeBuilder的参数 gap 定义了 每个显示白点 之间的间隔像素个数    越小白点月密集   白点越多

  function processCanvas() {
        var pixels = shapeContext.getImageData(0, 0, shapeCanvas.width, shapeCanvas.height).data;
        dots = [],  // Point的数组 
        pixels,
        x = 0,
        y = 0,
        fx = shapeCanvas.width,  // canvas的宽
        fy = shapeCanvas.height,  //  canvas 的 高
        w = 0,
        h = 0;
        for (var p = 0; p < pixels.length; p += (4 * gap)) {  
            if (pixels[p + 3] > 0) { //    A - alpha 通道（0-255; 0 是透明的，255 是完全可见的）  不是透明的话 
                dots.push(new S.Point({   // 4 标识的是一个像素的四个指标 RGBA , gap 标识 白球的间隔像素
                    x: x,
                    y: y
                }));  // 在当前循环计算出来的 x y 坐标  创建一个 Point 对象 
                w = x > w ? x : w;    // x与w  哪个大 赋值给 w (取得 所有像素中  x坐标值最大的那一个)
                h = y > h ? y : h;   // y与h  哪个大 赋值给 h  (取得 所有像素中  y坐标值最大的那一个)
                fx = x < fx ? x : fx;     // x 与 fx  哪个小  就把 值 赋值给 fx (取得 所有像素中  fx坐标值最小的那一个)
                fy = y < fy ? y : fy;   // y 与 fy  哪个小  就把 值 赋值给 fy (取得 所有像素中  fy坐标值最小的那一个)
            } 
            x += gap; // x = x + 13 , x 坐标自增13    x 坐标最大不能超过 shapeCanvas.width 
            if (x >= shapeCanvas.width) { //  如果 x的值 大于 canvas的宽度 
                x = 0;     // x 复位为0
                y += gap;  // y = y+13  y 坐标往下移动 13 个像素?    上下白点之间的差距也是 13 个像素  来到下一行
				//   p = p + 52 * shapeCanvas.width;  把 循环索引 增加 4*13*  shapeCanvas.width  貌似这些里面包含的是非正确数据 
                p += gap * 4 * shapeCanvas.width;   // 从一行可见像素的终点 来到下一行可见像素的起点
            }
        }
		 // w宽 = x最大点的值宽 + x最小点的值宽  
		 // h高 = y最大点的高 + y最小点的高
         // dots 中包含了 当前包含用户输入字符串的像素点,间隔为 gap个像素点的 所有像素点中 那些 透明度 不是0 的那些所有点的集合 
        return {dots: dots, w: w + fx, h: h + fy};
    }

```

<img src="//../zimage/selfstudy/js/js_canvas_1.jpg" />

### S.Shape.switchShape(x)

```

***********
  S.Shape.dots    S.Shape 定义的那个保存 Point的数组[]
***********



                switchShape: function (n, fast) {  // fast 未定义  或者 为0 
                    var size,
                        a = S.Drawing.getArea();  // 绘制的区域
                    width = n.w;
                    height = n.h;
                    compensate();  //   【1】 取得 中心点的那个 x y 值
					//   S.Shape.dots  当前保存点的数量 小于   processCanvas()绘制字符串计算出来的点的集合的话   
                    if (n.dots.length > dots.length) {  // 大于当前本地保存的点的个数    S.Shape.dots 初始位为0   n绘制的点 必须大于0
                        size = n.dots.length - dots.length;    
                        for (var d = 1; d <= size; d++) {   // 在中心位置 新增加 需要 补充的那些 不够的点 
                            dots.push(new S.Dot(a.w / 2, a.h / 2));   // 新补充 S.Dot
                        }
                    }
                    var d = 0,   // d 和 i  是 记录索引的变量
                        i = 0;
						
// Dot {					
// this.p= Point({  Point 的属性
// this.x: x, x坐标
// this.y: y,  y坐标
// this.z: 5,  阴影偏移值
// this.a: 1,  透明度
// this.h: 0    高
//     } // end_Point
// this.e = 0.07;   //初始化为 0.07      e = this.e * d;
// this.s = true;   boolean值
// this.c = new S.Color(255, 255, 255, this.p.a);
// this.t = this.clone();
// this.q = [];
// } // end_Dot
                    while (n.dots.length > 0) {
                        i = Math.floor(Math.random() * n.dots.length);  // 依据长度 随机 获取一个随机值 i
						// 在 调用   S.Shape.switchShape(S.ShapeBuilder.letter(index), true) 有时候 会传递 fast = true 进来
						// 如果 fast为 true  那么 把  0.25 赋值给 dot.e
						// 如果 fast为 fasle     那么判断 dot.s   [ dot.s=true dot.e=0.14 ]  [ dot.s=false dot.e=0.11 ] 
						
						//   dots[d].e 应该影响的是速度    该值和两点距离是正比  
						// e = 0.07  0.11  0.14  0.25 
//  fast为true时  设置点的.e= 0.25 
//  fast为fasle时 如果当前的点【为】字符串组成点 设置点的.e= 0.25 
//  fast为fasle时 如果当前的点【不为】字符串组成点  设置点的.e= 0.11
                     dots[d].e =  fast ? 0.25 : (dots[d].s ? 0.14 : 0.11);  
                        if (dots[d].s)  { // 如果当前的  S.Shape.dots[索引d] 当前点如果是构成字符串的点
                            dots[d].move(new S.Point({               // 【2】当前的点 发生变换  h 变为18   在 Dot.q[] 中加入 Dot.Point
                                z: Math.random() * 20 + 10,
                                a: Math.random(),
                                h: 18
                            }));
                        } else {   // 如果是  不是字符串组成SDot
						
						var temp_z = Math.random() * 5 + 5;
						var temp_h = fast ? 18 : 30;
                            dots[d].move(new S.Point({   // 变大的情况   没有发生位置的变化 
                                z: temp_z,
                                h: temp_h
                            }));
							// zukgit10   dots[d].z= 7.81175742488324    dots[d].h=30
							// zukgit10   dots[d].z= 5.826350179619853    dots[d].h=30
							//  一开始都是白色  慢慢有绿色      后续新增的点 是绿色
					    	// 	dots[d].c = new S.Color(0, 255, 0,255);  //  最后都在 数字的那些点里
						   //  console.log("zukgit10   dots[d].z= "+ temp_z   +"    dots[d].h="+ temp_h);
                        }
                        dots[d].s = true;   // 把所有的 d 索引 的字符串标识 flag s 改为 true
						//   如果没有该段代码  字母就不能正常显示   
                        dots[d].move(new S.Point({  //  把所有字符串的点 进行一个位置的移动 添加到指定的位置     a=1 不透明   
						// x , y 是一个这样的点, 它的中心在正中间的点   【x,y 是左上的那个点】 所以需要使得它的中心在原点
                         //   d=0的点 移动到 随机i的该点    加 中心点的偏移
                          // 没有 cx 和  cy  那么显示的位置就歪了
                            x: n.dots[i].x  + cx,  
                            y: n.dots[i].y   + cy, 	// x 点  y 点  满足 它的中心在原点
                            a: 1,
                            z: 5,
                            h: 0
                        }));   
						//  slice 方法可从已有的数组中返回选定的元素  把 随机产生的那个 数值i 从 dot集合中删除
                        n.dots = n.dots.slice(0, i).concat(n.dots.slice(i + 1)); 
						// d自增1   d = 1,2,3,4,5,6,7,8,9,10.....    一直到当前的 n.dots.length=0 
						//  n.dots.length=0  意味着当前的数组 S.Shape.dots[] 依据完全包含 ProcessCanvas()返回的点 并处理过了
                        d++;        // d 索引++  进行下一个 字符串点 S.Point点的数据的处理  
                    }
					// 对于那些 原有的  S.Shape.dots[dot1,dot2,dot3,dot4]  local.node[dot1,dot2] 对于原有的S.Shape.dots多出来的node  dot3,dot4的处理

                    for (var i = d; i < dots.length; i++) {  // 从d 的值 开始 遍历  S.Shape.dots[]
 // 如果遍历的 dot.s 为 true   那么说明这个点原来是组成字母的单元 , 现在已经不需要由这个点来构建 
// 那么把它的 标记值 改为 false  随机移动到一个位置 h = 0  高度变小
                        if (dots[i].s) {
                            dots[i].move(new S.Point({   //  变化大小
                                z: Math.random() * 20 + 10,
                                a: Math.random(),
                                h: 20
                            }));
                            dots[i].s = false;   // 把 dot.s 变为 false   貌似标记的是那些 是否是组成字母的单元
                            dots[i].e = 0.04;  // e   变为 0.04 
							
							  // dots[i].c = new S.Color(0, 255, 0,255); 那些不再需要
                            dots[i].move(new S.Point({  // 让他随机的移动
                                x: Math.random() * a.w,
                                y: Math.random() * a.h,
                                a: 0.3, //.4
                                z: Math.random() * 4,
                                h: 0
                            }));   //   高度为0? 
                        }
                    }
                }



```

#### compensate()
```

    function compensate() {
        var a = S.Drawing.getArea();    // 使得 x + cx = x(左上角坐标)  然后图形的位置 一直保持在 中心点
        cx = a.w / 2 - width / 2;      // 画布的宽/2 - 绘制图形的宽/2 获取中心点的 x 值   y值
        cy = a.h / 2 - height / 2;
    }

```

#### dots[d].move(new S.Point({x,y}）

```
在 Dot.q[] 中加入 Point
this.q = [];

    move: function (p, avoidStatic) {  // 移动  avoidStatic参数用来 控制逻辑 
	// 当 avoidStatic 为 true时   只有移动的距离大于1 才把需要移动的点 放入到 数组 this.q[]
        if (!avoidStatic || (avoidStatic && this.distanceTo(p) > 1)) {
            this.q.push(p);
        }
    }


```

##  S.Drawing.loop(function () {S.Shape.render()}
```

    S.Drawing.loop(function () {  //  执行 S.Drawing.loop 方法 并传入方法参数  S.Shape.render()
        S.Shape.render();   // 把 S.Shape.render() 函数 传入    S.Drawing.loop 中
    });



```

```

//  fn 是秒数          renderFn 是刷新Canvas的函数
//zukgit1   fn=44455.91700000023   
//renderFn=function () {  //  执行 S.Drawing.loop 方法 并传入方法参数  S.Shape.render()
//	S.Shape.render();   // 把 S.Shape.render() 函数 传入    S.Drawing.loop 中
//}


                loop: function (fn) { 
                    renderFn = !renderFn ? fn : renderFn;
                    this.clearFrame();  // 清除 画布 Canvas
                    renderFn();   // 【1】  执行 方法函数 这个方法函数应该是   S.Shape.render()  不断刷新 dot[] 数组
					
					// requestFrame 是一个函数?  call 表示执行这个函数  并传参数? 
                    requestFrame.call(window, this.loop.bind(this));  // 【2】requestFrame 是每帧都执行的this.loop函数的函数   每秒60帧 一直循环的源泉
                }


```

###  renderFn【S.Shape.render()】
```
【1】 renderFn 就是 S.Drawing.loop 提供的匿名函数
//renderFn=function () {  //  执行 S.Drawing.loop 方法 并传入方法参数  S.Shape.render()
//	S.Shape.render();   // 把 S.Shape.render() 函数 传入    S.Drawing.loop 中
//}


   S.Shape.render: function () {         
        for (var d = 0; d < dots.length; d++) {   //  遍历所有的 S.Shape.dots[] , 并调用每个 Point的 render() 方法
            dots[d].render();  // 【1】
		
        }
    }



```

#### Dot.render()
```
         Dot.render: function () {  //  每次的显示  粉刷 
                this._update();   // 【1】 执行每个点的 update
                this._draw(); // 【2】 执行每个点的 _draw()
            }

```

#####   this._update()
```
            _update: function () {  //  更新 ? 
			// this.t  是 最初的dot的样子? 
                if (this._moveTowards(this.t)) { // this.p = Point{} 和  this.t 对比?     this.t = this.clone();
                    var p = this.q.shift();     // 方法用于把数组的第一个元素从其中删除,并返回第一个元素的值 //  如果返回 需要移动 
                    if (p) {
//  = || ; 的含义  相当于这样			
//if(p.x){                
//return p.x;
//}else{
//return this.p.x;
//}


// 如果 point.x的x有值 那么就赋值给 this.t.x 否则就把this.p.x 赋值给this.t.x
//  相当于对 原始保存的 dot.t [this.clone()]  进行更新  方便下次比较
//  把    x   y   z   a  h  都赋值给 当前的 t 
                        this.t.x = p.x || this.p.x;  
                        this.t.y = p.y || this.p.y;
                        this.t.z = p.z || this.p.z;
                        this.t.a = p.a || this.p.a;
                        this.p.h = p.h || 0;
                    } else {  //  如果 从数组 q[] 得到的点 point 为空 
                        if (this.s) {  //  如果当dot 是 字母的组成成分的话 
						// Math.sin(x) x 的正玄值。返回值在 -1.0 到 1.0 之间；   X与Y 上下 调整 1 个像素?
                            this.p.x -= Math.sin(Math.random() * 3.142);  // Math.sin([0,3.142]);
                            this.p.y -= Math.sin(Math.random() * 3.142);
                        } else {  //  如果当dot  不是 字母的组成成分的话    往q[] 加入一个新的 Point  x 和 y 上下随机变动  当前  [-25,25]
                            this.move(new S.Point({
                                x: this.p.x + (Math.random() * 50) - 25,
                                y: this.p.y + (Math.random() * 50) - 25
                            }));
                        }
                    }
                }
                d = this.p.a - this.t.a;  // alpha的值 之间的差距
                this.p.a = Math.max(0.1, this.p.a - (d * 0.05)); // 在 0.1 与  this.p.a - (d * 0.05) 之间找那个 大的值 赋值给 this.p.a
                d = this.p.z - this.t.z;           // z 坐标 之间的差异
                this.p.z = Math.max(1, this.p.z - (d * 0.05));  // 1 与  this.p.a - (d * 0.05) 之间找那个 大的值  this.p.z
            },



```



#####  this._draw()
```

    Dot._draw: function () {  // 绘制的方法  this.c = color  , this.p =Dot.Point(x,y,z,a,h)
        this.c.a = this.p.a;  //  把 当前 Dot的 属性c (Colors)的alpha颜色 赋值给 dot.point 的alpha属性 
        S.Drawing.drawCircle(this.p, this.c);   //  以 this.c 指定的颜色 绘制 当前的点 this.p = Dot.Point(x,y,z,a,h)  画圆圈 
    },

```


### requestFrame.call(window, this.loop.bind(this))
```
// requestFrame 是每帧都执行的this.loop函数的函数   每秒60帧 一直循环的源泉
requestFrame.call(window, this.loop.bind(this)) ;

意味着 每 0.016 秒 就调用一次 this.loop 方法   . 1 秒钟 调用 60 次 

```