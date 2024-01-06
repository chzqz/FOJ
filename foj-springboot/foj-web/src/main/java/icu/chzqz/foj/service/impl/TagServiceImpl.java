package icu.chzqz.foj.service.impl;

import icu.chzqz.foj.dto.TagDTO;
import icu.chzqz.foj.entity.Tag;
import icu.chzqz.foj.mapper.TagMapper;
import icu.chzqz.foj.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;
    @Override
    public List<Tag> tags(TagDTO tagDTO) {
        return tagMapper.list(tagDTO);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.selectById(id);
    }
}
