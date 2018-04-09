package com.seeyon.shbb.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

public class PropertySearchFilter {

	public String fieldName;
	public Object value;
	public String operator;

	public PropertySearchFilter(String fieldName, String operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	public PropertySearchFilter(String fieldExpression, Object value) {
		this.operator = StringUtils.substringBefore(fieldExpression, "_");
		this.fieldName = StringUtils.substringAfter(fieldExpression, "_");
		this.value = value;
	}

	public static Map<String, PropertySearchFilter> parse(Map<String, Object> filterParams) {
		Map<String, PropertySearchFilter> filters = new HashMap<String, PropertySearchFilter>();
		for (Entry<String, Object> entry : filterParams.entrySet()) {
			PropertySearchFilter filter = new PropertySearchFilter(entry.getKey(), entry.getValue());
			filters.put(entry.getKey(), filter);
		}
		return filters;
	}

	public String getOperatorExp() {
		if (StringUtils.equalsIgnoreCase(this.operator, "eq")) {
			return "=";
		}
		if (StringUtils.equalsIgnoreCase(this.operator, "like")) {
			return "like";
		}
		if (StringUtils.equalsIgnoreCase(this.operator, "not")) {
			return "<>";
		}
		//大于
		if (StringUtils.equalsIgnoreCase(this.operator, "gt")) {
			return ">";
		}
		//小于
		if (StringUtils.equalsIgnoreCase(this.operator, "lt")) {
			return "<";
		}
		//大于等于 
		if (StringUtils.equalsIgnoreCase(this.operator, "gte")) {
			return ">=";
		}
		//小于等于 
		if (StringUtils.equalsIgnoreCase(this.operator, "lte")) {
			return "<=";
		}

		return "=";
	}

}
