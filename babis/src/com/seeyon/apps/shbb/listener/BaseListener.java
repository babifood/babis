package com.seeyon.apps.shbb.listener;

import org.springframework.jdbc.core.JdbcTemplate;
import com.seeyon.apps.collaboration.manager.ColManager;
import com.seeyon.ctp.common.ctpenumnew.manager.EnumManager;
import com.seeyon.ctp.common.template.manager.TemplateManager;
import com.seeyon.shbb.modules.ibatis.IbatisQueryDao;

/**
 * 久久丫基础监听类，主要将监控过程使用的业务类注入
 * 
 * @author jerry
 * 
 */
public abstract class BaseListener {



	protected ColManager colManager;

	protected JdbcTemplate jdbcTemplate;

	protected TemplateManager templateManager;

	protected EnumManager enumManagerNew;

	protected IbatisQueryDao ibatisQueryDao;

	protected String templateCode;



	public void setColManager(ColManager colManager) {
		this.colManager = colManager;
	}



	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}



	public ColManager getColManager() {
		return colManager;
	}



	public TemplateManager getTemplateManager() {
		return templateManager;
	}



	public EnumManager getEnumManagerNew() {
		return enumManagerNew;
	}



	public void setEnumManagerNew(EnumManager enumManagerNew) {
		this.enumManagerNew = enumManagerNew;
	}



	public IbatisQueryDao getIbatisQueryDao() {
		return ibatisQueryDao;
	}



	public void setIbatisQueryDao(IbatisQueryDao ibatisQueryDao) {
		this.ibatisQueryDao = ibatisQueryDao;
	}



	public String getTemplateCode() {
		return templateCode;
	}



	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

}
