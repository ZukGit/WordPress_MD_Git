zbinPath = "%userprofile%"+"\Desktop\zbin"
command = "cmd /c "+ zbinpath +"\B4.bat  " +zbinpath 
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")