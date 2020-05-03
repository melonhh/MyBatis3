package cn.melon;

import cn.melon.dao.PersonMapper;
import cn.melon.dao.RolesMapper;
import cn.melon.model.Person;
import cn.melon.model.Roles;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResultMapTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        String resource = "mybatisConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 测试PersonMapper
     * 分步查询
     */
    @Test
    public void test1() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

            Person person = personMapper.getPersonById(1);
            System.out.println(person);
        }
    }

    /**
     * 测试RolesMapper
     * 分步查询
     */
    @Test
    public void test2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            RolesMapper rolesmapper = sqlSession.getMapper(RolesMapper.class);

            Roles roles = rolesmapper.getRolesById(1);
            System.out.println(roles);
        }
    }


    @Test
    public void test3() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            RolesMapper rolesmapper = sqlSession.getMapper(RolesMapper.class);

            Roles roles = rolesmapper.getRolesById(1);
            System.out.println(roles);
        }
    }

}
