package com.and.zmain_life.txt_edit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.and.zmain_life.R;
import com.and.zmain_life.activity.Video_FullScreen_Show_Activity;
import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.bean.GoodBadHolder;
import com.and.zmain_life.bean.StockHolder;
import com.and.zmain_life.fragment.Txt_TxtEdit_Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtAdapter extends RecyclerView.Adapter<TxtAdapter.MyHolder> {

    public List getList() {
        return mList;
    }

    public void setList(List mList) {
        this.mList = mList;
    }

    public   Txt_TxtEdit_Fragment  mFragment;

    private List mList;//数据源

    private Context mContext;//数据源

    private OnItemClickListener onItemClickListener;


    //这个地方的handler最好封装 防止内存泄漏
    // 100   跟新  Adapter
    Handler handler = null;

/*    Handler handler   =  new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    String filePath = msg.obj+"";
                    print("Douyin filePath  = "+ msg.obj+"");
                    notifyDataSetChanged();
                    File douyinFile = new File(filePath);
                    if(douyinFile.exists()){
                        showPathDialog(douyinFile);
                    }


                    break;
            }
        }
    };*/


    public TxtAdapter(Context pContext, List txtList, Txt_TxtEdit_Fragment xFragment){
        mContext = pContext;

        mList =  txtList;
        mFragment = xFragment;
        initHandle();

    }


    void initHandle(){

        // 100--- 刷新数据
        handler =  new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 100:   // douyin 下载
                        String filePath = msg.obj+"";
                        print("Douyin filePath  = "+ msg.obj+"");
                        notifyDataSetChanged();
                        File douyinFile = new File(filePath);
                        if(douyinFile.exists()){
                            showPathDialog(douyinFile);
                        }


                        break;


                    case 101:   // git 下载
                        String filePath_git = msg.obj+"";
                        print("git 下载完成的路径 filePath  = "+ msg.obj+"");
                        notifyDataSetChanged();
                        File gitDownFile = new File(filePath_git);
                        if(gitDownFile.exists()){
                            showGitFileDialog(gitDownFile);
                        }


                        break;

                }
            }
        };


    }

    /**
     * 供外部调用设置监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 自定义的接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //将我们自定义的item布局R.layout.item_one转换为View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.txt_showtxt_item, parent, false);
        //将view传递给我们自定义的ViewHolder
        MyHolder holder = new MyHolder(view);
        //返回这个MyHolder实体
        holder.linestr_edittext.clearFocus();
        return holder;

    }


    int getFutureDayFlag(int dayFlagInt , int DaySpace){
        int curDay =  dayFlagInt;
        int futureDay = dayFlagInt;
        String daysDesc = dayFlagInt+"";
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
        try {
            Date nowDate = null;
            try {
                nowDate = simple.parse(daysDesc);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar curCalendar =Calendar.getInstance();
            curCalendar.setTime(nowDate);
            int curDayYear =   curCalendar.get(Calendar.DAY_OF_YEAR);
            int newDay2Year = curDayYear + DaySpace;
            curCalendar.set(Calendar.DAY_OF_YEAR,newDay2Year);

            int year = curCalendar.get(Calendar.YEAR);
            int month = curCalendar.get(Calendar.MONTH)+1;
            int day2month = curCalendar.get(Calendar.DAY_OF_MONTH);
            String monthDesc = month>=10?month+"":"0"+month;
            String dayDesc = day2month>=10?day2month+"":"0"+day2month;

            String dayIntFalg = year+""+monthDesc+dayDesc;
            futureDay = Integer.parseInt(dayIntFalg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return futureDay;
    }


    //通过方法提供的ViewHolder，将数据绑定到ViewHolder中
    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.linestr_edittext.setText(mList.get(position).toString());
        holder.linenum_textView.setText((position+1)+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, holder.getAdapterPosition() + 1);
                }
            }
        });

        holder.delete_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mList.remove(position);
                DataHolder.sdcard_1_txt_contentList.remove(position);
                DataHolder.writeContentToFile(DataHolder.sdcard_1_txt_file,DataHolder.sdcard_1_txt_contentList);
                setList(DataHolder.sdcard_1_txt_contentList);
                notifyDataSetChanged();
            }
        });




        // 执行按钮执行的方法
        holder.run_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                mList.remove(position);
//                DataHolder.sdcard_1_txt_contentList.remove(position);
//                DataHolder.writeContentToFile(DataHolder.sdcard_1_txt_file,DataHolder.sdcard_1_txt_contentList);
//                setList(DataHolder.sdcard_1_txt_contentList);
//                notifyDataSetChanged();

                // 检测当前的输入是否是一个路径   如果是一个路径  那么弹框 提示 是否删除
                String rawContent =  holder.linestr_edittext.getText().toString().trim();
                String content =    holder.linestr_edittext.getText().toString().trim();


                if(rawContent.contains("【") && rawContent.contains("】")  &&
                        rawContent.indexOf("【") < rawContent.indexOf("】")
                ){


                    String tipNum  =     rawContent.substring(rawContent.lastIndexOf("【"),rawContent.lastIndexOf("】")+1);  // 去除最后 包含的 【  ， 原因 有些路径本身包含 【】

                    System.out.println("tipNum = "+ tipNum);
                    content = content.replace(tipNum,"");

                }


                System.out.println("content 真实命令 = "+ content);

                boolean isZcmd =    isZcmdLine(content);
                boolean isAnd_File_Operation =    isZandFileLine(content);
                boolean isAnd_Bash_Operation =    isAndBashLine(content);   // 是否是 安卓自身 bash 执行的命令
                boolean isGitDown =    isGitDown(content);  //  是否是去 git xia
                boolean isGitJsonDailyDown =    isGitJsonDailyDown(content);  //  是否是去 下载 json_daily down day_2019_0115.json


                boolean isEmailOperation =    isEmailLine(content);  //  是否是去 git xia


                if(isEmailOperation){
                    System.out.println("执行发送 Email的命令  真实命令 = "+ content);
                    ShowSendEmailDialog(rawContent);

                    return ;
                }




                String mMatchDouinUrl = isGetContainDouyinUrl(content);
                System.out.println("isZcmd="+isZcmd+"  isZand="+isAnd_File_Operation+"  isGitDown="+isGitDown+"  isGitJsonDailyDown="+isGitJsonDailyDown+"   rawContent="+rawContent+"   content 真实命令 = "+ content+"   mMatchDouinUrl="+mMatchDouinUrl);
                if(isAnd_File_Operation){
                    String operationType = getZandOperationType(content);
                    if(operationType == null){
                        Toast.makeText(mContext,"当前zand_xxx执行类型为空",Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    ArrayList<String> operationList = calculOperationListFromZcmd(content,operationType);
                    ArrayList<String> inputParamList = calculParamListFromZcmd(content,operationType);

                    print("解析出的操作集合");
                    showParamList(content,operationList);
                    print("解析出的参数集合");
                    showParamList(content,inputParamList);
                    zand_showAlterDialog(content,operationType,operationList,inputParamList);
                } else if(isGitJsonDailyDown){

                    String daily_down_line =    content.replace("zgit_jsondailydown_","");


                    print("daily_down_line = "+ daily_down_line);
                    String[] timeArr = daily_down_line.split("_");
                    if(timeArr == null || timeArr.length != 2){
                        Toast.makeText(mContext,"【"+daily_down_line+"】\n执行下载 day_xxxx_xxxx.json Git文件失败:\n"+"",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String beginDayStr = timeArr[0];
                    String endDayStr = timeArr[1];
                    int beginFlagInt = Integer.parseInt(beginDayStr);
                    int endFlagInt = Integer.parseInt(endDayStr);

                   int daySpace = endFlagInt - beginFlagInt;
                    print("daily_down_line = "+ daily_down_line +"  daySpace="+daySpace);
                   ArrayList<Integer> allMatchDayIntList = new ArrayList<Integer>();
                    for (int i = 0; i < daySpace; i++) {
                      int mDayInt_item =    getFutureDayFlag(beginFlagInt,i);

                      if(mDayInt_item == endFlagInt){
                          break;
                      }


                      if(!allMatchDayIntList.contains(mDayInt_item) && !StockHolder.isWeekEnd(mDayInt_item) ){
                          allMatchDayIntList.add(mDayInt_item);
                      }

                    }


                    print("daily_down_line = "+ daily_down_line +"  daySpace="+daySpace +" allMatchDayIntList.size() = "+ allMatchDayIntList.size());



                    if(allMatchDayIntList.size() <= 0 ){
                        Toast.makeText(mContext,"【"+daily_down_line+"】\n执行下载 day_xxxx_xxxx.json Git文件失败:\n allMatchDayIntList.size()="+allMatchDayIntList.size()+"",Toast.LENGTH_SHORT).show();

                        return ;
                    }



                        for (int i = 0; i < allMatchDayIntList.size(); i++) {
                            int matchDayIntFlag = allMatchDayIntList.get(i);
                            print("daily_down_line day["+i+"_"+allMatchDayIntList.size()+" = "+matchDayIntFlag );
                            String YearStr = (""+matchDayIntFlag).substring(0,4);
                            String MonthStr = (""+matchDayIntFlag).substring(4,6);
                            String DayStr = (""+matchDayIntFlag).substring(6);
                            String matchJsonName = "day_"+YearStr+"_"+MonthStr+DayStr+".json";

                        }
                    if(StockHolder.day_timestamp_jsonFileArr == null ){
                        Toast.makeText(mContext,"【"+daily_down_line+"】\n执行下载 day_xxxx_xxxx.json Git文件失败:\n  读取/sdcard/zmain/stock/的day_xxxx_xxxx.json还未初始化完成! 请等待!"+allMatchDayIntList.size()+"",Toast.LENGTH_SHORT).show();
                        return ;
                    }

                    ArrayList<Integer> needDownloadIntFlag = new    ArrayList<Integer>();



                    for (int i = 0; i < allMatchDayIntList.size(); i++) {
                        int matchDayIntFlag = allMatchDayIntList.get(i);
                        print("daily_down_line day["+i+"_"+allMatchDayIntList.size()+" = "+matchDayIntFlag );
                        String YearStr = (""+matchDayIntFlag).substring(0,4);
                        String MonthStr = (""+matchDayIntFlag).substring(4,6);
                        String DayStr = (""+matchDayIntFlag).substring(6);
                        String matchJsonName = "day_"+YearStr+"_"+MonthStr+DayStr+".json";

                        File curJsonExistFile =   StockHolder.calculateSelectedJsonFile(matchJsonName);
                        if(curJsonExistFile == null){

                            File  downDailyJsonFile = new File(StockHolder.mStock_Root_File+File.separator+matchJsonName);
                            needDownloadIntFlag.add(matchDayIntFlag);
                        }

                    }


                    print("daily_down_line  NeedDownload_JsonFile_Size="+ needDownloadIntFlag.size() );

                    if(needDownloadIntFlag.size() == 0){
                        Toast.makeText(mContext,"【"+daily_down_line+"】对应的 daily_json 文件已经全部下载到本地! 需要下载json的数量为0! ",Toast.LENGTH_SHORT).show();

                        return ;
                    }







                    for (int i = 0; i <  needDownloadIntFlag.size(); i++) {

                        int dayIntFlag = needDownloadIntFlag.get(i);
                        print("daily_down_line  "+ "【"+daily_down_line+"】\n执行下载 "+dayIntFlag+" daily_json文件!:\n  needDownloadIntFlag ["+i+"]["+needDownloadIntFlag.size()+"]" );

                        Executor singleThreadExecutor = Executors.newSingleThreadExecutor();
                        final int count = i ;
                        singleThreadExecutor.execute(new Runnable() {
                            @Override
                            public void run() {

                            boolean  downloadFlag =     StockHolder.Download_Git_JsonFile_With_IntFlag(dayIntFlag);

                            if(downloadFlag){
                                mFragment.getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext,"【"+daily_down_line+"】\n执行下载成功! "+dayIntFlag+" daily_json文件!:\n  ["+count+"]["+needDownloadIntFlag.size()+"]",Toast.LENGTH_SHORT).show();

                                    }
                                });

                                if(StockHolder.mStock_Root_File.exists()){
                                    StockHolder.initDayTimeStamp_JSON_WithStockDir(StockHolder.mStock_Root_File.listFiles());

                                }
                            }else{

                                mFragment.getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext,"【"+daily_down_line+"】\n执行下载失败! "+dayIntFlag+" daily_json文件!:\n  ["+count+"]["+needDownloadIntFlag.size()+"]",Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }

                            }
                        });




                        mFragment.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext,"【"+daily_down_line+"】\n执行下载 "+dayIntFlag+" daily_json文件!:\n  ["+count+"]["+needDownloadIntFlag.size()+"]",Toast.LENGTH_SHORT).show();

                            }
                        });

                    }









                }else if(isGitDown){

                    File gitFilePath  = getPathFromContent(rawContent);
                    String gitFilePathString  = getPathStringFromContent(rawContent);
                    File gitFilePathString_File = null;
                    if(gitFilePathString != null){
                        gitFilePathString_File = new File(gitFilePathString);
                    }


                    if(gitFilePath ==null && rawContent.contains("【") && rawContent.contains("】")  &&
                            rawContent.indexOf("【") < rawContent.indexOf("】")
                    ){
                        String tipNum  =     rawContent.substring(rawContent.indexOf("【"),rawContent.lastIndexOf("】")+1);

                        System.out.println("tipNum = "+ tipNum);
                        content = content.replace(tipNum,"");

                    }

                    String mGitFileName  = isGetContainGitUrl(content);


                    print("isGitDown = "+isGitDown+"    gitFilePath="+gitFilePath +"  gitFilePathString="+gitFilePathString+"  mGitFileName="+mGitFileName +"  content="+content);

                    String mFileName = getTimeStampLong()+"";
                    mFileName = mGitFileName;


                    if(mGitFileName.lastIndexOf("/") > 0){
                        mFileName = mGitFileName.substring(mGitFileName.lastIndexOf("/")).replace("/","");

                    }



                    File gitFile = new File(DataHolder.Sdcard_File+ File.separator+"1"+File.separator+mFileName);




                    //    不管  当前 xlsx是不是为空  都去下载
                    if(!mGitFileName.contains("】")  ){



                        // 执行下载操作！
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Looper.prepare();

                                // 下载 Git 文件 返回 File 文件

                                mFragment.getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext,"正在尝试下载 Git文件【"+mGitFileName+"】",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            boolean downGitFlag =      StockHolder.Download_Git_With_Prefix_Path(mGitFileName,gitFile);
                                if(downGitFlag && gitFile != null && gitFile.exists() && gitFile.length() > 100){
//                                     Toast.makeText(mContext,"【"+content+"】\n执行下载抖音视频成功position["+position+"]\n"+""+resultFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                                    // 在当前 Text  中 加入 路径
//                                    String oldText =     mList.get(position)+"";
//                                    oldText = oldText.replace("【path:"+gitFile.getAbsolutePath()+"】","");
//
//                                    String newText = oldText+"【path:"+gitFile.getAbsolutePath()+"】";
//                                    DataHolder.sdcard_1_txt_contentList.set(position,newText);
//                                    DataHolder.writeContentToFile(DataHolder.sdcard_1_txt_file,DataHolder.sdcard_1_txt_contentList);
//                                    setList(DataHolder.sdcard_1_txt_contentList);

//                                 notifyDataSetChanged();   //   只有在 主线程  更新UI  否则报错
                                    Message message = handler.obtainMessage();
                                    message.what = 101;
                                    message.obj = ""+gitFile.getAbsolutePath();
                                    handler.sendMessage(message);

                                }else{
                                    Toast.makeText(mContext,"【"+rawContent+"】\n执行下载Git文件【"+mGitFileName+"】失败:\n"+""+gitFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();

                                }

                                Looper.loop();
                            }
                        }).start();

                    } else if(gitFilePath != null && gitFilePath.exists() && gitFilePath.length() > 0 ){

//                        String url = gitFilePath.getAbsolutePath();
//                        Intent intent = new Intent();
//                        intent.setAction(Intent.ACTION_VIEW);
//                        Uri uri = Uri.parse(url);
//                        intent.setData(uri);
//                        mContext.startActivity(intent);

                        showGitFileDialog(gitFilePath);

                    } else {

                        if(gitFilePath == null && gitFilePathString_File == null){
                            Toast.makeText(mContext,"【"+content+"】\n无法执行 参数不对!:\n"+""+gitFile,Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(mContext,"【"+gitFilePathString+"】\n文件为空-无法打开:\n"+""+gitFile,Toast.LENGTH_SHORT).show();

                        }

                    }


                }  else if(isAnd_Bash_Operation){   // 是否是 zand_bash_ 命令


                    String rawBashCmd = content.replace("zand_bash_@","").trim();



                      String zandResult = zand_bash_operation(rawBashCmd);
                      if(zandResult != null && zandResult.length() > 0 ){
                          Toast.makeText(mContext,"当前命令【"+rawBashCmd+"】返回长度为:"+zandResult.length() +"\n"+"以复制到剪切板!",Toast.LENGTH_SHORT).show();

                          if(mFragment != null){
                              mFragment.setClipContent(zandResult);
                          }



                      } else{
                          Toast.makeText(mContext,"当前命令【"+rawBashCmd+"】执行失败返回长度为0 请检查!",Toast.LENGTH_SHORT).show();


                      }


                } else {

                 if(mMatchDouinUrl != null){


                     // 有 抖音 链接   还要查看 这个 是否 有 下载 标识 【path:】
                     File sdpathFile  = getPathFromContent(rawContent);
                     if(sdpathFile != null && sdpathFile.exists()){
                         //  存在 这个 抖音 下载到 本地的文件  那么 dialog  提示 播放
                         showPathDialog(sdpathFile);

                     }else{



                         // SD卡上 三方 APP 已经 没有权限 操作 了  会发生  open failed: EACCES (Permission denied)
                         // 所以  这里 只能写入到 内置的  /sdcard 文件中了
                         //
//                         File targetDirFile = new File(DataHolder.ZMain_File.getAbsolutePath()+File.separator+"mp4_scene_port");

                         // port
                         File targetDirFile_mp4_scene_port = new File(DataHolder.Emulated_0_ZMain_File+""+File.separator+"mp4_scene_port"+File.separator+"douyin_download_port");

                         // land
                         File targetDirFile_mp4_scene_land = new File(DataHolder.Emulated_0_ZMain_File+""+File.separator+"mp4_scene_land"+File.separator+"douyin_download_land");



                         Toast.makeText(mContext,"【"+content+"】\n执行下载抖音视频操作路径如下:\n"+""+targetDirFile_mp4_scene_port.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                         // 视频下载目录

                         // 执行下载操作！
                         new Thread(new Runnable() {
                             @Override
                             public void run() {
                                 Looper.prepare();

                                 // 下载 抖音 视频 返回 File 文件
                                 File resultFile =     DataHolder.douYinParseUrl(mMatchDouinUrl,targetDirFile_mp4_scene_port,targetDirFile_mp4_scene_land);
                                 if(resultFile != null && resultFile.exists()){
//                                     Toast.makeText(mContext,"【"+content+"】\n执行下载抖音视频成功position["+position+"]\n"+""+resultFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                                     // 在当前 Text  中 加入 路径
                                     String oldText =     mList.get(position)+"";
                                     oldText = oldText.replace("【path:"+resultFile.getAbsolutePath()+"】","");

                                     String newText = oldText+"【path:"+resultFile.getAbsolutePath()+"】";
                                     DataHolder.sdcard_1_txt_contentList.set(position,newText);
                                     DataHolder.writeContentToFile(DataHolder.sdcard_1_txt_file,DataHolder.sdcard_1_txt_contentList);
                                     setList(DataHolder.sdcard_1_txt_contentList);

//                                 notifyDataSetChanged();   //   只有在 主线程  更新UI  否则报错
                                     Message message = handler.obtainMessage();
                                     message.what = 100;
                                     message.obj = ""+resultFile.getAbsolutePath();
                                     handler.sendMessage(message);

                                     if(resultFile.getAbsolutePath().toLowerCase().contains("land")){  // 更新下载的 mp4_scene_land
                                         DataHolder.init_Mp4_Scene_Land();
                                     }else{
                                         DataHolder.init_Mp4_Scene_Port();  // 更新下载的 mp4_scene_port 文件
                                     }




                                 }else{
                                     Toast.makeText(mContext,"【"+rawContent+"】\n执行下载抖音视频失败:\n"+""+resultFile,Toast.LENGTH_SHORT).show();

                                 }

                                 Looper.loop();
                             }
                         }).start();







                     }









                 } else     if(isZcmd){
                        Toast.makeText(mContext,"【"+content+"】\nPC(CMD)命令非And命令",Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(mContext,"【"+content+"】\n无执行逻辑",Toast.LENGTH_SHORT).show();

                    }




                }
/*                File curFile = new File(content);
                if(curFile.exists()){
//                    Dialog mDialog = new Dialog(new new);
                    showAlterDialog(curFile);
                }else if(content.startsWith("/storage/emulated/0/")){
                    Toast.makeText(mContext,"【"+content+"】\n该文件已删除",Toast.LENGTH_SHORT).show();
                } else {
//                    mContext
                Toast.makeText(mContext,"【"+content+"】\n无执行逻辑",Toast.LENGTH_SHORT).show();
                }*/

            }
        });


        holder.linenum_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mList.remove(position);
                String content =    holder.linestr_edittext.getText().toString();

                if(mFragment != null){
                    mFragment.setClipContent(content);
                }
                Log.i("linenum_textView_click"," content = "+content+"     (mFragment== "+(mFragment != null));

