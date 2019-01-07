# blueprint背景介绍

```
随着android工程越来越大，包含的module越来越多，以makefile组织的项目编译花费的时间越来越多。
谷歌在7.0开始引入了ninja进行编译系统的组织。相对于make来说Ninja 在大的项目管理中【速度】和【并行】方面有突出的优势，
因此谷歌采用了Ninja 来取代之前使用的 make。

Android 7.0 之后再很多地方出现了Android.bp的文件，bp = BluePrint缩写，
本文主要是介绍其由来和简单的语法和其使用方法，以及与其相关的Blueprint 和 Soong。

Android 7.0之后希望用Android.bp替换Android.mk，bp简单的配置更方便Ninja 文件的产生，而Blueprint和Soong 就此产生

Android 利用Blueprint和Soong 来解析bp 文件，经过最终转换为 ninja files

Blueprint和Soong都是由Go语言写的项目   https://golang.org/#      // go 语言在线编辑
从Android Nougat开始，prebuilts/go/ 目录下新增了Golang所需的运行环境，在编译时使用
http://androidxref.com/9.0.0_r3/xref/prebuilts/go/


kati是Google专门为了Android而开发的一个小项目，基于Golang和C++。 目的是为了把Android中的Makefile，转换成Ninja文件


```

<img src="//../zimage/system/android/08_blueprint/1.jpg"/>

## blueprint 
```
/build/blueprint/

http://androidxref.com/9.0.0_r3/xref/build/blueprint/

https://github.com/google/blueprint    // blueprint git开源仓库

Android.bp文件设计得非常简洁。没有条件或控制流语言（任何复杂性都在用Go编写的构建逻辑中被处理）

详细的Android.bp 的描述文档 :    http://note.qidong.name/demo/soong_build/

Android.bp，是用来替换Android.mk的配置文件。 它使用Blueprint框架来解析，最终转换成Ninja文件

与Android.mk不同的是，Android.bp是纯粹的配置文件，不包含分支、循环等流程控制，也不能做算数、逻辑运算。 
与此同时，Ninja文件也是纯粹的配置文件，不包含分支、循环等流程控制，也不能做算数、逻辑运算。 
这就产生了一些新的问题与需求——在Android项目上进行选择编译、解析配置、转换成Ninja等——Soong应运而生




READ.MD 
Blueprint Build System
======================
[![Build Status](https://travis-ci.org/google/blueprint.svg?branch=master)](https://travis-ci.org/google/blueprint) 

Blueprint is a meta-build system that reads in Blueprints files that describe
modules that need to be built, and produces a
[Ninja](https://ninja-build.org/) manifest describing the commands that
need to be run and their dependencies.  Where most build systems use built-in
rules or a domain-specific language to describe the logic for converting module
descriptions to build rules, Blueprint delegates this to per-project build
logic written in Go. 【Blueprint将此委托给每个项目构建 用Go编写的逻辑】
 For large, heterogenous projects this allows the inherent
complexity of the build logic to be maintained in a high-level language, while
still allowing simple changes to individual modules by modifying easy to
understand Blueprints files.



```
### blueprint语法
```
Blueprint文件的语法比较简单，毕竟只是配置文件

1.模块    Android.bp文件中的模块以模块类型开头，后跟一组name: value格式的属性：
cc_binary {
    name: "gzip",
    srcs: ["src/test/minigzip.c"],
    shared_libs: ["libz"],
    stl: "none",
}
每个模块必须有name属性，且其值在所有Android.bp文件中必须是唯一的。

2.





```

### bp模块类型属性列表
http://note.qidong.name/
```
	android_app  【 index = 1 】
	art_cc_binary
	art_cc_defaults
	art_cc_library
	art_cc_test
	art_cc_test_library
	art_debug_defaults
	art_global_defaults
	blueprint_go_binary
	bootstrap_core_go_binary
	bootstrap_go_binary
	bootstrap_go_package
	cc_benchmark
	cc_benchmark_host
	cc_binary
	cc_binary_host
	cc_defaults
	cc_library
	cc_library_headers
	cc_library_host_shared
	cc_library_host_static
	cc_library_shared
	cc_library_static
	cc_object
	cc_prebuilt_binary
	cc_prebuilt_library_shared
	cc_prebuilt_library_static
	cc_test
	cc_test_host
	cc_test_library
	clang_binary_host
	clang_tblgen
	filegroup
	fluoride_defaults
	force_build_llvm_components_defaults
	genrule
	gensrcs
	hal2vts
	java_binary
	java_binary_host
	java_library
	java_library_host
	java_library_static
	llndk_library
	llvm_defaults
	llvm_tblgen
	ndk_headers
	ndk_library
	ndk_prebuilt_library
	ndk_prebuilt_object
	ndk_prebuilt_shared_stl
	ndk_prebuilt_static_stl
	phony
	prebuilt_java_library
	prebuilt_sdk
	preprocessed_ndk_headers
	toolchain_library  【 index = 57 】



```

