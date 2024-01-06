package icu.chzqz.foj.mapper;

import icu.chzqz.foj.dto.TagDTO;
import icu.chzqz.foj.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper {


    @Select("select t.id as id, t.name as name, t.color as color from tag t, questions_tag q where q.t_id = t.id and q.q_id = #{id}")
    List<Tag> selectByQId(Long id);

    List<Tag> list(TagDTO tagDTO);

    @Select("select * from tag where id = #{id}")
    Tag selectById(Long id);
}
