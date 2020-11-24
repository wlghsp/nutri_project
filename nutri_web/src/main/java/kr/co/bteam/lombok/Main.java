package kr.co.bteam.lombok;

public class Main {
public static void main(String[] args) {
	LombokTest lt01 = new LombokTest("haha","ha",22,true,true,188.8);
	LombokTest lt02 = new LombokTest();
	System.out.println(lt01);System.out.println(lt02.toString());
}
}
