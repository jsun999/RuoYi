package com.ruoyi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 订单号的工具类
*/
public class OrderNumberUtils {
	
	/**
	 * 使用随机数生成18位唯一订单号
	*/
	public static String generate18BitOrderNumber() {
		String orderNumber = RandomStringUtils.random(18, "1234567890");
		return orderNumber;
	}
	
	/**
	 * 使用UUID生成唯一订单号
	*/
	public static String generateOrderNumber() {
        String first = "OR";
		return getString(first);
	}

	private static String getString(String first) {
		int hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0) { //有可能是负数
			hashCode = -hashCode;
		}
		String hashCodeString = String.valueOf(hashCode);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateString = sdf.format(new Date());
		return first + String.format("%s%s", dateString, hashCodeString.substring(hashCodeString.length() - 9, hashCodeString.length()));
	}

	/**
	 * 使用UUID生成料号
	 */
	public static String generatePartNumber() {
		String first = "PN";
		return getString(first);
	}

}
