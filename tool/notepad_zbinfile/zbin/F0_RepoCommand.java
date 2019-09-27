import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class F0_RepoCommand {


    // zrepo_command_F0   xx.git  xada   dada.xml  lima
    // -u ssh://gerrit.mot.com:29418/home/repo/dev/platform/android/platform/manifest/o.git
    //  -m  xxxxx.xml
    //   xxxx.git      mq     xxxx.xml


    //【lima】  o.git  prodp-mtk6771  r-mt6771.xml
    //  -u o.git  --manifest-branch=prodp-mtk6771   -m  r-mt6771.xml

    //【sofia】
    // q.git  mq     r-6125.xml
    //  -u q.git  --manifest-branch=mq   -m  r-6125.xml

    // 输入的参数列表
    static String productName= "";  // 产品名称
    static String xmlbranchName="";  // .xml输入
    static String gitRepoName="";  // .git 输入
    static String manifestBranchName="";  // manifest的分支



    static ArrayList<String> mKeyWordName = new ArrayList<>();
static int DEFAULT_INPUT_NUM = 4;
    public static void main(String[] args) {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                String itemArgStr = args[i];
                mKeyWordName.add(itemArgStr);
            }
        }

        if(mKeyWordName.size() == 0){
            System.out.println("输入的Product参数数据为空!");
            showtip();
            return;
        }else if(mKeyWordName.size() != DEFAULT_INPUT_NUM) {
            System.out.println("输入的Product参数数据个数不对,当前为 "+mKeyWordName.size()+"个参数,实际需要"+DEFAULT_INPUT_NUM+"个!");
            showtip();
            return;
        }

        productName = mKeyWordName.get(mKeyWordName.size()-1);  // 获取最后一个当做产品名称

        if("".equals(productName)){
            System.out.println("输入的Product产品名称为空!");
            showtip();
            return;
        }
        ArrayList<String> otherParamList = new  ArrayList<String>();
        for (int i = 0; i < mKeyWordName.size()-1; i++) {
            otherParamList.add(mKeyWordName.get(i));
        }

        getDetailInput(otherParamList);

        if(!checkParamRight()){
            System.out.println("检测到当前输入参数存在空的情况，请重新输入!");
            showtip();
            return;
        }

        showRepoInit();
        showRebuildingCommand();
        showBuildingAppCommand();
        showCommitTip();
    }
    static void  showCommitTip() {
        printSchema("【 提交commit命令 提示】");
        System.out.println("git push origin TEMP:refs/for/【当前分支| 通过 git gui ，gitk 查看提交分支】");
        System.out.println("示例:  git push origin TEMP:refs/for/bp ");

    }

    static void  showBuildingAppCommand(){
        printSchema("【 编译 apk jar so bin 命令】");
     //   System.out.println("--------------------------------------------------");
        System.out.println("【 Settings.apk "+productName+"_retail-userdebug"+"】");
        System.out.println(buildSettingsApk(gitRepoName,manifestBranchName,xmlbranchName,productName,"retail"));
        printLine();
        System.out.println("【 framework.jar "+productName+"_retail-userdebug"+"】");
        System.out.println(buildFrameworkJar(gitRepoName,manifestBranchName,xmlbranchName,productName,"retail"));


        printLine();
        System.out.println("【 wifi-services.jar "+productName+"_retail-userdebug"+"】");
        System.out.println(buildWifiServiceJar(gitRepoName,manifestBranchName,xmlbranchName,productName,"retail"));

        printLine();
        System.out.println("【 bin/wpa_supplicant "+productName+"_retail-userdebug"+"】");
        System.out.println(buildWpaSupplicant(gitRepoName,manifestBranchName,xmlbranchName,productName,"retail"));


        printLine();
        printSchema("");
    }

    static void  showRebuildingCommand(){
        printSchema("【 rebuilding 再次编译 命令】");
     //   System.out.println("--------------------------------------------------");
        System.out.println("【 rebuilding "+productName+"_retail"+"】");
        System.out.println(buildRebuildingCommand(gitRepoName,manifestBranchName,xmlbranchName,productName,"retail"));
        printLine();
        System.out.println("【rebuilding "+productName+"_retail"+" -E oem-image】");
        System.out.println(buildRebuildingCommand_OEM(gitRepoName,manifestBranchName,xmlbranchName,productName,"retail"));
        printLine();
        printSchema("");
    }

