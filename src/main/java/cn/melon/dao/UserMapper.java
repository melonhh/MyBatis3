package cn.melon.dao;

import cn.melon.model.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     *
     * @param id
     * @return
     */
    User getOneUser2(int id);

    /**
     *
     * @param user
     * @return
     */
    boolean addOneUser(User user);

    /**
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除
     * @param id
     */
    void deleteUser(int id);

    /**
     *
     * @param start_id
     * @param end_id
     * @return
     */
    List<User> getSomeUsers(@Param("start_id") int start_id, @Param("end_id") int end_id);

    /**
     *
     * @return
     */
    List<User> getAllUsers();

    /**
     *
     * @param id
     * @return
     */
    Map<String, Object> getMapUserById(int id);

    @MapKey("id")
    Map<Integer, User> getMapKeyUserById(int id);


    /**
     * 模糊查询 if
     * @param username
     * @return
     */
    List<User> getUserByLike(@Param("username") String username);

    List<User> getUserByLikeWhere(@Param("username") String username);

    List<User> getUserByLikeTrim(@Param("username") String username);

    /**
     * in 集合查询
     * @param ids
     * @return
     */
    List<User> getUserByLikeForEach(@Param("ids") int[] ids);
}

/**
 * 增删改是可以直接设置放回值的, int  long  boolean
 */
