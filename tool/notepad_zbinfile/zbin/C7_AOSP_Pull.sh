#!/bin/bash
. /etc/profile
 
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
cd $DIR
classes=$DIR
AOSPRootPath=$DIR
ProductPath=$(dirname  "$PWD")
echo "PATH="$DIR
timestampDirName="ZC7_outdir_"$(date +%Y%m%d-%H%M%S)
echo "timestampDirName="$timestampDirName
mkdir $timestampDirName


OutName=`echo $ProductPath | awk -F "/" '{print $NF}' | tr '[A-Z]' '[a-z]'`
echo "AOSPRootPath="$AOSPRootPath
echo "ProductPath="$ProductPath
echo "OutName="$OutName

# copy-step

# subclass-one 

## 1-1.about.html  ./vendor/qcom/nonhlos/common/about.html
cp -fr ./vendor/qcom/nonhlos/common/about.html ./$timestampDirName

## 1-2 WCNSS_qcom_cfg.ini  ./device/qcom/wlan/talos/WCNSS_qcom_cfg.ini
cp -fr  ./device/qcom/wlan/talos/WCNSS_qcom_cfg.ini  ./$timestampDirName

## 1-3  dex_preopt.mk    ./build/core/dex_preopt.mk
cp -fr  ./build/core/dex_preopt.mk  ./$timestampDirName

## 1-4  *.elf   ./vendor/qcom/nonhlos/WLAN.HL.3.0.1/wlan_proc/build/ms/*.elf 
cp -fr  ./vendor/qcom/nonhlos/WLAN.HL.3.0.1/wlan_proc/build/ms/*.elf  ./$timestampDirName


# subclass-two

 ########## priv-app-begin ##########
## 2-1 priv-app   Settings.apk
cp -fr ./out/target/product/$OutName/system/priv-app/Settings/Settings.apk ./$timestampDirName


## 2-2 priv-app  WpaConfigApp.apk
cp -fr ./out/target/product/$OutName/system/priv-app/WpaConfigApp/WpaConfigApp.apk ./$timestampDirName


## 2-3 priv-app  CoreSettingsExt.apk
cp -fr ./out/target/product/$OutName/system/priv-app/CoreSettingsExt/CoreSettingsExt.apk ./$timestampDirName

 ########## priv-app-end ##########


 ########## framework-begin ##########

## 3-1 framework  framework.jar
cp -fr ./out/target/product/$OutName/system/framework/framework.jar ./$timestampDirName


## 3-2 framework  framework-res.apk
cp -fr ./out/target/product/$OutName/system/framework/framework-res.apk ./$timestampDirName

## 3-3 framework wifi-service.jar
cp -fr ./out/target/product/$OutName/system/framework/wifi-service.jar ./$timestampDirName


 ########## framework-end ##########

 ########## system-lib64-begin ##########

## 4-1 libwifi-service.so
cp -fr ./out/target/product/$OutName/system/lib64/libwifi-service.so ./$timestampDirName


## 4-2 libwifi-system.so
cp -fr ./out/target/product/$OutName/system/lib64/libwifi-system.so ./$timestampDirName



 ########## system-lib64-end ##########



 ########## vendor-begin ##########

## 5-1 vendor/bin/wpa_cli
cp -fr ./out/target/product/$OutName/vendor/bin/wpa_cli ./$timestampDirName

## 5-2 vendor/lib64/wpa_client.so
cp -fr ./out/target/product/$OutName/vendor/lib64/libwpa_client.so ./$timestampDirName

## 5-3 vendor/bin/hw/wpa_supplicant
cp -fr ./out/target/product/$OutName/vendor/bin/hw/wpa_supplicant ./$timestampDirName

## 5-4 vendor/bin/hw/hostapd
cp -fr ./out/target/product/$OutName/vendor/bin/hw/hostapd ./$timestampDirName


 ########## vendor-end ##########


 ########## out/product/obj/-begin ##########
## 6-1  pronto_wlan.ko.unstripped
cp -fr  ./out/target/product/$OutName/obj/vendor/qcom/opensource/wlan/qcacld-3.0/*.unstripped  ./$timestampDirName
cp -fr  ./out/target/product/$OutName/obj/vendor/qcom/opensource/wlan/qcacld-3.0/*.unsigned  ./$timestampDirName


## 6-2  vmlinux.o  ./out/target/product/obj/KERNEL_OBJ/vmlinux.o
cp -fr  ./out/target/product/$OutName/obj/KERNEL_OBJ/vmlinux.o  ./$timestampDirName
 ########## out/product/obj/-begin ##########












