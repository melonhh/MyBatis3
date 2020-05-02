package cn.melon;

import cn.melon.dao.UserMapper;
import cn.melon.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class mybatisTest {

    /**
     * 入门案例
     * 通过Mapper直接与SQL语句关联
     */
    @Test
    public void testOne() throws IOException {
        // 获取配置文件的元数据
        String resource = "mybatisConfig.xml";
        InputStream inputstream = Resources.getResourceAsStream(resource);
        // 获取核心SqlSessionFactory实例，每个MyBatis应用都有一个SqlSessionFactory实例
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            User user = session.selectOne("cn.melon.dao.UserMapper.selectOneUser1", 1);
            System.out.println(user);
        }
    }


    /**
     * 入门升级案例
     * 面向接口式编程
     * （在dao层创建接口）
     */
    @Test
    public void testTwo() throws IOException {
        // 1,获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatisConfig.xml"));

        // 2,拿到sqlSession接口实例
        try(SqlSession session = sqlSessionFactory.openSession()) {
            // 3,获取接口的实现类对象，不需要我们实现接口，mybatis会自动帮我们创建一个代理
            UserMapper mapper = session.getMapper(UserMapper.class);
            System.out.println(mapper); // org.apache.ibatis.binding.MapperProxy@32eff876

            User oneUser = mapper.getOneUser2(1);
            System.out.println(oneUser); // User{id=1, username='melon', password='dwj123##', regDate=2020-05-01, state=1}
        }


    }

}
