

import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.JavaRuntimeInfo;
import cn.hutool.system.RuntimeInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;


/**
 * git 学习地址: https://github.com/SiweiZhong/json2Bean
 * https://github.com/JamalJo/Json2Bean
 * https://github.com/liuyu520/json2bean
 *
 * @author zukgit
 */

/**
 * @author zukgit  2019-04-23</br></br>
 * <p>
 * 1. 若json以 { 开头，则用fastjson解析成Map<String,Object>，
 * 依次取出map中的值，若值为null则不处理该字段，若instanceof Integer则该字段处理完毕，该字段对应的类型为Integer。 Double,String时同理。
 * <p>
 * 2. 若值以 { 开头则又是一个新的Bean，类名为map中的键（nameGeneration.formatName格式化后的驼峰式写法），则交给新的Json2Bean处理</br></br>
 * <p>
 * 3. 若值以 [ 开头说明是一个List，泛型参数需要根据[ ] 内部json决定，待内部json处理完毕后返回的就是类型（例如  [{"name":"Zukgit","age":20}]   这个json是一个List,这种情况时泛型参数可以在[ ]内数据处理前就得到，
 * 但 如果json是这样的  [1,2,3]或 ["a","b","c"]  泛型参数就不能随意规定了，只能是Integer或String，所以要等[] 内部json处理完毕后才能得到类型）</br></br>
 * <p>
 * <p>
 * 4. 若map中值以[ 开头说明值对应的json、是一个List，这时用fastjson解析得到List<Object>，若list为null或空，则不处理该字段（因 泛型参数无法确定）
 * 同理，当list中第一个元素  instancof Double时说明是List<Double>，Integer时还要遍历list决定到底是Integer还是Double（例如 [1,2.0,3.0]），若值以 { 开头说明是一个对象，对象名我们无法根据json字符串获取，这时使用nameGeneration.nextName()
 * 生成一个名字作为泛型参数，并构造该类
 * <p>
 * 5. 生成 dot 描述的 graphiz 绘制的语言
 */

public class B6 {
    // ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName)

    static ArrayList<String> dotStringArr = new ArrayList<String>();
    static ArrayList<HashMap<String, ArrayList<ProperityItem>>> arrMapList = new ArrayList<HashMap<String, ArrayList<ProperityItem>>>();
    // 对 ArrayList 进行 排序



