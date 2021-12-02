winzbinPath = "%userprofile%"+"\Desktop\zbin\win_zbin"
command = "cmd /c "+ winzbinPath +"\zrule_apply_G2_39rule.vbs  "
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
'  runas /user:administrator cmd
' copy %userprofile%\Desktop\zbin\B4_startup.vbs  "C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\"
