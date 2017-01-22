前提是安装了grunt ，如果没有在cmd 执行 npm install -g grunt-cli 命令
1、进入webapp
2、执行npm install (该命令会根据当前目录下的package.json 文件来执行)
3、安装 npm install bower 或则npm install bower -g
4、执行bower  init 和bower install (该命令是根据bower.json文件来下载项目需要的插件)
5、执行 grunt  (该命令是根据Gruntfile.js 来执行任务的)
6、执行grunt server 启动
under the webapp dir execute grunt serve this will run the webapp under the 9000 port
