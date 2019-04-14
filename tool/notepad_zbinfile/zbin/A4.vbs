Set args = WScript.Arguments
If args.Count = 3 Then

zbinpath= WScript.Arguments(0)
textpath= WScript.Arguments(1)
paramString=WScript.Arguments(2)

command = "cmd /c "+ zbinpath +"\A4.bat  " +zbinpath+"\  "+textpath+"  "+paramString
DIM objShell
set objShell=wscript.createObject("wscript.shell")
iReturn=objShell.Run(command, 0, TRUE)
WScript.Echo("Please Refresh!")
End If