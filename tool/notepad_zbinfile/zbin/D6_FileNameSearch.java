
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

public class D6_FileNameSearch {


    // 名称中的关键词
    static ArrayList<String> mKeyWordName = new ArrayList<>();
    static ArrayList<String> mTypeList = new ArrayList<>();

    static int currentMode = 0;
    static int SEARCH_MODE_TYPE_NAME = 0;
    static int SEARCH_MODE_TYPE = 1;
    static int SEARCH_MODE_NAME = 2;

    static Set<File> allDirFileSet = new HashSet<>();  // 工程下所有的 文件夹文件
    static Set<File> allSimpleFileSet = new HashSet<>();   // 当前工程下所有非文件夹的 文件
    static int mSumDirNum = 0;
    static  File curDirFile ;

    public static void main(String[] args) {

        String typeStr = null;
        String mShellPath = null;
         curDirFile = null;
//args[0] = D:\jira_download\0802\src
//args[1] = .jpg.txt.mp4.png
//args[2] = aaa
//args[3] = xxsaadx
//args[4] = xfdsa
//args[5] = sa
//args[6] = daada
//args[7] = dacfada
//args[8] = daada

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }
        }


        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (i == 0) {
                    mShellPath = args[0];
                    curDirFile = new File(mShellPath);
                    if (!curDirFile.exists()) {
                        System.out.println("当前shell路径不存在! 执行失败!  失败路径=" + mShellPath);
                        System.out.println("示例输入格式:  zfilesearch .jpg.txt.png.mp4  you  me  she ");
                        return;
                    }
                    continue;
                }
                String itemArgStr = args[i];
                if (itemArgStr.startsWith(".")) {
                    typeStr = itemArgStr.toLowerCase();
                    mTypeList = getSingleType(typeStr);
                } else {
                    mKeyWordName.add(itemArgStr.toLowerCase());
                }
            }
        }

        if (mKeyWordName.size() == 0 && mTypeList.size() == 0) {
            System.out.println(" 当前搜索的文件类型为空  当前搜索的文件名为空  将显示文件夹文件类型数据!");

            // 显示出当前文件夹 文件类型 以及文件个数
            initDirFileSet(curDirFile);
            initSimpleFileSetDetail();
            getFileTypeInfo();
            showMapSummaryDataStyle2();
            System.out.println("程序正常结束!");
            return;
        }

        if (mKeyWordName.size() > 0 && mTypeList.size() == 0) {
            currentMode = SEARCH_MODE_NAME;
        }
        if (mKeyWordName.size() == 0 && mTypeList.size() > 0) {
            currentMode = SEARCH_MODE_TYPE;
        }
        if (mKeyWordName.size() > 0 && mTypeList.size() > 0) {
            currentMode = SEARCH_MODE_TYPE_NAME;
        }


        initDirFileSet(curDirFile);
        initSimpleFileSet();

        initArrFileMap(mTypeList);

        beginFliterFile(mTypeList, mKeyWordName);

        sortMapData();
        showMapDetailData();

        showMapSummaryData();   // 文件类型:.mp3    匹配文件个数:128 
        if(mTypeList.size() == 1) {
        	String type = mTypeList.get(0);
            showOnlyOneTypeDetailInfo(type); //  对于只有一种选中类型的文件 进行 详细的解析
        	
        }


        System.out.println("程序正常结束!");
    }
    
    public static void showOnlyOneTypeDetailInfo(String type) {

    	String type_lower_trim = type.toLowerCase().trim();
    	System.out.println("打印 "+type+"文件类型的详细信息");
    	ArrayList<File> mFileList = arrFileMap.get(type);
    	switch(type_lower_trim) {
    	case ".mp3":
    		
    		ShowMp3Info(mFileList,type);
    	   break;
    	   
    	   
    	      default:
    	      	System.out.println(" "+type+"文件类型的详细信息  无具体逻辑请添加 到 D6_FileNameSearch!");
    	}
    	
    	
    }

      static void ShowMp3Info(ArrayList<File> fileList,String type) {
    	if(fileList == null || fileList.size() == 0) {
    		System.out.println("当前目录: "+curDirFile.getAbsolutePath()+" 不存在 文件类型"+type+"的文件!!");
    	   return;
    	}

    	ArrayList<File> D3V2_LowerSongInfo_DifferentArr= new ArrayList<File>();
    	for (int i = 0; i < fileList.size(); i++) {
    		try {

                File MP3FileItem =     fileList.get(i);

				Mp3File mp3TagItem = new Mp3File(MP3FileItem.getAbsolutePath());
               boolean isSameInfo =   GetDifferent_D3V2Tag_ByteTag_MP3Info(mp3TagItem,MP3FileItem,i+1,fileList.size());

               if(!isSameInfo){
                   D3V2_LowerSongInfo_DifferentArr.add(MP3FileItem);
               }

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("当前 MP3 文件解析错误! "+fileList.get(i).getAbsolutePath());
			}
    		
		}

if(D3V2_LowerSongInfo_DifferentArr.size() > 0){
    System.out.println("════════════ 存在【"+D3V2_LowerSongInfo_DifferentArr.size()+"】个 D3V2_Tag 和 Byte_Tag 不同的MP3文件 ══════════");

    for (int i = 0; i < D3V2_LowerSongInfo_DifferentArr.size(); i++) {
        File MP3FileItem =     fileList.get(i);

        try {
            Mp3File mp3TagItem_2 = new Mp3File(MP3FileItem.getAbsolutePath());

            Show_D3V2Tag_ByteTag_MP3Info(mp3TagItem_2,MP3FileItem,i+1,D3V2_LowerSongInfo_DifferentArr.size());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }

    }
    System.out.println("════════════ 存在【"+D3V2_LowerSongInfo_DifferentArr.size()+"】个 D3V2_Tag 和 Byte_Tag 不同的MP3文件 ══════════");

}else {
	System.out.println("════════════ 恭喜 Congratulations  当前读取到的所有MP3 D3V2_Tag 和 Byte_Tag 一致 ════════════");
}




      }

 // MP3的Comment 的 Tag 每次读取 都读取到
