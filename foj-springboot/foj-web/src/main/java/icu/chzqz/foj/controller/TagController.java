package icu.chzqz.foj.controller;

import icu.chzqz.foj.dto.TagDTO;
import icu.chzqz.foj.entity.Tag;
import icu.chzqz.foj.result.Result;
import icu.chzqz.foj.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class TagController {

    @Autowired
    TagService tagService;

    /**
     * 获取标签列表
     * @return
     */
    @GetMapping("/user/tags/list")
    public Result<List<Tag>> tags(TagDTO tagDTO){
        log.info("查询标签列表: {}", tagDTO);
        List<Tag> list = tagService.tags(tagDTO);
        return Result.success(list);
    }

    /**
     * 通过id查询标签
     */
    @GetMapping("/user/tag/{id}")
    public Result<Tag> getTag(@PathVariable Long id){
        log.info("查询标签: {}", id);
        Tag tag = tagService.getTag(id);
        return Result.success(tag);
    }

    @PostMapping("/admin/tags")
    public Result<Long> addTag(@RequestBody TagDTO tagDTO){
        log.info("新增标签: {}", tagDTO);
        Long id = tagService.addTag(tagDTO);
        return Result.success(id);
    }

    @DeleteMapping("/admin/tags")
    public Result deleteTag(@RequestBody Long id){
        log.info("删除标签: {}", id);
        tagService.deleteTag(id);
        return Result.success();
    }

    @PutMapping("admin/tags")
    public Result modifyTag(@RequestBody Tag tag){
        log.info("修改标签: {}", tag);
        tagService.modifyTag(tag);
        return Result.success();
    }
}
