package com.botham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.botham.base.BaseHelper;

public class StartPoint {

    private static final Logger log = LoggerFactory.getLogger(StartPoint.class);
    
	public static void main(String[] args) {
       
		log.debug(BaseHelper.getRoots().toString());

	}

}
