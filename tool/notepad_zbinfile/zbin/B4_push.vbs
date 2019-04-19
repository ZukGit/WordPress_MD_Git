Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
gitpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\B4_push.bat  " + gitpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
End If