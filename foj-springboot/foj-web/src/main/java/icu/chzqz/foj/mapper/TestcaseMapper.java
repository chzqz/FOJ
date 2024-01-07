package icu.chzqz.foj.mapper;

import icu.chzqz.foj.entity.Testcase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.List;

@Mapper
public interface TestcaseMapper {
    @Select("select * from testcases where q_id = #{qid} order by id")
    List<Testcase> selectByQId(Long qid);

    @Select("select * from testcases where q_id = #{qid} and status = #{status}")
    List<Testcase> selectByQIdStatus(Long qid, Integer status);

    void deleteByQids(List<Long> ids);

    @Insert("insert into testcases (q_id, input, output, status) VALUES (#{qId}, #{input}, #{output}, #{status})")
    void insert(Testcase testcase);

    @Delete("delete from testcases where id = #{id}")
    void deleteById(Long id);

    @Select("select * from testcases where id =#{id}")
    Testcase selectById(Long id);

    @Select("select * from testcases where q_id = #{qid}")
    List<Testcase> list(Long qid);
}
