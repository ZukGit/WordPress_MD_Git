

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// 根号  $  美元符号标识根号
public class H2_Calcul {


    /*       43~(2*4)+[(8#/e)*π]/sin45 -{tan20*21*10$10}+ln45/32-lg(78/32)/cot45+cos60
    str_mi = "~";
    str_jiecheng = "#";
    str_pai = "π";
    str_e = "e";
    str_lg = "lg";
    str_ln = "ln";
    str_geng = "$";
    str_sin = "sin";
    str_cos = "cos";
    str_cot = "cot";
    str_tan = "tan";
    str_zuo_xiao_kuohao = "(";
    str_you_xiao_kuohao = "(";
    str_zuo_zhong_kuohao = "[";
    str_you_zhong_kuohao = "]";
    str_zuo_da_kuohao = "{";
    str_you_ad_kuohao = "}";
    */


    public static void main(String[] args) {

        initSystemInfo();

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                // System.out.println("args[" + i + "] = " + args[i]);
                if (i == 0) {   // 第一个参数永远是  当前 shell的目录
                    CUR_Dir_1_PATH = args[i];
                } else {
                    CUR_INPUT_ParamStrList.add(args[i]);   // 当前cmd目录   第一个类型选项      之后所有的参数 保存在  CUR_INPUT_ParamStrList
                    //    Rule_Identify_TypeIndexList.add(args[i]);
                }
            }
        }

        File mCUR_Dir_FILE = new File(CUR_Dir_1_PATH);
        CUR_Dir_FILE = new File(CUR_Dir_1_PATH);


        // 用户没有输入参数
        if (CUR_INPUT_ParamStrList.size() == 0) {
             System.out.println("用户没有输入参数  提示如下:");
            showNoTypeTip();
            return;
        }


        if (!mCUR_Dir_FILE.exists() || !mCUR_Dir_FILE.isDirectory()) {
             System.out.println("当前执行替换逻辑的文件路径:" + CUR_Dir_1_PATH + "  不存在! ");
            return;
        }


        // 开始执行逻辑
        //1.  对参数进行过滤判断
        //2.  拿到参数再具体进行逻辑操作

        jisuan(CUR_INPUT_ParamStrList);


        setProperity();
    }


    public static void main4(String[] args) {
        String exp1 = "2~10";
        // System.out.println("exp = " + exp1 + " result = " + tryNextMakeSimple(exp1));
        // System.out.println();


        String exp2 = "3$3";
        // System.out.println("exp = " + exp2 + " result = " + tryNextMakeSimple(exp2));
        // System.out.println();

        String exp3 = "8*e";
        // System.out.println("exp = " + exp3 + " result = " + tryNextMakeSimple(exp3));
        // System.out.println();


        String exp4 = "8*π";
        // System.out.println("exp = " + exp4 + " result = " + tryNextMakeSimple(exp4));
        // System.out.println();

        String exp5 = "8π";
        // System.out.println("exp = " + exp5 + " result = " + tryNextMakeSimple(exp5));
        // System.out.println();


        String exp6 = "8e";
        // System.out.println("exp = " + exp6 + " result = " + tryNextMakeSimple(exp6));
        // System.out.println();


        String exp7 = "5#";
        // System.out.println("exp = " + exp7 + " result = " + tryNextMakeSimple(exp7));
        // System.out.println();

        String exp8 = "10#";
        // System.out.println("exp = " + exp8 + " result = " + tryNextMakeSimple(exp8));
        // System.out.println();



        String exp10 = "50#";
        // System.out.println("exp = " + exp10 + " result = " + tryNextMakeSimple(exp10));
        // System.out.println();

        String exp9 = "100#";
        // System.out.println("exp = " + exp9 + " result = " + tryNextMakeSimple(exp9));
        // System.out.println();


        String exp11 = "lg10";
        // System.out.println("exp = " + exp11 + " result = " + tryNextMakeSimple(exp11));
        // System.out.println();


        String exp12 = "lg1000";
        // System.out.println("exp = " + exp12 + " result = " + tryNextMakeSimple(exp12));
        // System.out.println();

        String exp13 = "2lg1000";
        // System.out.println("exp = " + exp13 + " result = " + tryNextMakeSimple(exp13));
        // System.out.println();

        String exp14 = "lne";
        // System.out.println("exp = " + exp14 + " result = " + tryNextMakeSimple(exp14));
        // System.out.println();


        String exp15 = "ln10";
        // System.out.println("exp = " + exp15 + " result = " + tryNextMakeSimple(exp15));
        // System.out.println();

        String exp16 = "2log4";
        // System.out.println("exp = " + exp16 + " result = " + tryNextMakeSimple(exp16));
        // System.out.println();


        String exp17 = "sin45";
        // System.out.println("exp = " + exp17 + " result = " + tryNextMakeSimple(exp17));
        // System.out.println();

        String exp18 = "cos45";
        // System.out.println("exp = " + exp18 + " result = " + tryNextMakeSimple(exp18));
        // System.out.println();


        String exp19 = "tan45";
        // System.out.println("exp = " + exp19 + " result = " + tryNextMakeSimple(exp19));
        // System.out.println();



        String exp20 = "cot45";
        // System.out.println("exp = " + exp20 + " result = " + tryNextMakeSimple(exp20));
        // System.out.println();

        // 43~8+[null*3.1416]/sin45 -{tan20*21*10$10}+ln45/32-lg2.4375/cot45+cos60
        // 43~(2*4)+[(8#/e)*π]/sin45 -{tan20*21*10$10}+ln45/32-lg(78/32)/cot45+cos60

        String exp22 = "8#";
        // System.out.println("exp = " + exp22 + " result = " + tryNextMakeSimple(exp22));
        // System.out.println();


/*
        String exp21 = "3~(1+1)+[(8#/e)*π]/sin45-{tan20*21*10$10}+ln45/32-lg(78/32)/cot45+cos60";
        // System.out.println("exp = " + exp21 + " result = " + tryNextMakeSimple(exp21));
        // System.out.println();


        String exp23 = "[2log(4*4+1)/5+3#+4~2]/7+sin45+cos45+($2+lg100)/3$3";
        // System.out.println("exp = " + exp23 + " result = " + tryNextMakeSimple(exp23));
        // System.out.println();
*/

/*
[2log(4*4+1)/5+3#+4~2]/7+sin45+cos45+($2+lg100)/3$3 result = 7.04113882434962
[2log17/5+6+16]+$2+$2+($2+2)/3$3
            */
        String exp24 = "2log17";
        // System.out.println("exp = " + exp24 + " result = " + tryNextMakeSimple(exp24));
        // System.out.println();

        String exp25 = "3$3";
        // System.out.println("exp = " + exp25 + " result = " + tryNextMakeSimple(exp25));
        // System.out.println();


        String exp23 = "[2log(4*4+1)/5+3#+4~2]/7+sin45+cos45+($2+lg100)/3$3";
        // System.out.println("exp = " + exp23 + " result = " + tryNextMakeSimple(exp23));
        // System.out.println();


        String exp21 = "3~(1+1)+[(3#/e)*π]/sin45-{tan20*21*10$10}+ln45/32-lg(78/32)/cot45+cos60";
        // System.out.println("exp = " + exp21 + " result = " + tryNextMakeSimple(exp21));
        // System.out.println();

    }


    /*******************修改属性列表 ------Begin *********************/
