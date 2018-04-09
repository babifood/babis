package com.seeyon.shbb.modules.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.Assert;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.seeyon.shbb.model.MapBean;
import com.seeyon.shbb.utils.PropertySearchFilter;

/**
 * 基于Ibatis2 的查询专用DAO. 虽然提供 insert update delete 方法 ，再老系统无实体类情况下可以使用，但是不推荐使用。
 * 
 * @author 付翀
 * 
 */
public class IbatisQueryDao extends SqlMapClientDaoSupport {



	protected final Logger log = LoggerFactory.getLogger(getClass());



	public void setSqlMapClientSuper(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}



	public String getCountStatementForPaging(String statementName) {
		return statementName + ".count";
	}



	public String getIbatisSqlMapNamespace() {
		throw new RuntimeException("not yet implement");
	}



	/**
	 * 分页查询， 根据 给定条件 获取页面查询结果
	 * 
	 * @param statementName
	 *            ibatis完整ID(SQLID)
	 * @param pageRequest
	 *            分页条件
	 * @param filters
	 *            查询条件
	 * @return 查询结果页
	 */
	public <T, K> Page<MapBean<T, K>> pageQuery(String statementName, PageRequest pageRequest, List<PropertySearchFilter> filters) {
		return pageQuery(getSqlMapClientTemplate(), statementName, getCountStatementForPaging(statementName), pageRequest, filters);
	}



	/**
	 * 分页查询， 根据 给定条件 获取页面查询结果
	 * 
	 * @param sqlMapClientTemplate
	 *            ibatisClientTemplate
	 * @param statementName
	 *            ibatis完整ID(SQLID)
	 * @param countStatementName
	 *            分页count() sql id
	 * @param pageRequest
	 *            分页条件
	 * @param filters
	 *            查询条件
	 * @return 查询结果页
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T, K> Page<MapBean<T, K>> pageQuery(SqlMapClientTemplate sqlMapClientTemplate, String statementName, String countStatementName, PageRequest pageRequest, List<PropertySearchFilter> filters) {
		Assert.notNull(pageRequest, "page不能为空");
		Map<String, Object> otherFilters = new HashMap<String, Object>();

		// 查询条件转化为MAP
		if (filters != null && !filters.isEmpty()) {
			for (PropertySearchFilter propertyFilter : filters) {
				otherFilters.put(propertyFilter.fieldName, propertyFilter.value);
			}
		}

		Long totalCount = (Long) sqlMapClientTemplate.queryForObject(countStatementName, otherFilters);
		if (totalCount == null || totalCount.longValue() <= 0) {
			PageImpl page = new PageImpl(new ArrayList<MapBean<T, K>>());
			return page;
		}

		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		otherFilters.put("offset", pageRequest.getOffset());
		otherFilters.put("pageSize", pageRequest.getPageSize());
		otherFilters.put("lastRows", pageRequest.getOffset() + pageRequest.getPageSize());
		otherFilters.put("sortColumns", getOrderBy(pageRequest.getSort()));

		List list = sqlMapClientTemplate.queryForList(statementName, otherFilters, pageRequest.getOffset() - 1, pageRequest.getPageSize());

		return new PageImpl(list, pageRequest, totalCount);
	}



	private String getOrderBy(Sort sort) {
		List<String> orderString = new ArrayList<String>();
		if (sort != null) {
			for (Order order : sort) {
				orderString.add(order.getProperty() + " " + order.getDirection());
			}
		}
		return StringUtils.join(orderString, ",");
	}



	/**
	 * 为防止sql注入 ，不允许直接使用
	 * 
	 * @param sqlId
	 * @param query
	 * @param order
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T, K> List<MapBean<T, K>> findAllByMap(String sqlId, Map<String, Object> query, String order) {
		if (StringUtils.isNotEmpty(order)) {
			if (query == null) {
				query = new HashMap<String, Object>();
			}
			query.put("sortColumns", order);
		}
		return this.getSqlMapClientTemplate().queryForList(sqlId, query);
	}



	/**
	 * 防止SQL注入，不允许直接使用，请使用Sort排序
	 * 
	 * @param sqlId
	 * @param filters
	 * @param order
	 * @return
	 */
	private <T, K> List<MapBean<T, K>> findAllMapBean(String sqlId, List<PropertySearchFilter> filters, String order) {
		Map<String, Object> otherFilters = new HashMap<String, Object>();
		// 查询条件转化为MAP
		if (filters != null && !filters.isEmpty()) {
			for (PropertySearchFilter propertyFilter : filters) {
				otherFilters.put(propertyFilter.fieldName, propertyFilter.value);
			}
		}
		return findAllByMap(sqlId, otherFilters, order);
	}



	public <T, K> List<MapBean<T, K>> findAll(String sqlId, List<PropertySearchFilter> filters, Sort order) {
		return findAllMapBean(sqlId, filters, getOrderBy(order));
	}



	public <T, K> List<MapBean<T, K>> findAll(String sqlId, List<PropertySearchFilter> filters) {
		return findAllMapBean(sqlId, filters, "");
	}



	public <T, K> List<MapBean<T, K>> findAllByMap(String sqlId, Map<String, Object> filters) {
		return findAllByMap(sqlId, filters, "");
	}



	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(String sqlId, Map<String, Object> query, String order) {
		if (StringUtils.isNotEmpty(order)) {
			if (query == null) {
				query = new HashMap<String, Object>();
			}
			query.put("sortColumns", order);
		}
		return this.getSqlMapClientTemplate().queryForList(sqlId, query);
	}



	public <T> List<T> queryForList(String sqlId, Map<String, Object> query) {
		return this.queryForList(sqlId, query, null);
	}



	@SuppressWarnings("unchecked")
	public <T> Page<T> pageQueryForObject(SqlMapClientTemplate sqlMapClientTemplate, String statementName, String countStatementName, PageRequest pageRequest, List<PropertySearchFilter> filters) {
		Assert.notNull(pageRequest, "page不能为空");
		Map<String, Object> otherFilters = new HashMap<String, Object>();

		// 查询条件转化为MAP
		if (filters != null && !filters.isEmpty()) {
			for (PropertySearchFilter propertyFilter : filters) {
				otherFilters.put(propertyFilter.fieldName, propertyFilter.value);
			}
		}

		Long totalCount = (Long) sqlMapClientTemplate.queryForObject(countStatementName, otherFilters);
		if (totalCount == null || totalCount.longValue() <= 0) {
			PageImpl<T> page = new PageImpl<T>(new ArrayList<T>());
			return page;
		}

		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		otherFilters.put("offset", pageRequest.getOffset());
		otherFilters.put("pageSize", pageRequest.getPageSize());
		otherFilters.put("lastRows", pageRequest.getOffset() + pageRequest.getPageSize());
		otherFilters.put("sortColumns", getOrderBy(pageRequest.getSort()));

		List<T> list = sqlMapClientTemplate.queryForList(statementName, otherFilters, pageRequest.getOffset() - 1, pageRequest.getPageSize());

		return new PageImpl<T>(list, pageRequest, totalCount);
	}



	public <T> Page<T> pageQueryForObject(String statementName, PageRequest pageRequest, List<PropertySearchFilter> filters) {
		return pageQueryForObject(getSqlMapClientTemplate(), statementName, getCountStatementForPaging(statementName), pageRequest, filters);
	}



	public void flush() {
		// ignore
	}

}