//                DataHolder.sdcard_1_txt_contentList.remove(position);
//                DataHolder.writeContentToFile(DataHolder.sdcard_1_txt_file,DataHolder.sdcard_1_txt_contentList);
//                setList(DataHolder.sdcard_1_txt_contentList);
//                notifyDataSetChanged();
            }
        });

        holder.linestr_edittext.clearFocus();


    }


    String      zand_sh_allprop(){


       String result =  getPropName(null);

       if(result == null || "".equals(result)){
           print("zand_sh_allprop 无输出结果! ");
       }else{
           print("zand_sh_allprop 有正确输出结果:");
           print(result);
       }

       return result;
    }


    public  String getPropName(String propname) {
        String propName = propname;
        String line;
        BufferedReader input = null;
        StringBuilder rsultSB = new StringBuilder();
        try {

            Process process = null ;
            String command = null ;
            if(propname == null || "".equals(propname)){
                command = " getprop " ;
            } else{

                //  grep 在 exe 下面没用 那么 就 执行全部 过滤出符合 条件的 prop
                // command =" getprop " +" | grep  \""+ propName+"\"" ;

                command = " getprop " ;
            }
            print("getPropName  command = "+ command + "  propname="+propname);


            String[] command_list = { "sh", "-c", command };



            // 直接执行 command 会导致 grep   管道命令无法使用
            process = Runtime.getRuntime().exec(command_list);

            input = new BufferedReader(
                    new InputStreamReader(process.getInputStream()), 1024*1024*10);

            int prop_index = 0 ;
            while(true){
                line=input.readLine();
                if(line==null){
                    break;
                }


                // 如果为空 那么  不过滤
                if(propname == null || "".equals(propname)){

                    rsultSB.append(line+"\n");
                } else {

                    // 如果 prop 不为空  那么 需要 过滤关键字
                    if(line.trim().contains(propname.trim())){
                        rsultSB.append(line+"\n");
                    }

                    print("getPropName["+prop_index+"]  = "+  line);
                }

                prop_index++;

            }


            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();

            print("getPropName  返回空  无输出结果! ");
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        print("getPropName  返回正常输出结果!  result="+rsultSB.toString());
        return rsultSB.toString().trim();
    }



    String   zand_bash_operation(String realCmd){

        StringBuilder resultSB = new StringBuilder();
        switch (realCmd){
            case "allprop":
                resultSB.append(zand_sh_allprop());
                break;

            case "getxiaomisno":
                resultSB.append(getPropName("sno"));
                break;


            default:
                resultSB.append("没有找到 zand_bash_"+realCmd+" 对应的执行命令!");
//                Toast.makeText(mContext,"没有找到 zand_bash_"+realCmd+" 对应的执行命令!",Toast.LENGTH_SHORT).show();

        }

        return resultSB.toString();

    }

    void SendEmailOperation(String mSubject){


        try {
            print("A  SendEmailOperation  mSubject="+mSubject);
            DataHolder.sendemail("zmain_apk",null,mSubject,"txtAdapter_mailcmd_"+DataHolder.getTimeStampyyyyMMdd_HHmmss(),null,null,null,null);

            print("B SendEmailOperation  mSubject="+mSubject);


//            Toast.makeText(mContext,"发送邮件_"+mSubject+"完成!",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.fillInStackTrace();
            e.printStackTrace();

            print("C SendEmailOperation  e="+e.getMessage());
        }

//        sendemail(String PcName, String targetEMail, String mTitle, String inputContent,
//                ArrayList<String> paramTextList, ArrayList<String> extraTextList, HashMap<File, String> imageFile_Desc_Map,
//                ArrayList<File> attatchFileList)





    }

    void ShowSendEmailDialog(String mSubjectCommand){
        StringBuilder dialogTitle = new StringBuilder();   // 提示标题

        dialogTitle.append("发送_MailCmd_邮件:");
        StringBuilder dialogMessage = new StringBuilder();   // 提示文本
        dialogMessage.append("主题:"+mSubjectCommand+"");

        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(mContext);

        alterDiaglog.setTitle(dialogTitle.toString());//文字
        alterDiaglog.setMessage(dialogMessage.toString());//提示消息
        //积极的选择
        alterDiaglog.setNeutralButton("发送", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(mContext,"播放",Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SendEmailOperation(mSubjectCommand);
//                        Toast.makeText(mContext,"播放",Toast.LENGTH_SHORT).show();
                        print("A发送   SendEmailOperation  mSubject="+mSubjectCommand );


                        print("B发送   SendEmailOperation  mSubject="+mSubjectCommand );

                    }
                }).start();

                Toast.makeText(mContext,"已发送邮件_"+mSubjectCommand+"!",Toast.LENGTH_SHORT).show();

            }
        });

        //中立的选择
        alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(mContext,"播放",Toast.LENGTH_SHORT).show();


            }
        });

        alterDiaglog.show();


    }
    String  getPathStringFromContent(String rawContent){
        String matchFilePathString = null;
        String path = null ;
        if (rawContent.contains("【path:") && rawContent.contains("】")) {
            path    = rawContent.substring(rawContent.lastIndexOf("【path:")+"【path:".length());
            path = path.replace("】","");


            File pathFile = new File(path);
            print("getPathFromContent GitDown  getPathFromContent matchFile="+ matchFilePathString+"  rawContent="+ rawContent+"   path="+path);
            matchFilePathString = pathFile.getAbsolutePath();


        }
        print("GitDown  getPathStringFromContent matchFilePathString="+ matchFilePathString+"  rawContent="+ rawContent +"  path="+path);
        return matchFilePathString;
    }

    File  getPathFromContent(String rawContent){
        File matchFile = null;
        String path = null ;
        if (rawContent.contains("【path:") && rawContent.contains("】")) {
              path    = rawContent.substring(rawContent.lastIndexOf("【path:")+"【path:".length());
            path = path.replace("】","");


            File pathFile = new File(path);
            print("getPathFromContent GitDown  getPathFromContent matchFile="+ matchFile+"  rawContent="+ rawContent+"   path="+path);

            if(pathFile.exists()){

                matchFile = pathFile;
            }

        }
        print("GitDown  getPathFromContent matchFile="+ matchFile+"  rawContent="+ rawContent +"  path="+path);
            return matchFile;
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if(mList == null){
            return 0;
        }
        return mList.size();
    }

