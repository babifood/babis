cd d:\babis
cd d:\babis\build\classes
jar cvf seeyon_apps_shbbplugin.jar *
xcopy seeyon_apps_shbbplugin.jar babis\安装包\seeyon\WEB-INF\lib /S /Y
CD D:\babis\WebContent
xcopy WEB-INF\apps_res  babis\安装包\seeyon\apps_res /S /Y
xcopy WEB-INF\cfgHome  babis\安装包\seeyon\WEB-INF\cfgHome /S /Y
xcopy WEB-INF\jsp  babis\安装包\seeyon\WEB-INF\jsp /S /Y
xcopy WEB-INF\tags  babis\安装包\seeyon\WEB-INF\tags /S /Y
xcopy WEB-INF\lib  babis\安装包\seeyon\WEB-INF\lib /S /Y

