package com.botham.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RefreshScope
@Configuration
public class Followers {

  private static final Logger log = LoggerFactory.getLogger(Followers.class);
    
  @Value("${follower.count:10}")
  private int followers;

  public void outputFollowers() {
    log.info("===========> Followers " + followers);
  }
}