package night.calculate;

/**
 * 	数学基本操作
 * @author dx_hualuo
 */
public class Maths {
	/**
	 *  计算一个double值的几次方
	 * @param num 要计算的数
	 * @param squ 几次方
	 * @return num的squ次方的结果
	 */
	public static double square(double num, int squ) {
		double value;
		value = num;
		for (int res = 1; res < squ; res++) {
			value *= num;
		}
		return value;
	}

}