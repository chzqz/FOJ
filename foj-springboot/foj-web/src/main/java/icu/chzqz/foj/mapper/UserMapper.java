package icu.chzqz.foj.mapper;

import com.github.pagehelper.Page;
import icu.chzqz.foj.dto.UserPageDTO;
import icu.chzqz.foj.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询头像
     * @param id
     * @return
     */
    @Select("select picture from user where id = #{id}")
    String selectAvatarById(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User selectById(Integer id);

    void update(User user);

    @Select("select * from user where name=#{username}")
    User selectByName(String username);

    @Select("select * from user where email = #{email}")
    User selectByEmail(String email);

    @Insert("insert into user (name, email, picture, password, accepted, experiment, create_date, last_login_time, authority, comment) VALUES(#{name},#{email},#{picture},#{password},#{accepted},#{experiment},#{createDate},#{lastLoginTime},#{authority},#{comment})")
    void insert(User user);

    @Update("update user set password = #{passwd} where id = #{id}")
    void updatePasswdById(Integer id, String passwd);


    Page<User> list(UserPageDTO userPageDTO);
}
