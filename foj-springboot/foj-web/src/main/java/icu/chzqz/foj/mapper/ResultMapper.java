package icu.chzqz.foj.mapper;

import icu.chzqz.foj.entity.Judge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ResultMapper {

    @Select("select * from judge where id = #{id}")
    public Judge selectById(Long id);
}