## Soong 
```
/build/soong/

http://androidxref.com/9.0.0_r3/xref/build/soong/

Soong是以前Android基于make的编译系统的替代品。
它以Android.bp文件替代Android.mk，Android.bp文件用类似JSON的简洁声明来描述需要构建的模块


与Android.mk不同的是，Android.bp是纯粹的配置文件，不包含分支、循环等流程控制，也不能做算数、逻辑运算。 
与此同时，Ninja文件也是纯粹的配置文件，不包含分支、循环等流程控制，也不能做算数、逻辑运算。 
这就产生了一些新的问题与需求——在Android项目上进行选择编译、解析配置、转换成Ninja等————Soong应运而生。

Soong  主要用于 选择编译、解析配置  然后 配合 blueprint框架生成  ninja 文件。

Soong 其实就相当于Makefile编译系统的核心，即build/make/core/下面的内容
http://androidxref.com/9.0.0_r3/xref/build/make/core/

Soong还会编译产生一个androidmk命令，可以手动把Android.mk转换成Android.bp。 这只对无选择、循环等复杂流程控制的Android.mk生效
/out/soong/host/linux-86/bin/androidmk
/out/soong/host/linux-86/bin/bpfmt     用于格式化format bp文件

READ.MD
# Soong

Soong is the replacement for the old Android make-based build system.  It
replaces Android.mk files with Android.bp files, which are JSON-like simple
declarative descriptions of modules to build.

使用 bp 取代 mk , bp 文件类似 json格式的模块构建描述
## Android.bp file format

By design, Android.bp files are very simple.  
There are no conditionals or control flow statements 【没有条件流语句和控制语句】
 - any complexity is handled in build logic written in Go.  【 在用Go编写的构建逻辑中处理任何复杂性 】
 - The syntax and semantics of Android.bp files are intentionally similar
to [Bazel BUILD files](https://www.bazel.io/versions/master/docs/be/overview.html)
when possible.

### Modules

A module in an Android.bp file starts with a module type, followed by a set of
properties in `name: value,` format:

```
cc_binary {
    name: "gzip",
    srcs: ["src/test/minigzip.c"],
    shared_libs: ["libz"],
    stl: "none",
}
```

Every module must have a `name` property, and the value must be unique across all Android.bp files.
每个模块必须有一个“name”属性，该值在所有Android的blueprint构建系统中必须是唯一的

For a list of valid module types and their properties see
[$OUT_DIR/soong/.bootstrap/docs/soong_build.html](https://go/Android.bp).
/out/soong/docs/soong_build.html
```

### Soong 编译
```
/build/soong/soong.bash    启用Soong以后，在Android编译最开始的准备阶段，会执行build/soong/soong.bash进行环境准备
Soong是与Android强关联的一个项目，  而Blueprint则相对比较独立，可以单独编译、使用


```
### Soong处理bp后生成的文件
```
soong处理后的bp文件生成 
1. /out/soong/build.ninja     256万行   450MB大小     尼玛这么大   所有 .bp 文件生成的 ninja 文件
2. /out/soong/Android-xxxproduct.mk     31万行 也是个狠角色     这个文件是编译完成后模块的安装脚本，负责将模块安装到对应位置
3. /out/build-xxxxproduct.ninja     /out/build-aosp_arm.ninja  154万行  650MB 更狠角色 所有 .mk 文件生成的ninja 文件  


4. /out/soong/.minibootstrap/build.ninja       5行    主要是用来编译blueprint和生成   /out/soong/.bootstrap/build.ninja
		bootstrapBuildDir = out/soong
		topFile = ./Android.bp
		extraArgs =  -t -l out/.module_paths/Android.bp.list
		builddir = out
		include ./build/blueprint/bootstrap/build.ninja

5. /out/soong/.bootstrap/build.ninja           4000 行    主要是生成 soong相关工具和 /out/soong/build.ninja文件
```