// 修改0.   全局修改 把 G8 改为当前应用的序号规则序号  当前类名称也需要修改
// 修改1.当前 执行代码的 bat sh 文件名称  最后必须是标识序号
    static String Cur_Bat_Name = "zcalcul_H2";


    /*******************修改属性列表 ------End *********************/


//   所有的算数符号  +-*/%  1234567890
//  ~  代表幂     2~3  = 2³
// #  代表阶乘        10# = 10!
// π  3.1415926
// e ---自然数  e=2.71828183
//  lg 是表示以10为底数的对数源函数  10^lgA=A
// ln  以自然数 底数 e 为底 的 对数函数   e=2.71828183    lne = 1    ln10 = 2.30258509
// lg20 = 1.3103    10^1.3103=20     【1.3103】  就是标识的那个对数   以10和结果 标识这个幂值(无理数)
//
// sin cos cot tan
// []  () {}

    static double hudu_changliang = Math.PI/180d;

    static String str_0 = "0";
    static String str_1 = "1";
    static String str_2 = "2";
    static String str_3 = "3";
    static String str_4 = "4";
    static String str_5 = "5";
    static String str_6 = "6";
    static String str_7 = "7";
    static String str_8 = "8";
    static String str_9 = "9";
    static String str_jia = "+";
    static String str_jian = "-";
    static String str_cheng = "*";
    static String str_chu = "/";
    static String str_yu = "%";
    static String str_dian = ".";


    static String str_mi = "~";
    static String str_jiecheng = "#";
    static String str_pai = "π";
    static String str_e = "e";
    static String str_lg = "lg";
    static String str_ln = "ln";
    static String str_log = "log";
    static String str_geng = "$";  // 3$3   3的立方更    $3 3的二次方根   10$3  3的十次方根
    static String str_sin = "sin";
    static String str_cos = "cos";
    static String str_cot = "cot";
    static String str_tan = "tan";
    static String str_zuo_xiao_kuohao = "(";
    static String str_you_xiao_kuohao = "(";
    static String str_zuo_zhong_kuohao = "[";
    static String str_you_zhong_kuohao = "]";
    static String str_zuo_da_kuohao = "{";
    static String str_you_ad_kuohao = "}";


    //  所有的  数学表达式  字符集合
    static ArrayList<String> mathCharList = new ArrayList<String>();

    //   简单表达式 字符集合
    static ArrayList<String> simpleCharList = new ArrayList<String>();

    static {
        simpleCharList.add(str_0);
        simpleCharList.add(str_1);
        simpleCharList.add(str_2);
        simpleCharList.add(str_3);
        simpleCharList.add(str_4);
        simpleCharList.add(str_5);
        simpleCharList.add(str_6);
        simpleCharList.add(str_7);
        simpleCharList.add(str_8);
        simpleCharList.add(str_9);
        simpleCharList.add(str_jia);
        simpleCharList.add(str_jian);
        simpleCharList.add(str_cheng);
        simpleCharList.add(str_chu);
        simpleCharList.add(str_yu);
        simpleCharList.add(str_dian);

    }

    //   非简单 需要计算结果的表达式的 字符集合
    static ArrayList<String> hardCharList = new ArrayList<String>();

    static {
        hardCharList.add(str_mi);
        hardCharList.add(str_jiecheng);
        hardCharList.add(str_pai);
        hardCharList.add(str_e);
        hardCharList.add(str_lg);
        hardCharList.add(str_ln);
        hardCharList.add(str_log);
        hardCharList.add(str_geng);
        hardCharList.add(str_sin);
        hardCharList.add(str_cos);
        hardCharList.add(str_cot);
        hardCharList.add(str_tan);
        hardCharList.add(str_zuo_xiao_kuohao);
        hardCharList.add(str_you_xiao_kuohao);
        hardCharList.add(str_zuo_zhong_kuohao);
        hardCharList.add(str_you_zhong_kuohao);
        hardCharList.add(str_zuo_da_kuohao);
        hardCharList.add(str_you_ad_kuohao);


    }


    static {
        mathCharList.add(str_0);
        mathCharList.add(str_1);
        mathCharList.add(str_2);
        mathCharList.add(str_3);
        mathCharList.add(str_4);
        mathCharList.add(str_5);
        mathCharList.add(str_6);
        mathCharList.add(str_7);
        mathCharList.add(str_8);
        mathCharList.add(str_9);
        mathCharList.add(str_jia);
        mathCharList.add(str_jian);
        mathCharList.add(str_cheng);
        mathCharList.add(str_chu);
        mathCharList.add(str_yu);
        mathCharList.add(str_mi);
        mathCharList.add(str_jiecheng);
        mathCharList.add(str_pai);
        mathCharList.add(str_dian);
        mathCharList.add(str_e);
        mathCharList.add(str_lg);
        mathCharList.add(str_ln);
        mathCharList.add(str_log);
        mathCharList.add(str_geng);
        mathCharList.add(str_zuo_xiao_kuohao);
        mathCharList.add(str_you_xiao_kuohao);
        mathCharList.add(str_zuo_zhong_kuohao);
        mathCharList.add(str_you_zhong_kuohao);
        mathCharList.add(str_zuo_da_kuohao);
        mathCharList.add(str_you_ad_kuohao);
        mathCharList.add(str_sin);
        mathCharList.add(str_cos);
        mathCharList.add(str_cot);
        mathCharList.add(str_tan);

    }


    static boolean isSimpleExp(String exp) {

        return checkExpContainHardChar(exp);
    }

    //  如果表达式包含了    hard数学字符 那么 就不是 simple的表达式
    static boolean checkExpContainHardChar(String exp) {
        boolean flag = true;
        // System.out.println("checkExpContainHardChar -> hardCharList.size()=" + hardCharList.size());
        for (int i = 0; i < hardCharList.size(); i++) {
            String hardMathChar = hardCharList.get(i);
            // System.out.println("checkExpContainHardChar -> hardMathChar=" + hardMathChar + "   exp=" + exp +"  containHard="+exp.contains(hardMathChar));
            if (exp.contains(hardMathChar)) {
                return false;
            }
        }
        return flag;
    }

