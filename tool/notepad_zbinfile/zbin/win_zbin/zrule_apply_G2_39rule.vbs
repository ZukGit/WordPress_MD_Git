win_zbinPath = "%userprofile%"+"\Desktop\zbin\win_zbin"
zbinPath = "%userprofile%"+"\Desktop\zbin\"
docpath = "%userprofile%"+"\Documents\"

command =  "cmd /c "+ zbinpath +"\G2.bat  "+docpath+"  _39_   mdname_true  logfile_true "
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh ! command="+command)