<img src="//../zimage/system/android/08_blueprint/2.jpg"/>


## kati
```
/build/kati/
https://github.com/google/kati                        //  谷歌 kati 开源项目
http://androidxref.com/9.0.0_r3/xref/build/kati/

apt install ninja-build
apt install gcc
apt install g++
kati是Google专门为了Android而开发的一个小项目，基于Golang和C++。 目的是为了把Android中的Makefile，转换成Ninja文件

git clone https://github.com/google/kati.git
在Git库中，可以用make来直接编译。
 编译完成后，目录下会出现ckati这个可执行文件。
 产生ckati后，通过执行【 ./m2n 】，可以把Makefile转换成build.ninja文件。 接下来，可以通过执行ninja来再次编译
在Android项目中，这个Git库自带Android.bp文件，可以作为一个模块自动跟随项目一起编译。 
也可以在项目路径中，执行mm单独编译。 编译产物主要是ckati，会被安装到项目的out/host/linux-x86/bin/ckati，作为编译过程中主机环境的一部分
在Android项目中，ckati会在编译过程中，自动被使用，无需操心。

单独使用时，在包含Makefile的目录下，执行ckati，效果与make基本相同。 
执行【 ckati --ninja 】，可以根据Makefile生成build.ninja文件，并且附带env.sh和ninja.sh。
通过env.sh来配置环境   初始化为空文件
通过执行./ninja.sh来启动Ninja、使用build.ninja编译

 生成的ninja.sh文件，主要内容如下
. ./env.sh
exec ninja -f ./build.ninja "$@"


步骤:
【0】 apt install gcc     apt install g++    apt install ninja-build     
【1】 git clone https://github.com/google/kati.git
【2】 make         // 生成  ckati   
【3】./m2n         // 使用kati 把 makefile 转为 ninja文件  并附带     env.sh    ninja.sh

【4】./ninja.sh    // 使用 ninja-build  来对  ninja文件进行处理   再次生成 ckati   但是是通过 ninja
【5】 ckati --ninja  // 单独使用 把makefile 转为 ninj  在   testcase 目录中 

kati是一个基于Makefile来生成ninja.build的小项目。 
它是Google专为Android而开发，用来修正Android项目创建之初的错误——使用Makefile来做一个超复杂的编译构建系统
在编译过程中，kati负责把既有的Makefile、Android.mk，转换成Ninja文件
在Android 7.0中，它独挑大梁。 在Android 8.0以后，它与Soong一起，成为Ninja文件的两大来源

在单独使用时，它对普通的小项目还能勉强生效。 面对复杂的、多嵌套的Makefile时，它往往无法支持，会出现各种各样的问题。
 当然，也可以理解为，它只为Android而设计。
所以 不推荐在Android以外的任何项目中使用kati
```
## ninja
```
https://ninja-build.org/
https://github.com/ninja-build/ninja

Ninja是一个专注于速度的小型构建系统。
它与其他构建系统的不同之处在于两个主要方面:
1. 目的是让更高级别的构建系统生成其输入文件，
2. 目的是尽可能快地运行构建。
3. 专注于处理 ninja 配置文件   而该文件由 soong  kati 项目 产生 

使用ninja构建系统为什么还需要一个其他的构建系统?

在其他构建系统使用的语言是高级语言，Ninja的目标语言是构建汇编器。

Ninja构建文件是人类可读的，但是手工编写并不方便
Ninja build files are human-readable but not especially convenient to write by hand


```
### build.ninja.html  ninja自构建例子
```
https://ninja-build.org/build.ninja.html


# This file is used to build ninja itself.
# It is generated by configure.py.

ninja_required_version = 1.3

# The arguments passed to configure.py, for rerunning it.
configure_args = 

root = .
builddir = build
cxx = g++
ar = ar
cflags = -g -Wall -Wextra -Wno-deprecated -Wno-missing-field-initializers $
    -Wno-unused-parameter -fno-rtti -fno-exceptions -fvisibility=hidden $
    -pipe '-DNINJA_PYTHON="python"' -O2 -DNDEBUG -DUSE_PPOLL $
    -DNINJA_HAVE_BROWSE -I.
ldflags = -L$builddir

rule cxx
  command = $cxx -MMD -MT $out -MF $out.d $cflags -c $in -o $out
  description = CXX $out
  depfile = $out.d
  deps = gcc

rule ar
  command = rm -f $out && $ar crs $out $in
  description = AR $out

rule link
  command = $cxx $ldflags -o $out $in $libs
  description = LINK $out

# browse_py.h is used to inline browse.py.
rule inline
  command = "$root/src/inline.sh" $varname < $in > $out
  description = INLINE $out
build $builddir/browse_py.h: inline $root/src/browse.py | $root/src/inline.sh
  varname = kBrowsePy

build $builddir/browse.o: cxx $root/src/browse.cc || $builddir/browse_py.h

# the depfile parser and ninja lexers are generated using re2c.
rule re2c
  command = re2c -b -i --no-generation-date -o $out $in
  description = RE2C $out
build $root/src/depfile_parser.cc: re2c $root/src/depfile_parser.in.cc
build $root/src/lexer.cc: re2c $root/src/lexer.in.cc

# Core source files all build into ninja library.
build $builddir/build.o: cxx $root/src/build.cc
build $builddir/build_log.o: cxx $root/src/build_log.cc
build $builddir/clean.o: cxx $root/src/clean.cc
build $builddir/debug_flags.o: cxx $root/src/debug_flags.cc
build $builddir/depfile_parser.o: cxx $root/src/depfile_parser.cc
build $builddir/deps_log.o: cxx $root/src/deps_log.cc
build $builddir/disk_interface.o: cxx $root/src/disk_interface.cc
build $builddir/edit_distance.o: cxx $root/src/edit_distance.cc
build $builddir/eval_env.o: cxx $root/src/eval_env.cc
build $builddir/graph.o: cxx $root/src/graph.cc
build $builddir/graphviz.o: cxx $root/src/graphviz.cc
build $builddir/lexer.o: cxx $root/src/lexer.cc
build $builddir/line_printer.o: cxx $root/src/line_printer.cc
build $builddir/manifest_parser.o: cxx $root/src/manifest_parser.cc
build $builddir/metrics.o: cxx $root/src/metrics.cc
build $builddir/state.o: cxx $root/src/state.cc
build $builddir/util.o: cxx $root/src/util.cc
build $builddir/version.o: cxx $root/src/version.cc
build $builddir/subprocess-posix.o: cxx $root/src/subprocess-posix.cc
build $builddir/libninja.a: ar $builddir/browse.o $builddir/build.o $
    $builddir/build_log.o $builddir/clean.o $builddir/debug_flags.o $
    $builddir/depfile_parser.o $builddir/deps_log.o $
    $builddir/disk_interface.o $builddir/edit_distance.o $
    $builddir/eval_env.o $builddir/graph.o $builddir/graphviz.o $
    $builddir/lexer.o $builddir/line_printer.o $builddir/manifest_parser.o $
    $builddir/metrics.o $builddir/state.o $builddir/util.o $
    $builddir/version.o $builddir/subprocess-posix.o

# Main executable is library plus main() function.
build $builddir/ninja.o: cxx $root/src/ninja.cc
build ninja: link $builddir/ninja.o | $builddir/libninja.a
  libs = -lninja

# Tests all build into ninja_test executable.
build $builddir/build_log_test.o: cxx $root/src/build_log_test.cc
build $builddir/build_test.o: cxx $root/src/build_test.cc
build $builddir/clean_test.o: cxx $root/src/clean_test.cc
build $builddir/depfile_parser_test.o: cxx $root/src/depfile_parser_test.cc
build $builddir/deps_log_test.o: cxx $root/src/deps_log_test.cc
build $builddir/disk_interface_test.o: cxx $root/src/disk_interface_test.cc
build $builddir/edit_distance_test.o: cxx $root/src/edit_distance_test.cc
build $builddir/graph_test.o: cxx $root/src/graph_test.cc
build $builddir/lexer_test.o: cxx $root/src/lexer_test.cc
build $builddir/manifest_parser_test.o: cxx $root/src/manifest_parser_test.cc
build $builddir/ninja_test.o: cxx $root/src/ninja_test.cc
build $builddir/state_test.o: cxx $root/src/state_test.cc
build $builddir/subprocess_test.o: cxx $root/src/subprocess_test.cc
build $builddir/test.o: cxx $root/src/test.cc
build $builddir/util_test.o: cxx $root/src/util_test.cc
build ninja_test: link $builddir/build_log_test.o $builddir/build_test.o $
    $builddir/clean_test.o $builddir/depfile_parser_test.o $
    $builddir/deps_log_test.o $builddir/disk_interface_test.o $
    $builddir/edit_distance_test.o $builddir/graph_test.o $
    $builddir/lexer_test.o $builddir/manifest_parser_test.o $
    $builddir/ninja_test.o $builddir/state_test.o $
    $builddir/subprocess_test.o $builddir/test.o $builddir/util_test.o | $
    $builddir/libninja.a
  libs = -lninja

# Ancillary executables.
build $builddir/build_log_perftest.o: cxx $root/src/build_log_perftest.cc
build build_log_perftest: link $builddir/build_log_perftest.o | $
    $builddir/libninja.a
  libs = -lninja
build $builddir/canon_perftest.o: cxx $root/src/canon_perftest.cc
build canon_perftest: link $builddir/canon_perftest.o | $builddir/libninja.a
  libs = -lninja
build $builddir/depfile_parser_perftest.o: cxx $
    $root/src/depfile_parser_perftest.cc
build depfile_parser_perftest: link $builddir/depfile_parser_perftest.o | $
    $builddir/libninja.a
  libs = -lninja
build $builddir/hash_collision_bench.o: cxx $root/src/hash_collision_bench.cc
build hash_collision_bench: link $builddir/hash_collision_bench.o | $
    $builddir/libninja.a
  libs = -lninja
build $builddir/manifest_parser_perftest.o: cxx $
    $root/src/manifest_parser_perftest.cc
build manifest_parser_perftest: link $builddir/manifest_parser_perftest.o | $
    $builddir/libninja.a
  libs = -lninja

# Generate a graph using the "graph" tool.
rule gendot
  command = ./ninja -t graph all > $out
rule gengraph
  command = dot -Tpng $in > $out
build $builddir/graph.dot: gendot ninja build.ninja
build graph.png: gengraph $builddir/graph.dot

# Generate the manual using asciidoc.
rule asciidoc
  command = asciidoc -b docbook -d book -o $out $in
  description = ASCIIDOC $out
rule xsltproc
  command = xsltproc --nonet doc/docbook.xsl $in > $out
  description = XSLTPROC $out
build $builddir/manual.xml: asciidoc $root/doc/manual.asciidoc
build $root/doc/manual.html: xsltproc $builddir/manual.xml | $
    $root/doc/style.css $root/doc/docbook.xsl
build manual: phony || $root/doc/manual.html

rule dblatex
  command = dblatex -q -o $out -p doc/dblatex.xsl $in
  description = DBLATEX $out
build $root/doc/manual.pdf: dblatex $builddir/manual.xml | $
    $root/doc/dblatex.xsl
# Generate Doxygen.
rule doxygen
  command = doxygen $in
  description = DOXYGEN $in
doxygen_mainpage_generator = $root/src/gen_doxygen_mainpage.sh
rule doxygen_mainpage
  command = $doxygen_mainpage_generator $in > $out
  description = DOXYGEN_MAINPAGE $out
build $builddir/doxygen_mainpage: doxygen_mainpage README COPYING | $
    $doxygen_mainpage_generator
build doxygen: doxygen $root/doc/doxygen.config | $builddir/doxygen_mainpage

# Regenerate build files if build script changes.
rule configure
  command = ${configure_env}python $root/configure.py $configure_args
  generator = 1
build build.ninja: configure | $root/configure.py $root/misc/ninja_syntax.py

default ninja

# Packaging
rule rpmbuild
  command = misc/packaging/rpmbuild.sh
  description = Building rpms..
build rpm: rpmbuild

build all: phony ninja ninja_test build_log_perftest canon_perftest $
    depfile_parser_perftest hash_collision_bench manifest_parser_perftest

```

