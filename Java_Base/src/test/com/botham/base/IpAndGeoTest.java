package com.botham.base;

import com.botham.base.BaseHelper;
import com.botham.base.GlobalConstants;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

@RunWith(SpringRunner.class)
public class IpAndGeoTest {
	Logger log = LoggerFactory.getLogger(IpAndGeoTest.class);

	@Test
	public void IpTest() {
		if (log.isDebugEnabled()) {
			log.debug(GlobalConstants.STARTS);
		}
		BaseHelper.getIp();
		
	}

}
