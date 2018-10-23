# 微信点餐

 ![image](https://github.com/ccyefan/sell/blob/master/doc/img/功能.png)
 ![image](https://github.com/ccyefan/sell/blob/master/doc/img/关系图.png)
 ![image](https://github.com/ccyefan/sell/blob/master/doc/img/表关系.png)
 ![image](https://github.com/ccyefan/sell/blob/master/doc/img/表模型.png)

* yml文件在eclipse中无效，还未找到解决方法，暂时用properties代替
* ddl-auto=create 会清空数据重新生成
* @transaction 加在测试中自动回滚
* @Data lombok包提供，可以省略get/set方法(cmd 可以直接用，IDEA需要装插件才可以)
* productInfo 和 前端显示(为了安全需要隐藏一部分字段)，新建字段producInfoVO
* 书库的查询不要放在for循环中，数据库查询开销大
* 泛型 JpaRepository<OrderMaster, String>
* 枚举类型的使用
* 无法做到 模块化 系统构建 ？学习方法有点问题，需要画个 流程图，总结才是最重要的
* 商品列表展示 功能模块  GET /sell/buyer/product/list 两张表一次性查询出来以后做拼装
* 创建订单 功能模块   扣库存 多线程并发时会出现同时扣库存，也叫超库存用redis的锁来避免
* spring boot 表单验证  BindingResult类
* 创建订单，http://127.0.0.1:8080/sell/buyer/order/create  post
		Content-Type: application/x-www-form-urlencoded
		name=张三&phone=18868822111&address=慕课网总部&openid=ew3euwhd7sjw9diwkq&items=[{     productId: 123456,     productQuantity: 2 }]
* 查询订单，http://127.0.0.1:8080/sell/buyer/order/list?openid=1018302
* 对象 -> json 数据篡改(@jsonSerialize(继承Serialization重写其方法))   date -> Long 除以1000
* 订单详情
	http://127.0.0.1:8080/sell/buyer/order/detail?openid=1018302&orderid=1523285181616248964
* 订单取消
	http://127.0.0.1:8080/sell/buyer/order/cancle	post
	Content-Type: application/x-www-form-urlencoded 
	openid=1018302&orderid=1523285181616248964
* 微信授权和支付 代码省略  返工时候再看
* 订单后端页面
	FreeMarker 遍历集合，根据code获取枚举，分页
* 卖家取消订单    ftl 跳转成功，错误页面，判断字段

* 部署 tomcat ,jar  两种方式
    打包jar
  mvn clean package -Dmaven.test.skip=true
    运行jar	
  javar -jar (-Dserver.port=8090) (-Dspring.profiles.active=prod) sell.jar	
   部署路径：/opt/javaapp/
   后台运行： nohup ......  > tem.txt &
   调回前台: jobs 然后  fg 1 
   后台停止进程：ps -ef|grep sell  然后   kill -9 进程号
   用端口查看进程号：netstat -nlp | grep :9090
  