    static void tryWriteJsonToFile( ArrayList<String> dotStringArr , File file , String netAddr){

        if (file != null && file.exists()) {

            try {
// String curItem = "#  "+new String(netAddr);
                BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                // curBW.write(curItem);
                for (int i = 0; i < dotStringArr.size(); i++) {
                    curBW.write(dotStringArr.get(i));
                    curBW.newLine();
                }
                curBW.close();
                System.out.println("OK !");
                System.out.println("write json File OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }


    public static String decode(String url)
    {
        try {
            String prevURL="";
            String decodeURL=url;
            while(!prevURL.equals(decodeURL))
            {
                prevURL=decodeURL;
                decodeURL= URLDecoder.decode( decodeURL, "UTF-8" );
            }
            return decodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while decoding" +e.getMessage();
        }
    }
    public static String encode(String url)
    {
        try {
            String encodeURL= URLEncoder.encode( url, "UTF-8" );
            return encodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" +e.getMessage();
        }
    }

    static void tryReadJsonFromFile(StringBuilder sb, File file){

        if (file != null && file.exists()) {

            try {
                BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                String lineStr = "";


                while (lineStr != null) {
                    lineStr = curBR.readLine();
                    if (lineStr == null || lineStr.trim().isEmpty()) {
                        continue;
                    }

                    sb.append(lineStr.trim());
                }
                curBR.close();


                System.out.println("read json File OK !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed !");
        }
    }
    static String outFilepath ="";
    static File outFile = null;
    static String dirPath = "";  // zbin 路径
    public static void main(String[] args) {

        try {

            //     从文件夹读取Json 字符串    Local_test
      //      String mFilePath = System.getProperty("user.dir") + File.separator + "in.txt";


            //===============real-test begin===============
            String mFilePath = null;
            if (args.length >= 1) {
                mFilePath = args[0];
            } else {
                System.out.println("input argument is empty ! retry input again!");
                return;
            }
            //===============real-test end===============


            dirPath = mFilePath.trim().substring(0, mFilePath.lastIndexOf(File.separator));
            System.out.println("dirPath  =  " + dirPath);

            File curFile = new File(mFilePath);
            if (mFilePath != null && !mFilePath.isEmpty() && curFile.exists()) {
                System.out.println("input argument success ! ");
            } else {
                System.out.println("input argument is invalid ! retry input again!");
                return;
            }


            outFilepath =dirPath + File.separator + "B6.gv";

            StringBuilder sb  = new StringBuilder();
			System.out.println("curFile = "+ curFile.getAbsolutePath());
            tryReadJsonFromFile( sb ,  curFile);
            boolean isArrJson = sb.toString().startsWith("[");
            if(isArrJson){
                sb = new StringBuilder("{ arr:"+sb.toString()+"}");
            }


            if(sb.toString().trim().equals("")){
                System.out.println("读取到的Json 文件为空  退出!!");
                return ;
            }

			String firstChar = sb.toString().substring(0,1);
			while( firstChar != null && !firstChar.equals("{")){
				sb = new StringBuilder(sb.toString().trim().substring(1));
				firstChar = sb.toString().substring(0,1);
				         System.out.println("以?开头  firstChar="+ firstChar);
			}
            if(!JSONUtil.isJson(sb.toString().trim())){
                System.out.println("读取到的文件不是标准的Json 格式 退出!  当前读取到的数据为:\n"+ sb.toString() );
				        System.out.println("读取到的文件不是标准的Json 格式 退出!  firstChar 第一个字符为:\n"+ sb.toString().substring(0,1) );
				        System.out.println("读取到的文件不是标准的Json 格式 退出!  第二个字符为:\n"+ sb.toString().substring(1,2) );         
		 return ;
            }

            outFile = new File(outFilepath);
            if(!outFile.exists()){
                outFile.createNewFile();
            }


//            System.out.println(JsonFormat.format(s));
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test5")).execute();
//
//
//            s = "{\"multyList\":[[[{\"name\":\"xm1\",\"age\":19}]]]}";


            //   System.out.println(toujiaoJson);
            String result = new Json2Bean(sb.toString(), "RootBean", null, new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test2")).execute();


            System.out.println("------------------------------");
            System.out.println("result = " + result);
//
//            List<List<List<String>>> list = new ArrayList<>();
//
//            List<List<String>> li1 = new ArrayList<List<String>>();
//            li1.add(Arrays.asList("d", "e", "f"));
//            li1.add(Arrays.asList("d1", "e1", "f1"));
//
//            List<List<String>> li2 = new ArrayList<List<String>>();
//            li2.add(Arrays.asList("d", "e", "f"));
//            li2.add(Arrays.asList("d2", "e2", "f2"));
//
//            list.add(li1);
//            list.add(li2);
//
//
//            s = JSON.toJSONString(list);
//            System.out.println(s);
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test3")).execute();
//
//            System.out.println("------------------------------");
//
//            s = "{\"post_message\":\"[:f002}[:f003}[:f003}[:f004}[:f004}\",\"intlist\":[1,2,3],\"str\":\"{}\"}";
//            System.out.println(JsonFormat.format(s));
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test4")).execute();
//
//            s = "[[[{\"name\":\"xm1\",\"age\":19},{\"name\":\"[xm2\",\"age\":19},{\"name\":\"{xm3\",\"age\":19}],[{\"name\":\"[[xm4\",\"age\":19},{\"name\":\"{{xm5\",\"age\":19}]],[[{\"name\":\"xm6\",\"age\":19},{\"name\":\"xm7\",\"age\":19}],[{\"name\":\"xm8\",\"age\":19}]]]";
//            System.out.println(JsonFormat.format(s));
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test5")).execute();
//
//
//            s = "{\"multyList\":[[[{\"name\":\"xm1\",\"age\":19}]]]}";
//            System.out.println(JsonFormat.format(s));
//            new Json2Bean(s, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test6")).execute();

            System.out.println(" arrArrList.size()" + arrMapList.size());
            int index = 0;
//            arrMapList.sort(new Comparator<HashMap<String, ArrayList<ProperityItem>>>() {
//                @Override
//                public int compare(HashMap<String, ArrayList<ProperityItem>> o1, HashMap<String, ArrayList<ProperityItem>> o2) {
//                    int lengthO1 = 0;
//                    int lengthO2 = 0;
//                    Iterator<Map.Entry<String, ArrayList<ProperityItem>>> itr_o1 = o1.entrySet().iterator();
//                    while (itr_o1.hasNext()) {
//                        Map.Entry<String, ArrayList<ProperityItem>> entry_o1 = itr_o1.next();
//                        String k = entry_o1.getKey();
//                        ArrayList<ProperityItem> o1Arr =  entry_o1.getValue();
//                        lengthO1 = o1Arr.size() + lengthO1;
//                    }
//
//                    Iterator<Map.Entry<String, ArrayList<ProperityItem>>> itr_o2 = o2.entrySet().iterator();
//                    while (itr_o2.hasNext()) {
//                        Map.Entry<String, ArrayList<ProperityItem>> entry_o2 = itr_o2.next();
//                        String k = entry_o2.getKey();
//                        ArrayList<ProperityItem> o2Arr =  entry_o2.getValue();
//                        lengthO2 = o2Arr.size() + lengthO2;
//                    }
//                    if(lengthO1 > lengthO2){
//                        return 1;
//                    } else{
//                        return -1;
//                    }
//                }
//            });
            // https://dreampuf.github.io/GraphvizOnline/#
            dotStringArr.add("digraph {");
                 dotStringArr.add("#    https://dreampuf.github.io/GraphvizOnline/#      Giraphz图形地址 ");
                 dotStringArr.add("#    http://www.bejson.com/jsonviewernew/    Json 数据 \n");
                 dotStringArr.add("#     dot  ./B6.gv -Tpdf -o B6.pdf  生成PDF \n");
				 dotStringArr.add("#     dot  ./B6.gv -Tpng -o B6.png    生成PNG   \n");
            dotStringArr.add("graph [rankdir=BT] \n");
            System.out.println("===============index" + index + "=============== Begin");

            for (Map<String, ArrayList<ProperityItem>> arr : arrMapList) {

                //System.out.println("===============index" + index + "=============== Begin");

                Iterator<Map.Entry<String, ArrayList<ProperityItem>>> itr = arr.entrySet().iterator();

                while (itr.hasNext()) {
                    Map.Entry<String, ArrayList<ProperityItem>> entry = itr.next();
                    String k = entry.getKey();
                    ArrayList<ProperityItem> properArr = entry.getValue();
                    int subindex = 0;
                    // System.out.println("开始打印 ClassName = 【" + k + "】 ");
                    for (ProperityItem properityItem : properArr) {
                        System.out.println("ClassName = 【" + k + "】 index =【" + index + " 】   subIndex=【" + subindex + "】  str=" + properityItem.toString());
//                        subindex++;
                        System.out.println(properityItem.makeNodeString());
                        dotStringArr.add(properityItem.makeNodeString());


                    }
                    for (ProperityItem properityItem : properArr) {
                        //    System.out.println(properityItem.makeNodeRelationString());
                        dotStringArr.add(properityItem.makeNodeRelationString());
                    }

                }
                //   System.out.println("===============index" + index + "=============== End");
                index++;

            }
            dotStringArr.add("} \n");
            System.out.println("==============dotStringArr=============== Begin");
            StringBuilder urlSB = new StringBuilder();
            for (String item : dotStringArr) {
                System.out.println(item);
                urlSB.append(item);
            }
            String encodeStr =  encode(urlSB.toString());
            encodeStr=encodeStr.replaceAll("\\+", "%20");

            encodeStr=encodeStr.replaceAll("\\+", "%20");
            encodeStr=encodeStr.replaceAll("%7B", "%7B%0A%0A%20%20");
            encodeStr=encodeStr.replaceAll("%3B", "%3B%0A%20%20%20%20");

            String netAddr = "https://dreampuf.github.io/GraphvizOnline/#"+encodeStr;
            tryWriteJsonToFile(dotStringArr,outFile,netAddr);
//  浏览器地址最多接受 4096个字符  所以 无法通过编译 .gv 文件来跳转到 对应的 graviz页面


            JavaRuntimeInfo javaRuntimeInfo = new JavaRuntimeInfo();
            System.out.println("=========================runtimeInfo=========================\n"+ javaRuntimeInfo);
            String libPath = javaRuntimeInfo.getLibraryPath();
            String dotPath = "";
            // ;C:\Program Files (x86)\Graphviz2.38\bin\dot.exe;

            System.out.println("libPath = "+libPath);
            if(libPath.contains("Graphviz") && libPath.contains("dot")){
                String[] libArr =  libPath.split(";");
                int length = libArr.length;

                for (int i = 0; i < length; i++) {
                    if(libArr[i].contains("Graphviz") && libArr[i].contains("dot")){
                        dotPath = libArr[i];
                        break;
                    }
                }

            }
			System.out.println("zukgit  dotPath.substring  dotPath ="+ dotPath);
			 String dotDirPath = "";
			if(dotPath != null && !dotPath.isEmpty()){
            dotDirPath = dotPath.substring(0,dotPath.lastIndexOf("\\"));
            dotPath="\""+dotPath+"\"";
            dotDirPath="\""+dotDirPath+"\"";
            // dot  ./B6.gv -Tpdf -o B6.pdf
			System.out.println("dotDirPath 1  = "+dotDirPath);
            File outputPdfFile = new File(outFile.getParentFile().getAbsolutePath()+File.separator+ "B6.pdf");
            String command = " cmd.exe /c cd " +dotDirPath+  "  &&  dot.exe  "+outFile.getAbsolutePath() +" -Tpdf -o  " + outFile.getParentFile().getAbsolutePath()+File.separator+ "B6.pdf";

            System.out.println("command = "+command);
            // RuntimeUtil.exec(command);
            String procResult = execCMD(command); // 生成PDF 文件
            System.out.println("command = "+command +" procResult="+ procResult);

            // command =  cmd.exe /c    C:\Program Files (x86)\Graphviz2.38\bin  &&   dot.exe C:\work_space\eclipse_workplace\B6\B6.gv -Tpdf -o  C:\work_space\eclipse_workplace\B6\B6.pdf
// cmd.exe /c cd "C:\Program Files (x86)\Graphviz2.38\bin"  &&  dot.exe  C:\work_space\eclipse_workplace\B6\B6.gv -Tpdf -o  C:\work_space\eclipse_workplace\B6\B6.pdf
            String command2 = "cmd.exe /C start acrord32  "  +outputPdfFile.getAbsolutePath();
            Thread.sleep(2000);
            String procResult2 = execCMD(command2); // 打开 acrord32 Adobe 阅读  PDF 文件
            System.out.println("command2 = "+command2 +" procResult="+ procResult2);

}
            String command3 = "cmd.exe /C start notepad++  "  +outFile.getAbsolutePath();
            String procResult3 = execCMD(command3); // 使用notepad 打开 gv 文件
            System.out.println("command3 = "+command3 +" procResult3="+ procResult3);

            String command4 = "";
            System.out.println("netAddr =   "+netAddr);
            if(netAddr.length() > 4000){
                command4 = "cmd.exe /C start chrome   https://dreampuf.github.io/GraphvizOnline/ " ;

            }else{

                command4 = "cmd.exe /C start chrome  "+ netAddr ;

            }
            //     String command4 = "cmd.exe /C start chrome  "  +netAddr;

 

            String command5 = "cmd.exe /C start chrome   http://www.bejson.com/jsonviewernew/ " ;

            String procResult5 = execCMD(command5); //  使用 Chrome 打开 JSON在线解析
			
			String procResult4 = execCMD(command4); //  使用 Chrome 打开 Graphiz 在线解析网页
            System.out.println("command5 = "+command5 +" procResult5="+ procResult5);

				if(dotPath != null && !dotPath.isEmpty()){
// command6   生成 Png 照片
            // String command6 = "cmd.exe /C start chrome   http://www.bejson.com/jsonviewernew/ ";
            File pngFile = new File(outFile.getParentFile().getAbsolutePath()+File.separator+ "B6.png");
            //
            //  String command6 = " cmd.exe /c cd " +dotDirPath+  "  &&  dot.exe  "+outFile.getAbsolutePath() +" -Tpng -o  " + outFile.getParentFile().getAbsolutePath()+File.separator+ "B6.png";
            String command6 = " cmd.exe /c cd " +dotDirPath+  "  &&  dot.exe  "+outFile.getAbsolutePath() +" -Tpng -o  " + pngFile.getAbsolutePath();
            String procResult6 = execCMD(command6);
            System.out.println("command6 = "+command6 +" procResult6="+ procResult6);
            // command7   使用照片浏览器打开照片
            String command7 = "rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + pngFile.getAbsolutePath();
            //    RuntimeUtil.exec("rundll32.exe C:\\\\Windows\\\\System32\\\\shimgvw.dll,ImageView_Fullscreen  " + pngFile.getAbsolutePath());
            String procResult7 = execCMD(command7);
            System.out.println("command7 = "+command7 +" procResult7="+ procResult7);
				}
        } catch (Exception e) {

        }

        // System.out.println("Zukgit-----------End");

    }

    public static String execCMD(String command) {
        StringBuilder sb =new StringBuilder();
        try {
            Process process=Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                sb.append(line+"\n");
            }
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    static class ProperityItem {
        String properityName;
        String TypeName;
        String ownnerClassName;
        String refClassName;  //   当 isClassType = true 时  它所指向引用的那个 类名的名字

        String baseTypeInListName;  //   List<Double> 中的 基础类型数据的名称
        String classTypeInListName;  //    List<Class> 中的 类的 名称

        boolean isBaseType;       //  TypeName 是否基础数据类型 Array<String>  和 String  都是基础数据类型
        boolean isClassType;        //  是否是 Class 引用数据类型
        boolean isObjectNullList ;     //  是否是 List<Object> 的 空的数组

        boolean isList;       //  是否是集合   List<>
        boolean isBaseList;   // 是否是基础的数据类型集合  List<String>   List<Double>
        int baseListCount;   // 该属性 包含 List的个数  嵌套数
        boolean isClassList;   // 是否是对象的数据类型集合  List<A>   List<B>
        int classListCount;   //该属性 包含 List的个数  嵌套数

        @Override
        public String toString() {
            return "【 ownnerClassName=" + ownnerClassName + "  properityName=" + properityName + " TypeName=" + TypeName + " refClassName=" + refClassName + "  isBaseType=" + isBaseType + "  isClassType=" + isClassType + "  isList=" + isList + " baseListCount=" + baseListCount + " classListCount=" + classListCount + "  isObjectNullList "+ isObjectNullList+"】";
        }

        ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName) {
            if (ownnerClassName != null && !ownnerClassName.isEmpty()) {
                this.ownnerClassName = ownnerClassName;
            }
            if (properityName != null && !properityName.isEmpty()) {
                this.properityName = properityName;
            }
            if (TypeName != null && !TypeName.isEmpty()) {
                this.TypeName = TypeName;
            }
            if (refClassName != null && !refClassName.isEmpty()) {
                this.refClassName = refClassName;
            }

            if (TypeName != null && !TypeName.isEmpty()) {
                isBaseType = true;
                isClassType = false;
                if (TypeName.contains("String") || TypeName.contains("Double") || TypeName.contains("Integer")) {
                    isBaseType = true;
                    isClassType = false;
                } else {
                    isClassType = true;
                    isBaseType = false;
                }
            }

            if (TypeName != null && !TypeName.isEmpty()) {
                isList = false;
                isBaseList = false;
                isClassList = false;
                baseListCount = 0;
                classListCount = 0;
                if (TypeName.contains("List")) {
                    isList = true;
                    if (TypeName.contains("String")) {
                        isBaseList = true;
                        isClassList = false;
                        baseTypeInListName = "String";
                        baseListCount = StrUtil.count(TypeName, "List");
                    } else if (TypeName.contains("Double")) {
                        isBaseList = true;
                        isClassList = false;
                        baseTypeInListName = "Double";
                        baseListCount = StrUtil.count(TypeName, "List");
                    } else if (TypeName.contains("Integer")) {
                        isBaseList = true;
                        isClassList = false;
                        baseTypeInListName = "Integer";
                        baseListCount = StrUtil.count(TypeName, "List");
                    }else if(TypeName.contains("Object")){  // 包含 Object的 空数组
                        isClassList = true;
                        isBaseList = false;
                        isList = true;
                        classListCount = StrUtil.count(TypeName, "List");
                        isObjectNullList = true;
                        classTypeInListName = "Object";
                    } else {       // List<List<List<SSSS>>>
                        isBaseList = false;
                        isClassList = true;
                        classListCount = StrUtil.count(TypeName, "List");
                        classTypeInListName = TypeName.replaceAll("List", "").replaceAll("<", "").replaceAll(">", "").replaceAll(" ", "").trim();

                    }
                }
            }
        }

        String getLable() {
            if (isClassType) {
                return StrUtil.upperFirst(properityName.trim());
            }
            return "\"" + properityName + "\\n" + "【" + TypeName + "】" + "\"";
        }

        //  ArrayList<String>    =List-String   List【】  String【】 baseListCount == 1
        //  ArrayList<String>   baseListCount == 1     ArrayList<List<String>>  baseListCount == 2
        ArrayList<String> getLableArr() {
            String item2 = "";
            ArrayList<String> stringArr = null;
            if (isList && isBaseType == true && baseListCount > 0) {


                stringArr = new ArrayList<String>();
                for (int i = 0; i < baseListCount; i++) {
                    // stringArr.add("List"+"<【" + TypeName +"】>");

                    stringArr.add("\"" + getBaseListShowString(properityName, TypeName, i) + "\"");
                }
                // baseTypeInListName String
                stringArr.add("\"" + properityName + "_item" + "\\n" + baseTypeInListName + "\"");

            } else if ( isList && isClassType  &&  isObjectNullList){  // 如果是空的列表的话 List<Object>
                stringArr = new ArrayList<String>();

                for (int i = 0; i < classListCount; i++) {
                    String strItem = "\"" + getListShowString(properityName, TypeName, i) + "\"";
                    // data\nList<【A___B___C】>
                    stringArr.add(strItem);
                    System.out.println(" properityName = " + properityName +"    TypeName="+ TypeName+"  index =" + i + "strItem = "+ strItem);

                }
                item2 =  "\"" +classTypeInListName + "\\n" + properityName + "\"" ;
                stringArr.add(item2); // A  类名  \\n 属性名

            }else if (isList && isClassType == true && classListCount > 0) {  // 如果是 List 列表的话
                // // 获取 数组的  data 【List<A>】    properityName=data     TypeName=List<A>
                // List<【List<A>】> data    List<>
                stringArr = new ArrayList<String>();

                for (int i = 0; i < classListCount; i++) {
                    String strItem = "\"" + getListShowString(properityName, TypeName, i) + "\"";
                    // data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]  tempstr=A___B___C
                    stringArr.add(strItem);
                    System.out.println(" properityName = " + properityName +"    TypeName="+ TypeName+"  index =" + i + "strItem = "+ strItem);
                    // properityName = data
                    // TypeName=List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
                    // index =0
                    // strItem = "data\nList<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"
                }
                item2 =  "\"" +classTypeInListName + "\\n" + properityName + "\"" ;
                stringArr.add(item2); // A  类名  \\n 属性名
            }

            System.out.println("classTypeInListName = "+ classTypeInListName +" properityName" + properityName );
            System.out.println("item2 = "+ item2 +" stringArr.size()"+ stringArr.size());  // stringArr.size() =2

            //classTypeInListName = A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0] properityName = data
            // item1 = data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]  tempstr=A___B___C
            //  item2 = A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]\ndata
            return stringArr;
        }


        String getBaseListShowString(String properityName, String strListTypeName, int index) {
            int count = StrUtil.count(strListTypeName, "List");
            if (count == 1 || index == 0) {
                return properityName + "\\n" + getPretyTypeName(TypeName, baseTypeInListName, 0);
                // data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]  tempstr=A___B___C
            } else {
                return getPretyTypeName(TypeName, baseTypeInListName, index);
            }
            //  properityName=data     TypeName=List<A>     classTypeInListName=A
//List<List<List<String>>>   data    【 count 是总的List的个数】   【index =0 】 是显示的个数 显示的个数是 count - index
// 【count 3 , index 0】  data \n List<【List<List<A>>】>
// 【count 3 , index 1】  List<【<List<A>】>
// 【count 3 , index 2】  List<【A】>


        }


        String getListShowString(String properityName, String strListTypeName, int index) {

            // properityName = data
            // TypeName=List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
            // index =0
            // strItem = "data\nList<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"

            int count = StrUtil.count(strListTypeName, "List");
            if (count == 1 || index == 0) {  // 如果只包含一个List
                //  strItem = "data\nList<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"
                String curItem = properityName + "\\n" + getPretyTypeName(TypeName, classTypeInListName, 0);
                // data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]  tempstr=A___B___C
                return curItem;
            } else {
                return getPretyTypeName(TypeName, classTypeInListName, index);
            }
            //  properityName=data     TypeName=List<A>     classTypeInListName=A
//List<List<List<A>>>   data    【 count 是总的List的个数】   【index =0 】 是显示的个数 显示的个数是 count - index
// 【count 3 , index 0】  data \n List<【List<List<A>>】>
// 【count 3 , index 1】  List<【<List<A>】>
// 【count 3 , index 2】  List<【A】>


        }

        String getPretyTypeName(String strListTypeName, String classTypeInListName, int index) {

            // properityName = data
            // TypeName=List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
            // index =0
            // strItem = "data\nList<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"
            // classTypeInListName
            //  zclassTypeInListName  =A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]

            System.out.println("zclassTypeInListName  =" + classTypeInListName);
            int count = StrUtil.count(strListTypeName, "List");
            if (index > count) {
                System.out.println("getPretyTypeName 索引错误  index=" + index + "   count =" + count);
            }
// 【count 3 , index 0  keepListCount=3 】  data \n List<【List<List<A>>】>
// 【count 3 , index 1  keepListCount=2 】  List<【<List<A>】>
// 【count 3 , index 2 keepListCount=1 】  List<【A】>

            // List<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"
            int keepListCount = count - index;
            String fixClassTypeInListName = new String(classTypeInListName);
//    // List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
            String listString = buildListString(fixClassTypeInListName, keepListCount); //  List<A>   List<List<List<A>>>
            System.out.println("zlistString " +listString);
            String tempstr= "";
            String resultStr="";
            if(listString.contains("\\n") && listString.contains("___")){
                tempstr =  classTypeInListName.substring(0,classTypeInListName.indexOf("\\n"));
                resultStr = listString.replaceAll(tempstr, "【" + tempstr + "】"); //  List<【A】>   List<List<List<【A】>>>
            } else{

                resultStr = listString.replaceAll(classTypeInListName, "【" + classTypeInListName + "】"); //  List<【A】>   List<List<List<【A】>>>
            }


            // resultStr List<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]  tempstr=A___B___C
            System.out.println("resultStr " +resultStr+"  tempstr="+ tempstr);
            return resultStr;
        }

        String buildListString(String classTypeInListName, int listCount) {
            // List<A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]>"



            //  classTypeInListName  =A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
            //  classTypeInListNameX E
            String arrEnd="";
            String listNameWithoutN = "";
            if(classTypeInListName.contains("\\n")  ){
                arrEnd = classTypeInListName.substring(classTypeInListName.indexOf("\\n"),classTypeInListName.length());
                listNameWithoutN =  classTypeInListName.substring(0,classTypeInListName.indexOf("\\n"));
            }
            System.out.println("classTypeInListNameX " +classTypeInListName);
            String curStr = "";
            String curStrKeep = "";
            for (int i = 0; i < listCount; i++) {
                if (i == 0) {
                    curStr = "List<" + (listNameWithoutN.isEmpty()?classTypeInListName:listNameWithoutN) + ">" + curStrKeep;
                    curStrKeep = curStr;
                } else {
                    curStr = "List<" + curStrKeep + ">";
                    curStrKeep = curStr;
                }
            }
// 【index 1  List<A>】
// 【index 2  List<List<A>>】
// 【index 3  List<List<List<A>>>】
            String result =  curStrKeep+arrEnd;
            // List<A___B___C>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]

            //  zlistString List<>
            System.out.println("result " +result);
            return result;
        }

        ArrayList<String> getShapeArr() {

//            boolean isBaseType;       //  TypeName 是否基础数据类型 Array<String>  和 String  都是基础数据类型
////            boolean isClassType;        //  是否是 Class 引用数据类型
////            boolean isList;       //  是否是集合   List<>
////            boolean isBaseList;   // 是否是基础的数据类型集合  List<String>   List<Double>
////            boolean isClassList;   // 是否是对象的数据类型集合  List<A>   List<B>
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
//            if(isBaseType == true && isList == false){
//                return "box";
//            } else if(isBaseType == true && isList == true){
//                return "doubleoctagon";
//            }else if(isBaseType == false && isList == false){
//                return "circle";
//            }else if(isBaseType == false && isList == true){
//                return "doublecircle";
//            }
//            return "";
            ArrayList<String> stringArr = null;
            if (isList && isBaseType == true && baseListCount > 0) {

                stringArr = new ArrayList<String>();
                for (int i = 0; i < baseListCount; i++) {
                    stringArr.add("shape=doubleoctagon");
                }
                stringArr.add("shape=box");
            } else if ( isList && isClassType  &&  isObjectNullList){
                for (int i = 0; i < classListCount; i++) {
                    stringArr.add("shape=doubleoctagon");
                }

            } else if (isList && isClassType == true && classListCount > 0) {
                stringArr = new ArrayList<String>();
                for (int i = 0; i < classListCount; i++) {
                    stringArr.add("shape=doubleoctagon");
                }
                stringArr.add("shape=circle");
            }
            return stringArr;
        }


        String getShape() {

//            boolean isBaseType;       //  TypeName 是否基础数据类型 Array<String>  和 String  都是基础数据类型
////            boolean isClassType;        //  是否是 Class 引用数据类型
////            boolean isList;       //  是否是集合   List<>
////            boolean isBaseList;   // 是否是基础的数据类型集合  List<String>   List<Double>
////            boolean isClassList;   // 是否是对象的数据类型集合  List<A>   List<B>
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
            if (isBaseType == true && isList == false) {
                return "shape=box";
            } else if (isBaseType == true && isList == true) {
                return "shape=doubleoctagon";
            } else if (isBaseType == false && isList == false) {
                return "shape=circle";
            } else if (isBaseType == false && isList == true && !isObjectNullList) {
                return "shape=doublecircle";
            }else if (isBaseType == false && isList == true && isObjectNullList) {
                return "shape=doubleoctagon";
            }
            return "";
        }


        String getStyle() {
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
            if (isBaseType == true && isList == false) {
                return "";   // 基础数据类型没有颜色
            } else if (isBaseType == true && isList == true) {
                return "";  // 基础数据类型没有颜色
            } else if (isBaseType == false && isList == false) {
                return "style=filled";   // 单个 Class的 颜色是 红色
            } else if (isBaseType == false && isList == true) {
                return "style=filled";  // 多个 List<Class> 是 黄色
            }
            return "";
        }


        ArrayList<String> getStyleArr() {
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
            ArrayList<String> stringArr = null;
            if (isList && isBaseType == true && baseListCount > 0) {  // 基础类型数据集合

                stringArr = new ArrayList<String>();
                for (int i = 0; i < baseListCount; i++) {
                    stringArr.add("");
                }
                stringArr.add("");
            } else if (isList && isClassType == true && classListCount > 0) {  // 对象类型颜色
                stringArr = new ArrayList<String>();
                for (int i = 0; i < classListCount; i++) {
                    stringArr.add("style=filled");
                }
                stringArr.add("style=filled");
            }
            return stringArr;

        }


        ArrayList<String> getColorArr() {
//// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
//            // box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
//            if(isBaseType == true && isList == false){
//                return "";   // 基础数据类型没有颜色
//            } else if(isBaseType == true && isList == true){
//                return "";  // 基础数据类型没有颜色
//            }else if(isBaseType == false && isList == false){
//                return "red";   // 单个 Class的 颜色是 红色
//            }else if(isBaseType == false && isList == true){
//                return "yellow";  // 多个 List<Class> 是 黄色
//            }
//            return "";


            ArrayList<String> stringArr = null;
            if (isList && isBaseType == true && baseListCount > 0) {  // 基础类型数据集合

                stringArr = new ArrayList<String>();
                for (int i = 0; i < baseListCount; i++) {
                    stringArr.add("");
                }
                stringArr.add("");
            }  else if ( isList && isClassType  &&  isObjectNullList) {
                for (int i = 0; i < classListCount; i++) {
                    stringArr.add("color=purple");
                }

            }else if (isList && isClassType == true && classListCount > 0) {  // 对象类型颜色
                stringArr = new ArrayList<String>();
                for (int i = 0; i < classListCount; i++) {
                    stringArr.add("color=yellow");
                }
                stringArr.add("color=red");
            }
            return stringArr;

        }


        String getColor() {
// box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
            // box【普通数据类型的数据】   doubleoctagon【普通数据类型的数组】     circle【 Class 引用 】     doublecircle 【List<Class 引用>】
            if (isBaseType == true && isList == false) {
                return "";   // 基础数据类型没有颜色
            } else if (isBaseType == true && isList == true) {
                return "";  // 基础数据类型没有颜色
            } else if (isBaseType == false && isList == false) {
                return "color=red";   // 单个 Class的 颜色是 红色
            } else if (isBaseType == false && isList == true && isObjectNullList) {
                return "color=purple";  // 多个 json为[]  的 对象的 颜色是蓝色的
            }else if (isBaseType == false && isList == true ) {
                return "color=yellow";  // 多个 List<Class> 是 黄色
            }
            return "";
        }

        String getNodeName() {
            if (TypeName.equals("Class")) {
                return properityName;
            }
            String nodeName = ownnerClassName + "_" + properityName;
            while (nodeName.startsWith("_")) {
                nodeName = nodeName.substring(1, nodeName.length());
            }
            while (nodeName.endsWith("_")) {
                nodeName = nodeName.substring(0, nodeName.length() - 1);
            }

            return nodeName;
        }

        ArrayList<String> getNodeNameArr() {
            ArrayList<String> stringArr = null;
            if (isList && isBaseType == true && baseListCount > 0) {

                stringArr = new ArrayList<String>();
                for (int i = 0; i < baseListCount; i++) {
                    stringArr.add(ownnerClassName + "_" + properityName + "_Arr" + i);
                }
                // stringArr.add(ownnerClassName + "_" + properityName + "_ArrEnd" + "_" + baseTypeInListName);
                stringArr.add(ownnerClassName + "_" + properityName + "_ArrEnd" + "_" + baseTypeInListName);
            } else if (isList && isClassType == true && isObjectNullList ){
                stringArr = new ArrayList<String>();
                stringArr.add(ownnerClassName + "_" + properityName);
            }else if (isList && isClassType == true && classListCount > 0 && !isObjectNullList) {
                stringArr = new ArrayList<String>();
                for (int i = 0; i < classListCount; i++) {
                    stringArr.add(ownnerClassName + "_" + properityName + "_Arr" + i);
                }
                //  类名在处理的时候  使用 类自己的名字
                stringArr.add(classTypeInListName);
            }
            return stringArr;
        }


        String getRefNodeName() {  //

            return refClassName;
        }


        String makeNodeString() {  //
            if (isBaseType == true && isList == false) {
                // RootBean_doubleList_Double [label="Double" shape=box] 单独的 基础类型变量
                return getNodeName() + " [label=" + getLable() + " " + getShape() + " ]";
            } else if (isBaseType == true && isList == true) {
                // RootBean_multyList_List [label="RootBean_multyList_List" shape=doubleoctagon] 基础类型变量 集合
                ArrayList<String> nodeArr = getNodeNameArr();
                ArrayList<String> labelArr = getLableArr();
                ArrayList<String> colorArr = getColorArr();
                ArrayList<String> shapeArr = getShapeArr();
                ArrayList<String> styleArr = getStyleArr();

                String item = "";
                StringBuilder sb = new StringBuilder();
                int length = nodeArr.size();
                for (int i = 0; i < length; i++) {
                    item = nodeArr.get(i).trim() + "[ label=" + labelArr.get(i).trim() + "  " + shapeArr.get(i).trim() + " " + styleArr.get(i).trim() + " " + colorArr.get(i) + "] \n ";
                    sb.append(item);
                }
                return sb.toString();
            } else if (isClassType == true && isList == false && isClassList == false && TypeName.equals("Class")) {
                //RootBean_Data [label="RootBean_Data" shape=circle  style=filled color=red]  单独的类 引用的变量
                return getNodeName() + " [label=" + getLable() + " " + getShape() + " " + getStyle() + " " + getColor() + " ]";

            } else if(isClassType == true && isList == true && isClassList == true && isObjectNullList){

                System.out.println("Test Here this.toString ="+ this.toString());
                // 【 ownnerClassName=null  properityName=image_list TypeName=List<Object> refClassName=null  isBaseType=false  isClassType=true  isList=true baseListCount=0 classListCount=1  isObjectNullList true】
// 【 ownnerClassName=Highlight  properityName=title TypeName=List<Object> refClassName=null  isBaseType=false  isClassType=true  isList=true baseListCount=0 classListCount=1  isObjectNullList true】


                String returnNode = getNodeName() + " [label=" + getLable() + " " + getShape() + " " + getStyle() + " " + getColor() + " ]";
                System.out.println("returnNodeX ="+ returnNode );
                // B_source [label=Source shape=doublecircle style=filled color=blue ]
                // 【 ownnerClassName=B  properityName=source TypeName=List<Object> refClassName=null
                return returnNode;

            }else if (isClassType == true && isList == true && isClassList == true && !isObjectNullList ) {
                // RootBean_Data_Item [label="RootBean_Data_Item" shape=doublecircle style=filled color=yellow]
                ArrayList<String> nodeArr = getNodeNameArr();
                ArrayList<String> labelArr = getLableArr();
                ArrayList<String> colorArr = getColorArr();
                ArrayList<String> shapeArr = getShapeArr();
                ArrayList<String> styleArr = getStyleArr();

                String item = "";
                StringBuilder sb = new StringBuilder();
                int length = nodeArr.size();
                for (int i = 0; i < length - 1; i++) {
                    // 最后一个是类 Class 属性的话 那么 就 不再创建 Node
                    String labelItem = labelArr.get(i).trim();   //  这里不正常
                    System.out.println(" labelItemepre = "+ labelItem);
                    System.out.println(" labelItemeback = "+ labelItem);
                    // item1 = data\nList<【A___B___C】>\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]  tempstr=A___B___C
                    //  item2 = A___B___C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]\ndata
                    item = nodeArr.get(i).trim() + "[ label=" + labelItem.trim() + "  " + shapeArr.get(i).trim() + "  " + styleArr.get(i).trim() + " " + colorArr.get(i) + "] \n ";
                    sb.append(item);
                }
                return sb.toString();

            }

            return "";
        }


        String getWeight() {
            if (TypeName.equals("Class")) {
                return "[weight= 30]";
            }
            if (isClassType == true && isList == true && isClassList == true) {

                return "[weight= 50]";
            }

            if (isClassType == true && isList == false && isClassList == false) {

                return "[weight= 20 ]";
            }

            if (isBaseType == true && isList == true) {

                return "[weight= 5]";
            } else{

                return "[weight= 1 ]";
            }
        }

        String makeNodeRelationString() {  //

            if (isBaseType == true && isList == false) {
                // 单独的 基础类型变量 关系  	RootBean -> RootBean_code
                if (ownnerClassName != null && !ownnerClassName.trim().isEmpty()) {
                    System.out.println("make-> 7  ownnerClassName="+getNodeName()+"    nodeName.get(0)="+getNodeName());
                    return ownnerClassName + " -> " + getNodeName() + " " + getWeight();
                }


            } else if (isBaseType == true && isList == true) {
//                RootBean -> RootBean_multyList
//                RootBean_multyList -> RootBean_multyList_List
//                RootBean_multyList_List -> RootBean_multyList_List_List
//                RootBean_multyList_List_List -> RootBean_multyList_List_List_Item

                if (ownnerClassName != null && !ownnerClassName.trim().isEmpty()) {

                    ArrayList<String> nodeName = getNodeNameArr();
                    int length = getNodeNameArr().size();
                    String item1 = "";
                    String item2 = "";
                    StringBuilder sb = new StringBuilder();
                    if (length > 0) {
                        System.out.println("make-> 6  ownnerClassName="+ownnerClassName+"    nodeName.get(0)="+nodeName.get(0));
                        sb.append(ownnerClassName + " -> " + nodeName.get(0) + " " + getWeight() + "\n");
                        for (int i = 1; i < length; i++) {
                            item1 = nodeName.get(i - 1);
                            item2 = nodeName.get(i);
                            System.out.println("make-> 5  item1="+item1+"    item2="+item2);
                            sb.append(item1 + " -> " + item2 + " " + getWeight() + "\n");
                        }
                    }

                    return sb.toString();
                }
            } else if (isClassType == true && isList == false && isClassList == false && refClassName != null) {
//   对于单独的 Class  它的 关系 已经 通过 属性 来设置了   所以 这里不用设置关系

                System.out.println("make-> 4  refClassName="+refClassName+"    properityName="+properityName);
                return refClassName + " -> " + properityName + " " + getWeight();

//   refClassName=B   这里替换为   ownnerClassName 错误   寻找 new Preperty的地方
                // ClassName = 【Extra】 index =【6 】   subIndex=【0】  str=【 ownnerClassName=Extra  properityName=topic TypeName=Topic refClassName=B  isBaseType=false  isClassType=true  isList=false baseListCount=0 classListCount=0】
// make-> 4  refClassName=Extra    properityName=Topic_2048
// make-> 4  refClassName=Extra    properityName=Topic
// make-> 4  refClassName=B    properityName=topic
// make-> 4  refClassName=B    properityName=topic_2048
            } else if(isClassType == true && isList == true && isClassList == true && isObjectNullList){

                System.out.println("make->x   refClassName="+refClassName+"    properityName="+properityName +" this.toString="+this.toString());

                //make->x   refClassName=null    properityName=image_detail this.toString=【 ownnerClassName=B  properityName=image_detail TypeName=List<Object> refClassName=null  isBaseType=false  isClassType=true  isList=true baseListCount=0 classListCount=1  isObjectNullList true】

// returnNodeX =Highlight_source [label=Source shape=doublecircle style=filled color=blue ]
                ArrayList<String> arr = getNodeNameArr();
                for(String nullArrStr : arr){
                    System.out.println("nullArrStr = "+ nullArrStr);
                }
                String nullRela = ownnerClassName + " -> " + arr.get(0) + " " + getWeight();
                System.out.println("nullRela = "+ nullRela);

                //     Line 39: Highlight_source [label=Source shape=doublecircle style=filled color=blue ]
                //    Line 43: Highlight -> Highlight_source_Arr0 [weight= 50]

                return nullRela;

            }else if (isClassType == true && isList == true && isClassList == true && !isObjectNullList) {
//  对象数组 列表       RootBean.java 的   List<A> data;
//  RootBean -> RootBean_Data  [weight=10]
//  RootBean_Data  -> RootBean_Data_Count

                ArrayList<String> nodeName = getNodeNameArr();
                // makeNodeRelationString = [RootBean_data_Arr0, A___B___C]
                System.out.println("makeNodeRelationString = " + nodeName);  // zzj
                int length = getNodeNameArr().size();
                String item1 = "";
                String item2 = "";
                StringBuilder sb = new StringBuilder();
                if (length > 0) {
                    System.out.println("make-> 3  ownnerClassName="+ownnerClassName+"    nodeName.get(0)="+nodeName.get(0));
                    sb.append(ownnerClassName + " -> " + nodeName.get(0) + " " + getWeight() + "\n");
                    for (int i = 1; i < length; i++) {
                        item1 = nodeName.get(i - 1);
                        item2 = nodeName.get(i);
                        if(!item2.contains("___")){
                            System.out.println("make-> 2  item1="+item1+"    item2="+item2);
                            sb.append(item1 + " -> " + item2 + " " + getWeight() + "\n");
                        } else{
                            String[] subArr =   item2.split("___");
                            int lengthSub = subArr.length;
//make-> 1 item1=RootBean_data_Arr0    subArr[x]=A
//make-> 1 item1=RootBean_data_Arr0    subArr[x]=B
// make-> 1 item1=RootBean_data_Arr0    subArr[x]=C\nA[1][2][3][4][5][6][7][8][10][11][12][13][14][15][16][17][18]\nB[9][19]\nC[0]
                            for (int x=0 ;x < lengthSub ; x++){

                                String itemStr = new String(subArr[x]);
                                System.out.println("make-> 1 item1="+item1+"    subArr[x]pre="+itemStr);
                                if(itemStr.trim().contains("\\n")){
                                    itemStr = itemStr.substring(0,itemStr.indexOf("\\n"));
                                }
                                System.out.println("make-> 1 item1="+item1+"    subArr[x]back="+itemStr);
                                sb.append(item1 + " -> " + itemStr + " " + getWeight() + "\n");
                            }
                        }

                    }
                }
                return sb.toString();
            }
            return "";
        }


    }

    public interface BeanGenerator {

        /**
         * @param className 类名
         * @param map       字段及类型
         * @throws IOException
         */
        void writeBean(String className, Map<String, Object> map) throws IOException;

        /**
         * @param list List<....>
         * @throws IOException
         */
        void writeList(String list) throws IOException;
    }


    public class CamelCaseBeanGenerator extends MyBeanGenerator {

        public CamelCaseBeanGenerator(String packName) {
            super(packName);
        }

        @Override
        public void writeBean(String className, Map<String, Object> map) throws IOException {
            System.out.println(" className1   = " + className);  // 找到对应的 Class 中的类    没有执行该类
            File file = new File("src/" + packName.replace(".", "/"));
            if (!file.exists() || file.exists() && file.isFile()) {
                file.mkdirs();
            }
            System.out.println(" className2  = " + className);
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file, className + ".java")));
            bw.write("package ");
            bw.write(packName);
            bw.write(";\n");
            bw.write("import java.util.List;\n\n");

            bw.write("/**\n");
            bw.write(" *auto generate\n");
            bw.write(" *\n");
            bw.write(" *@author Zukgit\n");
            bw.write(" *\n");
            bw.write(" */\n");

            bw.write("public class ");
            bw.write(className);
            bw.write("{\n");

            map = sortMapByKey(map);
            Set<Map.Entry<String, Object>> set = map.entrySet();

            for (Map.Entry<String, Object> entry : set) {
                bw.write("    ");
                bw.write(entry.getValue().toString());
                bw.write(" ");
                bw.write(formatReference(entry.getKey()));
                bw.write(";\n");
            }
            bw.write("\n");
            set = map.entrySet();

            for (Map.Entry<String, Object> entry : set) {

                bw.write("    public ");
                bw.write(entry.getValue().toString());
                bw.write(" get");
                bw.write(capitalUpper(entry.getKey()));
                bw.write("(){\n");
                bw.write("        ");
                bw.write("return ");
                bw.write(formatReference(entry.getKey()));

                bw.write(";\n    }\n\n");

                //////////////////////////

                bw.write("    public void ");
                bw.write("set");
                bw.write(capitalUpper(entry.getKey()));
                bw.write("(");
                bw.write(entry.getValue().toString());
                bw.write(" ");
                bw.write(formatReference(entry.getKey()));
                bw.write("){\n");
                bw.write("        ");
                bw.write("this.");
                bw.write(formatReference(entry.getKey()));
                bw.write("=");
                bw.write(formatReference(entry.getKey()));
                bw.write(";\n    }\n");

                bw.write("\n");

            }
            bw.write("}");

            bw.close();
        }

        public Map<String, Object> sortMapByKey(Map<String, Object> oriMap) {

            Map<String, Object> sortedMap = new TreeMap<String, Object>(new Comparator<String>() {
                public int compare(String key1, String key2) {

                    return key1.compareTo(key2);
                }
            });
            sortedMap.putAll(oriMap);
            return sortedMap;
        }

        private String capitalUpper(String getset) {
            String s = formatReference(getset);
            char[] chs = s.toCharArray();
            if (chs[0] > 'a' && chs[0] < 'z') {
                chs[0] = (char) (chs[0] - 32);
            }

            return new String(chs);

        }

        private String formatReference(String ref) {

            char[] chs = ref.toCharArray();

            if (chs[0] >= 'A' && chs[0] <= 'Z') {
                chs[0] = (char) (chs[0] + 32);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chs[0]);
            for (int i = 1; i < chs.length; i++) {
                if (chs[i] == '_') {
                    continue;
                }
                if (chs[i - 1] == '_') {
                    if (chs[i] >= 'a' && chs[i] <= 'z') {
                        stringBuilder.append((char) (chs[i] - 32));
                    } else {
                        stringBuilder.append(chs[i]);
                    }
                } else {
                    stringBuilder.append(chs[i]);
                }
            }

            return stringBuilder.toString();
        }
    }

    static  volatile String returnString ="";
    static class Json2Bean {
        public String json;
        public static JsonParse jsonParse;
        public String name;
        public static NameGenerator nameGeneration;
        public static BeanGenerator generationBean;
        private ArrayList<ProperityItem> arrList;
        public String fatherName;


        @Override
        public String toString() {
            System.out.println("============== 打印 JavaBean: " + this.name+" 开始==============");
            int index = 0 ;
            for (ProperityItem  item: arrList){

                System.out.println("index="+ index +"  item="+item);
                index++;
            }
            System.out.println("============== 打印 JavaBean: " + this.name+" 结束==============");
            return "this.name="+ this.name + "   this.fatherName="+ this.fatherName;
        }

        public Json2Bean(String json, String name, String fatherName, NameGenerator nameGeneration, JsonParse jsonParse, BeanGenerator generationBean) {
            this.json = json;
            this.name = name;
            this.nameGeneration = nameGeneration;
            this.jsonParse = jsonParse;
            this.fatherName = fatherName;
            this.generationBean = generationBean;
            arrList = new ArrayList<ProperityItem>();
            // ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName)
            // 类本身 所添加的 项
            System.out.println(" 新建Bean类 X1  = " + this.name);
            if(this.fatherName != null){
                this.fatherName =  filter(this.fatherName);
            }

            if(this.name != null){
                this.name =  filter(this.name);
            }



            while(this.fatherName != null && this.fatherName.contains(" ") ){
                this.fatherName = this.fatherName.replaceAll(" ","");
            }


            while(this.name != null && this.name.contains(" ") ){
                this.name = this.name.replaceAll(" ","");
            }

            arrList.add(new ProperityItem(null, this.name, "Class", this.fatherName));  // 类创建的Bean 所以该处  不可能 产生
            // make-> 4  refClassName=B    properityName=topic
            System.out.println(" 新建Bean类 X2  = " + this.name);
        }


        public static String filter(String content){
            if (content != null && content.length() > 0) {
                char[] contentCharArr = content.toCharArray();
                for (int i = 0; i < contentCharArr.length; i++) {
                    if (contentCharArr[i] < 0x20 || contentCharArr[i] == 0x7F) {
                        contentCharArr[i] = 0x20;
                    }
                }
                return new String(contentCharArr);
            }
            return "";
        }


        public String execute() {
            String curRentName = "";
            //
            try {
                if (this.name != null) {
                    curRentName = this.name;
                    System.out.println("开始解析Bean类: this.name  " + this.name +"  name ="+ name);
                    System.out.println("zukgit4_1_1_非空  this.name :" + this.name+"  name ="+ name +" curRentName ="+ curRentName);

                }else{
                    System.out.println("zukgit4_1_2 空  this.name :" + this.name+"  name ="+ name +" curRentName ="+ curRentName);
                }
                // zukgit2  json ={"source":[],"abstract":[],"title":[[0,4]]}
                System.out.println("zukgit2  json =" + json);
                //zukgit2  json =[[[{"name":"xm1","age":19},{"name":"[xm2","age":19},{"name":"{xm3","age":19}],[{"name":"[[xm4","age":19},{"name":"{{xm5","age":19}]],[[{"name":"xm6","age":19},{"name":"xm7","age":19}],[{"name":"xm8","age":19}]]]
                if(json.startsWith("{}")){ // 为空{} 的对象 创建 Bean    AA
                    System.out.println("zukgit6_1  ");
                    parseMap();
                    HashMap curMap = new HashMap();
                    arrList.sort(new Comparator<ProperityItem>() {
                        @Override
                        public int compare(ProperityItem o1, ProperityItem o2) {

                            if(o1.isClassList && o2.isBaseList){ // 【类集合】大于【基础属性集合】
                                return -1 ;
                            } else if(o1.isClassType &&  o2.isBaseList){ // 【类基础】大于【基础属性集合】
                                return -1 ;
                            } else if(o1.isList && !o2.isList){  // 【列表】大于【非列表】
                                return -1 ;
                            } else if(o1.isClassType && o2.isBaseType){
                                return -1;
                            }else{
                                return 0;
                            }

                        }
                    });

                    curMap.put(this.name, arrList);
                    arrMapList.add(curMap);
                    return null;

                } else if (json.startsWith("{")) {
                    System.out.println("zukgit3   ");
                    parseMap();
                    System.out.println("zukgit3_1  ");
                    HashMap curMap = new HashMap();
                    // zukgit2  json ={"source":[],"abstract":[],"title":[[0,4]]}
                    arrList.sort(new Comparator<ProperityItem>() {
                        @Override
                        public int compare(ProperityItem o1, ProperityItem o2) {

                            if(o1.isClassList && o2.isBaseList){ // 【类集合】大于【基础属性集合】
                                return -1 ;
                            } else if(o1.isClassType &&  o2.isBaseList){ // 【类基础】大于【基础属性集合】
                                return -1 ;
                            } else if(o1.isList && !o2.isList){  // 【列表】大于【非列表】
                                return -1 ;
                            } else if(o1.isClassType && o2.isBaseType){
                                return -1;
                            }else{
                                return 1;
                            }

                        }
                    });
                    curMap.put(this.name, arrList);
                    arrMapList.add(curMap);
                    return null;
                } else if(json.startsWith("[]")){  // 如果当前的 对象为 [] 即为空
                    //  获取这个
                    System.out.println("AA1 json= "+ json +" properItem="+ this.toString());  // [对象1, 对象2]
                    return "Object";
                }else if (json.startsWith("[") && json.length() > 2) {  //  ["小康","社会"]
                    System.out.println("zukgit4  ");  // [对象1, 对象2]
                    String clz = parseArray();  // 解析对象 返回  List<Onjext>  xxx  等等
                    returnString = new String(clz)+this.fatherName;
                    // father_name
                    System.out.println("zukgitx8  ");
                    if(this.fatherName != null && this.fatherName.equals("RootBean")){   // 继续点
// RootBean_Arr Clz = List<A_B_C>  name= null  fathername=RootBean curRentName=
                        System.out.println("ZZXX RootBean_Arr Clz = "+ clz +"   name= "+ name +"  fathername="+this.fatherName+" curRentName="+ curRentName);
                    }
                    if (name == null && curRentName.trim().isEmpty()) {
                        System.out.println("zukgit4_1_2_空  this.name :" + this.name+"  name ="+ name +" curRentName ="+ curRentName+" clz="+clz+ "  不会执行 writeList(clz) 方法了  操蛋！");
                        System.out.println("zukgitx7  ");
                        return clz;
                    } else{

                        System.out.println("zukgit4_1_1_非空  this.name :" + this.name+"  name ="+ name +" curRentName ="+ curRentName+" clz="+clz);
                    }

//                    Line 214: zukgit4_1  clz =List<String>name =null
//                    Line 299: zukgit4_1  clz =List<Integer>name =null
//                    Line 301: zukgit4_1  clz =List<>name =null
//                    Line 416: zukgit4_1  clz =List<D>name =null
//                    Line 421: zukgit4_1  clz =List<A_B_C>name =null
//                    Line 467: zukgit4_1  clz =List<E>name =null
                    System.out.println("zukgitx6  ");
                    System.out.println("zukgit4_2  name != null   非空的情况才会在这里 在这里书写 writeList  zzj");
                    generationBean.writeList(clz);
                    HashMap curMap = new HashMap();
                    if(name != null){
                        System.out.println("zukgitx5  ");
                        arrList.sort(new Comparator<ProperityItem>() {
                            @Override
                            public int compare(ProperityItem o1, ProperityItem o2) {

                                if(o1.isClassList && o2.isBaseList){ // 【类集合】大于【基础属性集合】
                                    return -1 ;
                                } else if(o1.isClassType &&  o2.isBaseList){ // 【类基础】大于【基础属性集合】
                                    return -1 ;
                                } else if(o1.isList && !o2.isList){  // 【列表】大于【非列表】
                                    return -1 ;
                                } else if(o1.isClassType && o2.isBaseType){
                                    return -1;
                                }else{
                                    return 1;
                                }

                            }
                        });
                        curMap.put(name, arrList);
                        arrMapList.add(curMap);
                        System.out.println("zukgitx4  ");
                        return clz;
                    } else if(curRentName != null && !curRentName.trim().isEmpty()){

                        arrList.sort(new Comparator<ProperityItem>() {
                            @Override
                            public int compare(ProperityItem o1, ProperityItem o2) {

                                if(o1.isClassList && o2.isBaseList){ // 【类集合】大于【基础属性集合】
                                    return -1 ;
                                } else if(o1.isClassType &&  o2.isBaseList){ // 【类基础】大于【基础属性集合】
                                    return -1 ;
                                } else if(o1.isList && !o2.isList){  // 【列表】大于【非列表】
                                    return -1 ;
                                } else if(o1.isClassType && o2.isBaseType){
                                    return -1;
                                }else{
                                    return 1;
                                }

                            }
                        });
                        curMap.put(curRentName, arrList);
                        arrMapList.add(curMap);
                        System.out.println("zukgitx3  ");
                        return clz;

                    }else{
                        System.out.println("zukgitx2  ");
                        return clz;
                    }
                } else {
                    System.out.println("zukgitx1  ");
                    System.out.println("开始解析Bean类:" + this.name + " 失败 ");
                }
                return null;
            } catch (Exception e) {
                e.fillInStackTrace();
                System.out.println(" 发生异常 ！ Exception =");
                e.printStackTrace();

            } finally {
                return "";
            }
        }

        private void parseMap() throws IOException {
            //  parseMap() json={"source":[],"abstract":[],"title":[[0,4]]}
            Map<String, Object> map = jsonParse.toMap(json);
            System.out.println(" parseMap() json="+ json);
            Iterator<Map.Entry<String, Object>> itr = map.entrySet().iterator();
            String childName_arr = null;
            while (itr.hasNext()) {
                Map.Entry<String, Object> entry = itr.next();
                String k = entry.getKey();
                Object v = entry.getValue();
                if (v == null || v.toString().equals("[]")) {
                    // old 逻辑: 如果发现 当前的列表为空的话 就从  属性map 中删除它
                    // new 逻辑: 如果发现 当前的列表为空的话 就设置它的属性是 List<Object> null  不需要创建类对象 但需要有 这个 Property
                    System.out.println("AA  k ="+ k +"   v="+v + " this.toString()"+this.toString()+ "   json="+ json);
//  AA  k =abstract   v=[] this.toString()this.name=Highlight   this.fatherName=B   json={"source":[],"abstract":[],"title":[[0,4]]}
                    entry.setValue("List<Object>");

//============== 打印 JavaBean: Highlight 开始==============
//index=0  item=【 ownnerClassName=null  properityName=Highlight TypeName=Class refClassName=B  isBaseType=false  isClassType=true  isList=false baseListCount=0 classListCount=0】
//============== 打印 JavaBean: Highlight 结束==============
//  AA  k =image_list   v=[] this.toString()this.name=B   this.fatherName=null   json=
                    //  itr.remove();         ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName)

// AA  k =source   v=[] this.toString()this.name=Highlight   this.fatherName=B   json={"source":[],"abstract":[],"title":[[0,4]]}
                    String ownerClassName="";
                    if(this.fatherName != null && this.name != null){
                        ownerClassName = this.name;
                    } else if(this.name == null && this.fatherName != null){
                        ownerClassName = this.fatherName;

                    } else if(this.name != null && this.fatherName == null ){

                        ownerClassName = this.name;
                    } else{
                        ownerClassName = null;
                    }
                    ProperityItem item = new ProperityItem(ownerClassName, k, entry.getValue() + "", this.name == null ? this.fatherName:null);
                    arrList.add(item);
                    continue;
                }
                if (v instanceof Integer) {
                    entry.setValue("Integer");
                } else if (v instanceof BigDecimal) {
                    entry.setValue("Double");
                } else if (v instanceof String) {
                    entry.setValue("String");
                } else {
                    String childJson = v.toString();  // key 是 对象中 非基础数据类型   value 可能还是空 需要设置
                    if (childJson.endsWith("{}")) {  // 空的对象  那么 Object

                        String childName = nameGeneration.formatName(k);  // nima
                        entry.setValue(childName);
                        String key = entry.getKey();
                        String valueName =    new Json2Bean(childJson, childName, this.name, nameGeneration, jsonParse, generationBean).execute();
                        // entry.setValue("valueName");
                        System.out.println("zchildJson="+ childJson +"  valueName="+ valueName+"  key="+ key +"  childName="+ childName+"    this.name="+ this.name);
                    } else if (childJson.startsWith("{")) {

                        String childName = nameGeneration.formatName(k);
                        entry.setValue(childName);
                        new Json2Bean(childJson, childName, this.name, nameGeneration, jsonParse, generationBean).execute();
                    }else if(childJson.startsWith("[]")){  // 是空的对象的话
                        String childName = nameGeneration.formatName(k); // 产生名字
                        entry.setValue(childName);
                        new Json2Bean(childJson, childName, this.name, nameGeneration, jsonParse, generationBean).execute();

                    } else if (childJson.startsWith("[")) {

                        childName_arr = new Json2Bean(childJson, null, this.name, nameGeneration, jsonParse, generationBean).execute();

                        if(childName_arr== null || childName_arr.trim().equals("")){

                            if(returnString != null  &&  !returnString.trim().isEmpty() && returnString.contains(this.name) ){
                                String item = new String( returnString);
                                item = item.replaceAll(this.name,"");
                                childName_arr = item;
                            }
                        }
                        // List<A_B_C> 会走这里     zzj  继续点
                        if(((String)k).equals("data")) {
                            System.out.println("ZZXX childJson.startsWith parseMap    name[fatherName]="+ name + "    this.name[fatherName]="+ this.name +" key ="+ k+"   childName[Clz]="+ childName_arr);
                            // childJson.startsWith parseMap    name=RootBean    this.name=RootBean key =data   childName=空  这里应该返回 List<A_B_C>
                        }


                        entry.setValue(childName_arr);



                    } else {
                        entry.setValue("String");
                    }

                }
                //     // ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName)
                arrList.add(new ProperityItem(this.name, k, entry.getValue() + "", this.name == null ? this.fatherName:null));
//         ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName)
                // zkey = topic zvalue = Topic this.fatherName=B childName = null  this.name=Extra
                // zkey = topic_2048 zvalue = Topic_2048 this.fatherName=B childName = null  this.name=Extra
                System.out.println(" zkey = " + k + " zvalue = " + entry.getValue() +" this.fatherName="+ this.fatherName +" childName = "+ childName_arr +"  this.name="+ this.name );

            }

            System.out.println("writeBean  name ="+ name);
            generationBean.writeBean(name, map);


        }

        public static boolean isContainChinese(String str) {
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(str);
            if (m.find()) {
                return true;
            }
            return false;
        }

        private String parseArray() throws IOException {
            List<Object> list = jsonParse.toArray(json);   //  ["小康","社会"]
            if (list == null || list.size() == 0) {
                return null;
            }

            Object firstObj = list.get(0);
            System.out.println("Zukgit firstObj =  " + firstObj.toString());

            if (firstObj instanceof Integer) {
                for (int i = 1; i < list.size(); i++) {
                    Object v = list.get(i);
                    if (v instanceof BigDecimal) {
                        System.out.println(" childName 4=  List<Double>");
                        //    returnStr = "List<Double>";
                        return "List<Double>";
                    }
                }
                System.out.println(" childName 5 =  List<Integer>");
                //   returnStr = "List<Double>";
                return "List<Integer>";
            } else if (firstObj instanceof BigDecimal) {
                System.out.println(" childName 6=  List<Double>");
                // returnStr = "List<Double>";
                return "List<Double>";

            } else if (firstObj instanceof String) {
                System.out.println(" childName 7=  List<String>");
                // returnStr = "List<Double>";
                return "List<String>";
            }


            if (!firstObj.toString().startsWith("{") && !firstObj.toString().startsWith("[")) {


                return "List<String>";
            }

            String childName = "";
            String arrReturenString = "";
            //   Object v = list.get(0);  // 只拿取  [{1},{2},{3} ....]  只拿取第一个 对象  但往往有些Json的 第一个位置  第二个位置 第三个位置 往往是不同的对象
            // 在这里要判断  它的数组里面的属性是否是相同的  相同的话  取一个分析 就可以       数组对象属性不同的话 那么就要每个拿出来分析 并新创建对象

            List<Object> diffObjectInArr = checkObjectSame(list);   //
            System.out.println("diffObjectInArr  =  " + diffObjectInArr.size());
            String returnStr = "";
            if (diffObjectInArr != null) {
                if (diffObjectInArr.size() == 1) {
                    Object v = diffObjectInArr.get(0);
                    if (v instanceof Integer) {
                        for (int i = 1; i < list.size(); i++) {
                            v = list.get(i);
                            if (v instanceof BigDecimal) {
                                System.out.println(" childName 8=  List<Double>");
                                returnStr = "List<Double>";
                                return "List<Double>";
                            }
                        }
                        System.out.println(" childName 99 =  List<Integer>");
                        returnStr = "List<Double>";
                        return "List<Integer>";
                    } else if (v instanceof BigDecimal) {
                        System.out.println(" childName 10=  List<Double>");
                        returnStr = "List<Double>";
                        return "List<Double>";

                    } else if (v instanceof String) {
                        System.out.println(" childName 11=  List<String>");
                        returnStr = "List<Double>";
                        return "List<String>";
                    } else {
                        String childJson = v.toString();

                        if (childJson.startsWith("{}")) {
                            return "List<Object>";
                        } else if (childJson.startsWith("{")) {
                            childName = nameGeneration.nextName();

                            new Json2Bean(childJson, childName, name, nameGeneration, jsonParse, generationBean).execute();
                            System.out.println(" childName  12= " + childName);
                            //  arrList.add("List<" + childName + ">");
                            returnStr = "List<" + childName + ">";
                            return "List<" + childName + ">";
                        } else if (childJson.startsWith("[")) {
                            childName = new Json2Bean(childJson, null, this.name, nameGeneration, jsonParse, generationBean).execute();
                            System.out.println(" childName 13= " + childName); // 这里返回为空 curRentName = clz=List<>
                            //  arrList.add("List<" + childName + ">");
                            if(childName == null || childName.trim().isEmpty() || childName.trim().equals("null")){
                                childName = "Object";
                            }
                            returnStr = "List<" + childName + ">";
                            return "List<" + childName + ">";

                        } else {
                            System.out.println(" childName 14= " + childName);
                            //    arrList.add("List<String>" + childName);
                            returnStr = "List<" + childName + ">";
                            return "List<String>";
                        }

                    }
                } else {
                    int ZCount = 0;
//  对于多个 数组中 多个   Object的 处理

                    for (Object object : diffObjectInArr) {

//                        String childJson = object.toString();
//                        String className = nameGeneration.nextName();
//
//                        System.out.println("ZCount=" + ZCount + " iffObjectInArr.size =" + diffObjectInArr.size() + " this.name=" + name + "    Zukgit  childName = " + childName + " className =" + className + " childJson=" + childJson);
//                        new Json2Bean(childJson, className, this.name, nameGeneration, jsonParse, generationBean).execute();
//                        System.out.println("ZCount=" + ZCount + "   Zukgit  childJson = " + childJson);
//                        ZCount++;


                        String childJson = object.toString();
                        System.out.println("ZCount = "+ ZCount+"   Zukgit_Object  childJson= "+ childJson);
                        System.out.println(" zukgit childJson = " + childJson);

                        if (childJson.startsWith("{}")) {
                            arrReturenString = "List<Object>";
                        } else if (childJson.startsWith("{")) {
                            childName = nameGeneration.nextName();

                            new Json2Bean(childJson, childName, name, nameGeneration, jsonParse, generationBean).execute();

                            //  arrList.add("List<" + childName + ">");
                            curIndexStr =curIndexStr + "\\n"+childName  ;    // /nA[][][][]
                            int diffObjectSize = diffObjectInArr.size();
                            int indexArrSize =  classArr.size();   // <A___B___C>/nA[][][][]/nB[][][]/nC[][][] 的位置不对
                            if(diffObjectSize == indexArrSize){
                                ArrayList<Integer> curIndexArr = classArr.get(ZCount);
                                for(Integer intx : curIndexArr){
                                    curIndexStr=curIndexStr+"["+intx+"]";
                                }
                            }

                            // A___B___C  ==> A[1]___B[2]___C[3]
                            arrReturenString = arrReturenString+childName +  "___"; // <A___B___C>/nA[][][][]/nB[][][]/nC[][][]
                            System.out.println("diffObjectInArr.size"+diffObjectInArr.size()+"   childName 1= " + childName+"  arrReturenString"+arrReturenString+"    childJson= "+ childJson);


//                            diffObjectInArr.size3   childName 1= A  arrReturenStringA___
//                            diffObjectInArr.size3   childName 1= B  arrReturenStringA___B___
//                            diffObjectInArr.size3   childName 1= C  arrReturenStringA___B___C___


                            //  return "List<" + childName + ">";     // 这里没有返回 "List<" + childName + ">"; 导致  程序中 缺 List<>
                        } else if (childJson.startsWith("[")) {
                            childName = new Json2Bean(childJson, null, this.name, nameGeneration, jsonParse, generationBean).execute();
                            System.out.println(" childName 2= " + childName);
                            //  arrList.add("List<" + childName + ">");
                            arrReturenString = "【" + childName + "】" + arrReturenString;
                            //       return "List<" + childName + ">";

                        } else {
                            System.out.println(" childName 3= " + childName);
                            //    arrList.add("List<String>" + childName);
                            arrReturenString = "【" + childName + "】";
                            return "List<String>";
                        }
                        ZCount++;
                    }
                    classArr.clear();
                }
            }

            while(arrReturenString.endsWith("_")){
                arrReturenString = arrReturenString.substring(0,arrReturenString.length()-1);

            }
            if(arrReturenString.trim().isEmpty()){
                arrReturenString = "Object";
            }
            String endResult = "List<"+arrReturenString+">";
            if(!curIndexStr.trim().isEmpty()){
                endResult = endResult+curIndexStr;
            }

            // endResult=List<【C】【B】【A】>\nA[1][2]\n[3][4][5]\n[6][7][8]
            System.out.println("zendResult=" + endResult +  "arrReturenString ="+  arrReturenString +" nima ######################");
            return endResult;
        }

        static String curIndexStr =""; //\nA[1][2]\nB[3]\nC[4]
        static List<Object> checkObjectSame(List<Object> srcList) {  // 对象和对象相同
            // 来到这里的都是 [{},{},{}] 样式的数据

            boolean isDiffClass = false;
            List<Object> temp = new ArrayList<Object>();
            int length = srcList.size();
            if (length == 0) {
                return null;
            }
            int index_y = 0;
            System.out.println("=========== Json 原始长度 srcList.length = " + srcList.size() + "===========Begin ");
            for (Object object : srcList) {
                System.out.println("index =" + index_y + "  object =" + object.toString());
                index_y++;

            }
            System.out.println("=========== Json 原始长度 srcList.length = " + srcList.size() + "===========End ");
            Object firstObject = srcList.get(0);
            HashSet<Set<String>> diffObjSet = new HashSet<Set<String>>();
            ArrayList<Integer> indexList = new ArrayList<Integer>();
            for (int index = 0; index < length; index++) {
                Object curObject = srcList.get(index);
                if (curObject instanceof JSONObject && firstObject instanceof JSONObject) {
                    Set<String> curKeySet = ((JSONObject) curObject).keySet();
                    Set<String> firstKeySet = ((JSONObject) firstObject).keySet();


                    if (!firstKeySet.equals(curKeySet)) {

                        isDiffClass = true;
                    } else {
                        isDiffClass = false;
                    }


                    // 判断 数据列表中 有多少个 Object 的    缺斤少两的 同一归为一个Object
//                    if (curKeySet.equals(firstKeySet)) {
//                        if (!diffObjSet.contains(curKeySet)) {
//                            indexList.add(index);
//                            System.out.println("当前 index = " + index + "    相同的 KeySet 键值集合  ");
//                            diffObjSet.add(curKeySet);  // 如果 相同 则 放入
//                        }
//                    } else {
//                        if (!diffObjSet.contains(curKeySet) && checkMaxValue(diffObjSet, curKeySet)) {
//                            diffObjSet.add(curKeySet);
//                            indexList.add(index);
//                            System.out.println("当前 index = " + index + "    不相同的 KeySet 键值集合  ");
//                        }
//                        System.out.println("难道这里会打印！ ");
//                    }


                } else {

                    System.out.println("当前 index = " + index + "     不能转为  JSONObject?  curObject= " + curObject.toString());
                }


            }
            System.out.println("isDiffClass = " + isDiffClass);

            if (!isDiffClass) {
                temp.add(srcList.get(0));
                return temp;

            }
            //   处理 那些  不同  Object [{A:1,B:2},{C:3,D:4}......] 样式的数据
            temp = toCheckDiff(srcList);

            return temp;
        }

        static ArrayList<ArrayList<Integer>> classArr =new ArrayList<ArrayList<Integer>>();

        static List<Object> toCheckDiff(List<Object> srcList) {
            List<Object> temp = new ArrayList<Object>();
            int length = srcList.size();
            // 获取到 所有的 Key  以及 所有的Value
//   两个集合的交集大于 当前size的一半  那么 就认为 它们是同一个类
            // 两次 循环 找出 和自己 相似的那个 集合  然后 把 它们的 索引放到一起
            Set<String> allSetKey = new HashSet<String>();
            Map<Integer,Integer> intMap = new  HashMap<Integer,Integer>();
            int classCategory = 0;
            // 【1,3】  【1,4】 【1,5】  【1,6】
            // 【2,7】【2，8】
            // 【3,9】【3，10】

            for(int i =0 ; i < length ; i++){
                JSONObject curObjectA  = (JSONObject)srcList.get(i);
                allSetKey.addAll(curObjectA.keySet());
                for(int j =0 ; j < length ; j++){
                    if(i == j){
                        continue;
                    }
                    JSONObject curObjectB  = (JSONObject)srcList.get(j);
                    Set<String>  aSet =  new HashSet<String>(curObjectA.keySet());
                    Set<String>  bSet =  new HashSet<String>(curObjectB.keySet());
                    int aSize = aSet.size();
                    int bSize = aSet.size();
                    if(aSet.size() >= bSet.size()){ // 总是以大Set去 交集 小的Set
                        aSet.retainAll(bSet);
                        if(aSet.size() > (aSize/2)){
                            intMap.put(i,j);
                        }
                    } else{
                        bSet.retainAll(aSet);
                        if(bSet.size() > (bSize/2)){
                            intMap.put(i,j);
                        }
                    }
                }
            }


            Map.Entry<Integer , Integer> entry;

            Iterator iterator = intMap.entrySet().iterator();


            int xIndex = 0;
            while( iterator.hasNext() ){
                entry = (Map.Entry<Integer, Integer>) iterator.next();
                Integer key = entry.getKey();  //Map的Value
                Integer value = entry.getValue();  //Map的Value
                System.out.println("【 key=" +key+"  value=" +value+"】");
                if(xIndex == 0){
                    ArrayList<Integer> arrItem = new  ArrayList<Integer>();
                    arrItem.add(key);
                    arrItem.add(value);
                    classArr.add(arrItem);
                    xIndex++;
                    continue;
                }


                ListIterator<ArrayList<Integer>>  outArray =    classArr.listIterator();
                //   for(ArrayList<Integer> arrBean : classArr){
                while (outArray.hasNext()) {

                    ArrayList<Integer>     arrBean = (ArrayList<Integer>)outArray.next();
//                    for(Integer intIndex : arrBean){
//                        System.out.println("intIndex = "+ intIndex);
//
// if(arrBean.contains(key) || arrBean.contains(value) || intIndex == key || intIndex == value){
//                            arrBean.add(key);
//                            arrBean.add(value);
//                        }else{
//                            ArrayList<Integer> newItem = new  ArrayList<Integer>();
//                            newItem.add(key);
//                            newItem.add(value);
//     classArr.add(newItem);
//                        }
//

                    //  Iterator<Integer> iteratorInteger = arrBean.iterator();
                    ListIterator<Integer> iteratorInteger =     arrBean.listIterator();
                    while (iteratorInteger.hasNext()) {
                        Integer curInteger = iteratorInteger.next();
                        System.out.println("curInteger  = "+ curInteger);
                        if (arrBean.contains(key) || arrBean.contains(value) || curInteger == key || curInteger == value) {
                            // 循环中 增加
                            if(!arrBean.contains(key) && arrBean.contains(value) ){
                                iteratorInteger.add(key);
                                break;
                            }
                            if(!arrBean.contains(value) && arrBean.contains(key) ){
                                iteratorInteger.add(value);
                                break;
                            }

                        } else{

// 【 key=9  value=19】
//curInteger  = 1
//
//
//【 key=19  value=9】
// curInteger  = 1

                            ArrayList<Integer> arrItemNew = new  ArrayList<Integer>();
                            if(key < value) {
                                arrItemNew.add(key);
                                arrItemNew.add(value);
                            }else{
                                arrItemNew.add(value);
                                arrItemNew.add(key);
                            }
                            if(!classArr.contains(arrItemNew)){
                                outArray.add(arrItemNew);
                                break;
                            }
                            continue;


                        }
                    }
                    break;

                }
            }

// 没有包含索引的那个类 是单独的类
//  具有相同 Value , 以及 该Value 对应的 Key  为 相同的类
            System.out.println("classArr.size = "+ classArr.size());

            for(int index_value = 0 ; index_value < classArr.size() ; index_value++){
                System.out.println("=============数组 ==" + index_value +" Begin=============");
                Collections.sort(classArr.get(index_value));
                for(Integer item : classArr.get(index_value)){
                    System.out.println("item = "+ item);
                }
                System.out.println("=============数组 ==" + index_value +" End=============");
            }

            for(int y=0;y<length;y++){
                if(intMap.get(y) == null){  // 说明 该索引对应的 {}  为单独存在的  不与其他的项相同
                    temp.add(srcList.get(y));
                }
// 存在相同的项的话  就从  classArr 中寻找那个 KeySet 最大的
            }

            List<Object> sameObjectMax = getSameObjectMax(srcList ,classArr ) ;

            for(int y=0;y<length;y++){
                if(intMap.get(y) == null){  // 说明 该索引对应的 {}  为单独存在的  不与其他的项相同
                    // 往   ArrayList<ArrayList<Integer>> classArr 也 添加这个索引 单独在一个ArrayList
                    ArrayList<Integer> newItem_alone = new  ArrayList<Integer>();
                    newItem_alone.add(y);
                    classArr.add(newItem_alone);
                }
// 存在相同的项的话  就从  classArr 中寻找那个 KeySet 最大的
            }

            classArr.sort(new Comparator<ArrayList<Integer>>() {
                @Override
                public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                    int minIndexO1 = 0;
                    int minIndexO2 = 0;

                    for(int x =0 ; x < o1.size();x++){
                        int temp = o1.get(x);
                        if(x == 0){
                            minIndexO1 = o1.get(x);
                        }
                        if(temp < minIndexO1){
                            minIndexO1 = temp;
                        }
                    }
                    for(int x =0 ; x < o2.size();x++){
                        int temp = o2.get(x);
                        if(x == 0){
                            minIndexO2 = o2.get(x);
                        }
                        if(temp < minIndexO2){
                            minIndexO2 = temp;
                        }
                    }

                    if(minIndexO1 < minIndexO2){
                        return -1;
                    }else{
                        return 1;
                    }
                }
            });
            if(sameObjectMax != null){

                System.out.println("sameObjectMax.size = "+sameObjectMax.size());
                temp.addAll(sameObjectMax);
            }



//【 key=1  value=18】
//【 key=2  value=18】
//【 key=3  value=18】
//【 key=4  value=18】
//【 key=5  value=18】
//【 key=6  value=18】
//【 key=7  value=18】
//【 key=8  value=18】、
//【 key=10  value=18】
//【 key=11  value=18】
//【 key=12  value=18】
//【 key=13  value=18】
//【 key=14  value=18】
//【 key=15  value=18】
//【 key=16  value=18】
//【 key=17  value=18】
//【 key=18  value=17】
//
//【 key=9  value=19】
//【 key=19  value=9】
            return temp;
        }

        static List<Object> getSameObjectMax(List<Object>  srcList ,  ArrayList<ArrayList<Integer>> classArr) {
            List<Object> itemList = new ArrayList<Object>();
            System.out.println("maxLenthObj= X2  classArr.size()="+ classArr.size()  +" srcList.size"+ srcList.size());
            for (int index_value = 0; index_value < classArr.size(); index_value++) {
                JSONObject maxLenthObj = null ;
                System.out.println("maxLenthObj= X1");

                for(Integer index_value_y :  classArr.get(index_value)){
                    if(maxLenthObj == null){
                        maxLenthObj = (JSONObject)srcList.get(classArr.get(index_value).get(0));
                    }
                    System.out.println("index_value:"+index_value+"    index_value_y="+index_value_y);
                    JSONObject currentObj = (JSONObject)srcList.get(index_value_y);
                    if(currentObj == null){
                        System.out.println("尼玛 空 currentObj= "+currentObj);
                        continue;
                    }

                    int curLength =  currentObj.keySet().size();
                    int maxLength =   maxLenthObj.keySet().size();
                    if(curLength >= maxLength){
                        maxLenthObj = currentObj;
                    }

                }
                itemList.add(maxLenthObj);
                System.out.println("maxLenthObj= "+maxLenthObj);
            }
            System.out.println("maxLenthObj= X3");
            return itemList;
        }

        static boolean checkMaxValue(HashSet<Set<String>> hashset, Set<String> itemSet) {
            boolean flag = false;
            Iterator<Set<String>> it = hashset.iterator();
            while (it.hasNext()) {
                Set<String> curSet = it.next();
                // 如果当前 添加的到 hashset的 项 set<String>   包含 当前循环找到的 set<String>  那么删除 这个 当前循环到的  set<String>
                if (itemSet.containsAll(curSet)) {
                    hashset.remove(curSet);
                    flag = true;
                } else if (curSet.containsAll(itemSet)) { //  如果   添加的到 hashset的 已经有 item 完全 包含 那么就   不添加该项
                    flag = false;
                    return false;
                } else {
                    // 【data[16] 有 60项 】 data[13]有 56项   但是 data[16] 的 60项中却没有  data[13] 中的 summary 这项
                    //   curSet.retainAll()
                    // 那么 把当前的 扫描的 Item  和 当前参数的 Item  合并   并把 当前item 删除

                    Set<String> result = new HashSet<String>();
                    result.addAll(itemSet);
                    result.addAll(curSet);
                    hashset.remove(curSet);
                    hashset.add(result);
                    flag = false;
                }
            }


            return flag;
        }

    }


    static class JsonFormat {

        /**
         * 默认每次缩进两个空格
         */
        private static final String empty = "  ";

        public static String format(String json) {
            try {
                json = removeUnUsedSpaces(json);
                int empty = 0;
                char[] chs = json.toCharArray();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < chs.length; ) {
                    //若是双引号，则为字符串，下面if语句会处理该字符串
                    if (chs[i] == '\"') {

                        stringBuilder.append(chs[i]);
                        i++;
                        //查找字符串结束位置
                        for (; i < chs.length; ) {
                            //如果当前字符是双引号，且前面有连续的偶数个\，说明字符串结束
                            if (chs[i] == '\"' && isDoubleSerialBackslash(chs, i - 1)) {
                                stringBuilder.append(chs[i]);
                                i++;
                                break;
                            } else {
                                stringBuilder.append(chs[i]);
                                i++;
                            }

                        }
                    } else if (chs[i] == ',') {
                        stringBuilder.append(',').append('\n').append(getEmpty(empty));

                        i++;
                    } else if (chs[i] == '{' || chs[i] == '[') {
                        empty++;
                        stringBuilder.append(chs[i]).append('\n').append(getEmpty(empty));

                        i++;
                    } else if (chs[i] == '}' || chs[i] == ']') {
                        empty--;
                        stringBuilder.append('\n').append(getEmpty(empty)).append(chs[i]);

                        i++;
                    } else {
                        stringBuilder.append(chs[i]);
                        i++;
                    }


                }


                return stringBuilder.toString();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return json;
            }

        }

        static boolean isDoubleSerialBackslash(char[] chs, int i) {
            int count = 0;
            for (int j = i; j > -1; j--) {
                if (chs[j] == '\\') {
                    count++;
                } else {
                    return count % 2 == 0;
                }
            }

            return count % 2 == 0;
        }

        /**
         * 缩进
         *
         * @param count
         * @return
         */
        static String getEmpty(int count) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < count; i++) {
                stringBuilder.append(empty);
            }

            return stringBuilder.toString();
        }


