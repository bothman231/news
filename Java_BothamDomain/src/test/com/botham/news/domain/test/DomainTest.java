
package com.botham.news.domain.test;

import org.springframework.test.context.junit4.SpringRunner;

import com.botham.domain.resource.Resource;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

@RunWith(SpringRunner.class)
public class DomainTest {
	Logger log = LoggerFactory.getLogger(DomainTest.class);

	@Test
	public void DoDomainTest() {
		Resource entity = new Resource();
		if (log.isDebugEnabled()) {
			log.debug(" Constructed entity="+entity.toString());
		}
	}

}
