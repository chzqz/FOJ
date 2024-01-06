package icu.chzqz.foj.mapper;

import icu.chzqz.foj.dto.QuestionsPageDTO;
import icu.chzqz.foj.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<Question> list(QuestionsPageDTO questionsPageDTO);

    @Select("select * from questions where id = #{id}")
    Question selectById(Long id);


    void update(Question question);
}
