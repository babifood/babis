package com.seeyon.apps.shbb.manager;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.seeyon.ctp.common.exceptions.BusinessException;
import com.seeyon.v3x.services.ServiceException;
import com.seeyon.v3x.services.flow.impl.FlowFactoryImpl;
import com.seeyon.v3x.services.form.FormUtils;
import com.seeyon.v3x.services.form.bean.FormExport;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 用于集成A8流程相关业务操作
 * 
 * @author 付翀
 * 
 */
public class FreemarkFlowManager extends BaseManager {
	private static final Log log = LogFactory.getLog(FreemarkFlowManager.class);
	private FreeMarkerConfigurer freeMarkerConfigurer;
	private FlowFactoryImpl flowFactoryImpl;

	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		return freeMarkerConfigurer;
	}

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	public FlowFactoryImpl getFlowFactoryImpl() {
		return flowFactoryImpl;
	}

	public void setFlowFactoryImpl(FlowFactoryImpl flowFactoryImpl) {
		this.flowFactoryImpl = flowFactoryImpl;
	}

	public String fltTemplateIntoString(String templateFileName, Object model) {
		// 保证Configuration能初始化
		String templateString = null;
		try {
			freeMarkerConfigurer.afterPropertiesSet();
			Configuration config = freeMarkerConfigurer.getConfiguration();
			Template template = config.getTemplate(templateFileName);
			templateString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (IOException e) {
			log.error("Freemark模板"+templateFileName+"获取IOException异常:"  +e.getMessage());
			
		} catch (TemplateException e) {
			log.error("Freemark模板"+templateFileName+"TemplateException异常:" +e.getMessage());
		}
		return templateString;

	}

	public long sendCollaboration(String senderLoginName, String templateCode, String subject, String xmlData,
			Long[] attachments, String parameter, String relateDoc) throws Exception {
		FormExport formExportData = FormUtils.xmlTransformFormExport(xmlData);
		return this.sendCollaboration(senderLoginName, templateCode, subject, formExportData, attachments, parameter,
				relateDoc);
	}

	public long sendCollaboration(String senderLoginName, String templateCode, String subject,
			FormExport formExportData, Long[] attachments, String parameter, String relateDoc)
			throws BusinessException, ServiceException {
		return this.flowFactoryImpl.sendCollaboration(senderLoginName, templateCode, subject, formExportData,
				attachments, parameter, relateDoc);
	}

}
