package binzo.java.main;

import binzo.java.util.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number = 1100004567;
		String test = null;
		test = DigitsConverter.convertToSimplifiedChinese(number);
		System.out.println(test);
		test = DigitsConverter.convertToChineseCurrency(number);
		System.out.println(test);
		test = DigitsConverter.convertToEnglish(number);
		System.out.println(test);
		test = DigitsConverter.convertToEnglishCurrency(number);
		System.out.println(test);
	}

}
