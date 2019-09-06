import java.util.ArrayList;
import java.util.Comparator;

public class C0_test {


    public static void main(String[] args) {
ArrayList<String>  arrStr = new ArrayList<String>();

        arrStr.add("0017");
        arrStr.add("0036");
        arrStr.add("0079");
        arrStr.add("0021");
        arrStr.add("0052");
        arrStr.add("0010");
        arrStr.add("0062");
        arrStr.add("0037");
        arrStr.add("0020");
        arrStr.add("0044");
        arrStr.add("0056");
        arrStr.add("0058");
        arrStr.add("0073");
        arrStr.add("0088");
        arrStr.add("0090");
        arrStr.add("0008");
        arrStr.add("0005");
        arrStr.add("0096");
        arrStr.add("0019");
        arrStr.add("0039");
        arrStr.add("0055");
        arrStr.add("0098");
        arrStr.add("0007");
        arrStr.add("0013");
        arrStr.add("0051");
        arrStr.add("0030");
        arrStr.add("0045");
        arrStr.add("0032");
        arrStr.add("0072");
        arrStr.add("0069");
        arrStr.add("0077");
        arrStr.add("0059");
        arrStr.add("0042");
        arrStr.add("0057");
        arrStr.add("0009");
        arrStr.add("0018");
        arrStr.add("0099");
        arrStr.add("0034");
        arrStr.add("0061");
        arrStr.add("0060");
        arrStr.add("0071");
        arrStr.add("0068");
        arrStr.add("0076");
        arrStr.add("0065");
        arrStr.add("0040");
        arrStr.add("0001");
        arrStr.add("0081");
        arrStr.add("0087");
        arrStr.add("0028");
        arrStr.add("0070");
        arrStr.add("0025");
        arrStr.add("0078");
        arrStr.add("0029");
        arrStr.add("0046");
        arrStr.add("0024");
        arrStr.add("0035");
        arrStr.add("0064");
        arrStr.add("0082");
        arrStr.add("0011");
        arrStr.add("0038");
        arrStr.add("0053");
        arrStr.add("0085");
        arrStr.add("0022");
        arrStr.add("0084");
        arrStr.add("0089");
        arrStr.add("0094");
        arrStr.add("0002");
        arrStr.add("0043");
        arrStr.add("0006");
        arrStr.add("0048");
        arrStr.add("0027");
        arrStr.add("0003");
        arrStr.add("0080");
        arrStr.add("0049");
        arrStr.add("0097");
        arrStr.add("0026");
        arrStr.add("0050");
        arrStr.add("0023");
        arrStr.add("0067");
        arrStr.add("0092");
        arrStr.add("0012");
        arrStr.add("0033");
        arrStr.add("0047");
        arrStr.add("0063");
        arrStr.add("0091");
        arrStr.add("0054");
        arrStr.add("0093");

            arrStr.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                            return o1.compareTo(o2);
                    }
            });

            for (int i = 0; i <arrStr.size() ; i++) {
                    System.out.println(arrStr.get(i));
            }



    }

    public static void main1(String[] args) {
String template = "        // htmlcode2x2\n" +
        "        String htmlcode2x2 = readStringFromFile(zukgitxx);\n" +
        "        if (htmlcode2x2 != null && htmlcode2x2.contains(\"zukgitPlaceHolderindex\")) {\n" +
        "            htmlcode2x2 = htmlcode2x2.replaceAll(\"zukgitPlaceHolderindex\", length + \"\");\n" +
        "        }\n" +
        "        File htmlcode2x2_File = new File(addPath + File.separator + zukgitxx.getName());\n" +
        "        if (!htmlcode2x2_File.exists()) {\n" +
        "            try {\n" +
        "                htmlcode2x2_File.createNewFile();\n" +
        "            } catch (IOException e) {\n" +
        "                e.printStackTrace();\n" +
        "            }\n" +
        "        }\n" +
        "        writeContentToFile(htmlcode2x2_File, htmlcode2x2);";


ArrayList<String> arr = new ArrayList();
        arr.add("htmlFile_gif_1x1_flow_left");
        arr.add("htmlFile_gif_1x1_flow_right");
        arr.add("htmlFile_gif_2x2_flow_left");
        arr.add("htmlFile_gif_2x2_flow_right");
        arr.add("htmlFile_gif_3x3_flow_left");
        arr.add("htmlFile_gif_3x3_flow_right");
        arr.add("htmlFile_gif_4x2_flow_left");
        arr.add("htmlFile_gif_4x2_flow_right");
        arr.add("htmlFile_gif_4x3_flow_left");
        arr.add("htmlFile_gif_4x3_flow_right");
        arr.add("htmlFile_gif_4x4_flow_left");
        arr.add("htmlFile_gif_4x4_flow_right");
        arr.add("htmlFile_gif_5x3_flow_left");
        arr.add("htmlFile_gif_5x3_flow_right");
        arr.add("htmlFile_gif_5x4_flow_left");
        arr.add("htmlFile_gif_5x4_flow_right");
        arr.add("htmlFile_jpg_1x1_flow_left");
        arr.add("htmlFile_jpg_1x1_flow_right");
        arr.add("htmlFile_jpg_2x2_flow_left");
        arr.add("htmlFile_jpg_2x2_flow_right");
        arr.add("htmlFile_jpg_3x3_flow_left");
        arr.add("htmlFile_jpg_3x3_flow_right");
        arr.add("htmlFile_jpg_4x2_flow_left");
        arr.add("htmlFile_jpg_4x2_flow_right");
        arr.add("htmlFile_jpg_4x3_flow_left");
        arr.add("htmlFile_jpg_4x3_flow_right");
        arr.add("htmlFile_jpg_4x4_flow_left");
        arr.add("htmlFile_jpg_4x4_flow_right");
        arr.add("htmlFile_jpg_5x3_flow_left");
        arr.add("htmlFile_jpg_5x3_flow_right");
        arr.add("htmlFile_jpg_5x4_flow_left");
        arr.add("htmlFile_jpg_5x4_flow_right");

String code = "";

        for (int i = 0; i < arr.size(); i++) {
            String fileName = arr.get(i);
            String htmlcode2x2 = "z"+fileName;
            System.out.println("========"+fileName+"==========");
            code = template.replace("zukgitxx",fileName);
            code = code.replace("htmlcode2x2" , htmlcode2x2 );
            System.out.println(code);


            System.out.println("===============================");
        }






    }
}
