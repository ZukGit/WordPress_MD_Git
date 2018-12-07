# 首页 
https://android-review.googlesource.com

https://review.openstack.org/Documentation/user-search.html   【openstack 搜索释义】






# 搜索条件

```

status:open
-status:open

status:merged
-status:merged

status:abandoned
-status:abandoned

负号是什么意思?
```


# List标题
```


Subject【主题】	Status【状态】     Owner【责任人】	  Assignee【被指定人】	  
Repo【仓库】 	Branch【分支】	Updated	Size【上传大小】	 
AR【API-Review】   	A【Autosubmit】	   BCO【Build-Cop-Override】
CR【Code Review】	PR【Presubmit-Ready】	PV【Presubmit-Veritified】	V【Veritified】



```

# 搜索栏搜索

## Status【状态】可选值
负号: -  表示查询该状态的相反的状态

###  status:abandoned   (已经丢弃的提交)

```

status:abandoned
-status:abandoned

```


### status:closed  (merged状态 abandoned状态的集合) 
```
status:closed
-status:closed
```

###  status:merged  （已合入状态）
```
status:merged      
-status:merged
```

###  status:open (pending状态reviewed状态集合)

```
status:open
-status:open
```

###  status:pending （等待审核的状态）
```
status:pending
-status:pending
```

###  status:reviewed  （已审核待合入的状态）

```
status:reviewed
-status:reviewed
```

###  Merge Conflict
```
Merge Conflict 说明合入存在冲突 , 两个owner的修改冲突(同一个位置的不同修改)

```



## age:1week    
reviewed 审核后时长超过1周的提交List列表
```
age:1year
age:1mon
age:1week
age:1day
age:1hr
age:1min
age:1sec

s, sec, second, seconds
m, min, minute, minutes
h, hr, hour, hours
d, day, days
w, week, weeks (1 week is treated as 7 days)
mon, month, months (1 month is treated as 30 days)
y, year, years (1 year is treated as 365 days)

https://android-review.googlesource.com/q/age:1d,100
https://android-review.googlesource.com/q/age:1d,3000                // 100(第二页)  和 3000(第三十一页) 代表显示的起始索引


```