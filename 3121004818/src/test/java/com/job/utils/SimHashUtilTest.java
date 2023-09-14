package com.job.utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @author czh
 * @version 1.0.0
 * 2023/9/14 11:13
 */
public class SimHashUtilTest extends TestCase {

    @Test
    public void testGetSimHash() throws IOException, NoSuchAlgorithmException {
        //成功计算出simhash
        System.out.println(SimHashUtil.getSimHash("E:\\AllJavaTools\\softwarejob\\txt\\orig.txt"));
    }

    @Test
    public void testGetSimHashFail() throws IOException, NoSuchAlgorithmException {
        //失败计算,输入不存在文件
        try {
            SimHashUtil.getSimHash("E:\\AllJavaTools\\softwarejob\\txt\\orig123123.txt");
            fail();
        } catch (Exception e) {
            System.out.println("文件打开失败");
        }
    }


    @Test
    public void testCutWords() throws IOException {
        //成功
        SimHashUtil.cutWords("E:\\AllJavaTools\\softwarejob\\txt\\orig.txt");
    }

    @Test
    public void testWordsWeight() {
        //失败
        try {
            SimHashUtil.cutWords("E:\\AllJavaTools\\softwarejob\\txt\\orig14141.txt");
        } catch (IOException e) {
            System.out.println("文件打开失败");
        }
    }


    @Test
    public void testGetHash() throws NoSuchAlgorithmException {
        //map键值对类型正确即可通过
        HashMap<String, Integer> map = new HashMap<>();
        SimHashUtil.getHash(map);
    }

    public void testWeightAndMerge() {
        //map键值对类型正确即可通过
        HashMap<String, Integer> map = new HashMap<>();
        SimHashUtil.weightAndMerge(map);
    }
}












