
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDescriptor;
import com.drew.metadata.exif.ExifSubIFDDirectory;
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

	static Set<File> allDirFileSet = new HashSet<>(); // 工程下所有的 文件夹文件
	static Set<File> allSimpleFileSet = new HashSet<>(); // 当前工程下所有非文件夹的 文件
	static int mSumDirNum = 0;
	static File curDirFile;

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
		

System.out.println("系统默认编码"+Charset.defaultCharset());
        
System.getProperties().put("file.encoding", "UTF-8");
System.getProperties().list(System.out);
        
System.out.println("defaultCharSet: "+Charset.defaultCharset());


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
			showAllTip();
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

		showMapSummaryData(); // 文件类型:.mp3 匹配文件个数:128
		if (mTypeList.size() == 1) {
			String type = mTypeList.get(0);
			showOnlyOneTypeDetailInfo(type); // 对于只有一种选中类型的文件 进行 详细的解析

		}

		System.out.println("程序正常结束!");
	}

	public static void showOnlyOneTypeDetailInfo(String type) {

		String type_lower_trim = type.toLowerCase().trim();
		System.out.println("打印 " + type + "文件类型的详细信息");
		ArrayList<File> mFileList = arrFileMap.get(type);
		switch (type_lower_trim) {
		case ".mp3":

			ShowMp3Info(mFileList, type);
			break;

		case ".jpg":
		case ".JPG":
		case ".jpeg":
		case ".JPEG":
		case ".png":
		case ".PNG":
			showExifInfo(mFileList, type);
			break;

		default:
			System.out.println(" " + type + "文件类型的详细信息  无具体逻辑请添加 到 D6_FileNameSearch!");
		}

	}

	static void ShowMp3Info(ArrayList<File> fileList, String type) {
		if (fileList == null || fileList.size() == 0) {
			System.out.println("当前目录: " + curDirFile.getAbsolutePath() + " 不存在 文件类型" + type + "的文件!!");
			return;
		}

		ArrayList<File> D3V2_LowerSongInfo_DifferentArr = new ArrayList<File>();
		for (int i = 0; i < fileList.size(); i++) {
			try {

				File MP3FileItem = fileList.get(i);

				Mp3File mp3TagItem = new Mp3File(MP3FileItem.getAbsolutePath());
				boolean isSameInfo = GetDifferent_D3V2Tag_ByteTag_MP3Info(mp3TagItem, MP3FileItem, i + 1,
						fileList.size());

				if (!isSameInfo) {
					D3V2_LowerSongInfo_DifferentArr.add(MP3FileItem);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("当前 MP3 文件解析错误! " + fileList.get(i).getAbsolutePath());
			}

		}

		if (D3V2_LowerSongInfo_DifferentArr.size() > 0) {
			System.out.println("════════════ 存在【" + D3V2_LowerSongInfo_DifferentArr.size()
					+ "】个 D3V2_Tag 和 Byte_Tag 不同的MP3文件 ══════════");

			for (int i = 0; i < D3V2_LowerSongInfo_DifferentArr.size(); i++) {
				File MP3FileItem = fileList.get(i);

				try {
					Mp3File mp3TagItem_2 = new Mp3File(MP3FileItem.getAbsolutePath());

					Show_D3V2Tag_ByteTag_MP3Info(mp3TagItem_2, MP3FileItem, i + 1,
							D3V2_LowerSongInfo_DifferentArr.size());

				} catch (IOException e) {
					e.printStackTrace();
				} catch (UnsupportedTagException e) {
					e.printStackTrace();
				} catch (InvalidDataException e) {
					e.printStackTrace();
				}

			}
			System.out.println("════════════ 存在【" + D3V2_LowerSongInfo_DifferentArr.size()
					+ "】个 D3V2_Tag 和 Byte_Tag 不同的MP3文件 ══════════");

		} else {
			System.out.println("════════════ 恭喜 Congratulations  当前读取到的所有MP3 D3V2_Tag 和 Byte_Tag 一致 ════════════");
		}

	}

	// MP3的Comment 的 Tag 每次读取 都读取到
//      D3V2_Comment=[?国语]_CharSet[UTF-8]_Byte[00111111 10111001 11111010 11010011 11101111 ]  Byte_Comment=[?国语]_ChatSet[GB2312]_Byte[00111111 10111001 11111010 11010011 111011
//   问号  ?国语    为了把问号 去掉  所以创建这个方法                                                                                                                                      11 ]

	static String clearLuanMaForCommantTag(byte[] arr) {
		String resultStr = null;
		if (arr == null) {
			return null;
		}

		byte[] temp_byte_arr = new byte[arr.length - 1];

		int temp_index = 0;
		for (int i = 0; i < arr.length; i++) {
			byte curByte = arr[i];
			String byteStr = showByte(curByte);
			if (byteStr != null && byteStr.trim().equals("00111111") && i == 0) {
				continue;
			}
			if (temp_index <= temp_byte_arr.length - 1) {
				temp_byte_arr[temp_index] = curByte;
				temp_index++;
			}

		}

		try {
			resultStr = new String(temp_byte_arr, "gbk");
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

			System.out.println("Lower25_SDK_SongInfo tag  = " + tag);
			if (tag.equalsIgnoreCase("TAG")) {
				valid = true;
				try {
					// utf-8 gbk
					songName = new String(data, 3, 30, "gbk").trim();
					artist = new String(data, 33, 30, "gbk").trim();
					album = new String(data, 63, 30, "gbk").trim();
					year = new String(data, 93, 4, "gbk").trim();
					comment = new String(data, 97, 28, "gbk").trim();
					comment = clearLuanMaForCommantTag(comment.getBytes());
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

	public static boolean GetDifferent_D3V2Tag_ByteTag_MP3Info(Mp3File mp3Tag, File mp3File, int index, int allSize) {

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
				System.out.println("════════════════════════ MP3_D3V2_Info_mp3【" + index + "】【" + allSize + "】 "
						+ mp3Tag.getFilename() + " ════════════════════════ ");

				String id3v2Tag_artist = id3v2Tag.getArtist() != null ? id3v2Tag.getArtist().trim() : null;
				String id3v2Tag_songname = id3v2Tag.getTitle() != null ? id3v2Tag.getTitle().trim() : null;
				String id3v2Tag_comment = id3v2Tag.getComment() != null ? id3v2Tag.getComment().trim() : null;
				if (id3v2Tag_comment != null) {
					id3v2Tag_comment = clearLuanMaForCommantTag(id3v2Tag_comment.getBytes());
				}

				byte[] id3v3Comment_ByteArr = null;
				if (id3v2Tag_comment != null) {
					id3v3Comment_ByteArr = id3v2Tag_comment.getBytes();
				}

				byte[] mp3Info_comment_ByteArr = null;
				if (mp3Info_comment != null) {
					mp3Info_comment_ByteArr = mp3Info_comment.getBytes();
				}

				System.out.println(
						"D3V2_Artist=[" + id3v2Tag.getArtist() + "]  " + "  Byte_Artist=[" + mp3Info_artist + "]");
				System.out.println(
						"D3V2_Title=[" + id3v2Tag.getTitle() + "]       Byte_Title=[" + mp3Info_songname + "]");
				System.out.println("D3V2_Comment=[" + id3v2Tag_comment + "]_CharSet[" + getEncoding(id3v2Tag_comment)
						+ "]_Byte[" + showOneLineByteStr(id3v3Comment_ByteArr) + "]  Byte_Comment=[" + mp3Info_comment
						+ "]_ChatSet[" + getEncoding(mp3Info_comment) + "]_Byte["
						+ showOneLineByteStr(mp3Info_comment_ByteArr) + "]");
				System.out.println("TAG_Album___________:  " + id3v2Tag.getAlbum());
				System.out.println("TAG_Year____________:  " + id3v2Tag.getYear());
				System.out.println(
						"TAG_Genre___________:  " + id3v2Tag.getGenre() + "(" + id3v2Tag.getGenreDescription() + ")");
				System.out.println("TAG_Track___________:  " + id3v2Tag.getTrack() + "");
				System.out.println("TAG_Lyrics__________:  " + id3v2Tag.getLyrics());
				System.out.println("TAG_Composer________:  " + id3v2Tag.getComposer());
				System.out.println("TAG_Publisher_______:  " + id3v2Tag.getPublisher());
				System.out.println("TAG_Original_artist_:  " + id3v2Tag.getOriginalArtist());
				System.out.println("TAG_Album_artist____:  " + id3v2Tag.getAlbumArtist());
				System.out.println("TAG_Copyright_______:  " + id3v2Tag.getCopyright());
				System.out.println("TAG_URL_____________:  " + id3v2Tag.getUrl());
				System.out.println("TAG_Encoder_________:  " + id3v2Tag.getEncoder());
				byte[] albumImageData = id3v2Tag.getAlbumImage();
				if (albumImageData != null) {
					System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
					System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
				}

				// 只有 三个 项 都相等 才 是一致的
				if (id3v2Tag_artist.equals(mp3Info_artist) && id3v2Tag_songname.equals(mp3Info_songname)
						&& id3v2Tag_comment.equals(mp3Info_comment)) {
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			System.out.println(mp3Tag.getFilename() + " 不存在 D3V2 Tag");
			return isDifferent;
		}
		return isDifferent;
	}

	public static String getEncoding(String str) {
		if (str == null) {
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

	public static String showByte(byte byteData) {
		StringBuilder sb = new StringBuilder();
		String result = "";

		for (int i = 7; i >= 0; i--) {

			byte tempByte = byteData;
			tempByte = (byte) (tempByte >> i);
			int value = tempByte & 0x01;
			if (value == 1) {
				sb.append("1");
			} else {
				sb.append("0");
			}

		}

		return sb.toString() + " ";

	}

	public static String showOneLineByteStr(byte[] byteArr) {
		String resultByte = null;
		if (byteArr == null) {
			return null;
		}

		StringBuilder mBinarySB = new StringBuilder();
		for (int i = 0; i < byteArr.length; i++) {
			byte byteStr = byteArr[i];
			mBinarySB.append(showByte(byteStr));
		}

		return mBinarySB.toString();

	}

	public static void Show_D3V2Tag_ByteTag_MP3Info(Mp3File mp3Tag, File mp3File, int index, int allSize) {

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
				System.out.println("════════════ MP3_D3V2_Info_mp3【" + index + "】【" + allSize + "】 "
						+ mp3Tag.getFilename() + " ════════════════════════ ");

				String id3v2Tag_artist = id3v2Tag.getArtist() != null ? id3v2Tag.getArtist().trim() : null;
				String id3v2Tag_songname = id3v2Tag.getTitle() != null ? id3v2Tag.getTitle().trim() : null;
				String id3v2Tag_comment = id3v2Tag.getComment() != null ? id3v2Tag.getComment().trim() : null;

				if (id3v2Tag_comment != null) {
					id3v2Tag_comment = clearLuanMaForCommantTag(id3v2Tag_comment.getBytes());
				}

				byte[] id3v3Comment_ByteArr = null;
				if (id3v2Tag_comment != null) {
					id3v3Comment_ByteArr = id3v2Tag_comment.getBytes();
				}

				byte[] mp3Info_comment_ByteArr = null;
				if (mp3Info_comment != null) {
					mp3Info_comment_ByteArr = mp3Info_comment.getBytes();
				}

				System.out.println(
						"D3V2_Artist=[" + id3v2Tag.getArtist() + "]  " + "  Byte_Artist=[" + mp3Info_artist + "]");
				System.out.println(
						"D3V2_Title=[" + id3v2Tag.getTitle() + "]       Byte_Title=[" + mp3Info_songname + "]");
				System.out.println("D3V2_Comment=[" + id3v2Tag_comment + "]_CharSet[" + getEncoding(id3v2Tag_comment)
						+ "]_Byte[" + showOneLineByteStr(id3v3Comment_ByteArr) + "]  Byte_Comment=[" + mp3Info_comment
						+ "]_ChatSet[" + getEncoding(mp3Info_comment) + "]_Byte["
						+ showOneLineByteStr(mp3Info_comment_ByteArr) + "]");
				System.out.println("TAG_Album___________:  " + id3v2Tag.getAlbum());
				System.out.println("TAG_Year____________:  " + id3v2Tag.getYear());
				System.out.println(
						"TAG_Genre___________:  " + id3v2Tag.getGenre() + "(" + id3v2Tag.getGenreDescription() + ")");
				System.out.println("TAG_Track___________:  " + id3v2Tag.getTrack() + "");
				System.out.println("TAG_Lyrics__________:  " + id3v2Tag.getLyrics());
				System.out.println("TAG_Composer________:  " + id3v2Tag.getComposer());
				System.out.println("TAG_Publisher_______:  " + id3v2Tag.getPublisher());
				System.out.println("TAG_Original_artist_:  " + id3v2Tag.getOriginalArtist());
				System.out.println("TAG_Album_artist____:  " + id3v2Tag.getAlbumArtist());
				System.out.println("TAG_Copyright_______:  " + id3v2Tag.getCopyright());
				System.out.println("TAG_URL_____________:  " + id3v2Tag.getUrl());
				System.out.println("TAG_Encoder_________:  " + id3v2Tag.getEncoder());
				byte[] albumImageData = id3v2Tag.getAlbumImage();
				if (albumImageData != null) {
					System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
					System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			System.out.println(mp3Tag.getFilename() + " 不存在 D3V2 Tag");
			System.out.println("════════════ MP3_Index[" + index + "] All_Size【" + allSize + "】 不存在 ID3v2 TAG !!!!");
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

	public static Comparator fileSizeCompara = new Comparator<File>() {
		@Override
		public int compare(File o1, File o2) {
			if(o1.length() > o2.length()) {
				return -1;
			}
			
			if(o1.length() == o2.length()) {
			  return 0 ; 	
			 }
			
			return 1;
			
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
				String typeStr = entry.getKey(); // Map的Value
				ArrayList<File> fileArr = entry.getValue(); // Map的Value
				String fixedType = getFixedType(typeStr);
				System.out.println("================" + fixedType + "Begin================");
				String mdHex = "";
				for (int i = 0; i < fileArr.size(); i++) {
					File curFile = fileArr.get(i);
					String Path = curFile.getAbsolutePath();
					try {

						mdHex = getMD5Three(Path);
					} catch (Exception e) {

					}

					int index2 = i + 1;
					int md5_Length = mdHex.length();
					boolean isPaddingEmpty = md5_Length == 31 ? true : false;
					String fixedIndex = getFixedIndex(index2);
					System.out.println(" 【index : " + fixedIndex + "】   【 MD5(" + md5_Length + "):  "
							+ (isPaddingEmpty ? " " : "") + mdHex + " 】 "+"Size["+getPaddingIntString((int)curFile.length(),12," ",true)+"] " + Path);
				}
				System.out.println("================" + fixedType + "End================");
			}
		}
	}

	
	static String getPaddingString(String rawStr, int padinglength, String oneStr, boolean dirPre) {
		String result = "" + rawStr;
		int length = ("" + rawStr).length();

		if (length < padinglength) {
			int distance = padinglength - length;
			for (int i = 0; i < distance; i++) {
				if (dirPre) {
					result = oneStr + result;
				} else {
					result = result + oneStr;
				}

			}

		}
		return result;

	}
	
	
	static String getPaddingIntString(int index, int padinglength, String oneStr, boolean dirPre) {
		String result = "" + index;
		int length = ("" + index).length();

		if (length < padinglength) {
			int distance = padinglength - length;
			for (int i = 0; i < distance; i++) {
				if (dirPre) {
					result = oneStr + result;
				} else {
					result = result + oneStr;
				}

			}

		}
		return result;

	}
	
	
	static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (byte aSrc : src) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(aSrc & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
//        System.out.println(builder.toString());
		return builder.toString();
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
		} else if (i >= 100 && i < 1000) { // 999
			rtnStr = "000" + i;
		} else if (i >= 1000 && i < 10000) { // 9999
			rtnStr = "00" + i;
		} else if (i >= 10000 && i < 100000) { // 99999
			rtnStr = "0" + i;
		} else {
			rtnStr = "" + i;
		}

		return rtnStr;
	}

	
	
	public static void showAllTip() {
		
		
		for (String infoItem : summaryTipList) {
			System.out.println(infoItem);
		}
		
		System.out.println();
		System.out.println();

		for (String infoItem : dirFileTipList) {
			System.out.println(infoItem);
		}
		
		System.out.println();
		System.out.println();

		
		for (String infoItem : realFileTipList ) {
			System.out.println(infoItem);
		}
		
		System.out.println();
		System.out.println();

		for (String infoItem : fileTypeTipList) {
		System.out.println(infoItem);
	    }

		System.out.println();
		
		for (String infoItem : summaryTipList) {
			System.out.println(infoItem);
		}
		
		
		
	}
	@SuppressWarnings("unchecked")
	public static void showMapSummaryDataStyle2() {
		int fileSum = 0;
		System.out.println();
		System.out.println();
		Map.Entry<String, ArrayList<File>> entry;
		if (arrFileMap != null) {
			Iterator iterator = arrFileMap.entrySet().iterator();
			while (iterator.hasNext()) {
				entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
				String typeStr = entry.getKey(); // Map的Value
				ArrayList<File> fileArr = entry.getValue(); // Map的Value
				
			    long mSameTypeFileSize = 0 ;
			    if(fileArr != null && fileArr.size() > 0 ) {
			    	
			    	for (int i = 0; i < fileArr.size(); i++) {
			    		mSameTypeFileSize  +=  ( fileArr.get(i) == null ? 0 : fileArr.get(i).length());
					}
			    }
				int curFileSize = fileArr.size();
				fileSum = fileSum + curFileSize;
				// System.out.println("文件类型:" + get15FixedType(typeStr) + " 匹配文件个数:" +
				// fileArr.size());
				
				fileTypeTipList.add("文件类型:" + get15FixedType(typeStr) + "  匹配文件个数:" + get15FixedType(""+curFileSize) +"类型文件大小:"+get15FixedType(""+(getPaddingString(getFileSizeMBString(mSameTypeFileSize), 9, " ", true)))+typeStr);
			
			
			}
		}
		fileTypeTipList.sort(strCompara);
//		for (String infoItem : fileTypeTipList) {
//			System.out.println(infoItem);
//		}
//		System.out.println("文件夹总数:" + allSimpleFileSet.size() +"           匹配文件类型总数:"+fileTypeTipList.size());
//		System.out.println("文件总数:" + fileSum);
		summaryTipList.add("实体文件总数:[" + getPaddingIntString(fileSum,6," ",true)+"   ]");
		
		
		summaryTipList.add("文件类型总数:["+getPaddingIntString(fileTypeTipList.size(),6," ",true)+"   ]"  );


	}

	@SuppressWarnings("unchecked")
	public static void showMapSummaryData() {
		Map.Entry<String, ArrayList<File>> entry;
		if (arrFileMap != null) {
			Iterator iterator = arrFileMap.entrySet().iterator();
			while (iterator.hasNext()) {
				entry = (Map.Entry<String, ArrayList<File>>) iterator.next();
				String typeStr = entry.getKey(); // Map的Value
				ArrayList<File> fileArr = entry.getValue(); // Map的Value
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
				// 返回匹配到的类型 作为map的key 没有匹配到 就返回 null
				String matchTypeKey = CheckFileType(curFileName, typeList);
				if (matchTypeKey != null) {
					addItemFileToMap(matchTypeKey, curFile);
				}
			} else { // 类型和 名称都要检查
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

	// key = type value = 符合过滤文件规则的名称的文件的集合
	public static HashMap<String, ArrayList<File>> arrFileMap = new HashMap<String, ArrayList<File>>();

	// 当 Mode 为 SEARCH_MODE_NAME 时 typeStrList 为空 那么 我们初始化的时候 就以 空字符串 "" 为key
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
		int allFileCount = allSimpleFileSet.size();
		
		ArrayList<File> allFileArray = new ArrayList<File> ();
		allFileArray.addAll(allSimpleFileSet);
		
		allFileArray.sort(fileSizeCompara);
	
		for (File curFile : allFileArray) {
			String fileName = curFile.getName();
//			System.out.println("文件索引[ " + allFileCount+"_"+getPaddingIntString(index,6," ",false) + "]"+" Size[ " +getPaddingString(getFileSizeMBString(curFile.length()),9," ",true)+" ]"+"  路径: " + curFile.getAbsolutePath() );
			realFileTipList.add("文件索引[ " + allFileCount+"_"+getPaddingIntString(index,6," ",false) + "]"+" Size[ " +getPaddingString(getFileSizeMBString(curFile.length()),9," ",true)+" ]"+"  路径: " + curFile.getAbsolutePath() );
		
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
	
	
	// AllSize_[ 12.5GB ] [ 12833.2MB ]
	static ArrayList<String> summaryTipList = new ArrayList<String>();
	
	
	
//	Dir[ 111_4    ]   Size[ 1763.9MB      ]    D:\ScreenShot
//	Dir[ 111_5    ]   Size[ 1729.7MB      ]    D:\ScreenShot\PK\APK
	static ArrayList<String> dirFileTipList = new ArrayList<String>();
	
	
//	文件索引[ 1213_1     ] Size[  1661.7MB ]  路径: D:\ScreenShot\books-master.zip
//	文件索引[ 1213_2     ] Size[   299.0MB ]  路径: D:\ScreenShot\1\1.mp4
	static ArrayList<String> realFileTipList = new ArrayList<String>();
	
	
//	文件类型:.7z              匹配文件个数:1              类型文件大小:    9.5MB      .7z
//	文件类型:.apk             匹配文件个数:61             类型文件大小: 1734.8MB      .apk
//	文件类型:.bat             匹配文件个数:3              类型文件大小:    0.0MB      .bat
	static ArrayList<String> fileTypeTipList = new ArrayList<String>();
	
	
	
	static public HashMap<File,Long> dirFileSizeMap = new HashMap<File,Long> ();
	static void initSimpleFileSetDetail() {

		System.out.println();
		System.out.println();
		ArrayList<File> allDirFileArr = new ArrayList<File>();
		
		allDirFileArr.addAll(allDirFileSet);
	
		long allDirFileSize = 0 ; // 当前所有目录 所有文件的大小
		
		
		for (int i = 0; i < allDirFileArr.size(); i++) {
			
		File dirFile = allDirFileArr.get(i);
		if(dirFile.isFile()) {
			continue;
		}
		long dirLength = getDirectorySizeByte(dirFile);
			
		dirFileSizeMap.put(dirFile, dirLength);
		
		allDirFileSize += dirLength;
			
		}
		
	
		allDirFileArr.sort(new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				// TODO Auto-generated method stub
				Long o1_length = dirFileSizeMap.get(o1);
				Long o2_length = dirFileSizeMap.get(o2);
				if(o1_length > o2_length) {
					
					return -1;
				}
				if(o1_length < o2_length) {
					
					return 1;
				}
				return 0;
			}
		});
		int fileDirIndex = 1;
//		System.out.println("AllSize_"+"[ "+get15FixedType(""+(getPaddingString(getFileSizeGBString(allDirFileSize), 8, " ", true))).trim()+" ]"+" [ "+get15FixedType(""+(getPaddingString(getFileSizeMBString(allDirFileSize), 8, " ", true))).trim()+" ]");

//		System.out.println("文件夹总数:"+ dirFileSizeMap.size() );
//		System.out.println("文件总数:" );

		summaryTipList.add("AllSize_"+"[ "+get15FixedType(""+(getPaddingString(getFileSizeGBString(allDirFileSize), 8, " ", true))).trim()+" ]"+" [ "+get15FixedType(""+(getPaddingString(getFileSizeMBString(allDirFileSize), 8, " ", true))).trim()+" ]");
		summaryTipList.add("目录文件总数:["+ getPaddingIntString(dirFileSizeMap.size(),6," ",true)+"   ]");

		
		
		for (File dirFile : allDirFileArr) {
//			System.out.println("Dir[ " + allDirFileArr.size()+"_" +getPaddingIntString(fileDirIndex,5," ",false) + "]"+ "   " +"Size["+ get15FixedType(""+(getPaddingString(getFileSizeMBString(dirFileSizeMap.get(dirFile)), 9, " ", true)))+"]"+"    " + dirFile.getAbsolutePath());
		
			dirFileTipList.add("Dir[ " + allDirFileArr.size()+"_" +getPaddingIntString(fileDirIndex,5," ",false) + "]"+ "   " +"Size["+ get15FixedType(""+(getPaddingString(getFileSizeMBString(dirFileSizeMap.get(dirFile)), 9, " ", true)))+"]"+"    " + dirFile.getAbsolutePath());
		
			fileDirIndex++;
		}
		
		for (File dirFile : allDirFileSet) {
			File[] childFileList = dirFile.listFiles();
		if (childFileList != null && childFileList.length > 0) {
			for (int i = 0; i < childFileList.length; i++) {
				if (!childFileList[i].isDirectory()) {
					allSimpleFileSet.add(childFileList[i]);
				}
			}

		   }
		}
		
	}

	static int addAllFileDir(File dirFile) { // 添加所有的 文件夹对象
		if (dirFile != null && dirFile.isDirectory()) {
			allDirFileSet.add(dirFile);
			mSumDirNum++;
		}
		if (isEmptyDirFile(dirFile)) { // 如果是空的文件夹 返回它的内部文件夹数量是0
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
		ArrayList<File> dirFileList = null; // new ArrayList<File>();
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

	static void showExifInfo(ArrayList<File> fileList, String type) {
		if (fileList == null || fileList.size() == 0) {
			System.out.println("当前目录: " + curDirFile.getAbsolutePath() + " 不存在 文件类型" + type + "的文件!!");
			return;
		}

		for (int i = 0; i < fileList.size(); i++) {
			File mPictureFileItem = fileList.get(i);
			String mPictureName = mPictureFileItem.getName();
			System.out.println("_______ type=[" + type + "] index=[" + (i + 1) + "] name=[" + mPictureName
					+ "]  Exif Begin " + " _______");

			showExifInfo(mPictureFileItem.getAbsolutePath(), (i+1));

		}
	}

	/**
	 * 图片翻转时，计算图片翻转到正常显示需旋转角度
	 */
	/**
	 * 图片翻转时，计算图片翻转到正常显示需旋转角度
	 */
	static boolean showExifInfo(String fileName , int index) {

		boolean isPort = true;
		File file = new File(fileName);

		String mImageDescription_Utf8 = null;
		String mImageMake_Utf8 = null;
		String mImageModel_Utf8 = null;
		String mImageArtist_Utf8= null;
		String mImageCopyright_Utf8 = null;
		String mPhotoUserComment_Utf8 = null;
		int angel = 0;
		Metadata metadata;

		try {
	
			
			metadata = JpegMetadataReader.readMetadata(file);
			if(metadata == null) {
				return false; 
			}
		
			



			// zukgit_directory [Exif IFD0] - Orientation = Right side, top (Rotate 90 CW)
			for (Directory directory : metadata.getDirectories()) {
				for (Tag tag : directory.getTags()) {
					// 格式化输出[directory.getName()] - tag.getTagName() = tag.getDescription()
//					System.out.format("zukgit_directory  [%s] - %s = %s\n", directory.getName(), tag.getTagName(),tag.getDescription());

					if ("Exif IFD0".equals(directory.getName())) {
		

						String mImageDescription = directory.getString(ExifIFD0Directory.TAG_IMAGE_DESCRIPTION);
						
						if(mImageDescription != null ) {


				            mImageDescription_Utf8 = new String(mImageDescription.getBytes(),StandardCharsets.UTF_8);
							
	
						}

						
						String mImageMake = directory.getString(ExifIFD0Directory.TAG_MAKE);
						if(mImageMake != null )
						 mImageMake_Utf8 = new String(mImageMake.getBytes(),"utf-8");
						
						
						String mImageModel = directory.getString(ExifIFD0Directory.TAG_MODEL);
						if(mImageModel != null )
						mImageModel_Utf8 = new String(mImageModel.getBytes(),"utf-8");
						
						
						String mImageArtist = directory.getString(ExifIFD0Directory.TAG_ARTIST);
						if(mImageArtist != null )
						mImageArtist_Utf8 = new String(mImageArtist.getBytes(),"utf-8");
						
						
						
						String mImageCopyright = directory.getString(ExifIFD0Directory.TAG_COPYRIGHT);
						if(mImageCopyright != null )
						mImageCopyright_Utf8 = new String(mImageCopyright.getBytes(),"utf-8");
						
						
						
//						System.out.println("XXmImageDescription=["+mImageDescription+"]  Utf8["+mImageDescription_Utf8+"]");
//						System.out.println("XXmImageMake=["+mImageMake+"]  Utf8["+mImageMake_Utf8+"]");
//						System.out.println("XXmImageModel=["+mImageModel+"]  Utf8["+mImageModel_Utf8+"]");
//						System.out.println("XXmImageArtist=["+mImageArtist+"]  Utf8["+mImageArtist_Utf8+"]");
//						System.out.println("XXmImageCopyright=["+mImageCopyright+"]  Utf8["+mImageCopyright_Utf8+"]");

				
					}
					
					if ("Exif SubIFD".equals(directory.getName())) {

				if("User Comment".equals(tag.getTagName())) {	
					String mPhotoUserComment =  tag.getDescription();
//					System.out.println("AZ_User_Comment=["+tag.getDescription()+"]");	
					if(mPhotoUserComment != null )
					mPhotoUserComment_Utf8 = new String(mPhotoUserComment.getBytes(),"utf-8");
//					System.out.println("AZXXmPhotoUserComment=["+mPhotoUserComment+"]   mPhotoUserComment_Utf8=["+mPhotoUserComment_Utf8+"]" );	

						}
				
					}
				}

			}



		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String mImageDescription_Utf8 ;
//		String mImageMake_Utf8 ;
//		String mImageModel_Utf8 ;
//		String mImageArtist_Utf8;
//		String mImageCopyright_Utf8 ;
//		String mPhotoUserComment_Utf8;
		
		System.out.println("JpgIndex["+index+"] == "+"Artist["+mImageArtist_Utf8+"] Desc["+mImageDescription_Utf8+"] Make["+mImageMake_Utf8+"] Mode["+mImageModel_Utf8+"] Copyright["+mImageCopyright_Utf8+"] UserComment["+mPhotoUserComment_Utf8+"]");
//		System.out.println("图片旋转角度：" + angel);
		return isPort;
	}
	
	


	static String byteArrToString(byte[] byteArr) {
		InputStream inputStream = null;
		byte[] result = byteArr;
		StringBuilder sb = new StringBuilder();
		inputStream = new ByteArrayInputStream(result);
		InputStreamReader input;
		try {
//			input = new InputStreamReader(inputStream,"utf-8");
			input = new InputStreamReader(inputStream);
		BufferedReader bf = new BufferedReader(input);
		String line = null;

	
			while((line=bf.readLine()) != null){
				sb.append(line);
			}
		} catch (IOException e) {                                                                                    
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  sb.toString();
	}
	
	
    public static String getFileSizeGBString(long fileSize) {
    	double length = 0;

    	length = (double) ((double) fileSize / (1024*1024*1024));

    	String result = decimalFormatOne.format(length)+"GB";
//    	System.out.println("GGGGGBBBBresult="+result+"   length="+length+"   fileSize="+fileSize);

    	
        return result;
    }
    
    
    public static String getFileSizeMBString(long fileSize) {
    	double length = 0;

    	length = (double) ((double)  fileSize / (1024*1024));

    	String result = decimalFormatOne.format(length)+"MB";
    	
//    	System.out.println("result="+result+"   length="+length+"   fileSize="+fileSize);
    	
        return result;
    }
    
	
    public static double getFileSizeMBLong(long fileSize) {
    	double length = 0;

    	length = (double) ((double) fileSize / (1024*1024));

    	
    	
        return (long)length;
    }
    
	static  DecimalFormat decimalFormatOne = new DecimalFormat("#0.0");

    
    /**
     * This method gets you the total size in Mb of a given directory
     *
     * @param dir Directory
     * @return Total size in Mb (int)
     */
    public static double getDirectorySizeMb(File dir) {
        double length = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile())
                    length += (double)file.length() / (1024 * 1024);
                else
                    length += getDirectorySizeMb(file);
            }
        }

        return length;
    }

    
    public static long getDirectorySizeByte(File dir) {
        long length = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile())
                    length += file.length() ;
                else
                    length += getDirectorySizeMb(file);
            }
        }

        return length;
    }
    
    
    public static double getDirectorySizeMb(String path) {
        return getDirectorySizeMb(new File(path));
    }

    
    
}