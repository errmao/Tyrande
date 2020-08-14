FROM java:8

# 作者
MAINTAINER tyrande <errmao@aliyun.com>

# 将jar包添加到容器中并更名为corebusiness.jar
ADD tyrande-starter.jar /data/tyrande-starter.jar

# 设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# 最后/data/corebusiness-0.0.1-SNAPSHOT.jar 是容器中jar包的位置
ENTRYPOINT ["java", "-Dspring.profiles.active=pro","-jar","/data/tyrande-starter.jar"]