        static String removeUnUsedSpaces(String json) {
            char[] chs = json.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < chs.length; ) {
                //若是双引号，则为字符串，下面if语句会处理该字符串
                if (chs[i] == '\"') {

                    stringBuilder.append(chs[i]);
                    i++;
                    //查找字符串结束位置
                    for (; i < chs.length; ) {
                        //如果当前字符是双引号，且前面有连续的偶数个\，说明字符串结束
                        if (chs[i] == '\"' && isDoubleSerialBackslash(chs, i - 1)) {
                            stringBuilder.append(chs[i]);
                            i++;
                            break;
                        } else {
                            stringBuilder.append(chs[i]);
                            i++;
                        }

                    }
                } else {

                    if (chs[i] == ' ' || chs[i] == '\t' || chs[i] == '\n') {
                        i++;
                        continue;
                    }

                    stringBuilder.append(chs[i]);
                    i++;
                }

            }

            return stringBuilder.toString();
        }

    }


    public interface JsonParse {
        Map<String, Object> toMap(String json);

        List<Object> toArray(String json);
    }


    public static class MyBeanGenerator implements BeanGenerator {

        String packName;

        public MyBeanGenerator(String packName) {
            // TODO Auto-generated constructor stub
            this.packName = packName;
        }


        static  String[] javaCode={     "abstract",             "assert",               "boolean",
                "break",                "byte",                 "case",                 "catch",
                "char",                 "class",                "const",                "continue",
                "default",              "do",                   "double",               "else",
                "enum",                 "extends",              "final",                "finally",
                "float",                "for",                  "goto",                 "if",
                "implements",           "import",               "instanceof",           "int",
                "interface",            "long",                 "native",               "new",
                "package",              "private",              "protected",            "public",
                "return",               "strictfp",             "short",                "static",
                "super",                "switch",               "synchronized",         "this",
                "throw",                "throws",               "transient",            "try",
                "void",                 "volatile",             "while"                 };

        static ArrayList<String> javaCodeArr = new ArrayList<String> ();
        static{
            Collections.addAll(javaCodeArr,javaCode);
        }
        @Override
        public void writeBean(String className, Map<String, Object> map) throws IOException {
// Topic048.java  zzj               执行该类

            System.out.println(" MyBeanGenerator  className  ="+ className);
            while(className.contains(" ")){
                className = className.replaceAll(" ","");

            }
            File file = new File("src/" + packName.replace(".", "/"));
            if (!file.exists() || file.exists() && file.isFile()) {
                file.mkdirs();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file, className + ".java")));
            bw.write("package ");
            bw.write(packName);
            bw.write(";\n");
            bw.write("import java.util.List;\n\n");

            bw.write("/**\n");
            bw.write(" *auto generate\n");
            bw.write(" *\n");
            bw.write(" *@author Zukgit\n");
            bw.write(" *\n");
            bw.write(" */\n");

            bw.write("public class ");
            bw.write(className);
            bw.write("{\n");
            map = sortMapByKey(map);
            Set<Map.Entry<String, Object>> set = map.entrySet();

            for (Map.Entry<String, Object> entry : set) {
                bw.write("    ");

                String value =   entry.getValue().toString();
                System.out.println(" zvaluepre = "+ value);
                if(value.contains("\\n")){
                    value = value.substring(0,value.indexOf("\\n"));
                }
                System.out.println(" zvalueback = "+ value);
                if(value.contains("List") && value.contains("___")){
                    //   List<A___B___C> == > List<Object>
                    String pre = value.substring(0,value.indexOf("___")-1);
                    String back = value.substring(value.lastIndexOf("___")+"___".length()+1,value.length());
                    value = pre+"Object"+back;    //   List<AObject___B___C>
                }
                System.out.println(" zvalue = "+ value);
                bw.write(value);
// entry.getValue().key = abstract
                System.out.println("X1  MyBeanGenerator  className  ="+ className+ " entry.getValue().toString() ="+ entry.getValue().toString()  + " entry.getValue().key = "+  entry.getKey());
                //X1  MyBeanGenerator  className  =RootBean entry.getValue().toString() =List<A___B___C>\nA[1][2][3][4][5][6][7]
                bw.write(" ");
                String key = entry.getKey();
                if(javaCodeArr.contains(key)){
                    key = key+"_javacode";
                }
                bw.write(key);
                bw.write(";\n");
            }
            bw.write("\n");

            set = map.entrySet();

            for (Map.Entry<String, Object> entry : set) {

                bw.write("    public ");
                String value1 =  entry.getValue().toString();
                System.out.println(" zvaluepre  value1 = "+ value1);
                if(value1.contains("\\n")){
                    value1 = value1.substring(0,value1.indexOf("\\n"));
                }
                System.out.println(" zvalueback  value1 = "+ value1);
                if(value1.contains("List") && value1.contains("___")){
                    //   List<A___B___C> == > List<Object>
                    String pre = value1.substring(0,value1.indexOf("___")-1);
                    String back = value1.substring(value1.lastIndexOf("___")+"___".length()+1,value1.length());
                    value1 = pre+"Object"+back;    // List<AObject___C>
                }
                bw.write(value1);
                System.out.println(" zvalue1 = "+ value1);
                // // entry.getValue().toString() = abstract
                System.out.println("X2  MyBeanGenerator  className  ="+ className+ " entry.getValue().toString() ="+ entry.getValue().toString());
                bw.write(" get");
                String key =entry.getKey();
                if(javaCodeArr.contains(key)){
                    key = key+"_javacode";
                }

                //  如果  当前的 value  包含 List 和 ___   那么获取  第一个 ___ 之前的位置    最后一个 ___之后的位置,  截取这个字符串 中间加 List<Object>

// entry.getValue().toString() =List<A_B_C>
                bw.write(capitalUpperCase(key));
                System.out.println("X3  MyBeanGenerator  className  ="+ className+ " entry.getValue().toString() ="+ entry.getValue().toString() +"  capitalUpperCase(entry.getKey()) ="+ capitalUpperCase(entry.getKey()));

                bw.write("(){\n");
                bw.write("        ");
                bw.write("return ");
                bw.write(key);

                bw.write(";\n    }\n\n");

                //////////////////////////

                bw.write("    public void ");
                bw.write("set");  // 设置方法

                String key_set =entry.getKey();
                if(javaCodeArr.contains(key_set)){
                    key_set = key_set+"_javacode";
                }

                bw.write(capitalUpperCase(key_set));


                bw.write("(");
                bw.write(value1);
                bw.write(" ");
                bw.write(key_set);
                bw.write("){\n");
                bw.write("        ");
                bw.write("this.");
                bw.write(key_set);
                bw.write("=");
                bw.write(key_set);
                bw.write(";\n    }\n");

                bw.write("\n");

            }
            bw.write("}");

            bw.close();
        }

        private String capitalUpperCase(String s) {
            char[] chs = s.toCharArray();
            if (chs[0] >= 'a' && chs[0] <= 'z') {
                chs[0] = (char) (chs[0] - 32);
            }
            return new String(chs);

        }

        @Override
        public void writeList(String list) throws IOException {

            System.out.println("className1  writeList  = " + list);  // zzj
            File file = new File("src/" + packName.replace(".", "/"));
            if (!file.exists() || file.exists() && file.isFile()) {
                file.mkdirs();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file, list.replaceAll("<|>", "_") + ".txt")));

            bw.write(list);
            bw.write(";");

            bw.close();

        }

        public Map<String, Object> sortMapByKey(Map<String, Object> oriMap) {

            Map<String, Object> sortedMap = new TreeMap<String, Object>(new Comparator<String>() {
                public int compare(String key1, String key2) {

                    return key1.compareTo(key2);
                }
            });
            sortedMap.putAll(oriMap);
            return sortedMap;
        }

    }


    public static class MyJsonParser implements JsonParse {

        @SuppressWarnings("unchecked")
        @Override
        public Map<String, Object> toMap(String json) {
            return JSON.parseObject(json, Map.class);
        }

        @Override
        public List<Object> toArray(String json) {
            return JSON.parseArray(json, Object.class);
        }


    }


    public static class MyNameGenerator implements NameGenerator {


        String names[] = {"A", "B", "C", "D", "E", "F"
                , "G", "H", "I", "J", "K", "L", "M"
                , "N", "O", "P", "Q", "R", "S", "T"
                , "U", "V", "W", "X", "Y", "Z"
                , "AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "JJ", "KK", "LL", "MM", "NN"};
        int posiiotn;

        @Override
        public String nextName() {

            return names[posiiotn++];
        }

        //字符串转换为ascii
        public static String StringToA(String content){
            String result = "";
            int max = content.length();
            for (int i=0; i<max; i++){
                char c = content.charAt(i);
                int b = (int)c;
                result = result + b;
            }
            return result;
        }

        //ascii转换为string
        public static String AToString(int i){
            return Character.toString((char)i);
        }



        @Override
        public  String formatName(String name) {
            char[] chs = name.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chs[0]);
            System.out.println("chs[0] ="+ +chs[0] );
            for (int i = 1; i < chs.length; i++) {
                System.out.println("chs["+i+"] ="+ +chs[i] );
                if (chs[i - 1] == '_') { // 如果当前查询是_  那么就把后面的 转为大写  _  对应的ACSII码 是 95
                    if(!isNum(AToString(chs[i])) && (""+AToString(chs[i])).matches("^[a-z,A-Z].*$")){
                        stringBuilder.append((char) (chs[i] - 32));
                        System.out.println("test run!  +chs[i] ="+ +chs[i] );
                    }else{
                        stringBuilder.append(chs[i]);
                    }
                } else
                    stringBuilder.append(chs[i]);
            }
            //  String s = stringBuilder.toString().replace("_", "");
            String s = stringBuilder.toString();
            chs = s.toCharArray();
            if (chs[0] >= 'a' && chs[0] <= 'z') {
                chs[0] = (char) (chs[0] - 32);
            }

            return new String(chs);
        }


        public static boolean isNum(String str){
            return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
        }


    }


    public interface NameGenerator {
        String nextName();

        /**
         * 格式化标识符，驼峰式写法
         *
         * @param name
         * @return
         */
        String formatName(String name);
    }

}


