package night.calculate;

/**
 *  随机数工具类
 * @author dx_hualuo
 */
public class Random {
    /**
     *  获得一个在maxValue和minValue之间的随机数
     * @param maxValue 最大值
     * @param minValue 最小值
     * @return 随机数
     */
	public static int getInt(int maxValue,int minValue){
        int resultInt;
		if(maxValue > minValue){
			int different = maxValue - minValue;
			resultInt = (int)(Math.random()*(different+1)) + minValue;
			return resultInt;
		}
		else if(minValue > maxValue){
			int different = minValue - maxValue;
			resultInt = (int)(Math.random()*(different+1)) + maxValue;
			return resultInt;
		}
		return -1;
	}
    /**
     *  获得一个在int值可存范围之间的随机数
     * @return 随机数
     */
    public static int getInt(){
	    return getInt(Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
}













