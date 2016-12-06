package binzo.java.util;

public class DigitsConverter {
	
	public static boolean DEBUG = false;

	/**
	 * @author Binzo
	 * @param number 整型参数。例如：1023050405
	 * @return 根据整型参数返回大写数字。例如：一十亿二千三百零五万零四百零五
	 * @date 2016-12-6
	 */
	public static String convertToSimplifiedChinese(int number){
		final String[] TAG_DIGIT = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
		final String[] TAG_UNIT = {"", "万", "亿"}; //
		final String[] TAG_WEIGHT = {"", "十", "百", "千"}; //每四位是一个单位，个位不用加
		final int STEP = TAG_WEIGHT.length;
		String numberString = String.valueOf(number);
		char[] digits = numberString.toCharArray();
		StringBuffer sb = new StringBuffer();
		boolean allZero = true;//按步长决定每段是否全为零
		int previousDigit = -1;
		int toIndex = digits.length - 1;
		// 判断最后一个非零数在哪位上
		if (number != 0) {
			for (int i = digits.length - 1; i >= 0; i--) {
				int d = Integer.valueOf(String.valueOf(digits[i]));
				if (d == 0) {
					toIndex = i - 1;
				} else {
					break;
				}
			}
		}
		for (int i = 0; i <= toIndex; i++) {
			// 获取每位上的数字d
			int d = Integer.valueOf(String.valueOf(digits[i]));
			String digit = TAG_DIGIT[d];
			// 反转，for循环是低位~高位，反转后即高位~低位
			int index = digits.length - i - 1;
			String unit = TAG_UNIT[index/STEP];
			String weight = TAG_WEIGHT[index%STEP];
			if (d != 0) allZero = false;
			if (DEBUG && true){
				System.out.printf("index=%d;", index);
				System.out.printf("digit=%s;", digit);
				System.out.printf("unit=%s;\t", unit);
				System.out.printf("allZero=%b;\t", allZero);
				System.out.printf("weight=%s\n", weight);
			}
			
			if (d == 0) {
				// 如果当前位是"0"，并且上一位不为"0"，则加"零"
				if (previousDigit > 0 && previousDigit != 0) {
					// 如果是个位为"0"，那么不能加"零"
					if (index % STEP != 0) {
						sb.append(digit);
					}
				} else if (number == 0) { //如果传入的参数就是"0"本身，那么"零"为最终结果
					sb.append(digit);
				}
			} else {
				sb.append(digit);
				sb.append(weight);
			}

			// 如果到了"万"位、"亿"位，或者到了最后一个非"0"位的情况下，需要加单位
			if (index % STEP == 0 || i == toIndex) { // 万位、亿 位
				if (!allZero) { // 如果此段不全为"0"的话，添加"万"、"亿"
					sb.append(unit);
				}
				allZero = true;
			}
			
			previousDigit = d;
		}
		if (DEBUG){
			System.out.printf("toIndex=%d\n", toIndex);
			System.out.printf("目标参数:%d, ", number);
			System.out.printf("返回结果:%s\n", sb.toString());
		}
		return sb.toString();
	}
	
	/**
	 * @author Binzo
	 * @param number 整型参数。例如：1023050405
	 * @return 根据整型参数返回大写的货币形式。例如：人民币壹拾亿贰仟叁佰零伍万零肆佰零伍圆整
	 * @date 2016-12-6
	 */
	public static String convertToChineseCurrency(int number){
		final String[] TAG_DIGIT = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
		final String[] TAG_UNIT = {"", "万", "亿"}; //
		final String[] TAG_WEIGHT = {"", "拾", "佰", "仟"}; //每四位是一个单位，个位不用加
		final int STEP = TAG_WEIGHT.length;
		String numberString = String.valueOf(number);
		char[] digits = numberString.toCharArray();
		StringBuffer sb = new StringBuffer();
		boolean allZero = true;//按步长决定每段是否全为零
		int previousDigit = -1;
		int toIndex = digits.length - 1;
		// 判断最后一个非零数在哪位上
		if (number != 0) {
			for (int i = digits.length - 1; i >= 0; i--) {
				int d = Integer.valueOf(String.valueOf(digits[i]));
				if (d == 0) {
					toIndex = i - 1;
				} else {
					break;
				}
			}
		}
		sb.append("人民币");
		for (int i = 0; i <= toIndex; i++) {
			// 获取每位上的数字d
			int d = Integer.valueOf(String.valueOf(digits[i]));
			String digit = TAG_DIGIT[d];
			// 反转，for循环是低位~高位，反转后即高位~低位
			int index = digits.length - i - 1;
			String unit = TAG_UNIT[index/STEP];
			String weight = TAG_WEIGHT[index%STEP];
			if (d != 0) allZero = false;
			if (DEBUG && true){
				System.out.printf("index=%d;", index);
				System.out.printf("digit=%s;", digit);
				System.out.printf("unit=%s;\t", unit);
				System.out.printf("allZero=%b;\t", allZero);
				System.out.printf("weight=%s\n", weight);
			}
			
			if (d == 0) {
				// 如果当前位是"0"，并且上一位不为"0"，则加"零"
				if (previousDigit > 0 && previousDigit != 0) {
					// 如果是个位为"0"，那么不能加"零"
					if (index % STEP != 0) {
						sb.append(digit);
					}
				} else if (number == 0) { //如果传入的参数就是"0"本身，那么"零"为最终结果
					sb.append(digit);
				}
			} else {
				sb.append(digit);
				sb.append(weight);
			}

			// 如果到了"万"位、"亿"位，或者到了最后一个非"0"位的情况下，需要加单位
			if (index % STEP == 0 || i == toIndex) { // 万位、亿 位
				if (!allZero) { // 如果此段不全为"0"的话，添加"万"、"亿"
					sb.append(unit);
				}
				allZero = true;
			}
			
			previousDigit = d;
		}
		sb.append("元整");
		if (DEBUG){
			System.out.printf("toIndex=%d\n", toIndex);
			System.out.printf("目标参数:￥%d, ", number);
			System.out.printf("返回结果:%s\n", sb.toString());
		}
		return sb.toString();
	}

