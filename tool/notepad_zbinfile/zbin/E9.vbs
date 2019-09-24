zbinPath = "%userprofile%"+"\Desktop\zbin"
command = "cmd /c "+ zbinpath +"\E9.bat"
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
rem WScript.Echo("Please Refresh!")