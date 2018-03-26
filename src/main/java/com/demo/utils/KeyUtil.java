package com.demo.utils;

import java.util.Random;

import javax.transaction.Synchronization;

/**
 * 格式：事件 + 随机数
 * @author Alex
 *
 */
public class KeyUtil {
	
	public static synchronized String genUniqueKey(){

		Random random = new Random();
		
		Integer number = random.nextInt(900000) + 100000;
		
		return System.currentTimeMillis() + String.valueOf(number)	;
	}
	
}
