#!/usr/bin/python
# -*- coding: UTF-8 -*-

import random
import pygame
import sys
import os
from enum import Enum
import time

#  全局变量  与系统 息息相关的 项 
class OS_TYPE(Enum):
    Windows = "windows"
    Linux = "linux"
    MacOS = "macos"
    Android = "android"
    IOS = "ios"


CUR_Batch_End = ".bat"
CUR_Bat_Name_Notype = "prule_apply_Z9"
CUR_Bat_Name = "prule_apply_Z9"
CUR_OS_TYPE = OS_TYPE.Windows
CUR_OS_ExeTYPE = ""
CUR_Shell_PATH = ""
CUR_Rule_Index = "-1"
zbinPath = ""
desktopPath = ""
pbinPath = ""
prulePath = ""
params_list=list()
realTypeRuleList=list()
now_yyyymmdd=str(time.strftime('%Y%m%d', time.localtime(time.time())))



def GetDesktopPath():
    return os.path.join(os.path.expanduser("~"), 'Desktop')





def initSystemInfo():
    print("════════════════"+"System Info Begin"+"════════════════")
    global CUR_Rule_Index 
    global desktopPath
    global zbinPath
    global pbinPath
    global prulePath
    global CUR_Bat_Name
    global CUR_Shell_PATH
    global CUR_Batch_End
    global CUR_OS_TYPE
    global CUR_OS_ExeTYPE
    global params_list
    desktopPath = GetDesktopPath()
    zbinPath=str(desktopPath)+os.sep+"zbin"
    pbinPath=zbinPath+os.sep+"win_pbin"
    prulePath=pbinPath+os.sep+CUR_Bat_Name_Notype+CUR_Batch_End
    CUR_Bat_Name=CUR_Bat_Name_Notype+CUR_Batch_End

    if len(sys.argv) >= 2:
        CUR_Shell_PATH=sys.argv[1]

    if len(sys.argv) >= 3:
        CUR_Rule_Index=sys.argv[2]
        CUR_Rule_Index=CUR_Rule_Index.replace("#", "").replace("_", "")

    if len(sys.argv) >= 4:
        params_list=sys.argv[3:]

    print("CUR_OS_TYPE:"+str(CUR_OS_TYPE))
    print("输入参数个数:"+str(len(sys.argv)))
    print("参数列表:"+str(sys.argv))
    print("操作系统:"+str(sys.platform))
    print("批处理后缀:"+CUR_Batch_End)
    print("批处理文件名称:"+CUR_Bat_Name)
    print("desktopPath:"+str(desktopPath))
    print("zbinPath:"+str(zbinPath));
    print("pbinPath:"+str(pbinPath)); 
    print("prulePath:"+str(prulePath));
    print("python可执行文件路径:"+str(sys.executable))
    print("now_yyyymmdd:"+str(now_yyyymmdd))
    print("CUR_Shell_PATH 执行命令路径:"+str(CUR_Shell_PATH))
    print("CUR_Rule_Index 当前所中规则:"+str(CUR_Rule_Index))
    print("params_list 规则参数列表:"+str(params_list))

    print("════════════════"+"System Info End"+"════════════════")





##// operation_type 
## 操作类型 0--不读取文件 不依靠参数 实现 自身逻辑
## 操作类型 1--读取文件内容字符串  进行修改
## 操作类型 2--对文件对文件内容(字节)--进行修改 
## 操作类型 3--对全体子文件进行的随性的操作 // 属性进行修改(文件名称)
## 操作类型4--对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) // 
## 操作类型 5--从shell 中获取到的路径 去对某一个文件进行操作



class Rule:
    '基础规则类'
    rule_index = 0
    operation_type = 0
    file_type = 0
    os_battype=".bat"  ## .sh


    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type

    def __init__(self, rule_index, operation_type,file_type):
        self.rule_index = rule_index
        self.operation_type = operation_type
        self.file_type = file_type

# abstract String applyNoParamOperationRule0( );
    def applyNoParamOperationRule0(self):
        print("applyNoParamOperationRule0")

# abstract String applyStringOperationRule1(String origin);
    def applyStringOperationRule1(self,rawContent):
        return ""

# abstract File applyFileByteOperationRule2(File originFile);
    def applyFileByteOperationRule2(self,rawfile):
        return None

# abstract ArrayList<File> applyFileListRule3(ArrayList<File> subFileList,HashMap<String, ArrayList<File>> fileTypeMap);     
    def applyFileListRule3(self,subFileList,fileType_file_Map):
        return None

