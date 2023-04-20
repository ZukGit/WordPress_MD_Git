
#所有mainlog解压缩，然后执行这个命令 log_concat -m
#如果是radio log，就是log_concat -r

#! /usr/bin/perl -w

sub parse_command_line();
sub usage();

$CUR_INPUT_DIR = ".";
if (@ARGV){
    parse_command_line();
}else{
    usage();
}



use Cwd;
$CUR_CWD_DIR = getcwd;
print "____getcwd____=$CUR_CWD_DIR\n";
print "CUR_CWD_DIR=$CUR_CWD_DIR\n";
$pwd = $ENV{'PWD'};
print "____pwd____=$pwd\n";


$os_is_linux = $^O eq "linux";
$os_is_macos = $^O eq "drawx";
$os_is_windows = $^O eq "MSWin32";
$os_is_msys = $^O eq "msys"; 


$CUR_OS_WINDOS = $os_is_windows || $os_is_msys;


$Linux_Seperator= "/";
$Windos_Seperator="\\";

$CUR_OS_Seperator = $Windos_Seperator;


if ($CUR_OS_WINDOS){
   print("OSName____[Windows] \n");
   $CUR_Seperator = $Windos_Seperator;
} else {
   print("OSName____[Linux_MacOS] \n");
   $CUR_OS_Seperator = $Linux_Seperator;
}


print "OS____$^O \n";
print "os_is_linux ____ $os_is_linux    \n";
print "os_is_macos ____ $os_is_macos    \n";
print "os_is_windows ____ $os_is_windows    \n";
print "os_is_msys ____ $os_is_msys    \n";
print "CUR_OS_WINDOS ____ $CUR_OS_WINDOS    \n";
print "CUR_OS_Seperator _______[$CUR_OS_Seperator]____\n";
print "CUR_INPUT_DIR _______[$CUR_INPUT_DIR]____\n";


# ____getcwd____=/d/APK/1/geneva-aplogd/aplogd
# ____pwd____=
# __FILE__ = C:\Users\zhuzj5\Desktop\zbin\win_zbin\log_concat.pl
# File_Separator _______[/]____
# Self_File_PATH = C:/Users/zhuzj5/Desktop/zbin/win_zbin/log_concat.pl
# Self_File_PATH = C:/Users/zhuzj5/Desktop/zbin/win_zbin/log_concat.pl
# Self_File_Dir  = C:/Users/zhuzj5/Desktop/zbin/win_zbin/
# self_file_name  = log_concat.pl
# self_file_vol  =
# self_file_abs_path  = /c/Users/zhuzj5/Desktop/zbin/win_zbin/log_concat.pl


use File::Spec;
$File_Separator = File::Spec->catfile('', '');
$self_file_path = File::Spec->rel2abs(__FILE__);
($self_file_vol, $self_file_dir, $self_file_name) = File::Spec->splitpath($self_file_path);
print "__FILE__ = ", __FILE__,"\n";
print "File_Separator _______[$File_Separator]____\n";
print "Self_File_PATH = ", $self_file_path,"\n";
print "Self_File_PATH = ", $self_file_path,"\n";
print "Self_File_Dir  = ", $self_file_dir,"\n";
print "self_file_name  = ", $self_file_name,"\n";
print "self_file_vol  = ", $self_file_vol,"\n";

use Cwd 'abs_path';
$self_file_abs_path = abs_path($self_file_path);
print "self_file_abs_path  = ", $self_file_abs_path,"\n";


# ——————————————————————— Code Begin ————————————————————————

$script_version = "2.0";

$logid = "Stream-m";
$bootid = "";
$folder = ".";

##  //是Linux 下的分隔符号___在Windows下是 \___ 所以直接bat传过来不匹配   所以 不匹配 
## $sel = "\\*$bootid\_Set*$logid.txt*";  
$sel = "$File_Separator\*$bootid\_Set*$logid.txt*";   # 变量后面+\ 以标识分隔
$se2 = $CUR_CWD_DIR.$sel;    #$se2 = $folder.$sel;

