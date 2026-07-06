package com.xiaohua.echo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohua.echo.mapper.TagMapper;
import com.xiaohua.echo.model.entity.Tag;
import com.xiaohua.echo.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author qq
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2026-07-06 21:51:15
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}