/*       43~(2*4)+[(8#/e)*π]/sin45 -{tan20*21*10$10}+ln45/32-lg(78/32)/cot45+cos60
str_mi = "~";
str_jiecheng = "#";
str_pai = "π";
str_e = "e";
str_lg = "lg";
str_ln = "ln";
str_geng = "$";
str_sin = "sin";
str_cos = "cos";
str_cot = "cot";
str_tan = "tan";
str_zuo_xiao_kuohao = "(";
str_you_xiao_kuohao = "(";
str_zuo_zhong_kuohao = "[";
str_you_zhong_kuohao = "]";
str_zuo_da_kuohao = "{";
str_you_ad_kuohao = "}";
*/

//  类型1  常量替换
// e  替换为 数值
// π  替换为 数值

    static double paiValue = Math.PI;
    static double eValue = Math.E;


    static boolean isContainChangLiang(String exp) {
        return exp.contains("e") || exp.contains("π");
    }

    static boolean isContainXiaoKuoHao(String exp) {
        return exp.contains("(") && exp.contains(")");
    }

    static boolean isContainZhongKuoHao(String exp) {
        return exp.contains("[") && exp.contains("]");
    }

    static boolean isContainDaKuoHao(String exp) {
        return exp.contains("{") && exp.contains("}");
    }


    // 把  e  和  π  转为 数值
    static String makeChangLiangShuZhiHua(String exp) {
        String paiStr = String.format("%.4f", paiValue);
        String eStr = String.format("%.4f", eValue);
        if(exp.length() ==1){

            return  exp.replaceAll("e",  eStr).replaceAll("π", paiStr);
        }

        String result = exp.replaceAll("e", "*" + eStr).replaceAll("π", "*" + paiStr);
        result = result.replace("**", "*");
        result = result.replace("ln*", "ln");
        result = result.replace("log*", "log");
        result = result.replace("lg*", "lg");
        result = result.replace("/*", "/");

        return result;
    }