static void showRepoInit(){
    printSchema("【repo init 命令】");
   // System.out.println("--------------------------------------------------");
    printLine();
    System.out.println("【"+productName+"_retail"+"】");
    System.out.println(buildInitAndCompileCommand(gitRepoName,manifestBranchName,xmlbranchName,productName,"retail"));
    printLine();
    System.out.println("【"+productName+"_retail"+" -E oem-image】");
    System.out.println(buildInitAndCompileCommand_oemimage(gitRepoName,manifestBranchName,xmlbranchName,productName,"retail"));
    printLine();

    System.out.println("【"+productName+"_factory"+"】");
    System.out.println(buildInitAndCompileCommand(gitRepoName,manifestBranchName,xmlbranchName,productName,"factory"));
    printLine();
    System.out.println("【"+productName+"_factory"+" -E oem-image 】");
    System.out.println(buildInitAndCompileCommand_oemimage(gitRepoName,manifestBranchName,xmlbranchName,productName,"factory"));

    printSchema("");

}

   static void printLine(){
       System.out.println("===================================================================");
    }

    static void printSchema(String title){
        System.out.println("################################"+title+"#####################################");
    }


    static String buildInitAndCompileCommand(String cGitRepoName ,String cManifestBranchName , String cXmlbranchName , String productName ,String buildType){
        String logname = productName+"_"+buildType;
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str1_2 = "source /opt/conf/moto.conf && ";
        String str2_1 = "repo init -u ssh://gerrit.mot.com/home/repo/dev/platform/android/platform/manifest/"+cGitRepoName.trim()+" "+"--repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git ";
        String str2_2 = "--manifest-branch="+cManifestBranchName.trim()+"  ";
        String str2_3 = "-m "+cXmlbranchName.trim()+"  && ";
        String str3_1 = "repo sync -j2  && repo start --all TEMP  && ";
        String str4_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() +"_"+buildType+"  -g -j4 2>&1 | tee "+getTimeStampDesc()+"_"+logname+".log";
        result = str1_1 + str1_2 + str2_1 + str2_2 + str2_3 + str3_1 + str4_1;
        return result;

    }


    static String buildInitAndCompileCommand_oemimage(String cGitRepoName ,String cManifestBranchName , String cXmlbranchName , String productName ,String buildType){
        String logname = productName+"_"+buildType+"_oem";
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str1_2 = "source /opt/conf/moto.conf && ";
        String str2_1 = "repo init -u ssh://gerrit.mot.com/home/repo/dev/platform/android/platform/manifest/"+cGitRepoName.trim()+" "+"--repo-url=ssh://gerrit.mot.com/home/repo/dev/platform/android/repo.git ";
        String str2_2 = "--manifest-branch="+cManifestBranchName.trim()+"  ";
        String str2_3 = "-m "+cXmlbranchName.trim()+"  && ";
        String str3_1 = "repo sync -j2  && repo start --all TEMP  && ";
        String str4_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() +"_"+buildType+"  -g -j4 -E oem-image 2>&1 | tee "+getTimeStampDesc()+"_"+logname+".log";

        result = str1_1 + str1_2 + str2_1 + str2_2 + str2_3 + str3_1 + str4_1;
        return result;

    }


    static String buildRebuildingCommand(String cGitRepoName ,String cManifestBranchName , String cXmlbranchName , String productName ,String buildType){
        String logname = productName+"_"+buildType+"_oem";
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = "source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  &&  ";
        String str3_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() +"_"+buildType+"  -g -j4  2>&1 | tee "+getTimeStampDesc()+"_"+logname+".log";
        result = str1_1 +  str2_1 + str3_1;
        return result;
    }


    static String buildRebuildingCommand_OEM(String cGitRepoName ,String cManifestBranchName , String cXmlbranchName , String productName ,String buildType){
        String logname = productName+"_"+buildType+"_oem";
        String result = "";
        String str1_1 = "export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = "source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  &&  ";
        String str3_1 = " motorola/build/bin/build_device.bash -b nightly -p " + productName.toLowerCase().trim() +"_"+buildType+"  -g -j4 -E oem-image 2>&1 | tee "+getTimeStampDesc()+"_"+logname+".log";
        result = str1_1 +  str2_1 + str3_1;
        return result;
    }


    static String buildSettingsApk(String cGitRepoName ,String cManifestBranchName , String cXmlbranchName , String productName ,String buildType){
        String result = "";

        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
String str3_1 =" lunch "+productName+"_retail-userdebug && ";
String str4_1 = " mmm packages/apps/Settings ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildSettingsLibs(String cGitRepoName ,String cManifestBranchName , String cXmlbranchName , String productName ,String buildType){
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 =" lunch "+productName+"_retail-userdebug && ";
        String str4_1 = " mmm frameworks/base/packages/SettingsLib ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildFrameworkJar(String cGitRepoName ,String cManifestBranchName , String cXmlbranchName , String productName ,String buildType){
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 =" lunch "+productName+"_retail-userdebug && ";
        String str4_1 = " mmm frameworks/base/ ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildWifiServiceJar(String cGitRepoName ,String cManifestBranchName , String cXmlbranchName , String productName ,String buildType){
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 =" lunch "+productName+"_retail-userdebug && ";
        String str4_1 = " mmm frameworks/opt/net/wifi/service ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }


    static String buildWpaSupplicant(String cGitRepoName ,String cManifestBranchName , String cXmlbranchName , String productName ,String buildType){
        String result = "";
        String str1_1 = " export PATH=/apps/android/python-2.7.6-x64/bin:$PATH  && export PATH=/apps/android/perl-5.1aclmsx8.4-x64/bin:$PATH && ";
        String str2_1 = " source build/envsetup.sh && source /opt/android.conf  &&   source /opt/conf/moto.conf  && ";
        String str3_1 =" lunch "+productName+"_retail-userdebug && ";
        String str4_1 = " mmm external/wpa_supplicant_8 ";
        result = str1_1 + str2_1 + str3_1 + str4_1;
        return result;
    }



    static boolean checkParamRight(){
        boolean flag = true;
        if("".equals(gitRepoName) || "".equals(xmlbranchName) || "".equals(manifestBranchName) || "".equals(productName)){
            flag = false;
        }
        return flag;
    }


    static String getTimeStampDesc() {
        //  String timeDesc = DateUtil.now();


        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("MMdd HHmm");
        String timeDesc =    dateFormatYYYYMMDD.format(Calendar.getInstance().getTime());

        if (timeDesc.contains(" ")) {
            timeDesc = timeDesc.replaceAll(" ", "_");
        }
        if (timeDesc.contains("-")) {
            timeDesc = timeDesc.replaceAll("-", "_");
        }
        if (timeDesc.contains(":")) {
            timeDesc = timeDesc.replaceAll(":", "_");
        }

        return timeDesc.trim();
    }
    static void getDetailInput(ArrayList<String> paramList){

        for (int i = 0; i < paramList.size(); i++) {
            String item = paramList.get(i).toLowerCase().trim();

            if(item.endsWith(".git")){
                gitRepoName = item;
            }else if(item.endsWith(".xml")){
                xmlbranchName = item;
            }else{
                manifestBranchName = item;
            }
        }


    }

    static void showtip(){
        System.out.println("当前示例的格式为: zrepo_command_F0  q.git  bp  test.xml  sky");
        System.out.println("q.git   【git仓库(.git结尾) (ReleaseNote.html 搜索 .git )】    ");
        System.out.println("bp      【 MANIFEST BRANCH 分支 (ReleaseNote.html 搜索 MANIFEST BRANCH)】");
        System.out.println("test.xml【 具体的manifest文件(必须.xml结尾) (ReleaseNote.html 搜索 test-keys 之前的字符串) 】");
        System.out.println("sky     【产品名称,必须在最后输入 ( ReleaseNote.html 搜索 BUILD TARGET )】");
    }
}
