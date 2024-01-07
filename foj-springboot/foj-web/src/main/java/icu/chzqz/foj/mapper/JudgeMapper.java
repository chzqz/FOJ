package icu.chzqz.foj.mapper;

import com.github.pagehelper.Page;
import icu.chzqz.foj.dto.ResultPageDTO;
import icu.chzqz.foj.entity.Judge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JudgeMapper {
    @Select("select status from judge where u_id = #{uid} and q_id =#{qid} ")
    List<Integer> selectStatus(Integer uid, Long qid);

    Long insert(Judge judge);

    @Select("select * from judge where id = #{id}")
    public Judge selectById(Long id);

    Page<Judge> list(ResultPageDTO resultPageDTO);

    void deleteByQids(List<Long> ids);

}
