# dubbo-JT

#### 介绍
京淘电商

#### 软件架构
技术栈：Spring + Spring MVC + MyBatis + MySQL + EasyUI + Dubbo + redis + nginx


#### 配置教程


图片服务器：   
在jt_manage/src/main/resources/properties/image.properties中配置图片服务器地址和url


redis：   
在jt_common的src/main/resources/redis.properties文件中配置redis地址  
在jt_common的src/main/java/com/jt/aop/CacheAOP.java中选中你添加的redis源类型   

zookeeper：   
在jt_manage、jt_order、jt_soo中都要配置zookeeper地址（本项目只使用了注册中心功能）
     
数据源:     
在jt_manage、jt_order、jt_soo中都要配置mysql数据源


#### 使用说明



#### 参与贡献

1、使用Dubbo框架搭建微服务系统   
2、实现后台对商品的增删改查    
3、使用redis集群和AOP对商品分类菜单进行缓存   
4、使用nginx实现商品后台集群的负载均衡和高可用。   
5、实现mysql的读写分离和高可用，使用mycat搭建mysql集群    
6、使用redis、Interceptor、和cookie实现单点登录和权限控制    