//
//    ArrayList<String> operationList = calculOperationListFromZcmd(content);
//    ArrayList<String> inputParamList = calculParamListFromZcmd(content);

//    zand_file_@【opt1】_@【opt2】_@【opt3】_x_@【arg1】_@【arg2】_@【arg3】  安卓上执行
//    zcmd_file_@【opt1】_@【opt2】_@【opt3】_x_@【arg1】_@【arg2】_@【arg3】  Pc上执行







    private boolean isImage(String filePath){
        boolean isImage = false;
        String lower_full_path = filePath.toLowerCase();
        if(lower_full_path.endsWith(".jpg") || lower_full_path.endsWith(".png")){
            isImage = true;

        }

        return isImage;
    }

//    zand_file_@【opt1】_@【opt2】_@【opt3】_x_@【arg1】_@【arg2】_@【arg3】
// zand_file_@delete1_x_@pathA_@pathB;  // 删除
// zand_file_@copy12_@copy13_x_@path1_@path2_@path3;  //  复制到 /sdcard/1
// zand_file_@copy1_@delete_x_@pathA_@pathB;  // 剪切
// zand_file_@good12_@delete1_x_#p_3pathA#pathB  // 解密到 /sdcard/1/中
// zand_file_@bad1_x_@path  // 加密到  /sdcard/1/中
// zand_file_@bad1_@path
// zand_command_【opt1】_【opt2】_【opt3】_x_【arg1】_【arg2】_【arg3】
// zand_command_【shutdown】



    // 只下载 Git 的 逻辑
    String isGetContainGitUrl(String txtLine){

        String matchContainDouyinUrl = null;
        String[] strArrRow = null;
        String fixStr = "";

//	        if(str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
//	                str.trim().startsWith("thunder:") ||   str.trim().startsWith("magnet::") ){


        if (txtLine != null) {
            fixStr = new String(txtLine);
            // http://xxxxxx/sahttp://  避免出现 http://http: 连着的情况 起码也要使得间隔一个空格
            fixStr = fixStr.replace("http:", " http:");
            fixStr = fixStr.replace("https:", " https:");
            fixStr = fixStr.replace("thunder:", " thunder:");
            fixStr = fixStr.replace("magnet:", " magnet:");

            // content 真实命令 = zgit_down_https://github.do/https://raw.githubusercontent.com/ZukGit/ActionDemo/main/J0_Data/2022_main_stock.xlsx
            fixStr = fixStr.replace("zgit_down_", ""); //
            fixStr = fixStr.replace(" ","").trim();

            matchContainDouyinUrl = fixStr;


        }






        return matchContainDouyinUrl;
    }




    // 只下载 抖音的 逻辑
      String isGetContainDouyinUrl(String txtLine){

        String matchContainDouyinUrl = null;
          String[] strArrRow = null;
          String fixStr = "";

//	        if(str.trim().startsWith("http:") || str.trim().startsWith("https:") ||
//	                str.trim().startsWith("thunder:") ||   str.trim().startsWith("magnet::") ){


          if (txtLine != null) {
              fixStr = new String(txtLine);
              // http://xxxxxx/sahttp://  避免出现 http://http: 连着的情况 起码也要使得间隔一个空格
              fixStr = fixStr.replace("http:", " http:");
              fixStr = fixStr.replace("https:", " https:");
              fixStr = fixStr.replace("thunder:", " thunder:");
              fixStr = fixStr.replace("magnet:", " magnet:");
              strArrRow = fixStr.split(" ");
          }

          if (strArrRow != null && strArrRow.length > 0) {

              for (int i = 0; i < strArrRow.length; i++) {
                  String mCurContent = strArrRow[i];
                  if (mCurContent == null || mCurContent.trim().equals("")) {
                      continue;
                  }


                  boolean isUrl = toJudgeUrl(mCurContent);
                  if(isUrl){   // 当前 url  已经 包含  所以   不再添加重复的
                      String matchUrl = clearChinese(mCurContent);

                      //  当前 只 支持 下载 抖音视频
                      if(matchUrl.contains("v.douyin.com") || matchUrl.contains("douyin")){

                          return matchUrl;
                      }



                  }

              }


          }

        return matchContainDouyinUrl;
    }


    public static String clearChinese(String lineContent) {
        String REGEX_CHINESE = "[\u4e00-\u9fa5]";
        if (lineContent == null || lineContent.trim().isEmpty()) {
            return null;
        }
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(lineContent);
        return mat.replaceAll(" ");
    }


    public  boolean toJudgeUrl(String str) {
        boolean isUrl = false;

        if (str.trim().toLowerCase().startsWith("http:") || str.toLowerCase().trim().startsWith("https:") ||
                str.toLowerCase().trim().startsWith("thunder:") || str.toLowerCase().trim().startsWith("magnet:")) {

            return true;
        }


        return isUrl;
    }


      //  zand_file_
    private  boolean isZandFileLine(String txtLine){
        boolean isZcmd = false;
        String trim_line = txtLine.trim();
        if(trim_line.startsWith("zand_file_")){

            isZcmd = true;
        }
        return isZcmd;
    }





    private  boolean isEmailLine(String txtLine){
        boolean isMailFlag = false;
        String trim_line = txtLine.trim();
        if(trim_line.startsWith("mailcmd_") || trim_line.startsWith("mailcmder_") ){

            isMailFlag = true;
        }
        return isMailFlag;
    }



    private  boolean isGitDown(String txtLine){
        boolean isGitDown = false;
        String trim_line = txtLine.trim();
        if(trim_line.startsWith("zgit_down_")){

            isGitDown = true;
        }
        return isGitDown;
    }

    private  boolean isGitJsonDailyDown(String txtLine){
        boolean isGitDown = false;
        String trim_line = txtLine.trim();
        if(trim_line.startsWith("zgit_jsondailydown_")){

            isGitDown = true;
        }
        return isGitDown;
    }




    private  boolean isAndBashLine(String txtLine){
        boolean isAndBashLine = false;
        String trim_line = txtLine.trim();
        if(trim_line.startsWith("zand_bash_")){

            isAndBashLine = true;
        }
        return isAndBashLine;
    }



    private  boolean isZcmdLine(String txtLine){
        boolean isZcmd = false;
        String trim_line = txtLine.trim();
        if(trim_line.startsWith("zcmd_")){

            isZcmd = true;
        }
        return isZcmd;
    }

/*

    private void zand_showAlterDialog( String rawCommandStr , String cmdType , ArrayList<String> operationList ,   ArrayList<String> paramList ){
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(mContext);

//        alterDiaglog.setIcon();
        alterDiaglog.setTitle("删除文件");//文字
        alterDiaglog.setMessage("将删除该路径的文件,请确认执行删除或取消返回");//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext,"取消",Toast.LENGTH_SHORT).show();
            }
        });
        //消极的选择
//        alterDiaglog.setNegativeButton("死亡", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this,"点击了死亡",Toast.LENGTH_SHORT).show();
//            }
//        });
        //中立的选择
        alterDiaglog.setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext,"删除文件【"+targetFile.getAbsolutePath()+"】",Toast.LENGTH_SHORT).show();
                targetFile.delete();
            }
        });

        //显示
        alterDiaglog.show();
    }
*/

    void print(String logStr){
        Log.i("ZTxtAdapter",logStr);
    }




    void showGitFileDialog(File targetFile){
        StringBuilder dialogTitle = new StringBuilder();   // 提示标题

        dialogTitle.append("播放Git下载:");
        StringBuilder dialogMessage = new StringBuilder();   // 提示文本
        dialogMessage.append(""+targetFile.getAbsolutePath()+"");

        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(mContext);

        alterDiaglog.setTitle(dialogTitle.toString());//文字
        alterDiaglog.setMessage(dialogMessage.toString());//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("打开", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(mContext,"播放",Toast.LENGTH_SHORT).show();
                String url = targetFile.getAbsolutePath();

                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_VIEW);

