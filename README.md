MyBatis3 官方中文文档：<https://mybatis.org/mybatis-3/zh/dynamic-sql.html>

## MyBatis3核心配置文件  
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

## Mapper XML配置文件（SQL 映射文件）  
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
* 使用collection标签分步查询集合

## MyBatis缓存
>### 一级缓存
>* 默认开启
>* 进行增删改操作会刷新缓存

>### 二级缓存
>* 默认关闭
>* 基于namespace级别的缓存，也就是一个名称空间对应一个缓存，一般情况下一个mapper映射文件中所有的查询都会共享一个二级缓存空间
>* 在二级缓存开启的情况下，一级缓存里的数据会在**清空**或**提交**之前转存到二级缓存
>* session缓存中的对象统一放在一个map对象中，转入二级缓存时，依类别存入不同的map对象中
>* 对象必须可序列化，因为缓存不够时需要向磁盘转移
>
>在Mapper中`<cache/>`的意义：
>* 映射语句文件中的所有 select 语句将会被缓存
>* 映射语句文件中的所有 insert,update 和 delete 语句会刷新缓存 （一级和二级缓存都被刷新）
>* 缓存会使用 Least Recently Used(LRU,最近最少使用的)算法来收回
>* 根据时间表(比如 no Flush Interval,没有刷新间隔), 缓存不会以任何时间顺序来刷新
>* 缓存会存储列表集合或对象(无论查询方法返回什么)的1024个引用
>* 缓存会被视为是 read/write(可读/可写)的缓存,意味着对象检索不是共享的,而 且可以安全地被调用者修改,而不干扰其他调用者或线程所做的潜在修改
>
>`<cache/>`中相关属性：
>* eviction（回收策略）：
>   * LRU --- 最近最少使用：最长时间不被使用的对象
>   * FIFO
>   * SOFT --- 软引用：移除基于垃圾回收器状态和软引用规则的对象
>   * WEAK --- 弱引用：比软引用更积极
>* flushInterval（刷新间隔，默认不设置）
>* size（引用数目，默认1024）
>* readOnly（默认false）：
>   * true：（只读）给调用者返回缓存对象的相同实例，快
>   * false：（读写）通过序列化返回缓存对象的拷贝，慢，但安全
>
>其他设置：
>* 每个select标签可以设置 useCache 使用/不适用 二级缓存
>* 增删改查 标签可以设置 flushCache （比较特殊）
>   * true：刷新，一级 和 二级 缓存
>   * false：表示禁用所有缓存
>
>使用第三方缓存框架ehcache
>* 回收策略：
>   * FIFO
>   * LFU: 最少命中
>   * LRU（默认）：最近最少
>* `<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>`

### 动态SQL
* if
* where(trim set)
    * where 能去掉第一个条件多出来的 and / or
    * trim
        * prefix属性 --- 前缀  比如 where
        * prefixOverrides属性  --- 需要干掉的第一个字符串 比如and or
        * suffix属性 --- 后缀
        * suffixOverrides属性 --- 需要刚掉的最后一个字符串 
    * set --- 更新使使用
        * 比如更新传入参数中不为null的属性，其他属性不更新
        * 可在set中加入if标签
* choose(when otherwise)
    * 类似于switch case
* foreach
    * 小批量操作
    * collection属性 --- 参数为数组， 集合， map时的参数名
    * item属性 --- 参数的迭代值
    * separator属性 --- 每个值之间的连接符 "," 使用，连接
    * open属性 --- open="("
    * close属性 --- close=")" 构造（，，，，）结构
    * index属性 --- 该值为下标或key
* bind（绑定）
    * 相当于二次封装
    * name属性 --- 封装后的别名
    * value属性 --- 对传递过来的属性二次加工
* sql 和 include（提公因式）
    * sql标签内也可以使用动态sql
    * include标签内可以使用子标签property给sql传值，但是sql标签里只能用${}接收（即只能拼接）
* 内置属性：
    * _databaseId --- 对应于databaseIdProvider 里定义的别名
    * _parameter --- 也就是封装参数的map对象的引用
        * 通过_parameter变量来判断参数是否为空

