@echo off
if %cd%==%cd:~,3% echo ��ǰĿ¼�Ѿ���%cd:~,1%�̵ĸ�Ŀ¼��&goto end
cd..
set "bd=%cd%"
cd..
set "bbd=%cd%"
::call echo ��ǰbat�ļ���һ��Ŀ¼�ǣ�%bbd%\%%bd:%bbd%\=%%
:end

set "SrcFile=%bbd%\%%bd:%bbd%\=%%\AutomationTestSystem"
set "LnkFile=�Զ�������ϵͳ.lnk"
set "IconPath=%~dp0src\main\resources\image\LoginPane\Logo\Logo.ico"
call :CreateShort "%SrcFile%" "%LnkFile%" "%IconPath%"

echo �����ݷ�ʽ�����ɹ���
pause
goto :eof

:CreateShort
mshta VBScript:Execute("Set a=CreateObject(""WScript.Shell""):Set b=a.CreateShortcut(a.SpecialFolders(""DeskTop"") & ""\%~2""):b.TargetPath=""%~1"":b.IconLocation=""%~3"":b.Save:close")

::Arguments              Ŀ��������
::Description            ��ݷ�ʽ��ע
::FullName               ���ؿ�ݷ�ʽ����·��
::Hotkey                 ��ݷ�ʽ��ݼ�
::IconLocation           ��ݷ�ʽͼ�꣬������ʹ��Ĭ��ͼ��
::TargetPath             Ŀ��
::WindowStyle            ��������״̬
::WorkingDirectory       ��ʼλ��

