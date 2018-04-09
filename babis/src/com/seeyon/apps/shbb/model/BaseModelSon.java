package com.seeyon.apps.shbb.model;

/**
 * 系统基类，用于匹配A8中的重复表头系统自带的字段
 */
public class BaseModelSon {
	protected Long id; // id
	private Long formmainId; // formmain_id
	private Integer sort; // sort

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFormmainId() {
		return formmainId;
	}

	public void setFormmainId(Long formmainId) {
		this.formmainId = formmainId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
