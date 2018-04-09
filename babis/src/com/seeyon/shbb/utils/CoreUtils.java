package com.seeyon.shbb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.util.Assert;

public class CoreUtils {



	private static final Log log = LogFactory.getLog(CoreUtils.class);

	// 默认查询请求参数前缀字符串
	public static final String SEARCH_PARAMETERS_STARTING_WITH_PARAM = "search_";

	// 标准日期格式数组
	public static final String[] SIMPLE_PARSE_PATTERNS = new String[] { "yyyy-MM-dd", "MM-dd-yyyy", "yyyy/MM/dd","yyyyMMdd" };

	public static final String[] DAYTIME_PARSE_PATTERNS = new String[] { "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm" };

	public static final long MINUTE_CONVERT = 1000 * 60;

	public static final long HOUR_CONVENT = 1000 * 60 * 60;



	/**
	 * 获取请求中所有search_ 为前缀的查询条件，并包装成PropertySearchFilter 返回
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, PropertySearchFilter> getParametersStartingWith(ServletRequest request) {
		return PropertySearchFilter.parse(getParametersStartingWith(request, SEARCH_PARAMETERS_STARTING_WITH_PARAM));
	}



	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils. *
	 * 返回的结果的Parameter名已去除前缀.
	 * 
	 * @param request
	 * @param prefix
	 *            指定前缀
	 * @param noSub
	 *            返回时 是否截取掉相关前缀，true为保留,false则截取返回
	 * @return
	 */
	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix, boolean noSub) {
		Validate.notNull(request, "Request must not be null");
		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = noSub ? paramName : paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					if (StringUtils.isNotBlank(values[0])) {
						params.put(unprefixed, values[0]);
					}
				}
			}
		}
		return params;
	}



	/**
	 * 创建一个新的分页请求
	 * 
	 * @param pageNumber
	 *            当前页码
	 * @param pagzSize
	 *            每页数量
	 * @param sortOrderBy
	 *            排序字段数组
	 * @param sortOrderDesc
	 *            排序字段类型数组，必须与sortOrderBy长度一致
	 * @return 分页请求对象
	 */
	public static PageRequest buildPageRequest(int pageNumber, int pagzSize, String[] sortOrderBy, String[] sortOrderDesc) {
		return buildPageRequest(pageNumber, pagzSize, buildSort(sortOrderBy, sortOrderDesc));
	}



	public static PageRequest buildPageRequest(int pageNumber, int pagzSize, Sort sort) {
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}



	public static PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortOrderBy, String sortOrderDesc) {

		return buildPageRequest(pageNumber, pagzSize, buildSort(sortOrderBy, sortOrderDesc));
	}



	public static Sort buildSort(String sortOrderBy, String sortOrderDesc) {
		String[] orderByArray = null;
		String[] orderArray = null;
		if (StringUtils.isNotBlank(sortOrderBy) && StringUtils.isNotBlank(sortOrderDesc)) {
			orderByArray = StringUtils.split(sortOrderBy, ',');
			orderArray = StringUtils.split(sortOrderDesc, ',');
		}
		return buildSort(orderByArray, orderArray);
	}



	public static Sort buildSort(String[] sortOrderBy, String[] sortOrderDesc) {
		Sort sort = null;

		if (ArrayUtils.isNotEmpty(sortOrderBy) && ArrayUtils.isNotEmpty(sortOrderDesc)) {
			Assert.isTrue(sortOrderBy.length == sortOrderDesc.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");
			List<Order> orders = new ArrayList<Sort.Order>();
			for (int i = 0; i < sortOrderBy.length; i++) {
				orders.add(new Order(Direction.fromString(sortOrderDesc[i]), sortOrderBy[i]));
			}
			sort = new Sort(orders);
		}
		return sort;
	}



	/**
	 * 按顺序设定给定数组中的值
	 * 
	 * @param ps
	 * @param args
	 * @throws SQLException
	 */
	public static void setParametersValue(PreparedStatement ps, Object[] args) throws SQLException {
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				Object arg = args[i];
				StatementCreatorUtils.setParameterValue(ps, i + 1, SqlTypeValue.TYPE_UNKNOWN, arg);
			}

		}
	}



	/**
	 * 获取某一天的最小时刻
	 * 
	 * @param day
	 *            日期 为null时 默认获取当天的最小时刻
	 * @return 一个日期的 0:0:0.000时刻
	 */
	public static Date getMinTimeByDay(Date day) {
		Calendar mintime = Calendar.getInstance();
		if (day != null) {
			mintime.setTime(day);
		}
		mintime.set(Calendar.HOUR_OF_DAY, 0);
		mintime.set(Calendar.SECOND, 0);
		mintime.set(Calendar.MINUTE, 0);
		mintime.set(Calendar.MILLISECOND, 0);
		return mintime.getTime();
	}



	/**
	 * 获取某一天的最大时刻
	 * 
	 * @param day
	 *            日期 为null时 默认获取当天的最大时刻
	 * @return 一个日期的 23:59:59.999时刻
	 */
	public static Date getMaxTimeByDay(Date day) {
		Calendar mintime = Calendar.getInstance();
		if (day != null) {
			mintime.setTime(day);
		}
		mintime.set(Calendar.HOUR_OF_DAY, 23);
		mintime.set(Calendar.SECOND, 59);
		mintime.set(Calendar.MINUTE, 59);
		mintime.set(Calendar.MILLISECOND, 999);
		return mintime.getTime();
	}



	@SuppressWarnings("rawtypes")
	public static Map getParametersStartingWith(ServletRequest request, String prefix) {
		return CoreUtils.getParametersStartingWith(request, prefix, false);
	}



	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param queryString
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的HTML
	 */
	public static String doGet(String url, String queryString, String charset, boolean pretty) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			if (StringUtils.isNotBlank(queryString))
				// 对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
				method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		} catch (URIException e) {
			// log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
		} catch (IOException e) {
			// log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}



	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的HTML
	 */
	@SuppressWarnings("deprecation")
	public static String doPost(String url, Map<String, String> params, String aaa, String charset, boolean pretty) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		if (params != null) {
			HttpMethodParams p = new HttpMethodParams();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				p.setParameter(entry.getKey(), entry.getValue());
			}

		}

		try {
			method.setRequestHeader("Content-Type", "text/xml");
			method.setRequestHeader("charset", "UTF-8");
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			method.setRequestBody(aaa);
			System.out.println(method.getRequestCharSet());
			client.executeMethod(method);

			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			log.info("执行HTTP Post请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

}
