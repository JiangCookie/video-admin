package com.jyh.videoadmin.common.util;

import lombok.Data;

import java.util.List;

/**
 * @Description: 封装分页后的数据格式
 */
@Data
public class PagedResult {

	/**
	 * 当前页数
	 */
	private int page;

	/**
	 * 总页数
	 */
	private int total;

	/**
	 * 总记录数
	 */
	private long records;

	/**
	 * 每行显示的内容
	 */
	private List<?> rows;
	


}
