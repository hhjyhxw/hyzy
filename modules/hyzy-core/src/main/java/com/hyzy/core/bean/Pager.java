package com.hyzy.core.bean;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Bean类 - 分页
 * 
 * @author chengkunxf
 */

@SuppressWarnings("unchecked")
public class Pager{
	
	private static final int VIEW_PAGE = 10;

	// 排序方式
	public enum OrderType{
		asc, desc
	}
	
	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	//-- 分页参数 --//
	private Integer pageNo = 1;// 当前页码
	private Integer pageSize = 20;// 每页记录数
	private String property;// 查找属性名称
	private String keyword;// 查找关键字
	private String orderBy = "modifyDate";// 排序字段
	private String order = OrderType.desc.name(); //排序方式
	private OrderType orderType = OrderType.desc;// 排序方式
	private boolean autoCount = true;
	
	//-- 返回结果 --//
	private List result;// 数据List
	private Long totalCount = 0L;// 总记录数
	private Integer totalPages = 1;// 总页数

	//-- 分页参数访问函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		}
		this.pageNo = pageNo;
	}
	
	/**
	 * 获得每页的记录数量, 默认为-1.
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置每页的记录数量.
	 */
	public void setPageSize(final Integer pageSize) {
		if (pageSize < 1) {
			this.pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE) {
			this.pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * 获得总记录数, 默认值为20.
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final Long totalCount) {
		this.totalCount = totalCount;
	}
	
	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为0.
	 */
	public Integer getTotalPages() {
		totalPages = totalCount%pageSize == 0 ? totalCount.intValue()/pageSize : totalCount.intValue()/pageSize+1 ;
		return totalPages;
	}
	
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 获得排序方向, 无默认值.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		//检查order字符串的合法值
		String[] orders = StringUtils.split(lowcaseOrder, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(OrderType.desc.name(), orderStr) && !StringUtils.equals(OrderType.asc.name(), orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.order = lowcaseOrder;
	}
	
	/**
	 * 获得页内的记录列表.
	 */
	public List getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List result) {
		this.result = result;
	}
	
	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}
	
	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置, 序号从0开始. 
	 * 用于Mysql, Hibernate.
	 */
	public int getOffset() {
		return ((pageNo - 1) * pageSize);
	}
	
	/**
	 * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为true.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}
	
	/**
	 * 设置查询对象时是否自动先执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}
	
	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && orderType != null);
	}
	 
}