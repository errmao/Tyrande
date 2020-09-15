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

## 版本说明

### v1.0.0

- 基础模块（系统管理/通用模块/Security安全模块）
- 整合基于 `xxl` 的调度系统

### v1.0.1

- 整合工作流 `Flowable-modeler-ui` 设计器