//    List<A_B_C>  需要把这个 创建了三个 JavaBean
// A , B  ,C  这三个 对象的 execute()方法 会执行  parseMap();  zzj
//         而不会执行  parseMap()  的  generationBean.writeList(clz);  去 生成
//       WriteBean
//
//         Line 231: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<String>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 339: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<Integer>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 341: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<Object>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 671: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<D>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 684: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<A_B_C>  不会执行 writeList(clz) 方法了  操蛋！
//        Line 741: zukgit4_1_2_空  this.name :null  name =null curRentName = clz=List<E>  不会执行 writeList(clz) 方法了  操蛋！
//      writeList(clz)  // 把 clz 写入一个 txt 文件  文件的名称为  List<Object>   转为  List_Object_     List_A_B_C_
//     File file = new File("src/" + packName.replace(".", "/"));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file, list.replaceAll("<|>", "_") + ".txt")));
//    bw.write(list);       写入 .txt  有什么作用?   这个从代码根本看不出来



//  X2  MyBeanGenerator  className  =RootBean entry.getValue().toString() =
//       X3  MyBeanGenerator  MyBeanGenerator  className  =RootBean entry.getValue().toString() = capitalUpperCase(entry.getKey()) =Data
// 它的 key=data  , value 为空  map.put
//  generationBean.writeBean(name, map 【 Map<String, Object> map = jsonParse.toMap(json) 】);  map的 数据来源于 Json


//  [{类A}{类B}{类C}]    以什么方式     value =  List<A_B_C>   List<A___B___C>
// X3  MyBeanGenerator  className  =RootBean entry.getValue().toString() =List<<A___B___C>>  capitalUpperCase(entry.getKey()) =Data
//  如果  当前的 value  包含 List 和 ___   那么获取  第一个 ___ 之前的位置    最后一个 ___之后的位置,  截取这个字符串 中间加 List<Object>

//  B -> topic_2048 [weight=6]
//B -> topic [weight=6]    重复！
// make-> 4  refClassName=B    properityName=topic_2048
// make-> 4  refClassName=Extra    properityName=Topic_2048
// make-> 4  refClassName=Extra    properityName=Topic
// make-> 4  refClassName=B    properityName=topic
// new ProperityItem
//  ArrayList<HashMap<String, ArrayList<ProperityItem>>> arrMapList  的 Item 排序

// 对于 对象中 为 {  [] }  为 空的情况的处理