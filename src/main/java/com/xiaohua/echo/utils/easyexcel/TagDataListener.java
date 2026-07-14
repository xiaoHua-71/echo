package com.xiaohua.echo.utils.easyexcel;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcloud.cos.utils.Jackson;
import com.xiaohua.echo.model.entity.Tag;
import com.xiaohua.echo.model.once.TagExcelData;
import com.xiaohua.echo.service.TagService;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 转JavaAgent开发加油!
 * @author: XiaoHua
 *
 **/

// 有个很重要的点 TagDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class TagDataListener implements ReadListener<TagExcelData> {

	private final TagService tagService;

	public TagDataListener(TagService tagService) {
		this.tagService = tagService;
	}

	/**
	 * 这个每一条数据解析都会来调用
	 *
	 * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
	 * @param context
	 */
	@Override
	public void invoke(TagExcelData data, AnalysisContext context) {
		log.info("解析到一条数据:{}", Jackson.toJsonString(data));
		QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
		System.out.println("data.getTagName() = " + data.getTagName());
		queryWrapper.eq("tagName", data.getTagName());
		Tag IsexistTag = tagService.getOne(queryWrapper);
		if (IsexistTag == null) {
			//这里进行入库
			Tag tag = new Tag();
			BeanUtil.copyProperties(data, tag);
			tag.setId(null); // id 由数据库自增，不使用 Excel 中的 id
			tagService.save(tag);
		}
	}

	/**
	 * 所有数据解析完成了 都会来调用
	 *
	 * @param context
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		// 这里也要保存数据，确保最后遗留的数据也存储到数据库

		log.info("所有数据解析完成:{}", Jackson.toJsonString(context));
	}

}
