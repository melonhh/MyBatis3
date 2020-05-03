package cn.melon.dao;

import cn.melon.model.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    User getOneUser2(int id);
    boolean addOneUser(User user);
    int updateUser(User user);
    void deleteUser(int id);

    List<User> getSomeUsers(@Param("start_id") int start_id, @Param("end_id") int end_id);
    List<User> getAllUsers();

    Map<String, Object> getMapUserById(int id);

    @MapKey("id")
    Map<Integer, User> getMapKeyUserById(int id);
}

/**
 * 增删改是可以直接设置放回值的, int  long  boolean
 */