# Android.mk、Android.bp、Soong、Blueprint、Ninja 关系
```
Android.bp --> Blueprint --> Soong --> Ninja
Makefile or Android.mk --> kati --> Ninja
(Android.mk --> Blueprint --> Soong  --> Android.bp) 


现存的Android.mk、既有的Android.bp，都会分别被转换成Ninja

 从Android.mk与其它Makefile，会生成 /out/build-<product_name>.ninja   154万行  650MB 
 从Android.bp，则会生成out/soong/build.ninja     256万行   450MB大小 
此外，还会生成一个较小的out/combined-<product_name>.ninja     5行  负责把二者组合起来，作为执行入口
Ninja文件才是真正直接控制源码编译的工具

1. /out/soong/build.ninja     256万行   450MB大小     尼玛这么大   所有 .bp 文件生成的 ninja 文件
2. /out/soong/Android-xxxproduct.mk     31万行 也是个狠角色     这个文件是编译完成后模块的安装脚本，负责将模块安装到对应位置
3. /out/build-xxxxproduct.ninja     /out/build-aosp_arm.ninja  154万行  650MB 更狠角色 所有 .mk 文件生成的ninja 文件  


4. /out/soong/.minibootstrap/build.ninja       5行    主要是用来编译blueprint和生成   /out/soong/.bootstrap/build.ninja
		bootstrapBuildDir = out/soong
		topFile = ./Android.bp
		extraArgs =  -t -l out/.module_paths/Android.bp.list
		builddir = out
		include ./build/blueprint/bootstrap/build.ninja

5. /out/soong/.bootstrap/build.ninja           4000 行    主要是生成 soong相关工具和 /out/soong/build.ninja文件
6. /out/combined-xxxproduct.ninja              5 行     include    1和3
```

