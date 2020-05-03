MyBatis3 官方中文文档：<https://mybatis.org/mybatis-3/zh/dynamic-sql.html>

MyBatis3核心配置文件  
>文档的顶层结构如下：
* configuration 配置
* properties 属性 (读取外面属性文件，但数据源一般交给spring管理)
* settings 设置 (极其重要，会改变MyBatis运行时行为)
* typeAliases 类型别名 (我觉得还是直接写全类名好)
* typeHandlers 类型处理器
* plugins 插件
* environments 环境 (配置多种不同的环境，例如开发，测试，生产环境)
    * environment 环境变量 (可配置不同数据库环境)
        * transactionManager 事务管理器
        * dataSource 数据源
* databaseIdProvider 数据库厂商标识
* mappers 映射器

Mapper XML配置文件（SQL 映射文件）  
> 顶级元素
* cache --- 给命名空间的缓存配置
* cache-ref --- 其他命名空间的引用
* resultMap --- 最复杂、最强大的元素，描述如何从结果集中来加载对象
* insert --- 映射插入语句
* update --- 映射更新语句
* select --- 映射查询语句

> 获取接口抽象函数参数的规则
* 普通类型:
    * 单参数: #{名称任意} --- 还是和参数一样最好
    * 多参数: MyBatis会将参数封装成一个Map对象
        * 参数1: #{param1} 或者 #{arg0}
        * 参数2: #{param2} 或者 #{arg1}
        * 使用@Param(Value): 在接口方法中,使用注解则在sql中可使用自定义的 #{Value}
        * 将参数设为Map(K,V),则直接通过key访问参数,#{Key}
* POJO参数:
    * 使用: #{属性名} 访问
* List Set参数:
    * 使用: #{Collection[0]} 或者 #{List[0]} 访问
* 数组类型参数:
    * 使用: #{array[0]} 访问
* ${} 和 #{} 的区别
    * ${} 用来拼接
    * /#{} 占位符来实现(PreparedStatement)

>resultType用法
* 指定返回类型
* map
    * 以column - value
    * 以id - 记录
>resultMap用法(不可和resultType同时使用)
* 使用resultMap自定义数据库字段名称和JavaBean的属性名称的对应关系
* 使用resultMap实现关联查询（在hibernate中是自动完成的，Mybatis得我们手工配置）
* 在上面的基础上使用association标签实现关联查询
* 在上面的基础上使用association标签分步实现关联查询