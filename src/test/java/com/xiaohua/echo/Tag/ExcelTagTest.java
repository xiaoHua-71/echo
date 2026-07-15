package com.xiaohua.echo.Tag;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohua.echo.model.entity.Tag;
import com.xiaohua.echo.model.once.TagExcelData;
import com.xiaohua.echo.service.TagService;
import com.xiaohua.echo.utils.easyexcel.TagDataListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @description: TagService.getOne + EasyExcel 导入集成测试
 * @author: XiaoHua
 *
 **/
@SpringBootTest
@ActiveProfiles("test")
public class ExcelTagTest {

    @Autowired
    private TagService tagService;

    private String uniqueTagName;

    @BeforeEach
    public void setUp() {
        uniqueTagName = "测试标签_" + UUID.randomUUID().toString().substring(0, 8);
    }

    @Test
    public void testGetOneNotExists() {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tagName", uniqueTagName);
        Tag result = tagService.getOne(queryWrapper);
        System.out.println("查询不存在的标签结果: " + result);
        Assertions.assertNull(result, "查询不存在的标签应返回 null");
    }

    @Test
    @Transactional
    public void testGetOneExists() {
        // 先插入一条数据
        Tag tag = new Tag();
        tag.setTagName(uniqueTagName);
        tag.setUserId(1L);
        tag.setParentId(0L);
        tag.setIsParent(1);
        tagService.save(tag);

        // 再查询
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tagName", uniqueTagName);
        Tag result = tagService.getOne(queryWrapper);
        System.out.println("查询存在的标签结果: " + result);
        Assertions.assertNotNull(result, "查询存在的标签应返回非 null");
        Assertions.assertEquals(uniqueTagName, result.getTagName());
    }

    @Test
    public void testImportExcelWithListener() {
        // 读取 test_tag_excel.xlsx，通过 TagDataListener 逐行入库
        String fileName = "src/main/resources/test_tag_excel.xlsx";
        TagDataListener listener = new TagDataListener(tagService);
        EasyExcel.read(fileName, TagExcelData.class, listener).sheet().doRead();

        // 验证 Excel 中的第一条数据 "编程" 是否入库成功
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tagName", "编程");
        Tag result = tagService.getOne(queryWrapper);
        System.out.println("Excel 导入后查询结果: " + result);
        Assertions.assertNotNull(result, "Excel 导入后，标签'编程'应在数据库中存在");
        Assertions.assertEquals("编程", result.getTagName());
    }

}
