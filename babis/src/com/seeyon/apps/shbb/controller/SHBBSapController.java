package com.seeyon.apps.shbb.controller;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.seeyon.apps.shbb.manager.FYManager;
import com.seeyon.apps.shbb.manager.HKZFManager;
import com.seeyon.apps.shbb.manager.SHBBSapManager;
import com.seeyon.apps.shbb.manager.YFKManager;


public class SHBBSapController extends SHBBBaseController{
	private static final Log log = LogFactory.getLog(SHBBSapController.class);
	private SHBBSapManager shbbSapManager;
	private YFKManager yfkManager;
	private HKZFManager hkzfManager;
	private FYManager fyManager;
	public ModelAndView sendBasicData (HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter out = response.getWriter(); 
		String[] ids = request.getParameterValues("ids[]");	
		if(ids.length>0){
			for (String id : ids){
			List<HashMap<String, Object>> list= shbbSapManager.bdName(id);
			String bdname=(String)list.get(0).get("bdname");
			String pznumber=(String)list.get(0).get("pznumber");
			//凭证编号为空
			if(pznumber==null || pznumber.equals("")){
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
			  }
			else{ 		
				//已拋送成功，返回no,不能重复拋送
	    		out.print("no");
	    		out.flush();
	    		out.close();
			   }
			}
		 } 		
		return null;
	}
	public SHBBSapManager getShbbSapManager() {
		return shbbSapManager;
	}
	public void setShbbSapManager(SHBBSapManager shbbSapManager) {
		this.shbbSapManager = shbbSapManager;
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
