@echo off
xcopy /y %~dp0\src\main\resources %~dp0\Config\src\main\resources /e
xcopy /y %~dp0\Config %~dp0\target\jfx\app /e
echo �ļ������ɹ���

md %~dp0\AutomationTestSystem\bundles
start %~dp0\AutomationTestSystem\bundles
echo ��Ŀ¼�򿪳ɹ�,��رգ�����������...��

echo ��ʼִ�д��EXE���������
javafxpackager -deploy -native exe -srcdir target\jfx\app -outdir AutomationTestSystem -name AutomationTestSystem -outfile AutomationTestSystem -appclass AutomationTestSystem.StartClient
echo ���EXE����ɹ���



