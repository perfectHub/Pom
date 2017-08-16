package com.rest.util;

import java.util.List;

public class Pager<E> {

	/**
	 * 当前页码
	 */
	private Integer currentPage = 1;
	
	/**
	 * 每页记录数
	 */
	private Integer pageSize = 10;
	
	/**
	 * 总记录数
	 */
	private Integer totalCount = 0;
	
	/**
	 * 总页数
	 */
	private Integer pageCount = 0;
	
	/**
	 * 数据list
	 */
	private List<E> list;
	
	/**
	 * 默认降序
	 */
	protected OrderType orderType = OrderType.DESC;
	
	/**
	 * 排序字段
	 */
	protected String orderColumns;

	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * 当前页码
	 * @param currentPage
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage < 1 ? 0 : currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 如果当前记录数小于1，就查询1条
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize < 1 ? 1 : pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		/**
		 * 计算总页数
		 */
		this.pageCount = (totalCount + pageSize - 1) / pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public String getOrderColumns() {
		return orderColumns;
	}

	public void setOrderColumns(String orderColumns) {
		this.orderColumns = orderColumns;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
}
