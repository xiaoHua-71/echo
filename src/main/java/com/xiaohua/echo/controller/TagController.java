package com.xiaohua.echo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaohua.echo.common.BaseResponse;
import com.xiaohua.echo.common.ErrorCode;
import com.xiaohua.echo.common.ResultUtils;
import com.xiaohua.echo.exception.BusinessException;
import com.xiaohua.echo.model.entity.Tag;
import com.xiaohua.echo.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签接口
 *
 * @author 小花
 */
@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = {"http://localhost:3000"})
@Slf4j
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 新增标签
     *
     * @param tag
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addTag(@RequestBody Tag tag) {
        if (tag == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean save = tagService.save(tag);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultUtils.success(tag.getId());
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTag(@RequestBody long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = tagService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新标签
     *
     * @param tag
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTag(@RequestBody Tag tag) {
        if (tag == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = tagService.updateById(tag);
        return ResultUtils.success(b);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BaseResponse<Tag> getTagById(@PathVariable("id") Long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Tag tag = tagService.getById(id);
        return ResultUtils.success(tag);
    }

    /**
     * 查询所有数据
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<Tag>> listTag() {
        List<Tag> list = tagService.list();
        return ResultUtils.success(list);
    }

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/{currentPage}/{pageSize}")
    public BaseResponse<Page<Tag>> queryByPage(@PathVariable int currentPage, @PathVariable int pageSize) {
        if (currentPage <= 0 || pageSize <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<Tag> page = new Page<>(currentPage, pageSize);
        tagService.page(page);
        return ResultUtils.success(page);
    }
}
