
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;


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
 *   1. 若json以 { 开头，则用fastjson解析成Map<String,Object>，
 * 依次取出map中的值，若值为null则不处理该字段，若instanceof Integer则该字段处理完毕，该字段对应的类型为Integer。 Double,String时同理。

 * 2. 若值以 { 开头则又是一个新的Bean，类名为map中的键（nameGeneration.formatName格式化后的驼峰式写法），则交给新的Json2Bean处理</br></br>

 * 3. 若值以 [ 开头说明是一个List，泛型参数需要根据[ ] 内部json决定，待内部json处理完毕后返回的就是类型（例如  [{"name":"Zukgit","age":20}]   这个json是一个List,这种情况时泛型参数可以在[ ]内数据处理前就得到，
 * 但 如果json是这样的  [1,2,3]或 ["a","b","c"]  泛型参数就不能随意规定了，只能是Integer或String，所以要等[] 内部json处理完毕后才能得到类型）</br></br>
 * <p>
 *
 * 4. 若map中值以[ 开头说明值对应的json、是一个List，这时用fastjson解析得到List<Object>，若list为null或空，则不处理该字段（因 泛型参数无法确定）
 * 同理，当list中第一个元素  instancof Double时说明是List<Double>，Integer时还要遍历list决定到底是Integer还是Double（例如 [1,2.0,3.0]），若值以 { 开头说明是一个对象，对象名我们无法根据json字符串获取，这时使用nameGeneration.nextName()
 * 生成一个名字作为泛型参数，并构造该类
 *
 * 5. 生成 dot 描述的 graphiz 绘制的语言
 */

public class B6 {
    // ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName)

    static ArrayList<String> dotStringArr = new ArrayList<String>();
    static ArrayList<HashMap<String, ArrayList<ProperityItem>>> arrMapList = new ArrayList<HashMap<String, ArrayList<ProperityItem>>>();

    public static void main(String[] args) {

        try {
            String ss = "{\"count\":0.0,\"doubleList\":[1,2,3.0,4],\"intList\":[1,2,3,4,5,6]}";
//            System.out.println(ss);
//            new Json2Bean(ss, "RootBean", new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test1")).execute();
//            System.out.println("------------------------------");
            String toujiaoJson = "{\"count\":20,\"action_label\":\"click_search\",\"return_count\":20,\"no_outsite_res\":0,\"has_more\":1,\"page_id\":\"/search/\",\"request_id\":\"20180129215748010008062196331DBD\",\"cur_tab\":1,\"tab\":{\"tab_list\":[{\"tab_name\":\"\u7efc\u5408\",\"tab_id\":1,\"tab_code\":\"news\"},{\"tab_name\":\"\u89c6\u9891\",\"tab_id\":2,\"tab_code\":\"video\"},{\"tab_name\":\"\u56fe\u96c6\",\"tab_id\":3,\"tab_code\":\"gallery\"},{\"tab_name\":\"\u7528\u6237\",\"tab_id\":4,\"tab_code\":\"pgc\"},{\"tab_name\":\"\u95ee\u7b54\",\"tab_id\":5,\"tab_code\":\"wenda\"}],\"cur_tab\":1},\"offset\":20,\"action_label_web\":\"click_search\",\"show_tabs\":1,\"data\":[{\"tokens\":[\"\u5c0f\u5eb7\",\"\u793e\u4f1a\"],\"cell_type\":26,\"id_str\":\"3752881820\",\"ala_src\":\"sogou_baike\",\"key_info\":{},\"id\":3752881820,\"display\":{\"url\":\"http://baike.sogou.com/m/fullLemma?ch=jrtt.search.item&cid=xm.click&lid=98831\",\"source\":\"\u641c\u72d7\u767e\u79d1\",\"pic\":\"http://pic.baike.soso.com/ugc/baikepic2/8792/20160729105309-1846634880.jpg/300\",\"lemmaTitle\":\"\u5c0f\u5eb7\u793e\u4f1a\",\"picAbs\":\"\u5c0f\u5eb7\u793e\u4f1a\u662f\u53e4\u4ee3\u601d\u60f3\u5bb6\u63cf\u7ed8\u7684\u8bf1\u4eba\u7684\u793e\u4f1a\u7406\u60f3\uff0c\u4e5f\u8868\u73b0\u4e86\u666e\u901a\u767e\u59d3\u5bf9\u5bbd\u88d5\u3001\u6bb7\u5b9e\u7684\u7406\u60f3\u751f\u6d3b\u7684\u8ffd\u6c42\u3002 \u6240\u8c13\u5168\u9762\u7684\u5c0f\u5eb7\u793e\u4f1a\uff0c\u4e0d\u4ec5\u4ec5\u662f\u89e3\u51b3\u6e29\u9971\u95ee\u9898\uff0c\u800c\u662f\u8981\u4ece\u653f\u6cbb\u3001\u7ecf\u6d4e\u3001\u6587\u5316\u7b49\u5404\u65b9\u9762\u6ee1\u8db3\u57ce\u4e61\u53d1\u5c55\u9700\u8981\u3002\u5341\u516d\u5927\u62a5\u544a\u4e2d\uff0c\u4ece\u7ecf\u6d4e\u3001\u653f\u6cbb\u3001\u6587\u5316\u3001\u53ef\u6301\u7eed\u53d1\u5c55\u7684\u56db\u4e2a\u65b9\u9762\u754c\u5b9a\u4e86\u5168\u9762\u5efa\u8bbe\u5c0f\u5eb7\u793e\u4f1a\u7684\u5177\u4f53\u5185\u5bb9\u3002\u7279\u522b\u5c06\u53ef\u6301\u7eed\u6027\u53d1\u5c55\u80fd\u529b\u7684\u8981\u6c42\u5305\u542b\u5728\u5176\u4e2d\u3002\u5177\u4f53\u5c31\u662f\u516d\u4e2a\u201c\u66f4\u52a0\u201d\uff1a\u7ecf\u6d4e\u66f4\u52a0\u53d1\u5c55\u3001\u6c11\u4e3b\u66f4\u52a0\u5065\u5168\u3001\u79d1\u6559\u66f4\u52a0\u8fdb\u6b65\u3001\u6587\u5316\u66f4\u52a0\u7e41\u8363\u3001\u793e\u4f1a\u66f4\u52a0\u548c\u8c10\u3001\u4eba\u6c11\u751f\u6d3b\u66f4\u52a0\u6bb7\u5b9e\u30021992\u5e74\u4e2d\u56fd\u6539\u9769\u5f00\u653e\u8f6c\u578b\u540e\uff0c\u6b63\u5f0f\u5411\u5168\u9762\u5efa\u8bbe\u5c0f\u5eb7\u793e\u4f1a\u8f6c\u578b\u3002\u5c0f\u5eb7\u793e\u4f1a\u662f\u6539\u9769\u5f00\u653e\u6218\u7565\u4e4b\u4e00\u3002\u9093\u5c0f\u5e73\u5728\u89c4\u5212\u4e2d\u56fd\u793e\u4f1a\u53d1\u5c55\u84dd\u56fe\u65f6\u63d0\u51fa\u7684\u5c0f\u5eb7\u793e\u4f1a\u6982\u5ff5\u30021979\u5e7412\u67086\u65e5\uff0c\u9093\u5c0f\u5e73\u5728\u4f1a\u89c1\u65e5\u672c\u9996\u76f8\u5927\u5e73\u6b63\u82b3\u65f6\u8bf4\uff1a\u201c\u6211\u4eec\u7684\u56db\u4e2a\u73b0\u4ee3\u5316\u7684\u6982\u5ff5\uff0c\u4e0d\u662f\u50cf\u4f60\u4eec\u90a3\u6837\u7684\u73b0\u4ee3\u5316\u7684\u6982\u5ff5\uff0c\u800c\u662f\u2018\u5c0f\u5eb7\u4e4b\u5bb6\u2019\u3002\u201d\uff08\u300a\u9093\u5c0f\u5e73\u6587\u9009\u300b\u7b2c2\u5377\u7b2c237\u9875\uff091984\u5e743\u670825\u65e5\uff0c\u9093\u5c0f\u5e73\u5728\u4f1a\u89c1\u65e5\u672c\u9996\u76f8\u4e2d\u66fe\u6839\u5eb7\u5f18\u65f6\u8bf4\uff1a\u201c\u7ffb\u4e24\u756a\uff0c\u56fd\u6c11\u751f\u4ea7\u603b\u503c\u4eba\u5747\u8fbe\u5230\u516b\u767e\u7f8e\u5143\uff0c\u5c31\u662f\u5230\u672c\u4e16\u7eaa\u672b\u5728\u4e2d\u56fd\u5efa\u7acb\u4e00\u4e2a\u5c0f\u5eb7\u793e\u4f1a\u3002\u8fd9\u4e2a\u5c0f\u5eb7\u793e\u4f1a\uff0c\u53eb\u505a\u4e2d\u56fd\u5f0f\u7684\u73b0\u4ee3\u5316\u3002\u7ffb\u4e24\u756a\u3001\u5c0f\u5eb7\u793e\u4f1a\u3001\u4e2d\u56fd\u5f0f\u7684\u73b0\u4ee3\u5316\uff0c\u8fd9\u4e9b\u90fd\u662f\u6211\u4eec\u7684\u65b0\u6982\u5ff5\u3002\u201d\u8fd9\u4e2a\u65b0\u6982\u5ff5\u7684\u63d0\u51fa\uff0c\u4e3a\u6211\u56fd\u7684\u73b0\u4ee3\u5316\u5efa\u8bbe\u63d0\u51fa\u4e86\u4e00\u4e2a\u660e\u786e\u7684\u594b\u6597\u76ee\u6807\u3002\"}},{\"is_qk\":0,\"publish_time\":1501364732,\"qid\":6448312424343798030,\"abstract\":\"\",\"single_mode\":false,\"image_list\":[],\"display_time\":1501364732,\"answer_count\":1,\"source_url\":\"sslocal://wenda_list?search_result_id=6448312424343798030&qid=6448312424343798030&gd_ext_json=%7B%22qid%22%3A+%226448312424343798030%22%2C+%22log_pb%22%3A+%7B%22impr_id%22%3A+%2220180129215748010008062196331DBD%22%7D%2C+%22query%22%3A+%22%5Cu5c0f%5Cu5eb7%5Cu793e%5Cu4f1a%22%2C+%22source%22%3A+%22search_tab%22%2C+%22enter_from%22%3A+%22click_search%22%7D\",\"tokens\":[\"\u5c0f\u5eb7\",\"\u793e\u4f1a\"],\"more_mode\":false,\"create_time\":1501364732,\"cell_type\":37,\"item_id\":\"6448312424343798030\",\"datetime\":\"2017-07-30 05:45:32\",\"comments_count\":0,\"display_title\":\"\",\"group_id\":\"6448312424343798030\",\"title\":\"\u4ec0\u4e48\u662f\u5c0f\u5eb7\u793e\u4f1a\uff1f\",\"url\":\"https://www.wukong.com/question/6448312424343798030/\",\"highlight\":{\"source\":[],\"abstract\":[],\"title\":[[3,4]]},\"source\":\"\u609f\u7a7a\u95ee\u7b54\",\"large_image_url\":\"\",\"tag\":\"news\",\"big_pic\":\"\",\"has_image\":false,\"image_url\":\"\",\"middle_mode\":false,\"large_mode\":false,\"gallary_image_count\":0},{\"media_creator_id\":4705245426,\"media_name\":\"\u5fae\u89c6\u516d\u679d\",\"repin_count\":5,\"ban_comment\":0,\"extra\":{\"topic_2048\":{},\"query_type\":\"LongtermESQueryType\",\"titles_terms\":\"\u5c0f\u5eb7 \u793e\u4f1a \u6709 \u51e0\u4e2a \u9636\u6bb5\",\"has_video\":0,\"topic\":{},\"source\":\"\",\"img_count\":0,\"sstag\":\"\"},\"single_mode\":true,\"abstract\":\">> \u6709\u4e13\u5bb6\u5c06\u5176\u5212\u5206\u4e3a\u201c\u4e0d\u5f88\u5bbd\u88d5\u7684\u5c0f\u5eb7\u793e\u4f1a\u201d\u201c\u5bbd\u88d5\u7684\u5c0f\u5eb7\u793e\u4f1a\u201d\u548c\u201c\u6bb7\u5b9e\u7684\u5c0f\u5eb7\u793e\u4f1a\u201d\u4e09\u4e2a\u9636\u6bb5\uff0c\u5c0f\u5eb7\u793e\u4f1a\",\"display_title\":\"\",\"media_avatar_url\":\"//p3.pstatp.com/large/6286/5404479575\",\"datetime\":\"2017-03-17 17:55\",\"article_type\":0,\"more_mode\":false,\"create_time\":1489744521,\"has_m3u8_video\":0,\"keywords\":\"\u5168\u9762\u5efa\u8bbe\u5c0f\u5eb7\u793e\u4f1a,\u5c0f\u5eb7\u793e\u4f1a\",\"has_mp4_video\":0,\"favorite_count\":5,\"aggr_type\":0,\"comments_count\":2,\"article_sub_type\":0,\"bury_count\":0,\"title\":\"\u5c0f\u5eb7\u793e\u4f1a\u6709\u51e0\u4e2a\u9636\u6bb5\",\"show_play_effective_count\":0,\"has_video\":false,\"share_url\":\"http://toutiao.com/group/6398400751064121602/?iid=0&app=news_article\",\"id\":6398400751064121602,\"source\":\"\u5fae\u89c6\u516d\u679d\",\"comment_count\":2,\"article_url\":\"http://mp.weixin.qq.com/s?__biz=MzAxMzA5NzU1Mg==&mid=2652045155&idx=3&sn=146436a469cd83fb0b2014be68e991b9\",\"image_url\":\"//p9.pstatp.com/list/190x124/1992000695bf8301eb13\",\"middle_mode\":true,\"large_mode\":false,\"item_source_url\":\"/group/6398400751064121602/\",\"media_url\":\"/c/user/4705245426/\",\"display_time\":1489743915,\"publish_time\":1489743915,\"go_detail_count\":614,\"image_list\":[],\"gallary_image_count\":0,\"item_seo_url\":\"/i6398404000122667522/\",\"tag_id\":6398400751064121602,\"source_url\":\"/group/6398400751064121602/\",\"item_id\":\"6398404000122667522\",\"natant_level\":0,\"seo_url\":\"/i6398404000122667522/\",\"display_url\":\"http://toutiao.com/group/6398400751064121602/\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MzAxMzA5NzU1Mg==&mid=2652045155&idx=3&sn=146436a469cd83fb0b2014be68e991b9\",\"level\":0,\"digg_count\":0,\"behot_time\":1489743915,\"image_detail\":[{\"url\":\"http://p3.pstatp.com/large/19d7000d71d92b7c05ec\",\"width\":360,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/19d7000d71d92b7c05ec\"},{\"url\":\"http://pb9.pstatp.com/large/19d7000d71d92b7c05ec\"},{\"url\":\"http://pb1.pstatp.com/large/19d7000d71d92b7c05ec\"}],\"uri\":\"large/19d7000d71d92b7c05ec\",\"height\":200},{\"url\":\"http://p1.pstatp.com/large/19dd000d74cfbdef1067\",\"width\":640,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/19dd000d74cfbdef1067\"},{\"url\":\"http://pb3.pstatp.com/large/19dd000d74cfbdef1067\"},{\"url\":\"http://pb9.pstatp.com/large/19dd000d74cfbdef1067\"}],\"uri\":\"large/19dd000d74cfbdef1067\",\"height\":480}],\"tag\":\"news_politics\",\"has_gallery\":false,\"has_image\":true,\"highlight\":{\"source\":[],\"abstract\":[[17,4],[26,4],[36,4],[46,4]],\"title\":[[0,4]]},\"group_id\":\"6398400751064121602\",\"middle_image\":\"http://p9.pstatp.com/list/1992000695bf8301eb13\"},{\"is_qk\":0,\"publish_time\":1514217652,\"qid\":6503515294080696590,\"abstract\":\"\",\"single_mode\":false,\"image_list\":[],\"display_time\":1514217652,\"answer_count\":9,\"source_url\":\"sslocal://wenda_list?search_result_id=6503515294080696590&qid=6503515294080696590&gd_ext_json=%7B%22qid%22%3A+%226503515294080696590%22%2C+%22log_pb%22%3A+%7B%22impr_id%22%3A+%2220180129215748010008062196331DBD%22%7D%2C+%22query%22%3A+%22%5Cu5c0f%5Cu5eb7%5Cu793e%5Cu4f1a%22%2C+%22source%22%3A+%22search_tab%22%2C+%22enter_from%22%3A+%22click_search%22%7D\",\"tokens\":[\"\u5c0f\u5eb7\",\"\u793e\u4f1a\"],\"more_mode\":false,\"create_time\":1514217652,\"cell_type\":37,\"item_id\":\"6503515294080696590\",\"datetime\":\"2017-12-26 00:00:52\",\"comments_count\":0,\"display_title\":\"\",\"group_id\":\"6503515294080696590\",\"title\":\"\u5c0f\u5eb7\u793e\u4f1a\u6709\u4ec0\u4e48\u6807\u51c6\u5417\uff1f\",\"url\":\"https://www.wukong.com/question/6503515294080696590/\",\"highlight\":{\"source\":[],\"abstract\":[],\"title\":[[0,4]]},\"source\":\"\u609f\u7a7a\u95ee\u7b54\",\"large_image_url\":\"\",\"tag\":\"news\",\"big_pic\":\"\",\"has_image\":true,\"image_url\":\"//p1.pstatp.com/list/190x124/5043001e94d5efb0d710\",\"middle_mode\":true,\"large_mode\":false,\"gallary_image_count\":0},{\"is_qk\":1,\"publish_time\":1464624000,\"qid\":6498316481011384589,\"abstract\":\"\",\"single_mode\":false,\"image_list\":[],\"display_time\":1464624000,\"answer_count\":1,\"source_url\":\"sslocal://webview?search_result_id=6498316481011384589&hide_more=1&title=%E6%82%9F%E7%A9%BA%E5%BF%AB%E7%AD%94&url=http%3A%2F%2Flf.snssdk.com%2Fwendawap%2Fquickqa%2F6498316481011384589%2F%3Fhide_more%3D1%26gd_ext_json%3D%257B%2522qid%2522%253A%25226498316481011384589%2522%252C%2522log_pb%2522%253A%257B%2522impr_id%2522%253A%252220180129215748010008062196331DBD%2522%257D%252C%2522query%2522%253A%2522%255Cu5c0f%255Cu5eb7%255Cu793e%255Cu4f1a%2522%252C%2522source%2522%253A%2522search_tab%2522%252C%2522enter_from%2522%253A%2522click_search%2522%257D&qid=6498316481011384589&gd_ext_json=%7B%22qid%22%3A%226498316481011384589%22%2C%22log_pb%22%3A%7B%22impr_id%22%3A%2220180129215748010008062196331DBD%22%7D%2C%22query%22%3A%22%5Cu5c0f%5Cu5eb7%5Cu793e%5Cu4f1a%22%2C%22source%22%3A%22search_tab%22%2C%22enter_from%22%3A%22click_search%22%7D\",\"tokens\":[\"\u5c0f\u5eb7\",\"\u793e\u4f1a\"],\"more_mode\":false,\"create_time\":1464624000,\"cell_type\":37,\"item_id\":\"6498316481011384589\",\"datetime\":\"2016-05-31 00:00:00\",\"comments_count\":0,\"display_title\":\"\",\"group_id\":\"6498316481011384589\",\"title\":\"\u4ec0\u4e48\u662f\u5c0f\u5eb7\u793e\u4f1a\uff0c\u5c0f\u5eb7\u793e\u4f1a\u7684\u6807\u51c6\u662f\u4ec0\u4e48\uff1f\",\"url\":\"https://www.wukong.com/question/6498316481011384589/\",\"highlight\":{\"source\":[],\"abstract\":[],\"title\":[[3,4],[8,4]]},\"source\":\"\u609f\u7a7a\u5feb\u7b54\",\"large_image_url\":\"\",\"tag\":\"news\",\"big_pic\":\"\",\"has_image\":false,\"image_url\":\"\",\"middle_mode\":false,\"large_mode\":false,\"gallary_image_count\":0},{\"media_creator_id\":5806115967,\"media_name\":\"\u5149\u660e\u7f51\",\"repin_count\":2,\"ban_comment\":0,\"extra\":{\"topic_2048\":{\"155\":0.4378,\"113\":0.5622},\"query_type\":\"NormalAndQueryType\",\"titles_terms\":\"\u4e60\u8fd1\u5e73 \u7684 2017 \u91d1 \u53e5 \u4e4b \u5168\u9762 \u5efa\u6210 \u5c0f\u5eb7 \u793e\u4f1a\",\"has_video\":0,\"topic\":{\"39\":0.2693,\"138\":0.2833,\"43\":0.3417},\"source\":\"\u5149\u660e\u7f51\",\"img_count\":2,\"sstag\":\"nineteenth\"},\"single_mode\":true,\"abstract\":\"[]\u3002\u4e60\u8fd1\u5e73\u76842017\u91d1\u53e5\u4e4b\u5168\u9762\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a\u3002\",\"display_title\":\"\",\"media_avatar_url\":\"//p4.pstatp.com/large/2c68000006582322ad80\",\"datetime\":\"2017-12-27 21:34\",\"article_type\":1,\"more_mode\":false,\"create_time\":1514381664,\"has_m3u8_video\":0,\"keywords\":\"\u4e60\u8fd1\u5e73,\u5c0f\u5eb7\u793e\u4f1a\",\"has_mp4_video\":0,\"favorite_count\":2,\"aggr_type\":0,\"comments_count\":0,\"article_sub_type\":1,\"bury_count\":0,\"title\":\"\u4e60\u8fd1\u5e73\u76842017\u91d1\u53e5\u4e4b\u5168\u9762\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a\",\"show_play_effective_count\":0,\"has_video\":false,\"share_url\":\"http://toutiao.com/group/6504219688489189901/?iid=0&app=news_article\",\"id\":6504219688489189901,\"source\":\"\u5149\u660e\u7f51\",\"comment_count\":0,\"article_url\":\"http://m.gmw.cn/toutiao/2017-12/27/content_120262796.htm\",\"image_url\":\"//p1.pstatp.com/list/190x124/52fe00088079d5032445\",\"middle_mode\":true,\"large_mode\":false,\"item_source_url\":\"/group/6504219688489189901/\",\"media_url\":\"/c/user/5806115967/\",\"display_time\":1514381655,\"publish_time\":1514381655,\"go_detail_count\":333,\"image_list\":[],\"gallary_image_count\":0,\"item_seo_url\":\"/group/6504219688489189901/\",\"tag_id\":6504219688489189901,\"source_url\":\"/group/6504219688489189901/\",\"item_id\":\"6504219688489189901\",\"natant_level\":0,\"seo_url\":\"/group/6504219688489189901/\",\"display_url\":\"http://toutiao.com/group/6504219688489189901/\",\"url\":\"http://m.gmw.cn/toutiao/2017-12/27/content_120262796.htm\",\"level\":0,\"digg_count\":0,\"behot_time\":1514381655,\"image_detail\":[{\"url\":\"http://p1.pstatp.com/large/52fe00088079d5032445\",\"width\":700,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/52fe00088079d5032445\"},{\"url\":\"http://pb3.pstatp.com/large/52fe00088079d5032445\"},{\"url\":\"http://pb9.pstatp.com/large/52fe00088079d5032445\"}],\"uri\":\"large/52fe00088079d5032445\",\"height\":3500},{\"url\":\"http://p3.pstatp.com/large/52fe0008807ae96d064b\",\"width\":700,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/52fe0008807ae96d064b\"},{\"url\":\"http://pb9.pstatp.com/large/52fe0008807ae96d064b\"},{\"url\":\"http://pb1.pstatp.com/large/52fe0008807ae96d064b\"}],\"uri\":\"large/52fe0008807ae96d064b\",\"height\":201}],\"tag\":\"nineteenth\",\"has_gallery\":false,\"has_image\":true,\"highlight\":{\"source\":[],\"abstract\":[[18,4]],\"title\":[[15,4]]},\"group_id\":\"6504219688489189901\",\"middle_image\":\"http://p1.pstatp.com/list/52fe00088079d5032445\"},{\"is_qk\":0,\"publish_time\":1510503665,\"qid\":6487563842283897101,\"abstract\":\"\u4ece\u6765\u6ca1\u6709\u542c\u8bf4\u8fc7\u4e00\u4e2a\u5546\u4eba\u53ef\u4ee5\u4ee3\u9886\u5c0f\u5eb7\uff0c\u96f7\u519b\u7684\u53f7\u53ec\u529b\u592a\u5927\u4e86\u5427\uff0c\u7ecf\u5439\u770b\u5230\u8bf4\u6ca1\u6709\u5c0f\u7c73\uff0c\u6211\u4eec\u7528\u4e0d\u4e0a\u624b\u673a\uff0c\u53ef\u662f\u6211\u4eec\u6709\u591a\u5c11\u4eba\u8fd8\u7528\u5c0f\u7c73\uff1f\u73b0\u5728\u66f4\u626f\u4e86\",\"single_mode\":false,\"image_list\":[],\"display_time\":1510503665,\"answer_count\":4,\"source_url\":\"sslocal://wenda_list?search_result_id=6487563842283897101&qid=6487563842283897101&gd_ext_json=%7B%22qid%22%3A+%226487563842283897101%22%2C+%22log_pb%22%3A+%7B%22impr_id%22%3A+%2220180129215748010008062196331DBD%22%7D%2C+%22query%22%3A+%22%5Cu5c0f%5Cu5eb7%5Cu793e%5Cu4f1a%22%2C+%22source%22%3A+%22search_tab%22%2C+%22enter_from%22%3A+%22click_search%22%7D\",\"tokens\":[\"\u5c0f\u5eb7\",\"\u793e\u4f1a\"],\"more_mode\":false,\"create_time\":1510503665,\"cell_type\":37,\"item_id\":\"6487563842283897101\",\"datetime\":\"2017-11-13 00:21:05\",\"comments_count\":0,\"display_title\":\"\",\"group_id\":\"6487563842283897101\",\"title\":\"\u5c0f\u7c73\u53ef\u4ee5\u4e3a\u5c0f\u5eb7\u793e\u4f1a\u505a\u8d21\u732e\uff1f\",\"url\":\"https://www.wukong.com/question/6487563842283897101/\",\"highlight\":{\"source\":[],\"abstract\":[[15,2]],\"title\":[[5,4]]},\"source\":\"\u609f\u7a7a\u95ee\u7b54\",\"large_image_url\":\"\",\"tag\":\"news\",\"big_pic\":\"\",\"has_image\":true,\"image_url\":\"//p1.pstatp.com/list/190x124/3ea60001d891bb1bc73a\",\"middle_mode\":true,\"large_mode\":false,\"gallary_image_count\":0},{\"media_creator_id\":5558886913,\"media_name\":\"\u6b63\u4e49\u5f25\u52d2\",\"repin_count\":85,\"ban_comment\":0,\"extra\":{\"topic_2048\":{\"25\":0.0596,\"155\":0.0616,\"229\":0.0425,\"872\":0.0804,\"414\":0.0359,\"1875\":0.1732,\"113\":0.1605,\"59\":0.0372,\"1516\":0.0372,\"2011\":0.0377},\"query_type\":\"NormalAndQueryType\",\"titles_terms\":\"\u5341\u4e5d \u5927 \u7cbe\u795e \u5982\u4f55 \u7406\u89e3 \u5168\u9762 \u5efa\u6210 \u5c0f\u5eb7 \u793e\u4f1a \u51b3\u80dc \u671f\",\"has_video\":0,\"topic\":{\"142\":0.0565,\"195\":0.0891,\"43\":0.256,\"180\":0.0423,\"123\":0.024,\"212\":0.026,\"138\":0.3885,\"95\":0.022},\"source\":\"\u6b63\u4e49\u5f25\u52d2\",\"img_count\":1,\"sstag\":\"nineteenth\"},\"single_mode\":true,\"abstract\":\"\u4e60\u8fd1\u5e73\u603b\u4e66\u8bb0\u5728\u515a\u7684\u5341\u4e5d\u5927\u62a5\u544a\u4e2d\u6307\u51fa\uff0c\u201c\u4ece\u73b0\u5728\u5230\u4e8c\u3007\u4e8c\u3007\u5e74\uff0c\u662f\u5168\u9762\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a\u51b3\u80dc\u671f\u201d\u3002\u6240\u8c13\u51b3\u80dc\uff0c\u5c31\u662f\u4e3e\u5168\u515a\u5168\u56fd\u4e4b\u529b\uff0c\u4e3a\u5b9e\u73b0\u7b2c\u4e00\u4e2a\u767e\u5e74\u594b\u6597\u76ee\u6807\u800c\u594b\u6597\uff0c\u786e\u4fdd\u5982\u671f\u3002\",\"display_title\":\"\",\"media_avatar_url\":\"//p1.pstatp.com/large/8715/3217811678\",\"datetime\":\"2017-12-04 11:18\",\"article_type\":0,\"more_mode\":false,\"create_time\":1512357535,\"has_m3u8_video\":0,\"keywords\":\"\u594b\u6597\u76ee\u6807,\u5341\u516d\u5927,\u5e73\u8861\u6027,\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a,\u534f\u8c03\u6027\",\"has_mp4_video\":0,\"favorite_count\":85,\"aggr_type\":0,\"comments_count\":0,\"article_sub_type\":0,\"bury_count\":0,\"title\":\"\u3010\u5341\u4e5d\u5927\u7cbe\u795e\u3011\u5982\u4f55\u7406\u89e3\u5168\u9762\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a\u51b3\u80dc\u671f\uff1f\",\"show_play_effective_count\":0,\"has_video\":false,\"share_url\":\"http://toutiao.com/group/6495526144945685005/?iid=0&app=news_article\",\"id\":6495526144945685005,\"source\":\"\u6b63\u4e49\u5f25\u52d2\",\"comment_count\":0,\"article_url\":\"http://toutiao.com/group/6495526144945685005/\",\"image_url\":\"//p3.pstatp.com/list/190x124/4add0001a95b36e9abaa\",\"middle_mode\":true,\"large_mode\":false,\"item_source_url\":\"/group/6495526144945685005/\",\"media_url\":\"/c/user/5558886913/\",\"display_time\":1512357533,\"publish_time\":1512357533,\"go_detail_count\":1559,\"image_list\":[],\"gallary_image_count\":0,\"item_seo_url\":\"/group/6495526144945685005/\",\"tag_id\":6495526144945685005,\"source_url\":\"/group/6495526144945685005/\",\"item_id\":\"6495526144945685005\",\"natant_level\":0,\"seo_url\":\"/group/6495526144945685005/\",\"display_url\":\"http://toutiao.com/group/6495526144945685005/\",\"url\":\"http://toutiao.com/group/6495526144945685005/\",\"level\":0,\"digg_count\":0,\"behot_time\":1512357533,\"image_detail\":[{\"url\":\"http://p3.pstatp.com/large/4add0001a95b36e9abaa\",\"width\":900,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/4add0001a95b36e9abaa\"},{\"url\":\"http://pb9.pstatp.com/large/4add0001a95b36e9abaa\"},{\"url\":\"http://pb1.pstatp.com/large/4add0001a95b36e9abaa\"}],\"uri\":\"large/4add0001a95b36e9abaa\",\"height\":500}],\"tag\":\"nineteenth\",\"has_gallery\":false,\"has_image\":true,\"highlight\":{\"source\":[],\"abstract\":[[34,4]],\"title\":[[15,4]]},\"group_id\":\"6495526144945685005\",\"middle_image\":\"http://p3.pstatp.com/list/4add0001a95b36e9abaa\"},{\"is_qk\":0,\"publish_time\":1501202383,\"qid\":6447615141625200909,\"abstract\":\"A\u623f\u5b50\u3001B\u94b1\u3001C\u4f34\u4fa3\u8fd8\u662fD\u5065\u5eb7\uff1f\",\"single_mode\":false,\"image_list\":[],\"display_time\":1501202383,\"answer_count\":1,\"source_url\":\"sslocal://wenda_list?search_result_id=6447615141625200909&qid=6447615141625200909&gd_ext_json=%7B%22qid%22%3A+%226447615141625200909%22%2C+%22log_pb%22%3A+%7B%22impr_id%22%3A+%2220180129215748010008062196331DBD%22%7D%2C+%22query%22%3A+%22%5Cu5c0f%5Cu5eb7%5Cu793e%5Cu4f1a%22%2C+%22source%22%3A+%22search_tab%22%2C+%22enter_from%22%3A+%22click_search%22%7D\",\"tokens\":[\"\u5c0f\u5eb7\",\"\u793e\u4f1a\"],\"more_mode\":false,\"create_time\":1501202383,\"cell_type\":37,\"item_id\":\"6447615141625200909\",\"datetime\":\"2017-07-28 08:39:43\",\"comments_count\":0,\"display_title\":\"\",\"group_id\":\"6447615141625200909\",\"title\":\"\u5c0f\u5eb7\u793e\u4f1a\u4ec0\u4e48\u6700\u91cd\u8981\uff1f\",\"url\":\"https://www.wukong.com/question/6447615141625200909/\",\"highlight\":{\"source\":[],\"abstract\":[],\"title\":[[0,4]]},\"source\":\"\u609f\u7a7a\u95ee\u7b54\",\"large_image_url\":\"\",\"tag\":\"news\",\"big_pic\":\"\",\"has_image\":true,\"image_url\":\"//p3.pstatp.com/list/190x124/2c6b000a12d0a7fc340a\",\"middle_mode\":true,\"large_mode\":false,\"gallary_image_count\":0},{\"cell_type\":20,\"queries\":[{\"url\":\"/search/keyword=2020%E5%B9%B4%E5%85%A8%E9%9D%A2%E5%B0%8F%E5%BA%B7\",\"text\":\"2020\u5e74\u5168\u9762\u5c0f\u5eb7\"},{\"url\":\"/search/keyword=%E5%B0%8F%E5%BA%B7%E7%9A%84%E6%A0%87%E5%87%86%E6%98%AF%E4%BB%80%E4%B9%88\",\"text\":\"\u5c0f\u5eb7\u7684\u6807\u51c6\u662f\u4ec0\u4e48\"},{\"url\":\"/search/keyword=%E5%B0%8F%E5%BA%B7%E7%A4%BE%E4%BC%9A%E6%A0%87%E5%87%86\",\"text\":\"\u5c0f\u5eb7\u793e\u4f1a\u6807\u51c6\"},{\"url\":\"/search/keyword=%E4%BB%80%E4%B9%88%E6%98%AF%E5%B0%8F%E5%BA%B7\",\"text\":\"\u4ec0\u4e48\u662f\u5c0f\u5eb7\"},{\"url\":\"/search/keyword=%E7%B1%B3%E9%9B%AA\",\"text\":\"\u7c73\u96ea\"},{\"url\":\"/search/keyword=%E5%B0%8F%E5%BA%B7%E9%98%B6%E6%AE%B5\",\"text\":\"\u5c0f\u5eb7\u9636\u6bb5\"},{\"url\":\"/search/keyword=%E5%85%A8%E9%9D%A2%E5%BB%BA%E6%88%90%E5%B0%8F%E5%BA%B7%E7%A4%BE%E4%BC%9A\",\"text\":\"\u5168\u9762\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a\"},{\"url\":\"/search/keyword=%E8%80%81%E8%B5%96\",\"text\":\"\u8001\u8d56\"}]},{\"is_qk\":1,\"publish_time\":1390924800,\"qid\":6496177537071186189,\"abstract\":\"\",\"single_mode\":false,\"image_list\":[],\"display_time\":1390924800,\"answer_count\":1,\"source_url\":\"sslocal://webview?search_result_id=6496177537071186189&hide_more=1&title=%E6%82%9F%E7%A9%BA%E5%BF%AB%E7%AD%94&url=http%3A%2F%2Flf.snssdk.com%2Fwendawap%2Fquickqa%2F6496177537071186189%2F%3Fhide_more%3D1%26gd_ext_json%3D%257B%2522qid%2522%253A%25226496177537071186189%2522%252C%2522log_pb%2522%253A%257B%2522impr_id%2522%253A%252220180129215748010008062196331DBD%2522%257D%252C%2522query%2522%253A%2522%255Cu5c0f%255Cu5eb7%255Cu793e%255Cu4f1a%2522%252C%2522source%2522%253A%2522search_tab%2522%252C%2522enter_from%2522%253A%2522click_search%2522%257D&qid=6496177537071186189&gd_ext_json=%7B%22qid%22%3A%226496177537071186189%22%2C%22log_pb%22%3A%7B%22impr_id%22%3A%2220180129215748010008062196331DBD%22%7D%2C%22query%22%3A%22%5Cu5c0f%5Cu5eb7%5Cu793e%5Cu4f1a%22%2C%22source%22%3A%22search_tab%22%2C%22enter_from%22%3A%22click_search%22%7D\",\"tokens\":[\"\u5c0f\u5eb7\",\"\u793e\u4f1a\"],\"more_mode\":false,\"create_time\":1390924800,\"cell_type\":37,\"item_id\":\"6496177537071186189\",\"datetime\":\"2014-01-29 00:00:00\",\"comments_count\":0,\"display_title\":\"\",\"group_id\":\"6496177537071186189\",\"title\":\"\u5c0f\u5eb7\u793e\u4f1a\u5341\u5927\u6307\u6807\uff1f\",\"url\":\"https://www.wukong.com/question/6496177537071186189/\",\"highlight\":{\"source\":[],\"abstract\":[],\"title\":[[0,4]]},\"source\":\"\u609f\u7a7a\u5feb\u7b54\",\"large_image_url\":\"\",\"tag\":\"news\",\"big_pic\":\"\",\"has_image\":false,\"image_url\":\"\",\"middle_mode\":false,\"large_mode\":false,\"gallary_image_count\":0},{\"media_creator_id\":50394389985,\"media_name\":\"\u5370\u6c5f\u53bf\u5987\u8054\",\"repin_count\":6,\"ban_comment\":0,\"extra\":{\"topic_2048\":{},\"query_type\":\"LongtermESQueryType\",\"titles_terms\":\"\u5168\u9762 \u5c0f\u5eb7 \u793e\u4f1a \u5efa\u8bbe \u77e5\u8bc6 \u4ecb\u7ecd\",\"has_video\":0,\"topic\":{},\"source\":\"\",\"img_count\":0,\"sstag\":\"\"},\"single_mode\":true,\"abstract\":\"1)\u6211\u53bf2017\u5e74\u7533\u62a5\u8d35\u5dde\u7701\u540c\u6b65\u5c0f\u5eb7\u521b\u5efa\u8fbe\u6807\u53bf\uff0c\u662f\u5168\u5e02\u4eca\u5e74\u552f\u4e00\u7533\u62a5\u53bf\u30023)\u4ec0\u4e48\u662f\u5c0f\u5eb7\uff1a\u201c\u5c0f\u5eb7\u201d\u5c31\u662f\u6e29\u9971\u6709\u4f59\u800c\u5bcc\u88d5\u76f8\u5bf9\u4e0d\u8db3\u3002\",\"display_title\":\"\",\"media_avatar_url\":\"//p3.pstatp.com/large/bc30001af580a306f7c\",\"datetime\":\"2017-07-19 18:33\",\"article_type\":0,\"more_mode\":false,\"create_time\":1500460381,\"has_m3u8_video\":0,\"keywords\":\"\u5c0f\u5eb7\u793e\u4f1a,\u603b\u4f53\u5c0f\u5eb7,\u540c\u6b65\u5c0f\u5eb7\u521b\u5efa,\u53ef\u652f\u914d\u6536\u5165,\u8bd7\u7ecf,\u519c\u6751\u5c45\u6c11\",\"has_mp4_video\":0,\"favorite_count\":6,\"aggr_type\":0,\"comments_count\":0,\"article_sub_type\":0,\"bury_count\":0,\"title\":\"\u5168\u9762\u5c0f\u5eb7\u793e\u4f1a\u5efa\u8bbe\u77e5\u8bc6\u4ecb\u7ecd\",\"show_play_effective_count\":0,\"has_video\":false,\"share_url\":\"http://toutiao.com/group/6444346165076443405/?iid=0&app=news_article\",\"id\":6444346165076443405,\"source\":\"\u5370\u6c5f\u53bf\u5987\u8054\",\"comment_count\":0,\"article_url\":\"http://mp.weixin.qq.com/s?src=3&timestamp=1500460376&ver=1&signature=sp-5*d5z-A4wS*1O6Can-0M2VUNy5AGc4Y9JhyjGztW*L3Ne8xKLRhz5Y8YZ8lW7K2Sn77VE7JSHBPSf-q9RXNv2Du9YKV9PoqgXs2sNCH1tfiDF3e2qmsoV*2bDntnWwo2TNLjGM5bMZtmEgCwKgAT41iC-ZsxvM43Cq9zbgng=\",\"image_url\":\"//p3.pstatp.com/list/190x124/2f9500013170d859c12a\",\"middle_mode\":false,\"large_mode\":false,\"item_source_url\":\"/group/6444346165076443405/\",\"media_url\":\"/c/user/50394389985/\",\"display_time\":1500460380,\"publish_time\":1500460380,\"go_detail_count\":340,\"image_list\":[],\"gallary_image_count\":0,\"item_seo_url\":\"/i6444428267083530765/\",\"tag_id\":6444346165076443405,\"source_url\":\"/group/6444346165076443405/\",\"item_id\":\"6444428267083530765\",\"natant_level\":0,\"seo_url\":\"/i6444428267083530765/\",\"display_url\":\"http://toutiao.com/group/6444346165076443405/\",\"url\":\"http://mp.weixin.qq.com/s?src=3&timestamp=1500460376&ver=1&signature=sp-5*d5z-A4wS*1O6Can-0M2VUNy5AGc4Y9JhyjGztW*L3Ne8xKLRhz5Y8YZ8lW7K2Sn77VE7JSHBPSf-q9RXNv2Du9YKV9PoqgXs2sNCH1tfiDF3e2qmsoV*2bDntnWwo2TNLjGM5bMZtmEgCwKgAT41iC-ZsxvM43Cq9zbgng=\",\"level\":0,\"digg_count\":0,\"behot_time\":1500460380,\"image_detail\":[{\"url\":\"http://p3.pstatp.com/large/2f9500013170d859c12a\",\"width\":720,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/2f9500013170d859c12a\"},{\"url\":\"http://pb9.pstatp.com/large/2f9500013170d859c12a\"},{\"url\":\"http://pb1.pstatp.com/large/2f9500013170d859c12a\"}],\"uri\":\"large/2f9500013170d859c12a\",\"height\":84},{\"url\":\"http://p3.pstatp.com/large/2dfd0007fc64cbbdccea\",\"width\":300,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/2dfd0007fc64cbbdccea\"},{\"url\":\"http://pb9.pstatp.com/large/2dfd0007fc64cbbdccea\"},{\"url\":\"http://pb1.pstatp.com/large/2dfd0007fc64cbbdccea\"}],\"uri\":\"large/2dfd0007fc64cbbdccea\",\"height\":64},{\"url\":\"http://p3.pstatp.com/large/2f86000852204ec85283\",\"width\":400,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/2f86000852204ec85283\"},{\"url\":\"http://pb9.pstatp.com/large/2f86000852204ec85283\"},{\"url\":\"http://pb1.pstatp.com/large/2f86000852204ec85283\"}],\"uri\":\"large/2f86000852204ec85283\",\"height\":40},{\"url\":\"http://p3.pstatp.com/large/2f90000853172288ac04\",\"width\":550,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/2f90000853172288ac04\"},{\"url\":\"http://pb9.pstatp.com/large/2f90000853172288ac04\"},{\"url\":\"http://pb1.pstatp.com/large/2f90000853172288ac04\"}],\"uri\":\"large/2f90000853172288ac04\",\"height\":21},{\"url\":\"http://p3.pstatp.com/large/2dfd0006e3b6ca0289e1\",\"width\":600,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/2dfd0006e3b6ca0289e1\"},{\"url\":\"http://pb9.pstatp.com/large/2dfd0006e3b6ca0289e1\"},{\"url\":\"http://pb1.pstatp.com/large/2dfd0006e3b6ca0289e1\"}],\"uri\":\"large/2dfd0006e3b6ca0289e1\",\"height\":30},{\"url\":\"http://p1.pstatp.com/large/2f8e0006d6b0ae113736\",\"width\":500,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/2f8e0006d6b0ae113736\"},{\"url\":\"http://pb3.pstatp.com/large/2f8e0006d6b0ae113736\"},{\"url\":\"http://pb9.pstatp.com/large/2f8e0006d6b0ae113736\"}],\"uri\":\"large/2f8e0006d6b0ae113736\",\"height\":40},{\"url\":\"http://p1.pstatp.com/large/2f87000452a82205496b\",\"width\":420,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/2f87000452a82205496b\"},{\"url\":\"http://pb3.pstatp.com/large/2f87000452a82205496b\"},{\"url\":\"http://pb9.pstatp.com/large/2f87000452a82205496b\"}],\"uri\":\"large/2f87000452a82205496b\",\"height\":30},{\"url\":\"http://p3.pstatp.com/large/2f85000a7c0ebc1ebc9e\",\"width\":350,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/2f85000a7c0ebc1ebc9e\"},{\"url\":\"http://pb9.pstatp.com/large/2f85000a7c0ebc1ebc9e\"},{\"url\":\"http://pb1.pstatp.com/large/2f85000a7c0ebc1ebc9e\"}],\"uri\":\"large/2f85000a7c0ebc1ebc9e\",\"height\":21},{\"url\":\"http://p1.pstatp.com/large/2f85000db4c88808604e\",\"width\":597,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/2f85000db4c88808604e\"},{\"url\":\"http://pb3.pstatp.com/large/2f85000db4c88808604e\"},{\"url\":\"http://pb9.pstatp.com/large/2f85000db4c88808604e\"}],\"uri\":\"large/2f85000db4c88808604e\",\"height\":50}],\"tag\":\"news_agriculture\",\"has_gallery\":false,\"has_image\":true,\"highlight\":{\"source\":[],\"abstract\":[[16,2],[40,2],[44,2]],\"title\":[[2,4]]},\"group_id\":\"6444346165076443405\"},{\"play_effective_count\":\"210\",\"media_name\":\"\u7528\u623770891637423\",\"repin_count\":1,\"ban_comment\":0,\"extra\":{\"topic_2048\":{},\"query_type\":\"LongtermESQueryType\",\"titles_terms\":\"\u5c0f\u5eb7 \u793e\u4f1a \u7684 \u519c\u6c11 \u8def\",\"has_video\":0,\"topic\":{},\"source\":\"\",\"img_count\":0,\"sstag\":\"\"},\"single_mode\":true,\"abstract\":\"\",\"display_title\":\"\",\"media_avatar_url\":\"\",\"datetime\":\"2017-10-23 09:20\",\"article_type\":0,\"more_mode\":false,\"create_time\":1508721610,\"has_m3u8_video\":0,\"keywords\":\"\",\"video_duration\":100,\"has_mp4_video\":0,\"favorite_count\":1,\"aggr_type\":0,\"comments_count\":0,\"article_sub_type\":0,\"bury_count\":0,\"title\":\"\u5c0f\u5eb7\u793e\u4f1a\u7684\u519c\u6c11\u8def\",\"show_play_effective_count\":1,\"has_video\":true,\"share_url\":\"http://toutiao.com/group/6479909974154674701/?iid=0&app=news_article\",\"id\":6479909974154674701,\"source\":\"\u7528\u623770891637423\",\"comment_count\":0,\"article_url\":\"http://toutiao.com/group/6479909974154674701/\",\"image_url\":\"//p3.pstatp.com/list/190x124/40f50002e78bf61d3419\",\"middle_mode\":true,\"large_mode\":false,\"item_source_url\":\"/group/6479909974154674701/\",\"media_url\":\"http://toutiao.com/m1581953476593678/\",\"display_time\":1508721610,\"publish_time\":1508721610,\"go_detail_count\":34,\"image_list\":[],\"gallary_image_count\":0,\"item_seo_url\":\"/group/6479909974154674701/\",\"video_duration_str\":\"01:40\",\"source_url\":\"/group/6479909974154674701/\",\"tag_id\":6479909974154674701,\"item_id\":\"6479909974154674701\",\"natant_level\":0,\"seo_url\":\"/group/6479909974154674701/\",\"display_url\":\"http://toutiao.com/group/6479909974154674701/\",\"url\":\"http://toutiao.com/group/6479909974154674701/\",\"level\":0,\"digg_count\":1,\"behot_time\":1508721610,\"image_detail\":[],\"tag\":\"video_agriculture\",\"has_gallery\":false,\"has_image\":false,\"highlight\":{\"source\":[],\"abstract\":[],\"title\":[[0,4]]},\"group_id\":\"6479909974154674701\",\"middle_image\":\"http://p3.pstatp.com/list/40f50002e78bf61d3419\"},{\"media_creator_id\":5757425042,\"media_name\":\"\u65b0\u534e\u793e\",\"repin_count\":1,\"ban_comment\":0,\"extra\":{\"topic_2048\":{\"977\":0.038,\"1948\":0.0215,\"1935\":0.3505,\"113\":0.0874,\"1695\":0.0697,\"489\":0.0847,\"2023\":0.064,\"1887\":0.2015,\"1208\":0.0306},\"query_type\":\"RealTimeQueryType\",\"titles_terms\":\"\u6cd5\u6cbb \u516c\u5b89\u90e8 \u4e25\u5389 \u6253\u51fb \u6574\u6cbb \u8d4c\u535a \u8fdd\u6cd5 \u72af\u7f6a \u6d3b\u52a8\",\"has_video\":0,\"topic\":{\"147\":0.0361,\"237\":0.6109,\"37\":0.0278,\"76\":0.0203,\"138\":0.1644,\"95\":0.046},\"source\":\"\u65b0\u534e\u793e\",\"img_count\":0,\"sstag\":\"news_politics\"},\"single_mode\":false,\"abstract\":\"\u65b0\u534e\u793e\u5317\u4eac1\u670829\u65e5\u7535\u516c\u5b89\u673a\u5173\u5c06\u4e25\u5389\u6253\u51fb\u6574\u6cbb\u8d4c\u535a\u8fdd\u6cd5\u72af\u7f6a\u6d3b\u52a8\u3002\u575a\u6301\u6253\u65e9\u6253\u5c0f\u3001\u9732\u5934\u5c31\u6253\u3001\u4e0d\u505c\u5730\u6253\uff0c\u4e3a\u51b3\u80dc\u5168\u9762\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a\u3001\u5efa\u8bbe\u793e\u4f1a\u4e3b\u4e49\u73b0\u4ee3\u5316\u56fd\u5bb6\u8425\u9020\u826f\u597d\u6cbb\u5b89\u73af\u5883\u3002\",\"display_title\":\"\",\"media_avatar_url\":\"//p2.pstatp.com/large/9519/925052030\",\"datetime\":\"2018-01-29 20:20\",\"article_type\":0,\"more_mode\":false,\"create_time\":1517228424,\"has_m3u8_video\":0,\"keywords\":\"\u6574\u6cbb\u8d4c\u535a\u8fdd\u6cd5\u72af\u7f6a\u6d3b\u52a8,\u516c\u5b89\u673a\u5173,\u6253\u51fb\u6574\u6cbb,\u516c\u5b89\u90e8,\u6cd5\u6cbb\",\"has_mp4_video\":0,\"favorite_count\":1,\"aggr_type\":0,\"article_sub_type\":0,\"bury_count\":0,\"title\":\"\uff08\u6cd5\u6cbb\uff09\u516c\u5b89\u90e8\uff1a\u4e25\u5389\u6253\u51fb\u6574\u6cbb\u8d4c\u535a\u8fdd\u6cd5\u72af\u7f6a\u6d3b\u52a8\",\"show_play_effective_count\":0,\"has_video\":false,\"share_url\":\"http://toutiao.com/group/6516446462589534734/?iid=0&app=news_article\",\"id\":6516446462589534734,\"source\":\"\u65b0\u534e\u793e\",\"comment_count\":3,\"article_url\":\"http://home.xinhua-news.com/rss/newsdetail/983631c2310aa06da88587df2a24f820/1517227744792\",\"comments_count\":3,\"middle_mode\":false,\"large_mode\":false,\"item_source_url\":\"/group/6516446462589534734/\",\"media_url\":\"/c/user/5757425042/\",\"display_time\":1517233719,\"publish_time\":1517233719,\"go_detail_count\":443,\"image_list\":[],\"gallary_image_count\":0,\"item_seo_url\":\"/i6516446462589534734/\",\"tag_id\":6516446462589534734,\"source_url\":\"/group/6516446462589534734/\",\"item_id\":\"6516446462589534734\",\"natant_level\":0,\"seo_url\":\"/i6516446462589534734/\",\"display_url\":\"http://toutiao.com/group/6516446462589534734/\",\"url\":\"http://home.xinhua-news.com/rss/newsdetail/983631c2310aa06da88587df2a24f820/1517227744792\",\"level\":0,\"digg_count\":0,\"behot_time\":1517233719,\"summary\":\"\u5c0f\u5eb7\u793e\u4f1a\u3001\u5efa\u8bbe\u793e\u4f1a\u4e3b\u4e49\u73b0\u4ee3\u5316\u56fd\u5bb6\u8425\u9020\u826f\u597d\u6cbb\u5b89\u73af\u5883\u3002\u516c\u5b89\u90e829\u65e5\u5728\u4eac\u53ec\u5f00\u89c6\u9891\u4f1a\u8bae\uff0c\u90e8\u7f72\u5168\u56fd\u516c\u5b89\u673a\u5173\u8fdb\u4e00\u6b65\u6df1\u5316\u6253\u51fb\u6574\u6cbb\u8d4c\",\"image_detail\":[],\"tag\":\"news_politics\",\"has_gallery\":false,\"has_image\":false,\"highlight\":{\"source\":[],\"abstract\":[[55,4]],\"summary\":[[0,4]],\"title\":[]},\"group_id\":\"6516446462589534734\"},{\"is_qk\":0,\"publish_time\":1517228823,\"qid\":6516448177090986247,\"abstract\":\"\",\"single_mode\":false,\"image_list\":[],\"display_time\":1517228823,\"answer_count\":2,\"source_url\":\"sslocal://wenda_list?search_result_id=6516448177090986247&qid=6516448177090986247&gd_ext_json=%7B%22qid%22%3A+%226516448177090986247%22%2C+%22log_pb%22%3A+%7B%22impr_id%22%3A+%2220180129215748010008062196331DBD%22%7D%2C+%22query%22%3A+%22%5Cu5c0f%5Cu5eb7%5Cu793e%5Cu4f1a%22%2C+%22source%22%3A+%22search_tab%22%2C+%22enter_from%22%3A+%22click_search%22%7D\",\"tokens\":[\"\u5c0f\u5eb7\",\"\u793e\u4f1a\"],\"more_mode\":false,\"create_time\":1517228823,\"cell_type\":37,\"item_id\":\"6516448177090986247\",\"datetime\":\"2018-01-29 20:27:03\",\"comments_count\":0,\"display_title\":\"\",\"group_id\":\"6516448177090986247\",\"title\":\"\u5b54\u5b50\u6240\u8bf4\u7684\u201c\u5c0f\u5eb7\u793e\u4f1a\u201d\u6307\u7684\u662f\u4ec0\u4e48\u65f6\u4ee3\uff1f\",\"url\":\"https://www.wukong.com/question/6516448177090986247/\",\"highlight\":{\"source\":[],\"abstract\":[],\"title\":[[6,4]]},\"source\":\"\u609f\u7a7a\u95ee\u7b54\",\"large_image_url\":\"\",\"tag\":\"news\",\"big_pic\":\"\",\"has_image\":false,\"image_url\":\"\",\"middle_mode\":false,\"large_mode\":false,\"gallary_image_count\":0},{\"media_creator_id\":55445027699,\"play_effective_count\":\"74\",\"media_name\":\"Molly\u8bf4\",\"repin_count\":1,\"ban_comment\":0,\"extra\":{\"topic_2048\":{},\"query_type\":\"LongtermESQueryType\",\"titles_terms\":\"\u5c0f\u5eb7 \u793e\u4f1a \u4eba\u4eba \u4eab \u793e\u4f1a \u8d23\u4efb\u4eba \u4eba \u8d1f\",\"has_video\":0,\"topic\":{},\"source\":\"\",\"img_count\":0,\"sstag\":\"\"},\"single_mode\":true,\"abstract\":\"\u5317\u5927\u5149\u534e\u65b0\u5e74\u8bba\u575b3\",\"display_title\":\"\",\"media_avatar_url\":\"//p1.pstatp.com/large/249800255baddfe336ba\",\"datetime\":\"2017-07-24 01:06\",\"article_type\":0,\"more_mode\":false,\"create_time\":1500829611,\"has_m3u8_video\":0,\"keywords\":\"\u5c0f\u5eb7\u793e\u4f1a,\u8d23\u4efb\u4eba\",\"video_duration\":66,\"has_mp4_video\":0,\"favorite_count\":1,\"aggr_type\":0,\"comments_count\":0,\"article_sub_type\":0,\"bury_count\":0,\"title\":\"\u5c0f\u5eb7\u793e\u4f1a\u4eba\u4eba\u4eab\uff0c\u793e\u4f1a\u8d23\u4efb\u4eba\u4eba\u8d1f\",\"show_play_effective_count\":1,\"has_video\":true,\"share_url\":\"http://toutiao.com/group/6446014033488249358/?iid=0&app=news_article\",\"id\":6446014033488249358,\"source\":\"Molly\u8bf4\",\"comment_count\":0,\"article_url\":\"http://toutiao.com/group/6446014033488249358/\",\"image_url\":\"//p3.pstatp.com/list/190x124/308500169702dc4ae885\",\"middle_mode\":true,\"large_mode\":false,\"item_source_url\":\"/group/6446014033488249358/\",\"media_url\":\"/c/user/55445027699/\",\"display_time\":1500829596,\"publish_time\":1500829596,\"go_detail_count\":92,\"image_list\":[],\"gallary_image_count\":0,\"item_seo_url\":\"/group/6446014033488249358/\",\"video_duration_str\":\"01:06\",\"source_url\":\"/group/6446014033488249358/\",\"tag_id\":6446014033488249358,\"item_id\":\"6446014033488249358\",\"natant_level\":0,\"seo_url\":\"/group/6446014033488249358/\",\"display_url\":\"http://toutiao.com/group/6446014033488249358/\",\"url\":\"http://toutiao.com/group/6446014033488249358/\",\"level\":0,\"digg_count\":1,\"behot_time\":1500829596,\"image_detail\":[],\"tag\":\"news\",\"has_gallery\":false,\"has_image\":false,\"highlight\":{\"source\":[],\"abstract\":[],\"title\":[[0,4]]},\"group_id\":\"6446014033488249358\",\"middle_image\":\"http://p3.pstatp.com/list/308500169702dc4ae885\"},{\"media_creator_id\":5790308670,\"media_name\":\"\u793e\u4f1a\u79d1\u5b66\u62a5\",\"repin_count\":2,\"ban_comment\":0,\"extra\":{\"topic_2048\":{\"25\":0.0647,\"1848\":0.1143,\"1834\":0.027,\"210\":0.0421,\"715\":0.0313,\"1336\":0.0419,\"1875\":0.1235,\"353\":0.0273,\"508\":0.1695,\"1914\":0.0323},\"query_type\":\"RealTimeQueryType\",\"titles_terms\":\"\u89c2\u70b9 \u6559\u80b2 \u5b85\u57fa\u5730 \u642c\u8fc1 \u8fd9 \u90fd \u662f \u4e61\u6751 \u632f\u5174 \u8be5 \u76f4\u9762 \u7684 \u95ee\u9898\",\"has_video\":0,\"topic\":{\"39\":0.0382,\"195\":0.0698,\"56\":0.1666,\"62\":0.0784,\"43\":0.1408,\"180\":0.0873,\"138\":0.0422,\"171\":0.0231,\"255\":0.2437},\"source\":\"\u793e\u4f1a\u79d1\u5b66\u62a5\",\"img_count\":5,\"sstag\":\"news_agriculture\"},\"single_mode\":true,\"abstract\":\"\u539f\u6587\uff1a\u300a\u4e61\u6751\u632f\u5174\uff1a\u52a9\u529b\u5168\u9762\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a\u300b\u4f5c\u8005\uff1a\u4e2d\u56fd\u519c\u4e1a\u5927\u5b66\u519c\u6c11\u95ee\u9898\u7814\u7a76\u6240\u6240\u957f\u6731\u542f\u81fb\u4e61\u6751\u6559\u80b2\u590d\u5174\u662f\u4e61\u6751\u632f\u5174\u7684\u91cd\u8981\u5185\u5bb9\u64a4\u70b9\u5e76\u6821\u7684\u521d\u8877\u662f\u56e0\u4e3a\u4e61\u6751\u513f\u7ae5\u6570\u91cf\u51cf\u5c11\uff0c\u96be\u4ee5\u6491\u8d77\u4e00\u6240\u50cf\u6837\u7684\u5b66\u6821\uff0c\u56e0\u6b64\uff0c\u4e3a\u4e86\u8ba9\u4e61\u6751\u513f\u7ae5\u63a5\u53d7\u66f4\u201c\u4f18\u8d28\u7684\u6559\u80b2\u201d\uff0c\u5c31\u53ea\u80fd\u8ba9\u4ed6\u4eec\u79bb\u5f00\u4e61\u6751\u3002\",\"display_title\":\"\",\"media_avatar_url\":\"//p2.pstatp.com/large/10070/1391121828\",\"datetime\":\"2018-01-29 19:32\",\"article_type\":0,\"more_mode\":true,\"create_time\":1517225555,\"has_m3u8_video\":0,\"keywords\":\"\u64a4\u70b9\u5e76\u6821,\u793e\u4f1a\u79d1\u5b66,\u5b85\u57fa\u5730,\u4e61\u6751\u632f\u5174,\u519c\u4e1a\",\"has_mp4_video\":0,\"favorite_count\":2,\"aggr_type\":0,\"comments_count\":1,\"article_sub_type\":0,\"bury_count\":0,\"title\":\"\u89c2\u70b9\uff5c\u6559\u80b2\u3001\u5b85\u57fa\u5730\u3001\u642c\u8fc1\uff0c\u8fd9\u90fd\u662f\u201c\u4e61\u6751\u632f\u5174\u201d\u8be5\u76f4\u9762\u7684\u95ee\u9898\",\"show_play_effective_count\":0,\"has_video\":false,\"share_url\":\"http://toutiao.com/group/6516434142245175812/?iid=0&app=news_article\",\"id\":6516434142245175812,\"source\":\"\u793e\u4f1a\u79d1\u5b66\u62a5\",\"comment_count\":1,\"article_url\":\"http://mp.weixin.qq.com/s?src=11&timestamp=1517225533&ver=666&signature=56oU-PlZ7Ib1PFZLPijQ6td5vfWj2mI*xZK-YSXA-SpWpPcLjPdj8fidxQZQK0LLMuA9jSZuJaNLKUjBTUoFEIAWoU8cP9b2RbTSI-a-ZHkY5YGmu44vfsDnk*VqQw17&new=1\",\"image_url\":\"//p3.pstatp.com/list/190x124/5e3900077d81b7dfd877\",\"middle_mode\":false,\"large_mode\":false,\"item_source_url\":\"/group/6516434142245175812/\",\"media_url\":\"/c/user/5790308670/\",\"display_time\":1517225553,\"publish_time\":1517225553,\"go_detail_count\":419,\"image_list\":[{\"url\":\"//p3.pstatp.com/list/190x124/5e3900077d81b7dfd877\"},{\"url\":\"//p3.pstatp.com/list/190x124/5e380007aeb7adb863d5\"},{\"url\":\"//p3.pstatp.com/list/190x124/5e3b0005cf4b3680823d\"}],\"gallary_image_count\":0,\"item_seo_url\":\"/i6516434142245175812/\",\"tag_id\":6516434142245175812,\"source_url\":\"/group/6516434142245175812/\",\"item_id\":\"6516434142245175812\",\"natant_level\":0,\"seo_url\":\"/i6516434142245175812/\",\"display_url\":\"http://toutiao.com/group/6516434142245175812/\",\"url\":\"http://mp.weixin.qq.com/s?src=11&timestamp=1517225533&ver=666&signature=56oU-PlZ7Ib1PFZLPijQ6td5vfWj2mI*xZK-YSXA-SpWpPcLjPdj8fidxQZQK0LLMuA9jSZuJaNLKUjBTUoFEIAWoU8cP9b2RbTSI-a-ZHkY5YGmu44vfsDnk*VqQw17&new=1\",\"level\":0,\"digg_count\":0,\"behot_time\":1517225553,\"summary\":\"\u5c0f\u5eb7\u793e\u4f1a\u300b\u4f5c\u8005\uff1a\u4e2d\u56fd\u519c\u4e1a\u5927\u5b66\u519c\u6c11\u95ee\u9898\u7814\u7a76\u6240\u6240\u957f \u6731\u542f\u81fb\u4e61\u6751\u6559\u80b2\u590d\u5174\u662f\u4e61\u6751\u632f\u5174\u7684\u91cd\u8981\u5185\",\"image_detail\":[{\"url\":\"http://p3.pstatp.com/large/5e3900077d81b7dfd877\",\"width\":640,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/5e3900077d81b7dfd877\"},{\"url\":\"http://pb9.pstatp.com/large/5e3900077d81b7dfd877\"},{\"url\":\"http://pb1.pstatp.com/large/5e3900077d81b7dfd877\"}],\"uri\":\"large/5e3900077d81b7dfd877\",\"height\":386},{\"url\":\"http://p3.pstatp.com/large/5e380007aeb7adb863d5\",\"width\":500,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/5e380007aeb7adb863d5\"},{\"url\":\"http://pb9.pstatp.com/large/5e380007aeb7adb863d5\"},{\"url\":\"http://pb1.pstatp.com/large/5e380007aeb7adb863d5\"}],\"uri\":\"large/5e380007aeb7adb863d5\",\"height\":333},{\"url\":\"http://p3.pstatp.com/large/5e3b0005cf4b3680823d\",\"width\":530,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/5e3b0005cf4b3680823d\"},{\"url\":\"http://pb9.pstatp.com/large/5e3b0005cf4b3680823d\"},{\"url\":\"http://pb1.pstatp.com/large/5e3b0005cf4b3680823d\"}],\"uri\":\"large/5e3b0005cf4b3680823d\",\"height\":396},{\"url\":\"http://p1.pstatp.com/large/5e36001830f69dc29bda\",\"width\":500,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/5e36001830f69dc29bda\"},{\"url\":\"http://pb3.pstatp.com/large/5e36001830f69dc29bda\"},{\"url\":\"http://pb9.pstatp.com/large/5e36001830f69dc29bda\"}],\"uri\":\"large/5e36001830f69dc29bda\",\"height\":375},{\"url\":\"http://p3.pstatp.com/large/5e380007aeb8029d2abe\",\"width\":640,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/5e380007aeb8029d2abe\"},{\"url\":\"http://pb9.pstatp.com/large/5e380007aeb8029d2abe\"},{\"url\":\"http://pb1.pstatp.com/large/5e380007aeb8029d2abe\"}],\"uri\":\"large/5e380007aeb8029d2abe\",\"height\":427}],\"tag\":\"news_agriculture\",\"has_gallery\":false,\"has_image\":true,\"highlight\":{\"source\":[[0,2]],\"abstract\":[[15,4]],\"summary\":[[0,4]],\"title\":[]},\"group_id\":\"6516434142245175812\",\"middle_image\":{\"url\":\"http://p3.pstatp.com/list/5e3900077d81b7dfd877\",\"width\":640,\"url_list\":[{\"url\":\"http://p3.pstatp.com/list/5e3900077d81b7dfd877\"},{\"url\":\"http://pb9.pstatp.com/list/5e3900077d81b7dfd877\"},{\"url\":\"http://pb1.pstatp.com/list/5e3900077d81b7dfd877\"}],\"uri\":\"list/5e3900077d81b7dfd877\",\"height\":386}},{\"media_creator_id\":73082379083,\"media_name\":\"\u4f18\u5f69\u7f8e\u5bb6\",\"repin_count\":1,\"ban_comment\":0,\"extra\":{\"topic_2048\":{},\"query_type\":\"LongtermESQueryType\",\"titles_terms\":\"\u8fc8\u5165 \u5c0f\u5eb7 \u793e\u4f1a \u5efa\u8bbe \u751f\u6001 \u5bb6\u56ed\",\"has_video\":0,\"topic\":{},\"source\":\"\",\"img_count\":0,\"sstag\":\"\"},\"single_mode\":true,\"abstract\":\"\u6211\u5bb6\u73af\u7ed5\u56db\u5468\u90fd\u662f\u571f\u623f\u5b50\uff0c\u6bcf\u6b21\u4e0b\u96e8\u8def\u4e0a\u5c31\u4f1a\u5f62\u6210\u4e00\u6761\u5c0f\u6cb3\u5751\u5751\u6d3c\u6d3c\uff0c\u623f\u5b50\u4e0a\u7684\u6ce5\u571f\u90fd\u4f1a\u8131\u843d\u4e0b\u6765\u3002\u4f46\u662f\uff0c\u5982\u4eca\u519c\u6751\u7684\u7a7a\u5fc3\u5316\u73b0\u8c61\u65e5\u76ca\u4e25\u91cd\uff0c\u66f4\u662f\u51fa\u73b0\u4e86\u5927\u7247\u7684\u5e9f\u5f03\u5b85\u57fa\u5730\u548c\u8352\u5730\uff0c\u8fd9\u5176\u5b9e\u5c31\u6d6a\u8d39\u4e86\u5f88\u5927\u4e00\u90e8\u5206\u571f\u5730\u8d44\u6e90\u3002\",\"display_title\":\"\",\"media_avatar_url\":\"//p9.pstatp.com/large/403700008fea79fbdf8e\",\"datetime\":\"2017-10-30 18:07\",\"article_type\":0,\"more_mode\":false,\"create_time\":1509358049,\"has_m3u8_video\":0,\"keywords\":\"\u5b85\u57fa\u5730,\u5c0f\u5eb7\u793e\u4f1a,\u65b0\u519c\u6751,\u751f\u6001\u5bb6\u56ed,\u519c\u6751\",\"has_mp4_video\":0,\"favorite_count\":1,\"aggr_type\":0,\"comments_count\":1,\"article_sub_type\":0,\"bury_count\":0,\"title\":\"\u8fc8\u5165\u5c0f\u5eb7\u793e\u4f1a\uff0c\u5efa\u8bbe\u751f\u6001\u5bb6\u56ed\uff01\",\"show_play_effective_count\":0,\"has_video\":false,\"share_url\":\"http://toutiao.com/group/6482643446812115469/?iid=0&app=news_article\",\"id\":6482643446812115469,\"source\":\"\u4f18\u5f69\u7f8e\u5bb6\",\"comment_count\":1,\"article_url\":\"http://toutiao.com/group/6482643446812115469/\",\"image_url\":\"//p3.pstatp.com/list/190x124/43480000d9e268c9f513\",\"middle_mode\":true,\"large_mode\":false,\"item_source_url\":\"/group/6482643446812115469/\",\"media_url\":\"/c/user/73082379083/\",\"display_time\":1509358046,\"publish_time\":1509358046,\"go_detail_count\":175,\"image_list\":[],\"gallary_image_count\":0,\"item_seo_url\":\"/group/6482643446812115469/\",\"tag_id\":6482643446812115469,\"source_url\":\"/group/6482643446812115469/\",\"item_id\":\"6482643446812115469\",\"natant_level\":0,\"seo_url\":\"/group/6482643446812115469/\",\"display_url\":\"http://toutiao.com/group/6482643446812115469/\",\"url\":\"http://toutiao.com/group/6482643446812115469/\",\"level\":0,\"digg_count\":0,\"behot_time\":1509358046,\"image_detail\":[{\"url\":\"http://p3.pstatp.com/large/43480000d9e268c9f513\",\"width\":600,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/43480000d9e268c9f513\"},{\"url\":\"http://pb9.pstatp.com/large/43480000d9e268c9f513\"},{\"url\":\"http://pb1.pstatp.com/large/43480000d9e268c9f513\"}],\"uri\":\"large/43480000d9e268c9f513\",\"height\":450},{\"url\":\"http://p1.pstatp.com/large/43440003ac9a92703440\",\"width\":450,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/43440003ac9a92703440\"},{\"url\":\"http://pb3.pstatp.com/large/43440003ac9a92703440\"},{\"url\":\"http://pb9.pstatp.com/large/43440003ac9a92703440\"}],\"uri\":\"large/43440003ac9a92703440\",\"height\":300}],\"tag\":\"news_agriculture\",\"has_gallery\":false,\"has_image\":true,\"highlight\":{\"source\":[],\"abstract\":[],\"title\":[[2,4]]},\"group_id\":\"6482643446812115469\",\"middle_image\":\"http://p3.pstatp.com/list/43480000d9e268c9f513\"},{\"media_creator_id\":63709239852,\"media_name\":\"\u534e\u6cf0\u5b8f\u89c2\u674e\u8d85\u56e2\u961f\",\"repin_count\":58,\"ban_comment\":0,\"extra\":{\"topic_2048\":{\"2047\":0.0247,\"1225\":0.0355,\"155\":0.0302,\"1301\":0.0267,\"552\":0.0489,\"1875\":0.0368,\"1865\":0.0317,\"1379\":0.0337,\"1413\":0.0246,\"1751\":0.0624},\"query_type\":\"NormalAndQueryType\",\"titles_terms\":\"\u5168\u9762 \u5c0f\u5eb7 \u793e\u4f1a \u5439\u54cd \u96c6\u7ed3\u53f7 \u70b9\u8bc4 \u4e2d\u592e \u7ecf\u6d4e \u5de5\u4f5c \u4f1a\u8bae\",\"has_video\":0,\"topic\":{\"39\":0.03,\"141\":0.0573,\"195\":0.1175,\"43\":0.165,\"149\":0.0487,\"183\":0.0387,\"180\":0.0963,\"73\":0.0393,\"93\":0.0354,\"95\":0.0695},\"source\":null,\"img_count\":16,\"sstag\":\"news_finance\"},\"single_mode\":true,\"abstract\":\"\u672c\u6b21\u4e2d\u592e\u7ecf\u6d4e\u5de5\u4f5c\u4f1a\u8bae\u4e0e\u5f80\u5e74\u4e0d\u540c\uff0c2018\u5e74\u65f6\u95f4\u70b9\u975e\u5e38\u7279\u6b8a\u3002\u4e00\u662f\u8d2f\u5f7b\u515a\u7684\u5341\u4e5d\u5927\u7cbe\u795e\u7684\u5f00\u5c40\u4e4b\u5e74\uff0c\u4e8c\u662f\u6539\u9769\u5f00\u653e40\u5468\u5e74\uff0c\u4e09\u662f\u51b3\u80dc\u5168\u9762\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a\u3001\u5b9e\u65bd\u201c\u5341\u4e09\u4e94\u201d\u89c4\u3002\",\"display_title\":\"\",\"media_avatar_url\":\"//p8.pstatp.com/large/2c5f000a0a1ffe519a7b\",\"datetime\":\"2017-12-21 18:02\",\"article_type\":0,\"more_mode\":true,\"create_time\":1513850551,\"has_m3u8_video\":0,\"keywords\":\"\u571f\u58e4\u6c61\u67d3\u9632\u6cbb\u884c\u52a8\u8ba1\u5212,REITS,\u8d27\u5e01\u653f\u7b56,\u73af\u5883\u6cbb\u7406,\u6c34\u6c61\u67d3\u9632\u6cbb\u884c\u52a8\u8ba1\u5212,\u623f\u5730\u4ea7\u5e02\u573a,\u4e00\u5e26\u4e00\u8def,\u8d22\u653f\u653f\u7b56,\u91d1\u878d\u673a\u6784,\u534e\u6cf0\u8bc1\u5238,\u5c0f\u5eb7\u793e\u4f1a,\u4f9b\u7ed9\u4fa7,\u6295\u8d44\u673a\u4f1a,\u8d64\u5b57\u7387,\u4e2d\u592e\u7ecf\u6d4e\",\"has_mp4_video\":0,\"favorite_count\":58,\"aggr_type\":0,\"comments_count\":0,\"article_sub_type\":0,\"bury_count\":0,\"title\":\"\u5168\u9762\u5c0f\u5eb7\u793e\u4f1a\u5439\u54cd\u96c6\u7ed3\u53f7\u2014\u2014\u70b9\u8bc4\u4e2d\u592e\u7ecf\u6d4e\u5de5\u4f5c\u4f1a\u8bae\",\"show_play_effective_count\":0,\"has_video\":false,\"share_url\":\"http://toutiao.com/group/6501938501330665997/?iid=0&app=news_article\",\"id\":6501938501330665997,\"source\":\"\u534e\u6cf0\u5b8f\u89c2\u674e\u8d85\u56e2\u961f\",\"comment_count\":0,\"article_url\":\"http://toutiao.com/group/6501938501330665997/\",\"image_url\":\"//p3.pstatp.com/list/190x124/5098000246e2f943e30f\",\"middle_mode\":false,\"large_mode\":false,\"item_source_url\":\"/group/6501938501330665997/\",\"media_url\":\"/c/user/63709239852/\",\"display_time\":1513850526,\"publish_time\":1513850526,\"go_detail_count\":1228,\"image_list\":[{\"url\":\"//p3.pstatp.com/list/190x124/5098000246e2f943e30f\"},{\"url\":\"//p1.pstatp.com/list/190x124/509b0001b56e057b3cd5\"},{\"url\":\"//p1.pstatp.com/list/190x124/509a000210ffcdeb307e\"}],\"gallary_image_count\":0,\"item_seo_url\":\"/group/6501938501330665997/\",\"tag_id\":6501938501330665997,\"source_url\":\"/group/6501938501330665997/\",\"item_id\":\"6501938501330665997\",\"natant_level\":0,\"seo_url\":\"/group/6501938501330665997/\",\"display_url\":\"http://toutiao.com/group/6501938501330665997/\",\"url\":\"http://toutiao.com/group/6501938501330665997/\",\"level\":0,\"digg_count\":0,\"behot_time\":1513850526,\"image_detail\":[{\"url\":\"http://p3.pstatp.com/large/5098000246e2f943e30f\",\"width\":1149,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/5098000246e2f943e30f\"},{\"url\":\"http://pb9.pstatp.com/large/5098000246e2f943e30f\"},{\"url\":\"http://pb1.pstatp.com/large/5098000246e2f943e30f\"}],\"uri\":\"large/5098000246e2f943e30f\",\"height\":612},{\"url\":\"http://p1.pstatp.com/large/509b0001b56e057b3cd5\",\"width\":1133,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/509b0001b56e057b3cd5\"},{\"url\":\"http://pb3.pstatp.com/large/509b0001b56e057b3cd5\"},{\"url\":\"http://pb9.pstatp.com/large/509b0001b56e057b3cd5\"}],\"uri\":\"large/509b0001b56e057b3cd5\",\"height\":722},{\"url\":\"http://p3.pstatp.com/large/5096000594878fcf90c2\",\"width\":1143,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/5096000594878fcf90c2\"},{\"url\":\"http://pb9.pstatp.com/large/5096000594878fcf90c2\"},{\"url\":\"http://pb1.pstatp.com/large/5096000594878fcf90c2\"}],\"uri\":\"large/5096000594878fcf90c2\",\"height\":638},{\"url\":\"http://p3.pstatp.com/large/509b0001b56fa19094a6\",\"width\":1132,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/509b0001b56fa19094a6\"},{\"url\":\"http://pb9.pstatp.com/large/509b0001b56fa19094a6\"},{\"url\":\"http://pb1.pstatp.com/large/509b0001b56fa19094a6\"}],\"uri\":\"large/509b0001b56fa19094a6\",\"height\":191},{\"url\":\"http://p1.pstatp.com/large/509a000210ffcdeb307e\",\"width\":1131,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/509a000210ffcdeb307e\"},{\"url\":\"http://pb3.pstatp.com/large/509a000210ffcdeb307e\"},{\"url\":\"http://pb9.pstatp.com/large/509a000210ffcdeb307e\"}],\"uri\":\"large/509a000210ffcdeb307e\",\"height\":707},{\"url\":\"http://p3.pstatp.com/large/5097000524ebdf0e2139\",\"width\":1129,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/5097000524ebdf0e2139\"},{\"url\":\"http://pb9.pstatp.com/large/5097000524ebdf0e2139\"},{\"url\":\"http://pb1.pstatp.com/large/5097000524ebdf0e2139\"}],\"uri\":\"large/5097000524ebdf0e2139\",\"height\":744},{\"url\":\"http://p3.pstatp.com/large/5097000524ee35d74bcc\",\"width\":1132,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/5097000524ee35d74bcc\"},{\"url\":\"http://pb9.pstatp.com/large/5097000524ee35d74bcc\"},{\"url\":\"http://pb1.pstatp.com/large/5097000524ee35d74bcc\"}],\"uri\":\"large/5097000524ee35d74bcc\",\"height\":685},{\"url\":\"http://p3.pstatp.com/large/509b0001b574cf46f221\",\"width\":1515,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/509b0001b574cf46f221\"},{\"url\":\"http://pb9.pstatp.com/large/509b0001b574cf46f221\"},{\"url\":\"http://pb1.pstatp.com/large/509b0001b574cf46f221\"}],\"uri\":\"large/509b0001b574cf46f221\",\"height\":525},{\"url\":\"http://p1.pstatp.com/large/509c0001bb3ef80cef44\",\"width\":1515,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/509c0001bb3ef80cef44\"},{\"url\":\"http://pb3.pstatp.com/large/509c0001bb3ef80cef44\"},{\"url\":\"http://pb9.pstatp.com/large/509c0001bb3ef80cef44\"}],\"uri\":\"large/509c0001bb3ef80cef44\",\"height\":566},{\"url\":\"http://p1.pstatp.com/large/5099000230ec5012f57e\",\"width\":1140,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/5099000230ec5012f57e\"},{\"url\":\"http://pb3.pstatp.com/large/5099000230ec5012f57e\"},{\"url\":\"http://pb9.pstatp.com/large/5099000230ec5012f57e\"}],\"uri\":\"large/5099000230ec5012f57e\",\"height\":610},{\"url\":\"http://p9.pstatp.com/large/509a00021101c8683f04\",\"width\":1518,\"url_list\":[{\"url\":\"http://p9.pstatp.com/large/509a00021101c8683f04\"},{\"url\":\"http://pb1.pstatp.com/large/509a00021101c8683f04\"},{\"url\":\"http://pb3.pstatp.com/large/509a00021101c8683f04\"}],\"uri\":\"large/509a00021101c8683f04\",\"height\":571},{\"url\":\"http://p3.pstatp.com/large/509a00021102a13659be\",\"width\":1516,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/509a00021102a13659be\"},{\"url\":\"http://pb9.pstatp.com/large/509a00021102a13659be\"},{\"url\":\"http://pb1.pstatp.com/large/509a00021102a13659be\"}],\"uri\":\"large/509a00021102a13659be\",\"height\":528},{\"url\":\"http://p3.pstatp.com/large/509a00021104a1d4a66c\",\"width\":1133,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/509a00021104a1d4a66c\"},{\"url\":\"http://pb9.pstatp.com/large/509a00021104a1d4a66c\"},{\"url\":\"http://pb1.pstatp.com/large/509a00021104a1d4a66c\"}],\"uri\":\"large/509a00021104a1d4a66c\",\"height\":699},{\"url\":\"http://p1.pstatp.com/large/5096000594881b93b76a\",\"width\":1135,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/5096000594881b93b76a\"},{\"url\":\"http://pb3.pstatp.com/large/5096000594881b93b76a\"},{\"url\":\"http://pb9.pstatp.com/large/5096000594881b93b76a\"}],\"uri\":\"large/5096000594881b93b76a\",\"height\":613},{\"url\":\"http://p3.pstatp.com/large/5098000246e7a44b90ca\",\"width\":1144,\"url_list\":[{\"url\":\"http://p3.pstatp.com/large/5098000246e7a44b90ca\"},{\"url\":\"http://pb9.pstatp.com/large/5098000246e7a44b90ca\"},{\"url\":\"http://pb1.pstatp.com/large/5098000246e7a44b90ca\"}],\"uri\":\"large/5098000246e7a44b90ca\",\"height\":450},{\"url\":\"http://p1.pstatp.com/large/509c0001be839ef2e38b\",\"width\":640,\"url_list\":[{\"url\":\"http://p1.pstatp.com/large/509c0001be839ef2e38b\"},{\"url\":\"http://pb3.pstatp.com/large/509c0001be839ef2e38b\"},{\"url\":\"http://pb9.pstatp.com/large/509c0001be839ef2e38b\"}],\"uri\":\"large/509c0001be839ef2e38b\",\"height\":353}],\"tag\":\"news_finance\",\"has_gallery\":false,\"has_image\":true,\"highlight\":{\"source\":[],\"abstract\":[[65,4]],\"title\":[[2,4]]},\"group_id\":\"6501938501330665997\",\"middle_image\":{\"url\":\"http://p3.pstatp.com/list/5098000246e2f943e30f\",\"width\":1149,\"url_list\":[{\"url\":\"http://p3.pstatp.com/list/5098000246e2f943e30f\"},{\"url\":\"http://pb9.pstatp.com/list/5098000246e2f943e30f\"},{\"url\":\"http://pb1.pstatp.com/list/5098000246e2f943e30f\"}],\"uri\":\"list/5098000246e2f943e30f\",\"height\":612}},{\"cell_type\":20,\"queries\":[{\"url\":\"/search/keyword=%E7%A4%BE%E4%BC%9A%E5%BB%BA%E8%AE%BE%E7%9F%A5%E8%AF%86\",\"text\":\"\u793e\u4f1a\u5efa\u8bbe\u77e5\u8bc6\"},{\"url\":\"/search/keyword=%E4%BA%AC%E5%B7%B4\",\"text\":\"\u4eac\u5df4\"},{\"url\":\"/search/keyword=%E5%85%A8%E9%9D%A2%E5%B0%8F%E5%BA%B7%E7%A4%BE%E4%BC%9A\",\"text\":\"\u5168\u9762\u5c0f\u5eb7\u793e\u4f1a\"},{\"url\":\"/search/keyword=%E5%B0%8F%E5%BA%B7%E7%9A%84%E6%A0%87%E5%87%86\",\"text\":\"\u5c0f\u5eb7\u7684\u6807\u51c6\"},{\"url\":\"/search/keyword=%E5%B0%8F%E5%BA%B7%E6%A0%87%E5%87%86\",\"text\":\"\u5c0f\u5eb7\u6807\u51c6\"},{\"url\":\"/search/keyword=%E5%85%A8%E9%9D%A2%E5%86%B3%E8%83%9C%E5%B0%8F%E5%BA%B7%E7%A4%BE%E4%BC%9A\",\"text\":\"\u5168\u9762\u51b3\u80dc\u5c0f\u5eb7\u793e\u4f1a\"},{\"url\":\"/search/keyword=%E5%85%A8%E9%9D%A2%E5%BB%BA%E6%88%90%E5%B0%8F%E5%BA%B7%E7%A4%BE%E4%BC%9A%E7%9A%84%E7%9B%AE%E6%A0%87\",\"text\":\"\u5168\u9762\u5efa\u6210\u5c0f\u5eb7\u793e\u4f1a\u7684\u76ee\u6807\"},{\"url\":\"/search/keyword=%E4%B8%AD%E5%9B%BD%E6%9C%80%E6%96%B0%E5%B0%8F%E5%BA%B7%E6%A0%87%E5%87%86\",\"text\":\"\u4e2d\u56fd\u6700\u65b0\u5c0f\u5eb7\u6807\u51c6\"}]}],\"message\":\"success\",\"action_label_pgc\":\"click_search\"}";
            String s = "{\"doubleList\":[1,2.0000,3,4,5,6],\"intList\":[1,2,3,4,5,6],\"multyList\":[[[\"d\",\"e\",\"f\"],[\"d1\",\"e1\",\"f1\"]],[[\"d\",\"e\",\"f\"],[\"d2\",\"e2\",\"f2\"]]],\"code\":0,\"data\":{\"count\":\"6\",\"list\":[{\"pid\":\"236\",\"author\":\"M12345678977\",\"author_id\":\"31\",\"subject\":\"\u4e5d\u5934\",\"dateline\":\"1459232596\",\"message\":\"\u554a\u805a\u805a\u51e0\u53f7\u6765\",\"useip\":\"2104960333\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"0\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 14:23:16\",\"user_info\":{\"id\":\"31\",\"username\":\"M12345678977\",\"nickname\":\"123566\",\"head\":\"948\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[\"948\"]},{\"pid\":\"237\",\"author\":\"M18267152148\",\"author_id\":\"27\",\"subject\":\"\",\"dateline\":\"1459234314\",\"message\":\"thugs \",\"useip\":\"2062279264\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"1\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 14:51:54\",\"user_info\":{\"id\":\"27\",\"username\":\"M18267152148\",\"nickname\":\"123456\",\"head\":\"865\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[\"865\"]},{\"pid\":\"238\",\"author\":\"M18267152148\",\"author_id\":\"27\",\"subject\":\"\",\"dateline\":\"1459234741\",\"message\":\"hfs schizophrenia [:f00}[:f01}[:f02}\",\"useip\":\"2062279264\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"2\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 14:59:01\",\"user_info\":{\"id\":\"27\",\"username\":\"M18267152148\",\"nickname\":\"123456\",\"head\":\"865\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[]},{\"pid\":\"239\",\"author\":\"M18267152148\",\"author_id\":\"27\",\"subject\":\"\",\"dateline\":\"1459234984\",\"message\":\"[:f020}[:f021}[:f022}[:f010}[:f009}\",\"useip\":\"2062279264\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"3\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 15:03:04\",\"user_info\":{\"id\":\"27\",\"username\":\"M18267152148\",\"nickname\":\"123456\",\"head\":\"865\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[]},{\"pid\":\"240\",\"author\":\"M18267152148\",\"author_id\":\"27\",\"subject\":\"\",\"dateline\":\"1459235016\",\"message\":\"Sfyhgff\",\"useip\":\"2062279264\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"4\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 15:03:36\",\"user_info\":{\"id\":\"27\",\"username\":\"M18267152148\",\"nickname\":\"123456\",\"head\":\"865\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[]},{\"pid\":\"241\",\"author\":\"M12345678977\",\"author_id\":\"31\",\"subject\":\"\",\"dateline\":\"1459238898\",\"message\":\"\u6cd5\u56fd\u548c\u9ec4\u91d1\u5b63\u8282\",\"useip\":\"2104960333\",\"invisible\":\"0\",\"status\":\"1\",\"comment\":\"0\",\"position\":\"5\",\"tid\":\"121\",\"fid\":\"1\",\"dateline_show\":\"2016-03-29 16:08:18\",\"user_info\":{\"id\":\"31\",\"username\":\"M12345678977\",\"nickname\":\"123566\",\"head\":\"948\",\"group\":{\"adminid\":\"2\",\"alloweditpost\":\"1\",\"allowstickthread\":\"1\",\"allowdelpost\":\"1\",\"allowbanuser\":\"1\",\"allowdigestthread\":\"1\",\"allowpost\":\"1\",\"name\":\"\u7248\u4e3b\",\"fid\":\"1\"}},\"img\":[]}]},\"notify_id\":\"1459300528\"}";
            //   System.out.println(toujiaoJson);
            String result = new Json2Bean(toujiaoJson, "RootBean", null, new MyNameGenerator(), new MyJsonParser(), new MyBeanGenerator("com.test2")).execute();


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
            // https://dreampuf.github.io/GraphvizOnline/#
            dotStringArr.add("digraph {");
            dotStringArr.add("#    https://dreampuf.github.io/GraphvizOnline/# ");
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
            for (String item : dotStringArr) {
                System.out.println(item);
            }

        } catch (Exception e) {

        }

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


        boolean isList;       //  是否是集合   List<>
        boolean isBaseList;   // 是否是基础的数据类型集合  List<String>   List<Double>
        int baseListCount;   // 该属性 包含 List的个数  嵌套数
        boolean isClassList;   // 是否是对象的数据类型集合  List<A>   List<B>
        int classListCount;   //该属性 包含 List的个数  嵌套数

        @Override
        public String toString() {
            return "【 ownnerClassName=" + ownnerClassName + "  properityName=" + properityName + " TypeName=" + TypeName + " refClassName=" + refClassName + "  isBaseType=" + isBaseType + "  isClassType=" + isClassType + "  isList=" + isList + " baseListCount=" + baseListCount + " classListCount=" + classListCount + "】";
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
            return "\""+properityName+"\\n"+"【"+TypeName+"】"+"\"";
        }

        //  ArrayList<String>    =List-String   List【】  String【】 baseListCount == 1
        //  ArrayList<String>   baseListCount == 1     ArrayList<List<String>>  baseListCount == 2
        ArrayList<String> getLableArr() {
            ArrayList<String> stringArr = null;
            if (isList && isBaseType == true && baseListCount > 0) {



                stringArr = new ArrayList<String>();
                for (int i = 0; i < baseListCount; i++) {
                    // stringArr.add("List"+"<【" + TypeName +"】>");
                    stringArr.add("\""+getBaseListShowString(properityName,TypeName,i)+"\"");
                }
                // baseTypeInListName String
                stringArr.add("\""+properityName+"_item"+"\\n"+baseTypeInListName+"\"");

            } else if (isList && isClassType == true && classListCount > 0) {
                // // 获取 数组的  data 【List<A>】    properityName=data     TypeName=List<A>
                // List<【List<A>】> data    List<>
                stringArr = new ArrayList<String>();
                for (int i = 0; i < classListCount; i++) {
                    stringArr.add("\""+getListShowString(properityName,TypeName,i)+"\"");
                }
                stringArr.add(classTypeInListName+"\\n"+properityName); // A
            }
            return stringArr;
        }


        String getBaseListShowString(String properityName , String strListTypeName , int index){
            int count = StrUtil.count(strListTypeName,"List");
            if( count == 1 || index == 0){
                return properityName+"\\n"+getPretyTypeName(TypeName,baseTypeInListName,0);
            } else{
                return getPretyTypeName(TypeName,baseTypeInListName,index);
            }
            //  properityName=data     TypeName=List<A>     classTypeInListName=A
//List<List<List<String>>>   data    【 count 是总的List的个数】   【index =0 】 是显示的个数 显示的个数是 count - index
// 【count 3 , index 0】  data \n List<【List<List<A>>】>
// 【count 3 , index 1】  List<【<List<A>】>
// 【count 3 , index 2】  List<【A】>


        }



        String getListShowString(String properityName , String strListTypeName , int index){
            int count = StrUtil.count(strListTypeName,"List");
            if( count == 1 || index == 0){
                return properityName+"\\n"+getPretyTypeName(TypeName,classTypeInListName,0);
            } else{
                return getPretyTypeName(TypeName,classTypeInListName,index);
            }
            //  properityName=data     TypeName=List<A>     classTypeInListName=A
//List<List<List<A>>>   data    【 count 是总的List的个数】   【index =0 】 是显示的个数 显示的个数是 count - index
// 【count 3 , index 0】  data \n List<【List<List<A>>】>
// 【count 3 , index 1】  List<【<List<A>】>
// 【count 3 , index 2】  List<【A】>


        }

        String getPretyTypeName( String strListTypeName ,String classTypeInListName , int index){
            int count = StrUtil.count(strListTypeName,"List");
            if(index > count){
                System.out.println("getPretyTypeName 索引错误  index="+index+"   count ="+ count);
            }
// 【count 3 , index 0  keepListCount=3 】  data \n List<【List<List<A>>】>
// 【count 3 , index 1  keepListCount=2 】  List<【<List<A>】>
// 【count 3 , index 2 keepListCount=1 】  List<【A】>
            int keepListCount = count - index;
            String listString = buildListString(classTypeInListName, keepListCount) ; //  List<A>   List<List<List<A>>>
            listString =  listString.replaceAll(classTypeInListName,"【"+classTypeInListName+"】"); //  List<【A】>   List<List<List<【A】>>>

            return listString;
        }

        String buildListString(String classTypeInListName , int listCount){
            String curStr ="";
            String curStrKeep ="";
            for(int i=0; i < listCount;i++){
                if( i == 0){
                    curStr = "List<"+classTypeInListName+">" + curStrKeep;
                    curStrKeep = curStr;
                } else{
                    curStr = "List<" + curStrKeep+">";
                    curStrKeep = curStr;
                }
            }
// 【index 1  List<A>】
// 【index 2  List<List<A>>】
            // 【index 2  List<List<List<A>>>】
            return curStrKeep;
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
            } else if (isBaseType == false && isList == true) {
                return "shape=doublecircle";
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
            } else if (isList && isClassType == true && classListCount > 0) {  // 对象类型颜色
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
            } else if (isBaseType == false && isList == true) {
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
            } else if (isList && isClassType == true && classListCount > 0) {
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

            } else if (isClassType == true && isList == true && isClassList == true) {
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

                    item = nodeArr.get(i).trim() + "[ label=" + labelArr.get(i).trim() + "  " + shapeArr.get(i).trim() + "  " + styleArr.get(i).trim() + " " + colorArr.get(i) + "] \n ";
                    sb.append(item);
                }
                return sb.toString();

            }

            return "";
        }


        String getWeight() {
            if (TypeName.equals("Class")) {
                return "[weight=10]";
            }
            if(isClassType == true && isList == true && isClassList == true){

                return "[weight=8]";
            }

            if(isClassType == true && isList == false && isClassList == false){

                return "[weight=6]";
            }

            if(isBaseType == true && isList == true ){

                return "[weight=4]";
            }


            return "";
        }

        String makeNodeRelationString() {  //

            if (isBaseType == true && isList == false) {
                // 单独的 基础类型变量 关系  	RootBean -> RootBean_code
                if (ownnerClassName != null && !ownnerClassName.trim().isEmpty()) {

                    return ownnerClassName + " -> " + getNodeName()+" "+ getWeight();
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
                        sb.append(ownnerClassName + " -> " + nodeName.get(0) + " "+ getWeight() +"\n");
                        for (int i = 1; i < length; i++) {
                            item1 = nodeName.get(i - 1);
                            item2 = nodeName.get(i);
                            sb.append(item1 + " -> " + item2 +  " "+ getWeight() +"\n");
                        }
                    }

                    return sb.toString();
                }
            } else if (isClassType == true && isList == false && isClassList == false && refClassName != null) {
//   对于单独的 Class  它的 关系 已经 通过 属性 来设置了   所以 这里不用设置关系
                return refClassName + " -> " + properityName +" "+getWeight();

            } else if (isClassType == true && isList == true && isClassList == true) {
//  对象数组 列表       RootBean.java 的   List<A> data;
//  RootBean -> RootBean_Data  [weight=10]
//  RootBean_Data  -> RootBean_Data_Count

                ArrayList<String> nodeName = getNodeNameArr();
                int length = getNodeNameArr().size();
                String item1 = "";
                String item2 = "";
                StringBuilder sb = new StringBuilder();
                if (length > 0) {
                    sb.append(ownnerClassName + " -> " + nodeName.get(0)  + " "+ getWeight() +"\n");
                    for (int i = 1; i < length; i++) {
                        item1 = nodeName.get(i - 1);
                        item2 = nodeName.get(i);
                        sb.append(item1 + " -> " + item2 +  " "+ getWeight() +"\n");
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


    static class Json2Bean {
        private String json;
        private JsonParse jsonParse;
        private String name;
        private NameGenerator nameGeneration;
        private BeanGenerator generationBean;
        private ArrayList<ProperityItem> arrList;
        private String fatherName;

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
            arrList.add(new ProperityItem(null, this.name, "Class", this.fatherName));

        }

        public String execute() throws IOException {
            if (this.name != null) {
                System.out.println("开始解析Bean类:" + this.name);
            }
            if (json.startsWith("{")) {
                parseMap();
                HashMap curMap = new HashMap();
                curMap.put(this.name, arrList);
                arrMapList.add(curMap);
                return null;
            } else if (json.startsWith("[")) {
                String clz = parseArray();
                if (name == null) {
                    return clz;
                }

                generationBean.writeList(clz);
                HashMap curMap = new HashMap();
                curMap.put(this.name, arrList);
                arrMapList.add(curMap);
                return clz;
            }
            return null;
        }

        private void parseMap() throws IOException {
            Map<String, Object> map = jsonParse.toMap(json);

            Iterator<Map.Entry<String, Object>> itr = map.entrySet().iterator();

            while (itr.hasNext()) {
                Map.Entry<String, Object> entry = itr.next();
                String k = entry.getKey();
                Object v = entry.getValue();
                if (v == null || v.toString().equals("[]")) {
                    itr.remove();
                    continue;
                }
                if (v instanceof Integer) {
                    entry.setValue("Integer");
                } else if (v instanceof BigDecimal) {
                    entry.setValue("Double");
                } else if (v instanceof String) {
                    entry.setValue("String");
                } else {
                    String childJson = v.toString();
                    if (childJson.startsWith("{")) {

                        String childName = nameGeneration.formatName(k);
                        entry.setValue(childName);
                        new Json2Bean(childJson, childName, this.name, nameGeneration, jsonParse, generationBean).execute();
                    } else if (childJson.startsWith("[")) {

                        String childName = new Json2Bean(childJson, null, this.name, nameGeneration, jsonParse, generationBean).execute();

                        entry.setValue(childName);
                    } else {
                        entry.setValue("String");
                    }

                }
                //     // ProperityItem(String ownnerClassName, String properityName, String TypeName, String refClassName)
                arrList.add(new ProperityItem(this.name, k, entry.getValue() + "", this.fatherName));
                System.out.println(" key = " + k + " value = " + entry.getValue());

            }


            generationBean.writeBean(name, map);

        }

        private String parseArray() throws IOException {
            List<Object> list = jsonParse.toArray(json);
            if (list == null || list.size() == 0) {
                return null;
            }
            String childName = "";
            Object v = list.get(0);
            if (v instanceof Integer) {
                for (int i = 1; i < list.size(); i++) {
                    v = list.get(i);
                    if (v instanceof BigDecimal) {
                        System.out.println(" childName =  List<Double>");
                        return "List<Double>";
                    }
                }
                System.out.println(" childName =  List<Integer>");
                return "List<Integer>";
            } else if (v instanceof BigDecimal) {
                System.out.println(" childName =  List<Double>");
                return "List<Double>";

            } else if (v instanceof String) {
                System.out.println(" childName =  List<String>");
                return "List<String>";
            } else {
                String childJson = v.toString();
                if (childJson.startsWith("{")) {
                    childName = nameGeneration.nextName();

                    new Json2Bean(childJson, childName, this.name, nameGeneration, jsonParse, generationBean).execute();
                    System.out.println(" childName = " + childName);
                    //  arrList.add("List<" + childName + ">");
                    return "List<" + childName + ">";
                } else if (childJson.startsWith("[")) {
                    childName = new Json2Bean(childJson, null, this.name, nameGeneration, jsonParse, generationBean).execute();
                    System.out.println(" childName = " + childName);
                    //     arrList.add("List<" + childName + ">");
                    return "List<" + childName + ">";

                } else {
                    System.out.println(" childName = " + childName);
                    //    arrList.add("List<String>" + childName);
                    return "List<String>";
                }

            }


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

        @Override
        public void writeBean(String className, Map<String, Object> map) throws IOException {
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
                bw.write(entry.getValue().toString());
                bw.write(" ");
                bw.write(entry.getKey());
                bw.write(";\n");
            }
            bw.write("\n");

            set = map.entrySet();

            for (Map.Entry<String, Object> entry : set) {

                bw.write("    public ");
                bw.write(entry.getValue().toString());
                bw.write(" get");
                bw.write(capitalUpperCase(entry.getKey()));
                bw.write("(){\n");
                bw.write("        ");
                bw.write("return ");
                bw.write(entry.getKey());

                bw.write(";\n    }\n\n");

                //////////////////////////

                bw.write("    public void ");
                bw.write("set");
                bw.write(capitalUpperCase(entry.getKey()));
                bw.write("(");
                bw.write(entry.getValue().toString());
                bw.write(" ");
                bw.write(entry.getKey());
                bw.write("){\n");
                bw.write("        ");
                bw.write("this.");
                bw.write(entry.getKey());
                bw.write("=");
                bw.write(entry.getKey());
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
                , "AA", "BB", "CC"};
        int posiiotn;

        @Override
        public String nextName() {

            return names[posiiotn++];
        }

        @Override
        public String formatName(String name) {
            char[] chs = name.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chs[0]);
            for (int i = 1; i < chs.length; i++) {
                if (chs[i - 1] == '_') {
                    stringBuilder.append((char) (chs[i] - 32));
                } else
                    stringBuilder.append(chs[i]);
            }
            String s = stringBuilder.toString().replace("_", "");
            chs = s.toCharArray();
            if (chs[0] >= 'a' && chs[0] <= 'z') {
                chs[0] = (char) (chs[0] - 32);
            }

            return new String(chs);
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