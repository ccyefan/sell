package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author Alex
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class LoggerTest {
	
	/**
	 * @Slf4j
	 */
	private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	
	@Test
	public void test1(){
		String name = "demoo";
		String password = "12346";
	
		logger.debug("degug");
		logger.info("name:" + name + ",password:" +password);
		logger.error("error");
	}
	
}
