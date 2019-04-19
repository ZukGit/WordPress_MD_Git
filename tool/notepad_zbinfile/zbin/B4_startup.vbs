'  runas /user:administrator cmd  
' copy %userprofile%\Desktop\zbin\B4_startup.vbs  C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\
zbinPath = "%userprofile%"+"\Desktop\zbin"
command = "cmd /c "+ zbinpath +"\B4.vbs  "
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)