@echo off
xcopy /y %~dp0\src\main\resources %~dp0\Config\src\main\resources /e
xcopy /y %~dp0\Config %~dp0\target\jfx\app /e
echo 文件拷贝成功！

md %~dp0\AutomationTestSystem\bundles
start %~dp0\AutomationTestSystem\bundles
echo 【目录打开成功,别关闭，程序生成中...】

echo 开始执行打包EXE程序操作！
javafxpackager -deploy -native exe -srcdir target\jfx\app -outdir AutomationTestSystem -name AutomationTestSystem -outfile AutomationTestSystem -appclass AutomationTestSystem.StartClient
echo 打包EXE程序成功！



