cd %~dp0
cd ../build/classes
jar cvf seeyon_apps_kkplugin.jar *
xcopy seeyon_apps_kkplugin.jar ..\..\安装包\seeyon\WEB-INF\lib /S /Y
cd ../../WebContent
xcopy apps_res  ..\安装包\seeyon\apps_res /S /Y
xcopy common  ..\安装包\seeyon\common /S /Y
xcopy WEB-INF\cfgHome  ..\安装包\seeyon\WEB-INF\cfgHome /S /Y
xcopy WEB-INF\jsp  ..\安装包\seeyon\WEB-INF\jsp /S /Y
xcopy WEB-INF\tags  ..\安装包\seeyon\WEB-INF\tags /S /Y
xcopy WEB-INF\lib  ..\安装包\seeyon\WEB-INF\lib /S /Y
xcopy main  ..\安装包\seeyon\main /S /Y

xcopy H:\workspace\eclipse_4.2_workspace\上海_直销_中饮食品集团有限公司_20160722_移动考勤\安装包\seeyon  D:\Seeyon_v56_sp1_babi\ApacheJetspeed\webapps\seeyon  /S /Y

cd ../
cd ../
cd ../
cd ../
cd ../
d:
cd D:/Seeyon_v56_sp1_babi/ApacheJetspeed/bin/
start startup.bat
exit