# sel_1====/*_Set*Stream-k.txt*
# sel_2====/d/APK/1/geneva-aplogd/aplogd/*_Set*Stream-k.txt* (Linux路径)
# folder ___D:\APK\1\geneva-aplogd\aplogd  (windows路径 放到 glob 中会无法匹配到 正则表达式 匹配的文件)
# CUR_CWD_DIR __/d/APK/1/geneva-aplogd/aplogd


print "sel_1_begin ==== $sel\n";
print "sel_2_begin ==== $se2\n";
print "folder___$folder\n";
print "CUR_CWD_DIR __$CUR_CWD_DIR\n";
#$folder=$CUR_CWD_DIR;     ##  绝对路径
$folder = ".";             ##  相对路径
$sel = "$File_Separator\*$bootid\_Set*$logid.txt*"; 
$se2 = $folder.$sel; 
print "sel_1_end ==== $sel\n";
print "sel_2_end ==== $se2\n";


@files = glob("$se2");

print "found ".scalar(@files)." names\n";
if ($bootid eq "") {
    print "No boot id supplied, this will concatenate logs across all boot cycles in $folder.\n\n";
}

system("pause");
print "File_Separator=$File_Separator    CUR_OS_Seperator=$CUR_OS_Seperator  \n";
foreach (@files)
  {
    $name = $_;

    if($name =~ /(\d+)_Set-(\d+)_$logid.txt/)
      {
        $num = $1*1000000+$2;
		
		
		#  /   替换为  \     	
		#$name =~ s/$File_Separator/$CUR_OS_Seperator/g;
		
		# 去除 ./     	
 	    $name =~ s/.$File_Separator//g;
		$name_fixed_path = $name;
        $filenames{$num} = $name_fixed_path;
        print "match_______==> $_  name_fixed_path=$name_fixed_path\n";
        if($name =~ /.gz/)
        {
          $filenames{$num} =~ s/.gz//g;
          system("7z e -y $name -o$folder");
        }
      } 
  }
$allfiles = "";
foreach $key (sort numerically keys(%filenames))
{
    print "$key --- $filenames{$key}\n";
    #$filenames{$key} =~ s/.gz//g;
    #print "foreach $filenames{$key}\n";
    $allfiles = $allfiles." ".$filenames{$key};
}

### exit; ########################

print "\n Concatenating: $allfiles \n\n into ==> $folder$CUR_OS_Seperator\concat_$logid.txt\n\n\n";


print "\n Command:\n  type $allfiles  > $folder$CUR_OS_Seperator\\concat_$logid.txt\n\n\n";

## type 又是 Windows 下的命令   使用 / 作为分隔符   尼玛
system (" type $allfiles > $folder$CUR_OS_Seperator\\concat_$logid.txt");

print "Done.";

print "\n Command:\n  type $allfiles  > $folder$CUR_OS_Seperator\\concat_$logid.txt\n\n\n";


#--------------------------------------------------------------------------------
sub parse_command_line()
  {
    my $arg;
    while ($arg=shift(@ARGV))
      {
          if($arg =~/^-k/i)
          {
            $logid = "Stream-k";
          }elsif($arg =~/^-r/i)
          {
            $logid = "Stream-r";
          }elsif($arg =~/^-s/i)
          {
            $logid = "Stream-s";
          }elsif($arg =~/^-m/i)
          {
            $logid = "Stream-m";
          }
          elsif($arg =~/^-b/i)
          {
            $bootid = shift(@ARGV);
            if ($bootid !~ /^\d+$/) {
               print "Error: non-numeric boot number!\n";
               usage();
            }
            
            $logid = "Stream-m";
          }
          else
          {
            $CUR_INPUT_DIR = $arg;
          }
      }
  }
  
sub usage()
{
    @string = split(/\\/,$0);

  print qq{

  Version $script_version

  Usage: $string[$#string] [options] <log folder>

  This program concatenates the target log type into 

  The following file is generated:
  =================================== 
  concat_<log_type>.txt

  Options:
  
  log_type:  one of the following selections. main is selected by default
    -k    kernel logs.
    -m    main logs.
    -r    radio logs.
    -s    system logs.
    
    -b <N>  where N is the desired boot cycle. If not provided, all will be concatenated.
  };
  exit();
}

sub rev_numerically { $b <=> $a;}
sub numerically { $a <=> $b;}