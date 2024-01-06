package icu.chzqz.foj.service;

import icu.chzqz.foj.dto.TagDTO;
import icu.chzqz.foj.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * 查询标签列表
     * @param tagDTO
     * @return
     */
    List<Tag> tags(TagDTO tagDTO);

    /**
     * 通过id查询标签
     * @param id
     * @return
     */
    Tag getTag(Long id);
}
