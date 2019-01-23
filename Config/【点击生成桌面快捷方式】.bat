@echo off
if %cd%==%cd:~,3% echo 当前目录已经是%cd:~,1%盘的根目录！&goto end
cd..
set "bd=%cd%"
cd..
set "bbd=%cd%"
::call echo 当前bat文件上一级目录是：%bbd%\%%bd:%bbd%\=%%
:end

set "SrcFile=%bbd%\%%bd:%bbd%\=%%\AutomationTestSystem"
set "LnkFile=自动化测试系统.lnk"
set "IconPath=%~dp0src\main\resources\image\LoginPane\Logo\Logo.ico"
call :CreateShort "%SrcFile%" "%LnkFile%" "%IconPath%"

echo 桌面快捷方式创建成功！
pause
goto :eof

:CreateShort
mshta VBScript:Execute("Set a=CreateObject(""WScript.Shell""):Set b=a.CreateShortcut(a.SpecialFolders(""DeskTop"") & ""\%~2""):b.TargetPath=""%~1"":b.IconLocation=""%~3"":b.Save:close")

::Arguments              目标程序参数
::Description            快捷方式备注
::FullName               返回快捷方式完整路径
::Hotkey                 快捷方式快捷键
::IconLocation           快捷方式图标，不设则使用默认图标
::TargetPath             目标
::WindowStyle            窗口启动状态
::WorkingDirectory       起始位置

