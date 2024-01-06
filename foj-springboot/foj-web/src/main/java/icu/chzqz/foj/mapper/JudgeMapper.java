package icu.chzqz.foj.mapper;

import icu.chzqz.foj.entity.Judge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JudgeMapper {
    @Select("select status from judge where u_id = #{uid} and q_id =#{qid} ")
    List<Integer> selectStatus(Integer uid, Long qid);

    Long insert(Judge judge);
}
