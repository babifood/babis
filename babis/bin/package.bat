cd d:\babis
cd d:\babis\build\classes
jar cvf seeyon_apps_shbbplugin.jar *
xcopy seeyon_apps_shbbplugin.jar babis\��װ��\seeyon\WEB-INF\lib /S /Y
CD D:\babis\WebContent
xcopy WEB-INF\apps_res  babis\��װ��\seeyon\apps_res /S /Y
xcopy WEB-INF\cfgHome  babis\��װ��\seeyon\WEB-INF\cfgHome /S /Y
xcopy WEB-INF\jsp  babis\��װ��\seeyon\WEB-INF\jsp /S /Y
xcopy WEB-INF\tags  babis\��װ��\seeyon\WEB-INF\tags /S /Y
xcopy WEB-INF\lib  babis\��װ��\seeyon\WEB-INF\lib /S /Y

