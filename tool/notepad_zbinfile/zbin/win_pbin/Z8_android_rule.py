#!/usr/bin/python
# -*- coding: UTF-8 -*-

import random
import pygame
import sys
import os
from enum import Enum
from random import randint
from random import seed

import re
from pathlib import Path
import sys
import signal
import logging
import subprocess
import time
import argparse
import multiprocessing
from datetime import datetime

#  全局变量  与系统 息息相关的 项 
class OS_TYPE(Enum):
    Windows = "windows"
    Linux = "linux"
    MacOS = "macos"
    Android = "android"
    IOS = "ios"

ADB_PATH = "adb"
CUR_Batch_End = ".bat"
CUR_Bat_Name_Notype = "android_rule_Z8"
CUR_Bat_Name = "android_rule_Z8"
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
Device_Height = 1080
Device_Width = 720
Command_Index=0

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
    ADB_PATH=zbinPath+os.sep+"win_zbin"+os.sep+"adb"
    if len(sys.argv) >= 2:
        CUR_Shell_PATH=sys.argv[1]

    if len(sys.argv) >= 3:
        CUR_Rule_Index=sys.argv[2]
        CUR_Rule_Index=CUR_Rule_Index.replace("#", "").replace("_", "")

    if len(sys.argv) >= 4:
        params_list=sys.argv[3:]


    if sys.platform != "win32":
        ADB_PATH=zbinPath+os.sep+"lin_zbin"+os.sep+"adb"
    print("CUR_OS_TYPE:"+str(CUR_OS_TYPE))
    print("输入参数个数:"+str(len(sys.argv)))
    print("参数列表:"+str(sys.argv))
    print("操作系统:"+str(sys.platform))
    print("屏幕宽度_Width :"+str(Device_Width))
    print("屏幕宽度_Height:"+str(Device_Height))
    print("批处理后缀:"+CUR_Batch_End)
    print("批处理文件名称:"+CUR_Bat_Name)
    print("ADB_PATH:"+ADB_PATH)
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


###################### AndroidTool Begin  ######################

# Class to catch the ctrl + c interrupt (sigint)
class InterruptHandler(object):
    def __init__(self, sig=signal.SIGINT):
        self.sig = sig

    def __enter__(self):

        self.interrupted = False
        self.released = False

        self.original_handler = signal.getsignal(self.sig)

        def handler(signum, frame):
            self.release()
            self.interrupted = True

        signal.signal(self.sig, handler)

        return self

    def __exit__(self, type, value, tb):
        self.release()

    def release(self):

        if self.released:
            return False

        signal.signal(self.sig, self.original_handler)

        self.released = True

        return True


def run_adb_cmd(device=None, adb_cmd="shell", error_message=None, continue_on_exception=False):
    output = None
    cmd = ADB_PATH
    if device:
        cmd += " -s " + device
    global Command_Index
    Command_Index=Command_Index+1
    cmd += " " + adb_cmd


    print("["+str(Command_Index)+"]Run: %s" % cmd)
    try:
        output = subprocess.check_output(
            cmd, shell=True, stderr=subprocess.STDOUT
        ).decode("utf-8")
    except subprocess.CalledProcessError as e:
        if not continue_on_exception:
            if error_message:
                print("["+str(Command_Index)+"]Err: "+str(error_message));
            error_exit()
    print("["+str(Command_Index)+"]Rst: "+str(error_message));
    print("\n")
    return output


def error_exit():
    input("Error encountered... Press enter to abort.")
    sys.exit()


def sync_time(device):
    print("Synchronizing device time with computer time.")

    run_adb_cmd(
        device, "shell date `date +%m%d%H%M%G.%S`", "Could not synchronize the time."
    )


def init_config(device):

    # run adb remount
    run_adb_cmd(device, "remount", "Could not remount the device.")

    sync_time(device)

    # set con_mode to default 0
    run_adb_cmd(
        device,
        'shell "echo 0 > /sys/module/qca6490/parameters/con_mode"',
        "Could not configure con_mode.",
    )

    # enable wlan0
    run_adb_cmd(device, 'shell "ip link set wlan0 up"',
                "Could not configure wlan0.")

    # enable wifi
    wait_for_wlan_up(device)


def scan_ssids(device):
    print("Scanning wifi networks.")
    retry = 5
    retry_delay = 2
    # scan and return all information for available ssids
    while retry >= 0:
        scan = run_adb_cmd(device, 'shell "iw dev wlan0 scan"',
                           "Could not scan the ssids.", retry)
        # check if the scan returned is valid
        if type(scan) == str and not re.search("scan aborted!", scan):
            break
        # error if retry timeout. (retry reaches 0 but scan is aborted instead of failing)
        if not retry:
            print(
                "Number of retries reached for scanning the networks.")
            error_exit()

        retry -= 1
        time.sleep(retry_delay)

    ssids = [x.split(":")[1].strip() for x in re.findall(r"SSID:.*", scan)]
    freqs = [x.split(":")[1].strip() for x in re.findall(r"freq:.*", scan)]

    return scan, ssids, freqs


