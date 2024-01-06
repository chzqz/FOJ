package icu.chzqz.foj.mapper;

import icu.chzqz.foj.entity.Testcase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestcaseMapper {
    @Select("select * from testcases where q_id = #{qid}")
    List<Testcase> selectByQId(Long qid);
}
