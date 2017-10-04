package com.botham.base.test;




import com.botham.base.GlobalConstants;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

@RunWith(SpringRunner.class)
public class GlobalConstantsTest {
	Logger log = LoggerFactory.getLogger(GlobalConstantsTest.class);

	@Test
	public void GlobalConstantsEchoTest() {
		if (log.isDebugEnabled()) {
			log.debug(GlobalConstants.STARTS);
		}
	}

}