# Android 8.0的编译过程与问题
```
在原先Android 6.0纯Makefile编译的传统流程前，8.0版本新增了四个步骤：

1.Soong的自举（bootstrap）。这个步骤会编译Soong的核心组件。
2.收集Android.bp并生成out/soong/build.ninja文件。
3.收集Android.mk并生成out/build-<product>.ninja与out/combined-<product>.ninja文件。
4.执行Ninja文件，进行编译这个combined-*.ninja   文件，就是真正的执行入口。
  后面的步骤就是自底向上编译Android所有模块，与以前相同

问题1: Ninja文件太大
在AOSP 8.0版本，out/build-<product>.ninja文件超过400MB，
out/soong/build.ninja也超过200MB，实际的项目会更大。 
out/combined-<product>.ninja是对另外两个文件的组合，本身并不大。
比起out目录下其它几十GB的产物，这可能不算是一个真正的问题。 但这些巨大的纯文本文件，会产生其它问题。

问题2: 生成Ninja文件很慢
Android编译脚本重新生成Ninja文件的场景，比想象中要多。
如果只是在项目lunch不同产品参数时，生成不同的Ninja文件，这还可以接受。 
实际上不仅如此，用mm进行单模块编译时，也会生成一组Ninja文件， 
命名规律为build-<product_name>-<path_to_Android.mk>.ninja
比如，在AOSP项目lunch aosp_arm64-eng后，于system/netd目录下，做mm编译，会出现以下打印：
out/build-aosp_arm64-system_netd_Android.mk.ninja is missing, regenerating...
卡4~6分钟后，生成了out/build-aosp_arm64-system_netd_Android.mk.ninja文件，并继续编译过程
此外，mmm会额外生成另外一种Ninja文件：out/build-aosp_arm64-_system_netd_Android.mk.ninja。 
仔细看，可以发现，差别就是多了一个下划线_！ 这两个文件，不仅名称相近，而且内容相同，可能是一个bug
有时，也会生成一种带SHA1的的文件，比如out/build-8015fee4a534af9c63f8512e71a7f51b.ninja

问题3：重新生成Ninja的条件很多
在system/netd目录下，再次执行mm，则大约9秒就得到结果 ninja: no work to do.
然而，如果touch Android.mk，再执行mm，就需要重新生成Ninja文件
system/netd/Android.mk was modified, regenerating...
已知的规则，只有编译配置文件被修改。 除了显式修改，还有类似git pull、repo sync这样的情况。
 然而实际使用中，有更多未知因素，也会导致Ninja文件重新生成。


```

