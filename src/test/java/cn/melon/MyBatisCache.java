package cn.melon;

import cn.melon.dao.PersonMapper;
import cn.melon.dao.UserMapper;
import cn.melon.model.Person;
import cn.melon.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

public class MyBatisCache {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        String resource = "mybatisConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * demo1
     * 两个不同的session获取同一个user
     */
    @Test
    public void test1() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession1.getMapper(UserMapper.class);


        User user1 = userMapper1.getOneUser2(1);
        User user2 = userMapper2.getOneUser2(1);

        sqlSession1.close();
        sqlSession2.close();
    }

    /**
     * demo2
     * 同一个session，同一个user， 中间清空缓存
     * 两次查询
     */
    @Test
    public void test2() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);

        User user1 = userMapper1.getOneUser2(1);

        sqlSession1.clearCache();

        User user2 = userMapper1.getOneUser2(1);

        sqlSession1.close();
    }

    /**
     * demo3
     * 同一个session，同一个user， 中间存在增删改
     * 两次查询 (增删改会刷新缓存)
     */
    @Test
    public void test3() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);

        User user1 = userMapper1.getOneUser2(1);

        User temp = new User();
        temp.setUsername("瓜");
        temp.setPassword("dwj");
        temp.setState(1);
        temp.setRegDate(new Date(new java.util.Date().getTime()));
        userMapper1.addOneUser(temp);

        User user2 = userMapper1.getOneUser2(1);

        sqlSession1.commit();
        sqlSession1.close();
    }
//    2020-05-03 10:23:18,601 DEBUG ==>  Preparing: select * from users where id = ?
//    2020-05-03 10:23:18,631 DEBUG ==> Parameters: 1(Integer)
//    2020-05-03 10:23:18,671 DEBUG <==      Total: 1
//    2020-05-03 10:23:18,672 DEBUG ==>  Preparing: insert into users(username, password, reg_date, state) values (?, ?, ?, ?)
//    2020-05-03 10:23:18,675 DEBUG ==> Parameters: 瓜(String), dwj(String), 2020-05-03(Date), 1(Integer)
//    2020-05-03 10:23:18,680 DEBUG <==    Updates: 1
//    2020-05-03 10:23:18,681 DEBUG ==>  Preparing: select * from users where id = ?
//    2020-05-03 10:23:18,681 DEBUG ==> Parameters: 1(Integer)
//    2020-05-03 10:23:18,683 DEBUG <==      Total:


    /**
     * demo4
     * 二级缓存
     * 两次查询 (增删改会刷新缓存) 这个缓存是一级和二级缓存
     */
    @Test
    public void test4() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);

        User user1 = userMapper1.getOneUser2(1);

        User temp = new User();
        temp.setUsername("瓜");
        temp.setPassword("dwj");
        temp.setState(1);
        temp.setRegDate(new Date(new java.util.Date().getTime()));
        userMapper1.addOneUser(temp);
        sqlSession1.commit();
        sqlSession1.close();


        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.getOneUser2(1);
        sqlSession2.close();

    }

    /**
     * demo5
     * 二级缓存
     * 一次查询
     */
    @Test
    public void test5() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = userMapper1.getOneUser2(1);
        sqlSession1.close();


        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.getOneUser2(1);
        sqlSession2.close();
    }
}
