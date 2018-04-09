package com.seeyon.apps.shbb.manager;

import org.springframework.jdbc.core.JdbcTemplate;

import com.seeyon.ctp.form.service.FormCacheManager;
import com.seeyon.ctp.form.service.FormManager;
import com.seeyon.shbb.modules.ibatis.IbatisQueryDao;

public class BaseManager {
	protected JdbcTemplate jdbcTemplate;
	protected FormManager formManager;
	protected FormCacheManager formCacheManager;
	protected IbatisQueryDao ibatisQueryDao;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public FormManager getFormManager() {
		return formManager;
	}

	public void setFormManager(FormManager formManager) {
		this.formManager = formManager;
	}

	public FormCacheManager getFormCacheManager() {
		return formCacheManager;
	}

	public void setFormCacheManager(FormCacheManager formCacheManager) {
		this.formCacheManager = formCacheManager;
	}

	public IbatisQueryDao getIbatisQueryDao() {
		return ibatisQueryDao;
	}

	public void setIbatisQueryDao(IbatisQueryDao ibatisQueryDao) {
		this.ibatisQueryDao = ibatisQueryDao;
	}

}
