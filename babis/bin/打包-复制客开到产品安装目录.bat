cd %~dp0
cd ../build/classes
jar cvf seeyon_apps_kkplugin.jar *
xcopy seeyon_apps_kkplugin.jar ..\..\��װ��\seeyon\WEB-INF\lib /S /Y
cd ../../WebContent
xcopy apps_res  ..\��װ��\seeyon\apps_res /S /Y
xcopy common  ..\��װ��\seeyon\common /S /Y
xcopy WEB-INF\cfgHome  ..\��װ��\seeyon\WEB-INF\cfgHome /S /Y
xcopy WEB-INF\jsp  ..\��װ��\seeyon\WEB-INF\jsp /S /Y
xcopy WEB-INF\tags  ..\��װ��\seeyon\WEB-INF\tags /S /Y
xcopy WEB-INF\lib  ..\��װ��\seeyon\WEB-INF\lib /S /Y
xcopy main  ..\��װ��\seeyon\main /S /Y

xcopy H:\workspace\eclipse_4.2_workspace\�Ϻ�_ֱ��_����ʳƷ�������޹�˾_20160722_�ƶ�����\��װ��\seeyon  D:\Seeyon_v56_sp1_babi\ApacheJetspeed\webapps\seeyon  /S /Y

cd ../
cd ../
cd ../
cd ../
cd ../
d:
cd D:/Seeyon_v56_sp1_babi/ApacheJetspeed/bin/
start startup.bat
exit