# 使用ninja能改进什么
```
使用ninja，可以不经过make，直接执行Ninja文件，完全避免重新生成，以及解析Makefile的运行开销。

此外，还能、或者说必须，指定更细致的执行内容。 mm的意思，是执行当前目录下的所有编译模块。
 Android.mk定义了几个模块，就会执行几个。 
使用ninja必须指定一个target，否则是全编译。 可以直接指定模块名，单独执行某个模块。 
甚至可以指定具体的*.o、*.jar、*.dex文件为target，避免编译整个模块。

（当然，make也支持指定模块名，或更细致的target。 用Ninja只是为了避免重新生成，其实相比Android 6.0，执行速度未见得有多快。）

```

# 使用ninja
```
1.准备build.ninja
make的默认编译文件是Makefile，而Ninja默认的编译文件则是build.ninja。
ninja需要在Android项目根目录执行，那里当然是没有这个文件的，需要自行准备。
【一】 ln -s out/combined-aosp_arm64.ninja build.ninja    // 在根目录创建一个软链接 build.ninja 指向  out/combined-aosp_arm64.ninja

以上仍然以AOSP的lunch aosp_arm64-eng为例。 如果有多个combined-*.ninja文件，可以自行选择一个。
ninja 也支持-f参数，可以临时指定一个Ninja文件来执行
ninja -f out/combined-aosp_arm64.ninja <target>

2. 找到模块名
在source build/envsetup.sh后，有一些额外的function，可以帮助查找代码，详见hmm。 
其中mgrep，对查找*.mk文件，很有帮助。 而Android.bp，则还没做相关功能，不过也可以通过原版grep来实现
比如，在AOSP的system/netd目录下，查找所有Makefile里的模块名：
source build/envsetup.sh
mgrep -w 'LOCAL_MODULE\|LOCAL_PACKAGE_NAME'

再查找所有Android.bp里的模块名。
grep -rnws --include='*.bp' 'name:'
libnetdutils/Android.bp:2:    name: "libnetdutils",
libnetdutils/Android.bp:26:    name: "netdutils_test",


有些Java层的模块，可能会没有指定LOCAL_MODULE，而是指定LOCAL_PACKAGE_NAME
mgrep -w 'LOCAL_MODULE\|LOCAL_PACKAGE_NAME'


原理大概是这样，而为了方便，可以用一个alias来简化查找

alias findm="grep -rnws --include='*.[mb][kp]' 'LOCAL_MODULE\|LOCAL_PACKAGE_NAME\|name:'"
```