//  计算如下表达式
    // 2~(tan30+1/2)+43~(2*4)+[((8/2)#/e)*π]/sin45 -{tan20*21*10$10}+ln45/32-lg(78/32)/cot45+cos60


    static ScriptEngine calculOperation = new ScriptEngineManager().getEngineByName("JavaScript");

    static String tryNextMakeSimple(String preExp) {
        String nextExp = preExp;
        //  类型1  常量替换   适用于  e 和  π
        if (isContainChangLiang(nextExp)) {
            nextExp = makeChangLiangShuZhiHua(nextExp);
        }
        //   类型2      如果是   简单表达式 那么 直接返回结果
        if (isSimpleExp(nextExp)) {
            String simpleValue = nextExp;
            try {
                simpleValue = String.valueOf((Object) calculOperation.eval(nextExp));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            return simpleValue;
        }

        // 类型3  依次小括号的结果 并   把   这个带括号的表达式替换为 数值结果
        while (isContainXiaoKuoHao(nextExp)) {   //  如果 包含 小括号
            // 2~(tan30+1/2)+43~(2*4)
            nextExp = clearFirstStepPair(nextExp, "(", ")");  // 清除掉第一个 匹配到的左右括号
        }
        while (isContainZhongKuoHao(nextExp)) {   //  如果 包含 中括号
            nextExp = clearFirstStepPair(nextExp, "[", "]");  // 清除掉第一个 匹配到的左右括号
        }

        while (isContainDaKuoHao(nextExp)) {   //  如果 包含 大括号
            nextExp = clearFirstStepPair(nextExp, "{", "}");  // 清除掉第一个 匹配到的左右括号
        }

//  类型4   没有大中小 括号了    其余的符号
// 处理
//1. "~"  处理次方  2~3    2的3次方
//2. 3#   处理阶乘  3！  1x2x3
//3. 10lg8     处理 10底数 函数 lg

//4. ln     处理 自然数底数函数
//5. $  处理根号   3$3  3的三次方根     $2  根号2
// 6. sin    60sin60  60*sin60  正玄
// 7. cos    60cos60  60*cos60  余玄
// 8. tan    60tan90  正切
// 9. cot    60cot90  余切
// 10 . log10
        //  判断当前的表达式中没有

        while (isExpContainHardChar(nextExp)){
            for (int i = 0; i < hardCharList.size(); i++) {
                String mathChar = hardCharList.get(i);
                if (nextExp.contains(mathChar)) {
                    nextExp =  calculMathValue(nextExp, mathChar);
                    break;
                }
            }
        }


            str_mi = "~";
        str_jiecheng = "#";
        str_geng = "$";

        str_lg = "lg";
        str_ln = "ln";
        str_log = "log";
        str_sin = "sin";
        str_cos = "cos";
        str_cot = "cot";
        str_tan = "tan";


        //  找到第一个配对的  左右小括号
        // 规则:   搜索第一个右括号,  然后寻找在右括号之前的左括号
        // 参数1.左括号位置
        // 参数2.右括号位置
        // 参数3.包括左右括号的完成的表达式
        // 参数4. 不包括左右括号的完成的表达式
        //4.1   如果该表达式是简单表达式  那么直接计算出参数5 结果
        //4.2  如果该表达式是复杂表达式   那么就把该表达式 重新传送给 函数tryNextMakeSimple 计算结果
        // 参数5.该表达式的数值结果


        // System.out.println("nextExp->A = " + nextExp);
        if (isSimpleExp(nextExp)) {
            String simpleValue = nextExp;
            try {
                simpleValue = String.valueOf((Object) calculOperation.eval(nextExp));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            return simpleValue;
        }

        // System.out.println("nextExp->End = " + nextExp);
        return nextExp;


    }


  static  boolean isExpContainHardChar(String exp) {
        boolean containHardChar = false;
        for (int i = 0; i < hardCharList.size(); i++) {
              String hardChar = hardCharList.get(i);
              if(exp.contains(hardChar)){
                  containHardChar = true;
                  break;
              }
        }

        return containHardChar;
    }

    //   实现 处理 最简单的 这 几个 数学运算  zukgit 后续处理
    //  处理 次方  2~10  次方
    static double cifang(double preNum, double endNum) {
//     double result = 0;
//     Math.log1p()
        return Math.pow(preNum, endNum);
    }

    //  处理 阶乘 #
    static String jiecheng(double jieshu) {
//     Math.log1p()
        return simpleCircle((int) jieshu);
    }

    //  求根  10$2     一个数的10次方等于2   这个数标识位  10$2
    static double geng(double preNum, double endNum) {

        if (preNum == 1) {
            return endNum;
        }

        if (preNum == 2) {
            return Math.sqrt(endNum);
        }

        if (preNum == 3) {
            return Math.cbrt(endNum);
        }
      //  System.out.println("preNum = "+ preNum +"    endNum="+endNum);
        // 大于3 的  开方    4次方  5次方   6次方  8次方  10次方
        return Math.pow(endNum ,1d / preNum);
    }


    // 求对数   lg10 = 1
    static double lg(double endNum) {
//        double result = 0;
//     Math.log1p()
        return Math.log10(endNum);
    }

    // 求对数  自然数   lne = 1
    static double ln(double endNum) {
//        double result = 0;
//     Math.log1p()
        return Math.log(endNum);
    }


    // 求对数  2log3   以2为底数 3为值的对数
    static double log(double dishu, double endNum) {
//        double result = 0;
//     Math.log1p()
        return Math.log(endNum) / Math.log(dishu);
    }

    // sin 求正玄
    static double sin(double endNum) {
//        double result = 0;
//     Math.log1p()
        return Math.sin(endNum * hudu_changliang);
    }

    // cos 求余玄
    static double cos(double endNum) {
//        double result = 0;
//     Math.log1p()
        return Math.cos(endNum *  hudu_changliang);
    }


    // tan 求正切
    static double tan(double endNum) {
//        double result = 0;
//     Math.log1p()
        return Math.tan(endNum * hudu_changliang);
    }

    // cot 求余切
    static double cot(double endNum) {
//        double result = 0;
//     Math.log1p()
        return 1d / Math.tan(endNum * hudu_changliang);
    }


    public static String simpleCircle(int num) {//简单的循环计算的阶乘

        BigDecimal result = new BigDecimal(1);
        BigDecimal a;
        for(int i = 2; i <= num; i++){
            a = new BigDecimal(i);//将i转换为BigDecimal类型
            result = result.multiply(a);//不用result*a，因为BigDecimal类型没有定义*操作</span><span>
        }
        return result.toString();
    }




    static double getTargetCharFirstPre(String exp, String mathChar) {
        double preNum = 0;
        int expLength = exp.length();
        int mathLength = mathChar.length();
        int mathIndex = exp.indexOf(mathChar);
        if (mathIndex < 0) {
            return preNum;
        }
        String preNumStr = "";
        // System.out.println("getTargetCharFirstPre - > exp " + exp);

        for (int i = mathIndex - 1; i >= 0; i--) {
            String singeNumItem = exp.charAt(i) + "";
            // System.out.println("getTargetCharFirstPre - > index = " + i + "  char = " + singeNumItem);

            if (!isNumeric(singeNumItem)) {
                //  遇到非数值的 那么 直接就 break 出 for循环
                break;
            }
            preNumStr = (singeNumItem + preNumStr)  ;
        }
        // System.out.println("getTargetCharFirstPre->1   preNum = " + preNum + " preNumStr = " + preNumStr);

        if (!"".equals(preNumStr)) {
            preNum = Double.parseDouble(preNumStr);
        }

        // System.out.println("getTargetCharFirstPre->2   preNum = " + preNum + " preNumStr = " + preNumStr);

        return preNum;
    }


    static double getTargetCharFirstEnd(String exp, String mathChar) {
        double endNum = 0;
        int expLength = exp.length();
        int mathLength = mathChar.length();
        int mathIndex = exp.indexOf(mathChar);
        if (mathIndex < 0) {
            return endNum;
        }
        String endNumStr = "";
        // System.out.println("getTargetCharFirstEnd - > exp " + exp);

        for (int i = mathIndex + mathChar.length(); i < expLength; i++) {
            String singeNumItem = exp.charAt(i) + "";

            // System.out.println("getTargetCharFirstEnd - > index = " + i + "  char = " + singeNumItem);


            if (!isNumeric(singeNumItem)) {
                //  遇到非数值的 那么 直接就 break 出 for循环
                break;
            }
            endNumStr = endNumStr + singeNumItem;
        }

        // System.out.println("getTargetCharFirstEnd->1   endNum = " + endNum + " endNumStr = " + endNumStr);

        if (!"".equals(endNumStr)) {
            endNum = Double.parseDouble(endNumStr);
        }
        // System.out.println("getTargetCharFirstEnd->2   endNum = " + endNum + " endNumStr = " + endNumStr);

        return endNum;
    }

    static String calculMathValue(String exp, String mathChar) {
        String resultStr = exp;
        //  获取 要替换的 size 大小
        MathCharReplaceInfo mathCharReplaceInfo = new H2_Calcul().new MathCharReplaceInfo(exp, mathChar);

        return mathCharReplaceInfo.ReturnResult;
    }

    static String clearFirstStepPair(String oldExp, String pre, String end) {
        String result = oldExp;
        int end_FirstIndex = result.indexOf(end);
        int begin_ExpIndex = calculNearPairIndex(result, end_FirstIndex, pre);  //
        String targetExpNoOut = result.substring(begin_ExpIndex + 1, end_FirstIndex);   //  包前 不包后
        String targetExpWithOut = result.substring(begin_ExpIndex, end_FirstIndex + 1);  // 包前 包后
        // System.out.println(" oldExp = " + oldExp);
        // System.out.println(" targetExpWithOut = " + targetExpWithOut);
        // System.out.println(" targetExpNoOut = " + targetExpNoOut);
        // System.out.println(" begin_ExpIndex = " + begin_ExpIndex + "    end_FirstIndex =" + end_FirstIndex);

        ReplaceItem curReplaceItem = new H2_Calcul().new ReplaceItem(oldExp, begin_ExpIndex, end_FirstIndex, targetExpWithOut, pre, end);


        return curReplaceItem.getNumberValue();
    }

    //  计算 在 字符串 originStr 中 在位置 endIndex 之前 最近的那个 匹配上的 matchStr的 索引
    static int calculNearPairIndex(String originStr, int endIndex, String matchStr) {
        int beginIndex = 0;
        String subStr = originStr.substring(0, endIndex);
        beginIndex = subStr.lastIndexOf(matchStr);
        return beginIndex;

    }

    // 例如: 2~(tan30+1/2)+43~(2*4) 这个表达式
    //  我们计算第一个 括号 匹配到的 那段字符串
    //  originalExp  = 2~(tan30+1/2)+43~(2*4)
    //  needReplace_OldExp = (tan30+1/2)
    //  beginIndex = 2
    //  endIndex = 12;
    //  result == 这个必须是一个数值了   如果不是数值 那么 就不能替换
    //  用于处理 大中小括号的类
    class ReplaceItem {
        String originalExp;   //   原始的参数传递过来的表达式
        String needReplace_OldExp;  // 原来的字符串中  需要被替换为 结果的那一部分

        int beginIndex;  //  左起点
        int endIndex;   //  右终点
        String preCharStr;  // 左括号
        String endCharStr;  // 右括号   默认为空""

        String needCalculExp_NoOut;  //  两边 没有 括号的   动态计算得到的

        String ExpResultNumberValue;  // 替换掉  needReplace_OldExp 的数值
        String ReturnResult;   //  完成  替换后的字符串 表达式
        ArrayList<String> expressList; //  表达式的变化历程


        ReplaceItem(String mOriExp, int mBeginIndex, int mEndIndex, String mNeedReplace_OldExp) {
            new ReplaceItem(mOriExp, mBeginIndex, mEndIndex, mNeedReplace_OldExp, "", "");
        }

        ReplaceItem(String mOriExp, int mBeginIndex, int mEndIndex, String mNeedReplace_OldExp, String mPreChar, String mEndCharStr) {
            this.originalExp = mOriExp;
            this.beginIndex = mBeginIndex;
            this.endIndex = mEndIndex;
            this.needReplace_OldExp = mNeedReplace_OldExp;
            this.preCharStr = mPreChar;
            this.endCharStr = mEndCharStr;
            this.needCalculExp_NoOut = clearBlankOut(mNeedReplace_OldExp);
            expressList = new ArrayList<String>();
            expressList.add(this.needCalculExp_NoOut);

        }

        String getNumberValue() {
            String mNumberValue = "";
            if (isSimpleExp(needCalculExp_NoOut)) {
                try {
                    this.ExpResultNumberValue = String.valueOf((Object) calculOperation.eval(needCalculExp_NoOut));
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
/*                if(!isNumeric(ExpResultNumberValue)){
                    // System.out.println("当前 数值");
                }*/
                this.ReturnResult = replaceNumberValue(this.originalExp, this.beginIndex, this.endIndex, this.needReplace_OldExp, this.ExpResultNumberValue);
                mNumberValue = this.ReturnResult;
                // System.out.println("当前 数值 ExpResultNumberValue = " + ExpResultNumberValue);
                return mNumberValue;

            } else {
                // System.out.println("while(isSimple)-->1  Index:"+(expressList.size() - 1)+"    Exp = "+ expressList.get(expressList.size() - 1) + " isSimple = "+ isSimpleExp(expressList.get(expressList.size() - 1)));


                while (!isSimpleExp(expressList.get(expressList.size() - 1))) {
                    //  如果 当前 表达式 不是 最最简单的 那么 把 当前表达式  简化一步 放到  expressList;
                    // System.out.println("while(isSimple)-->2  Index:"+(expressList.size() - 1)+"    Exp = "+ expressList.get(expressList.size() - 1));
                    expressList.add(tryNextMakeSimple(expressList.get(expressList.size() - 1)));
                }
                //  到这里  的话  那么 最后一个 表达式 一定是一个  最简化的 表达式
                String simpleExp = expressList.get(expressList.size() - 1);
                try {
                    this.ExpResultNumberValue = String.valueOf((Object) calculOperation.eval(simpleExp));
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
                this.ReturnResult = replaceNumberValue(this.originalExp, this.beginIndex, this.endIndex, this.needReplace_OldExp, this.ExpResultNumberValue);
                mNumberValue = this.ReturnResult;
                // System.out.println("当前 数值 ExpResultNumberValue = " + ExpResultNumberValue);
                return mNumberValue;
            }
        }


    }


    //  2*2~31+14+21*2~31
    // 数学符号替换的辅助类
    class MathCharReplaceInfo {
        double preNumValue;    //  2*2~31+14
        double endNumValue;

        String mathChars;   // 当前的数学符号  ~
        String originalExp;   //   原始的参数传递过来的表达式  2~31
        String needReplace_OldExp;  // 原来的字符串中  需要被替换为 结果的那一部分

        int beginIndex;  //  左起点 第一个 2~31 在 2*2~31+14 的左起点   【2】
        int endIndex;   //  右终点  第一个 2~31 在 2*2~31+14 的右终点索引  【5】
        String mathResult;   //  运算结果
        String ReturnResult;   //  完成  替换后的字符串 表达式

        MathCharReplaceInfo(String exp, String curMathChars) {
            this.originalExp = exp;
            this.mathChars = curMathChars;
            this.preNumValue = getTargetCharFirstPre(exp, curMathChars);
            this.endNumValue = getTargetCharFirstEnd(exp, curMathChars);
            //  返回当前作为独立计算单元的左值 右值 运算符 组成的 表达式
            this.needReplace_OldExp = calculSubSimpleExp(exp, curMathChars);
            // System.out.println("originalExp = "+ originalExp);
            // System.out.println("needReplace_OldExp = "+ needReplace_OldExp);
            this.beginIndex = exp.indexOf(this.needReplace_OldExp);
            this.endIndex = exp.indexOf(this.needReplace_OldExp) + this.needReplace_OldExp.length() - 1;
            this.mathResult = tryCalculSimpleExpValue();  // 计算当前的结果
            // oriExp 原始的表达式     起点beginIndex    终点endIndex    匹配的内容matchStr    替换matchStr的字符串
            this.ReturnResult = replaceNumberValue(exp, beginIndex, endIndex, needReplace_OldExp, mathResult + "");


            // System.out.println(this);
        }


        String tryCalculSimpleExpValue() {
            double resultNumValue = 0;
            String resultNumValueStr = "";

            // System.out.println(" preNum = " + this.preNumValue);
            // System.out.println(" endNum = " + this.endNumValue);
            // System.out.println(" beginIndex = " + this.beginIndex);
            // System.out.println(" endIndex = " + this.endIndex);
            // System.out.println(" simpleExp = " + this.needReplace_OldExp);


            switch (this.mathChars) {    // 10*2~10      2的 10次方
                //  9 个 处理方法                preNum=2【指数】    endNum=10【幂数】
                case "~":
                    double cifang_result = cifang(this.preNumValue, this.endNumValue);
                    resultNumValue = cifang_result;
                    break;

                case "#":
                    //  把当前 所有的# 号 计算数值           // 10*10#  10的阶乘  preNum=10【阶数】    endNum=【幂数】
                    String jiecheng_result = jiecheng(this.preNumValue);
                    resultNumValueStr = jiecheng_result;
                    break;

                case "$":
                    // 2$2   2的平方根    10$2   2的10次方根  // preNum=10【默认为2】    endNum=2【根数】
//                 resultStr = replace_geng(resultStr);
                    double geng_result = 0;
                    double gengshu = this.preNumValue;
                    if (this.preNumValue == 0) {
                        gengshu = 2;
                    }
                    geng_result = geng(gengshu, this.endNumValue);
                    resultNumValue = geng_result;
                    break;

                case "lg":

                    // lg10  10为底数的结果     pre=【默认为空】    endNum=2【底数】
                    double lg_result = lg(this.endNumValue);
                    if (this.preNumValue != 0) {
                        lg_result = this.preNumValue * lg_result;
                    }
                    resultNumValue = lg_result;
                    break;
                case "ln":  // lge  e自然数为底数的结果  ln10
                    // lne  10为底数的结果     pre=【默认为空】    endNum=2【底数】
                    double ln_result = ln(this.endNumValue);
                    if (this.preNumValue != 0) {
                        ln_result = this.preNumValue * ln_result;
                    }
                    resultNumValue = ln_result;
                    break;

                // ⁰¹²³⁴⁵⁶⁷⁸⁹
                case "log":    // 2log2 ; 4log2 ; 以 4为底    2的对数 4?次方 为 2
                    //  pre=4【底数】    endNum=2【对数】
                    double dishu = this.preNumValue;
                    if (this.preNumValue == 0) {
                        dishu = Math.E;  //   没有写 底数   log2   那么 默认就以e为底数
                    }
                    double log_result = log(dishu, this.endNumValue);
                    resultNumValue = log_result;
                    break;

                case "sin":   // 30*sin60   1.2sin342   pre =1.2 【倍数】  endNum=342【正玄度数】
                    double beishu_sin = this.preNumValue;
                    if (this.preNumValue == 0) {
                        beishu_sin = 1;  //   没有写 底数   log2   那么 默认就以e为底数
                    }
                    double sin_result = sin(this.endNumValue);
                    if (beishu_sin != 0) {
                        sin_result = beishu_sin * sin_result;
                    }
                    resultNumValue = sin_result;
                    break;


                case "cos":// 30*cos60   1.2cos355   pre =1.2 【倍数】  endNum=355【余玄度数】
                    double beishu_cos = this.preNumValue;
                    if (this.preNumValue == 0) {
                        beishu_cos = 1;  //   没有写 底数   log2   那么 默认就以e为底数
                    }
                    double cos_result = cos(this.endNumValue);
                    if (beishu_cos != 0) {
                        cos_result = beishu_cos * cos_result;
                    }
                    resultNumValue = cos_result;
                    break;

                case "tan": // 30*tan60   1.2tan344   pre =1.2 【倍数】  endNum=344【正切度数】
                    double beishu_tan = this.preNumValue;
                    if (this.preNumValue == 0) {
                        beishu_tan = 1;  //   没有写 底数   log2   那么 默认就以e为底数
                    }
                    double tan_result = tan(this.endNumValue);
                    if (beishu_tan != 0) {
                        tan_result = beishu_tan * tan_result;
                    }
                    resultNumValue = tan_result;
                    break;

                case "cot":  // 30*cot60   1.2cot111   pre =1.2 【倍数】  endNum=111【余切度数】
                    double beishu_cot = this.preNumValue;
                    if (this.preNumValue == 0) {
                        beishu_cot = 1;  //   没有写 底数   log2   那么 默认就以e为底数
                    }
                    double cot_result = cot(this.endNumValue);
                    if (beishu_cot != 0) {
                        cot_result = beishu_cot * cot_result;
                    }
                    resultNumValue = cot_result;
                    break;

                default:
            }
            if("".equals(resultNumValueStr) ){
                resultNumValueStr = resultNumValue +"";
            }
            return resultNumValueStr;

        }


        //  计算首次匹配到的字符串
        String calculSubSimpleExp(String curExp, String curMathChar) {
            String simpleSubExp = "";
            int mathIndex = curExp.indexOf(curMathChar);
            int expLength = curExp.length();
            String preStr = "";
            String endStr = "";

            //  从 后往前 遍历 得到前缀
            for (int i = mathIndex - 1; i >= 0; i--) {
                String singeNumItem = curExp.charAt(i) + "";

                if (!isNumeric(singeNumItem)) {
                    //  遇到非数值的 那么 直接就 break 出 for循环
                    break;
                }
                preStr = singeNumItem + preStr;
            }

            for (int i = mathIndex + curMathChar.length(); i < expLength; i++) {
                String singeNumItem = curExp.charAt(i) + "";

                if (!isNumeric(singeNumItem)) {
                    //  遇到非数值的 那么 直接就 break 出 for循环
                    break;
                }
                endStr = endStr + singeNumItem;
            }


            simpleSubExp = preStr + curMathChar + endStr;

            return simpleSubExp;
        }

        @Override
        public String toString() {
            return "MathCharReplaceInfo{" +
                    "preNumValue=" + preNumValue +
                    ", endNumValue=" + endNumValue +
                    ", mathChars='" + mathChars + '\'' +
                    ", originalExp='" + originalExp + '\'' +
                    ", needReplace_OldExp='" + needReplace_OldExp + '\'' +
                    ", beginIndex=" + beginIndex +
                    ", endIndex=" + endIndex +
                    ", mathResult=" + mathResult +
                    ", ReturnResult='" + ReturnResult + '\'' +
                    '}';
        }
    }

    // oriExp 原始的表达式     起点beginIndex    终点endIndex    匹配的内容matchStr    替换matchStr的字符串
    static String replaceNumberValue(String oriExp, int beginIndex, int endIndex, String matchStr, String replaceNumberStr) {
        String pre = oriExp.substring(0, beginIndex);
        String middle = oriExp.substring(beginIndex, endIndex + 1);
        String end = oriExp.substring(endIndex + 1, oriExp.length());
        if (!middle.equals(matchStr)) {
            // System.out.println(" 当前匹配到的字符串 和 原始匹配到的字符串 不一致！  出错  得排查!");
        }

        String result = pre + replaceNumberStr + end;
        return result;
    }

    static String clearBlankOut(String exp) {
        String exp_no_out = exp;
        while (exp_no_out.startsWith("(") && exp_no_out.endsWith(")")) {
            exp_no_out = exp_no_out.substring(1, exp_no_out.length() - 1);
        }

        while (exp_no_out.startsWith("[") && exp_no_out.endsWith("]")) {
            exp_no_out = exp_no_out.substring(1, exp_no_out.length() - 1);
        }

        while (exp_no_out.startsWith("{") && exp_no_out.endsWith("}")) {
            exp_no_out = exp_no_out.substring(1, exp_no_out.length() - 1);
        }

        return exp_no_out;
    }

    /*******************固定属性列表 ------Begin *********************/
//  固定属性列表 ------Begin
//固定1  zbin 的 字符串绝对路径
    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin";

    // 固定2 当前执行文件的编号 A1  A2  A3   ... G1   G2   G3 ... Z9
    static File G8_Properties_File = new File(System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator + get_Bat_Sh_FlagNumber(Cur_Bat_Name) + ".properties");
    static InputStream G8_Properties_InputStream;
    static OutputStream G8_Properties_OutputStream;
    static Properties G8_Properties = new Properties();
    static Map<String, String> propKey2ValueList = new HashMap<String, String>();

    // 固定3   当前操作系统的类型
    static OS_TYPE CUR_OS_TYPE = OS_TYPE.Windows;

    //  固定4  从CMD窗口输入得到的 目录关键字参数
    // 当前 SHELL  所在目录  默认是main中的第一个 arg[0] 就是shell路径
    static String CUR_Dir_1_PATH = "";    //  arg[0] 就是shell路径 String 类型
    static File CUR_Dir_FILE;   // 当前 CMDER的路径 File 文件


    // 固定5 从CMD窗口输入得到的 功能 tyoe 索引类型  以及依据索引 选中的 逻辑规则
    // 输入的第一个数值 是 rule的索引   同时搭配  * # 实现不同功能
    static String CUR_TYPE_2_ParamsStr;  //  arg[1] 就是输入的第一个参数  固定 通过 tip输出

    // 固定6  从shell 中获取到的 除了 args[0] 和 args[1] 之外的所有其他的输入参数类型
    static ArrayList<String> CUR_INPUT_ParamStrList = new ArrayList<>();


    // 固定8 当前Shell目录下的 文件类型列表  抽取出来  通用  文件类型Str-文件及合
    static HashMap<String, ArrayList<File>> CUR_Dir_FILETypeMap = new HashMap<String, ArrayList<File>>();
    ;


//  固定属性列表 ------End
    /*******************固定属性列表 ------End *********************/


    // 检测中文 编码序列
    static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    // 获取 运行时 参数
//    static JavaRuntimeInfo JavaRuntimeInfoValue =  new JavaRuntimeInfo();

    // PATH 环境变量值进行当前的保存处理  获取 Path参数


    static String EnvironmentValue = System.getProperties().getProperty("java.library.path");


    static boolean isContainEnvironment(String program) {
        boolean environmentExist = false;
        if (EnvironmentValue.contains(program)) {
            environmentExist = true;
        }
        return environmentExist;
    }


    // A1  ..... A2.
    static String get_Bat_Sh_FlagNumber(String mCur_Bat_Name) {
        String mCharNumber = "error";
        String curBat = mCur_Bat_Name;
        if (mCur_Bat_Name.contains(".sh")) {
            curBat = curBat.replace(".sh", "");
        }

        if (mCur_Bat_Name.contains(".bat")) {
            curBat = curBat.replace(".bat", "");
        }
        if (curBat.contains("_")) {
            String[] arrNameList = curBat.split("_");
            mCharNumber = arrNameList[arrNameList.length - 1];
        } else {
            mCharNumber = curBat;
        }

        return mCharNumber;
    }


    static {
        try {
            if (!G8_Properties_File.exists()) {
                G8_Properties_File.createNewFile();
            }
            G8_Properties_InputStream = new BufferedInputStream(new FileInputStream(G8_Properties_File.getAbsolutePath()));
            G8_Properties.load(G8_Properties_InputStream);
            Iterator<String> it = G8_Properties.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                // // System.out.println("key:" + key + " value: " + G8_Properties.getProperty(key));
                propKey2ValueList.put(key, G8_Properties.getProperty(key));
            }
            G8_Properties_InputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void setProperity() {
        try {
            G8_Properties_OutputStream = new BufferedOutputStream(new FileOutputStream(G8_Properties_File.getAbsolutePath()));
            G8_Properties.store(G8_Properties_OutputStream, "");
            G8_Properties_OutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    enum OS_TYPE {
        Windows,
        Linux,
        MacOS
    }


    static void initSystemInfo() {
        String osName = System.getProperties().getProperty("os.name").toLowerCase();
        if (osName.contains("window")) {
            CUR_OS_TYPE = OS_TYPE.Windows;
            Cur_Bat_Name = Cur_Bat_Name + ".bat";
        } else if (osName.contains("linux")) {
            CUR_OS_TYPE = OS_TYPE.Linux;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
        } else if (osName.contains("mac")) {
            CUR_OS_TYPE = OS_TYPE.MacOS;
            Cur_Bat_Name = Cur_Bat_Name + ".sh";
        }
    }


    public static String clearChinese(String lineContent) {
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }


    static void writeContentToFile(File file, String strParam) {

        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(strParam);
                curBW.flush();
                curBW.close();
                //    // System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                // System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String ReadFileContent(File mFilePath) {

        if (mFilePath != null && mFilePath.exists()) {
            //  // System.out.println("存在  当前文件 "+ mFilePath.getAbsolutePath());
        } else {
            // System.out.println("不存在 当前文件 " + mFilePath.getAbsolutePath());

            return null;
        }
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(mFilePath), "utf-8"));
            String oldOneLine = "";
            int index = 1;
            while (oldOneLine != null) {

                oldOneLine = curBR.readLine();
                if (oldOneLine == null || oldOneLine.trim().isEmpty()) {
                    continue;
                }

                sb.append(oldOneLine + "\n");
//                    // System.out.println("第"+index+"行读取到的字符串:"+oldOneLine);
                index++;


            }
            curBR.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    public static boolean isNumeric(String str) {
        //    如果包含两个. 那么不是一个数值
        if (str.indexOf(".") != str.lastIndexOf(".")) {
            return false;
        }

        for (int i = str.length(); --i >= 0; ) {
            if (".".equals(str.charAt(i) + "")) {
                continue;  //   如果 当前的字符 是  小数点 那么  continue
            }
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    static void writeContentToFile(File file, ArrayList<String> strList) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strList.size(); i++) {
            sb.append(strList.get(i) + "\n");
        }
        try {
            if (file != null && !file.exists()) {
                file.createNewFile();
            }

            if (file != null && file.exists()) {
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                curBW.write(sb.toString());
                curBW.flush();
                curBW.close();
                // System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                // System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static String getBlankPaddingString(int padinglength, String oneStr) {
            String result = "";
            for (int i = 0; i < padinglength; i++) {
                    result = result + oneStr;
        }
        return result;
    }


    static String getPaddingIntString(int index, int padinglength, String oneStr, boolean dirPre) {
        String result = "" + index;
        int length = ("" + index).length();

        if (length < padinglength) {
            int distance = padinglength - length;
            for (int i = 0; i < distance; i++) {
                if (dirPre) {
                    result = oneStr + result;
                } else {
                    result = result + oneStr;
                }

            }

        }
        return result;

    }


    static ArrayList<File> getAllSubFile(File dirFile, String aospPath, ArrayList<String> typeList) {
        if (aospPath == null || "".equals(aospPath)) {
            return getAllSubFile(dirFile.getAbsolutePath(), "", typeList);
        }
        return getAllSubFile(dirFile.getAbsolutePath(), aospPath, typeList);

    }

    static ArrayList<File> getAllSubFile(String rootPath, String aospItemPath, ArrayList<String> typeList) {
        ArrayList<File> allFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath + File.separator + aospItemPath);

        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();
                    //// System.out.println("pathString = " + fileString);
                    for (int i = 0; i < typeList.size(); i++) {
                        String type = typeList.get(i);
                        if ("*".equals(type)) {  // 如果 类型是 * 那么就把 所有的 非目录文件加入列表中
                            File curFile = new File(fileString);
                            if (!curFile.isDirectory()) {
                                allFile.add(curFile);
                                break;
                            }


                        } else {
                            if (fileString.endsWith(type)) {
                                allFile.add(new File(fileString));
                                break;
//                         // System.out.println("file found at path: " + file.toAbsolutePath());
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return allFile;


    }


    static void showNoTypeTip() {
        // System.out.println("用户输入的参数为空  或者 参数不符合规格  请参考如下 重新输入:");
        // System.out.println();  // 输入 提示  输入格式 提示
        // System.out.println();
        // System.out.println();
        // System.out.println();
        // System.out.println();
    }


    static int calculInputTypeIndex(String inputParams) {
        if (inputParams == null) {
            return 0;
        }
        if (isNumeric(inputParams)) {
            return Integer.parseInt(inputParams);
        }
        if (inputParams.contains("_")) {
            String[] mTypeParamArr = inputParams.split("_");
            if (mTypeParamArr.length == 0) {
                return 0;
            }

            for (int i = 0; i < mTypeParamArr.length; i++) {
                String curPositionStr = mTypeParamArr[i];
                if (isNumeric(curPositionStr)) {
                    return Integer.parseInt(curPositionStr);
                }
            }
        }

        return 0;

    }



    static void jisuan(ArrayList<String> expressList) {  //  表达式列表

         ArrayList<Integer> expLengthList = new ArrayList<Integer>();
         ArrayList<String> resultList = new  ArrayList<String>();
         // 所有表达式对其
        for (int i = 0; i < expressList.size(); i++) {
            //  表达式  1++1++1
            String express = expressList.get(i);
            expLengthList.add(express.length());
            resultList.add(tryNextMakeSimple(express));

//             System.out.println( express + "═════════" + tryNextMakeSimple(express));
        }
        Integer maxExpLength = calculMaxLength(expLengthList) ; // 5是额外的补充
        //
        int maxResultPointLength = calculResultPointLength(resultList) ;
//        boolean isEResult =  isE_In_Result(resultList); // 是否有科学表达式
        if(isE_In_Result(resultList))
        System.out.println("E7═千万 "+" E8═亿 "+" E9═十亿 "+" E10═百亿 "+" E11═千亿 "+" E12═万亿(兆) "+" E13═十万亿(十兆) "+" E14═百万亿(百兆) "+" E15═千万亿(千兆) "+" E16═亿亿(京) ");

        for (int i = 0; i < resultList.size(); i++) {
            String express = expressList.get(i);
            String results = resultList.get(i);
            String paddingzExp = paddingExp(express,maxExpLength);
            String paddingzResult = paddingPointResult(results,maxResultPointLength);

            System.out.println(paddingzExp+"   ═════════   "+paddingzResult);
        }


    }

    static boolean isE_In_Result(ArrayList<String> resultList){
        boolean flag = false;

        for (int i = 0; i < resultList.size(); i++) {
            String result = resultList.get(i);
            if(result.contains("E")){
                flag = true;
                break;
            }
        }

        return flag;
    }

    static String  paddingPointResult(String exp , int maxPointLength) {
        String paddingResult = "";
        int curResultPointLength = exp.indexOf(".");
        if(curResultPointLength == -1 ){
            curResultPointLength = exp.length();
        }
        int diffLength = maxPointLength - curResultPointLength;
        String paddingStr = getBlankPaddingString(diffLength," ");
        paddingResult = paddingStr+exp;

        return paddingResult;
    }


    static String paddingExp(String exp , int maxLength){
        String paddingResult = "";
        int expLength = exp.length();
        int diffLength = maxLength-expLength;
        String paddingStr = getBlankPaddingString(diffLength," ");
        paddingResult = exp + paddingStr;


        return paddingResult;


    }

    static int calculResultPointLength(ArrayList<String> resultList){
        int curMaxPointLength = 0;
        for (int i = 0; i < resultList.size() ; i++) {
            String resultItem = resultList.get(i);
            int curPointLength = resultItem.indexOf(".");
            if( curPointLength == -1){
                curPointLength = resultItem.length();
            }
            if(curMaxPointLength < curPointLength){
                curMaxPointLength = curPointLength;
            }
        }
        return curMaxPointLength;
    }

    static Integer calculMaxLength(ArrayList<Integer> expLengthList){
        Integer curLength = null;
        for (int i = 0; i < expLengthList.size() ; i++) {
            Integer curItem = expLengthList.get(i);
            if(curLength == null){
                curLength = curItem;
            }
            if(curLength < curItem){
                curLength = curItem;
            }
        }
        return curLength;
    }
    static void addCurFileTypeMapItemWithKey(String keyType, File curFile) {
        if (CUR_Dir_FILETypeMap.containsKey(keyType)) {
            ArrayList<File> fileList = CUR_Dir_FILETypeMap.get(keyType);
            fileList.add(curFile);
        } else {
            ArrayList<File> fileList = new ArrayList<File>();
            fileList.add(curFile);
            CUR_Dir_FILETypeMap.put(keyType, fileList);
        }
    }

    static void initFileTypeMap(ArrayList<File> subFileList) {
        for (File curFile : subFileList) {
            String fileName = curFile.getName();
            if (!fileName.contains(".")) {
                addCurFileTypeMapItemWithKey("unknow", curFile);
            } else {
                String suffix = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
                addCurFileTypeMapItemWithKey(suffix, curFile);
            }
        }


    }


}
