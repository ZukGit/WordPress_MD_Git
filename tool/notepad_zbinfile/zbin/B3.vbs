Set args = WScript.Arguments
If args.Count = 2 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)

command = "cmd /c "+ zbinpath +"\B3.bat  " +zbinpath+"\  "+textpath
DIM objShell
set objShell=wscript.createObject("wscript.shell")
objShell.SendKeys "^c"
iReturn=objShell.Run(command, 0, TRUE)
End If