## 使用ninja执行单模块编译
```
要编译单个文件，先要找到对应的target，这一个原则是相同的。 但由于C/C++与Java的编译方式大不相同，因此分开举例介绍。

C/C++
开发过程中，如果单个文件编译出错了，可以在修改后，用ninja单独编译这个文件。
$ echo error >> system/netd/server/Network.cpp  # Make an error
$ ninja netd
[1/6] target  C++: netd <= system/netd/server/Network.cpp
FAILED: out/target/product/aosp_arm64/obj/EXECUTABLES/netd_intermediates/Network.o  【Network.cpp 生成 Network.o 失败 】
...
system/netd/server/Network.cpp:95:1: error: unknown type name 'error'
error
^
system/netd/server/Network.cpp:95:6: error: expected unqualified-id
error
     ^
2 errors generated.
ninja: build stopped: subcommand failed.

在修改了这个错误后，可以通过FAILED:的提示，直接编译这个*.o文件
ninja out/target/product/aosp_arm64/obj/EXECUTABLES/netd_intermediates/Network.o
这样，能最快地检查编译问题是否修复





Java
Java层的情况也类似，但没有办法做到像C/C++这么精确。

echo error >> packages/apps/Settings/src/com/android/settings/Settings.java  # Make an error 报错
$ ninja Settings
...
[3/9] Building with Jack: out/target/common/obj/APPS/Settings_intermediates/with-local/classes.dex
FAILED: out/target/common/obj/APPS/Settings_intermediates/with-local/classes.dex
...
ERROR: /home/yanqd1/workspace/aosp_arm64/packages/apps/Settings/src/com/android/settings/Settings.java:183.1: Syntax error on token "error", delete this token
ninja: build stopped: subcommand failed.


复问题后，同样是根据FAILED:这一行的提示，编译这个*.dex文件
$ ninja out/target/common/obj/APPS/Settings_intermediates/with-local/classes.dex
...
[3/3] Building with Jack: out/target/common/obj/APPS/Settings_intermediates/with-local/classes.dex



```