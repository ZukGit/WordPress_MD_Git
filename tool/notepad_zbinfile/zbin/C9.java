


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class C9 {
    static String curProjectPath = System.getProperty("user.dir");


    // 把 Settings下的java文件都打包成一个文件  输出 html 页面  以及 md文件
    //  文件名称为 packages-apps-Settings_java_Analysis.html
    //  文件名称为 packages-apps-Settings_java_Analysis.md


    // 把  /packages/apps  和 frameworks/base 下的包含 values-zh-rCN/string.xml  的文件打包成一个文件
    //  文件名称为  packages-apps-frameworks-base-values-zh-rCN-_Analysis.txt



    static File current_juwuba_template_file = new File(System.getProperty("user.dir") + File.separator + "C9_juwuba_template.html");
    static String current_juwuba_html_template_content = "";
static String markdownName = "C9_default.html";
static String curDirPath = "";

    public static void main(String[] args) {

        String mFilePath = null;
        if (args.length >= 1) {
            mFilePath = args[0];
        } else {
            System.out.println("input argument is empty ! retry input again!");
            return;
        }
        File curFile;
        if (mFilePath != null && !mFilePath.isEmpty() && (curFile = new File(mFilePath)).exists()) {
            System.out.println("input argument success ! ");
        } else {
            System.out.println("input argument is invalid ! retry input again!");
            return;
        }

        markdownName = curFile.getName().substring(0,curFile.getName().lastIndexOf("."));
        curDirPath = curFile.getAbsolutePath();
        curDirPath = curDirPath.substring(0,curDirPath.lastIndexOf(File.separator));
        if (current_juwuba_template_file != null && current_juwuba_template_file.exists()) {
            current_juwuba_html_template_content = readTemplateStringFromFile(current_juwuba_template_file).toString();
            System.out.println("================================ current_juwuba_template_content   begin =================================");
            //  System.out.println(" current_juwuba_template_content  = " + current_juwuba_html_template_content);
            System.out.println("================================ current_juwuba_template_content   end =================================");
        } else {
            System.out.println(" current_juwuba_template_content  = null ");
        }


        String markDownContent = readTemplateStringFromFile(curFile).toString();
        String markdownHtml = translateFromMarkdownToHtml(markDownContent);

        String html_template_str = new String(current_juwuba_html_template_content);  // 生成 html 文件的  StringBuilder  先放入 html模板

        String outfile_html_name = getOutFileHTMLName();
        String htmlTitle = outfile_html_name.replace(".html", "");
        // tag 标题
        html_template_str = html_template_str.replace("ZukgitHeadTitleHoldPlace_1", outfile_html_name);
        html_template_str = html_template_str.replace("ZukgitContentTitleHoldPlace_2", htmlTitle + "文件集合");
        Date day = new Date();
        SimpleDateFormat simpleDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        html_template_str = html_template_str.replace("ZukgitTimeStampHoldPlace_3", simpleDf.format(day));
        html_template_str = html_template_str.replace("ZukgitMDContentHoldPlace_4", markdownHtml);
        writeContentToFile(new File(curDirPath+File.separator+outfile_html_name), html_template_str.toString());

    }

    static String getOutFileHTMLName() { // 输出文件的名称  依据MarkDown文件的名称命名
        return markdownName+".html";
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
                System.out.println("write out File OK !  File = " + file.getAbsolutePath());
            } else {
                System.out.println("write out File  Failed !    File = " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //  把 MD 格式的 翻译成  html 格式



    static StringBuilder readTemplateStringFromFile(File fileItem) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(fileItem), "utf-8"));
            String lineContent = "";
            while (lineContent != null) {
                lineContent = curBR.readLine();
                if (lineContent == null || lineContent.trim().isEmpty()) {
                    continue;
                }
                sb.append(lineContent + "\n");
            }
            curBR.close();
        } catch (Exception e) {
        }
        return sb;
    }


    static String translateFromMarkdownToHtml(String markdownCode) {

        //如果当前的 内容并不在 ```  precode里面的情况
        // #空格   <h1>  </h1>
        //##空格   <h2>  </h2>
        // ###空格   <h3>  </h3>
        // ####空格   <h4>  </h4>
        // #####空格   <h5>  </h5>
        // ######空格   <h6>  </h6>
        // #######空格   <h7>  </h7>
        // ########空格   <h8>  </h8>
        // #########空格   <h9>  </h9>


        // 每次遇到 ```  就把 标志位索引 0 + 1  ,  奇数 i%2 ==1 那么就在precode 外面  ,  偶数i%2 == 0  就在precode 里面
        // 当在 precode 以内  把数据的格式 整理下
        //1.   pre ```   =   "\n<pre><code class=\"\">\n"     end``` =</code></pre>

        // 2. 左右 括号的 切换
//        inprecodeStr = inprecodeStr.replace("<", "&lt;");
//        inprecodeStr = inprecodeStr.replace(">", "&gt;");

        StringBuilder sb = new StringBuilder();
        String htmlCode = "";
        htmlCode = markdownCode.replace("\n\n\n\n", "\n");
        htmlCode = htmlCode.replace("\n\n\n", "\n");
        htmlCode = htmlCode.replace("\n\n", "\n");
        htmlCode = htmlCode.replace("\n\n", "\n");
        String[] mdArr = htmlCode.split("\n");
        int codeIndex = 1;
        for (int i = 0; i < mdArr.length; i++) {
            String codeHtmlLine = new String(mdArr[i]);
            if (codeIndex % 2 == 0) {  // 偶数 在 preCode 里面
                if (mdArr[i].trim().equals("```")) {   //  遇到 end```
                    codeIndex++;
                    codeHtmlLine = "\n</code></pre>\n";
                } else {
                    codeHtmlLine = mdArr[i].replace("<", "&lt;");
                    codeHtmlLine = codeHtmlLine.replace(">", "&gt;");
                }

            } else { // 奇数 在 preCode外面

                if (mdArr[i].trim().equals("```")) { // 首次遇到  pre```
                    codeIndex++;
                    codeHtmlLine = "\n<pre><code class=\"\">\n";
                } else {
                    if (mdArr[i].trim().startsWith("######### ")) { // h9
                        codeHtmlLine = mdArr[i].trim().replace("######### ", "<h9>");
                        codeHtmlLine = codeHtmlLine + "</h9>";
                    } else if (mdArr[i].trim().startsWith("######## ")) { // h8
                        codeHtmlLine = mdArr[i].trim().replace("######## ", "<h8>");
                        codeHtmlLine = codeHtmlLine + "</h8>";
                    } else if (mdArr[i].trim().startsWith("####### ")) { // h7
                        codeHtmlLine = mdArr[i].trim().replace("####### ", "<h7>");
                        codeHtmlLine = codeHtmlLine + "</h7>";
                    } else if (mdArr[i].trim().startsWith("###### ")) { // h6
                        codeHtmlLine = mdArr[i].trim().replace("###### ", "<h6>");
                        codeHtmlLine = codeHtmlLine + "</h6>";
                    } else if (mdArr[i].trim().startsWith("##### ")) { // h5
                        codeHtmlLine = mdArr[i].trim().replace("##### ", "<h5>");
                        codeHtmlLine = codeHtmlLine + "</h5>";
                    } else if (mdArr[i].trim().startsWith("#### ")) { // h4
                        codeHtmlLine = mdArr[i].trim().replace("#### ", "<h4>");
                        codeHtmlLine = codeHtmlLine + "</h4>";
                    } else if (mdArr[i].trim().startsWith("### ")) { // h3
                        codeHtmlLine = mdArr[i].trim().replace("### ", "<h3>");
                        codeHtmlLine = codeHtmlLine + "</h3>";
                    } else if (mdArr[i].trim().startsWith("## ")) { // h2
                        codeHtmlLine = mdArr[i].trim().replace("## ", "<h2>");
                        codeHtmlLine = codeHtmlLine + "</h2>";
                    } else if (mdArr[i].trim().startsWith("# ")) { // h1
                        codeHtmlLine = mdArr[i].trim().replace("# ", "<h1>");
                        codeHtmlLine = codeHtmlLine + "</h1>";
                    }
                }

            }
            sb.append(codeHtmlLine + "\n");

            System.out.println("第" + i + "行: " + mdArr[i]);
        }
        return sb.toString();
    }

}
