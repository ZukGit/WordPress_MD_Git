import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

public class StringHtml2 {
    public static final String A_File = "html2.txt";   // 输入文件1.txt
    public static final String AB_File = "html2out.txt";   // 输出文件1.txt
    
	public static void main(String[] args) {
        File txtFile = new File(System.getProperty("user.dir") + File.separator+"/src/" + A_File);
        File txtFileOut = new File(System.getProperty("user.dir") + File.separator+"/src/"  + AB_File);
        ArrayList<String> stringListBranch = new ArrayList();
        ArrayList<String> stringListTag = new ArrayList();
        
        
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
	         
	           
	            String[] strArr = StringbuildA.toString().split("RefList-item");
	         //   System.out.println(strArr.length);
	           boolean tagFlag = false;
	            for (int i = 2; i < strArr.length ; i++) {
	            	//System.out.println("index = "+ i +" ## value = "+ strArr[i] );
	            	String item = strArr[i].substring(3, strArr[i].length());
		          //  System.out.println("index = "+ i +"  ## item = "+item);
	            	int begin = item.indexOf(">");
	            	int end = item.indexOf("<");
	            	String subString = item.substring(begin+1, end);
	            	System.out.println(subString);
	            	//String subString = strArr[i].substring(2, index);
	          // System.out.println("begin = "+ begin +"   end ="+end  +" ## subString = "+ subString );
	            	if(subString.isEmpty()){
	            		tagFlag = true;
	            		continue;
	            	}
	            	if(tagFlag){
	            		stringListTag.add(subString);
	            	}else{
	            		stringListBranch.add(subString);
	            	}
	            
				}
	        //	System.out.println("stringListBranch-size:"+stringListBranch.size()+" index0:"+stringListBranch.get(0)); 
	       // 	System.out.println("stringListTag-size:"+stringListTag.size()+" index0:"+stringListTag.get(0));   
	            
	            //   0
            	String Head1 = "# 谷歌Git仓库Branch分支";
            	System.out.println(Head1);
            	txtBW_AB.write(Head1);
            	txtBW_AB.newLine();
            	
	            for(int j = 0; j < stringListBranch.size(); j++){
	            	String stringItem = stringListBranch.get(j);
	            	String value1 = "## "+ stringItem;
	            	
	            	// 1
	            	System.out.println(value1);
	            	txtBW_AB.write(value1);
	            	txtBW_AB.newLine();
	            	
	            	//2
	              	System.out.println("```");
	            	txtBW_AB.write("```");
	            	txtBW_AB.newLine();	
	            	
	            	
	            	//   3
	              	System.out.println("1.查看仓库分支最新一笔提交【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+/"+stringItem);
	            	txtBW_AB.write("1.查看仓库分支最新一笔提交【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+/"+stringItem);
	            	txtBW_AB.newLine();	
	            	
	            	//   4
	              	System.out.println("2.查看仓库分支最新提交记录【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+log/"+stringItem);
	            	txtBW_AB.write("2.查看仓库分支最新提交记录【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+log/"+stringItem);
	            	txtBW_AB.newLine();	
	            	
	            	
	            	//   5
	              	System.out.println("3.查看commitId提交分支下提交记录【repositories】【commitId】[commitId的点击事件]:   https://android.googlesource.com/【仓库路径】/+/【commitId】");
	            	txtBW_AB.write("3.查看commitId提交分支下提交记录【repositories】【commitId】[commitId的点击事件]:   https://android.googlesource.com/【仓库路径】/+/【commitId】");
	            	txtBW_AB.newLine();	
	          
	            	//  6
	              	System.out.println("4.查看某个【commitId】提交的历史路径 [log的点击事件]:   https://android.googlesource.com/【仓库路径】/+log/【commitId】/");
	            	txtBW_AB.write("4.查看某个【commitId】提交的历史路径 [log的点击事件]:   https://android.googlesource.com/【仓库路径】/+log/【commitId】/");
	            	txtBW_AB.newLine();	
	            	
	            	
	            	//  7
	              	System.out.println("5.查看【commitId】提交过后该仓库的文件 [tree的点击事件]:       https://android.googlesource.com/【仓库路径】/+/【commitId】/");
	            	txtBW_AB.write("5.查看【commitId】提交过后该仓库的文件 [tree的点击事件]:       https://android.googlesource.com/【仓库路径】/+/【commitId】/");
	            	txtBW_AB.newLine();	
	            	
	            	//  8
	              	System.out.println("6.查看【commitId】 与 【parrntId】之间的详细修改记录 [有些时候有多个parentId] ");
	            	txtBW_AB.write("6.查看【commitId】 与 【parrntId】之间的详细修改记录 [有些时候有多个parentId] ");
	            	txtBW_AB.newLine();	
	            	
	            	
	            	//  9
	              	System.out.println("%5E1..    第一个parentId对比");
	            	txtBW_AB.write("%5E1..    第一个parentId对比");
	            	txtBW_AB.newLine();	
	            	
	            	//  10
	              	System.out.println("%5E2..    第二个parentId对比(如果有)");
	            	txtBW_AB.write("%5E2..    第二个parentId对比(如果有)");
	            	txtBW_AB.newLine();	
	            	
	            	
	            	//  11
	              	System.out.println("%5E3..    第三个parentId对比(如果有)");
	            	txtBW_AB.write("%5E3..    第三个parentId对比(如果有)");
	            	txtBW_AB.newLine();	
	         
	            	
	            	//  12
	              	System.out.println(" https://android.googlesource.com/【仓库路径】/+/【commitId】%5E1..【commitId】");
	            	txtBW_AB.write(" https://android.googlesource.com/【仓库路径】/+/【commitId】%5E1..【commitId】");
	            	txtBW_AB.newLine();	
	            	
	            	
	            	// 13    14
	              	System.out.println();
	              	System.out.println();
	            	txtBW_AB.newLine();		
	            	txtBW_AB.newLine();		
	            	
	            	
	            	// 15
	              	System.out.println("repositories:     platform/packages/apps/Settings");
	            	txtBW_AB.write("repositories:     platform/packages/apps/Settings");
	            	txtBW_AB.newLine();	
	            	
	            	// 16
	              	System.out.println("branch:           master");
	            	txtBW_AB.write("branch:           master");
	            	txtBW_AB.newLine();		      
	            	
	            	// 17
	              	System.out.println("commitId:         71f9fe4dfabfdeba17cf9233a6104e473fa31f68");
	            	txtBW_AB.write("commitId:         71f9fe4dfabfdeba17cf9233a6104e473fa31f68");
	            	txtBW_AB.newLine();		
	            	
	            	
	            	// 18
	              	System.out.println("parent	6db9f21256108addf338a33db06b582f92115cdd");
	            	txtBW_AB.write("parent	6db9f21256108addf338a33db06b582f92115cdd");
	            	txtBW_AB.newLine();	
	            	
	            	
	            	
	            	// 19
	              	System.out.println("parent	54d5a727c1829872c37bd983dd4f45c2efa091c2");
	            	txtBW_AB.write("parent	54d5a727c1829872c37bd983dd4f45c2efa091c2");
	            	txtBW_AB.newLine();	
	            	
	            // 20
	              	System.out.println();
	            	txtBW_AB.newLine();	
	            	
	            	
	            	// 21 
	              	System.out.println("1.    https://android.googlesource.com/platform/packages/apps/Settings/+/"+stringItem);
	            	txtBW_AB.write("1.    https://android.googlesource.com/platform/packages/apps/Settings/+/"+stringItem);
	            	txtBW_AB.newLine();	
	       
	            	
	            	// 22
	              	System.out.println("2.    https://android.googlesource.com/platform/packages/apps/Settings/+log/"+ stringItem);
	            	txtBW_AB.write("2.    https://android.googlesource.com/platform/packages/apps/Settings/+log/"+ stringItem);
	            	txtBW_AB.newLine();	
	 
	            	
	            	// 23
	              	System.out.println("3.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68");
	            	txtBW_AB.write("3.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68");
	            	txtBW_AB.newLine();	
	            	
	       
	            	// 24
	              	System.out.println("4.    https://android.googlesource.com/platform/packages/apps/Settings/+log/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
	            	txtBW_AB.write("4.    https://android.googlesource.com/platform/packages/apps/Settings/+log/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
	            	txtBW_AB.newLine();	
	            	
	            	
	            	
	            	// 25
	              	System.out.println("5.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
	            	txtBW_AB.write("5.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
	            	txtBW_AB.newLine();	
	            	
	            	
	            	
	            	// 26
	              	System.out.println("6.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E1..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
	            	txtBW_AB.write("6.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E1..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
	            	txtBW_AB.newLine();	
	            	
	    
	            	// 27
	              	System.out.println("      https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E2..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
	            	txtBW_AB.write("      https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E2..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
	            	txtBW_AB.newLine();	
	            	
	            	
	            	//28
	              	System.out.println("```");
	            	txtBW_AB.write("```");
	            	txtBW_AB.newLine();	
	            	
	            }
	            
	            
            	String Head2 = "# 谷歌Git仓库Tag标签";
            	System.out.println(Head2);
            	txtBW_AB.write(Head2);
            	txtBW_AB.newLine(); 
            	
	            for(int j = 0; j < stringListTag.size(); j++){
	            	String stringItem = stringListTag.get(j);
	            	String value1 = "## "+ stringItem;
	            	
	            	// 1
	            	System.out.println(value1);
	            	txtBW_AB.write(value1);
	            	txtBW_AB.newLine();
	            	
	            	//2
	              	System.out.println("```");
	            	txtBW_AB.write("```");
	            	txtBW_AB.newLine();	
	            	
//	            	
//	            	//   3
	              	System.out.println("1.查看仓库分支最新一笔提交【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+/"+stringItem);
	            	txtBW_AB.write("1.查看仓库分支最新一笔提交【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+/"+stringItem);
	            	txtBW_AB.newLine();	
//	            	
//	            	//   4
	              	System.out.println("2.查看仓库分支最新提交记录【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+log/"+stringItem);
	            	txtBW_AB.write("2.查看仓库分支最新提交记录【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+log/"+stringItem);
	            	txtBW_AB.newLine();	
//	            	
//	            	
//	            	//   5
//	              	System.out.println("3.查看commitId提交分支下提交记录【repositories】【commitId】[commitId的点击事件]:   https://android.googlesource.com/【仓库路径】/+/【commitId】");
//	            	txtBW_AB.write("3.查看commitId提交分支下提交记录【repositories】【commitId】[commitId的点击事件]:   https://android.googlesource.com/【仓库路径】/+/【commitId】");
//	            	txtBW_AB.newLine();	
//	          
//	            	//  6
//	              	System.out.println("4.查看某个【commitId】提交的历史路径 [log的点击事件]:   https://android.googlesource.com/【仓库路径】/+log/【commitId】/");
//	            	txtBW_AB.write("4.查看某个【commitId】提交的历史路径 [log的点击事件]:   https://android.googlesource.com/【仓库路径】/+log/【commitId】/");
//	            	txtBW_AB.newLine();	
//	            	
//	            	
//	            	//  7
//	              	System.out.println("5.查看【commitId】提交过后该仓库的文件 [tree的点击事件]:       https://android.googlesource.com/【仓库路径】/+/【commitId】/");
//	            	txtBW_AB.write("5.查看【commitId】提交过后该仓库的文件 [tree的点击事件]:       https://android.googlesource.com/【仓库路径】/+/【commitId】/");
//	            	txtBW_AB.newLine();	
//	            	
//	            	//  8
//	              	System.out.println("6.查看【commitId】 与 【parrntId】之间的详细修改记录 [有些时候有多个parentId] ");
//	            	txtBW_AB.write("6.查看【commitId】 与 【parrntId】之间的详细修改记录 [有些时候有多个parentId] ");
//	            	txtBW_AB.newLine();	
//	            	
//	            	
//	            	//  9
//	              	System.out.println("%5E1..    第一个parentId对比");
//	            	txtBW_AB.write("%5E1..    第一个parentId对比");
//	            	txtBW_AB.newLine();	
//	            	
//	            	//  10
//	              	System.out.println("%5E2..    第二个parentId对比(如果有)");
//	            	txtBW_AB.write("%5E2..    第二个parentId对比(如果有)");
//	            	txtBW_AB.newLine();	
//	            	
//	            	
//	            	//  11
//	              	System.out.println("%5E3..    第三个parentId对比(如果有)");
//	            	txtBW_AB.write("%5E3..    第三个parentId对比(如果有)");
//	            	txtBW_AB.newLine();	
//	         
//	            	
//	            	//  12
//	              	System.out.println(" https://android.googlesource.com/【仓库路径】/+/【commitId】%5E1..【commitId】");
//	            	txtBW_AB.write(" https://android.googlesource.com/【仓库路径】/+/【commitId】%5E1..【commitId】");
//	            	txtBW_AB.newLine();	
//	            	
//	            	
//	            	// 13    14
//	              	System.out.println();
//	              	System.out.println();
//	            	txtBW_AB.newLine();		
//	            	txtBW_AB.newLine();		
	            	
	            	
//	            	// 15
//	              	System.out.println("repositories:     platform/packages/apps/Settings");
//	            	txtBW_AB.write("repositories:     platform/packages/apps/Settings");
//	            	txtBW_AB.newLine();	
//	            	
//	            	// 16
//	              	System.out.println("branch:           master");
//	            	txtBW_AB.write("branch:           master");
//	            	txtBW_AB.newLine();		      
//	            	
//	            	// 17
//	              	System.out.println("commitId:         71f9fe4dfabfdeba17cf9233a6104e473fa31f68");
//	            	txtBW_AB.write("commitId:         71f9fe4dfabfdeba17cf9233a6104e473fa31f68");
//	            	txtBW_AB.newLine();		
//	            	
//	            	
//	            	// 18
//	              	System.out.println("parent	6db9f21256108addf338a33db06b582f92115cdd");
//	            	txtBW_AB.write("parent	6db9f21256108addf338a33db06b582f92115cdd");
//	            	txtBW_AB.newLine();	
//	            	
//	            	
//	            	
//	            	// 19
//	              	System.out.println("parent	54d5a727c1829872c37bd983dd4f45c2efa091c2");
//	            	txtBW_AB.write("parent	54d5a727c1829872c37bd983dd4f45c2efa091c2");
//	            	txtBW_AB.newLine();	
//	            	
//	            // 20
//	              	System.out.println();
//	            	txtBW_AB.newLine();	
//	            	
//	            	
	            	// 21 
	              	System.out.println("1.    https://android.googlesource.com/platform/packages/apps/Settings/+/"+stringItem);
	            	txtBW_AB.write("1.    https://android.googlesource.com/platform/packages/apps/Settings/+/"+stringItem);
	            	txtBW_AB.newLine();	
	       
	            	
	            	// 22
	              	System.out.println("2.    https://android.googlesource.com/platform/packages/apps/Settings/+log/"+stringItem);
	            	txtBW_AB.write("2.    https://android.googlesource.com/platform/packages/apps/Settings/+log/"+stringItem);
	            	txtBW_AB.newLine();	
//	 
//	            	
//	            	// 23
//	              	System.out.println("3.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68");
//	            	txtBW_AB.write("3.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68");
//	            	txtBW_AB.newLine();	
//	            	
//	       
//	            	// 24
//	              	System.out.println("4.    https://android.googlesource.com/platform/packages/apps/Settings/+log/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
//	            	txtBW_AB.write("4.    https://android.googlesource.com/platform/packages/apps/Settings/+log/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
//	            	txtBW_AB.newLine();	
//	            	
//	            	
//	            	
//	            	// 25
//	              	System.out.println("5.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
//	            	txtBW_AB.write("5.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
//	            	txtBW_AB.newLine();	
//	            	
//	            	
//	            	
//	            	// 26
//	              	System.out.println("6.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E1..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
//	            	txtBW_AB.write("6.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E1..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
//	            	txtBW_AB.newLine();	
//	            	
//	    
//	            	// 27
//	              	System.out.println("      https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E2..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
//	            	txtBW_AB.write("      https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E2..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/");
//	            	txtBW_AB.newLine();	
	            	
	            	
	            	//28
	              	System.out.println("```");
	            	txtBW_AB.write("```");
	            	txtBW_AB.newLine();	
	            	
	            }
	            txtBW_AB.flush();
	            txtBW_AB.close();
	            
	     /*       
	        【0】    # 谷歌Git仓库Branch分支

	             【1】## master  【Head1】
	       【2】  ```
	     【3】  1.查看仓库分支最新一笔提交【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+/master
	    【4】        2.查看仓库分支最新提交记录【repositories】【branch】:   https://android.googlesource.com/【仓库路径】/+log/master
	   【5】         3.查看commitId提交分支下提交记录【repositories】【commitId】[commitId的点击事件]:   https://android.googlesource.com/【仓库路径】/+/【commitId】
	  【6】          4.查看某个【commitId】提交的历史路径 [log的点击事件]:   https://android.googlesource.com/【仓库路径】/+log/【commitId】/
	  【7】          5.查看【commitId】提交过后该仓库的文件 [tree的点击事件]:       https://android.googlesource.com/【仓库路径】/+/【commitId】/
	  【8】          6.查看【commitId】 与 【parrntId】之间的详细修改记录 [有些时候有多个parentId] 
	   【9】         %5E1..    第一个parentId对比
	   【10】         %5E2..    第二个parentId对比(如果有)
	    【11】        %5E3..    第三个parentId对比(如果有)
	   【12】         https://android.googlesource.com/【仓库路径】/+/【commitId】%5E1..【commitId】
            【13】
            【14】
	   【15】         repositories:     platform/packages/apps/Settings
	   【16】         branch:           master
	  【17】          commitId:         71f9fe4dfabfdeba17cf9233a6104e473fa31f68
	  【18】          parent	6db9f21256108addf338a33db06b582f92115cdd
	  【19】          parent	54d5a727c1829872c37bd983dd4f45c2efa091c2
      【20】
	            1.    https://android.googlesource.com/platform/packages/apps/Settings/+/master
	            2.    https://android.googlesource.com/platform/packages/apps/Settings/+log/master
	            3.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68
	            4.    https://android.googlesource.com/platform/packages/apps/Settings/+log/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/
	            5.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68/
	            6.    https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E1..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/
	                  https://android.googlesource.com/platform/packages/apps/Settings/+/71f9fe4dfabfdeba17cf9233a6104e473fa31f68%5E2..71f9fe4dfabfdeba17cf9233a6104e473fa31f68/
	 【28】           ```
	            */
	            /**
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
            **/
		} catch (IOException e) {
			e.printStackTrace();
		}
      
	}
}
