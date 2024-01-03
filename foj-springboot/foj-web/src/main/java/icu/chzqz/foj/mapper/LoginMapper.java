package icu.chzqz.foj.mapper;

import icu.chzqz.foj.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select * from user where name= #{username}")
    User login(String username);
}
