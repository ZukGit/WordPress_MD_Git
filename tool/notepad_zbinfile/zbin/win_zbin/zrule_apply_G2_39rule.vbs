win_zbinPath = "%userprofile%"+"\Desktop\zbin\win_zbin\"
zbinPath = "%userprofile%"+"\Desktop\zbin\"
docpath = "%userprofile%"+"\Documents\"

command =  "cmd /c "+ zbinpath +"G2.bat  "+docpath+"  _39_   bootup_true  mdname_true  logfile_true > %userprofile%\Desktop\zbin\G2_Monitor_Log.txt"
DIM objShell

set objShell=wscript.createObject("wscript.shell")
command_real=objShell.ExpandEnvironmentStrings(command)
iReturn=objShell.Run(command_real, 0, TRUE)
WScript.Echo("Please Refresh  ! command_real="+command_real )

