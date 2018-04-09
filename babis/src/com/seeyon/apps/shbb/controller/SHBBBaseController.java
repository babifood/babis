package com.seeyon.apps.shbb.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import com.seeyon.ctp.common.controller.BaseController;
import com.seeyon.ctp.common.template.manager.TemplateManager;
import com.seeyon.ctp.form.service.FormCacheManager;
import com.seeyon.ctp.form.service.FormManager;
import com.seeyon.shbb.modules.ibatis.IbatisQueryDao;
import com.seeyon.shbb.utils.CoreUtils;
import com.seeyon.v3x.common.dao.support.page.Page;

public abstract class SHBBBaseController extends BaseController {



	protected FormManager formManager;

	protected FormCacheManager formCacheManager;

	protected JdbcTemplate jdbcTemplate;

	protected IbatisQueryDao ibatisQueryDao;

	protected TemplateManager templateManager;

	protected static String ROOT_PATH = "plugin/shbb";

	protected Map<String, String> sqlMap = new HashMap<String, String>();

	// 默认查询请求参数前缀字符串
	public static final String SEARCH_PARAMETERS_STARTING_WITH_PARAM = "search_";

	// 默认分页参数request 中的名称
	public static final String PAGE_NUM_PARAM = "pageNum";

	public static final String NUM_PER_PAGE_PARAM = "numPerPage";

	public static final String ORDER_FIELD_PARAM = "orderField";

	public static final String ORDER_DIRECTION_PARAM = "orderDirection";



	public Map<String, String> getSqlMap() {
		return sqlMap;
	}



	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}



	public FormManager getFormManager() {
		return this.formManager;
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



	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	public IbatisQueryDao getIbatisQueryDao() {
		return ibatisQueryDao;
	}



	public void setIbatisQueryDao(IbatisQueryDao ibatisQueryDao) {
		this.ibatisQueryDao = ibatisQueryDao;
	}



	public TemplateManager getTemplateManager() {
		return templateManager;
	}



	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}



	protected ModelAndView ajaxDone(int statusCode, String message, String data) {
		ModelAndView mav = new ModelAndView(ROOT_PATH + "/json/ajaxDone");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		if (data != null) {
			mav.addObject("data", data);
		}
		return mav;
	}



	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(200, message, null);
	}



	protected ModelAndView ajaxDoneError(String message) {
		return ajaxDone(300, message, null);
	}



	protected ModelAndView ajaxDoneError(String message, String data) {
		return ajaxDone(300, message, data);
	}



	protected ModelAndView ajaxDoneSuccess(String message, String data) {
		return ajaxDone(200, message, data);
	}



	/**
	 * 使用默认规则，从 request中获取分页请求对象
	 * 
	 * @param request
	 * @return
	 */
	private PageRequest getPageRequest(HttpServletRequest request, int pageNumber, int pagzSize, String sortOrderBy, String sortOrderDesc, boolean forceReset) {
		pageNumber = pageNumber <= 0 ? 1 : pageNumber;
		pagzSize = pagzSize <= 0 ? Page.DEFAULT_PAGE_SIZE : pagzSize;
		if (!forceReset) {
			String pageNumberString = WebUtils.findParameterValue(request, PAGE_NUM_PARAM);
			if (StringUtils.isNotBlank(pageNumberString) && StringUtils.isNumeric(pageNumberString)) {
				pageNumber = Integer.parseInt(pageNumberString);
			}
			String pagzSizeString = WebUtils.findParameterValue(request, NUM_PER_PAGE_PARAM);
			if (StringUtils.isNotBlank(pagzSizeString) && StringUtils.isNumeric(pagzSizeString)) {
				pagzSize = Integer.parseInt(pagzSizeString);
			}
			String sortOrderByString = WebUtils.findParameterValue(request, ORDER_FIELD_PARAM);
			String sortOrderDescString = WebUtils.findParameterValue(request, ORDER_DIRECTION_PARAM);
			if (StringUtils.isNotBlank(sortOrderByString) && StringUtils.isNotBlank(sortOrderDescString)) {
				sortOrderBy = sortOrderByString;
				sortOrderDesc = sortOrderDescString;
			}
		}
		return CoreUtils.buildPageRequest(pageNumber, pagzSize, sortOrderBy, sortOrderDesc);
	}



	protected String getSortOrderBy(HttpServletRequest request) {
		return WebUtils.findParameterValue(request, ORDER_FIELD_PARAM);
	}



	protected String getSortOrderBy() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return getSortOrderBy(request);
	}



	protected String getSortOrderDesc() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return getSortOrderDesc(request);

	}



	protected String getSortOrderDesc(HttpServletRequest request) {
		return WebUtils.findParameterValue(request, ORDER_DIRECTION_PARAM);
	}



	protected PageRequest getPageRequest() {
		return getPageRequest(-1, -1, null, null, false);
	}



	/**
	 * 当request中没有设定分页信息时，将用该设定值进行分页
	 * 
	 * @param pageNumber
	 *            当前页码
	 * @param pagzSize
	 *            每页数量
	 * @return
	 */
	protected PageRequest getPageRequest(int pageNumber, int pagzSize) {
		return getPageRequest(pageNumber, pagzSize, false);
	}



	/**
	 * @param pageNumber
	 *            当前页码
	 * @param pagzSize
	 *            每页数量
	 * @param forceReset
	 *            是否强制覆盖request中传递的参数
	 * @return
	 */
	protected PageRequest getPageRequest(int pageNumber, int pagzSize, boolean forceReset) {
		return getPageRequest(pageNumber, pagzSize, null, null, forceReset);
	}



	/**
	 * 当request中没有设定排序信息时，将用该设定值进行排序
	 * 
	 * @param sortOrderBy
	 *            排序字段,多个用 , 分隔
	 * @param sortOrderDesc
	 *            排序字段类型,多个用 , 分隔，必须与一致
	 * @return
	 */
	protected PageRequest getPageRequest(String sortOrderBy, String sortOrderDesc) {
		return getPageRequest(sortOrderBy, sortOrderDesc, false);
	}



	/**
	 * 
	 * @param sortOrderBy
	 *            排序字段,多个用 , 分隔
	 * @param sortOrderDesc
	 *            排序字段类型,多个用 , 分隔，必须与一致
	 * @param forceReset
	 *            是否强制覆盖request中传递的参数
	 * @return
	 */
	protected PageRequest getPageRequest(String sortOrderBy, String sortOrderDesc, boolean forceReset) {
		return getPageRequest(-1, -1, sortOrderBy, sortOrderDesc, forceReset);
	}



	/**
	 * 
	 * @param pageNumber
	 *            当前页码
	 * @param pagzSize
	 *            每页数量
	 * @param sortOrderBy
	 *            排序字段,多个用 , 分隔
	 * @param sortOrderDesc
	 *            排序字段类型,多个用 , 分隔，必须与一致
	 * @param forceReset
	 *            是否强制覆盖request中传递的参数
	 * @return
	 */
	protected PageRequest getPageRequest(int pageNumber, int pagzSize, String sortOrderBy, String sortOrderDesc, boolean forceReset) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return getPageRequest(request, pageNumber, pagzSize, sortOrderBy, sortOrderDesc, forceReset);
	}



	/**
	 * 根据默认查询参数前缀，获取所有查询条件
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getSearchRequest(HttpServletRequest request) {
		return CoreUtils.getParametersStartingWith(request, SEARCH_PARAMETERS_STARTING_WITH_PARAM);
	}



	protected Map<String, Object> getSearchRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return getSearchRequest(request);
	}

}
