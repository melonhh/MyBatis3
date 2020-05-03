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
import java.util.List;

public class DynamicSQLTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        String resource = "mybatisConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * if 标签  where 标签 foreach 标签
     * getUserByLike
     */
    @Test
    public void test1() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<User> users = userMapper.getUserByLike("%m%");
            System.out.println(users);

            List<User> users2 = userMapper.getUserByLikeWhere("%sb%");
            System.out.println(users2);

            List<User> users3 = userMapper.getUserByLikeTrim("%m%");
            System.out.println(users3);

            int[] ids = {1,3,4,6,5,7};
            List<User> users4 = userMapper.getUserByLikeForEach(ids);
            System.out.println(users4);
        }
    }


}
