import java.util.ArrayList;

public class C0_test {

    public static void main(String[] args) {
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
