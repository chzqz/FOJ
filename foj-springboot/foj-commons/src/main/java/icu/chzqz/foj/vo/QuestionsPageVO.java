package icu.chzqz.foj.vo;

import icu.chzqz.foj.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionsPageVO {
    Long id;
    String name;
    Double passRate;
    Integer status;
    List<Tag> tags;
}
