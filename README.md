MyBatis3 官方中文文档：<https://mybatis.org/mybatis-3/zh/dynamic-sql.html>

MyBatis3核心配置文件
>文档的顶层结构如下：
>>configuration 配置
>>>properties 属性 (读取外面属性文件，但数据源一般交给spring管理)

>>>settings 设置 (极其重要，会改变MyBatis运行时行为)

>>>typeAliases 类型别名 (我觉得还是直接写全类名好)

>>>typeHandlers 类型处理器

>>>plugins 插件

>>>environments 环境 (配置多种不同的环境，例如开发，测试，生产环境)

>>>>environment 环境变量 (可配置不同数据库环境)

>>>>>transactionManager 事务管理器

>>>>>dataSource 数据源

>>>databaseIdProvider 数据库厂商标识

>>>mappers 映射器