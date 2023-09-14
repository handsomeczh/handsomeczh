package com.job.utils;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author czh
 * @version 1.0.0
 * 2023/9/14 11:13
 */
public class HammingUtilTest extends TestCase {

    @Test
    public void testGetHammingDistanceFail() {
        //simhash长度不同不通过
        int distance = HammingUtil.getHammingDistance("10101010", "1001");
        assertEquals(-1,distance);
    }

    @Test
    public void testGetHammingDistance() {
        //simhash长度相同通过
        int distance = HammingUtil.getHammingDistance("101", "100");
        assertEquals(1,distance);
    }

    @Test
    public void testSimilarity() {
        //计算相似度
        assertEquals(1.0,HammingUtil.similarity("101", "111"));
    }
}