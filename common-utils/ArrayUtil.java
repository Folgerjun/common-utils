package com.sharpen.array;

import java.util.List;

/**
 * 对数组的操作类
 * 
 * @author Administrator
 *
 */
public class ArrayUtil {

	/**
	 * 两个byte数组拼接
	 * 
	 * @param byteOne
	 * @param byteTow
	 * @return
	 */
	public static byte[] byteMerger(byte[] byteOne, byte[] byteTow) {

		byte[] resByte = new byte[byteOne.length + byteTow.length];
		System.arraycopy(byteOne, 0, resByte, 0, byteOne.length);
		System.arraycopy(byteTow, 0, resByte, byteOne.length, byteTow.length);
		return resByte;
	}

	/**
	 * 多个byte数组拼接
	 * 
	 * @param byteArr
	 *            待拼接的byte数组
	 * @return 拼接后的byte数组
	 */
	public static byte[] copyMbyte(byte[]... byteArr) {

		byte[] resByteArr = new byte[] {};

		// 循环将byteArr中的byte数组拼接
		for (byte[] tbyte : byteArr) {
			resByteArr = byteMerger(resByteArr, tbyte);
		}
		return resByteArr;
	}

	/**
	 * 集合转数组
	 * 
	 * @param dataList
	 *            传入的集合
	 * @return 转换后的数组(若集合为null或者集合中无元素 则返回null)
	 */
	public static String[] list2Arr(List<String> dataList) {

		// 如果传入的集合为空或者集合中没有元素 返回null
		if (dataList == null || dataList.size() <= 0) {
			return null;
		}
		String[] array = new String[dataList.size()];

		array = dataList.toArray(array);

		return array;

	}

	public static void main(String[] args) {

	}

}
