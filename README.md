# 项目 - 拓扑图

![微服务-拓扑图](https://www.liupeng.cloud/files/picture/microservices/topology-diagram/微服务-拓扑图.gif)

------

# 项目描述

项目使用 [SpringCloud](https://spring.io/projects/spring-cloud)、[SpringCloud Alibaba](https://spring.io/projects/spring-cloud-alibaba)
开发分布式微服务

Nacos 作为服务注册与发现管理所有应用服务、
Sentinel 流量控制、
Seata 分布式事务、
Dobbu 数据服务调用、
SkyWalking 服务监视、
RocketMQ 分布式消息中间件

------

# 环境要求

操作系统：Linux（[AlmaLinux](https://almalinux.org/zh-hans) 推荐），window10+

运行环境：[JDK 8+](https://www.oracle.com/cn/java/technologies/java-se-glance.html)，[Maven](https://maven.apache.org/download.cgi)

开发工具：[IntelliJ IDEA](https://www.jetbrains.com/zh-cn/idea/download)，[Navicat](https://www.navicat.com.cn/products)

[阿里巴巴开源镜像站](https://developer.aliyun.com/mirror)

------

# 项目目录

├── spring-cloud-alibaba  
├  
├── # 公共 API  
├── spring-cloud-alibaba-commons-api  
├  
├── # 实体类 API  
├── spring-cloud-alibaba-commons-domain  
├  
├── # 统一依赖管理  
├── spring-cloud-alibaba-dependencies   
├  
├── # Dubbo 接口模块  
├── spring-cloud-alibaba-interface-dubbo  
├  
├── # OpenFiegn 接口模块  
├── spring-cloud-alibaba-interface-openfeign  
├  
├── # 认证 Dubbo 消费数据服务  
├── spring-cloud-alibaba-dubbo-consumer-auth-1120  
├  
├── # 仓储 Dubbo 消费数据服务    
├── spring-cloud-alibaba-dubbo-consumer-storage-1121  
├  
├── # 订单 Dubbo 消费数据服务    
├── spring-cloud-alibaba-dubbo-consumer-order-1122  
├  
├── # 账户 Dubbo 消费数据服务    
├── spring-cloud-alibaba-dubbo-consumer-account-1123  
├  
├── # 支付 Dubbo 消费数据服务    
├── spring-cloud-alibaba-dubbo-consumer-pay-1124  
├  
├── # 物流 Dubbo 消费数据服务    
├── spring-cloud-alibaba-dubbo-consumer-logistics-1125  
├  
├── # 认证 Dubbo 提供数据服务  
├── spring-cloud-alibaba-dubbo-provider-auth-1020  
├  
├── # 仓储 Dubbo 提供数据服务    
├── spring-cloud-alibaba-dubbo-provider-storage-1021  
├  
├── # 订单 Dubbo 提供数据服务    
├── spring-cloud-alibaba-dubbo-provider-order-1022  
├  
├── # 账户 Dubbo 提供数据服务    
├── spring-cloud-alibaba-dubbo-provider-account-1023  
├  
├── # 支付 Dubbo 提供数据服务    
├── spring-cloud-alibaba-dubbo-provider-pay-1024  
├  
├── # 物流 Dubbo 提供数据服务    
├── spring-cloud-alibaba-dubbo-provider-logistics-1025  
├  
├── # 网关服务  
├── spring-cloud-alibaba-service-gateway-9527  
├  
├── # 认证服务  
├── spring-cloud-alibaba-service-auth-2020  
├  
├── # 仓储服务  
├── spring-cloud-alibaba-service-storage-2021  
├  
├── # 订单服务  
├── spring-cloud-alibaba-service-order-2022  
├  
├── # 账户服务  
├── spring-cloud-alibaba-service-account-2023  
├  
├── # 支付服务  
├── spring-cloud-alibaba-service-pay-2024  
├  
├── # 物流服务  
├── spring-cloud-alibaba-service-logistics-2025  
├  
├── # 消息中间件服务  
├── spring-cloud-alibaba-service-rocketmq-3020  
├  
└── README.md

------

更多项目描述请访问 [Wiki](https://gitee.com/liupeng_gitee/spring-cloud-alibaba/wikis)

------

# 第三方软件

服务注册与发现：[Alibaba Nacos](http://nacos.io/zh-cn)

流量治理：[Alibaba Sentinel](https://sentinelguard.io/zh-cn/index.html)

分布式事务：[Alibaba Stata](http://seata.io/zh-cn/index.html)

消息中间件：[Alibaba RocketMQ](https://rocketmq.apache.org/zh)

应用程序性能监视工具：[SkyWalking](https://skywalking.apache.org/downloads)

------

# 第三方软件安装部署

> 提示：以下内容未特殊标注，默认在 Linux 系统操作。

## Nacos 动态服务注册与发现

### 一、nacos 下载

[nacos 官方文档](http://nacos.io/zh-cn/docs/v2/what-is-nacos.html)

[nacos 官方下载](https://github.com/alibaba/nacos/releases)

> 提示：Linux 下载 [.tar.gz] 包（推荐）、windows 下载 [.zip] 包

### 二、nacos 安装

**1、MySQL 创建 nacos 数据库**

- 解压 nacos 压缩包，进入 conf 目录。
- 导入 **mysql-schema.sql** 数据库

**2、配置 MySQL 数据库**

打开 **application.properties** 文件修改配置

**修改前**

```properties
# spring.sql.init.platform=mysql

### Count of DB:
# db.num=1

### Connect URL of DB:
# db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
# db.user.0=nacos
# db.password.0=nacos
```

**修改后**

```properties
spring.sql.init.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=root
```

1.2、**nacos 集群配置**

- 备份 **cluster.conf.example** 文件
- 备份后的文件名 **cluster.conf**
- 配置 **cluster.conf** 文件
- 添加集群 **ip:post**

> 注意：每个 **ip:post** 下 **nacos** 都需要修改 **cluster.conf** 文件

### 三、nacos 启动

> Nacos的运行建议至少在2C4G 60G的机器配置下运行。

进入 nacos/bin 目录

**1、单机启动**

**Linux/Unix/Mac**

启动命令（standalone 代表着单机模式运行，非集群模式）:

```shell
sh startup.sh -m standalone
```

如果使用的是 ubuntu 系统，或者运行脚本报错提示[[符号找不到，可尝试如下运行：

```shell
bash startup.sh -m standalone
```

**Windows**

启动命令（standalone 代表着单机模式运行，非集群模式）:

```shell
startup.cmd -m standalone
```

**2、关闭服务器**

**Linux/Unix/Mac**

```shell
sh shutdown.sh
```

**Windows**

```shell
shutdown.cmd
```

或者双击 shutdown.cmd 运行文件

**3、伪集群启动**

```shell
sh startup.sh -p 端口号
```

**4、查看启动**

查看 nacos 启动进程

```shell
ps -ef|grep nacos
```

查看集群启动个数

```shell
ps -ef|grep nacos|grep -v grep|wc -l
```

### 四、nacos 访问

打开浏览器输入 nacos 网址

```text
http://127.0.0.1:8848/nacos
```

******

## Sentinel 流量治理组件

### 一、sentinel 下载

[Sentinel 官方文档](https://sentinelguard.io/zh-cn/docs/introduction.html)

[Sentinel 官网下载](https://github.com/alibaba/Sentinel/releases)

### 二、sentinel 安装

下载 .jar 文件（推荐）

[Sentinel 源码下载安装](https://github.com/alibaba/Sentinel/tree/master/sentinel-dashboard)

解压并进入 sentinel 使用 maven 打包

```shell
mvn clean package
```

### 三、sentinel 启动

> 注意：启动 Sentinel 控制台需要 JDK 版本为 1.8 及以上版本。

1、直接启动 .jar

```shell
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=127.0.0.1:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
```

2、源码编译后启动

```shell
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=127.0.0.1:8080 -Dproject.name=sentinel-dashboard -jar target/sentinel-dashboard.jar
```

### 四、sentinel 访问

打开浏览器输入 sentinel 网址

```text
http://127.0.0.1:8080
```

******

## Seata 分布式事务

### 一、seata 下载

[Seata 官方文档](http://seata.io/zh-cn/docs/overview/what-is-seata.html)

[Seata 官网下载](https://github.com/seata/seata/releases)

### 二、seata 安装

**1、MySQL 创建 seata 数据库**

- 解压 seata 并进入 seata/script/server/db 目录
- 导入 **mysql.sql** 数据库

**2、修改 application.yml 配置文件**

进入 conf 目录，以下是 application.yml 修改后配置

```yaml
# seata 可视化 web 界面账号密码
console:
  user:
    username: seata
    password: seata

seata:
  config:
    # support: nacos, consul, apollo, zk, etcd3
    type: nacos  # 指定配置中心为 nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      namespace: alibaba-seata
      group: SEATA_GROUP
      data-id: seata-server.properties # 主要修改持久化链接配置
  registry:
    # support: nacos, eureka, redis, zk, consul, etcd3, sofa
    type: nacos  # 指定注册中心为 nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      namespace: spring-cloud-alibaba  # 需要和业务注册到同一个命名空间
      group: PROD_GROUP
      cluster: microservices-datalayer  # 集群名
  server:
    # service-port: 8091 #If not configured, the default is '${server.port} + 1000'
    vgroup-mapping: {<!--  -->"microservices_tx_group": "microservices-datalayer"}
```

nacos 配置中心添加以下内容  
> Data Id：service.vgroupMapping.microservices_tx_group  
> Group：SEATA_GROUP  
> 配置内容：microservices-datalayer  
> seata-server.properties 模板地址：[config.txt](https://github.com/seata/seata/blob/master/script/config-center/config.txt)  

### 三、seata 启动

进入 seata/bin 目录

```shell
sh seata-server.sh -h 127.0.0.1 -p 8091
```

> 启动 seata 指定 ip，否则可能出现与应用程序不在一个网段。

### 四、seata 访问

打开浏览器输入 seata 网址

```text
http://127.0.0.1:8091
```

******

## RocketMQ 消息中间件

### 一、rocketmq 下载

[RocketMQ 官方文档](https://rocketmq.apache.org/zh/docs)

[RocketMQ 官网下载](https://rocketmq.apache.org/zh/download)

[RocketMQ-Dashboard 下载](https://github.com/apache/rocketmq-dashboard/releases/tag/rocketmq-dashboard-1.0.0)

> 下载二进制包，二进制包是已经编译完成后可以直接运行的，源码包是需要编译后运行的。本项目采用 Binary 下载。

### 二、rocketmq 安装

解压 rocketmq 并打开 bin 目录，根据内存配置，修改占用内存大小。

**1、打开 bin/runserver.sh 文件**

修改前

```shell
JAVA_OPT="${JAVA_OPT} -server -Xms4g -Xmx4g -Xmn2g -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"
```

修改后

```shell
JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx256m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"
```

**2、打开 bin/runserver.sh 文件**

```shell
JAVA_OPT="${JAVA_OPT} -server -Xms8g -Xmx8g"
```

修改后

```shell
JAVA_OPT="${JAVA_OPT} -server Drocketmq.broker.diskSpaceWarningLevelRatio=0.98 -Xms512m -Xmx512m -Xmn512m"
```

**3、Maven build and run RocketMQ-Dashboard**

解压并进入 rocketmq-dashboard

```shell
mvn clean package -Dmaven.test.skip=true
```

### 三、rocketmq 启动

1、进入程序 bin 目录，启动 mqnamesrv

```shell
sh mqnamesrv &
```

2、进入程序 bin 目录，启动 mqbroker

```shell
sh mqbroker -n 127.0.0.1:9876 --enable-proxy &
```

3、进入rocketmq-dashboard/target 目录

```shell
nohup java -jar rocketmq-dashboard-1.0.0.jar --server.port=8080 --rocketmq.config.namesrvAddr=127.0.0.1:9876
```

### 四、rocketmq 访问

打开浏览器输入 rocketmq 网址

```text
http://127.0.0.1:8080
```

******

## SkyWalking 应用程序性能监视工具

### 一、skywalking 下载

[Skywalking 官方文档](https://skywalking.apache.org/docs)

[Skywalking 官网下载](https://skywalking.apache.org/downloads)

### 二、skywalking 安装

[下载 SkyWalking APM](https://www.apache.org/dyn/closer.cgi/skywalking/9.4.0/apache-skywalking-apm-9.4.0.tar.gz)
[下载 Java Agent](https://www.apache.org/dyn/closer.cgi/skywalking/java-agent/8.15.0/apache-skywalking-java-agent-8.15.0.tgz)

分别解压 apache-skywalking-apm 和 apache-skywalking-java-agent

### 三、skywalking 启动

进入 apache-skywalking-apm/bin 目录

```shell
sh startup.sh
```

### 四、skywalking 访问

打开浏览器输入 skywalking 网址

```text
http://127.0.0.1:8080
```

### 五、IntelliJ IDEA 配置 skywalking

1、修改启动参数：Run --> Edit Configurations

VM options

```shell
-javaagent:D:\skywalking-agent\skywalking-agent.jar -Dskywalking.agent.service_name=微服务名称，可以是中文
```

> 注意：解压后 skywalking-agent 不要单独拿出来 skywalking-agent.jar 否则会报错。

Environment variables

```shell
SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800
```
ip地址为 apache-skywalking-apm 所在服务器。

### 六、Java 启动项目配置 skywalking

```shell
java -javaagent:/home/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=微服务名称 -Dskywalking.collector.backend_service=127.0.0.1:11800 -jar microservices.jar
```

> -javaagent：/skywalking-agent 和项目需在同一台服务器
> -Dskywalking.collector.backend_service：apache-skywalking-apm 所在服务器 ip

### 七、Skywalking 配置 Gateway 插件

Skywalking 默认是不支持 Spring Cloud Gateway 网关服务的，需要手动将 Gateway 的插件添加到 Skywalking 启动依赖 jar 中。

解压 apache-skywalking-java-agent 包，进入 optional-plugins 目录下包含以下三个 gateway 包

> apm-spring-cloud-gateway-2.0.x-plugin-8.14.0.jar
> apm-spring-cloud-gateway-2.1.x-plugin-8.14.0.jar
> apm-spring-cloud-gateway-3.x-plugin-8.14.0.jar

根据 Spring Cloud Gateway 的版本选择对应的插件，不要同时使用所有版本的插件。

**具体操作方法**

将对应的 gateway jar 包 从 optional-plugins 目录复制到 plugins 目录中。

------