def select_input(scan_len, input_name):
    # get and validate user input
    inp = -1
    while not inp in range(0, scan_len):
        inp = int(input(f"Select {input_name}:"))
    return inp


def get_bandwidth(scan, n):
    # split scan based on "on wlan" that appears on first line of each network scanned
    scan = list(scan.split("on wlan"))[n + 1]

    # channel width: 1 = 80 MHz, channel width: 0 = 20 or 40 MHz
    # When STA channel width: any, we use 40MHz (channel width: 0)
    bw = re.search(r"\*.channel.width:.1", scan)
    if not bw:
        bw = re.search(r"STA.channel.width:.*",
                       scan).group().split(":")[1].strip()
        bw = 1 if bw == "any" else 0
    else:
        bw = 2

    return bw


def wait_for_wlan_down(device):
    run_adb_cmd(
        device, 'shell "cmd wifi set-wifi-enabled disabled"', "Could not turn off wifi."
    )

    wifioff = None
    while not wifioff:
        wifioff = re.search(
            r"Wifi is disabled",
            run_adb_cmd(device, 'shell "cmd wifi status"',
                        "Could not get wifi status."),
        )


def wait_for_wlan_up(device):
    run_adb_cmd(
        device, 'shell "cmd wifi set-wifi-enabled enabled"', "Could not turn off wifi."
    )

    wifion = None
    while not wifion:
        wifion = re.search(
            r"Wifi is enabled",
            run_adb_cmd(device, 'shell "cmd wifi status"',
                        "Could not get wifi status."),
        )


def configure_sniffer_capture(device, freq, bw):
    print("Configuring sniffer capture.")
    # set con_mode to 4, to enter sniffer mode
    run_adb_cmd(
        device,
        'shell "echo 4 > /sys/module/qca6490/parameters/con_mode"',
        "Could not set con_mode to 4.",
    )

    wait_for_wlan_down(device)
    restart_wlan(device)

    command = f'shell "iwpriv wlan0 setMonChan {freq} {bw}"'

    run_adb_cmd(
        device,
        command,
        "Could not configure the sniffer.",
    )


def run_sniffer_capture(device):
    print("Starting the sniffer process.")
    try:
        run_adb_cmd(
            device,
            'shell "tcpdump -i wlan0 -w /sdcard/capture.pcap -C 500 -W 20"',
            "Could not RUN the sniffer process.",
        )

    # Sniffer process will stop when SIGINT (ctrl + c) is used
    except KeyboardInterrupt:
        pass


def collect_logs(device):
    file_name = datetime.now().strftime("%m-%d-%y_%H.%M.%S_sniffer.tar.gz")
    print("\nCollecting logs.")

    # Compress the sniffer logs
    run_adb_cmd(
        device,
        f'shell "cd sdcard && tar cvzf {file_name} capture*"',
        "Could not compress the sniffer captured.",
    )

    # Pull the compressed capture
    run_adb_cmd(
        device,
        f"pull sdcard/{file_name}",
        "Could not pull the compressed sniffer captured.",
    )

    current_path = str(Path.cwd())
    print(f"Logs saved at: {current_path}/{file_name}")


def restore_configs(device):
    # Restore device connection mode, or else device won't connect to wifi
    run_adb_cmd(
        device,
        'shell "echo 0 > /sys/module/qca6490/parameters/con_mode"',
        "Could not reconfigure con_mode.",
    )


def restart_wlan(device):
    run_adb_cmd(device, 'shell "ip link set wlan0 down"',
                "Could not turn off wlan0.")

    run_adb_cmd(device, 'shell "ip link set wlan0 up"',
                "Could not turn on wlan0.")


def get_device_list():
    device_list = [y.split()[0] for y in run_adb_cmd(
        None, 'devices', 'Could not get device list.').splitlines() if y]
    return device_list[1:]


def select_device():
    device_list = get_device_list()
    num_devices = len(device_list)

    # No devices or only onde device found
    if num_devices <= 1:
        wait_for_device()
        return get_device_list()[0]

    print("\nList of available devices:\n")
    for i in range(0, num_devices):
        print(f"{i}: {device_list[i]}")
    print("\n")

    inp = select_input(num_devices, "device")

    return device_list[inp]


def wait_for_device():
    print("Waiting for device")
    run_adb_cmd(None, "wait-for-device", "Something wrong with ADB.")
    print("Device found")


def adb_root(device):
    run_adb_cmd(device, "root", "Can't run adb on root")


