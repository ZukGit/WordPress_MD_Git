# android.mk 文件使用
https://www.jianshu.com/p/aaf44b513c63
```
初始化编译环境:     source build/envsetup.sh
指定编译目标:       lunch     user| userdebug | eng  .... 1..110
编译命令            make -j2
```
**envsetup.sh里面还帮我们动态增加了以下命令:**
<table><thead><tr>
<th>croot</th>
<th>切换到源码树的根目录</th></tr>
</thead>
<tbody>
<tr>
<td>m</td>
<td>在源码树的根目录执行 make</td>
</tr>
<tr>
<td>mm</td>
<td>Build 当前目录下的模块</td>
</tr>
<tr>
<td>mma</td>
<td>当前目录新增或删除文件后,可以用mma重新编译</td>
</tr>
<tr>
<td>mmm</td>
<td>Build 指定目录下的模块</td>
</tr>
<tr>
<td>cgrep</td>
<td>在所有 C/C++ 文件上执行 grep</td>
</tr>
<tr>
<td>jgrep</td>
<td>在所有 Java 文件上执行 grep</td>
</tr>
<tr>
<td>resgrep</td>
<td>在所有 res/*.xml 文件上执行 grep</td>
</tr>
<tr>
<td>godir</td>
<td>转到包含某个文件的目录路径</td>
</tr>
<tr>
<td>printconfig</td>
<td>显示当前 Build 的配置信息</td>
</tr>
<tr>
<td>add_lunch_combo</td>
<td>在 lunch 函数的菜单中添加一个条目</td>
</tr>
</tbody>
</table>
## 编译目录
```
/out/host/：该目录下包含了针对主机的 Android 开发工具的产物。即 SDK 中的各种工具，例如：emulator，adb，aapt 等。
/out/target/common/：该目录下包含了针对设备的共通的编译产物，主要是 Java 应用代码和 Java 库.
/out/target/product/<product_name>/：包含了针对特定设备的编译结果以及平台相关的 C/C++ 库和二进制文件。其中，<product_name>是具体目标设备的名称。 system.img也是在这下面


```

## 编译变量
<table>
<thead>
<tr>
<th>变量名</th>
<th>意义</th>
</tr>
</thead>
<tbody>
<tr>
<td>LOCAL_MODULE</td>
<td>LOCAL_MODULE变量必须定义，以标识你在Android.mk文件中描述的每个模块。名称必须是唯一的，而且不包含任何空格。注意编译系统会自动产生合适的前缀和后缀，换句话说，一个被命名为'foo'的共享库模块，将会生成'libfoo.so'文件。</td>
</tr>
<tr>
<td>LOCAL_SRC_FILES</td>
<td>当前模块包含的所有源码文件, 我们可以这么写<code>LOCAL_SRC_FILES := $(call all-subdir-java-files)</code>
</td>
</tr>
<tr>
<td>LOCAL_MODULE_TAGS</td>
<td>当前模块所包含的标签，一个模块可以包含多个标签。标签的值可能是 debug, eng, user，development 或者 optional。其中，optional 是默认标签。标签是提供给编译类型使用的。不同的编译类型会安装包含不同标签的模块,建议写成<code>LOCAL_MODULE_TAGS := optional</code>
</td>
</tr>
<tr>
<td>LOCAL_STATIC_JAVA_LIBRARIES</td>
<td>当前模块依赖的Java静态库,所谓静态库,即编译完会存在于你的module里面,成为其一部分</td>
</tr>
<tr>
<td>LOCAL_SHARED_LIBRARIES</td>
<td>当前模块在运行时依赖的动态库名, 即编译完不会存在于你的module里面</td>
</tr>
<tr>
<td>LOCAL_CERTIFICATE</td>
<td>签署当前应用的证书名称，比如platform, 如下<code>LOCAL_CERTIFICATE := platforms</code>
</td>
</tr>
<tr>
<td>LOCAL_SHARED_LIBRARIE</td>
<td>当前模块在运行时依赖的动态库名</td>
</tr>
<tr>
<td>LOCAL_PRELINK_MODULE</td>
<td>是否需要预连接处理(默认需要，用来做动态库优化)</td>
</tr>
<tr>
<td>LOCAL_REQUIRED_MODULES</td>
<td>指定模块运行所依赖的模块(模块安装时将会同步安装它所依赖的模块)</td>
</tr>
<tr>
<td>LOCAL_PROGUARD_ENABLED</td>
<td>enabled或者disabled 指定是否启动混淆</td>
</tr>
<tr>
<td>LOCAL_PROGUARD_FLAG_FILES</td>
<td>指定混淆配置 proguard.config</td>
</tr>
<tr>
<td>LOCAL_PACKAGE_NAM</td>
<td>当前 APK 应用的名称。</td>
</tr>
<tr>
<td>LOCAL_CFLAGS</td>
<td>提供给 C/C++ 编译器的额外编译参数, 比如指定所有warning成error, 指定优化等级这些, 比如: <code>LOCAL_CFLAGS := -Werror</code>
</td>
</tr>
<tr>
<td>LOCAL_C_INCLUDES</td>
<td>C/C++所需的头文件路径,比如:LOCAL_C_INCLUDES += $(JNI_H_INCLUDE) \  $(LOCAL_PATH) \ system/core/include  \ frameworks/base/include \ libnativehelper/include \</td>
</tr>
<tr>
<td>LOCAL_JAVA_LIBRARIES</td>
<td>编译java应用程序和库的时候指定包含的java类库，目前有core和framework两种, 多数情况下定义成<code>LOCAL_JAVA_LIBRARIES := core framework</code>注意LOCAL_JAVA_LIBRARIES不是必须的，而且编译APK时不允许定义(系统会自动添加)</td>
</tr>
<tr>
<td>LOCAL_REQUIRED_MODULES</td>
<td>指定模块运行所依赖的模块(模块安装时将会同步安装它所依赖的模块)</td>
</tr>
<tr>
<td>LOCAL_PREBUILT_LIBS</td>
<td>需要预编译的lib, 类似PREBUILT的还有很多,比如LOCAL_PREBUILT_JAVA_LIBRARIES, LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES</td>
</tr>
<tr>
<td>include $(BUILD_STATIC_LIBRARY)</td>
<td>表示编译成静态库</td>
</tr>
<tr>
<td>include $(BUILD_SHARED_LIBRARY)</td>
<td>表示编译成动态库。</td>
</tr>
<tr>
<td>include $(BUILD_EXECUTABLE)</td>
<td>表示编译成可执行程序</td>
</tr>
<tr>
<td>include $(BUILD_PACKAGE)</td>
<td>编译成APK</td>
</tr>
<tr>
<td>include $(BUILD_STATIC_JAVA_LIBRARY)</td>
<td>编译成Java静态库</td>
</tr>
<tr>
<td>include $(BUILD_MULTI_PREBUILT)</td>
<td>其实是会将prebuild的那些,还有required那些库拷到本地来编译</td>
</tr>
</tbody>
</table>

# 打印字符串

## $(info string)
```
$(info 'zukgit-begin')


$(info 'zukgit-end')
```



## $(info string)
```
$(warning 'zukgit-begin')
$(warning 'zukgit-end')

$(warning " LOCALPATH is $(LOCAL_PATH)")
$(warning " prebuilt_stdcxx_path is $(prebuilt_stdcxx_path)")
$(warning " uvc_util_src_files is $(uvc_util_src_files)")
$(warning " LOCAL_C_INCLUDES is $(LOCAL_C_INCLUDES)")
$(warning " LOCAL_SRC_FILES is $(LOCAL_SRC_FILES)")
$(warning " LOCAL_LDFLAGS is $(LOCAL_LDFLAGS)")
$(warning " LOCAL_SHARED_LIBRARIES is $(LOCAL_SHARED_LIBRARIES)")
$(warning " LOCAL_STATIC_LIBRARIES is $(LOCAL_STATIC_LIBRARIES)")

```

# 关键词

## $(LOCAL_PATH)
```
每个Android.mk文件必须以定义LOCAL_PATH为开始。 mk文件开头必须定义 LOCAL_PATH:= $(call my-dir)  变量LOCAL_PATH
它用于在开发tree中查找源文件。宏my-dir 则由Build System提供。返回包含Android.mk的目录路径。
引用该变量时 使用  $(LOCAL_PATH)

```

## %
```
%为Makefile规则通配符，一般用于规则描述，如
%.o:%c


```
## =
```
= 是最基本的赋值变量符号

VAR = “Hello World!”



“=”表示 make会将整个makefile展开后，再决定变量的值。也就是说，变量的值将会是整个makefile中最后被指定的值。看例子：

x = foo
y = $(x) bar
x = xyz

在上例中，y的值将会是 xyz bar ，而不是 foo bar 


```
## :=
```
:= 是覆盖之前的值
 LOCAL_PATH:= $(call my-dir)    //  覆盖之前的值
VAR =2019
VAR :=2020
VAR(2020) 实际值


 “:=” 表示变量的值决定于它在makefile中的位置，而不是整个makefile展开后的最终值

x := foo
y := $(x) bar
x := xyz

 在上例中，y的值将会是 foo bar ，而不是 xyz bar 了。

```
## ?=
```
?= 是如果没有被赋值过就赋予等号后面的值
VAR ?= “2019”
VAR(2019) 实际值
------------
VAR =2019
VAR ?= “2020”
VAR(2020) 实际值
```
## += 
```
+= 是添加等号后面的值
VAR := 2019
VAR += 2020
VAR（2019,2020）实际值
```

## call
```
Android MK构建系统中用于 调用系统提供的define宏 的关键字 可理解为函数关键字 
常见于以下命令 :
 $(LOCAL_PATH) := $( call my-dir ) 
include $(call all-makefiles-under,$(LOCAL_PATH));
include $(call all-subdir-makefiles,$(LOCAL_PATH));

include $(call my-dir)：获取当前文件夹路径。
include $(call all-java-files-under, <src>)：获取指定目录下的所有 Java 文件。
include $(call all-c-files-under, <src>)：获取指定目录下的所有 C 语言文件。
include $(call all-Iaidl-files-under, <src>) ：获取指定目录下的所有 AIDL 文件。
include $(call all-makefiles-under, <folder>)：获取指定目录下的所有 Make 文件。
include $(call intermediates-dir-for, <class>, <app_name>, <host or target>, <common?> )：获取 Build 输出的目标文件夹路径。


```



## all-subdir-makefiles
```
all-subdir-makefiles 是安卓MakeFile系统定义的宏 
用于命令
include $(call all-subdir-makefiles,$(LOCAL_PATH));
表示:    当前目录下没有需要编译的文件，请向子目录深入 告诉编译器继续向目录深处递归的一种规定写法

```

## all-makefiles-under
```
all-makefiles-under 是安卓MakeFile系统定义的宏 
用于命令
include $(call all-makefiles-under,$(LOCAL_PATH));
表示: 当前目录下有文件需要编译，而且在当前目录下还有子目录，子目录中也有文件需要编译 在编译完当前目录下的文件后，继续向子目录深入的语句

```


## LOCAL_CERTIFICATE := platform
```
如果一个应用程序想要使用系统的方法,那么就需要给这个应用程序签名,相当于授权。  给程序进行platform、media、shared签名的方法分别如下:



(1)platform签名: 
AndroidManifest.xml的manifest节点中添加 android:sharedUserId=”android.uid.system”, 
Android.mk中增加 LOCAL_CERTIFICATE := platform 

就代表使用platform来签名，这样的话这个apk就拥有了和system相同的签名，因为系统级别的签名也是使用的platform来签名，此时使用android:sharedUserId="android.uid.system"才有用！

(2)shared签名: 
AndroidManifest.xml的manifest节点中增加android:sharedUserId=”android.uid.shared”, 
Android.mk中增加LOCAL_CERTIFICATE := shared 

(3)media签名: 
AndroidManifest.xml的manifest节点中增加 android:sharedUserId=”android.media”, 
Android.mk中增加 LOCAL_CERTIFICATE := media 

(4) testkey 签名:

如果系统中的apk的android.mk中没有设置LOCAL_CERTIFICATE的值，就默认使用testkey


```
## LOCAL_MODULE:=
```

LOCAL_MODULE必须定义，以表示Android.mk中生成的模块名称( 除APK模块  因为APK模块有专门命名方式)。  名字必须唯一且不包含空格。

Build System会自动添加适当的前缀和后缀
例如，LOCAL_MODULE:=foo     include  $(BUILD_SHARED_LIBRARY)要产生动态库，则生成 libfoo.so
LOCAL_MODULE:=libfoo     include  $(BUILD_SHARED_LIBRARY)  则生成 libfoo.so   不再增加前缀

LOCAL_MODULE := wifi-service    include  $(BUILD_STATIC_JAVA_LIBRARY)

```
## LOCAL_MODULE_TAGS :=
```
LOCAL_MODULE_TAGS ：=user | eng | tests | optional
user: 指该模块只在user版本下才编译
eng: 指该模块只在eng版本下才编译
tests: 指该模块只在tests版本下才编译
optional:指该模块在所有版本下都编译
```
<img src="//../zimage/system/android/07_mk/1.jpg" />

## LOCAL_PACKAGE_NAME :=
```
LOCAL_PACKAGE_NAME 专门来命名APK模块  名称必须是唯一的且不包含空格
LOCAL_PACKAGE_NAME := Settings    include $(BUILD_PACKAGE)

```
## LOCAL_PATH := $(call my-dir)
```
每个Android.mk文件必须以定义LOCAL_PATH为开始。它用于在开发tree中查找源文件。

宏my-dir 则由Build System提供。返回包含Android.mk的目录路径。
LOCAL_PATH := $(call my-dir)   // 表示当前的mk文件路径 赋值给 LOCAL_PATH

```
## LOCAL_PRIVATE_PLATFORM_APIS := true|false
```
LOCAL_PRIVATE_PLATFORM_APIS := true
设置后，会使用sdk的hide的api來编译


在Android.mk中如果有
LOCAL_SDK_VERSION 这个编译配置，就会使编译的应用不能访问hide的api,
有时一些系统的class被import后编译时说找不到这个类，就是这个原因造成的。
LOCAL_SDK_VERSION := current  意思是编译时忽略系统隐藏类(@hide)

如果在系统源码修改中使用到了系统隐藏类(@hide),请将LOCAL_SDK_VERSION := current 删掉后编译出来的APK 才能使用 隐藏API
```

## LOCAL_PRIVILEGED_MODULE := true|false
```
对于Android系统应用，LOCAL_PRIVILEGED_MODULE 决定了其编译后的在ROM中的安装位置
false 或者 不设置，安装位置为 /system/app
true  安装位置为            /system/priv-app

build/base_rules.mk 中 有 LOCAL_PRIVILEGED_MODULE 的处理逻辑

 ifeq (true,$(LOCAL_PRIVILEGED_MODULE))
    install_path_var := $(install_path_var)_PRIVILEGED
  endif


```
## LOCAL_PROGUARD_FLAG_FILES := proguard.flags
```
1. 指定不需要混淆的native方法与变量可以加入到proguard.flags文件,在Android.mk添加选项 (解决引用三方库 而三方库不可以被混淆的情况)
LOCAL_PROGUARD_FLAG_FILES := proguard.flags

2. 禁止代码混淆，在Android.mk添加选项
LOCAL_PROGUARD_ENABLED := disabled


3. 不设置，默认该工程代码全部混淆
LOCAL_PROGUARD_ENABLED := full
```
## LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES :=
```
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES 用于集成第三方jar包

LOCAL_STATIC_JAVA_LIBRARIES := libbaidumapapi

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := libbaidumapapi:libs/libbaidumapapi.jar //引用名：jar包名    格式：别名:jar文件路径
注意：别名一定要与 LOCAL_STATIC_JAVA_LIBRARIES 里所取的别名一致，且不含.jar

include  $(BUILD_MULTI_PREBUILT)   // 以 BUILD_MULTI_PREBUILT 来引入 三方jar包

```

## LOCAL_STATIC_JAVA_LIBRARIES := \
```
LOCAL_STATIC_JAVA_LIBRARIES 标识当前需要引入的三方jar库 集合   ， 后面的库文件名  必须在LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES 中 定义

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := xsocket:lib/xSocket-2.8.14.jar \
　　　　jackson-mapper:lib/jackson-mapper-asl-1.6.2.jar \
　　　　logging:lib/commons-logging.jar \
　　　　jackson-core:lib/jackson-core-asl-1.6.2.jar \
　　　　javatar:lib/javatar-2.5.jar \
　　　　log4j:lib/log4j-1.2.15.jar

LOCAL_STATIC_JAVA_LIBRARIES := xsocket jackson-mapper logging jackson-core javatar log4j
include $(BUILD_MULTI_PREBUILT)

```


## LOCAL_SRC_FILES := \
```
LOCAL_SRC_FILES 当前需要编译的源文件列表


LOCAL_SRC_FILES := $(call all-java-files-under, src)


LOCAL_SRC_FILES := \
	jni/com_android_server_wifi_WifiNative.cpp \
	jni/jni_helper.cpp


LOCAL_SRC_FILES := $(call all-java-files-under, java) \
	$(call all-Iaidl-files-under, java) \
	$(call all-Iaidl-files-under, $(wificond_aidl_rel_path)) \
	$(call all-logtags-files-under, java)


```
### 示例
```
cpp文件全部位于android项目下的jni文件夹下,结构如下
 
    jni 
     |---1.cpp
     |---2.cpp
     |---Android.mk
     |---Application.mk
     |---ndk_test.cpp
     |---src    
     |    |---core
     |    |    |---core1.cpp
     |    |    |---core2.cpp
     |    |---src1.cpp


通常 LOCAL_SRC_FILES 写法
LOCAL_SRC_FILES := ndk_test.cpp \
                1.cpp \
                2.cpp \
                src/src1.cpp \
                src/src2.cpp \
                src/core/core1.cpp \
                src/core/core2.cpp


【简单写法 一】： 一句话引入单个目录(不包括子目录)下的所有cpp源文件

MY_CPP_LIST := $(wildcard $(LOCAL_PATH)/*.cpp)
MY_CPP_LIST += $(wildcard $(LOCAL_PATH)/src/*.cpp)
MY_CPP_LIST += $(wildcard $(LOCAL_PATH)/src/core/*.cpp)
LOCAL_SRC_FILES := $(MY_CPP_LIST:$(LOCAL_PATH)/%=%)

解释:
MY_CPP_LIST := $(wildcard $(LOCAL_PATH)/*.cpp)
这句话的意思是使用wildcard函数获取$(LOCAL_PATH)目录也就是jni目录下的所有后缀名为cpp的文件,并把结果放到变量MY_CPP_LIST里.
我们知道$(LOCAL_PATH)指的是当前Android.mk文件所在目录,所以通过这句话,
MY_CPP_LIST中的值应该是jni/1.cpp jni/2.cpp jni/ndk_test.cpp.
MY_CPP_LIST += $(wildcard $(LOCAL_PATH)/src/*.cpp), 获取jni/src目录下的源文件,并追加到变量MY_CPP_LIST里
MY_CPP_LIST += $(wildcard $(LOCAL_PATH)/src/core/*.cpp),同上,获取jni/src/core目录下的源文件
通过以上几步,得到MY_CPP_LIST中内容是
jni/1.cpp jni/2.cpp jni/ndk_test.cpp jni/src/src1.cpp jni/src/src2.cpp jni/src/core/core1.cpp jni/src/core/core2.cpp
LOCAL_SRC_FILES := $(MY_CPP_LIST:$(LOCAL_PATH)/%=%)
前面我们获取的文件都是以jni开头的,而真正编译所需要的文件都应该是直接从jni目录开始的,所以我们使用模式替换把所有文件名前面的jni/去掉
$(MY_CPP_LIST:$(LOCAL_PATH)/%=%) 就是 
1.cpp 2.cpp ndk_test.cpp jsrc/src1.cpp src/src2.cpp  src/core/core1.cpp src/core/core2.cpp

这里我解释一下$(MY_CPP_LIST:$(LOCAL_PATH)/%=%)的语法含义,
它的意思是对MY_CPP_LIST中每一项,应用冒号后面的规则,规则是什么呢?
规则是$(LOCAL_PATH)/%=%,意思是,查找所有$(LOCAL_PATH)/开头的项,并截取后面部分


#替换每一项中的 "$(LOCAL_PATH)/" 为 ""(空)
LOCAL_SRC_FILES := $(subst $(LOCAL_PATH)/, , $(MY_CPP_LIST))

#同模式替换,这里使用patsubst函数
LOCAL_SRC_FILES := $(patsubst $(LOCAL_PATH)/%, %, $(MY_CPP_LIST)) 


【简单写法 二】：引入单个目录(包括子目录)下的所有*.cpp和*.c源文件

MY_CPP_PATH  := $(LOCAL_PATH)/
My_All_Files := $(shell find $(MY_CPP_PATH)/.)
My_All_Files := $(My_All_Files:$(MY_CPP_PATH)/./%=$(MY_CPP_PATH)%)
MY_CPP_LIST  := $(filter %.cpp %.c,$(My_All_Files)) 
MY_CPP_LIST  := $(MY_CPP_LIST:$(LOCAL_PATH)/%=%)
 
LOCAL_SRC_FILES := $(MY_CPP_LIST)


【简单写法 二】： 引入多个目录(包括子目录)下的多个后缀名的源文件

# 扫描目录下的所有源文件
MY_FILES_PATH  :=  $(LOCAL_PATH) \
                   $(LOCAL_PATH)/../../Classes
 
MY_FILES_SUFFIX := %.cpp %.c %.cc
 
My_All_Files := $(foreach src_path,$(MY_FILES_PATH), $(shell find "$(src_path)" -type f) ) 
My_All_Files := $(My_All_Files:$(MY_CPP_PATH)/./%=$(MY_CPP_PATH)%)
MY_SRC_LIST  := $(filter $(MY_FILES_SUFFIX),$(My_All_Files)) 
MY_SRC_LIST  := $(MY_SRC_LIST:$(LOCAL_PATH)/%=%)

变量MY_FILES_PATH保存源文件所在目录
变量MY_FILES_SUFFIX保存源文件的后缀名

```
## LOCAL_STATIC_ANDROID_LIBRARIES := \
## LOCAL_USE_AAPT2 := true|false
## LOCAL_JAR_EXCLUDE_FILES :=

## LOCAL_AIDL_INCLUDES :=
```
LOCAL_AIDL_INCLUDES   用于保存当前 aidl文件的路径
LOCAL_AIDL_INCLUDES := $(LOCAL_PATH)/java $(wificond_aidl_path)

```
## LOCAL_REQUIRED_MODULES :
```
LOCAL_REQUIRED_MODULES  指定模块运行所依赖的模块(模块安装时将会同步安装它所依赖的模块)


LOCAL_REQUIRED_MODULES := SoundRecorder  
//表示该模块需要SoundRecorder模块，在系统全编译的时候就会将SoundRecorder放入到system.img中，  如果单独编译mm的话就不会关心SoundRecorder

LOCAL_REQUIRED_MODULES的作用当编译整个android源码时，如果这个模块在编译路径中，
则会自动编译 SoundRecorder，并且打包到system.img。
如果不是编译整个源码，只是mm。则不会编译 SoundRecorder 直接使用生成相应的文件。　所以这个时候可能需要去使用mma 去编译。


整机开发集成64位.so库生效方法

集成需要在Android.mk文件下添加如下几个选项
LOCAL_SHARED_LIBRARIES := libsdk
LOCAL_REQUIRED_MODULES := libsdk
LOCAL_PREBUILT_LIBS := \
                libsdk:libs/arm64-v8a/libsdk.so

验证是否生效：
编译生成目录：
     out/target/product/project_name/system/lib64/libsdk.so

更新到手机对应目录
    /system/lib64/libsdk.so


```
## LOCAL_DEX_PREOPT_APP_IMAGE := false
## LOCAL_DEX_PREOPT_GENERATE_PROFILE := true
## LOCAL_DEX_PREOPT_PROFILE_CLASS_LISTING := 
## LOCAL_ERROR_PRONE_FLAGS := -Xep:CheckReturnValue:ERROR
## LOCAL_CFLAGS +=
```
LOCAL_CFLAGS += -Wall -Werror -Wextra -Wno-unused-parameter -Wno-unused-function \
                -Wunused-variable -Winit-self -Wwrite-strings -Wshadow



LOCAL_CFLAGS 表示用于 C 编译器的选项
CXXFLAGS     表示用于 C++ 编译器的选项。
这两个变量实际上涵盖了编译和汇编两个步骤。


LOCAL_CFLAGS += -DXXX   相当于在所有源文件中增加一个宏定义#define XXX
LOCAL_CFLAGS += -Wxxx    // 打开警告开关

编译出现警告性错误unused-but-set-variable，变量定义但没有使用
解决方法:
 增加CFLAGS 或CPPFLAGS参数如下：
  LOCAL_CFLAGS += -Wno-unused-parameter -Wno-unused-function

LOCAL_CFLAGS参数说明
1、-Wall
是打开警告开关

2、-O
代表默认优化，可选：-O0不优化，-O1低级优化，-O2中级优化，-O3高级优化，-Os代码空间优化

3、-g
是生成调试信息，生成的可执行文件具有和源代码关联的可调试的信息

4、-fopenmp
OpenMp是由OpenMP Architecture Review Board牵头提出的，并已被广泛接受的，用于共享内存并行系统的多处理器程序设计的一套指导性的编译处理方案(Compiler Directive)。OpenMP支持的编程语言包括C语言、C++和Fortran；而支持OpenMp的编译器包括Sun Compiler，GNU Compiler和Intel Compiler等。OpenMp提供了对并行算法的高层的抽象描述，程序员通过在源代码中加入专用的pragma来指明自己的意图，由此编译器可以自动将程序进行并行化，并在必要之处加入同步互斥以及通信。当选择忽略这些pragma，或者编译器不支持OpenMp时，程序又可退化为通常的程序(一般为串行)，代码仍然可以正常运作，只是不能利用多线程来加速程序执行。

5、-D
增加全局宏定义

6、-ffast-math  
浮点优化选项 -ffast-math：极大地提高浮点运算速度

7、-mfloat-abi=softfp 浮点运算
```
<img src="//../zimage/system/android/07_mk/2.jpg" />

## LOCAL_C_INCLUDES += \
```
LOCAL_C_INCLUDES += \
	$(JNI_H_INCLUDE) \


LOCAL_C_INCLUDES 来设置头文件的include目录 


```
## ifeq(x,x)  endif
```
判断相等语句(形式1)：

         ifeq ($（变量名）， 变量值 )
           ........
         else ifeq ($(..), ..)
           .........
         else
           .........
         endif


判断相等语句(形式2 与 &&)：
        ifeq ($(VALUE1)_$(VALUE2), V1_V2)    ### 当然中间的下划线 "_" 可以用其他字符代替  
               do something....
        endif


判断相等语句(形式3  或 || )：
         ifneq ($(findstring $(VALUE1)$(VALUE2),  V1  V2),)
            do something...
         endif

  if( VALUE1 == V1 || VALUE2 == V2 ) {...} 可以用findstring函数做如下变通实现：
   #如果VALUE1或者VALUE2为V1或V2，则findstring 不会返回空。

```
## ifneq (x,x) endif

```
判断相等语句(形式1)：

         ifneq ($（变量名）， 变量值 )
           ........
         else ifneq ($(..), ..)
           .........
         else
           .........
         endif


```

## include、-include、sinclude的区别
```

include、-include、sinclude的区别

如果指示符“include”指定的文件不是以斜线的绝对路径开始（绝对路径，如/usr/src/Makefile...），而且当前目录下也不存在此文件；
make将根据文件名试图在以下几个目录下查找：
首先，查找使用命令行选项“-I”或者“--include-dir”指定的目录，如果找到指定的文件，则使用这个文件；
否则继续依此搜索以下几个目录（如果其存在）：“/usr/gnu/include”、“/usr/local/include”和“/usr/include”。

当在这些目录下都没有找到“include”指定的文件时，make将会提示一个包含文件未找到的告警提示，但是不会立刻退出。
而是继续处理Makefile的后续内容。当完成读取整个Makefile后，make将试图使用规则来创建通过指示符“include”指定的但未找到的文件，
当不能创建它时（没有创建这个文件的规则），make将提示致命错误并退出。会输出类似如下错误提示：

Makefile:错误的行数：未找到文件名：提示信息（No such file or directory）
Make：*** No rule to make target ‘’. Stop



使用 include 关键字可以把别的 Makefile 包含进来，这很像 C 语言的#include，被包含的文件会原模原样的放在当前文件的包含位置。


通常我们在Makefile中可使用“-include”来代替“include”，来忽略由于包含文件不存在或者无法创建时的错误提示（“-”的意思是告诉make，忽略此操作的错误。make继续执行）
-include FILENAMES...
使用这种方式时，当所要包含的文件不存在时不会有错误提示、make也不会退出；除此之外，和第一种方式效果相同。



【 两种方式的比较 】：
使用“include FILENAMES...”，make程序处理时，如果“FILENAMES”列表中的任何一个文件不能正常读取而且不存在一个创建此文件的规则时make程序将会提示错误并退出。

使用“-include FILENAMES...”的情况是，当所包含的文件不存在或者不存在一个规则去创建它，make程序会继续执行，只有真正由于不能正确完成终极目标的重建时
（某些必需的目标无法在当前已读取的makefile文件内容中找到正确的重建规则），才会提示致命错误并退出。

为了和其它的make程序进行兼容。也可以使用“sinclude”来代替“-include”


```

```
Makefile 中的 include (依赖文件) 的执行过程


示例代码：定义了一个模式规则，即如何由.c文件生成.d文件的规则
 SRCS:$(wildcard *.c)
    DEPS=$(SRCS:*.c=*.d)           // 把SRCS中的所有字符串 符合*.c 规则的 都转换为 *.d 字符串 然后存储在 DEPS
    .....

    include $(DEPS)
    %.d:%.c                         // 使得每个.c文件生成对应.d文件     规则就是  gcc -MM $< > $@
        gcc -MM $< > $@             #注释:$^:表示所有的依赖文件 $@:表示目标文件


 - $@ : 表示一个规则中的目标.当规则中有多个目标时，$@ 所指的是其中任何造成规则的命令运行的目标
 - $^ : 表示规则中的所有依赖项
 - $< : 表示规则中的第一个依赖项


疑问 : 若是首次 make，目录下不存在 xxx.d 文件，同时 xxx.d 文件并不是哪个规则的依赖文件，所以   %.d:%.c   这条模式规则应该不会执行，可实际上执行了，因为目录下生成了 xxx.d 文件。
.d 文件  用gcc编译.c文件时 用来供gdb调试的文件

结果 : 经过测试，发现首次 make 时，当执行到 include $(DEPS) 的时候，终端在报告了如下警告: 
makefile:6: xxx.d: No such file or dirctory 
之后，会默认在 makefile 中去找以 xxx.d 为目标的规则并执行，若该规则也没发现，便会报错并中止执行。

```


## include $(CLEAR_VARS)
```
清理（可能由其他模块设置过的）编译环境中用到的变量

```
## include  $(BUILD_EXECUTABLE)
## include  $(BUILD_PACKAGE) 
## include  $(BUILD_STATIC_JAVA_LIBRARY)
## include  $(BUILD_STATIC_LIBRARY)
## include  $(BUILD_SHARED_LIBRARY)
## include  $(BUILD_MULTI_PREBUILT)
## include $(call all-makefiles-under,$(LOCAL_PATH))
## include $(call all-subdir-makefiles,$(LOCAL_PATH))
## 函数
### filter
### subst
### wildcard

# 编译模板

## 编译应用 apk
```
LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_STATIC_JAVA_LIBRARIES := gson2.2 aquery v3dcore asupport socketio
LOCAL_SRC_FILES := $(call all-java-files-under, src) \
                   src/com/letv/t2/launcher/aidl/service/LiveLayerStatusBinderInterface.aidl \
                   src/com/letv/t2/launcher/aidl/service/BarrageObserver.aidl \
# Add overlay folder.
LOCAL_PACKAGE_OVERLAYS := overlay
LOCAL_PACKAGE_NAME := Settings           #【编译的包名 Settings】
LOCAL_CERTIFICATE := platform
ifeq ($(findstring full_mstarnapoli,$(TARGET_PRODUCT)),full_mstarnapoli)
    LOCAL_JAVA_LIBRARIES := com.mstar.android
endif
include $(CLEAR_VARS)
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := gson2.2:libs/gson-2.2.2.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += aquery:libs/android-query.0.24.3.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += v3dcore:libs/V3DCore.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += asupport:libs/android-support-v4.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += socketio:libs/android-websockets.jar
include $(BUILD_MULTI_PREBUILT)
include $(BUILD_PACKAGE)             # 【 编译成APK 】 Settings.apk


```

## 编译JAVA库模板 jar
```
LOCAL_PATH := $(call my-dir)  
include $(CLEAR_VARS)  
# Build all java files in the java subdirectory  
LOCAL_SRC_FILES := $(call all-subdir-java-files)  
# Any libraries that this library depends on  
LOCAL_JAVA_LIBRARIES := android.test.runner  
# The name of the jar file to create  
LOCAL_MODULE := sample     # 【编译的jar包名 sample 】
# Build a static jar file.  
include $(BUILD_STATIC_JAVA_LIBRARY)     # 【 编译成jar 】 sample.jar


```

## 编译C，C++应用程序  bin
```
LOCAL_PATH := $(call my-dir)  
#include $(CLEAR_VARS)  
LOCAL_SRC_FILES := main.c  
LOCAL_MODULE := test_exe  # 【编译的bin执行文件名称  test_exe 】
#LOCAL_C_INCLUDES :=  
#LOCAL_STATIC_LIBRARIES :=  
#LOCAL_SHARED_LIBRARIES :=  
include $(BUILD_EXECUTABLE)   # 【 编译成 bin 】 test_exe.bin

```


## 编译C，C++静态库  静态库（archive）.a
```
LOCAL_PATH := $(call my-dir)  
include $(CLEAR_VARS)  
LOCAL_SRC_FILES :=  
helloworld.c  
LOCAL_MODULE:= libtest_static      # 【编译的 .a 静态库库文件名称  libtest_static 】
#LOCAL_C_INCLUDES :=  
#LOCAL_STATIC_LIBRARIES :=  
#LOCAL_SHARED_LIBRARIES :=  
include $(BUILD_STATIC_LIBRARY)   # 【 编译成 .a 】 libtest_static.a

```

## 编译C，C++动态库 so 文件
```
LOCAL_PATH := $(call my-dir)  
include $(CLEAR_VARS)  
LOCAL_SRC_FILES := helloworld.c  
LOCAL_MODULE := libtest_shared      # 【编译的 .so 静态库库文件名称  libtest_static 】
TARGET_PRELINK_MODULES := false  
#LOCAL_C_INCLUDES :=  
#LOCAL_STATIC_LIBRARIES :=  
#LOCAL_SHARED_LIBRARIES :=  
include $(BUILD_SHARED_LIBRARY)



```
## 生成模块的目标文件夹
```
1）APK程序：XXX_intermediates 
2）JAVA库程序：XXX_intermediates  
3）CC++可执行程序：XXX_intermediates 
4）CC++静态库： XXX_static_intermediates 
5）CC++动态库： XXX_shared_intermediates

```

# 加载第三方应用和类库


## 集成静态库  .a
```
LOCAL_PATH := $(call my-dir) 
include $(CLEAR_VARS) 
LOCAL_PREBUILT_LIBS := libA.a libB.a          
LOCAL_STATIC_LIBRARIES := libA libB  
include $(BUILD_MULTI_PREBUILT)

```

## 集成静态jar包  .jar
```
include $(CLEAR_VARS)
 
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES :=libbaidumapapi:libs/baidumapapi.jar
LOCAL_PREBUILT_LIBS :=libBMapApiEngine_v1_3_1:libs/armeabi/libBMapApiEngine_v1_3_1.so
LOCAL_MODULE_TAGS := optional
include $(BUILD_MULTI_PREBUILT)
 
# Use the following include to make our testapk.
include $(callall-makefiles-under,$(LOCAL_PATH))

```