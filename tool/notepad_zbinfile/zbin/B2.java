

import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.PinyinUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B2 {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20190418000289139";
    private static final String SECURITY_KEY = "A_KfJIXPllP5W4FvKGkk";
    public static final java.util.ArrayList<String> errorInfoList = new ArrayList<>();
    public static final java.util.ArrayList<String> successInfoList = new ArrayList<>();
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";
    static boolean isMultiWord = false;  // 是否是多个单词  // 24 个为 分界点  大于三十个 就 把翻译放到最后
    static int word_max_char = 30;

    public static void main3(String[] args) {

        String clipStr = ClipboardUtil.getStr();
        System.out.println("clipStr = " + clipStr);
        System.out.println("clipStr.trim() = " + clipStr.trim());

   //  String jsonerror =   "{\"error_code\":\"DB Get Connection Exception: fail in ' Bd_Db_ConnMgr::getConn(VipTransApi); ',referral_write_log\",\"error_msg\":\"Unknown Error Code\"}";

        String jsonerror =      "{\"from\":\"zh\",\"to\":\"en\",\"trans_result\":[{\"src\":\"\u4ee5\u8272\u5217\",\"dst\":\"Israel\"}]}";
;
        JSONObject jsonObj = JSONUtil.parseObj(jsonerror);
        if(jsonObj.get("error_code") != null){
            System.out.println("error_code = 出错");
        } else{
            System.out.println("没错 ！");
        }
    }
    public static void main4(String[] args) {
        String query = "天天起床跑步";
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        // {"from":"zh","to":"en","trans_result":[{"src":"\u9ad8\u5ea6600\u7c73","dst":"Height 600 meters"}]}
        // {"from":"zh","to":"en","trans_result":[{"src":"\u4ee5\u8272\u5217","dst":"Israel"}]}
        // 没有网络 返回字符串  null
        String resultJson = api.getTransResult(query, "en", "zh");
        System.out.println(resultJson);
        JSONObject jsonObj = JSONUtil.parseObj(resultJson);
        JSONArray arr = jsonObj.getJSONArray("trans_result");
        String result = arr.get(0).toString();
        // result = {"dst":"Israel","src":"以色列"}
        System.out.println("result = " + result);

    }

    public static void main(String[] args) {
        //===============real-test begin===============
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

        String clipStr = ClipboardUtil.getStr().trim();   // 剪贴板上赋值的字符串
        int n_num = StrUtil.count(clipStr.trim(),"\n");
        System.out.println("n_num = "+ n_num);
        // 只有单词数小于 30个  并且 复制的行数 只有一行的情况 才检测是否包含字符串 在一行中
        if (clipStr.length() >= word_max_char ||    StrUtil.count(clipStr.trim(),"\n") >= 1) {
            isMultiWord = true;
        }
        //===============real-test end===============
        // 只有单词数小于 30个  并且 复制的行数 只有一行的情况 才检测是否包含字符串 在一行中
        if (!isMultiWord ) {
            if (!checkIfContainclipStr(curFile, clipStr)) {
                String searchResult = "【祝婉婷(ˉ(∞)ˉ) 没有执行复制操作啊!  当前的剪切板字符串是: " + clipStr + "】";
                addErrorInfo(curFile, searchResult);
                return;
            }

        }


        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String query = new String(clipStr.replaceAll("\r|\n", ""));

        // {"from":"zh","to":"en","trans_result":[{"src":"\u9ad8\u5ea6600\u7c73","dst":"Height 600 meters"}]}
        String resultJson = "";
        if (isContainChinese(query)) {
            resultJson = api.getTransResult(query, "zh", "en");
        } else {
            resultJson = api.getTransResult(query, "en", "zh");
        }


        // 百度翻译 返回回来的数据
        System.out.println(resultJson);

        if (resultJson == null || resultJson.trim().equals("null") || resultJson.trim().isEmpty()) {
            // 可能发生网络错误
            String searchResult = "【祝婉婷(ˉ(∞)ˉ) 检查下有没有连接WIFI 网络是否正常!  当前的剪切板字符串是: " + clipStr + "】";
            addErrorInfo(curFile, searchResult);
            return;
        }
        JSONObject preCheck = JSONUtil.parseObj(resultJson);
        if(preCheck.get("error_code") != null){
            String searchResult = "【祝婉婷(ˉ(∞)ˉ) 翻译服务器出现问题 尝试关闭文档 再打开试试  当前剪切板字符串: " + clipStr + "】";
            addErrorInfo(curFile, searchResult);
            return;

        }

// {"error_code":"DB Get Connection Exception: fail in ' Bd_Db_ConnMgr::getConn(VipTransApi); ',refer ral_write_log","error_msg":"Unknown Error Code"}
 // {"from":"zh","to":"en","trans_result":[{"src":"\u9ad8\u5ea6600\u7c73","dst":"Height 600 meters"}]}
        JSONObject jsonObj = JSONUtil.parseObj(resultJson);
        JSONArray arr = jsonObj.getJSONArray("trans_result");
        String result = arr.get(0).toString();
        // result = {"dst":"Israel","src":"以色列"}
        System.out.println("result = " + result);
        while (result.contains("{")) {
            result = result.replace("{", "【");
        }

        while (result.contains("}")) {
            result = result.replace("}", "】");
        }
        //  result = 【"dst":"Israel","src":"以色列"】
        while (result.contains("\"")) {
            result = result.replace("\"", "");
        }

        while (result.contains("dst:")) {
            result = result.replace("dst:", "");
        }

        while (result.contains(",src:")) {
            result = result.replace(",src:", ":");
        }


        //  result = 【Israel:以色列】
        addOKInfo(curFile, clipStr, result);
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile(REGEX_CHINESE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void addOKInfo(File txtFile, String clipStr, String resultInfo) {
        try {

            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile), "utf-8"));

            String lineString = "";


            while (lineString != null) {
                lineString = curBR.readLine();
                if (lineString != null) {
                    if (lineString.contains(clipStr) && !isMultiWord) {
                        lineString = lineString.substring(0, lineString.indexOf(clipStr) + clipStr.length()) + resultInfo + lineString.substring(lineString.indexOf(clipStr) + clipStr.length(), lineString.length());
                        System.out.println("需要刷新的那行字符串 lineString =  " + lineString);
                    }
                    successInfoList.add(lineString);
                }
            }
            if(isMultiWord){  //  多行的话就把结果放到末尾
                // 【关于本月6日来函，:With reference to your letter of the6th inst,】
                if(resultInfo.length() > 80){
                    successInfoList.add("【 祝婉婷: 翻译字符串长度大于30 或者 复制了多行  所以分行打印！");
                    successInfoList.add(resultInfo.substring(resultInfo.indexOf("【")+1,resultInfo.indexOf(":") ));
                    successInfoList.add(resultInfo.substring(resultInfo.indexOf(":")+1,resultInfo.lastIndexOf("】") ));
                    successInfoList.add("】多行翻译完成!");
                } else{
                    successInfoList.add(resultInfo);
                }

            }
            curBR.close();
            BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "utf-8"));
            for (int i = 0; i < successInfoList.size(); i++) {
                curBW.write(successInfoList.get(i));
                curBW.newLine();
            }
            curBW.close();
            System.out.println(" 翻译成功！ ");


        } catch (Exception e) {
        }
    }


    public static void addErrorInfo(File txtFile, String errorInfo) {
        try {

            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile), "utf-8"));

            String lineString = "";


            while (lineString != null) {
                lineString = curBR.readLine();
                if (lineString != null) {
                    errorInfoList.add(lineString);
                }
            }
            curBR.close();
            errorInfoList.add(0, errorInfo);
            BufferedWriter curBW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile), "utf-8"));
            for (int i = 0; i < errorInfoList.size(); i++) {
                curBW.write(errorInfoList.get(i));
                curBW.newLine();
            }
            curBW.close();
            System.out.println("打印 搜索错误信息结束: 退出程序！ ");


        } catch (Exception e) {
        }
    }


    public static boolean checkIfContainclipStr(File txtFile, String clipStr) {
        boolean flag = false;
        try {
            BufferedReader curBR = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile), "utf-8"));
            String stringLine = "";

            while (stringLine != null) {
                stringLine = curBR.readLine();
                if (stringLine != null && stringLine.trim().contains(clipStr)) {
                    flag = true;
                    break;
                }
            }
            curBR.close();
            System.out.println("匹配到剪贴板上的字符串 该行字符串为: = " + stringLine);

        } catch (Exception e) {
            System.out.println("Exception !");
        }

        return flag;
    }


    static class TransApi {
        private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

        private String appid;
        private String securityKey;

        public TransApi(String appid, String securityKey) {
            this.appid = appid;
            this.securityKey = securityKey;
        }

        public String getTransResult(String query, String from, String to) {
            Map<String, String> params = buildParams(query, from, to);
            return HttpGet.get(TRANS_API_HOST, params);
        }

        private Map<String, String> buildParams(String query, String from, String to) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("q", query);
            params.put("from", from);
            params.put("to", to);

            params.put("appid", appid);

            // 随机数
            String salt = String.valueOf(System.currentTimeMillis());
            params.put("salt", salt);

            // 签名
            String src = appid + query + salt + securityKey; // 加密前的原文
            params.put("sign", MD5.md5(src));

            return params;
        }

    }


    static class HttpGet {
        protected static final int SOCKET_TIMEOUT = 10000; // 10S
        protected static final String GET = "GET";

        public static String get(String host, Map<String, String> params) {
            try {
                // 设置SSLContext
                SSLContext sslcontext = SSLContext.getInstance("TLS");
                sslcontext.init(null, new TrustManager[]{myX509TrustManager}, null);

                String sendUrl = getUrlWithQueryString(host, params);

                // System.out.println("URL:" + sendUrl);

                URL uri = new URL(sendUrl); // 创建URL对象
                HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
                if (conn instanceof HttpsURLConnection) {
                    ((HttpsURLConnection) conn).setSSLSocketFactory(sslcontext.getSocketFactory());
                }

                conn.setConnectTimeout(SOCKET_TIMEOUT); // 设置相应超时
                conn.setRequestMethod(GET);
                int statusCode = conn.getResponseCode();
                if (statusCode != HttpURLConnection.HTTP_OK) {
                    System.out.println("Http错误码：" + statusCode);
                }

                // 读取服务器的数据
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }

                String text = builder.toString();

                close(br); // 关闭数据流
                close(is); // 关闭数据流
                conn.disconnect(); // 断开连接

                return text;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static String getUrlWithQueryString(String url, Map<String, String> params) {
            if (params == null) {
                return url;
            }

            StringBuilder builder = new StringBuilder(url);
            if (url.contains("?")) {
                builder.append("&");
            } else {
                builder.append("?");
            }

            int i = 0;
            for (String key : params.keySet()) {
                String value = params.get(key);
                if (value == null) { // 过滤空的key
                    continue;
                }

                if (i != 0) {
                    builder.append('&');
                }

                builder.append(key);
                builder.append('=');
                builder.append(encode(value));

                i++;
            }

            return builder.toString();
        }

        protected static void close(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 对输入的字符串进行URL编码, 即转换为%20这种形式
         *
         * @param input 原文
         * @return URL编码. 如果编码失败, 则返回原文
         */
        public static String encode(String input) {
            if (input == null) {
                return "";
            }

            try {
                return URLEncoder.encode(input, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return input;
        }

        private static TrustManager myX509TrustManager = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        };

    }

    static class MD5 {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};

        /**
         * 获得一个字符串的MD5值
         *
         * @param input 输入的字符串
         * @return 输入字符串的MD5值
         */
        public static String md5(String input) {
            if (input == null)
                return null;

            try {
                // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                // 输入的字符串转换成字节数组
                byte[] inputByteArray = input.getBytes("utf-8");
                // inputByteArray是输入字符串转换得到的字节数组
                messageDigest.update(inputByteArray);
                // 转换并返回结果，也是字节数组，包含16个元素
                byte[] resultByteArray = messageDigest.digest();
                // 字符数组转换成字符串返回
                return byteArrayToHex(resultByteArray);
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * 获取文件的MD5值
         *
         * @param file
         * @return
         */
        public static String md5(File file) {
            try {
                if (!file.isFile()) {
                    System.err.println("文件" + file.getAbsolutePath() + "不存在或者不是文件");
                    return null;
                }

                FileInputStream in = new FileInputStream(file);

                String result = md5(in);

                in.close();

                return result;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static String md5(InputStream in) {

            try {
                MessageDigest messagedigest = MessageDigest.getInstance("MD5");

                byte[] buffer = new byte[1024];
                int read = 0;
                while ((read = in.read(buffer)) != -1) {
                    messagedigest.update(buffer, 0, read);
                }

                in.close();

                String result = byteArrayToHex(messagedigest.digest());

                return result;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private static String byteArrayToHex(byte[] byteArray) {
            // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
            char[] resultCharArray = new char[byteArray.length * 2];
            // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
            int index = 0;
            for (byte b : byteArray) {
                resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
                resultCharArray[index++] = hexDigits[b & 0xf];
            }

            // 字符数组组合成字符串返回
            return new String(resultCharArray);

        }

    }


}
