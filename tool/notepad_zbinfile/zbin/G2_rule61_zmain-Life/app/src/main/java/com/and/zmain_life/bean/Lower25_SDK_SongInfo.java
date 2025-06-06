package com.and.zmain_life.bean;


import java.io.UnsupportedEncodingException;

public class Lower25_SDK_SongInfo {
    private final String TAG = "TAG"; // 文件头1-3

    private String songName; // 歌曲名4-33

    private String artist; // 歌手名34-63

    private String album; // 专辑名61-93

    private String year; // 年94-97

    private String comment; // 备注98-125

    private byte r1, r2, r3; // 三个保留位126，127，128

    private boolean valid; // 是否合法

    public transient String fileName; // 此歌曲对应的文件名,没有封装


    public Lower25_SDK_SongInfo(byte[] data) {
        if (data.length != 128) {
            throw new RuntimeException("数据长度不合法:" + data.length);
        }
        String tag = new String(data, 0, 3);
// 只有前三个字节是TAG才处理后面的字节
        android.util.Log.i("Lower25_SDK_SongInfo","tag  = "+ tag);
        if (tag.equalsIgnoreCase("TAG")) {
            valid = true;
            try {
                songName = new String(data, 3, 30,"gbk").trim();
                artist = new String(data, 33, 30,"gbk").trim();
                album = new String(data, 63, 30,"gbk").trim();
                year = new String(data, 93, 4,"gbk").trim();
                comment = new String(data, 97, 28,"gbk").trim();
//                comment =   clearLuanMaForCommantTag(comment.getBytes());
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

    public Lower25_SDK_SongInfo() {
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

    // MP3的Comment 的 Tag 每次读取 都读取到
//      D3V2_Comment=[?国语]_CharSet[UTF-8]_Byte[00111111 10111001 11111010 11010011 11101111 ]  Byte_Comment=[?国语]_ChatSet[GB2312]_Byte[00111111 10111001 11111010 11010011 111011
//   问号  ?国语    为了把问号 去掉  所以创建这个方法                                                                                                                                      11 ]

     String clearLuanMaForCommantTag(byte[] arr) {
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

    public  String showByte(byte byteData  ){
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
