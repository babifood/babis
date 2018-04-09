package com.seeyon.apps.shbb.model;

import java.util.Date;

/**
 * 系统基类，用于匹配A8中的主表表头系统自带的字段
 */
public class BaseModel {
	protected Long id; // id
	protected Integer state; // 审核状态
	protected Long start_member_id; // 发起人
	protected Date start_date; // 发起时间
	protected Long approve_member_id; // 审核人
	protected Date approve_date; // 审核时间
	protected Integer finishedflag; // 流程状态
	protected Integer ratifyflag; // 核定状态
	protected Long ratify_member_id; // 核定人
	protected Date ratify_date; // 核定时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getStart_member_id() {
		return start_member_id;
	}

	public void setStart_member_id(Long start_member_id) {
		this.start_member_id = start_member_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Long getApprove_member_id() {
		return approve_member_id;
	}

	public void setApprove_member_id(Long approve_member_id) {
		this.approve_member_id = approve_member_id;
	}

	public Date getApprove_date() {
		return approve_date;
	}

	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}

	public Integer getFinishedflag() {
		return finishedflag;
	}

	public void setFinishedflag(Integer finishedflag) {
		this.finishedflag = finishedflag;
	}

	public Integer getRatifyflag() {
		return ratifyflag;
	}

	public void setRatifyflag(Integer ratifyflag) {
		this.ratifyflag = ratifyflag;
	}

	public Long getRatify_member_id() {
		return ratify_member_id;
	}

	public void setRatify_member_id(Long ratify_member_id) {
		this.ratify_member_id = ratify_member_id;
	}

	public Date getRatify_date() {
		return ratify_date;
	}

	public void setRatify_date(Date ratify_date) {
		this.ratify_date = ratify_date;
	}

}
