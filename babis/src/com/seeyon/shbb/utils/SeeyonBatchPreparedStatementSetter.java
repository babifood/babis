package com.seeyon.shbb.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.util.Assert;
import com.seeyon.ctp.util.UUIDLong;

/**
 * 数据库批量插入时的参数设置方式
 * 
 * Map<String, Object> map = list.get(index); ps.setLong(1,
 * UUIDLong.longUUID()); ps.setLong(2, MapUtils.getLong(map, "人员ID"));
 * ps.setLong(3, MapUtils.getLong(map, "人员ID")); ps.setLong(4,
 * MapUtils.getLong(map, "UUID"));// 数据ID. ps.setLong(5, -5488001883784265206L);
 * ps.setLong(6, MapUtils.getLong(map, "UUID"));// 数据ID. ps.setLong(7,
 * 6360018677200866051L);// 表单ID. ps.setString(8, "打卡记录表");// 表单名称.
 * 
 * 可以写成
 * 
 * abc=
 * SeeyonBatchPreparedStatementSetter(list,["UUID","人员ID","人员ID","UUID","TID"
 * ,"UUID","SID","STYPE"]) Map
 * defVal={"TID":-5488001883784265206L,"STYPE":"打卡记录表"};
 * abc.setDefValue(defVal);
 *
 * 还未完善，暂时不要使用
 * 
 * @author 付翀
 *
 */
@Deprecated
public class SeeyonBatchPreparedStatementSetter implements BatchPreparedStatementSetter {



	private List<Map<String, Object>> list;

	private List<String> keys;

	@SuppressWarnings({ "unused", "rawtypes" })
	private Map<String, Class> valueType;

	private Map<String, Object> defValue;

	// 默认的主键KEY
	public final static String UUID_KEY = "UUID";



	/**
	 * 设置key对应的字段转换类型
	 * 
	 * @param valueType
	 */
	@SuppressWarnings("rawtypes")
	public void setValueType(Map<String, Class> valueType) {
		this.valueType = valueType;
	}



	/**
	 * 设置key对应值为空时的默认值
	 * 
	 * @param defValue
	 */
	public void setDefValue(Map<String, Object> defValue) {
		this.defValue = defValue;
	}



	/**
	 * 
	 * @param list
	 *            需要插入的数据列表，每一笔数据为一个Map<String, Object>
	 * @param keys
	 *            插入数据时，每一列对应的key的顺序
	 */
	@SuppressWarnings("rawtypes")
	public SeeyonBatchPreparedStatementSetter(List<Map<String, Object>> list, List<String> keys) {
		this(list, keys, new HashMap<String, Class>(), new HashMap<String, Object>());
	}



	/**
	 * 
	 * @param list
	 *            需要插入的数据列表，每一笔数据为一个Map<String, Object>
	 * @param keys
	 *            插入数据时，每一列对应的key的顺序
	 */
	@SuppressWarnings("rawtypes")
	public SeeyonBatchPreparedStatementSetter(List<Map<String, Object>> list, String[] keys) {
		this(list, Arrays.asList(keys), new HashMap<String, Class>(), new HashMap<String, Object>());
	}



	/**
	 * 
	 * @param list
	 *            需要插入的数据列表，每一笔数据为一个Map<String, Object>
	 * @param keys
	 *            插入数据时，每一列对应的key的顺序
	 * @param valueType
	 *            插入数据时，key所对应的值类型
	 * @param defValue
	 *            插入数据时，key所对应的默认值
	 */
	@SuppressWarnings("rawtypes")
	public SeeyonBatchPreparedStatementSetter(List<Map<String, Object>> list, List<String> keys, Map<String, Class> valueType, Map<String, Object> defValue) {
		Assert.notNull(list);
		Assert.notNull(keys);
		Assert.notNull(valueType);
		Assert.notNull(defValue);
		this.list = list;
		this.keys = keys;
		this.valueType = valueType;
		this.defValue = defValue;
	}



	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		Map<String, Object> map = this.list.get(i);
		int index = 0;
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			Object value = map.get(key);
			index++;// 序号从1开始

			if (StringUtils.equalsIgnoreCase(key, UUID_KEY) && value == null) {
				value = UUIDLong.longUUID();
				map.put(key, value);
			}
			if (value == null && this.defValue.containsKey(key)) {
				value = this.defValue.containsKey(key);
				map.put(key, value);
			}

			StatementCreatorUtils.setParameterValue(ps, index, SqlTypeValue.TYPE_UNKNOWN, value);
			//
			// if (value == null) {
			// ps.setString(index, null);
			// } else {
			//
			// if (valueType.containsKey(key)) {
			// this.setStatementValue(ps, index, valueType.get(key), value);
			// } else {
			// this.setStatementValue(ps, index, value.getClass(), value);
			// }
			// }

		}

	}



	//
	// private void setStatementValue(PreparedStatement ps, int index, Class
	// clazz, Object value) throws SQLException {
	// Assert.notNull(value);
	// Assert.notNull(clazz);
	// if (clazz.equals(Long.class)) {
	// ps.setLong(index, (Long) value);
	// } else if (clazz.equals(Date.class)) {
	// Date temp = (Date) value;
	// ps.setDate(index, new java.sql.Date(temp.getTime()));
	// } else if (clazz.equals(java.sql.Date.class)) {
	// ps.setDate(index, (java.sql.Date) value);
	// } else if (clazz.equals(java.sql.Timestamp.class)) {
	// ps.setTimestamp(index, (java.sql.Timestamp) value);
	// } else if (clazz.equals(Integer.class)) {
	// ps.setInt(index, (Integer) value);
	// } else if (clazz.equals(Double.class)) {
	// ps.setDouble(index, (Double) value);
	// } else if (clazz.equals(Float.class)) {
	// ps.setFloat(index, (Float) value);
	// } else if (clazz.equals(String.class)) {
	// ps.setString(index, (String) value);
	// } else {
	// ps.setString(index, (String) value);
	// }
	//
	// }

	@Override
	public int getBatchSize() {

		return this.list.size();
	}

}
