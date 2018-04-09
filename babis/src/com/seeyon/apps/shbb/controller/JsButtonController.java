package com.seeyon.apps.shbb.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import com.seeyon.ctp.common.po.template.CtpTemplate;

public class JsButtonController extends SHBBBaseController {



	private Map<String, String> shbbJslink = new HashMap<String, String>();



	public Map<String, String> getShbbJslink() {
		return shbbJslink;
	}



	public void setShbbJslink(Map<String, String> shbbJslink) {
		this.shbbJslink = shbbJslink;
	}



	/**
	 * 
	 * 用于扩展业务表单的按钮
	 */
	public ModelAndView expButton(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(ROOT_PATH + "/serjs/button");

		return mav;
	}



	/**
	 * 用于扩展业务表单的控件 将业务表单根据预制Map跳转到指定页面，页面中为JS代码
	 */
	public ModelAndView expFormJs(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String link = "none";
		String formId = request.getParameter("wformId");
		String moduleTemplateId = request.getParameter("moduleTemplateId");
		if (StringUtils.isBlank(formId) && StringUtils.isNotBlank(moduleTemplateId)) {
			CtpTemplate template = this.templateManager.getCtpTemplate(Long.valueOf(moduleTemplateId));
			if (template != null) {
				formId = template.getSubject();
			}
		}
		// 适用与无流程单据
		if (StringUtils.isBlank(formId)) {
			formId = request.getParameter("formId");
		}

		// 如果表单配置了专用的控件JS初始化代码，则转向该初始化页面
		if (StringUtils.isNotBlank(formId) && this.shbbJslink.containsKey(formId)) {
			link = this.shbbJslink.get(formId);
		}

		ModelAndView mav = new ModelAndView(ROOT_PATH + "/serjs/" + link);

		return mav;
	}



	/**
	 * 用于扩展业务表单发送按钮前的校验 将业务表单根据预制Map跳转到指定页面，页面中为JS代码
	 */
	public ModelAndView expButtonJs(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String link = "none";
		String formId = request.getParameter("wformId");
		String moduleTemplateId = request.getParameter("moduleTemplateId");
		if (StringUtils.isBlank(formId) && StringUtils.isNotBlank(moduleTemplateId)) {
			CtpTemplate template = this.templateManager.getCtpTemplate(Long.valueOf(moduleTemplateId));
			if (template != null) {
				formId = template.getSubject();
			}
		}
		// 适用与无流程单据
		if (StringUtils.isBlank(formId)) {
			formId = request.getParameter("formId");
		}

		// 如果表单配置了专用的控件JS初始化代码，则转向该初始化页面
		if (StringUtils.isNotBlank(formId) && this.shbbJslink.containsKey(formId)) {
			link = this.shbbJslink.get(formId);
		}

		ModelAndView mav = new ModelAndView(ROOT_PATH + "/serjs/" + link);

		return mav;
	}
}