//                String type = "video/mp4";

                Uri uri = Uri.parse(url);
                intent.setDataAndType(uri,"application/vnd.ms-excel");

                mContext.startActivity(intent);

            }
        });

        //中立的选择
        alterDiaglog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext,"取消",Toast.LENGTH_SHORT).show();

            }
        });

        alterDiaglog.show();


    }


    void showPathDialog(File targetFile){
        StringBuilder dialogTitle = new StringBuilder();   // 提示标题

        dialogTitle.append("播放下载抖音文件:");
        StringBuilder dialogMessage = new StringBuilder();   // 提示文本
        dialogMessage.append(""+targetFile.getAbsolutePath()+"");

        print("打印当前 Douyin 下载完成 Dialog  试图打开这个 MP4 文件");
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(mContext);

        alterDiaglog.setTitle(dialogTitle.toString());//文字
        alterDiaglog.setMessage(dialogMessage.toString());//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("播放", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(mContext,"播放",Toast.LENGTH_SHORT).show();

                //  系统的 播放器 播放视频
/*                String url = targetFile.getAbsolutePath();
                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_VIEW);

                String type = "video/mp4";

                Uri uri = Uri.parse(url);
                intent.setDataAndType(uri,type);
                mContext.startActivity(intent);*/



                // 本地的 播放器 播放视频
                Intent local_activity_intent = new Intent(mContext, Video_FullScreen_Show_Activity.class);
                local_activity_intent.putExtra("path",targetFile.getAbsolutePath());
                mContext.startActivity(local_activity_intent);

            }
        });

        //中立的选择
        alterDiaglog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext,"取消",Toast.LENGTH_SHORT).show();

            }
        });

        alterDiaglog.show();


    }

    void zand_showAlterDialog( String rawCommandStr ,  String cmdType , ArrayList<String> operationList ,   ArrayList<String> paramList ){

        StringBuilder dialogTitle = new StringBuilder();   // 提示标题

        StringBuilder dialogMessage = new StringBuilder();   // 提示文本

        print("rawCommandStr = "+rawCommandStr+"   cmdType="+cmdType+"   operationList.size()="+operationList.size()+"   paramList.size()="+paramList.size());

        switch (cmdType.trim()) {
            case "file":
                for (int i = 0; i < operationList.size(); i++) {
                    String mFileCommand =  operationList.get(i).toLowerCase();
                    String rawCommond = clearNumber(mFileCommand).toLowerCase();
                    String paramStepFlag = mFileCommand.replace(rawCommond,"");
                    ArrayList<String> matchParamList = calStepIndexFromCommandItem(mFileCommand,paramStepFlag,paramList);

                    dialogTitle.append(rawCommond+"_");
                    dialogMessage.append(rawCommond+" ");
                    for (int j = 0; j < matchParamList.size() ; j++) {
                        dialogMessage.append("参["+(j+1)+"]"+"["+matchParamList.get(j)+"]");
                    }
                    dialogMessage.append("\n");
                    dialogMessage.append("\n");

                }
                dialogTitle.append("操作");



                final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(mContext);

//        alterDiaglog.setIcon();
                alterDiaglog.setTitle(dialogTitle.toString());//文字
                alterDiaglog.setMessage(dialogMessage.toString());//提示消息
                //积极的选择
                alterDiaglog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext,"取消",Toast.LENGTH_SHORT).show();
                    }
                });
                //消极的选择
//        alterDiaglog.setNegativeButton("死亡", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this,"点击了死亡",Toast.LENGTH_SHORT).show();
//            }
//        });
                //中立的选择
                alterDiaglog.setNeutralButton("执行", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext,"开始执行【"+dialogTitle.toString()+"】",Toast.LENGTH_SHORT).show();




                        for (int i = 0; i < operationList.size(); i++) {
                            String mFileCommand =  operationList.get(i);
                           String resultTip =  Zcmd_File_Operation(mFileCommand,paramList);
                            Toast.makeText(mContext,"执行【"+dialogTitle.toString()+"】结果: zukgit_md \n"+resultTip,Toast.LENGTH_LONG).show();

                        }


                    }
                });

                //显示
                alterDiaglog.show();


                break;

            case "command":
                Toast.makeText(mContext,"暂未实现【"+"zand_command_xxx"+"】命令逻辑",Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }


    }


    /**
     * 自定义的ViewHolder
     */
    class MyHolder extends RecyclerView.ViewHolder {

        EditText linestr_edittext;
        TextView linenum_textView;
        ImageView delete_imageview;
        ImageView run_imageview;

        public MyHolder(View itemView) {
            super(itemView);
            linestr_edittext = itemView.findViewById(R.id.line_str_edittext);

            linenum_textView = itemView.findViewById(R.id.line_num_text);
            delete_imageview = itemView.findViewById(R.id.line_delete_image);
            run_imageview =  itemView.findViewById(R.id.item_run_image);
            linestr_edittext.clearFocus();
        }
    }









    public     ArrayList<String>  calStepIndexFromCommandItem(String comondStr ,String stepIndexArrStr , ArrayList<String> paramsList){
        ArrayList<String> matchIndexParams = new ArrayList<String>();

        for (int i = 0; i < stepIndexArrStr.length(); i++) {
            String int1_9_str = stepIndexArrStr.charAt(i)+"";

            if(!"".equals(int1_9_str) && isNumber(int1_9_str)) {
                int indexParam = Integer.parseInt(int1_9_str) - 1;

                if(indexParam < 0 || indexParam >= paramsList.size()) {
                    print("输入的执行命令参数超过输入的参数 导致该命令["+comondStr+"]无法执行!! ");
                    return null;
                }
                matchIndexParams.add(paramsList.get(indexParam));


            }

        }


        return matchIndexParams;

    }

    public   void showParamList(String rawComment , ArrayList<String> paramsList){
        if(paramsList == null) {
            print("当前参数列表为空");
        }

        for (int i = 0; i < paramsList.size(); i++) {
            String mParam = paramsList.get(i);
            print("param["+i+"]"+"["+mParam+"]  ---> command["+rawComment+"]" );
        }


    }




    public    String   zand_file_baddirs_operation(String rawComment , ArrayList<String> paramsList) {
        StringBuilder resultSB = new StringBuilder();
        if (paramsList == null || paramsList.size() == 0) {
            print("_baddirs 命令执行失败--- 当前 baddirs 输入的参数个数为空");
            return "_baddirs 命令执行失败--- 当前 baddirs 输入的参数个数为空";
        }

        if( paramsList.size() != 2 ) {

            resultSB.append(" 命令执行失败--- 当前命令 baddirs 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[2] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcDirPath = paramsList.get(0);
        String dstDirPath = paramsList.get(1);

        File srcDirFile = new File(srcDirPath);
        File dstDirFile = new File(dstDirPath);



        if(!srcDirFile.exists() || srcDirFile.isFile()){
            resultSB.append("src目录["+srcDirPath+"] 不存在 或者 不是文件夹 请检查!");
            return resultSB.toString();
        }

        if(!dstDirFile.exists() || dstDirFile.isFile()){
            resultSB.append("dst目录["+dstDirPath+"] 不存在 或者 不是文件夹 请检查!");
            return resultSB.toString();
        }

        String real_srcdir_path = srcDirFile.getAbsolutePath();
        String real_dstdir_path = dstDirFile.getAbsolutePath();

        ArrayList<File>  all_srcdir_sub_file_arr =   getAllSubFile(srcDirPath,"*");

        if(all_srcdir_sub_file_arr == null || all_srcdir_sub_file_arr.size() == 0){

            resultSB.append("src目录["+srcDirPath+"] 中子文件数量为空! 请检查");
            return resultSB.toString();
        }


        int bad_file_count  = 0 ;
        for (int i = 0; i < all_srcdir_sub_file_arr.size(); i++) {
            File src_lin_file = all_srcdir_sub_file_arr.get(i);

            String src_file_md5_name =  DataHolder.getMD5Three(src_lin_file.getAbsolutePath());

            String src_file_origin_name = src_lin_file.getName();
            if(src_lin_file.isDirectory()){
                continue;
            }

            String src_abs_path = src_lin_file.getAbsolutePath();

            String dst_abs_path = src_abs_path.replace(real_srcdir_path,real_dstdir_path).replace(src_file_origin_name,src_file_md5_name);


            print("src["+i+"]["+all_srcdir_sub_file_arr.size()+"]  Src:"+ src_abs_path+"  Dst:"+dst_abs_path );

           File dst_abs_file =    new File(dst_abs_path);

            GoodBadHolder.create_BadEncry_File(src_lin_file,dst_abs_file);

            if(dst_abs_file.exists() && dst_abs_file.length() > 0 ){
                bad_file_count++;
            }
        }
        resultSB.append("bad文件数量["+bad_file_count+"]\nsrc目录["+real_srcdir_path+"] \ndst目录["+real_dstdir_path+"]");

        return resultSB.toString();

    }



        public    String   zand_file_lin_operation(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0) {
            print("lindirs 命令执行失败--- 当前lindirs输入的参数个数为空");
            return "lindirs 命令执行失败--- 当前lindirs输入的参数个数为空" ;
        }



        for (int i = 0; i < paramsList.size(); i++) {
            String lin_dir_path = paramsList.get(i);
            print("执行把 i["+(i+1)+"]["+paramsList.size()+"] ["+lin_dir_path+"] make的操作! ");
            File taretLinDirFile = new File(lin_dir_path);


            //   /sdcard/1/2.txt   判断是否是文件还是文件夹
            boolean isRealFile = isRealFile(lin_dir_path);

            if(isRealFile){
                resultSB.append("当前的参数 "+lin_dir_path+" 是一个文件  不是 lin 文件夹");
            }else{

                if(!taretLinDirFile.exists()){
                    taretLinDirFile.mkdirs();
                    resultSB.append("当前的参数 "+lin_dir_path+" 路径文件夹不存在 数据为空 请检查!");
                    break;
                }

                File[] linDirFileList = taretLinDirFile.listFiles();

                if(linDirFileList == null || linDirFileList.length == 0){
                    resultSB.append("当前的参数 "+lin_dir_path+" 路径文件夹无内容 请检查 数据为空 请检查!");
                    break;
                }

                ArrayList<File> jpg_realFile = new  ArrayList<File>();
//                ArrayList<File> gif_realFile = new  ArrayList<File>();
//                ArrayList<File> mp4_realFile = new  ArrayList<File>();

                int copyFileCount = 0 ;
                for (int j = 0; j < linDirFileList.length ; j++) {
                    File linItem = linDirFileList[j];

                    if(linItem.isDirectory()){
                        continue;
                    }

                   String realFileName =  linItem.getName().toLowerCase();

                    if(realFileName.endsWith(".jpg") || realFileName.endsWith(".jpeg")
                            || realFileName.endsWith(".png")){

                        jpg_realFile.add(linItem);
                    }
//
//                    if(realFileName.endsWith(".gif") ){
//
//                        gif_realFile.add(linItem);
//                    }
//
//                    if(realFileName.endsWith(".mp4")){
//                        jpg_realFile.add(linItem);
//                        mp4_realFile.add(linItem);
//                    }

                }

                resultSB.append("\n照片lin_jpg数量["+jpg_realFile.size()+"]\n");


                for (int j = 0; j < jpg_realFile.size() ; j++) {
                    File jpgFile = jpg_realFile.get(j);
                    // 获得 照片的宽高    jpg_lin_port   jpg_lin_land

                    String copyFilePath = null;
                    ExifInterface exifInterface = null;
                    int width = 0 ;
                    int height = 0 ;
                    try {
                        exifInterface = new ExifInterface(jpgFile.getAbsolutePath());

                        int rotation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                        width = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0);
                        height = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0);
                        if (rotation == ExifInterface.ORIENTATION_ROTATE_90 || rotation == ExifInterface.ORIENTATION_ROTATE_270) {

// 图片被旋转90或者270，使用时候将width、height换下
                            int temp = height;
                            height = width;
                            width = temp;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(height == 0 || width == 0 ){
                        continue;
                    }

                       String md5_str = DataHolder.getMD5Three(jpgFile.getAbsolutePath());

                    if(height >= width){
                        // jpg_lin_port
                        copyFilePath = jpgFile.getParentFile().getAbsolutePath()+File.separator+"jpg_lin_port"+File.separator+md5_str;
                    } else {
                        // jpg_lin_port
                        copyFilePath = jpgFile.getParentFile().getAbsolutePath()+File.separator+"jpg_lin_land"+File.separator+md5_str;

                    }


                    File copyFile = new File(copyFilePath);

                    fileCopy(jpgFile,copyFile);

                    if(copyFile.exists() && copyFile.length() > 0 ){

                        copyFileCount ++;
                    }

                    print("lintype_jpg["+j+"]["+jpg_realFile.size()+"] jpgFile="+jpgFile.getAbsolutePath()+"  md5_str="+md5_str+"  copyFilePath="+copyFilePath+" copyFile.exists="+copyFile.exists()+"  copyFile.length()="+copyFile.length());

                }

                resultSB.append("\n复制lin_jpg数量["+copyFileCount+"]\n");


            }
        }

        return resultSB.toString();
    }

    public    String   zand_file_make(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0) {
            print("zand_file_make 命令执行失败--- 当前make输入的参数个数为空");
            return "zand_file_make 命令执行失败--- 当前make输入的参数个数为空" ;
        }



        for (int i = 0; i < paramsList.size(); i++) {
            String deleteParamItem = paramsList.get(i);
            print("执行把 i["+(i+1)+"]["+paramsList.size()+"] ["+deleteParamItem+"] make的操作! ");
            File taretFile = new File(deleteParamItem);

            //   /sdcard/1/2.txt   判断是否是文件还是文件夹
            boolean isRealFile = isRealFile(deleteParamItem);

            if(isRealFile){
                try {
                boolean  isOKFile =    taretFile.createNewFile();
                if(isOKFile){
                    resultSB.append("成功创建_文件["+i+"] "+taretFile.getAbsolutePath()+"\n");
                }else{
                    resultSB.append("创建失败_文件["+i+"] "+taretFile.getAbsolutePath()+"\n");

                }

                } catch (IOException e) {
                    resultSB.append("创建失败_文件["+i+"] "+taretFile.getAbsolutePath()+"\n");
                    e.printStackTrace();
                }
            }else{
              boolean isOKDir =   taretFile.mkdirs();
                if(isOKDir){
                    resultSB.append("成功创建_文件夹["+i+"] "+taretFile.getAbsolutePath()+"\n");
                }else{
                    resultSB.append("创建失败_文件夹["+i+"] "+taretFile.getAbsolutePath()+"\n");

                }
            }
        }

        return resultSB.toString();
    }

    //   /sdcard/1/2.txt   判断是否是文件还是文件夹
    public boolean isRealFile(String mPath){
        boolean isrealFile = false;
        String[] pathArr = mPath.split("/");
        if(pathArr == null || pathArr.length == 0){
            return isrealFile;
        }
        String lastPart = pathArr[pathArr.length-1];
        if(lastPart.contains(".")){   //  最后的部分包含 . 那么就认为是实体文件
            isrealFile = true;
        }

        return isrealFile;
    }



    public    String  zand_file_renamenum(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 3 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);
        String intBeginStr = paramsList.get(2);
        int beginNum = 1;


        boolean beginflag = isNumber(intBeginStr);
        if(beginflag){
            beginNum = Integer.parseInt(intBeginStr);
        }
        int beginFrezz = beginNum;

        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        if(srcDir.isFile()){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量改名!");
            return resultSB.toString();
        }


        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                String newName = beginNum+type;
                tryReName(subFile,newName);
                beginNum++;
                print(" AtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                String newName = beginNum+type;
                tryReName(subFile,newName);
                print(" BtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
                beginNum++;
            }
        }

        int renameCount = beginNum - beginFrezz;
        if(renameCount > 0 ){
            resultSB.append("对于文件夹["+srcPath+"] 已执行 ["+renameCount+"] 次 rename操作! typeFliter=["+typeFliter+"]  beginInt["+beginFrezz+"]");
        }else {
            resultSB.append("对于文件夹["+srcPath+"] 未执行任何 rename操作! 该目录下的实体文件可能为0 请检查!");

        }

        return resultSB.toString();
        // Logic_____Begin

    }


    boolean tryReName(File curFile, String newName) {
        String newFilePath = curFile.getParent() + File.separator + newName;
        String oldName = curFile.getName();
        File newFile = new File(newFilePath);
        if (newFile.exists() && newFilePath.equals(curFile.getAbsolutePath())) {

//           newFilePath = curFile.getParent() + File.separator +"重复_"+newName;
//           newFile = new File(newFilePath);
            System.out.println("当前目录已存在重命名后的文件  文件名称:" + curFile.getName());
            return false; // 已经存在的文件不处理 直接返回

        }
        boolean flag = curFile.renameTo(newFile);
        if (flag) {
            System.out.println(oldName + " 转为 " + newFilePath + " 成功！");

        } else {
            System.out.println(oldName + " 转为 " + newFilePath + " 失败！");
        }
        return flag;
    }


    public  String getFileNameNoPoint(String originName) {
        String name = "";
        if (originName.contains(".")) {
            name = originName.substring(originName.lastIndexOf(".")).trim();
        } else {
            name = "";
        }
        return originName.replace(name, "");
    }





    public    String  zand_file_renameplace(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 4 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);
        String srcNameStr = paramsList.get(2);
        String dstNameStr = paramsList.get(3);


        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        if(srcDir.isFile()){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量 类型修改 ");
            return resultSB.toString();
        }
        int beginNum = 0;

        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                String newName = oldName.replace(srcNameStr,dstNameStr);
                tryReName(subFile,newName);
                beginNum++;
                print(" AtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                String newName = oldName.replace(srcNameStr,dstNameStr);
                tryReName(subFile,newName);
                print(" BtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
                beginNum++;
            }
        }


        if(beginNum > 0 ){
            resultSB.append("对于文件夹["+srcPath+"] 已执行 ["+beginNum+"] 次 renameplace 操作! typeFliter=["+typeFliter+"]  srcName["+srcNameStr+"] dstName["+dstNameStr+"]");
        }else {
            resultSB.append("对于文件夹["+srcPath+"] 未执行任何 renameplace 操作! 该目录下的实体文件可能为0 请检查!");

        }

        return resultSB.toString();
        // Logic_____Begin

    }

    public    String  zand_file_retype(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 3 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);
        String destType = paramsList.get(2);
        destType = destType.replace(".","");
        destType="."+destType;

        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        if(srcDir.isFile()){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量 类型修改 ");
            return resultSB.toString();
        }
        int beginNum = 0;

        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                String newName = oldNameNoType+destType;
                tryReName(subFile,newName);
                beginNum++;
                print(" AtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                String newName = oldNameNoType+destType;
                tryReName(subFile,newName);
                print(" BtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
                beginNum++;
            }
        }


        if(beginNum > 0 ){
            resultSB.append("对于文件夹["+srcPath+"] 已执行 ["+beginNum+"] 次 retype 操作! typeFliter=["+typeFliter+"]  dstType["+destType+"]");
        }else {
            resultSB.append("对于文件夹["+srcPath+"] 未执行任何 retype 操作! 该目录下的实体文件可能为0 请检查!");

        }

        return resultSB.toString();
        // Logic_____Begin

    }

    public    String  zand_file_renameappend(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 3 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);
        String EndTag = paramsList.get(2);

        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        if(srcDir.isFile()){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量后缀改名!");
            return resultSB.toString();
        }
        int beginNum = 0;

        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                String newName = oldNameNoType+EndTag+type;
                tryReName(subFile,newName);
                beginNum++;
                print(" AtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                String newName = oldNameNoType+EndTag+type;
                tryReName(subFile,newName);
                print(" BtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
                beginNum++;
            }
        }


        if(beginNum > 0 ){
            resultSB.append("对于文件夹["+srcPath+"] 已执行 ["+beginNum+"] 次 renameappend 操作! typeFliter=["+typeFliter+"]  EndTag["+EndTag+"]");
        }else {
            resultSB.append("对于文件夹["+srcPath+"] 未执行任何 renameappend 操作! 该目录下的实体文件可能为0 请检查!");

        }

        return resultSB.toString();
        // Logic_____Begin

    }

    public    String  zand_file_renameprefix(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 3 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);
        String PreFix_Tag = paramsList.get(2);

        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        if(srcDir.isFile()){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量前缀改名!");
            return resultSB.toString();
        }
