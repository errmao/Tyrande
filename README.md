# 泰兰德管理系统

前后端分离、父子模块、后台管理系统

## 开发环境说明

使用了 `Window10` 版本的 `Docker Desktop`

> `MySQL`

基于 `Docker` 安装，安装命令如下

```shell script
docker run -d -p 60006:3306 \
-v D:\tools\docker\container\mysql:/var/lib/mysql \
--privileged=true -e MYSQL_ROOT_PASSWORD=123456 --name mysql mysql
```

> `Redis`

```shell script
docker run -d -p 60007:6379  \
-v D:\tools\docker\container\redis\data:/data \
-v D:\tools\docker\container\redis\conf:/etc/redis/redis.conf \
--name redis redis \
--appendonly yes --requirepass "redis123"
```