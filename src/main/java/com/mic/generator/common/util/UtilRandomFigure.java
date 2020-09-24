package com.mic.generator.common.util;


import com.mic.generator.common.exception.UtilException;

import java.util.Map;

public class UtilRandomFigure {

	/**
	 * 获取数值概率平均分配的随机数
	 * @param min 随机数最小值
	 * @param max 随机数最大值
	 * @return 获得值为最小最大之间的概率平均的随机数
	 */
	public static int getRandomInt(int min, int max) {
		// TODO Auto-generated method stub
		int returnval = -1;
		int perint = max-min+1;
		returnval = (int) (Math.random()*perint);
		return returnval;
	}

	/**
	 * 平均返回0--99int值
	 * @return
	 */
	public static int getRandom100Per() {
		return getRandomInt(0, 99);
	}

	/**
	 * 获取区域概率中的随机数.
	 * mapregion是概率区域，key为区域值，value为概率。sum（value）必为100。   key为分各个区域的值。例如map：{45:40},{65:30},{85:20},{100:10}
	 * minval~45的概率是40%，46~65的概率30%，66~85的概率20%，86~100概率为10                                                                    %。    mapregion的最后的map，key可以不是随机数区域的最大值。比如可以改为{95:10}
	 * 那么就是86~95的概率为10%。96~100的值则不会出现。
	 * @param minval 随机数最小值
	 * @param mapregion 概率区域
	 * @return 区域概率的随机数
	 */
	public static int getRegionInt(int minval, Map<Integer, Integer> mapregion) throws UtilException{
		// TODO Auto-generated method stub
		int resultval = -1;
		if(mapregion==null||mapregion.size()==0) {
			throw new UtilException("概率区域map为null！！");
		}

		int ranint = getRandom100Per();
		int keyint_region_begin = 0;
		int percentcount = 0;
		for(Integer keyRegion : mapregion.keySet()) {
			percentcount = percentcount + keyRegion;
			if(percentcount>100) {
				throw new UtilException("概率大于100");
			}
			if(percentcount<ranint) {
				//没在区间内
				keyint_region_begin = keyRegion;
				continue;
			}
			resultval = getRandomInt(keyint_region_begin, keyRegion);
			break;
		}
		return resultval;
	}

}
