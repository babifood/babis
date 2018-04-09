package com.seeyon.apps.shbb.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.seeyon.apps.shbb.manager.SHBBSapManager;

public class SHBBTask {
	 private static final Log log = LogFactory.getLog(SHBBTask.class);
     private SHBBSapManager shbbSapManager;
	
     public void excute1()throws Exception{
		log.info("定时器执行任务:测试-start");		 
			shbbSapManager.saptimer();
		log.info("定时器执行任务:测试-end");	
     }

	public SHBBSapManager getShbbSapManager() {
		return shbbSapManager;
	}

	public void setShbbSapManager(SHBBSapManager shbbSapManager) {
		this.shbbSapManager = shbbSapManager;
	}


	
}