# abstract ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,HashMap<String, ArrayList<File>> subFileTypeMap,
# ArrayList<File> curDirList,ArrayList<File> curRealFileList);

    def applySubFileListRule4(self,subFileList,fileType_file_Map , alldirlist , allrealfilelist):
        return None


    def allowEmptyDirFileList(self):
        return False

    def initParamsWithInputList(self,inputParamList):
        return True


    def simpleDesc(self):
        return ""

    def ruleTip(self):
        simpdescStr =  self.simpleDesc();
        return CUR_Bat_Name + "  "  + "#_" + str(self.rule_index) + "   ## [索引 " + str(self.rule_index) + "]  描述:" + str(simpdescStr)


class Basic_Rule(Rule):   
    '公共基础类'
    rule_index = 0
    operation_type = 0
    file_type = 0
    os_battype=".bat"  ## .sh


    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type

    def __init__(self, rule_index, operation_type,file_type):
        self.rule_index = rule_index
        self.operation_type = operation_type
        self.file_type = file_type

class CodeRain_Rule_1(Basic_Rule):

    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type

#    def __init__(self, rule_index, operation_type,file_type):
#        self.rule_index = rule_index
#        self.operation_type = operation_type
#        self.file_type = file_type


    def applyNoParamOperationRule0(self):
        pygame.init()
        screenInfo = pygame.display.Info()
        PANEL_width = screenInfo.current_w
        PANEL_highly = screenInfo.current_h
        FONT_PX = 15
        winSur = pygame.display.set_mode((PANEL_width, PANEL_highly))  # 全屏模式
        font = pygame.font.SysFont("arial", 20)
        bg_suface = pygame.Surface((PANEL_width, PANEL_highly), flags=pygame.SRCALPHA)
        pygame.Surface.convert(bg_suface)
        bg_suface.fill(pygame.Color(0, 0, 0, 28))
        winSur.fill((0, 0, 0))
        letter = ["0", "1"]
        texts = [font.render(str(letter[i]), True, (0, 255, 0))
                for i in range(len(letter))]
        column = int(PANEL_width / FONT_PX)
        drops = [0 for i in range(column)]
        while True:
            for event in pygame.event.get():
                if event.type == pygame.KEYDOWN:  # 按esc键退出
                    pygame.quit()
            pygame.time.delay(30)
            winSur.blit(bg_suface, (0, 0))
            for i in range(len(drops)):
                text = random.choice(texts)
                winSur.blit(text, (i * FONT_PX, drops[i] * FONT_PX))
                drops[i] += 1
                if drops[i] * 10 > PANEL_highly or random.random() > 0.95:
                    drops[i] = 0
            pygame.display.flip()

    def simpleDesc(self):
        return "屏幕打印二进制代码雨"

def initRule():
    realTypeRuleList.append(CodeRain_Rule_1(0,0));
    print("当前规则数量:"+str(len(realTypeRuleList)))



def DoRuleOperation( mRuleObj ):
    if mRuleObj.initParamsWithInputList(params_list):
        print("参数初始化完成 Step1 完成 开始执行对应 Rule 逻辑")
        if mRuleObj.operation_type == 0:
            mRuleObj.applyNoParamOperationRule0()
        if mRuleObj.operation_type == 1:
            mRuleObj.applyStringOperationRule1()
        if mRuleObj.operation_type == 2:
            mRuleObj.applyFileByteOperationRule2()
        if mRuleObj.operation_type == 3:
            mRuleObj.applyFileListRule3()
        if mRuleObj.operation_type == 4:
            mRuleObj.applySubFileListRule4()
    else:
        print("参数初始化错误 程序在 Step1 终止, 请检查输入参数!")

def ShowAllRuleTip():
    print()
    print()
    print()
    print()
    print("════════════════"+" All Rule 【"+str(len(realTypeRuleList))+"】 Tip Begin "+"════════════════")
    for ruleobj in realTypeRuleList:
        ruleobj_tip = ruleobj.ruleTip()
        print(ruleobj_tip)
        print()
    print("════════════════"+" All Rule 【"+str(len(realTypeRuleList))+"】 Tip End "+"════════════════")


if __name__ == '__main__':
    initSystemInfo()
    initRule()
    CUR_Rule_Index_Int = int(CUR_Rule_Index)
    if CUR_Rule_Index_Int != -1 and CUR_Rule_Index_Int < len(realTypeRuleList) :
        print("执行 对应的 CUR_Rule_Index="+CUR_Rule_Index+"的规则逻辑")
        RuleObj = realTypeRuleList[CUR_Rule_Index_Int]
        DoRuleOperation(RuleObj)
    else:
        print("当前索引搜索无效 请检查-打印Tip CUR_Rule_Index="+CUR_Rule_Index + "  规则总数:"+str(len(realTypeRuleList)))
        ShowAllRuleTip()
