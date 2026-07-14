package com.xiaohua.echo.utils.easyexcel;

/**
 * @description: 转JavaAgent开发加油!
 * @author: XiaoHua
 *
 **/

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.xiaohua.echo.model.once.TagExcelData;
import lombok.extern.slf4j.Slf4j;

/**
 * 最简单的读
 * <p>
 * 1. 创建excel对应的实体对象 参照{@link }
 * <p>
 * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link }
 * <p>
 * 3. 直接读即可
 */
@Slf4j
public class ExcelSimpleRead {

    /**
     * 最简单的读
     */
    public static void main(String[] args) {
        simpleRead();
    }

    public static void simpleRead() {
        // TagDataListener 需要注入 TagService(Spring Bean)，此处仅为示例
        // 实际使用方式参见 ExcelTagTest 集成测试
        // String fileName = "src/main/resources/test_tag_excel.xlsx";
        // EasyExcel.read(fileName, TagExcelData.class, new TagDataListener(tagService)).sheet().doRead();
    }
}

