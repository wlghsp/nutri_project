package kr.co.bteam.lombok;

import kr.co.bteam.lombok.LombokTestClass;

public class LogmbokTastClassMain {
	
	public static void main(String[] args) {
		/*
		 * LombokTestClass ltc01 = new LombokTestClass("ha", "jk", 18, 1, true, 172.5);
		 */
		LombokTestClass ltc01 = new LombokTestClass("ha", "jk", 18, '1', true, 172.5);
		LombokTestClass ltc02 = new LombokTestClass();		
		
		
		System.out.println(ltc01.toString());
		System.out.println(ltc02.toString());
		
	}
	
}
