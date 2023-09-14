package com.job.utils;

/**
 * @author czh
 * @version 1.0.0
 * 2023/9/13 17:59
 */
//计算海明距离

public class HammingUtil {
    private HammingUtil(){}
    //计算距离
    public static int getHammingDistance(String simhash01,String simhash02){
        int distance=0;
        if (simhash01.length()!=simhash02.length()){
            return -1;
        }else{
            for (int i = 0; i < simhash01.length(); i++) {
                if (simhash01.charAt(i)!=simhash02.charAt(i)){
                    distance++;
                }
            }
        }
        return distance;
    }

    //计算形式度(包括计算海明距离)
    public static double similarity(String simhash01,String simhash02){
        int distance=getHammingDistance(simhash01,simhash02);
        return 0.01 * (100 - distance * 100 / 128);
    }

}
