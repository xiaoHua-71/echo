package com.xiaohua.echo.model.once;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 通过爬取数据，生成excel表格，进行读取
 * @description: 转JavaAgent开发加油!
 * @author: XiaoHua
 *
 **/

@Getter
@Setter
@EqualsAndHashCode
public class TagExcelData {


    /**
     * id
     */
    @ExcelProperty("标签id")
    private Long id;

    /**
     * 标签名称
     */
    @ExcelProperty("标签名称")
    private String tagName;

    /**
     * 用户 id
     */
    @ExcelProperty("用户id")
    private Long userId;

    /**
     * 父标签 id
     */
    @ExcelProperty("父标签 id")
    private Long parentId;

    /**
     * 0 - 不是, 1 - 父标签
     */
    @ExcelProperty("是否为父标签")
    private Integer isParent;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private Date createTime;

    /**
     *
     */
    @ExcelProperty("更新时间")
    private Date updateTime;

    /**
     * 是否删除
     */
    @ExcelProperty("是否删除")
    private Integer isDelete;
}