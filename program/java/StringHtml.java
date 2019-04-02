import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

public class StringHtml {
    public static final String A_File = "html.txt";   // 输入文件1.txt
    public static final String AB_File = "htmlout.txt";   // 输出文件1.txt
    
	public static void main(String[] args) {
        File txtFile = new File(System.getProperty("user.dir") + File.separator+"/src/" + A_File);
        File txtFileOut = new File(System.getProperty("user.dir") + File.separator+"/src/"  + AB_File);
        ArrayList<String> stringList = new ArrayList();
        if(!txtFileOut.exists()){
        	try {
				txtFileOut.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        FileReader txtReader;

        FileWriter txtWriter ;
		try {
			txtReader = new FileReader(txtFile);
			txtWriter = new FileWriter(txtFileOut);
			  BufferedReader txtBR_A = new BufferedReader(txtReader);
			  BufferedWriter txtBW_AB = new BufferedWriter(txtWriter);
			  String lineContentFirst_A = "";   
			  String lineContentFirst_B = "";   
			  StringBuilder StringbuildA = new StringBuilder();
			  StringBuilder StringbuildB = new StringBuilder();
			  int num = 1;
		        // 一次性读出所有的字符串String   然后再重新编排？
	            while (lineContentFirst_A != null && lineContentFirst_B != null) {
	 
	            	lineContentFirst_A = txtBR_A.readLine();   // 从1.txt中读取一行字符串出来
	                if (lineContentFirst_A == null || lineContentFirst_B == null ) { // 如果读取到的字符串为null 说明读取到了末尾了
	                   // System.out.println("1.txt read to end!");
	                    break;
	                }
	                StringbuildA.append(lineContentFirst_A);
	                String  newLineStr = num+"  :"+lineContentFirst_A +"   "+ lineContentFirst_B;
	                num++;
	            }
	            

	           // System.out.println(StringbuildA.toString());
	         
	            String[] strArr = StringbuildA.toString().split("RepoList-itemName");
	            //System.out.println(strArr.length);
	            for (int i = 2; i < strArr.length; i++) {
	            //	System.out.println("index = "+ i +" ## value = "+ strArr[i] );
	            	int index = strArr[i].indexOf("<");
	            	String subString = strArr[i].substring(2, index);
	            //	System.out.println("index = "+ i +" ## subString = "+ subString );
	            	stringList.add(subString);
				}
	            int itemIndex = 1;
	            for(int j = 0; j < stringList.size(); j++){
		         // for(int j = 500; j < stringList.size(); j++){
		            	
		     
	            	String stringItem = stringList.get(j);
	            	String value1 = "## "+ stringItem;
	            	
	            	// 1
	            	System.out.println(value1);
	            	txtBW_AB.write(value1);
	            	txtBW_AB.newLine();
	            	
	            	//2
	              	System.out.println("```");
	            	txtBW_AB.write("```");
	            	txtBW_AB.newLine();
	            	
	            	
	            	
	            	String value2 = "地址:         https://android.googlesource.com/"+ stringItem;
	            	
	            	//3
	            	System.out.println(value2);
	            	txtBW_AB.write(value2);
	            	txtBW_AB.newLine();
	            	
	            	
	            	
	            	
	            	
	            	String value3 = "抓取命令:     git clone https://android.googlesource.com/"+ stringItem;
	            	
	            	//4
	            	System.out.println(value3);
	            	txtBW_AB.write(value3);
	            	txtBW_AB.newLine();
	            	
	            	//4.1
	            	String value4_1 = "branch|Tag详细信息:     https://android.googlesource.com/"+ stringItem+"/+refs";
	            	System.out.println(value4_1);
	            	txtBW_AB.write(value4_1);
	            	txtBW_AB.newLine();
	            	
	            	//4.2
	            	String value4_2 = "commit提交详细信息:     https://android.googlesource.com/"+ stringItem+"/+log";
	            	System.out.println(value4_2);
	            	txtBW_AB.write(value4_2);
	            	txtBW_AB.newLine();
	            	
	            	//5
	              	System.out.println("```");
	            	txtBW_AB.write("```");
	            	txtBW_AB.newLine();
	              	
	              	//6
	              	System.out.println();
	              	System.out.println();
	            	txtBW_AB.newLine();
	            	txtBW_AB.newLine();
//	            	System.out.println("itemIndex = "+ itemIndex +" ## stringItem = "+ stringItem );
//	            	itemIndex++;
	            }
	            txtBW_AB.flush();
	            txtBW_AB.close();
	            
//	            ## accessories/manifest
//	            ```
//	            地址:       https://android.googlesource.com/accessories/manifest
//	            抓取命令:   git clone https://android.googlesource.com/accessories/manifest
//        branch|Tag详细信息:     https://android.googlesource.com/platform/system/hwservicemanager/+refs
//	            ```
            
		} catch (IOException e) {
			e.printStackTrace();
		}
      
	}
}
