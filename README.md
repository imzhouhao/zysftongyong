### 项目介绍

1. zysf-api:后端提供API的项目
2. zysf-web:前端展示数据的项目
3. 项目使用的数据库是mysql——现在数据有限，直接查看数据库获取数据
数据库在deploy/data下面
### 项目打包

项目打包也分为前端打包和后端打包，后端是springboot maven项目，前端是vue项目

#### 后端部署
0. 安装了基本的java的开发环境，比如jdk等
##### 部署方式1
1. 将data/zysf.sql文件导入到数据库，并修改相关的数据库的配置——config/application-dev.yml里面
2. 可以直接使用deploy文件夹下面的jar包即可：java -jar zysf-api.jar，这种方式启动可以看到输出，可以测试时使用
3. 后台方式启动：nohup java -jar zysf-api.jar > zysf-api.log &

##### 部署方式2
1. 开发方式将项目导入；
2. 修改zysf-api项目resource下面的配置，然后打包；
3. 而后按照部署方式1启动即可；

#### 前端部署：
0. 安装了node与vue-cli
1. 进入zysf-web目录，运行npm install安装依赖；
2. 安装好后可以使用npm run dev查看效果——如果启动了后台可以看到数据管理和展示图的效果；
3. 生产打包之前，需要修改相关的请求api的配置文件，在src/config/index.js里面进行修改，
将生产上要用的机器ip和端口修改好。
4. 运行npm run build命令来打包，打包好后悔生成dist目录，这个目录下便是全部的生产下用的文件
5. 简单查看效果：安装了http-server的可以直接在dist目录下运行：http-server；
6. 生产部署使用nginx，下载nginx,nginx里面的部分配置如下
    ```
      location / {
                index index.html;
                try_files $uri $uri/ @router;
                index index.html;
                gzip_static on; #开启静态压缩文件.gz的返回
        }
    
        location @router {
            rewrite ^.*$ /index.html last;
        }

    ```
