package com.seeyon.apps.shbb.manager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
    
public class SHBBSapManager extends BaseManager{

	private YFKManager yfkManager;
	private HKZFManager hkzfManager;
	private FYManager fyManager;
	@SuppressWarnings({ "unchecked"})
	public void saptimer()throws Exception{
	//定时器自动获取未拋送或拋送失败的ID
		List<Map<String, Object>> ids=ibatisQueryDao.getSqlMapClient().queryForList("getdateId");
	    for (int i = 0; i < ids.size(); i++){
			String id = ids.get(i).get("id").toString();
			try {
				List<HashMap<String, Object>> list= bdName(id);
				String bdname=(String)list.get(0).get("bdname");
				if("预付款申请单".equals(bdname)){
					//表单名称为预付款
					yfkManager.sapYFKData(id);
				}else if("货款支付申请单".equals(bdname)){
					//表单名称为货款支付单
					hkzfManager.sapHKZFData(id);
				}else{
					 //其它费用类单据
					fyManager.sapData(id);
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
//查询表单名称
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> bdName(String id){
		List<HashMap<String, Object>> list=null;
		try {
			 list= ibatisQueryDao.getSqlMapClient().queryForList("bdname_select",id);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public YFKManager getYfkManager() {
		return yfkManager;
	}
	public void setYfkManager(YFKManager yfkManager) {
		this.yfkManager = yfkManager;
	}
	public HKZFManager getHkzfManager() {
		return hkzfManager;
	}
	public void setHkzfManager(HKZFManager hkzfManager) {
		this.hkzfManager = hkzfManager;
	}
	public FYManager getFyManager() {
		return fyManager;
	}
	public void setFyManager(FYManager fyManager) {
		this.fyManager = fyManager;
	}

}
     