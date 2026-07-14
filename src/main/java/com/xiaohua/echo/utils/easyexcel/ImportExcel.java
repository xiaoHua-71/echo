package com.xiaohua.echo.utils.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 导入Excel
 * @description: 转JavaAgent开发加油!
 * @author: XiaoHua
 *
 **/

public class ImportExcel {

    @Data
    public static class UserExcelData {
        @ExcelProperty("用户id")
        private String userId;

        @ExcelProperty("用户昵称")
        private String nickname;

        @ExcelProperty("创建时间")
        private String createTime;
    }

    @Data
    public static class TagExcelData {
        @ExcelProperty("标签id")
        private Long id;

        @ExcelProperty("标签名称")
        private String tagName;

        @ExcelProperty("用户id")
        private Long userId;

        @ExcelProperty("父标签id")
        private Long parentId;

        @ExcelProperty("是否为父标签")
        private Integer isParent;

        @ExcelProperty("创建时间")
        private String createTime;
    }

    /**
     * 创建Excel文件
     * @param args
     */
    public static void main(String[] args) {
        // 生成用户Excel
        String userFilePath = "src/main/resources/test_excel.xlsx";
        List<UserExcelData> userList = new ArrayList<>();
        userList.add(new UserExcelData() {{ setUserId("1"); setNickname("张三"); setCreateTime("2025-01-15 10:30:00"); }});
        userList.add(new UserExcelData() {{ setUserId("2"); setNickname("李四"); setCreateTime("2025-03-22 14:20:00"); }});
        userList.add(new UserExcelData() {{ setUserId("3"); setNickname("王五"); setCreateTime("2025-06-08 09:15:00"); }});
        userList.add(new UserExcelData() {{ setUserId("4"); setNickname("赵六"); setCreateTime("2025-08-30 16:45:00"); }});
        userList.add(new UserExcelData() {{ setUserId("5"); setNickname("小明"); setCreateTime("2025-11-12 11:00:00"); }});
        EasyExcel.write(userFilePath, UserExcelData.class).sheet("用户").doWrite(userList);
        System.out.println("User Excel created: " + userFilePath);

        // 生成标签Excel
        String tagFilePath = "src/main/resources/test_tag_excel.xlsx";
        List<TagExcelData> tagList = new ArrayList<>();
        tagList.add(new TagExcelData() {{ setId(1L); setTagName("编程"); setUserId(1L); setParentId(0L); setIsParent(1); setCreateTime("2025-01-10 09:00:00"); }});
        tagList.add(new TagExcelData() {{ setId(2L); setTagName("Java"); setUserId(1L); setParentId(1L); setIsParent(0); setCreateTime("2025-01-12 10:00:00"); }});
        tagList.add(new TagExcelData() {{ setId(3L); setTagName("Python"); setUserId(1L); setParentId(1L); setIsParent(0); setCreateTime("2025-01-15 14:00:00"); }});
        tagList.add(new TagExcelData() {{ setId(4L); setTagName("运动"); setUserId(1L); setParentId(0L); setIsParent(1); setCreateTime("2025-02-20 11:00:00"); }});
        tagList.add(new TagExcelData() {{ setId(5L); setTagName("篮球"); setUserId(1L); setParentId(4L); setIsParent(0); setCreateTime("2025-03-01 16:30:00"); }});
        tagList.add(new TagExcelData() {{ setId(6L); setTagName("跑步"); setUserId(1L); setParentId(4L); setIsParent(0); setCreateTime("2025-03-15 08:20:00"); }});
        EasyExcel.write(tagFilePath, TagExcelData.class).sheet("标签").doWrite(tagList);
        System.out.println("Tag Excel created: " + tagFilePath);
    }
}