int beginNum = 0;

        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                String newName = PreFix_Tag+oldNameNoType+type;
                tryReName(subFile,newName);
                beginNum++;
                print(" AtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                String newName = PreFix_Tag+oldNameNoType+type;
                tryReName(subFile,newName);
                print(" BtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
                beginNum++;
            }
        }


        if(beginNum > 0 ){
            resultSB.append("对于文件夹["+srcPath+"] 已执行 ["+beginNum+"] 次 renameprefix 操作! typeFliter=["+typeFliter+"]  prefixTag["+PreFix_Tag+"]");
        }else {
            resultSB.append("对于文件夹["+srcPath+"] 未执行任何 renameprefix 操作! 该目录下的实体文件可能为0 请检查!");

        }

        return resultSB.toString();
        // Logic_____Begin

    }


    public    String  zand_file_renamenumpre(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 3 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);
        String intBeginStr = paramsList.get(2);
        int beginNum = 1;


        boolean beginflag = isNumber(intBeginStr);
        if(beginflag){
            beginNum = Integer.parseInt(intBeginStr);
        }
        int beginFrezz = beginNum;

        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        if(srcDir.isFile()){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量改名!");
            return resultSB.toString();
        }


        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                String newName = beginNum+"_"+oldNameNoType+type;
                tryReName(subFile,newName);
                beginNum++;
                print(" AtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                String newName = beginNum+"_"+oldNameNoType+type;
                tryReName(subFile,newName);
                print(" BtryReName  -- newName=["+newName+"]   beginNum="+beginNum);
                beginNum++;
            }
        }

        int renameCount = beginNum - beginFrezz;
        if(renameCount > 0 ){
            resultSB.append("对于文件夹["+srcPath+"] 已执行 ["+renameCount+"] 次 renamepre 操作! typeFliter=["+typeFliter+"]  beginInt["+beginFrezz+"]");
        }else {
            resultSB.append("对于文件夹["+srcPath+"] 未执行任何 renamepre 操作! 该目录下的实体文件可能为0 请检查!");

        }

        return resultSB.toString();
        // Logic_____Begin

    }




    public    String  zand_file_rename_alldir_md5(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " MD5 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" MD5 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 2 ) {

            resultSB.append(" MD5 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[2] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);




        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        if(srcDir.isFile()){
            resultSB.append(" MD5 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量改名!");
            return resultSB.toString();
        }


        int renameCount = 0;
//        File[] allSubFile = getAllSubFile(srcDir.getAbsolutePath(),"*");
ArrayList<File> allDir_SubFileList = getAllSubFile(srcDir.getAbsolutePath(),"*");
        for (int i = 0; i <allDir_SubFileList.size() ; i++) {
            File subFile = allDir_SubFileList.get(i);
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print("MD5 AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
//                    String newName = beginNum+"_"+oldNameNoType+type;
                String md5_str = DataHolder.getMD5Three(subFile.getAbsolutePath());
                String newName = md5_str+type;
                tryReName(subFile,newName);
                renameCount++;
                print(" MD5 AtryReName  -- newName=["+newName+"]   renameCount="+renameCount);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
//                    String newName = beginNum+"_"+oldNameNoType+type;
                String md5_str = DataHolder.getMD5Three(subFile.getAbsolutePath());
                String newName = md5_str+type;
                tryReName(subFile,newName);
                renameCount++;
                print(" MD5 BtryReName  -- newName=["+newName+"]   renameCount="+renameCount);

            }
        }



        if(renameCount > 0 ){
            resultSB.append("对于文件夹["+srcPath+"] 已执行 ["+renameCount+"] 次 renameMD5 操作! typeFliter=["+typeFliter+"] ");
        }else {
            resultSB.append("对于文件夹["+srcPath+"] 未执行任何 renameMD5  操作! 该目录下的实体文件可能为0 请检查!");

        }




        return resultSB.toString();
        // Logic_____Begin


    }



    public    String  zand_file_renamemd5(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " MD5 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" MD5 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 2 ) {

            resultSB.append(" MD5 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[2] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);




        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        if(srcDir.isFile()){
            resultSB.append(" MD5 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量改名!");
            return resultSB.toString();
        }


        int renameCount = 0;
        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print("MD5 AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
//                    String newName = beginNum+"_"+oldNameNoType+type;
                String md5_str = DataHolder.getMD5Three(subFile.getAbsolutePath());
                String newName = md5_str+type;
                tryReName(subFile,newName);
                renameCount++;
                print(" MD5 AtryReName  -- newName=["+newName+"]   renameCount="+renameCount);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
//                    String newName = beginNum+"_"+oldNameNoType+type;
                String md5_str = DataHolder.getMD5Three(subFile.getAbsolutePath());
                String newName = md5_str+type;
                tryReName(subFile,newName);
                renameCount++;
                print(" MD5 BtryReName  -- newName=["+newName+"]   renameCount="+renameCount);

            }
        }



        if(renameCount > 0 ){
            resultSB.append("对于文件夹["+srcPath+"] 已执行 ["+renameCount+"] 次 renameMD5 操作! typeFliter=["+typeFliter+"] ");
        }else {
            resultSB.append("对于文件夹["+srcPath+"] 未执行任何 renameMD5  操作! 该目录下的实体文件可能为0 请检查!");

        }




        return resultSB.toString();
        // Logic_____Begin


    }

        public    String  zand_file_renametime(String rawComment , ArrayList<String> paramsList){
            StringBuilder resultSB = new StringBuilder();
            if(paramsList == null  || paramsList.size() == 0 ) {
                print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
                resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
                return resultSB.toString();
            }
            if( paramsList.size() != 2 ) {

                resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[2] 不符 程序无法执行!");

                return resultSB.toString();

            }

            String srcPath = paramsList.get(0);
            String typeFliter = paramsList.get(1);





            //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

            File srcDir = new File(srcPath);

            if(srcDir.isFile()){
                resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量改名!");
                return resultSB.toString();
            }


            int renameCount = 0;
            File[] allSubFile = srcDir.listFiles();

            for (int i = 0; i <allSubFile.length ; i++) {
                File subFile = allSubFile[i];
                if(subFile.isDirectory()){
                    continue;
                }
                String oldName = subFile.getName();
                String oldNameNoType = getFileNameNoPoint(subFile.getName());
                // AtryReName  type[.dat]  typeFliter=[dat]
                String type = oldName.replace(oldNameNoType,"");
                print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
                if("*".equals(typeFliter)){  // 过滤所有的文件
//                    String newName = beginNum+"_"+oldNameNoType+type;
                    String newName = getTimeStampLong()+type;
                    tryReName(subFile,newName);
                    renameCount++;
                    print(" AtryReName  -- newName=["+newName+"]   renameCount="+renameCount);
                }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
//                    String newName = beginNum+"_"+oldNameNoType+type;
                    String newName = getTimeStampLong()+type;
                    tryReName(subFile,newName);
                    renameCount++;
                    print(" BtryReName  -- newName=["+newName+"]   renameCount="+renameCount);

                }
            }



            if(renameCount > 0 ){
                resultSB.append("对于文件夹["+srcPath+"] 已执行 ["+renameCount+"] 次 renametime 操作! typeFliter=["+typeFliter+"] ");
            }else {
                resultSB.append("对于文件夹["+srcPath+"] 未执行任何 renametime 操作! 该目录下的实体文件可能为0 请检查!");

            }

            return resultSB.toString();
            // Logic_____Begin

        }



    public String getTimeStampLong() {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");// 设置日期格式
        String date = df.format(new Date());
        return date;
    }


    public    String  zand_file_badtype(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 3 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);

        String destDir = paramsList.get(2);




        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        File dstDir = new File(destDir);

        if(srcDir.isFile()  ){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量加密!");
            return resultSB.toString();
        }

        if(dstDir.isFile()  ){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的dstDir参数["+dstDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量加密!");
            return resultSB.toString();
        }


        // 满足类型 过滤 条件的 文件的 集合
        ArrayList<File> srcFilterFileList  = new  ArrayList<File>();

        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                srcFilterFileList.add(subFile);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                srcFilterFileList.add(subFile);
            }
        }


        if(srcFilterFileList.size() == 0){
            resultSB.append(" 命令执行失败--- src参数["+srcDir.getAbsolutePath()+"] 下过滤类型["+typeFliter+"] 搜索结果为空! ");

        }else{

            if(!dstDir.exists()){
                boolean makeDirOperation =  dstDir.mkdirs();
                if(!makeDirOperation){
                    resultSB.append(" 创建 dst目录["+destDir+"] 失败! ");

                    return resultSB.toString();

                }
            }

            for (int i = 0; i < srcFilterFileList.size(); i++) {
                File srcRealFile = srcFilterFileList.get(i);
                String fileName = srcRealFile.getName();
                String fileNameNoType = getFileNameNoPoint(fileName);
                String fileType = fileName.replace(fileNameNoType,"");
                String newName = fileNameNoType+"_"+"bad"+fileType;
                File destRealFile = new File(dstDir.getAbsolutePath()+File.separator+newName);
//                fileCopy(srcRealFile,destRealFile);
//                GoodBadHolder.createDecryFile(srcRealFile,destRealFile);
                GoodBadHolder.create_BadEncry_File(srcRealFile,destRealFile);
            }
            resultSB.append(" 从 ["+srcPath+"] 加密了["+srcFilterFileList.size()+"]个类型为 ["+typeFliter+"] 的文件到目标文件夹["+destDir+"]");

        }
        return resultSB.toString();
        // Logic_____Begin
    }


    public    String  zand_file_goodtype(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 3 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);

        String destDir = paramsList.get(2);




        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        File dstDir = new File(destDir);

        if(srcDir.isFile()  ){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量解密!");
            return resultSB.toString();
        }

        if(dstDir.isFile()  ){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的dstDir参数["+dstDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量解密!");
            return resultSB.toString();
        }


        // 满足类型 过滤 条件的 文件的 集合
        ArrayList<File> srcFilterFileList  = new  ArrayList<File>();

        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                srcFilterFileList.add(subFile);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                srcFilterFileList.add(subFile);
            }
        }


        if(srcFilterFileList.size() == 0){
            resultSB.append(" 命令执行失败--- src参数["+srcDir.getAbsolutePath()+"] 下过滤类型["+typeFliter+"] 搜索结果为空! ");

        }else{

            if(!dstDir.exists()){
                boolean makeDirOperation =  dstDir.mkdirs();
                if(!makeDirOperation){
                    resultSB.append(" 创建 dst目录["+destDir+"] 失败! ");

                    return resultSB.toString();

                }
            }

            for (int i = 0; i < srcFilterFileList.size(); i++) {
                File srcRealFile = srcFilterFileList.get(i);
                String fileName = srcRealFile.getName();
                String fileNameNoType = getFileNameNoPoint(fileName);
                String fileType = fileName.replace(fileNameNoType,"");
                String newName = fileNameNoType+"_"+"bad"+fileType;
                newName = newName.replace("bad","good");

                File destRealFile = new File(dstDir.getAbsolutePath()+File.separator+newName);
//                fileCopy(srcRealFile,destRealFile);
                GoodBadHolder.create_GoodDecry_File(srcRealFile,destRealFile);
            }
            resultSB.append(" 从 ["+srcPath+"] 解密了["+srcFilterFileList.size()+"]个类型为 ["+typeFliter+"] 的文件到目标文件夹["+destDir+"]");

        }
        return resultSB.toString();
        // Logic_____Begin
    }




    public    String  zand_file_movetype(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 3 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);

        String destDir = paramsList.get(2);




        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        File dstDir = new File(destDir);

        if(srcDir.isFile()  ){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量剪切!");
            return resultSB.toString();
        }

        if(dstDir.isFile()  ){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的dstDir参数["+dstDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量剪切!");
            return resultSB.toString();
        }


        // 满足类型 过滤 条件的 文件的 集合
        ArrayList<File> srcFilterFileList  = new  ArrayList<File>();

        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                srcFilterFileList.add(subFile);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                srcFilterFileList.add(subFile);
            }
        }


        if(srcFilterFileList.size() == 0){
            resultSB.append(" 命令执行失败--- src参数["+srcDir.getAbsolutePath()+"] 下过滤类型["+typeFliter+"] 搜索结果为空! ");

        }else{

            if(!dstDir.exists()){
                boolean makeDirOperation =  dstDir.mkdirs();
                if(!makeDirOperation){
                    resultSB.append(" 创建 dst目录["+destDir+"] 失败! ");

                    return resultSB.toString();

                }
            }

            for (int i = 0; i < srcFilterFileList.size(); i++) {
                File srcRealFile = srcFilterFileList.get(i);
                String fileName = srcRealFile.getName();
                File destRealFile = new File(dstDir.getAbsolutePath()+File.separator+fileName);
                fileCopy(srcRealFile,destRealFile);
                srcRealFile.delete();
            }
            resultSB.append(" 从 ["+srcPath+"] 剪切了["+srcFilterFileList.size()+"]个类型为 ["+typeFliter+"] 的文件到目标文件夹["+destDir+"]");

        }
        return resultSB.toString();
        // Logic_____Begin
    }


    public    String  zand_file_copytype(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0 ) {
            print(rawComment+ " 命令执行失败--- 当前前命令 输入的参数个数为空");
            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为空");
            return resultSB.toString();
        }
        if( paramsList.size() != 3 ) {

            resultSB.append(" 命令执行失败--- 当前前命令 输入的参数个数为["+paramsList.size()+"]"+"与标准参数个数[3] 不符 程序无法执行!");

            return resultSB.toString();

        }

        String srcPath = paramsList.get(0);
        String typeFliter = paramsList.get(1);

        String destDir = paramsList.get(2);




        //  如果要 改名  单独改 一个 文件 是 没有意义的  所以 这里一定 必须是 一个 文件夹

        File srcDir = new File(srcPath);

        File dstDir = new File(destDir);

        if(srcDir.isFile()  ){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的src参数["+srcDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量复制!");
            return resultSB.toString();
        }

        if(dstDir.isFile()  ){
            resultSB.append(" 命令执行失败--- 当前前命令 输入的dstDir参数["+dstDir.getAbsolutePath()+"] 是一个文件 而不是文件夹 无法批量复制!");
            return resultSB.toString();
        }


        // 满足类型 过滤 条件的 文件的 集合
        ArrayList<File> srcFilterFileList  = new  ArrayList<File>();

        File[] allSubFile = srcDir.listFiles();

        for (int i = 0; i <allSubFile.length ; i++) {
            File subFile = allSubFile[i];
            if(subFile.isDirectory()){
                continue;
            }
            String oldName = subFile.getName();
            String oldNameNoType = getFileNameNoPoint(subFile.getName());
            // AtryReName  type[.dat]  typeFliter=[dat]
            String type = oldName.replace(oldNameNoType,"");
            print(" AtryReName  type["+type+"]  typeFliter=["+typeFliter+"]");
            if("*".equals(typeFliter)){  // 过滤所有的文件
                srcFilterFileList.add(subFile);
            }else if(("."+typeFliter).toLowerCase().equals(type)){  // 过滤 执行类型的文件
                srcFilterFileList.add(subFile);
            }
        }


        if(srcFilterFileList.size() == 0){
            resultSB.append(" 命令执行失败--- src参数["+srcDir.getAbsolutePath()+"] 下过滤类型["+typeFliter+"] 搜索结果为空! ");

        }else{

            if(!dstDir.exists()){
               boolean makeDirOperation =  dstDir.mkdirs();
               if(!makeDirOperation){
                   resultSB.append(" 创建 dst目录["+destDir+"] 失败! ");

                   return resultSB.toString();

               }
            }

            for (int i = 0; i < srcFilterFileList.size(); i++) {
                File srcRealFile = srcFilterFileList.get(i);
String fileName = srcRealFile.getName();
                File destRealFile = new File(dstDir.getAbsolutePath()+File.separator+fileName);
                fileCopy(srcRealFile,destRealFile);
            }
            resultSB.append(" 从 ["+srcPath+"] 复制了["+srcFilterFileList.size()+"]个类型为 ["+typeFliter+"] 的文件到目标文件夹["+destDir+"]");

        }
        return resultSB.toString();
        // Logic_____Begin
    }


    public  void fileCopy(File origin, File target) {
        InputStream input = null;
        OutputStream output = null;
        int lengthSize;
        // 创建输入输出流对象
        try {

            if(!target.getParentFile().exists()){
                target.getParentFile().mkdirs();
            }
            input = new FileInputStream(origin);
            output = new FileOutputStream(target);
            // 获取文件长度
            try {
                lengthSize = input.available();
                // 创建缓存区域
                byte[] buffer = new byte[lengthSize];
                // 将文件中的数据写入缓存数组
                input.read(buffer);
                // 将缓存数组中的数据输出到文件
                output.write(buffer);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (Exception e) {

        } finally {
            if (input != null && output != null) {
                try {
                    input.close(); // 关闭流
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }













    // 删除相同 md5 文件的操作
    public    String  zand_file_delete_same_md5_file(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();

        if(paramsList == null  || paramsList.size() == 0) {
            print("zand_file_delete_same_md5_file   命令执行失败--- 当前 deletetype  输入的参数个数为空");
            return "zand_file_delete_same_md5_file  命令执行失败--- 当前 deletetype 输入的参数个数为空";
        }





        File targetDirFile = null;
        String delete_type = "";


        if( paramsList.size() != 2 ){

            resultSB.append("\nzand_file_delete_same_md5_file--删除--文件夹  参数个数不为2  1=目标文件夹  2=目标类型"+"\n");
            return  resultSB.toString();
        }


        String deleteParamItem = paramsList.get(0);
        String deleteType = paramsList.get(1);



        File deleteDirFile = new File(deleteParamItem);

        if(!deleteDirFile.exists()){

            resultSB.append("\n zand_file_delete_same_md5_file--删除--文件夹"+deleteDirFile.getAbsolutePath()+"不存在,请检查!"+"\n");
            return  resultSB.toString();
        }


        if("".equals(deleteType)){
            resultSB.append("\n zand_file_delete_same_md5_file--删除--文件夹"+deleteDirFile.getAbsolutePath()+" 目标类型 deleteType="+deleteType+" 为空 请检查!"+"\n");
            return  resultSB.toString();
        }


        if("*".equals(deleteType)){
print(" zand_file_delete_same_md5_file  将删除文件夹 "+ deleteDirFile.getAbsolutePath() +" 下所有的文件对应的 md5相同的 文件  只保留一个MD5 ");
        }


        ArrayList<String>  deleteTypeList = new     ArrayList<String>();

        deleteTypeList.add(deleteType);

        CountDownLatch mCountDownLatch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {


                ArrayList<File>  deleteFileList = new ArrayList<File>();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    deleteFileList =   getAllSubFile(deleteDirFile.getAbsolutePath(),deleteTypeList);
                }

                //  计算 所有文件的 md5  然后 删除

                ArrayList<String> md5_list = new     ArrayList<String>();

                ArrayList<String> repeat_md_list = new     ArrayList<String>();
                ArrayList<File> repeat_file_list  =new   ArrayList<File>();



                for (int i = 0; i < deleteFileList.size(); i++) {

                    File rawFile = deleteFileList.get(i);
                    String raw_md5_str = DataHolder.getMD5Three(rawFile.getAbsolutePath());

                    if(raw_md5_str != null &&  raw_md5_str.length() > 8 && md5_list.contains(raw_md5_str)){   // 如果当前 已经 包含这个 md5 了 那么  添加到 重复的list 中

                        repeat_md_list.add(raw_md5_str);
                        repeat_file_list.add(rawFile);


                    }else if(raw_md5_str != null &&  raw_md5_str.length() > 8 &&  !md5_list.contains(raw_md5_str)){  //  不包含 那么就 添加到



                        md5_list.add(raw_md5_str);
                    }

                }


                for (int i = 0; i < repeat_file_list.size(); i++) {
                    File need_del_repeat_file = repeat_file_list.get(i);

                    need_del_repeat_file.delete();

//                    print(" zand_file_delete_same_md5_file  将删除文件夹 "+ deleteDirFile.getAbsolutePath() +" delFile["+(i+1)+"]_same_md5_size["+repeat_file_list.size()+"] allsize["+deleteFileList.size()+"] = "+need_del_repeat_file.getAbsolutePath());

                }


//                print(" zand_file_delete_same_md5_file   "+ deleteDirFile.getAbsolutePath() + "same_md5_size["+repeat_file_list.size()+"] allsize["+deleteFileList.size()+"]    resultSB= "+resultSB.toString());

                resultSB.append("same_md5_size["+repeat_file_list.size()+"] 已删除 \n allsize["+deleteFileList.size()+"] in "+deleteDirFile.getAbsolutePath()+ "  for type="+deleteType);
                try {
                    mCountDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        try {
            mCountDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return resultSB.toString();


    }





    public    String  zand_file_deletetype(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0) {
            print("zand_file_deletetype 命令执行失败--- 当前 deletetype  输入的参数个数为空");
            return "zand_file_deletetype 命令执行失败--- 当前 deletetype 输入的参数个数为空";
        }


//        2022-09-19 22:46:16.075 23318-23318/? I/ZTxtAdapter: 执行把 i[1][2] [/sdcard/zapp/] deletetype的操作!
//       2022-09-19 22:46:16.075 23318-23318/? I/ZTxtAdapter: 执行把 i[2][2] [mp4] deletetype的操作!


        File targetDirFile = null;
        String delete_type = "";


        if( paramsList.size() != 2 ){

            resultSB.append("\ndeletetype--删除--文件夹  参数个数不为2  1=目标文件夹  2=目标类型"+"\n");
            return  resultSB.toString();
        }





            String deleteParamItem = paramsList.get(0);
            String deleteType = paramsList.get(1);



            File deleteDirFile = new File(deleteParamItem);

            if(!deleteDirFile.exists()){

                resultSB.append("\ndeletetype--删除--文件夹"+deleteDirFile.getAbsolutePath()+"不存在,请检查!"+"\n");
                return  resultSB.toString();
            }

        if(deleteDirFile.isFile()){

            resultSB.append("\ndeletetype--删除--文件夹"+  deleteDirFile.getAbsolutePath()+"是一个文件 ,请检查!"+"\n");
            return  resultSB.toString();
        }

            if("".equals(deleteType)){
                resultSB.append("\ndeletetype--删除--文件夹"+deleteDirFile.getAbsolutePath()+" 目标类型 deleteType="+deleteType+" 为空 请检查!"+"\n");
                return  resultSB.toString();
            }



        print("执行把  目标文件夹["+deleteDirFile.getAbsolutePath()+"] 按类型["+deleteType+"] 的  deleteType 的操作! ");
        ArrayList<String>  deleteTypeList = new     ArrayList<String>();


        if("media".equals(deleteType)){
            deleteTypeList.add(".jpg");
            deleteTypeList.add(".png");
            deleteTypeList.add(".gif");
            deleteTypeList.add(".mp4");
            deleteTypeList.add(".avi");
            deleteTypeList.add(".flv");
            deleteTypeList.add(".webp");
            deleteTypeList.add(".rmvb");
            deleteTypeList.add(".webp");
            deleteTypeList.add(".ts");
            deleteTypeList.add(".wav");
            deleteTypeList.add(".m4a");
            deleteTypeList.add(".mov");
            deleteTypeList.add(".m4v");
        }

        deleteTypeList.add(deleteType);

        ArrayList<File>  deleteFileList = new ArrayList<File>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             deleteFileList =   getAllSubFile(deleteDirFile.getAbsolutePath(),deleteTypeList);
        }

        if(deleteFileList == null || deleteFileList.size() == 0){

            resultSB.append("\n deletetype--删除--文件夹"+deleteDirFile.getAbsolutePath()+" 目标类型 deleteType="+deleteType+" 遍历得到的  ArrayList<File> 列表为空!  "+"\n");
            return  resultSB.toString();

        }

        for (int i = 0; i < deleteFileList.size(); i++) {
            File delete_file_item = deleteFileList.get(i);

            delete_file_item.delete();
            print("  deleteType["+(i+1)+"]["+deleteFileList.size()+"]  "+delete_file_item.getAbsolutePath()+ " deleteDir="+deleteParamItem+"  "+"deleteType="+deleteType+"  执行删除操作! ");

        }
        resultSB.append("\n deletetype--删除--文件夹"+deleteDirFile.getAbsolutePath()+" 目标类型 deleteType="+deleteType+" 遍历的"+ deleteFileList.size()+"个文件  "+"\n");

        return  resultSB.toString();

    }



    static ArrayList<File> getAllSubFile(String rootPath, String type) {
        ArrayList<String>  typeList = new     ArrayList<String> ();
        typeList.add(type);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return getAllSubFile(rootPath, typeList);
        }

        return null;


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static ArrayList<File> getAllSubFile(String rootPath, ArrayList<String> typeList) {
        ArrayList<File> allFile = new ArrayList<File>();
        Path curRootPath = Paths.get(rootPath);

        try {
            Files.walkFileTree(curRootPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = null;
                     fileString = file.toAbsolutePath().toString();
                    // System.out.println("pathString = " + fileString);
                    for (int i = 0; i < typeList.size(); i++) {
                        String type = typeList.get(i);
                        if ("*".equals(type)) { // 如果 类型是 * 那么就把 所有的 非目录文件加入列表中
                            File curFile = new File(fileString);
                            if (!curFile.isDirectory()) {
                                allFile.add(curFile);
                                break;
                            }

                        } else {
                            if (fileString.toLowerCase().endsWith(type.toLowerCase())) {
                                allFile.add(new File(fileString));
                                break;
//                         System.out.println("file found at path: " + file.toAbsolutePath());
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allFile;

    }


    public    String  zand_file_delete(String rawComment , ArrayList<String> paramsList){
        StringBuilder resultSB = new StringBuilder();
        if(paramsList == null  || paramsList.size() == 0) {
            print("zand_file_delete 命令执行失败--- 当前delete输入的参数个数为空");
            return "zand_file_delete 命令执行失败--- 当前delete输入的参数个数为空";
        }



        for (int i = 0; i < paramsList.size(); i++) {
            String deleteParamItem = paramsList.get(i);
            print("执行把 i["+(i+1)+"]["+paramsList.size()+"] ["+deleteParamItem+"] delete的操作! ");

            File deleteFile = new File(deleteParamItem);
            if(deleteFile.exists()){
                resultSB.append("\n删除成功--文件["+i+"]"+deleteFile.getAbsolutePath()+"\n");
                deleteFile.delete();

            }else{
                resultSB.append("\n删除失败--文件["+i+"]"+deleteFile.getAbsolutePath()+"不存在 无法执行删除操作\n");

            }

        }

       return  resultSB.toString();

    }


    public    void  zand_file_copy(String rawComment , ArrayList<String> paramsList){
        if(paramsList == null ) {
            print(" zand_file_copy 命令执行失败--- 当前copy输入的参数个数为空");
            return ;
        }

        if(paramsList.size()  != 2 ) {
            print(" zand_file_copy 命令执行失败--- 当前copy输入的参数个数为 ["+paramsList.size() +"]"+" 不为2 无法执行");
            showParamList(rawComment,paramsList);
            return ;
        }


        String srcFilePath = paramsList.get(0);
        String dstFilePath = paramsList.get(1);

        print("执行把 src["+srcFilePath+"] copy 到 dst["+dstFilePath+"]的操作!! ");





    }

    public    void  zand_file_move(String rawComment , ArrayList<String> paramsList){
        if(paramsList == null ) {
            print(" zand_file_move 命令执行失败--- 当前move输入的参数个数为空");
            return ;
        }

        if(paramsList.size()  != 2 ) {
            print(" zand_file_move 命令执行失败--- 当前move输入的参数个数为 ["+paramsList.size() +"]"+" 不为2 无法执行");
            showParamList(rawComment,paramsList);
            return ;
        }


        String srcFilePath = paramsList.get(0);
        String dstFilePath = paramsList.get(1);

        print("执行把 src["+srcFilePath+"] move 到 dst["+dstFilePath+"]的操作!! ");





    }


    public    void  zand_file_bad(String rawComment , ArrayList<String> paramsList){
        if(paramsList == null ) {
            print(" zand_file_bad 命令执行失败--- 当前bad输入的参数个数为空");
            return ;
        }

        if(paramsList.size()  != 2 ) {
            print(" zand_file_bad 命令执行失败--- 当前bad输入的参数个数为 ["+paramsList.size() +"]"+" 不为2 无法执行");
            showParamList(rawComment,paramsList);
            return ;
        }


        String srcFilePath = paramsList.get(0);
        String dstFilePath = paramsList.get(1);

        print("执行把 src["+srcFilePath+"] bad 到 dst["+dstFilePath+"]的操作!! ");





    }


    public    void  zand_file_good(String rawComment , ArrayList<String> paramsList){
        if(paramsList == null ) {
            print(" zand_file_good 命令执行失败--- 当前good输入的参数个数为空");
            return ;
        }

        if(paramsList.size()  != 2 ) {
            print(" zand_file_good 命令执行失败--- 当前good输入的参数个数为 ["+paramsList.size() +"]"+" 不为2 无法执行");
            showParamList(rawComment,paramsList);
            return ;
        }


        String srcFilePath = paramsList.get(0);
        String dstFilePath = paramsList.get(1);

        print("执行把 src["+srcFilePath+"] good 到 dst["+dstFilePath+"]的操作!! ");





    }

//    zand_file_@【opt1】_@【opt2】_@【opt3】_x_@【arg1】_@【arg2】_@【arg3】  安卓上执行
    // zand_file_@make
    // zand_file_@delete
    // zand_file_@renamenum
    // zand_file_@renamenumpre
    // zand_file_@renametime
    // zand_file_@copytype
    // zand_file_@copy
    // zand_file_@copys
    // zand_file_@goodtype
    // zand_file_@goods
    // zand_file_@good
    // zand_file_@badtype
    // zand_file_@bads
    // zand_file_@bad
    // zand_file_@movetype
    // zand_file_@moves
    // zand_file_@move
    // zand_file_@renameprefix
    // zand_file_@renameappend
    // zand_file_@retype
    // zand_file_@renameplace

    public    String Zcmd_File_Operation(String fileCommand , ArrayList<String> paramsList) {
        String resultTip = "";
        String rawCommondNoDigitalStr = clearNumber(fileCommand);
        //123456789   987654321
        ArrayList<String> filterParams = new  ArrayList<String>();
        String onlyDigitalStr = fileCommand.replace(rawCommondNoDigitalStr,"");
        print("rawCommondNoDigitalStr = "+ rawCommondNoDigitalStr +"  onlyDigitalStr="+onlyDigitalStr);

        ArrayList<String>  commandParamList =  calStepIndexFromCommandItem(fileCommand,onlyDigitalStr,paramsList);

        android.util.Log.i("zukgit_md","zand_file_delete_same_md5_file  fileCommand="+fileCommand+"  rawCommondNoDigitalStr = "+ rawCommondNoDigitalStr +"  onlyDigitalStr="+onlyDigitalStr);


        switch (rawCommondNoDigitalStr) {

            //____1_____ 每次次处理一个参数的类型_Begin



            case "make":
                resultTip =   zand_file_make(fileCommand,commandParamList);
                break;


            case "lindirs":
                resultTip =   zand_file_lin_operation(fileCommand,commandParamList);
                break;


            //____1_____ 只每次次处理一个参数的类型_End


            // ____2_____ 每次次处理两个参数的类型_Begin

        //  指定原src目录 经过bad后复制到dst目录
            case "baddirs":
                resultTip =  zand_file_baddirs_operation(fileCommand,commandParamList);
                break;



            //  检测下一个是否是路径 来判断  单个删除  还是 类型删除
            case "delete":    //  srcdir scrtype  给定一个目录 以及   以及一个类型  执行删除符合类型的文件
                resultTip =  zand_file_delete(fileCommand,commandParamList);
                break;



            case "deletetype":    //  srcdir scrtype  给定一个目录 以及   以及一个类型  执行删除符合类型的文件
                resultTip =  zand_file_deletetype(fileCommand,commandParamList);
                break;


            case "delsamemd":    // @delsamemd12_x_@/sdcard/zapp/jpg_lin_port_@*   删除相同 MD5 字符串的文件
                resultTip =  zand_file_delete_same_md5_file(fileCommand,commandParamList);
                android.util.Log.i("zukgit_md","zand_file_delete_same_md5_file  resultTip="+resultTip);
                break;




            // ./jpg  srcdir  type   把给定的参数  按照 数字依次重命名
            case "renamenum": // srcdir  type
                resultTip =       zand_file_renamenum(fileCommand,commandParamList);
                break;

            case "renamenumpre":  // srcdir  type  在当前名字前加入数字前置_   1_  2_  3_
                resultTip =      zand_file_renamenumpre(fileCommand,commandParamList);
                break;

            case "renametime":  // 在当前名字后加入后缀时间戳 // srcdir  type
                resultTip =      zand_file_renametime(fileCommand,commandParamList);
                break;


            case "renamemd":  // 在当前名字后加入后缀时间戳 // srcdir  type
                resultTip =      zand_file_renamemd5(fileCommand,commandParamList);
                break;

            case "renameallmd":  // 在当前名字后加入后缀时间戳 // srcdir  type
                resultTip =      zand_file_rename_alldir_md5(fileCommand,commandParamList);
                break;



            //____2_____ 每次次处理两个参数的类型_End


            //____3_____ 每次次处理三个参数的类型_Begin

            case "copytype":  // srcdir type dstdir
                resultTip =      zand_file_copytype(fileCommand,commandParamList);
                break;

            case "copy":   //  srcdir scrtype
                resultTip =        zand_file_copytype(fileCommand,commandParamList);
                break;

            case "copys":   //  srcdir scrtype
                resultTip =     zand_file_copytype(fileCommand,commandParamList);
                break;




            case "goodtype":   // srcdir type dstdir
                resultTip =         zand_file_goodtype(fileCommand,commandParamList);
                break;
            case "goods":  //  srcdir scrtype  dsttype
                resultTip =        zand_file_goodtype(fileCommand,commandParamList);
                break;
            case "good":  //  srcdir scrtype  dsttype
                resultTip =         zand_file_goodtype(fileCommand,commandParamList);
                break;



            case "badtype":    // srcdir type dstdir
                resultTip =              zand_file_badtype(fileCommand,commandParamList);
                break;
            case "bad":    //  srcdir scrtype  dsttype
                resultTip =                 zand_file_badtype(fileCommand,commandParamList);
                break;
            case "bads":    //  srcdir scrtype  dsttype
                resultTip =          zand_file_badtype(fileCommand,commandParamList);
                break;



            case "movetype":     //  srcdir type dstdir
                resultTip =         zand_file_movetype(fileCommand,commandParamList);
                break;
            case "moves":     //  srcdir scrtype  dsttype
                resultTip =          zand_file_movetype(fileCommand,commandParamList);
                break;
            case "move":     //  srcdir scrtype  dsttype
                resultTip =        zand_file_movetype(fileCommand,commandParamList);
                break;




            case "renameprefix":  // 在当前名字加入前缀  srcdir type prefixname
                resultTip =     zand_file_renameprefix(fileCommand,commandParamList);
                break;


            case "renameappend":  // 在当前名字加入后缀  srcdir type appendname
                resultTip =      zand_file_renameappend(fileCommand,commandParamList);
                break;




            case "retype":  //  srcdir  srctype  dsttype
                resultTip =       zand_file_retype(fileCommand,commandParamList);
                break;

            //____3_____ 每次次处理三个参数的类型_End


            //____4_____ 每次次处理四个参数的类型_Begin
            case "renameplace":  //  srcdir srctype srcname dstname
                resultTip =        zand_file_renameplace(fileCommand,commandParamList);
                break;


            //____4_____ 每次次处理四个参数的类型_End




            default:
                break;
        }



        return resultTip;
    }


    public   String clearNumber(String str){
        String result = new String(str);
        result = result.replaceAll("0","");
        result = result.replaceAll("1","");
        result = result.replaceAll("2","");
        result = result.replaceAll("3","");
        result = result.replaceAll("4","");
        result = result.replaceAll("5","");
        result = result.replaceAll("6","");
        result = result.replaceAll("7","");
        result = result.replaceAll("8","");
        result = result.replaceAll("9","");
        return result;
    }


    public   boolean isNumber(String str){
        String result = new String(str);
        result = result.replaceAll("0","");
        result = result.replaceAll("1","");
        result = result.replaceAll("2","");
        result = result.replaceAll("3","");
        result = result.replaceAll("4","");
        result = result.replaceAll("5","");
        result = result.replaceAll("6","");
        result = result.replaceAll("7","");
        result = result.replaceAll("8","");
        result = result.replaceAll("9","").trim();
        return "".equals(result);
    }



    // zand_file_【opt1】_【opt2】_【opt3】_x_@【arg1】_@【arg2】_@【arg3】
    public     String getZandOperationType(String txtLine){
        String matchZcmdType = null;
        String clearZcmdStr = txtLine.replace("zand_","");
        int firstlowLine = clearZcmdStr.indexOf("_");
        if(firstlowLine > 0){
            matchZcmdType = clearZcmdStr.substring(0,firstlowLine).toLowerCase();
        }


        return matchZcmdType;
    }


    //  zand_file_@【opt1】_@【opt2】_@【opt3】_x_@【arg1】_@【arg2】_@【arg3】
    public     ArrayList<String>  calculParamListFromZcmd(String txtLine,String operationType){
        ArrayList<String>  matchParamList = new    ArrayList<String>();
        if(!txtLine.contains("_x_")){
            print("getParams_Error_A");
            return matchParamList;
        }
        String preStr = ("zand_"+operationType+"_").toLowerCase();

        int preStrIndex = (txtLine.trim().toLowerCase()).indexOf(preStr);
        if(preStrIndex < 0){
            print("getParams_Error_B  preStrIndex="+preStrIndex+"   preStr="+preStr+"  txtLine="+txtLine);
            return matchParamList;
        }

        // @【opt1】_@【opt2】_@【opt3】_x_@【arg1】_@【arg2】_@【arg3】
        String rawCommandLine = txtLine.substring(preStrIndex);

        int indexof_param = rawCommandLine.indexOf("_x_@");

        String paramstr = rawCommandLine.substring(indexof_param+"_x_@".length());

        // 【arg1】_@【arg2】_@【arg3】

        String[]  paramArr = paramstr.split("_@");
        if(paramArr == null || paramArr.length == 0 ){
            print("getParams_Error_C");

            return matchParamList;
        }


        for (int i = 0; i < paramArr.length ; i++) {
            String item = paramArr[i].trim();
            if(!"".equals(item)){
                matchParamList.add(item);
            }
        }

        return matchParamList;

    }




    //     zand_file_@【opt1】_@【opt2】_@【opt3】_x_@【arg1】_@【arg2】_@【arg3】
    public    ArrayList<String>  calculOperationListFromZcmd(String txtLine,String operationType){
        ArrayList<String>  matchOperationList = new    ArrayList<String>();
        if(!txtLine.contains("_x_")){
            print("operation_Error_A");

            return matchOperationList;
        }
        String preStr = ("zand_"+operationType+"_").toLowerCase();

        int preStrIndex = (txtLine.trim().toLowerCase()).indexOf(preStr);
        if(preStrIndex < 0){
            print("operation_Error_B   preStr="+preStr+"  txtLine="+txtLine+"   preStrIndex="+preStrIndex);

            return matchOperationList;
        }

        // @【opt1】_@【opt2】_@【opt3】_x_@【arg1】_@【arg2】_@【arg3】
        String rawCommandLine = txtLine.substring(preStrIndex);

        int indexof_param = rawCommandLine.indexOf("_x_@");

        String paramstr = rawCommandLine.substring(0,indexof_param);

        // 【arg1】_@【arg2】_@【arg3】

        String[]  paramArr = paramstr.split("_@");
        if(paramArr == null || paramArr.length == 0 ){
            print("operation_Error_C");
            return matchOperationList;
        }


        for (int i = 0; i < paramArr.length ; i++) {
            String item = paramArr[i].trim();
            if(!"".equals(item) &&  !preStr.toLowerCase().startsWith(item.toLowerCase().trim())){
                print("ZmatchOperation item = "+ item +"   preStr="+preStr);

                matchOperationList.add(item);
            }
        }

        return matchOperationList;

    }


}
