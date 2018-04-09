package com.seeyon.apps.shbb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.seeyon.ctp.common.AbstractSystemInitializer;

public class SHBBPluginInitializer extends AbstractSystemInitializer {
	private static Log log = LogFactory.getLog(SHBBPluginInitializer.class);

	@Override
	public void initialize() {
		log.info("上海区客开插件启动_中饮食品集团有限公司_SAP对接");
	}

	@Override
	public void destroy() {
		log.info("上海区客开插件启动_中饮食品集团有限公司_SAP对接销毁");
		super.destroy();
	}

}