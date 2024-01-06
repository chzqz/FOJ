package icu.chzqz.foj.dto;

import icu.chzqz.foj.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsPageDTO {
    Integer uid;
    Long id;
    String name;
    List<Integer> tags;
    Integer page;
    Integer pageSize;
}
