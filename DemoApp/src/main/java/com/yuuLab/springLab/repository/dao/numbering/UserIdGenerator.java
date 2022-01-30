package com.yuuLab.springLab.repository.dao.numbering;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * userIdを生成する。
 * 実際にはプロジェクトに合わせた採番処理を実施する。
 * @author yuuLab
 *
 */
public class UserIdGenerator {
	
	private static final AtomicInteger cnt = new AtomicInteger(0);
	
	private static final int max = 999;
	
	private static int getIncrementedValue() {
		return cnt.getAndUpdate(prev -> prev < max ? prev + 1 : 0);
	}
	
	/**
	 * userIdを採番する。なお、今回userIdはサンプルのため単純なタイムスタンプと連番の組み合わせで生成することとする。
	 * @return userId
	 */
	public static String numberUserId() {
		return DateFormatUtils.format(new Date(), "YYYYMMDDHHSS") + fillDigitZero(getIncrementedValue(), 3);
	}
	
	/**
	 * targetを指定桁数(numberOfDigits)で0埋めする。
	 * @param target 0埋め対象
	 * @param numberOfDigits 指定桁数
	 * @return 0埋めした文字列
	 */
	private static String fillDigitZero(int target, int numberOfDigits) {
		String pattern = "%0xd".replace("x", String.valueOf(numberOfDigits));
		return String.format(pattern, target);
	}

}
