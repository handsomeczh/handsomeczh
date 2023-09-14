package com.job.utils;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author czh
 * @version 1.0.0
 * 2023/9/13 17:57
 */
//计算SimHash工具类
/*
SimHash也即相似hash，是一类特殊的信息指纹，常用来比较文章的相似度，与传统hash相比，
传统hash只负责将原始内容尽量随机的映射为一个特征值，并保证相同的内容一定具有相同的
特征值。而且如果两个hash值是相等的，则说明原始数据在一定概率下也是相等的。但通过传
统hash来判断文章的内容是否相似是非常困难的，原因在于传统hash只唯一标明了其特殊性，
并不能作为相似度比较的依据。
 */
public class SimHashUtil {
    private SimHashUtil(){}

    //获取SimHash
    public static String getSimHash(String fileName) throws IOException, NoSuchAlgorithmException {
        return weightAndMerge(getHash(wordsWeight(cutWords(fileName))));
    }

    //将文档分词
    public static ArrayList<String> cutWords(String fileName) throws IOException {
        /*
        fileName：文件名，由命令行输入
        return：strList:存放词
         */
        ArrayList<String> strList = new ArrayList<>();
        String text = FilesIOUtil.readFile(fileName);
        //使用ik分词器分词
        try (StringReader reader = new StringReader(text)) {
            IKSegmenter segmenter = new IKSegmenter(reader, true);
            Lexeme lexeme;
            while ((lexeme = segmenter.next()) != null) {
                //将每个词放入list中
                strList.add(lexeme.getLexemeText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strList;
    }

    //给每个词增加权重
    public static HashMap<String, Integer> wordsWeight(ArrayList<String> strList){
        /*
        strList:存放分词后的集合
        return:HashMap<String, Integer>:存放了每个词和权重<str，权重（出现次数）>
         */
        //存入map中<str，权重（出现次数）>
        HashMap<String, Integer> strMap = new HashMap<>();
        for (String s : strList) {
            if (strMap.containsKey(s)){
                //存在存入
                strMap.put(s,strMap.get(s)+1);
            }else{
                //加
                strMap.put(s,0);
            }
        }
        return strMap;
    }

    //计算Hash值
    //使用MD5获取hash值，并补位128
    public static HashMap<String, Integer> getHash(HashMap<String, Integer> strMap) throws NoSuchAlgorithmException {
        /*
        HashMap<String, Integer> strMap:<词，权重>
        return:HashMap<String, Integer>:<hash,权重>
         */
        HashMap<String, Integer> newstrMap = new HashMap<String, Integer>();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        strMap.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer vaule) {
                try {
                    String strHash = new BigInteger(1, messageDigest.digest(s.getBytes("UTF-8"))).toString(2);
                    //添加新的键值对  <hash，权重>，移除旧的键值对
                    //补位
                    if (strHash.length()<128){
                        int dif=128-strHash.length();
                        for (int i = 0; i < dif; i++) {
                            strHash+="0";
                        }
                    }
                    /*strMap.put(strHash,vaule);
                    strMap.remove(s);*/
                    newstrMap.put(strHash,vaule);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        /*Set<String> strs = strMap.keySet();
        for (String str : strs) {
            try {
                String strHash = new BigInteger(1, messageDigest.digest(str.getBytes("UTF-8"))).toString(2);
                //添加新的键值对  <hash，权重>，移除旧的键值对
                //补位
                if (strHash.length()<128){
                    int dif=128-strHash.length();
                    for (int i = 0; i < dif; i++) {
                        strHash+="0";
                    }
                }
                strMap.put(strHash,strMap.get(str));
                strMap.remove(str);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }*/

        strMap.clear();
        return newstrMap;
    }

    //加权、合并、降维
    public static String weightAndMerge(HashMap<String, Integer> strMap){
        /*
        HashMap<String, Integer> strMap:<词的hash值，权重>
        return:simhash
         */
        int[] v = new int[128];
        //遍历并加权合并
        strMap.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String strhash, Integer weight) {
                for (int i = 0; i < v.length; i++) {
                    if (strhash.charAt(i)=='1'){
                        v[i]+=weight;
                    }else{
                        v[i]-=weight;
                    }
                }
            }
        });
        //降维
        String himhash="";
        for (int i : v) {
            if (i>0){
                himhash+="1";
            }else{
                himhash+="0";
            }
        }
        return himhash;
    }

}
