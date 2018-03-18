# 微信点餐
```
* yml文件在eclipse中无效，还未找到解决方法，暂时用properties代替
* ddl-auto=create 会清空数据重新生成
* @transaction 加在测试中自动回滚
* @Data lombok包提供，可以省略get/set方法(cmd 可以直接用，IDEA需要装插件才可以)
* productInfo 和 前端显示(为了安全需要隐藏一部分字段)，新建字段producInfoVO
* 书库的查询不要放在for循环中，数据库查询开销大

```