//      D3V2_Comment=[?国语]_CharSet[UTF-8]_Byte[00111111 10111001 11111010 11010011 11101111 ]  Byte_Comment=[?国语]_ChatSet[GB2312]_Byte[00111111 10111001 11111010 11010011 111011
//   问号  ?国语    为了把问号 去掉  所以创建这个方法                                                                                                                                      11 ]
                                                                                                                                    		 
      static String clearLuanMaForCommantTag(byte[] arr) {
    	  String resultStr = null;
    	  if(arr == null) {
        	  return null;
    	  }

    	  
    	  byte[] temp_byte_arr = new byte[arr.length-1];
    	  
    	  int temp_index = 0;
    	  for (int i = 0; i < arr.length; i++) {
			byte curByte = arr[i];
			String byteStr = showByte(curByte);
			if(byteStr != null && byteStr.trim().equals("00111111") && i ==0) {
				continue;
			}
			if(temp_index <= temp_byte_arr.length -1) {
				temp_byte_arr[temp_index] = curByte;
				temp_index++;
			}
	

		}

 
    	  try {
    		  resultStr =  new String(temp_byte_arr,"gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    	  return resultStr;
      }
      
      
      static class MP3_Lower25_SDK_SongInfo {
        private final String TAG = "TAG"; // 文件头1-3
        private String songName; // 歌曲名4-33
        private String artist; // 歌手名34-63
        private String album; // 专辑名64-93
        private String year; // 年94-97
        private String comment; // 备注98-125
        private byte r1, r2, r3; // 三个保留位126，127，128
        private boolean valid; // 是否合法
        public transient String fileName; // 此歌曲对应的文件名,没有封装


        public MP3_Lower25_SDK_SongInfo(byte[] data) {
            if (data.length != 128) {
                throw new RuntimeException("数据长度不合法:" + data.length);
            }
            String tag = new String(data, 0, 3);
// 只有前三个字节是TAG才处理后面的字节

            System.out.println("Lower25_SDK_SongInfo tag  = "+ tag);
            if (tag.equalsIgnoreCase("TAG")) {
                valid = true;
                try {
                	// utf-8    gbk
                    songName = new String(data, 3, 30,"gbk").trim();
                    artist = new String(data, 33, 30,"gbk").trim();
                    album = new String(data, 63, 30,"gbk").trim();
                    year = new String(data, 93, 4,"gbk").trim();
                    comment = new String(data, 97, 28,"gbk").trim();
                    comment =   clearLuanMaForCommantTag(comment.getBytes());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                r1 = data[125];
                r2 = data[126];
                r3 = data[127];
            } else {
                valid = false;
            }
        }


        /**
         * 返回是否合法
         *
         * @return 是否
         */
        public boolean isValid() {
            return valid;
        }

        /**
         * 得到此对象的128个字节的表示形式
         *
         * @return
         */
        public byte[] getBytes() {
            byte[] data = new byte[128];
            System.arraycopy(TAG.getBytes(), 0, data, 0, 3);
            byte[] temp = songName.getBytes();
            System.arraycopy(temp, 0, data, 3, temp.length > 30 ? 30 : temp.length);
            temp = artist.getBytes();
            System.arraycopy(temp, 0, data, 33, temp.length > 30 ? 30 : temp.length);
            temp = album.getBytes();
            System.arraycopy(temp, 0, data, 63, temp.length > 30 ? 30 : temp.length);
            temp = year.getBytes();
            System.arraycopy(temp, 0, data, 93, temp.length > 4 ? 4 : temp.length);
            temp = comment.getBytes();
            System.arraycopy(temp, 0, data, 97, temp.length > 28 ? 28 : temp.length);
            data[125] = r1;
            data[126] = r2;
            data[127] = r3;
            return data;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String authorName) {
            this.artist = authorName;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public byte getR1() {
            return r1;
        }

        public void setR1(byte r1) {
            this.r1 = r1;
        }

        public byte getR2() {
            return r2;
        }

        public void setR2(byte r2) {
            this.r2 = r2;
        }

        public byte getR3() {
            return r3;
        }

        public void setR3(byte r3) {
            this.r3 = r3;
        }

        public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            if (songName == null) {
                throw new NullPointerException("歌名不能是null!");
            }
            valid = true;
            this.songName = songName;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String specialName) {
            this.album = specialName;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }


    }


  	public static boolean GetDifferent_D3V2Tag_ByteTag_MP3Info(Mp3File  mp3Tag ,File mp3File, int index , int allSize) {

        boolean isDifferent = false;
		if (mp3Tag.hasId3v2Tag()) {
            RandomAccessFile raFile = null;
            byte[] buffer = new byte[128];
            try {
                raFile = new RandomAccessFile(mp3File, "r");

              raFile.seek(raFile.length() - 128);
              raFile.read(buffer);

                MP3_Lower25_SDK_SongInfo mp3Info = new MP3_Lower25_SDK_SongInfo(buffer);


                String mp3Info_artist = mp3Info.getArtist().trim();
                String mp3Info_songname = mp3Info.getSongName().trim();
                String mp3Info_comment = mp3Info.getComment().trim();


                ID3v2 id3v2Tag = mp3Tag.getId3v2Tag();
			  System.out.println("════════════════════════ MP3_D3V2_Info_mp3【"+index+"】【"+allSize+"】 "+mp3Tag.getFilename()+" ════════════════════════ ");

              String id3v2Tag_artist =  id3v2Tag.getArtist() != null ? id3v2Tag.getArtist().trim():null;
              String id3v2Tag_songname = id3v2Tag.getTitle() != null ? id3v2Tag.getTitle().trim():null;
              String id3v2Tag_comment = id3v2Tag.getComment() != null ? id3v2Tag.getComment().trim():null;
              if(id3v2Tag_comment != null) {
            	  id3v2Tag_comment =   clearLuanMaForCommantTag(id3v2Tag_comment.getBytes());
              }
              
         	 byte[] id3v3Comment_ByteArr = null;
              if(id3v2Tag_comment != null) {
            	 id3v3Comment_ByteArr =   id3v2Tag_comment.getBytes();
              }
              
           	 byte[] mp3Info_comment_ByteArr = null;
             if(mp3Info_comment != null) {
            	 mp3Info_comment_ByteArr =   mp3Info_comment.getBytes();
             }
             

              
              

			  System.out.println("D3V2_Artist=["+id3v2Tag.getArtist()+"]  "+"  Byte_Artist=["+mp3Info_artist+"]");
			  System.out.println("D3V2_Title=["+id3v2Tag.getTitle()+"]       Byte_Title=["+mp3Info_songname+"]");
                System.out.println("D3V2_Comment=["+id3v2Tag_comment+"]_CharSet["+getEncoding(id3v2Tag_comment)+"]_Byte["+showOneLineByteStr(id3v3Comment_ByteArr)+"]  Byte_Comment=["+mp3Info_comment+"]_ChatSet["+getEncoding(mp3Info_comment)+"]_Byte["+showOneLineByteStr(mp3Info_comment_ByteArr)+"]");
			  System.out.println("TAG_Album___________:  "+id3v2Tag.getAlbum());
			  System.out.println("TAG_Year____________:  "+id3v2Tag.getYear());
			  System.out.println("TAG_Genre___________:  "+id3v2Tag.getGenre()+"("+id3v2Tag.getGenreDescription()+")");
                System.out.println("TAG_Track___________:  "+id3v2Tag.getTrack()+"");
			  System.out.println("TAG_Lyrics__________:  "+id3v2Tag.getLyrics());
			  System.out.println("TAG_Composer________:  "+id3v2Tag.getComposer());
			  System.out.println("TAG_Publisher_______:  "+id3v2Tag.getPublisher());
			  System.out.println("TAG_Original_artist_:  "+id3v2Tag.getOriginalArtist());
			  System.out.println("TAG_Album_artist____:  "+id3v2Tag.getAlbumArtist());
			  System.out.println("TAG_Copyright_______:  "+id3v2Tag.getCopyright());
			  System.out.println("TAG_URL_____________:  "+id3v2Tag.getUrl());
			  System.out.println("TAG_Encoder_________:  "+id3v2Tag.getEncoder());
			  byte[] albumImageData = id3v2Tag.getAlbumImage();
			  if (albumImageData != null) {
			    System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
			    System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
			  }

			  // 只有 三个 项  都相等 才 是一致的
			  if(id3v2Tag_artist.equals(mp3Info_artist) &&  id3v2Tag_songname.equals(mp3Info_songname)   &&  id3v2Tag_comment.equals(mp3Info_comment)  ){
			      return true;
             }
            } catch (IOException e) {
                e.printStackTrace();
            }
	}else {
		
		System.out.println(mp3Tag.getFilename()+" 不存在 D3V2 Tag");
		return isDifferent;
	}
        return isDifferent;
	}


  	public static String getEncoding(String str) {
  		if(str == null) {
  			return "str_null";
  			
  		}
  		
  		String encode = "GB2312";
  		try {
  		if (str.equals(new String(str.getBytes(encode), encode))) {
  		String s = encode;
  		return s;
  		}
  		} catch (Exception exception) {
  		}
  		encode = "ISO-8859-1";
  		try {
  		if (str.equals(new String(str.getBytes(encode), encode))) {
  		String s1 = encode;
  		return s1;
  		}
  		} catch (Exception exception1) {
  		}
  		encode = "UTF-8";
  		try {
  		if (str.equals(new String(str.getBytes(encode), encode))) {
  		String s2 = encode;
  		return s2;
  		}
  		} catch (Exception exception2) {
  		}
  		
  		encode = "GBK";
  		try {
  		if (str.equals(new String(str.getBytes(encode), encode))) {
  		String s3 = encode;
  		return s3;
  		}
  		} catch (Exception exception3) {
  		}
  		
  		encode = "UTF-16";
  		try {
  		if (str.equals(new String(str.getBytes(encode), encode))) {
  		String s3 = encode;
  		return s3;
  		}
  		} catch (Exception exception3) {
  		}
  		
  		return "";
  		}
  	

    public static String showByte(byte byteData  ){
        StringBuilder sb  = new StringBuilder();
        String result = "";

        for (int i = 7; i >= 0; i--) {

            byte tempByte = byteData;
            tempByte = (byte)(tempByte >> i);
            int value = tempByte & 0x01;
            if(value == 1){
                sb.append("1");
            }else{
                sb.append("0");
            }

        }


        return sb.toString()+ " ";

    }
    
    public static String showOneLineByteStr(byte[] byteArr) {
    	String resultByte = null;
    	if(byteArr == null) {
    		return null;
    	}
    	
    	 StringBuilder mBinarySB = new StringBuilder();
    	for (int i = 0; i < byteArr.length; i++) {
    		byte byteStr  = byteArr[i];
    		mBinarySB.append(showByte(byteStr));
		}

    	
    	return mBinarySB.toString();
    	
    	
    }

    public static void Show_D3V2Tag_ByteTag_MP3Info(Mp3File  mp3Tag ,File mp3File, int index , int allSize) {


        if (mp3Tag.hasId3v2Tag()) {
            RandomAccessFile raFile = null;
            byte[] buffer = new byte[128];
            try {
                raFile = new RandomAccessFile(mp3File, "r");

                raFile.seek(raFile.length() - 128);
                raFile.read(buffer);

                MP3_Lower25_SDK_SongInfo mp3Info = new MP3_Lower25_SDK_SongInfo(buffer);


                String mp3Info_artist = mp3Info.getArtist().trim();
                String mp3Info_songname = mp3Info.getSongName().trim();
                String mp3Info_comment = mp3Info.getComment().trim();


                ID3v2 id3v2Tag = mp3Tag.getId3v2Tag();
                System.out.println("════════════ MP3_D3V2_Info_mp3【"+index+"】【"+allSize+"】 "+mp3Tag.getFilename()+" ════════════════════════ ");

                String id3v2Tag_artist =  id3v2Tag.getArtist() != null ? id3v2Tag.getArtist().trim():null;
                String id3v2Tag_songname = id3v2Tag.getTitle() != null ? id3v2Tag.getTitle().trim():null;
                String id3v2Tag_comment = id3v2Tag.getComment() != null ? id3v2Tag.getComment().trim():null;

                
                if(id3v2Tag_comment != null) {
              	  id3v2Tag_comment =   clearLuanMaForCommantTag(id3v2Tag_comment.getBytes());
                }
                
            	 byte[] id3v3Comment_ByteArr = null;
                 if(id3v2Tag_comment != null) {
               	 id3v3Comment_ByteArr =   id3v2Tag_comment.getBytes();
                 }
                 
              	 byte[] mp3Info_comment_ByteArr = null;
                if(mp3Info_comment != null) {
               	 mp3Info_comment_ByteArr =   mp3Info_comment.getBytes();
                }
                

                 
                 
                

                System.out.println("D3V2_Artist=["+id3v2Tag.getArtist()+"]  "+"  Byte_Artist=["+mp3Info_artist+"]");
                System.out.println("D3V2_Title=["+id3v2Tag.getTitle()+"]       Byte_Title=["+mp3Info_songname+"]");
                System.out.println("D3V2_Comment=["+id3v2Tag_comment+"]_CharSet["+getEncoding(id3v2Tag_comment)+"]_Byte["+showOneLineByteStr(id3v3Comment_ByteArr)+"]  Byte_Comment=["+mp3Info_comment+"]_ChatSet["+getEncoding(mp3Info_comment)+"]_Byte["+showOneLineByteStr(mp3Info_comment_ByteArr)+"]");
                System.out.println("TAG_Album___________:  "+id3v2Tag.getAlbum());
                System.out.println("TAG_Year____________:  "+id3v2Tag.getYear());
                System.out.println("TAG_Genre___________:  "+id3v2Tag.getGenre()+"("+id3v2Tag.getGenreDescription()+")");
                System.out.println("TAG_Track___________:  "+id3v2Tag.getTrack()+"");
                System.out.println("TAG_Lyrics__________:  "+id3v2Tag.getLyrics());
                System.out.println("TAG_Composer________:  "+id3v2Tag.getComposer());
                System.out.println("TAG_Publisher_______:  "+id3v2Tag.getPublisher());
                System.out.println("TAG_Original_artist_:  "+id3v2Tag.getOriginalArtist());
                System.out.println("TAG_Album_artist____:  "+id3v2Tag.getAlbumArtist());
                System.out.println("TAG_Copyright_______:  "+id3v2Tag.getCopyright());
                System.out.println("TAG_URL_____________:  "+id3v2Tag.getUrl());
                System.out.println("TAG_Encoder_________:  "+id3v2Tag.getEncoder());
                byte[] albumImageData = id3v2Tag.getAlbumImage();
                if (albumImageData != null) {
                    System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
                    System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {

            System.out.println(mp3Tag.getFilename()+" 不存在 D3V2 Tag");
            System.out.println("════════════ MP3_Index["+index+"] All_Size【"+allSize+"】 不存在 ID3v2 TAG !!!!");
        }

    }

    public static Comparator nameCompara = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            String fileName1 = o1.getName().toLowerCase();
            String fileName2 = o2.getName().toLowerCase();
            return fileName1.compareTo(fileName2);
        }
    };


    public static Comparator strCompara = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };


    @SuppressWarnings("unchecked")
    public static void sortMapData() {
        Set<String> keySet = arrFileMap.keySet();

        for (String curKey : keySet) {
            ArrayList<File> curFileList = arrFileMap.get(curKey);
            curFileList.sort(nameCompara);
        }

    }

    @SuppressWarnings("unchecked")
    public static void showMapDetailData() {
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                String fixedType = getFixedType(typeStr);
                System.out.println("================" + fixedType + "Begin================");
                String mdHex = "";
                for (int i = 0; i < fileArr.size(); i++) {
                    File curFile = fileArr.get(i);
                    String Path = curFile.getAbsolutePath();
                    try{

                         mdHex =     getMD5Three(Path);
                    }catch ( Exception e){

                    }

                    int index2 = i + 1;
                    int md5_Length = mdHex.length();
                    boolean isPaddingEmpty = md5_Length == 31 ? true:false;
                    String fixedIndex = getFixedIndex(index2);
                    System.out.println(" 【index : " + fixedIndex + "】   【 MD5("+md5_Length+"):  "+(isPaddingEmpty?" ":"")+mdHex+" 】 " + Path);
                }
                System.out.println("================" + fixedType + "End================");
            }
        }
    }

    public static String getMD5Three(String path) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi.toString(16);
    }


    static String get15FixedType(String type) {
        // type 最长保留10位
        String fixedType = "";
        int curLength = type.length();
        if (curLength < 15) {
            int blankIndex = 15 - curLength;
            for (int i = 0; i < blankIndex; i++) {
                fixedType = " " + fixedType;
            }

        }

        return type + fixedType;
    }



    static String getFixedType(String type) {
        // type 最长保留10位
        String fixedType = "";
        int curLength = type.length();
        if (curLength < 6) {
            int blankIndex = 6 - curLength;
            for (int i = 0; i < blankIndex; i++) {
                fixedType = " " + fixedType;
            }

        }

        return type + fixedType;
    }

    static String getFixedIndex(int i) {
        String rtnStr = "";
        if (i >= 0 && i < 10) { // 9
            rtnStr = "00000" + i;
        } else if (i >= 10 && i < 100) { // 99
            rtnStr = "0000" + i;
        } else if (i >= 100 && i < 1000) {  // 999
            rtnStr = "000" + i;
        } else if (i >= 1000 && i < 10000) {  // 9999
            rtnStr = "00" + i;
        } else if (i >= 10000 && i < 100000) {  // 99999
            rtnStr = "0" + i;
        } else {
            rtnStr = "" + i;
        }

        return rtnStr;
    }



    @SuppressWarnings("unchecked")
    public static void showMapSummaryDataStyle2() {
        int fileSum = 0 ;
        System.out.println();
        System.out.println();
        ArrayList<String> formatStringList = new  ArrayList<String>();
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                int curFileSize = fileArr.size();
                fileSum = fileSum +  curFileSize;
                //  System.out.println("文件类型:" + get15FixedType(typeStr) + "  匹配文件个数:" + fileArr.size());
                formatStringList.add("文件类型:" + get15FixedType(typeStr) + "  匹配文件个数:" + curFileSize);
            }
        }
        formatStringList.sort(strCompara);
        for(String infoItem: formatStringList){
            System.out.println(infoItem);
        }
        System.out.println("文件夹总数:"+allSimpleFileSet.size());
        System.out.println("文件总数:"+fileSum);
    }


    @SuppressWarnings("unchecked")
    public static void showMapSummaryData() {
        Map.Entry<String, ArrayList<File>> entry;
        if (arrFileMap != null) {
            Iterator iterator = arrFileMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
                String typeStr = entry.getKey();  //Map的Value
                ArrayList<File> fileArr = entry.getValue();  //Map的Value
                System.out.println("文件类型:" + getFixedType(typeStr) + "  匹配文件个数:" + fileArr.size());
            }
        }
    }


    public static void beginFliterFile(ArrayList<String> typeList, ArrayList<String> nameList) {
        ArrayList<File> allSingleFileList = new ArrayList<File>();
        allSingleFileList.addAll(allSimpleFileSet);
        for (int i = 0; i < allSingleFileList.size(); i++) {
            File curFile = allSingleFileList.get(i);

            if (currentMode == SEARCH_MODE_NAME) {
                String curFileName = curFile.getName().toLowerCase();
                boolean matchNameFlag = CheckFileName(curFileName, nameList);

                if (matchNameFlag) {
                    addItemFileToMap("", curFile);
                }


            } else if (currentMode == SEARCH_MODE_TYPE) {
                String curFileName = curFile.getName().toLowerCase();
                // 返回匹配到的类型  作为map的key 没有匹配到 就返回 null
                String matchTypeKey = CheckFileType(curFileName, typeList);
                if (matchTypeKey != null) {
                    addItemFileToMap(matchTypeKey, curFile);
                }
            } else {  //  类型和 名称都要检查
                String curFileName = curFile.getName().toLowerCase();
                boolean matchNameFlag = CheckFileName(curFileName, nameList);
                String matchTypeKey = CheckFileType(curFileName, typeList);
                if (matchNameFlag && matchTypeKey != null) {
                    addItemFileToMap(matchTypeKey, curFile);

                }

            }


        }

    }

    static void addItemFileToMap(String key, File fileItem) {
        ArrayList<File> fileArr = arrFileMap.get(key);
        fileArr.add(fileItem);
    }

    //
    static boolean CheckFileName(String curFileName, ArrayList<String> nameList) {
        boolean flag = false;

        for (int i = 0; i < nameList.size(); i++) {
            String curNameItem = nameList.get(i);

            if (curFileName.contains(curNameItem)) {
                flag = true;
                break;
            }

        }
        return flag;
    }


    static String CheckFileType(String curFileName, ArrayList<String> typeList) {
        String curTypeStr = null;
        if (!curFileName.contains(".")) {
// 当前的文件没有包含后缀  所以无法识别类型 返回 null
            return curTypeStr;
        }


//  获得 当前文件的  .后缀名    .jpg   .png
        String suffix = curFileName.substring(curFileName.lastIndexOf(".")).trim().toLowerCase();
        for (int i = 0; i < typeList.size(); i++) {
            String curFileType = typeList.get(i);

            if (curFileType.equals(suffix)) {
                curTypeStr = suffix;
                break;
            }

        }
        return curTypeStr;
    }


    // key = type       value =  符合过滤文件规则的名称的文件的集合
    public static HashMap<String, ArrayList<File>> arrFileMap = new HashMap<String, ArrayList<File>>();

    // 当 Mode 为 SEARCH_MODE_NAME 时 typeStrList 为空   那么 我们初始化的时候 就以 空字符串 "" 为key
    static void initArrFileMap(ArrayList<String> typeStrList) {
        if (typeStrList.size() == 0) {
            String key = "";
            ArrayList<File> fileList = new ArrayList<File>();
            arrFileMap.put(key, fileList);
        } else {
            for (int i = 0; i < typeStrList.size(); i++) {
                String typeItem = typeStrList.get(i);
                ArrayList<File> fileListItem = new ArrayList<File>();
                arrFileMap.put(typeItem, fileListItem);
            }
        }
    }


    static void addFileMapItemWithKey(String keyType, File curFile) {
        if (arrFileMap.containsKey(keyType)) {
            ArrayList<File> fileList = arrFileMap.get(keyType);
            fileList.add(curFile);
        } else {
            ArrayList<File> fileList = new ArrayList<File>();
            fileList.add(curFile);
            arrFileMap.put(keyType, fileList);
        }
    }


    static void initDirFileSet(File shellDirFile) {
        addAllFileDir(shellDirFile);
    }

    static void getFileTypeInfo() {
        int index = 1;
        System.out.println();
        System.out.println();
        for (File curFile : allSimpleFileSet) {
            String fileName = curFile.getName();
            System.out.println("文件索引[ "+index+"]  路径: "+ curFile.getAbsolutePath() );
            if (!fileName.contains(".")) {
                addFileMapItemWithKey("unknow", curFile);
            } else {
                String suffix = fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
                addFileMapItemWithKey(suffix, curFile);
            }
            index++;

        }
    }


    static void initSimpleFileSet() {
        int fileIndex = 1;
        for (File dirFile : allDirFileSet) {
//              System.out.println("index=" + fileIndex + "   PATH:" + dirFile.getAbsolutePath());
            File[] childFileList = dirFile.listFiles();
            if (childFileList != null && childFileList.length > 0) {
                for (int i = 0; i < childFileList.length; i++) {
                    if (!childFileList[i].isDirectory()) {
                        allSimpleFileSet.add(childFileList[i]);
                    }
                }

            }
            fileIndex++;
        }
    }


    static void initSimpleFileSetDetail() {
        int fileIndex = 1;
        System.out.println();
        System.out.println();
        for (File dirFile : allDirFileSet) {
            System.out.println("文件夹索引index=" + fileIndex + "   PATH: " + dirFile.getAbsolutePath());
            File[] childFileList = dirFile.listFiles();
            if (childFileList != null && childFileList.length > 0) {
                for (int i = 0; i < childFileList.length; i++) {
                    if (!childFileList[i].isDirectory()) {
                        allSimpleFileSet.add(childFileList[i]);
                    }
                }

            }
            fileIndex++;
        }
    }


    static int addAllFileDir(File dirFile) {   //  添加所有的 文件夹对象
        if (dirFile != null && dirFile.isDirectory()) {
            allDirFileSet.add(dirFile);
            mSumDirNum++;
        }
        if (isEmptyDirFile(dirFile)) {  // 如果是空的文件夹  返回它的内部文件夹数量是0
            return 0;
        }
        ArrayList<File> childDirFile = getChildDirFileMethod(dirFile);
        if (childDirFile != null && childDirFile.size() > 0) {

            for (File dirFileItem : childDirFile) {
                addAllFileDir(dirFileItem);
            }
        }
        return mSumDirNum;
    }


    static ArrayList<File> getChildDirFileMethod(File dirFile) {
        ArrayList<File> dirFileList = null;  // new   ArrayList<File>();
        if (dirFile == null) {
            return null;
        }
        File[] childChildList = dirFile.listFiles();
        for (int i = 0; i < childChildList.length; i++) {
            if (childChildList[i].isDirectory()) {
                if (dirFileList == null) {
                    dirFileList = new ArrayList<File>();
                }
                dirFileList.add(childChildList[i]);
            }
        }
        return dirFileList;
    }


    static boolean isEmptyDirFile(File dirFile) {
        boolean flag = true;
        if (dirFile == null) {
            return true;
        }
        File[] childChildList = dirFile.listFiles();
        if (childChildList == null) {
            return true;
        }
        for (int i = 0; i < childChildList.length; i++) {
            if (childChildList[i].isDirectory()) {
                return false;
            }
        }
        return flag;
    }


    static ArrayList<String> getSingleType(String typeStr) {
        ArrayList<String> strList = new ArrayList<String>();
        String replacePointStr = typeStr.replace(".", "_");
        String[] arr = replacePointStr.split("_");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null || "".equals(arr[i].trim())) {
                continue;
            }
            strList.add("." + arr[i].trim());
        }
        return strList;
    }


}