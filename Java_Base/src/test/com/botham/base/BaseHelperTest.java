package com.botham.base;

import com.botham.base.BaseHelper;
import com.botham.base.GlobalConstants;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

import org.junit.Test;

@RunWith(SpringRunner.class)
public class BaseHelperTest {
	
	Logger log = LoggerFactory.getLogger(BaseHelperTest.class);

	//@Test
	public void ConfigRootTest() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug(GlobalConstants.STARTS);
		}
		
		String configRoot=BaseHelper.getConfigRoot();
		System.out.println("configRoot="+configRoot);
		log.debug("configRoot="+configRoot);
	}
	
	//@Test
	public void SystemNameTest() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug(GlobalConstants.STARTS);
		}
		String systemName=BaseHelper.getSystemName();
		System.out.println("systemName="+systemName);
		log.debug("systemName="+systemName);
	}
	
	@Test
	public void LoadPropsTest() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug(GlobalConstants.STARTS);
		}
		Properties properties=null;
		//properties=BaseHelper.getPropValues("c:\\blu", "");
		//properties=BaseHelper.getPropValues("c:\\unique", "A");
		properties=BaseHelper.getPropValues("c:"+
			                                 System.getProperty("file.separator")+"unique", "");


	}

}