	/**
	 * @author Binzo
	 * @param number 整型参数。例如：1023050405
	 * @return 根据整型参数返回美国货币大写形式。例如：U.S. DOLLARS ONE BILLION,TWENTY-THREE MILLION,FIFTY THOUSAND,FOUR HUNDRED AND FIVE ONLY
	 */
	public static String convertToEnglish(int number){
		final String[] TAG_DIGIT = {"", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
		final String[] TAG_TEEN = {"TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"};
		final String[] TAG_DECADE = {"", "", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY"};
		final String[] TAG_WEIGHT = {"", "THOUSAND", "MILLION", "BILLION"};
		final String TAG_HUNDRED = "HUNDRED";
		String numberString = String.valueOf(number);
		char[] digits = numberString.toCharArray();
		int length = digits.length;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digits.length; i++) {
			int d = Integer.valueOf(String.valueOf(digits[i]));
			String digit = TAG_DIGIT[d];
			int index = length - i - 1;
			int weight_index = (length - i) % 3 == 0 ? (length - i) / 3 - 1 : (length - i) / 3;
			String weight = TAG_WEIGHT[weight_index];
			if (DEBUG){
				System.out.printf("d=%d, ", d);
				System.out.printf("index=%d, ", index);
				System.out.printf("digit=%s, ", digit);
				System.out.printf("weight=%s\n", weight);
			}
			if (index % 3 == 0) {
				// 如果是首位是过渡位，如1,234，解析为1 thousand
				if (i == 0 && d != 0) {
					sb.append(digit).append(" ");
				}
				if (weight_index != 0) {
					sb.append(weight).append(",");
				}
			} else if (index % 3 == 2 && d != 0) {
				// 进入此条件，需要解析123,123中的1 hundred
				// 百直接对应数字即可（也就是1 hundred）
				sb.append(digit).append(" ");
				sb.append(TAG_HUNDRED).append(" ");
			} else if (index % 3 == 1) {
				// 进入此条件，即需要解析12,123的12 twelve
				// 或者解析32,556中的32 thirty-two
				int dPrevious = Integer.valueOf(String.valueOf(digits[i-1]));
				int dNext = Integer.valueOf(String.valueOf(digits[i+1]));
				if (d == 0 && dNext != 0) {
					if (dPrevious != 0)
						sb.append("AND ");
					sb.append(TAG_DIGIT[dNext]).append(" ");
				} else if (d == 1) {
					if (dPrevious != 0)
						sb.append("AND ");
					sb.append(TAG_TEEN[dNext]).append(" ");
				} else {
					if (dPrevious != 0)
						sb.append("AND ");
					sb.append(TAG_DECADE[d]);
					if (dNext != 0) {
						sb.append("-").append(TAG_DIGIT[dNext]).append(" ");
					} else {
						sb.append(" ");
					}
				}
			}
		}
		if (DEBUG){
			System.out.printf("%d : ", number);
			System.out.println(sb.toString());
		}
		return sb.toString();
	}
	
	/**
	 * @author Binzo
	 * @param number 整型参数。例如：1023050405
	 * @return 根据整型参数返回英文形式。例如：ONE BILLION,TWENTY-THREE MILLION,FIFTY THOUSAND,FOUR HUNDRED AND FIVE ONLY
	 */
	public static String convertToEnglishCurrency(int number){
		StringBuffer sb = new StringBuffer();
		sb.append("U.S. DOLLARS ");
		sb.append(convertToEnglish(number));
		sb.append("ONLY");
		if (DEBUG){
			System.out.printf("$%d : ", number);
			System.out.println(sb.toString());
		}
		return sb.toString();
	}
	
	
}