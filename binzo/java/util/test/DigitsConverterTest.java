package binzo.java.util.test;

import org.junit.Assert;
import org.junit.Test;

import binzo.java.util.DigitsConverter;

public class DigitsConverterTest {

	@Test
	public void testConvertToSimplifiedChinese () {
		String result = "";
		result = DigitsConverter.convertToSimplifiedChinese(0);
		Assert.assertEquals("零", result);
		result = DigitsConverter.convertToSimplifiedChinese(6);
		Assert.assertEquals("六", result);
		result = DigitsConverter.convertToSimplifiedChinese(120);
		Assert.assertEquals("一百二十", result);
		result = DigitsConverter.convertToSimplifiedChinese(1009);
		Assert.assertEquals("一千零九", result);
		result = DigitsConverter.convertToSimplifiedChinese(1100000000);
		Assert.assertEquals("一十一亿", result);
		result = DigitsConverter.convertToSimplifiedChinese(1100004567);
		Assert.assertEquals("一十一亿零四千五百六十七", result);
		result = DigitsConverter.convertToSimplifiedChinese(1010101010);
		Assert.assertEquals("一十亿一千零一十万一千零一十", result);
		result = DigitsConverter.convertToSimplifiedChinese(2147483647);
		Assert.assertEquals("二十一亿四千七百四十八万三千六百四十七", result);
	}
	
	@Test
	public void convertToChineseCurrency () {
		String result = "";
		result = DigitsConverter.convertToChineseCurrency(0);
		Assert.assertEquals("人民币零元整", result);
		result = DigitsConverter.convertToChineseCurrency(1010101010);
		Assert.assertEquals("人民币壹拾亿壹仟零壹拾万壹仟零壹拾元整", result);
		result = DigitsConverter.convertToChineseCurrency(2147483647);
		Assert.assertEquals("人民币贰拾壹亿肆仟柒佰肆拾捌万叁仟陆佰肆拾柒元整", result);
	}

}