def select_ssid(ssids, freqs):
    print("\nList of available ssids:\n")
    print(len(ssids))
    print("\n")


    for i in range(len(ssids)):
        ssids[i] = ssids[i] if ssids[i] else "hidden"
        print(len(ssids))
        print(f"{i}: {ssids[i]} - {freqs[i]}MHz")

    print("\n")

    return select_input(len(ssids), "ssid")


def run_sniffer_process_and_wait_for_sigint(device):
    p = multiprocessing.Process(
        target=run_sniffer_capture,
        args=(
            device,
        ),
    )
    # Start sniffer process
    p.start()
    # Wait 2 seconds to print message, or else the it won't print in the correct order
    time.sleep(2)
    print("Sniffer is running. To finish, press ctrl + c to terminate sniffer process.")
    with InterruptHandler() as h:
        while True:
            if h.interrupted:
                break
    p.terminate()
    # wait for the sniffer process to terminate
    while p.is_alive():
        time.sleep(0.1)
    p.close()


def capture_sniffer(mDevice,mFreq,mBandWith):
    device = mDevice
    bw = mBandWith
    freq = mFreq
    if not device:
        device = select_device()
    print("capture_sniffer_begin  channel:"+str(freq)+"  width:"+str(bw))

    adb_root(device)
    init_config(device)
#    scan, ssids, freqs = scan_ssids(device)
#    print(len(ssids))
#    print(" ")
#    print(len(freqs))
#    inp = select_ssid(ssids, freqs)
#    freq = int(freqs[inp])

#    if not bw:
#        bw = get_bandwidth(scan, inp)

# capture_sniffer  channel:5300  width:0
    print("capture_sniffer_end  channel:"+str(freq)+"  width:"+str(bw))
    configure_sniffer_capture(device, freq, bw)
    run_sniffer_process_and_wait_for_sigint(device)
    collect_logs(device)
    restore_configs(device)
    input("Sniffer captured. Press enter to exit.")


###################### AndroidTool End  ######################


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

###################### Rule_1 Begin  ######################
class CodeRain_Rule_1(Basic_Rule):
    freq = 2414
    bw = 0
    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type

#    def __init__(self, rule_index, operation_type,file_type):
#        self.rule_index = rule_index
#        self.operation_type = operation_type
#        self.file_type = file_type


    def applyNoParamOperationRule0(self):
        # On Windows calling this function is necessary for
        # pyinstaller to work properly with multiprocessing
        multiprocessing.freeze_support()

        capture_sniffer(None,self.freq,self.bw)
        return "applyNoParamOperationRule0"


    def initParamsWithInputList(self,inputParamList):
        for i in range(len(inputParamList)):
            print("arg["+str(i)+"] = "+str(inputParamList[i]))
            if str(inputParamList[i]).startswith('freq_') :
                freqstr=str(inputParamList[i]).replace('freq_', '')
                print("freqstr="+str(freqstr))
                self.freq=int(freqstr)

            if str(inputParamList[i]).startswith('bw_') :
                bwstr=str(inputParamList[i]).replace('bw_', '')
                print("bwstr="+str(bwstr))
                self.bw=int(bwstr)
            
        print("input freq="+str(self.freq)+"  bw="+str(self.bw))
        return True


    def simpleDesc(self):
        return "抓取SniffLog"

    def ruleTip(self):
        simpdescStr =  self.simpleDesc();
        return CUR_Bat_Name + "  "  + "#_" + str(self.rule_index) + "   ## [索引 " + str(self.rule_index) + "]  描述:" + str(simpdescStr)+"默认 freq_2412 bw_0  "+"\n" \
            +CUR_Bat_Name + "  "  + "#_" + str(self.rule_index) + "   ## [索引 " + str(self.rule_index) + "]  描述:" + "freq_网络频段 "+"band width: bw=1(80 MHz),  bw=0(20MHz,40MHz)"+"\n" \
            +CUR_Bat_Name + "  "  + "#_" + str(self.rule_index) + " freq_2414 bw_0  ## [索引 " + str(self.rule_index) + "]  描述:" + str(simpdescStr)+"\n" \
        

###################### Rule_1 End  ######################


def initRule():
    realTypeRuleList.append(CodeRain_Rule_1(1,0));
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
    if CUR_Rule_Index_Int != -1 and CUR_Rule_Index_Int <= len(realTypeRuleList) :
        print("执行 对应的 CUR_Rule_Index="+CUR_Rule_Index+"的规则逻辑")
        RuleObj = realTypeRuleList[CUR_Rule_Index_Int-1]
        DoRuleOperation(RuleObj)
    else:
        print("当前索引搜索无效 请检查-打印Tip CUR_Rule_Index="+CUR_Rule_Index + "  规则总数:"+str(len(realTypeRuleList)))
        ShowAllRuleTip()


