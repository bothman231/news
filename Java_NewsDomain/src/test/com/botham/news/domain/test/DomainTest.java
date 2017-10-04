
package com.botham.news.domain.test;

import org.springframework.test.context.junit4.SpringRunner;

import com.botham.base.GlobalConstants;
import com.botham.news.domain.jobs.Jobs;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

@RunWith(SpringRunner.class)
public class DomainTest {
	Logger log = LoggerFactory.getLogger(DomainTest.class);

	@Test
	public void DoDomainTest() {
		Jobs jobs = new Jobs();
		if (log.isDebugEnabled()) {
			log.debug(" Constructed jobs="+jobs.toString());
		}
	}

}
