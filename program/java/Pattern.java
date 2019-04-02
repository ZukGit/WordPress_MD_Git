import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Pattern {
    public static final String A_File = "1.txt";   // 输入文件1.txt
    public static final String B_File = "2.txt";   // 输出文件1.txt
    public static final String AB_File = "out.txt";   // 输出文件1.txt
    
	public static void main(String[] args) {
        File txtFile = new File(System.getProperty("user.dir") + File.separator+"/src/" + A_File);
        File txtFileB = new File(System.getProperty("user.dir") + File.separator+"/src/"  + B_File);
        File txtFileOut = new File(System.getProperty("user.dir") + File.separator+"/src/"  + AB_File);
        if(!txtFileOut.exists()){
        	try {
				txtFileOut.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        FileReader txtReader;
        FileReader txtReaderB;
        FileWriter txtWriter ;
		try {
			txtReader = new FileReader(txtFile);
			txtReaderB = new FileReader(txtFileB);
			txtWriter = new FileWriter(txtFileOut);
			  BufferedReader txtBR_A = new BufferedReader(txtReader);
			  BufferedReader txtBR_B = new BufferedReader(txtReaderB);
			  BufferedWriter txtBW_AB = new BufferedWriter(txtWriter);
			  String lineContentFirst_A = "";   // 读取到的输入文件 1.txt 的每一行字符串
			  String lineContentFirst_B = "";   // 读取到的输入文件 1.txt 的每一行字符串
			  int num = 1;
		        // 一次性读出所有的字符串String   然后再重新编排？
	            while (lineContentFirst_A != null && lineContentFirst_B != null) {
	 
	            	lineContentFirst_A = txtBR_A.readLine();   // 从1.txt中读取一行字符串出来
	            	lineContentFirst_B = txtBR_B.readLine();   // 从1.txt中读取一行字符串出来
	                if (lineContentFirst_A == null || lineContentFirst_B == null ) { // 如果读取到的字符串为null 说明读取到了末尾了
	                    System.out.println("1.txt read to end!");
	                    txtBW_AB.flush();
	                    break;
	                }
	                String  newLineStr = num+"  :"+lineContentFirst_A +"   "+ lineContentFirst_B;
	                num++;
	                txtBW_AB.write(newLineStr);
	                txtBW_AB.newLine();
	            }
		} catch (IOException e) {
			e.printStackTrace();
		}
      
	}
}
